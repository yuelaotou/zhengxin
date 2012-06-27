<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ include file="/checkUrl.jsp"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
    <title>提前还款参数设置查询</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script src="<%=path%>/js/common.js">
	</script>
	
  </head>
 <script>
 function verdictLoanBankId(){
	var loanBankId=document.forms[0].elements["paramQueryDTO.loanBankId"].value.trim();
	document.URL=('paramQueryShowAC.do?loanBankId='+loanBankId);
}
var s1="";
function onload(){
	for(var i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="button"){
			if(document.all.item(i).value=="打印"){
				s1=i;
			}
		}
	}
	var type=document.forms[0].elements["paramQueryDTO.type"].value;
	if(type==""){
		document.all.item(s1).disabled="true";
	}else if(type=='1'){
		document.all.item(s1).disabled="";
	}
}
function toprint(){
	document.URL=('querystatistics/datareadyquery/paramquery/paramquery_print.jsp');
}
 </script> 
  <body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false" onload="onload();">
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
                <td class=font14 bgcolor="#FFFFFF" align="center" valign="bottom"><font color="00B5DB">数据准备查询信息&gt;还款参数设置查询</font></td>
                <td class=fofnt14>&nbsp;</td>
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
    <html:form action="/paramQueryShowAC.do">
      <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td height="35"> 
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td height="22" bgcolor="#CCCCCC" align="center" width="117"><b class="font14">参数设置</b></td>
                <td height="22" background="<%=path%>/img/bg2.gif" align="center">&nbsp;</td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
      <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=3 align="center" >
      	<tr id="tr1"> 
          <td class="td1">&nbsp;</td>
          <td class="td1"> 
            <html:select property="paramQueryDTO.loanBankId" styleClass="input4" name="paramQueryAF" style="width=30%;"  onchange="verdictLoanBankId();">
          		<option value=""></option>
          		<html:options  collection="loanbankList1" property="value" labelProperty="label"/>
			</html:select>      
          </td>
        </tr>
        <tr id="tr1"> 
          <td class="td1">1</td>
          <td class="td1"> 
            <html:radio name="paramQueryAF" property="paramQueryDTO.kouAccMode" value="1" disabled="true"/>
            足额扣款 　 
            <html:radio name="paramQueryAF" property="paramQueryDTO.kouAccMode" value="2" disabled="true"/>
            全额扣款 </td>
        </tr>
        <tr id="tr1"> 
          <td class="td1">2</td>
          <td class="td1"> 
            <html:radio name="paramQueryAF" property="paramQueryDTO.decideDateMode" value="1" disabled="true"/>
            按户定日 　
            <html:radio name="paramQueryAF" property="paramQueryDTO.decideDateMode" value="2" disabled="true"/>
  
            统一定日 
            <html:text name="paramQueryAF"  property="paramQueryDTO.uniteDate"  styleClass="input3" style="width=7%;" readonly="true"/>
          号</td>
        </tr>
        <tr id="tr1"> 
          <td class="td1">3</td>
          <td class="td1"> 扣款顺序设置 正常本金 
            <html:select property="paramQueryDTO.corpus" name="paramQueryAF" styleClass="input4" style="width=7%;" disabled="true">
	              <option value=""></option>
	              <html:option value="1">1</html:option>
	              <html:option value="2">2</html:option>
	              <html:option value="3">3</html:option>
	              <html:option value="4">4</html:option>
	              <html:option value="5">5</html:option>
	            </html:select> 正常利息 
            <html:select property="paramQueryDTO.interest" name="paramQueryAF" styleClass="input4" style="width=7%;" disabled="true">
            	  <option value=""></option>
	              <html:option value="1">1</html:option>
	              <html:option value="2">2</html:option>
	              <html:option value="3">3</html:option>
	              <html:option value="4">4</html:option>
	              <html:option value="5">5</html:option>
	            </html:select> 逾期本金 
            <html:select property="paramQueryDTO.overdueCorpus" name="paramQueryAF" styleClass="input4" style="width=7%;" disabled="true">
	              <option value=""></option>
	              <html:option value="1">1</html:option>
	              <html:option value="2">2</html:option>
	              <html:option value="3">3</html:option>
	              <html:option value="4">4</html:option>
	              <html:option value="5">5</html:option>
	            </html:select> 逾期利息 
            <html:select property="paramQueryDTO.overdueInterest" name="paramQueryAF" styleClass="input4" style="width=7%;" disabled="true">
	              <option value=""></option>
	              <html:option value="1">1</html:option>
	              <html:option value="2">2</html:option>
	              <html:option value="3">3</html:option>
	              <html:option value="4">4</html:option>
	              <html:option value="5">5</html:option>
	            </html:select> 罚息 
            <html:select property="paramQueryDTO.punishInterest" name="paramQueryAF" styleClass="input4" style="width=7%;" disabled="true">
	              <option value=""></option>
	              <html:option value="1">1</html:option>
	              <html:option value="2">2</html:option>
	              <html:option value="3">3</html:option>
	              <html:option value="4">4</html:option>
	              <html:option value="5">5</html:option>
	            </html:select>      
          </td>
        </tr>
        <tr id="tr1"> 
          <td class="td1">4</td>
          <td class="td1"> 罚息利率 
            <html:radio name="paramQueryAF" property="paramQueryDTO.punishInterestRateMode" value="1" disabled="true"/>
            按罚息日利率
            <html:text name="paramQueryAF"  property="paramQueryDTO.punishInterestDateRate"  styleClass="input3" style="width=7%;" readonly="true"/>
            计息 
            <html:radio name="paramQueryAF" property="paramQueryDTO.punishInterestRateMode" value="2" disabled="true"/>
            按合同日利率 
            <html:text name="paramQueryAF"  property="paramQueryDTO.contractDateRate"  styleClass="input3" style="width=7%;" readonly="true"/>
            %计息 
            <html:radio name="paramQueryAF" property="paramQueryDTO.punishInterestRateMode" value="3" disabled="true"/>
            按额每日
            <html:text name="paramQueryAF"  property="paramQueryDTO.moneyDateInterest"  styleClass="input3" style="width=7%;" readonly="true"/>
            元计息</td>
        </tr>
        <tr> 
          <td class="td1">5</td>
          <td class="td1"> 宽限天数 
            <html:text name="paramQueryAF"  property="paramQueryDTO.permitDateNum"  styleClass="input3" style="width=7%;" readonly="true"/>
            天, 宽限天数内是否记罚息 
            <html:radio name="paramQueryAF" property="paramQueryDTO.isRecord" value="0" disabled="true"/>是
			<html:radio name="paramQueryAF" property="paramQueryDTO.isRecord" value="1" disabled="true"/>否</td>
        </tr>
        <tr> 
          <td class="td1">6</td>
          <td class="td1">五级分类期限设置正常 
            <html:text name="paramQueryAF"  property="paramQueryDTO.commonMonthNum"  styleClass="input3" style="width=7%;" readonly="true"/>个月
	    关注 
            <html:text name="paramQueryAF"  property="paramQueryDTO.attentionMonthNum"  styleClass="input3" style="width=7%;" readonly="true"/>个月
            次级 
            <html:text name="paramQueryAF"  property="paramQueryDTO.subMonthNum"  styleClass="input3" style="width=7%;" readonly="true"/>个月
            可疑 
            <html:text name="paramQueryAF"  property="paramQueryDTO.shadinessMonthNum"  styleClass="input3" style="width=7%;" readonly="true"/>个月
            损失 
            <html:text name="paramQueryAF"  property="paramQueryDTO.lossMonthNum"  styleClass="input3" style="width=7%;" readonly="true"/>个月          </td>
        </tr>
        <tr> 
          <td class="td1">7</td>
          <td class="td1"> 贷款流程 审批贷款 
            <html:select property="paramQueryDTO.loanVipCheck" name="paramQueryAF" styleClass="input4" style="width=7%;" disabled="true">
	              <option value=""></option>
	              <html:option value="A">1</html:option>
	              <html:option value="B">2</html:option>
	            </html:select> 签定贷款
            <html:select property="paramQueryDTO.endorseLoan" name="paramQueryAF" styleClass="input4" style="width=7%;" disabled="true">
	              <option value=""></option>
	              <html:option value="A">1</html:option>
	              <html:option value="B">2</html:option>
	            </html:select>       
          </td>
        </tr>
        <tr> 
          <td class="td1">8</td>
          <td class="td1"> 保证金 活期利率 
            <html:text name="paramQueryAF"  property="paramQueryDTO.currentRate"  styleClass="input3" style="width=7%;" readonly="true"/>
            % 定期利率
            <html:text name="paramQueryAF"  property="paramQueryDTO.terminalRate"  styleClass="input3" style="width=7%;" readonly="true"/>%  </td>
        </tr>
        <tr> 
          <td class="td1">9</td>
          <td class="td1"> 利率调整时，申请中的合同是否采用新利率： 
            <html:radio name="paramQueryAF" property="paramQueryDTO.isAdopt" value="0" disabled="true"/>是
		    <html:radio name="paramQueryAF" property="paramQueryDTO.isAdopt" value="1" disabled="true"/>否</td>
        </tr>
        <html:hidden name="paramQueryAF" property="paramQueryDTO.type"></html:hidden>
      </table>
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr valign="bottom"> 
          <td  colspan="7" bgcolor="#FFFFFF" align="center" height="30"> 
            <table border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="70"> 
                  <input type="button" name="Submit" value="打印" class="buttona" onclick="toprint();">
                </td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
      </html:form>
    </table></body>
</html:html>
