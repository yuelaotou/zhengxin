<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.util.List"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic"%>
<%@ page
	import="org.xpup.hafmis.sysloan.querystatistics.querystatistics.overduequery.dto.OverdueInfoDTO"%>

<%
	String path = request.getContextPath();
	List list = (List) request.getAttribute("printList");
	String bizDate = (String) request.getAttribute("bizDate");
	String bankName = (String) request.getAttribute("bankName");
%>
<html>
	<head>
		<script src="<%=path%>/js/common.js"></script>
	</head>
	<script type="text/javascript">
	function load(){	
	loginReg();
	document.forms(0).Cell1.openfile("<%=path%>/sysloan/querystatistics/report/overduequery.cll","");
	var contractId=[];
	var borrowerName=[];
	var loanKouAcc=[];	
	var orgName=[];
	var houseTel=[];
	var mobile=[];
	var orgTel=[];
	var astBorrowerName=[];
	var astOrgName=[];
	var astMobile=[];
	var loanMoney=[]
    var balance=[];
    var loanStartDate=[];
    var repayDate=[];
    var overdueMonths=[];
    var shouldCorpus=[];
    var shouldInterest=[];
    var shouldMoney=[];
    var punishInterest=[];
    var bankName=[];
    var shouldCorpusSum = 0;
    var shouldInterestSum = 0;
    var shouldMoneySum = 0;
    var punishInterestSum = 0;
    var bizdate = "<%=bizDate%>";
    bizdate = bizdate.substring(0,4)+"年"+bizdate.substring(4,6)+"月"+bizdate.substring(6,8)+"日";
	var i=0;
	<%	
		for(int j=0; j<list.size(); j++){
			OverdueInfoDTO dto = (OverdueInfoDTO) list.get(j);
	%>
			contractId[i] = "<%=dto.getContractId()%>";
			borrowerName[i] = "<%=dto.getBorrowerName()%>";
			loanKouAcc[i] = "<%=dto.getLoanKouAcc()%>";	
			orgName[i] = "<%=dto.getBorrowerOrgName()%>";
			houseTel[i] = "<%=dto.getBorrowerTel()%>";
			mobile[i] = "<%=dto.getBorrowerMobile()%>";
			orgTel[i] = "<%=dto.getBorrowerOrgTel()%>";
			astBorrowerName[i] = "<%=dto.getAstBorrowerName()%>";
			astOrgName[i] = "<%=dto.getAstBorrowerOrgName()%>";
			astMobile[i] = "<%=dto.getAstBorrowerMobile()%>";
			loanMoney[i] = "<%=dto.getLoanMoney()%>";
		    balance[i] = "<%=dto.getBalance()%>";
		    loanStartDate[i] = "<%=dto.getLoanStartDate()%>";
		    repayDate[i] = "<%=dto.getRepayMonth()%>";
		    overdueMonths[i] = "<%=dto.getOverdueMonths()%>";
		    shouldCorpus[i] = "<%=dto.getCorpus()%>";
		    shouldInterest[i] = "<%=dto.getInterest()%>";
		    shouldMoney[i] = "<%=dto.getShouldPayMoney()%>";
		    punishInterest[i] = "<%=dto.getPunishInterest()%>";
		    punishInterest[i] = "<%=dto.getPunishInterest()%>";
		    bankName[i] = "<%=dto.getLoanBank()%>";
			i++;
	<%
		}
	%>
	var totalLine=contractId.length;		//总的行数 数组的长度
	var totalPageLine=29;					//每页显示多少行--除了第一行
	var pageTotal=totalLine/totalPageLine;	//总共分几页 总行数/每页的行数
	var pageCurrent=0;						//当前页
	var completeline=0;						//记录行的

	for(k = 0 ; k < totalLine; k++){
		if(k%totalPageLine==0&&k>0)
		{
			document.forms(0).Cell1.S(1,totalPageLine+5,pageCurrent,"本页小计");
			document.forms(0).Cell1.SetFormula (16, totalPageLine+5, pageCurrent, "Sum(P4:P"+(totalPageLine+4)+")" );
			document.forms(0).Cell1.SetFormula (17, totalPageLine+5, pageCurrent, "Sum(Q4:Q"+(totalPageLine+4)+")" );
			document.forms(0).Cell1.SetFormula (18, totalPageLine+5, pageCurrent, "Sum(R4:R"+(totalPageLine+4)+")" );
			document.forms(0).Cell1.SetFormula (19, totalPageLine+5, pageCurrent, "Sum(S4:S"+(totalPageLine+4)+")" );
			pageCurrent++;//当前页++	
			completeline=k-2;
			document.forms(0).Cell1.insertSheetFromFile("<%=path%>/sysloan/querystatistics/report/overduequery.cll",0,1,pageCurrent);
			document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页")	
		}
		if(pageCurrent==0)
		{	
			document.forms(0).Cell1.S(4,3,0,"<%=bankName%>");
			document.forms(0).Cell1.S(17,3,0,bizdate);
			document.forms(0).Cell1.S(1,k+5,0,contractId[k]);
			document.forms(0).Cell1.S(2,k+5,0,borrowerName[k]);
			document.forms(0).Cell1.S(3,k+5,0,orgName[k]);
			document.forms(0).Cell1.S(4,k+5,0,loanKouAcc[k]);
			document.forms(0).Cell1.S(5,k+5,0,houseTel[k]);
			document.forms(0).Cell1.S(6,k+5,0,mobile[k]);
			document.forms(0).Cell1.S(7,k+5,0,orgTel[k]);
			document.forms(0).Cell1.S(8,k+5,0,astBorrowerName[k]);
			document.forms(0).Cell1.S(9,k+5,0,astOrgName[k]);
			document.forms(0).Cell1.S(10,k+5,0,astMobile[k]);
			document.forms(0).Cell1.d(11,k+5,0,loanMoney[k]);
			document.forms(0).Cell1.d(12,k+5,0,balance[k]);
			document.forms(0).Cell1.S(13,k+5,0,loanStartDate[k]);
			document.forms(0).Cell1.S(14,k+5,0,repayDate[k]);
			document.forms(0).Cell1.S(15,k+5,0,overdueMonths[k]);
			document.forms(0).Cell1.d(16,k+5,0,shouldCorpus[k]);
			document.forms(0).Cell1.d(17,k+5,0,shouldInterest[k]);
			document.forms(0).Cell1.d(18,k+5,0,shouldMoney[k]);
			document.forms(0).Cell1.d(19,k+5,0,punishInterest[k]);
			//document.forms(0).Cell1.S(20,k+5,0,bankName[k]);
			
			shouldCorpusSum += parseFloat(shouldCorpus[k]);
			shouldInterestSum += parseFloat(shouldInterest[k]);
			shouldMoneySum += parseFloat(shouldMoney[k]);
			punishInterestSum += parseFloat(punishInterest[k]);
			

		}else{//向第一页后的所有页插数据
			document.forms(0).Cell1.S(4,3,pageCurrent,"<%=bankName%>");
			document.forms(0).Cell1.S(17,3,pageCurrent,bizdate);
			document.forms(0).Cell1.S(1,k-completeline+3,pageCurrent,contractId[k]);
			document.forms(0).Cell1.S(2,k-completeline+3,pageCurrent,borrowerName[k]);
			document.forms(0).Cell1.S(3,k-completeline+3,pageCurrent,orgName[k]);
			document.forms(0).Cell1.S(4,k-completeline+3,pageCurrent,loanKouAcc[k]);
			document.forms(0).Cell1.S(5,k-completeline+3,pageCurrent,houseTel[k]);
			document.forms(0).Cell1.S(6,k-completeline+3,pageCurrent,mobile[k]);
			document.forms(0).Cell1.S(7,k-completeline+3,pageCurrent,orgTel[k]);
			document.forms(0).Cell1.S(8,k-completeline+3,pageCurrent,astBorrowerName[k]);
			document.forms(0).Cell1.S(9,k-completeline+3,pageCurrent,astOrgName[k]);
			document.forms(0).Cell1.S(10,k-completeline+3,pageCurrent,astMobile[k]);
			document.forms(0).Cell1.d(11,k-completeline+3,pageCurrent,loanMoney[k]);
			document.forms(0).Cell1.d(12,k-completeline+3,pageCurrent,balance[k]);
			document.forms(0).Cell1.S(13,k-completeline+3,pageCurrent,loanStartDate[k]);
			document.forms(0).Cell1.S(14,k-completeline+3,pageCurrent,repayDate[k]);
			document.forms(0).Cell1.S(15,k-completeline+3,pageCurrent,overdueMonths[k]);
			document.forms(0).Cell1.d(16,k-completeline+3,pageCurrent,shouldCorpus[k]);
			document.forms(0).Cell1.d(17,k-completeline+3,pageCurrent,shouldInterest[k]);
			document.forms(0).Cell1.d(18,k-completeline+3,pageCurrent,shouldMoney[k]);
			document.forms(0).Cell1.d(19,k-completeline+3,pageCurrent,punishInterest[k]);
			//document.forms(0).Cell1.S(20,k-completeline+3,pageCurrent,bankName[k]);
			
			shouldCorpusSum += parseFloat(shouldCorpus[k]);
			shouldInterestSum += parseFloat(shouldInterest[k]);
			shouldMoneySum += parseFloat(shouldMoney[k]);
			punishInterestSum += parseFloat(punishInterest[k]);
		}		
	}
	if(completeline==0)//查询出来的记录可以在一页显示的时候会进入
	{
		document.forms(0).Cell1.S(1,totalLine+5,pageCurrent,"本页小计");
		document.forms(0).Cell1.SetFormula (16, totalLine+5, pageCurrent, "Sum(P4:P"+(totalLine+4)+")" );
		document.forms(0).Cell1.SetFormula (17, totalLine+5, pageCurrent, "Sum(Q4:Q"+(totalLine+4)+")" );
		document.forms(0).Cell1.SetFormula (18, totalLine+5, pageCurrent, "Sum(R4:R"+(totalLine+4)+")" );
		document.forms(0).Cell1.SetFormula (19, totalLine+5, pageCurrent, "Sum(S4:S"+(totalLine+4)+")" );
		
		document.forms(0).Cell1.S(1,totalLine+6,pageCurrent,"总计");
		document.forms(0).Cell1.S(2,totalLine+6,pageCurrent,totalLine + "户");
		document.forms(0).Cell1.d(16,totalLine+6,pageCurrent,shouldCorpusSum);
		document.forms(0).Cell1.d(17,totalLine+6,pageCurrent,shouldInterestSum);
		document.forms(0).Cell1.d(18,totalLine+6,pageCurrent,shouldMoneySum);		
		document.forms(0).Cell1.d(19,totalLine+6,pageCurrent,punishInterestSum);		
		document.forms(0).Cell1.DeleteRow(totalLine+7,34-totalLine-6,pageCurrent);
		document.forms(0).Cell1.ReDraw();
		
	}	
	else
	{
		document.forms(0).Cell1.S(1,totalLine-completeline+3,pageCurrent,"本页小计");
		document.forms(0).Cell1.SetFormula (16, totalLine-completeline+3, pageCurrent, "Sum(P4:P"+(totalLine-(completeline-2))+")" );
		document.forms(0).Cell1.SetFormula (17, totalLine-completeline+3, pageCurrent, "Sum(Q4:Q"+(totalLine-(completeline-2))+")" );
		document.forms(0).Cell1.SetFormula (18, totalLine-completeline+3, pageCurrent, "Sum(R4:R"+(totalLine-(completeline-2))+")" );
		document.forms(0).Cell1.SetFormula (19, totalLine-completeline+3, pageCurrent, "Sum(S4:S"+(totalLine-(completeline-2))+")" );
		
		document.forms(0).Cell1.S(1,totalLine-completeline+4,pageCurrent,"总计");
		document.forms(0).Cell1.S(2,totalLine-completeline+4,pageCurrent,totalLine + "户");
		document.forms(0).Cell1.d(16,totalLine-completeline+4,pageCurrent,shouldCorpusSum);
		document.forms(0).Cell1.d(17,totalLine-completeline+4,pageCurrent,shouldInterestSum);
		document.forms(0).Cell1.d(18,totalLine-completeline+4,pageCurrent,shouldMoneySum);		
		document.forms(0).Cell1.d(19,totalLine-completeline+4,pageCurrent,punishInterestSum);
		document.forms(0).Cell1.DeleteRow(totalLine-completeline+5,30-(totalLine-completeline),pageCurrent);
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

	<body onload="load();">
		<form action="">
			<table align="center">
				<tr>
					<OBJECT id=Cell1
						style="LEFT:0px;WIDTH:900px;  TOP:0px;HEIGHT:500px"
						codeBase="http://192.168.1.44:8088/hafmis/CellWeb5.CAB#version=5,3,7,321"
						classid="clsid:3F166327-8030-4881-8BD2-EA25350E574A" VIEWASTEXT>
						<PARAM NAME="_Version" VALUE="65536">
						<PARAM NAME="_ExtentX" VALUE="10266">
						<PARAM NAME="_ExtentY" VALUE="7011">
						<PARAM NAME="_StockProps" VALUE="0">
					</OBJECT>
				</tr>
				<tr>
					<td align="center">
						<input type="button" name="print" value="打印预览"
							onclick="printPreview();" />
						<INPUT id="Button1" onclick="Button1_onclick()" type="button"
							value="另存为Excel" name="Button1" />
						<INPUT id="Button1" onclick="Button2_onclick()" type="button"
							value="另存为pdf" name="Button1" />
						<INPUT id="Button3" style="WIDTH: 100px"
							onclick="Button3_onclick()" type="button" value="页面设置" />
						<INPUT id="Button3" style="WIDTH: 100px"
							onclick="Button4_onclick()" type="button" value="查找对话框" />
						<INPUT id="Button3" style="WIDTH: 100px"
							onclick="Button5_onclick()" type="button" value="excel导入" />
						<INPUT id="Button1" onclick="printsheet()" type="button"
							value=" 打印 " name="Button1" />
						<INPUT id="Button3" onclick="javascript:history.back();"
							type="button" value=" 返回 " />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
