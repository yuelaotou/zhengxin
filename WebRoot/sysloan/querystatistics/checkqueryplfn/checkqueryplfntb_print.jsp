<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.List" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic" %>
<%@ page import="org.xpup.hafmis.sysloan.querystatistics.checkqueryplfn.dto.CheckQueryPlFnTBDTO" %>
<%@ include file="/checkUrl.jsp"%>

<%
   String path=request.getContextPath();
   List list=(List) request.getSession().getAttribute("printList");
   String url = (String)request.getAttribute("url");
 %>
<html>
<head>
<script src="<%=path%>/js/common.js"></script>
</head>
<script type="text/javascript">
	function load(){
	loginReg();
	document.forms(0).Cell1.openfile("<%=path%>/sysloan/querystatistics/report/checkqueryplfntb.cll","");
	  var contractid = [];// 合同编号

	  var loankouacc = [];// 贷款账号
	
	  var credencenum = [];// 个贷凭证号
	  
	  var fncredencenum = [];// 财务凭证号
	
	  var notenum = [];// 结算号
	
	  var empid = [];// 职工编号
	
	  var empname = [];// 借款人名称
	
	  var temp_biztype = [];
	
	  var bizdate = [];// 个贷结算日期
	  
	  var temp_plbizst = [];
	
	  var settledate = [];// 财务结算日期
	  
	  var temp_credenceSt = [];
	
	  var credit = [];// 贷方金额
	
	  var debit = [];// 借方金额
	
	  var balance = [];// 余额
  	  
  	  var i=0;
	
	<%
		CheckQueryPlFnTBDTO checkQueryPlFnTBDTO = new CheckQueryPlFnTBDTO();
		for(int j=0;j<list.size();j++){
		checkQueryPlFnTBDTO = (CheckQueryPlFnTBDTO)list.get(j);
	%>
	    //把数据传到JS的数组里面..
		contractid[i] = "<%=checkQueryPlFnTBDTO.getContractid()%>"; 
		loankouacc[i] = "<%=checkQueryPlFnTBDTO.getLoankouacc()%>";
		credencenum[i] = "<%=checkQueryPlFnTBDTO.getCredencenum()%>";
		fncredencenum[i] = "<%=checkQueryPlFnTBDTO.getFncredencenum()%>"; 
		notenum[i] = "<%=checkQueryPlFnTBDTO.getNotenum()%>";
		empid[i] = "<%=checkQueryPlFnTBDTO.getEmpid()%>";
		empname[i] = "<%=checkQueryPlFnTBDTO.getEmpname()%>";
		temp_biztype[i] = "<%=checkQueryPlFnTBDTO.getTemp_biztype()%>";
		bizdate[i] = "<%=checkQueryPlFnTBDTO.getBizdate()%>";
		temp_plbizst[i] = "<%=checkQueryPlFnTBDTO.getTemp_plbizst()%>";
		settledate[i] = "<%=checkQueryPlFnTBDTO.getSettledate()%>";
		temp_credenceSt[i] = "<%=checkQueryPlFnTBDTO.getTemp_credenceSt()%>";
		credit[i] = "<%=checkQueryPlFnTBDTO.getCredit()%>";
		debit[i] = "<%=checkQueryPlFnTBDTO.getDebit()%>";
		balance[i] = "<%=checkQueryPlFnTBDTO.getBalance()%>";
		i++;
	<%}%>
	
	var totalLine=debit.length;				//总的行数 数组的长度
	var totalPageLine=40;					//每页显示多少行--除了第一行
	var pageTotal=totalLine/totalPageLine;	//总共分几页 总行数/每页的行数
	var pageCurrent=0;						//当前页
	var completeline=0;						//记录行的
	var debittotal=0;						//借方金额合计
	var credittotal=0;						//贷方金额合计
	var balancetotal=0;						//余额合计
	
	for(k = 0 ; k < totalLine; k++){
		if(k%totalPageLine==0&&k>0)
		{
			document.forms(0).Cell1.S(1,totalPageLine+4,pageCurrent,"本页小计");
			document.forms(0).Cell1.SetFormula (13, totalPageLine+4, pageCurrent, "Sum(M4:M"+(totalPageLine+3)+")" );
			document.forms(0).Cell1.SetFormula (14, totalPageLine+4, pageCurrent, "Sum(N4:N"+(totalPageLine+3)+")" );
			document.forms(0).Cell1.SetFormula (15, totalPageLine+4, pageCurrent, "Sum(O4:O"+(totalPageLine+3)+")" );
			document.forms(0).Cell1.ReDraw();//重画一个表格
			pageCurrent++;//当前页++	
			completeline=k-2;
			//绘制标签 param 	表页索引号。param 要设置的表页页签名称
			document.forms(0).Cell1.insertSheetFromFile("<%=path%>/sysloan/querystatistics/report/checkqueryplfntb.cll",0,1,pageCurrent);
  			document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页")	
		}
		if(pageCurrent==0)
		{	
			document.forms(0).Cell1.S(1,k+4,0,contractid[k]);
			document.forms(0).Cell1.S(2,k+4,0,loankouacc[k]);
			document.forms(0).Cell1.S(3,k+4,0,credencenum[k]);
			document.forms(0).Cell1.S(4,k+4,0,fncredencenum[k]);
			document.forms(0).Cell1.S(5,k+4,0,notenum[k]);
			document.forms(0).Cell1.S(6,k+4,0,empid[k]);
			document.forms(0).Cell1.S(7,k+4,0,empname[k]);
			document.forms(0).Cell1.S(8,k+4,0,temp_biztype[k]);
			document.forms(0).Cell1.S(9,k+4,0,bizdate[k]);
			document.forms(0).Cell1.S(10,k+4,0,temp_plbizst[k]);
			document.forms(0).Cell1.S(11,k+4,0,settledate[k]);
			document.forms(0).Cell1.S(12,k+4,0,temp_credenceSt[k]);
			document.forms(0).Cell1.d(13,k+4,0,credit[k]);
			document.forms(0).Cell1.d(14,k+4,0,debit[k]);
			document.forms(0).Cell1.d(15,k+4,0,balance[k]);
			credittotal=credittotal+parseFloat(credit[k]);
			debittotal=debittotal+parseFloat(debit[k]);
			balancetotal=balancetotal+parseFloat(balance[k]);
			}
		else{//向第一页后的所有页插数据
			document.forms(0).Cell1.S(1,k-completeline+2,pageCurrent,contractid[k]);
			document.forms(0).Cell1.S(2,k-completeline+2,pageCurrent,loankouacc[k]);
			document.forms(0).Cell1.S(3,k-completeline+2,pageCurrent,credencenum[k]);
			document.forms(0).Cell1.S(4,k-completeline+2,pageCurrent,fncredencenum[k]);
			document.forms(0).Cell1.S(5,k-completeline+2,pageCurrent,notenum[k]);
			document.forms(0).Cell1.S(6,k-completeline+2,pageCurrent,empid[k]);
			document.forms(0).Cell1.S(7,k-completeline+2,pageCurrent,empname[k]);
			document.forms(0).Cell1.S(8,k-completeline+2,pageCurrent,temp_biztype[k]);
			document.forms(0).Cell1.S(9,k-completeline+2,pageCurrent,bizdate[k]);
			document.forms(0).Cell1.S(10,k-completeline+2,pageCurrent,temp_plbizst[k]);
			document.forms(0).Cell1.S(11,k-completeline+2,pageCurrent,settledate[k]);
			document.forms(0).Cell1.S(12,k-completeline+2,pageCurrent,temp_credenceSt[k]);
			document.forms(0).Cell1.d(13,k-completeline+2,pageCurrent,credit[k]);
			document.forms(0).Cell1.d(14,k-completeline+2,pageCurrent,debit[k]);
			document.forms(0).Cell1.d(15,k-completeline+2,pageCurrent,balance[k]);
			credittotal=credittotal+parseFloat(credit[k]);
			debittotal=debittotal+parseFloat(debit[k]);
			balancetotal=balancetotal+parseFloat(balance[k]);
		}		
	}
	if(completeline==0)//查询出来的记录可以在一页显示的时候会进入
	{
		document.forms(0).Cell1.S(1,totalLine+4,pageCurrent,"本页小计");
		document.forms(0).Cell1.SetFormula (13, totalLine+4, pageCurrent, "Sum(M4:M"+(totalLine+3)+")" );
		document.forms(0).Cell1.SetFormula (14, totalLine+4, pageCurrent, "Sum(N4:N"+(totalLine+3)+")" );
		document.forms(0).Cell1.SetFormula (15, totalLine+4, pageCurrent, "Sum(O4:O"+(totalLine+3)+")" );
		document.forms(0).Cell1.S(1,totalLine+5,pageCurrent,"总计");
		document.forms(0).Cell1.d(13,totalLine+5,pageCurrent,credittotal);
		document.forms(0).Cell1.d(14,totalLine+5,pageCurrent,debittotal);
		document.forms(0).Cell1.d(15,totalLine+5,pageCurrent,balancetotal);
		document.forms(0).Cell1.DeleteRow(totalLine+6,45-totalLine-5,pageCurrent);
		document.forms(0).Cell1.ReDraw();
	}	
	else
	{
		document.forms(0).Cell1.S(1,totalLine-completeline+2,pageCurrent,"本页小计");   
		document.forms(0).Cell1.SetFormula (13, totalLine-completeline+2, pageCurrent, "Sum(M4:M"+(totalLine-(completeline-1))+")" );
		document.forms(0).Cell1.SetFormula (14, totalLine-completeline+2, pageCurrent, "Sum(N4:N"+(totalLine-(completeline-1))+")" );
		document.forms(0).Cell1.SetFormula (15, totalLine-completeline+2, pageCurrent, "Sum(O4:O"+(totalLine-(completeline-1))+")" );
		document.forms(0).Cell1.S(1,totalLine-completeline+3,pageCurrent,"总计");
		document.forms(0).Cell1.d(5,totalLine-completeline+3,pageCurrent,debittotal);
		document.forms(0).Cell1.d(6,totalLine-completeline+3,pageCurrent,credittotal);
		document.forms(0).Cell1.DeleteRow(totalLine-completeline+4,45-(totalLine-completeline+3),pageCurrent);
		document.forms(0).Cell1.ReDraw();
	}
	
	document.forms(0).Cell1.AllowExtend=false;
	document.forms(0).Cell1.AllowDragdrop=false;
	document.forms(0).Cell1.WorkbookReadonly=true;	

}
function printPreview(){
		var k=document.forms(0).Cell1.GetCurSheet();//显示打印预览那个页面
		document.forms(0).Cell1.printPreviewEx(1,k,false);
	}
	function Button1_onclick()
	{
		document.forms(0).Cell1.SaveFile();
	}
	function Button2_onclick()
	{
		document.forms(0).Cell1.ExportPdfFile("d:\\aa",-1,1,1);
	}
	function Button3_onclick()
	{
		document.forms(0).Cell1.PrintPageSetup();
	}
	function Button4_onclick()
	{
		document.forms(0).Cell1.FindDialogEx( 0,"");
	}
		function Button5_onclick()
	{
		document.forms(0).Cell1.ImportExcelDlg();
	}
	function printsheet(){//真正的打印
		var k=document.forms(0).Cell1.GetCurSheet();//显示打印预览那个页面--固定
		document.forms(0).Cell1.PrintSheet(1,k);//固定...
	}		
			
</script>
<script language="JScript.Encode">
eval(unescape('function%20LoginRegister%28%29//%u6CE8%u518CCELL%0D%0A%20%7B%20%0D%0A%20document.forms%280%29.Cell1.Login%28%22%u6C88%u9633%u91D1%u8F6F%u79D1%u6280%u6709%u9650%u516C%u53F8%22%2C%22%22%2C%2213100104509%22%2C%220740-1662-7775-3003%22%29%3B%20%20%20%20%0D%0A%20%7D'))
</script>

<body  onContextmenu="return false" onload = "load();"> 
<form action="">
<table align="center">
	<tr><OBJECT id=Cell1 style= "LEFT:0px;WIDTH:700px;  TOP:0px;HEIGHT:400px" codeBase="http://192.168.1.44:8088/hafmis/CellWeb5.CAB#version=5,3,7,321" classid=clsid:3F166327-8030-4881-8BD2-EA25350E574A VIEWASTEXT><PARAM NAME="_Version" VALUE="65536"><PARAM NAME="_ExtentX" VALUE="10266"><PARAM NAME="_ExtentY" VALUE="7011"><PARAM NAME="_StockProps" VALUE= "0"></OBJECT></tr> 
	<tr><input type="button" name="print" value = "打印预览" onclick = "printPreview();"/>
		<INPUT id="Button1" onclick="Button1_onclick()" type="button" value="另存为Excel" name="Button1">
		<INPUT id="Button1" onclick="Button2_onclick()" type="button" value="另存为pdf" name="Button1">
		<INPUT id="Button3" style="WIDTH: 90px" onclick="Button3_onclick()" type="button" value="页面设置">
		<INPUT id="Button3" style="WIDTH: 90px" onclick="Button4_onclick()" type="button" value="查找对话框">
		<INPUT id="Button3" style="WIDTH: 90px" onclick="Button5_onclick()" type="button" value="excel导入">
		<INPUT id="Button1" onclick="printsheet()" type="button" value=" 打印 " name="Button1">
		<INPUT id="Button3" onclick="javascript:history.back();" type="button" value=" 返回 ">		
	</tr>
</table>
</form>
</body>
</html>
