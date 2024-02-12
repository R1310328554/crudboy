package com.luokup.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.poi.excel.WorkbookUtil;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.apache.poi.ss.usermodel.*;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author luokai
 */
public class GenerateSqlUtil {

    private static boolean DEFAULT_REQUIRED = false;
    public static String TABLE_PREFIX = "yjgl_"; // yjxt

    public static Map<String, String> tableAndFieldPrefixs = new HashMap();
    public static Map<String, String> fieldAliases = new HashMap();
//    public static Map<String, String> knownDictTypes = new HashMap();
//    public static Map<String, String> knownDictDatas = new HashMap();

    public static Properties knownDictTypes = new Properties();
    public static Properties knownDictDatas = new Properties();
    static MultiValueMap<String, String> fieldAliases2 = new LinkedMultiValueMap<>();
    private static boolean justDict = true;

    private static boolean yjgl_dicts_vue = true;
    private static boolean genSqlForce = true;

    /**
     * 标志人工添加的字段
     */
    public static String MARK = "__arti__";

    static {
        tableAndFieldPrefixs.put("保康商务酒店", "星级饭店");
        tableAndFieldPrefixs.put("森林消防队伍与装备", "队伍");
        tableAndFieldPrefixs.put("保康县应急物资储备库", "储备库");
        tableAndFieldPrefixs.put("乡镇（街道）减灾能力", "乡镇（街道）");
        tableAndFieldPrefixs.put("应急管理局", "单位");
        tableAndFieldPrefixs.put("政府灾害管理能力", "单位");
        tableAndFieldPrefixs.put("矿山救护中队", "队伍");
        tableAndFieldPrefixs.put("社会组织减灾能力", "组织");
        tableAndFieldPrefixs.put("社区居委会应急避难场所", "应急避难场所");

//		['01', '组织名称', '', '（文字说明）'],
//		['02', '机构地址', '', '（文字说明）'],
        tableAndFieldPrefixs.put("社区（行政村）减灾能力", "社区（行政村）");
        tableAndFieldPrefixs.put("乡（镇）基础指标统计表", "单位");
        tableAndFieldPrefixs.put("企业基础信息", "企业");
        tableAndFieldPrefixs.put("保康县体育场", "体育场");
        tableAndFieldPrefixs.put("保康县地质灾害监测和工程防治调查表", "体育场");
        tableAndFieldPrefixs.put("保康县景区", "景区");
        tableAndFieldPrefixs.put("保康县物资仓库", "储备库");
        tableAndFieldPrefixs.put("加油加气加氢站", "体育场");
        tableAndFieldPrefixs.put("保康县百货有限公司", "企业");
        tableAndFieldPrefixs.put("医疗卫生机构", "医疗卫生机构");
//        tableAndFieldPrefixs.put("县域基础指标统计表", "医疗卫生机构");
        tableAndFieldPrefixs.put("学校", "学校（机构）");
        tableAndFieldPrefixs.put("提供住宿的社会服务机构", "单位");
        tableAndFieldPrefixs.put("湖北省襄阳市国际影城", "单位");
        tableAndFieldPrefixs.put("金属非金属地下矿山承灾体", "矿山");

//        林业
        tableAndFieldPrefixs.put("历史森林和草原火灾调查", "火灾");
        tableAndFieldPrefixs.put("防火指挥机构调查", "单位");
        tableAndFieldPrefixs.put("消防专业(半专业)队伍调查", "队伍");
        tableAndFieldPrefixs.put("经批准野外用火调查", "用火");
//        tableAndFieldPrefixs.put("调查单位基本情况", "调查");
        tableAndFieldPrefixs.put("违规野外用火调查", "用火");
        tableAndFieldPrefixs.put("重要火源点调查", "火源点");
        tableAndFieldPrefixs.put("防火信息指挥系统调查", "调查");
        tableAndFieldPrefixs.put("防火物资储备库调查", "储备库");
//        tableAndFieldPrefixs.put("防火瞭望监测系统设施调查", "监测");
//        tableAndFieldPrefixs.put("防火道路调查", "道路");

//        List<String> ff = new ArrayList<>();
//        ff.add("占地面积");
//        ff.add("基地建筑面积");

        fieldAliases.put("占地面积", "面积");
        fieldAliases.put("基地建筑面积", "面积");
//        fieldAliases.put("营区用地面积", "面积");

        fieldAliases.put("详细地址", "地址");
        fieldAliases.put("机构地址", "地址");
        fieldAliases.put("位置", "地址");
        fieldAliases.put("地点", "地址");

        // 电话 = contact 如果冲突？
        fieldAliases.put("联系方式", "电话");
        fieldAliases.put("电话号码", "电话");
//        fieldAliases.put("调查人电话", "电话");

        fieldAliases.put("名", "名称");
//        fieldAliases.put("调查单位", "名称");
        fieldAliases.put("姓名", "人名称");
//        fieldAliases.put("所属企业名称", "名称");
//        fieldAliases.put("单位名称", "名称");
        fieldAliases.put("调查人姓名", "调查人");

//        fieldAliases.put("行政编码", "编码");
        fieldAliases.put("调查区域", "区域");

        fieldAliases.put("质检", "检查");
        fieldAliases.put("核查", "检查");
        fieldAliases.put("审核", "检查");

        InputStream inputStream = Chinese2VariableNameUtil.class.getResourceAsStream("/knownDictDatas.properties");
        InputStream typeIs = Chinese2VariableNameUtil.class.getResourceAsStream("/knownDictTypes.properties");
        try {
            if (inputStream != null) {
                knownDictDatas.load(inputStream);
            }
            if (typeIs != null) {
                knownDictTypes.load(typeIs);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        所属企业名称
//        若有， 是否有
        knownDictDatas.put("__是__否", "infra_boolean_string");
        knownDictTypes.put("数据状态", "data_status");
    }

    public static void savePropertiesAll() {
        saveProperties(knownDictDatas , "/knownDictDatas.properties");
        saveProperties(knownDictTypes , "/knownDictTypes.properties");
    }

    public static void saveProperties(Properties properties, String path) {
        //保存文件
        try {
            URL fileUrl = PropertiesUtil.class.getResource(path);//得到文件路径
            FileOutputStream fos = new FileOutputStream(new File(fileUrl.toURI()));
            properties.store(fos, path);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//     翻译的时候， 和 字略去。。 中英翻译， 并且采用缩写。。 todo
    public static void main(String[] args) throws Exception {
//        generateSql();
//        Chinese2VariableNameUtil.useCache = false;

        String dir = "D:\\d\\xg\\减灾能力、野外火源和历史火灾调查表格";
        dir = "D:\\d\\xg\\yjgl\\应急管理最新1009资料\\最新1009资料\\保康林业普查减灾能力整改后数据\\森林火灾风险评估和区划成果\\火险预警系统设施调查表.xlsx";
        dir = "D:\\d\\xg\\yjgl\\整理后1020资料\\最新1009资料\\保康林业普查减灾能力整改后数据\\减灾能力\\森林火灾风险评估和区划成果\\防火通信指挥系统调查表.xlsx";
        dir = "D:\\d\\xg\\yjgl\\整理后1020资料\\最新1009资料\\保康林业普查减灾能力整改后数据\\减灾能力\\森林火灾风险评估和区划成果\\其它防火基础设施调查表.xlsx";
        dir = "D:\\d\\xg\\yjgl\\整理后1020资料\\最新1009资料\\保康林业普查减灾能力整改后数据\\减灾能力\\护林员队伍调查表.xlsx";
        dir = "D:\\d\\xg\\yjgl\\整理后1020资料\\最新1009资料\\保康林业普查减灾能力整改后数据\\减灾能力\\森林火灾风险评估和区划成果\\调查单位基本情况表.xls";
        dir = "C:\\Users\\xd\\Documents\\崩塌风险点信息调查表.xlsx";
        dir = "C:\\Users\\xd\\Documents\\交通\\保康县风险点与高边坡台账\\公路高边坡信息调查表.xlsx";

        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            System.out.println("arg = " + arg);
        }

        String optype = "genDdlSQL";
        if (args.length > 0) {
            optype = args[0];
        }

        String dir2 = "D:\\d\\git\\py\\allPdf1233333333.txt";
        if (args.length > 1) {
            dir = args[1];
            dir2 = args[1];
        }

        if ("genDdlSQL".equalsIgnoreCase(optype)) {
            readAllExcelTitles(dir);
        } else if ("genDictSQL".equalsIgnoreCase(optype)) {
            testGenDisct2();
        } else if ("genDdlSQLPdf".equalsIgnoreCase(optype)) {
            readerMethod(dir2);
        } else if ("genDdlSQLee".equalsIgnoreCase(optype)) {
            readAllExcelTitles(dir);
        } else {
//        strings1 = genDict("目前稳定状况", "", delimiter);
//        strings1 = genDict("结构物破损情况", "是、否、", delimiter);

//        List<String> ret = genCategory(dir);
//        dir = "D:\\d\\xg\\应急管理局\\承灾体";
//        ret = genCategory(dir);
//        dir = "D:\\d\\xg\\应急管理局\\减灾能力";
//        ret = genCategory(dir);
        }


    }

    private static void testGenDisct2() {

        String delimiter = "、|,";
        List<String> strings1 = genDict("用火类型", "已经批准、未批准、违反规定、", delimiter);
        strings1 = genDict("基建投资额类型", "大手笔、小手笔、巨额、民间、", delimiter);// 别名， 变形类型
        strings1 = genDict("传输网络类型", "LAN、WAN、SAN、", delimiter);// 别名， 变形类型
        strings1 = genDict("运行状态", "运行中、异常、停止、", delimiter);// 别名， 变形类型
        strings1 = genDict("火源点类型", "运行中、异常、停止、", delimiter);// 别名， 变形类型

        // YJ_EESOAAN = 'yj_Eesoaan',  TODO， 为什么前面这个字母是大写

    }

    private static void testGenDisct1() {

        String delimiter = "、|,";
        List<String> strings1 = genDict("变形类型", "蠕变、剧烈、腐蚀、", delimiter);
        strings1 = genDict("运动类型", "", delimiter);// 别名， 变形类型

        strings1 = genDict("斜坡类型", "路堤边坡、路堑边坡", delimiter);
        strings1 = genDict("斜坡结构类型", "土石、沙土、大理石、混泥土", delimiter);
        strings1 = genDict("险情等级", "", delimiter);
//        strings1 = genDict("防治点分级", "", delimiter);
        strings1 = genDict("规模等级", "", delimiter);
        strings1 = genDict("管理层级", "", delimiter);
        strings1 = genDict("监测等级", "", delimiter);
        strings1 = genDict( "技术等级", "一级、二级、三级、四级、等外、其他", delimiter);
        strings1 = genDict( "公路技术等级", "一级公路、二级公路、三级公路、四级公路、等外公路", delimiter);

//        strings1 = genDict("是否", "是、否、未知", delimiter);

        strings1 = genDict("诱发灾害类型", "", delimiter);
        strings1 = genDict( "灾害类型", "崩塌、水毁、沉陷与塌陷、滑坡、高边坡", delimiter);

        strings1 = genDict("成因分类", "", delimiter);
        strings1 = genDict("道路类型", "农村公路、城市公路、山路", delimiter);

        strings1 = genDict("危害程度", "轻微、严重、一般、", delimiter);
        strings1 = genDict("灾害发育程度", "轻微、严重、一般", delimiter); // 可以认为是和 危害程度 相同
        strings1 = genDict("灾情等级", "一级（严重）、二级（告警）、三级（一般）、四级（低）", delimiter);

        strings1 = genDict("断通状况", "双向通行、半幅通行、单向通行、无法通行、部分通行、", delimiter);
        strings1 = genDict("面层类型", "沥青混凝土、钢筋混凝土、石板", delimiter);

        strings1 = genDict("防护设施类型", "", delimiter);
        strings1 = genDict("防洪标准", "", delimiter);
        strings1 = genDict("排水设施类型", "", delimiter);

        strings1 = genDict("灾害处治情况", "未处治修复、已处治修复、", delimiter);
    }


    /**
     *
     * 其实也不需要什么数据字典， 也不是强制性需要。
     *
     * 数据字典的优势在于， 可以选择， 不用手动输入，但是劣势在于 难维护， 不动态。
     *
     * 我们可以在编辑页面的输入框的地方， 给与 可用的tags 列表。 然后 可以限制只能从已有tags 中选择， 也可以不做任何限制： 他想用tag 就用， 不用就自己输入。
     *
     * 后台 检测是否有新的 tag， 如果是， 那么。。
     *
     * @param comment
     */
    private static List<String > genDict(final String comment) {
        if (StringUtils.isEmpty(comment)) {
            return null;
        }
//        String comment = "应急避难种类|||☑ 地震 □台风 ☑ 洪水 ☑ 地质灾害 ☑ 火灾（含森林火灾） □其他灾害(自然灾害) □综合灾害(自然灾害) □事故灾难 □其他突发事件（突发公共卫生事 件、社会安全事件）";
        String[] fieldAndComments = comment.split("\\|\\|\\|");
        String fieldName = fieldAndComments[0];
        String commentArr = fieldAndComments[1].replaceAll("\\s+", "");
        String delimiter = "☑|□";
        return genDict(fieldName, commentArr, delimiter);
    }

    private static List<String > genDict(final String fieldName, final String fieldComment) {
        if (StringUtils.isEmpty(fieldComment)) {
            System.out.println("跳过数据字典的创建： " + fieldName);
            return null;
        }
        String delimiter = "☑|□";
        return genDict(fieldName, fieldComment, delimiter);
    }

    private static List<String > genDict(final String fieldName, final String fieldComment, String delimiter) {
//        String comment = "应急避难种类|||☑ 地震 □台风 ☑ 洪水 ☑ 地质灾害 ☑ 火灾（含森林火灾） □其他灾害(自然灾害) □综合灾害(自然灾害) □事故灾难 □其他突发事件（突发公共卫生事 件、社会安全事件）";
        List<String > sqls = new ArrayList<>();
        String transRetHead = Chinese2VariableNameUtil.hanzi2FiledName(fieldName);

        if (StringUtils.isEmpty(fieldName)) {
            System.out.println("跳过数据字典的创建： " + fieldName);
            return null;
        }

        if (StringUtils.isEmpty(fieldComment)) {
            System.out.println("跳过数据字典的创建： " + fieldComment);
            return null;
        }
        
        String[] options = fieldComment.split(delimiter);
        if (options.length <= 1) {
            System.out.println("非选择项， 不需要数据字典 = " + fieldComment);
            return sqls;
        }
        String comment__ = fieldComment.replaceAll(delimiter, "__").trim();
        if (knownDictDatas.containsKey(comment__)) {
            // 考虑通用的数据字典， 因为 并不需要每一个字段都创建一个数据字典吧！！ 很多时候是可以共用的啊
            if (genSqlForce) {

            } else {
            }
            System.out.println("已经存在数据字典， 不需要再次添加 = " + comment__);
            // transRetHead = knownDictDatas.getProperty(comment__);
            return sqls;
        } else {
        }

        System.out.println("准备创建数据字典 = " + fieldName + ", " + fieldComment);

        transRetHead = "yj_" + transRetHead;

        sqls.add("\n");
        String set = "set @dict_name = '"+fieldName+"';\n" +
                "set @dict_type = '"+transRetHead+"';\n";
        String headSql = "insert into system_dict_type (name, type ) values (@dict_name, @dict_type);\n";
        sqls.add(set);
        sqls.add(headSql);
        sqls.add("\n");

        Set<String> allDicts = new HashSet<>();
        for (int j = 0; j < options.length; j++) {
            final String option0 = options[j];
            String option = option0;
            int i1 = option.indexOf("(");
            if (i1 < 0) {
                i1 = option.indexOf("（");
                if (i1 > 0) {
                    option = option.substring(0, i1).trim();
                }
            }
            if (StrUtil.isNotEmpty(option)) {
                option = option.trim();
                if (allDicts.contains(option)) {
                    System.out.println(option0+ " == 重复了 == " + option);
                    option = option0;
                }
                if (allDicts.contains(option)) {
                    System.out.println(option0+ " == 还是== 重复了 == " + option);
                    continue;
                }
                allDicts.add(option);
                String transRet = Chinese2VariableNameUtil.translate(option);
//                        String insertTypeSql = "insert into system_dict_type values()";
                String detailSet = "set @dict_data_label = '"+option +"';\n" +
                        "set @dict_data_val = '"+transRet+"';\n";
                String detailSql = "insert into system_dict_data (label, value, dict_type ) values (@dict_data_label, @dict_data_val, @dict_type );\n";
                sqls.add(detailSet);
                sqls.add(detailSql);
            }
        }
        knownDictTypes.put(fieldName, transRetHead);
        knownDictDatas.put(comment__, transRetHead);

        for (int i = 0; i < sqls.size(); i++) {
            String s =  sqls.get(i);
            System.out.println("" + s);
        }

        return sqls;
    }

    private static List<String> genCategory(String dir) {
        List<String> et = new ArrayList<>();
        File d = new File(dir);
        for (File file : d.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".xls") || name.endsWith(".xlsx")|| name.endsWith(".pdf");
            }
        })) {
            boolean file1 = file.isFile();
            if (file1) {
                TableEntity table = new TableEntity();
                String fileName = file.getName();
                String[] split = fileName.split("\\.");
                if (split.length < 2) {
                    System.out.println("aaaaaaaa = " + fileName);
                    return null;
                }
                fileName = split[0];
                table.setComment(fileName);

                if (fileName.endsWith("调查表")) {
                    fileName = fileName.substring(0, fileName.length() - 3);
                }
                if (fileName.endsWith("调查")) {
                    fileName = fileName.substring(0, fileName.length() - 2);
                }
                if (fileName.endsWith("表")) {
                    fileName = fileName.substring(0, fileName.length() - 1);
                }
                if (fileName.endsWith("统计")) {
                    fileName = fileName.substring(0, fileName.length() - 2);
                }
                if (fileName.startsWith("保康县")) {
                    fileName = fileName.substring(3);
                } else if (fileName.startsWith("保康")) {
                    fileName = fileName.substring(2);
                }


                String hanzi2FiledName = Chinese2VariableNameUtil.hanzi2FiledName(fileName);
                if (hanzi2FiledName.length() > 48) {
                    System.out.println("表名实在太长了， 还是手动修改一下吧！！：  " + hanzi2FiledName);
                }
                table.setTableName(TABLE_PREFIX+ hanzi2FiledName);
                List<String> fieldNames = new ArrayList<>();

//                String sql = "insert into yjgl_subject_category(name, code, level, type, dept, parent_id, tenant_id, subject_id, status, deleted) values (" +
//                        "'" +fileName + "', '" + hanzi2FiledName + "', 2, 3,4,5,6,7,8,false" +
//                        ")";

                String sql = "update yjgl_subject_category set code = '" + hanzi2FiledName + "' where name = '" + fileName + "';";

                et.add(sql);

                System.out.println();
                System.out.println("" + sql);
            }
        }
        return et;
    }

    /**
     * 读取json 文件内容，解析为json，
     * @param file json 文件
     * @return
     * @throws IOException
     */
    private static List<TableEntity> readerMethod(String file) throws IOException {
        FileReader fileReader = new FileReader(file);
        Reader reader = new InputStreamReader(new FileInputStream(file), "Utf-8");
        int ch = 0;
        StringBuffer sb = new StringBuffer();
        while ((ch = reader.read()) != -1) {
            sb.append((char) ch);
        }
        fileReader.close();
        reader.close();
        String jsonStr = sb.toString();

        Set<String> allFields = new HashSet<>();
        List<TableEntity> ret = new ArrayList<>();

        StringBuilder sqls = new StringBuilder();
        List<String > dictSqls = new ArrayList<>();

        JSONObject jsonObject = new JSONObject(jsonStr);
        Set<String> strings = jsonObject.keySet();
        for (Iterator<String> iterator = strings.iterator(); iterator.hasNext(); ) {
            String tableDesc = iterator.next();
            JSONArray table = jsonObject.getJSONArray(tableDesc);
            List<ExcelEntity> tableFields = new ArrayList<>();
            int size = table.size();
            Map<String, ExcelEntity> fieldNames = new HashMap<>();
            for (int i = 0; i < size; i++) {
                JSONArray fieldCfg = table.getJSONArray(i);
                String sort = fieldCfg.getStr(0);
                if (sort.equalsIgnoreCase("代码") && i == 0) {
//                    System.out.println("第一行第1列 是 代码 = " + sort);
                    continue;
                } else
                if (sort.equalsIgnoreCase("丙") && i == 1) {
//                    System.out.println("第一行第1列 是 丙 = " + sort);
                    continue;
                }
                if (sort.startsWith("——") || sort.startsWith("--") ) {
//                    System.out.println("是 注释或说明 = " + sort);
                    continue;
                }
                ExcelEntity field = new com.luokup.util.ExcelEntity();
                String unit = "";
                String str;
                String filedType = "";
                String descOrOption = "";
                String filedName = "";
                if (fieldCfg.size() == 4) {
                    str = fieldCfg.getStr(1);
                    descOrOption = fieldCfg.getStr(2);
                    unit = fieldCfg.getStr(3);
                    if (StrUtil.isNotEmpty(unit)) {
                        unit = unit.trim();
                    }
                    if (StrUtil.isEmptyIfStr(str)) {
                        str = fieldCfg.getStr(2);
                        if (StrUtil.isEmptyIfStr(str)) {
                            str = fieldCfg.getStr(3);
                        }
                    }

                } else if (fieldCfg.size() == 6) {
                    // ['代码', '指标名称', None, None, '数量', '计量单位']
                    str = fieldCfg.getStr(1);
                    if (StrUtil.isEmptyIfStr(str)) {
                        str = fieldCfg.getStr(2);
                        if (StrUtil.isEmptyIfStr(str)) {
                            str = fieldCfg.getStr(3);
                        }
                    }
                    descOrOption = fieldCfg.getStr(4);
                    unit = fieldCfg.getStr(5);
                } else {
                    System.out.println("err = " + 11111111);
                    continue;
                }

                if (StrUtil.isEmpty(str)) {
                    // 已有自然灾害应急预案类型
                    // 属于上一个 page。 pdf 被断开了。。
                    // ["","","□地质灾害（滑坡、泥石流\n、崩塌）应急预案 ☑\n地震灾害应急预案 ☑\n洪涝灾害应急预案\n□台风灾害应急预案\n□风雹（雷电、大风、龙卷\n风、冰雹）灾害应急预案\n□森林草原火灾应急预案\n□风暴潮、海啸应急预案\n□其它类型自然灾害应急预\n案（请文字说明）\n□无任何自然灾害应急预案",""]  医疗卫生机构
                    System.out.println("不可能啊， 坑爹啊 = " + fieldCfg + "  " + tableDesc);
                    continue;
                }

                String fieldNameCn = getUnifiedName(tableDesc, str);

                if (fieldNameCn.startsWith("其中：")) {
                    fieldNameCn = fieldNameCn.substring(3);
                }

                if (fieldNameCn == null) {

                    System.out.println("tableDesc = " + tableDesc);
                    System.out.println("fieldNameCn = " + fieldNameCn);
                }

                if (isStartWithNumber(fieldNameCn)) {
                    fieldNameCn = "qz_" + fieldNameCn; // qz 的含义是？
                }

                ExcelEntity excelEntity;
                if (fieldNames.containsKey(fieldNameCn)) {
                    excelEntity = fieldNames.get(fieldNameCn);
                    if (StrUtil.isEmpty(unit)) { // 一个心思不细腻， 考虑不周，判断不到位， 那么就会立即的导致各种bug..
                        System.out.println("出现重复的字段名！！ ========= " + excelEntity + " ,  at :" + tableDesc);
                    }
                    fieldNameCn = excelEntity.getFiledName() + PinyinUtil.toFirstChar(unit);
                    excelEntity.setFiledName(fieldNameCn);
                } else {
                    fieldNames.put(fieldNameCn, field);
                    excelEntity = Chinese2VariableNameUtil.str2Field(fieldNameCn);
                }
                excelEntity.setNotes(str);
                filedType = excelEntity.getFiledType();
                if (unit.equalsIgnoreCase("（文字说明）")) {
                    filedType = "varchar(256)";
                } else if (unit.equalsIgnoreCase("（单选）")) {
                    filedType = "varchar(256)";
                } else if (unit.equalsIgnoreCase("（多选）")) {
                    filedType = "varchar(256)";
                } else {

                }
                filedName = excelEntity.getFiledName();

//                field.setIndex(sort); // 索引 不是 序号..
                field.setUnit(unit);
                if (DEFAULT_REQUIRED) {
                    field.setAttribute("Y");
                    field.setRequired(true);
                }
                field.setNotes(str.replaceAll("\n", " ") + "|||" + descOrOption.replaceAll("\n", " "));
                field.setExample(descOrOption.replaceAll("\n", " "));
                field.setFiledType(filedType);
                field.setFiledName(filedName);
                tableFields.add(field);
            }

            TableEntity tableEntity = new TableEntity();

            tableEntity.setComment(tableDesc);
            tableEntity.setDept("林业部");

            String hanzi2FiledName = Chinese2VariableNameUtil.hanzi2FiledName(tableDesc);
            if (hanzi2FiledName.length() > 48) {
                System.out.println("表名实在太长了， 还是手动修改一下吧！！：  " + hanzi2FiledName);
            }
            tableEntity.setTableName(TABLE_PREFIX+ hanzi2FiledName);
            tableEntity.setFields(tableFields);
            addCommonFields(tableEntity);

            ret.add(tableEntity);

            if (! justDict) {
                String sql = generateSql(tableEntity);
                sqls.append(sql);
            }
//                break;
            List<ExcelEntity> fields = tableEntity.getFields();

            for (int i = 0; i < fields.size(); i++) {
               ExcelEntity excelEntity = fields.get(i);
                allFields.add(excelEntity.getFiledName());

                String notes = excelEntity.getNotes();
                String transRetHead = excelEntity.getFiledName();
                String[] transRetHeadArr = transRetHead.split("☑|□");
                if (transRetHeadArr.length > 1) {
                    transRetHead = transRetHeadArr[0];
//                    transRetHead = transRetHead.replaceAll("☑|□", "");
                    transRetHead = transRetHead.replaceAll("/", "_");
                }
                if (yjgl_dicts_vue) {
                    String[] options = notes.split("☑|□");
                    if (options.length > 1) {
                        transRetHead = "yj_" + transRetHead;
                        String set = transRetHead.toUpperCase() + " = '" + transRetHead + "',";
                        System.out.println(" " + set);
                        if (!dictSqls.contains(set)) {
                            dictSqls.add(set);
                        }
                    }
                } else {
                    List<String> strings1 = genDict(notes, transRetHead);
                    dictSqls.addAll(strings1);
                }
            }
        }

        if (! justDict) {
            FileUtil.writeUtf8String(sqls.toString(), "yjgl_forest.sql");
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < dictSqls.size(); i++) {
                String s =  dictSqls.get(i);
                stringBuilder.append(s);
            }

            String path = "yjgl_dicts.sql";
            if (yjgl_dicts_vue) {
                path = "yjgl_dicts_vue.sql";
            }
            FileUtil.writeUtf8String(stringBuilder.toString(), path);
            savePropertiesAll();
        }
        System.out.println("allFields = " + allFields);
        return ret;
//        jsonObject.getBeanList()
//        System.out.println(JSONObject.parseObject(jsonStr));
    }

    /**
     * 获取统一化之后的名称, 方便做查询， 简化程序！
     */
    private static String getUnifiedName(String tableDesc, String fieldName0) {
        int idx = fieldName0.indexOf("(");
        if (idx == -1) {
            idx = fieldName0.indexOf("（");
        }
        String fieldName = fieldName0;
        String left = "";
        if (idx != -1) {
            fieldName = fieldName0.substring(0, idx);
            left = fieldName0.substring(idx);
        }

        String fieldNameCn = fieldName;
        String fieldNameCn2 = fieldAliases.get(fieldName);
        if (fieldNameCn2 == null) {
            String prefix = tableAndFieldPrefixs.get(tableDesc);
            if (prefix != null) {
                if (fieldNameCn.startsWith(prefix)) {
                    fieldNameCn = fieldName.substring(prefix.length());
                }
            }
        } else {
            fieldNameCn = fieldNameCn2;
        }
        return fieldNameCn+left;
    }

    public boolean startWithNumber(String str){
        return Pattern.matches("[0-9].*", str);
    }

    //判断字符串是不是以数字开头
    public static boolean isStartWithNumber(String str) {
        if (str == null || str.length()==0) {
            System.out.println("str = " + str);
        }

        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str.charAt(0)+"");
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    private static List<TableEntity> readAllExcelTitles(String dir) throws Exception {
        File d = new File(dir);
        Set<String> allFields = new HashSet<>();
        List<TableEntity> ret = new ArrayList<>();
        StringBuilder sqls = new StringBuilder();

        if (d.isFile()) {
            extracted(allFields, ret, sqls, d);
        } else {
            for (File file : d.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.endsWith(".xls") || name.endsWith(".xlsx");
                }
            })) {
                boolean file1 = file.isFile();
                if (file1) {
                    extracted(allFields, ret, sqls, file);
                }
            }
        }

        FileUtil.writeUtf8String(sqls.toString(), "yjgldb.sql");
        System.out.println("allFields = " + allFields);
        return ret;
    }

    private static void extracted(Set<String> allFields, List<TableEntity> ret, StringBuilder sqls, File file) throws IOException {
        TableEntity table = readExcel(file);
        ret.add(table);
        String sql = generateSql(table);
        sqls.append(sql);
//                break;
        List<ExcelEntity> fields = table.getFields();
        for (int i = 0; i < fields.size(); i++) {
           ExcelEntity excelEntity = fields.get(i);
            allFields.add(excelEntity.getFiledName());
        }
    }

    private static TableEntity readExcel(File file) {
        Workbook book = WorkbookUtil.createBook(file);
        Sheet sheetAt = book.getSheetAt(0);
        TableEntity table = reaHead(sheetAt, 0);
        try {
            book.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        String fileName = file.getName();
        String[] split = fileName.split("\\.");
        if (split.length < 2) {
            System.out.println("aaaaaaaa = " + fileName);
            return null;
        }
        fileName = split[0];
        table.setComment(fileName);

        if (fileName.endsWith("表")) {
            fileName = fileName.substring(0, fileName.length() - 1);
        }
        if (fileName.endsWith("调查")) {
            fileName = fileName.substring(0, fileName.length() - 2);
        }
        if (fileName.endsWith("统计表")) {
            fileName = fileName.substring(0, fileName.length() - 3);
        }

        String hanzi2FiledName = Chinese2VariableNameUtil.hanzi2FiledName(fileName);

        if (hanzi2FiledName.length() > 48) {
            System.out.println("表名实在太长了， 还是手动修改一下吧！！：  " + hanzi2FiledName);
        }

        table.setTableName(TABLE_PREFIX+ hanzi2FiledName);
        List<String> fieldNames = new ArrayList<>();

        List<ExcelEntity> tableFields = table.getFields();
        for (int j = 0; j < tableFields.size(); j++) {
           ExcelEntity srcFromExcel = tableFields.get(j);
            String filedName = srcFromExcel.getFiledName();
            if (StrUtil.isBlank(filedName)) {
                System.out.println("竟然为空？  start" + filedName + "end");
                continue;
            }

            String filedType = srcFromExcel.getFiledType();
//          System.out.println(filedName + " = " + filedType);

            String filedNameUnified = getUnifiedName(table.getComment(), filedName);
           ExcelEntity entity2 = Chinese2VariableNameUtil.str2Field(filedNameUnified);// ???

            srcFromExcel.setNotes(filedName);

            Boolean required = entity2.getRequired();
            if (required) {
                srcFromExcel.setRequired(required);
                srcFromExcel.setAttribute(entity2.getAttribute());
            } else {
                if (DEFAULT_REQUIRED) {
                    srcFromExcel.setAttribute("Y");
                    srcFromExcel.setRequired(true);
                }
            }
            String unit = entity2.getUnit();
            if (StrUtil.isNotEmpty(unit)) {
                unit = unit.trim();
            }
            srcFromExcel.setUnit(unit);

            String filedName2 = entity2.getFiledName();
            if (fieldNames.contains(filedName2)) {
//                System.out.println("已经存在 = " + filedName2);
//                filedName2 += 2;
                if (StrUtil.isEmpty(unit)) { // 一个心思不细腻， 考虑不周，判断不到位， 那么就会立即的导致各种bug..
                    System.out.println("出现重复的字段名！！ ========= " + entity2 + " ,  at :" + filedName2);
                }
                filedName2 += "_";
                filedName2 += Chinese2VariableNameUtil.hanzi2FiledName(unit);
                if (fieldNames.contains(filedName2)) {
                    System.out.println("不会吧~~~ 又已经存在 = " + filedName2);
                }
            }
            fieldNames.add(filedName2);
            srcFromExcel.setFiledName(filedName2);

            if (filedType.equalsIgnoreCase("BLANK") ||
                    filedType.equalsIgnoreCase("STRING")) {
                srcFromExcel.setFiledType(entity2.getFiledType());
            } else {
                System.out.println("filedType ================== " + filedType);
            }
        }
        table.setFields(tableFields);
        addCommonFields(table);
        return table;
    }

    private static void addCommonFields(TableEntity table) {
       ExcelEntity entity = new com.luokup.util.ExcelEntity();
        entity.setAttribute("Y");
        entity.setNotes("主键");
        entity.setRequired(true);
        entity.setUnit("");
        entity.setFiledName("id");
        entity.setFiledType("bigint");
        table.getFields().add(0, entity);

        entity = new com.luokup.util.ExcelEntity();
        entity.setAttribute("Y");
        entity.setNotes("调查对象分类ID");
        entity.setRequired(true);
        entity.setUnit("");
        entity.setFiledName("subject_category_id");
        entity.setFiledType("bigint");
        table.getFields().add(1, entity);

//        entity = new ExcelEntity();
//        entity.setAttribute("Y");
//        entity.setNotes("调查对象分类");
//        entity.setRequired(true);
//        entity.setUnit("");
//        entity.setFiledName("subject_category_name");
//        entity.setFiledType("varchar(256)");
//        table.getFields().add(2, entity);
    }


    /**
     * 生成sql文件
     *
     * @return
     */
    private static String generateSql(TableEntity result) throws IOException{
//        System.out.println("请输入excel路径：");
//        String filePath = "C:\\Users\\xd\\Downloads\\OA系统开发计划 (1).xlsx";
//        filePath = "D:\\d\\xg\\减灾能力、野外火源和历史火灾调查表格\\护林员队伍调查表.xls";
//        String sheetName = "填报情况";

//        System.out.println("================================开始生成SQL，共"+result.size()+"个业务字段================================");
        StringBuilder indexSb = null;

        String tableName = result.getTableName(); //表名
        String tableNotes = result.getComment(); //注释
        if (StringUtils.isEmpty(tableName)) {
            return "err ";
        }

        List<ExcelEntity> fields = result.getFields();
        if (CollectionUtils.isEmpty(fields)) {
            return "err ";
        }

        JSONObject json = new JSONObject(true);// 有序
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("-- %s：%s\n", tableName, tableNotes));
        sb.append(String.format("DROP TABLE IF EXISTS %s;\n", tableName));
        int size = fields.size();
        Set<String> allFields = new HashSet<>();
        if (size > 0) {
            sb.append(String.format("CREATE TABLE %s (\n", tableName));
            indexSb = new StringBuilder();
            for (int i = 0; i < size; i++) {
               ExcelEntity entity = fields.get(i);
                String filedName = entity.getFiledName();
                if (StringUtils.isEmpty(filedName)) {
                    continue;
                }
                allFields.add(filedName);
                sb.append(String.format("\t%s %s", filedName, entity.getFiledType()));
                String type = entity.getFiledType();
                String attribute = entity.getAttribute();
                if (filedName.equalsIgnoreCase("id")) {
                    sb.append(" AUTO_INCREMENT  PRIMARY KEY  " ); //设置主键
                } else if (type.contains("char") || type.contains("text")) {
//                    sb.append(" CHARACTER SET utf8mb4 "); //字符串、文本设置编码 # 不需要， 表上 统一设置即可
                }
                if (attribute != null && attribute.contains("Y")) {
                    sb.append(" NOT NULL ");
                }
//                    else sb.append(" NULL DEFAULT NULL ");
                sb.append(String.format(" COMMENT '%s'", entity.getNotes()));

                if (StrUtil.isNotBlank(entity.getIndex())) { //设置索引
                    //INDEX `index_name`(`field1`, `field2`) USING BTREE
                    indexSb.append("\tINDEX `");
                    String[] index = entity.getIndex().split(":");
                    indexSb.append(index[0]);
                    indexSb.append("`(`");
                    indexSb.append(index[1]);
                    indexSb.append("`)");
                    indexSb.append(" USING BTREE,\n");
                }
//                if (i < size - 1 || indexSb.length() > 0)
                sb.append(",");
                sb.append("\n");
                json.putOpt(filedName,entity.getNotes());
            }
            if (indexSb.length() > 0) {
//                sb.append(indexSb.substring(0, indexSb.lastIndexOf(",")));
//                sb.append("\n");
            }

//            调查人 investigator_name
//              audit_time
//            name
// 数据 采集  2303
// 数据 流量  2304
// 数据 展示  2305

            if(!allFields.contains("name")) {
                sb.append("  `name` varchar(64)   DEFAULT '' COMMENT '名称',\n");
                // todo 如果不存在 name ，但是存在 unit_name 或者  survey_name， 那么把这个名字改为 name ？？
            }
            if(!allFields.contains("code")) {
                sb.append("  `code` varchar(64)    DEFAULT '' COMMENT '编码',\n");
            }
            if(!allFields.contains("address")) {
                sb.append("  `address` varchar(64)    DEFAULT '' COMMENT '地址',\n");
            }
//            if(!allFields.contains("province")) {
//                sb.append("  `province` varchar(64)    DEFAULT '' COMMENT '省',\n");
//            }
//            if(!allFields.contains("city")) {
//                sb.append("  `city` varchar(64)    DEFAULT '' COMMENT '市',\n");
//            }
//            if(!allFields.contains("county")) {
//                sb.append("  `county` varchar(64)    DEFAULT '' COMMENT '县',\n");
//            }
//            if(!allFields.contains("district")) {
//                sb.append("  `district` varchar(64)    DEFAULT '' COMMENT '区',\n");
//            }
//            if(!allFields.contains("village")) {
//                sb.append("  `village` varchar(64)    DEFAULT '' COMMENT '村',\n");
//            }
//            if(!allFields.contains("region")) {
//                sb.append("  `region` varchar(64)    DEFAULT '' COMMENT '区域',\n");
//            }
//            if(!allFields.contains("town")) {
//                sb.append("  `town` varchar(64)    DEFAULT '' COMMENT '乡镇',\n");
//            }
            if(!allFields.contains("contact") && !allFields.contains("phone")) { // 不就是电话吗？
                sb.append("  `contact` varchar(64)    DEFAULT '' COMMENT '联系方式',\n");
            }
//            if(!allFields.contains("phone")) { // 不就是电话吗？
//                sb.append("  `phone` varchar(64)    DEFAULT '' COMMENT '电话',\n");
//            }
            if(!allFields.contains("status")) {
                sb.append("  `status` varchar(64)    DEFAULT '' COMMENT '数据状态',\n");
            }
            if(!allFields.contains("data_source")) {
                sb.append("  `data_source` varchar(64)    DEFAULT '' COMMENT '数据来源',\n");
            }

            if(!allFields.contains("survey_man")) {
                sb.append("  `survey_man` varchar(64)    DEFAULT '' COMMENT '调查人',\n");
            }

//            if(!allFields.contains("unit")) {
//                sb.append("  `unit` varchar(64)    DEFAULT '' COMMENT '调查单位',\n");
//            }
            if(!allFields.contains("survey_unit") && !allFields.contains("unit")) { // ??? 是否可以统一？？
                sb.append("  `survey_unit` varchar(64)    DEFAULT '' COMMENT '调查单位',\n");
            }
            if(!allFields.contains("survey_time")) {
                sb.append("  `survey_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '调查时间',\n");
            }
            if(!allFields.contains("audit_time")) {
                sb.append("  `audit_time` datetime NULL COMMENT '核查时间',\n");
            }
            if(!allFields.contains("audit_man")) {
                sb.append("  `audit_man` varchar(64)    DEFAULT '' COMMENT '核查人',\n");
            }
            if(!allFields.contains("audit_ret")) {
                sb.append("  `audit_ret` varchar(64)    DEFAULT '' COMMENT '核查结果',\n");
            }

            sb.append("" +
//                    "  `investigate_id` bigint NULL DEFAULT 0 COMMENT '调查人id',\n" +
                    "  `survey_man_id` bigint NULL DEFAULT 0 COMMENT '调查人id" + MARK + "',\n" + // todo
//                    "  `survey_name` varchar(64)    DEFAULT '' COMMENT '调查人',\n" +
//                    "  `survey_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '调查时间',\n" +

//                    "  `rectify_man_id` bigint NULL DEFAULT 0 COMMENT '整改人id',\n" +
//                    "  `rectify_man` varchar(64)    DEFAULT '' COMMENT '整改人',\n" +
//                    "  `rectify_ret` varchar(64)    DEFAULT '' COMMENT '整改结果',\n" +
//                    "  `rectify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '整改时间',\n" +

//                    "  `qac_man_id` bigint NULL DEFAULT 0 COMMENT '质检人id',\n" +
//                    "  `qac_ret` varchar(64)    DEFAULT '' COMMENT '质检结果',\n" +
//                    "  `qac_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '质检时间',\n" +

                    "  `audit_man_id` bigint NULL DEFAULT 0 COMMENT '核查人id',\n" +
//                    "  `audit_man` varchar(64)    DEFAULT '' COMMENT '核查人',\n" +
//                    "  `audit_ret` varchar(64)    DEFAULT '' COMMENT '核查结果',\n" +
//                    "  `audit_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '核查时间',\n" +

//                    "  `status` varchar(64)    DEFAULT '' COMMENT '状态',\n" +

                    "  `creator` varchar(64)    DEFAULT '' COMMENT '创建者',\n" +
                    "  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',\n" +
                    "  `updater` varchar(64)    DEFAULT '' COMMENT '更新者',\n" +
                    "  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '更新时间',\n" +
                    "  `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号',\n" +
                    "  `deleted` bit(1) NOT NULL DEFAULT 0 COMMENT '是否删除'" +
                    "\n");
            sb.append(String.format(") ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT '%s' ;", tableNotes));
//            sb.append(String.format(") ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '%s' ROW_FORMAT = Dynamic;", tableNotes));
            sb.append("\n");
        }

        System.out.println(" "+sb.toString());
//        File file = new File("C:\\Users\\Admin\\Desktop\\1.sql");
//        FileOutputStream fos1 = new FileOutputStream(file);
//        OutputStreamWriter dos1 = new OutputStreamWriter(fos1);
//        dos1.write(sb.toString());
//        dos1.close();
        return sb.toString();
    }


    /**
     * 需要一个工具， 一键把一个普通的Excel表格， 转为建表语句、并导入，并生成前端、后端！！
     *
     * @param sheet
     * @param startRowNo
     * @return
     */
    private static TableEntity reaHead(Sheet sheet, int startRowNo ) {
        TableEntity table = new TableEntity();
        List<ExcelEntity> list = new ArrayList<>();
        int lastRowNum = sheet.getLastRowNum();
        if (startRowNo > lastRowNum) {
            return table;
        }
        for (int i = startRowNo; i <= lastRowNum; i++) {
            Row row = sheet.getRow(i);
            if (isEmptyRow(row)) {
                System.out.println("empty =========== " + i);
//                map.put("list", list);
                return table;
            }
            Cell cell = row.getCell(0);
            if (cell == null) {
            }
            if (cell.getCellType() != CellType.BLANK) {
            }
            int physicalNumberOfCells = row.getPhysicalNumberOfCells();

            Row rowValue = sheet.getRow(i+1);
            for (int j = 0; j < physicalNumberOfCells; j++) {
                Cell cell1 = row.getCell(j);
                CellType cellType = cell1.getCellType();
                String stringCellValue = cell1.getStringCellValue();

               ExcelEntity entity = new ExcelEntity();
                entity.setFiledType(cellType.name());
                entity.setFiledName(stringCellValue);

                if (rowValue != null) {
                    Cell cell2 = rowValue.getCell(j);
                    if (cell2 == null) {
                        // 森工集团(非必填) 为什么报错..
                        System.out.println("cell2 = " + stringCellValue + " " + cell1);
                    } else {
                        CellType cellType2 = cell2.getCellType();
                        entity.setFiledType(cellType2.name());
                    }
                }

                list.add(entity);
            }
            table.setFields(list);
            break;
        }
        table.setTableName(sheet.getSheetName());
//        map.put("tableName", sheet.getSheetName());
        return table;
    }

    public static boolean isEmptyRow(Row row) {
        if (row == null || row.toString().isEmpty()) {
            return true;
        } else {
            Iterator<Cell> it = row.iterator();
            boolean isEmpty = true;
            while (it.hasNext()) {
                Cell cell = it.next();
                if (cell.getCellType() != CellType.BLANK) {
                    isEmpty = false;
                    break;
                }
            }
            return isEmpty;
        }
    }

}
