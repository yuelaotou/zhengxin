<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>

<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ page import="org.xpup.hafmis.syscollection.tranmng.tranout.action.Tran_showAC" %>

<%
       String path = request.getContextPath();
       Object pagination = request.getSession(false).getAttribute(Tran_showAC.PAGINATION_KEY);
	   request.setAttribute("pagination", pagination);
	   String type=(String)request.getAttribute("type");
	  	    String typetran=(String)request.getAttribute("typetran");
	   request.setAttribute("type",type);
 %>

<html:html>
<head>
<title>Tran 添加</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
</head>
<script src="<%=path%>/js/common.js"> </script>
<script language="javascript"  type="text/javascript" >
var s1="";
var s2="";
var typetran=<%=typetran%>;
function toPop() {
	var orgID = document.forms[0].elements["outOrgId"].value.trim();

	open_findEmp('<%=path%>',orgID,'null','null','null','0');
}
function executeAjax() {   //  从页面取 emp id  值 
    var queryString = "tran_AddFindempinfoAAC.do?";  
    var id = document.tranAddAF.elements["emp.empId"].value;  
    var orgid = document.tranAddAF.elements["outOrgId"].value;
    if(id==""){
    	toPop();
    }
    queryString = queryString + "empid="+id+"&orgid="+orgid; 	     
	findInfo(queryString);   
}
  
function displays(empid,name,card_kind,card_num,preBalance,curBalance,salary,preInterest,curInterest,sumInterest,transum,str){ 
		document.tranAddAF.elements["yesNo"].value="2"; 
		//alert(document.tranAddAF.elements["yesNo"].value+"=>");
	if(str != ""){
		alert(str);
	      document.tranAddAF.elements["emp.empId"].value="";
		  document.tranAddAF.elements["emp.empInfo.name"].value="";
		  document.tranAddAF.elements["card_kind"].value="";
		  document.tranAddAF.elements["emp.empInfo.cardNum"].value="";
		  document.tranAddAF.elements["preBalance"].value="";
		  document.tranAddAF.elements["curBalance"].value="";
		  document.tranAddAF.elements["salary"].value="";  
		  //document.tranAddAF.elements["yesNo"].value=""; 
		  if(typetran==2){
		  document.tranAddAF.elements["preinterest"].value="";
		  document.tranAddAF.elements["curinterest"].value=""; 
		  document.tranAddAF.elements["suminterest"].value=""; 
		  document.tranAddAF.elements["transum"].value=""; 
		  
		  document.tranAddAF.elements["preInterest"].value=""; 
		  document.tranAddAF.elements["curInterest"].value=""; 
		  document.tranAddAF.elements["sumInterest"].value=""; 
		  document.tranAddAF.elements["transum"].value=""; 
		  document.tranAddAF.elements["tranReason"].value=""; 
		  }
		  document.tranAddAF.elements["emp.empId"].focus();
	}else{
		  document.tranAddAF.elements["emp.empId"].value=empid;
		  document.tranAddAF.elements["empid"].value=empid;
		  document.tranAddAF.elements["emp.empInfo.name"].value=name;
		  document.tranAddAF.elements["card_kind"].value=card_kind;
		  document.tranAddAF.elements["emp.empInfo.cardNum"].value=card_num;
		  document.tranAddAF.elements["preBalance"].value=preBalance;
		  document.tranAddAF.elements["curBalance"].value=curBalance;
		  document.tranAddAF.elements["salary"].value=salary;  
		  //document.tranAddAF.elements["yesNo"].value=""; 
		    
		     if(typetran==2){
		  document.tranAddAF.elements["preinterest"].value=preInterest;
		  document.tranAddAF.elements["curinterest"].value=curInterest; 
		  document.tranAddAF.elements["suminterest"].value=sumInterest; 	  
	 	  document.tranAddAF.elements["tranSum"].value=transum;   
		
		  document.tranAddAF.elements["preInterest"].value=""; 
		  document.tranAddAF.elements["curInterest"].value=""; 
		  document.tranAddAF.elements["sumInterest"].value=""; 
		  document.tranAddAF.elements["transum"].value=""; 
		  document.tranAddAF.elements["tranReason"].value=""; 
		  }
	 }
	 var isSett=document.tranAddAF.elements["yesNo"].value;
	if(typetran==2){
	
	if(isSett==1){
	    document.tranAddAF.elements["preInterest"].value=document.tranAddAF.elements["preinterest"].value;
		document.tranAddAF.elements["curInterest"].value=document.tranAddAF.elements["curinterest"].value; 
		document.tranAddAF.elements["sumInterest"].value=document.tranAddAF.elements["suminterest"].value; 
		document.tranAddAF.elements["transum"].value=document.tranAddAF.elements["tranSum"].value;
	}else if(isSett==2){
	    document.tranAddAF.elements["preInterest"].value=0;
		document.tranAddAF.elements["curInterest"].value=0; 
		document.tranAddAF.elements["sumInterest"].value=0; 
		document.tranAddAF.elements["transum"].value=document.tranAddAF.elements["salary"].value;
	}
	if(isSett==""){
	    document.tranAddAF.elements["preInterest"].value="";
		document.tranAddAF.elements["curInterest"].value=""; 
		document.tranAddAF.elements["sumInterest"].value=""; 
		document.tranAddAF.elements["transum"].value="";
		document.tranAddAF.elements["tranReason"].value=""; 
	}
	}
}
function gotoIsSettIntrerest(){
	var isSett=document.tranAddAF.elements["yesNo"].value;
	if(typetran==2){
	
	if(isSett==1){
	    document.tranAddAF.elements["preInterest"].value=document.tranAddAF.elements["preinterest"].value;
		document.tranAddAF.elements["curInterest"].value=document.tranAddAF.elements["curinterest"].value; 
		document.tranAddAF.elements["sumInterest"].value=document.tranAddAF.elements["suminterest"].value; 
		document.tranAddAF.elements["transum"].value=document.tranAddAF.elements["tranSum"].value;
	}else if(isSett==2){
	    document.tranAddAF.elements["preInterest"].value=0;
		document.tranAddAF.elements["curInterest"].value=0; 
		document.tranAddAF.elements["sumInterest"].value=0; 
		document.tranAddAF.elements["transum"].value=document.tranAddAF.elements["salary"].value;
		document.tranAddAF.elements["tranReason"].value=""; 
	}
	if(isSett==""){
	    document.tranAddAF.elements["preInterest"].value="";
		document.tranAddAF.elements["curInterest"].value=""; 
		document.tranAddAF.elements["sumInterest"].value=""; 
		document.tranAddAF.elements["transum"].value="";
		document.tranAddAF.elements["tranReason"].value=""; 
	}
	}
}
function gotoSure(){
	var isSett=document.tranAddAF.elements["yesNo"].value;
	var empid=document.tranAddAF.elements["emp.empId"].value;
	if(empid != ""){
		empid=empid;
	}
	var id=document.tranAddAF.elements["empid"].value;
	if(empid==""){
		alert("请选择职工！");
		document.tranAddAF.elements["emp.empId"].focus();
		return false;
	}
	if(isSett==""){
		alert("请选择是否结息！");
		return false;
	}
	if(empid != id){
	//	alert("职工编号已经改变，请重新查询职工信息！");
	document.tranAddAF.elements["yesNo"].value=""; 
	document.tranAddAF.elements["preInterest"].value=""; 
	document.tranAddAF.elements["curInterest"].value=""; 
	document.tranAddAF.elements["sumInterest"].value=""; 
    document.tranAddAF.elements["transum"].value=""; 
    document.tranAddAF.elements["tranReason"].value=""; 
    executeAjax();
	return false;
	}
	var inid = document.tranAddAF.elements["tranin_empId"].value;  
    var inorgid = document.tranAddAF.elements["inOrgId"].value;
    var outorgid = document.tranAddAF.elements["outOrgId"].value;
    if(outorgid==inorgid){
    	if(inid==""){
    		alert("请填写转入员工编号");
    		return false;
    	}
    }
    if(inid!=""){
    	executeAjax_ws();
	    if(flag!=""){
		    if(flag!="go"){
				return false;
	 		}
	 	}
    }
}
function executeAjax_ws(){
	var inOrgId = document.tranAddAF.elements["inOrgId"].value;
	var outOrgId = document.tranAddAF.elements["outOrgId"].value;
	var outempid = document.tranAddAF.elements["emp.empId"].value;  
	var inempid = document.tranAddAF.elements["tranin_empId"].value;  
	var queryString = "tran_CheckEmpInfoAAC.do?";  
    queryString = queryString + "inempid="+inempid+"&outempid="+outempid+"&inoutorgid="+inOrgId+","+outOrgId;
	findInfo(queryString);   
}
var flag = "";
function conf(str){
	if(str=="different"){
		if(confirm("转出职工的姓名和身份证号与转入职工的不同,是否继续操作")){
			flag = "go";
		}else{
			flag = "stop";
		}
	}
}
function loads(){
	 document.tranAddAF.elements["tranReason"].value=""; 
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
	var inorgid= document.tranAddAF.elements["inOrgId"].value;
	if(inorgid==""){
		document.all.Submit5.disabled="disabled";
		document.forms[0].elements["tranin_empId"].readOnly="readOnly";
	}
}
function gotoEnter(){
	if(event.keyCode==13){
		event.keyCode = 9;
		executeAjax();
	}
}
function gotoEnter2(){
	if(event.keyCode==13){
		event.keyCode = 9;
		executeAjaxIn();
	}
}
function checkmonths(String){ 
    var Letters = "1234567890"; 
    var i;
    var c;
    if(String.charAt( 0 )=='.')
 		return false;
    if( String.charAt( String.length - 1 ) == '.' )
    	return false;
    for( i = 0; i < String.length; i ++ ){
	    c = String.charAt( i );
	   	if (Letters.indexOf( c ) < 0)
	    	return false;
	}
    return true;
}
function executeAjaxIn(){
var queryString = "inTran_AddFindempinfoAAC.do?";  
    var id = document.tranAddAF.elements["tranin_empId"].value;  
    var name = document.tranAddAF.elements["emp.empInfo.name"].value;
    name =encodeURI(name);
    var cardNum = document.tranAddAF.elements["emp.empInfo.cardNum"].value;
    var orgid = document.tranAddAF.elements["inOrgId"].value;
	if(!checkmonths(id)){
		alert("职工编号输入有误");
		return false;
	}

    if(id!=""){
    if(!checkmonths(id)){
		alert("职工编号输入有误");
		 document.tranAddAF.elements["tranin_empId"].value="";
		return false;
	}
    	queryString = queryString + "empid="+id+"&orgid="+orgid+"&name="+name+"&cardNum="+cardNum; 	     
	findInfo(queryString);  
    }
     
}
function gotoPop(){
	var inorgID = document.forms[0].elements["inOrgId"].value.trim();

	open_findEmp('<%=path%>',inorgID,'null','null','null','23');
}
function displaysin(empid,str){ 
	if(str == "a"){
		alert("转入职工不存在！！！");
	   document.tranAddAF.elements["tranin_empId"].value="";
	   document.tranAddAF.elements["tranin_empId"].focus();
	}else if(str == "c"){
		alert("转入职工与转出职工姓名，身份证不同！！！");
		document.tranAddAF.elements["tranin_empId"].value="";
		document.tranAddAF.elements["tranin_empId"].focus();
	}else{
	 	document.tranAddAF.elements["tranin_empId"].value=empid;
	}
}
</script>
<script language="javascript"  type="text/javascript" ></script>
<body bgcolor="#FFFFFF" text="#000000" onload="loads();"  onContextmenu="return false">
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

                <td width="47" class=font14>&nbsp;</td>
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
    <html:form action="/tran_addMaintainAC.do" style="margin: 0" >
    
      <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td height="35"> 
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td height="22" bgcolor="#CCCCCC" align="center" width="117"><b class="font14">基 本 信 息</b></td>
                <td height="22" background="<%=path%>/img/bg2.gif" align="center">&nbsp;</td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
      
     <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=3 align="center" >
        <tr> 
          <td width="17%" class="td1"  >职工编号</td>
          <td width="24%"   > <input type="hidden" name="empid" value="">
            <html:text name="tranAddAF" property="emp.empId"  styleClass="input3" onkeydown="gotoEnter();" ondblclick="executeAjax()"/>
          </td>
          <td width="9%"   > 
            <input type="button" name="Submit4" value="……" class="buttona" onclick="toPop();">          </td>
          <td width="17%" class="td1"   >职工姓名</td>
          <td width="33%"   >
            <html:text name="tranAddAF" property="emp.empInfo.name"  styleClass="input3" readonly="true"/>
            <html:hidden name="tranAddAF" property="outOrgId" styleId="txtsearch"/>
            <html:hidden name="tranAddAF" property="inOrgId" styleId="txtsearch"/>
            <html:hidden name="tranAddAF" property="headId" styleId="txtsearch"/>
            <html:hidden name="tranAddAF" property="noteNum" styleId="txtsearch"/>
            <input type="hidden" name="types" value='<%=type%>'>
          </td>
        </tr>
        <tr> 
          <td   class="td1" width="17%">证件类型</td>
          <td colspan="2"  > 
             <html:text name="tranAddAF" property="card_kind"  styleClass="input3" readonly="true"/>         </td>
          <td class="td1" width="17%" ><span class="STYLE1">证件号码</span></td>
          <td width="33%">
           <html:text name="tranAddAF" property="emp.empInfo.cardNum"  styleClass="input3" readonly="true"/>
          </td>
        </tr>
        <tr> 
          <td   class="td1" width="17%">往年余额</td>
          <td colspan="2"  > 
             <html:text name="tranAddAF" property="preBalance"  styleClass="input3"  readonly="true"/>          </td>
          <td class="td1" width="17%" >本年余额</td>
          <td width="33%"  > 
             <html:text name="tranAddAF" property="curBalance"  styleClass="input3" readonly="true" />          </td>
        </tr>
        <tr> 
          <td   class="td1" width="17%">余额</td>
          <td colspan="2"  > 
             <html:text name="tranAddAF" property="salary"  styleClass="input3"  readonly="true"/>         </td>
          <td class="td1" width="17%" >是否结息<font color="#FF0000">*</font></td>
          <td width="33%"  >
            <html:select  property="yesNo" styleClass="input3" onchange="gotoIsSettIntrerest();">
             <html:optionsCollection property="map"  name="tranAddAF" label="value" value="key"/>
           </html:select>
           </td>
        </tr>
        <security:orgcannot>
        <tr> 
          <td   class="td1" width="17%">往年利息</td>
          <td colspan="2"  > <input type="hidden" name="preinterest" value="">
             <html:text name="tranAddAF" property="preInterest"  styleClass="input3"  readonly="true"/>          </td>
          <td class="td1" width="17%" >本年利息</td>
          <td width="33%"  ><input type="hidden" name="curinterest" value="">
            <html:text name="tranAddAF" property="curInterest"  styleClass="input3" readonly="true" />
                      </td>
        </tr>
        <tr>
          <td   class="td1" width="17%">利息合计</td>
          <td colspan="2"  > <input type="hidden" name="suminterest" value="">
            <html:text name="tranAddAF" property="sumInterest"  styleClass="input3"  readonly="true"/>
                    </td>
          <td class="td1" width="17%" >转出总额</td>
          <td width="33%" ><input type="hidden" name="tranSum" value="">
          <html:text name="tranAddAF" property="transum"  styleClass="input3" readonly="true" /></td>
        </tr>
        </security:orgcannot>
        <tr> 
          <td width="17%" class="td1"  >转入职工编号</td>
          <td width="24%"   >
            <html:text name="tranAddAF" property="tranin_empId"  styleClass="input3" onblur="executeAjaxIn();" onkeydown="gotoEnter2();" ondblclick="executeAjaxIn()" maxlength="10"/>
          </td>
          <td width="9%"   > 
            <input type="button" name="Submit5" value="……" class="buttona" onclick="gotoPop();"></td>
          <td width="17%" class="td1"   >转移原因</td>
          <td width="33%" ><html:text name="tranAddAF" property="tranReason"  styleClass="input3" />
          </td>
</tr>
		
      </table>
     <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr valign="bottom"> 
          <td  colspan="7" bgcolor="#FFFFFF" align="center" height="30"> 
            <table border="0" cellspacing="0" cellpadding="0">
              <tr>
              <td><html:submit property="method" styleClass="buttona" onclick="return gotoSure();"><bean:message key="button.sure"/> </html:submit> </td>
              <td><html:submit property="method" styleClass="buttona"><bean:message key="button.back"/> </html:submit> </td>
              </tr>
            </table>    
    </td>
  </tr>
</table>
  </html:form>
</body>
</html:html>
