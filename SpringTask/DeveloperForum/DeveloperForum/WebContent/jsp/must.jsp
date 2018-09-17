
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
if((session.getAttribute("role")==null))
{
	out.println("<script>href='login.jsp'</script>");
}
%>
