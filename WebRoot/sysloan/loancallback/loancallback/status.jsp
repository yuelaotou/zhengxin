<%@ page contentType="text/html;charset=GB2312" %>

<jsp:useBean id="task" scope="session"
    class="org.xpup.hafmis.sysloan.loancallback.loancallback.action.TaskBean"/>

<HTML>

<HEAD>
    <TITLE>JSP进度条</TITLE>
    <% if (task.isRunning()) { %>
        <SCRIPT LANGUAGE="JavaScript">
            setTimeout("location='status.jsp'", 1000);
        </SCRIPT>
    <% } %>
</HEAD>

<BODY>

    <H1 ALIGN="CENTER">JSP进度条</H1>

    <H2 ALIGN="CENTER">
        结果: <%= task.getResult() %><BR>
        <% int percent = task.getPercent(); %>
        <%= percent %>%
    </H2>

    <TABLE WIDTH="60%" ALIGN="CENTER"
            BORDER=1 CELLPADDING=0 CELLSPACING=2>
        <TR>
            <% for (int i = 10; i <= percent; i += 10) { %>
                <TD WIDTH="10%" BGCOLOR="#000080">&nbsp;</TD>
            <% } %>
            <% for (int i = 100; i > percent; i -= 10) { %>
                <TD WIDTH="10%">&nbsp;</TD>
            <% } %>
        </TR>
    </TABLE>

    <TABLE WIDTH="100%" BORDER=0 CELLPADDING=0 CELLSPACING=0>
        <TR>
            <TD ALIGN="CENTER">
                <% if (task.isRunning()) { %>
                    正在执行
                <% } else { %>
                    <% if (task.isCompleted()) { %>
                        完成
                    <% } else if (!task.isStarted()) { %>
                        尚未开始
                    <% } else { %>
                        已停止
                    <% } %>
                <% } %>
            </TD>
        </TR>

        <TR>
            <TD ALIGN="CENTER">
                <BR>
                <% if (task.isRunning()) { %>
                    <FORM METHOD="GET" ACTION="stop.jsp">
                        <INPUT TYPE="SUBMIT" VALUE="停止">
                    </FORM>
                <% } else { %>
                    <FORM METHOD="GET" ACTION="start.jsp">
                        <INPUT TYPE="SUBMIT" VALUE="开始">
                    </FORM>
                <% } %>
            </TD>
        </TR>
    </TABLE>

</BODY>

</HTML>
