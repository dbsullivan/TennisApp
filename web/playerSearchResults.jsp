<%--
  Created by IntelliJ IDEA.
  User: Dave Sullivan
  Date: 11/8/2015
  Time: 7:41 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<%-- this is Player results display page --%>

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
        <h2>Player Search Results</h2>
        <h4>Search Messages: ${playerSearchMessage}</h4>
        <table border=1 color="WHITE">
            <tr>
                <th> Player </th>
                <th> Value </th>
            <tr> </tr>
            <tr>
                <td> Search Type </td>
                <td> ${playerStatusSearch.searchType} </td>
            </tr>
            <tr>
                <td> Search Term </td>
                <td> ${playerStatusSearch.searchTerm} </td>
            </tr>
            <c:forEach var="player" items="${playerStatusSearch.playersList}">
                <tr>
                    <!-- <td> Player </td> -->
                    <td><a href="/player-maintenance?playerID=${player.getPlayerId()}" id="${player.getPlayerId()}" > Select Player  ${player.getPlayerId()} </a></td>
                    <%-- just show player ID,  names with row as a link, passing the selected ID, to PlayerMaint.java to populate playerMaint.jsp --%>
                    <td> ${player.toString()}</td>
                    <%-- <c:forEach nest the list of LeagueAssigns after each player, shorten the toString to player.getNameFirst,last,id BUILD PlayerLeagues.java like PlayerSearch.java --%>
                    <%-- put this in the individual playerMaintenance.jsp instead - it is Per player! --%>
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

