<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page
	import="org.xpup.hafmis.syscollection.accounthandle.orgveraccountbalance.action.*"%>
	
<%
	String path = request.getContextPath();
	Object pagination = request.getSession(false).getAttribute(
			OrgVerAccountBalanceShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html lang="true">
<head>
	<title>余额结转</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
</head>
<script language="javascript" src="<%=path%>/js/common.js"></script>

<script>
function loads(){
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
}

function executeAjax() {
   var queryString = "orgVerAccountBalanceFindAAC.do?";  
   var id = document.orgVerAccountBalanceAF.elements["orgId"].value;
   if(id==""){
   	gotoOrg('2','0');
   }
   queryString = queryString + "orgId="+id; 	   
   findInfo(queryString);
}

function gotoEnter(){
	if(event.keyCode==13){
		event.keyCode = 9;
		executeAjax();
	}
}

function gotoOrg(status,indexs){
	gotoOrgpop(status,'<%=path%>',indexs);
}

function displays(orgId, orgName){
  document.orgVerAccountBalanceAF.elements["orgId"].value=orgId;
  document.orgVerAccountBalanceAF.elements["orgName"].value=orgName;
  showlist(); 
}

function showlist() {
  document.Form1.submit();
}

function gotoSure(){
	if(confirm("是否确认进行余额结转？")){
		return true;
  	}else {
  		return false;
  	}	
}
</script>

<body bgcolor="#FFFFFF" text="#000000" onload="loads();" onContextmenu="return false">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="7"><img src="<%=path%>/img/table_left.gif" width="7" height="37"></td>
          <td background="<%=path%>/img/table_bg_line.gif" width="55">&nbsp;</td>
            <td background="<%=path%>/img/table_bg_line.gif"></td>
          <td background="<%=path%>/img/table_bg_line.gif" align="right"><table width="300" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="37"><img src="<%=path%>/img/title_banner.gif" width="37" height="24"></td>
              <td width="184" align="center" valign="bottom" bgcolor="#FFFFFF" ><font color="#00B5DB" class="font14">余额结转</font></td>
              <td width="79" class=font14>&nbsp;</td>
            </tr>
          </table></td>
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
                  <td height="22" bgcolor="#CCCCCC" align="center" width="118"><b class="font14">余 额 结 转</b></td>
                  <td height="22" background="<%=path%>/img/bg2.gif" align="center" width="549">&nbsp;</td>
                </tr>
              </table>
            </td>
          </tr>
        </table>
        <html:form action="/orgVerAccountBalanceFindAC" style="margin: 0">
		<table border="0" width="95%" id="table1" cellspacing=1  cellpadding=0 align="center" >
          <tr> 
            <td width="17%"   class="td1">单位编号</td>
            <td width="23%"   > 
            	<html:text name="orgVerAccountBalanceAF" property="orgId" ondblclick="return executeAjax();" onkeydown="gotoEnter();" styleClass="input3"  styleId="txtsearch"/>
            </td>
            <td width="10%" align="center"  > 
            	<input type="button" name="Submit4" value="..." class="buttona" onclick="gotoOrg('2','0');" onkeydown="gotoEnter();">
            </td>
            <td width="17%" class="td1">单位名称</td>
            <td width="33%" >
				<html:text name="orgVerAccountBalanceAF" property="orgName" styleClass="input3" styleId="txtsearch" readonly="true"/>
		    </td>
          </tr>
          <tr> 
            <td width="17%"   class="td1">结转年度</td>
            <td width="33%"  colspan="2">
				<html:select name="orgVerAccountBalanceAF" property="accYear" styleClass="input3" onchange="showlist();">
					<html:options collection="accYearList" property="value"
						labelProperty="label" />
				</html:select>
            </td>
            <td width="17%" >&nbsp;</td>
            <td width="33%" >&nbsp;</td>
          </tr>
        </table>
		<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
          <tr> 
            <td height="35"> 
              <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr> 
                  <td height="22" bgcolor="#CCCCCC" align="center" width="118"><b class="font14">查 询 条 件</b></td>
                  <td height="22" background="<%=path%>/img/bg2.gif" align="center" width="549">&nbsp;</td>
                </tr>
              </table>
            </td>
          </tr>
        </table>
		<table border="0" width="95%" id="table1" cellspacing=1  cellpadding=0 align="center" >
          <tr> 
            <td width="17%" class="td1">职工编号</td>
            <td width="33%">
				<html:text name="orgVerAccountBalanceAF" property="empId" styleClass="input3" styleId="txtsearch" onkeydown="enterNextFocus1();"/>
			</td>
            <td width="17%" class="td1">职工姓名</td>
            <td width="33%">
            	<html:text name="orgVerAccountBalanceAF" property="empName" styleClass="input3" styleId="txtsearch" onkeydown="enterNextFocus1();"/>
            </td>
          </tr>
        </table>
        <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr> 
            <td width="100%" align="right">
				<html:submit property="method" styleClass="buttona">
					<bean:message key="button.search" />
				</html:submit>
			</td>
          </tr>
        </table>
       </html:form>
	<html:form action="/orgVerAccountBalanceMaintainAC" style="margin: 0">
      <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td height="35"> 
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td height="22" bgcolor="#CCCCCC" align="center" width="193"><b class="font14">余 额 结 转 列 表</b></td>
                <td width="407" height="22" align="center" background="<%=path%>/img/bg2.gif">&nbsp;</td>
              </tr>
            </table>          </td>
        </tr>
      </table>
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr bgcolor="1BA5FF" >  
          <td align="center" height="6" colspan="1" ></td>
        </tr>
      </table>
      
        <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">    
          <tr align="center" >
            <td width="5%" class="td2">&nbsp;</td>
            <td width="15%" class="td2">职工编号</td>
            <td width="15%" class="td2">职工姓名</td>
            <td width="15%" class="td2">&#20013;&#24515;&#32467;&#24687;&#21069;&#24448;&#24180;&#20313;&#39069;</td>
            <td width="15%" class="td2">单位往年余额</td>
            <td width="15%" class="td2">&#20013;&#24515;&#32467;&#24687;&#21069;&#26412;&#24180;&#20313;&#39069;</td>
            <td width="15%" class="td2">单位本年余额</td>
          </tr>
          
       		<logic:notEmpty name="orgVerAccountBalanceAF" property="list">
				<logic:iterate name="orgVerAccountBalanceAF" property="list"
					id="element" indexId="i">
					<tr id="tr<%=i%>"
						onclick='gotoClick("tr<%=i%>","s<%=i%>",idAF);'
						onMouseOver='this.style.background="#eaeaea"'
						onMouseOut='gotoColor("tr<%=i%>","s<%=i%>", idAF);' class=td4
						onDblClick="">
						<td valign="top">
							<p align="left">
								<input id="s<%=i%>" type="radio" name="id"
									value="<bean:write name="element" property="id"/>"
									<%if(new Integer(0).equals(i)) out.print("checked"); %>>
							</p>
						</td>
						<td>
							<bean:write name="element" property="empId" />
						</td>
						<td>
							<bean:write name="element" property="empName" />
						</td>
						<td>
							<bean:write name="element" property="preBalanceCen" />
						</td>
						<td>
							<bean:write name="element" property="preBalanceOrg" />
						</td>
						<td>
							<bean:write name="element" property="curBalanceCen" />
						</td>
						<td>
							<bean:write name="element" property="curBalanceOrg" />
						</td>
					</tr>
					<tr>
						<td colspan="7" class=td5></td>
					</tr>
				</logic:iterate>
			</logic:notEmpty>
			<logic:empty name="orgVerAccountBalanceAF" property="list">
				<tr>
					<td colspan="4" height="30" style="color:red">
						没有找到与条件相符合的结果！
					</td>
				</tr>
				<tr>
					<td colspan="7"></td>
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
									<jsp:param name="url" value="orgVerAccountBalanceShowAC.do" />
								</jsp:include>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
					
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr valign="bottom"> 
          <td  colspan="7" bgcolor="#FFFFFF" align="center" height="30"> 
            <table border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td>
				<table border="0" cellpadding="0" cellspacing="0">
                <tr>
                  <td width="58">
                    <logic:notEmpty name="orgVerAccountBalanceAF" property="list">
                  		<html:submit property="method" styleClass="buttona" onclick="return gotoSure();">
							<bean:message key="button.carryforword.balance" />
						</html:submit>
					</logic:notEmpty>
					<logic:empty name="orgVerAccountBalanceAF" property="list">
                  		<html:submit property="method" styleClass="buttona" disabled="true" onclick="return gotoSure();">
							<bean:message key="button.carryforword.balance" />
						</html:submit>
					</logic:empty>
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
    </td>
  </tr>
</table>
			<form action="orgVerAccountBalanceShowAC.do" method="POST" name="Form1" id="Form1">
			</form>
</body>
</html:html>

