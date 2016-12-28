package com.wondersgroup.special.entity;

import java.io.Serializable;

/**
 * Created by bjy on 2016/12/6.
 */

public class PersonLicenseModel implements Serializable {
    private String uuid;
    private String certName;
    private String certType;
    private String name;
    private String certGrantDate;
    private String certAvailableDate;

    public String getCertAvailableDate() {
        return certAvailableDate;
    }

    public void setCertAvailableDate(String certAvailableDate) {
        this.certAvailableDate = certAvailableDate;
    }

    public String getCertGrantDate() {
        return certGrantDate;
    }

    public void setCertGrantDate(String certGrantDate) {
        this.certGrantDate = certGrantDate;
    }

    public String getCertName() {
        return certName;
    }

    public void setCertName(String certName) {
        this.certName = certName;
    }

    public String getCertType() {
        return certType;
    }

    public void setCertType(String certType) {
        this.certType = certType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
