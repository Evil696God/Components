package com.kuke.common.network;


/**
 * Created by Jessica on 2020/02/12.
 * 登录实体类
 */

public class LoginResult {

    /**
     * token : user:f42477c1-2db2-4ee0-9160-113245641c3a
     * deviceId : null
     * ip : 192.168.0.6
     * id : 1
     * orgId : 0
     * studentAccount : c-test
     * realName : test
     * userName : test
     * headImg : null
     * status : 1
     * userType : null
     * expireTime : null
     * useropenid : null
     * createTime : 1589886509000
     * loginTime : 1589886637536
     * lastUpdateTime : 1589886637536
     */

    private String token;
    private String deviceId;
    private String ip;
    private String id;
    private int orgId;
    private String studentAccount;
    private String realName;
    private String userName;
    private String headImg;
    private int status;
    private int userType;
    private String expireTime;
    private String useropenid;
    private long createTime;
    private long loginTime;
    private long lastUpdateTime;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public String getStudentAccount() {
        return studentAccount;
    }

    public void setStudentAccount(String studentAccount) {
        this.studentAccount = studentAccount;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(long loginTime) {
        this.loginTime = loginTime;
    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public String getUseropenid() {
        return useropenid;
    }

    public void setUseropenid(String useropenid) {
        this.useropenid = useropenid;
    }
}
