package com.wondersgroup.special.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 投举档案列表
 */
public class CompListResult extends BaseListResult {
    private List<CompList> resultList;

    public List<CompList> getResultList() {
        return resultList;
    }

    public void setResultList(List<CompList> resultList) {
        this.resultList = resultList;
    }

    public static class CompList implements Serializable {
        /**
         * 主键
         */
        private String uuid;
        /**
         * 企业信用代码/组织机构代码
         */
        private String compCreditCode;
        /**
         * 被投诉对象名称
         */
        private String compObjectName;
        /**
         * 客户姓名
         */
        private String clientName;
        /**
         * 受理日期
         */
        private String dealDate;

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getCompCreditCode() {
            return compCreditCode;
        }

        public void setCompCreditCode(String compCreditCode) {
            this.compCreditCode = compCreditCode;
        }

        public String getCompObjectName() {
            return compObjectName;
        }

        public void setCompObjectName(String compObjectName) {
            this.compObjectName = compObjectName;
        }

        public String getClientName() {
            return clientName;
        }

        public void setClientName(String clientName) {
            this.clientName = clientName;
        }

        public String getDealDate() {
            return dealDate;
        }

        public void setDealDate(String dealDate) {
            this.dealDate = dealDate;
        }
    }
}
