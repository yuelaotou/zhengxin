<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.util.List"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic"%>
<%@ page
	import="org.xpup.hafmis.sysfinance.treasurermng.depositcheckacc.dto.*"%>
<%@ include file="/checkUrl.jsp"%>

<%
	String path = request.getContextPath();
	List bankList = (List) request.getSession().getAttribute(
			"bankList_gjp");
	List officeList = (List) request.getSession().getAttribute(
			"officeList_gjp");
	List bankOutList = (List) request.getSession().getAttribute(
			"bankOutList_gjp");
	List officeOutList = (List) request.getSession().getAttribute(
			"officeOutList_gjp");
	DepositCheckAccWindowDTO depositCheckAccWindowDTO = (DepositCheckAccWindowDTO) request
			.getSession().getAttribute("depositCheckAccWindowDTO");
	int sizeFI = 0;
	if (bankList.size() > officeList.size()) {
		sizeFI = bankList.size();
	} else {
		sizeFI = officeList.size();
	}
	int sizeSE = 0;
	if (bankOutList.size() > officeOutList.size()) {
		sizeSE = bankOutList.size();
	} else {
		sizeSE = officeOutList.size();
	}
%>
<html>
	<head>
		<script src="<%=path%>/js/common.js"></script>
	</head>
	<script type="text/javascript">
	function load(){
	loginReg();
	document.forms(0).Cell1.openfile("<%=path%>/sysfinance/treasurermng/report/depositcheckaccwindow.cll","");
  	var pageCurrent=0;						//当前页
  	var completeline=0;						//记录行的
	var totalPageLine=40;					//每页显示多少行--除了第一行
	
	document.forms(0).Cell1.S(1,4,pageCurrent,"银行日记账余额");
	document.forms(0).Cell1.S(2,4,pageCurrent,<%=depositCheckAccWindowDTO.getBdcBalanceSum()%>);
	document.forms(0).Cell1.S(3,4,pageCurrent,"银行对账单余额");
	document.forms(0).Cell1.S(4,4,pageCurrent,<%=depositCheckAccWindowDTO.getBcaBalanceSum()%>);
	document.forms(0).Cell1.S(1,5,pageCurrent,"加：银行已收中心未收");
	document.forms(0).Cell1.S(2,5,pageCurrent,<%=depositCheckAccWindowDTO.getBankMoneySum()%>);
	document.forms(0).Cell1.S(3,5,pageCurrent,"加：中心已收银行未收");
	document.forms(0).Cell1.S(4,5,pageCurrent,<%=depositCheckAccWindowDTO.getOfficeMoneySum()%>);
	<%int m=0;
	  int n=0;
	  for(int i=0;i<sizeFI;i++){
	%>
		if(<%=i+2%>%totalPageLine==0&&<%=i%>>0)
		{	document.forms(0).Cell1.ReDraw();//重画一个表格
			pageCurrent++;//当前页++	
			completeline=<%=i%>;
			//绘制标签 param 	表页索引号。param 要设置的表页页签名称
			document.forms(0).Cell1.insertSheetFromFile("<%=path%>/sysfinance/treasurermng/report/depositcheckaccwindow.cll",0,1,pageCurrent);
	 		document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页");	
		}
	<%
		if(m<bankList.size()){
				DepositCheckAccWindowBaseDTO depositCheckAccWindowBaseDTO = (DepositCheckAccWindowBaseDTO) bankList.get(m);
	%>
			if(pageCurrent==0){
				document.forms(0).Cell1.S(1,<%=i%>+6,pageCurrent,<%=i%>+1 + ".结算日期："+ "<%=depositCheckAccWindowBaseDTO.getSettDate()%>"+ " 结算号："+ "<%=depositCheckAccWindowBaseDTO.getSettNum()%>");
				document.forms(0).Cell1.S(2,<%=i%>+6,pageCurrent,<%=depositCheckAccWindowBaseDTO.getMoney()%>);
			}else{
				document.forms(0).Cell1.S(1,<%=i%>+6-completeline-2,pageCurrent,<%=i%>+1 + ".结算日期："+ "<%=depositCheckAccWindowBaseDTO.getSettDate()%>"+ " 结算号："+ "<%=depositCheckAccWindowBaseDTO.getSettNum()%>");
				document.forms(0).Cell1.S(2,<%=i%>+6-completeline-2,pageCurrent,<%=depositCheckAccWindowBaseDTO.getMoney()%>);
			}
	<%		
			m++;
	  	}else{
	%>
		  	if(pageCurrent==0){
				document.forms(0).Cell1.S(1,<%=i%>+6,pageCurrent,"");
				document.forms(0).Cell1.S(2,<%=i%>+6,pageCurrent,"");
			}else{
				document.forms(0).Cell1.S(1,<%=i%>+6-completeline-2,pageCurrent,"");
				document.forms(0).Cell1.S(2,<%=i%>+6-completeline-2,pageCurrent,"");
			}
	<% 
	  	}
	  	if(n<officeList.size()){
			DepositCheckAccWindowBaseDTO depositCheckAccWindowBaseDTO = (DepositCheckAccWindowBaseDTO) officeList.get(n);
	%>
			if(pageCurrent==0){
				document.forms(0).Cell1.S(3,<%=i%>+6,pageCurrent,<%=i%>+1 + ".结算日期："+ <%=depositCheckAccWindowBaseDTO.getSettDate()%>+ " 结算号："+ "<%=depositCheckAccWindowBaseDTO.getSettNum()%>");
				document.forms(0).Cell1.S(4,<%=i%>+6,pageCurrent,<%=depositCheckAccWindowBaseDTO.getMoney()%>);
			}else{
				document.forms(0).Cell1.S(3,<%=i%>+6-completeline-2,pageCurrent,<%=i%>+1 + ".结算日期："+ <%=depositCheckAccWindowBaseDTO.getSettDate()%>+ " 结算号："+ "<%=depositCheckAccWindowBaseDTO.getSettNum()%>");
				document.forms(0).Cell1.S(4,<%=i%>+6-completeline-2,pageCurrent,<%=depositCheckAccWindowBaseDTO.getMoney()%>);
			}
	<%		
			n++;
	    }else{
	%>
		  	if(pageCurrent==0){
				document.forms(0).Cell1.S(3,<%=i%>+6,pageCurrent,"");
				document.forms(0).Cell1.S(4,<%=i%>+6,pageCurrent,"");
			}else{
				document.forms(0).Cell1.S(3,<%=i%>+6-completeline-2,pageCurrent,"");
				document.forms(0).Cell1.S(4,<%=i%>+6-completeline-2,pageCurrent,"");
			}
	<% 
	    }	
	  }
	%>
	if(pageCurrent==0){
		document.forms(0).Cell1.S(1,<%=sizeFI + 6%>,pageCurrent,"减：银行已付中心未付");
		document.forms(0).Cell1.S(2,<%=sizeFI + 6%>,pageCurrent,<%=depositCheckAccWindowDTO.getBankOutMoneySum()%>);
		document.forms(0).Cell1.S(3,<%=sizeFI + 6%>,pageCurrent,"减：中心已付银行未付");
		document.forms(0).Cell1.S(4,<%=sizeFI + 6%>,pageCurrent,<%=depositCheckAccWindowDTO.getOfficeOutMoneySum()%>);
	}else{
		document.forms(0).Cell1.S(1,<%=sizeFI + 6%>-completeline-2,pageCurrent,"减：银行已付中心未付");
		document.forms(0).Cell1.S(2,<%=sizeFI + 6%>-completeline-2,pageCurrent,<%=depositCheckAccWindowDTO.getBankOutMoneySum()%>);
		document.forms(0).Cell1.S(3,<%=sizeFI + 6%>-completeline-2,pageCurrent,"减：中心已付银行未付");
		document.forms(0).Cell1.S(4,<%=sizeFI + 6%>-completeline-2,pageCurrent,<%=depositCheckAccWindowDTO.getOfficeOutMoneySum()%>);
	}
	<%int p=0;
	  int q=0;
	  for(int i=sizeFI+1;i<sizeFI+sizeSE+1;i++){
	%>
		if(<%=i+2%>%totalPageLine==0&&<%=i%>>0)
		{
			document.forms(0).Cell1.ReDraw();//重画一个表格
			pageCurrent++;//当前页++	
			completeline=<%=i%>;
			//绘制标签 param 	表页索引号。param 要设置的表页页签名称
			document.forms(0).Cell1.insertSheetFromFile("<%=path%>/sysfinance/treasurermng/report/depositcheckaccwindow.cll",0,1,pageCurrent);
  			document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页");	
		}
	<%
		if(p<bankOutList.size()){
			DepositCheckAccWindowBaseDTO depositCheckAccWindowBaseDTO = (DepositCheckAccWindowBaseDTO) bankOutList.get(p);
	%>
			if(pageCurrent==0){
				document.forms(0).Cell1.S(1,<%=i%>+6,pageCurrent,<%=i - sizeFI - 1%>+1 + ".结算日期："+ <%=depositCheckAccWindowBaseDTO.getSettDate()%>+ " 结算号："+ "<%=depositCheckAccWindowBaseDTO.getSettNum()%>");
				document.forms(0).Cell1.S(2,<%=i%>+6,pageCurrent,<%=depositCheckAccWindowBaseDTO.getMoney()%>);
			}else{
				document.forms(0).Cell1.S(1,<%=i%>+6-completeline-2,pageCurrent,<%=i - sizeFI - 1%>+1 + ".结算日期："+ <%=depositCheckAccWindowBaseDTO.getSettDate()%>+ " 结算号："+ "<%=depositCheckAccWindowBaseDTO.getSettNum()%>");
				document.forms(0).Cell1.S(2,<%=i%>+6-completeline-2,pageCurrent,<%=depositCheckAccWindowBaseDTO.getMoney()%>);
			}
	<%		
			p++;
	    }else{
	%>
		  	if(pageCurrent==0){
				document.forms(0).Cell1.S(1,<%=i%>+6,pageCurrent,"");
				document.forms(0).Cell1.S(2,<%=i%>+6,pageCurrent,"");
			}else{
				document.forms(0).Cell1.S(1,<%=i%>+6-completeline-2,pageCurrent,"");
				document.forms(0).Cell1.S(2,<%=i%>+6-completeline-2,pageCurrent,"");
			}
	<% 
	    }
	    if(q<officeOutList.size()){
			DepositCheckAccWindowBaseDTO depositCheckAccWindowBaseDTO = (DepositCheckAccWindowBaseDTO) officeOutList.get(q);
	%>
			if(pageCurrent==0){
				document.forms(0).Cell1.S(3,<%=i%>+6,pageCurrent,<%=i - sizeFI - 1%>+1 + ".结算日期："+ <%=depositCheckAccWindowBaseDTO.getSettDate()%>+ " 结算号："+ "<%=depositCheckAccWindowBaseDTO.getSettNum()%>");
				document.forms(0).Cell1.S(4,<%=i%>+6,pageCurrent,<%=depositCheckAccWindowBaseDTO.getMoney()%>);
			}else{
				document.forms(0).Cell1.S(3,<%=i%>+6-completeline-2,pageCurrent,<%=i - sizeFI - 1%>+1 + ".结算日期："+ <%=depositCheckAccWindowBaseDTO.getSettDate()%>+ " 结算号："+ "<%=depositCheckAccWindowBaseDTO.getSettNum()%>");
				document.forms(0).Cell1.S(4,<%=i%>+6-completeline-2,pageCurrent,<%=depositCheckAccWindowBaseDTO.getMoney()%>);
			}
	<%		
			q++;
	   }else{
	%>
		  	if(pageCurrent==0){
				document.forms(0).Cell1.S(3,<%=i%>+6,pageCurrent,"");
				document.forms(0).Cell1.S(4,<%=i%>+6,pageCurrent,"");
			}else{
				document.forms(0).Cell1.S(3,<%=i%>+6-completeline-2,pageCurrent,"");
				document.forms(0).Cell1.S(4,<%=i%>+6-completeline-2,pageCurrent,"");
			}
	<% 
	   }
	  }
	%>
	if(pageCurrent==0){
		document.forms(0).Cell1.S(1,<%=sizeFI + sizeSE + 7%>,pageCurrent,"调节后余额（中心）");
		document.forms(0).Cell1.S(2,<%=sizeFI + sizeSE + 7%>,pageCurrent,<%=depositCheckAccWindowDTO.getAdjustOfficeBalance()%>);
		document.forms(0).Cell1.S(3,<%=sizeFI + sizeSE + 7%>,pageCurrent,"调节后余额（银行）");
		document.forms(0).Cell1.S(4,<%=sizeFI + sizeSE + 7%>,pageCurrent,<%=depositCheckAccWindowDTO.getAdjustBankBalance()%>);
	}else{
		document.forms(0).Cell1.S(1,<%=sizeFI + sizeSE + 7%>-completeline-2,pageCurrent,"调节后余额（中心）");
		document.forms(0).Cell1.S(2,<%=sizeFI + sizeSE + 7%>-completeline-2,pageCurrent,<%=depositCheckAccWindowDTO.getAdjustOfficeBalance()%>);
		document.forms(0).Cell1.S(3,<%=sizeFI + sizeSE + 7%>-completeline-2,pageCurrent,"调节后余额（银行）");
		document.forms(0).Cell1.S(4,<%=sizeFI + sizeSE + 7%>-completeline-2,pageCurrent,<%=depositCheckAccWindowDTO.getAdjustBankBalance()%>);
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

	<body onload="load();">
		<form action="">
			<table align="center">
				<tr>
					<OBJECT id=Cell1
						style="LEFT:0px;WIDTH:700px;  TOP:0px;HEIGHT:400px"
						codeBase="http://192.168.1.44:8088/hafmis/CellWeb5.CAB#version=5,3,7,321"
						classid=clsid:3F166327-8030-4881-8BD2-EA25350E574A VIEWASTEXT>
						<PARAM NAME="_Version" VALUE="65536">
						<PARAM NAME="_ExtentX" VALUE="10266">
						<PARAM NAME="_ExtentY" VALUE="7011">
						<PARAM NAME="_StockProps" VALUE="0">
					</OBJECT>
				</tr>
				<tr>
					<input type="button" name="print" value="打印预览"
						onclick="printPreview();" />
					<INPUT id="Button1" onclick="Button1_onclick()" type="button"
						value="另存为Excel" name="Button1">
					<INPUT id="Button1" onclick="Button2_onclick()" type="button"
						value="另存为pdf" name="Button1">
					<INPUT id="Button3" style="WIDTH: 90px" onclick="Button3_onclick()"
						type="button" value="页面设置">
					<INPUT id="Button3" style="WIDTH: 90px" onclick="Button4_onclick()"
						type="button" value="查找对话框">
					<INPUT id="Button3" style="WIDTH: 90px" onclick="Button5_onclick()"
						type="button" value="excel导入">
					<INPUT id="Button1" onclick="printsheet()" type="button"
						value=" 打印 " name="Button1">
					<INPUT id="Button3"
						onclick="location.href='depositCheckAccWindowShowAC.do'"
						type="button" value=" 返回 ">
				</tr>
			</table>
		</form>
	</body>
</html>
