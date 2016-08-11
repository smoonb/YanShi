package com.example.administrator.yanshi.po;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.util.Date;

/**
 * Created by Administrator on 2016/4/13 0013.
 */
@Table(name="tblUser")
public class TblUser {
    //0定义子段
    @Column(name="id",isId = true)
    private  int id;
    @Column(name="userName")
    private  String userName;
    @Column(name="userAge")
    private  int userAge;
    @Column(name="recordDate")
    private Date recordDate;  //alt+insert  快速get set

    public int getId() {
        return id;
    }public void setId(int id) {
        this.id = id;
    }public String getUserName() {
        return userName;
    }public void setUserName(String userName) {
        this.userName = userName;
    }public int getUserAge() {
        return userAge;
    }public void setUserAge(int userAge) {
        this.userAge = userAge;
    }public Date getRecordDate() {
        return recordDate;
    }public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

//2重载构造函数

    public TblUser(String userName, int userAge) {
        this.userName = userName;
        this.userAge = userAge;
    }
    //2.1空构造  必须要有

    public TblUser() {
    }

    //重写toString()方法

    @Override
    public String toString() {
        return "TblUser{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userAge=" + userAge +
                ", recordDate=" + recordDate +
                '}';
    }
}
