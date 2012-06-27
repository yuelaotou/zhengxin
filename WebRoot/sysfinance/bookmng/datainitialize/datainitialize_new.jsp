<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ page import="java.util.List"%>
<%@ page import="org.xpup.hafmis.sysfinance.bookmng.datainitialize.dto.DatainitializeDTO"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	List lendsMoneyLists = (List)request.getAttribute("datainitializeLists");
	int lendsMoneyNumber = 0;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
	  <head>
	    <title>初始数据</title>
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
					document.URL=('datainitalizeShowAC.do?officecode='+officecode+'&status=1');
				}else{
					document.URL=('datainitalizeShowAC.do?officecode='+officecode);
				}
			}
  		</script>
  		
  		<script type="text/javascript">
			function checksave(){
				document.getElementById("method").value="save";
			}
			
			function checktotals(){
				document.getElementById("method").value="totals";
			}
			function check_zl(num,num1){
				var temp_name = "radiobb"+num;
				document.getElementById(temp_name).value=num1;
			}
    	</script>
    
  <body bgcolor="#FFFFFF" text="#000000" onLoad="reportErrors();" onContextmenu="return false">
		<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
		  <tr>
		    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
		      <tr>
		        <td width="7"><img src="<%=path%>/img/table_left.gif" width="7" height="37"></td>
		        <td background="<%=path%>/img/table_bg_line.gif" width="56">&nbsp;</td>
		        <td background="<%=path%>/img/table_bg_line.gif" width="57"><a href="开户销户_开户登记_开户登记.htm"> </a></td>
		        <td background="<%=path%>/img/table_bg_line.gif" align="right" width="837"><table width="300" border="0" cellspacing="0" cellpadding="0">
		          <tr>
		            <td width="37"><img src="<%=path%>/img/title_banner.gif" width="37" height="24"></td>
		            <td class=font14 bgcolor="#FFFFFF" align="center" valign="bottom"><font color="00B5DB">账套管理&gt;初始数据</font></td>
		            <td width="15" class=font14>&nbsp;</td>
		          </tr>
		        </table></td>
		        <td width="9"><img src="<%=path%>/img/table_right.gif" width="9" height="37"></td>
		      </tr>
		    </table></td>
		  </tr>
		  <tr>
		    <td class=td3><table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
		      <tr>
		        <td height="35"><table width="100%" border="0" cellspacing="0" cellpadding="0">
		          <tr>
		            <td height="22" bgcolor="#CCCCCC" align="center" width="165"><b class="font14">创 建 初 始 数 据</b></td>
		            <td height="22" background="<%=path%>/img/bg2.gif" align="center" width="709">&nbsp;</td>
		          </tr>
		        </table></td>
		      </tr>
		    </table>
		    	 <html:form action="/datainitalizeNewAC.do">
		        <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=3 align="center" >
		          <tr align="center"> 
		            <td colspan="8" class="td1" align="left" >
						<table width="100%" border="0" cellspacing="3" cellpadding="0" >
						<logic:notEmpty name="officeList1">
							<tr>
								<td class=td1 width="9%">所属办事处<font color="#FF0000">*</font></td>
								<td class="td4" width="31%">
									<html:select property="officeName" styleClass="input4" name="datainitializeNewAF" onchange="verdictOffice();">
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
		            <td class="td1" align="center">科目代码</td>
		            <td class="td1" align="center">科目名称</td>
		            <td class="td1" align="center">本年累计借方<font color="#FF0000">*</font></td>
		            <td class="td1" align="center">本年累计贷方<font color="#FF0000">*</font></td>
		            <td class="td1" align="center">上年累计借方<font color="#FF0000">*</font></td>
		            <td class="td1" align="center">上年累计贷方<font color="#FF0000">*</font></td>
		            <td class="td1" align="center">期初余额<font color="#FF0000">*</font></td>
		            <td class="td1" align="center">余额方向<font color="#FF0000">*</font></td>
		          </tr>
		          <logic:notEmpty name="datainitializeLists">
		          <logic:iterate id="datainitializeList" name="datainitializeLists">
			          <tr>
			            <td class="td1"><%out.print(((DatainitializeDTO)lendsMoneyLists.get(lendsMoneyNumber)).getSubjectCode());%></td>
			            <input type="hidden" name="subjectCode<%=lendsMoneyNumber %>" value="<%out.print(((DatainitializeDTO)lendsMoneyLists.get(lendsMoneyNumber)).getSubjectCode()); %>" />
			            <input type="hidden" name="subjectName<%=lendsMoneyNumber %>" value="<%out.print(((DatainitializeDTO)lendsMoneyLists.get(lendsMoneyNumber)).getSubjectName()); %>" />
			            <td class="td1"><%out.print(((DatainitializeDTO)lendsMoneyLists.get(lendsMoneyNumber)).getSubjectName());%></td>
			            <logic:empty name="bookST">
				            <td><input name="debit<%=lendsMoneyNumber %>" type="text" id="txtsearch" class="input3" value="<%out.print(((DatainitializeDTO)lendsMoneyLists.get(lendsMoneyNumber)).getDebit());%>" onkeydown="enterNextFocus1();" readonly="readonly"/>
				            </td>
				            <td><input name="credit<%=lendsMoneyNumber %>" type="text" id="txtsearch4522213" class="input3" value="<%out.print(((DatainitializeDTO)lendsMoneyLists.get(lendsMoneyNumber)).getCredit());%>" onkeydown="enterNextFocus1();" readonly="readonly"/>
				            </td>
				            <td><input name="yesterdayDebit<%=lendsMoneyNumber %>" type="text" id="txtsearch4522213" class="input3" value="<%out.print(((DatainitializeDTO)lendsMoneyLists.get(lendsMoneyNumber)).getYesterdayDebit());%>" onkeydown="enterNextFocus1();" readonly="readonly"/>
				            </td>
				            <td><input name="yesterdayCredit<%=lendsMoneyNumber %>" type="text" id="txtsearch4522213" class="input3" value="<%out.print(((DatainitializeDTO)lendsMoneyLists.get(lendsMoneyNumber)).getYesterdayCredit());%>" onkeydown="enterNextFocus1();" readonly="readonly"/>
				            </td>
				            <td><input name="yesterdayRemainingSum<%=lendsMoneyNumber %>" type="text" id="txtsearch4522213" class="input3" value="<%out.print(((DatainitializeDTO)lendsMoneyLists.get(lendsMoneyNumber)).getYesterdayRemainingSum());%>" onkeydown="enterNextFocus1();"/>
				            </td>
				            <td class="td7">
				            	<input type="radio"  disabled="disabled" name="balaceDirection<%=lendsMoneyNumber %>" value="0" <%if(((DatainitializeDTO)lendsMoneyLists.get(lendsMoneyNumber)).getBalaceDirection().equals("0")) out.print("checked"); %>>借
				              	<input type="radio"  disabled="disabled" name="balaceDirection<%=lendsMoneyNumber %>" value="1" <%if(((DatainitializeDTO)lendsMoneyLists.get(lendsMoneyNumber)).getBalaceDirection().equals("1")) out.print("checked"); %>>贷
				              	<input type="radio"  disabled="disabled" name="balaceDirection<%=lendsMoneyNumber %>" value="2" <%if(((DatainitializeDTO)lendsMoneyLists.get(lendsMoneyNumber)).getBalaceDirection().equals("2")) out.print("checked"); %>>平
				              	 <input type="hidden" name="radiobb<%=lendsMoneyNumber %>" value="<%=((DatainitializeDTO)lendsMoneyLists.get(lendsMoneyNumber)).getBalaceDirection() %>" />
			            	</td>
			            </logic:empty>
			            <logic:notEmpty name="bookST">
			            	<td><input name="debit<%=lendsMoneyNumber %>" type="text" id="txtsearch" class="input3" value="<%out.print(((DatainitializeDTO)lendsMoneyLists.get(lendsMoneyNumber)).getDebit());%>" onkeydown="enterNextFocus1();"/>
				            </td>
				            <td><input name="credit<%=lendsMoneyNumber %>" type="text" id="txtsearch4522213" class="input3" value="<%out.print(((DatainitializeDTO)lendsMoneyLists.get(lendsMoneyNumber)).getCredit());%>" onkeydown="enterNextFocus1();"/>
				            </td>
				            <td><input name="yesterdayDebit<%=lendsMoneyNumber %>" type="text" id="txtsearch4522213" class="input3" value="<%out.print(((DatainitializeDTO)lendsMoneyLists.get(lendsMoneyNumber)).getYesterdayDebit());%>" onkeydown="enterNextFocus1();" />
				            </td>
				            <td><input name="yesterdayCredit<%=lendsMoneyNumber %>" type="text" id="txtsearch4522213" class="input3" value="<%out.print(((DatainitializeDTO)lendsMoneyLists.get(lendsMoneyNumber)).getYesterdayCredit());%>" onkeydown="enterNextFocus1();" />
				            </td>
				            <td><input name="yesterdayRemainingSum<%=lendsMoneyNumber %>" type="text" id="txtsearch4522213" class="input3" value="<%out.print(((DatainitializeDTO)lendsMoneyLists.get(lendsMoneyNumber)).getYesterdayRemainingSum());%>" onkeydown="enterNextFocus1();"/>
				            </td>
				            <td class="td7">
				            	<input type="radio" name="balaceDirection<%=lendsMoneyNumber %>" value="0" onclick="check_zl('<%=lendsMoneyNumber %>','0');" <%if(((DatainitializeDTO)lendsMoneyLists.get(lendsMoneyNumber)).getBalaceDirection().equals("0")) out.print("checked"); %>>借
				              	<input type="radio" name="balaceDirection<%=lendsMoneyNumber %>" value="1" onclick="check_zl('<%=lendsMoneyNumber %>','1');" <%if(((DatainitializeDTO)lendsMoneyLists.get(lendsMoneyNumber)).getBalaceDirection().equals("1")) out.print("checked"); %>>贷
				              	<input type="radio" name="balaceDirection<%=lendsMoneyNumber %>" value="2" onclick="check_zl('<%=lendsMoneyNumber %>','2');" <%if(((DatainitializeDTO)lendsMoneyLists.get(lendsMoneyNumber)).getBalaceDirection().equals("2")) out.print("checked"); %>>平
				              	 <input type="hidden" name="radiobb<%=lendsMoneyNumber %>" value="<%=((DatainitializeDTO)lendsMoneyLists.get(lendsMoneyNumber)).getBalaceDirection() %>" />
			            	</td>
			            </logic:notEmpty>
	          		  </tr>
	          		  <%lendsMoneyNumber++; %>
		          </logic:iterate>
		          </logic:notEmpty>
		        </table>
		        <input type="hidden" name="lendsMoneySize" value="<%=lendsMoneyNumber %>" />
		      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
          <tr valign="bottom">
            <td  colspan="7" bgcolor="#FFFFFF" align="center" height="30"><table border="0" cellspacing="0" cellpadding="0">
                <tr>
              	<input type="hidden" name="method" id="method" value="" />
                	<logic:notEmpty name="bookST">
		                  <td width="70">
		                  <html:submit property="method" styleClass="buttona" onclick="return checksave();"><bean:message key="button.sure"/></html:submit>
		                  </td>
		            </logic:notEmpty>
		            <logic:empty name="bookST">
		                  <td width="70">
		                  <html:submit property="method" styleClass="buttona" disabled="true" ><bean:message key="button.sure"/></html:submit>
		                  </td>
		            </logic:empty>
		            
                  <logic:notEmpty name="bookST">
	                  <td width="70">
	                  	<html:submit property="method" styleClass="buttona" onclick="return checktotals();">试算平衡</html:submit>
	                  </td>
                  </logic:notEmpty>
                  <logic:empty name="bookST">
	                  <td width="70">
	                  	<input type="button" name="Submit" value="试算平衡" class="buttona" disabled="disabled" />
	                  </td>
                  </logic:empty>
                </tr>
            </table></td>
          </tr>
        </table></html:form>
     </td>
  </tr>
</table>
	</body>
</html:html>
