package com.wondersgroup.special.archive;

import com.wondersgroup.special.entity.SupervisionContent;

import java.util.LinkedHashMap;

/**
 * Created by root on 12/7/16.
 * 监察档案
 */

public class RoutineInfo {
    /**
     * 基本信息
     *
     * @return
     */
    public static LinkedHashMap<String, String> baseInfo() {
        LinkedHashMap<String, String> data = new LinkedHashMap<>();
        data.put("组织机构代码", "");
        data.put("企业信用代码", "");
        data.put("单位名称", "");
        data.put("设备注册代码", "");
        data.put("设备名称", "");
        data.put("设备类型", "");
        data.put("任务类型", "");
        data.put("检查开始日期", "");
        data.put("检查结束日期", "");
        data.put("检查人员", "");
        return data;
    }

    /**
     * 检查概况
     *
     * @return
     */
    public static LinkedHashMap<String, String> checkOverview() {
        LinkedHashMap<String, String> data = new LinkedHashMap<>();
        data.put("计划检查时间", "");
        data.put("计划执行单位", "");
        data.put("计划执行人员", "");
        data.put("制定人", "");
        data.put("制定时间", "");
        data.put("执行状态", "");
        return data;
    }

    /**
     * 检查情况
     *
     * @return
     */
    public static LinkedHashMap<String, String> checkSituation() {
        LinkedHashMap<String, String> data = new LinkedHashMap<>();
        data.put("企业陪同人员", "");
        data.put("电话", "");
        data.put("检查主要内容", "");
        data.put("检查中发现的主要问题", "");
        data.put("处理措施", "");
        data.put("检查开始时间", "");
        data.put("检查结束时间", "");
        data.put("检查人员", "");
        data.put("随同专家", "");
        data.put("检查时停产", "");
        data.put("需复查", "");
        return data;
    }

    /**
     * 检查内容
     *
     * @return
     */
    public static LinkedHashMap<String, String> checkContentList(SupervisionContent result) {
        LinkedHashMap<String, String> data = new LinkedHashMap<>();
        data.put("检查项目", result.getCheckPro());
        data.put("检查结果", result.getResult());
        data.put("检查情况", result.getCheckSituation());
        data.put("取证", result.getEvidence());
        return data;
    }

    /**
     * 安全监察指令书
     *
     * @return
     */
    public static LinkedHashMap<String, String> book() {
        LinkedHashMap<String, String> data = new LinkedHashMap<>();
        data.put("文本展示", "");
        return data;
    }

    /**
     * 检验记录
     *
     * @return
     */
    public static LinkedHashMap<String, String> inspectionRecordList() {
        LinkedHashMap<String, String> data = new LinkedHashMap<>();
        data.put("检验日期", "");
        data.put("检验类别", "");
        data.put("检验单位", "");
        data.put("检验结论", "");
        data.put("检验报告编号", "");
        return data;
    }

}
