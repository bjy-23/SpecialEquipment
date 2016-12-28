package com.wondersgroup.special.employment.bean;

import java.io.Serializable;

/**
 * Created by bjy on 2016/12/21.
 */

public class EChangeInfo implements Serializable {
    //主键
    private String uuid;

    //变更项目
    private String changePro;

    //变更日期
    private String changeDate;

    //变更人
    private String cahngePerson;

    //变更内容
    private String changeContent;

    //人员表主键
    private String personId;

    public String getCahngePerson() {
        return cahngePerson;
    }

    public void setCahngePerson(String cahngePerson) {
        this.cahngePerson = cahngePerson;
    }

    public String getChangeContent() {
        return changeContent;
    }

    public void setChangeContent(String changeContent) {
        this.changeContent = changeContent;
    }

    public String getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(String changeDate) {
        this.changeDate = changeDate;
    }

    public String getChangePro() {
        return changePro;
    }

    public void setChangePro(String changePro) {
        this.changePro = changePro;
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
