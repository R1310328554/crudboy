package com.luokup.util;
 
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 汉字转换为拼音
 * @author Wjhsmart
 */
public class PinyinUtil {
    /**
     * 测试main方法
     * @param args
     */
    public static void main(String[] args) {
        String s = "护林员数量（人）张三’首字母大写：";
        s = "护林员数量(人)张三’首字母大写：";
        s = "护林员数量人张三’首字母大写：";
//        s = "护林员数量人张三首字母大写";
        System.out.println(s + "" + toFirstChar(s).toUpperCase()); //转为首字母大写
        System.out.println(s + "" + toPinyin(s));

    }
    /**
     * 获取字符串拼音的第一个字母
     * @param chinese
     * @return
     */
    public static String toFirstChar(String chinese){
        String pinyinStr = "";
        char[] newChar = chinese.toCharArray();  //转为单个字符
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < newChar.length; i++) {
            if (newChar[i] > 128) {
                try {
//                    StringUtils
                    String[] a = PinyinHelper.toHanyuPinyinStringArray(newChar[i], defaultFormat);
                    if (a == null || a.length == 0) {
                        continue;
                    }
                    pinyinStr += a[0].charAt(0);
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            }else{
                pinyinStr += newChar[i];
            }
        }
        return pinyinStr;
    }


    /**
     * 汉字转为拼音
     * @param chinese
     * @return
     */
    public static String toPinyin(String chinese){
        String pinyinStr = "";
        char[] newChar = chinese.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < newChar.length; i++) {
            if (newChar[i] > 128) {
                try {
                    String[] a = PinyinHelper.toHanyuPinyinStringArray(newChar[i], defaultFormat);
                    if (a == null || a.length == 0) {
                        continue;
                    }
                    pinyinStr += a[0];
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            }else {
                pinyinStr += newChar[i];
            }
        }
        return pinyinStr;
    }
}