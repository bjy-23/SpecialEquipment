package com.wondersgroup.special.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by bjy on 2016/12/15.
 */

public class QueryCheckoutEntity implements Serializable{
    //主键
    private String uuid;

    //单位id
    private String unitId;

    //组织机构代码
    private String unitCode;

    //企业信用代码
    private String creditCode;

    //单位名称
    private String unitName;

    //设备注册代码
    private String deviceNumber;

    //设备id
    private String deviceId;

    //设备名称
    private String deviceName;

    //设备类型
    private String deviceType;

    //检验日期
    private String checkDaye;

    //检验类别
    private String checkType;

    //检验报告编号
    private String reportNo;

    //检验结论
    private String checkResult;

    //检验单位
    private String checkUnit;

    //监察档案id
    private String visionId;

    private List<GetCheckoutEntity> recordList;

    public String getCheckDaye() {
        return checkDaye;
    }

    public void setCheckDaye(String checkDaye) {
        this.checkDaye = checkDaye;
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

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public List<GetCheckoutEntity> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<GetCheckoutEntity> recordList) {
        this.recordList = recordList;
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

    public String getVisionId() {
        return visionId;
    }

    public void setVisionId(String visionId) {
        this.visionId = visionId;
    }
}
