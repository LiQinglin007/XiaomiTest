package com.li.xiaomi.xiaomi_core.dbdata;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 类描述：
 * 作  者：Admin or 李小米
 * 时  间：2018/3/26
 * 修改备注：
 */
@Entity
public class DBUser {
    @Id
    Long UserId;
    String UserName;
    String UserEmail;
    String UserPhone;
    String UserPassword;
    @Generated(hash = 188409467)
    public DBUser(Long UserId, String UserName, String UserEmail, String UserPhone,
            String UserPassword) {
        this.UserId = UserId;
        this.UserName = UserName;
        this.UserEmail = UserEmail;
        this.UserPhone = UserPhone;
        this.UserPassword = UserPassword;
    }
    @Generated(hash = 138933025)
    public DBUser() {
    }
    public Long getUserId() {
        return this.UserId;
    }
    public void setUserId(Long UserId) {
        this.UserId = UserId;
    }
    public String getUserName() {
        return this.UserName;
    }
    public void setUserName(String UserName) {
        this.UserName = UserName;
    }
    public String getUserEmail() {
        return this.UserEmail;
    }
    public void setUserEmail(String UserEmail) {
        this.UserEmail = UserEmail;
    }
    public String getUserPhone() {
        return this.UserPhone;
    }
    public void setUserPhone(String UserPhone) {
        this.UserPhone = UserPhone;
    }
    public String getUserPassword() {
        return this.UserPassword;
    }
    public void setUserPassword(String UserPassword) {
        this.UserPassword = UserPassword;
    }
}
