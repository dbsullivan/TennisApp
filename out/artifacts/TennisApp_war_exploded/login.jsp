<%--
  Created by IntelliJ IDEA.
  User: Dave
  Date: 9/26/2015
  Time: 9:10 AM
  This is the login.jsp to use Form Based Authentication
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

        <h2>Login Page</h2>
        <h4>Messages: ${loginMessage}</h4>

        <FORM ACTION="j_security_check" METHOD="POST">
            <table>
                <tr><td align="left">Please Enter your UserId and Password:</td></tr>
                <tr>
                    <td align="left">UserID: <input type="text" name="j_username" value="${userLoggedIn}" maxlength="15" size="15" style="background-color:<%= textAreaColor %> ;" />
                    </td>
                </tr>
                <tr>
                    <td align="left">Password: <input type="PASSWORD" name="j_password" maxlength="15" size="15" style="background-color:<%= textAreaColor %> ;" />
                    </td>
                </tr>
            </table>
            <br />
            <input type="submit" name="" value="Enter" />
        </form>
    </div>
</div>

<!-- end main -->

<!-- footer -->
<c:import url="template_footer.jsp" />
<!-- end footer -->

</body>
</html>
