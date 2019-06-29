package com.demo.controller;
import com.demo.dao.CoursePictureDao;
import com.demo.model.Picture;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;

/**
 * Created by Enzo Cotter on 2019/6/9.
 */
@WebServlet("/manageCoursePicture.do")
@MultipartConfig
public class ManageCoursePictureServlet extends HttpServlet {

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                Picture pic=new Picture();
                int num=Integer.parseInt(request.getParameter("number"));
                CoursePictureDao dao=new CoursePictureDao();
                pic=dao.readCoursePicture(num);
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

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                Picture pic = new Picture();

                try {
                response.setCharacterEncoding("utf-8");
                response.setContentType("text/html;charset=utf-8");
                request.setCharacterEncoding("UTF-8");

                String path = this.getServletContext().getRealPath("file");
                Part p = request.getPart("stream");
                System.out.println("获取PART完成");
                //System.out.println(h);
                String fileName = request.getParameter("stream");
                System.out.println("获取文件成功" + fileName);
                pic.setId(Integer.parseInt(request.getParameter("cid")));
                File file = new File(path + File.separator + fileName); //获取表单传过来的图片的url
                FileInputStream fis = new FileInputStream(file);
                pic.setStream(fis);
                CoursePictureDao dao = new CoursePictureDao();
                dao.addCoursePicture(pic);
                System.out.println("写入成功" + "/images/" + fileName);
                //request.getSession().setAttribute("picture", pic);
                } catch (Exception e) {

                e.printStackTrace();
                }



        }


        }



