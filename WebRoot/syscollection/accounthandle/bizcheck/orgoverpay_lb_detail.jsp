<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ page import="org.xpup.hafmis.syscollection.accounthandle.bizcheck.action.BizcheckTaWindowAC" %>
<%@ include file="/checkUrl.jsp"%>
<%
   Object pagination= request.getSession(false).getAttribute(BizcheckTaWindowAC.PAGINATION_KEY);
   request.setAttribute("pagination",pagination);
   String path=request.getContextPath();
 %>
<html:html>
<head>
<title>单位挂账</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
<script src="<%=path%>/js/common.js">
</script>
</head>
<script type="text/javascript">
var s1="";
var s2="";
var s3="";
var s4="";
function executeAjax() {

doRequestUsingGET();
}

//

var xmlHttp;

function createXMLHttpRequest() {
    if (window.ActiveXObject) {
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    } 
    else if (window.XMLHttpRequest) {
        xmlHttp = new XMLHttpRequest();
    }
}

function doRequestUsingGET() {
	
 	createXMLHttpRequest();
 
    var queryString = "/hafmis/syscollection/paymng/orgoverpay/action/orgoverpayTaFindAAC.do?";
    var orgId = document.getElementById("orgId").value.trim();   
    queryString = queryString + "orgId="+orgId; 
    if(orgId!=""){
	    xmlHttp.onreadystatechange = handleStateChange;
	    xmlHttp.open("GET", queryString, true);
	    xmlHttp.send(null);   
    }
}

function handleStateChange() {
  if(xmlHttp.readyState == 4) {
      if(xmlHttp.status == 200) {
         var x=xmlHttp.responseText;
         eval(x);
      }
   }
}
function showlist() {
  document.Form1.submit();
}

function displays(unitName,docNumber){
  
  document.getElementById("unitName").value=unitName;
  document.getElementById("banlance").value=banlance;

  showlist() 
}

function gotoSelect(){

gotoOrgpop("" ,"/hafmis");
}


function loads(){
document.getElementById("confirm").enable="true";
}

function validateBanlance(){

if((document.getElementById("amount")!=null)&&(document.getElementById("amount")!=""))
   {
     var banlance=document.getElementById("banlace").value;
     var amount=document.getElementById("amount").value;
     var total=banlance+amount;
     if(total>=0){
     return true;
     }else{
     alert("挂账金额小于0");
     return false;
     }
   
   }else{
   alert("请输入挂账金额");
   return false;
   }

}
function executeAjax() {
     var queryString = "bizcheckTaRemotePrintAAC.do";
	 findInfo(queryString);
}
function display(headid,url,type){
	document.URL='<%=path%>/syscollection/paymng/orgoverpay/orgoverpayTbMXPrintAC.do?paymentid='+headid+'&url=<%=path %>'+url+'';
}
</script>
<body bgcolor="#FFFFFF" text="#000000"  onContextmenu="return false">
<jsp:include page="../../../inc/sort.jsp"><jsp:param name="url" value="OrgoverpayTaShowAC.do"/></jsp:include>

<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="7"><img src="<%=path%>/img/table_left.gif" width="7" height="37"></td>
          <td background="<%=path%>/img/table_bg_line.gif" width="55">&nbsp;</td>
            <td width="235" background="<%=path%>/img/table_bg_line.gif"> 
              <table border="0" cellspacing="0" cellpadding="0">

              </table>
            </td>
          <td background="<%=path%>/img/table_bg_line.gif" align="right"> 
            <table width="300" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="37"><img src="<%=path%>/img/title_banner.gif" width="37" height="24"></td>
                  <td width="136" class=font14 bgcolor="#FFFFFF" align="center" valign="bottom"><font color="00B5DB"> 业务复核 &gt; 详细信息</font></td>
                  <td class=font14>&nbsp;</td>
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
                <td height="22" bgcolor="#CCCCCC" align="center" width="117"><b class="font14">业务复核信息</b></td>
                <td height="22" background="<%=path%>/img/bg2.gif" align="center">&nbsp;</td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
	  

	 <html:form action="/bizcheckTaWindowOrgoverPrintAC.do">
	 <html:hidden name="bizcheckDetailAF" property="headid" />
        <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=3 align="center" >
          <tr> 
            <td width="17%" class="td1"  >单位编号：</td>
            <td width="22%"   > 
              <html:text name="bizcheckDetailAF" property="traninId"  styleClass="input3" onkeydown="gotoEnter();" styleId="txtsearch" readonly="true" /> 
            </td>
            <td width="17%" class="td1"  > 单位名称： </td>
            <td  width="33%"  > 
              <html:text name="bizcheckDetailAF" property="traninName"  styleClass="input3" styleId="unitName" readonly="true"/>
            </td>
          </tr>
          <tr> 
            <td width="17%"   class="td1">结算号：</td>
            <td width="33%" > 
              <html:text name="bizcheckDetailAF" property="noteNum"  styleClass="input3" styleId="noteNum" readonly="true"/>
            </td>
            <td class="td1" width="17%" >挂账余额：</td>
            <td width="33%"  >
              <html:text name="bizcheckDetailAF" property="banlance"  styleClass="input3" styleId="banlance" readonly="true"/>
            </td>
          </tr>
          <tr>
         <td class="td1" width="17%" >挂账金额：</td>
            <td width="33%"  >
              <html:text name="bizcheckDetailAF" property="amount"  styleClass="input3" styleId="amount" readonly="true"/>
            </td>
          </tr>
           <tr>
           <td class="td1" width="17%" rowspan="3" >挂账原因：</td>
           <td colspan="4" width="50%" rowspan="3" >
           <html:textarea name="bizcheckDetailAF" property="reason" cols="50" rows="4" styleId="reason" readonly="true" ></html:textarea>
           </td>
          </tr>
        </table>
        <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr valign="bottom"> 
          <td  colspan="7" bgcolor="#FFFFFF" align="center" height="30"> 
              <table border="0" cellspacing="0" cellpadding="0">
                <tr><!-- 
                  <td width="70"> 
                   <input type="button" name="Submit42" value="打印" class="buttona" onclick="executeAjax();">    
                   --><td width="70"> 
                    <input type="button" name="Submit42" value="关闭" class="buttona" onClick="javascript:window.close();" ></td>
              </table>
    </td>
  </tr>
</table>

        </html:form>
        </td>
        </tr>
        </table>
        </html:html>