package com.example.administrator.yanshi.service.impl;

import com.example.administrator.yanshi.dao.UserDao;
import com.example.administrator.yanshi.dao.impl.UserDaoImpl;
import com.example.administrator.yanshi.po.TblUser;
import com.example.administrator.yanshi.service.UserService;

import java.util.List;

/**
 * Created by Administrator on 2016/4/13 0013.
 */
public class UserServiceImpl implements UserService {

    //0定义组件
    private UserDao userDao;
    //1定义空构造

    public UserServiceImpl() {
        userDao=new UserDaoImpl();
    }

    @Override
    public int userInsertLocal(TblUser user) {
        return userDao.userInsertLocal(user);
    }

    @Override
    public int userDeleteLocal(TblUser user) {
        return userDao.userDeleteLocal(user);
    }

    @Override
    public int userUpdateLocal(TblUser user) {
        return userDao.userUpdateLocal(user);
    }

    @Override
    public int userSelectOneLocal(TblUser user) {
        return userDao.userSelectOneLocal(user);
    }

    @Override
    public List<TblUser> userSelectAllLocal() {
        return userDao.userSelectAllLocal();
    }
}
