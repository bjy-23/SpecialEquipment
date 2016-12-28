package com.wondersgroup.special.entity;

/**
 * 获奖记录
 */
public class UnitAwardResult {

    /**
     * 主键
     */
    private String uuid;
    /**
     * 单位名称
     */
    private String unitName;
    /**
     * 成果名称
     */
    private String achievement;
    /**
     * 项目名称
     */
    private String proName;
    /**
     * 获奖情况
     */
    private String awardStatus;
    /**
     * 受理局
     */
    private String acceptOrgan;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getAchievement() {
        return achievement;
    }

    public void setAchievement(String achievement) {
        this.achievement = achievement;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getAwardStatus() {
        return awardStatus;
    }

    public void setAwardStatus(String awardStatus) {
        this.awardStatus = awardStatus;
    }

    public String getAcceptOrgan() {
        return acceptOrgan;
    }

    public void setAcceptOrgan(String acceptOrgan) {
        this.acceptOrgan = acceptOrgan;
    }

}
