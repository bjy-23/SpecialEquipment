package com.wondersgroup.special.entity;

import java.io.Serializable;

/**
 * Created by bjy on 2016/12/15.
 */

public class UnitApprovalEntity implements Serializable {
    //主键
    private String uuid;

    //许可表id
    private String certId;

    //限制条件
    private String limit;

    //项目代码
    private String proCode;

    //核准项目
    private String proName;

    //检验分址
    private String testAssess;

    //许可项目
    private String certPro;

    //许可级别
    private String certLevel;

    //设备类别
    private String deviceType;

    //备注
    private String remark;

    //作废状态
    private String cancelStatus;

    public String getCancelStatus() {
        return cancelStatus;
    }

    public void setCancelStatus(String cancelStatus) {
        this.cancelStatus = cancelStatus;
    }

    public String getCertId() {
        return certId;
    }

    public void setCertId(String certId) {
        this.certId = certId;
    }

    public String getCertLevel() {
        return certLevel;
    }

    public void setCertLevel(String certLevel) {
        this.certLevel = certLevel;
    }

    public String getCertPro() {
        return certPro;
    }

    public void setCertPro(String certPro) {
        this.certPro = certPro;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getProCode() {
        return proCode;
    }

    public void setProCode(String proCode) {
        this.proCode = proCode;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTestAssess() {
        return testAssess;
    }

    public void setTestAssess(String testAssess) {
        this.testAssess = testAssess;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

}
