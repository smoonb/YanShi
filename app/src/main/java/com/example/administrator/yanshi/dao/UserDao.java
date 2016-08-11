package com.example.administrator.yanshi.dao;

import com.example.administrator.yanshi.po.TblUser;

import java.util.List;

/**
 * Created by Administrator on 2016/4/13 0013.
 */
public interface UserDao {
    //0本增
    public int userInsertLocal(TblUser user);
    //1 本删
    public int userDeleteLocal(TblUser user);
    //2本改
    public int userUpdateLocal(TblUser user);
    //3本单
    public int userSelectOneLocal(TblUser user);
    //4本多
    public List<TblUser> userSelectAllLocal();
}
