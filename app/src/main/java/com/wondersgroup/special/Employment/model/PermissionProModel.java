package com.wondersgroup.special.employment.model;

/**
 * Created by bjy on 2016/12/22.
 */

public class PermissionProModel {
    //主键
    private String uuid;

    //聘用资格项目名称
    private String proName;

    //考核单位
    private String assessOrgan;

    //批准单位
    private String approveOrgan;

    //考核日期
    private String assessDate;

    //有效日期
    private String validDate;

    //作业级别
    private String operateLevel;

    //受理日期
    private String acceptDate;

    //是否补证
    private String fillFlag;

    //作业表id（目前为人员证书表id）
    private String operateId;

    public String getAcceptDate() {
        return acceptDate;
    }

    public void setAcceptDate(String acceptDate) {
        this.acceptDate = acceptDate;
    }

    public String getApproveOrgan() {
        return approveOrgan;
    }

    public void setApproveOrgan(String approveOrgan) {
        this.approveOrgan = approveOrgan;
    }

    public String getAssessDate() {
        return assessDate;
    }

    public void setAssessDate(String assessDate) {
        this.assessDate = assessDate;
    }

    public String getAssessOrgan() {
        return assessOrgan;
    }

    public void setAssessOrgan(String assessOrgan) {
        this.assessOrgan = assessOrgan;
    }

    public String getFillFlag() {
        return fillFlag;
    }

    public void setFillFlag(String fillFlag) {
        this.fillFlag = fillFlag;
    }

    public String getOperateId() {
        return operateId;
    }

    public void setOperateId(String operateId) {
        this.operateId = operateId;
    }

    public String getOperateLevel() {
        return operateLevel;
    }

    public void setOperateLevel(String operateLevel) {
        this.operateLevel = operateLevel;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getValidDate() {
        return validDate;
    }

    public void setValidDate(String validDate) {
        this.validDate = validDate;
    }
}
