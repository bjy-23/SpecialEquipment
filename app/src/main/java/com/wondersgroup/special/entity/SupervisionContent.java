package com.wondersgroup.special.entity;

import java.io.Serializable;

/**
 * 监察档案监察内容
 *
 * @author Martin
 */
public class SupervisionContent implements Serializable {

    private static final long serialVersionUID = 703880128821L;

    //主键
    private String uuid;

    //监察信息表id
    private String visionId;

    //检查结果
    private String result;

    //检查情况
    private String checkSituation;

    //取证
    private String evidence;

    //检查项目
    private String checkPro;


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getVisionId() {
        return visionId;
    }

    public void setVisionId(String visionId) {
        this.visionId = visionId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getCheckSituation() {
        return checkSituation;
    }

    public void setCheckSituation(String checkSituation) {
        this.checkSituation = checkSituation;
    }

    public String getEvidence() {
        return evidence;
    }

    public void setEvidence(String evidence) {
        this.evidence = evidence;
    }

    public String getCheckPro() {
        return checkPro;
    }

    public void setCheckPro(String checkPro) {
        this.checkPro = checkPro;
    }


    public Object getPrimaryKey() {
        return uuid;
    }
}

