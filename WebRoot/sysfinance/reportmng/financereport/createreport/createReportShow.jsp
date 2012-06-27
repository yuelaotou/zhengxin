<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ include file="/checkUrl.jsp"%>

<%@ page import="org.xpup.hafmis.sysfinance.reportmng.financereport.createreport.action.CreateReportShowAC" %>

<%
   Object pagination= request.getSession(false).getAttribute(CreateReportShowAC.PAGINATION_KEY);
   request.setAttribute("pagination",pagination);
   String path = request.getContextPath();
 %>
<html:html>
<head>
<title>财务核算-创建报表</title>
<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">  
<script language="javascript" src="<%=path%>/js/common.js"></script>
</head>
<script type="text/javascript" charset="utf-8" >
function reportErrors(message) {	
	var actionflag = document.forms[0].elements["actionflag"].value;
	if(actionflag==1){
    	document.all.button1.style.display="none";
	}else{
    	document.all.button2.style.display="none";
	}
	var savemethod = document.forms[0].elements["savemethod"].value;
	if(savemethod==1){
		document.URL="<%=path%>/toindex.jsp";
	}
	
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
}

function checkSaveData(){
      var tablenamedef=document.all.tablenamedef.value.trim();
      var colspandef=document.all.colspandef.value.trim();
      var rowspandef=document.all.rowspandef.value.trim();
      if(tablenamedef.length==0){
      	alert("请录入报表名称!!");
      	document.all.tablenamedef.focus();
		return false;
      }else if(colspandef.length==0){
      	alert("请录入报表行数 !!");
      	document.all.colspandef.focus();
		return false;
      }else if(rowspandef.length==0){
      	alert("请录入报表列数!!");
      	document.all.rowspandef.focus();
		return false;
	  }else if(isNaN(colspandef)&&colspandef<1){
      	alert("请正确录入报表行数 !!");
      	document.all.colspandef.focus();
		return false;
	  }else if(isNaN(rowspandef)){
      	alert("请正确录入报表列数 !!");
      	document.all.rowspandef.focus();
		return false;
	  }else if(colspandef<1){
      	alert("请正确录入有效报表行数 !!");
      	document.all.colspandef.focus();
		return false;
	  }else if(rowspandef<1){
      	alert("请正确录入有效报表列数 !!");
      	document.all.rowspandef.focus();
		return false;
	  }else if(colspandef>300){
      	alert("报表行数不能超过300！！");
      	document.all.colspandef.focus();
		return false;
	  }else if(colspandef>30){
      	alert("报表列数不能超过50！！");
      	document.all.rowspandef.focus();
		return false;
	  }else if(document.forms[0].elements["actionflag"].value==1){
	  	checkModifyData();
	  }else{
	  	return true;
	  }
}
function checkModifyData(){
		    var queryString = "createReportCheckModifyAAC.do?";
			queryString = queryString + "reportid="+document.all.tableid.value+"&newcol="+document.all.colspandef.value.trim()+"&newrow="+document.all.rowspandef.value.trim()+""; 
			
			findInfo(queryString);
}

function checkModify(){
		document.URL="createReportMaintainAC.do?method=modify&reportid="+getCheckId()+"";
}

function display_Modify(flag){
	if(flag=="have"){
		var x=confirm("要修改的表格格式中已经定义数据，是否继续修改?");
		if(x){
			document.forms[0].action="createReportUpdateAC.do";
			document.forms[0].submit();
		}else{
		  return false;
		}
	}
	else{
		document.forms[0].action="createReportUpdateAC.do";
		document.forms[0].submit();
	}

}
function checkDetele(){
	var x=confirm("确定删除记录?");
		if(x){
		    var queryString = "createReportCheckDeleteAAC.do?";
			queryString = queryString + "reportid="+getCheckId(); 
			findInfo(queryString);
		  	
		}else{
		  return false;
		}
}
function display_Delete(flag){
	if(flag=="have"){
		var x=confirm("该报表已经定义数据，是否继续删除?");
		if(x){
			document.URL="createReportMaintainAC.do?method=delete&reportid="+getCheckId()+"";
		}else{
		  return false;
		}
	}
	else{
		document.URL="createReportMaintainAC.do?method=delete&reportid="+getCheckId()+"";
	}

}
function checkPrint(){
	document.URL="createReportMaintainAC.do?method=print";
}

</script>
<body bgcolor="#FFFFFF" text="#000000"  onContextmenu="return false" onload="reportErrors();">

<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td>     
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="7"><img src="<%=path %>/img/table_left.gif" width="7" height="37"></td>
          <td background="<%=path %>/img/table_bg_line.gif" width="55">&nbsp;</td>
          <td width="235" background="<%=path %>/img/table_bg_line.gif"></td>
          <td background="<%=path %>/img/table_bg_line.gif" align="right"> 
            <table width="300" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="37"><img src="<%=path %>/img/title_banner.gif" width="37" height="24"></td>
                <td width="148" class=font14 bgcolor="#FFFFFF" align="center" valign="bottom"><font color="00B5DB">报表管理&gt;创建报表</font></td>
                <td width="115" class=font14>&nbsp;</td>
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
      <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td height="35"> 
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td height="22" bgcolor="#CCCCCC" align="center" width="132"><b class="font14">创 建 报 表</b></td>
                <td height="22" background="<%=path %>/img/bg2.gif" align="center" width="742">&nbsp;</td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
      	<html:form action="/createReportSaveAC.do">
      <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=1 align="center" >
        <tr> 
        	<html:hidden name="createReportAF" property="actionflag" />
        	<html:hidden name="createReportAF" property="tableid" />
        	<html:hidden name="createReportAF" property="savemethod" />
          <td width="15%" class="td1"  >报表名称<font color="#FF0000">*</font><br></td>
          <td width="35%"   >
              <html:text name="createReportAF" property="tablenamedef" onkeydown="enterNextFocus1();"  styleClass="input3" styleId="txtsearch" /></td>
          <td width="15%" ></td>
          <td  width="35%"></td>
        </tr>
        <tr> 
          <td width="15%"   class="td1" height="18">报表行数<font color="#FF0000">*</font></td>
          <td width="35%" align="center" height="18"  > 
              <html:text name="createReportAF" property="colspandef" onkeydown="enterNextFocus1();" styleClass="input3" styleId="txtsearch" /></td>
          <td width="15%" class="td1" height="18" >报表列数<font color="#FF0000">*</font></td>
          <td width="35%"  height="18" > 
              <html:text name="createReportAF" property="rowspandef" onkeydown="enterNextFocus1();" styleClass="input3" styleId="txtsearch" /></td>
        </tr>
      </table>
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr valign="bottom"> 
          <td  colspan="7" bgcolor="#FFFFFF" align="center" height="30"> 
            <table border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="70" id="button1">
                  <html:submit styleClass="buttona" onclick="return checkSaveData()"><bean:message key="button.sure"/></html:submit>
                </td>
                <td width="70" id="button2">
                	<input type="button" class="buttona" onclick="return checkSaveData();" value="<bean:message key="button.confirm"/>">
                </td>  
              </tr>
            </table>
          </td>
        </tr>
      </table>
      	 </html:form>
      	 <html:form action="/createReportFindAC.do">
	  <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td height="35"> 
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                  <td height="22" bgcolor="#CCCCCC" align="center" width="127"><b class="font14">查 询 条 件</b></td>
                  <td height="22" background="<%=path %>/img/bg2.gif" align="center" width="731">&nbsp;</td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
	  <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=0 align="center" >
          <tr> 
            <td width="15%"   class="td1">报表名称<br>
            </td>
            <td width="35%"  > 
               <html:text name="createReportAF" property="tablenamequery" onkeydown="enterNextFocus1();" styleClass="input3" styleId="txtsearch" />
            </td>
            <td width="15%" class="td1" >创建日期</td>
            <td width="15%"  > 
               <html:text name="createReportAF" property="createtimestart" onkeydown="enterNextFocus1();" styleClass="input3" styleId="txtsearch" />
            </td>
            <td width="5%" align="center" >至</td>
            <td width="15%"  > 
               <html:text name="createReportAF" property="createtimeend" onkeydown="enterNextFocus1();" styleClass="input3" styleId="txtsearch" />
            </td>
          </tr>
      </table>
	  <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td align="right"><html:submit property="method" styleClass="buttona" onclick="return checkSearchData()"><bean:message key="button.search"/></html:submit></td>
        </tr>
      </table>
      	 </html:form>
      	 <html:form action="/createReportMaintainAC.do">
	  <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td height="35">
          	<table width="100%" border="0" cellspacing="0" cellpadding="0">
            	<tr>
                  <td height="22" bgcolor="#CCCCCC" align="center" width="127"><b class="font14">报 表 列 表</b></td>
                  <td width="731" height="22" align="center" background="<%=path %>/img/bg2.gif">&nbsp;</td>
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
      <table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1" cellpadding="3" 
      	align="center">
          <tr align="center" > 
            <td height="23" bgcolor="C4F0FF" >&nbsp;</td>
            <td align="center" class=td2 >报表名称</td>
            <td align="center" class=td2 >行数</td>  
            <td align="center" class=td2 >列数</td>  
            <td align="center" class=td2 >创建日期</td>
            <td align="center" class=td2 >创建人</td>
          </tr>
          
         <logic:notEmpty name="createReportAF" property="list">
         <%
			int j=0;
			String strClass="";
		 	%>
		 <logic:iterate name="createReportAF" property="list" id="element" indexId="i">
		<%	j++;
		if (j%2==0) {strClass = "td8";
		}
	    else {strClass = "td9";
	    }%>
        <tr id="tr<%=i%>" onclick='gotoClickpp("<%=i%>",idAF);' 
	        onMouseOver='this.style.background="#eaeaea"'  
	        onMouseOut='gotoColorpp("<%=i %>",idAF);' 
	        class="<%=strClass %>" > 
          <td valign="top">
          <p align="left">
            <input id="s<%=i %>" type="radio" name="id" value="<bean:write name="element" property="id"/>" <%if(new Integer(0).equals(i)) out.print("checked"); %>>
          </p>
          </td>
          <td valign="top"><p><bean:write name="element" property="tailtableNameChinese" /></p></td>
          <td valign="top"><p><bean:write name="element" property="colspan" /></p></td>
          <td valign="top"><p><bean:write name="element" property="rowspan" /></p></td>
          <td valign="top"><p><bean:write name="element" property="createtime" /></p></td>
          <td valign="top"><p><bean:write name="element" property="createperson" /></p></td>
        </tr>
          </logic:iterate>
        </logic:notEmpty>
        <logic:empty name="createReportAF" property="list">
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
                <td align="right"><jsp:include page="../../../../inc/pagination.jsp"><jsp:param name="url" value="createReportShowAC.do"/></jsp:include></td>
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
              	<input type="hidden" name="method" id="method" value="" />
              	
         		<logic:notEmpty name="createReportAF" property="list">
                <td width="69" align="right"><html:button property="method" styleClass="buttona" onclick="return checkModify();"><bean:message key="button.update"/></html:button></td>
                <td width="69" align="right"><html:button property="method" styleClass="buttona" onclick="return checkDetele();"><bean:message key="button.delete"/></html:button></td>
                <td width="69" align="right"><html:button property="method" styleClass="buttona" onclick="return checkPrint();"><bean:message key="button.print"/></html:button></td>      
			  	</logic:notEmpty>
              	
         		<logic:empty name="createReportAF" property="list">
                <td width="69" align="right"><html:submit styleClass="buttona" disabled="true"><bean:message key="button.update"/></html:submit></td>
                <td width="69" align="right"><html:submit styleClass="buttona" disabled="true"><bean:message key="button.delete"/></html:submit></td>
                <td width="69" align="right"><html:submit styleClass="buttona" disabled="true"><bean:message key="button.print"/></html:submit></td>      
			  	</logic:empty>
			  	
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
      	 </html:form>
    </td>
  </tr>
  
</table>

</body>
</html:html>
