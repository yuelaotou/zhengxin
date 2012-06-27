<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page
	import="org.xpup.hafmis.sysloan.querystatistics.checkqueryplfn.action.CheckQueryPlFnTBShowAC"%>
<%
	String path = request.getContextPath();
	Object pagination = request.getSession().getAttribute(
			CheckQueryPlFnTBShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>个贷财务对账查询</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script src="<%=path%>/js/common.js">
	</script>

</head>
<script>
	var old_temp="tr0";
	function gotoClick1(id1,id2,form){
		var temp1;
		var temp2;
		var temp3;
		eval("temp1="+id1);
		eval("temp3="+old_temp);
		eval("temp2=form."+id2);
		temp2.checked="true";
		old_temp=id1;
	}
	function toprint(){
	
		window.open('<%=path%>'+'/sysloan/checkQueryPlFnWindow2AC.do',"window","height=450,width=700,top="+(window.screen.availHeight-450)/2+",left="+(window.screen.availWidth-700)/2+",scrollbars=yes, status=yes");       
	}
	//贷款账号弹出框
function gotoLoankouaccpop(status,path,indexs){

  window.open(path+"/sysloan/loanKouAccpopFindAC.do?status="+status+"&indexs="+indexs+"&qx=no","window","height=450,width=700,top="+(window.screen.availHeight-450)/2+",left="+(window.screen.availWidth-700)/2+",scrollbars=yes, status=yes"); 
  
}
function gotoLoanKouAccgPop(){
	gotoLoankouaccpop('',"<%=path%>",4);
}
function executeAjax(){
	var queryString = "checkQueryPlFnFindAAC.do?";
    var empId = document.all.empId.value.trim();
    
    var empName = encodeURI(document.all.empName.value.trim());
    var startTime = document.all.startTime.value.trim();
    var endTime = document.all.endTime.value.trim();
    if(startTime==""){
		alert('请输入开始发生日期');
		return false;
	}else{
		if(!checkDate(document.all.startTime)){
			document.all.startTime.value="";
			return false;
		}
	}
	if(endTime==""){
		alert('请输入结束发生日期');
		return false;
	}else{
		if(!checkDate(document.all.endTime)){
			document.all.endTime.value="";
			return false;
		}
	}
    
    
   
    var contractId = document.all.contractId.value.trim();
    queryString = queryString + "empId="+empId+"&empName="+empName+"&startTime="+startTime+"&endTime="+endTime+"&contractId="+contractId; 	     
    findInfo(queryString);
}
function displays(count,contracId){
	if(count==1){
		 var startTime = document.all.startTime.value.trim();
   		 var endTime = document.all.endTime.value.trim();
		document.URL='<%=path%>'+"/sysloan/checkQueryPlFnTBFindAC.do?contractid="+contracId+"&startTime="+startTime+"&endTime="+endTime;
	}
	if(count==0){
		 var startTime = document.all.startTime.value.trim();
   		 var endTime = document.all.endTime.value.trim();
		document.URL='<%=path%>'+"/sysloan/checkQueryPlFnTBFindAC.do?contractid="+"fhugishfuighjkgh"+"&startTime="+startTime+"&endTime="+endTime;
	}
	if(count>1){
		 var empId = document.all.empId.value.trim();
   		 var empName = encodeURI(document.all.empName.value.trim());
    	 var startTime = document.all.startTime.value.trim();
   		 var endTime = document.all.endTime.value.trim();
   		 var contractId = document.all.contractId.value.trim();
		window.open('<%=path%>'+"/sysloan/checkQueryPlFnWindowFindAC.do?empId="+empId+"&empName="+empName+"&startTime="+startTime+"&endTime="+endTime+"&contractid="+contractId,"window","height=450,width=700,top=300,left=300,scrollbars=yes, status=yes"); 
	}
	
}
function executeAjaxIn(){
	var empId = document.all.empId.value.trim();
   		 var empName =  encodeURI(document.all.empName.value.trim());
   		 
    	 var startTime = document.all.startTime.value.trim();
   		 var endTime = document.all.endTime.value.trim();
   		var contractId = document.all.contractId.value.trim();
    document.URL='<%=path%>'+"/sysloan/checkQueryPlFnTBFindAC.do?empId="+empId+"&empName="+empName+"&startTime="+startTime+"&endTime="+endTime+"&contractId="+contractId;
    }
</script>
<body bgcolor="#FFFFFF" text="#000000">
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
							&nbsp;
						</td>
						<td background="<%=path%>/img/table_bg_line.gif" align="right">
							<table width="300" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="37">
										<img src="<%=path%>/img/title_banner.gif" width="37"
											height="24">
									</td>
									<td width="148" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">贷款账</font>
									</td>
									<td width="115" class=font14>
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
						<td width="9">
							<img src="images/table_right.gif" width="9" height="37">
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td class=td3>
				<html:form action="/checkQueryPlFnTBFindAC.do" style="margin: 0">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="117">
											<b class="font14">查 询 条 件</b>
										</td>
										<td height="22" background="<%=path%>/img/bg2.gif"
											align="center">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=3 align="center">
						
						<tr>
							<td width="17%" class="td1">
								职工编号
							</td>
							<td width="33%" colspan="3">
								<html:text name="checkQueryPlFnTBAF"
									property="empId" styleClass="input3"
									onkeydown="enterNextFocus1();" />
							</td>
							<td width="17%" class="td1">
								借款人姓名
							</td>
							<td width="33%" colspan="3">
								<html:text name="checkQueryPlFnTBAF"
									property="empName" styleClass="input3"
									onkeydown="enterNextFocus1();" />
							</td>
						</tr>
						<tr>
							<td width="17%" class="td1">
								合同编号
							</td>
							<td width="33%" colspan="3">
								
								<html:text name="checkQueryPlFnTBAF"
									property="contractId" styleClass="input3"
									onkeydown="enterNextFocus1();" />
							
							</td>
							<td width="17%" class="td1">
								发生日期<font color="#FF0000">*</font>
							</td>
							<td width="15%">
								<html:text name="checkQueryPlFnTBAF"
									property="startTime"
									styleClass="input3" onkeydown="enterNextFocus1();" />
							</td>
							<td width="3%">
								至
							</td>
							<td width="15%">
								<html:text name="checkQueryPlFnTBAF"
									property="endTime"
									styleClass="input3" onkeydown="enterNextFocus1();" />
							</td>
						</tr>
					</table>
					<table width="95%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td align="right">
								&nbsp;
							</td>
						</tr>
					</table>
					<table width="95%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td align="right">
								<html:button property="method" styleClass="buttona" onclick="executeAjax();">
									<bean:message key="button.search" />
								</html:button>
							</td>
						</tr>
					</table>
				</html:form>
				<html:form action="/checkQueryPlFnTBSureAC.do" style="margin: 0">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="142">
											<b class="font14">贷款账列表 </b>
										</td>
										<td height="22" background="<%=path%>/img/bg2.gif"
											align="center" width="762">
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
            <
							<td align="center" class=td2>
								日期
							</td>
							
							<td align="center" class=td2>
								凭证号
							</td>
							<td align="center" class=td2>
								摘要
							</td>
							<td align="center" class=td2>
								借方
							</td>
							<td align="center" class=td2>
								贷方
							</td>
							<td align="center" class=td2>
								利息
							</td>
							<td align="center" class=td2>
								罚息
							</td>
							
							<td align="center" class=td2>
								方向
							</td>
							<td align="center" class=td2>
								余额
							</td>
						</tr>
							<logic:notEmpty name="particularglTaAF" property="list">
				          <% int j=0;
							String strClass="";%>
								<logic:iterate name="particularglTaAF" property="list"
									id="elments" indexId="i">
										<logic:equal name="elments" property="type" value="">
						          <%j++;
									if (j%2==0) {strClass = "td8";
									}
								    else {strClass = "td9";
								    }%>
							
									<tr id="tr<%=i%>"  onMouseOver='this.style.background="#eaeaea"'  onMouseOut='gotoColorpp("<%=i %>", idAF);'  onclick='gotoClickpp("<%=i %>", idAF);' class="<%=strClass%>"> 
										<td style="display:none">
											<input id="s<%=i%>" type="radio" name="id"
												value="<bean:write name="elments" property="ocyear"/>"
												<%if(new Integer(0).equals(i)) out.print("checked"); %> />
										</td>
										<td valign="top">
											<bean:write name="elments" property="ocyear" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="docNum" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="bizType" />
										</td>
										
										<td valign="top">
											<bean:write name="elments" property="thisborrower" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="thispaymoney" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="thisinterest" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="thispunishinterest" />
										</td>
										
										<td valign="top">
											<bean:write name="elments" property="derection" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="lastcorpus" />
										</td>
										
									</tr>
									<tr>
										<td colspan="20" class=td5></td>
									</tr>
							
</logic:equal>
								<logic:equal name="elments" property="type" value="0">
							    <%j++;
									if (j%2==0) {strClass = "td8";
									}
								    else {strClass = "td9";
								    }%>
							
									<tr id="tr<%=i%>"  class=td4  onDblClick="" style="background-color:#FF6659;">	
										<td style="display:none">
											<input id="s<%=i%>" type="radio" name="id"
												value="<bean:write name="elments" property="ocyear"/>"
												<%if(new Integer(0).equals(i)) out.print("checked"); %> />
										</td>
										<td valign="top">
											<bean:write name="elments" property="ocyear" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="docNum" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="bizType" />
										</td>
										
										<td valign="top">
											<bean:write name="elments" property="thisborrower" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="thispaymoney" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="thisinterest" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="thispunishinterest" />
										</td>
										
										<td valign="top">
											<bean:write name="elments" property="derection" />
										</td>
										<td valign="top">
											<bean:write name="elments" property="lastcorpus" />
										</td>
										
									</tr>
									<tr>
										<td colspan="20" class=td5></td>
									</tr>
							
</logic:equal>
								</logic:iterate>

							</logic:notEmpty>
							<logic:empty name="particularglTaAF" property="list">
								<tr>
									<td colspan="7" height="30" style="color:red">
										没有找到与条件相符合的结果！
									</td>
								</tr>
								<tr>
									<td colspan="7"></td>
								</tr>
							</logic:empty>
					</table>

					
					<table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr class="td1"> 
		  <td>
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr> 
			  	<td align="left">共<bean:write name="pagination" property="nrOfElements"/> 项</td>
                <td align="right">
														<jsp:include page="../../../inc/pagination.jsp">
															<jsp:param name="url" value="checkQueryPlFnTBShowAC.do" />
														</jsp:include></td>
              </tr>
            </table>
          </td>
	    </tr>
      </table>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr valign="bottom">
							<td colspan="10" bgcolor="#FFFFFF" align="center" height="30">
								<table border="0" cellspacing="0" cellpadding="0">
									<tr>
										<logic:notEmpty name="checkQueryPlFnTBAF" property="list">
										<td width="70">
											  <input type="button" name="Submit" value="打印" class="buttona" onclick="toprint();">
                
										</td>
										</logic:notEmpty>
										<logic:empty name="checkQueryPlFnTBAF" property="list">
										<td width="70">
											  <input type="button" name="Submit" value="打印" class="buttona" onclick="toprint();">
                
										</td>
										</logic:empty>
										<td width="70">
											<input type="button" name="Submit4" value="返回"
												class="buttona" onclick=location.href='checkQueryPlFnShowAC.do'>
										</td>
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
