<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ include file="/checkUrl.jsp"%>
<html:html>
<head>
<title>变更业务>缴额调整
</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/index.css" type="text/css">
</head>
<script language="javascript" src="<%=request.getContextPath()%>/js/common.js">

<script language="javascript" ></script>

<script language="javascript"  type="text/javascript" >
function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
	   var empid=document.chgpayNewAF.elements["chgPaymentTail.empId"].value;
	   if(empid!=''){
		document.chgpayNewAF.elements["chgPaymentTail.empId"].value=format(document.chgpayNewAF.elements["chgPaymentTail.empId"].value)+document.chgpayNewAF.elements["chgPaymentTail.empId"].value;
	   }
}

function backErrors(errors){
   alert(errors);
}

function getEnter()
{
   if(event.keyCode==13)
   {
    event.keyCode=9
   var empid=document.chgpayNewAF.elements["chgPaymentTail.empId"].value;
     if(empid==""){
  var orgId=document.chgpayNewAF.elements["orgId"].value;
	open_findEmp('<%=request.getContextPath()%>',orgId,'1','null','null','0');
	   return false;
	}
    var queryString = "chgpayEmpTaFindAAC.do?";    
    queryString = queryString + "chgPaymentTail.empId="+empid;
     findInfo(queryString);
  }
}


function toPop(status){
  var orgId=document.chgpayNewAF.elements["orgId"].value;
	open_findEmp('<%=request.getContextPath()%>',orgId,'1','null','null','0');
}
function executeAjax()
  {
    var empid=document.chgpayNewAF.elements["chgPaymentTail.empId"].value;
     if(empid==""){
  var orgId=document.chgpayNewAF.elements["orgId"].value;
	open_findEmp('<%=request.getContextPath()%>',orgId,'1','null','null','0');
	   return false;
	}
    var queryString = "chgpayEmpTaFindAAC.do?";    
    queryString = queryString + "chgPaymentTail.empId="+empid;
     findInfo(queryString);
  }
function displays(employee_name,card_kind,card_number,birthday,sex,oldorgpay,pldemppay,salaryBase) {
	document.chgpayNewAF.elements["chgPaymentTail.empId"].value=format(document.chgpayNewAF.elements["chgPaymentTail.empId"].value)+document.chgpayNewAF.elements["chgPaymentTail.empId"].value;
	document.chgpayNewAF.elements["chgPaymentTail.emp.empInfo.name"].value=employee_name;
    document.chgpayNewAF.elements["chgPaymentTail.emp.empInfo.cardKind"].value=card_kind;
	document.chgpayNewAF.elements["chgPaymentTail.emp.empInfo.cardNum"].value=card_number;
    document.chgpayNewAF.elements["chgPaymentTail.emp.empInfo.birthday"].value=birthday;
	document.chgpayNewAF.elements["chgPaymentTail.emp.empInfo.sex"].value=sex;
	document.chgpayNewAF.elements["chgPaymentTail.oldOrgPay"].value=oldorgpay;
	document.chgpayNewAF.elements["chgPaymentTail.oldEmpPay"].value=pldemppay;
	document.chgpayNewAF.elements["chgPaymentTail.emp.salaryBase"].value=salaryBase;
    document.chgpayNewAF.elements["chgPaymentTail.emp.salaryBase"].focus();
}

function checkData() {

	 var empid=document.chgpayNewAF.elements["chgPaymentTail.empId"].value;
     if(empid==""){
	   alert("请录入职工编号");
	   return false;
	}
	var salaryBase=document.chgpayNewAF.elements["chgPaymentTail.emp.salaryBase"].value;
	  if(!checkMoney(salaryBase)){
         return false;
       }
     if(salaryBase=="" || salaryBase<0){
	   alert("新工资基数是否填入，新工资基数要〉=0");
	   return false;
	}
	   var orgPay=document.chgpayNewAF.elements["chgPaymentTail.orgPay"].value;
	   if(!checkMoney(orgPay)){
         return false;
       }
     if(orgPay=="" || orgPay<0){
	   alert("新单位缴额是否填入，新单位缴额要〉=0");
	   return false;
	}	  
	 var empPay=document.chgpayNewAF.elements["chgPaymentTail.empPay"].value;
	
	  if(!checkMoney(empPay)){
         return false;
       }
     if(empPay=="" || empPay<0){
	   alert("新职工缴额是否填入，新职工缴额要〉=0");
	   return false;
	}
}
function goEnter2(){
if(event.keyCode==13){
		event.keyCode=9;
		document.chgpayNewAF.elements["chgPaymentTail.orgPay"].focus();
	}
}
function goEnter3(){
if(event.keyCode==13){
		event.keyCode=9;
		document.chgpayNewAF.elements["chgPaymentTail.empPay"].focus();
	}
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onload="return reportErrors()" onContextmenu="return false">
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
                <td width="216" class=font14 bgcolor="#FFFFFF" align="center" valign="bottom"><font color="00B5DB"> 变更业务 &gt; 缴额调整 </font></td>
                <td width="47" class=font14>&nbsp;</td>
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
    <html:form action="/chgpayTaSaveAC.do" style="margin: 0" >
      <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td height="35"> 
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td height="22" bgcolor="#CCCCCC" align="center" width="117"><b class="font14">基 本 信 息</b></td>
                <td height="22" background="<%=request.getContextPath()%>/img/bg2.gif" align="center">&nbsp;</td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
      <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=3 align="center" >
        <tr> 
          <td width="17%" class="td1">职工编号</td>
          <logic:equal name="chgpayNewAF" property="type" value="1">
          <td width="22%"> 
          <html:text name="chgpayNewAF" property="chgPaymentTail.empId"  onkeydown="getEnter()" styleClass="input3"   ondblclick="executeAjax();"  styleId="txtsearch"></html:text>
          </td>
            <td width="11%"   > 
              <input type="button" name="Submit3222" value="..." class="buttona" onclick="toPop(2)">
            </td>
          </logic:equal>
          <logic:equal name="chgpayNewAF" property="type" value="2">
          <td width="22%"> 
          <html:text name="chgpayNewAF" property="chgPaymentTail.empId"  styleClass="input3" readonly="true"   styleId="txtsearch"></html:text>
          
          </td>
          <td width="11%">             
          </td>
          </logic:equal>
         <td width="17%" class="td1"  ></td>
          <td width="33%" colspan="2"   > 
         
          </td>
        </tr>
        <tr> 
        <td width="17%" class="td1"  >职工姓名</td>
          <td width="33%" colspan="2"   > 
            <html:text name="chgpayNewAF" property="chgPaymentTail.emp.empInfo.name" readonly="true" styleClass="input3"/>
         <html:hidden  name="chgpayNewAF" property="orgRate"  styleClass="input3"    styleId="txtsearch"></html:hidden>
           <html:hidden  name="chgpayNewAF" property="empRate"  styleClass="input3"    styleId="txtsearch"></html:hidden>
             <html:hidden  name="chgpayNewAF" property="orgId"  styleClass="input3"    styleId="txtsearch"></html:hidden>
          </td>
        
        
         <td width="17%" class="td1"  >证件类型 </td>
          <td width="33%" colspan="2"   >  
          <html:select name="chgpayNewAF" property="chgPaymentTail.emp.empInfo.cardKind" styleClass="input4" ><html:optionsCollection property="documentsstateMap" name="chgpayNewAF" label="value" value="key"/></html:select>
          </td>
        
        </tr>
       
            <tr> 
            
              <td width="17%" class="td1"  >证件号码 </td>
          <td width="33%"  colspan="2"> 
            <html:text name="chgpayNewAF" property="chgPaymentTail.emp.empInfo.cardNum" readonly="true" styleClass="input3"/>
          </td>
            
          <td width="17%" class="td1"  >&#20986;&#29983;&#26085;&#26399;<font color="#FF0000"></font><br></td>
          <td width="33%" colspan="2"   > 
           <html:text name="chgpayNewAF" property="chgPaymentTail.emp.empInfo.birthday" readonly="true"  styleClass="input3"/>
          </td>
         
        </tr>
        <tr> 
         <td width="17%" class="td1"  >性别<font color="#FF0000"> </font></td>
          <td width="33%"  colspan="2"> 
          <html:select name="chgpayNewAF" property="chgPaymentTail.emp.empInfo.sex" styleClass="input4" ><html:optionsCollection property="sexMap" name="chgpayNewAF" label="value" value="key"/></html:select>
          </td>
         
         <td width="17%" class="td1"  >工资基数<font color="#FF0000">*</font></td>
          <td width="33%" colspan="2" > 
           <logic:equal name="chgpayNewAF" property="type" value="1">
            <html:text name="chgpayNewAF" property="chgPaymentTail.emp.salaryBase" maxlength="10" styleClass="input3" onkeydown="goEnter2();"/>
            </logic:equal>
             <logic:equal name="chgpayNewAF" property="type" value="2">
            <html:text name="chgpayNewAF" property="chgPaymentTail.emp.salaryBase" maxlength="10" styleClass="input3" onkeydown="goEnter2();"/>
            </logic:equal>
          </td>
        
        
          
        </tr>
        <tr> 
        
          <td width="17%" class="td1"  >原单位缴额 </td>
          <td width="33%" colspan="2"   > 
            <html:text name="chgpayNewAF" property="chgPaymentTail.oldOrgPay" readonly="true" styleClass="input3"/>
          </td>
        
        <td width="17%" class="td1"  >原职工缴额 </td>
          <td width="33%"  colspan="2"> 
            <html:text name="chgpayNewAF" property="chgPaymentTail.oldEmpPay" readonly="true" styleClass="input3"/>
          </td>
        
        
          
        </tr>
        <tr> 
          <td width="17%" class="td1"  >新单位缴额<font color="#FF0000">*</font></td>
          <td width="33%" colspan="2"   > 
            <html:text name="chgpayNewAF" property="chgPaymentTail.orgPay" maxlength="10" styleClass="input3" onkeydown="goEnter3();"/>
          </td>
          <td width="17%" class="td1"  >新职工缴额<font color="#FF0000">*</font></td>
          <td width="33%"  colspan="2"> 
           <html:text name="chgpayNewAF" property="chgPaymentTail.empPay" maxlength="10" styleClass="input3" styleId="txtsearch"/>
          </td>
        </tr>
      </table>     
<table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr valign="bottom"> 
          <td  colspan="7" bgcolor="#FFFFFF" align="center" height="30"> 
            <table border="0" cellspacing="0" cellpadding="0">
              <tr>
              <td><html:hidden name="chgpayNewAF" property="chgPaymentTail.id"/></td>
                <td><logic:equal name="chgpayNewAF" property="type" value="1"><html:submit property="method" styleClass="buttona" onclick="return checkData()"><bean:message key="button.add"/></html:submit></logic:equal></td>
                <td><logic:equal name="chgpayNewAF" property="type" value="2"><html:submit property="method" styleClass="buttona" onclick="return checkData()"><bean:message key="button.update"/></html:submit></logic:equal></td>
                <td><html:submit property="method" styleClass="buttona"><bean:message key="button.back"/></html:submit></td>    
			  </tr>
            </table>    

    </td>
  </tr>
</table>
      </html:form>
   
</body>
</html:html>
