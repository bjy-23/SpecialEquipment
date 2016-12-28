package com.wondersgroup.special.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by bjy on 2016/12/6.
 */

public class PersonLicenseResult extends BaseListResult implements Serializable{
    private List<PersonLicenseModel> resultList;

    public List<PersonLicenseModel> getResultList() {
        return resultList;
    }

    public void setResultList(List<PersonLicenseModel> resultList) {
        this.resultList = resultList;
    }
}
