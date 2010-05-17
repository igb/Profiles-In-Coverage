<%@ page import="org.hccp.instrument.report.graph.Method" %><%
     Method m = new Method();
    m.setName("hi");

%>

hello world

<br/>
<%=m.getName()%>
<br/>
<%=System.getProperty("org.hccp.instrument.coverageFile")%>
<%=System.getProperties()%>