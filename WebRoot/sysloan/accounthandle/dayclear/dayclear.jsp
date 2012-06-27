<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.lang.*" import="java.util.*" import="java.math.*"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page import="org.xpup.hafmis.common.util.BusiTools" %>
<%@ page
	import="org.xpup.hafmis.syscollection.accounthandle.dayclear.dto.DayClearDTO"%>
<%String path=request.getContextPath();%>
<html:html>
<head>
<title>修改业务日期</title>
<link href="<%=path%>/css/index.css" type="text/css" rel="stylesheet"/>
 <SCRIPT language="JAVASCRIPT">
 var arr= document.getElementsByName("rowArray");
 
function isSure(){
  	temp=0;//选中几条记录
  	for(var i=0;i<arr.length;i++){
		if(arr[i].checked==true){
			temp=temp+1;
		}
	}
	if(temp==0){
		alert("请选日结的银行！！");
		return false;
	}
	var x=confirm("您确认要进行日结?");
		if(x){ 
		  	return true;
		}else{
		
    	temp=0;//选中几条记录
  		for(var i=0;i<arr.length;i++){
			if(arr[i].checked==true){
				arr[i].checked="";
				temp=temp+1;
		}
		}
		  return false;
		}
}
function gettr(indextid){
	var date=document.getElementById("select"+indextid.substring(2,indextid.length)).value;
	if(arr.length==1){
		document.idAF.rowArray.value=document.idAF.rowArray.value.substring(0,2)+date;
	}else{
		document.idAF.rowArray[indextid.substring(2,indextid.length)].value=document.idAF.rowArray[indextid.substring(2,indextid.length)].value.substring(0,document.idAF.rowArray[indextid.substring(2,indextid.length)].value.indexOf(",")+1)+date;
	}
}
function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
    if(message=="0"){
    alert("日结成功！即将重新登录系统！");
    parent.window.location="<%=path%>/login.jsp";
    }else if(message=="1"){
     alert("日结失败！");
    }else{
    	alert(message);
    }
	</logic:messagesPresent>
	
    	temp=0;//选中几条记录
  		for(var i=0;i<arr.length;i++){
			if(arr[i].checked==true){
				arr[i].checked="";
				temp=temp+1;
		}
		}
}
</SCRIPT>
</head>

<body topmargin=0 leftmargin=0 onload="reportErrors();">
<html:form action="/dayclearDoAC.do">
<div align="center"><br>
  <br>
  <br>
  <table width=641 border=0 cellpadding=0 cellspacing=0>
    <tr>
      <td colspan=4 height="32" class="font14" background="<%=path%>/img/search_1.jpg" style="PADDING-top: 8px"><b>日结</b>
      </td>
    </tr>
    <tr>
<%--      <td rowspan=3> <img src="<%=path%>/img/search_2.jpg" width=26 height=325 alt=""></td>--%>
<%--      <td colspan=3> <img src="<%=path%>/img/search_3.jpg" width=615 height=39 alt=""></td>--%>
    </tr>
    <tr>
<%--      <td rowspan=2> <img src="<%=path%>/img/search_4.jpg" width=40 height=286 alt=""></td>--%>
      <td  height="225" valign="top">
        <table width="100%" border="0" cellspacing="0" cellpadding="0" >
          <tr>
            <td align="center" valign="top" style="border-bottom-style: solid; border-bottom-width: 1;border-left-style: solid; border-left-width: 1;border-right-style: solid; border-right-width: 1" bordercolor="#2D7DC6">
              <table width="100%" border="0" cellspacing="0" cellpadding="2">
              <tr bgcolor="1BA5FF" > 
          <td align="center" height="6" colspan="1" ></td>
        </tr>
      </table>
        <table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1" cellpadding="3" align="center">
          <tr align="center" bgcolor="C4F0FF"> 
            <td height="23" bgcolor="C4F0FF" >&nbsp;</td>
            <td align="center" class=td2 >委托扣款银行</td>
            <td align="center" class=td2 >业务日期</td>
          </tr> 
         <logic:notEmpty name="List">
         <% int j=0;
			String strClass="";
		%>
       	<%
    
	List list = new ArrayList();
	list = (List)request.getAttribute("List");
	DayClearDTO dto = null;
	int m=0;
	for(m=0;m<list.size();m++){
	dto = (DayClearDTO)list.get(m);
	
	%>
       <%j++;
			if (j%2==0) {strClass = "td8";
			}
		    else {strClass = "td9";
		    }
		%>
        <tr id="tr<%=m%>" class="<%=strClass%>">     
		
				
			<td >
			<html:multibox property="rowArray"  ><%=dto.getCollBankId() %>,<%=dto.getCollBankDate() %></html:multibox>
			</td>
			<td valign="top" ><p><%=dto.getCollBankName() %></p></td>
			<td valign="top" ><select id="select<%=m%>" name="id" onchange='gettr("tr<%=m%>");'>
     		<% String bizdate = dto.getCollBankDate();%>
     		<option value="<%=bizdate%>"><%=bizdate%></option>
     			<%
     			 String ff = bizdate;
     			for(int k=1;k<10;k++){ 
     			String bizdate0=BusiTools.addDay(ff,k);
     			%>
     			<option value="<%=bizdate0%>"><%=bizdate0%></option>
     			<%} %>
     		</select></td>
        </tr>
         
       	<%} %>
        </logic:notEmpty>
        <logic:empty name="List">
        <tr> 
          <td colspan="15" height="30" style="color:red">没有找到与条件相符合结果！</td>
	    </tr>
		
        </logic:empty>  
              </table>
              <table width="100%" border="0" cellspacing="1" cellpadding="3" bgcolor="66BEE3">
                <tr align="center" >
                  <td colspan="4" class="td1" height="14">
                    <html:submit onclick="return isSure()">日结</html:submit>
                  </td>
                </tr>
              </table>
            </td>
          </tr>
        </table>
      </td>
<%--      <td rowspan=2> <img src="<%=path%>/img/search_6.jpg" width=42 height=286 alt=""></td>--%>
    </tr>
    <tr>
<%--      <td> <img src="<%=path%>/img/search_7.jpg" width=533 height=61 alt=""></td>--%>
    </tr>
  </table>
</div>
</html:form>
</body>

</html:html>
