package com.wondersgroup.special.entity;

/**
 * Created by root on 12/27/16.
 */

public class Version {
    private String version;

    private String versionCount;

    private String downLoadAddress;

    public void setVersion(String version) {
        this.version = version;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersionCount(String versionCount) {
        this.versionCount = versionCount;
    }

    public String getVersionCount() {
        return this.versionCount;
    }

    public void setDownLoadAddress(String downLoadAddress) {
        this.downLoadAddress = downLoadAddress;
    }

    public String getDownLoadAddress() {
        return this.downLoadAddress;
    }
}
