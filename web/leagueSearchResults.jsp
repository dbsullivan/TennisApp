<%--
  Created by IntelliJ IDEA.
  User: Dave Sullivan
  Date: 11/8/2015
  Time: 7:41 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<%-- this is League results display page --%>

<html xmlns="http://www.w3.org/1999/xhtml">
<!-- head -->
<c:import url="template_head_tag.jsp" />
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
    <h2>League Search Results</h2>
    <h4>Search Messages: ${leagueSearchMessage}</h4>
    <table border=1 color="WHITE">
      <tr>
        <th> League </th>
        <th> Value </th>
      <tr> </tr>
      <tr>
        <td> Search Type </td>
        <td> ${leagueStatusSearch.searchType} </td>
      </tr>
      <tr>
        <td> Search Term </td>
        <td> ${leagueStatusSearch.searchTerm} </td>
      </tr>
      <c:forEach var="league" items="${leagueStatusSearch.leaguesList}">
        <tr>
          <td> League </td>
          <td> ${league.toString()}</td>
        </tr>
      </c:forEach>
    </table>
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

