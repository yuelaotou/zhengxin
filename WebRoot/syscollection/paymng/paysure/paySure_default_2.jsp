<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ page import="org.xpup.hafmis.syscollection.paymng.paysure.action.PaymentTaFindAC" %>
<%@ include file="/checkUrl.jsp"%>

<%
   Object pagination= request.getSession(false).getAttribute(PaymentTaFindAC.PAGINATION_KEY);
   request.setAttribute("pagination",pagination);
 %>
<html:html>
<head>
<title>缴存管理>>缴存登记>>正常汇缴[paySure_default_2.jsp]</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/index.css" type="text/css">de
<script src="<%=request.getContextPath()%>/js/common.js">
</script>
</head>

<script language="javascript"  type="text/javascript" >


var s1="";
var s2="";
var s3="";

function reportErrors() {
}
  
function findName() {
   
        var queryString = "paymentTaFindACC.do?";
        var orgId = document.paymentAF.elements["orgId"].value;
        queryString = queryString + "orgId="+orgId; 	  alert(queryString);    
	    findInfo(queryString);      
	    
}

function findInfo(queryString) {
 createXMLHttpRequest();        
	    xmlHttp.onreadystatechange = handleStateChange;
	    xmlHttp.open("GET", queryString, true);   
	    xmlHttp.send(null);   alert("get true");
}



function displays(id,name){
  document.demoAF.elements["orgId"].value=id;
  document.demoAF.elements["orgName"].value=name;
  alter(document.demoAF.elements["orgName"].value);
  showlist() 
}
function showlist() {
  document.Form1.submit();
}

var tr="tr0"; 
function gettr(trindex) {
  tr=trindex;
  update1();
}
function gettr2(trindex) {
  tr=trindex;
  update1();
  sureType();
}
function update1() {
  	var status=document.getElementById(tr).childNodes[8].childNodes[0].innerHTML;
  	if(status=='登记'){
  		document.all.item(s1).disabled="";
		document.all.item(s2).disabled="true";
		
  	}else if(status=='确认'){
  		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="";
		
  	}else {
  		document.all.item(s1).disabled="false";
		document.all.item(s2).disabled="false";
		
  	}
}

function sureType(){
   var status=document.getElementById(tr).childNodes[6].childNodes[0].innerHTML;
 if(status=='汇缴'){
  	 var id=document.getElementById(tr).childNodes[9].childNodes[0].innerHTML;
  		window.open('<%=request.getContextPath()%>/syscollection/paymng/monthpay/monthpayTbWindowShowAC.do?paymentid='+id,'','width=600,height=400,top=200,left=300,scrollbars=yes');return gotoShow();
                  
 	}
 if(status=='单位补缴'){
 
  	 var id=document.getElementById(tr).childNodes[9].childNodes[0].innerHTML;
  		window.open('<%=request.getContextPath()%>/syscollection/paymng/orgaddpay/orgaddpayTbWindowShowAC.do?paymentid='+id,'','width=600,height=400,top=200,left=300,scrollbars=yes');return gotoShow();
                  
 	}
 	 if(status=='个人补缴'){
 
  	 var id=document.getElementById(tr).childNodes[9].childNodes[0].innerHTML;
  		window.open('<%=request.getContextPath()%>/syscollection/paymng/orgoverpay/orgoverpayTbWindowShowAC.do?paymentid='+id,'','width=600,height=400,top=200,left=300,scrollbars=yes');return gotoShow();
                  
 	}
 	 if(status=='单位挂账'){
 
  	 var id=document.getElementById(tr).childNodes[9].childNodes[0].innerHTML;
  		window.open('<%=request.getContextPath()%>/syscollection/paymng/orgoverpay/orgoverpayTbWindowShowAC.do?paymentid='+id,'','width=600,height=400,top=200,left=300,scrollbars=yes');return gotoShow();
                  
 	}
 }
function loads(){
  
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
	for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
			if(document.all.item(i).value=="到账确认"){
				s1=i;
			}
			if(document.all.item(i).value=="撤消到账确认"){
				s2=i;
			}
		}
	}

  var list = document.all.listCount.value;
  
    if(list.length==2){
    	alert("edfghjk");
  		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="true";
    }else{
		update1();
    }
}

function gotoSure(){
	if(!confirm("确认要进行该次到账确认吗？")){
		return false;
	}

}
function gotoDelete(){
	if(!confirm("确定要进行该次撤消到账确认吗？")){
		return false;
	}

}
function gotocheck(){
   var month=document.all.settDate.value;
   var orgid=document.all.orgId.value;
   var notenum=document.all.noteNum.value;
   var docnum=document.all.docNum.value;
   var paymoney=document.all.payMoney.value;
   
   if(orgid != ""){
   if(isNaN(orgid)){
     alert("请输入正确格式的单位编号！");
     return false;
   }
   }
   
   if(notenum != ""){
      if(isNaN(notenum)){
        alert("请输入正确格式的结算号！");
        return false;
      }
   }
   
    if(docnum != ""){
      if(isNaN(docnum)){
        alert("请输入正确格式的凭证编号！");
        return false;
      }
   }
   
   if(paymoney != ""){
      if(isNaN(paymoney)){
        alert("请输入正确格式的缴存金额！");
        return false;
      }
   }
   
   
   if(month != ""){
   if(month.length!=8)
  {
    alert("请输入八位的日期格式，例如20070101！");
    return false;
  }
    }
}
</script>

<body bgcolor="#FFFFFF" text="#000000" onload="loads()" onContextmenu="return false">

<jsp:include page="../../../inc/sort.jsp"><jsp:param name="url" value="to_paymentTaFindAC.do"/></jsp:include>

<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="7"><img src="<%=request.getContextPath()%>/img/table_left.gif" width="7" height="37"></td>
          <td background="<%=request.getContextPath()%>/img/table_bg_line.gif" width="55">&nbsp;</td>
            <td width="235" background="<%=request.getContextPath()%>/img/table_bg_line.gif">&nbsp;</td>
          <td background="<%=request.getContextPath()%>/img/table_bg_line.gif" align="right"> 
            <table width="300" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="37"><img src="<%=request.getContextPath()%>/img/title_banner.gif" width="37" height="24"></td>
                  <td width="148" class=font14 bgcolor="#FFFFFF" align="center"  valign="middle"><font color="00B5DB">缴存管理</font><font color="00B5DB">&gt;&gt;</font><font color="00B5DB">到账确认</font></td>
                <td width="115" class=font14>&nbsp;</td>
              </tr>
            </table>
          </td>
          <td width="9"><img src="<%=request.getContextPath()%>/img/table_right.gif" width="9" height="37"></td>
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
                  <td height="22" bgcolor="#CCCCCC" align="center" width="162"><b class="font14"> 
                    查 询 条 件</b></td>
                  <td height="22" background="<%=request.getContextPath()%>/img/bg2.gif" align="center" width="758">&nbsp;</td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
      <html:form action="/paySuerTaShowOrgAC.do" method="post">
	  <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=0 align="center" > 
        
        <tr id="gjtr">
          <td colspan="4"><table width="100%" border="0" align="center"  cellpadding=0 cellspacing=1 id="table1" >
            <tr>
                  <td width="13%"   class="td1" >单位编号</td>
                  <td width="18%"  ><html:text name="paymentAF" property="orgId" styleClass="input3" styleId="txtsearch" ></html:text></td>
              <td width="11%" class="td1" >单位名称</td>
              <td width="21%"  ><html:text name="paymentAF" property="orgName" styleClass="input3" styleId="txtsearch" ></html:text></td>
            </tr>
			<tr>
                  <td width="13%"   class="td1" >结算号</td>
                  <td width="18%"  ><html:text name="paymentAF" property="noteNum" styleClass="input3" styleId="txtsearch" ></html:text></td>
                  <td width="11%" class="td1" >凭证编号</td>
              <td width="21%"  ><html:text name="paymentAF" property="docNum" styleClass="input3" styleId="txtsearch" ></html:text></td>
            </tr>
			<tr>
                  <td width="13%"   class="td1" >缴存金额</td>
              <td width="18%"  ><html:text name="paymentAF" property="payMoney" styleClass="input3" ></html:text></td>
              <td width="11%" class="td1" >业务日期</td>
              <td width="21%"  ><html:text name="paymentAF" property="settDate" styleClass="input3" styleId="txtsearch" ></html:text></td>
            </tr>
			<tr>
              <td width="13%"   class="td1" > 业务类型</td>
               <td width="18%" > 
                   <html:select property="payType" styleClass="input4" >
                       <html:option value=""></html:option>
                       <html:optionsCollection property="other_map" name="thepaymentAF" label="value" value="key"/>
                   </html:select></td>
                           
              <td width="11%" class="td1" > 业务状态</td>
              <td width="21%"  >
                   <html:select property="payStatus" styleClass="input4" >
                    <html:option value=""></html:option>
                    <html:optionsCollection property="map"  name="thepaymentAF" label="value" value="key"/>
                   </html:select>
              </tr>
			
          </table></td>
        </tr>
      </table>
      <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td align="right">
           <html:submit property="method" styleClass="buttona" onclick="return gotocheck();"><bean:message key="button.search" /></html:submit>
          </td>
        </tr>
      </table> 
      </html:form>
      
	  <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
          <tr> 
            <td class=h4>合计：缴存金额<u>：</u><bean:write name="thepaymentAF" property="sumPayMoney"/></td>
          </tr>
        </table>
	  <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td height="35"> 
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td height="22" bgcolor="#CCCCCC" align="center" width="132"><b class="font14">缴存到账确认列表 </b></td>
                <td width="542" height="22" align="center" background="<%=request.getContextPath()%>/img/bg2.gif">&nbsp;</td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
      
     <html:form action="/paySuerTaOrgPayAccountListAC.do" method="post" >
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr bgcolor="1BA5FF" > 
          <td align="center" height="6" colspan="1" ></td>
        </tr>
      </table>
        <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
          <tr > 
            <td align="center" height="23" bgcolor="C4F0FF" >选项</td>
            <td align="center" class=td2 >
               <a href="javascript:sort('paymentHead.noteNum')">结算号</a>
               <logic:equal name="pagination" property="orderBy" value="paymentHead.noteNum">
          	  <logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
          	  <logic:equal name="pagination" property="orderother" value="DESC">▼</logic:equal>
          	</logic:equal>
            </td>
            <td align="center" class=td2 >
                <a href="javascript:sort('paymentHead.docNum')">凭证编号</a>
                <logic:equal name="pagination" property="orderBy" value="paymentHead.docNum">
          	    <logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
          	    <logic:equal name="pagination" property="orderother" value="DESC">▼</logic:equal>
          	  	</logic:equal>
            </td>
            <td align="center" class=td2 >
                <a href="javascript:sort('org.id')">单位编号</a>
                <logic:equal name="pagination" property="orderBy" value="org.id">
          	    <logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
          	    <logic:equal name="pagination" property="orderother" value="DESC">▼</logic:equal>
          	  	</logic:equal>
            </td>
            <td align="center" class=td2 > 
              <a href="javascript:sort('org.orgInfo.name')">单位名称</a>
                <logic:equal name="pagination" property="orderBy" value="org.orgInfo.name">
          	    <logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
          	    <logic:equal name="pagination" property="orderother" value="DESC">▼</logic:equal>
          	  	</logic:equal>
            </td>
            <td align="center" class=td2 > 
              <a href="javascript:sort('paymentHead.payMoney')">缴存金额</a>
                <logic:equal name="pagination" property="orderBy" value="paymentHead.payMoney">
          	    <logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
          	    <logic:equal name="pagination" property="orderother" value="DESC">▼</logic:equal>
          	  	</logic:equal>
            </td>
            <td align="center" class=td2 >业务类型</td>
            <td align="center" class=td2 >业务日期</td>
            <td align="center" class=td2 >业务状态</td>
          </tr>
          <td>
           <input type="hidden" name="listCount" value="<bean:write name="thepaymentAF" property="list"/>">
           </td>
        <logic:notEmpty name="thepaymentAF" property="list">
								<logic:iterate id="element" name="thepaymentAF" property="list" indexId="i">
								
								<tr id="tr<%=i%>" onclick='gotoClick("tr<%=i %>","s<%=i%>", idAF);gettr("tr<%=i %>");' onMouseOver='this.style.background="#eaeaea"'  onMouseOut='gotoColor("tr<%=i %>","s<%=i %>", idAF);' class=td4  onDblClick=""> 
					 
									    
							<td valign="top">
          <p align="left">
            <input id="s<%=i %>" type="radio" name="id" value="<bean:write name="element" property="id"/>" <%if(new Integer(0).equals(i)) out.print("checked"); %>>
          </p>
          </td> 
          <td valign="top"><p><bean:write name="element" property="noteNum" /></p></td>
          <td valign="top"><p><bean:write name="element" property="docNum" /></p></td>
          <td valign="top"><p><bean:write name="element" property="org.id" format="0000000000" /></p></td>
          <td valign="top"><p><a href="#" onClick="gettr2('tr<%=i %>');"><bean:write name="element" property="org.orgInfo.name" /></a></p></td>
          <td valign="top"><p><bean:write name="element" property="payMoney"/></p></td>
          <td valign="top"><p><bean:write name="element" property="payType"/></p></td>
          <td valign="top"><p><bean:write name="element" property="settDate"/></p></td>
          <td valign="top"><p><bean:write name="element" property="payStatus_"/></p></td>
         <td valign="top" style="display:none"><p><bean:write name="element" property="id"/></p></td>
           </tr>
        <tr > 
          <td colspan="12" class=td5 ></td>
        </tr>
          </logic:iterate>
        </logic:notEmpty>
		
	    <logic:empty name="thepaymentAF" property="list" >
        <tr> 
          <td colspan="11" height="30" style="color:red">没有找到与条件相符合结果！</td>
	    </tr>
		<tr > 
          <td colspan="11" class=td5 ></td>
        </tr>
        </logic:empty>					
        </table>
        
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr class="td1"> 
          <td align="center">
            <table width="585" height="19" border="0" cellpadding="0" cellspacing="0">
          
              
              <tr> 
			  	<td align="left">共<bean:write name="pagination" property="nrOfElements"/> 项</td>
                <td align="right"><jsp:include page="../../../inc/pagination.jsp"><jsp:param name="url" value="to_paymentTaFindAC.do"/></jsp:include></td>
              </tr>
              
            </table>
          </td>
        </tr>
      </table>
  
        <table border="0" cellspacing="0" cellpadding="3" align="center">
          <tr valign="bottom"> 
            <td width="70"> 
              <html:submit property="method" styleClass="buttonb" onclick="return gotoSure(); " ><bean:message key="button.sure.account" /></html:submit>&nbsp;&nbsp;
            </td>
            <td width="70"> 
               <html:submit property="method" styleClass="buttonb" onclick="return gotoDelete(); " ><bean:message key="button.del.account" /></html:submit>
            </td>
          </tr>
        </table>
        </html:form> 
	</td>
  </tr>
</table>

<table>
    <form action="to_paymentTaFindAC.do" method="POST" name="Form1" id="Form1">
    </form>
 </table> 
</body>
</html:html>
