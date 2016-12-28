package com.wondersgroup.special.archive;

import java.util.LinkedHashMap;

/**
 * Created by chan on 12/9/16.
 * 人员档案
 */

public class EmployInfo {
    /**
     * 作业人员基本信息
     *
     * @return
     */
    public static LinkedHashMap<String, String> workBase() {
        LinkedHashMap<String, String> data = new LinkedHashMap<>();
        data.put("姓名", "");
        data.put("身份证号", "");
        data.put("学历", "");
        data.put("性别", "");
        data.put("职称", "");
        data.put("专业", "");
        data.put("联系地址", "");
        data.put("电话", "");
        data.put("邮政编码", "");
        data.put("传真", "");
        data.put("电子信箱", "");
//        data.put("工作属性", "");
        data.put("毕业院校", "");

        data.put("受聘单位组织机构代码", "");
        data.put("受聘单位信用代码", "");
        data.put("是否为本省（市）发证人员", "");
        data.put("数据源", "");
        data.put("管理单位名称", "");
        data.put("管理单位地址", "");
        data.put("管理单位联系人", "");
        data.put("管理单位联系电话", "");
        data.put("是否普通公民", "");
        data.put("备注", "");
        return data;
    }

    /**
     * 检验检测人员基本信息
     *
     * @return
     */
    public static LinkedHashMap<String, String> testBase() {
        LinkedHashMap<String, String> data = new LinkedHashMap<>();
        data.put("姓名", "");
        data.put("身份证号", "");
        data.put("学历", "");
        data.put("性别", "");
        data.put("职称", "");
        data.put("专业", "");
        data.put("联系地址", "");
        data.put("电话", "");
        data.put("邮政编码", "");
        data.put("传真", "");
        data.put("电子信箱", "");
//        data.put("工作属性", "");
        data.put("毕业院校", "");

        data.put("出生年月日", "");
        data.put("归属", "");
        return data;
    }

    /**
     * 监察人员基本信息
     *
     * @return
     */
    public static LinkedHashMap<String, String> inspectorsBase() {
        LinkedHashMap<String, String> data = new LinkedHashMap<>();
        data.put("姓名", "");
        data.put("身份证号", "");
        data.put("学历", "");
        data.put("性别", "");
        data.put("职称", "");
        data.put("专业", "");
        data.put("联系地址", "");
        data.put("电话", "");
        data.put("邮政编码", "");
        data.put("传真", "");
        data.put("电子信箱", "");
//        data.put("工作属性", "");
        data.put("毕业院校", "");

        data.put("人员编号", "");
        data.put("是否公务员", "");
        data.put("职务", "");
        data.put("是否事业编制", "");
        data.put("是否借调人员", "");
        data.put("是否协管人员", "");
        data.put("部门", "");
        data.put("岗位", "");
        data.put("负责工作", "");
        data.put("从事监察工作年限", "");
        data.put("从事使用管理工作年限", "");
        data.put("特种设备知识培训学时", "");
        data.put("从事设备生产工作年限", "");
        data.put("是否检查人员", "");
        data.put("备注", "");
        return data;
    }

    /**
     * 无损检测人员基本信息
     *
     * @return
     */
    public static LinkedHashMap<String, String> nondestructiveBase() {
        LinkedHashMap<String, String> data = new LinkedHashMap<>();
        data.put("姓名", "");
        data.put("身份证号", "");
        data.put("学历", "");
        data.put("性别", "");
        data.put("职称", "");
        data.put("专业", "");
        data.put("联系地址", "");
        data.put("电话", "");
        data.put("邮政编码", "");
        data.put("传真", "");
        data.put("电子信箱", "");
//        data.put("工作属性", "");
        data.put("毕业院校", "");

        data.put("受聘单位组织机构代码", "");
        data.put("受聘单位信用代码", "");
        data.put("是否为本省（市）发证人员", "");
        data.put("数据源", "");
        data.put("管理单位名称", "");
        data.put("管理单位地址", "");
        data.put("管理单位联系人", "");
        data.put("管理单位联系电话", "");
        data.put("是否普通公民", "");
        data.put("备注", "");
        return data;
    }

    /**
     * 监察、检验检测变更记录
     *
     * @return
     */
    public static LinkedHashMap<String, String> changeRecordList() {
        LinkedHashMap<String, String> data = new LinkedHashMap<>();
        data.put("变更项目", "");
        data.put("变更日期", "");
        data.put("变更人", "");
        data.put("变更内容", "");
        return data;
    }

    /**
     * 奖惩信息
     *
     * @return
     */
    public static LinkedHashMap<String, String> awardList() {
        LinkedHashMap<String, String> data = new LinkedHashMap<>();
        data.put("奖惩项目", "");
        data.put("奖惩日期", "");
        data.put("奖惩机构", "");
        return data;
    }

    /**
     * 工作记录
     *
     * @return
     */
    public static LinkedHashMap<String, String> jobRecordList() {
        LinkedHashMap<String, String> data = new LinkedHashMap<>();
        data.put("开始日期", "");
        data.put("结束日期", "");
        data.put("工作单位", "");
        data.put("工作单位组织机构代码", "");
        data.put("职务", "");
        return data;
    }

    /**
     * 培训记录
     *
     * @return
     */
    public static LinkedHashMap<String, String> trainRecordList() {
        LinkedHashMap<String, String> data = new LinkedHashMap<>();
        data.put("培训内容", "");
        data.put("培训成绩", "");
        data.put("结束日期", "");
        data.put("培训单位", "");
        data.put("培训成绩", "");
        return data;
    }

    /**
     * 考核信息
     *
     * @return
     */
    public static LinkedHashMap<String, String> checkInfoList() {
        LinkedHashMap<String, String> data = new LinkedHashMap<>();
        data.put("持证项目", "");
        data.put("考核内容", "");
        data.put("考核日期", "");
        data.put("考核机构", "");
        data.put("开卷考试成绩", "");
        data.put("闭卷考试成绩", "");
        data.put("实际操作成绩", "");
        data.put("考核成绩", "");
        return data;
    }
}
