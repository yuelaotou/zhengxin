<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@page
	import="org.xpup.hafmis.syscollection.querystatistics.collfncomparison.action.CollFnComparisonOrgAccountShowAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	Object pagination = session
			.getAttribute(CollFnComparisonOrgAccountShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
		<script src="<%=path%>/js/common.js"></script>
		<script src="<%=path%>/js/my.js"></script>
	</head>
	<script language="javascript">
var tr="tr0";
function gettr(trindex) {
	tr=trindex;
}
function back(){
	document.location='collFnComparisonShowAC.do';
}	
function precheck(){
	var orgid=document.collFnComparisonOrgAccountShowAF.orgId.value.trim();
	var orgname=document.collFnComparisonOrgAccountShowAF.orgName.value.trim();
	var timeSt=document.collFnComparisonOrgAccountShowAF.timeSt.value.trim();
	var timeEnd=document.collFnComparisonOrgAccountShowAF.timeEnd.value.trim();
  	var name =encodeURI(orgname);
  	
	if(timeSt==""){
		alert('请输入开始发生日期');
		return false;
	}else{
		if(!checkDate(document.collFnComparisonOrgAccountShowAF.timeSt)){
			document.collFnComparisonOrgAccountShowAF.timeSt.value="";
			return false;
		}
	}
	if(timeEnd==""){
		alert('请输入结束发生日期');
		return false;
	}else{
		if(!checkDate(document.collFnComparisonOrgAccountShowAF.timeEnd)){
			document.collFnComparisonOrgAccountShowAF.timeEnd.value="";
			return false;
		}
	}
	if(orgid!=""||orgname!=""){
		var queryString = "orgpopTaFindAC.do?";
		queryString = queryString + "orgid="+orgid+"&orgname="+name; 
		findInfo(queryString);
		return false;
	}else if(orgid==""&&orgname==""){
		return toorgpop();
	}
	
}
function display1(orgid,orgname,count) {
	if(count>1){
		return toorgpop();
	}else{
		document.collFnComparisonOrgAccountShowAF.orgName.value=orgname;
		document.collFnComparisonOrgAccountShowAF.orgId.value=orgid;
		document.forms[0].submit();
	}
}
function toorgpop(){
	var orgid=document.collFnComparisonOrgAccountShowAF.orgId.value.trim();
	var orgname=document.collFnComparisonOrgAccountShowAF.orgName.value.trim();
	var timeSt=document.collFnComparisonOrgAccountShowAF.timeSt.value.trim();
	var timeEnd=document.collFnComparisonOrgAccountShowAF.timeEnd.value.trim();
	if(timeSt==""){
		alert('请输入开始发生日期');
		return false;
	}
	if(timeEnd==""){
		alert('请输入结束发生日期');
		return false;
	}
	orgname =encodeURI(orgname);
	window.open("orgpopTaShowAC.do?orgid="+orgid+"&orgname="+orgname+"&indexs="+'0',"window","height=450,width=700,top=300,left=300,scrollbars=yes, status=yes"); 
	return false;
}
function executeAjax(){
	var queryString = "findOrgNameAAC.do?";
    var id = document.collFnComparisonOrgAccountShowAF.orgId.value.trim();
	queryString = queryString + "id="+id; 
	findInfo(queryString);
}
function displays(orgid,orgName) {
	document.collFnComparisonOrgAccountShowAF.orgName.value=orgName;
	document.collFnComparisonOrgAccountShowAF.orgId.value=orgid;
	document.forms[0].submit();
}
function to_print(){
	window.open('<%=path%>/syscollection/querystatistics/collfncomparison/collFnComparisonOrgAccountFindPrintConditionAC.do',"window","height=450,width=700,top=300,left=300,scrollbars=yes, status=yes");       
}
</script>

	<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false">

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
										<td width="148" class=font14 bgcolor="#FFFFFF" align="center"
											valign="bottom">
											<font color="00B5DB">新版单位账</font>
										</td>
										<td width="115" class=font14>
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
					<html:form action="/collFnComparisonOrgFindAC" style="margin: 0">
						<table width="95%" border="0" cellspacing="0" cellpadding="0"
							align="center">
							<tr>
								<td height="35">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td height="22" bgcolor="#CCCCCC" align="center" width="117">
												<b class="font14">查 询 条 件</b>
											</td>
											<td height="22" background="<%=path%>/img/bg2.gif"
												align="center">
												&nbsp;
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
						<table border="0" width="95%" id="table1" cellspacing=1
							cellpadding=3 align="center">
							<tr>
								<td width="17%" class="td1">
									单位编号
								</td>
								<td width="33%" colspan="3">
									<html:text name="collFnComparisonOrgAccountShowAF"
										property="orgId" styleClass="input3" maxlength="50"
										onkeydown="enterNextFocus1();"></html:text>
								</td>
								<td width="17%" class="td1">
									单位名称
								</td>
								<td width="33%" colspan="3">
									<html:text name="collFnComparisonOrgAccountShowAF"
										property="orgName" styleClass="input3" maxlength="50"
										onkeydown="enterNextFocus1();"></html:text>
								</td>
							</tr>
							<tr>
								<td width="17%" class="td1">
									发生日期<font color="#FF0000">*</font>
								</td>
								<td width="15%">
									<html:text name="collFnComparisonOrgAccountShowAF"
										property="timeSt" styleClass="input3" maxlength="50"
										onkeydown="enterNextFocus1();"></html:text>
								</td>
								<td width="3%">
									至
								</td>
								<td width="15%">
									<html:text name="collFnComparisonOrgAccountShowAF"
										property="timeEnd" styleClass="input3" maxlength="50"
										onkeydown="enterNextFocus1();"></html:text>
								</td>

							</tr>
						</table>
						<table width="95%" border="0" align="center" cellpadding="0"
							cellspacing="0">
							<tr>
								<td align="right">
									&nbsp;
								</td>
							</tr>
						</table>
						<table width="95%" border="0" align="center" cellpadding="0"
							cellspacing="0">
							<tr>
								<td align="right">
									<input type="submit" name="submit1" class=buttona value="查询"
										onclick="return precheck();" />
								</td>
							</tr>
						</table>
					</html:form>
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="142">
											<b class="font14">单位账列表 </b>
										</td>
										<td height="22" background="<%=path%>/img/bg2.gif"
											align="center" width="762">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<html:form action="/collFnComparisonOrgAccountMaintainAC"
						style="margin: 0">
						<table width="95%" border="0" cellspacing="0" cellpadding="3"
							align="center">
							<tr bgcolor="1BA5FF">
								<td align="center" height="6" colspan="1"></td>
							</tr>
						</table>
						<table width="95%" border="0" cellspacing="0" cellpadding="3"
							align="center">
							<tr>

								<td align="center" class=td2>
									日期
								</td>
								<td align="center" class=td2>
									凭证号
								</td>
								<td align="center" class=td2>
									摘要
								</td>
								<td align="center" class=td2>
									借方
								</td>
								<td align="center" class=td2>
									贷方
								</td>
								<td align="center" class=td2>
									销户利息
								</td>
								<td align="center" class=td2>
									方向
								</td>
								<td align="center" class=td2>
									余额
								</td>
							</tr>
							<logic:notEmpty name="collFnComparisonOrgAccountShowAF"
								property="list">
					          <% int j=0;
								String strClass="";%>
								<logic:iterate id="element"
									name="collFnComparisonOrgAccountShowAF" property="list"
									indexId="i">
										<logic:equal name="element" property="type" value="">
						          <%j++;
									if (j%2==0) {strClass = "td8";
									}
								    else {strClass = "td9";
								    }%>
									   <tr id="tr<%=i%>"  onMouseOver='this.style.background="#eaeaea"'  onMouseOut='gotoColorpp("<%=i %>", idAF);' class="<%=strClass%>"  onDblClick=""> 
										<td style="display:none">
											<input id="s<%=i%>" type="radio" name="id"
												value="<bean:write name="element" property="doc_num"/>"
												<%if(new Integer(0).equals(i)) out.print("checked"); %> />
										</td>
										<td>
											<div align="left">
												<bean:write name="element" property="collsett_date" />
											</div>
										</td>
										<td>
											<div align="left">
												<bean:write name="element" property="doc_num" />
											</div>
										</td>
										<td>
											<div align="left">
												<bean:write name="element" property="temp_collSt" />
										</td>
										<td>
											<div align="left">
												<bean:write name="element" property="debit" />
											</div>
										</td>
										<td>
											<div align="left">
												<bean:write name="element" property="credit" />
											</div>
										</td>
										<td>
											<div align="left">
												<bean:write name="element" property="distorybalance" />
											</div>
										</td>
										<td>
											<div align="left">
												<bean:write name="element" property="aspect" />
											</div>
										</td>
										<td>
											<div align="left">
												<bean:write name="element" property="moneySum" />
											</div>
										</td>
										
									</tr>
									<tr>
										<td colspan="18" class=td5></td>
									</tr>
</logic:equal>
								<logic:equal name="element" property="type" value="0">
						          <%j++;
									if (j%2==0) {strClass = "td8";
									}
								    else {strClass = "td9";
								    }%>
									<tr id="tr<%=i%>"  class=td4  onDblClick="" style="background-color:#FF6659;">	
										<td style="display:none">
											<input id="s<%=i%>" type="radio" name="id"
												value="<bean:write name="element" property="doc_num"/>"
												<%if(new Integer(0).equals(i)) out.print("checked"); %> />
										</td>
										<td>
											<div align="left">
												<bean:write name="element" property="collsett_date" />
											</div>
										</td>
										<td>
											<div align="left">
												<bean:write name="element" property="doc_num" />
											</div>
										</td>
										<td>
											<div align="left">
												<bean:write name="element" property="temp_collSt" />
										</td>
										<td>
											<div align="left">
												<bean:write name="element" property="debit" />
											</div>
										</td>
										<td>
											<div align="left">
												<bean:write name="element" property="credit" />
											</div>
										</td>
										<td>
											<div align="left">
												<bean:write name="element" property="distorybalance" />
											</div>
										</td>
										<td>
											<div align="left">
												<bean:write name="element" property="aspect" />
											</div>
										</td>
										<td>
											<div align="left">
												<bean:write name="element" property="moneySum" />
											</div>
										</td>
									</tr>
									<tr>
										<td colspan="18" class=td5></td>
									</tr>
</logic:equal>
								</logic:iterate>
							</logic:notEmpty>

							<logic:empty name="collFnComparisonOrgAccountShowAF"
								property="list">
								<tr>
									<td colspan="11" height="30" style="color:red">
										没有找到与条件相符合结果！
									</td>
								</tr>
								<tr>
									<td colspan="11"></td>
								</tr>
							</logic:empty>
						</table>
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr class="td1"> 
		  <td>
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr> 
			  	<td align="left">共<bean:write name="pagination" property="nrOfElements"/> 项</td>
                <td align="right"><jsp:include page="../../../inc/pagination.jsp">
											<jsp:param name="url"
													value="collFnComparisonOrgAccountShowAC.do" />
											</jsp:include></td>
              </tr>
            </table>
          </td>
	    </tr>
      </table>
						<table width="95%" border="0" cellspacing="0" cellpadding="3"
							align="center">
							<tr valign="bottom">
								<td colspan="10" bgcolor="#FFFFFF" align="center" height="30">
									<table border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="70">
                    							<input type="button" name="Submit4" value="打印" class="buttona" onclick="to_print();">    
											</td>
											<td width="70">
												<html:button property="method" styleClass="buttona"
													onclick="back();">
													<bean:message key="button.back" />
												</html:button>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</html:form>

				</td>
			</tr>
		</table>

	</body>
</html>
