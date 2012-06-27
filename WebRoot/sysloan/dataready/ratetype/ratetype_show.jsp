<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page
	import="org.xpup.hafmis.sysloan.dataready.ratetype.action.RateTypeTaShowAC"%>
<%
	Object pagination = request.getSession(false).getAttribute(
			RateTypeTaShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String path = request.getContextPath();
%>
<html:html lang="true">
<head>
	<title>个贷管理</title>
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script language="javascript" src="<%=path%>/js/common.js">
	
	<script language="javascript" src="js/common.js">
	</script>
</head>
<script language="javascript" type="text/javascript">
var s1="";
var s2="";
var s3="";
var tr="tr0"; 
function loads(){
	var count = "<bean:write name="pagination" property="nrOfElements"/>";
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
		}
	}
	document.all.item(s1).disabled="";
	if(count==0){
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="true";
	}
	
}
function gettr(trindex) {
	tr=trindex;
}
function reportErrors() {
	<logic:messagesPresent>
		var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
		alert(message);
	</logic:messagesPresent>
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onload="loads();reportErrors();"
	>
	<jsp:include page="../../../inc/sort.jsp">
		<jsp:param name="url" value="rateTypeTaShowAC.do" />
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
						<td width="350" background="<%=path%>/img/table_bg_line.gif">
						<td background="<%=path%>/img/table_bg_line.gif" align="right">
							<table width="300" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="37">
										<img src="<%=path%>/img/title_banner.gif" width="37"
											height="24">
									</td>
									<td width="189" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">数据准备</font><font color="00B5DB">&gt;</font><font
											color="00B5DB">利率类型维护</font>
									</td>
									<td width="74" class=font14>
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
						<td width="9">
							<img src="<%=path%>/table_right.gif" width="9" height="37">
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td class=td3>
			<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td height="35"> 
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td height="22" bgcolor="#CCCCCC" align="center" width="117"><b class="font14">查 询 信 息</b></td>
                <td height="22" background="<%=path %>/img/bg2.gif" align="center">&nbsp;</td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
      <html:form  action="/rateTypeTaFindAC.do" >  
        <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=3 align="center" >
          <tr> 
            <td width="17%" class="td1"  algin="center">利率类型</td>
             <td width="22%" >
             	<html:text name="rateTypeShowAF" property="rateType" onkeydown="enterNextFocus1();"  
             		styleClass="input3"  styleId="txtsearch" >
             	</html:text> 
            </td>
            <td class="td1" width="17%" algin="center">利率说明</td>
            <td width="22%"  > 
              	<html:text name="rateTypeShowAF" property="rateExplain" onkeydown="enterNextFocus1();"  
              		styleClass="input3"  styleId="txtsearch" ></html:text>
            </td>
          </tr>
          <tr> 
            <td class="td1" width="17%" algin="center">利率时间</td>
            <td width="22%"  > 
              	<html:text name="rateTypeShowAF" property="rateDate" onkeydown="enterNextFocus1();" 
              		styleClass="input3"  styleId="txtsearch" ></html:text>
            </td>
            <td width="17%" algin="center"></td>
            <td align="right" >
              	<html:submit property="method" styleClass="buttona"><bean:message key="button.search"/></html:submit>
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
									<td height="22" bgcolor="#CCCCCC" align="center" width="180">
										<b class="font14">利率设置</b>
									</td>
									<td width="750" height="22" align="center"
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
				<html:form action="/rateTypeTaMaintainAC.do" style="margin: 0">
					<input type="hidden" name="appDate" value="" />
					<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1" cellpadding="3" align="center">
          <tr align="center" bgcolor="C4F0FF"> 
            <td height="23" bgcolor="C4F0FF" >&nbsp;</td>
							<td align="center" class=td2>
								<a href="javascript:sort('loanRateType.loanRateType')">利率类型</a>
								<logic:equal name="pagination" property="orderBy"
									value="loanRateType.loanRateType">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('loanRateType.loanRateExplain')">利率说明</a>
								<logic:equal name="pagination" property="orderBy"
									value="loanRateType.loanRateExplain">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('loanRateType.loanRateDate')">利率时间</a>
								<logic:equal name="pagination" property="orderBy"
									value="loanRateType.loanRateDate">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
						</tr>
						<logic:notEmpty name="rateTypeShowAF" property="list">
						<% int j=0;
			String strClass="";
		%>
							<logic:iterate name="rateTypeShowAF" property="list" id="element"
								indexId="i">
								<%j++;
			if (j%2==0) {strClass = "td8";
			}
		    else {strClass = "td9";
		    }
		%>
								
								<tr id="tr<%=i%>"
									onclick='gotoClickpp("<%=i %>", idAF);gettr("tr<%=i%>");' onMouseOver='this.style.background="#eaeaea"' onMouseOut='gotoColorpp("<%=i %>", idAF);' class="<%=strClass%>"
									onDblClick="">
									<td valign="top">
										<p align="left">
											<input id="s<%=i%>" type="radio" name="id"
												value="<bean:write name="element" property="id"/>"
												<%if(new Integer(0).equals(i)) out.print("checked"); %>>
										</p>
									</td>
									<td align="center">
										<p>
											<bean:write name="element" property="loanRateType" />
										</p>
									</td>
									<td align="center">
										<p>
											<bean:write name="element" property="loanRateExplain" />
										</p>
									</td>
									<td align="center">
										<p>
											<bean:write name="element" property="loanRateDate" />
										</p>
									</td>
								</tr>
								
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="rateTypeShowAF" property="list">
							<tr>
								<td colspan="4" height="30" style="color:red">
									没有找到与条件相符合的结果！
								</td>
							</tr>
							
						</logic:empty>
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
												<jsp:param name="url" value="rateTypeTaShowAC.do" />
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
											<html:submit property="method" styleClass="buttona">
												<bean:message key="button.add" />
											</html:submit>
										</td>
										<td width="70">
											<html:submit property="method" styleClass="buttona">
												<bean:message key="button.update" />
											</html:submit>
										</td>
										<td width="70">
											<html:submit property="method" styleClass="buttona">
												<bean:message key="button.delete" />
											</html:submit>
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
</html:html>
