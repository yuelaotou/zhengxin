<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ page import="org.xpup.hafmis.sysloan.loancallback.baddebtdestroy.action.BadDebtDestroyTaShowAC" %>
<%@ include file="/checkUrl.jsp"%>
<%
   Object pagination= request.getSession(false).getAttribute(BadDebtDestroyTaShowAC.PAGINATION_KEY);
   request.setAttribute("pagination",pagination);
   String path=request.getContextPath();
   String orgCheck="";
   if(request.getSession().getAttribute("orgName")!=null){
   		orgCheck=(String)request.getSession().getAttribute("orgName");
   }
   request.getSession().setAttribute("orgName",null);
 %>
<html:html>
<head>
<title>个贷管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
</head>
<script src="<%=path%>/js/common.js">
</script>
<script type="text/javascript">
function loads(){
	var orgCheck;
	orgCheck="<%=orgCheck%>";
	<logic:messagesPresent>
		var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
		alert(message);
	</logic:messagesPresent>
	var orgTypes=document.all.orgTypes.value;
	var plLoanReturnType=<bean:write name="plLoanReturnType"/>;
	var name = document.badDebtDestroyTaAF.elements["borrowerInfoDTO.borrowerName"].value;
	if(orgTypes != "2"){
		document.all.orgButton.disabled="true";
	}
	if(orgCheck!=""){
		document.all.orgButton.disabled="";
	}
	if(plLoanReturnType==1){
		document.all.disp1.disabled="true";
		document.all.disp2.disabled="true";
	}else{
		document.all.select1.disabled="true";
		document.all.radio1.disabled="true";
		document.all.radio2.disabled="true";
		document.all.radio3.disabled="true";
		document.all.disp3.disabled="true";
		document.badDebtDestroyTaAF.elements["borrowerInfoDTO.loanKouAcc"].disabled="true";
		document.forms[0].button32.style.display="none";
	}
	if(name == ""){
		document.all.disp3.disabled="true";
	}
	var count="<bean:write name="pagination" property="nrOfElements"/>";
	if(count=="0"){
		document.all.disp2.disabled="true";
		document.all.disp3.disabled="true";
	}
}
function executeAjax() {
        var queryString = "<%=path%>/sysloan/badDebtDestroyTaFindAAC.do?";
        var id = document.badDebtDestroyTaAF.elements["borrowerInfoDTO.loanKouAcc"].value.trim();
        if(id == ""){
        	gotoSearch();
        }else{
	        queryString = queryString + "loanKouAcc="+id; 	 
		    findInfo(queryString);
	    }
}
function gotoEnter(){
	if(event.keyCode==13){
		event.keyCode = 9;
		executeAjax();
	}
}
function gotoSearch(){
	gotoLoankouaccpop('11','<%=path%>','0');
}
function display(id){
	document.badDebtDestroyTaAF.elements["borrowerInfoDTO.loanKouAcc"].value=id;
	showInfo();
}
function showInfo(){
	document.Form1.submit();
}

function gotoMonth(){
	var x= document.getElementsByName("orgType");
for(var i=0;i<x.length;i++){
		if(x[i].checked){
		document.all.orgTypes.value=x[i].value;
		}
	}
	var month=document.forms[0].elements["monthYear"].value.trim();
	var orgType=document.all.orgTypes.value.trim();
	var assistantOrgId=document.all.assistantOrgId.value.trim();
	var contractId=document.forms[0].elements["borrowerInfoDTO.contractId"].value.trim();
	location.href="badDebtDestroyTaChangeMonthAC.do?month="+month+"&contractId="+contractId+"&orgType="+orgType+"&assistantOrgId="+encodeURI(assistantOrgId);
}
function gotoType1(){
	document.all.orgButton.disabled="true";
	document.all.orgTypes.value="1";
	document.badDebtDestroyTaAF.elements["assistantOrgId"].value="";
}
function gotoType2(){
	document.all.orgButton.disabled="";
	document.all.orgTypes.value="2";
}
function gotoType3(){
	document.all.orgButton.disabled="true";
	document.all.orgTypes.value="3";
	document.badDebtDestroyTaAF.elements["assistantOrgId"].value="";
}

function gotoSearchOrg(){
	gotoAssistantorgpop('<%=path%>','12');
}
function gotoSure(){
	var orgTypes=document.all.orgTypes.value;
	var orgName=document.all.assistantOrgId.value;
	var plLoanReturnType=<bean:write name="plLoanReturnType"/>;
	if(plLoanReturnType=="1"){
		if(orgTypes == ""){
			alert("请选择核销单位！");
			return false;
		}else if(orgTypes=="2" && orgName==""){
			alert("请选择单位名称！");
			return false;
		}
	}
	if(confirm("是否打印回收凭证？")){
		document.badDebtDestroyTaAF.report.value="print";
	}else{
		document.badDebtDestroyTaAF.report.value="noprint";
	}
}
function gotoDelete(){
	if(!confirm("是否确定删除该笔业务？")){
		return false;
	}
}
function onclear(){
var x= document.getElementsByName("orgType");
for(var i=0;i<x.length;i++){
		if(x[i].checked){
		document.all.orgTypes.value=x[i].value;
		}
	}
}
</script>

<body bgcolor="#FFFFFF" text="#000000" onload="loads();" >
<html:form action="/badDebtDestroyTaMaintainAC" style="margin: 0">
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
                  <td width="112" height="37" background="<%=path%>/img/buttonbl.gif" align="center" valign="top"  style="PADDING-top: 7px">办理呆账核销</td>
                  <td width="112" height="37" background="<%=path%>/img/buttong.gif" align="center"   style="PADDING-top: 7px" valign="top"><a href="<%=path%>/sysloan/badDebtDestroyTbForwardURLAC.do" class=a2>呆账核销维护</a></td>
                </tr>
              </table>
            </td>
          <td background="<%=path%>/img/table_bg_line.gif" align="right"> 
            <table width="300" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="37"><img src="<%=path%>/img/title_banner.gif" width="37" height="24"></td>
                <td width="189" class=font14 bgcolor="#FFFFFF" align="center" valign="bottom"><p><font color="00B5DB">回收贷款&gt;呆账核销</font></p>
                  </td>
                <td width="74" class=font14>&nbsp;</td>
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
                  <td height="22" bgcolor="#CCCCCC" align="center" width="163"><b class="font14">借 款 人 信 息</b></td>
                  <td height="22" background="<%=path%>/img/bg2.gif" align="center" width="747">&nbsp;</td>
                </tr>
              </table>
            </td>
          </tr>
        </table>
        <table  width="95%" id="table9" cellspacing=1  cellpadding=3 align="center" >
          <tr> 
            <td width="17%"class=td1>贷款账号<br>
            </td>
            <td class="td4" width="20%"> 
              <html:text property="borrowerInfoDTO.loanKouAcc" ondblclick="executeAjax();" onkeydown="gotoEnter();" styleClass="input3"/>
            </td>
            <td class="td4"> 
              <input type="button" class="buttona" value="..." name="button32" onclick="gotoSearch();">
            </td>
            <td class="td1" width="17%">合同编号</td>
            <td class="td4" width="33%" colspan="2"> 
              <html:text property="borrowerInfoDTO.contractId" styleClass="input3" readonly="true"/>
            </td>
          </tr>
          <tr> 
            <td width="17%"class=td1> 借款人姓名</td>
            <td class="td4" colspan="2"> 
              <html:text property="borrowerInfoDTO.borrowerName" styleClass="input3" readonly="true"/>
             </td>
            <td width="17%"class=td1> 证件类型</td>
            <td  class="td4" width="33%" colspan="2">
              <html:text property="borrowerInfoDTO.cardKind" styleClass="input3" readonly="true"/>
            </td>
          </tr>
          <tr> 
            <td class=td1 width="17%">证件号码</td>
            <td class="td4" colspan="2">
              <html:text property="borrowerInfoDTO.cardNum" styleClass="input3" readonly="true"/>
            </td>
            <td class=td1 width="17%">剩余本金<br>
            </td>
            <td class="td4" width="33%" colspan="2"> 
              <html:text property="borrowerInfoDTO.overplusLoanMoney" styleClass="input3" readonly="true"/>
            </td>
          </tr>
          <tr> 
            <td class=td1 width="17%">还款方式</td>
            <td class="td4" colspan="2"> 
              <html:text property="borrowerInfoDTO.loanMode" styleClass="input3" readonly="true"/>
            </td>
            <td class=td1 width="17%">还至日</td>
            <td  class="td4" width="33%" colspan="2">      
	          <html:select property="monthYear" styleClass="input4"  onchange="gotoMonth();" styleId="select1">
	          <html:option value=""></html:option>
	              	<html:options  collection="monthYearList" property="value" labelProperty="label"/>
	          </html:select>
            </td>
          </tr>
          <tr> 
            <td class=td1 width="17%" height="26">核销单位<font color="#FF0000">*</font></td>
            <td class="td4" colspan="2" height="26"> 
              <html:radio property="orgType" value="1" onclick="gotoType1();"  styleId="radio1"/>
              中心 
              <html:radio property="orgType" value="2" onclick="gotoType2();"  styleId="radio2"/>
              担保公司 
              <html:radio property="orgType" value="3" onclick="gotoType3();"  styleId="radio3"/>
              其他</td>
            <td class=td1 width="17%" height="26">单位名称</td>
            <td  class="td4" width="23%" height="26"> 
              <html:text property="assistantOrgId" styleClass="input3" readonly="true"/>
              <html:hidden name="badDebtDestroyTaAF" property="loanassistantorgId"/>
              <input type="hidden" name="orgTypes" value="">
	          <html:hidden property="borrowerInfoDTO.loanBankId"/>
	          <html:hidden property="borrowerInfoDTO.officeCode"/>
	          <input type="hidden" name="report" value="">
            </td>
            <td  class="td4" width="10%" height="26"> 
              <input type="button" class="buttona" value="..." name="button322" id="orgButton" onclick="gotoSearchOrg();">
            </td>
          </tr>
        </table>
        <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
          <tr> 
            <td height="35"> 
              <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr> 
                  <td height="22" bgcolor="#CCCCCC" align="center" width="169"><b class="font14">应 还 款 列 表</b></td>
                  <td width="741" height="22" align="center" background="<%=path%>/img/bg2.gif">&nbsp;</td>
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
        <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
          <tr > 
            <td align="center" class=td1 >还款年月 </td>
            <td align="center" class=td2 > 还款类型</td>
            <td align="center" class=td2 > 应还本金</td>
            <td align="center" class=td2 > 应还利息</td>
            <td align="center" class=td2 > 逾期天数</td>
            <td align="center" class=td2 > 罚息利息 </td>
            <td align="center" class=td2 >应还本息合计</td>
            <td align="center" class=td2 >每月利率</td>
          </tr>
        <logic:notEmpty name="badDebtDestroyTaAF"  property="shouldBackList">
		<logic:iterate name="badDebtDestroyTaAF"  property="shouldBackList" id="element">
          <tr id="tr1"  class=td4 onDblClick=""> 
            <td valign="top"><bean:write name="element" property="loanKouYearmonth"/></td>
            <td valign="top"><bean:write name="element" property="loanKouType"/></td>
            <td valign="top"><bean:write name="element" property="shouldCorpus"/></td>
            <td valign="top"><bean:write name="element" property="shouldInterest"/></td>
            <td valign="top"><bean:write name="element" property="days"/></td>
            <td valign="top"><bean:write name="element" property="punishInterest"/></td>
            <td valign="top"><bean:write name="element" property="ciMoney"/></td>
            <td valign="top"><bean:write name="element" property="show_loanRate"/></td>
          </tr>
          <tr > 
            <td colspan="9" class=td5 ></td>
          </tr>
        </logic:iterate>
        </logic:notEmpty>
        <logic:empty name="badDebtDestroyTaAF"  property="shouldBackList">
        <tr> 
          <td colspan="9" height="30" style="color:red">没有找到与条件相符合的结果！</td>
	    </tr>
		<tr > 
          <td colspan="9" class=td5 ></td>
        </tr>
        </logic:empty>      
        </table>
        <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
          <tr> 
            <td height="35"> 
              <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr> 
                  <td height="22" bgcolor="#CCCCCC" align="center" width="172"><b class="font14"> 
                    还 款 信 息</b></td>
                  <td height="22" background="<%=path%>/img/bg2.gif" align="center" width="738">&nbsp;</td>
                </tr>
              </table>
            </td>
          </tr>
        </table>
        <table  width="95%" id="table9" cellspacing=1  cellpadding=3 align="center" >
          <tr> 
            <td class=td1 width="17%">本次总还款本金<font color="#FF0000">*</font></td>
            <td class="td4">
              <html:text property="sumCorpus" styleClass="input3" readonly="true"/>
            </td>
            <td class=td1 width="17%"> 本次总还款利息</td>
            <td class="td4" width="33%">  
              <html:text property="sumInterest" styleClass="input3" readonly="true"/>
            </td>
          </tr>
          <tr> 
            <td class=td1 width="17%"> 本次总还款金额 </td>
            <td class="td4"> 
              <html:text property="sumMoney" styleClass="input3" readonly="true"/>
            </td>
            <td class=td1 width="17%"> 本次实收金额</td>
            <td  class="td4" width="33%"> 
              <html:text property="realMoney" styleClass="input3" readonly="true"/>
            </td>
          </tr>
        </table>
        <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
          <tr valign="bottom"> 
            <td  colspan="7" bgcolor="#FFFFFF" align="center" height="30"> 
              <table border="0" cellspacing="0" cellpadding="0">
                <tr> 
                  <td width="70"> 
                    <html:submit property="method" styleClass="buttona" styleId="disp1" onclick=""><bean:message key="button.import"/></html:submit>
                  </td>
                  <td width="70">
                    <html:submit property="method" styleClass="buttona" styleId="disp2" onclick="return gotoDelete();"><bean:message key="button.delete"/></html:submit>
                  </td>
                  <td width="70">
                    <html:submit property="method" styleClass="buttona" styleId="disp3" onclick="return gotoSure();"><bean:message key="button.sure"/></html:submit>
                  </td>
                </tr>
              </table>
            </td>
          </tr>
        </table>
      </td>
  </tr>
</table>
  </html:form>
<form action="<%=path%>/sysloan/badDebtDestroyTaShowAC.do" method="POST" name="Form1" id="Form1">
</form>
</body>
</html:html>
