package com.wondersgroup.special.entity;

import java.io.Serializable;

/**
 * Created by bjy on 2016/12/15.
 */

public class QueryAccidentEntity implements Serializable {
    /**
     * 事故表主键
     */
    private String uuid;
    /**
     * 事故单位
     */
    private String accidentUnit;
    /**
     * 发生时间
     */
    private String happenTime;
    /**
     * 上报时间
     */
    private String reportTime;
    /**
     * 所属区域
     */
    private String area;
    /**
     * 事故地点
     */
    private String accidentPlace;
    /**
     * 事故类别
     */
    private String accidentType;

    public String getAccidentPlace() {
        return accidentPlace;
    }

    public void setAccidentPlace(String accidentPlace) {
        this.accidentPlace = accidentPlace;
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getHappenTime() {
        return happenTime;
    }

    public void setHappenTime(String happenTime) {
        this.happenTime = happenTime;
    }

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
