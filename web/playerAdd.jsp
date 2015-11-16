<%--
  Created by IntelliJ IDEA.
  User: Dave
  Date: 11/16/2015
  Time: 2:22 PM
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
  </div>

  <div id="text"  >

    <h2>Player Add Form</h2>
    <h4>Add Messages: ${playerAddMessage}</h4>
    <form action="/player-add-action" method="POST">
      <table>
        <tr>
          <td align="left">Player First Name:</td>
          <td align="left"><%-- input type="text" name="firstName" maxlength="25" size="30" value="${firstName}" style="background-color:#ffffff;"/ --%>
            <c:choose>
              <c:when test="${playerfirstNameErr == true}" >
                <input type="text" name="firstName" maxlength="20" size="20" value="${firstName}" style="background-color:<%= textAreaHighlight %> ;" />
              </c:when>
              <c:otherwise>
                <input type="text" name="firstName" maxlength="20" size="20" value="${firstName}" style="background-color:<%= textAreaColor %> ;" />
              </c:otherwise>
            </c:choose>
          </td>
        </tr>
        <tr>
          <td align="left">Player Last Name:</td>
          <td align="left"><%-- <input type="text" name="lastName" maxlength="30" size="30" value="${lastName}" /></td> --%>
            <c:choose>
              <c:when test="${playerlastNameErr == true}" >
                <input type="text" name="lastName" maxlength="20" size="20" value="${lastName}" style="background-color:<%= textAreaHighlight %> ;" />
              </c:when>
              <c:otherwise>
                <input type="text" name="lastName" maxlength="20" size="20" value="${lastName}" style="background-color:<%= textAreaColor %> ;" />
              </c:otherwise>
            </c:choose>
          </td>
        </tr>
        <tr>
          <td align="left">Player email:</td>
          <td align="left"><%-- <input type="text" name="ssn" maxlength="20" size="30" value="${ssn}" /></td> --%>
            <c:choose>
              <c:when test="${playerEmailErr == true}" >
                <input type="text" name="email" maxlength="50" size="50" value="${email}" style="background-color:<%= textAreaHighlight %> ;" />
              </c:when>
              <c:otherwise>
                <input type="text" name="email" maxlength="50" size="50" value="${email}" style="background-color:<%= textAreaColor %> ;" />
              </c:otherwise>
            </c:choose>
          </td>
        </tr>
        <tr>
          <td align="left">Player Gender:</td>
          <td align="left"><%-- <input type="text" name="gender" maxlength="10" size="30" value="${gender}" /></td> --%>
            <c:choose>
              <c:when test="${playergenderErr == true}" >
                <input type="text" name="gender" maxlength="1" size="1" value="${gender}" style="background-color:<%= textAreaHighlight %> ;" />
              </c:when>
              <c:otherwise>
                <input type="text" name="gender" maxlength="1" size="1" value="${gender}" style="background-color:<%= textAreaColor %> ;" />
              </c:otherwise>
            </c:choose>
          </td>
        </tr>
        <tr>
          <td align="left">Player NTRP Level:</td>
          <td align="left"><%-- <input type="text" name="ntrpLevel" maxlength="5" size="5" value="${ntrpLevel}" /></td> --%>
            <c:choose>
              <c:when test="${playerntrpLevelErr == true}" >
                <input type="text" name="ntrpLevel" maxlength="5" size="5" value="${ntrpLevel}" style="background-color:<%= textAreaHighlight %> ;" />
              </c:when>
              <c:otherwise>
                <input type="text" name="ntrpLevel" maxlength="5" size="5" value="${ntrpLevel}" style="background-color:<%= textAreaColor %> ;" />
              </c:otherwise>
            </c:choose>
          </td>
        </tr>
        <tr>
          <td align="left">Player Phone Number:</td>
          <td align="left"><%-- <input type="text" name="phoneNumber" maxlength="15" size="15" value="${phoneNumber}" /></td> --%>
            <c:choose>
              <c:when test="${playerphoneNumberErr == true}" >
                <input type="text" name="phoneNumber" maxlength="15" size="15" value="${phoneNumber}" style="background-color:<%= textAreaHighlight %> ;" />
              </c:when>
              <c:otherwise>
                <input type="text" name="phoneNumber" maxlength="15" size="15" value="${phoneNumber}" style="background-color:<%= textAreaColor %> ;" />
              </c:otherwise>
            </c:choose>
          </td>
        </tr>
      </table>
      <br />
      <input type="submit" name="" value="Enter" />
    </form>

    </br>
    <a href="index.jsp">Take me to the Add-In Home page</a>
  </div>
</div>
<!-- end main -->

<!-- footer -->
<c:import url="template_footer.jsp" />
<!-- end footer -->

</body>
</html>






