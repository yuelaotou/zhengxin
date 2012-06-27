<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.util.List"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic"%>
<%@ page
	import="org.xpup.hafmis.sysloan.loanaccord.loanaccord.dto.LoanaccordDTO"%>
<%@ page import="java.math.BigDecimal"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	String url = (String) request.getAttribute("URL");
	if (url == null) {
		url = "loanaccordTbForwardUrlAC.do";
	}
%>
<html>
	<head>
		<script src="<%=path%>/js/common.js">
</script>
	</head>
	<script type="text/javascript">
	function load(){	
	loginReg();
	var pageCurrent = 0;
	var i=0;
	document.forms(0).Cell1.openfile("<%=path%>/sysloan/loanaccord/report/loanaccordtb_wsh_final.cll","");
	<%
	List list = (List)request.getSession().getAttribute("loanaccordPrintList");
	LoanaccordDTO loanaccordDTO = null;
	
	for(int i=0;i<list.size();i++){
		loanaccordDTO = new LoanaccordDTO();
		loanaccordDTO=(LoanaccordDTO) list.get(i);
		
	%>
	i=<%=i%>
		if(pageCurrent==0){
			var sumPay = "<%=loanaccordDTO.getLoanMoney().toString()%>";
			var bigsumPay=ChangeToBig(sumPay);
			if(i%2==1){
				document.forms(0).Cell1.S(2,30,0,bigsumPay);
				document.forms(0).Cell1.S(4,30,0,sumPay);
				document.forms(0).Cell1.S(4,25,0,"<%=loanaccordDTO.getBorrowerName() %>");
				document.forms(0).Cell1.S(4,26,0,"<%=loanaccordDTO.getLoanKouAcc() %>");
				document.forms(0).Cell1.S(2,26,0,"<%=loanaccordDTO.getLoanBankName() %>");
				document.forms(0).Cell1.S(4,27,0,"<%=loanaccordDTO.getLoanModeName() %>");
				document.forms(0).Cell1.S(2,25,0,"<%=loanaccordDTO.getContractId() %>");
				document.forms(0).Cell1.d(2,27,0,"<%=loanaccordDTO.getCorpusInterest() %>");
				document.forms(0).Cell1.S(2,28,0,"<%=loanaccordDTO.getLoanStartDate() %>");
				document.forms(0).Cell1.S(4,28,0,"<%=loanaccordDTO.getOverTime() %>");
				document.forms(0).Cell1.S(2,29,0,"<%=loanaccordDTO.getLoanMonthRate() %>");
				document.forms(0).Cell1.S(4,29,0,"<%=loanaccordDTO.getLoanTimeLimit() %>");
				document.forms(0).Cell1.S(4,24,0,"<%=loanaccordDTO.getDocNum() %>");
				document.forms(0).Cell1.S(7,24,0,"<%=loanaccordDTO.getNoteNum() %>");
			}else{
				document.forms(0).Cell1.S(2,10,0,bigsumPay);
				document.forms(0).Cell1.S(4,10,0,sumPay);
				document.forms(0).Cell1.S(4,5,0,"<%=loanaccordDTO.getBorrowerName() %>");
				document.forms(0).Cell1.S(4,6,0,"<%=loanaccordDTO.getLoanKouAcc() %>");
				document.forms(0).Cell1.S(2,6,0,"<%=loanaccordDTO.getLoanBankName() %>");
				document.forms(0).Cell1.S(4,7,0,"<%=loanaccordDTO.getLoanModeName() %>");
				document.forms(0).Cell1.S(2,5,0,"<%=loanaccordDTO.getContractId() %>");
				document.forms(0).Cell1.d(2,7,0,"<%=loanaccordDTO.getCorpusInterest() %>");
				document.forms(0).Cell1.S(2,8,0,"<%=loanaccordDTO.getLoanStartDate() %>");
				document.forms(0).Cell1.S(4,8,0,"<%=loanaccordDTO.getOverTime() %>");
				document.forms(0).Cell1.S(2,9,0,"<%=loanaccordDTO.getLoanMonthRate() %>");
				document.forms(0).Cell1.S(4,9,0,"<%=loanaccordDTO.getLoanTimeLimit() %>");
				document.forms(0).Cell1.S(4,4,0,"<%=loanaccordDTO.getDocNum() %>");
				document.forms(0).Cell1.S(7,4,0,"<%=loanaccordDTO.getNoteNum() %>");
			}
			
		}else{
			if(i%2==0){
				document.forms(0).Cell1.insertSheetFromFile("<%=path%>/sysloan/loanaccord/report/loanaccordtb_wsh_final_1.cll",0,1,pageCurrent);
			document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页");	
			}
			if(i%2==1){
				var sumPay = "<%=loanaccordDTO.getLoanMoney().toString()%>";
				var bigsumPay=ChangeToBig(sumPay);
				document.forms(0).Cell1.S(2,30,pageCurrent,bigsumPay);
				document.forms(0).Cell1.S(4,30,pageCurrent,sumPay);
				document.forms(0).Cell1.S(4,25,pageCurrent,"<%=loanaccordDTO.getBorrowerName() %>");
				document.forms(0).Cell1.S(4,26,pageCurrent,"<%=loanaccordDTO.getLoanKouAcc() %>");
				document.forms(0).Cell1.S(2,26,pageCurrent,"<%=loanaccordDTO.getLoanBankName() %>");
				document.forms(0).Cell1.S(4,27,pageCurrent,"<%=loanaccordDTO.getLoanModeName() %>");
				document.forms(0).Cell1.S(2,25,pageCurrent,"<%=loanaccordDTO.getContractId() %>");
				document.forms(0).Cell1.d(2,27,pageCurrent,"<%=loanaccordDTO.getCorpusInterest() %>");
				document.forms(0).Cell1.S(2,28,pageCurrent,"<%=loanaccordDTO.getLoanStartDate() %>");
				document.forms(0).Cell1.S(4,28,pageCurrent,"<%=loanaccordDTO.getOverTime() %>");
				document.forms(0).Cell1.S(2,29,pageCurrent,"<%=loanaccordDTO.getLoanMonthRate() %>");
				document.forms(0).Cell1.S(4,29,pageCurrent,"<%=loanaccordDTO.getLoanTimeLimit() %>");
				document.forms(0).Cell1.S(4,24,pageCurrent,"<%=loanaccordDTO.getDocNum() %>");
				document.forms(0).Cell1.S(7,24,pageCurrent,"<%=loanaccordDTO.getNoteNum() %>");
			}else{
				
				
				var sumPay = "<%=loanaccordDTO.getLoanMoney().toString()%>";
				var bigsumPay=ChangeToBig(sumPay);
				document.forms(0).Cell1.S(2,10,pageCurrent,bigsumPay);
				document.forms(0).Cell1.S(4,10,pageCurrent,sumPay);
				document.forms(0).Cell1.S(4,5,pageCurrent,"<%=loanaccordDTO.getBorrowerName() %>");
				document.forms(0).Cell1.S(4,6,pageCurrent,"<%=loanaccordDTO.getLoanKouAcc() %>");
				document.forms(0).Cell1.S(2,6,pageCurrent,"<%=loanaccordDTO.getLoanBankName() %>");
				document.forms(0).Cell1.S(4,7,pageCurrent,"<%=loanaccordDTO.getLoanModeName() %>");
				document.forms(0).Cell1.S(2,5,pageCurrent,"<%=loanaccordDTO.getContractId() %>");
				document.forms(0).Cell1.d(2,7,pageCurrent,"<%=loanaccordDTO.getCorpusInterest() %>");
				document.forms(0).Cell1.S(2,8,pageCurrent,"<%=loanaccordDTO.getLoanStartDate() %>");
				document.forms(0).Cell1.S(4,8,pageCurrent,"<%=loanaccordDTO.getOverTime() %>");
				document.forms(0).Cell1.S(2,9,pageCurrent,"<%=loanaccordDTO.getLoanMonthRate() %>");
				document.forms(0).Cell1.S(4,9,pageCurrent,"<%=loanaccordDTO.getLoanTimeLimit() %>");
				document.forms(0).Cell1.S(4,4,pageCurrent,"<%=loanaccordDTO.getDocNum() %>");
				document.forms(0).Cell1.S(7,4,pageCurrent,"<%=loanaccordDTO.getNoteNum() %>");
				
				
			}
			if(i==<%=list.size()-1%>&&i%2==0){
				document.forms(0).Cell1.DeleteRow(21, 15, pageCurrent);
			}
			
		}
		if(i>0&&i%2==1){
			pageCurrent++;
		}
		
	  <%
	  }
	  %>

	document.forms(0).Cell1.AllowExtend=false;
	document.forms(0).Cell1.AllowDragdrop=false;
	document.forms(0).Cell1.WorkbookReadonly=true;	
}
function printsheet(){//真正的打印
		var k=document.forms(0).Cell1.GetCurSheet();//显示打印预览那个页面--固定
		document.forms(0).Cell1.PrintSheet(1,k);//固定...
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
	
</script>
	<script language="JScript.Encode">
eval(unescape('function%20LoginRegister%28%29//%u6CE8%u518CCELL%0D%0A%20%7B%20%0D%0A%20document.forms%280%29.Cell1.Login%28%22%u6C88%u9633%u91D1%u8F6F%u79D1%u6280%u6709%u9650%u516C%u53F8%22%2C%22%22%2C%2213100104509%22%2C%220740-1662-7775-3003%22%29%3B%20%20%20%20%0D%0A%20%7D'))
</script>
	<body onload="load();" onContextmenu="return false">
		<form action="">
			<table align="center">
				<tr>
					<OBJECT id=Cell1
						style="LEFT:0px;WIDTH:800px;  TOP:0px;HEIGHT:450px"
						codeBase="http://192.168.1.44:8088/hafmis/CellWeb5.CAB#version=5,3,7,321"
						classid=clsid:3F166327-8030-4881-8BD2-EA25350E574A VIEWASTEXT>
						<PARAM NAME="_Version" VALUE="65536">
						<PARAM NAME="_ExtentX" VALUE="10266">
						<PARAM NAME="_ExtentY" VALUE="7011">
						<PARAM NAME="_StockProps" VALUE="0">
					</OBJECT>
				</tr>
				<tr>
					<td>
						<input type="button" name="print" value="打印预览"
							onclick="printPreview();" />
					</td>
					<td>
						<INPUT id="Button1" onclick="Button1_onclick()" type="button"
							value="另存为Excel" name="Button1">
					</td>
					<td>
						<INPUT id="Button1" onclick="Button2_onclick()" type="button"
							value="另存为pdf" name="Button1">
					</td>
					<td>
						<INPUT id="Button3" style="WIDTH: 100px"
							onclick="Button3_onclick()" type="button" value="页面设置">
					</td>
					<td>
						<INPUT id="Button3" style="WIDTH: 100px"
							onclick="Button4_onclick()" type="button" value="查找对话框">
					</td>
					<td>
						<INPUT id="Button3" style="WIDTH: 100px"
							onclick="Button5_onclick()" type="button" value="excel导入">
					</td>
					<td>
						<INPUT id="Button1" onclick="printsheet()" type="button"
							value=" 打印 " name="Button1">
					</td>
					<td>
						<INPUT id="Button3" onclick="location.href='<%=url%>'"
							type="button" value=" 返回 ">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
