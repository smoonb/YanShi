package com.example.administrator.yanshi.dao.impl;

import com.example.administrator.yanshi.dao.UserDao;
import com.example.administrator.yanshi.po.TblUser;
import com.example.administrator.yanshi.util.Util;

import org.xutils.DbManager;
import org.xutils.db.sqlite.WhereBuilder;

import java.util.List;

/**
 * Created by Administrator on 2016/4/13 0013.
 */
public class UserDaoImpl implements UserDao {
    //0定义组件

    private  static DbManager dbManager;
    private  int intResult=-1;
     private WhereBuilder wb;
    private TblUser tblUser;
    //1空构造
    public UserDaoImpl() {

       dbManager= Util.getDbManager();
   }

    @Override
    public int userInsertLocal(TblUser user) {
        try {
            dbManager.save(user);
            intResult=1;
        } catch (Exception e) {
            e.printStackTrace();
            //将异常存放本地数据库
            intResult=-1;
        }   finally {
            return intResult;
        }

    }

    @Override
    public int userDeleteLocal(TblUser user) {
        //设置条件
        wb= WhereBuilder.b();
        wb.expr("userName='"+user.getUserName()+"'");
       wb.and("userAge","=",user.getUserAge());
        //执行删除
        try {
            intResult=dbManager.delete(TblUser.class,wb);
        } catch (Exception e) {
            intResult=-1;
            e.printStackTrace();
            System.out.println(e.getMessage());
            //未来把异常存放数据库
        }finally {
            return intResult;
        }
    }

    @Override
    public int userUpdateLocal(TblUser tblUser) {

        try {
            //old userName new userAge
           this.tblUser=dbManager
                    .selector(TblUser.class)
                    .expr("userName='"+tblUser.getUserName()+"'")
                    .findFirst();
           this.tblUser.setUserAge(tblUser.getUserAge());
            dbManager.saveOrUpdate(this.tblUser);
            intResult=1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            intResult=-1;
        }
        finally {
            return intResult;
        }
    }

    @Override
    public int userSelectOneLocal(TblUser user) {
        try {
            //old userName new userAge
            intResult=dbManager
                    .selector(TblUser.class)
                    .expr("userName='"+tblUser.getUserName()+"'")
                    .and("userAge","=",user.getUserAge())
                    .findAll().size();


        } catch (Exception e) {
            System.out.println(e.getMessage());
            intResult=-1;
        }
        finally {
            return intResult;
        }
    }

    @Override
    public List<TblUser> userSelectAllLocal() {
        try {
            return dbManager.findAll(TblUser.class);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        }
    }
}