<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@page
	import="org.xpup.hafmis.sysloan.querystatistics.datareadyquery.empolderquery.action.EmpolderQueryWindowShowAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	Object pagination = session
			.getAttribute(EmpolderQueryWindowShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

	<title>开发商信息</title>
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script language="javascript" src="<%=path%>/js/common.js" />
  
</head>
<script language="javascript" src="js/common.js">
	var oldColor="#ffffff";                     //原来的颜色
	var newColor="#E8FCFD";                     //要变成的颜色
	function chgBGColor(oTD){
		oldColor=oTD.style.backgroundColor;		//保存当前颜色
		oTD.style.backgroundColor=newColor;		//改变表格颜色
		newColor=oldColor;                 		//改变下次要变成的颜色
	}
</script>
<script>
	function loads(){
		for(i=0;i<document.all.length;i++){//固定写法
			if(document.all.item(i).type=="submit"){//如果按钮是提交
	
				if(document.all.item(i).value=="添加"){
					s1=i;
				}
				if(document.all.item(i).value=="修改"){
					s2=i;
				}
				if(document.all.item(i).value=="作废"){
					s3=i;
				}
				if(document.all.item(i).value=="撤消作废"){
					s4=i;
				}
				if(document.all.item(i).value=="打印"){
					s5=i;
				}
				if(document.all.item(i).value=="返回"){
					s6=i;
				}
	
			}
		}
	}

	var tr="tr0"; 
	function gettr(trindex) {
	  tr=trindex;
	  update();
	}
	
	function update() {
	 
		if(document.getElementById(tr)!=null){
			 var status=document.getElementById(tr).childNodes[8].childNodes[0].innerHTML;
			if(status=='正常'){
			  	document.all.item(s4).disabled=true;
			  	document.all.item(s3).disabled=false;
			}else{
				document.all.item(s3).disabled=true;
				document.all.item(s4).disabled=false;
			}
		}
	}
	function oncanceled(){
	  var x=confirm("是否要进行作废操作！");
	  if(x){
		return true;
	  }else{
	    return false;
	  }
	}
	function oncanceledQuash(){
	  var x=confirm("是否要进行取消作废操作！");
	  if(x){
		return true;
	  }else{
	    return false;
	  }
	}
	function reportErrors() {
		<logic:messagesPresent>
			var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
			alert(message);
		</logic:messagesPresent>
	}
	function gotoSelect(id,form){
		document.getElementById("s"+id).checked="true";
	}
</script>
<body bgcolor="#FFFFFF" text="#000000" onload="reportErrors();"
	onContextmenu="return false">
	<html:form method="post" action="/empolderQueryWindowPrintAC">
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
										<td width="165" class=font14 bgcolor="#FFFFFF" align="center"
											valign="bottom">
											<font color="00B5DB">数据准备&gt;开发商维护</font>
										</td>
										<td width="98" class=font14>
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
										<td height="22" bgcolor="#CCCCCC" align="center" width="117">
											<b class="font14">开发商信息</b>
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
							<td width="19%" bgcolor="#FF0000" class="td1">
								开发商编号
							</td>
							<td width="31%">
								<html:text name="floorFindAF"
									property="floorDevelopInfoDTO.developerId" styleClass="input3"
									maxlength="50" readonly="true"></html:text>
							</td>
							<td width="17%" class="td1">
								开发商名称
							</td>
							<td width="33%">
								<html:text name="floorFindAF"
									property="floorDevelopInfoDTO.developerName"
									styleClass="input3" maxlength="50" readonly="true"></html:text>
							</td>
						</tr>
						<tr>
							<td width="19%" class="td1">
								国有土地使用权证编号
							</td>
							<td width="31%">
								<html:text name="floorFindAF"
									property="floorDevelopInfoDTO.landUseId" styleClass="input3"
									maxlength="50" readonly="true"></html:text>
							</td>
							<td width="17%" class="td1">
								商品房销售许可证号
							</td>
							<td width="33%">
								<html:text name="floorFindAF"
									property="floorDevelopInfoDTO.salePermis" styleClass="input3"
									maxlength="50" readonly="true"></html:text>
							</td>
						</tr>
						<tr>
							<td width="19%" class="td1">
								组织机构代码
							</td>
							<td width="31%">
								<html:text name="floorFindAF"
									property="floorDevelopInfoDTO.code" styleClass="input3"
									maxlength="50" readonly="true"></html:text>
							</td>
							<td width="17%" class="td1">
								所属办事处
							</td>
							<td width="33%">
								<html:text name="floorFindAF"
									property="floorDevelopInfoDTO.office" styleClass="input3"
									maxlength="50" readonly="true"></html:text>
							</td>
						</tr>
						<tr>
							<td width="19%" class="td1">
								协议签订日期
							</td>
							<td width="31%">
								<html:text name="floorFindAF"
									property="floorDevelopInfoDTO.agreementStartDate"
									styleClass="input3" maxlength="50" readonly="true"></html:text>
							</td>
							<td width="17%" class="td1">
								协议到期日期
							</td>
							<td width="33%">
								<html:text name="floorFindAF"
									property="floorDevelopInfoDTO.agreementEndDate"
									styleClass="input3" maxlength="50" readonly="true"></html:text>
							</td>
						</tr>
						<tr>
							<td width="19%" class="td1">
								开户银行
							</td>
							<td width="31%">
								<html:text name="floorFindAF"
									property="floorDevelopInfoDTO.developerPaybankA"
									styleClass="input3" maxlength="50" readonly="true"></html:text>
							</td>
							<td width="17%" class="td1">
								开户账号
							</td>
							<td width="33%">
								<html:text name="floorFindAF"
									property="floorDevelopInfoDTO.developerPaybankAAcc"
									styleClass="input3" maxlength="50" readonly="true"></html:text>
							</td>
						</tr>
						<tr>
							<td width="19%" class="td1">
								开发商地址
							</td>
							<td width="31%">
								<html:text name="floorFindAF"
									property="floorDevelopInfoDTO.developerAddr"
									styleClass="input3" maxlength="50" readonly="true"></html:text>
							</td>
							<td width="17%" class="td1">
								&nbsp;
							</td>
							<td width="33%">
								<input name="textfield3" type="text" id="txtsearch"
									class="input3" onKeyDown="enterNextFocus1();" readonly="true">
							</td>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="162">
											<b class="font14">新增楼盘信息列表</b>
										</td>
										<td height="22" background="<%=path%>/img/bg2.gif"
											align="center" width="742">
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
					<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
						cellpadding="3" align="center">
						<logic:notEmpty name="floorFindAF" property="list">

							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate id="floorListDTO" name="floorFindAF"
								property="list" indexId="i">
								<%
											j++;
											if (j % 2 == 0) {
												strClass = "td8";
											} else {
												strClass = "td9";
											}
								%>
								<logic:equal name="floorListDTO" property="floorFlag" value="1">
									<tr align="center" bgcolor="C4F0FF">

										<td align="center" class=td2 colspan="2">
											准许销售楼盘
										</td>
										<td align="center" class=td2 colspan="7">
											<bean:write name="floorListDTO" property="floorName" />
										</td>
									</tr>
								</logic:equal>
								<logic:equal name="floorListDTO" property="addressFlag"
									value="1">
									<tr align="center" bgcolor="C4F0FF">
										<td align="center" class=td2>
											<a href="javascript:sort('p6.floor_id')">楼盘号</a>
											<logic:equal name="pagination" property="orderBy"
												value="p6.floor_num">
												<logic:equal name="pagination" property="orderother"
													value="ASC">▲</logic:equal>
												<logic:equal name="pagination" property="orderother"
													value="DESC">▼</logic:equal>
											</logic:equal>
										</td>
										<td align="center" class=td2>
											所属地区
										</td>
										<td align="center" class=td2>
											项目投资总额
										</td>
										<td align="center" class=td2>
											项目完工程度(%)
										</td>
										<td align="center" class=td2>
											住宅价格（元/ M
											<sup>
												2
											</sup>
											）
										</td>
										<td align="center" class=td2>
											建筑面积（M
											<sup>
												2
											</sup>
											）
										</td>
										<td align="center" class=td2>
											是否拨款
										</td>
										<td align="center" class=td2>
											状态
										</td>
									</tr>
								</logic:equal>
								<logic:notEqual name="floorListDTO" property="floorFlag"
									value="1">
									<tr id="tr<%=i%>"
										class="<%=strClass%>">
										<td>
											<p>
												<bean:write name="floorListDTO" property="floorName" />
											</p>
										</td>
										<td>
											<p>
												<bean:write name="floorListDTO" property="region" />
											</p>
										</td>
										<td>
											<p>
												<bean:write name="floorListDTO" property="itemTotleAmnt" />
											</p>
										</td>
										<td>
											<p>
												<bean:write name="floorListDTO" property="itemFinishDegree" />
											</p>
										</td>
										<td>
											<p>
												<bean:write name="floorListDTO" property="housePrice" />
											</p>
										</td>
										<td>
											<p>
												<bean:write name="floorListDTO" property="buildingArea" />
											</p>
										</td>
										<td>
											<p>
												<bean:write name="floorListDTO" property="fundStatus" />
											</p>
										</td>
										<td>
											<p>
												<bean:write name="floorListDTO" property="floorSt" />
											</p>
										</td>
									</tr>
								</logic:notEqual>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="floorFindAF" property="list">
							<tr>
								<td colspan="11" height="30" style="color:red">
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
												<jsp:param name="url" value="empolderQueryWindowShowAC.do" />
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
									<logic:notEmpty name="floorFindAF" property="list">
										<tr>
											<td width="70">
												<html:submit property="method" styleClass="buttona">
													<bean:message key="button.print" />
												</html:submit>
											</td>
											<td width="70">
												<input type="button" name="Submit42" value="关闭"
													class="buttona" onClick="javascript:window.close();">
											</td>
										</tr>
									</logic:notEmpty>
									<logic:empty name="floorFindAF" property="list">
										<tr>
											<td width="70">
												<html:submit property="method" styleClass="buttona"
													disabled="true">
													<bean:message key="button.print" />
												</html:submit>
											</td>
											<td width="70">
												<input type="button" name="Submit42" value="关闭"
													class="buttona" onClick="javascript:window.close();">
											</td>
										</tr>
									</logic:empty>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</html:form>
</body>
</html:html>
