package com.wondersgroup.special.entity;

import java.io.Serializable;

/**
 * Created by bjy on 2016/12/15.
 */

public class GetCheckoutEntity implements Serializable{
    //主键
    private String uuid;

    //检验表id
    private String chekcoutId;

    //组织机构代码
    private String unitCode;

    //企业信用代码
    private String creditCode;

    //法定代表人
    private String legalName;

    //身份证号码
    private String legalPin;

    //检验类别
    private String checkType;

    //检验日期
    private String checkDate;

    //应该检验日期
    private String planCheckDate;

    //检验员
    private String checkPerson;

    //身份证号码
    private String idCard;

    //主要问题
    private String problem;

    //检验结论
    private String checkResult;

    //检验单位
    private String checkUnit;

    //下次检验日期
    private String nextCheckDate;

    //下次内检日期
    private String nextIntercheckDate;

    //下次外检日期
    private String nextOutcheckDate;

    //下次耐压检验日期
    private String nextPressureDate;

    //检验报告编号
    private String reportNo;

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    public String getCheckPerson() {
        return checkPerson;
    }

    public void setCheckPerson(String checkPerson) {
        this.checkPerson = checkPerson;
    }

    public String getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult;
    }

    public String getCheckType() {
        return checkType;
    }

    public void setCheckType(String checkType) {
        this.checkType = checkType;
    }

    public String getCheckUnit() {
        return checkUnit;
    }

    public void setCheckUnit(String checkUnit) {
        this.checkUnit = checkUnit;
    }

    public String getChekcoutId() {
        return chekcoutId;
    }

    public void setChekcoutId(String chekcoutId) {
        this.chekcoutId = chekcoutId;
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public String getLegalPin() {
        return legalPin;
    }

    public void setLegalPin(String legalPin) {
        this.legalPin = legalPin;
    }

    public String getNextCheckDate() {
        return nextCheckDate;
    }

    public void setNextCheckDate(String nextCheckDate) {
        this.nextCheckDate = nextCheckDate;
    }

    public String getNextIntercheckDate() {
        return nextIntercheckDate;
    }

    public void setNextIntercheckDate(String nextIntercheckDate) {
        this.nextIntercheckDate = nextIntercheckDate;
    }

    public String getNextOutcheckDate() {
        return nextOutcheckDate;
    }

    public void setNextOutcheckDate(String nextOutcheckDate) {
        this.nextOutcheckDate = nextOutcheckDate;
    }

    public String getNextPressureDate() {
        return nextPressureDate;
    }

    public void setNextPressureDate(String nextPressureDate) {
        this.nextPressureDate = nextPressureDate;
    }

    public String getPlanCheckDate() {
        return planCheckDate;
    }

    public void setPlanCheckDate(String planCheckDate) {
        this.planCheckDate = planCheckDate;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getReportNo() {
        return reportNo;
    }

    public void setReportNo(String reportNo) {
        this.reportNo = reportNo;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
