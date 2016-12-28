package com.wondersgroup.special.entity;

/**
 * Created by root on 12/1/16.
 */

public class BaseListResult {
    private String currPageNo;
    private String pageCount;
    private String pageSize;
    private int totalRecord;

    public String getCurrPageNo() {
        return currPageNo;
    }

    public void setCurrPageNo(String currPageNo) {
        this.currPageNo = currPageNo;
    }

    public String getPageCount() {
        return pageCount;
    }

    public void setPageCount(String pageCount) {
        this.pageCount = pageCount;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

}
