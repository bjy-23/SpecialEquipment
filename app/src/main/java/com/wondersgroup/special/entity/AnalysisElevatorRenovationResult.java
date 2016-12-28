package com.wondersgroup.special.entity;

import java.util.List;

/**
 * Created by root on 12/22/16.
 */

public class AnalysisElevatorRenovationResult {
    List<AnalysisResult> fixData;
    List<AnalysisResult> reformData;
    List<AnalysisResult> repairData;

    public List<AnalysisResult> getFixData() {
        return fixData;
    }

    public void setFixData(List<AnalysisResult> fixData) {
        this.fixData = fixData;
    }

    public List<AnalysisResult> getReformData() {
        return reformData;
    }

    public void setReformData(List<AnalysisResult> reformData) {
        this.reformData = reformData;
    }

    public List<AnalysisResult> getRepairData() {
        return repairData;
    }

    public void setRepairData(List<AnalysisResult> repairData) {
        this.repairData = repairData;
    }
}
