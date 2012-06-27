<% session.removeAttribute("task"); %>

<jsp:useBean id="task" scope="session"
    class="org.xpup.hafmis.sysloan.loancallback.loancallback.action.TaskBean">
	<BODY></BODY>
</jsp:useBean>

<% task.setRunning(true); %>

<% new Thread(task).start(); %>

<jsp:forward page="status.jsp"/>

