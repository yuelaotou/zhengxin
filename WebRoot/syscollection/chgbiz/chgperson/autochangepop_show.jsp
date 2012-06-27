<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@page import="org.xpup.hafmis.syscollection.chgbiz.chgperson.action.AutoChangePopShowAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
String path = request.getContextPath();
Object pagination = session.getAttribute(AutoChangePopShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
    <title>自动变更</title>
    <link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script language="javascript" src="<%=path%>/js/common.js"></script>
	
  </head>

<script>
function reportErrors() {
	<logic:messagesPresent>
		var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
		alert(message);
	</logic:messagesPresent>
}

 function gotoShow(){
return false;
 }
 function check_fyf(){
 	var empid;
 	var temp_empid = [];
 	var j=0;
 	empid = document.getElementsByName("rowArray");
 	for(i=0;i<empid.length;i++){
 		if(empid[i].checked){
 			temp_empid[j] = empid[i].value;
 			j++;
 		}
 	}
 	if(temp_empid.length==0){
		alert("请选择职工！！");
		return false;
	}
 	window.opener.document.URL="<%=path%>/syscollection/chgbiz/chgperson/autoChangeSaveAC.do?empid="+temp_empid;
 	window.close();
 	return false;
 }
 function gotoCheck(){
	var arrays = document.idAF.rowArray;
 	var count = "<bean:write name="pagination" property="nrOfElements"/>";
 	if(document.all.checkALL.checked){
 		if(count=="1"){
 			document.idAF.rowArray.checked="true"; 			
 		}else{
	 		for(var i=0;i<arrays.length;i++){
	 			document.idAF.rowArray[i].checked="true";
	 		}
 		}
 	}else{
 		if(count=="1"){
 			document.idAF.rowArray.checked=""; 			
 		}else{
	 		for(var i=0;i<arrays.length;i++){
	 			document.idAF.rowArray[i].checked="";
	 		}
 		}
 	}
 }
 function gotoClick(){
opener.childFunction();
 window.close();
 }
</script>
<body bgcolor="#FFFFFF" text="#000000" onload="reportErrors();" onContextmenu="return false">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
    <tr>
    <td>     
    
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="7"><img src="<%=path %>/img/table_left.gif" width="7" height="37"></td>
          <td background="<%=path %>/img/table_bg_line.gif" width="55">&nbsp;</td>
          <td width="235" background="<%=path %>/img/table_bg_line.gif">
            <table border="0" cellspacing="0" cellpadding="0">    
              <tr> 
              </tr>  
            </table>
            
           <td background="<%=path %>/img/table_bg_line.gif" align="right"> 
            <table width="300" border="0" cellspacing="0" cellpadding="0">
              <tr> 
              </tr>
            </table>
          </td>
          <td width="9"><img src="<%=path %>/img/table_right.gif" width="9" height="37"></td>
        </tr>
      </table>
      
    </td>
  </tr>
  <tr> 
    <td class=td3>
      <html:form action="/autoChangeSaveAC">
      <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td height="35"><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
                  <td height="22" bgcolor="#CCCCCC" align="center" width="130"><b class="font14">自动变更列表</b></td>
                  <td width="744" height="22" align="center" background="<%=path%>/img/bg2.gif">&nbsp;</td>
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
            <td height="23" bgcolor="C4F0FF" >
            <input type="checkbox" name="checkALL" value="" onclick="gotoCheck();">全选</td>
            <td class="td2">
            	职工编号
            </td>
            <td class="td2"> 
				职工姓名
            </td>
            <td class="td2">
            	证件号码
            </td>
            <td class="td2">
            	缴存状态
            </td>
          </tr>
         <logic:notEmpty name="autoChangePopAF" property="list"> 
         <logic:iterate id="autoChangePopDTO" name="autoChangePopAF" property="list" indexId="i">
          <tr> 
            <td>
              <html:multibox property="rowArray" styleId="checkID" name="idAF">
					<bean:write name="autoChangePopDTO" property="empId" />
			 </html:multibox>
            </td>
            <td><p><bean:write name="autoChangePopDTO" property="empId"/></p></td>
            <td><p><bean:write name="autoChangePopDTO" property="empName"/></p></td>
            <td><p><bean:write name="autoChangePopDTO" property="cardNum"/></p></td>
            <td><p><bean:write name="autoChangePopDTO" property="payStatus"/></p></td>
          </tr>
          <tr > 
            <td colspan="5" class=td5></td>
          </tr>
         </logic:iterate>
         </logic:notEmpty>
         <logic:empty name="autoChangePopAF" property="list">
        <tr> 
          <td colspan="11" height="30" style="color:red">没有找到与条件相符合的结果！</td>
	    </tr>
		<tr > 
          <td colspan="11" class=td5 ></td>
        </tr>
        </logic:empty>
        </table>
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr class="td1"> 
          <td>
            <table width="100%" border="0" align="center" cellpadding="0"
									cellspacing="0">
                <tr> 
                  <td align="left">共<bean:write name="pagination" property="nrOfElements"/> 项</td>
                	<td align="right">  
                 	<jsp:include page="../../../inc/pagination.jsp"><jsp:param name="url" value="autoChangePopShowAC.do"/></jsp:include>
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
                  <td width="69" align="center"> 
                    <html:submit property="method" styleClass="buttona" onclick="return check_fyf();"><bean:message key="button.sure"/></html:submit>
                  </td>
               	 <td width="69" align="center"> 
                    <input type="button" name="Submit42" value="选择全部" class="buttona" onClick="gotoClick();">
                  </td>
               	 <td width="69" align="center"> 
                    <input type="button" name="Submit42" value="关闭" class="buttona" onClick="javascript:window.close();">
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
