package com.demo.dao;


import com.demo.model.Picture;
import sun.nio.cs.ext.PCK;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Enzo Cotter on 2019/6/8.
 */
public class AssPictureDao extends BaseDao{


    public boolean isPictureExist(Picture picture) {
        String sql="select id from asspicture where id=?";
        try {

            Connection conn=dataSource.getConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1, picture.getId());
            pstmt.executeQuery();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // 向picture表中添加记录
    public boolean addAssPicture(Picture picture) {
        if(isPictureExist(picture)) {
            String sql="update asspicture set stream=? where id=?";

            try {

                Connection conn=dataSource.getConnection();
                PreparedStatement pstmt=conn.prepareStatement(sql);
                pstmt.setBinaryStream(1, picture.getStream(),picture.getStream().available());
                pstmt.setInt(2, picture.getId());
                pstmt.executeUpdate();
                conn.close();

            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else {
            String sql="insert into asspicture (id,pname,stream) values(?,?,?)";

            try {
                Connection conn=dataSource.getConnection();
                PreparedStatement pstmt=conn.prepareStatement(sql);
                pstmt.setInt(1, picture.getId());
                pstmt.setString(2, picture.getName());
                pstmt.setBinaryStream(3, picture.getStream(),picture.getStream().available());

                pstmt.executeUpdate();
                conn.close();

            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }



        return true;
    }
    // 读取数据库中图片
    public  Picture readAssPicture(int pid) {
        //String targetPath = "D:/image/1.png";
        Connection conn;
        Picture pic=new Picture();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from asspicture where id =?";
        try {
            conn=dataSource.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, pid);
            rs = ps.executeQuery();
            while (rs.next()) {
                //Utils.readImage(in, targetPath);
                pic.setId(rs.getInt("id"));
                pic.setName(rs.getString("pname"));
                pic.setStream(rs.getBinaryStream("stream"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return pic;
    }

}
