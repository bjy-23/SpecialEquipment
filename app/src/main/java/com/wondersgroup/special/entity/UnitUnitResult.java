package com.wondersgroup.special.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by bjy on 2016/12/6.
 */

public class UnitUnitResult extends BaseListResult implements Serializable {
    private List<UnitUnitModel> resultList;

    public List<UnitUnitModel> getResultList() {
        return resultList;
    }

    public void setResultList(List<UnitUnitModel> resultList) {
        this.resultList = resultList;
    }
}
