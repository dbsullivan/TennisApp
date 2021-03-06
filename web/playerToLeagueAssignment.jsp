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
          <td> ${leagueAssignStatusSearch.searchType} </td>
        </tr>
        <tr>
          <td> Search Term </td>
          <td> ${leagueAssignStatusSearch.searchTerm} </td>
        </tr>
        <c:forEach var="leagueassign" items="${leagueAssignStatusSearch.getLeagueAssignDisplay()}">
          <tr>
            <td><a href="/league-assign-delete-action?leagueAssignId=${leagueassign.getLeagueAssignId()}" id="${leagueassign.getLeagueAssignId()}" > Remove Assignment  ${leagueassign.getLeagueAssignId()} </a></td>
            <td> ${leagueassign.toString()}</td>
          </tr>
        </c:forEach>
      </table>
      <br />
      <%-- Drop down list of Available (players / leagues) depending on direction/type, with Add button adjacent to list box       --%>
      <input type="submit" name="addAssignmentBtn" value="Add Assignment" />
<%-- TODO start here, add dropdown list of leagues avail using for each, add button will apply it, SERVLETS TO ADD, and REMOVE League_Assignment --%>
      <td>
          <select name="newSelectedLeague" >
              <c:forEach var="leagueAvailable" items="${leagueAssignStatusSearch.getLeagueAvailDisplayList()}" >
                  <option value="${leagueAvailable.leagueId}" ${leagueAvailable.leagueId == selectedLeague ? 'selected="selected"' : ''}>${leagueAvailable.leagueName}</option>
              </c:forEach>
          </select>
      </td>
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

