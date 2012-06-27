<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ include file="/checkUrl.jsp"%>
<%
String path=request.getContextPath();
%>
<html:html>
<head>
<title>提取特权维护&gt;&gt;提取特权维护</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
<script language="javascript" src="<%=path%>/js/common.js" ></script>
</head>
<% 
response.setHeader("Pragma","No-cache"); 
response.setHeader("Cache-Control","no-cache"); 
response.setDateHeader("Expires", 0); 
%> 
<script language="javascript" >
function skip(){
	var empId = document.forms[0].elements["specialPick.emp.empId"].value.trim();
	//alert("emp-->"+empId);
	window.open('../pickup/pageSkipAC.do?empId='+empId,'','width=700,height=450,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',scrollbars=yes');
	return false ;
}
var s1="";
var s2="";
var s3="";
var s5="";
function onload(){
	for(var i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="button"){
			if(document.all.item(i).value=="..."){
				s1=i;
			}
		}
		if(document.all.item(i).type=="button"){
			if(document.all.item(i).value=="..."){
				s2=i;
			}
		}
		if(document.all.item(i).type=="submit"){
			if(document.all.item(i).value=="提取情况"){
				s3=i;
			}
			//if(document.all.item(i).value=="还款情况"){
			//	s4=i;
			//}
			if(document.all.item(i).value=="确定"){
				s5=i;
			}
		}
	}
	document.forms[0].elements["specialPick.org.id"].value="";
	document.forms[0].elements["specialPick.org.orgInfo.name"].value="";
	document.forms[0].elements["specialPick.emp.empId"].value="";
	document.forms[0].elements["specialPick.emp.empInfo.name"].value="";
	document.forms[0].elements["specialPick.emp.empInfo.TEMP_cardKind"].value="";
	document.forms[0].elements["specialPick.emp.empInfo.cardNum"].value="";
	document.forms[0].elements["specialPick.emp.curBalance"].value="";
	document.forms[0].elements["specialPick.emp.preBalance"].value="";
	document.forms[0].elements["specialPick.emp.balance"].value="";
	document.all.item(s1).disabled="";
	document.all.item(s2).disabled="true";
	document.all.item(s3).disabled="true";
	//document.all.item(s4).disabled="true";
	document.all.item(s5).disabled="true";
}
/*查询时使用*/
var flag;
var orgcode;
var flag1;
var f;
var f1;
function toPop1() {
	gotoOrgpop(2,'<%=path%>','0');
	flag=1;
}
function toPop2() {
	var orgID = document.forms[0].elements["specialPick.org.id"].value.trim();
	open_findEmp('<%=path%>',orgID,'null','null','null','0');
	flag=2;
}
function executeAjax(){
	if(flag==1)
	{
		var queryString = "spePickfindAAC.do?";
        var id = document.forms[0].elements["specialPick.org.id"].value.trim();
		queryString = queryString + "id="+id; 
		orgcode=id;
		findInfo(queryString);
    }
    if(flag==2)
    {
    	var queryString = "spePickfindempAAC.do?";
    	var id = document.forms[0].elements["specialPick.emp.empId"].value.trim();
    	var orgid = document.forms[0].elements["specialPick.org.id"].value.trim();
    	queryString = queryString + "id="+orgid+"&orgid="+orgcode; 	
    	findInfo(queryString);
    }
}
/*失去焦点*/
function b(){
	var queryString = "spePickfindempAAC.do?";
    		var id = document.forms[0].elements["specialPick.emp.empId"].value.trim();
    		if(id==""){
			alert("请你输入编号");
			}
			else if(isNaN(id) || id.indexOf(".")>0){
			alert("请你输入合法的整数数字");
			}
			else{
    			var orgid = document.forms[0].elements["specialPick.org.id"].value.trim();
    			queryString = queryString + "id="+id+"&orgid="+orgcode; 	
    			findInfo(queryString);
    		}
}
/*回车时使用*/
function findEmployeeInfo(){
		if(flag1==1)
		{
			var queryString = "spePickfindAAC.do?";
    		var id = document.forms[0].elements["specialPick.org.id"].value.trim();
    		if(id==""){
			alert("请你输入编号");
			}
			else if(isNaN(id) || id.indexOf(".")>0){
			alesrt("请你输入合法的整数数字");
			}
			else{
				queryString = queryString + "id="+id; 	   
				orgcode=id;  
				findInfo(queryString);
			}
		}
		if(flag1==2)
		{
			var queryString = "spePickfindempAAC.do?";
    		var id = document.forms[0].elements["specialPick.emp.empId"].value.trim();
    		if(id==""){
			alert("请你输入编号");
			}
			else if(isNaN(id) || id.indexOf(".")>0){
			alert("请你输入合法的整数数字");
			}
			else{
    			var orgid = document.forms[0].elements["specialPick.org.id"].value.trim();
    			queryString = queryString + "id="+id+"&orgid="+orgcode; 	
    			findInfo(queryString);
    		}
		}
}
/*双击时使用*/
function findOrg() {
   		var queryString = "spePickfindAAC.do?";
        var id = document.forms[0].elements["specialPick.org.id"].value.trim();
        if(id==""){
		alert("请你输入编号");
		}
		else if(isNaN(id) || id.indexOf(".")>0){
		alert("请你输入合法的整数数字");
		}
		else{
			queryString = queryString + "id="+id; 
			orgcode=id;	     
	    	findInfo(queryString);
	    }
}
function findEmp(){
		var queryString = "spePickfindempAAC.do?";
    	var id = document.forms[0].elements["specialPick.emp.empId"].value.trim();
    	if(id==""){
		alert("请你输入编号");
		}
		else if(isNaN(id) || id.indexOf(".")>0){
		alert("请你输入合法的整数数字");
		}
		else{
    		var orgid = document.forms[0].elements["specialPick.org.id"].value.trim();
    		queryString = queryString + "id="+id+"&orgid="+orgcode; 	
    		findInfo(queryString);
    	}
}
function showNull1(){
	alert("没有该职工!");
	document.forms[0].elements["specialPick.emp.empId"].value="";
}
function showNull(){
	alert("查无此单位!");
	document.forms[0].elements["specialPick.org.id"].value="";
}
function showNull2(){
	alert("改单位的开户状态为非正常!");
	document.forms[0].elements["specialPick.org.id"].value="";
}
/*双击和查询共用*/
function displays(orgid,orgName,f,f1) {
	if((f!="")&&(f1==""))
  {
  	alert('该单位存在未记账的错账调整业务！');
  }
  	if((f=="")&&(f1!=""))
  {
  	alert('该单位存在未记账的转出业务！');
  }
  	if((f!="")&&(f1!=""))
  {
  	alert('该单位存在未记账的错账调整和转出业务！');
  }
	document.forms[0].elements["specialPick.org.orgInfo.name"].value=orgName;
	document.forms[0].elements["specialPick.org.id"].value=orgid;
	//document.forms[0].elements["specialPick.emp.empId"].focus();
	document.all.item(s2).disabled="";
	document.forms[0].elements["specialPick.emp.empId"].value="";
	document.forms[0].elements["specialPick.emp.empInfo.name"].value="";
	document.forms[0].elements["specialPick.emp.empInfo.TEMP_cardKind"].value="";
	document.forms[0].elements["specialPick.emp.empInfo.cardNum"].value="";
	document.forms[0].elements["specialPick.emp.curBalance"].value="";
	document.forms[0].elements["specialPick.emp.preBalance"].value="";
	document.forms[0].elements["specialPick.emp.balance"].value="";
	document.forms[0].elements["specialPick.pickCorpus"].value="";
	document.all.item(s3).disabled="true";
	//document.all.item(s4).disabled="true";
	document.all.item(s5).disabled="true";
}
function displays1(orgid,empid,empName,cardKind,cardNum,curBalance,preBalance,balance,pickCorpus) {
	document.forms[0].elements["specialPick.org.id"].value=orgid;
	document.forms[0].elements["specialPick.emp.empId"].value=empid;
	document.forms[0].elements["specialPick.emp.empInfo.name"].value=empName;
	document.forms[0].elements["specialPick.emp.empInfo.TEMP_cardKind"].value=cardKind;
	document.forms[0].elements["specialPick.emp.empInfo.cardNum"].value=cardNum;
	document.forms[0].elements["specialPick.emp.curBalance"].value=curBalance;
	document.forms[0].elements["specialPick.emp.preBalance"].value=preBalance;
	document.forms[0].elements["specialPick.emp.balance"].value=balance;
	document.forms[0].elements["specialPick.pickCorpus"].value=pickCorpus;
	document.forms[0].elements["specialPick.pickCorpus"].focus();
	document.all.item(s3).disabled="";
	//document.all.item(s4).disabled="";
	document.all.item(s5).disabled="";
}
function disp() {
	var darwMoney = document.forms[0].elements["specialPick.pickCorpus"].value;
	var balance = document.forms[0].elements["specialPick.emp.balance"].value;
	if(parseFloat(darwMoney)>parseFloat(balance)){
		alert("提取金额不能大于"+balance+"元!");
		return false;
	}
	if(parseFloat(darwMoney)<parseFloat(0)){
		alert("提取金额不能小于0元!");
		return false;
	}
	if(	document.forms[0].elements["specialPick.pickCorpus"].value.trim()==""||document.forms[0].elements["specialPick.pickCorpus"].value.trim()=="0"){
	alert("请录入提取金额！");
  	return false;
  	}
  	if(	document.forms[0].elements["specialPick.emp.empId"].value.trim()==""||document.forms[0].elements["specialPick.emp.empId"].value.trim()=="0"){
	alert("请录入职工编号！");
  	return false;
  	}
  	if(isNaN(darwMoney)){
		alert("请你输入合法的数字");
		return false;
	}
	if(!checkMoney(darwMoney)){
		return false;
	}
}
function reportErrors() {
	<logic:messagesPresent>
	var errors = "<html:messages id="error"><bean:write name="error"/>\n</html:messages>"
	alert(errors);
	document.forms[0].elements["specialPick.org.id"].value="";
	document.forms[0].elements["specialPick.org.orgInfo.name"].value="";
	document.forms[0].elements["specialPick.emp.empId"].value="";
	document.forms[0].elements["specialPick.emp.empInfo.name"].value="";
	document.forms[0].elements["specialPick.emp.empInfo.TEMP_cardKind"].value="";
	document.forms[0].elements["specialPick.emp.empInfo.cardNum"].value="";
	document.forms[0].elements["specialPick.emp.curBalance"].value="";
	document.forms[0].elements["specialPick.emp.preBalance"].value="";
	document.forms[0].elements["specialPick.emp.balance"].value="";
	</logic:messagesPresent>
}
function goEnter1(){
flag1=1;
if(event.keyCode==13){
		event.keyCode=9;
		findEmployeeInfo();
		
	}
}
function goEnter2(){
flag1=2;
if(event.keyCode==13){
		event.keyCode=9;
		findEmployeeInfo();
	}
}
function goEnter3(){
	if(event.keyCode==13){
		event.keyCode=9;
		document.all.item(s5).click();
	}
}
</script>
<body bgcolor="#FFFFFF" text="#000000"  onContextmenu="return false" onload="reportErrors();onload();">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="7"><img src="<%=path%>/img/table_left.gif" width="7" height="37"></td>
          <td background="<%=path%>/img/table_bg_line.gif" width="55">&nbsp;</td>
          <td width="235" background="<%=path%>/img/table_bg_line.gif">
            <table border="0" cellspacing="0" cellpadding="0">    
              <tr> 
                <td width="112" height="37" background="<%=path %>/img/buttonbl.gif" align="center" valign="top"  style="PADDING-top: 7px">办理提取审批</td>
                <td width="112" height="37" background="<%=path %>/img/buttong.gif" align="center"   style="PADDING-top: 7px" valign="top"><a href="spePickShowListAC.do" class=a2>提取审批维护</a></td>
              </tr>  
            </table>
          <td background="<%=path%>/img/table_bg_line.gif" align="right"> 
            <table width="300" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="37"><img src="<%=path%>/img/title_banner.gif" width="37" height="24"></td>
                <td width="216" class=font14 bgcolor="#FFFFFF" align="center" valign="bottom"><font color="00B5DB"> 提取管理 &gt;办理提取审批</font>  </td>
                <td width="47" class=font14>&nbsp;</td>
              </tr>
            </table>  
          </td>
          <td width="9"><img src="<%=path%>/img/table_right.gif" width="9" height="37"></td>
        </tr>
      </table>
      
    </td>
  </tr>
  <html:form action="/spePickSaveAC.do"  style="margin: 0" >
  <tr> 
    <td class=td3>
      <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td height="35"> 
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td height="22" bgcolor="#CCCCCC" align="center" width="117"><b class="font14">办理提取审批</b></td>
                <td height="22" background="<%=path%>/img/bg2.gif" align="center">&nbsp;</td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
	    <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=3 align="center" >
          <tr> 
            <td   class="td1" width="17%">单位编号</td>
            <td width="22%"  > 
              <html:text name="spePickAF"  property="specialPick.org.id"  styleClass="input3" onkeydown="goEnter1();" ondblclick="findOrg()" /> 
              </td>
            <td width="11%" > 
              <input type="button" name="searchbutton" value="..." onclick="toPop1()" class="buttona"/>
            </td>
            <td width="17%" class="td1"  >单位名称</td>
            <td width="33%" > 
              <html:text name="spePickAF" property="specialPick.org.orgInfo.name"  styleClass="input3" readonly="true" />
            </td>
          </tr>
          <tr> 
            <td   class="td1" width="17%">职工编号</td>
            <td width="22%"  > 
              <html:text name="spePickAF"  property="specialPick.emp.empId"  styleClass="input3" onkeydown="goEnter2();" ondblclick="findEmp();" onblur="b();"/>
            </td>
            <td width="11%"  >
              <input type="button" name="searchbutton" value="..."  onclick="toPop2()" class="buttona"/>
            </td>
            <td width="17%" class="td1"  >职工姓名</td>
            <td width="33%"  > 
              <html:text  name="spePickAF" property="specialPick.emp.empInfo.name"  styleClass="input3" readonly="true" />
            </td>
          </tr>
          	  <tr> 
            <td   class="td1" width="17%">证件类型</td>
            <td colspan="2" > 
              <html:text name="spePickAF" property="specialPick.emp.empInfo.TEMP_cardKind"  styleClass="input3" readonly="true" />
            </td>
            <td width="17%" class="td1"  >证件号码</td>
            <td width="33%"  > 
              <html:text name="spePickAF" property="specialPick.emp.empInfo.cardNum"  styleClass="input3" readonly="true"/>
            </td>
          </tr>
          	  <tr> 
            <td   class="td1" width="17%">本年余额</td>
            <td colspan="2" > 
              <html:text name="spePickAF" property="specialPick.emp.curBalance"  styleClass="input3" readonly="true" />
            </td>
            <td width="17%" class="td1"  >往年余额</td>
            <td width="33%"  > 
              <html:text name="spePickAF"  property="specialPick.emp.preBalance"  styleClass="input3" readonly="true"/>
            </td>
          </tr>
		  <tr> 
            <td   class="td1" width="17%">账户余额</td>
            <td colspan="2" > 
              <html:text name="spePickAF" property="specialPick.emp.balance"  styleClass="input3" readonly="true" />
            </td>
            <td width="17%" class="td1"  >提取金额</td>
            <td width="33%"  > 
              <html:text name="spePickAF" property="specialPick.pickCorpus" maxlength="18"  styleClass="input3" onkeydown="goEnter3();" />
            </td>
          </tr>
        </table>
        
	  <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">   
        <tr valign="bottom"> 
          <td  colspan="7" bgcolor="#FFFFFF" align="right" height="30"> 
            <table border="0" align="center" cellpadding="0" cellspacing="0">
              <tr> 
                <td><html:submit property="method" styleClass="buttona" onclick="return skip();"><bean:message key="button.pickup.infor"/></html:submit></td>
                <!--  <td><html:submit property="method" styleClass="buttona"><bean:message key="button.repayment"/></html:submit></td>-->
                <td><html:submit property="method" styleClass="buttona" onclick="return disp();"><bean:message key="button.sure"/></html:submit></td>               
                
              </tr>
            </table>
    	  </td>
  		</tr>
	 </table>
	 
    </td>
  </tr>
  </html:form>
</table>
</body>
</html:html>
