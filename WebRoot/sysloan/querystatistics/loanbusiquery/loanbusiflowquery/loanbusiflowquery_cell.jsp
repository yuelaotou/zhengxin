<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.util.List"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic"%>
<%@ page
	import="org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.loanbusiflowquery.dto.LoanBusiFlowQueryDTO"%>
<%@ include file="/checkUrl.jsp"%>

<%
	String path = request.getContextPath();
	List list = (List) request.getAttribute("printLoanBusiFlowQueryList");
	String url = (String) request.getAttribute("URL");
	if (url == null) {
		url = "loanBusiFlowQueryShowAC.do";
	}
	String userName=(String)request.getAttribute("userName");
	String plbizDate=(String)request.getAttribute("plbizDate");
%>
<html>
	<head>
		<script src="<%=path%>/js/common.js"></script>
	</head>
	<script type="text/javascript">
	function load(){
	loginReg();	
	document.forms(0).Cell1.openfile("<%=path%>/sysloan/querystatistics/report/loanbusiflowquery.cll","");
	var docNum=[];
	var loanKouAcc=[];
	var contractId=[];
	var borrowerName=[];
	var bizType=[];
	var occurMoney=[];
	var reclaimCorpus=[];
	var reclaimAccrual=[];
	var realPunishInterest=[];
	var badDebt=[];
	var reclaim=[];
	var putUpMoney=[];
	var bail=[];
	var bailAccrual=[];
	var bizDate=[];
	var bizSt=[];
	var makePerson=[];
	var i=0;
	
	<%
		LoanBusiFlowQueryDTO loanBusiFlowQueryDTO = new LoanBusiFlowQueryDTO();
		for(int j=0;j<list.size();j++){
		loanBusiFlowQueryDTO = (LoanBusiFlowQueryDTO)list.get(j);
	%>
	    //把数据传到JS的数组里面..
		docNum[i] = "<%=loanBusiFlowQueryDTO.getDocNum()%>"; 
		loanKouAcc[i] = "<%=loanBusiFlowQueryDTO.getLoanKouAcc()%>";
		contractId[i] = "<%=loanBusiFlowQueryDTO.getContractId()%>";
		borrowerName[i] = "<%=loanBusiFlowQueryDTO.getBorrowerName()%>";
		bizType[i] = "<%=loanBusiFlowQueryDTO.getBizType()%>";
		occurMoney[i] = "<%=loanBusiFlowQueryDTO.getOccurMoney()%>";
		reclaimCorpus[i] = "<%=loanBusiFlowQueryDTO.getReclaimCorpus()%>";
		reclaimAccrual[i] = "<%=loanBusiFlowQueryDTO.getReclaimAccrual()%>"; 
		realPunishInterest[i] = "<%=loanBusiFlowQueryDTO.getRealPunishInterest()%>";
		badDebt[i] = "<%=loanBusiFlowQueryDTO.getBadDebt()%>";
		reclaim[i] = "<%=loanBusiFlowQueryDTO.getReclaim()%>";
		putUpMoney[i] = "<%=loanBusiFlowQueryDTO.getPutUpMoney()%>";
		bail[i] = "<%=loanBusiFlowQueryDTO.getBail()%>";
		bailAccrual[i] = "<%=loanBusiFlowQueryDTO.getBailAccrual()%>";
		bizDate[i] = "<%=loanBusiFlowQueryDTO.getBizDate()%>"; 
		bizSt[i] = "<%=loanBusiFlowQueryDTO.getBizSt()%>";
		makePerson[i] = "<%=loanBusiFlowQueryDTO.getMakePerson()%>";
		i++;
	<%}%>
	var totalLine=docNum.length;			//总的行数 数组的长度
	var totalPageLine=40;					//每页显示多少行--除了第一行
	var pageTotal=totalLine/totalPageLine;	//总共分几页 总行数/每页的行数
	var pageCurrent=0;						//当前页
	var completeline=0;						//记录行的
	var occurMoneyTotle=0;					//发放金额-总额
	var reclaimCorpusTotle=0;				//回收本金-总额
	var reclaimAccrualTotle=0;				//回收利息-总额总额
	var realPunishInterestTotle=0;          //回收罚息-总额
	var badDebtTotle=0;						//呆账核销金额-总额
	var reclaimTotle=0;						//回收总金额-总额
	var putUpMoneyTotle=0;					//挂账金额-总额
	var bailTotle=0;          				//保证金-总额
	var bailAccrualTotle=0;          		//保证金利息-总额
	
	for(k = 0 ; k < totalLine; k++){
		if(k%totalPageLine==0&&k>0)
		{
			document.forms(0).Cell1.S(1,totalPageLine+4,pageCurrent,"小计:");
			document.forms(0).Cell1.SetFormula (6, totalPageLine+4, pageCurrent, "Sum(F4:F"+(totalPageLine+3)+")" );
			document.forms(0).Cell1.SetFormula (7, totalPageLine+4, pageCurrent, "Sum(G4:G"+(totalPageLine+3)+")" );
			document.forms(0).Cell1.SetFormula (8, totalPageLine+4, pageCurrent, "Sum(H4:H"+(totalPageLine+3)+")" );
			document.forms(0).Cell1.SetFormula (9, totalPageLine+4, pageCurrent, "Sum(I4:I"+(totalPageLine+3)+")" );
			document.forms(0).Cell1.SetFormula (10, totalPageLine+4, pageCurrent, "Sum(J4:J"+(totalPageLine+3)+")" );
			document.forms(0).Cell1.SetFormula (11, totalPageLine+4, pageCurrent, "Sum(K4:K"+(totalPageLine+3)+")" );
			document.forms(0).Cell1.SetFormula (12, totalPageLine+4, pageCurrent, "Sum(L4:L"+(totalPageLine+3)+")" );
			document.forms(0).Cell1.SetFormula (13, totalPageLine+4, pageCurrent, "Sum(M4:M"+(totalPageLine+3)+")" );
			document.forms(0).Cell1.SetFormula (14, totalPageLine+4, pageCurrent, "Sum(N4:N"+(totalPageLine+3)+")" );
			document.forms(0).Cell1.DeleteRow(totalPageLine+5,46-totalPageLine-4,pageCurrent);
			document.forms(0).Cell1.ReDraw();//重画一个表格
			pageCurrent++;//当前页++	
			completeline=k-2;
			//绘制标签 param 	表页索引号。param 要设置的表页页签名称					
			document.forms(0).Cell1.insertSheetFromFile("<%=path%>/sysloan/querystatistics/report/loanbusiflowquery.cll",0,1,pageCurrent);
			document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页")	
		}
		if(pageCurrent==0)
		{	
			document.forms(0).Cell1.S(1,k+4,0,docNum[k]);
			document.forms(0).Cell1.S(2,k+4,0,loanKouAcc[k]);
			document.forms(0).Cell1.S(3,k+4,0,contractId[k]);
			document.forms(0).Cell1.S(4,k+4,0,borrowerName[k]);
			document.forms(0).Cell1.S(5,k+4,0,bizType[k]);
			document.forms(0).Cell1.d(6,k+4,0,occurMoney[k]);
			document.forms(0).Cell1.d(7,k+4,0,reclaimCorpus[k]);
			document.forms(0).Cell1.d(8,k+4,0,reclaimAccrual[k]);
			document.forms(0).Cell1.d(9,k+4,0,realPunishInterest[k]);
			document.forms(0).Cell1.d(10,k+4,0,badDebt[k]);
			document.forms(0).Cell1.d(11,k+4,0,reclaim[k]);
			document.forms(0).Cell1.d(12,k+4,0,putUpMoney[k]);
			document.forms(0).Cell1.d(13,k+4,0,bail[k]);
			document.forms(0).Cell1.d(14,k+4,0,bailAccrual[k]);
			document.forms(0).Cell1.S(15,k+4,0,bizDate[k]);
			document.forms(0).Cell1.S(16,k+4,0,bizSt[k]);
			document.forms(0).Cell1.S(17,k+4,0,makePerson[k]);
			occurMoneyTotle=occurMoneyTotle+parseFloat(occurMoney[k]);
			reclaimCorpusTotle=reclaimCorpusTotle+parseFloat(reclaimCorpus[k]);
			reclaimAccrualTotle=reclaimAccrualTotle+parseFloat(reclaimAccrual[k]);
			realPunishInterestTotle=realPunishInterestTotle+parseFloat(realPunishInterest[k]);
			badDebtTotle=badDebtTotle+parseFloat(badDebt[k]);
			reclaimTotle=reclaimTotle+parseFloat(reclaim[k]);
			putUpMoneyTotle=putUpMoneyTotle+parseFloat(putUpMoney[k]);
			bailTotle=bailTotle+parseFloat(bail[k]);
			bailAccrualTotle=bailAccrualTotle+parseFloat(bailAccrual[k]);
			}
		else{//向第一页后的所有页插数据
			document.forms(0).Cell1.S(1,k-completeline+2,pageCurrent,docNum[k]);
			document.forms(0).Cell1.S(2,k-completeline+2,pageCurrent,loanKouAcc[k]);
			document.forms(0).Cell1.S(3,k-completeline+2,pageCurrent,contractId[k]);
			document.forms(0).Cell1.S(4,k-completeline+2,pageCurrent,borrowerName[k]);
			document.forms(0).Cell1.S(5,k-completeline+2,pageCurrent,bizType[k]);
			document.forms(0).Cell1.d(6,k-completeline+2,pageCurrent,occurMoney[k]);
			document.forms(0).Cell1.d(7,k-completeline+2,pageCurrent,reclaimCorpus[k]);
			document.forms(0).Cell1.d(8,k-completeline+2,pageCurrent,reclaimAccrual[k]);
			document.forms(0).Cell1.d(9,k-completeline+2,pageCurrent,realPunishInterest[k]);
			document.forms(0).Cell1.d(10,k-completeline+2,pageCurrent,badDebt[k]);
			document.forms(0).Cell1.d(11,k-completeline+2,pageCurrent,reclaim[k]);
			document.forms(0).Cell1.d(12,k-completeline+2,pageCurrent,putUpMoney[k]);
			document.forms(0).Cell1.d(13,k-completeline+2,pageCurrent,bail[k]);
			document.forms(0).Cell1.d(14,k-completeline+2,pageCurrent,bailAccrual[k]);
			document.forms(0).Cell1.S(15,k-completeline+2,pageCurrent,bizDate[k]);
			document.forms(0).Cell1.S(16,k-completeline+2,pageCurrent,bizSt[k]);
			document.forms(0).Cell1.S(17,k-completeline+2,pageCurrent,makePerson[k]);
			occurMoneyTotle=occurMoneyTotle+parseFloat(occurMoney[k]);
			reclaimCorpusTotle=reclaimCorpusTotle+parseFloat(reclaimCorpus[k]);
			reclaimAccrualTotle=reclaimAccrualTotle+parseFloat(reclaimAccrual[k]);
			realPunishInterestTotle=realPunishInterestTotle+parseFloat(realPunishInterest[k]);
			badDebtTotle=badDebtTotle+parseFloat(badDebt[k]);
			reclaimTotle=reclaimTotle+parseFloat(reclaim[k]);
			putUpMoneyTotle=putUpMoneyTotle+parseFloat(putUpMoney[k]);
			bailTotle=bailTotle+parseFloat(bail[k]);
			bailAccrualTotle=bailAccrualTotle+parseFloat(bailAccrual[k]);
		}		
	}
	if(completeline==0)//查询出来的记录可以在一页显示的时候会进入
	{
		document.forms(0).Cell1.S(1,totalLine+4,pageCurrent,"小计");
		document.forms(0).Cell1.SetFormula (6, totalLine+4, pageCurrent, "Sum(F4:F"+(totalLine+3)+")" );
		document.forms(0).Cell1.SetFormula (7, totalLine+4, pageCurrent, "Sum(G4:G"+(totalLine+3)+")" );
		document.forms(0).Cell1.SetFormula (8, totalLine+4, pageCurrent, "Sum(H4:H"+(totalLine+3)+")" );
		document.forms(0).Cell1.SetFormula (9, totalLine+4, pageCurrent, "Sum(I4:I"+(totalLine+3)+")" );
		document.forms(0).Cell1.SetFormula (10, totalLine+4, pageCurrent, "Sum(J4:J"+(totalLine+3)+")" );
		document.forms(0).Cell1.SetFormula (11, totalLine+4, pageCurrent, "Sum(K4:K"+(totalLine+3)+")" );
		document.forms(0).Cell1.SetFormula (12, totalLine+4, pageCurrent, "Sum(L4:L"+(totalLine+3)+")" );
		document.forms(0).Cell1.SetFormula (13, totalLine+4, pageCurrent, "Sum(M4:M"+(totalLine+3)+")" );
		document.forms(0).Cell1.SetFormula (14, totalLine+4, pageCurrent, "Sum(N4:N"+(totalLine+3)+")" );
		document.forms(0).Cell1.S(1,totalLine+5,pageCurrent,"总计");
		document.forms(0).Cell1.d(6,totalLine+5,pageCurrent,occurMoneyTotle);
		document.forms(0).Cell1.d(7,totalLine+5,pageCurrent,reclaimCorpusTotle);
		document.forms(0).Cell1.d(8,totalLine+5,pageCurrent,reclaimAccrualTotle);
		document.forms(0).Cell1.d(9,totalLine+5,pageCurrent,realPunishInterestTotle);
		document.forms(0).Cell1.d(10,totalLine+5,pageCurrent,badDebtTotle);
		document.forms(0).Cell1.d(11,totalLine+5,pageCurrent,reclaimTotle);
		document.forms(0).Cell1.d(12,totalLine+5,pageCurrent,putUpMoneyTotle);
		document.forms(0).Cell1.d(13,totalLine+5,pageCurrent,bailTotle);
		document.forms(0).Cell1.d(14,totalLine+5,pageCurrent,bailAccrualTotle);
		document.forms(0).Cell1.S(9,totalLine+6,pageCurrent,"操作员");
		document.forms(0).Cell1.S(11,totalLine+6,pageCurrent,"<%=userName%>");
		document.forms(0).Cell1.S(12,totalLine+6,pageCurrent,"操作时间");
		document.forms(0).Cell1.S(15,totalLine+6,pageCurrent,"<%=plbizDate%>");
		document.forms(0).Cell1.DeleteRow(totalLine+7,46-totalLine-6,pageCurrent);
		document.forms(0).Cell1.ReDraw();
	}	
	else
	{
		document.forms(0).Cell1.S(1,totalLine-completeline+2,pageCurrent,"小计");   
		//F1 第F列的第1行: N9 到第N列的第9行  求和
		document.forms(0).Cell1.SetFormula (6, totalLine-completeline+2, pageCurrent, "Sum(F4:F"+(totalLine-(completeline-1))+")" );
		document.forms(0).Cell1.SetFormula (7, totalLine-completeline+2, pageCurrent, "Sum(G4:G"+(totalLine-(completeline-1))+")" );
		document.forms(0).Cell1.SetFormula (8, totalLine-completeline+2, pageCurrent, "Sum(H4:H"+(totalLine-(completeline-1))+")" );
		document.forms(0).Cell1.SetFormula (9, totalLine-completeline+2, pageCurrent, "Sum(I4:I"+(totalLine-(completeline-1))+")" );
		document.forms(0).Cell1.SetFormula (10, totalLine-completeline+2, pageCurrent, "Sum(J4:J"+(totalLine-(completeline-1))+")" );
		document.forms(0).Cell1.SetFormula (11, totalLine-completeline+2, pageCurrent, "Sum(K4:K"+(totalLine-(completeline-1))+")" );
		document.forms(0).Cell1.SetFormula (12, totalLine-completeline+2, pageCurrent, "Sum(L4:L"+(totalLine-(completeline-1))+")" );
		document.forms(0).Cell1.SetFormula (13, totalLine-completeline+2, pageCurrent, "Sum(M4:M"+(totalLine-(completeline-1))+")" );
		document.forms(0).Cell1.SetFormula (14, totalLine-completeline+2, pageCurrent, "Sum(N4:N"+(totalLine-(completeline-1))+")" );
		document.forms(0).Cell1.S(1,totalLine-completeline+3,pageCurrent,"总计");
		document.forms(0).Cell1.d(6,totalLine-completeline+3,pageCurrent,occurMoneyTotle);
		document.forms(0).Cell1.d(7,totalLine-completeline+3,pageCurrent,reclaimCorpusTotle);
		document.forms(0).Cell1.d(8,totalLine-completeline+3,pageCurrent,reclaimAccrualTotle);
		document.forms(0).Cell1.d(9,totalLine-completeline+3,pageCurrent,realPunishInterestTotle);
		document.forms(0).Cell1.d(10,totalLine-completeline+3,pageCurrent,badDebtTotle);
		document.forms(0).Cell1.d(11,totalLine-completeline+3,pageCurrent,reclaimTotle);
		document.forms(0).Cell1.d(12,totalLine-completeline+3,pageCurrent,putUpMoneyTotle);
		document.forms(0).Cell1.d(13,totalLine-completeline+3,pageCurrent,bailTotle);
		document.forms(0).Cell1.d(14,totalLine-completeline+3,pageCurrent,bailAccrualTotle);
		document.forms(0).Cell1.S(9,totalLine-completeline+4,pageCurrent,"操作员");
		document.forms(0).Cell1.S(11,totalLine-completeline+4,pageCurrent,"<%=userName%>");
		document.forms(0).Cell1.S(12,totalLine-completeline+4,pageCurrent,"操作时间");
		document.forms(0).Cell1.S(15,totalLine-completeline+4,pageCurrent,"<%=plbizDate%>");
		document.forms(0).Cell1.DeleteRow(totalLine-completeline+5,46-(totalLine-completeline+4),pageCurrent);
		document.forms(0).Cell1.ReDraw();
	}
	document.forms(0).Cell1.PrintSetSheetOpt(3);
 				document.forms(0).Cell1.PrintSetPrintRange(1,0);
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

	<body onContextmenu="return false" onload="load();">
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
					<td>
						<input type="button" name="print" value="打印预览"
							onclick="printPreview();" />
					</td>
					<td>
						<INPUT id="Button1" onclick="Button1_onclick()" type="button"
							value="另存为Excel" name="Button1" />
					</td>
					<td>
						<INPUT id="Button1" onclick="Button2_onclick()" type="button"
							value="另存为pdf" name="Button1" />
					</td>
					<td>
						<INPUT id="Button3" style="WIDTH: 100px"
							onclick="Button3_onclick()" type="button" value="页面设置" />
					</td>
					<td>
						<INPUT id="Button3" style="WIDTH: 100px"
							onclick="Button4_onclick()" type="button" value="查找对话框" />
					</td>
					<td>
						<INPUT id="Button3" style="WIDTH: 100px"
							onclick="Button5_onclick()" type="button" value="excel导入" />
					</td>
					<td>
						<INPUT id="Button1" onclick="printsheet()" type="button"
							value=" 打印 " name="Button1" />
					</td>
					<td>
						<INPUT id="Button3" onclick="location.href='<%=url%>'"
							type="button" value=" 返回 " />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
