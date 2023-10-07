package com.luokup.util;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.luokup.util.TransApi;

public class Main {

    // 在平台申请的APP_ID 详见 http://api.fanyi.baidu.com/api/trans/product/desktop?req=developer
    private static final String APP_ID = "20200829000554601";
    private static final String SECURITY_KEY = "lD7F99jRulkCrRzXItoo";

    public static void main1(String[] args) {

        String query = "森林防火报警次数";
        query = "玻璃用脉石英\\n) □粉石英 □天然油石\\n□含钾砂页岩 □硅藻土\\n□页岩(陶粒页岩、";
//        String transResult = api.getTransResult(query, "zh", "jp");
        String transResult = TransApi.getInstance().getTransResult(query, "zh", "en");
        JSONObject jsonObject = new JSONObject(transResult);
        System.out.println("jsonObject = " + jsonObject);
        JSONArray trans_result = jsonObject.getJSONArray("trans_result");
        JSONObject o = trans_result.getJSONObject(0);
        System.out.println("o = " + o);
        String dst = o.getStr("dst");
        System.out.println(dst);
    }

}
