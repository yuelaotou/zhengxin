<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@page
	import="org.xpup.hafmis.sysloan.querystatistics.datareadyquery.empolderquery.action.EmpolderQueryShowAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	Object pagination = session
			.getAttribute(EmpolderQueryShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>开发商查询</title>
		<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
		<script type="text/javascript" src="<%=path%>/js/picture.js"></script>
		<script language="javascript" src="<%=path%>/js/common.js" />
  
	
	
	
	
	
	
	</head>
	<script language="javascript" src="<%=path%>/js/common.js">
	var oldColor="#ffffff";                     //原来的颜色
	var newColor="#E8FCFD";                     //要变成的颜色
	function chgBGColor(oTD){
		oldColor=oTD.style.backgroundColor;		//保存当前颜色
		oTD.style.backgroundColor=newColor;		//改变表格颜色
		newColor=oldColor;                 		//改变下次要变成的颜色
	}
	
</script>
	<script>
// 选择完开发商之后取出楼盘下拉
function executeAjaxIn(){
	/*var developerId = document.developFindAF.elements["buyhouseorgid"].value.trim();
	var url = "empolderQueryFindFloorAAC.do?developerId="+developerId;
	findInfo_ws(url);*/
}
function findInfo_ws(url) {
 	createXMLHttpRequest();  
    xmlHttp.onreadystatechange = handleStateChange_ws;
    xmlHttp.open("GET", url, true);
    xmlHttp.send(null);   
}

function handleStateChange_ws() {
  	if(xmlHttp.readyState == 4) {
      	if(xmlHttp.status == 200) {
        	var xmlDoc = xmlHttp.responseXML;
			var values = xmlDoc.getElementsByTagName("value");
			var texts  = xmlDoc.getElementsByTagName("text");
			var selectObj = document.getElementById("floorList");
			selectObj.length = 0;
			for ( i=0; i < values.length; i++ ) {
				var childOption = new Option(texts[i].firstChild.data,values[i].firstChild.data);
				selectObj.options[selectObj.length++] = childOption;
				if(i==0){
					document.all.floorNum.value=values[i].firstChild.data;
				}
			}
      	}
   	}
}
function findorghouses(){
  	window.open("<%=path%>/sysloan/develepershowAC.do?indexs="+5+"&objInput=flag_ws"+"&qx=no","window","height=450,width=700,top="+(window.screen.availHeight-450)/2+",left="+(window.screen.availWidth-700)/2+",scrollbars=yes, status=no"); 
}

</script>
	<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false">
		<jsp:include page="../../../../inc/sort.jsp">
			<jsp:param name="url" value="empolderQueryShowAC.do" />
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
							<td background="<%=path%>/img/table_bg_line.gif" width="55">
								&nbsp;
							</td>
							<td width="235" background="<%=path%>/img/table_bg_line.gif">
								&nbsp;
							</td>
							<td background="<%=path%>/img/table_bg_line.gif" align="right">
								<table width="292" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="37">
											<img src="<%=path%>/img/title_banner.gif" width="37"
												height="24">
										</td>
										<td width="261" class=font14 bgcolor="#FFFFFF" align="center"
											valign="bottom">
											<font color="00B5DB">数据准备信息查询&gt;开发商查询</font>
										</td>
										<td width="10" class=font14>
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
					<html:form action="/empolderQueryFindAC">
						<table width="95%" border="0" cellspacing="0" cellpadding="0"
							align="center">
							<tr>
								<td height="35">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td height="22" bgcolor="#CCCCCC" align="center" width="165">
												<b class="font14">查 询 条 件</b>
											</td>
											<td height="22" background="<%=path%>/img/bg2.gif"
												align="center" width="693">
												&nbsp;
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
						<table border="0" width="95%" id="table1" cellspacing=1
							cellpadding=0 align="center">
							<tr>
								<td width="17%" class="td1">
									开发商编号
								</td>
								<td width="33%" colspan="3">
									<html:text name="developFindAF"
										property="developTbFindDTO.developerId" styleClass="input3"
										onkeydown="enterNextFocus1();" maxlength="100"></html:text>
								</td>
								<td width="17%" class="td1">
									开发商名称
								</td>
								<td width="30%" colspan="2">
									<html:text property="developTbFindDTO.developerName"
										name="developFindAF" styleClass="input3" readonly="true"
										onkeydown="enterNextFocus1();" />
								</td>
								<td width="3%">
									<input type="button" name="submit12" class="buttona"
										value="..." onclick="findorghouses();"
										onkeydown="enterNextFocus1();" />
									<html:hidden property="buyhouseorgid" name="developFindAF" />
								</td>

							</tr>
							<tr>
								<td width="17%" class="td1">
									楼盘
								</td>
								<td width="33%" colspan="3">
									<html:text name="developFindAF" property="floorName"
										styleClass="input3" onkeydown="enterNextFocus1();">
									</html:text>
								</td>
								<td width="17%" class="td1">
									组织机构代码
								</td>
								<td width="33%" colspan="3">
									<html:text name="developFindAF"
										property="developTbFindDTO.code" styleClass="input3"
										onkeydown="enterNextFocus1();" maxlength="100"></html:text>
								</td>
							</tr>
							<tr>
								<td width="17%" class="td1">
									协议签订日期
								</td>
								<td width="15%">
									<html:text name="developFindAF"
										property="developTbFindDTO.startAgreementStartDate"
										onkeydown="enterNextFocus1();" styleClass="input3"
										maxlength="8"></html:text>
								</td>
								<td width="3%">
									至
								</td>
								<td width="15%">
									<html:text name="developFindAF"
										property="developTbFindDTO.endAgreementStartDate"
										onkeydown="enterNextFocus1();" styleClass="input3"
										maxlength="8"></html:text>
								</td>
								<td width="17%" class="td1">
									协议到期日期
								</td>
								<td width="15%">
									<html:text name="developFindAF"
										property="developTbFindDTO.startAgreementEndDate"
										onkeydown="enterNextFocus1();" styleClass="input3"
										maxlength="8"></html:text>
								</td>
								<td width="3%">
									至
								</td>
								<td width="15%">
									<html:text name="developFindAF"
										property="developTbFindDTO.endAgreementEndDate"
										onkeydown="enterNextFocus1();" styleClass="input3"
										maxlength="8"></html:text>
								</td>
							</tr>
							<tr>
								<td width="17%" class="td1">
									所属办事处
								</td>
								<td width="33%" colspan="3">
									<html:select name="developFindAF"
										property="developTbFindDTO.office" styleClass="input4"
										onkeydown="enterNextFocus1();">
										<html:option value="">全部</html:option>
										<html:options collection="officeList" property="value"
											labelProperty="label" />
									</html:select>
								</td>
								<td width="17%" class="td1">
									状态
								</td>
								<td width="33%" colspan="3">
									<html:select name="developFindAF"
										property="developTbFindDTO.developerSt" styleClass="input4"
										onkeydown="enterNextFocus1();">
										<html:option value=""></html:option>
										<html:optionsCollection property="developerStMap"
											name="developFindAF" label="value" value="key" />
									</html:select>
								</td>
							</tr>
							<tr>
								<td width="17%" class="td1">
									联系人
								</td>
								<td width="33%" colspan="3">
									<html:text name="developFindAF"
										property="developTbFindDTO.contactPrsnA" styleClass="input3"
										onkeydown="enterNextFocus1();" maxlength="100"></html:text>
								</td>
								<td width="17%" class="td1">
									法人代表
								</td>
								<td width="33%" colspan="3">
									<html:text name="developFindAF"
										property="developTbFindDTO.artfclprsn" styleClass="input3"
										onkeydown="enterNextFocus1();" maxlength="100"></html:text>
								</td>
							</tr>
							<tr>
								<td width="17%" class="td1">
									开户行
								</td>
								<td width="33%" colspan="3">
									<html:text name="developFindAF"
										property="developTbFindDTO.paybank" styleClass="input3"
										onkeydown="enterNextFocus1();" maxlength="100"></html:text>
								</td>
								<td width="17%" class="td1">
									开户行账号
								</td>
								<td width="33%" colspan="3">
									<html:text name="developFindAF"
										property="developTbFindDTO.paybankacc" styleClass="input3"
										onkeydown="enterNextFocus1();" maxlength="100"></html:text>
								</td>
							</tr>
						</table>
						<table width="95%" border="0" align="center" cellpadding="0"
							cellspacing="0">
							<tr>
								<td align="right">
									<html:submit property="method" styleClass="buttona">
										<bean:message key="button.search" />
									</html:submit>
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
										<td height="22" bgcolor="#CCCCCC" align="center" width="165">
											<b class="font14">开发商信息列表 </b>
										</td>
										<td height="22" background="<%=path%>/img/bg2.gif"
											align="center" width="693">
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
					<html:form action="/empolderQueryPrintAC">
						<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
							cellpadding="3" align="center">
							<tr align="center" bgcolor="C4F0FF">
								<td height="23" bgcolor="C4F0FF">
									&nbsp;
								</td>
								<td align="center" class=td2>
									<a href="javascript:sort('p5.developer_code')">开发商编号</a>
									<logic:equal name="pagination" property="orderBy"
										value="p5.developer_code">
										<logic:equal name="pagination" property="orderother"
											value="ASC">▲</logic:equal>
										<logic:equal name="pagination" property="orderother"
											value="DESC">▼</logic:equal>
									</logic:equal>
								</td>
								<td align="center" class=td2>
									<a href="javascript:sort('p5.developer_name')">开发商名称</a>
									<logic:equal name="pagination" property="orderBy"
										value="p5.developer_name">
										<logic:equal name="pagination" property="orderother"
											value="ASC">▲</logic:equal>
										<logic:equal name="pagination" property="orderother"
											value="DESC">▼</logic:equal>
									</logic:equal>
								</td>
								<td align="center" class=td2>
									总楼盘 数
									<br>
								</td>
								<td align="center" class=td2>
									总楼栋数
								</td>
								<td align="center" class=td2>
									<a href="javascript:sort('p5.office')">所属办事处</a>
									<logic:equal name="pagination" property="orderBy"
										value="p5.office">
										<logic:equal name="pagination" property="orderother"
											value="ASC">▲</logic:equal>
										<logic:equal name="pagination" property="orderother"
											value="DESC">▼</logic:equal>
									</logic:equal>
								</td>
								<td align="center" class=td2>
									法人代表
								</td>
								<td align="center" class=td2>
									开户行A
								</td>
								<td align="center" class=td2>
									开户行B
								</td>
								<td align="center" class=td2>
									状态
								</td>
								<td align="center" class=td2>
									开发商协议查看
								</td>
							</tr>
							<logic:notEmpty name="developFindAF" property="list">
								<%
											int j = 0;
											String strClass = "";
								%>
								<logic:iterate id="developTbListDTO" name="developFindAF"
									property="list" indexId="i">
									<%
											j++;
											if (j % 2 == 0) {
												strClass = "td8";
											} else {
												strClass = "td9";
											}
									%>
									<tr id="tr<%=i%>" onclick='gotoClickpp("<%=i%>", idAF);'
										onMouseOver='this.style.background="#eaeaea"'
										onMouseOut='gotoColorpp("<%=i%>", idAF);'
										class="<%=strClass%>" onDblClick="">
										<td>
											<input id="s<%=i%>" type="radio" name="id"
												value="<bean:write name="developTbListDTO" property="id"/>"
												<%if(new Integer(0).equals(i)) out.print("checked"); %>>
										</td>
										<td>
											<p>
												<bean:write name="developTbListDTO" property="developerId" />
											</p>
										</td>
										<td>
											<p>
												<a href="#"
													onClick="window.open('empolderQueryWindowForwardAC.do?id=<bean:write name="developTbListDTO" property="id"/>','','width=800,height=600,top='+(window.screen.availHeight-600)/2+',left='+(window.screen.availWidth-800)/2+',scrollbars=yes');">
													<bean:write name="developTbListDTO"
														property="developerName" /> </a>
											</p>
										</td>
										<td>
											<p>
												<bean:write name="developTbListDTO" property="sumFloor" />
											</p>
										</td>
										<td>
											<p>
												<bean:write name="developTbListDTO" property="sumFloorNum" />
											</p>
										</td>
										<td>
											<p>
												<bean:write name="developTbListDTO" property="office" />
											</p>
										</td>
										<td>
											<p>
												<bean:write name="developTbListDTO" property="artfclprsn" />
											</p>
										</td>
										<td>
											<p>
												<bean:write name="developTbListDTO"
													property="developerPaybankA" />
											</p>
										</td>
										<td>
											<p>
												<bean:write name="developTbListDTO"
													property="developerPaybankB" />
											</p>
										</td>
										<td>
											<p>
												<bean:write name="developTbListDTO" property="developerSt" />
											</p>
										</td>
										<td>
											<a
												href='javascript:excHz("<bean:write name="developTbListDTO" property="photoUrl"/>");'><img
													src="<%=path%>/img/lookinfo.gif" width="37" height="24">
											</a>
										</td>
									</tr>

								</logic:iterate>
							</logic:notEmpty>
							<logic:empty name="developFindAF" property="list">
								<tr>
									<td colspan="12" height="30" style="color:red">
										没有找到与条件相符合的结果！
									</td>
								</tr>

							</logic:empty>
						</table>
						<table width="95%" border="0" cellspacing="0" cellpadding="3"
							align="center">
							<tr class="td1">
								<td align="center">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr align="center">
											<td align="left">
												共
												<bean:write name="pagination" property="nrOfElements" />
												项
											</td>
											<td align="right">
												<jsp:include page="../../../../inc/pagination.jsp">
													<jsp:param name="url" value="empolderQueryShowAC.do" />
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
												<logic:notEmpty name="developFindAF" property="list">
													<html:submit property="method" styleClass="buttona">
														<bean:message key="button.print" />
													</html:submit>
												</logic:notEmpty>
												<logic:empty name="developFindAF" property="list">
													<html:submit property="method" styleClass="buttona"
														disabled="true">
														<bean:message key="button.print" />
													</html:submit>
												</logic:empty>
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
