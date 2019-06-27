package com.demo.controller;

import com.demo.model.Association;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
  *ClassNmae:  ManageAssInfoServlet
  *Description:  TODO
  *@author  MasonWu
  *@date  2019/6/27 17:24
  *@version  1.0
  *Copyright (c) 2018-2020 Koreancoco All Rights Reserved.
  **/

@WebServlet("/manageAssInfo.do")
public class ManageAssInfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Association aid=(Association) req.getSession().getAttribute("association");
        resp.sendRedirect("manage/manageAssInfoPage.jsp");

    }
}
