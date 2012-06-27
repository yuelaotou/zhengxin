<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ page import="org.xpup.hafmis.syscollection.tranmng.tranout.action.Tran_showTCAC"%>
<%@ include file="/checkUrl.jsp"%>


<%
	String path = request.getContextPath();
	Object pagination = request.getSession(false).getAttribute(
			Tran_showTCAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script language="javascript" src="<%=path%>/js/common.js"></script>

</head>

<script language="javascript">
function sun(borrowerName,cardNum){
	borrowerName =encodeURI(borrowerName);
	if(borrowerName.indexOf("_")>0){
		borrowerName=borrowerName+"_"+document.all.outOrgId.value;
	}
	window.open ('<%=path%>/sysloan/showEmpAccountPopListAC.do?borrowerName='+borrowerName+'&cardNum='+cardNum,'window','height=450,width=700,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',scrollbars=yes, status=yes');
	return false;
}
function sun1(borrowerName,cardNum){
	borrowerName =encodeURI(borrowerName);
	if(borrowerName.indexOf("_")>0){
		borrowerName=borrowerName+"_"+document.all.inOrgId.value;
	}
	window.open ('<%=path%>/sysloan/showEmpAccountPopListAC.do?borrowerName='+borrowerName+'&cardNum='+cardNum,'window','height=450,width=700,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',scrollbars=yes, status=yes');
	return false;
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false">
<jsp:include page="../../../inc/sort.jsp"><jsp:param name="url" value="tran_showTCAC.do"/></jsp:include>
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="7"><img src="<%=path%>/img/table_left.gif" width="7" height="37"></td>
          <td background="<%=path%>/img/table_bg_line.gif" width="55">&nbsp;</td>
            <td width="235" background="<%=path%>/img/table_bg_line.gif">&nbsp; </td>
          <td background="<%=path%>/img/table_bg_line.gif" align="right"> 
            <table width="300" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="37"><img src="<%=path%>/img/title_banner.gif" width="37" height="24"></td>
                  <td class=font14 bgcolor="#FFFFFF" align="center" valign="bottom"><font color="00B5DB">转入转出<font color="00B5DB">&gt;办理转出</font></td>
                  <td width="15" class=font14>&nbsp;</td>
              </tr>
            </table>          </td>
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
                <td height="22" bgcolor="#CCCCCC" align="center" width="117"><b class="font14">转 出 信 息</b></td>
                <td height="22" background="<%=path%>/img/bg2.gif" align="center">&nbsp;</td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
	<html:form action="/tran_showAC.do" style="margin: 0">
	  <table border="0" width="95%" id="table1" cellspacing=1 cellpadding=3 align="center">
		<tr>
			<td class="td1" width="17%">转出单位编号</td>
			<td width="22%">
			<html:text name="tranAF" property="outOrgId" styleClass="input3" readonly="true"/>
			</td>
		    
			<td width="17%" class="td1">转出单位名称</td>
			<td width="33%"><html:text name="tranAF" property="outOrgname" styleClass="input3" readonly="true"/></td>
		</tr>
		<tr>
			<td class="td1" width="17%">转入单位编号</td>
			<td width="22%">
				<html:text name="tranAF" property="inOrgId"	styleClass="input3" readonly="true"/>
			</td>
			
			<td width="17%" class="td1">转入单位名称</td>
			<td width="33%"><html:text name="tranAF" property="inOrgName" styleClass="input3" readonly="true"/></td>
		</tr>
		<tr>
			<td class="td1" width="17%">结算号</td>
			<td colspan="1"><html:text name="tranAF" property="noteNumber" styleClass="input3" readonly="true"/></td>
			<td width="17%" class="td1">凭证号:</td>
			<td width="33%" ><html:text name="tranAF" property="docNum" styleClass="input3" readonly="true"/>
			</td>
       </tr>
	</table>
    <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
		<tr>
			<td class=h4>合计：人数<u>： <bean:write name="pagination" property="nrOfElements" /></u>转出金额
								<u> ：<bean:write name="tranAF" property="sumBalance" />
								</u>利息
								<u> ：<bean:write name="tranAF" property="sumInterest" /></u>转出总额
								<u> ：<bean:write name="tranAF" property="sumMoney" /> </u>
			</td>
		</tr>
	</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="117">
											<b class="font14">办理转出列表</b>
										</td>
										<td height="22"
											background="<%=request.getContextPath()%>/syscollection/tranmng/tranout/<%=path%>/img/bg2.gif"
											align="center">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr bgcolor="1BA5FF">
							<td align="center" height="6" colspan="1"></td>
						</tr>
					</table>
				</html:form>
				<html:form action="/tranOutMXPrintAC.do" style="margin: 0">
					<table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
						<tr>
							<td align="center" bgcolor="C4F0FF">
							<input type="hidden"  name="headid" value="<bean:write name="tranAF" property="headid" />">
								&nbsp;
							</td>
							
							<td align="center" class=td2>
								<a href="javascript:sort('tranOutTail.empId')">职工编号</a>
								    <logic:equal name="pagination" property="orderBy" value="tranOutTail.empId">
									<logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother" value="DESC">▼</logic:equal>
								</logic:equal>
							</td>

							<td align="center" class=td2>
								<a href="javascript:sort('tranOutTail.empName')">职工姓名</a>
				                   <logic:equal name="pagination" property="orderBy" value="tranOutTail.empName">
				          	       <logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
				          	       <logic:equal name="pagination" property="orderother" value="DESC">▼</logic:equal>
				          	  	</logic:equal>
							</td>
							
							<td align="center" class=td2>
								证件号码
							</td>
							<td align="center" class=td2>
								转出金额
							</td>
							<td align="center" class=td2>
								利息
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('tranOutTail.sumMoney')">转出总额</a>
				                   <logic:equal name="pagination" property="orderBy" value="tranOutTail.sumMoney">
				          	       <logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
				          	       <logic:equal name="pagination" property="orderother" value="DESC">▼</logic:equal>
				          	  	</logic:equal>
							</td>
							<td align="center" class=td2>
								转入职工编号
							</td>
						</tr>
						<logic:notEmpty name="tranAF" property="list">
						<logic:iterate name="tranAF" property="list" id="element" indexId="i">
						<tr align="left" id="tr<%=i%>" onclick='gotoClick("tr<%=i%>","s<%=i%>", idAF);' onMouseOver='this.style.background="#eaeaea"' onMouseOut='gotoColor("tr<%=i%>","s<%=i%>", idAF);' class=td4	onDblClick="">
							<td valign="top">
							<p align="left"><input id="s<%=i%>" type="radio" name="id" value="<bean:write name="element" property="id"/>"
												<%if(new Integer(0).equals(i)) out.print("checked"); %>>
							</p>
							</td>
							<td>
							  <p>
								<a href="#" onclick="return sun('<bean:write name="element" property="empName" />_<bean:write name="element" property="empId"/>','<bean:write name="element" property="emp.empInfo.cardNum" />');">
										<bean:write name="element" property="empId" format="000000"/></a>
							</td>
							<td><bean:write name="element" property="empName" /></td>						
							<td><bean:write name="element" property="emp.empInfo.cardNum" /></td>
							<td><bean:write name="element" property="sumBalance" /></td>
							<td><bean:write name="element" property="sumInterest" /></td>
							<td><bean:write name="element" property="sumMoney" /></td>
							<logic:notEqual name="element" property="tranin_empid" value="0">
							<td>
							  <p>
								<a href="#" onclick="return sun1('<bean:write name="element" property="reserveaB" />_<bean:write name="element" property="tranin_empid" />','<bean:write name="element" property="reserveaC" />');">
										<bean:write name="element" property="tranin_empid" /></a>
							</td>
							</logic:notEqual>
							<logic:equal name="element" property="tranin_empid" value="0">
								<td>&nbsp;</td>
							</logic:equal>
						</tr>
						
						<tr>
							<td colspan="9" class="td5"></td>
						</tr>
						</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="tranAF" property="list">
				        <tr> 
				          <td colspan="9" height="30" style="color:red">没有找到与条件相符合结果！</td>
					    </tr>
						<tr > 
				          <td colspan="9" class=td5 ></td>    
				        </tr>
				        </logic:empty>
					</table>

					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr class="td1">
							<td>
								<table width="100%" border="0" align="center" cellpadding="0"
									cellspacing="0">
									<tr>
										<td align="left">
											共
											<bean:write name="pagination" property="nrOfElements" />
											项
										</td>
										<td align="right">
											<jsp:include page="../../../inc/pagination.jsp">
												<jsp:param name="url" value="tran_showTCAC.do"/>
											</jsp:include>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>

					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr valign="bottom">
							<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
									<!-- 
										<td>
											<html:submit property="method" styleClass="buttona"
												onclick="">
												<bean:message key="button.print" />
											</html:submit>
										</td> -->
										<td>
											<input type="button" name="Submit42" value="关闭" class="buttona" onClick="javascript:window.close();" > 
										</td>
									</tr>
								</table>

							</td>
						</tr>
					</table>
				</html:form>
</body>
</html:html>
