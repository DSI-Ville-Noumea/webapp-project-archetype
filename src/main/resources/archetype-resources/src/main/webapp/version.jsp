#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
<%@page contentType="text/plain"%>
<%@page import="java.net.InetAddress"%>
${appName}.version=${symbol_dollar}{version}
${appName}.hostaddress=<%=InetAddress.getLocalHost().getHostAddress() %>
${appName}.canonicalhostname=<%=InetAddress.getLocalHost().getCanonicalHostName() %>
${appName}.hostname=<%=InetAddress.getLocalHost().getHostName() %>
${appName}.tomcat.version=<%= application.getServerInfo() %>
${appName}.tomcat.catalina_base=<%= System.getProperty("catalina.base") %>
