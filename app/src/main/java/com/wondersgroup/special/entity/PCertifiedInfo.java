package com.wondersgroup.special.entity;

import java.io.Serializable;

/**
 * Created by bjy on 2016/12/21.
 */

public class PCertifiedInfo implements Serializable {
    //主键
    private String uuid;

    //证书类别(监察人员)
    //检验检测证书、作业证书
    private String certType;

    //级别（检验检测人员）
    //检验员、检验师、高级检验师、无损检测I级别（初级）、无损检测II级别（中级）、无损检测III级别（高级）
    //持证级别（监察人员）
    private String certLevel;

    //证书编号
    private String certCode;

    //初次取证日期
    private String firstDate;

    //发证日期
    private String certDate;

    //持证项目
    private String certPro;

    //持证状态
    //已持证、待发证、已吊销、已注销、变更中、换证中
    private String certStatus;

    //检验检测种类
    //监督检验、定期检验、型式试验
    private String checkType;

    //审批机构
    private String approveOrgan;

    //人员表id
    private String personId;

    //有效日期
    private String validDate;

    //作业类型（作业）
    private String operateType;

    //发证机关（作业）
    private String certOrgan;

    //作废状态（作业）
    private String cancelStatus;

    //是否新证（作业）
    private String newFlag;

    private String certName;

    public String getCertName() {
        return certName;
    }

    public void setCertName(String certName) {
        this.certName = certName;
    }

    public String getApproveOrgan() {
        return approveOrgan;
    }

    public void setApproveOrgan(String approveOrgan) {
        this.approveOrgan = approveOrgan;
    }

    public String getCancelStatus() {
        return cancelStatus;
    }

    public void setCancelStatus(String cancelStatus) {
        this.cancelStatus = cancelStatus;
    }

    public String getCertCode() {
        return certCode;
    }

    public void setCertCode(String certCode) {
        this.certCode = certCode;
    }

    public String getCertDate() {
        return certDate;
    }

    public void setCertDate(String certDate) {
        this.certDate = certDate;
    }

    public String getCertLevel() {
        return certLevel;
    }

    public void setCertLevel(String certLevel) {
        this.certLevel = certLevel;
    }

    public String getCertOrgan() {
        return certOrgan;
    }

    public void setCertOrgan(String certOrgan) {
        this.certOrgan = certOrgan;
    }

    public String getCertPro() {
        return certPro;
    }

    public void setCertPro(String certPro) {
        this.certPro = certPro;
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

    public String getCheckType() {
        return checkType;
    }

    public void setCheckType(String checkType) {
        this.checkType = checkType;
    }

    public String getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(String firstDate) {
        this.firstDate = firstDate;
    }

    public String getNewFlag() {
        return newFlag;
    }

    public void setNewFlag(String newFlag) {
        this.newFlag = newFlag;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
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
