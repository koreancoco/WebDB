<%--
  Created by IntelliJ IDEA.
  User: 90545
  Date: 2019/6/2
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <meta content="width=device-width, initial-scale=1" name="viewport">
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="../js/jquery.min.js"></script>
</head>
<body>
<div class="page-content container clearfix">




    <div class="col-3 float-left pr-4">
        <nav class="menu" aria-label="Personal settings" data-pjax="">
            <h3 class="menu-heading">
                 社团封面设置
            </h3>
            <form action = "../manageAssPicture.do" enctype="multipart/form-data" method = "post">
                <div>
                    <dl class="form-group col-4">
                        <dt><label >上传图片</label></dt>
                        <dd><input type="file" name="stream"/></dd>
                    </dl>

                    <dl class="form-group">
                        <input class="btn btn-success btn-default" type="submit" value="确定" >
                        <input class="btn btn-danger btn-default" type="reset" value="重置" >

                    </dl>
                </div>
            </form>

        </nav>

        <nav class="menu" aria-label="Developer settings">
            <a class="menu-item" href="/settings/apps">
                Developer settings
            </a>  </nav>


    </div>

    <div class="col-9 float-left">

        <!-- Public Profile -->
        <div class="Subhead mt-0 mb-0">
            <h2 id="public-profile-heading" class="Subhead-heading">社团信息管理</h2>
        </div>
        <div class="clearfix gutter d-flex flex-shrink-0">
            <div class="col-8">
                <form action="../showMemberinfo.do"   method="post"  accept-charset="UTF-8" class="edit_user" id="edit_user_39484031" aria-labelledby="public-profile-heading" >

                    <div>

                        <dl class="form-group">
                            <dt><label >社团名称</label></dt>
                            <dd><input value="${sessionScope.association.assname}" class="form-control" type="text" name="assname"></dd>
                        </dl>

                        <dl class="form-group">
                            <dt><label for="user_profile_bio">社团简介</label></dt>
                            <dd class="user-profile-bio-field-container js-length-limited-input-container">
                                <textarea name="introduction" class="form-control user-profile-bio-field js-length-limited-input" placeholder="${sessionScope.association.introduction}"   ></textarea>
                                <p class="note">
                                    通过<strong>简介</strong>可以让同学们更好地了解社团
                                </p>
                                <p class="js-length-limited-input-warning user-profile-bio-message d-none"></p>
                            </dd>
                        </dl>


                        <dl class="form-group">
                            <dt><label for="user_profile_company">社团课程总数</label></dt>
                            <dd class="user-profile-company-field-container">
                                <p class="note">
                                     <strong>${sessionScope.courseCount}</strong>
                                </p>
                            </dd>
                        </dl>
                        <hr>
                        <dl class="form-group">
                            <dt><label for="user_profile_location">社团人数</label></dt>
                            <dd class="user-profile-company-field-container">
                                <p class="note">
                                    <strong>${sessionScope.memberCount}</strong>
                                </p>
                            </dd>
                        </dl>
                        <hr>


                        <p><button type="submit" class="btn btn-success btn-default">更新简介</button></p>
                    </div>
                </form>
            </div>



        </div>

        <style type="text/css">

            .menu-item.selected {
                background-color: #fff;
                color: #24292e;
                cursor: default;
                font-weight: 600;
            }

            .menu-item {
                border-bottom: 1px solid #e1e4e8;
                display: block;
                padding: 8px 10px;
                position: relative;
            }

            .page-content {
                padding-top: 20px;
            }

            .container {
                margin-left: auto;
                margin-right: auto;
                width: 980px;
            }
            .col-9 {
                width: 75%;
            }
            .pr-4 {
                padding-right: 24px!important;
            }

            .float-left {
                float: left!important;
            }

            .col-3 {
                width: 25%;
            }

            .menu {
                background-color: #fff;
                border: 1px solid #d1d5da;
                border-radius: 3px;
                list-style: none;
                margin-bottom: 15px;
            }

            .menu-heading:first-child {
                border-top-left-radius: 2px;
                border-top-right-radius: 2px;
            }

            .note {
                color: #586069;
                font-size: 12px;
                margin: 4px 0 2px;
                min-height: 17px;
            }

            a{
                text-decoration: none;
            }
            form {
                display: block;
                margin-top: 0em;
            }
            .form-group {
                margin: 15px 0;
            }
            .form-control {
                background-color: #fafbfc;
                margin-right: 5px;
                max-width: 100%;
                width: 440px;
            }

            .form-group .form-control.user-profile-bio-field {
                height: 5.35em;
                min-height: 0;
                width: 440px;
            }
        </style>

        <script>

        </script>
</body>
</html>

