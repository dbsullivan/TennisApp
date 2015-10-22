<%--
  Created by IntelliJ IDEA.
  User: Dave
  Date: 10/19/2015
  Time: 3:41 PM
  This is the login-error.jsp to use Form Based Authentication if the login fails.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<!-- head -->
<c:import url="template_head_tag.jsp" />
<%-- <c:set var="textAreaColor" value="#ffffff" scope="session" />  --%>

<%!
  private String textAreaWhite = "#ffffff";
  private String textAreaHighlight = "#fffd48";
  private String textAreaColor = "";
%>

<body>

<!-- header -->
<c:import url="template_header.jsp" />
<!--end header -->

<!-- main -->
<div id="main">

  <!--begin sidebar -->
  <div id="sidebar">
    <!--      <a href="/java112"><img src="images/clover.jpg" alt="Dave Sullivan Java112 Home" align="left" width=200 height=150 /></a>
      -->

  </div>

  <div id="text"  >

    <h2>Login Error Page</h2>
    <h4>Add Messages: ${project4AddMessage}</h4>

      Invalid name or password entered.
      <ul>
        <li><a href="login.jsp">Take me to the Login page</a></li>
        <li><a href="index.jsp">Take me to Add-In Home page</a></li>
      </ul>

      <br />

  </div>
</div>
<!-- end main -->

<!-- footer -->
<c:import url="template_footer.jsp" />
<!-- end footer -->

</body>
</html>

