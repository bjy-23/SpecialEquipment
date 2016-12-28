package com.wondersgroup.special.archive;

import java.util.LinkedHashMap;

/**
 * Created by bjy on 2016/12/8.
 *
 * 单位许可信息
 */

public class UnitPermissionInfo {
    /*
    *许可基本信息->生产单位、检验检测单位
    * */
    public static LinkedHashMap<String, String> baseInfoProCheck() {
        LinkedHashMap<String, String> data = new LinkedHashMap<>();
        data.put("许可证编号", "");
        data.put("鉴定评审机构代码", "");
        data.put("鉴定评审机构", "");
        data.put("审批机关", "");
        data.put("发证机关", "");
        data.put("发证日期", "");
        data.put("有效日期", "");
        data.put("变更日期", "");
        data.put("备注", "");

        return data;
    }

    /*
    * 许可基本信息->生产单位
    * */
    public static LinkedHashMap<String, String> baseInfoPro() {
        LinkedHashMap<String, String> data = new LinkedHashMap<>();
        data.put("证件类型", "");
        data.put("设备种类", "");
        data.put("业务审批通过日期", "");
        data.put("生产地址", "");

        return data;
    }

    /*
    * 许可基本信息->检验检测单位
    * */
    public static LinkedHashMap<String, String> baseInfoCheck() {
        LinkedHashMap<String, String> data = new LinkedHashMap<>();
        data.put("持证状态", "");
        data.put("受理日期", "");
        data.put("检验地址", "");
        return data;
    }

    /*
    * 核准项目列表->生产单位、检验检测单位
    * */
    public static LinkedHashMap<String,String> projectInfoProCheck(){
        LinkedHashMap<String,String> data = new LinkedHashMap<>();
        data.put("作废状态", "");
        data.put("限制条件", "");

        return data;
    }

    /*
    * 核准项目列表->生产单位
    * */
    public static LinkedHashMap<String,String> projectInfoPro(){
        LinkedHashMap<String,String> data = new LinkedHashMap<>();
        data.put("项目代码", "");
        data.put("核准项目", "");
        data.put("发证机关", "");

        return data;
    }

    /*
    * 核准项目列表->检验检测单位
    * */
    public static LinkedHashMap<String,String> projectInfoCheck(){
        LinkedHashMap<String,String> data = new LinkedHashMap<>();
        data.put("发证日期", "");
        data.put("有效日期", "");
        data.put("变更日期", "");
        data.put("备注", "");

        return data;
    }
}
