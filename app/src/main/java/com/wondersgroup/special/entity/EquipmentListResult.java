package com.wondersgroup.special.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by root on 12/1/16.
 */

public class EquipmentListResult extends BaseListResult {

    private List<ResultList> resultList;

    public List<ResultList> getResultList() {
        return resultList;
    }

    public void setResultList(List<ResultList> resultList) {
        this.resultList = resultList;
    }

    public static class ResultList implements Serializable{
        private String deviceAddress;
        private String deviceName;
        private String deviceNumber;
        private String deviceType1;
        private String deviceType2;
        private String deviceType3;
        private String ifCheck;
        private String ifDanger;
        private String useCerNum;
        private String uuid;

        public String getDeviceAddress() {
            return deviceAddress;
        }

        public void setDeviceAddress(String deviceAddress) {
            this.deviceAddress = deviceAddress;
        }

        public String getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(String deviceName) {
            this.deviceName = deviceName;
        }

        public String getDeviceNumber() {
            return deviceNumber;
        }

        public void setDeviceNumber(String deviceNumber) {
            this.deviceNumber = deviceNumber;
        }

        public String getDeviceType1() {
            return deviceType1;
        }

        public void setDeviceType1(String deviceType1) {
            this.deviceType1 = deviceType1;
        }

        public String getDeviceType2() {
            return deviceType2;
        }

        public void setDeviceType2(String deviceType2) {
            this.deviceType2 = deviceType2;
        }

        public String getDeviceType3() {
            return deviceType3;
        }

        public void setDeviceType3(String deviceType3) {
            this.deviceType3 = deviceType3;
        }

        public String getIfCheck() {
            return ifCheck;
        }

        public void setIfCheck(String ifCheck) {
            this.ifCheck = ifCheck;
        }

        public String getIfDanger() {
            return ifDanger;
        }

        public void setIfDanger(String ifDanger) {
            this.ifDanger = ifDanger;
        }

        public String getUseCerNum() {
            return useCerNum;
        }

        public void setUseCerNum(String useCerNum) {
            this.useCerNum = useCerNum;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }
    }
}
