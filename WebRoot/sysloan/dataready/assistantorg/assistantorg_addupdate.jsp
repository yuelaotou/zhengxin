<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>担保公司新增</title>
</head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css" />
<script src="<%=path%>/js/common.js"></script>
<script language="javascript">
var oldColor="#ffffff";                            //原来的颜色
var newColor="#E8FCFD";                     //要变成的颜色
function chgBGColor(oTD){
	oldColor=oTD.style.backgroundColor;//保存当前颜色
	oTD.style.backgroundColor=newColor;//改变表格颜色
	newColor=oldColor;                 //改变下次要变成的颜色
}
</script>
<script>
  function reportErrors(){
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
	}
</script>
<script type="text/javascript">
	function checkIdCard(){
		var cardKind = document.all.artfclprsnCardKind.value;
		var cardNum = document.all.artfclprsnCardNum.value;
		if(cardKind == null || cardKind == "")
			return true;
		if(cardKind == 0){
			if(isIdCardNo(cardNum)){
				return true;
			}else{
				return false;
			}
		}
	}
</script>
<script>
function checkData_YM(){
	if(document.all.assistantOrgName.value==""){
	   alert("担保公司名称不许为空!");
	   return false;
	   }	 
	 var basedDate=document.all.basedDate;
	 var base=document.all.basedDate.value;
	 if(base!="")
	 {
	 	if(!isNumber(base))
	 	{
	 		alert("请输入正确格式");
	 		return false;
	 	}
	 	if(!checkDate(basedDate))
	 	{
	 		alert("请输入正确格式");
	 		return false;
	 	}
	 }
	var registerFund=document.all.registerFund.value;
	if(registerFund==""){
		document.all.registerFund.value="0.00";
		return false;
	}else{
		if(isNaN(registerFund)){
			alert("请你输入合法的数字");
			document.all.registerFund.value="0.00";
			return false;
		}
	}
	var contactTel=document.all.contactTel.value;
	if(contactTel!="")
	{
		if(!isNumber(contactTel))
		{
			alert("请输入正确格式电话号码!");
			return false;
		}
	}
	var artfclprsn=document.all.artfclprsn.value;
	if(artfclprsn!="")
	{
		if(!isCHorEN(artfclprsn))
		{
			alert("请输入正确法人名称");
			return false;
		}
	}
	var contactPrsn=document.all.contactPrsn.value;
	if(contactPrsn!="")
	{
		if(!isCHorEN(contactPrsn))
		{
			alert("请输入正确联系人名称");
			return false;
		}
	}
	return checkIdCard();
}
function ondelete(){
  var x=confirm("确定删除此记录?");
  if(x){
	return true;
  }else{
    return false;
  }
}

function onblues()
{
	 var basedDate=document.all.basedDate.value;
		if(basedDate!=null && basedDate!=""){
			if(isNumber(basedDate)){
				 if(!checkDate(document.all.basedDate)){
				 	document.all.basedDate.focus();
				 	return false;
				 }
			}else{
				alert("请输入八位的日期格式，例如20070101！");
				document.all.basedDate.focus();
			}
		}
}
	 	function checkAgreementStartDate(){
		var agreementStartDate = document.all.agreementStartDate.value;
		if(agreementStartDate!=null && agreementStartDate!=""){
			if(isNumber(agreementStartDate)){
				 if(!checkDate(document.all.agreementStartDate)){
				 	document.all.agreementStartDate.focus();
				 	return false;
				 }
			}else{
				alert("请输入八位的日期格式，例如20070101！");
				document.all.agreementStartDate.focus();
			}
		}
}	
	function checkAgreementEndDate(){
		var agreementEndDate = document.all.agreementEndDate.value;
		if(agreementEndDate!=null && agreementEndDate!=""){
			if(isNumber(agreementEndDate)){
				 if(!checkDate(document.all.agreementEndDate)){
				 	document.all.agreementEndDate.focus();
				 	return false;
				 }
			}else{
				alert("请输入八位的日期格式，例如20070101！");
				document.all.agreementEndDate.focus();
			}
		}
}
</script>

<body bgcolor="#FFFFFF" text="#000000" onLoad="reportErrors();" onContextmenu="return false">
<html:form action="/assistantorgAddUpdateAC">
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
                <td width="234" class=font14 bgcolor="#FFFFFF" align="center" valign="bottom"><a href="#" class=a1>数据准备</a><font color="00B5DB">&gt;</font><a href="#" class=a1>担保公司维护</a></td>
                <td width="29" class=font14>&nbsp;</td>
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
                <td height="22" bgcolor="#CCCCCC" align="center" width="145"><b class="font14">担保公司新增</b></td>
                <td height="22" background="<%=path%>/img/bg2.gif" align="center" width="775">&nbsp;</td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
      <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=3 align="center" >
        <tr  > 
          <td  class="td1" width="14%"> 担保公司名称<font color="#FF0000">*</font></td>
          <td  class="td4"> 
             <html:text name="AF" property="assistantOrgName" styleClass="input3" onkeydown="enterNextFocus1();" maxlength="100"/> </td>
          <td class="td1" width="14%" > 法人代表</td>
          <td  class="td4"> 
            <html:text name="AF" property="artfclprsn" styleClass="input3" onkeydown="enterNextFocus1();" maxlength="45"/> </td>
        </tr>
        <tr id="tr1"> 
          <td width="17%" class="td1"> 组织机构代码</td>
          <td> 
			<html:text name="AF" property="code" styleClass="input3" onkeydown="enterNextFocus1();" maxlength="100"/> </td>
          <td class="td1">担保公司地址</td>
          <td> 
			<html:text name="AF" property="assistantOrgAddr" styleClass="input3" onkeydown="enterNextFocus1();" maxlength="45"/> </td>
        </tr><html:hidden name="AF" property="type"/>
        <html:hidden name="AF" property="assistantOrgId"/>
        <html:hidden name="AF" property="assistantOrgType"/>
        <!-- 张列改  头 -->
        <tr id="tr1"> 
          <td width="17%" class="td1"> 所属地区 </td>
          <td> 
			<html:select name="AF" property="arear" styleClass="input4" onkeydown="enterNextFocus1();">
	        <html:option value=""></html:option>
			<html:optionsCollection property="regionMap" name="AF" label="value" value="key" />
			</html:select>
		</td>
		<!-- 张列改 尾 -->
         <td width="17%" class="td1">成立日期</td>
          <td width="26%"> 
			<html:text name="AF" property="basedDate" styleClass="input3" onkeydown="enterNextFocus1();" maxlength="8" onblur="return onblues();"/> </td>
        </tr>
        <tr id="tr1"> 
          <td width="17%" class="td1" >  法人证件类型        </td>
          <td width="33%"> 
           <html:select name="AF" property="artfclprsnCardKind" styleClass="input4" onkeydown="enterNextFocus1();">
	        <html:option value=""></html:option>
			<html:optionsCollection property="map" name="AF" label="value" value="key" />			</html:select>
			 <td width="17%" class="td1">法人证件号码</td>
          <td width="26%">
			<html:text name="AF" property="artfclprsnCardNum" onkeydown="enterNextFocus1();" styleClass="input3" maxlength="100"/> </td>
        </tr>
        <tr id="tr1">
          <td width="17%" class="td1" >批准机关</td>
          <td width="33%"> 
			<html:text name="AF" property="allowDept" styleClass="input3" onkeydown="enterNextFocus1();" maxlength="45"/> </td>
			<td width="17%" class="td1"> 批准文号</td>
          <td width="26%"> 
			<html:text name="AF" property="allowId" styleClass="input3" onkeydown="enterNextFocus1();" maxlength="100"/> </td>
        </tr>
        <tr id="tr1"> 
          <td width="17%" class="td1" >协议签订日期 </td>
          <td width="33%"> 
			<html:text name="AF" property="agreementStartDate" onkeydown="enterNextFocus1();" styleClass="input3" maxlength="8"  onblur="return checkAgreementStartDate();"/> </td>
			<td width="17%" class="td1"> 协议到期日期</td>
          <td width="33%"> 
			<html:text name="AF" property="agreementEndDate" onkeydown="enterNextFocus1();" styleClass="input3" maxlength="8"  onblur="return checkAgreementEndDate();"/> </td>
        </tr>
        <tr id="tr1"> 
          <td width="17%" class="td1" >开户银行 </td>
          <td width="33%"> 
			<html:text name="AF" property="paybank" styleClass="input3" onkeydown="enterNextFocus1();" maxlength="100"/> </td>
			<td width="17%" class="td1"> 开户行账号</td>
          <td width="33%"> 
			<html:text name="AF" property="payacc" styleClass="input3" onkeydown="enterNextFocus1();" maxlength="100"/> </td>
        </tr>
        <tr id="tr1"> 
          <td width="17%" class="td1" >联系人</td>
          <td width="33%"> 
			<html:text name="AF" property="contactPrsn" styleClass="input3" onkeydown="enterNextFocus1();" maxlength="45"/> </td>
			<td width="17%" class="td1">联系电话</td>
          <td width="33%"> 
			<html:text name="AF" property="contactTel" styleClass="input3" onkeydown="enterNextFocus1();" maxlength="100"/> </td>
        </tr>
        <tr id="tr1"> 
          <td width="17%" class="td1" >职务</td>
          <td width="33%"> 
			<html:text name="AF" property="business" styleClass="input3" onkeydown="enterNextFocus1();" maxlength="100"/> </td>
			<td width="17%" class="td1">注册资金</td>
          <td width="33%"> 
			<html:text name="AF" property="registerFund" styleClass="input3" onkeydown="enterNextFocus1();" maxlength="13"/> </td>
        </tr>
        <tr id="tr1"> 
          <td width="17%" class="td1" >备注</td>
          <td width="33%">
			<html:text name="AF" property="remark" styleClass="input3" onkeydown="enterNextFocus1();" maxlength="100"/> </td>
			<td width="17%" class="td1" >&nbsp</td>
          <td width="33%"><input name="txtsearch452215222" type="text" id="txtsearch4522152" class="input3" readonly="readonly"maxlength="100" /></td>
        </tr>
      </table>
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr valign="bottom"> 
          <td  colspan="7" bgcolor="#FFFFFF" align="center" height="30"> 
            <table border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="70">
                  <html:submit property="method" styleClass="buttona" onclick="return checkData_YM();">
                  <bean:message key="button.sure"/>
                  </html:submit>
                <br></td>
                
               <td width="70">&nbsp; 
             <html:submit  property="method" styleClass="buttona" onclick="javascript:history.back();">
             <bean:message key="button.back"/>
             </html:submit>
           <br></td>       
        </tr>
      </table>
    </table>
</html:form>
</body>
</html:html>