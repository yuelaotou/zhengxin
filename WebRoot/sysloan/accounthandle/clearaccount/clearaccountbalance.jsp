<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.util.List"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ page
	import="org.xpup.hafmis.sysloan.accounthandle.clearaccount.action.ClearaccountTbShowAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	Object pagination = request.getSession(false).getAttribute(
			ClearaccountTbShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>
<html:html>
<head>
	<title>账务处理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script language="javascript" src="<%=path%>/js/common.js"></script>
	<script type="text/javascript">
function gotoSearch(){
	var startDate=document.clearAccountBalanceAF.startDate.value.trim();
	var endDate=document.clearAccountBalanceAF.endDate.value.trim();
	if(startDate == "" ){
		alert("请输入查询日期！");
		if(startDate == ""){
			document.clearAccountBalanceAF.startDate.focus();
		}
<%--		else if(endDate == ""){--%>
<%--			document.clearAccountBalanceAF.endDate.focus();--%>
<%--		}--%>
		return false;
	}
	var mydate = document.all.mydate.value;
    if(mydate>startDate){
    	 alert("请查询 "+mydate+" 之后的日期");
    	 return false;
    }
}
function loads(){	
	document.clearAccountBalanceAF.bizType.value="";
	document.clearAccountBalanceAF.loanBankId.value="";
<%--	document.clearAccountBalanceAF.makePerson.value="";--%>
	document.clearAccountBalanceAF.startDate.value="";
	document.clearAccountBalanceAF.endDate.value="";
}
function executeAjax() {
     var queryString = "clearaccountTbMXFindAAC.do";
	 findInfo(queryString);
}
function display(){
	window.open('<%=path%>/sysloan/clearaccountTbMXShowAC.do','','width=1000,height=600,top='+(window.screen.availHeight-600)/2+',left='+(window.screen.availWidth-1000)/2+',scrollbars=yes,status=yes');
}
function blurday(){
	var day="";
	day=document.clearAccountBalanceAF.startDate.value.trim();
	if(day.length==6){
		document.all.endDate.disabled="true";
	}else{
		document.all.endDate.disabled="";
	}
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onload="loads();">
	<table width="95%" border="0" cellspacing="0" cellpadding="0"
		align="center">
		<tr>
			<td>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="7">
							<img src="<%=path%>/img/table_left.gif" width="7" height="37">
						</td>
						<td background="<%=path%>/img/table_bg_line.gif" width="55">
							&nbsp;
						</td>
						<td width="235" background="<%=path%>/img/table_bg_line.gif">
							<table border="0" cellspacing="0" cellpadding="0">
								<tr>
									<%--                <td width="112" height="37" background="<%=path%>/img/buttong.gif" align="center" valign="top"  style="PADDING-top: 7px"><a href="clearaccountTaForwardUrlAC.do" class=a2>扎账</a></td>--%>
									<td width="112" height="37"
										background="<%=path%>/img/buttonbl.gif" align="center"
										style="PADDING-top: 7px" valign="top">
										日结单查询
									</td>
								</tr>
							</table>
						</td>
						<td background="<%=path%>/img/table_bg_line.gif" align="right">
							<table width="300" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="37">
										<img src="<%=path%>/img/title_banner.gif" width="37"
											height="24">
									</td>
									<td width="234" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">账务处理&gt;日结单查询</font>
									</td>
									<td width="29" class=font14>
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
						<td width="9">
							<img src="<%=path%>/img/table_right.gif" width="9" height="37">
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td class=td3>
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					align="center">
					<tr>
						<td height="35">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="22" bgcolor="#CCCCCC" align="center" width="237">
										<b class="font14">查 询 条 件</b>
									</td>
									<td height="22" background="<%=path%>/img/bg2.gif"
										align="center" width="1407">
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<html:form action="/clearaccountTbFindAC" styleClass="margin: 0">
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=0 align="center">
						<tr>
							<td class="td1">
								业务类型
							</td>
							<td colspan="3">
								<html:select property="bizType" styleClass="input4"
									onkeydown="enterNextFocus1();">
									<html:option value=""></html:option>
									<html:optionsCollection property="typeMap"
										name="clearAccountBalanceAF" label="value" value="key" />
								</html:select>
							</td>
							<td class="td1">
								放款银行
							</td>
							<td width="34%">
								<html:select name="clearAccountBalanceAF" property="loanBankId" styleClass="input4"
									onkeydown="enterNextFocus1();">
									<html:option value=""></html:option>
									<html:options collection="banklist" property="value"
										labelProperty="label" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td width="11%" class="td1">
								起始日期
							</td>
							<td width="13%">
								<html:text property="startDate" styleClass="input3" maxlength="8"
									onkeydown="enterNextFocus1();" onblur="blurday()"/>
								<html:hidden name="clearAccountBalanceAF" property="mydate" />
							</td>
							<td width="11%" class="td1">
								终止日期
							</td>
							<td width="13%">
								<html:text property="endDate" styleClass="input3" maxlength="8"
									onkeydown="enterNextFocus1();" />
							</td>
							<td width="13%" class="td1">
<%--								制单人--%>
							</td>
							<td width="34%">
<%--								<html:select property="makePerson" styleClass="input4"--%>
<%--									onkeydown="enterNextFocus1();">--%>
<%--									<html:option value=""></html:option>--%>
<%--									<html:options collection="operList1" property="value"--%>
<%--										labelProperty="label" />--%>
<%--								</html:select>--%>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td align="right">
								<html:submit property="method" styleClass="buttona"
									onclick="return gotoSearch();">
									<bean:message key="button.search" />
								</html:submit>
							</td>
						</tr>
					</table>
				</html:form>
				<html:form action="/clearaccountTbMaintainAC" styleClass="margin: 0">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="240">
											<b class="font14">日 结 单</b>
										</td>
										<td width="1404" height="22" align="center"
											background="<%=path%>/img/bg2.gif">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table border="0" width="95%" id="table1" cellspacing=2
						cellpadding=0 align="center">
						<tr align="center">
							<td class="td1" colspan="3">
								本期借方
							</td>
							<td class="td1" colspan="6">
								本期贷方
							</td>
						</tr>
						<tr align="center">
							<td class="td1">
								项目
							</td>
							<td class="td1">
								笔数
							</td>
							<td class="td1">
								发生额
							</td>

							<td class="td1">
								项目
							</td>
							<td class="td1">
								笔数
							</td>
							<td class="td1">
								正常本金
							</td>
							<td class="td1">
								利息
							</td>
							<td class="td1">
								逾期利息
							</td>
							<!-- <td class="td1"   >呆账核销金额</td> -->
							<td class="td1">
								挂账金额
							</td>
						</tr>
						<tr>
							<td class="td1">
								发放
							</td>
							<td>
								<html:text name="clearAccountBalanceInfoAF"
									property="loanaccord_count" styleClass="input3" readonly="true" />
							</td>
							<td>
								<html:text name="clearAccountBalanceInfoAF"
									property="loanaccord_occurMoney" styleClass="input3"
									readonly="true" />
							</td>
							<td class="td1">
								单笔回收
							</td>
							<td>
								<html:text name="clearAccountBalanceInfoAF"
									property="single_count" styleClass="input3" readonly="true" />
							</td>
							<td>
								<html:text name="clearAccountBalanceInfoAF"
									property="single_corpus" styleClass="input3" readonly="true" />
							</td>

							<td>
								<html:text name="clearAccountBalanceInfoAF"
									property="single_interest" styleClass="input3" readonly="true" />
							</td>
							<td>
								<html:text name="clearAccountBalanceInfoAF"
									property="single_punishInterest" styleClass="input3"
									readonly="true" />
							</td>
							<!-- <td > 
            <input type="text" class="input3" readonly="readonly">       </td> -->
							<td>
								<html:text name="clearAccountBalanceInfoAF"
									property="single_occurMoney" styleClass="input3"
									readonly="true" />
							</td>

						</tr>
						<tr>
							<td class="td1" >
								错账调整
							</td>
							<td>
								<html:text name="clearAccountBalanceInfoAF"
									property="adjustaccount_loanaccordCount" styleClass="input3"
									readonly="true" />
							</td>
							<td>
								<html:text name="clearAccountBalanceInfoAF"
									property="adjustaccount_loanaccordOccurMoney"
									styleClass="input3" readonly="true" />
							</td>
							<td class="td1">
								部分提前还款
							</td>
							<td>
								<html:text name="clearAccountBalanceInfoAF"
									property="part_count" styleClass="input3" readonly="true" />
							</td>
							<td>
								<html:text name="clearAccountBalanceInfoAF"
									property="part_corpus" styleClass="input3" readonly="true" />
							</td>

							<td>
								<html:text name="clearAccountBalanceInfoAF"
									property="part_interest" styleClass="input3" readonly="true" />
							</td>
							<td>
								<html:text name="clearAccountBalanceInfoAF"
									property="part_punishInterest" styleClass="input3"
									readonly="true" />
							</td>
							<td>
								<html:text name="clearAccountBalanceInfoAF"
									property="part_occurMoney" styleClass="input3" readonly="true" />
							</td>

						</tr>
						<tr>
							<td class="td1">
								合计：
							</td>
							<td>
								<html:text name="clearAccountBalanceInfoAF"
									property="debit_count" styleClass="input3" readonly="true" />
							</td>
							<td>
								<html:text name="clearAccountBalanceInfoAF"
									property="debit_occurMoney" styleClass="input3" readonly="true" />
							</td>
							<td class="td1">
								一次性清还
							</td>
							<td>
								<html:text name="clearAccountBalanceInfoAF" property="all_count"
									styleClass="input3" readonly="true" />
							</td>
							<td>
								<html:text name="clearAccountBalanceInfoAF"
									property="all_corpus" styleClass="input3" readonly="true" />
							</td>

							<td>
								<html:text name="clearAccountBalanceInfoAF"
									property="all_interest" styleClass="input3" readonly="true" />
							</td>
							<td>
								<html:text name="clearAccountBalanceInfoAF"
									property="all_punishInterest" styleClass="input3"
									readonly="true" />
							</td>
							<td>
								<html:text name="clearAccountBalanceInfoAF"
									property="all_occurMoney" styleClass="input3" readonly="true" />
							</td>

							<!-- <td  ><html:text name="clearAccountBalanceInfoAF" property="debit_bail_count" styleClass="input3" readonly="true"/> </td>
          <td  ><html:text name="clearAccountBalanceInfoAF" property="debit_bail_occurMoney" styleClass="input3" readonly="true"/> </td>
           -->
						</tr>
						<tr>
							<td class="td1">
							</td>
							<td>
								<input type="text" class="input3" readonly="readonly">
							</td>
							<td>
								<input type="text" class="input3" readonly="readonly">
							</td>
							<td class="td1">
								批量扣款
							</td>
							<td>
								<html:text name="clearAccountBalanceInfoAF"
									property="batch_count" styleClass="input3" readonly="true" />
							</td>
							<td>
								<html:text name="clearAccountBalanceInfoAF"
									property="batch_corpus" styleClass="input3" readonly="true" />
							</td>

							<td>
								<html:text name="clearAccountBalanceInfoAF"
									property="batch_interest" styleClass="input3" readonly="true" />
							</td>
							<td>
								<html:text name="clearAccountBalanceInfoAF"
									property="batch_punishInterest" styleClass="input3"
									readonly="true" />
							</td>
							<td>
								<html:text name="clearAccountBalanceInfoAF"
									property="batch_occurMoney" styleClass="input3" readonly="true" />
							</td>

							<!-- ===============================隐藏表单存放复核员和记账员======================= -->
							<html:hidden name="clearAccountBalanceInfoAF"
								property="checkPerson" />
							<html:hidden name="clearAccountBalanceInfoAF"
								property="clearAccountPerson" />
						</tr>
						<!-- <tr> 
          <td    class="td1">呆账核销</td>
          <td   > 
            <html:text name="clearAccountBalanceInfoAF" property="destroy_count" styleClass="input3" readonly="true"/>          </td>
          <td   ><html:text name="clearAccountBalanceInfoAF" property="destroy_corpus" styleClass="input3" readonly="true"/> </td>
          <td   > 
            <html:text name="clearAccountBalanceInfoAF" property="destroy_overdueCorpus" styleClass="input3" readonly="true"/>          </td>
          <td   > 
            <html:text name="clearAccountBalanceInfoAF" property="destroy_interest" styleClass="input3" readonly="true"/>          </td>
          <td   > 
            <html:text name="clearAccountBalanceInfoAF" property="destroy_punishInterest" styleClass="input3" readonly="true"/>          </td>
          <td   > 
            <html:text name="clearAccountBalanceInfoAF" property="destroy_occurMoney" styleClass="input3" readonly="true"/>          </td>
          <td   ><input type="text" class="input3" readonly="readonly"> </td>
          <td > 
            <input type="text" class="input3" readonly="readonly">       </td>
          <td   class="td1" ></td>
          <td    ><input type="text" class="input3" readonly="readonly"> </td>
          <td    ><input type="text" class="input3" readonly="readonly"> </td>
        </tr><tr> 
          <td    class="td1">核销收回</td>
          <td   > 
            <html:text name="clearAccountBalanceInfoAF" property="destroyback_count" styleClass="input3" readonly="true"/>          </td>
          <td   > 
            <html:text name="clearAccountBalanceInfoAF" property="destroyback_corpus" styleClass="input3" readonly="true"/>          </td>
          <td   > 
            <html:text name="clearAccountBalanceInfoAF" property="destroyback_overdueCorpus" styleClass="input3" readonly="true"/>          </td>
          <td   > 
            <html:text name="clearAccountBalanceInfoAF" property="destroyback_interest" styleClass="input3" readonly="true"/>          </td>
          <td   > 
            <html:text name="clearAccountBalanceInfoAF" property="destroyback_punishInterest" styleClass="input3" readonly="true"/>          </td>
          <td   > 
            <html:text name="clearAccountBalanceInfoAF" property="destroyback_occurMoney" styleClass="input3" readonly="true"/>          </td>
          <td  ><input type="text" class="input3" readonly="readonly"> </td>
          <td > 
            <input type="text" class="input3" readonly="readonly">       </td>
          <td  class="td1" >&nbsp;</td>
          <td class="td7"   >&nbsp; </td>
          <td class="td7"   >&nbsp; </td>
        </tr>
         -->
						<tr>
							<td class="td1">
							</td>
							<td>
								<input type="text" class="input3" readonly="readonly">
							</td>
							<td>
								<input type="text" class="input3" readonly="readonly">
							</td>
							<td class="td1">
								挂账
							</td>
							<td>
								<html:text name="clearAccountBalanceInfoAF"
									property="overpay_count" styleClass="input3" readonly="true" />
							</td>
							<td>
								<input type="text" class="input3" readonly="readonly">
							</td>

							<td>
								<input type="text" class="input3" readonly="readonly">
							</td>
							<td>
								<input type="text" class="input3" readonly="readonly">
							</td>
							<td>
								<html:text name="clearAccountBalanceInfoAF"
									property="overpay_occurMoney" styleClass="input3"
									readonly="true" />
							</td>
						</tr>
						<tr>
							<td class="td1">
							</td>
							<td>
								<input type="text" class="input3" readonly="readonly">
							</td>
							<td>
								<input type="text" class="input3" readonly="readonly">
							</td>
							<td class="td1">
								错账调整
							</td>
							<td>
								<html:text name="clearAccountBalanceInfoAF"
									property="adjustaccount_count" styleClass="input3"
									readonly="true" />
							</td>
							<td>
								<html:text name="clearAccountBalanceInfoAF"
									property="adjustaccount_corpus" styleClass="input3"
									readonly="true" />
							</td>

							<td>
								<html:text name="clearAccountBalanceInfoAF"
									property="adjustaccount_interest" styleClass="input3"
									readonly="true" />
							</td>
							<td>
								<html:text name="clearAccountBalanceInfoAF"
									property="adjustaccount_punishInterest" styleClass="input3"
									readonly="true" />
							</td>
							<!-- <td   > 
            <html:text name="clearAccountBalanceInfoAF" property="adjustaccount_destroyOccurMoney" styleClass="input3" readonly="true"/>          </td> -->
							<td>
								<html:text name="clearAccountBalanceInfoAF"
									property="adjustaccount_occurMoney" styleClass="input3"
									readonly="true" />
							</td>
						</tr>
						<!-- <tr>
          <td    class="td1">保证金</td>
          <td   > <html:text name="clearAccountBalanceInfoAF" property="credit_bail_count" styleClass="input3" readonly="true"/></td>
          <td   ><input type="text" class="input3" readonly="readonly"></td>
          <td   ><input type="text" class="input3" readonly="readonly"></td>
          <td   ><input type="text" class="input3" readonly="readonly"></td>
          <td   ><input type="text" class="input3" readonly="readonly"></td>
          <td   ><input type="text" class="input3" readonly="readonly"></td>
          <td   ><input type="text" class="input3" readonly="readonly"></td>
         <td > 
             <html:text name="clearAccountBalanceInfoAF" property="credti_bail_occurMoney" styleClass="input3" readonly="true"/>       </td>
          <td class="td1" >&nbsp;</td>
          <td class="td7" >&nbsp;</td>
          <td class="td7" >&nbsp;</td>
        </tr>
        -->
						<tr>
							<td class="td1">
							</td>
							<td>
								<input type="text" class="input3" readonly="readonly">
							</td>
							<td>
								<input type="text" class="input3" readonly="readonly">
							</td>
							<td class="td1">
								合计：
							</td>
							<td>
								<html:text name="clearAccountBalanceInfoAF"
									property="credit_count" styleClass="input3" readonly="true" />
							</td>
							<td>
								<html:text name="clearAccountBalanceInfoAF"
									property="credit_corpus" styleClass="input3" readonly="true" />
							</td>

							<td>
								<html:text name="clearAccountBalanceInfoAF"
									property="credit_interest" styleClass="input3" readonly="true" />
							</td>
							<td>
								<html:text name="clearAccountBalanceInfoAF"
									property="credit_punishInterest" styleClass="input3"
									readonly="true" />
							</td>
							<!-- <td   > 
            <html:text name="clearAccountBalanceInfoAF" property="credit_destoryOccurMoney" styleClass="input3" readonly="true"/>         </td> -->
							<td>
								<html:text name="clearAccountBalanceInfoAF"
									property="credit_overpayOccurMoney" styleClass="input3"
									readonly="true" />
							</td>

						</tr>
					</table>
					<table border="0" width="95%" id="table1" cellspacing="2"
						cellpadding="0" align="center">

						
						<tr align="center">
							<td class="td1" width="11%" height="8">
								期初本金余额
							</td>
							<td width="12%" height="8">
								<html:text name="clearAccountBalanceInfoAF"
									property="initialStages_corpus" styleClass="input3"
									readonly="true" />
							</td>
							<td class="td1" width="9%" height="8">
								正常本金发生额
							</td>
							<td width="16%" height="8" colspan="3">
								<html:text name="clearAccountBalanceInfoAF"
									property="corpus_occurMoney" styleClass="input3"
									readonly="true" />
							</td>

							<td class="td1" width="12%" height="8">
								期末本金余额
							</td>
							<td width="15%" height="8">
								<html:text name="clearAccountBalanceInfoAF"
									property="final_corpus" styleClass="input3" readonly="true" />
							</td>
						</tr>
						<tr align="center">
							<td width="11%" class="td1">
								期初利息余额
							</td>
							<td width="12%">
								<html:text name="clearAccountBalanceInfoAF"
									property="initialStages_interest" styleClass="input3"
									readonly="true" />
							</td>
							<td width="9%" class="td1">
								利息发生额
							</td>
							<td colspan="3">
								<html:text name="clearAccountBalanceInfoAF"
									property="interest_occurMoney" styleClass="input3"
									readonly="true" />
							</td>
							<td width="12%" class="td1">
								期末利息余额
							</td>
							<td width="15%">
								<html:text name="clearAccountBalanceInfoAF"
									property="final_interest" styleClass="input3" readonly="true" />
							</td>
						</tr>
						<tr align="center">
							<td width="11%" class="td1">
								&#26399;&#21021;&#36926;&#26399;&#21033;&#24687;&#20313;&#39069;
							</td>
							<td width="12%">
								<html:text name="clearAccountBalanceInfoAF"
									property="initialStages_punishInterest" styleClass="input3"
									readonly="true" />
							</td>
							<td width="9%" class="td1">
								&#36926;&#26399;&#21033;&#24687;&#21457;&#29983;&#39069;
							</td>
							<td colspan="3">
								<html:text name="clearAccountBalanceInfoAF"
									property="punishInterest_occurMoney" styleClass="input3"
									readonly="true" />
							</td>
							<td width="12%" class="td1">
								期末逾期利息余额
							</td>
							<td width="15%">
								<html:text name="clearAccountBalanceInfoAF"
									property="final_punishInterest" styleClass="input3"
									readonly="true" />
							</td>
						</tr>
						<!-- 
        <tr align="center">
          <td width="11%"   class="td1">期初呆账核销金额</td>
          <td width="12%"  >
            <html:text name="clearAccountBalanceInfoAF" property="initialStages_destroyOccurMoney" styleClass="input3" readonly="true"/>
          </td>
          <td width="9%" class="td1"  >呆账核销发生额</td>
          <td width="16%"  ><html:text name="clearAccountBalanceInfoAF" property="destroyOccurMoney" styleClass="input3" readonly="true"/></td>
          <td width="10%" class="td1"  >核销收回发生额</td>
          <td width="15%"  ><html:text name="clearAccountBalanceInfoAF" property="destroybackOccurMoney" styleClass="input3" readonly="true"/></td>
          <td width="12%"   class="td1">期末呆账核销金额</td>
          <td width="15%" ><html:text name="clearAccountBalanceInfoAF" property="final_destroyOccurMoney" styleClass="input3" readonly="true"/></td>
        </tr>
         -->
						<tr align="center">
							<td width="11%" class="td1">
								期初挂账余额
							</td>
							<td width="12%">
								<html:text name="clearAccountBalanceInfoAF"
									property="initialStages_overpayOccurMoney" styleClass="input3"
									readonly="true" />
							</td>
							<td width="9%" class="td1">
								挂账发生额
							</td>
							<td colspan="3">
								<html:text name="clearAccountBalanceInfoAF"
									property="overpayOccurMoney" styleClass="input3"
									readonly="true" />
							</td>
							<td width="12%" class="td1">
								期末挂账余额
							</td>
							<td width="15%">
								<html:text name="clearAccountBalanceInfoAF"
									property="final_overpayOccurMoney" styleClass="input3"
									readonly="true" />
							</td>
						</tr>
						<!-- 
        <tr align="center">
          <td width="11%"   class="td1">期初保证金余额</td>
          <td width="12%"  >
            <html:text name="clearAccountBalanceInfoAF" property="initialStages_bailOccurMoney" styleClass="input3" readonly="true"/>
          </td>
          <td width="9%" class="td1"  >保证金发生额</td>
          <td width="16%"  ><html:text name="clearAccountBalanceInfoAF" property="bailOccurMoney" styleClass="input3" readonly="true"/></td>
          <td width="10%" class="td1"  >保证金利息</td>
          <td width="15%"  ><html:text name="clearAccountBalanceInfoAF" property="bailInterestOccurMoney" styleClass="input3" readonly="true"/></td>
          <td width="12%"   class="td1">期末保证金余额</td>
          <td width="15%" ><html:text name="clearAccountBalanceInfoAF" property="final_bailOccurMoney" styleClass="input3" readonly="true"/></td>
        </tr>
         -->
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr valign="bottom">
							<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="70">
											<html:submit property="method" styleClass="buttona">
												<bean:message key="button.print" />
											</html:submit>
										</td>
										<td width="70">
											<html:button property="method" styleClass="buttonb"
												onclick="executeAjax();">
												<bean:message key="button.look.infor_1" />
											</html:button>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</html:form>
			</td>
		</tr>
	</table>
</body>
</html:html>

