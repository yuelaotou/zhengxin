<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@page
	import="org.xpup.hafmis.sysloan.common.biz.emppop.action.EmployeesShowAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
			Object pagination = session
			.getAttribute(EmployeesShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String path = request.getContextPath();
	String indexs = (String) session.getAttribute("indexs");
	String objInput = (String) request.getSession().getAttribute(
			"objInput");
%>
<html>
	<head>
		<title>开户销户>单位列表</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
		<script src="<%=path%>/js/common.js"></script>
		<script type="text/javascript">

	var tr="tr0"; 
	function gettr(trindex) {
	  tr=trindex;
	}
	
	//个人贷款确定时调用
	//这个方法最少3个人用 请不要改
function qdValues1(indexs){
	var a;
	var b=document.getElementsByName("id");
	var c=document.getElementById(tr).childNodes[4].childNodes[0].innerHTML;
  	var objInput;
  	var str="<%=objInput%>";
  	eval("objInput=window.opener.document.all."+str);
  	
  	
	for(i=0;i<b.length;i++)   
    {
    	if(b[i].checked==true){
    		a=b[i].value;
    	}
  	}
	if(""+a!="undefined"){
		//window.opener.document.all.code.value=format(a)+a;
		//window.opener.document.forms[0].elements["orgPaymentAgreement.organization.id"].value=format(a)+a;
		window.opener.document.forms[0].elements["org_Id"].value=format(c)+c;//不允许改名
		//var orgid=format(c)+c;
		var obj=window.opener.document.getElementsByTagName("input");
		if(indexs==0){
      	for(i=0;i<obj.length;i++){
      	  if(obj[i+eval(indexs)].type=="text"){
            obj[i+eval(indexs)].value=a;
            //opener.executeAjax();
            window.close();
        	break;
          }
  		}
  		}
  		if(indexs=="0"){
  			opener.executeAjax();
  		}else if(indexs=="1000"){
  			objInput.value=format(a)+a;
  			opener.executeAjaxIn_wsh();
  		}else if(indexs=="1001"){
  			objInput.value=format(a)+a;
  			opener.executeAjaxIn_wsha();
  		}else if(indexs=="1002"){
  			objInput.value=format(a)+a;
  			opener.executeAjaxIn_wshb();
  		}else if(indexs=="1"){
  			objInput.value=format(a)+a;
  			opener.executeAjaxIn_1();
  		}else{
  			objInput.value=format(a)+a;
  			opener.executeAjaxIn();
  		}
		
	}else{
		opener.clears();
	}
	window.close();
}
</script>
		<script type="text/javascript">
function loads(){
	var counts="<bean:write name="pagination" property="nrOfElements"/>";
     if(counts=="0"){
     	document.form1.elements["sure"].disabled="true";
     }
     document.getElementById("sfzhs").focus();
}
function formsubmit(){
	if(event.keyCode==13){
		document.forms(0).submit;
	}
}
</script>
	</head>

	<body bgcolor="#FFFFFF" text="#000000" 
		onload="loads();">
		<jsp:include page="../../../../inc/sort.jsp">
			<jsp:param name="url" value="showEmployeesListAC.do" />
		</jsp:include>
		<table width="95%" border="0" cellspacing="0" cellpadding="0"
			align="center">
			<tr>
				<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="7">
								<img src="<%=path%>/img/table_left.gif" width="7" height="37">
							</td>
							<td background="<%=path%>/img/table_bg_line.gif" width="555">
								&nbsp;
							</td>
							<td width="635" background="<%=path%>/img/table_bg_line.gif"></td>
							<td background="<%=path%>/img/table_bg_line.gif" align="right"></td>
							<td width="1">
								<img src="<%=path%>/img/table_right.gif" width="9" height="37">
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td class=td3>
					<html:form action="/findEmployeesListAC.do" styleClass="margin: 0"
						target="_self">
						<table border="0" width="95%" id="table1" cellspacing=1
							cellpadding=3 align="center">
							<tr>
								<td width="13%" class="td1">
									职工编号：
								</td>
								<td width="18%">
									<html:text property="id" styleClass="input3" onkeydown="formsubmit();"
										styleId="sss"></html:text>
								</td>
								<td width="13%" class="td1">
									职工姓名：
								</td>
								<td width="18%">
									<html:text property="name" styleClass="input3"
										onkeydown="formsubmit();" styleId="txtsearch"></html:text>
								</td>
							</tr>
							<tr>
								<td width="13%" class="td1">
									原职工编号
								</td>
								<td width="18%">
									<html:text property="oldId" styleClass="input3"
										onkeydown="formsubmit();" styleId="txtsearch"></html:text>
								</td>
								<td width="11%" class="td1">
									身份证号
								</td>
								<td width="21%">
									<html:text property="sfzh" styleClass="input3"
										onkeydown="formsubmit();" styleId="sfzhs"></html:text>
								</td>
							</tr>
							<tr>
								<td width="13%" class="td1">
									单位编号
								</td>
								<td width="18%">
									<html:text property="dwbh" styleClass="input3"
										onkeydown="formsubmit();" styleId="txtsearch"></html:text>
								</td>
								<td width="13%" class="td1">
									单位名称
								</td>
								<td width="18%">
									<html:text property="dwmc" styleClass="input3"
										onkeydown="formsubmit();" styleId="txtsearch"></html:text>
								</td>
							</tr>
						</table>
						<table width="95%" border="0" align="center" cellpadding="0"
							cellspacing="0">
							<tr>
								<td align="right">
									<html:submit styleClass="buttona">查询</html:submit>
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
										<td height="22" bgcolor="#CCCCCC" align="center" width="117">
											<b class="font14">职工列表 </b>
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
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr bgcolor="1BA5FF">
							<td align="center" height="6" colspan="1"></td>
						</tr>
					</table>
					<form name="idAF" action="" style="margin: 0">
						<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
							cellpadding="3" align="center">
							<tr align="center" bgcolor="C4F0FF">
								<td height="23" bgcolor="C4F0FF">
									&nbsp;
								</td>
								<td align="center" class=td2>
									职工编号
								</td>

								<td align="center" class=td2>
									职工姓名
								</td>
								<td align="center" class=td2>
									身份证号
								</td>
								<td align="center" class=td2>
									单位编号
								</td>
								<td align="center" class=td2>
									单位名称
								</td>
								<td align="center" class=td2>
									原职工编号
								</td>
								<td align="center" class=td2>
									职工状态
								</td>
							</tr>
							<logic:notEmpty name="employees">
								<%
										int j = 0;
										String strClass = "";
								%>
								<logic:iterate id="employees" name="employees" indexId="i">
									<%
												j++;
												if (j % 2 == 0) {
											strClass = "td8";
												} else {
											strClass = "td9";
												}
									%>
									<tr id="tr<%=i%>"
										onclick='gotoClickpp("<%=i%>", idAF);gettr("tr<%=i%>");'
										onMouseOver='this.style.background="#eaeaea"'
										onMouseOut='gotoColorpp("<%=i%>", idAF);'
										class="<%=strClass%>" onDblClick="qdValues1('<%=indexs%>');">
										<td valign="top">
											<p align="left">
												<input id="s<%=i%>" type="radio" name="id"
													value="<bean:write name="employees" property="empId"/>"
													<%if(new Integer(0).equals(i)) out.print("checked"); %>>
											</p>
										</td>

										<td valign="top">
											<p>
												<bean:write name="employees" property="empId"
													format="000000" />
											</p>
										</td>


										<td valign="top">
											<p>
												<bean:write name="employees" property="empInfo.name" />
											</p>
										</td>
										<td valign="top">
											<p>
												<bean:write name="employees" property="empInfo.cardNum" />
											</p>
										</td>
										<td valign="top">
											<p>
												<bean:write name="employees" property="org.id" format="0000000000"/>
											</p>
										</td>
										<td valign="top">
											<p>
												<bean:write name="employees" property="org.orgInfo.name" />
											</p>
										</td>
										<td valign="top">
											<p>
												<bean:write name="employees" property="oldEmpID"
													format="000000" />
											</p>
										</td>
										<td valign="top">
											<p>
												<bean:write name="employees" property="payStatusStr" />
											</p>
										</td>
									</tr>

								</logic:iterate>
							</logic:notEmpty>
							<logic:empty name="employees">
								<tr>
									<td colspan="11" height="30" style="color:red">
										没有找到与条件相符合的结果！
									</td>
								</tr>

							</logic:empty>
						</table>
					</form>

					<form name="form1">
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
												<jsp:include page="../../../../inc/pagination.jsp">
													<jsp:param name="url" value="showEmployeesListAC.do" />
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
											<td width="70">
												<input type="button" name="sure" value="确 定" class="buttona"
													onclick='qdValues1("<%=indexs%>");'>
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
