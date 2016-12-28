package com.wondersgroup.special.entity;

import java.io.Serializable;

/**
 * Created by bjy on 2016/12/21.
 */

public class PAwardInfo implements Serializable {
    //主键
    private String uuid;

    //奖惩项目
    private String awardPro;

    //奖惩日期
    private String awardDate;

    //奖惩机构
    private String awardOrgan;

    //人员表主键
    private String personId;

    public String getAwardDate() {
        return awardDate;
    }

    public void setAwardDate(String awardDate) {
        this.awardDate = awardDate;
    }

    public String getAwardOrgan() {
        return awardOrgan;
    }

    public void setAwardOrgan(String awardOrgan) {
        this.awardOrgan = awardOrgan;
    }

    public String getAwardPro() {
        return awardPro;
    }

    public void setAwardPro(String awardPro) {
        this.awardPro = awardPro;
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
}
