package com.gupao.homework.factory.pool;

import java.io.IOException;
import java.io.InputStream;
import java.rmi.server.ExportException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

/**
 * @ClassName DBConnectionPool
 * @Description TODO
 * @Author Administrator
 * @Date 2019/11/16 14:05
 **/
public class DBConnectionPool extends Pool {
    //正在使用的连接数
    private int checkedOut;

    //存放产生的连接对象容器
    private Vector<Connection> freeConnections = new Vector<>();

    //密码
    private String passWord = null;

    //连接字符串
    private String url = null;

    //用户名
    private String userName = null;

    //空闲连接数
    private static int num = 0;

    //当前可用的连接数
    private static int numActivie = 0;

    //连接池实例变量
    private static DBConnectionPool pool = null;

    //产生数据连接池
    public static synchronized DBConnectionPool getInstance(){
        if(pool==null){
            pool = new DBConnectionPool();
        }
        return pool;
    }

    private DBConnectionPool(){
        try {
            init();
            for (int i = 0; i < normalConnect; i++) {
                Connection c = newConnection();
                if (c != null) {
                    freeConnections.addElement(c);
                    num++;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //初始化
    private void init() throws IOException {
        InputStream is = DBConnectionPool.class.getResourceAsStream(propertiesName);
        Properties p = new Properties();
        p.load(is);
        this.userName = p.getProperty("userName");
        this.passWord = p.getProperty("passWord");
        this.driverName = p.getProperty("driverName");
        this.url = p.getProperty("url");
        this.driverName = p.getProperty("dirverName");
        this.maxConnect = Integer.parseInt(p.getProperty("maxConnect"));
        this.normalConnect = Integer.parseInt(p.getProperty("normalConnect"));
    }

    //创建一个新连接
    private Connection newConnection(){
        Connection con = null;
        try {
            if (userName == null) {
                con = DriverManager.getConnection(url);
            }else{
                con = DriverManager.getConnection(url,userName,passWord);
            }
            System.out.println("连接池创建一个新的连接");
        }catch (Exception e){
            System.out.println("无法创建这个URL的连接"+url);
        }
        return con;
    }


    @Override
    public void createPool() {
        pool = new DBConnectionPool();
        if(pool!=null){
            System.out.println("创建连接池成功");
        }else{
            System.out.println("创建连接池失败");
        }
    }

    @Override
    public synchronized Connection getConnection() {
        Connection connection = null;
        if(freeConnections.size()>0){
            //有空闲连接
            num--;
            connection = freeConnections.firstElement();
            freeConnections.removeElementAt(0);
            try {
                if(connection.isClosed()){
                    System.out.println("从连接池中删除一个无效连接");
                    connection = getConnection();
                }
            } catch (SQLException e) {
                System.out.println("从连接池中删除一个无效连接");
                connection = getConnection();
            }

        }else if(maxConnect==0 || checkedOut<maxConnect){
            //没有空闲连接，或当前连接小于最大允许值，最大值为0则不限制
            connection = newConnection();
        }
        if(connection != null){
            checkedOut++;
        }
        numActivie++;
        return connection;
    }

    @Override
    public synchronized Connection getConnection(long time) {
        long startTime = System.currentTimeMillis();
        Connection connection ;
        while ((connection = newConnection())==null){
            try{
                wait(time);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if((System.currentTimeMillis()-startTime)>=time){
            return null;
        }
        return connection;
    }

    @Override
    public void freeConnection(Connection connection) {
        freeConnections.addElement(connection);
        num++;
        checkedOut--;
        numActivie--;
        notifyAll();
    }

    //返回当前空闲连接数
    @Override
    public int getNum() {
        return num;
    }

    //返回当前连接数
    @Override
    public int getNumActive() {
        return numActivie;
    }

    //关闭所有连接
    public synchronized void releaseConections(){
        try {
            Enumeration<Connection> allConnections = freeConnections.elements();
            while (allConnections.hasMoreElements()) {
                Connection connection = allConnections.nextElement();
                try {
                    connection.close();
                    num--;
                } catch (Exception e) {
                    System.out.println("无法关闭连接池中的连接");
                }
            }
            freeConnections.removeAllElements();
            numActivie = 0;
        }finally {
            super.release();
        }
    }

}
