<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ page
	import="org.xpup.hafmis.sysloan.loanapply.loanapply.action.DeveleperShowAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
			Object pagination = session
			.getAttribute(DeveleperShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String path = request.getContextPath();
	String indexs = (String) session.getAttribute("indexs");
	String objInput = (String) request.getSession().getAttribute(
			"objInput");
%>
<html>
	<head>
		<title>购房信息</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
		<script src="<%=path%>/js/common.js"></script>
		<script type="text/javascript">

	var tr="tr0"; 
	function gettr(trindex) {
	  tr=trindex;
	}
	
	//个人贷款确定时调用
function qdValues1(indexs){
	var a;
	var b=document.getElementsByName("id");
	var c=document.getElementById(tr).childNodes[5].innerHTML.trim();
	var objInput;
  	var str="<%=objInput%>";
  	
  	if(str=="flag_ws"){
  		eval("objInput=window.opener.document.forms[1].elements['developTbFindDTO.developerName']");
  	}else if(str=="specialapply"){
  		eval("objInput=window.opener.document.all.developerName");
  	}else{
  		eval("objInput=window.opener.document.all."+str);
  	}
	for(i=0;i<b.length;i++)   
    {
    	if(b[i].checked==true){
    		a=b[i].value;
    	}
  	}
	if(""+a!="undefined"){
		//window.opener.document.all.code.value=format(a)+a;
		//window.opener.document.forms[0].elements["orgPaymentAgreement.organization.id"].value=format(a)+a;
		if(str=="flag_ws"){
			window.opener.document.forms[1].elements["buyhouseorgid"].value=c;
		}else if(str=="specialapply"){
			window.opener.document.forms[0].elements["headId"].value=c;
		}else{
			window.opener.document.forms[0].elements["buyhouseorgid"].value=c;
		}
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
  		}else{
  			objInput.value=a;
  			opener.executeAjaxIn();
  		}
		
	}else{
		opener.clears();
	}
	window.close();
}
</script>

	</head>

	<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false">
		<jsp:include page="../../../inc/sort.jsp">
			<jsp:param name="url" value="develepershowAC.do" />
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
					<html:form action="/develeperfindAC.do" style="margin: 0">
						<table border="0" width="95%" id="table1" cellspacing=1
							cellpadding=3 align="center">
							<tr>
								<td width="13%" class="td1">
									开发商名称：
								</td>
								<td width="18%">
									<html:text name="develepernewAF" property="develpername"
										styleClass="input3" styleId="txtsearch"></html:text>
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
											<b class="font14">售房单位列表 </b>
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
									<a href="javascript:sort('p5.developer_id')">开发商编号</a>
									<logic:equal name="pagination" property="orderBy"
										value="p5.developer_id">
										<logic:equal name="pagination" property="orderother"
											value="ASC">▲</logic:equal>
										<logic:equal name="pagination" property="orderother"
											value="DESC">▼</logic:equal>
									</logic:equal>
									
								</td>
								<td align="center" class=td2>
									开发商名称
								</td>
								<td align="center" class=td2>
									法人代表
								</td>
								<td align="center" class=td2>
									法人代表联系电话
								</td>

							</tr>
							<logic:notEmpty name="develepernewAF" property="list">
								<%
										int j = 0;
										String strClass = "";
								%>
								<logic:iterate id="elments" name="develepernewAF"
									property="list" indexId="i">
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
													value="<bean:write name="elments" property="developername"/>"
													<%if(new Integer(0).equals(i)) out.print("checked"); %>>
											</p>
										</td>

										<td valign="top">
											<p>
												<bean:write name="elments" property="developerid"
													format="000000" />
											</p>
										</td>
										<td valign="top">
											<p>
												<bean:write name="elments" property="developername"
													format="000000" />
											</p>
										</td>
										<td valign="top">
											<p>
												<bean:write name="elments" property="artfclprsn" />
											</p>
										</td>
										<td valign="top">
											<p>
												<bean:write name="elments" property="artfclprsntel"
													format="000000" />
											</p>
										</td>
										<td style="display:none">
											<bean:write name="elments" property="pid" />
										</td>


									</tr>

								</logic:iterate>
							</logic:notEmpty>
							<logic:empty name="develepernewAF" property="list">
								<tr>
									<td colspan="11" height="30" style="color:red">
										没有找到与条件相符合结果！
									</td>
								</tr>

							</logic:empty>
						</table>
					</form>
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
												<jsp:param name="url" value="develepershowAC.do" />
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
											<input type="button" value="确 定" class="buttona"
												onclick='qdValues1("<%=indexs%>");'>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>

	</body>
</html>
