<%--
  Created by IntelliJ IDEA.
  User: Dave
  Date: 12/12/2015
  Time: 4:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%-- this is the League Assignment Display/Remove/Add page --%>

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
    <h2>League Assignment Form</h2>
    <h4>League Assignment Messages: ${leagueAssignmentMessage}</h4>

    <form action="/league-assignment-action" method="POST">   <%-- this will determine Delete or Assign_League --%>

      <table border=1 color="WHITE">
        <tr>
          <th> Assignment </th>
          <th> Current Assignments </th>
        </tr>
        <tr>
          <td> Assignment Type </td>
          <td> ${playerStatusSearch.searchType} </td>
        </tr>
        <%-- <tr>
          <td> Search Term </td>
          <td> ${playerStatusSearch.searchTerm} </td>
        </tr> --%>
        <%-- need to choose here, and dropdown, to show leagues or players? --%>
        <c:forEach var="player" items="${playerStatusSearch.playersList}">
          <tr>
              <%-- <td> Player </td> --%>
            <td><a href="/league-assignment?leagueAssignID=${player.getLeagueAssignId()}" id="${player.getLeagueAssignId()}" > Remove Assignment  ${player.getLeagueAssignId()} </a></td>
              <%-- just show player ID,  names with row as a link, passing the selected ID, to PlayerMaint.java to populate playerMaint.jsp --%>
            <td> ${player.toString()}</td>
          </tr>
        </c:forEach>
      </table>
      <br />
      <%-- Drop down list of Available (players / leagues) depending on direction/type, with Add button adjacent to list box
      <input type="submit" name="deleteAssignmentBtn" value="Remove Assignment" />
      <input type="submit" name="addAssignmentBtn" value="Add Assignment" />
      --%>
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

