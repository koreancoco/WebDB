package com.demo.controller;


import com.demo.dao.PictureDao;
import com.demo.model.Picture;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Enzo Cotter on 2019/6/9.
 */
@WebServlet("/managePicture.do")
public class ManagePictureServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Picture pic=new Picture();
        int num=(int)request.getSession().getAttribute("number");
        PictureDao dao=new PictureDao();
        pic=dao.readPicture(num);
        request.getSession().setAttribute("picture", pic);
        response.sendRedirect("manage/managePicturePage.jsp");
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Picture pic=new Picture();
        try{
            pic.setId((int)request.getSession().getAttribute("pid"));
            pic.setName((String)request.getSession().getAttribute("name"));
            File file=(File)request.getSession().getAttribute("stream");
            FileInputStream fos=new FileInputStream(file);
            pic.setStream(fos);
            PictureDao dao=new PictureDao();
            dao.addPicture(pic);
            //request.getSession().setAttribute("picture", pic);
        }catch(Exception e){

            e.printStackTrace();
        }

        //response.sendRedirect("manage/managePicturePage.jsp");

    }
}



