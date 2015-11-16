<!-- main -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="mycustomtags" uri="/WEB-INF/tlds/custom.tld" %>

<jsp:useBean id="User" class="com.TennisApp.java.entity.User" scope="session" />
<jsp:getProperty name="User" property="name" />


<div id="main">
    <!--begin sidebar -->
      <div id="sidebar">
      </div>

      <div id="text" >
            <h1><strong>Tennis League</strong></h1>

          <%-- use custom tag for this message? --%>
            <p>
                <%-- <mycustomtags:AM_PM_Greeting username="Dave" >Welcome to the Add-in Application.</mycustomtags:AM_PM_Greeting> --%>
                <%--  <mycustomtags:AM_PM_Greeting username="${username}" >Welcome to the Add-in Application.</mycustomtags:AM_PM_Greeting> --%>
                <mycustomtags:AM_PM_Greeting username="<%= User.getName() %>" >Welcome to the Add-in Application.</mycustomtags:AM_PM_Greeting>

            </p>
            <%-- <p> Welcome ${parameterUserNameValue} to the Add-in Application.</p> --%>

            <p><h3><strong>Manage Players</strong></h3></p>
            <ul>
               <li><a href="/player-search">Player Search</a></li> <%-- links to Servlets, use urlPatterns for Servlet controller forward to jsp --%>
               <li><a href="/player-add">Player Add</a></li>
            </ul>

            <p><h3><strong>Manage Leagues</strong></h3></p>
            <ul>
                <li><a href="/league-search">League Search</a></li>
                <%-- <li><a href="/java112/request-servlet">League Add</a></li> --%>
             </ul>


             <ul>
               <li><a href="index.jsp">Take me to the Add-In Home page</a></li>  <%-- link to jsp, above link to Servlets --%>
            </ul>

      </div>
</div>
<!-- end main -->
