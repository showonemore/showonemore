package com.jiuye.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;
public class BaseDao<T> {
    ComboPooledDataSource dataSource= new ComboPooledDataSource();
    QueryRunner qr=new QueryRunner(dataSource);
    //查找单个信息
    public T getEntity(String sql,Class aClass,Object ...obj){
        try {
            return qr.query(sql, new BeanHandler<T>(aClass),obj);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    //查找多个信息
    public List<T> getListEntity(String sql,Class aClass,Object ...obj){
        try {
            return qr.query(sql, new BeanListHandler<T>(aClass), obj);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    //更新信息
    public int update(String sql,Object ...obj){
        try {
            return qr.update(sql, obj);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
