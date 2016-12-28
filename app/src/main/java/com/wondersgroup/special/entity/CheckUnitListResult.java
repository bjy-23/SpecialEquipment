package com.wondersgroup.special.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by root on 12/5/16.
 */

public class CheckUnitListResult extends BaseListResult {
    private List<CheckList> resultList;

    public List<CheckList> getResultList() {
        return resultList;
    }

    public void setResultList(List<CheckList> resultList) {
        this.resultList = resultList;
    }

    public static class CheckList implements Serializable {
        private String uuid;
        private String unitName;
        private String socialCreditCode;
        private String unitAddress;
        private String unitAdminAreaCode;
        private String organLevel;
        private String organProperty;
        private String unitType;

        public String getUnitType() {
            return unitType;
        }

        public void setUnitType(String unitType) {
            this.unitType = unitType;
        }

        public String getUuid() {
            return uuid;
        }

        public String getUnitName() {
            return unitName;
        }

        public void setUnitName(String unitName) {
            this.unitName = unitName;
        }

        public String getSocialCreditCode() {
            return socialCreditCode;
        }

        public void setSocialCreditCode(String socialCreditCode) {
            this.socialCreditCode = socialCreditCode;
        }

        public String getUnitAddress() {
            return unitAddress;
        }

        public void setUnitAddress(String unitAddress) {
            this.unitAddress = unitAddress;
        }

        public String getUnitAdminAreaCode() {
            return unitAdminAreaCode;
        }

        public void setUnitAdminAreaCode(String unitAdminAreaCode) {
            this.unitAdminAreaCode = unitAdminAreaCode;
        }

        public String getOrganLevel() {
            return organLevel;
        }

        public void setOrganLevel(String organLevel) {
            this.organLevel = organLevel;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getOrganProperty() {
            return organProperty;
        }

        public void setOrganProperty(String organProperty) {
            this.organProperty = organProperty;
        }
    }
}
