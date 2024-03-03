package cn.iocoder.yudao.module.asc.service.filetraining;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.http.HttpUtil;
import cn.iocoder.yudao.framework.file.core.client.FileClient;
import cn.iocoder.yudao.framework.file.core.client.FileClientConfig;
import cn.iocoder.yudao.framework.file.core.client.local.LocalFileClient;
import cn.iocoder.yudao.framework.file.core.client.local.LocalFileClientConfig;
import cn.iocoder.yudao.module.infra.dal.dataobject.file.FileConfigDO;
import cn.iocoder.yudao.module.infra.service.file.FileConfigService;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.select.Evaluator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.io.File;
import java.net.URL;
import java.util.*;
import cn.iocoder.yudao.module.asc.controller.admin.filetraining.vo.*;
import cn.iocoder.yudao.module.asc.dal.dataobject.filetraining.FileTrainingDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.asc.convert.filetraining.FileTrainingConvert;
import cn.iocoder.yudao.module.asc.dal.mysql.filetraining.FileTrainingMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.asc.enums.ErrorCodeConstants.*;

/**
 * 文档训练 Service 实现类
 *
 *
 curl -X 'POST' \
 'http://home.asc-ai.cn:8000/knowledge_base/upload_docs' \
 -H 'accept: application/json' \
 -H 'Content-Type: multipart/form-data' \
 -F 'to_vector_store=true' \
 -F 'override=false' \
 -F 'not_refresh_vs_cache=false' \
 -F 'chunk_size=250' \
 -F 'chunk_overlap=50' \
 -F 'files=@日志系统选型.xlsx;type=application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' \
 -F 'knowledge_base_name=11' \
 -F 'docs={
 "test.txt": [
 {
 "page_content": "custom doc",
 "metadata": {},
 "type": "Document"
 }
 ]
 }'

 * @author 超级管理员
 */
@Service
@Validated
public class FileTrainingServiceImpl implements FileTrainingService {

    public static final String INFRA_FILE = "/infra/file/";
    public static final int INFRA_FILE_LEN = "/infra/file/".length();
    @Value("${asc.llm.host:http://home.asc-ai.cn:8000}")
    public final String ASC_LLM_HOST = "http://home.asc-ai.cn:8000";

    @Value("${asc.llm.default.knowledge_base_name:luokai}")
    public final String ASC_LLM_DEFAULT_KNOWLEDGE_BASE_NAME = "luokai";

    @Resource
    private FileTrainingMapper mapper;
    @Resource
    private FileConfigService fileConfigService;

    @Value("${asc.llm.default.llm_model:text2vec-bge-large-chinese}")
    private String llm_model = "text2vec-bge-large-chinese";



    /*

2024-03-03 21:14:51.401 |  WARN 9324 | http-nio-48080-exec-8 [TID: N/A] org.apache.tika.parsers.PDFParser        | JBIG2ImageReader not loaded. jbig2 files will be ignored
See https://pdfbox.apache.org/2.0/dependencies.html#jai-image-io
for optional dependencies.
TIFFImageWriter not loaded. tiff files will not be processed
See https://pdfbox.apache.org/2.0/dependencies.html#jai-image-io
for optional dependencies.
J2KImageReader not loaded. JPEG2000 files will not be processed.
See https://pdfbox.apache.org/2.0/dependencies.html#jai-image-io
for optional dependencies.

2024-03-03 21:14:51.546 |  WARN 9324 | http-nio-48080-exec-8 [TID: N/A] org.apache.tika.parser.SQLite3Parser     | org.xerial's sqlite-jdbc is not loaded.
Please provide the jar on your classpath to parse sqlite files.
See tika-parsers/pom.xml for the correct version.

     */

    @Override
    public Long create(FileTrainingCreateReqVO createReqVO) {
        // 插入
        FileTrainingDO fileTraining = FileTrainingConvert.INSTANCE.convert(createReqVO);
        mapper.insert(fileTraining);
        // 返回
        return fileTraining.getId();
    }

    @Override
    public void update(FileTrainingUpdateReqVO updateReqVO) {
        // 校验存在
        validateExists(updateReqVO.getId());
        // 更新
        FileTrainingDO updateObj = FileTrainingConvert.INSTANCE.convert(updateReqVO);
        mapper.updateById(updateObj);
    }

    @Override
    public void delete(Long id) {
        // 校验存在
        validateExists(id);
        // 删除
        mapper.deleteById(id);
    }

    private void validateExists(Long id) {
        if (mapper.selectById(id) == null) {
            throw exception(FILE_TRAINING_NOT_EXISTS);
        }
    }

    @Override
    public FileTrainingDO get(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public List<FileTrainingDO> getList(Collection<Long> ids) {
        return mapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<FileTrainingDO> getPage(FileTrainingPageReqVO pageReqVO) {
        return mapper.selectPage(pageReqVO);
    }

    @Override
    public List<FileTrainingDO> getList(FileTrainingExportReqVO exportReqVO) {
        return mapper.selectList(exportReqVO);
    }

//    @Override
    public FileTrainingRespVO train(Long id) {
        FileTrainingDO fileTraining = mapper.selectById(id);
        String trainingStatus = fileTraining.getTrainingStatus();
        if ("训练完成".equalsIgnoreCase(trainingStatus)) {
            FileTrainingRespVO ret = new FileTrainingRespVO();
            ret.setTrainingStatus("之前训练完成， 重复执行？ ");
//            ret.setRemark("");
            ret.setTokenCnt(fileTraining.getTokenCnt());
            return ret;
        }
//        String knowledge_base_name = "";
        String knowledge_base_name = fileTraining.getFileCategory();
        String fileId = fileTraining.getFileId();
        fileId = fileId.replaceAll("\\\\", "/");
//        http://127.0.0.1:48080/admin-api/infra/file/4/get/c94608b129bd9ea40bcba3f17be321e7e2144985a6e022ccf21909ddd8f8e380.jpg
        int i = fileId.indexOf(INFRA_FILE);
        String result = null;
        HashMap<String, Object> paramMap = new HashMap<>();
        long length = 0;
        if (i > -1) {
            try {
                int fileCfgId = Integer.parseInt(fileId.substring(i + INFRA_FILE_LEN, i + INFRA_FILE_LEN + 1));
                if (fileCfgId == 4) {
                    // 从数据库的 infra_file_content 表获取
                } else if (fileCfgId == 5) {

                } else if (fileCfgId == 6) {
                    // 从数据库的 infra_file_content 表获取
                }

                FileConfigDO fileConfig = fileConfigService.getFileConfig(Long.valueOf(fileCfgId));
                LocalFileClientConfig config = (LocalFileClientConfig) fileConfig.getConfig();
//            FileClientConfig config = fileConfig.getConfig();
                LocalFileClient fileClient = (LocalFileClient) fileConfigService.getFileClient(Long.valueOf(fileCfgId));
                String path = fileId.substring(fileId.lastIndexOf("/") + 1);
//                fileClient.getBasePath()
//                String docs = "docs={\n" +
//                        "                        \"test.txt\": [\n" +
//                        "                {\n" +
//                        "                    \"page_content\": \"custom doc\",\n" +
//                        "                        \"metadata\": {},\n" +
//                        "                    \"type\": \"Document\"\n" +
//                        "                }";
                String docs = "{}";
                paramMap.put("to_vector_store", true);
                paramMap.put("override", false);
                paramMap.put("not_refresh_vs_cache", false);
                paramMap.put("chunk_size", 250);
                paramMap.put("chunk_overlap", 50);
                paramMap.put("knowledge_base_name", knowledge_base_name);
                paramMap.put("docs", docs);
//                paramMap.put("files", "files=@日志系统选型.xlsx;type=application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//文件上传只需将参数中的键指定（默认file），值设为文件对象即可，对于使用者来说，文件上传与普通表单提交并无区别

//                byte[] content = fileClient.getContent(path);
//                paramMap.put("files", content); //  [B cannot be cast to [Ljava.lang.Object;

                URL a = FileTrainingServiceImpl.class.getResource("/");
                String path1 = a.getPath();
                System.out.println("path1 = " + path1);
                a = FileTrainingServiceImpl.class.getResource(".");
                path1 = a.getPath();
                System.out.println("path1 = " + path1);
                String filePath = config.getBasePath() + File.separator + path;
                System.out.println("filePath = " + filePath); // /Users/xxx
                String path2 = filePath;
                if (OSinfo.isWindows()) {
                    path2 = "D:" + filePath;
                }


                File ff = new File(path2);
                length = ff.length();
                System.out.println("ff = " + ff);
                System.out.println("ff = " + ff.exists());

                path2 = path2.replaceAll("\\\\", "/");
                System.out.println("path2 = " + path2);
                ff = new File(path2);
                System.out.println("ff = " + ff);
                System.out.println("ff = " + ff.exists());
                paramMap.put("files", FileUtil.file(path2));
                result = HttpUtil.post(ASC_LLM_HOST + "/knowledge_base/upload_docs", paramMap);
                System.out.println("result = " + result);
//                  config.getBasePath() + path;

            } catch (IORuntimeException e) {
                e.printStackTrace();
                result = "err:IORuntimeException";
            } catch (ClassCastException e) {
                result = "err:暂不支持； 仅支持本地文件存储 ";
            } catch (Exception e) {
//                throw new RuntimeException(e);
                e.printStackTrace();
                try {
                    paramMap.put("knowledge_base_name", ASC_LLM_DEFAULT_KNOWLEDGE_BASE_NAME);
                    result = HttpUtil.post(ASC_LLM_HOST + "/knowledge_base/upload_docs", paramMap);
                    System.out.println("result = " + result);

                    fileTraining.setTrainingStatus("训练完成");
                    fileTraining.setFileName(result);
                    fileTraining.setFileSize(Math.toIntExact(length));
                    fileTraining.setRemark(result);
                    mapper.updateById(fileTraining);

                } catch (Exception e2) {
                    e2.printStackTrace();

                }
            }
            /*
            {
              "code": 200,
              "msg": "文件上传与向量化完成",
              "data": {
                "failed_files": {}
              }
            }

            result = {"code":200,"msg":"文件上传与向量化完成","data":{"failed_files":{}}}

            result = {"detail":[{"loc":["body","files",0],"msg":"Expected UploadFile, received: <class 'str'>"
            ,"type":"value_error"},
            {"loc":["body","override"],
            "msg":"value could not be parsed to a boolean","type":"type_error.bool"}]}

            result = {"detail":[{"loc":["body","files",0],"msg":"Expected UploadFile, received: <class 'str'>","type":"value_error"}]}

             */
        }
        FileTrainingRespVO ret = new FileTrainingRespVO();
        ret.setTrainingStatus("训练完成");
        ret.setFileName(fileTraining.getFileName());
        ret.setId(fileTraining.getId());
        ret.setFileSize(fileTraining.getFileSize());
        ret.setFileId(fileTraining.getFileId());
        ret.setRemark(result);
        ret.setTokenCnt(Long.valueOf(length));
        return ret;
    }

    @Override
    public List<FileTrainingRespVO> train(Collection<Long> ids) {
        List<FileTrainingRespVO> ret = new ArrayList<>();
        if (ids != null && ids.size() > 0) {
            for (Iterator<Long> iterator = ids.iterator(); iterator.hasNext(); ) {
                Long aLong = iterator.next();
//                System.out.println("aLong = " + aLong);
                FileTrainingRespVO train = null;
                try {
                    train = train(aLong);
                } catch (Exception e) {
//                    throw new RuntimeException(e);
                    e.printStackTrace();
                }
                ret.add(train);
            }
        }
        return ret;
    }

    @Override
    public String createKnowledgeBase(String knowledgeBaseName) {
        /*
        {
          "knowledge_base_name": "samples",
          "vector_store_type": "faiss",
          "embed_model": "bge-large-zh-v1.5"
        }

        返回：
        {
          "code": 500,
          "msg": "创建知识库出错： loop of ufunc does not support argument 0 of type NoneType which has no callable conjugate method",
          "data": null
        }

        {
          "code": 200,
          "msg": "已新增知识库 业务流程",
          "data": null
        }

        {
          "code": 404,
          "msg": "已存在同名知识库 samples",
          "data": null
        }
          "data": "{\"detail\":[{\"loc\":[\"body\",\"knowledge_base_name\"],\"msg\":\"field required\",\"type\":\"value_error.missing\"},{\"loc\":[\"body\",\"vector_store_type\"],\"msg\":\"field required\",\"type\":\"value_error.missing\"},{\"loc\":[\"body\",\"embed_model\"],\"msg\":\"field required\",\"type\":\"value_error.missing\"}]}",

         */
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("vector_store_type", "faiss");
//        llm_model = "bge-large-zh-v1.5"; 这个 行不通
        paramMap.put("embed_model", llm_model);
        paramMap.put("knowledge_base_name", knowledgeBaseName);
//        HttpClientBuilder.create().setDefaultHeaders()
//        String result = HttpUtil.post(ASC_LLM_HOST + "/knowledge_base/create_knowledge_base", paramMap, ew);

        String result = "";
        DefaultHttpClient client = new DefaultHttpClient();
        String url = ASC_LLM_HOST + "/knowledge_base/create_knowledge_base";
        HttpPost post = new HttpPost(url);
        JSONObject json = new JSONObject(paramMap);
        System.out.println("json = " + json);
        JSONObject response = null;
        try {
            StringEntity s = new StringEntity(json.toString());
            s.setContentEncoding("UTF-8");
            s.setContentType("application/json");//发送json数据需要设置contentType
            post.setEntity(s);
            HttpResponse res = client.execute(post);
            if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                HttpEntity entity = res.getEntity();
                  result = EntityUtils.toString(entity);// 返回json格式：
//                response = JSONObject.(result);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("result = " + result);
        return result;
    }

}
