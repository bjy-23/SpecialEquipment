package com.wondersgroup.special.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 检验档案基本信息
 *
 * @author Martin
 */
public class CheckoutListResult extends BaseListResult {
    private List<CheckoutList> resultList;

    public List<CheckoutList> getResultList() {
        return resultList;
    }

    public void setResultList(List<CheckoutList> resultList) {
        this.resultList = resultList;
    }

    public static class CheckoutList implements Serializable {
        private static final long serialVersionUID = 798837766099L;

        //主键
        private String uuid;

        //单位id
        private String unitId;

        //组织机构代码
        private String unitCode;

        //企业信用代码
        private String creditCode;

        //单位名称
        private String unitName;

        //设备注册代码
        private String deviceNumber;

        //设备id
        private String deviceId;

        //设备名称
        private String deviceName;

        //设备类型
        private String deviceType;

        //检验日期
        private String checkDaye;

        //检验类别
        private String checkType;

        //检验报告编号
        private String reportNo;

        //检验结论
        private String checkResult;

        //检验单位
        private String checkUnit;

        //监察档案id
        private String visionId;

        // Constructors

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getUnitId() {
            return unitId;
        }

        public void setUnitId(String unitId) {
            this.unitId = unitId;
        }

        public String getUnitCode() {
            return unitCode;
        }

        public void setUnitCode(String unitCode) {
            this.unitCode = unitCode;
        }

        public String getCreditCode() {
            return creditCode;
        }

        public void setCreditCode(String creditCode) {
            this.creditCode = creditCode;
        }

        public String getUnitName() {
            return unitName;
        }

        public void setUnitName(String unitName) {
            this.unitName = unitName;
        }

        public String getDeviceNumber() {
            return deviceNumber;
        }

        public void setDeviceNumber(String deviceNumber) {
            this.deviceNumber = deviceNumber;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public String getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(String deviceName) {
            this.deviceName = deviceName;
        }

        public String getDeviceType() {
            return deviceType;
        }

        public void setDeviceType(String deviceType) {
            this.deviceType = deviceType;
        }

        public String getCheckDaye() {
            return checkDaye;
        }

        public void setCheckDaye(String checkDaye) {
            this.checkDaye = checkDaye;
        }

        public String getCheckType() {
            return checkType;
        }

        public void setCheckType(String checkType) {
            this.checkType = checkType;
        }

        public String getReportNo() {
            return reportNo;
        }

        public void setReportNo(String reportNo) {
            this.reportNo = reportNo;
        }

        public String getCheckResult() {
            return checkResult;
        }

        public void setCheckResult(String checkResult) {
            this.checkResult = checkResult;
        }

        public String getCheckUnit() {
            return checkUnit;
        }

        public void setCheckUnit(String checkUnit) {
            this.checkUnit = checkUnit;
        }

        public String getVisionId() {
            return visionId;
        }

        public void setVisionId(String visionId) {
            this.visionId = visionId;
        }

        public Object getPrimaryKey() {
            return uuid;
        }
    }
}

