package com.wondersgroup.special.archive;

import com.wondersgroup.special.entity.CheckoutRecord;

import java.util.LinkedHashMap;

/**
 * Created by root on 12/7/16.
 * 检验档案
 */

public class CheckInfo {
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
        data.put("检验日期", "");
        data.put("检验类别", "");
        data.put("检验单位", "");
        data.put("检验结论", "");
        data.put("检验报告编号", "");
        return data;
    }

    /**
     * 检验记录
     *
     * @return
     */
    public static LinkedHashMap<String, String> recordInfo(CheckoutRecord.Checkout result) {
        LinkedHashMap<String, String> data = new LinkedHashMap<>();
        data.put("检验单位", result.getCheckUnit());
        data.put("组织机构代码", result.getUnitCode());
        data.put("企业信用代码", result.getCreditCode());
        data.put("法定代表人", result.getLegalName());
        data.put("身份证号码", result.getLegalPin());
        data.put("检验类别", result.getCheckType());
        data.put("检验日期", result.getCheckDate());
        data.put("应该检验日期", result.getPlanCheckDate());
        data.put("检验员", result.getCheckPerson());
        data.put("身份证号码", result.getIdCard());
        data.put("主要问题", result.getProblem());
        data.put("检验结论", result.getCheckResult());
        data.put("检验报告编号", result.getReportNo());
        data.put("下次检验日期", result.getNextCheckDate());
        data.put("下次外检日期", result.getNextOutcheckDate());
        data.put("下次内检日期", result.getNextIntercheckDate());
        data.put("下次耐压检验日期", result.getNextPressureDate());
        return data;
    }
}
