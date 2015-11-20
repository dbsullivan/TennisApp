<%--
  Created by IntelliJ IDEA.
  User: Dave
  Date: 11/19/2015
  Time: 8:30 PM
  To change this template use File | Settings | File Templates.
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
    <h2>League Add Form</h2>
    <h4>Add Messages: ${leagueAddMessage}</h4>
    <form action="/league-add-action" method="POST">
      <table>
        <tr>
          <td align="left">League Name:</td>
          <td align="left"><%-- input type="text" name="leagueName" maxlength="25" size="30" value="${leagueName}" style="background-color:#ffffff;"/ --%>
            <c:choose>
              <c:when test="${leagueNameErr == true}" >
                <input type="text" name="leagueName" maxlength="50" size="50" value="${leagueName}" style="background-color:<%= textAreaHighlight %> ;" />
              </c:when>
              <c:otherwise>
                <input type="text" name="leagueName" maxlength="50" size="50" value="${leagueName}" style="background-color:<%= textAreaColor %> ;" />
              </c:otherwise>
            </c:choose>
          </td>
        </tr>
        <tr>
          <td align="left">League Number of Player Slots:</td>
          <td align="left"><%-- <input type="text" name="leaguePlayerSlots" maxlength="30" size="30" value="${leaguePlayerSlots}" /></td> --%>
            <c:choose>
              <c:when test="${leaguePlayerSlotsErr == true}" >
                <input type="text" name="leaguePlayerSlots" maxlength="2" size="2" value="${leaguePlayerSlots}" style="background-color:<%= textAreaHighlight %> ;" />
              </c:when>
              <c:otherwise>
                <input type="text" name="leaguePlayerSlots" maxlength="2" size="2" value="${leaguePlayerSlots}" style="background-color:<%= textAreaColor %> ;" />
              </c:otherwise>
            </c:choose>
          </td>
        </tr>
        <tr>
          <td align="left">League Number of Courts Needed:</td>
          <td align="left"><%-- <input type="text" name="courtsNeeded" maxlength="20" size="30" value="${courtsNeeded}" /></td> --%>
            <c:choose>
              <c:when test="${leagueCourtsNeededErr == true}" >
                <input type="text" name="leagueCourtsNeeded" maxlength="2" size="2" value="${leagueCourtsNeeded}" style="background-color:<%= textAreaHighlight %> ;" />
              </c:when>
              <c:otherwise>
                <input type="text" name="leagueCourtsNeeded" maxlength="2" size="2" value="${leagueCourtsNeeded}" style="background-color:<%= textAreaColor %> ;" />
              </c:otherwise>
            </c:choose>
          </td>
        </tr>
        <tr>
          <td align="left">League Number of Events:</td>
          <td align="left"><%-- <input type="text" name="gender" maxlength="10" size="30" value="${gender}" /></td> --%>
            <c:choose>
              <c:when test="${leagueEventsErr == true}" >
                <input type="text" name="leagueEvents" maxlength="2" size="2" value="${leagueEvents}" style="background-color:<%= textAreaHighlight %> ;" />
              </c:when>
              <c:otherwise>
                <input type="text" name="leagueEvents" maxlength="2" size="2" value="${leagueEvents}" style="background-color:<%= textAreaColor %> ;" />
              </c:otherwise>
            </c:choose>
          </td>
        </tr>
        <tr>
            //TODO START HERE WITH LEAGUE_TYPE_SNGL_DBL
          <td align="left">League s NTRP Level:</td>
          <td align="left"><%-- <input type="text" name="ntrpLevel" maxlength="5" size="5" value="${ntrpLevel}" /></td> --%>
            <c:choose>
              <c:when test="${leaguentrpLevelErr == true}" >
                <input type="text" name="ntrpLevel" maxlength="5" size="5" value="${ntrpLevel}" style="background-color:<%= textAreaHighlight %> ;" />
              </c:when>
              <c:otherwise>
                <input type="text" name="ntrpLevel" maxlength="5" size="5" value="${ntrpLevel}" style="background-color:<%= textAreaColor %> ;" />
              </c:otherwise>
            </c:choose>
          </td>
        </tr>
        <tr>
          <td align="left">League Phone Number:</td>
          <td align="left"><%-- <input type="text" name="phoneNumber" maxlength="15" size="15" value="${phoneNumber}" /></td> --%>
            <c:choose>
              <c:when test="${leaguephoneNumberErr == true}" >
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
    
