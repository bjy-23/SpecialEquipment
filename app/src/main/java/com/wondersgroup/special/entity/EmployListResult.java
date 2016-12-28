package com.wondersgroup.special.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by root on 12/5/16.
 */

public class EmployListResult extends BaseListResult {
    private List<EmployListResult.EmployList> resultList;

    public List<EmployListResult.EmployList> getResultList() {
        return resultList;
    }

    public void setResultList(List<EmployListResult.EmployList> resultList) {
        this.resultList = resultList;
    }

    public static class EmployList implements Serializable {
        private String uuid;
        private String name;
        private String unitName;
        private String certType;
        private String cardId;

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUnitName() {
            return unitName;
        }

        public void setUnitName(String unitName) {
            this.unitName = unitName;
        }

        public String getCertType() {
            return certType;
        }

        public void setCertType(String certType) {
            this.certType = certType;
        }

        public String getCardId() {
            return cardId;
        }

        public void setCardId(String cardId) {
            this.cardId = cardId;
        }
    }
}
