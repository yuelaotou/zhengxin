<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ include file="/checkUrl.jsp"%>
<%String path=request.getContextPath();%>
<html:html>
<head>
	<title>修改业务日期</title>
	<link href="<%=path%>/css/index.css" type="text/css" rel="stylesheet" />
	<SCRIPT language="JAVASCRIPT">
 var arr= document.getElementsByName("rowArray");
 
function isSure(){

  temp=0;//选中几条记录
  for(var i=0;i<arr.length;i++){
		if(arr[i].checked==true){
			temp=temp+1;
		}
	}
	if(temp==0){
		alert("请选月结的银行！！");
		return false;
	}
	var x=confirm("您确认要进行月结?");
		if(x){ 
		  	return true;
		}else{
			for(var i=0;i<arr.length;i++){
				if(arr[i].checked==true){
					arr[i].checked="";
				}
			}
		  return false;
		}
}

function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	
    if(message=="3"){
    var x=confirm("有未入账的转入业务！您确认要进行月结?");
    if(x){ 
    	document.all.temp.value="1";
		  	document.forms[0].submit();
		}else{
			for(var i=0;i<arr.length;i++){
				if(arr[i].checked==true){
				arr[i].checked="";
				}
			}
		  return false;
		}
    }else if(message=="0"){
    alert("月结成功！即将重新登录系统！");
    parent.window.location="<%=path%>/login.jsp";
    }else if(message=="1"){
     alert("月结失败！");
    }else {
    	alert(message);
    }
	</logic:messagesPresent>
	for(var i=0;i<arr.length;i++){
		if(arr[i].checked==true){
			arr[i].checked="";
		}
	}
}
</SCRIPT>
</head>

<body topmargin=0 leftmargin=0 onload="reportErrors();">
	<html:form action="/monthclearDoAC.do">
		<div align="center">
			<br>
			<br>
			<br>
			<table width=641 border=0 cellpadding=0 cellspacing=0>
				<tr>
					<td colspan=4 height="32" class="font14"
						background="<%=path%>/img/search_1.jpg" style="PADDING-top: 8px">
						<b>月结</b>
					</td>
				</tr>
				<tr>
					<%--      <td rowspan=3> <img src="<%=path%>/img/search_2.jpg" width=26 height=325 alt=""></td>--%>
					<%--      <td colspan=3> <img src="<%=path%>/img/search_3.jpg" width=615 height=39 alt=""></td>--%>
				</tr>
				<tr>
					<%--      <td rowspan=2> <img src="<%=path%>/img/search_4.jpg" width=40 height=286 alt=""></td>--%>
					<td height="225" valign="top">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td align="center" valign="top"
									style="border-bottom-style: solid; border-bottom-width: 1;border-left-style: solid; border-left-width: 1;border-right-style: solid; border-right-width: 1"
									bordercolor="#2D7DC6">
									<table width="100%" border="0" cellspacing="0" cellpadding="2">
										<tr bgcolor="1BA5FF">
											<td align="center" height="6" colspan="1">
												<input type="hidden" name="temp" value="0">
											</td>
										</tr>
									</table>
									<table width="100%" border="0" bgcolor="#76BEE9"
										cellspacing="1" cellpadding="3" align="center">
										<tr>
											<td align="center" height="23" bgcolor="C4F0FF" class=td2></td>
											<td align="center" class=td2>
												归集银行
											</td>
											<td align="center" class=td2>
												业务日期
											</td>
										</tr>
										<logic:notEmpty name="List">
											<%
													int j = 0;
													String strClass = "";
											%>
											<logic:iterate name="List" id="elements" indexId="i">
												<%
															j++;
															if (j % 2 == 0) {
																strClass = "td8";
															} else {
																strClass = "td9";
															}
												%>
												<tr id="tr<%=i%>" class="<%=strClass%>">



													<td >
														<html:multibox property="rowArray">
															<bean:write name="elements" property="collBankId" />,<bean:write
																name="elements" property="collBankDate" />
														</html:multibox>
													</td>
													<td valign="top" >
														<p>
															<bean:write name="elements" property="collBankName" />
														</p>
													</td>
													<td valign="top" >
														<p>
															<bean:write name="elements" property="collBankDate" />
														</p>
													</td>
												</tr>
											</logic:iterate>
										</logic:notEmpty>
										<logic:empty name="List">
											<tr>
												<td colspan="15" height="30" style="color:red">
													没有找到与条件相符合结果！
												</td>
											</tr>
										</logic:empty>
									</table>
									<table width="100%" border="0" cellspacing="1" cellpadding="3"
										bgcolor="66BEE3">
										<tr align="center">
											<td colspan="4" class="td1" height="14">
												<html:submit onclick="return isSure()">月结</html:submit>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
					<%--      <td rowspan=2> <img src="<%=path%>/img/search_6.jpg" width=42 height=286 alt=""></td>--%>
				</tr>
				<tr>
					<%--      <td> <img src="<%=path%>/img/search_7.jpg" width=533 height=61 alt=""></td>--%>
				</tr>
			</table>
		</div>
	</html:form>
</body>

</html:html>
