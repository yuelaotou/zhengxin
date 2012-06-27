<jsp:useBean id="task" scope="session"
    class="org.xpup.hafmis.sysloan.loancallback.loancallback.action.TaskBean"/>

<% task.setRunning(false); %>

<jsp:forward page="status.jsp"/>
