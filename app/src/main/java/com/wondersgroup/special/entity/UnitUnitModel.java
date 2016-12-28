package com.wondersgroup.special.entity;

import java.io.Serializable;

/**
 * Created by bjy on 2016/12/6.
 */

public class UnitUnitModel implements Serializable {
    /**
     * 主键
     */
    private String uuid;
    /**
     * 单元名称
     */
    private String uUnitName;
    /**
     * 单元地址
     */
    private String uUnitAddress;
    /**
     * 锅炉
     */
    private String type1;
    /**
     * 压力容器
     */
    private String type2;
    /**
     * 压力管道
     */
    private String type3;
    /**
     * 电梯
     */
    private String type4;
    /**
     * 起重机械
     */
    private String type5;
    /**
     * 客运索道
     */
    private String type6;
    /**
     * 大型游乐设施
     */
    private String type7;
    /**
     * 场（厂）内专用机动车辆
     */
    private String type8;

    public String getUuid() {
        return uuid;
    }
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    public String getuUnitName() {
        return uUnitName;
    }
    public void setuUnitName(String uUnitName) {
        this.uUnitName = uUnitName;
    }
    public String getuUnitAddress() {
        return uUnitAddress;
    }
    public void setuUnitAddress(String uUnitAddress) {
        this.uUnitAddress = uUnitAddress;
    }
    public String getType1() {
        return type1;
    }
    public void setType1(String type1) {
        this.type1 = type1;
    }
    public String getType2() {
        return type2;
    }
    public void setType2(String type2) {
        this.type2 = type2;
    }
    public String getType3() {
        return type3;
    }
    public void setType3(String type3) {
        this.type3 = type3;
    }
    public String getType4() {
        return type4;
    }
    public void setType4(String type4) {
        this.type4 = type4;
    }
    public String getType5() {
        return type5;
    }
    public void setType5(String type5) {
        this.type5 = type5;
    }
    public String getType6() {
        return type6;
    }
    public void setType6(String type6) {
        this.type6 = type6;
    }
    public String getType7() {
        return type7;
    }
    public void setType7(String type7) {
        this.type7 = type7;
    }
    public String getType8() {
        return type8;
    }
    public void setType8(String type8) {
        this.type8 = type8;
    }
}
