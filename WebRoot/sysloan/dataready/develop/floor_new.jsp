<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

	<title>新增楼盘信息</title>
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
	function reportErrors() {
		<logic:messagesPresent>
			var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
			alert(message);
		</logic:messagesPresent>
	}
	function check(){
		var floorName = document.forms[0].elements["developProject.floorName"].value.trim();
		var floorNum = document.forms[0].elements["developProject.floorNum"].value.trim();
		var itemTotleAmnt = document.forms[0].elements["developProject.itemTotleAmnt"].value.trim();
		var itemFinishDegree = document.forms[0].elements["developProject.itemFinishDegree"].value.trim();
		var housePrice = document.forms[0].elements["developProject.housePrice"].value.trim();
		
		if(floorName==null || floorName==""){
			alert("请输入准许销售楼盘！");
			return false;
		}else if(floorNum==null || floorNum==""){
			alert("请输入楼盘号并使用英文半角\",\"将楼盘号隔开！");
			return false;
		}else if(!checkMoney(itemTotleAmnt)){
			return false;
		}else if(!checkMoney(housePrice)){
			return false;
		}else if(isNaN(itemFinishDegree) || itemFinishDegree<0 || itemFinishDegree>100){
			alert("请输入正确的项目完成度！格式如：98.2");
			return false;
		}else{
			var type = document.forms[0].elements["type"].value
			if(type==null||type==''){
				document.getElementById("method").value="saveFloorInfo";
			}else{
				document.getElementById("method").value="modifyFloorInfo";
			}
		}

	}
	function checkback(){
		document.forms[0].elements["developProject.itemTotleAmnt"].value=0.00;
		document.forms[0].elements["developProject.housePrice"].value=0.00;
		
		document.getElementById("method").value="backFloorInfoList";
	}
	
</script>
<body bgcolor="#FFFFFF" text="#000000" onload="reportErrors();"
	onContextmenu="return false">
	<html:form method="post" action="/floorSaveAC">
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
										<td height="22" bgcolor="#CCCCCC" align="center" width="147">
											<b class="font14">新增楼盘信息</b>
										</td>
										<td height="22" background="<%=path%>/img/bg2.gif"
											align="center" width="757">
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
								准许销售楼盘
								<font color="#FF0000">*</font>
								<br>
							</td>
							<td width="33%">
								<html:text name="floorNewAF" property="developProject.floorName"
									styleClass="input3" maxlength="100"
									onkeydown="enterNextFocus1();"></html:text>
							</td>
							<td width="17%" class="td1">
								楼盘号
								<font color="#FF0000">*</font>
							</td>
							<td width="33%" align="center">
								<html:text name="floorNewAF" property="developProject.floorNum"
									styleClass="input3" maxlength="100"
									onkeydown="enterNextFocus1();"></html:text>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								楼盘地址
								<font color="#FF0000">*</font>
								<br>
							</td>
							<td width="33%">
								<html:text name="floorNewAF" property="developProject.floorAddr"
									styleClass="input3" maxlength="100"
									onkeydown="enterNextFocus1();"></html:text>
							</td>
							<td width="17%" class="td1">
								所属地区
							</td>
							<td width="33%" align="center">
								<html:text name="floorNewAF" property="developProject.region"
									styleClass="input3" onkeydown="enterNextFocus1();"></html:text>
								<!-- 
								<html:select name="floorNewAF" property="developProject.region"
									styleClass="input4" onkeydown="enterNextFocus1();">
									<html:option value=""></html:option>
									<html:optionsCollection property="regionMap" name="floorNewAF"
										label="value" value="key" />
								</html:select>
 								-->
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								项目投资总额(元)
							</td>
							<td width="33%">
								<html:text name="floorNewAF"
									property="developProject.itemTotleAmnt" styleClass="input3"
									maxlength="15" onkeydown="enterNextFocus1();"></html:text>
							</td>
							<td width="17%" class="td1">
								项目完工程度(%)
							</td>
							<td width="33%">
								<html:text name="floorNewAF"
									property="developProject.itemFinishDegree" styleClass="input3"
									maxlength="100" onkeydown="enterNextFocus1();"></html:text>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								住宅价格/ M
								<sup>
									2
								</sup>
								(元)
							</td>
							<td width="33%">
								<html:text name="floorNewAF"
									property="developProject.housePrice" styleClass="input3"
									maxlength="10" onkeydown="enterNextFocus1();"></html:text>
							</td>
							<td class="td1" width="17%">
								建筑面积（M
								<sup>
									2
								</sup>
								）
							</td>
							<td width="33%">
								<html:text name="floorNewAF"
									property="developProject.buildingAreas" styleClass="input3"
									onkeydown="enterNextFocus1();"></html:text>
								<html:hidden name="floorNewAF" property="type" />
							</td>
						</tr>
						<tr>
							<td class="td1" width="17%">
								是否拨款
							</td>
							<td width="33%" align="center">
								<html:select name="floorNewAF"
									property="developProject.fundStatus" styleClass="input4"
									onkeydown="enterNextFocus1();">
									<html:option value="1">是</html:option>
									<html:option value="0">否</html:option>
								</html:select>
							</td>
							<td width="17%" class="td1">
								国有土地使用权证编号
								<br>
							</td>
							<td width="33%">
								<html:text name="floorFindAF"
									property="floorDevelopInfoDTO.landUseId" styleClass="input3"
									maxlength="100" onkeydown="enterNextFocus1();" readonly="true"></html:text>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								商品房销售许可证号
							</td>
							<td width="33%" align="center">
								<html:text name="floorFindAF"
									property="floorDevelopInfoDTO.salePermis" styleClass="input3"
									maxlength="100" onkeydown="enterNextFocus1();" readonly="true"></html:text>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr valign="bottom">
							<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
										<input type="hidden" name="method" id="method" value="" />
										<logic:empty name="floorNewAF" property="type">
											<td width="70">
												<html:submit property="method" styleClass="buttona"
													onclick="return check();">
													<bean:message key="button.sure" />
												</html:submit>
											</td>
										</logic:empty>
										<logic:notEmpty name="floorNewAF" property="type">
											<td width="70">
												<html:submit property="method" styleClass="buttona"
													onclick="return check();">
													<bean:message key="button.confirm" />
												</html:submit>
											</td>
										</logic:notEmpty>
										<td width="70">
											<html:submit property="method" styleClass="buttona"
												onclick="checkback();">
												<bean:message key="button.back" />
											</html:submit>
										</td>
									</tr>
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
