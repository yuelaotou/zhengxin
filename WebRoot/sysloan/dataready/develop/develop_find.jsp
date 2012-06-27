<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>
<%@page
	import="org.xpup.hafmis.sysloan.dataready.develop.action.DevelopTbShowAC"%>
<%
	String path = request.getContextPath();
	Object pagination = session
			.getAttribute(DevelopTbShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

	<title>开发商维护</title>
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script src="<%=path%>/js/common.js">
</script>
	<script type="text/javascript" src="<%=path%>/js/picture.js"></script>
</head>
<script language="javascript">
	var oldColor="#ffffff";                     //原来的颜色
	var newColor="#E8FCFD";                     //要变成的颜色
	function chgBGColor(oTD){
		oldColor=oTD.style.backgroundColor;		//保存当前颜色
		oTD.style.backgroundColor=newColor;		//改变表格颜色
		newColor=oldColor;                 		//改变下次要变成的颜色
	}
	
</script>
<script>
	var s1,s2,s3,s4,s5,s6,s7,s8;
	function loads(){
		for(i=0;i<document.all.length;i++){//固定写法
			if(document.all.item(i).type=="submit"){//如果按钮是提交
				if(document.all.item(i).value=="新增"){
					s2=i;
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
				if(document.all.item(i).value=="楼盘"){
					s5=i;
				}
				if(document.all.item(i).value=="楼盘"){
					s6=i;
				}
				if(document.all.item(i).value=="打印"){
					s7=i;
				}
				if(document.all.item(i).value=="扫描"){
					s8=i;
				}
			}
		}
	}
	function reportErrors() {
		<logic:messagesPresent>
			var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
			alert(message);
		</logic:messagesPresent>
	}
	var tr="tr0"; 
	
	function gettr(trindex) {
	  	tr=trindex;
	  	update();
	}
	
	function update() {
		if(document.getElementById(tr)!=null){
			var status=document.getElementById(tr).childNodes[9].childNodes[0].innerHTML.trim();
			if(status=='正常'){
				document.all.item(s3).disabled=false;
			  	document.all.item(s4).disabled=true;
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
	function checkDelete(){
		return confirm("是否删除此记录?");
	}
	
</script>
<body bgcolor="#FFFFFF" text="#000000"
	onload="reportErrors();loads();update();" onContextmenu="return false">
	<jsp:include page="../../../inc/sort.jsp">
		<jsp:param name="url" value="developTbShowAC.do" />
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
							<table border="0" cellspacing="0" cellpadding="0">
								<tr>
									<%-- 
                  <td width="112" height="37" background="<%=path%>/img/buttong.gif" align="center" valign="top"  style="PADDING-top: 7px"><a href="developTaForwardAC.do" class=a2>添加开发商</a></td>
                --%>
									<td width="112" height="37"
										background="<%=path%>/img/buttonbl.gif" align="center"
										style="PADDING-top: 7px" valign="top">
										开发商维护
									</td>
								</tr>
							</table>
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
				<html:form method="post" action="/developTbFindAC">
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
							<td width="33%" colspan="3">
								<html:text name="developFindAF"
									property="developTbFindDTO.developerName" styleClass="input3"
									onkeydown="enterNextFocus1();" maxlength="100"></html:text>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								组织机构代码
							</td>
							<td width="33%" colspan="3">
								<html:text name="developFindAF" property="developTbFindDTO.code"
									styleClass="input3" onkeydown="enterNextFocus1();"
									maxlength="100"></html:text>
							</td>
							<td width="17%" class="td1">
								楼盘名称
							</td>
							<td width="33%" colspan="3">
								<html:text name="developFindAF"
									property="developTbFindDTO.floorName" styleClass="input3"
									onkeydown="enterNextFocus1();" maxlength="100"></html:text>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								开户行
							</td>
							<td width="33%" colspan="3">
								<html:text name="developFindAF"
									property="developTbFindDTO.developerPaybankA"
									styleClass="input3" onkeydown="enterNextFocus1();"
									maxlength="100"></html:text>
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
								协议签订日期
							</td>
							<td width="15%">
								<html:text name="developFindAF"
									property="developTbFindDTO.startAgreementStartDate"
									onkeydown="enterNextFocus1();" styleClass="input3"
									maxlength="8"></html:text>
							</td>
							<td width="4%">
								至
							</td>
							<td width="14%">
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
							<td width="4%">
								至
							</td>
							<td width="14%">
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
									<html:option value="all">全部</html:option>
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
								特殊开发商
							</td>
							<td colspan="3">
								<html:select property="developTbFindDTO.isSpecial" name="developFindAF"
									styleClass="input3" onkeydown="enterNextFocus1();">
									<html:option value=""></html:option>
									<html:option value="0">是</html:option>
									<html:option value="1">否</html:option>
								</html:select>
							</td>
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
				<html:form action="/developTbMainTainAC">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="117">
											<b class="font14">开 发 商 列 表 </b>
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
								总楼盘数
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
								浏览文件
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
								<tr id="tr<%=i%>"
									onclick='gotoClickpp("<%=i%>", idAF);gettr("tr<%=i%>");'
									onMouseOver='this.style.background="#eaeaea"'
									onMouseOut='gotoColorpp("<%=i%>", idAF);' class="<%=strClass%>"
									onDblClick="">
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
												onClick="window.open('developTbWindowAC.do?id=<bean:write name="developTbListDTO" property="id"/>','','width=800,height=600,top='+(window.screen.availHeight-600)/2+',left='+(window.screen.availWidth-800)/2+',scrollbars=yes');">
												<bean:write name="developTbListDTO" property="developerName" />
											</a>
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
											<bean:write name="developTbListDTO" property="developerPaybankA" />
										</p>
									</td>
									<td>
										<p>
											<bean:write name="developTbListDTO" property="developerPaybankB" />
										</p>
									</td>
									<td>
										<p>
											<bean:write name="developTbListDTO" property="developerSt" />
										</p>
									</td>
									<td>
										<p>
											<a
												href='javascript:excHz("<bean:write name="developTbListDTO" property="photoUrl"/>");'><img
													src="<%=path%>/img/lookinfo.gif" width="37" height="24">
											</a>
										</p>
									</td>
								</tr>

							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="developFindAF" property="list">
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
											<jsp:include page="../../../inc/pagination.jsp">
												<jsp:param name="url" value="developTbShowAC.do" />
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
									<logic:notEmpty name="developFindAF" property="list">
										<tr>
											<td width="70">
												<html:submit property="method" styleClass="buttona">
													<bean:message key="button.develop.add" />
												</html:submit>
											</td>
											<td width="70">
												<html:submit property="method" styleClass="buttona">
													<bean:message key="button.update" />
												</html:submit>
											</td>
											<td width="70">
												<html:submit property="method" styleClass="buttona"
													onclick="return oncanceled();">
													<bean:message key="button.canceled" />
												</html:submit>
											</td>
											<td width="70">
												<html:submit property="method" styleClass="buttona"
													onclick="return oncanceledQuash();">
													<bean:message key="button.canceled.quash" />
												</html:submit>
											</td>
											<td width="70">
												<html:submit property="method" styleClass="buttona"
													onclick="return checkDelete();">
													<bean:message key="button.delete" />
												</html:submit>
											</td>
											<td width="70">
												<html:submit property="method" styleClass="buttona">
													<bean:message key="button.floor" />
												</html:submit>
											</td>
											<td width="70">
												<html:submit property="method" styleClass="buttona">
													<bean:message key="button.print" />
												</html:submit>
											</td>
											<td width="70">
												<html:submit property="method" styleClass="buttona">
													<bean:message key="button.scan" />
												</html:submit>
											</td>
										</tr>
									</logic:notEmpty>
									<logic:empty name="developFindAF" property="list">
										<tr>
											<td width="70">
												<html:submit property="method" styleClass="buttona">
													<bean:message key="button.develop.add" />
												</html:submit>
											</td>
											<td width="70">
												<html:submit property="method" styleClass="buttona"
													disabled="true">
													<bean:message key="button.update" />
												</html:submit>
											</td>
											<td width="70">
												<html:submit property="method" styleClass="buttona"
													onclick="return oncanceled();" disabled="true">
													<bean:message key="button.canceled" />
												</html:submit>
											</td>
											<td width="70">
												<html:submit property="method" styleClass="buttona"
													onclick="return oncanceledQuash();" disabled="true">
													<bean:message key="button.canceled.quash" />
												</html:submit>
											</td>
											<td width="70">
												<html:submit property="method" styleClass="buttona"
													disabled="true">
													<bean:message key="button.delete" />
												</html:submit>
											</td>
											<td width="70">
												<html:submit property="method" styleClass="buttona"
													disabled="true">
													<bean:message key="button.floor" />
												</html:submit>
											</td>
											<td width="70">
												<html:submit property="method" styleClass="buttona"
													disabled="true">
													<bean:message key="button.print" />
												</html:submit>
											</td>
											<td width="70">
												<html:submit property="method" styleClass="buttona"
													disabled="true">
													<bean:message key="button.scan" />
												</html:submit>
											</td>
										</tr>

									</logic:empty>
								</table>
							</td>
						</tr>
					</table>
				</html:form>
			</td>
		</tr>
	</table>
</body>
</html:html>
