package com.demo.controller;

import com.demo.model.User;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
  *ClassNmae:  AjaxTest
  *Description:  TODO
  *@author  MasonWu
  *@date  2019/6/29 19:12
  *@version  1.0
  *Copyright (c) 2018-2020 Koreancoco All Rights Reserved.
  **/
@WebServlet("/AjaxTest")
public class AjaxTest extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxTest() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        String name = request.getParameter("username");
        String sex = request.getParameter("sex");
        String age = request.getParameter("age");
        String phone = request.getParameter("phone");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");


        User one = new User(name, sex, age, phone);
        String result = one.toString();
        String[] re={"22","dsf","dsaf"};
        System.out.println(result);
        HashMap<String,Integer> map=new HashMap<>();
        map.put("31", 2);
        map.put("33", 3);
        map.put("23", 3);
        map.put("nihao",44);
        Gson jso=new Gson();
        String s=jso.toJson(map);
        response.getWriter().append(s);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}