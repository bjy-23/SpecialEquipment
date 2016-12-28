package com.wondersgroup.special.archive;

import java.util.LinkedHashMap;

/**
 * Created by root on 12/12/16.
 * 从业人员许可
 */

public class EmployPermitInfo {
    /**
     * 检验检测证书信息列表
     *
     * @return
     */
    public static LinkedHashMap<String, String> test() {
        LinkedHashMap<String, String> data = new LinkedHashMap<>();
        data.put("有效日期", "");
        data.put("审批机构", "");
        data.put("证书编号", "");
        data.put("持证状态", "");
        data.put("级别", "");
        data.put("发证日期", "");
        data.put("检验检测种类", "");
        return data;
    }

    /**
     * 监察列表
     *
     * @return
     */
    public static LinkedHashMap<String, String> inspectors() {
        LinkedHashMap<String, String> data = new LinkedHashMap<>();
        data.put("有效日期", "");
        data.put("审批机构", "");
        data.put("证书编号", "");
        data.put("持证状态", "");
        data.put("级别", "");
        data.put("持证项目", "");
        data.put("证书类别", "");
        data.put("初次取证日期", "");
        return data;
    }

    /**
     * 无损检测人员和作业人员作业证书列表
     *
     * @return
     */
    public static LinkedHashMap<String, String> nondestructiveAndWorkList() {
        LinkedHashMap<String, String> data = new LinkedHashMap<>();
        data.put("作业类型", "");
        data.put("证书编号", "");
        data.put("发证机关", "");
        data.put("作废状态", "");
        data.put("作业类型", "");
        data.put("是否新证", "");
        return data;
    }

    /**
     * 无损检测人员和作业人员作业证书详情
     *
     * @return
     */
    public static LinkedHashMap<String, String> nondestructiveAndWorkDetail() {
        LinkedHashMap<String, String> data = new LinkedHashMap<>();
        data.put("聘用资格项目名称", "");
        data.put("考核单位", "");
        data.put("批准单位", "");
        data.put("考核日期", "");
        data.put("有效日期", "");
        data.put("作业级别", "");
        data.put("受理日期", "");
        data.put("是否补证", "");
        return data;
    }
}
