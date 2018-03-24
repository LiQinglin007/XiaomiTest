package com.li.xiaomi.xiaomitest.bean;

/**
 * 类描述：登陆接口
 * 作  者：Admin or 李小米
 * 时  间：2017/10/11
 * 修改备注：
 */
public class LoginBean {


    /**
     * code : 0
     * msg : 操作成功
     * serverTime : 1517036502278
     * data : {"Token":"69FFD4D1B082721771C3149E4215F2EBA9880785A043DA43B50A7FC5FE79CDA93FE31B63E1DB49C92976693CBA85D9FF2B917D2B20D36E6FD17AD8A2B04832AFF5238331A75F766EC5DCF802CFBBF105622F1495698AAA79D0B45EAE81B268C3F885570456F7E899C39A780C526DAA0577FEFEFEC0940D5DD5BA9740EBF1BE73CB8C6F7AE489832489B1EC68C3301839D47EB17533F33BC00ECD73109A6989F927D6637D135A9B4A578039D9EAD63A59","UserName":null,"Mobile":"15284224244","Expiration":1517040102209,"IsMainUser":true,"MainUserId":null}
     */

    private int code;
    private String msg;
    private long serverTime;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getServerTime() {
        return serverTime;
    }

    public void setServerTime(long serverTime) {
        this.serverTime = serverTime;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * Token : 69FFD4D1B082721771C3149E4215F2EBA9880785A043DA43B50A7FC5FE79CDA93FE31B63E1DB49C92976693CBA85D9FF2B917D2B20D36E6FD17AD8A2B04832AFF5238331A75F766EC5DCF802CFBBF105622F1495698AAA79D0B45EAE81B268C3F885570456F7E899C39A780C526DAA0577FEFEFEC0940D5DD5BA9740EBF1BE73CB8C6F7AE489832489B1EC68C3301839D47EB17533F33BC00ECD73109A6989F927D6637D135A9B4A578039D9EAD63A59
         * UserName : null
         * Mobile : 15284224244
         * Expiration : 1517040102209
         * IsMainUser : true
         * MainUserId : null
         */

        private String Token;
        private String UserName;
        private String Mobile;
        private long Expiration;//token期时间
        private boolean IsMainUser;//是否为主账户
        private String MainUserId;//上一级账户的id
        private String UserId;//当前用户id

        public boolean isMainUser() {
            return IsMainUser;
        }

        public void setMainUser(boolean mainUser) {
            IsMainUser = mainUser;
        }

        public String getUserId() {
            return UserId;
        }

        public void setUserId(String userId) {
            UserId = userId;
        }

        public String getToken() {
            return Token;
        }

        public void setToken(String Token) {
            this.Token = Token;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public String getMobile() {
            return Mobile;
        }

        public void setMobile(String Mobile) {
            this.Mobile = Mobile;
        }

        public long getExpiration() {
            return Expiration;
        }

        public void setExpiration(long Expiration) {
            this.Expiration = Expiration;
        }

        public boolean isIsMainUser() {
            return IsMainUser;
        }

        public void setIsMainUser(boolean IsMainUser) {
            this.IsMainUser = IsMainUser;
        }

        public String getMainUserId() {
            return MainUserId;
        }

        public void setMainUserId(String MainUserId) {
            this.MainUserId = MainUserId;
        }
    }
}
