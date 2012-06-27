<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ include file="/checkUrl.jsp"%>
<%@page
	import="org.xpup.hafmis.sysloan.accounthandle.adjustaccount.action.AdjustAccountTbShowdAC"%>
<%
	String path = request.getContextPath();
	Object pagination = session
			.getAttribute(AdjustAccountTbShowdAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

	<title>错帐调整维护</title>
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script language="javascript" src="<%=path%>/js/common.js" />
  
</head>
<script language="javascript">
	var oldColor="#ffffff";                     //原来的颜色
	var newColor="#E8FCFD";                     //要变成的颜色
	function chgBGColor(oTD){
		oldColor=oTD.style.backgroundColor;		//保存当前颜色
		oTD.style.backgroundColor=newColor;		//改变表格颜色
		newColor=oldColor;                 		//改变下次要变成的颜色
	}
</script>
<script>
	function loads(){
		for(i=0;i<document.all.length;i++){//固定写法
			if(document.all.item(i).type=="submit"){//如果按钮是提交
	
				if(document.all.item(i).value=="删除"){
					s1=i;
				}
				if(document.all.item(i).value=="打印"){
					s2=i;
				}
			}
		}
	}
	var tr="tr0"; 
	function gettr(trindex) {
		  tr=trindex;
	}
	function gettr(trindex) {
	  tr=trindex;
	  update();
	}
	
	function update() {
		if(document.getElementById(tr)!=null){
			var status=document.getElementById(tr).childNodes[9].innerHTML;
			if(status=='5'||status=='6'){
			  	document.all.item(s1).disabled=true;
			  	document.all.item(s2).disabled=false;
			}
		}
	}
	function isDelete(){
	  var x=confirm("提示是否删除所选记录！");
	  if(x){
		return true;
	  }else{
	    return false;
	  }
	}
	function reportErrors() {
		<logic:messagesPresent>
			var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
			alert(message);
		</logic:messagesPresent>
	}
function gettr2(trindex) {
  tr=trindex;
  //update1();
  sureType();
}
function sureType(){
   var status=document.getElementById(tr).childNodes[10].innerHTML;
 	if(status=='发放'){
  	 var id=document.getElementById(tr).childNodes[11].innerHTML;
  		window.open('<%=path%>/sysloan/loanaccordFindAC.do?id='+id,'','width=700,height=450,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',scrollbars=yes');return gotoShow();          
 	}
	if(status == "批量回收" || status == "单笔回收"){   
		var originalitybizType
		if(status == "批量回收"){
			originalitybizType='5'
		}
		if(status == "单笔回收"){
			originalitybizType='2'
		}   	
	    var id  = document.getElementById(tr).childNodes[11].innerHTML;
	    var contractId=document.getElementById(tr).childNodes[12].innerHTML;
	     
	    window.open('<%=path%>/sysloan/loancallbackTbForwardURLWindowAC.do?contractId='+contractId+'&headId='+id+'&type='+originalitybizType,'','width=700,height=450,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',scrollbars=yes');     
 	}
 	 if(status =="呆账核销（中心）"||status =="呆账核销（其他）")
 	 {
 	 	var id  = document.getElementById(tr).childNodes[11].innerHTML;
	    var contractId=document.getElementById(tr).childNodes[12].innerHTML;
 	 	window.open('<%=request.getContextPath()%>/sysloan/badDebtDestroyTbForwardURLWindowAC.do?contractId='+contractId+'&headId='+id,'','width=700,height=450,ttop='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',scrollbars=yes'); 
 	 }
 }
</script>
<body bgcolor="#FFFFFF" text="#000000"
	onload="reportErrors();loads();update();" onContextmenu="return false">

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
										background="<%=path%>/img/buttong.gif" align="center"
										valign="top" style="PADDING-top: 7px">
										<a href="adjustAccountTaShowAC.do" class=a2>办理错账调整</a>
									</td>
									<td width="112" height="37"
										background="<%=path%>/img/buttonbl.gif" align="center"
										style="PADDING-top: 7px" valign="top">
										错账调整维护
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
										<font color="00B5DB">账务处理&gt;错账维护</font>
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
				<html:form action="/adjustAccountTbFindAC" style="margin: 0">
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=0 align="center">
						<tr>
							<td width="17%" class="td1">
								扣款账号
								<br>
							</td>
							<td width="33%">
								<html:text name="adjustAccountFindAF"
									property="adjustAccountTbFindDTO.loanKouAcc"
									styleClass="input3" maxlength="50"
									onkeydown="enterNextFocus1();"></html:text>
							</td>
							<td width="17%" class="td1">
								合同编号
							</td>
							<td width="33%">
								<html:text name="adjustAccountFindAF"
									property="adjustAccountTbFindDTO.contractId"
									styleClass="input3" maxlength="50"
									onkeydown="enterNextFocus1();"></html:text>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								借款人姓名
							</td>
							<td width="33%">
								<html:text name="adjustAccountFindAF"
									property="adjustAccountTbFindDTO.borrowerName"
									styleClass="input3" maxlength="50"
									onkeydown="enterNextFocus1();"></html:text>
							</td>
							<td width="17%" class="td1">
								证件号码
							</td>
							<td>
								<html:text name="adjustAccountFindAF"
									property="adjustAccountTbFindDTO.cardNum" styleClass="input3"
									maxlength="50" onkeydown="enterNextFocus1();"></html:text>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								凭证编号
							</td>
							<td width="33%">
								<html:text name="adjustAccountFindAF"
									property="adjustAccountTbFindDTO.docNum" styleClass="input3"
									maxlength="50" onkeydown="enterNextFocus1();"></html:text>
							</td>
							<td width="17%" class="td1">
								业务类型
							</td>
							<td>
								<html:select name="adjustAccountFindAF"
									property="adjustAccountTbFindDTO.bizType" styleClass="input4"
									onkeydown="enterNextFocus1();">
									<html:option value=""></html:option>
									<html:optionsCollection property="bizTypeMap"
										name="adjustAccountFindAF" label="value" value="key" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								放款银行
							</td>
							<td width="33%">
								<span class="td4"> <html:select
										name="adjustAccountFindAF"
										property="adjustAccountTbFindDTO.loanBankId"
										styleClass="input4" onkeydown="enterNextFocus1();">
										<html:option value=""></html:option>
										<html:options collection="loanBankIdList" property="value"
											labelProperty="label" />
									</html:select> </span>
							</td>
							<td width="17%" class="td1">
								业务状态
							</td>
							<td>
								<html:select name="adjustAccountFindAF"
									property="adjustAccountTbFindDTO.bizSt" styleClass="input4"
									onkeydown="enterNextFocus1();">
									<html:option value=""></html:option>
									<html:optionsCollection property="bizStMap"
										name="adjustAccountFindAF" label="value" value="key" />
								</html:select>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td align="right">
								<input type="submit" name="submit1" class=buttona value="查询" />
							</td>
						</tr>
					</table>
				</html:form>
				<html:form action="/adjustAccountTbMainTainAC" style="margin: 0">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td class=h4>
								合计：发放金额
								<u>：<bean:write name="adjustAccountFindAF"
										property="sumOccurMoney" format="0.00" />
								</u> 回收本金
								<u>：<bean:write name="adjustAccountFindAF"
										property="sumCallbackCorpus" format="0.00" />
								</u> 回收利息
								<u>：<bean:write name="adjustAccountFindAF"
										property="sumCallbackInterest" format="0.00" />
								</u> 逾期利息
								<u>：<bean:write name="adjustAccountFindAF"
										property="sumPunishInterest" format="0.00" />
								</u>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="161">
											<b class="font14">错账维护列表</b>
										</td>
										<td width="749" height="22" align="center"
											background="<%=path%>/img/bg2.gif">
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
					<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
						cellpadding="3" align="center">
						<tr align="center" bgcolor="C4F0FF">
							<td height="23" bgcolor="C4F0FF">
								&nbsp;
							</td>
							<td align="center" class=td2>
								凭证编号
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('res.loankouacc')">扣款账号</a>
								<logic:equal name="pagination" property="orderBy"
									value="res.loankouacc">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('res.borrowername')">借款人姓名</a>
								<logic:equal name="pagination" property="orderBy"
									value="res.borrowername">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('res.wrongbiztype')">业务类型</a>
								<logic:equal name="pagination" property="orderBy"
									value="res.wrongbiztype">
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
								回收本金
							</td>
							<td align="center" class=td2>
								回收利息
							</td>
							<td align="center" class=td2>
								逾期利息
							</td>
							<td align="center" class=td2>
								实还总额
							</td>
						</tr>
						<logic:notEmpty name="adjustAccountFindAF" property="list">
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate id="adjustAccountTbListDTO"
								name="adjustAccountFindAF" property="list" indexId="i">
								<%
											j++;
											if (j % 2 == 0) {
												strClass = "td8";
											} else {
												strClass = "td9";
											}
								%>
								<tr id="tr<%=i%>"
									onclick='gotoClickpp("<%=i%>", idAF);gettr("tr<%=i%>");'
									onMouseOver='this.style.background="#eaeaea"'
									onMouseOut='gotoColorpp("<%=i%>", idAF);'
									class="<%=strClass%>" onDblClick="">
									<td>
										<input id="s<%=i%>" type="radio" name="id"
											value="<bean:write name="adjustAccountTbListDTO" property="flowHeadId"/>,<bean:write name="adjustAccountTbListDTO" property="bizType"/>"
											<%if(new Integer(0).equals(i)) out.print("checked"); %>>
									</td>
									<td valign="top">
										<p>
											<bean:write name="adjustAccountTbListDTO" property="docNum" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="adjustAccountTbListDTO"
												property="loanKouAcc" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="adjustAccountTbListDTO"
												property="borrowerName" />
										</p>
									</td>
									<td valign="top">
										<p>
											<a href="#" onClick="gettr2('tr<%=i%>');"><bean:write
													name="adjustAccountTbListDTO" property="bizTypeStr" />
											</a>
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="adjustAccountTbListDTO"
												property="occurMoney" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="adjustAccountTbListDTO"
												property="callbackCorpus" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="adjustAccountTbListDTO"
												property="callbackInterest" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="adjustAccountTbListDTO"
												property="punishInterest" />
										</p>
									</td>
									<td valign="top">
										<p>
											<bean:write name="adjustAccountTbListDTO"
												property="callbackTotal" />
										</p>
									</td>
									<td style="display:none">
										<bean:write name="adjustAccountTbListDTO" property="bisSt" />
									</td>
									<td style="display:none">
										<bean:write name="adjustAccountTbListDTO"
											property="bizTypeStr" />
									</td>
									<td style="display:none">
										<bean:write name="adjustAccountTbListDTO"
											property="wrongFlowNum" />
									</td>
									<td style="display:none">
										<bean:write name="adjustAccountTbListDTO"
											property="contractId" />
									</td>
								</tr>
								<tr>
									<td colspan="14" class=td5></td>
								</tr>
							</logic:iterate>
						</logic:notEmpty>

						<logic:empty name="adjustAccountFindAF" property="list">
							<tr>
								<td colspan="11" height="30" style="color:red">
									没有找到与条件相符合的结果！
								</td>
							</tr>
							<tr>
								<td colspan="11" class=td5></td>
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
											<jsp:include page="../../../inc/pagination.jsp">
												<jsp:param name="url" value="adjustAccountTbShowdAC.do" />
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
									<logic:notEmpty name="adjustAccountFindAF" property="list">
										<tr>
											<td width="70">
												<html:submit property="method" styleClass="buttona"
													onclick="return isDelete();">
													<bean:message key="button.delete" />
												</html:submit>
											</td>
											<td width="70">
												<html:submit property="method" styleClass="buttona">
													<bean:message key="button.print" />
												</html:submit>
											</td>
										</tr>
									</logic:notEmpty>
									<logic:empty name="adjustAccountFindAF" property="list">
										<tr>
											<td width="70">
												<html:submit property="method" styleClass="buttona"
													onclick="return isDelete();" disabled="true">
													<bean:message key="button.delete" />
												</html:submit>
											</td>
											<td width="70">
												<html:submit property="method" styleClass="buttona"
													disabled="true">
													<bean:message key="button.print" />
												</html:submit>
											</td>
										</tr>
									</logic:empty>
								</table>
							</td>
						</tr>
					</table>
				</html:form>
	</table>

</body>
</html:html>
