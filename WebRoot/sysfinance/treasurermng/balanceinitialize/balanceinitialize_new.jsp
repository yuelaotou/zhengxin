<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ page import="java.util.List"%>
<%@ page import="org.xpup.hafmis.sysfinance.treasurermng.balanceinitialize.dto.BalanceinitializeDTO"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	List subjectInfos = (List)request.getAttribute("subjectInfos");
	int lendsMoneyNumber = 0;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
	  <head>
	    <title>余额初始</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	  </head>
	  <script src="<%=path%>/js/common.js"></script>
	  
	  <script>
	  	function reportErrors(){
		  	<logic:messagesPresent>
			var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
			alert(message);
			</logic:messagesPresent>
		}
  	  </script>
  		<script>
  			function verdictOffice(){
				var officecode=document.all.officeName.value.trim();
				if(officecode != ""){
					document.URL=('balanceinitializeShowAC.do?officecode='+officecode+'&status=1');
				}else{
					document.URL=('balanceinitializeShowAC.do?officecode='+officecode);
				}
			}
  		</script>
  		
  
  <body bgcolor="#FFFFFF" text="#000000"  onLoad="reportErrors();" onContextmenu="return false">
	<html:form action="/balanceinitializeNewAC.do">
		<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
		  <tr>
		    <td>
		      <table width="100%" border="0" cellspacing="0" cellpadding="0">
		        <tr>
		          <td width="7"><img src="<%=path%>/img/table_left.gif" width="7" height="37"></td>
		          <td background="<%=path%>/img/table_bg_line.gif" width="55">&nbsp;</td>
		            <td background="<%=path%>/img/table_bg_line.gif"><a href="账户处理_损益结转_简约风格.htm"> 
		              </a></td>
		          <td background="<%=path%>/img/table_bg_line.gif" align="right"> 
		            <table width="300" border="0" cellspacing="0" cellpadding="0">
		              <tr> 
		                <td width="37"><img src="<%=path%>/img/title_banner.gif" width="37" height="24"></td>
		                  <td width="163" class=font14 bgcolor="#FFFFFF" align="center" valign="bottom"><font color="00B5DB">出纳管理&gt;余额初始</font></td>
		                <td width="100" class=font14>&nbsp;</td>
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
		          <td height="35"><table width="100%" border="0" cellspacing="0" cellpadding="0">
		          <tr align="center"> 
		            <td colspan="8" class="td1" align="left" >
						<table width="100%" border="0" cellspacing="3" cellpadding="0" >
							<logic:notEmpty name="officeList1">
								<tr>
									<td class=td1 width="9%">所属办事处<font color="#FF0000">*</font></td>
									<td class="td4" width="31%">
										<html:select property="officeName" styleClass="input4" name="balanceinitializeAF" onchange="verdictOffice();">
		          						<option value=""></option>
		          						<html:options  collection="officeList1" property="value" labelProperty="label"/>
		          						</html:select>
	          						</td>
									<td width="8%">&nbsp;</td>
								  	<td class="td4" width="10%">&nbsp;</td>
									<td width="12%" >&nbsp;</td>
									<td class="td4" width="30%">&nbsp;</td>
								</tr>
							</logic:notEmpty>
					</table>			</td>
		          </tr>
		            <tr>
		                  <td height="22" bgcolor="#CCCCCC" align="center" width="172"><b class="font14">余 额 初 始 设 置</b></td>
		                  <td width="702" height="22" align="center" background="<%=path%>/img/bg2.gif">&nbsp;</td>
		            </tr>
		          </table></td>
		        </tr>
		      </table>
		      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
		        <tr bgcolor="1BA5FF" > 
		          <td align="center" height="6" colspan="1" ></td>
		        </tr>
		      </table>
		        <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
		          <tr align="center" > 
		            <td class="td1" width="16%">科目</td>
		            <td class="td1" width="16%">科目名称</td>
		            <td class="td2" width="46%"> 启用期初余额</td>
		          </tr>
		          
		          <logic:empty name="balanceinitializeBT">
		          <logic:notEmpty name="subjectInfos">
		          <logic:iterate id="subjectInfo" name="subjectInfos">
			          <tr id="tr1"  valign="middle" > 
			            <td width="16%"><%out.print(((BalanceinitializeDTO)subjectInfos.get(lendsMoneyNumber)).getSubjectCode());%></td>
			            <td width="16%"><%out.print(((BalanceinitializeDTO)subjectInfos.get(lendsMoneyNumber)).getSubjectName());%></td>
			            <input type="hidden" name="subjectCode<%=lendsMoneyNumber %>" value="<%out.print(((BalanceinitializeDTO)subjectInfos.get(lendsMoneyNumber)).getSubjectCode());%>" />
			            <input type="hidden" name="subjectName<%=lendsMoneyNumber %>" value="<%out.print(((BalanceinitializeDTO)subjectInfos.get(lendsMoneyNumber)).getSubjectName());%>" />
			            <td width="46%"> 
			              <input type="text" name="debit<%=lendsMoneyNumber %>" class="input5" value="<%out.print(((BalanceinitializeDTO)subjectInfos.get(lendsMoneyNumber)).getDebit());%>" onkeydown="enterNextFocus1();"/>
			            </td>
			          </tr>
			          <tr> 
           				 <td colspan="3" class=td5></td>
          			  </tr>
			          <%lendsMoneyNumber++; %>
		          </logic:iterate>
		          </logic:notEmpty>
		          </logic:empty>
		          
		          <logic:notEmpty name="balanceinitializeBT">
		          <logic:notEmpty name="subjectInfos">
		          <logic:iterate id="subjectInfo" name="subjectInfos">
			          <tr id="tr1"  valign="middle" > 
			            <td width="16%"><%out.print(((BalanceinitializeDTO)subjectInfos.get(lendsMoneyNumber)).getSubjectCode());%></td>
			            <td width="16%"><%out.print(((BalanceinitializeDTO)subjectInfos.get(lendsMoneyNumber)).getSubjectName());%></td>
			            <input type="hidden" name="subjectCode<%=lendsMoneyNumber %>" value="<%out.print(((BalanceinitializeDTO)subjectInfos.get(lendsMoneyNumber)).getSubjectCode());%>" />
			            <input type="hidden" name="subjectName<%=lendsMoneyNumber %>" value="<%out.print(((BalanceinitializeDTO)subjectInfos.get(lendsMoneyNumber)).getSubjectName());%>" />
			            <td width="46%"> 
			              <input type="text" name="debit<%=lendsMoneyNumber %>" class="input5" value="<%out.print(((BalanceinitializeDTO)subjectInfos.get(lendsMoneyNumber)).getDebit());%>" onkeydown="enterNextFocus1();" readonly="readonly"/>
			            </td>
			          </tr>
			          <tr> 
           				 <td colspan="3" class=td5></td>
          			  </tr>
			          <%lendsMoneyNumber++; %>
		          </logic:iterate>
		          </logic:notEmpty>
		          </logic:notEmpty>
		          
		          <input type="hidden" name="lendsMoneySize" value="<%=lendsMoneyNumber %>" />
		        </table>
		      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
		        <tr class="td1"> 
		          <td align="center">&nbsp;</td>
		        </tr>
		      </table>
		      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
		        <tr valign="bottom"> 
		          <td  colspan="7" bgcolor="#FFFFFF" align="center" height="30"> 
		              <table border="0" cellspacing="0" cellpadding="0">
		                <tr>
		                  <td width="69" align="right">
		                  	<logic:empty name="balanceinitializeBT">
		                  		<html:submit property="method" styleClass="buttona"><bean:message key="button.sure"/></html:submit>
		                  	</logic:empty>
		                    <logic:notEmpty name="balanceinitializeBT">
		                    	<html:submit property="method" styleClass="buttona" disabled="true"><bean:message key="button.sure"/></html:submit>
		                    </logic:notEmpty>
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