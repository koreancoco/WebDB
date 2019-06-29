package com.demo.controller;

import com.demo.dao.CourseDao;
import com.demo.dao.CoursePictureDao;
import com.demo.dao.TeacherDao;
import com.demo.model.Association;
import com.demo.model.Course;
import com.demo.model.Picture;
import com.demo.model.Teacher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
  *ClassNmae:  ManageCoursePageServlet
  *Description:  管理课程
  *@author  MasonWu
  *@date  2019/6/1 16:21
  *@version  1.0
  *Copyright (c) 2018-2020 Koreancoco All Rights Reserved.
  **/
@WebServlet("/manageClassPage.do")
public class ManageCoursePageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取课程信息
        List<Course> courses;
        CourseDao dao=new CourseDao();
        Association association=(Association) req.getSession().getAttribute("association");
        System.out.println(association.getCaptain());
        System.out.println(association.getId());
        courses=dao.findAllCourseByAssID(association.getId());

        // 查找所有教师
        TeacherDao tdao=new TeacherDao();
        List<Teacher> teachers=tdao.findAllTeachers();
        req.getSession().setAttribute("teachers", teachers);

        System.out.println(req.getParameter("teacherid"));
        req.getSession().setAttribute("courses", courses);
        resp.sendRedirect("manage/manageClassPage.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        CourseDao dao=new CourseDao();
        String status=req.getParameter("status");


        if("cancelCourse".equals(status)) {

            // todo 删除课程 涉及级联删除
            dao.deleteCourse(Integer.parseInt(req.getParameter("cid")));
            System.out.println("cancel");
        } else if("addNewCourse".equals(status)) {

            Association association=(Association) req.getSession().getAttribute("association");
            System.out.println(req.getParameter("cname"));
            Course course=new Course(0,
                    req.getParameter("cname"),
                    Integer.parseInt( req.getParameter("maxnum")),
                    Integer.parseInt(req.getParameter("teacherid")),
                    association.getId(),
                    req.getParameter("introduction"));

            // TODO 将相关课程加入数据库
            if(dao.addCourse(course)) {
                System.out.println("addcourse");
            }

        } else { // 更新课程

            Association association=(Association) req.getSession().getAttribute("association");
            System.out.println(req.getParameter("cname"));

            Course course=new Course(122,
                    req.getParameter("cname"),
                    Integer.parseInt( req.getParameter("maxnum")),
                    0,
                    association.getId(),
                    req.getParameter("introduction"));

            Picture pic = new Picture();

            try {
                req.setCharacterEncoding("utf-8");
                resp.setContentType("text/html;charset=utf-8");
                req.setCharacterEncoding("UTF-8");

                String path = this.getServletContext().getRealPath("file");
                Part p = req.getPart("stream");
                System.out.println("获取PART完成");
                //System.out.println(h);
                String fileName = req.getParameter("stream");
                System.out.println("获取文件成功" + fileName);
                pic.setId(Integer.parseInt(req.getParameter("cid")));
                File file = new File(path + File.separator + fileName); //获取表单传过来的图片的url
                FileInputStream fis = new FileInputStream(file);
                pic.setStream(fis);
                CoursePictureDao cdao = new CoursePictureDao();
                cdao.addCoursePicture(pic);
                System.out.println("写入成功" + "/images/" + fileName);
                //request.getSession().setAttribute("picture", pic);
            } catch (Exception e) {

                e.printStackTrace();
            }

            // TODO 将相关课程加入数据库
            if(dao.updateCourse(course)) {
                System.out.println("updateCourse");
            }

        }

        List<Course> courses;
        CourseDao ddao=new CourseDao();
        Association association=(Association) req.getSession().getAttribute("association");
        System.out.println(association.getCaptain());
        System.out.println(association.getId());
        courses=ddao.findAllCourseByAssID(association.getId());

        req.getSession().setAttribute("courses", courses);
        resp.sendRedirect("manage/manageClassPage.jsp");
    }
}
