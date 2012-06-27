<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page import="org.xpup.hafmis.sysloan.accounthandle.clearaccount.action.ClearaccountTbMXShowAC"%>
<%
			Object pagination = request.getSession(false).getAttribute(
			ClearaccountTbMXShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String path = request.getContextPath();
%>
<html:html>
<head>
	<title>个贷管理</title>
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script language="javascript" src="<%=path%>/js/common.js"></script>
</head>
<script type="text/javascript">
function loads(){
	document.clearaccountAF.loanBankName.value="";
	document.clearaccountAF.makePerson.value="";
	document.clearaccountAF.bizType.value="";
	document.clearaccountAF.borrowerName.value="";
	document.clearaccountAF.loanKouAcc.value="";
	document.clearaccountAF.contractId.value="";
	document.clearaccountAF.docNum.value="";
	
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
	var count="<bean:write name="pagination" property="nrOfElements" />";
	if(count==0){		
		document.all.disp1.disabled="true";
	}
}
var tr="tr0";
function gettr2(trindex) {
	  tr=trindex;
	  sureType();
}
function sureType(){
     var bizType=document.getElementById(tr).childNodes[3].childNodes[0].innerHTML.trim();
  	 if(bizType=="批量回收" || bizType=="单笔回收" || bizType=="部分提前还款" || bizType=="一次性还清"){      	
     var id  = document.getElementById(tr).childNodes[15].innerHTML.trim();
     var contractId=document.getElementById(tr).childNodes[1].innerHTML.trim();
     var originalitybizType=document.getElementById(tr).childNodes[11].innerHTML.trim();
     window.open('<%=request.getContextPath()%>/sysloan/loancallbackTbForwardURLWindowAC.do?contractId='+contractId+'&headId='+id+'&type='+originalitybizType,'','width=700,height=450,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',scrollbars=yes');     
 	}
 	if(bizType=="发放"){  	
     var id  = document.getElementById(tr).childNodes[15].innerHTML.trim();
     window.open('<%=request.getContextPath()%>/sysloan/loanaccordFindAC.do?id='+id,'','width=700,height=450,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',scrollbars=yes');     
 	 }
 	 if(bizType == "挂账"){ 	     	
     var id  = document.getElementById(tr).childNodes[15].innerHTML.trim();
     window.open('<%=request.getContextPath()%>/sysloan/overPayFindAC.do?id='+id,'','width=700,height=450,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',scrollbars=yes');     
 	 }
 	 if(bizType == "保证金"){ 	     	
     var id  = document.getElementById(tr).childNodes[15].innerHTML.trim();
     window.open('<%=request.getContextPath()%>/sysloan/bailFindAC.do?id='+id,'','width=700,height=450,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',scrollbars=yes');     
 	 }
 	  if(bizType == "错账调整"){ 	     	
     var id  = document.getElementById(tr).childNodes[15].innerHTML.trim();
     window.open('<%=request.getContextPath()%>/sysloan/adjustAccountFindAC.do?id='+id,'','width=700,height=450,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',scrollbars=yes');     
 	 } 
 	 if(bizType =="核销收回（中心）"||bizType =="核销收回（其他）")
 	 {
 	 	var id  = document.getElementById(tr).childNodes[15].innerHTML;
 	 	window.open('<%=request.getContextPath()%>/sysloan/destoryBackTbWindowAC.do?id='+id,'','width=700,height=450,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',scrollbars=yes');
 	 }
 	  if(bizType =="呆账核销（中心）"||bizType =="呆账核销（其他）")
 	 {
 	 	var id  = document.getElementById(tr).childNodes[15].innerHTML.trim();
     	var contractId=document.getElementById(tr).childNodes[1].innerHTML.trim();
 	 	window.open('<%=request.getContextPath()%>/sysloan/badDebtDestroyTbForwardURLWindowAC.do?contractId='+contractId+'&headId='+id,'','width=700,height=450,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',scrollbars=yes'); 
 	 }
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onload="return loads();" onContextmenu="return true">
	<jsp:include page="../../../inc/sort.jsp">
		<jsp:param name="url" value="clearaccountTbMXShowAC.do" />
	</jsp:include>
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
									<td width="112" height="37"
										 align="center"
										valign="top" style="PADDING-top: 7px">
										
									</td>
									<td width="112" height="37"
										 align="center"
										style="PADDING-top: 7px" valign="top">
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
										<font color="00B5DB">账务处理&gt;查看明细</font>
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
									<td height="22" bgcolor="#CCCCCC" align="center" width="160">
										<b class="font14">查 询 条 件</b>
									</td>
									<td height="22" background="<%=path%>/img/bg2.gif"
										align="center" width="750">
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<html:form action="/clearaccountTbMXFindAC" style="margin: 0">
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=0 align="center">
						<tr>
							<td width="17%" class="td1">
								凭证编号
							</td>
							<td width="33%" colspan="3">
								<html:text name="clearaccountAF" property="docNum"
									styleId="txtsearch" onkeydown="enterNextFocus1();"
									styleClass="input3" />
							</td>
							<td width="17%" class="td1">
								合同编号
							</td>
							<td width="33%">
								<html:text name="clearaccountAF" property="contractId"
									styleId="txtsearch" onkeydown="enterNextFocus1();"
									styleClass="input3" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								扣款账号
							</td>
							<td width="33%" align="center" colspan="3">
								<html:text name="clearaccountAF" property="loanKouAcc"
									styleId="txtsearch" onkeydown="enterNextFocus1();"
									styleClass="input3" />
							</td>
							<td width="17%" class="td1">
								借款人姓名
							</td>
							<td width="33%">
								<html:text name="clearaccountAF" property="borrowerName"
									styleId="txtsearch" onkeydown="enterNextFocus1();"
									styleClass="input3" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								业务类型
							</td>
							<td width="33%" align="center" colspan="3">
								<html:select name="clearaccountAF" property="bizType"
									styleId="txtsearch" onkeydown="enterNextFocus1();"
									styleClass="input3">
									<html:option value=""></html:option>
									<html:optionsCollection property="bizTypeMap"
										name="clearaccountAF" label="value" value="key" />
								</html:select>
							</td>
							<td width="17%" class="td1">
								制单人
							</td>
							<td width="33%">
								<html:select name="clearaccountAF" property="makePerson"
									styleId="txtsearch" onkeydown="enterNextFocus1();"
									styleClass="input3">
									<html:option value=""></html:option>
									<html:options collection="operList1" property="value"
										labelProperty="label" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								放款银行
							</td>
							<td width="33%" align="center" colspan="3">
								<html:select name="clearaccountAF" property="loanBankName"
									styleId="txtsearch" onkeydown="enterNextFocus1();"
									styleClass="input3">
									<html:option value=""></html:option>
									<html:options collection="loanBankNameList" property="value"
										labelProperty="label" />
								</html:select>
							</td>
							<td width="17%" class="td1">
							</td>
							<td width="33%" class="td7">&nbsp;
							</td>
						</tr>
					</table>
					<table width="95%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td align="right">

								<html:submit property="method" styleClass="buttona">
									<bean:message key="button.search" />
								</html:submit>

							</td>
						</tr>
					</table>
				</html:form>
				<html:form action="/clearaccountTbMXMaintainAC" style="margin: 0">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td class=h4>
								合计：发放金额
								<u>：<bean:write name="clearaccountAF"
										property="occurMoneyTotle" /> </u>回收本金
											<u>：<bean:write name="clearaccountAF"
													property="reclaimCorpusTotle" /> </u> 回收利息
											<u>：<bean:write name="clearaccountAF"
													property="reclaimAccrualTotle" /> </u> 回收罚息
											<u>：<bean:write name="clearaccountAF"
													property="realPunishInterestTotle" /> </u> 回收金额
								<!-- <u>：<bean:write name="clearaccountAF"
										property="sumbackmoney" /> </u> 呆账核销金额 -->
								<u>：<bean:write name="clearaccountAF"
										property="badDebtTotle" /> </u> 挂账金额
								<u>：<bean:write name="clearaccountAF"
										property="putUpMoneyTotle" /> </u> 
							</td>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="160">
											<b class="font14">业 务 列 表</b>
										</td>
										<td width="750" height="22" align="center"
											background="images/bg2.gif">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr bgcolor="1BA5FF">
							<td align="center" height="6" colspan="1"></td>
						</tr>
					</table>
					<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1" cellpadding="3" align="center">
          <tr align="center" bgcolor="C4F0FF"> 
           
							<td align="center" class=td2>
								凭证编号
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('contractId')">合同编号</a>
								<logic:equal name="pagination" property="orderBy"
									value="contractId">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('borrowername')">借款人姓名</a>
								<logic:equal name="pagination" property="orderBy"
									value="borrowername">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('bizType')">业务类型</a>
								<logic:equal name="pagination" property="orderBy"
									value="bizType">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								发放金额
							</td>
							<td align="center" class=td2>
								实收本金
							</td>
							<td align="center" class=td2>
								实还利息
							</td>
							<td align="center" class=td2>
								实还逾期息
							</td>
							<td align="center" class=td2>
								实还总额
							</td>
							
							<!-- <td align="center" class=td2>
								呆账核销金额
							</td>
							 -->
							<td align="center" class=td2>
								挂账金额
							</td>
							<td align="center" class=td2>
								是否公积金还贷
							</td>
							<td align="center" class=td2>
								委托银行
							</td>
							<td align="center" class=td2>
								办理日期
							</td>
							<td align="center" class=td2>
								业务状态
							</td>
						</tr>
						<logic:notEmpty name="clearaccountAF" property="list">
						

<% int j=0;
			String strClass="";
		%>
							<logic:iterate name="clearaccountAF" property="list" id="element"
								indexId="i">
								<%j++;
			if (j%2==0) {strClass = "td8";
			}
		    else {strClass = "td9";
		    }
		%>
								
								<tr id="tr<%=i%>" class="<%=strClass%>">
									<td>
										<bean:write name="element" property="docNum" />
									</td>
									<td>
										<bean:write name="element" property="contractId" />
									</td>
									<td>
										<bean:write name="element" property="borrowerName" />
									</td>
									<td>
										<a href="#" onClick="gettr2('tr<%=i%>');" /><bean:write
												name="element" property="bizType" /> </a>
									</td>
									<td>
										<bean:write name="element" property="occurMoney" />
									</td>
									<td>
										<p>
											<bean:write name="element" property="reclaimCorpus" />
										</p>
									</td>
									<td>
										<p>
											<bean:write name="element" property="reclaimAccrual" />
										</p>
									</td>
									<td>
										<p>
											<bean:write name="element" property="realPunishInterest" />
										</p>
									</td>
									<td>
										<bean:write name="element" property="sumReclaimMoney" />
									</td>
									
									<!-- <td>
										<bean:write name="element" property="badDebt" />
									</td>
									 -->
									<td>
										<bean:write name="element" property="putUpMoney" />
									</td>
									<td>
										<bean:write name="element" property="isGjjLoanBack" />
									</td>
									<td>
										<bean:write name="element" property="loanBank" />
									</td>
									<!-- <td>
										<bean:write name="element" property="bail" />
									</td>
									 -->
									<td>
										<bean:write name="element" property="bizDate" />
									</td>
									<td>
										<bean:write name="element" property="bizSt" />
									</td>
									<td style="display:none">
										<bean:write name="element" property="originalitybizType" />
									</td>
									<td style="display:none">
										<bean:write name="element" property="flowHeadId" />
									</td>
								</tr>
								
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="clearaccountAF" property="list">
							<tr>
								<td colspan="9" height="30" style="color:red">
									没有找到与条件相符合结果！
								</td>
							</tr>
							
						</logic:empty>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr class="td1">
							<td align="center">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr align="center">
										<td align="left">
											共
											<bean:write name="pagination" property="nrOfElements" />
											项
										</td>
										<td align="right">
											<jsp:include page="/inc/pagination.jsp">
												<jsp:param name="url" value="clearaccountTbMXShowAC.do" />
											</jsp:include>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr valign="bottom">
							<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
											<td width="70">
												<html:submit property="method" styleClass="buttona" styleId="disp1">
													<bean:message key="button.print" />
												</html:submit>
											</td>
                <td width="70"> 
                  <input type="button" class="buttona" value="关闭" onclick="window.close();">
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
