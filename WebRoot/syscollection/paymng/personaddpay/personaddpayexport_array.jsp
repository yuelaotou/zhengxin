<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%> 
<%
	String path = request.getContextPath();
	String orgId = request.getParameter("orgId");
	String noteNum = request.getParameter("noteNum");
	String settMode = request.getParameter("settMode");
	String payWay = request.getParameter("payWay");
	String payKind= request.getParameter("payKind");
%>
<html lang="true">
	<head>
		<title>个人补缴导出排序</title>
		<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
		<script language="javascript" src="<%=path%>/js/common.js" />
<script language="javascript"></script>
<script language="javascript" type="text/javascript">	
function returnValue(){
   if(document.chgPayExport.elements["tatol"].value==1)
   {
     document.chgPayExport.elements["empIds"].value=1;
     document.chgPayExport.elements["tatol"].value=2;
     goClose();
   }
   //  document.chgPayExport.submit();
 //   alert("提交");
   else
   {
     goClose();
   }
}
function changeArray(value) {

    var tatolValue=document.chgPayExport.elements["tatol"].value;  //1
    var valueName=value+"s";
	var clickValue= document.chgPayExport.elements[valueName].value;
	 if(clickValue==0){
	 document.chgPayExport.elements[valueName].value=tatolValue;
	 tatolValue++;
	 }else {
		 if((tatolValue-1)!=clickValue){
		 alert("先从最小的取消");
		 return false;
		 }
	 document.chgPayExport.elements[valueName].value=0;
	 tatolValue--;
	 }
	 document.chgPayExport.elements["tatol"].value=tatolValue;
}
function goClose() {
     window.opener.document.URL="<%=path%>/syscollection/paymng/personaddpay/personAddPayTaExportAC.do?tatol="+document.chgPayExport.elements["tatol"].value+"&empIds="+document.chgPayExport.elements["empIds"].value+"&empNames="+document.chgPayExport.elements["empNames"].value
  +"&noteNum="+document.chgPayExport.elements["noteNum"].value+"&orgId="+document.chgPayExport.elements["orgId"].value+"&statuss="+document.chgPayExport.elements["statuss"].value
  +"&payWay="+document.chgPayExport.elements["payWay"].value+"&payKind="+document.chgPayExport.elements["payKind"].value;
	window.close();
	
}

function goCancel() {
	window.close();
}
</script>
	</head>
	<script language="javascript">
function reportErrors() {
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	window.opener.alert(message);
	</logic:messagesPresent>
}
</script>
	<body bgcolor="#FFFFFF" text="#000000" onload="reportErrors();"
		onContextmenu="return false">
		<form action="<%=path%>/syscollection/paymng/personaddpay/personAddPayTaExportAC.do"
			name="chgPayExport" method="POST" style="margin: 0">
			<input type="hidden" name="noteNum" value="<%=noteNum%>" />
			<input type="hidden" name="orgId" value="<%=orgId%>" />
			
			<input type="hidden" name="payWay" value="<%=payWay%>" />
			<input type="hidden" name="payKind" value="<%=payKind%>" />
			<table width="95%" border="0" cellspacing="0" cellpadding="3"
				align="center">
				<tr bgcolor="1BA5FF">
					<td align="center" height="6" colspan="1"></td>
				</tr>
			</table>

			<table width="95%" border="0" cellspacing="0" cellpadding="3"
				align="center">
				
				<tr>
					<td class="td1" align="center" valign="middle">
						请录入导出顺序：
					</td>
					<td class="td1" align="center" valign="left">
						<input type="checkbox" name="empId"  value="0" onclick = "return changeArray('empId');">职工编号
						<input type="test" name="empIds"  value="0" style="width:20px" readonly >
					</td>
					<td class="td1" align="center" valign="left">
						<input type="checkbox" name="empName"  value="0" onclick = "return changeArray('empName');">职工姓名
						<input type="test" name="empNames"  value="0" style="width:20px" readonly >
				</tr>
				<tr>
				<td class="td1" align="center" valign="right">    
					</td>
					<td class="td1" align="center" valign="left">
						<input type="checkbox" name="status"  value="0" onclick = "return changeArray('status');">职工状态
						<input type="test" name="statuss"  value="0" style="width:20px" readonly >
				<td class="td1" align="center" valign="right">    
					</td>
					
				</tr>
				 <input type="hidden" name="tatol"  value="1" >
				<tr>
					<td colspan="2">
						<table width="95%" border="0" cellspacing="0" cellpadding="3"
							align="center">
							<tr valign="bottom">
								<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
									<table border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td>
												<input type="button" name="confirm" onclick="return returnValue();" styleClass="buttona" value="确定" />
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
