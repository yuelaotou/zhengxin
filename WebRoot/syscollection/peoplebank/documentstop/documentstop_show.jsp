<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
	  <head>
	    <title>导出报文头设置</title>
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
		function chkTopicStr(cVal){
		    for(var i=0;i<=cVal.length;i++){
		        var tCode=cVal.charCodeAt(i);
		        if(tCode>=19968&&tCode<=40959){
		            return true;
		        }
		    }
		}

  	  	function checkDates(){
  	  		var dataFormatVersion = document.documentstopAF.elements["dataFormatVersion"].value.trim();
  	  		var dataReportingAgencies = document.documentstopAF.elements["dataReportingAgencies"].value.trim();
  	  		var contactPreson = document.documentstopAF.elements["contactPreson"].value.trim();
  	  		var contactPhone = document.documentstopAF.elements["contactPhone"].value.trim();
  	  		var locationSpace = document.documentstopAF.elements["locationSpace"].value.trim();
  	  		var dataMechanism = document.documentstopAF.elements["dataMechanism"].value.trim();
  	  		
  	  		if(dataFormatVersion == null || dataFormatVersion == ""){
  	  			alert("数据格式版本号不能为空!");
  	  			return false;
  	  		}
  	  		if(dataReportingAgencies == null || dataReportingAgencies == ""){
  	  			alert("数据上报机构不能为空!");
  	  			return false;
  	  		}
  	  		if(contactPreson == null || contactPreson == ""){
  	  			alert("联系人不能为空!");
  	  			return false;
  	  		}
  	  		if(contactPhone == null || contactPhone == ""){
  	  			alert("联系电话不能为空!");
  	  			return false;
  	  		}
  	  		if(locationSpace == null || locationSpace == ""){
  	  			alert("发生地点不能为空!");
  	  			return false;
  	  		}
  	  		if(dataMechanism == null || dataMechanism == ""){
  	  			alert("数据发生机构不能为空!");
  	  			return false;
  	  		}
  	  		
  	  		if(chkTopicStr(dataFormatVersion)){
  	  			alert("数据格式版本号只能是数字或字母！");
  	  			return false;
  	  		}
  	  		if(chkTopicStr(dataReportingAgencies)){
  	  			alert("数据上报机构只能是数字或字母！");
  	  			return false;
  	  		}
  	  		if(chkTopicStr(dataMechanism)){
  	  			alert("数据发生机构只能是数字或字母！");
  	  			return false;
  	  		}
  	  	}
  	  </script>
  	  
<body bgcolor="#FFFFFF" text="#000000" onLoad="reportErrors();" onContextmenu="return false">
	  <html:form action="/documentstopNewAC.do">
			<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
			  <tr>
			    <td>
			      <table width="100%" border="0" cellspacing="0" cellpadding="0">
			        <tr>
			          <td width="7"><img src="<%=path%>/img/table_left.gif" width="7" height="37"></td>
			          <td background="<%=path%>/img/table_bg_line.gif" width="56"></td>
			          <td background="<%=path%>/img/table_bg_line.gif" width="57"></td>
			          <td background="<%=path%>/img/table_bg_line.gif" align="right" width="837"> 
			            <table width="300" border="0" cellspacing="0" cellpadding="0">
			              <tr> 
			                <td width="37"><img src="<%=path%>/img/title_banner.gif" width="37" height="24"></td>
			                <td class=font14 bgcolor="#FFFFFF" align="center" valign="bottom"><font color="00B5DB">人民银行数据导出</font></td>
			                <td width="15" class=font14>&nbsp;</td>
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
			                <td height="22" bgcolor="#CCCCCC" align="center" width="132"><b class="font14">报 文 头 信 息</b></td>
			                <td height="22" background="<%=path%>/img/bg2.gif" align="center" width="742">&nbsp;</td>
			              </tr>
			            </table>
			          </td>
			        </tr>
			      </table>
			      <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=3 align="center" >
			        <tr id="tr1"> 
			          <td class="td1">1</td>
			          <td class="td1"> 数据格式版本号<font color="#FF0000">*</font>：
			              <html:text  name="documentstopAF" property="dataFormatVersion" styleClass="input3"  styleId="txtsearch" onkeydown="enterNextFocus1();" style="background-color:White;width=35%;"/></td>
			        </tr>
			        <tr id="tr1"> 
			          <td class="td1">2</td>
			          <td class="td1">数据上报机构<font color="#FF0000">&nbsp;&nbsp;&nbsp; *</font>：
			              <html:text  name="documentstopAF" property="dataReportingAgencies" styleClass="input3"  styleId="txtsearch" onkeydown="enterNextFocus1();" style="background-color:White;width=35%;"/></td>
			        </tr>
			        <tr id="tr1"> 
			          <td class="td1">3</td>
			          <td class="td1"> <p>联系人<font color="#FF0000">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; *</font>：
			            <html:text  name="documentstopAF" property="contactPreson" styleClass="input3"  styleId="txtsearch" onkeydown="enterNextFocus1();" style="background-color:White;width=35%;"/>
			          </p>          </td>
			        </tr>
			
			        <tr id="tr1"> 
			          <td class="td1">4</td>
			          <td class="td1"> &#32852;&#31995;&#30005;&#35805;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <font color="#FF0000">*</font>：
			            <html:text  name="documentstopAF" property="contactPhone" styleClass="input3"  styleId="txtsearch" onkeydown="enterNextFocus1();" style="background-color:White;width=35%;"/></td>
			        </tr>	
			
			        <tr id="tr1"> 
			          <td class="td1">5</td>
			          <td class="td1">发生地点<font color="#FF0000">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; *</font>：
			            <html:text  name="documentstopAF" property="locationSpace" styleClass="input3"  styleId="txtsearch" onkeydown="enterNextFocus1();" style="background-color:White;width=35%;"/></td>
			        </tr>	
			
			        <tr id="tr1"> 
			          <td class="td1">6</td>
			          <td class="td1"> 数据发生机构<font color="#FF0000">&nbsp;&nbsp;&nbsp; *</font>：
			            <html:text  name="documentstopAF" property="dataMechanism" styleClass="input3"  styleId="txtsearch" onkeydown="enterNextFocus1();" style="background-color:White;width=35%;"/></td>
			        </tr>	
			      </table>
			      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
			        <tr valign="bottom"> 
			          <td  colspan="7" bgcolor="#FFFFFF" align="center" height="30"> 
			            <table border="0" cellspacing="0" cellpadding="0">
			              <tr> 
			                	<td width="70"> 
			                  		<html:submit property="method" styleClass="buttona" onclick="return checkDates();"><bean:message key="button.sure"/></html:submit>
			                	</td>
			              </tr>
			            </table>
			          </td>
			        </tr>
			      </table>
			      <table width="100%" border="0" cellspacing="0" cellpadding="0">
			        <tr>
			          <td>&nbsp;</td>
			        </tr>
			      </table>
			    </td>
			  </tr>
			</table>
		</html:form>
</body>
</html:html>
