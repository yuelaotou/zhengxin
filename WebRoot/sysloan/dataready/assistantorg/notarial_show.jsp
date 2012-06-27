<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="org.xpup.hafmis.sysloan.dataready.assistantorg.action.NotarialShowAC"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>
<%
		String path = request.getContextPath();
%>
<%
	Object pagination = request.getSession(false).getAttribute(
	NotarialShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
 <head>
	<title>公证处维护</title>
</head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css" />
<script src="<%=path%>/js/common.js"></script>
<script language="javascript" src="js/common.js">
var oldColor="#ffffff";                            //原来的颜色
var newColor="#E8FCFD";                     //要变成的颜色
function chgBGColor(oTD){
	oldColor=oTD.style.backgroundColor;//保存当前颜色
	oTD.style.backgroundColor=newColor;//改变表格颜色
	newColor=oldColor;                 //改变下次要变成的颜色
}
</script>
<script>
  function reportErrors(){
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
	}
</script>
<script>
function ondelete(){
  var x=confirm("确定删除此记录?");
  if(x){
	return true;
  }else{
    return false;
  }
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onLoad="reportErrors();" onContextmenu="return false">
<html:form action="/notarialMaintainAC">
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
									<td width="234" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<a href="#" class=a1>数据准备</a><font color="00B5DB">&gt;</font><a
											href="#" class=a1>公证处维护</a>
									</td>
									<td width="29" class=font14>
										&nbsp;
									</td>
              </tr>
            </table>
          </td>
          <td width="9"><img src="<%=path%>/img/table_right.gif" width="9" height="37"></td>
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
                <td height="22" bgcolor="#CCCCCC" align="center" width="167"><b class="font14">公证处信息列表</b></td>
                <td width="737" height="22" align="center" background="<%=path%>/img/bg2.gif">&nbsp;</td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
     <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr bgcolor="1BA5FF" > 
          <td align="center" height="6" colspan="1" ></td>
        </tr>
      </table> 
      <table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1" cellpadding="3" align="center">
          <tr align="center" bgcolor="C4F0FF"> 
            <td height="23" bgcolor="C4F0FF" >&nbsp;</td>
            <td align="center" class=td2>
			<a href="javascript:sort('assistantOrg.assistantOrgName')">公证处名称</a>
          	<logic:equal name="pagination" property="orderBy" value="assistantOrg.assistantOrgName">
          	  <logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
          	  <logic:equal name="pagination" property="orderother" value="DESC">▼</logic:equal>
          	</logic:equal>
			</td>
            <td align="center" class=td2 > 公证处地址</td>
            <td align="center" class=td2>
            
            <!-- 张列改 头 -->
			<a href="javascript:sort('assistantOrg.arear')">所属地区</a>
          	<logic:equal name="pagination" property="orderBy" value="assistantOrg.arear">
          	<!-- 张列改 尾 -->
          	
          	  <logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
          	  <logic:equal name="pagination" property="orderother" value="DESC">▼</logic:equal>
          	</logic:equal>
			</td>
            <td align="center" class=td2 > 开户银行 </td>
            <td align="center" class=td2 >联系人</td>
            <td align="center" class=td2 > 联系电话</td>
          </tr>
          <logic:notEmpty name="ListAF" property="list">
          <% int j=0;
			String strClass="";
		%>
		  <logic:iterate name="ListAF" property="list" id="assorgAF" indexId="i">
		  <%j++;
			if (j%2==0) {strClass = "td8";
			}
		    else {strClass = "td9";
		    }
		%>
		  
          <tr id="tr<%=i%>" 
onclick='gotoClickpp("<%=i %>", idAF);' onMouseOver='this.style.background="#eaeaea"' onMouseOut='gotoColorpp("<%=i %>", idAF);' class="<%=strClass%>" onDblClick="">      
            <td valign="top"><p align="left">
			<input id="s<%=i%>" type="radio" name="id"
			value="<bean:write name="assorgAF" property="assistantOrgId"/>"
			<%if(new Integer(0).equals(i)) out.print("checked"); %>></p>
			</td>
			<td><bean:write name="assorgAF" property="assistantOrgName"/></td>
            <td><bean:write name="assorgAF" property="assistantOrgAddr"/></td>
            <td><bean:write name="assorgAF" property="arear"/></td>
            <td><bean:write name="assorgAF" property="paybank"/></td>
            <td><bean:write name="assorgAF" property="contactPrsn"/></td>
            <td><bean:write name="assorgAF" property="contactTel"/></td>
          </tr>
          
        </logic:iterate>
        </logic:notEmpty>
      </table>
        <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
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
											<jsp:param name="url" value="notarialShowAC.do" />
										</jsp:include>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
		<logic:empty name="ListAF" property="list">
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr valign="bottom"> 
          <td  colspan="7" bgcolor="#FFFFFF" align="center" height="30"> 
            <table border="0" cellspacing="0" cellpadding="0">
              <tr> 
              <td width="10">
				<td>
				<html:submit property="method" styleClass="buttona">
				<bean:message key="button.add"/>
				</html:submit>
				</td>
				<td width="10">
                <td>
				<html:submit property="method" styleClass="buttona" disabled="true">
				<bean:message key="button.update"/>
				</html:submit>
				</td>
				<td width="10">
                <td>
				<html:submit property="method" styleClass="buttona" disabled="true" onclick="return ondelete();">
				<bean:message key="button.delete"/>
				</html:submit>
				</td>
              </tr>
            </table>  
    </td>
  </tr>
</table>
</logic:empty>
<logic:notEmpty name="ListAF" property="list">
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr valign="bottom"> 
          <td  colspan="7" bgcolor="#FFFFFF" align="center" height="30"> 
            <table border="0" cellspacing="0" cellpadding="0">
              <tr> 
              <td width="10">
				<td>
				<html:submit property="method" styleClass="buttona">
				<bean:message key="button.add"/>
				</html:submit>
				</td>
				<td width="10">
                <td>
				<html:submit property="method" styleClass="buttona">
				<bean:message key="button.update"/>
				</html:submit>
				</td>
				<td width="10">
                <td>
				<html:submit property="method" styleClass="buttona" onclick="return ondelete();">
				<bean:message key="button.delete"/>
				</html:submit>
				</td>
              </tr>
        </table>
    </td>
  </tr>
</table>
</logic:notEmpty>
</table>
</html:form>
</body>
</html:html>