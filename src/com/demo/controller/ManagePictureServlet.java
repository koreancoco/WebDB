package com.demo.controller;


import com.demo.dao.PictureDao;
import com.demo.model.Picture;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.ByteBuffer;
import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Enzo Cotter on 2019/6/9.
 */
@WebServlet("/managePicture.do")
@MultipartConfig
public class ManagePictureServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Picture pic=new Picture();
        int num=Integer.parseInt(request.getParameter("number"));
        PictureDao dao=new PictureDao();
        pic=dao.readPicture(num);
        request.getSession().setAttribute("picture", pic);


        InputStream is =pic.getStream();
        int i = is.available(); // 得到文件大小
        byte data[] = new byte[i];
        is.read(data); // 读数据
        is.close();
        response.setContentType("image/*"); // 设置返回的文件类型
        OutputStream outs = response.getOutputStream(); // 得到向客户端输出二进制数据的对象
        outs.write(data); // 输出数据
        outs.flush();
        //outs.close();
        //byte bs[] = pic.getStream();
        //response.sendRedirect("/diaplaytest.jsp");


    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Picture pic = new Picture();

        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            request.setCharacterEncoding("UTF-8");
            //String title = request.getParameter("title");
            String path = this.getServletContext().getRealPath("file");
            //String name = (String) session.getAttribute("name");
            //String number = (String) session.getAttribute("number");
            Date t = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String data = df.format(t);
            //System.out.println("获取参数完成 " + name + " " + number + " " + title);
            Part p = request.getPart("stream");
            System.out.println("获取PART完成");
            //path = path + "\\" + name;
            File f = new File(path);
            if (!f.exists()) {
                f.mkdir();
            }
            String h = p.getHeader("content-disposition");
            System.out.println(h);
            String[] tempArr1 = h.split(";");
            String[] tempArr2 = tempArr1[2].split("=");
            String fileName = tempArr2[1].substring(tempArr2[1].lastIndexOf("\\") + 1).replaceAll("\"", "");
            System.out.println("获取文件成功" + fileName);
            p.write(path + File.separator + fileName);
            System.out.println(path + File.separator + fileName);
            System.out.println("写入成功" + path + File.separator + fileName);
            pic.setId(Integer.parseInt(request.getParameter("id")));
            pic.setName(request.getParameter("name"));
            InputStream is=request.getInputStream();

            //Part part = request.getPart("stream");
            //fileName = request.getParameter("stream");
            //part.write("/images" + File.separator + fileName);
            System.out.println("写入成功" + "/images/" + fileName);

            File file = new File(path + File.separator + fileName); //获取表单传过来的图片的url
            FileInputStream fis = new FileInputStream(file);
            pic.setStream(fis);
            PictureDao dao = new PictureDao();
            dao.addPicture(pic);
            //request.getSession().setAttribute("picture", pic);
        } catch (Exception e) {

            e.printStackTrace();
        }



    }


}



