/**  Copyright (C) 2015 Baidu, Inc. All Rights Reserved. */
package com.baidu.fbu.asset.constants;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;

import com.baidu.fbu.asset.util.DesUtils;
import com.baidu.fbu.asset.util.Util;

/** 资产管理 的 常量类 */
public class Constants {
    @Value( "${mis.asset}" )
    private String misAsset;

    @Value("${mis.checkurl}")
    private String misCheckurl;

    @Value("${mis.getinfourl}")
    private String misGetinfourl;

    @Value("${mis.sign}")
    private String misSign;

    public static class Base {
        public static String MIS_ASSET;
        public static String MIS_CHECKURL;
        public static String MIS_GETINFOURL;
        public static String MIS_SIGN;

        private static void init( String misAsset, String misCheckurl, String misGetinfourl, String misSign) {
            MIS_ASSET = misAsset;
            MIS_CHECKURL = misCheckurl;
            MIS_GETINFOURL = misGetinfourl;
            MIS_SIGN = DesUtils.decrypt( misSign );
        }
    }

    private void init() {
        Base.init( misAsset, misCheckurl, misGetinfourl, misSign );
    }

    /** 返回码     */
    public static class ResultCode {
        public static final String[] ERR_MSG = {
                "success", // 00
                "未知原因", // 01
                "请求参数格式错误" // 02
        };
        public static final int SUCCESS = 0;
        public static final int ERR_UNKOWN = 1;
        public static final int ERR_REQUEST_PARAM_ERROR = 2;
    }

    private static final ArrayList<Object> bankNameList = Util.getArrayList();

    static {
        bankNameList.add("中国银行");
        bankNameList.add("工商银行");
        bankNameList.add("建设银行");
        bankNameList.add("招商银行");
        bankNameList.add("农业银行");
        bankNameList.add("交通银行");
        bankNameList.add("光大银行");
        bankNameList.add("民生银行");
        bankNameList.add("中信银行");
        bankNameList.add("华夏银行");
        bankNameList.add("兴业银行");
        bankNameList.add("平安银行");
        bankNameList.add("中国邮政储蓄银行");
        bankNameList.add("北京银行");
        bankNameList.add("上海银行");
        bankNameList.add("南京银行");
        bankNameList.add("广州银行");
        bankNameList.add("浦发银行");
        bankNameList.add("广发银行");
        bankNameList.add("杭州银行");
        bankNameList.add("大连银行");
        bankNameList.add("天津银行");
        bankNameList.add("齐鲁银行");
        bankNameList.add("渤海银行");
        bankNameList.add("江苏银行");
        bankNameList.add("青岛银行");
        bankNameList.add("长安银行");
        bankNameList.add("宁波银行");
        bankNameList.add("西安银行");
        bankNameList.add("河北银行");
        bankNameList.add("廊坊银行");
        bankNameList.add("张家口商行");
        bankNameList.add("洛阳银行");

    }

    public static ArrayList<Object> getBankNameList() {
        return bankNameList;
    }

    private static final ArrayList<Object> productTypes = Util.getArrayList();

    static {
        HashMap<String, Object> pt1 = Util.getHashMap();
        HashMap<String, Object> pt2 = Util.getHashMap();
        HashMap<String, Object> pt3 = Util.getHashMap();
        HashMap<String, Object> pt4 = Util.getHashMap();

        pt1.put("name", "度学金");
        pt1.put("id", "DXJ");
        pt2.put("name", "度零钱");
        pt2.put("id", "DLQ");
        pt3.put("name", "去哪儿");
        pt3.put("id", "QNR");
        pt4.put("name", "度贴吧");
        pt4.put("id", "BTB");

        productTypes.add( pt1 );
        productTypes.add( pt2 );
        productTypes.add( pt3 );
        productTypes.add( pt4 );
    }

    public static ArrayList<Object> getProductTypes() {
        return productTypes;
    }




}