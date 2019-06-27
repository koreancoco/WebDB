<%--
  Created by IntelliJ IDEA.
  User: 王钧涛
  Date: 2019/6/9
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action = "managePicture.do" method = "post">
    <table>
        <tr><td>客户号：</td> <td><input type="text" name="pid" ></td></tr>
        <tr><td>客户名：</td> <td><input type="text" name="name" ></td></tr>
        <tr><td>客户名：</td> <td><input type="file" name="stream"/></td></tr>
        <tr><td><input type="submit" value="确定" ></td>
            <td><input type="reset" value="重置" ></td>
        </tr>
    </table>
    </form>
</body>
</html>
