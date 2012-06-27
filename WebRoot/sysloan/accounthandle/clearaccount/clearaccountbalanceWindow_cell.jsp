<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic"%>
<%@ page
	import="org.xpup.hafmis.sysloan.accounthandle.clearaccount.dto.ClearaccountDTO"%>
<%@ page import="java.util.List"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	String bizDate = (String) request.getAttribute("bizDate");
	String makebill = (String) request.getAttribute("makebill");
	String checkpsn = (String) request.getAttribute("checkpsn");
	String clearpsn = (String) request.getAttribute("clearpsn");
	
	String endDate = (String) request.getAttribute("endDate");
	String startDate = (String) request.getAttribute("startDate");
	String loanBankName = (String) request.getAttribute("loanBankName");
	String url = (String) request.getAttribute("URL");
	if (url == null) {
		url = "javascript:history.back();";
	}
%>
<html>
	<head>
		<script src="<%=path%>/js/common.js">
</script>
	</head>
	<script type="text/javascript">
	function getInt(i,k) { 
		var page=0; 
		var j; 
		j=Math.round(i/k)-i/k; 
		if (j>=0) 
		page=Math.round(i/k)-1; 
		if (j<=0) 
		page=Math.round(i/k); 
		if(j!=0)
		page=page+1;
		if(j==0)
		page=i/k;
		return page; 
	}
	function load(){	
	loginReg();
	document.forms(0).Cell1.openfile("<%=path%>/sysloan/accounthandle/report/clearaccountbalancewindow_1.cll","");
	var docNum=[]; 
    var contractId=[];
    var nameList=[];
	var bizType=[];
	var occurMoney=[];
	var sumReclaimMoney=[];
	var badDebt=[];
	var putUpMoney=[];
	var bail=[];
	var bizDate=[];
	var bizSt=[];
	var realCorpus = [];
	var realInterest = [];
	var realPunishInterest = [];
	var loanBankName = [];
	var makeBillPerson = [];
	var checkPerson = [];
	var clearPerson = [];
	var isBack = [];
	var i=0;

	<%		
			String pdate=(String)request.getAttribute("bizDate");
    	    List list =(List)request.getAttribute("printlist");
	%>
			<%
  			for(int j=0;j<list.size();j++)
  			{
  				ClearaccountDTO dto=(ClearaccountDTO)list.get(j);
  				
 	%>
 				 docNum[i]="<%=dto.getDocNum()%>";
 				 if(docNum[i]=="null"){
 				 	docNum[i]="";
 				 }
			     contractId[i]="<%=dto.getContractId()%>";
			     if(contractId[i]=="null"){
			     	contractId[i]="";
			     }
			     nameList[i]="<%=dto.getBorrowerName()%>";
			     if(nameList[i]=="null"){
			     	nameList[i]="";
			     }
				 bizType[i]="<%=dto.getBizType()%>";
				 occurMoney[i]="<%=dto.getOccurMoney()%>";
				 if(occurMoney[i]=="null"||occurMoney[i]==""){
				 	occurMoney[i]="0";
				 }
				 sumReclaimMoney[i]="<%=dto.getReclaimCorpus()%>";
				 if(sumReclaimMoney[i]=="null" || sumReclaimMoney[i]==""){
				 	sumReclaimMoney[i]="0";
				 }
				 badDebt[i]="<%=dto.getBadDebt()%>";
				 if(badDebt[i]=="null" || badDebt[i]==""){
				 	badDebt[i]="0";
				 }
				 putUpMoney[i]="<%=dto.getPutUpMoney()%>";
				 if(putUpMoney[i]=="null" || putUpMoney[i]==""){
				 	putUpMoney[i]="0";
				 }
				 bail[i]="<%=dto.getBail()%>";
				 if(bail[i]=="null" || bail[i]==""){
				 	bail[i]="0";
				 }
				 bizDate[i]="<%=dto.getBizDate()%>";
				 bizSt[i]="<%=dto.getBizSt()%>";   
				 realCorpus[i]="<%=dto.getSumReclaimMoney()%>";
				 if(realCorpus[i]=="null"||realCorpus[i]==""){
				 	realCorpus[i]="0";
				 }
				 realInterest[i]="<%=dto.getReclaimAccrual()%>";
				 if(realInterest[i]=="null"||realInterest[i]==""){
				 	realInterest[i]="0";
				 }
				 realPunishInterest[i]="<%=dto.getRealPunish_interest()%>";
				 if(realPunishInterest[i]=="null"||realPunishInterest[i]==""){
				 	realPunishInterest[i]="0";
				 }
				 loanBankName[i]="<%=dto.getLoanBank()%>";
				 isBack[i]="<%=dto.getIsGjjLoanBack()%>";
 	          	i++; 
 	<%
 			}
 	%>		     
	    var totalLine=nameList.length;			//总的行数 数组的长度
		var totalPageLine=32;					//每页显示多少行
		var pageTotal=totalLine/totalPageLine;	//总共分几页
		var pageCurrent=0;						//当前页
		var completeline=0;						//记录行的
		var sumOccurMoney=0;
		var sumCallbackMoney=0;	
		var sumBadDebt=0;
		var sumPutUpMoney=0;
		var sumBail=0;
		var realCorpusTotal=0;
		var realInterestTotal=0;
		var realPunishInterestTotal=0;
		var iPage = getInt(totalLine,totalPageLine);
		var temp_page = 1;
			
		for(k = 0 ; k < totalLine; k++){
			if(k%totalPageLine==0&&k>0)
			{      
				document.forms(0).Cell1.S(1,totalPageLine+4,pageCurrent,"本页小计");
				document.forms(0).Cell1.SetFormula (5, totalPageLine+4, pageCurrent, "Sum(E4:E"+(totalPageLine+3)+")" );
				document.forms(0).Cell1.SetFormula (6, totalPageLine+4, pageCurrent, "Sum(F4:F"+(totalPageLine+3)+")" );
				document.forms(0).Cell1.SetFormula (7, totalPageLine+4, pageCurrent, "Sum(G4:G"+(totalPageLine+3)+")" );
				document.forms(0).Cell1.SetFormula (8, totalPageLine+4, pageCurrent, "Sum(H4:H"+(totalPageLine+3)+")" );
				document.forms(0).Cell1.SetFormula (9, totalPageLine+4, pageCurrent, "Sum(I4:I"+(totalPageLine+3)+")" );
				document.forms(0).Cell1.SetFormula (10, totalPageLine+4, pageCurrent, "Sum(J4:J"+(totalPageLine+3)+")" );
				pageCurrent++;	
				completeline=k-2;		
				temp_page = temp_page +	1;				
				document.forms(0).Cell1.insertSheetFromFile("<%=path%>/sysloan/accounthandle/report/clearaccountbalancewindow_1.cll",0,1,pageCurrent);
				document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页");
			}
			if(pageCurrent==0)
			{
				document.forms(0).Cell1.S(11,2,0,"第"+temp_page+"/"+iPage+"页");
				document.forms(0).Cell1.S(2,2,0,"<%=loanBankName%>");
				document.forms(0).Cell1.S(6,2,0,"<%=startDate%>"+"-"+"<%=endDate%>");
				document.forms(0).Cell1.S(1,k+4,0,docNum[k]);
				document.forms(0).Cell1.S(2,k+4,0,contractId[k]);
				document.forms(0).Cell1.S(3,k+4,0,nameList[k]);
				document.forms(0).Cell1.S(4,k+4,0,bizType[k]);
				document.forms(0).Cell1.d(5,k+4,0,occurMoney[k]);
				document.forms(0).Cell1.d(6,k+4,0,sumReclaimMoney[k]);
				//document.forms(0).Cell1.d(7,k+4,0,badDebt[k]);
				document.forms(0).Cell1.d(9,k+4,0,realCorpus[k]);
				document.forms(0).Cell1.d(7,k+4,0,realInterest[k]);
				document.forms(0).Cell1.d(8,k+4,0,realPunishInterest[k]);
				document.forms(0).Cell1.d(10,k+4,0,putUpMoney[k]);
				document.forms(0).Cell1.s(11,k+4,0,isBack[k]);
				sumOccurMoney=sumOccurMoney+parseFloat(occurMoney[k]);
				sumCallbackMoney=sumCallbackMoney+parseFloat(sumReclaimMoney[k]);
				sumBadDebt=sumBadDebt+parseFloat(badDebt[k]);
				sumPutUpMoney=sumPutUpMoney+parseFloat(putUpMoney[k]);
				sumBail=sumBail+parseFloat(bail[k]);
				realCorpusTotal=realCorpusTotal+parseFloat(realCorpus[k]);
				realInterestTotal=realInterestTotal+parseFloat(realInterest[k]);
				realPunishInterestTotal=realPunishInterestTotal+parseFloat(realPunishInterest[k]);
			}else{//向第一页后的所有页插数据
				document.forms(0).Cell1.S(11,2,pageCurrent,"第"+temp_page+"/"+iPage+"页");
				document.forms(0).Cell1.S(2,2,pageCurrent,"<%=loanBankName%>");
				document.forms(0).Cell1.S(6,2,pageCurrent,"<%=startDate%>"+"-"+"<%=endDate%>");
			    document.forms(0).Cell1.S(1,k-completeline+2,pageCurrent,docNum[k]);
				document.forms(0).Cell1.S(2,k-completeline+2,pageCurrent,contractId[k]);
				document.forms(0).Cell1.S(3,k-completeline+2,pageCurrent,nameList[k]);
				document.forms(0).Cell1.s(4,k-completeline+2,pageCurrent,bizType[k]);
				document.forms(0).Cell1.d(5,k-completeline+2,pageCurrent,occurMoney[k]);
				document.forms(0).Cell1.d(6,k-completeline+2,pageCurrent,sumReclaimMoney[k]);
				//document.forms(0).Cell1.d(7,k-completeline+2,pageCurrent,badDebt[k]);
				document.forms(0).Cell1.d(9,k-completeline+2,pageCurrent,realCorpus[k]);
				document.forms(0).Cell1.d(7,k-completeline+2,pageCurrent,realInterest[k]);
				document.forms(0).Cell1.d(8,k-completeline+2,pageCurrent,realPunishInterest[k]);
				document.forms(0).Cell1.d(10,k-completeline+2,pageCurrent,putUpMoney[k]);
				document.forms(0).Cell1.S(11,k-completeline+2,pageCurrent,isBack[k]);
				sumOccurMoney=sumOccurMoney+parseFloat(occurMoney[k]);
				sumCallbackMoney=sumCallbackMoney+parseFloat(sumReclaimMoney[k]);
				sumBadDebt=sumBadDebt+parseFloat(badDebt[k]);
				sumPutUpMoney=sumPutUpMoney+parseFloat(putUpMoney[k]);
				sumBail=sumBail+parseFloat(bail[k]);
				realCorpusTotal=realCorpusTotal+parseFloat(realCorpus[k]);
				realInterestTotal=realInterestTotal+parseFloat(realInterest[k]);
				realPunishInterestTotal=realPunishInterestTotal+parseFloat(realPunishInterest[k]);
			}
		}
				
		if(completeline==0)//查询出来的记录可以在一页显示的时候会进入
		{
			document.forms(0).Cell1.S(1,totalLine+4,pageCurrent,"本页小计");
			document.forms(0).Cell1.SetFormula (5, totalLine+4, pageCurrent, "Sum(E4:E"+(totalLine+3)+")" );
			document.forms(0).Cell1.SetFormula (6, totalLine+4, pageCurrent, "Sum(F4:F"+(totalLine+3)+")" );
			document.forms(0).Cell1.SetFormula (7, totalLine+4, pageCurrent, "Sum(G4:G"+(totalLine+3)+")" );
			document.forms(0).Cell1.SetFormula (8, totalLine+4, pageCurrent, "Sum(H4:H"+(totalLine+3)+")" );
			document.forms(0).Cell1.SetFormula (9, totalLine+4, pageCurrent, "Sum(I4:I"+(totalLine+3)+")" );
			document.forms(0).Cell1.SetFormula (10, totalLine+4, pageCurrent, "Sum(J4:J"+(totalLine+3)+")" );
			document.forms(0).Cell1.S(1,totalLine+5,pageCurrent,"总计");
		
			document.forms(0).Cell1.d(5,totalLine+5,pageCurrent,sumOccurMoney);
			document.forms(0).Cell1.d(6,totalLine+5,pageCurrent,sumCallbackMoney);
			//document.forms(0).Cell1.d(7,totalLine+5,pageCurrent,sumBadDebt);
			document.forms(0).Cell1.d(7,totalLine+5,pageCurrent,realInterestTotal);
			document.forms(0).Cell1.d(8,totalLine+5,pageCurrent,realPunishInterestTotal);
			document.forms(0).Cell1.d(9,totalLine+5,pageCurrent,realCorpusTotal);
			document.forms(0).Cell1.d(10,totalLine+5,pageCurrent,sumPutUpMoney);
			document.forms(0).Cell1.S(1,totalLine+6,pageCurrent,"制单人:");
			document.forms(0).Cell1.MergeCells(2, totalLine+6,3,totalLine+6);
			document.forms(0).Cell1.S(2,totalLine+6,pageCurrent,"<%=makebill%>");
			document.forms(0).Cell1.S(4,totalLine+6,pageCurrent,"复核人:");
			document.forms(0).Cell1.MergeCells(5, totalLine+6,6,totalLine+6);
			document.forms(0).Cell1.S(5,totalLine+6,pageCurrent,"<%=checkpsn%>");
			document.forms(0).Cell1.S(7,totalLine+6,pageCurrent,"记账人:");
			//document.forms(0).Cell1.MergeCells(8, totalLine+6,9,totalLine+6);
			document.forms(0).Cell1.S(8,totalLine+6,pageCurrent,"<%=clearpsn%>");
			document.forms(0).Cell1.S(9,totalLine+6,pageCurrent,"打印日期:");
			document.forms(0).Cell1.MergeCells(10, totalLine+6,11,totalLine+6);
			document.forms(0).Cell1.S(10,totalLine+6,pageCurrent,"<%=bizDate%>");
			document.forms(0).Cell1.DrawGridLine(1,totalLine+6,11,totalLine+7,2,1,-1);//去左框线
			document.forms(0).Cell1.DrawGridLine(1,totalLine+6,11,totalLine+7,3,1,-1);//去右框线
			document.forms(0).Cell1.DrawGridLine(1,totalLine+6,11,totalLine+7,5,1,-1);//去下框线
			document.forms(0).Cell1.DeleteRow(totalLine+7,totalPageLine-(totalLine),pageCurrent);
			document.forms(0).Cell1.ReDraw();
		}
		else
		{
			document.forms(0).Cell1.S(1,totalLine-completeline+2,pageCurrent,"本页小计");
			document.forms(0).Cell1.SetFormula (5, totalLine-completeline+2, pageCurrent, "Sum(E4:E"+(totalLine-completeline+1)+")" );
			document.forms(0).Cell1.SetFormula (6, totalLine-completeline+2, pageCurrent, "Sum(F4:F"+(totalLine-completeline+1)+")" );
			//document.forms(0).Cell1.SetFormula (7, totalLine+4, pageCurrent, "Sum(G4:G"+(totalLine+3)+")" );
			document.forms(0).Cell1.SetFormula (7, totalLine-completeline+2, pageCurrent, "Sum(G4:G"+(totalLine-completeline+1)+")" );
			document.forms(0).Cell1.SetFormula (8, totalLine-completeline+2, pageCurrent, "Sum(H4:H"+(totalLine-completeline+1)+")" );
			document.forms(0).Cell1.SetFormula (9, totalLine-completeline+2, pageCurrent, "Sum(I4:I"+(totalLine-completeline+1)+")" );
			document.forms(0).Cell1.SetFormula (10, totalLine-completeline+2, pageCurrent, "Sum(J4:J"+(totalLine-completeline+1)+")" );
			//document.forms(0).Cell1.SetFormula (9, totalLine-completeline+2, pageCurrent, "Sum(I4:I"+(totalLine-(completeline+3))+")");
			document.forms(0).Cell1.S(1,totalLine-completeline+3,pageCurrent,"总计");
			
			document.forms(0).Cell1.d(5,totalLine-completeline+3,pageCurrent,sumOccurMoney);
			document.forms(0).Cell1.d(6,totalLine-completeline+3,pageCurrent,sumCallbackMoney);
			document.forms(0).Cell1.d(7,totalLine-completeline+3,pageCurrent,realInterestTotal);
			document.forms(0).Cell1.d(8,totalLine-completeline+3,pageCurrent,realPunishInterestTotal);
			document.forms(0).Cell1.d(9,totalLine-completeline+3,pageCurrent,realCorpusTotal);
			document.forms(0).Cell1.d(10,totalLine-completeline+3,pageCurrent,sumPutUpMoney);
			document.forms(0).Cell1.S(1,totalLine-completeline+4,pageCurrent,"制单人:");
			document.forms(0).Cell1.MergeCells(2, totalLine-completeline+4,3,totalLine-completeline+4);
			document.forms(0).Cell1.S(2,totalLine-completeline+4,pageCurrent,"<%=makebill%>");
			document.forms(0).Cell1.S(4,totalLine-completeline+4,pageCurrent,"复核人:");
			document.forms(0).Cell1.MergeCells(5, totalLine-completeline+4,6,totalLine-completeline+4);
			document.forms(0).Cell1.S(5,totalLine-completeline+4,pageCurrent,"<%=checkpsn%>");
			document.forms(0).Cell1.S(7,totalLine-completeline+4,pageCurrent,"记账人:");
			//document.forms(0).Cell1.MergeCells(8, totalLine-completeline+4,9,totalLine-completeline+4);
			document.forms(0).Cell1.S(8,totalLine-completeline+4,pageCurrent,"<%=clearpsn%>");
			document.forms(0).Cell1.S(9,totalLine-completeline+4,pageCurrent,"打印日期:");
			document.forms(0).Cell1.MergeCells(10, totalLine-completeline+4,11,totalLine-completeline+4);
			document.forms(0).Cell1.S(10,totalLine-completeline+4,pageCurrent,"<%=bizDate%>");
			document.forms(0).Cell1.DrawGridLine(1,totalLine-completeline+4,11,totalLine+7,2,1,-1);//去左框线
			document.forms(0).Cell1.DrawGridLine(1,totalLine-completeline+4,11,totalLine+7,3,1,-1);//去右框线
			document.forms(0).Cell1.DrawGridLine(1,totalLine-completeline+4,11,totalLine+7,5,1,-1);//去下框线
			document.forms(0).Cell1.DeleteRow(totalLine-completeline+5,totalPageLine-(totalLine-completeline-2),pageCurrent);
			
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
	<body onload="load();" onContextmenu="return false">
		<form action="">
			<table align="center">
				<tr>
					<OBJECT id=Cell1
						style="LEFT:0px;WIDTH:900px;  TOP:0px;HEIGHT:500px"
						codeBase="http://192.168.1.44:8088/hafmis/CellWeb5.CAB#version=5,3,7,321"
						classid=clsid:3F166327-8030-4881-8BD2-EA25350E574A VIEWASTEXT>
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
						<INPUT id="Button1" onclick="printsheet()" type="button"
							value=" 打印 " name="Button1">
						<INPUT id="Button1" onclick="Button1_onclick()" type="button"
							value="另存为Excel" name="Button1">
						<INPUT id="Button1" onclick="Button2_onclick()" type="button"
							value="另存为pdf" name="Button1">
						<INPUT id="Button3" style="WIDTH: 100px"
							onclick="Button3_onclick()" type="button" value="页面设置">
						<INPUT id="Button3" style="WIDTH: 100px"
							onclick="Button4_onclick()" type="button" value="查找对话框">
						<INPUT id="Button3" style="WIDTH: 100px"
							onclick="Button5_onclick()" type="button" value="excel导入">
						<INPUT id="Button3" onclick="location.href='<%=url%>'"
							type="button" value=" 返回 ">
					</td>
			</table>
		</form>
	</body>
</html>
