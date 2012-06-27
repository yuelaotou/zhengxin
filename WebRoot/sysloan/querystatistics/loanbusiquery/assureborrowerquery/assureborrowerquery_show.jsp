<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page
	import="org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.assureborrowerquery.action.AssureborrowerqueryShowAC"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Object pagination = request.getSession(false).getAttribute(
			AssureborrowerqueryShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>

<html>
<head>
<title>个贷管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
</head>
<script language="javascript" src="<%=path%>/js/common.js">
var oldColor="#ffffff";                            //原来的颜色
var newColor="#E8FCFD";                     //要变成的颜色
function chgBGColor(oTD){
	oldColor=oTD.style.backgroundColor;//保存当前颜色
	oTD.style.backgroundColor=newColor;//改变表格颜色
	newColor=oldColor;                 //改变下次要变成的颜色
}
</script>

<body bgcolor="#FFFFFF" text="#000000">
<table  border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="7"><img src="<%=path%>/img/table_left.gif" width="7" height="37"></td>
          <td background="<%=path%>/img/table_bg_line.gif" width="55">&nbsp;</td>
          <td width="235" background="<%=path%>/img/table_bg_line.gif">&nbsp;</td>
          <td background="<%=path%>/img/table_bg_line.gif" align="right"> 
            <table width="300" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="37"><img src="<%=path%>/img/title_banner.gif" width="37" height="24"></td>
                <td width="234" class=font14 bgcolor="#FFFFFF" align="center" valign="bottom"><font color="00B5DB">统计查询&gt;保证人辅助借款人查询</font></td>
                <td width="29" class=font14>&nbsp;</td>
              </tr>
            </table>
          </td>
          <td width="9"><img src="<%=path%>/img/table_right.gif" width="9" height="37"></td>
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
                <td height="22" bgcolor="#CCCCCC" align="center" width="226"><b class="font14">查 询 条 件</b></td>
                <td height="22" background="<%=path%>/img/bg2.gif" align="center" width="1102">&nbsp; </td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
      <html:form action="/assureborrowerqueryFindAC" style="margin: 0">
      <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=0 align="center" >
	 <tr>
	   <td   class="td1">单位编号</td>
	   <td ><html:text name="assureborrowerqueryAF" property="orgId"
												styleClass="input3" onkeydown="enterNextFocus1();" /></td>
	   <td class="td1" >单位名称</td>
	   <td><html:text name="assureborrowerqueryAF" property="orgName"
												styleClass="input3" onkeydown="enterNextFocus1();" /></td>
	   </tr>
	 <tr> 
          <td width="16%"   class="td1">职工编号</td>
          <td width="34%" ><html:text name="assureborrowerqueryAF" property="empId"
												styleClass="input3" onkeydown="enterNextFocus1();" /></td>
          <td width="16%" class="td1" >职工姓名</td>
          <td  width="34%"><html:text name="assureborrowerqueryAF" property="empName"
												styleClass="input3" onkeydown="enterNextFocus1();" /></td>
        </tr>
	 <tr>
	   <td   class="td1">合同编号</td>
	   <td ><html:text name="assureborrowerqueryAF" property="contract"
												styleClass="input3" onkeydown="enterNextFocus1();" /></td>
	   <td class="td1" >扣款账号</td>
	   <td><html:text name="assureborrowerqueryAF" property="loanKouAcc"
												styleClass="input3" onkeydown="enterNextFocus1();" /></td>
	   </tr>
			  <tr> 
			    <td align="right" colspan="4"><html:submit property="method" styleClass="buttona"
												onclick="return checkDate1();">
												<bean:message key="button.search" />
											</html:submit></td>
			  </tr>
      </table>
     </html:form>
      <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td height="35"> 
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td height="22" bgcolor="#CCCCCC" align="center" width="222"><b class="font14">职 工 列 表</b></td>
                <td width="1106" height="22" align="center" background="<%=path%>/img/bg2.gif">&nbsp;</td>
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
	  <html:form action="/assureborrowerqueryMaintainAC"
								style="margin: 0">
     <table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1" cellpadding="3" align="center">
          <tr align="center" bgcolor="C4F0FF"> 
            <td height="23" bgcolor="C4F0FF" >&nbsp;</td>
			<td align="center" class=td1 >单位编号</td>
            <td align="center" class=td2 > 单位名称</td>
            <td align="center" class=td2 > 职工编号</td>
            <td align="center" class=td2 > 职工姓名</td>
            <td align="center" class=td2 > 账户余额</td>
            <td align="center" class=td2 > 类型</td>
            <td align="center" class=td2 >借款人职工编号</td>
            <td align="center" class=td2 >借款人姓名</td>
            <td align="center" class=td2 >合同编号</td>
            <td align="center" class=td2 >贷款帐号</td>
          </tr>
          
          <logic:notEmpty name="assureborrowerqueryAF" property="list">
          <% int j=0;
			String strClass="";
		%>
										<logic:iterate name="assureborrowerqueryAF" property="list"
											id="element" indexId="i">
											<%j++;
			if (j%2==0) {strClass = "td8";
			}
		    else {strClass = "td9";
		    }
		%>
											<tr align="left" id="tr<%=i%>"
												
												 class="<%=strClass%>" onDblClick="">
												
												<td>
													<p>
														<bean:write name="element" property="orgId" />
													</p>
												</td>
												<td>
													<p>
														<bean:write name="element" property="otherBorrowerOrgName" />
													</p>
												</td>
												<td>
													<p>
														<bean:write name="element" property="otherBorrowerEmpId" />
													</p>
												</td>
												<td>
													<p>
														<bean:write name="element" property="otherBorrowerName" />
													</p>
												</td>
												<td>
													<bean:write
															name="element" property="loanMoney" /> 
												</td>
												<td>
													<p>
														<bean:write name="element" property="type" />
													</p>
												</td>
												<td>
													<p>
														<bean:write name="element" property="borrowerEmpId" />
													</p>
												</td>
												<td>
													<p>
														<bean:write name="element" property="borrowerName" />
													</p>
												</td>
												<td>
													<p>
														<bean:write name="element" property="contractId" />
													</p>
												</td>
												<td>
													<p>
														<bean:write name="element" property="loanKouAcc" />
													</p>
												</td>
											</tr>
											
										</logic:iterate>
									</logic:notEmpty>
									<logic:empty name="assureborrowerqueryAF" property="list">
										<tr>
											<td colspan="9" height="30" style="color:red">
												没有找到与条件相符合的结果！
											</td>
										</tr>
										
									</logic:empty>
          <tr > 
            <td colspan="12" class=td5 ></td>
          </tr>
      </table>
      <table width="95%" border="0" cellspacing="0" cellpadding="3"
									align="center">
									<tr class="td1">
										<td align="center">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr align="center">
													<td align="left">
														共
														<bean:write name="pagination" property="nrOfElements" />
														项
													</td>
													<td align="right">
														<jsp:include page="../../../../inc/pagination.jsp">
															<jsp:param name="url" value="assureborrowerqueryShowAC.do" />
														</jsp:include>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
								<table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr class="td1"> 
		  <td>&nbsp;</td>
	    </tr>
      </table>
	 
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr valign="bottom"> 
          <td  colspan="7" bgcolor="#FFFFFF" align="center" height="30"> 
            <table border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="70"> 
                 <html:submit property="method" styleClass="buttona"
												>
												<bean:message key="button.print" />
											</html:submit>
                </td>
              </tr>
            </table>
	  </html:form>
      
    </td>
  </tr>
</table>
</table></body>
<script language="javascript">
	function easyStyle(){
		window.location.href="工资基数调整_简约风格.html";
	}
</script>
</html>


