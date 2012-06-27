<%@ page contentType="text/html; charset=UTF-8" import="java.util.List"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ include file="/checkUrl.jsp"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ page
	import="org.xpup.hafmis.sysloan.querystatistics.datareadyquery.assistantorg.action.AssistantorgQueryShowAC"%>
<%
	String path = request.getContextPath();
	Object pagination = request.getSession().getAttribute(
			AssistantorgQueryShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>统计查询</title>
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
</head>
<script type="text/javascript" src="<%=path%>/js/picture.js"></script>
<script src="<%=path%>/js/common.js"></script>

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
						<td background="<%=path%>/img/table_bg_line.gif" width="56">
							&nbsp;
						</td>
						<td background="<%=path%>/img/table_bg_line.gif" width="154">
							<a href="特殊业务处理_五级分类修改_简约风格.html"></a>
						</td>
						<td background="<%=path%>/img/table_bg_line.gif" align="right"
							width="725">
							<table width="300" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="37">
										<img src="<%=path%>/img/title_banner.gif" width="37"
											height="24">
									</td>
									<td class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">数据准备信息查询&gt;代理机构查询</font>
									</td>
									<td class=font14>
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
						<td width="10">
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
									<td height="22" bgcolor="#CCCCCC" align="center" width="187">
										<b class="font14">查 询 条 件</b>
									</td>
									<td width="672" height="22" align="center"
										background="<%=path%>/img/bg2.gif">
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<html:form action="/assistantorgQueryFindAC.do"  style="margin: 0">
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=0 align="center">
						<tr>
							<td width="13%" class="td1">
								机构名称
							</td>
							<td width="18%">
								<html:text name="assistantorgAF" property="assistantOrgName"
									styleClass="input3" onkeydown="enterNextFocus1();"/>
							</td>
							<td width="11%" class="td1">
								法人代表
							</td>
							<td width="21%">
								<html:text name="assistantorgAF" property="artfclprsn"
									styleClass="input3"  onkeydown="enterNextFocus1();"/>
							</td>
						</tr>
						<tr>
							<td width="13%" class="td1">
								机构类型
							</td>
							<td width="5%">
								<html:select name="assistantorgAF" property="assistantType"
									styleClass="input4" onkeydown="enterNextFocus1();">
									<html:option value=""></html:option>
									<html:optionsCollection property="assistantMap"
										name="assistantorgAF" label="value" value="key" />
								</html:select>
							</td>
							<td width="4%" class="td1">
								所属地区
							</td>
							<td width="9%">
							<html:select name="assistantorgAF" property="inArea"
									styleClass="input4" onkeydown="enterNextFocus1();">
									<html:option value=""></html:option>
									<html:optionsCollection property="inAreaMap"
										name="assistantorgAF" label="value" value="key" />
								</html:select>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td align="right">
								<html:submit styleClass="buttona">
									<bean:message key="button.search" />
								</html:submit>
							</td>
						</tr>
					</table>
				</html:form>
				<html:form action="/assistantorgPrintAC.do"  style="margin: 0">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="189">
											<b class="font14">代理机构信息列表</b>
										</td>
										<td width="670" height="22" align="center"
											background="<%=path%>/img/bg2.gif">
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
								<a href="javascript:sort('assistantorgtype')">机构类型</a>
								<logic:equal name="pagination" property="orderBy"
									value="assistantorgtype">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('assistantorgname')">机构名称</a>
								<logic:equal name="pagination" property="orderBy"
									value="assistantorgname">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								单位地址
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('arear')">所属地区</a>
								<logic:equal name="pagination" property="orderBy" value="arear">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								开户银行
							</td>
							<td align="center" class=td2>
								联系人
							</td>
							<td align="center" class=td2>
								联系电话
							</td>
							<td align="center" class=td2>
								查看扫描信息
							</td>
						</tr>
						<logic:notEmpty name="assistantorgAF" property="list">
						<% int j=0;
			String strClass="";
		%>
							<logic:iterate name="assistantorgAF" property="list" id="element"
								indexId="i">
								<%j++;
			if (j%2==0) {strClass = "td8";
			}
		    else {strClass = "td9";
		    }
		%>
								<tr id="tr<%=i%>"
									onclick='gotoClickpp("<%=i %>", idAF);' onMouseOver='this.style.background="#eaeaea"' onMouseOut='gotoColorpp("<%=i %>", idAF);' class="<%=strClass%>"
									onDblClick="">
									<td valign="top">
										<p align="left">
											<input id="s<%=i%>" type="radio" name="id"
												value="<bean:write name="element" property="id"/>"
												<%if(new Integer(0).equals(i)) out.print("checked"); %>>
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="assistantOrgType"
												format="000000" />
										</p>
									</td>
									<td valign="top">
										<p>
											<a href="#"
												onclick="window.open('<%=path%>/sysloan/assistantOrgInfoShowAC.do?id=<bean:write name="element" property="id" />','window','height=600,width=1000,top='+(window.screen.availHeight-600)/2+',left='+(window.screen.availWidth-1000)/2+',scrollbars=yes,status=yes');"><bean:write
													name="element" property="assistantOrgName" /> </a>                                                            
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="assistantOrgAddr" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="arear" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="paybank" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="contactPrsn" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="element" property="contactTel" />
										</p>
									</td>
									<td valign="top">
										<p>
											<a href='javascript:excHz("<bean:write name="element" property="photoUrl"/>");'>
												<img src="<%=path%>/img/lookinfo.gif" width="37" height="24">
											</a>	
										</p>
									</td>
								</tr>
								
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="assistantorgAF" property="list">
							<tr>
								<td colspan="11" height="30" style="color:red">
									没有找到与条件相符合的结果！
								</td>
							</tr>
							
						</logic:empty>
						<tr>
							<td colspan="9"></td>
						</tr>
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
											<jsp:include page="../../../../inc/pagination.jsp">
												<jsp:param name="url" value="assistantorgQueryShowAC.do" />
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
									<logic:empty name="assistantorgAF" property="list">

										<td width="69" align="right">
											<html:submit property="method" styleClass="buttona"
												disabled="true">
												<bean:message key="button.print" />
											</html:submit>
										</td>

									</logic:empty>
									<logic:notEmpty name="assistantorgAF" property="list">

										<td width="69" align="right">
											<html:submit property="method" styleClass="buttona"
												onclick="return onprint();">
												<bean:message key="button.print" />
											</html:submit>
										</td>

									</logic:notEmpty>
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

