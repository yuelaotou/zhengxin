<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ include file="/checkUrl.jsp"%>
<%String path = request.getContextPath();%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
    <title>财务核算</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
  </head>
  <script src="<%=path%>/js/common.js"></script>
  <script>
  	function reportErrors(){
  	document.createBookAF.elements["bookName"].value = "";
  	document.createBookAF.elements["paramValue"].value = "";
  	document.createBookAF.elements["paramExplain1"].value = "";
	document.createBookAF.elements["paramExplain2"].value = "";
	document.createBookAF.elements["paramExplain3"].value = "";
	document.createBookAF.elements["paramExplain4"].value = "";
	document.createBookAF.elements["paramExplain5"].value = "";
	document.createBookAF.elements["paramExplain6"].value = "";
	
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
	}
  </script>
  <script>
  	function checkDates(){
  			var bookName = document.createBookAF.elements["bookName"].value.trim();
	  		var paramValue = document.createBookAF.elements["paramValue"].value.trim();
	  		var paramExplain1 = document.createBookAF.elements["paramExplain1"].value.trim();
	  		var paramExplain2 = document.createBookAF.elements["paramExplain2"].value.trim();
	  		var paramExplain3 = document.createBookAF.elements["paramExplain3"].value.trim();
	  		var paramExplain4 = document.createBookAF.elements["paramExplain4"].value.trim();
	  		var paramExplain5 = document.createBookAF.elements["paramExplain5"].value.trim();
	  		var paramExplain6 = document.createBookAF.elements["paramExplain6"].value.trim();
	  		
	  		if(bookName == null ||bookName == ""){
	  			alert('账套名称不能为空!');
	  			return false;
	  		}
	  		if(paramValue == null || paramValue == ""){
	  			alert("科目级数不能为空!");
	  			return false;
	  		}
	  		if(isNumber(paramValue)){
	  			if(paramValue>6 || paramValue<1){
	  			alert("科目级数只能是1-6!");
	  			return false;
		  		}else{
		  			if(paramValue == 1){
		  				if(paramExplain1 == null || paramExplain1 == ""){
		  					alert("代码结构1不能为空!");
		  					return false;
		  				}
		  			}else if(paramValue == 2){
		  				if(paramExplain1 == null || paramExplain1 == ""){
		  					alert("代码结构1不能为空!");
		  					return false;
		  				}
		  				if(paramExplain2 == null || paramExplain2 == ""){
		  					alert("代码结构2不能为空!");
		  					return false;
		  				}
		  			}else if(paramValue == 3){
		  				if(paramExplain1 == null || paramExplain1 == ""){
		  					alert("代码结构1不能为空!");
		  					return false;
		  				}
		  				if(paramExplain2 == null || paramExplain2 == ""){
		  					alert("代码结构2不能为空!");
		  					return false;
		  				}
		  				if(paramExplain3 == null || paramExplain3 == ""){
		  					alert("代码结构3不能为空!");
		  					return false;
		  				}
		  			}else if(paramValue == 4){
		  				if(paramExplain1 == null || paramExplain1 == ""){
		  					alert("代码结构1不能为空!");
		  					return false;
		  				}
		  				if(paramExplain2 == null || paramExplain2 == ""){
		  					alert("代码结构2不能为空!");
		  					return false;
		  				}
		  				if(paramExplain3 == null || paramExplain3 == ""){
		  					alert("代码结构3不能为空!");
		  					return false;
		  				}
		  				if(paramExplain4 == null || paramExplain4 == ""){
		  					alert("代码结构4不能为空!");
		  					return false;
		  				}
		  			}else if(paramValue == 5){
		  				if(paramExplain1 == null || paramExplain1 == ""){
		  					alert("代码结构1不能为空!");
		  					return false;
		  				}
		  				if(paramExplain2 == null || paramExplain2 == ""){
		  					alert("代码结构2不能为空!");
		  					return false;
		  				}
		  				if(paramExplain3 == null || paramExplain3 == ""){
		  					alert("代码结构3不能为空!");
		  					return false;
		  				}
		  				if(paramExplain4 == null || paramExplain4 == ""){
		  					alert("代码结构4不能为空!");
		  					return false;
		  				}
		  				if(paramExplain5 == null || paramExplain5 == ""){
		  					alert("代码结构5不能为空!");
		  					return false;
		  				}
		  			}else if(paramValue == 6){
		  				if(paramExplain1 == null || paramExplain1 == ""){
		  					alert("代码结构1不能为空!");
		  					return false;
		  				}
		  				if(paramExplain2 == null || paramExplain2 == ""){
		  					alert("代码结构2不能为空!");
		  					return false;
		  				}
		  				if(paramExplain3 == null || paramExplain3 == ""){
		  					alert("代码结构3不能为空!");
		  					return false;
		  				}
		  				if(paramExplain4 == null || paramExplain4 == ""){
		  					alert("代码结构4不能为空!");
		  					return false;
		  				}
		  				if(paramExplain5 == null || paramExplain5 == ""){
		  					alert("代码结构5不能为空!");
		  					return false;
		  				}
		  				if(paramExplain6 == null || paramExplain6 == ""){
		  					alert("代码结构6不能为空!");
		  					return false;
		  				}
		  			}
		  		}
		  	}else{
		  		alert("科目级数只能是数字!");
		  		return false;
		  	}
		  	if(confirm("是否创建账套?")){
			  	return true;
		  	}else{
				history.back();
				return false;
		  	}
	  }
  </script>
  <script>
  	function checkParamValue(){
  		var paramValue = document.createBookAF.elements["paramValue"].value;
  		if(paramValue == 1){
  			document.createBookAF.elements["paramExplain2"].disabled = true;
  			document.createBookAF.elements["paramExplain3"].disabled = true;
  			document.createBookAF.elements["paramExplain4"].disabled = true;
  			document.createBookAF.elements["paramExplain5"].disabled = true;
  			document.createBookAF.elements["paramExplain6"].disabled = true;
  		}
  		if(paramValue == 2){
  			document.createBookAF.elements["paramExplain3"].disabled = true;
  			document.createBookAF.elements["paramExplain4"].disabled = true;
  			document.createBookAF.elements["paramExplain5"].disabled = true;
  			document.createBookAF.elements["paramExplain6"].disabled = true;
  		}
  		if(paramValue == 3){
  			document.createBookAF.elements["paramExplain4"].disabled = true;
  			document.createBookAF.elements["paramExplain5"].disabled = true;
  			document.createBookAF.elements["paramExplain6"].disabled = true;
  		}
  		if(paramValue == 4){
  			document.createBookAF.elements["paramExplain5"].disabled = true;
  			document.createBookAF.elements["paramExplain6"].disabled = true;
  		}
  		if(paramValue == 5){
  			document.createBookAF.elements["paramExplain6"].disabled = true;
  		}
  	}
  	
  	function clearParamValue(){
  		document.createBookAF.elements["paramExplain1"].value = "";
  		document.createBookAF.elements["paramExplain2"].value = "";
  		document.createBookAF.elements["paramExplain3"].value = "";
  		document.createBookAF.elements["paramExplain4"].value = "";
  		document.createBookAF.elements["paramExplain5"].value = "";
  		document.createBookAF.elements["paramExplain6"].value = "";
  		
	  	document.createBookAF.elements["paramExplain2"].disabled = false;
  		document.createBookAF.elements["paramExplain2"].disabled = false;
  		document.createBookAF.elements["paramExplain3"].disabled = false;
  		document.createBookAF.elements["paramExplain4"].disabled = false;
  		document.createBookAF.elements["paramExplain5"].disabled = false;
  		document.createBookAF.elements["paramExplain6"].disabled = false;
  	}
  </script>
  
<body bgcolor="#FFFFFF" text="#000000" onLoad="reportErrors();"  onContextmenu="return false">
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
			                <td class=font14 bgcolor="#FFFFFF" align="center" valign="bottom"><font color="00B5DB">账套管理&gt;创建账套</font></td>
			                <td width="15" class=font14></td>
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
			                <td height="22" bgcolor="#CCCCCC" align="center" width="130"><b class="font14">创 建 账 套</b></td>
			                <td height="22" background="<%=path%>/img/bg2.gif" align="center" width="744"></td>
			              </tr>
			            </table>
			          </td>
			        </tr>
			      </table>
			      <html:form action="/createBookNewAC.do">
			      <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=3 align="center" >
			        <tr> 
			          <td width="15%" class="td1"  >账套名称<font color="#FF0000">*</font><br></td>
			          <td width="35%"> 
			          	<html:text  name="createBookAF" property="bookName" styleClass="input3"  styleId="txtsearch" onkeydown="enterNextFocus1();"/>
			          <td width="15%" class="td1">账套启用时间<font color="#FF0000">*</font></td>
			          <td width="35%" colspan="6" class="td7" >
			          	<select name="bookYear" onkeydown="enterNextFocus1();">
				            <option value="2006" selected>2006</option>
				            <option value="2007">2007</option>
				            <option value="2008">2008</option>
				            <option value="2009">2009</option>
			          	</select>年度</td>
			          <td  width="15%" class="td7" colspan="5" onkeydown="enterNextFocus1();">
			          	<select name="bookMonth">
				            <option value="01">01</option>
				            <option value="02">02</option>
				            <option value="03">03</option>
				            <option value="04">04</option>
				            <option value="05">05</option>
				            <option value="06">06</option>
				            <option value="07">07</option>
				            <option value="08">08</option>
				            <option value="09">09</option>
				            <option value="10">10</option>
				            <option value="11">11</option>
				            <option value="12">12</option>
				            </select>期</td>
			        </tr>
			        <tr> 
			          <td width="15%"   class="td1" height="18">科目级数<font color="#FF0000">*</font></td>
			          <td width="35%" align="center" height="18"><html:text  name="createBookAF" property="paramValue" styleClass="input3"  styleId="txtsearch" onblur="checkParamValue();" onfocus="clearParamValue();" onkeydown="enterNextFocus1();"/></td>
			          <td width="15%" class="td1" height="18" >代码结构<font color="#FF0000">*</font></td>
			          <td width="3%"  height="18"><html:text  name="createBookAF" property="paramExplain1" styleClass="input3"  styleId="txtsearch" onkeydown="enterNextFocus1();"/></td>
			          <td width="3%" >-</td>
			          <td width="3%" ><html:text  name="createBookAF" property="paramExplain2" styleClass="input3"  styleId="txtsearch" onkeydown="enterNextFocus1();"/></td>
			          <td width="3%" >-</td>
			          <td width="3%" ><html:text  name="createBookAF" property="paramExplain3" styleClass="input3"  styleId="txtsearch" onkeydown="enterNextFocus1();"/></td>
			          <td width="3%" >-</td>
			          <td width="3%" ><html:text  name="createBookAF" property="paramExplain4" styleClass="input3"  styleId="txtsearch" onkeydown="enterNextFocus1();"/></td>
			          <td width="3%" >-</td>
			          <td width="3%" ><html:text  name="createBookAF" property="paramExplain5" styleClass="input3"  styleId="txtsearch" onkeydown="enterNextFocus1();"/></td>
			          <td width="3%" >-</td>
			          <td width="3%"  height="18" ><html:text  name="createBookAF" property="paramExplain6" styleClass="input3"  styleId="txtsearch" onkeydown="enterNextFocus1();"/></td>
			        </tr>
			      </table>
			
			      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
			        <tr valign="bottom"> 
			          <td  colspan="7" bgcolor="#FFFFFF" align="center" height="30"> 
			            <table border="0" cellspacing="0" cellpadding="0">
			              <tr> 
			                <td> 
			                <html:submit property="method" styleClass="buttona" onclick="return checkDates();" ><bean:message key="button.sure"/></html:submit>
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
