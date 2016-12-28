package com.wondersgroup.special.employment.bean;

import java.io.Serializable;

/**
 * Created by bjy on 2016/12/23.
 */

public class EBasicInfo implements Serializable{
    //主键
    private String uuid;

    //身份证号
    private String cardId;

    //姓名
    private String name;

    //性别
    private String sex;

    //学历
    private String eduction;

    //出身年月
    private String bornyear;

    //专业
    private String profession;

    //联系地址
    private String address;

    //受聘单位组织机构代码
    private String unitCode;

    //受聘单位信用代码
    private String creditCode;

    //管理单位名称
    private String unitName;

    //电话
    private String tel;

    //毕业院校
    private String graduteInstitutions;

    //邮政编码
    private String postcode;

    //传真
    private String fax;

    //电子信箱
    private String email;

    //职称
    private String title;

    //职务
    private String post;

    //是否公务员
    private String civilServantsFlag;

    //是否事业编制
    private String formationFlag;

    //是否借调人员
    private String loanFlag;

    //是否协管人员
    private String coordinatorsFlag;

    //部门
    private String department;

    //岗位
    private String quarters;

    //负责工作
    private String work;

    //人员编号
    private String personNo;

    //从事使用管理工作年限
    private String manageLife;

    //从事设备生产工作年限
    private String productLife;

    //特种设备知识培训学时
    private String trainLife;

    //归属
    private String ascription;

    //是否检查人员
    private String checkFlag;

    //从事监察工作年限
    private String monitorLife;

    //数据源
    private String dataSource;

    //管理单位地址
    private String unitAddress;

    //管理单位联系电话
    private String unitTel;

    //是否普通公民
    private String citizenFlag;

    //管理单位联系人
    private String unitPerson;

    //是否为本省市发证人
    private String issuingFlag;

    //备注
    private String remark;

    //单位id
    private String unitId;

    //机构id
    private String organId;

    //人员属性（0、检验检测人员，1、无损检测人员，2、监察人员，3、作业人员）
    private String personType;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAscription() {
        return ascription;
    }

    public void setAscription(String ascription) {
        this.ascription = ascription;
    }

    public String getBornyear() {
        return bornyear;
    }

    public void setBornyear(String bornyear) {
        this.bornyear = bornyear;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(String checkFlag) {
        this.checkFlag = checkFlag;
    }

    public String getCitizenFlag() {
        return citizenFlag;
    }

    public void setCitizenFlag(String citizenFlag) {
        this.citizenFlag = citizenFlag;
    }

    public String getCivilServantsFlag() {
        return civilServantsFlag;
    }

    public void setCivilServantsFlag(String civilServantsFlag) {
        this.civilServantsFlag = civilServantsFlag;
    }

    public String getCoordinatorsFlag() {
        return coordinatorsFlag;
    }

    public void setCoordinatorsFlag(String coordinatorsFlag) {
        this.coordinatorsFlag = coordinatorsFlag;
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEduction() {
        return eduction;
    }

    public void setEduction(String eduction) {
        this.eduction = eduction;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getFormationFlag() {
        return formationFlag;
    }

    public void setFormationFlag(String formationFlag) {
        this.formationFlag = formationFlag;
    }

    public String getGraduteInstitutions() {
        return graduteInstitutions;
    }

    public void setGraduteInstitutions(String graduteInstitutions) {
        this.graduteInstitutions = graduteInstitutions;
    }

    public String getIssuingFlag() {
        return issuingFlag;
    }

    public void setIssuingFlag(String issuingFlag) {
        this.issuingFlag = issuingFlag;
    }

    public String getLoanFlag() {
        return loanFlag;
    }

    public void setLoanFlag(String loanFlag) {
        this.loanFlag = loanFlag;
    }

    public String getManageLife() {
        return manageLife;
    }

    public void setManageLife(String manageLife) {
        this.manageLife = manageLife;
    }

    public String getMonitorLife() {
        return monitorLife;
    }

    public void setMonitorLife(String monitorLife) {
        this.monitorLife = monitorLife;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrganId() {
        return organId;
    }

    public void setOrganId(String organId) {
        this.organId = organId;
    }

    public String getPersonNo() {
        return personNo;
    }

    public void setPersonNo(String personNo) {
        this.personNo = personNo;
    }

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getProductLife() {
        return productLife;
    }

    public void setProductLife(String productLife) {
        this.productLife = productLife;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getQuarters() {
        return quarters;
    }

    public void setQuarters(String quarters) {
        this.quarters = quarters;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTrainLife() {
        return trainLife;
    }

    public void setTrainLife(String trainLife) {
        this.trainLife = trainLife;
    }

    public String getUnitAddress() {
        return unitAddress;
    }

    public void setUnitAddress(String unitAddress) {
        this.unitAddress = unitAddress;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitPerson() {
        return unitPerson;
    }

    public void setUnitPerson(String unitPerson) {
        this.unitPerson = unitPerson;
    }

    public String getUnitTel() {
        return unitTel;
    }

    public void setUnitTel(String unitTel) {
        this.unitTel = unitTel;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }
}
