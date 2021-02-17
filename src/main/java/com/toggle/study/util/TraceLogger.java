package com.toggle.study.util;

public class TraceLogger {
    private long startTime;
    private String direction;
    private String type;
    private String methodType;
    private String entityId;
    private long transactionId;
    private String requestURL;
    private String deviceOS;
    private String deviceOSVersion;
    private String deviceModel;
    private String deviceVendor;
    private String deviceAppVersion;
    private String information;
    private int statusCode;
    private long differenceTime;

    public TraceLogger() {
        this.startTime = System.currentTimeMillis();
        this.direction = "";
        this.type = "";
        this.methodType = "";
        this.entityId = "";
        this.requestURL = "";
        this.information = "";
        this.deviceOS = "";
        this.deviceOSVersion = "";
        this.deviceModel = "";
        this.deviceVendor = "";
        this.deviceAppVersion = "";
        this.transactionId = this.startTime % 10000;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public String getRequestURL() {
        return requestURL;
    }

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getMethodType() {
        return methodType;
    }

    public void setMethodType(String methodType) {
        this.methodType = methodType;
    }

    public String getDeviceOS() {
        return deviceOS;
    }

    public void setDeviceOS(String deviceOS) {
        this.deviceOS = deviceOS;
    }

    public String getDeviceOSVersion() {
        return deviceOSVersion;
    }

    public void setDeviceOSVersion(String deviceOSVersion) {
        this.deviceOSVersion = deviceOSVersion;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getDeviceVendor() {
        return deviceVendor;
    }

    public void setDeviceVendor(String deviceVendor) {
        this.deviceVendor = deviceVendor;
    }

    public String getDeviceAppVersion() {
        return deviceAppVersion;
    }

    public void setDeviceAppVersion(String deviceAppVersion) {
        this.deviceAppVersion = deviceAppVersion;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public long getDifferenceTime() {
        return differenceTime;
    }

    public void setDifferenceTime(long differenceTime) {
        this.differenceTime = differenceTime;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(direction)
                .append(",")
                .append(type)
                .append(",")
                .append(methodType)
                .append(",")
                .append(entityId)
                .append(",")
                .append(transactionId)
                .append(",")
                .append(deviceOS).append("|").append(deviceOSVersion).append("|").append(deviceModel).append("|").append(deviceVendor).append("|").append(deviceAppVersion)
                .append(",")
                .append(requestURL)
                .append(",")
                .append(information);
        if (statusCode > 0) {
            sb.append(",")
                    .append(statusCode)
                    .append(",")
                    .append(differenceTime);
        }
        return sb.toString();
    }
}
