<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ page import="java.util.List"%>
<%@ page import="org.xpup.hafmis.orgstrct.domain.enums.DutyEnum"%>
<%@ page import="org.xpup.common.enums.SexEnum"%>

<%
	List sexes = SexEnum.getEnumList();
	List duties = DutyEnum.getEnumList();
	pageContext.setAttribute("sexes",sexes);
	pageContext.setAttribute("duties",duties);
	String path=request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户维护</title>
<link href="../css/common.css" rel="stylesheet" type="text/css" />
<script src="..//js/common.js" type="text/javascript" language="javascript1.2"></script>
<script language="JavaScript1.2">
<!-- Begin
function reportErrors() {
  <logic:messagesPresent>
  var errors = "<html:messages id="error" ><bean:write name="error" />\n</html:messages>"
  alert(errors);
  </logic:messagesPresent>
}

function cancel() {
    var v_money = document.hafEmployeeAF.elements["hafEmployee.checkMoney"].value.trim();
	if(isNaN(v_money)){
		alert("请输入正确金额！");
		return false;
	}
	document.location.href="show_employees.do"
}

function init() {
	reportErrors();
	document.all("username").focus();
}

function validate(f) {
	if(!validateHafEmployeeAF(f))
	  return false;
	if('<bean:write name="hafEmployeeAF" property="activity" />'=='CREATE') {
		if(confirm("您是否要继续添加？")) {
			document.hafEmployeeAF.createAgain.value="true";
		}
		else {
			document.hafEmployeeAF.createAgain.value="false";
		}
	}
	return true;
}

document.onkeypress = function() {
	if (event.ctrlKey) {
		hafEmployeeAF.submit();
	}
	
	if(event.keyCode == 27) {
		cancel();
	}
}

function openOrgTree() {
	//window.open('show_orgtree.do');
	var arr = new Array();
	window.showModalDialog("show_employee_orgtree.do",arr,"dialogWidth=400px;dialogHeight=300px;status=no;scroll=no;");
	if(arr[0] != undefined) {
	    document.all("orgUnitName").value=arr[0];
	    document.all("orgUnitId").value=arr[1];
	}
}

// End -->
function checkDate(date)
{
  var strDate = date.value;
  if(strDate.length!=8)
  {
    alert("请输入八位的日期格式，例如20070101！");
    return false;
  }
  if(strDate.substring(0,4)<1900){
    alert("年份应该大于1900！");
    return false;
  }
  if(strDate.substring(4,6)>12 || strDate.substring(4,6)<1)
  {
    alert("月份应该在1-12月之间！");
    return false;
  }
  var tempStrDate=strDate.substring(6,8);
  var tempStrMonth=strDate.substring(4,6);
 if(tempStrMonth==2&&tempStrDate>29)
  {
    alert("日期不能大于29！");
    return false;
  }else if((tempStrMonth==1||tempStrMonth==3||tempStrMonth==5||tempStrMonth==7||tempStrMonth==8||tempStrMonth==10||tempStrMonth==12)&&tempStrDate>31){
    alert("日期不能大于31！");
    return false;
  }else if((tempStrMonth==2||tempStrMonth==4||tempStrMonth==6||tempStrMonth==9||tempStrMonth==11)&&tempStrDate>30){
    alert("日期不能大于30！");
    return false;
  }
  return true;
}
function checkData(date){
  if(!checkDate(date)){
    document.all("hafEmployee.bizDate").focus();
  }
}
function gotoMoney(money){
	var v_money = money.value;
	if(isNaN(v_money)){
		alert("请输入正确金额！");
		document.hafEmployeeAF.elements["hafEmployee.checkMoney"].value="";
		document.hafEmployeeAF.elements["hafEmployee.checkMoney"].focus();
		return false;
	}
}
</script>
</head>
<body onload="init()">
<html:form action="/create_employee" onsubmit="return validate(this);">
  <table cellspacing="1" class="basicinfo">
    <thead>
      <tr>
        <th colspan="4">账户信息</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <th width="25%"><label for="username">登录名称：</label></th>
        <td width="25%"><html:text property="hafEmployee.username" styleId="username" styleClass="text" onkeydown="enterToTab()" /></td>
        <logic:equal value="CREATE" property="activity" name="hafEmployeeAF" >
        <th width="25%"><label for="password">登录密码：</label></th>
        <td width="25%"><html:password property="hafEmployee.password" styleId="password" styleClass="text" onkeydown="enterToTab()" /></td>
        </logic:equal>
        <logic:notEqual value="CREATE" property="activity" name="hafEmployeeAF" >
        <td colspan="2"></td>
        </logic:notEqual>
      </tr>
      <tr>
        <th width="25%"><label for="enabled">账户状态：</label></th>
        <td width="25%"><html:select property="hafEmployee.enabled" styleId="enabled" styleClass="text" onkeydown="enterToTab()" >
        	<html:option value="true">激活</html:option>
        	<html:option value="false">禁用</html:option>
          </html:select></td>
        <td colspan="2"></td>
      </tr>
    </tbody>
    <thead>
      <tr>
        <th colspan="4">基本信息</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <th width="25%"><label for="realName">真实名称：</label></th>
        <td width="25%"><html:text property="hafEmployee.realName" styleId="realName" styleClass="text" onkeydown="enterToTab()" /></td>
        <th width="25%"><label for="sex">性别：</label></th>
        <td width="25%">
          <html:select property="sex" styleId="sex" styleClass="text" onkeydown="enterToTab()" >
            <html:option value="-1">&nbsp;</html:option>
        	<html:optionsCollection name="sexes" label="name" value="value"/>
          </html:select>
        </td>
      </tr>
      <tr>
        <th width="25%"><label for="duty">职位：</label></th>
        <td width="25%">
          <html:select property="duty" styleId="duty" styleClass="text" onkeydown="enterToTab()" >
            <html:option value="-1">&nbsp;</html:option>
        	<html:optionsCollection name="duties" label="name" value="value"/>
          </html:select></td>
        <th width="25%"><label for="email">电子邮箱：</label></th>
        <td width="25%"><html:text property="hafEmployee.email" styleId="email" styleClass="text" onkeydown="enterToTab()" /></td>
      </tr>
      <tr>
        <th width="25%"><label for="orgUnitName">所属部门：</label></th>
        <td width="25%" nowrap="nowrap">
          <html:text property="hafEmployee.organizationUnit.name" styleId="orgUnitName" readonly="true" style="width:80%" ondblclick="openOrgTree();" />
          <input type="button" value="..." style="width:26px" onclick="openOrgTree();" />
        </td>
        <th width="25%"><label for="bizDate">公积金会计日期：</label></th>
        <td width="25%" nowrap="nowrap">
          <html:text property="hafEmployee.bizDate" styleId="bizDate"  style="width:80%" onchange="checkData(this);"/>        
        </td>       
      </tr>
      <tr>
        <th width="25%"><label for="plbizDate">贷款会计日期：</label></th>
         <td width="25%" nowrap="nowrap">
          <html:text property="hafEmployee.plbizDate" styleId="plbizDate"  style="width:80%" onchange="checkData(this);"/>        
        </td>
        <th width="25%"><label for="fbizDate">财务会计日期：</label></th>
        <td width="25%" nowrap="nowrap">
          <html:text property="hafEmployee.fbizDate" styleId="fbizDate"  style="width:80%" onchange="checkData(this);"/>  
          <input type="hidden" id="orgUnitId" name="orgUnitId" value="<bean:write name="hafEmployeeAF" property="hafEmployee.organizationUnit.id"/>" />      
        </td>
      </tr> 
      <tr>
        <th width="25%"><label for="checkMoney">预审金额：</label></th>
         <td width="25%" nowrap="nowrap">
          <html:text property="hafEmployee.checkMoney" styleId="checkMoney"  style="width:80%" onchange="gotoMoney(this);"/>        
        </td>
        <th width="25%">&nbsp;</th>
        <td width="25%" nowrap="nowrap">
          
        </td>
      </tr>       
    </tbody>
    <tfoot>
      <tr>
        <td colspan="4">
          <html:hidden property="hafEmployee.id"/>
          <html:hidden property="activity" />
          <html:hidden property="createAgain" />
          <html:submit styleClass="button" >确定</html:submit>
          <input type="button" value="取消" onclick=" return cancel();" class="button" />
        </td>
      </tr>
    </tfoot>
  </table>
</html:form>
</body>
</html>
<html:javascript formName="hafEmployeeAF" />