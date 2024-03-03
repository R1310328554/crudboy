package cn.iocoder.yudao.module.asc.service.filetraining;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpUtil;
import cn.iocoder.yudao.framework.file.core.client.FileClient;
import cn.iocoder.yudao.framework.file.core.client.FileClientConfig;
import cn.iocoder.yudao.module.infra.dal.dataobject.file.FileConfigDO;
import cn.iocoder.yudao.module.infra.service.file.FileConfigService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

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
//        String knowledge_base_name = "";
        String knowledge_base_name = fileTraining.getFileCategory();
        String fileId = fileTraining.getFileId();
//        http://127.0.0.1:48080/admin-api/infra/file/4/get/c94608b129bd9ea40bcba3f17be321e7e2144985a6e022ccf21909ddd8f8e380.jpg
        int i = fileId.indexOf(INFRA_FILE);
        String result = null;
        if (i > -1) {
            HashMap<String, Object> paramMap = new HashMap<>();
            int fileCfgId = Integer.parseInt(fileId.substring(i + INFRA_FILE_LEN, i + INFRA_FILE_LEN + 2));
            FileConfigDO fileConfig = fileConfigService.getFileConfig(Long.valueOf(fileCfgId));
//            FileClientConfig config = fileConfig.getConfig();
            FileClient fileClient = fileConfigService.getFileClient(Long.valueOf(fileCfgId));
            try {
                byte[] content = fileClient.getContent(fileId);

//                String docs = "docs={\n" +
//                        "                        \"test.txt\": [\n" +
//                        "                {\n" +
//                        "                    \"page_content\": \"custom doc\",\n" +
//                        "                        \"metadata\": {},\n" +
//                        "                    \"type\": \"Document\"\n" +
//                        "                }";
                String docs = "{}";
                paramMap.put("to_vector_store", true);
                paramMap.put("override", 250);
                paramMap.put("not_refresh_vs_cache", false);
                paramMap.put("chunk_overlap", 50);
                paramMap.put("knowledge_base_name", knowledge_base_name);
                paramMap.put("docs", docs);
//                paramMap.put("files", "files=@日志系统选型.xlsx;type=application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//文件上传只需将参数中的键指定（默认file），值设为文件对象即可，对于使用者来说，文件上传与普通表单提交并无区别
                paramMap.put("files", content);
                result = HttpUtil.post(ASC_LLM_HOST + "/knowledge_base/upload_docs", paramMap);
                System.out.println("result = " + result);
//                  config.getBasePath() + path;
            } catch (Exception e) {
//                throw new RuntimeException(e);
                e.printStackTrace();
                try {
                    paramMap.put("knowledge_base_name", ASC_LLM_DEFAULT_KNOWLEDGE_BASE_NAME);
                    result = HttpUtil.post(ASC_LLM_HOST + "/knowledge_base/upload_docs", paramMap);
                    System.out.println("result = " + result);
                } catch (Exception e2) {
                    e2.printStackTrace();

                }
            }
            if (fileCfgId == 4) {
                // 从数据库的 infra_file_content 表获取
            } else if (fileCfgId == 5) {

            } else if (fileCfgId == 6) {
                // 从数据库的 infra_file_content 表获取
            }

            /*
            {
              "code": 200,
              "msg": "文件上传与向量化完成",
              "data": {
                "failed_files": {}
              }
            }
             */
        }
        FileTrainingRespVO ret = new FileTrainingRespVO();
        ret.setTrainingStatus(result);
        ret.setTokenCnt(Long.valueOf(123));
        return ret;
    }

    @Override
    public List<FileTrainingRespVO> train(List<Long> ids) {
        List<FileTrainingRespVO> ret = new ArrayList<>();
        if (ids != null && ids.size() > 0) {
            for (int i = 0; i < ids.size(); i++) {
                Long aLong = ids.get(i);
                FileTrainingRespVO train = train(aLong);
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
         */
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("vector_store_type", "faiss");
        llm_model = "bge-large-zh-v1.5";
        paramMap.put("embed_model", llm_model);
        paramMap.put("knowledge_base_name", knowledgeBaseName);
        String result = HttpUtil.post(ASC_LLM_HOST + "/knowledge_base/upload_docs", paramMap);
        return result;
    }

}
