<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@page import="org.xpup.hafmis.syscollection.tranmng.tranin.action.EmployeeShowAC" %>
<%@ include file="/checkUrl.jsp"%>
<%
   Object pagination = session.getAttribute(EmployeeShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String path=request.getContextPath();
%>
<html>
<head>
<title>员工</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
<script src="<%=path%>/js/common.js"></script>
<script type="text/javascript">
</script>
</head>

<body bgcolor="#FFFFFF" text="#000000" align="center" onContextmenu="return false">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="7"><img src="images/table_left.gif" width="7" height="37"></td>
          <td background="images/table_bg_line.gif" width="555">&nbsp;</td>
          <td width="635" background="images/table_bg_line.gif"></td>
          <td background="images/table_bg_line.gif" align="right"></td>
          <td width="1"><img src="images/table_right.gif" width="9" height="37"></td>
        </tr>
      </table>
    </td>
  </tr>
  <tr> 
    <td class=td3>
      <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td height="35"> 
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td height="22" bgcolor="#CCCCCC" align="center" width="117"><b class="font14">职工列表 </b></td>
                <td>
                <font style="color:red">
                	<logic:equal name="Magssage" value="sameorg">
                	本单下存在该职工，请选择一个职工进行转入并户！
                	</logic:equal>
                	<logic:notEqual name="Magssage" value="sameorg">
                	本系统内该职工已经存在，请选择一个职工编号做为转入职工的编号！
                	</logic:notEqual>
                </font>
                </td>
                <td height="22" background="images/bg2.gif" align="center">&nbsp;</td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr bgcolor="1BA5FF" > 
          <td align="center" height="6" colspan="1" ></td>
        </tr>
      </table>
      <html:form  action="/addTraninMaintainAC2.do" style="margin: 0">
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
		<tr>         
          <td align="center" bgcolor="C4F0FF" >&nbsp;</td>
          <td align="center" class=td2 >职工编号</td>
          <td align="center" class=td2 >职工姓名 </td>
           <td align="center" class=td2>单位编号</td>
          <td align="center" class=td2 >单位名称 </td>
          <td align="center" class=td2 >老职工编号</td>
          <td align="center" class=td2 >职工状态 </td>
          <td align="center" class=td2 >帐户余额 </td>
		</tr>
		<logic:notEmpty name="traninAddAF" property="list">
		<logic:iterate name="traninAddAF" property="list" id="employees" indexId="i">
        <tr id="tr<%=i %>" onClick='gotoClick("tr<%=i %>","s<%=i %>", traninAddAF);' onMouseOver='this.style.background="#eaeaea"' onMouseOut='gotoColor("tr<%=i %>","s<%=i %>", traninAddAF);' class="td4" onDblClick="returnValues();"> 
          <td valign="top">
          <p align="left">
            <input id="s<%=i %>" type="radio" name="id" value="<bean:write name="employees" property="id"/>" <%if(new Integer(0).equals(i)) out.print("checked"); %>>
          </p>
          </td>
          <td valign="top"><p><bean:write name="employees" property="empId" /></p></td>
          <td valign="top"><p><bean:write name="employees" property="empInfo.name" /></p></td>
          <td valign="top"><p><bean:write name="employees" property="org.id" /></p></td>
          <td valign="top"><p><bean:write name="employees" property="org.orgInfo.name" /></p></td>
          <td valign="top"><p><bean:write name="employees" property="oldEmpID" /></p></td>
          <td valign="top"><p><bean:write name="employees" property="employeeState" /></p></td>  
          <td valign="top"><p><bean:write name="employees" property="TEMP_salaryBase" /></p></td>                  
        </tr>
        <tr > 
          <td colspan="11" class=td5 ></td>
        </tr>
        </logic:iterate>
        </logic:notEmpty>
        <logic:empty name="employees">
        <tr> 
          <td colspan="11" height="30" style="color:red">没有找到与条件相符合结果！</td>
	    </tr>
		<tr > 
          <td colspan="11" class=td5 ></td>
        </tr>
        </logic:empty>
      </table>
      <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
		<tr>
			<td align="center">
				<html:submit property="method" styleClass="buttona">
						<bean:message key="button.sure" />
				          </html:submit>
		
			    <html:submit property="method" styleClass="buttona">
						<bean:message key="button.back" />
						 </html:submit>
			</td>										
	 </table>
      </html:form>    
         </td>
        </tr>
      </table>	  
</body>
</html>
