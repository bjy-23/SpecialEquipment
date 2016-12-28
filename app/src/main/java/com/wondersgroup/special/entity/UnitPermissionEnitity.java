package com.wondersgroup.special.entity;

import java.io.Serializable;

/**
 * Created by bjy on 2016/12/15.
 * 单位许可查询
 */

public class UnitPermissionEnitity implements Serializable {
    //主键
    private String uuid;

    //鉴定评审机构代码
    private String unitCode;

    //鉴定评审机构信用代码
    private String creditCode;

    //鉴定评审机构
    private String unitName;

    //证书类型(设计、制造、安装改造修理、气瓶充装、制造、移动式压力容器重装)
    private String certType;

    //发证日期
    private String certGrantDate;

    //许可证编号
    private String certCode;

    //有效日期
    private String certAvailableDate;

    //发证机关
    private String grantGov;

    //备注
    private String remark;

    //审批机关
    private String approveOrgan;

    //设备种类
    private String deviceType;

    //业务审批通过日期
    private String passDate;

    //持证状态(已持证，待发证，已吊销，已注销，变更中，换证中)
    private String certStatus;

    //生产地址
    private String productAddress;

    //受理日期
    private String acceptDate;

    //检验地址
    private String checkAddress;

    //变更日期
    private String changeDate;

    //单位表主键
    private String unitId;

    //单位表主键（审批机构）
    private String issuingId;

    /**
     * 企业类型
     */
    private String certUnitType;

    {
        uuid = "1";
        unitCode = "1";
        creditCode = "1";
        unitName = "1";
        certType = "1";
        certGrantDate = "1";
        certCode = "1";
        certAvailableDate = "1";
        grantGov = "1";
        remark = "1";
        approveOrgan = "1";
        deviceType = "1";
        passDate = "1";
        certStatus = "1";
        productAddress = "1";
        acceptDate = "1";
        checkAddress = "1";
        changeDate = "1";
        unitId = "1";
        issuingId = "1";
    }

    public String getCertUnitType() {
        return certUnitType;
    }

    public void setCertUnitType(String certUnitType) {
        this.certUnitType = certUnitType;
    }

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

    public String getCertAvailableDate() {
        return certAvailableDate;
    }

    public void setCertAvailableDate(String certAvailableDate) {
        this.certAvailableDate = certAvailableDate;
    }

    public String getCertCode() {
        return certCode;
    }

    public void setCertCode(String certCode) {
        this.certCode = certCode;
    }

    public String getCertGrantDate() {
        return certGrantDate;
    }

    public void setCertGrantDate(String certGrantDate) {
        this.certGrantDate = certGrantDate;
    }

    public String getCertStatus() {
        return certStatus;
    }

    public void setCertStatus(String certStatus) {
        this.certStatus = certStatus;
    }

    public String getCertType() {
        return certType;
    }

    public void setCertType(String certType) {
        this.certType = certType;
    }

    public String getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(String changeDate) {
        this.changeDate = changeDate;
    }

    public String getCheckAddress() {
        return checkAddress;
    }

    public void setCheckAddress(String checkAddress) {
        this.checkAddress = checkAddress;
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getGrantGov() {
        return grantGov;
    }

    public void setGrantGov(String grantGov) {
        this.grantGov = grantGov;
    }

    public String getIssuingId() {
        return issuingId;
    }

    public void setIssuingId(String issuingId) {
        this.issuingId = issuingId;
    }

    public String getPassDate() {
        return passDate;
    }

    public void setPassDate(String passDate) {
        this.passDate = passDate;
    }

    public String getProductAddress() {
        return productAddress;
    }

    public void setProductAddress(String productAddress) {
        this.productAddress = productAddress;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
