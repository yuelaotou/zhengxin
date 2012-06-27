<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ include file="/checkUrl.jsp"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>

<%
	String path = request.getContextPath();
%>
<%@ page import="org.xpup.hafmis.sysfinance.bookmng.settlemodle.action.SettlemodleShowAC" %>

<%
   Object pagination= request.getSession(false).getAttribute(SettlemodleShowAC.PAGINATION_KEY);
   request.setAttribute("pagination",pagination);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
<title>结算方式</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="<%=path %>/css/index.css" type="text/css">

</head>
<script language="javascript" src="<%=path %>/js/common.js"></script>

		<script type="text/javascript">
				function reportErrors() {
					document.forms[0].elements["paramExplain"].focus();
					<logic:messagesPresent>
					var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
					alert(message);
					</logic:messagesPresent>
					}
		</script>
		<script>
			function checkNumber(){
				var paramExplain = document.forms[0].elements["paramExplain"].value.trim();
				if(paramExplain = null ||paramExplain == ""){
					alert("结算方式不能为空!");
					return false;
				}
				return true;
			}
		</script>
		 <script type="text/javascript">
			function checksave(){
				if(checkNumber()){
					document.getElementById("method").value="save";
					document.forms[0].submit();
				}else{
					document.forms[0].elements["paramExplain"].focus();
					return false;
				}
			}
			
			function checkupdate(){
				if(checkNumber()){
					document.getElementById("method").value="update";
					document.forms[0].submit();
				}else{
					document.forms[0].elements["paramExplain"].focus();
					return false;
				}
			}
			
			function checkDelete(){
				return confirm("是否删除此记录?");
			}
			
			function findEmployeeInfo(){
				var method1 = document.getElementById("method1").value;
				if(method1 == "save"){
					checksave();
				}else{
					checkupdate();
				}
			}
   		 </script>
  <body bgcolor="#FFFFFF" text="#000000"  onload="reportErrors();" onContextmenu="return false">
	<html:form action="/settlemodleNewAC.do">
		<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
		  <tr>
		    <td>
		      <table width="100%" border="0" cellspacing="0" cellpadding="0">
		        <tr>
		          <td width="7"><img src="<%=path%>/img/table_left.gif" width="7" height="37"></td>
		          <td background="<%=path%>/img/table_bg_line.gif" width="55">&nbsp;</td>
		            <td width="235" background="<%=path%>/img/table_bg_line.gif"> 
		            </td>
		          <td background="<%=path%>/img/table_bg_line.gif" align="right"> 
		            <table width="300" border="0" cellspacing="0" cellpadding="0">
		              <tr> 
		                <td width="37"><img src="<%=path%>/img/title_banner.gif" width="37" height="24"></td>
		                  <td width="189" class=font14 bgcolor="#FFFFFF" align="center" valign="bottom"> 
		                    <p><font color="00B5DB">账套管理&gt;结算方式</font></p>
		                  </td>
		                <td width="74" class=font14>&nbsp;</td>
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
		                  <td height="22" bgcolor="#CCCCCC" align="center" width="132"><b class="font14">新 增 结 算 方 式</b></td>
		                  <td height="22" background="<%=path%>/img/bg2.gif" align="center" width="726">&nbsp;</td>
		                </tr>
		              </table>
		            </td>
		          </tr>
		        </table>
		        <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=0 align="center" >
		          <tr> 
		            <td width="15%"   class="td1">结算方式<font color="#FF0000">*</font></td>
		            <html:hidden name="settlemodleNewAF" property="paraId"/>
		            <td width="85%" > 
		            	<html:text name="settlemodleNewAF" property="paramExplain"  styleClass="input3" styleId="txtsearch"  onkeydown="goEnter();"></html:text>
		            </td>
		          </tr>
		        </table>
		        <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
		         <input type="hidden" name="method" id="method" value="" />
		          <logic:empty name="updateInfo">
				                <tr>                  
				                	<td  align="center"> 
				                    	<html:button property="method" styleClass="buttona"  onclick="return checksave();"><bean:message key="button.sure" /></html:button>
				                  		<input type="hidden" name="method1" id="method1" value="save" />
				                  	</td>
				                </tr>
				                </logic:empty>
				                <logic:notEmpty name="updateInfo">
				                	<tr>                  
				                	<td  align="center"> 
				                    	<html:button property="method" styleClass="buttona"  onclick="return checkupdate();"><bean:message key="button.sure" /></html:button>
				                    	<input type="hidden" name="method1" id="method1" value="update" />
				                  	</td>
				                </tr>
				  </logic:notEmpty>
		        </table>
		        </html:form>
		        <html:form action="/settlemodleMainTainAC.do">
				        <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
		          <tr> 
		            <td height="35">
		              <table width="100%" border="0" cellspacing="0" cellpadding="0">
		                <tr> 
		                  <td height="22" bgcolor="#CCCCCC" align="center" width="131"><b class="font14">结 算 方 式 列 表</b></td>
		                  <td width="727" height="22" align="center" background="<%=path%>/img/bg2.gif">&nbsp;</td>
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
		          <tr align="center" > 
		            <td height="23" bgcolor="C4F0FF" >&nbsp;</td>
		            <td class="td2">结算方式</td>
		          </tr>
		            <logic:notEmpty name="settlemodleNewAF" property="list">
            			<%
		          		int j=0;
		          		String strClass="";
		          		%>
						<logic:iterate name="settlemodleNewAF" property="list" id="element" indexId="i">
						<%	j++;
						if (j%2==0) {strClass = "td8";
						}
					    else {strClass = "td9";
					    }%>
				        <tr id="tr<%=i%>" onClick='gotoClickpp("<%=i%>", idAF);' 
					        onMouseOver='this.style.background="#eaeaea"'  
					        onMouseOut='gotoColorpp("<%=i %>", idAF);' 
					        class="<%=strClass %>"> 
				           <td valign="top" >
				          <p align="center">
				            <input id="s<%=i %>" type="radio" name="id" value="<bean:write name="element" property="paraId"/>" <%if(new Integer(0).equals(i)) out.print("checked"); %> />
				          </p>
				          </td>
				          <td valign="top" align="center"><p><bean:write name="element" property="paramExplain" /></p></td>
				        </tr>
				        </logic:iterate>
			        </logic:notEmpty>
			        <logic:empty name="settlemodleNewAF"  property="list">
				        <tr> 
				          <td colspan="11" height="30" style="color:red">没有找到与条件相符合的结果！</td>
					    </tr>
				    </logic:empty>
		        </table>
				        <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
					        <tr class="td1"> 
							  <td>
					            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
					              <tr> 
								  	<td align="left">共<bean:write name="pagination" property="nrOfElements"/> 项</td>
					                <td align="right"><jsp:include page="../../../inc/pagination.jsp"><jsp:param name="url" value="settlemodleShowAC.do"/></jsp:include></td>
					              </tr>
					          </table></td>
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
		                  		<logic:empty name="noData">
								  <td width="69" align="right"> 
				                    <html:submit property="method" styleClass="buttona"><bean:message key="button.update" /></html:submit>
				                  </td>
				                  <td width="69" align="right"> 
				                    <html:submit property="method" styleClass="buttona" onclick="return checkDelete();"><bean:message key="button.delete" /></html:submit>
				                  </td>
				                 </logic:empty>
				                 
				                 <logic:notEmpty name="noData">
								  <td width="69" align="right"> 
				                    <html:submit property="method" styleClass="buttona" disabled="true"><bean:message key="button.update" /></html:submit>
				                  </td>
				                  <td width="69" align="right"> 
				                    <html:submit property="method" styleClass="buttona" disabled="true"><bean:message key="button.delete" /></html:submit>
				                  </td>
				                 </logic:notEmpty>
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
