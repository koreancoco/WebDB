<%--
  Created by IntelliJ IDEA.
  User: 90545
  Date: 2019/5/31
  Time: 22:03
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
    <script src="../js/jquery.min.js"></script>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>

<div class="page-content container clearfix">

    <div class="col-3 float-left pr-4">
        <nav class="menu" aria-label="Personal settings" data-pjax="">
            <h3 class="menu-heading">
                手动添加课程
            </h3>
            <br>
            <br>

            <div>
                <dl class="form-group">
                    <dt><label >课程名称</label></dt>
                    <dd><input id="cname" class="form-control" type="text" ></dd>
                </dl>
                <dl class="form-group">
                    <dt><label >人数限制</label></dt>
                    <dd><input id="maxnum" class="form-control" type="text" ></dd>
                </dl>


                <dl class="form-group">
                    <dt><label for="user_profile_bio">课程简介</label></dt>
                    <dd class="user-profile-bio-field-container js-length-limited-input-container">
                        <textarea id="introduction" class="form-control user-profile-bio-field js-length-limited-input" placeholder="描述一下这门课程吧"   ></textarea>

                    </dd>
                </dl>

                <select id="teacherid" class="form-group valid"  name="JoinAssociation"   required="" aria-required="true" aria-invalid="false">
                    <option value="-1">授课教师</option>
                    <c:forEach var="teacher" items="${sessionScope.teachers}">
                        <option value="${teacher.tid}">${teacher.tName}</option>
                    </c:forEach>
                </select>

                <button type="submit" id="onAdd" class="btn btn-success btn-default">添加课程</button>


            </div>


        </nav>



    </div>

    <div class="col-9 float-left">

        <!-- Public Profile -->
        <div class="Subhead mt-0 mb-0">
            <h2 id="public-profile-heading" class="Subhead-heading">Public profile</h2>
        </div>


<br>
<hr>
<br>
    <c:forEach varStatus="userStatus" var="course" items="${sessionScope.courses}">


            <form enctype="multipart/form-data"  style="float: left;border: darkgrey" action="../manageClassPage.do"  method="post"  accept-charset="UTF-8" class="edit" aria-labelledby="public-profile-heading" >

                <div>
                    <dl class="form-group">
                        <dt><label >课程名称</label></dt>
                        <dd><input value="${course.cname}" class="form-control" type="text" name="cname"></dd>
                    </dl>
                    <dl class="form-group">
                        <dt><label >课程号</label></dt>
                        <dd><input value="${course.cid}"  class="form-control" type="text" name="cid"></dd>
                    </dl>


                    <dl class="form-group">
                        <dt><label for="user_profile_bio">课程简介</label></dt>
                        <dd class="user-profile-bio-field-container js-length-limited-input-container">
                            <textarea name="introduction" class="form-control user-profile-bio-field js-length-limited-input" placeholder="${course.introduction}"   ></textarea>
                            <p class="note">
                                通过<strong>课程简介</strong>可以让他人更好地了解课程
                            </p>
                            <p class="js-length-limited-input-warning user-profile-bio-message d-none"></p>
                        </dd>
                    </dl>

                    <dl class="form-group">
                        <dt><label for="user_profile_blog">教师id</label></dt>
                        <dd><input value="${course.teacherid}" disabled class="form-control" type="text" name="teacherid" ></dd>
                    </dl>

                    <dl class="form-group">
                        <dt><label for="user_profile_company">人数限制</label></dt>
                        <dd class="user-profile-company-field-container">
                            <input value="${course.maxnum}" autocomplete="off"  type="text" name="maxnum" >
                            <p class="note">
                                降低<strong>人数限制</strong>让更多小伙伴加入到课程来吧
                            </p>
                        </dd>
                    </dl>

                    <dl class="form-group col-4">
                        <dt><label >上传图片</label></dt>
                        <dd><input type="file" name="stream"/></dd>
                    </dl>
                    <p>

                        <button type="submit" class="btn btn-success btn-default">更新课程</button>
                        <button onclick="onCancel(${course.cid})" type="button" class="mbtn btn btn-danger btn-default">删除课程</button>
                    </p>


                </div>
            </form>


    </c:forEach>

    </div>
<br>
<hr>
<br>

</div>
</body>


<script>



    function onCancel(cid) {
        $.ajax({
            type:'POST',
            url:'../manageClassPage.do',
            data:{
                cid:cid,
                status:'cancelCourse'
            },
            success:onSuccess(),

            dataType:"json",

        });
    }


    function onSuccess() {
        alert("成功")
        window.location.href="http://localhost:8080/WebDB/manage/manageClassPage.jsp";
    }

    $(document).ready(function(){


        $("#onAdd").click(function () {

            var maxnum = $("#maxnum").val();
            var cname = $("#cname").val();
            var teacherid = $("#teacherid").val();
            var introduction = $("#introduction").val();

            if(teacherid!=-1) {
                $.ajax({
                    type:'POST',
                    url:'../manageClassPage.do',
                    data:{
                        maxnum:maxnum,
                        cname:cname,
                        teacherid:teacherid,
                        introduction:introduction,
                        status:'addNewCourse'
                    },
                    success:onSuccess(),
                    dataType:"json",

                });
            } else {
                alert("要选择授课教师哦")
            }
        });
    });

</script>

<style type="text/css">

    .edit {
        margin-bottom: 50px;
        margin-right: 150px;
    }



    .page-content {
        padding-top: 20px;
    }

    .container {
        margin-left: auto;
        margin-right: auto;
        width: 100%;
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
        width: 300px;
    }

    .form-group .form-control.user-profile-bio-field {
        height: 5.35em;
        min-height: 0;
        width: 300px;
    }
</style>
</html>
