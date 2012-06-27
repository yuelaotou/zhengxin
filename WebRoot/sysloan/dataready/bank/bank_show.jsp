<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="org.xpup.hafmis.sysloan.dataready.bank.action.ShowBankAC"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>
<%
String path = request.getContextPath();
%>
<%
			Object pagination = request.getSession(false).getAttribute(
			ShowBankAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>银行设置</title>
</head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css" />
<script src="<%=path%>/js/common.js"></script>
<script language="javascript" src="js/common.js">
var oldColor="#ffffff";                            //原来的颜色
var newColor="#E8FCFD";                     //要变成的颜色
function chgBGColor(oTD){
	oldColor=oTD.style.backgroundColor;//保存当前颜色
	oTD.style.backgroundColor=newColor;//改变表格颜色
	newColor=oldColor;                 //改变下次要变成的颜色
}
</script>
<script>
  function reportErrors(){
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
	}
</script>
<script>
function ondelete(){
  var x=confirm("确定删除此记录?");
  if(x){
	return true;
  }else{
    return false;
  }
}
function onuse(){
var x=confirm("是否启用?");
  if(x){
	return true;
  }else{
    return false;
  }
}
</script>
<script>
var s1="";
var s2="";
var s3="";
var s4="";
var tr="tr0";
function loads(){
	for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){

			if(document.all.item(i).value=="添加"){
				s1=i;
			}
			if(document.all.item(i).value=="修改"){
				s2=i;
			}
			if(document.all.item(i).value=="删除"){
				s3=i;
			}
			if(document.all.item(i).value=="启用"){
				s4=i;
			}
		}
	}

	update();
}

function gettr(trindex) {
  tr=trindex;
  update();
}
function update() {
if(document.getElementById(tr)!=null){
  	var stat=document.getElementById(tr).childNodes[10].innerHTML;
  	var stat=stat.trim();
		if(stat=="未启用"){
				document.all.item(s1).disabled="";
		  		document.all.item(s2).disabled="";
				document.all.item(s3).disabled="";
				document.all.item(s4).disabled="";
		}
		if(stat=="启用"){
				document.all.item(s1).disabled="";
		  		document.all.item(s2).disabled="";
				document.all.item(s3).disabled="true";
				document.all.item(s4).disabled="true";
		
		}
	}
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onLoad="reportErrors();return loads();">
<html:form action="/bankMaintainAC.do">
	<table width="95%" border="0" cellspacing="0" cellpadding="0"
		align="center">
		<tr>
			<td>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="7">
							<img src="<%=path%>/img/table_left.gif" width="7" height="37">
						</td>
						<td background="<%=path%>/img/table_bg_line.gif" width="55">
							&nbsp;
						</td>
						<td width="235" background="<%=path%>/img/table_bg_line.gif">
							&nbsp;
						</td>
						<td background="<%=path%>/img/table_bg_line.gif" align="right">
							<table width="300" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="37">
										<img src="<%=path%>/img/title_banner.gif" width="37"
											height="24">
									</td>
									<td width="234" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<a href="#" class=a1>数据准备</a><font color="00B5DB">&gt;</font><a
											href="#" class=a1>银行维护</a>
									</td>
									<td width="29" class=font14>
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
						<td width="9">
							<img src="<%=path%>/img/table_right.gif" width="9" height="37">
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td class=td3>
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					align="center">
					<tr>
						<td height="35">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="22" bgcolor="#CCCCCC" align="center" width="127">
										<b class="font14">银行设置</b>
									</td>
									<td width="816" height="22" align="center"
										background="images/bg2.gif">
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

				<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1" cellpadding="3" align="center">
          <tr align="center" bgcolor="C4F0FF"> 
            <td height="23" bgcolor="C4F0FF" >&nbsp;</td>
							<td align="center" class=td2>
								<a href="javascript:sort('loanBank.loanBankId')">银行名称</a>
          	<logic:equal name="pagination" property="orderBy" value="loanBank.loanBankId">
          	  <logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
          	  <logic:equal name="pagination" property="orderother" value="DESC">▼</logic:equal>
          	</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('loanBank.office')">所在中心办事处</a>
          	<logic:equal name="pagination" property="orderBy" value="loanBank.office">
          	  <logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
          	  <logic:equal name="pagination" property="orderother" value="DESC">▼</logic:equal>
          	</logic:equal>
							</td>
							<td align="center" class=td2>
								中心委托贷款账号
							</td>
							<td align="center" class=td2>
								中心利息账号
							</td>
							<td align="center" class=td2>
								划出账号
							</td>
							<td align="center" class=td2>
								划入账号
							</td>
							<td align="center" class=td2>
								银行行长
							</td>
							<td align="center" class=td2>
								行长电话
							</td>
							<td align="center" class=td2>
								联系人
							</td>
							<td align="center" class=td2>
								联系人电话
							</td>
							<td align="center" class=td2>
								联系人职务
							</td>
							<td align="center" class=td2>
								银行状态
							</td>
						</tr>
						<td>
							<logic:notEmpty name="listform" property="list">
							<% int j=0;
			String strClass="";
		%>
								<logic:iterate name="listform" property="list" id="bank"
									indexId="i">
									<%j++;
			if (j%2==0) {strClass = "td8";
			}
		    else {strClass = "td9";
		    }
		%>
									<tr id="tr<%=i%>" onclick='gotoClickpp("<%=i %>", idAF);gettr("tr<%=i%>");' onMouseOver='this.style.background="#eaeaea"' onMouseOut='gotoColorpp("<%=i %>", idAF);' class="<%=strClass%>" onDblClick="">
										<td valign="top">
											<p align="left">
												<input id="s<%=i%>" type="radio" name="id"
													value="<bean:write name="bank" property="id"/> "
													<%if(new Integer(0).equals(i)) out.print("checked"); %>>
											</p>
										</td>
										<td valign="top">
											<p>
												<bean:write name="bank" property="bankName" />
											</p>
										</td>
										<td valign="top">
											<p>
												<bean:write name="bank" property="office" />
											</p>
										</td>
										<td valign="top">
											<p>
												<bean:write name="bank" property="loanAcc" />
											</p>
										</td>
										<td valign="top">
											<p>
												<bean:write name="bank" property="interestAcc" />
											</p>
										</td>
										<td valign="top">
											<p>
												<bean:write name="bank" property="outAccount" />
											</p>
										</td>
										<td valign="top">
											<p>
												<bean:write name="bank" property="inAccount" />
											</p>
										</td>
										<td valign="top">
											<p>
												<bean:write name="bank" property="bankPrisident" />
											</p>
										</td>
										<td valign="top">
											<p>
												<bean:write name="bank" property="bankPrisidentTel" />
											</p>
										</td>
										<td valign="top">
											<p>
												<bean:write name="bank" property="contactPrsn" />
											</p>
										</td>
										<td valign="top">
											<p>
												<bean:write name="bank" property="contactTel"/>
											</p>
										</td>
										<td valign="top">
												<bean:write name="bank" property="business"/>
											
										</td>
										<td valign="top">
												<bean:write name="bank" property="loanBankSt"/>
											
										</td>
									</tr>
									
								</logic:iterate>
							</logic:notEmpty>
							
						</td>
										
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
											<jsp:param name="url" value="showBankAC.do" />
										</jsp:include>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<logic:empty name="listform" property="list">
				<table width="95%" border="0" cellspacing="0" cellpadding="3"
					align="center">
					<tr valign="bottom">
						<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
							<table border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="10">
									<td>
										<html:submit property="method" styleClass="buttona">
											<bean:message key="button.add"/>
										</html:submit>
									</td>
									<td width="10">
									<td>
										<html:submit property="method" styleClass="buttona" disabled="true">
											<bean:message key="button.update"/>
										</html:submit>
									</td>
									<td width="10">
									<td>
										<html:submit property="method" styleClass="buttona" disabled="true" onclick="return ondelete();">
											<bean:message key="button.delete"/>
										</html:submit>
									</td>
									<td width="10">
									<td>
										<html:submit property="method" styleClass="buttona" disabled="true"  onclick="return onuse();">
											<bean:message key="button.use"/>
										</html:submit>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</logic:empty>
			<logic:notEmpty name="listform" property="list">
				<table width="95%" border="0" cellspacing="0" cellpadding="3"
					align="center">
					<tr valign="bottom">
						<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
							<table border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="10">
									<td>
										<html:submit property="method" styleClass="buttona">
											<bean:message key="button.add"/>
										</html:submit>
									</td>
									<td width="10">
									<td>
										<html:submit property="method" styleClass="buttona">
											<bean:message key="button.update"/>
										</html:submit>
									</td>
									<td width="10">
									<td>
										<html:submit property="method" styleClass="buttona" onclick="return ondelete();">
											<bean:message key="button.delete"/>
										</html:submit>
									</td>
									<td width="10">
									<td>
										<html:submit property="method" styleClass="buttona" onclick="return onuse();">
											<bean:message key="button.use"/>
										</html:submit>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</logic:notEmpty>
	</table>
</html:form>	
</body>
</html:html>
