<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/checkUrl.jsp"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ page import="java.util.List" %>
<%@ page import="org.xpup.hafmis.syscollection.querystatistics.chgbiz.chgbizinfo.dto.ChgbizinfoDTO" %>
<%@ page import="org.xpup.hafmis.common.util.BusiTools" %>
<%@ page import="org.xpup.hafmis.common.util.BusiConst" %>
<%@ page
	import="org.xpup.hafmis.sysfinance.accmng.totleacc.action.TotleaccShowAC"%>
<%
			Object pagination = request.getSession(false).getAttribute(
			TotleaccShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String bizDate=(String) request.getAttribute("bizDate");
	String path = request.getContextPath();
%>
<html:html>
<head>
	<title>账簿管理-总账</title>
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script language="javascript" src="<%=path%>/js/common.js"></script>
</head>
<script type="text/javascript">
function checkSearchData(){
      var orgId=document.all.orgId.value.trim();
      var month=document.all.chgMonth.value.trim();
      if(orgId.trim()==""){
      	alert("请录入单位编号!!");  
      	return false;
      }
      if(month.trim()==""){
      	alert("请录入变更年月!!");  
      	return false;
      }
                       	              
 
}


function executeAjaxIn(flag){
	var starsubject=document.all.starsubject.value.trim();
	var endsubject=document.all.endsubject.value.trim();
	if(flag=='1'){
		if(starsubject.length!=0){
		    var queryString = "totleaccCheckSubjectcodeAAC.do?";
		    queryString = queryString + "subject="+starsubject+"&flag="+flag+"";
		    findInfo(queryString);
		}
	}else{
		if(endsubject.length!=0){
		    var queryString = "totleaccCheckSubjectcodeAAC.do?";
		    queryString = queryString + "subject="+endsubject+"&flag="+flag+"";
		    findInfo(queryString);
		}
	}
}
function tofocus(flag) //按回车置下一个位置
{
	if(flag=='1'){
		document.all.endsubject.focus();
		return false;
	}else{
		document.all.office.focus();
		return false;
	}
} 
function display(message,flag){
	if(message.length!=0){
		alert(message);
		if(flag=='1'){
			document.all.starsubject.value="";
			document.all.endsubject.value="";
			document.all.starsubject.focus();			
			return false;
		}else{
			document.all.endsubject.value="";
			document.all.endsubject.focus();			
			return false;
		}
	}else{
		if(flag=='1'){
			document.all.endsubject.value=document.all.starsubject.value;
		}
	}

}
	</script>
<script type="text/javascript">
	function load(){	
	loginReg();
	document.forms(0).Cell1.openfile("<%=path%>/syscollection/querystatistics/chgbiz/chgbizinfo/table.cll","");
	document.forms(0).Cell1.AllowSizeColInGrid =[true];
	var empId=[];//科目代码
	var empName=[];//科目名称
	var cardNum=[];//日期
	var type=[];// 凭证字号
	var orgRate=[];//摘要
	var empRate=[];//贷方金额
	var salaryBase=[];//借方金额
	var monthPay=[];//方向
	var oldMonthPay=[];//余额
	var orgPay=[];//单位缴额
	var empPay=[];//职工缴额
	var i=0;
		<%
    		List list1=(List) request.getAttribute("list1");
  			ChgbizinfoDTO dto=null;
  			if(list1!=null){
  			for(int j=0;j<list1.size();j++){
  				dto=(ChgbizinfoDTO)list1.get(j);
  				%>
					empId[i]="<%=dto.getEmpid() %>";
					empName[i]="<%=dto.getEmpnamem() %>";
					cardNum[i]="<%=dto.getCardnumm() %>";
					type[i]="<%=dto.getType() %>";
					orgRate[i]="<%=dto.getOrgRatem() %>";
					empRate[i]=<%=dto.getEmpRatem() %>;
					salaryBase[i]=<%=dto.getSalaryBasem() %>;
					monthPay[i]="<%=dto.getPaySumm() %>";
					oldMonthPay[i]=<%=dto.getOldPaySum() %>;
					orgPay[i]=<%=dto.getOrgPay() %>;
					empPay[i]=<%=dto.getEmpPay() %>;
					i++;
  				<%
 			}
 			}
 		%> 	
 		<%
    		List list2=(List) request.getAttribute("list2");
  			ChgbizinfoDTO dto1=null;
  			if(list2!=null){
  			for(int j=0;j<list2.size();j++){
  				dto1=(ChgbizinfoDTO)list2.get(j);
  				%>
					empId[i]="<%=dto1.getEmpid() %>";
					empName[i]="<%=dto1.getEmpnamem() %>";
					cardNum[i]="<%=dto1.getCardnumm() %>";
					type[i]="<%=dto1.getType() %>";
					orgRate[i]="<%=dto1.getOrgRatem() %>";
					empRate[i]=<%=dto1.getEmpRatem() %>;
					salaryBase[i]=<%=dto1.getSalaryBasem() %>;
					monthPay[i]="<%=dto1.getPaySumm() %>";
					oldMonthPay[i]=<%=dto1.getOldPaySum() %>;
					orgPay[i]="<%=dto1.getOrgPay()%>";
					empPay[i]="<%=dto1.getEmpPay()%>";
					i++;
  				<%
 			}}
 		%> 	
 		<%
    		List list3=(List) request.getAttribute("list3");
  			ChgbizinfoDTO dto2=null;
  			if(list3!=null){
  			for(int j=0;j<list3.size();j++){
  				dto2=(ChgbizinfoDTO)list3.get(j);
  				%>
					empId[i]="<%=dto2.getEmpid() %>";
					empName[i]="<%=dto2.getEmpnamem() %>";
					cardNum[i]="<%=dto2.getCardnumm() %>";
					type[i]="<%=dto2.getType() %>";
					orgRate[i]="<%=dto2.getOrgRatem() %>";
					empRate[i]=<%=dto2.getEmpRatem() %>;
					salaryBase[i]=<%=dto2.getSalaryBasem() %>;
					monthPay[i]="<%=dto2.getPaySumm() %>";
					oldMonthPay[i]="<%=dto2.getOldPaySum() %>";
					orgPay[i]=<%=dto2.getOrgPay() %>;
					empPay[i]=<%=dto2.getEmpPay() %>;
					i++;
  				<%
 			}}
 		%> 	
 		var totalLine=empId.length;			//总的行数
			var totalPageLine=15;					//每页显示多少行
			var pageTotal=totalLine/totalPageLine;	//总共分几页
			var pageCurrent=0;						//当前页
			var completeline=0;						//记录行的
		var iPage = getInt(totalLine,totalPageLine);
			//总的合计
		for(k = 0 ; k < totalLine; k++){
		if(k%totalPageLine==0&&k>0)
			{
				
				document.forms(0).Cell1.ReDraw();
				pageCurrent++;	
				completeline=k-2;				
				document.forms(0).Cell1.insertSheetFromFile("<%=path%>/syscollection/querystatistics/chgbiz/chgbizinfo/table_1.cll",0,1,pageCurrent);
				document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页")	;

			}
		if(pageCurrent==0)
			{
				document.forms(0).Cell1.S(1,3,0,"单位名称（公章）"+"<%=request.getAttribute("data_10")%>");
				document.forms(0).Cell1.S(1,4,0,"单位账号"+"<%=request.getAttribute("data_9")%>");
				
				document.forms(0).Cell1.S(4,23,0,"<%=request.getAttribute("data_1")%>");
				document.forms(0).Cell1.S(4,24,0,"<%=request.getAttribute("data_3")%>");
				document.forms(0).Cell1.S(7,23,0,"<%=request.getAttribute("data_2")%>");
				document.forms(0).Cell1.S(7,24,0,"<%=request.getAttribute("data_4")%>");
				
				document.forms(0).Cell1.S(12,23,0,"<%=request.getAttribute("data_5")%>");
				document.forms(0).Cell1.S(12,24,0,"<%=request.getAttribute("data_6")%>");
				
				document.forms(0).Cell1.S(3,26,0,"<%=request.getAttribute("data_11")%>");
				document.forms(0).Cell1.S(6,26,0,"<%=request.getAttribute("data_12")%>");
				document.forms(0).Cell1.S(4,22,0,"<%=request.getAttribute("data_13")%>");
				document.forms(0).Cell1.S(7,22,0,"<%=request.getAttribute("data_14")%>");
			
				document.forms(0).Cell1.S(15,3,0,"第"+(pageCurrent+1)+"/"+iPage+"页");
				document.forms(0).Cell1.S(15,4,0,"<%=bizDate%>");
				
				document.forms(0).Cell1.S(2,k+7,0,empId[k]);
				document.forms(0).Cell1.S(3,k+7,0,empName[k]);
				document.forms(0).Cell1.S(5,k+7,0,cardNum[k]);
				document.forms(0).Cell1.s(9,k+7,0,type[k]);
				document.forms(0).Cell1.d(10,k+7,0,orgRate[k]);
				document.forms(0).Cell1.d(11,k+7,0,empRate[k]);
				document.forms(0).Cell1.d(12,k+7,0,salaryBase[k]);
				document.forms(0).Cell1.d(13,k+7,0,orgPay[k]);
				document.forms(0).Cell1.d(14,k+7,0,empPay[k]);
				document.forms(0).Cell1.d(15,k+7,0,monthPay[k]);
				document.forms(0).Cell1.d(16,k+7,0,oldMonthPay[k]);
			}else{//向第一页后的所有页插数据
				document.forms(0).Cell1.S(2,k-completeline+5,pageCurrent,empId[k]);
				document.forms(0).Cell1.S(3,k-completeline+5,pageCurrent,empName[k]);
				document.forms(0).Cell1.S(5,k-completeline+5,pageCurrent,cardNum[k]);
				document.forms(0).Cell1.S(9,k-completeline+5,pageCurrent,type[k]);
				document.forms(0).Cell1.d(10,k-completeline+5,pageCurrent,orgRate[k]);
				document.forms(0).Cell1.d(11,k-completeline+5,pageCurrent,empRate[k]);
				document.forms(0).Cell1.d(12,k-completeline+5,pageCurrent,salaryBase[k]);
				document.forms(0).Cell1.d(13,k-completeline+5,pageCurrent,orgPay[k]);
				document.forms(0).Cell1.d(14,k-completeline+5,pageCurrent,empPay[k]);
				document.forms(0).Cell1.d(15,k-completeline+5,pageCurrent,monthPay[k]);
				document.forms(0).Cell1.d(16,k-completeline+5,pageCurrent,oldMonthPay[k]);
				document.forms(0).Cell1.S(1,3,pageCurrent,"单位名称（公章）"+"<%=request.getAttribute("data_10")%>");
				document.forms(0).Cell1.S(1,4,pageCurrent,"单位账号"+"<%=request.getAttribute("data_9")%>");
				document.forms(0).Cell1.S(15,3,pageCurrent,"第"+(pageCurrent+1)+"/"+iPage+"页");
				document.forms(0).Cell1.S(15,4,0,"<%=bizDate%>");
				document.forms(0).Cell1.S(4,23,pageCurrent,"<%=request.getAttribute("data_1")%>");
				document.forms(0).Cell1.S(4,24,pageCurrent,"<%=request.getAttribute("data_3")%>");
				document.forms(0).Cell1.S(7,23,pageCurrent,"<%=request.getAttribute("data_2")%>");
				document.forms(0).Cell1.S(7,24,pageCurrent,"<%=request.getAttribute("data_4")%>");
				
				document.forms(0).Cell1.S(12,23,pageCurrent,"<%=request.getAttribute("data_5")%>");
				document.forms(0).Cell1.S(12,24,pageCurrent,"<%=request.getAttribute("data_6")%>");
				
				document.forms(0).Cell1.S(3,26,pageCurrent,"<%=request.getAttribute("data_11")%>");
				document.forms(0).Cell1.S(6,26,pageCurrent,"<%=request.getAttribute("data_12")%>");
				document.forms(0).Cell1.S(4,22,pageCurrent,"<%=request.getAttribute("data_13")%>");
				document.forms(0).Cell1.S(7,22,pageCurrent,"<%=request.getAttribute("data_14")%>");
			}
		}
		
			
				
		
				document.forms(0).Cell1.AllowExtend=false;
				document.forms(0).Cell1.AllowDragdrop=false;
				document.forms(0).Cell1.WorkbookReadonly=true;	
	} 
	function printPreview(){
		var k=document.forms(0).Cell1.GetCurSheet();//显示打印预览那个页面
		document.forms(0).Cell1.printPreviewEx(1,k,false);
	}
	function printsheet(){//真正的打印
		var k=document.forms(0).Cell1.GetCurSheet();//显示打印预览那个页面--固定
		document.forms(0).Cell1.PrintSheet(1,k);//固定...
	}
	function Button1_onclick()
	{
		document.forms(0).Cell1.SaveFile();
	}
	function Button2_onclick()
	{
		document.forms(0).Cell1.PrintPageSetup();
	}
	function Button3_onclick()
	{
		document.forms(0).Cell1.FindDialogEx( 0,"");
	}
		function Button4_onclick()
	{
		document.forms(0).Cell1.ImportExcelDlg();
	}
	function Cell1_MouseLClick(col, row, updn) 
	{
		if(col==1 ||col==2){
			//document.forms(0).Cell1.AllowSizeCol(col,row,0);
		}
	}
	
</script>
<script LANGUAGE=javascript FOR=Cell1 EVENT=MouseLClick(col,row,updn)>
Cell1_MouseLClick(col, row, updn);
</script>
<script language="JScript.Encode">
eval(unescape('function%20LoginRegister%28%29//%u6CE8%u518CCELL%0D%0A%20%7B%20%0D%0A%20document.forms%280%29.Cell1.Login%28%22%u6C88%u9633%u91D1%u8F6F%u79D1%u6280%u6709%u9650%u516C%u53F8%22%2C%22%22%2C%2213100104509%22%2C%220740-1662-7775-3003%22%29%3B%20%20%20%20%0D%0A%20%7D'))
</script>
<body bgcolor="#FFFFFF" text="#000000" onload = "load();">
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
										<img src="<%=path%>/img/title_banner.gif" width="37" height="24">
									</td>
									<td width="189" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<p>
											<font color="00B5DB">账簿管理&gt;总账</font>
										</p>
									</td>
									<td width="74" class=font14>
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
									<td height="22" bgcolor="#CCCCCC" align="center" width="126">
										<b class="font14">查 询 条 件</b>
									</td>
									<td height="22" background="<%=path%>/img/bg2.gif" align="center"
										width="796">
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				
				
				<html:form action="/findChgbizinfoListAC">
				<table border="0" width="95%" id="table1" cellspacing=1
					cellpadding=0 align="center">
					<tr>
						<td width="13%" class="td1">
							单位编号
							<font color="#FF0000">*</font>
							<br>
						</td>
						<td width="37%">
							<html:text name="chgbizinfoAF" property="orgId"
								styleClass="input3" onkeydown="enterNextFocus1();"></html:text>
						</td>
						

						
						
						<td width="11%" class="td1" >变更类型</td>
            <td width="21%" colspan="2"  ><html:select property="chgType" styleClass="input4"
									name="chgbizinfoAF" onkeydown="enterNextFocus1();">
									<option value=""></option>
									<option value="0">汇缴比例调整</option>
									<option value="1">工资基数调整</option>
									<option value="2">人员变更</option>						
								</html:select></td>
						
						
					</tr>
					<tr>
						<td width="13%" class="td1">
							变更年月
							<font color="#FF0000">*</font>
							<br>
						</td>
						<td width="37%">
							<html:text name="chgbizinfoAF" property="chgMonth"
								styleClass="input3" onkeydown="enterNextFocus1();"></html:text>
						</td>
						
						
						
						<td width="13%" class="td1">
						
							
						</td>
						<td width="37%">
							
						</td>
						 
						
						
					</tr>
				</table>
				
				
				
				
				
				<table width="95%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td align="right">
							<html:submit property="method" styleClass="buttona"
								onclick="return checkSearchData()">
								<bean:message key="button.search" />
							</html:submit>
						</td>
					</tr>
				</table>
				 <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td height="35">
          	<table border="0" cellspacing="0" cellpadding="0" align="center">
               <tr>  
              	<tr><OBJECT id=Cell1 style= "LEFT:0px;WIDTH:730px; TOP:0px;HEIGHT:400px" codeBase="http://192.168.1.44:8088/hafmis/CellWeb5.CAB#version=5,3,7,321" classid=clsid:3F166327-8030-4881-8BD2-EA25350E574A VIEWASTEXT><PARAM NAME="_Version" VALUE="65536"><PARAM NAME="_ExtentX" VALUE="10266"><PARAM NAME="_ExtentY" VALUE="7011"><PARAM NAME="_StockProps" VALUE= "0"></OBJECT></tr> 
              	<tr>
					<INPUT type="button" value = "打印预览" onclick = "printPreview();" class="buttonb">
					<INPUT type="button" value=" 打印 " onclick="printsheet()" class="buttona">
					<INPUT type="button" value="另存为Excel" onclick="Button1_onclick()" class="buttonb">
					<INPUT type="button" value="页面设置" onclick="Button2_onclick()" class="buttonb">
					<INPUT type="button" value="查找对话框" onclick="Button3_onclick()" class="buttonb">
					<INPUT type="button" value="excel导入" onclick="Button4_onclick()" class="buttonb">
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

