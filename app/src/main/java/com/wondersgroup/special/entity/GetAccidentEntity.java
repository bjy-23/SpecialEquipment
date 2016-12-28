package com.wondersgroup.special.entity;

import java.io.Serializable;

/**
 * Created by bjy on 2016/12/15.
 */

public class GetAccidentEntity implements Serializable {
    //主键
    private String uuid;

    //单位id
    private String unitId;

    //设备id
    private String deviceId;

    //事故单位
    private String accidentUnit;

    //事故单位组织机构代码
    private String accidentUnitCode;

    //发生时间
    private String happenTime;

    //上报时间
    private String reportTime;

    //行业
    private String trade;

    //举报时间
    private String receiveTime;

    //所属区域
    private String area;

    //信息来源
    private String infoSource;

    //事故地点
    private String accidentPlace;

    //设备种类
    private String deviceType;

    //死亡人数
    private int deadNum;

    //重伤人数
    private int heavyHurtNum;

    //轻伤人数
    private int lightHurtNum;

    //直接经济损失
    private int directEcoLoss;

    //间接经济损失
    private int indirectEcoLoss;

    //事故现象
    private String accidentPheno;

    //责任环节
    private String responsibleLink;

    //案发环节
    private String happenLink;

    //事故主要原因
    private String mainReason;

    //事故具体原因
    private String detailReason;

    //是否特种设备事故
    private String ifSpecialDevAccident;

    //经济类型
    private String ecoType;

    //结案日期
    private String endDate;

    //事故类别
    private String accidentType;

    //事故简要经过描述
    private String accidentDescription;

    //事故现场破坏描述
    private String accidentSiteDescription;

    //设备注册代码
    private String deviceNum;

    //设备其它相关信息
    private String deviceRelateInfo;

    //备注
    private String memo;

    //调查组负责人
    private String surveyResPerson;

    //调查组不同意见人员
    private String surveyDiffPerson;

    //调查组部门负责人
    private String surveyDeptPerson;

    //结案单位
    private String caseEndUnit;

    public String getAccidentDescription() {
        return accidentDescription;
    }

    public void setAccidentDescription(String accidentDescription) {
        this.accidentDescription = accidentDescription;
    }

    public String getAccidentPheno() {
        return accidentPheno;
    }

    public void setAccidentPheno(String accidentPheno) {
        this.accidentPheno = accidentPheno;
    }

    public String getAccidentPlace() {
        return accidentPlace;
    }

    public void setAccidentPlace(String accidentPlace) {
        this.accidentPlace = accidentPlace;
    }

    public String getAccidentSiteDescription() {
        return accidentSiteDescription;
    }

    public void setAccidentSiteDescription(String accidentSiteDescription) {
        this.accidentSiteDescription = accidentSiteDescription;
    }

    public String getAccidentType() {
        return accidentType;
    }

    public void setAccidentType(String accidentType) {
        this.accidentType = accidentType;
    }

    public String getAccidentUnit() {
        return accidentUnit;
    }

    public void setAccidentUnit(String accidentUnit) {
        this.accidentUnit = accidentUnit;
    }

    public String getAccidentUnitCode() {
        return accidentUnitCode;
    }

    public void setAccidentUnitCode(String accidentUnitCode) {
        this.accidentUnitCode = accidentUnitCode;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCaseEndUnit() {
        return caseEndUnit;
    }

    public void setCaseEndUnit(String caseEndUnit) {
        this.caseEndUnit = caseEndUnit;
    }

    public int getDeadNum() {
        return deadNum;
    }

    public void setDeadNum(int deadNum) {
        this.deadNum = deadNum;
    }

    public String getDetailReason() {
        return detailReason;
    }

    public void setDetailReason(String detailReason) {
        this.detailReason = detailReason;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(String deviceNum) {
        this.deviceNum = deviceNum;
    }

    public String getDeviceRelateInfo() {
        return deviceRelateInfo;
    }

    public void setDeviceRelateInfo(String deviceRelateInfo) {
        this.deviceRelateInfo = deviceRelateInfo;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public int getDirectEcoLoss() {
        return directEcoLoss;
    }

    public void setDirectEcoLoss(int directEcoLoss) {
        this.directEcoLoss = directEcoLoss;
    }

    public String getEcoType() {
        return ecoType;
    }

    public void setEcoType(String ecoType) {
        this.ecoType = ecoType;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getHappenLink() {
        return happenLink;
    }

    public void setHappenLink(String happenLink) {
        this.happenLink = happenLink;
    }

    public String getHappenTime() {
        return happenTime;
    }

    public void setHappenTime(String happenTime) {
        this.happenTime = happenTime;
    }

    public int getHeavyHurtNum() {
        return heavyHurtNum;
    }

    public void setHeavyHurtNum(int heavyHurtNum) {
        this.heavyHurtNum = heavyHurtNum;
    }

    public String getIfSpecialDevAccident() {
        return ifSpecialDevAccident;
    }

    public void setIfSpecialDevAccident(String ifSpecialDevAccident) {
        this.ifSpecialDevAccident = ifSpecialDevAccident;
    }

    public int getIndirectEcoLoss() {
        return indirectEcoLoss;
    }

    public void setIndirectEcoLoss(int indirectEcoLoss) {
        this.indirectEcoLoss = indirectEcoLoss;
    }

    public String getInfoSource() {
        return infoSource;
    }

    public void setInfoSource(String infoSource) {
        this.infoSource = infoSource;
    }

    public int getLightHurtNum() {
        return lightHurtNum;
    }

    public void setLightHurtNum(int lightHurtNum) {
        this.lightHurtNum = lightHurtNum;
    }

    public String getMainReason() {
        return mainReason;
    }

    public void setMainReason(String mainReason) {
        this.mainReason = mainReason;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

    public String getResponsibleLink() {
        return responsibleLink;
    }

    public void setResponsibleLink(String responsibleLink) {
        this.responsibleLink = responsibleLink;
    }

    public String getSurveyDeptPerson() {
        return surveyDeptPerson;
    }

    public void setSurveyDeptPerson(String surveyDeptPerson) {
        this.surveyDeptPerson = surveyDeptPerson;
    }

    public String getSurveyDiffPerson() {
        return surveyDiffPerson;
    }

    public void setSurveyDiffPerson(String surveyDiffPerson) {
        this.surveyDiffPerson = surveyDiffPerson;
    }

    public String getSurveyResPerson() {
        return surveyResPerson;
    }

    public void setSurveyResPerson(String surveyResPerson) {
        this.surveyResPerson = surveyResPerson;
    }

    public String getTrade() {
        return trade;
    }

    public void setTrade(String trade) {
        this.trade = trade;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
