package com.wondersgroup.special.entity;

import java.io.Serializable;

/**
 * Created by root on 12/21/16.
 */

public class StatisticsListResult implements Serializable {
    private String areaId;
    private String areaName;
    private String inspUnitNum;
    private String outUseNum;
    private String proUnitNum;
    private String totalUnitNum;
    private String useNum;

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getInspUnitNum() {
        return inspUnitNum;
    }

    public void setInspUnitNum(String inspUnitNum) {
        this.inspUnitNum = inspUnitNum;
    }

    public String getOutUseNum() {
        return outUseNum;
    }

    public void setOutUseNum(String outUseNum) {
        this.outUseNum = outUseNum;
    }

    public String getProUnitNum() {
        return proUnitNum;
    }

    public void setProUnitNum(String proUnitNum) {
        this.proUnitNum = proUnitNum;
    }

    public String getTotalUnitNum() {
        return totalUnitNum;
    }

    public void setTotalUnitNum(String totalUnitNum) {
        this.totalUnitNum = totalUnitNum;
    }

    public String getUseNum() {
        return useNum;
    }

    public void setUseNum(String useNum) {
        this.useNum = useNum;
    }
}
