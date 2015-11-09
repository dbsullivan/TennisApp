<%--
  Created by IntelliJ IDEA.
  User: Dave
  Date: 11/7/2015
  Time: 7:38 PM
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

            <h2>Player Search Page</h2>
            <h4>Search Messages: ${playerSearchMessage}</h4>
            <form action="/player-search-results" method="GET">  <%-- action to player-search-result --%>
              Search Term: <input type="text" name="searchTerm"/><br />
              <br />
              Search Type: <br />
              <input type="radio" name="searchType" value="id" />Player ID<br />
              <input type="radio" name="searchType" value="name" />Player Last Name<br />
              <br />
              <input type="submit" value="Enter" />
            </form>

            <br />
            <a href="index.jsp">Take me to the Add-In Home page</a>  <%-- link to jsp, above link to Servlets --%>

      </div>
</div>
<!-- end main -->

<!-- footer -->
<c:import url="template_footer.jsp" />
<!-- end footer -->

</body>
</html>



