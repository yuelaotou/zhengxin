<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ include file="/checkUrl.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html:html>
<head>
<title>账务处理</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
</head>
<script language="javascript"  src="<%=path%>/js/common.js"></script>
<script language="javascript"  type="text/javascript" >
function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
}
</script>
<script language="javascript"  type="text/javascript" >
function check(){
document.adjustaccountNewAF.elements["totalBalance"].disabled="true";
document.adjustaccountNewAF.elements["adjustWrongAccountTail.emp.empInfo.name"].disabled="true"
document.adjustaccountNewAF.elements["adjustWrongAccountTail.emp.empInfo.cardKind"].disabled="true"
document.adjustaccountNewAF.elements["adjustWrongAccountTail.emp.empInfo.cardNum"].disabled="true"
}
function checkData1() {
var date=document.adjustaccountNewAF.elements["adjustWrongAccountTail.settDate"].value.trim();
var date1=document.adjustaccountNewAF.elements["adjustWrongAccountTail.settDate"];
var moneys=document.adjustaccountNewAF.elements["adjustWrongAccountTail.adjustMoney"].value.trim();
if(document.adjustaccountNewAF.elements["adjustWrongAccountTail.emp.empInfo.name"].value.trim()==""){
	alert("没有该员工");return false;
	}else if (document.adjustaccountNewAF.elements["adjustWrongAccountTail.settDate"].value.trim()==""){
	alert("请输入全信息");return false;
	}else if (document.adjustaccountNewAF.elements["adjustWrongAccountTail.adjustMoney"].value.trim()==""){
	alert("请输入全信息");return false;
	}else if(date!=""){
		var str=IsValidDate(date);
		if(!str){
			alert("日期输入错误！");
			return false;
		}
	}else if (moneys!=""){
		if(isNaN(moneys)){alert("请输入正确数字");return false;}
	}
}
	
function prepareOrgId(){
	var orgId = document.adjustaccountNewAF.orgId.value.trim();
	empFind(orgId,'null','null','null');
}
function empFind(orgid,st,cardnum,empname){
	open_findEmp('<%=path%>',orgid,st,cardnum,empname,'0');
}

function executeAjax(){
//alert("可以到ajax楼");
		var queryString = "adjustaccountTaFindEmpAAC.do?"; 
 		var orgId = document.adjustaccountNewAF.orgId.value.trim();
 		var empId = document.adjustaccountNewAF.elements["adjustWrongAccountTail.empId"].value.trim();
  //  	alert("---+"+orgId+"^^^---"+empId);
     	queryString = queryString + "orgId="+orgId+"&&empId="+empId ; 	 
     	if (empId==""){	     
	   		empFind(orgId,'null','null','null');
	    }else{
	    	findInfo(queryString);
	    }    
}//type 判断错误提示   types下拉列表禁用
function displayEmp(empId,empName,empCardKind,empCardNum,empTotalBalance,type,types){
if(type!=""){//有错误信息
alert(type);
document.adjustaccountNewAF.elements["adjustWrongAccountTail.empId"].value="";
document.adjustaccountNewAF.elements["adjustWrongAccountTail.emp.empInfo.name"].value="";
document.adjustaccountNewAF.elements["adjustWrongAccountTail.emp.empInfo.cardKind"].value="";
document.adjustaccountNewAF.elements["adjustWrongAccountTail.emp.empInfo.cardNum"].value="";
document.adjustaccountNewAF.elements["totalBalance"].value="0.00";
}else{
document.adjustaccountNewAF.elements["adjustWrongAccountTail.empId"].value=empId;
document.adjustaccountNewAF.elements["adjustWrongAccountTail.emp.empInfo.name"].value=empName;
document.adjustaccountNewAF.elements["adjustWrongAccountTail.emp.empInfo.cardKind"].value=empCardKind;
document.adjustaccountNewAF.elements["adjustWrongAccountTail.emp.empInfo.cardNum"].value=empCardNum;
document.adjustaccountNewAF.elements["totalBalance"].value=empTotalBalance;
}
if(types==1){
////下拉列表不可以用
//alert("下拉列表不可以用");
}
document.adjustaccountNewAF.elements["totalBalance"].disabled="true";
document.adjustaccountNewAF.elements["adjustWrongAccountTail.emp.empInfo.name"].disabled="true"
document.adjustaccountNewAF.elements["adjustWrongAccountTail.emp.empInfo.cardKind"].disabled="true"
document.adjustaccountNewAF.elements["adjustWrongAccountTail.emp.empInfo.cardNum"].disabled="true"
}
function gotoEnter(){
var orgId = document.adjustaccountNewAF.orgId.value.trim();
	if(event.keyCode==13){
		event.keyCode = 9;
		if(document.adjustaccountNewAF.elements["adjustWrongAccountTail.empId"].value==""){
	 	empFind(orgId,'null','null','null');
	 	}
		else {
			var queryString = "adjustaccountTaFindEmpAAC.do?"; 
	 		var orgId = document.adjustaccountNewAF.orgId.value.trim();
	 		var empId = document.adjustaccountNewAF.elements["adjustWrongAccountTail.empId"].value.trim();
	     	queryString = queryString + "orgId="+orgId+"&&empId="+empId ; 	 
		    findInfo(queryString);
		}	
	}
}
function checkAjustaMoney(){
var tempAjustaMoney=document.adjustaccountNewAF.elements["adjustWrongAccountTail.adjustMoney"].value.trim();
if(document.adjustaccountNewAF.elements["adjustWrongAccountTail.adjustMoney"].value.trim()==""){
document.adjustaccountNewAF.elements["adjustWrongAccountTail.adjustMoney"].focus();
alert("请添加调整金额!")
return false;
}else{
      checkMoney1(tempAjustaMoney);
      var totalBalance=document.adjustaccountNewAF.elements["totalBalance"].value.trim()
	  var tempadjustaccount=document.adjustaccountNewAF.elements["adjustWrongAccountTail.adjustMoney"].value.trim();
	  var lastMoney=parseInt(totalBalance)+parseInt(tempadjustaccount);
	  if(lastMoney<parseInt(0)){
	  tempadjustaccount=document.adjustaccountNewAF.elements["adjustWrongAccountTail.adjustMoney"].focus();
	  alert('调整金额有误!请查看后再提交.')
	  return false;
	  }
	  return true;
}
}
//判断金额
function checkMoney1(money){
var salarybase = money.match(/^([0-9]{1,10})(\.[0-9]{1,2})?$/)
if (salarybase==null)
			{	
				return false;
			}else{
				return true;
			}

}

</script>


<body bgcolor="#FFFFFF" text="#000000" onload="return check()"  onContextmenu="return false">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="7"><img src="<%=path%>/img/table_left.gif" width="7" height="37"></td>
          <td background="<%=path%>/img/table_bg_line.gif" width="55">&nbsp;</td>
          <td width="235" background="<%=path%>/img/table_bg_line.gif">&nbsp;</td>
          <td background="<%=path%>/img/table_bg_line.gif" align="right"> 
            <table width="300" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="37"><img src="<%=path%>/img/title_banner.gif" width="37" height="24"></td>
                <td width="148" class=font14 bgcolor="#FFFFFF" align="center" valign="bottom">
                <font color="00B5DB">账务处理</font><font color="00B5DB">&gt;&gt;</font><font color="00B5DB">错账调整</font></td>
                <td width="115" class=font14>&nbsp;</td>
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
    <html:form action="/adjustaccountTaSaveAC.do" style="margin: 0" >
      <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td height="35"> 
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td height="22" bgcolor="#CCCCCC" align="center" width="117"><b class="font14">调 整 信 息</b></td>
                <td height="22" background="<%=path%>/img/bg2.gif" align="center">&nbsp;</td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
      <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=3 align="center" >
      <html:hidden name="adjustaccountNewAF" property="orgId"  styleClass="input3"/>
        <tr> 
          <td   class="td1" width="17%">职工编号<font color="#FF0000">*</font></td>
          <td width="23%"  > 
            <html:text name="adjustaccountNewAF" property="adjustWrongAccountTail.empId" ondblclick="executeAjax()" styleClass="input3" onkeydown="gotoEnter();"/>
                   </td>
          <td width="10%"  >
            <input type="button" class="buttona" value="..." name="button322" onclick="prepareOrgId()">          </td>
          <td class="td1" width="17%" >职工姓名</td>
          <td width="33%"  > 
            <html:text name="adjustaccountNewAF" property="adjustWrongAccountTail.emp.empInfo.name"  styleClass="input3"/>      </td>
        </tr>
        <tr> 
          <td   class="td1" width="17%">证件类型</td>
          <td colspan="2"  > 
          <html:text name="adjustaccountNewAF" property="adjustWrongAccountTail.emp.empInfo.cardKind"  styleClass="input3"/>
                      </td>
          <td class="td1" width="17%" >证件号码</td>
          <td width="33%"  > 
           <html:text name="adjustaccountNewAF" property="adjustWrongAccountTail.emp.empInfo.cardNum"  styleClass="input3"/>          </td>
        </tr>
        <tr> 
          <td   class="td1" width="17%">账户余额</td>
          <td colspan="2"   > 
            <html:text name="adjustaccountNewAF" property="totalBalance"  styleClass="input3"/>
           
                       </td>
          <td class="td1" width="17%"   >错账日期<font color="#FF0000">*</font></td>
          <td width="33%"   > 
            <html:text name="adjustaccountNewAF" property="adjustWrongAccountTail.settDate"  styleClass="input3" maxlength="8"/>
                   </td>
        </tr>
        <tr> 
          <td width="17%"   class="td1">调整金额<font color="#FF0000">*</font></td>
          <td width="33%" colspan="2"  > <html:text name="adjustaccountNewAF" property="adjustWrongAccountTail.adjustMoney" onblur="return checkAjustaMoney()" styleClass="input3"/></td>
          <td width="17%" class="td1" >调整业务类型<font color="#FF0000">*</font></td>
          <td width="33%"  >   
          <logic:equal name="adjustaccountNewAF" property="type" value="1">
            <html:select  name="adjustaccountNewAF" property="typelist" disabled="true" styleClass="input4" >
            <html:optionsCollection property="sexMap" name="adjustaccountNewAF" label="value" value="key"/>
            </html:select>
           </logic:equal>
             <logic:equal name="adjustaccountNewAF" property="type" value="2">
            <html:select  name="adjustaccountNewAF" property="typelist"  styleClass="input4">
            <html:optionsCollection property="sexMap" name="adjustaccountNewAF" label="value" value="key"/>
            </html:select>
            </logic:equal>
            </td>
        </tr>
        <tr> 
          <td   class="td1" width="17%">错账原因</td>
          <td colspan="2"   > 
            <html:text name="adjustaccountNewAF" property="adjustWrongAccountTail.reason"  styleClass="input3"/>
          </td>
        </tr>
        <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
          <tr valign="bottom"> 
            
          <td  colspan="7" bgcolor="#FFFFFF" align="center" height="30"> 
            <table border="0" cellspacing="0" cellpadding="0">
                <tr> 
                  <td width="70">             
                  <html:submit property="method" styleClass="buttona" onclick="return checkData1()"><bean:message key="button.add"/></html:submit>
                  </td>
                  <td width="70"> 
                   <html:button property="method" styleClass="buttona" onclick="javascript:history.back()"><bean:message key="button.back"/></html:button>
                  </td>
                </tr>
            </table>
            </td>
          </tr>
          
          
      </table>
      </table>
      
    </td>
    </html:form>
  </tr>
</table>
</body>
</html:html>
