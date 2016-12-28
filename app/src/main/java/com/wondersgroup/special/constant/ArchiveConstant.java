package com.wondersgroup.special.constant;

/**
 * Created by root on 12/15/16.
 */

public class ArchiveConstant {
    public static final String UNIT_PERMIT = "UNIT_PERMIT";//单位许可
    public static final String ACCIDENT = "ACCIDENT";//事故
    //    public static final String CHECKOUT = "CHECKOUT";//检验档案
    public static final String CASE = "CASE";//案件档案
    public static final String COMP = "COMP";//投举档案
    public static final String MANAGE = "MANAGE";//管理单元
    public static final String UNIT_AWARD = "UNIT_AWARD";//获奖记录

    public class UnitPermitBase {
        public static final String CHECK_ENT = "CHECK_ENT";
        public static final String PRODUCT_ENT = "PRODUCT_ENT";
    }

    /**
     * 监察档案
     */
    public class Routine {
        public static final String BASE_INFO = "ROUTINE_BASE_INFO";//基本信息
        public static final String OVERVIEW = "OVERVIEW";//检查概览
        public static final String SITUATION = "SITUATION";//检查情况
        public static final String BOOK = "BOOK";//安全指令书
        public static final String CONTENT = "CONTENT";//检查内容
        public static final String RECORD = "RECORD";//检查记录
    }

    /**
     * 检验档案
     */
    public class Checkout {
        public static final String BASE_INFO = "CHECKOUT_BASE_INFO";//基本信息
        public static final String RECORD = "CHECKOUT_RECORD";//检验检测记录
    }


    /*
    * 设备基本信息
    * */
    public class DeviceBaseInfo {
        public static final String DEVICE_MARK = "DEVICE_MARK";
        public static final String DEVICE_PRODUCT = "DEVICE_PRODUCT";
        public static final String DEVICE_DESIGN = "DEVICE_DESIGN";
        public static final String DEVICE_PROPERTY = "DEVICE_PROPERTY";
    }

    public class Unit {
        public static final String CHECK_UNIT = "CHECK_UNIT";//检验检测单位
        public static final String UNIT = "UNIT";//从业单位
        public static final String PRO_UNIT = "PRO_UNIT";//生产单位
    }
}
