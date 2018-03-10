package com.theadex.dataengineer.challenge;


public class Request {

    private long requestID;
    private String userID;
    private int customerID;
    private String userAgent;
    private String url;
    private long timestamp;

    public Request(long requestID, String userID, int customerID,
                   String userAgent, String url, long timestamp) {
        this.requestID = requestID;
        this.userID = userID;
        this.customerID = customerID;
        this.userAgent = userAgent;
        this.url = url;
        this.timestamp = timestamp;
    }

    public long getRequestID() {
        return requestID;
    }

    public void setRequestID(long requestID) {
        this.requestID = requestID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Request{" +
                "requestID=" + requestID +
                ", userID=\"" + userID + '"' +
                ", customerID=" + customerID +
                ", userAgent=\"" + userAgent + '"' +
                ", url=\"" + url + '"' +
                ", timestamp=" + timestamp +
                '}';
    }
}
