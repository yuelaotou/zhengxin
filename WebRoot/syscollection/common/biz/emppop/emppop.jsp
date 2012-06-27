<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@page import="org.xpup.hafmis.syscollection.common.biz.emppop.action.EmployeesShowAC" %>
<%@ include file="/checkUrl.jsp"%>
<%
	Object pagination = session.getAttribute(EmployeesShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String path=request.getContextPath();
   String indexs=(String)session.getAttribute("indexs");
%>
<html>
<head>
<title>开户销户>单位列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
<script src="<%=path%>/js/common.js"></script>
<script type="text/javascript">
	function loads(){
	var counts="<bean:write name="pagination" property="nrOfElements"/>";
     if(counts=="0"){
     	document.form1.elements["sure"].disabled="true";
     }
}
	function checkCardNum(){
		var cardNum = document.employeesAF.elements["cardNumber"].value.trim();
		if(cardNum != null && !cardNum == ""){
			if(isIdCardNo(cardNum)){
				return true;
			}else{
				document.employeesAF.elements["cardNumber"].focus();
				return false;
			}
		}
	}
</script>
</head>

<body bgcolor="#FFFFFF" text="#000000"  onContextmenu="return false" onload="loads();">
<jsp:include page="../../../../inc/sort.jsp"><jsp:param name="url" value="showEmployeesListAC.do"/></jsp:include>
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="7"><img src="<%=path%>/img/table_left.gif" width="7" height="37"></td>
          <td background="<%=path%>/img/table_bg_line.gif" width="555">&nbsp;</td>
          <td width="635" background="<%=path%>/img/table_bg_line.gif"></td>
          <td background="<%=path%>/img/table_bg_line.gif" align="right"></td>
          <td width="1"><img src="<%=path%>/img/table_right.gif" width="9" height="37"></td>
        </tr>
      </table>
    </td>
  </tr>
  <tr> 
    <td class=td3>
    <html:form action="/findEmployeesListAC" styleClass="margin: 0" target="_self">
        <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=3 align="center" >
        
        	<tr> 
            <td width="13%"   class="td1"> 单位编号： </td>
            <td width="18%"  > 
             <html:text property="orgId" name="employeesAF" styleClass="input3" readonly="true" styleId="txtsearch"></html:text>
            </td>
              <td width="13%"   class="td1">单位名称： </td>
            <td width="18%"  > 
             <html:text property="orgName" name="employeesAF" styleClass="input3" readonly="true" styleId="txtsearch"></html:text>
            </td>
          </tr>
          
          <tr> 
            <td width="13%"   class="td1"> 职工编号： </td>
            <td width="18%"  > 
             <html:text property="id" name="employeesAF" styleClass="input3" onkeydown="enterNextFocus1();" styleId="txtsearch"></html:text>
            </td>
              <td width="13%"   class="td1"> 职工姓名： </td>
            <td width="18%"  > 
             <html:text property="name" name="employeesAF" styleClass="input3" onkeydown="enterNextFocus1();" styleId="txtsearch"></html:text>
            </td>
          </tr>
           <tr>
          <td width="13%" class="td1">原职工编号</td>
	      <td width="18%"  >
			<html:text property="oldId" name="employeesAF" styleClass="input3" onkeydown="enterNextFocus1();"  styleId="txtsearch"></html:text>
          </td>
	      <td width="11%" class="td1" >身份证号码</td>
	      <td width="21%"  >
			<html:text property="cardNumber" name="employeesAF" styleClass="input3" onkeydown="enterNextFocus1();"  styleId="txtsearch" maxlength="18"></html:text>
          </td>
	      </tr>
      </table>
      <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td align="right">
            <html:submit styleClass="buttona" onclick="return checkCardNum();">查询</html:submit>
          </td>
        </tr>
      </table>
        </html:form>
      <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td height="35"> 
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td height="22" bgcolor="#CCCCCC" align="center" width="117"><b class="font14">职工列表 </b></td>
                <td height="22" background="<%=path%>/img/bg2.gif" align="center">&nbsp;</td>
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
      <form name="idAF" action="" style="margin: 0">
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
		<tr>         
          <td align="center" bgcolor="C4F0FF" >&nbsp;</td>
          <td align="center" class=td2 >
          	职工编号
          </td>
		  <td align="center" class=td2 >职工信息编号</td>
          <td align="center" class=td2 >职工姓名 </td>
          
          <!--
          <td align="center" class=td2 >单位编号</td>
          <td align="center" class=td2 >单位名称 </td>
          -->
          
          <td align="center" class=td2 >身份证号码</td>
          <td align="center" class=td2 >原职工编号</td>
          <td align="center" class=td2 >职工状态 </td>
		</tr>
		<logic:notEmpty name="employees">
		<logic:iterate id="employees" name="employees" indexId="i">
        <tr id="tr<%=i %>" onClick='gotoClick("tr<%=i %>","s<%=i %>", idAF);' onMouseOver='this.style.background="#eaeaea"' onMouseOut='gotoColor("tr<%=i %>","s<%=i %>", idAF);' class="td4" onDblClick="qdValues('<%=indexs%>');"> 
          <td valign="top">
          <p align="left">
            <input id="s<%=i %>" type="radio" name="id" value="<bean:write name="employees" property="empId"/>" <%if(new Integer(0).equals(i)) out.print("checked"); %>>
          </p>
          </td>

          	<td valign="top"><p><bean:write name="employees" property="empId" format="000000"/></p></td>
          	<td valign="top"><p><bean:write name="employees" property="empInfo.id" format="000000"/></p></td>
     	 	<td valign="top"><p><bean:write name="employees" property="empInfo.name" /></p></td>
     	 	
     	 	<!--
         	<td valign="top"><p><bean:write name="employees" property="org.id" format="000000"/></p></td>
          	<td valign="top"><p><bean:write name="employees" property="org.orgInfo.name" /></p></td>
          	-->
          	<td valign="top"><p><bean:write name="employees" property="empInfo.cardNum" /></p></td>
            <td valign="top"><p><bean:write name="employees" property="oldEmpID" format="000000"/></p></td>
            <td valign="top"><p><bean:write name="employees" property="payStatusStr" /></p></td>                   
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
      </form>
  
               <form name="form1">
         <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr class="td1"> 
		  <td>
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr> 
			  	<td align="left">共<bean:write name="pagination" property="nrOfElements"/> 项</td>
                <td align="right"><jsp:include page="../../../../inc/pagination.jsp"><jsp:param name="url" value="showEmployeesListAC.do"/></jsp:include></td>
              </tr>
          </table></td>
	    </tr>
      </table>
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr valign="bottom"> 
          <td  colspan="7" bgcolor="#FFFFFF" align="center" height="30"> 
            <table border="0" cellspacing="0" cellpadding="0">
               <tr>
                <td width="70">
                	<input type="button" name="sure" value="确 定" class="buttona" onclick='qdValues("<%=indexs%>");'>
                </td>
              </tr>
            </table>
          </td>
        </tr>
      </table>	
      </form>	
         </td>
        </tr>
      </table>	  
</body>
</html>
