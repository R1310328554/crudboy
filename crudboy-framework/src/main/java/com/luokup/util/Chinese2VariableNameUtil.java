package com.luokup.util;

import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import org.apache.logging.log4j.util.PropertiesUtil;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author luokai
 */
public class Chinese2VariableNameUtil {


//    填表单位	填表人	联系方式	省	市	县	乡镇(森林草原经营单位)名称	护林员数量（人）	总管护面积（hm²）	平均管护面积（hm²/人）	信息化个人终端配备情况	森工集团(非必填)	林业管理局(非必填)	林业局(非必填)	林场(非必填)

    static Properties properties = new Properties();
    static Properties translateCacheProperties = new Properties();
    public static Boolean useCache = true;

    static {
        try {
            // 读取 classpath 目录
            InputStream inputStream = Chinese2VariableNameUtil.class.getResourceAsStream("/chinese2VarName.properties");
            InputStream translateCache = Chinese2VariableNameUtil.class.getResourceAsStream("/translateCache.properties");
            // 读取 classpath
//            InputStream inputStream = ClassLoader.getSystemResourceAsStream("chinese2VarName.properties");
            System.out.println("inputStream = " + inputStream);
            Reader inputStreamReader = new InputStreamReader(inputStream, "UTF-8"); // 必须要这样才能转码； 默认InputStream都是使用ancii编码
//            BufferedReader fr = new BufferedReader(inputStreamReader);
//            fr.lines().forEach(System.out::println);
//            InputStream inputStream3 = new BufferedInputStream(new FileInputStream("chinese2VarName.properties"));
//            InputStream inputStream2 = Chinese2VariableNameUtil.class.getResourceAsStream("chinese2VarName.properties");
            properties.load(inputStreamReader);
            translateCacheProperties.load(translateCache);
//            Enumeration<Object> keys = properties.keys();
//            while (keys.hasMoreElements()) {
//                Object o = keys.nextElement();
//                System.out.println("o = " + o);
//            }

//            ResourceBundle rb2 = ResourceBundle.getBundle("chinese2VarName");//读取resources/ 目录下的application.properties
//            for(String key : rb2.keySet()){
//                String value = rb2.getString(key);
//                System.out.println(key + ":" + value);
//            }
//            ResourceBundle rb2 = ResourceBundle.getBundle("config/application");//读取resources/config目录下的application.properties
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String asd = "受害草原面积(公顷)(非必填)";
        asd = "调查单位基本情况";
//                victim_cy_area
        asd = "应急避难种类2";
        if (args.length > 0) {
            asd = args[0];
        }

        ExcelEntity excelEntity = str2Field(asd);
        System.out.println("excelEntity = " + excelEntity);

//        todo 	jksjbgsfgyddzghsw1myss/fs/f varchar(256) CHARACTER SET utf8mb4  NOT NULL  COMMENT '井口设计标高是否高于当地最高洪水位1m以上',

//        String namex = extractFromSql("");

        System.out.println("asd = " + asd);

    }

    private static String  extractFromSql(String asd) {

        asd = "\n" +
                " -- 菜单 SQL\n" +
                "INSERT INTO system_menu(\n" +
                "    name, permission, type, sort, parent_id,\n" +
                "    path, icon, component, status, component_name\n" +
                ")\n" +
                "VALUES (\n" +
                "    '防火指挥机构调查管理', '', 2, 0, 2304,\n" +
                "    'fire-prevent-command-org-survey', '', 'yjgl/firePreventCommandOrgSurvey/index', 0, 'FirePreventCommandOrgSurvey'\n" +
                ");\n" +
                "\n" +
                "-- 按钮父菜单ID\n" +
                "-- 暂时只支持 MySQL。如果你是 Oracle、PostgreSQL、SQLServer 的话，需要手动修改 @parentId 的部分的代码\n" +
                "SELECT @parentId := LAST_INSERT_ID();\n" +
                "\n" +
                "-- 按钮 SQL\n" +
                "INSERT INTO system_menu(\n" +
                "    name, permission, type, sort, parent_id,\n" +
                "    path, icon, component, status\n" +
                ")\n" +
                "VALUES (\n" +
                "    '防火指挥机构调查查询', 'yjgl:fire-prevent-command-org-survey:query', 3, 1, @parentId,\n" +
                "    '', '', '', 0\n" +
                ");";
        int i = asd.indexOf("'");
        asd = asd.substring(i+1, asd.indexOf("'", i+1));
        return asd;

    }

    public static void main2(String[] args) {
        String[] heads = readAllExcelTitles();
        String titles = "填表单位\t填表人\t联系方式\t省\t市\t县\t乡镇(森林草原经营单位)名称\t护林员数量（人）\t总管护面积（hm²）\t平均管护面积（hm²/人）\t信息化个人终端配备情况\t森工集团(非必填)\t林业管理局(非必填)\t林业局(非必填)\t林场(非必填)";
        String[] split = titles.split("\t");
        List<ExcelEntity> list = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            ExcelEntity entity = str2Field(s);
            list.add(entity);
        }
        System.out.println("list = " + list);
//        testA();
    }

    public static ExcelEntity str2Field(String s) {
        ExcelEntity entity = new ExcelEntity();

        String notes = s; //  + ":unit:";
        s = s.trim();
        s = s.replace("（", "(");
        s = s.replace("）", ")");
        s = s.replaceAll("/", "");
        s = s.replaceAll("%", "");
        s = s.replaceAll("\\\\", ""); // /fs/f
        s = s.replaceAll("\n", "");
        s = s.replaceAll("\r", "");

//        notes = notes.replaceAll("\n", ""); // 对于换行符， 是不是全部删除比较好.. ？

        String unit = "";
        String filedType = "varchar(256)";
        String attribute = "Y";
        attribute = "N";
        boolean required = true;
        if (s.endsWith("(非必填)")) {
            s = s.substring(0, s.length() - 5);
            required = false;
            attribute = "N";
        }
        if (s.endsWith("(必填)")) {
            s = s.substring(0, s.length() - 5);
            required = true;
            attribute = "Y";
        }
        if (s.endsWith(")")) {
            int parenthesisIdx = s.lastIndexOf("(");
            unit = s.substring(parenthesisIdx+1, s.length() - 1);
            s = s.substring(0, parenthesisIdx);

            if (s.endsWith(")")) {
                parenthesisIdx = s.lastIndexOf("(");
                unit = s.substring(parenthesisIdx+1, s.length() - 1);
                s = s.substring(0, parenthesisIdx);
            }
        }

        if (s.endsWith("数量")) {
            filedType = "integer";
        } else if (s.contains("名称")) {

        } else if (s.endsWith("名")) {

        } else if (s.endsWith("面积") || s.endsWith("位置") || s.endsWith("小时") || s.endsWith("人数")|| s.endsWith("次")) {
            filedType = "decimal(13, 2)";
        } else if (s.endsWith("时间") && !s.endsWith("响应时间") && !s.endsWith("处置时间") ) {
            if (s.endsWith("响应时间") && s.endsWith("处置时间") ) {
                filedType = "decimal(13, 5)";
            } else {
                filedType = "datetime";
            }
        } else if (s.endsWith("日期")) {
            filedType = "datetime";
        } else if (s.endsWith("原因") || s.endsWith("说明")) {
            filedType = "varchar(2550)";
        } else if (s.endsWith("人") || s.endsWith("员")) {
            filedType = "varchar(64)";
        }

        // todo 区分 (年-月-日)  (年-月-日 时 分 秒)
        if (unit.startsWith("年-月-日") || unit.startsWith("年-月-日")  || unit.endsWith("年-月")) {
            filedType = "date";
        } else
        if (unit.equals("人") || unit.equals("员")
                || unit.endsWith("次")   || unit.equals("台")
                || unit.equals("把")    || unit.equals("s")
                || unit.equals("秒")
                || unit.endsWith("辆")  || unit.endsWith("个")  || unit.equals("部")
                || unit.endsWith("索")
                || unit.endsWith("级")
        ) {
            filedType = "integer";
        } else if (unit.endsWith("两")
                || unit.equals("小时")   || unit.endsWith("天")  || unit.endsWith("元")
                || unit.endsWith("m")   || unit.endsWith("米")   || unit.endsWith("吨")
                || unit.endsWith("日")   || unit.endsWith("°")  || unit.endsWith("度")   || unit.endsWith("克")
                || unit.endsWith("公顷") || unit.endsWith("里")
                || unit.endsWith("顷")   || unit.endsWith("℃")  || unit.equals("亩")
                || unit.endsWith("m²")   || unit.endsWith("量")
        ) {
            filedType = "decimal(13, 5)";
        }

        if (unit.startsWith("yyyy-MM-dd HH:mm:ss") || unit.startsWith("yyyy-MM-dd HH:mm")) {
            filedType = "datetime";
        } else if (unit.startsWith("yyyy-MM-dd") ) {
            filedType = "date";
        }

        // 无相关数据填写未知

        // if  采用文本格式编辑 ,  filedType = "datetime";

        s = s.trim();
        String varName = hanzi2FiledName(s);
//        System.out.println(s + " = " + varName);

        entity.setAttribute(attribute);
        entity.setFiledName(varName);
        entity.setNotes(notes);
        entity.setUnit(unit);
        entity.setRequired(required);
        entity.setFiledType(filedType);
        return entity;
    }

    private static void testA() {
        String string = "helloWorld";
        String strCase = StrUtil.toUnderlineCase(string);
        System.out.println("strCase = " + strCase);

        string = "hello World";
        strCase = StrUtil.toCamelCase(string);
        System.out.println("strCase = " + strCase);


        string = "helloWorld";
        strCase = StrUtil.toCamelCase(string);
        System.out.println("strCase = " + strCase);

        string = "hello   World";
        strCase = StrUtil.trimToEmpty(string);
        System.out.println("strCase = " + strCase);


        string = "hello   Worl   d  ";
        strCase = StrUtil.trim(string);
        System.out.println("strCase = " + strCase);

        string = "helloWorld";
        strCase = StrUtil.toSymbolCase(string, 'c');
        System.out.println("strCase = " + strCase);
    }

    private static String[] readAllExcelTitles() {
        return new String[0];
    }

    public static String hanzi2FiledName(String chinese) {
        return hanzi2FiledName(chinese, 0);
    }


//    Whether to conduct disaster risk assessment for towns and streets
//    fengli integer COMMENT '风力(级)(非必填)',
//	fengxiang varchar(256) CHARACTER SET utf8mb4  COMMENT '风向(非必填)',
//    npe  检测探测装备
    private static String hanzi2FiledName(String chinese0, int deep) {
        if (StrUtil.isEmpty(chinese0)) {
            return "";
        }
        int le = chinese0.length();
        char c = chinese0.charAt(le - 1);
        StringBuilder tail = new StringBuilder();
        while (Character.isDigit(c)) {
            tail.insert(0, c);
            chinese0 = chinese0.substring(0, le - 1);
            le = chinese0.length();
            c = chinese0.charAt(le - 1);
        }

        String[] s = chinese0.split("_");
        if (s.length > 1) {
            for (int i = 0; i < s.length; i++) {
                String s1 = s[i]; // 如果此时还有数字， 那么暂时不支持
                s[i] = hanzi2FiledName(s1, deep, "");
            }
        } else {
            s = chinese0.split("-"); // - _ 做同样的处理。 但一次性 只能支持一种。
            for (int i = 0; i < s.length; i++) {
                String s1 = s[i]; // 如果此时还有数字， 那么暂时不支持
                s[i] = hanzi2FiledName(s1, deep, "");
            }
        }

        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < s.length; i++) {
            String s1 = s[i];
            ret.append(s1);
            ret.append("_");
        }

        return ret.substring(0, ret.length()-1) + tail.toString();
    }

    private static String hanzi2FiledName(final String chinese0, int deep, String fixedTail) {
        Object o = translateCacheProperties.get(chinese0);
        if (ObjUtil.isNotEmpty(o) && useCache) {
            return o.toString();
        }
        String chinese = chinese0.replace("（", "(");
        chinese = chinese.replaceAll("\n", " ");
        chinese = chinese.replace("）", ")");
        chinese = chinese.replace("(", "");
        chinese = chinese.replace(")", "");
        chinese = chinese.replace(" ", "");
        chinese = chinese.replace("-", "to");
        chinese = chinese.replace("%", "p");
        chinese = chinese.replaceAll("☑|□", "");
        int len = chinese.length();
        if (len <= 2) {
            String ret = properties.getProperty(chinese);
            if (ret != null) {
                ret = normalize(ret);
                return ret;
            }
//            ret = PinyinUtil.toPinyin(chinese);
            ret = trans(chinese);
            ret = normalize(ret);
            if (useCache) {
                translateCacheProperties.put(chinese, ret);
                saveLastKey();
            }
            return ret;
        }

        String ret = "";
        if (chinese.startsWith("总")) {
            String first = chinese.substring(1);
            chinese = chinese.substring(1, chinese.length());
            ret += " total";
        }

        String tail = chinese.substring(len - 2);
        boolean findtail = properties.containsKey(tail);
        if (findtail) {
            chinese = chinese.substring(0, len - 2);
        } else {
            tail = chinese.substring(len - 1);
            findtail = properties.containsKey(tail);
            if (findtail) {
                chinese = chinese.substring(0, len - 1);
            }
        }

        int firstCnt = 4;
        if (chinese.length() >= firstCnt) {
            String first = chinese.substring(0, firstCnt);
            boolean b = properties.containsKey(first);
            if (b) {
                chinese = chinese.substring(firstCnt);
                String firstV = properties.getProperty(first);
                ret += " " + firstV;
            } else {
                first = chinese.substring(0, --firstCnt); // 3
                b = properties.containsKey(first);
                if (b) {
                    chinese = chinese.substring(firstCnt);
                    String firstV = properties.getProperty(first);
                    ret += " " + firstV;
                } else {
                    first = chinese.substring(0, --firstCnt); // 2
                    b = properties.containsKey(first);
                    if (b) {
                        chinese = chinese.substring(firstCnt);
                        String firstV = properties.getProperty(first);
                        ret += " " + firstV;
                    }
                }
            }
        } else if (chinese.length() >= --firstCnt) {
            String first = chinese.substring(0, firstCnt); // 3
            Boolean b = properties.containsKey(first);
            if (b) {
                chinese = chinese.substring(firstCnt);
                String firstV = properties.getProperty(first);
                ret += " " + firstV;
            } else {
                first = chinese.substring(0, --firstCnt); // 2
                b = properties.containsKey(first);
                if (b) {
                    chinese = chinese.substring(firstCnt);
                    String firstV = properties.getProperty(first);
                    ret += " " + firstV;
                }
            }
        } else if (chinese.length() >= --firstCnt) {
            String first = chinese.substring(0, firstCnt); // 2
            Boolean b = properties.containsKey(first);
            if (b) {
                chinese = chinese.substring(firstCnt);
                String firstV = properties.getProperty(first);
                ret += " " + firstV;
            }
        }
//        if (deep > 2 || chinese.length() > 1) { // 不能太长！
        if (StrUtil.isNotEmpty(chinese)) { // 不能太长！
//            ret += " " + PinyinUtil.toFirstChar(chinese);
            String dst = null;
//            String dst2 = null;
            dst = trans(chinese);
            if (dst.length() > 48) {
//                dst = StringUtils.capitalize(dst);
//                System.out.println("dst = " + dst + " ---> " + chinese);

//                dst = Baokang County Emergency Material Reserve Warehouse ---> 保康县应急物资储备库
//                dst = Directly affiliated disaster related public institutions ---> 直属涉灾事业单位
//                        dst = The total number of officially hired expert team personnel ---> 正式聘用的专家队伍人员总
//                        dst = Qz_ Disaster prevention and reduction plan formulated since 2016 ---> qz_2016年含以来制定的防灾减灾规划
//                        dst = Expenditure on agriculture, forestry, and water in the previous year ---> 上一年度农林水支出
//                        dst = Expenditure on natural resources, marine meteorology, and other related expenses in the previous year ---> 上一年度自然资源海洋气象等支出
//                        dst = Expenditure on grain and oil material reserves in the previous year ---> 上一年度粮油物资储备支出
//                        dst = Previous year's disaster prevention and emergency expenses ---> 上一年度灾害防治及应急支出
//                dst = Expenditure on natural disaster prevention and control ---> 自然灾害防治支出
//                        dst = Expenditure on natural disaster relief and recovery and reconstruction ---> 自然灾害救助及恢复重建支出
                dst = getFirstChar(dst);
            }
            ret += " " + dst;
        } else {
//            String transResult = api.getTransResult(chinese, "zh", "en");
//            System.out.println(transResult);
//            ret += " " + hanzi2FiledName(chinese, ++deep);
        }
        if (findtail) {
            String tail2V = "";
            tail2V = properties.getProperty(tail);
            ret += " " + tail2V;
        }
        ret = normalize(ret);
        if (useCache) {
            translateCacheProperties.put(chinese0, ret);
            saveLastKey();
        }
        return ret;
    }


    // 重复： 	disaster_relief_tentsd varchar(256) CHARACTER SET utf8mb4  NOT NULL  COMMENT '救灾帐篷',

    public static String translate(final String chinese0) {  // translate 和 str2Field ，是否有些重复？
        if (StrUtil.isEmpty(chinese0)) {
            return "";
        }
        if (chinese0.matches("\\d+(.)\\d+")) {
            System.out.println("纯数字， 不需要翻译！ = " + chinese0); // todo 如果汉字夹杂数字呢？
            return "";
        }
        // todo 缓存起来
        Object val = translateCacheProperties.get(chinese0);
        if (ObjUtil.isNotEmpty(val) && useCache) {
            return val.toString();
        }

        String dst = "err";
        String chinese = chinese0;
        if (chinese.startsWith("若有，") || chinese.startsWith("若有、")) {
            chinese = chinese.substring(3, chinese.length());
        } else if (chinese.startsWith("有限公司")) {
            chinese = chinese.substring(0, chinese.length() - 4);
        }

        // todo 缓存起来
        val = translateCacheProperties.get(chinese);
        if (ObjUtil.isNotEmpty(val) && useCache) {
            if (!chinese0.equals(chinese)) {
                translateCacheProperties.put(chinese0, val); // todo 这里不能使用 原始翻译结果， 还是需要去掉空格，改为下划线之类的
                saveLastKey();
            }
            return val.toString();
        }

        dst = trans(chinese);
        dst = normalize(dst);
        if (useCache) {
            translateCacheProperties.put(chinese, dst);
            if (!chinese0.equals(chinese)) {
                translateCacheProperties.put(chinese0, dst);
            }
            saveLastKey();
        }
        return dst;
    }

    private static String trans(final String chinese0) {
        if (StrUtil.isEmpty(chinese0)) {
            return "";
        }
        String dst = "err";
        String chinese = chinese0;
        try {
            String transResult = TransApi.getInstance().getTransResult(chinese, "zh", "en");
            JSONObject jsonObject = new JSONObject(transResult);
//                System.out.println("jsonObject = " + jsonObject);
            JSONArray trans_result = jsonObject.getJSONArray("trans_result");
            JSONObject o = trans_result.getJSONObject(0);
//                System.out.println("o = " + o);
            dst = o.getStr("dst");
//                dst = dst.toLowerCase();
//                dst2 = dst.replaceAll(" +", "_");
        } catch (Exception e) {
//            throw new RuntimeException(e);
            e.printStackTrace();
        }
        // 	previous_year's_income_and_expenditure_situation varchar(256) CHARACTER SET utf8mb4  NOT NULL  COMMENT '六、上一年度收支情况|||',
        //
        return dst;
    }

    public static void saveLastKey() {
        //保存文件
        try {
            if (useCache) {
                URL fileUrl = PropertiesUtil.class.getResource("/translateCache.properties");//得到文件路径
                FileOutputStream fos = new FileOutputStream(new File(fileUrl.toURI()));
//            InputStream translateCache = Chinese2VariableNameUtil.class.getResourceAsStream("/translateCache.properties");
                translateCacheProperties.store(fos, "the primary key of article table");
                fos.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getFirstChar(String dst) {
        String[] s = dst.split(" ");
//        String[] s = dst.split("_");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length; i++) {
            String s1 = s[i];
            if (StrUtil.isNotEmpty(s1)) {
                if (s1.equalsIgnoreCase("and")) {
                    continue;
                }
                if (s1.equalsIgnoreCase("or")) {
                    continue;
                }
                if (s1.equalsIgnoreCase("whether")) {
                    continue;
                }
                if (s1.equalsIgnoreCase("the")) {
                    continue;
                }
                if (s1.equalsIgnoreCase("a")) {
                    continue;
                }
                if (s1.equalsIgnoreCase("an")) {
                    continue;
                }
                if (s1.equalsIgnoreCase("is")) {
                    continue;
                }
                if (s1.equalsIgnoreCase("will")) {
                    continue;
                }
                sb.append(s1.charAt(0));
            }
        }
        return sb.toString();
    }

    public static String normalize(String ret) {
        ret = ret.trim();
        ret = ret.replaceAll("-", " "); // 是否需要对 - 转义？
        ret = ret.replaceAll(",", " ");
        ret = ret.replaceAll("，", " ");
        ret = ret.replaceAll("/", " ");
        ret = ret.replaceAll("'s", " ");
        ret = ret.replaceAll("'\\.", "");
        ret = ret.replaceAll("'\\d+、", "");
        ret = ret.replaceAll("'\\d+\\.", "");
        ret = ret.replaceAll("'\\d+,", "");
        ret = ret.replaceAll("'\\d+ ", "");
        ret = ret.replaceAll("\\s+", " ");

        ret = ret.toLowerCase();
        ret = ret.replaceAll(" ", "_");
        ret = ret.replace("/", "_");   // bug 如果 camelcase 这里就不能出现下划线。
//        ret = StrUtil.toUnderlineCase(ret);   // 必须要是 大写， 才会进行转换。。 对于 geologic hazard 并不会转换为 geologic_hazard
//        ret = StrUtil.toCamelCase(ret);
//        ret = StrUtil.upperFirst(ret);
        return ret;
    }
}
