package com.demo.dao;


import com.demo.model.Picture;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Enzo Cotter on 2019/6/8.
 */
public class CoursePictureDao extends BaseDao{

    // 向picture表中添加记录
    public boolean addCoursePicture(Picture picture) {
        String sql="insert into coursepicture (cid,pname,stream) values(?,?,?)";
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


        return true;
    }
    // 读取数据库中图片
    public  Picture readCoursePicture(int pid) {
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
