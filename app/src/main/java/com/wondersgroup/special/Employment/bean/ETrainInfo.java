package com.wondersgroup.special.employment.bean;

import java.io.Serializable;

/**
 * Created by bjy on 2016/12/21.
 */

public class ETrainInfo implements Serializable {
    //主键
    private String uuid;

    //培训单位
    private String trainUnit;

    //结束日期
    private String endDate;

    //人员表id
    private String personId;

    //培训内容
    private String trainContent;

    //培训成绩
    private String trainScore;

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getTrainContent() {
        return trainContent;
    }

    public void setTrainContent(String trainContent) {
        this.trainContent = trainContent;
    }

    public String getTrainScore() {
        return trainScore;
    }

    public void setTrainScore(String trainScore) {
        this.trainScore = trainScore;
    }

    public String getTrainUnit() {
        return trainUnit;
    }

    public void setTrainUnit(String trainUnit) {
        this.trainUnit = trainUnit;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
