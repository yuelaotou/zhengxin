<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%
	String path = request.getContextPath();
	
%>
<html lang="true">
	<head>
		<title>提取排序</title>
		<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
		<script language="javascript" src="<%=path%>/js/common.js" />
<script language="javascript"></script>
<script language="javascript" type="text/javascript">	

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

function goClose() {
  window.opener.document.URL="<%=path%>/syscollection/pickupmng/pickup/pickupExportTaAC.do?tatol="+document.chgPayExport.elements["tatol"].value+"&empIds="+document.chgPayExport.elements["empIds"].value+"&empNames="+document.chgPayExport.elements["empNames"].value
  +"&cardNums="+document.chgPayExport.elements["cardNums"].value+"&cardKinds="+document.chgPayExport.elements["cardKinds"].value+"";
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
		<form action="<%=path%>/syscollection/pickupmng/pickup/pickupExportTaAC.do"
			name="chgPayExport" method="POST" style="margin: 0">
			
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
						<input type="checkbox" name="cardNum"  value="0" onclick = "return changeArray('cardNum');">证件号码
						<input type="test" name="cardNums"  value="0" style="width:20px" readonly >
					<td class="td1" align="center" valign="left">
						<input type="checkbox" name="cardKind"  value="0" onclick = "return changeArray('cardKind');">证件类型
						<input type="test" name="cardKinds"  value="0" style="width:20px" readonly >
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
