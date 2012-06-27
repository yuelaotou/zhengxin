<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.util.List"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic"%>
<%@ page
	import="org.xpup.hafmis.sysloan.querystatistics.querystatistics.loandeskaccquery.form.LoanDeskaccQueryTbAF"%>

<%@ page
	import="org.xpup.hafmis.sysloan.querystatistics.querystatistics.loandeskaccquery.dto.LoandeskaccqueryTbDTO"%>
<%@ include file="/checkUrl.jsp"%>
<%
String path = request.getContextPath();
%>
<html>
	<head>
	</head>
	<script src="<%=path%>/js/common.js">
</script>
	<script type="text/javascript">
function load(){	
	//加载默版文件..
	loginReg();
	document.forms(0).Cell1.openfile("<%=path%>/sysloan/querystatistics/report/loandeskaccquerytb1.cll","");
	document.forms(0).Cell1.insertSheetFromFile("<%=path%>/sysloan/querystatistics/report/particularglta1.cll",0,1,1);
    document.forms(0).Cell1.setSheetLabel(1,"第2页");
    document.forms(0).Cell1.PrintSetCustomPaper(2100,2970,1);
	document.forms(0).Cell1.PrintSetMargin(100,50,100,50);
	document.forms(0).Cell1.PrintSetAlign(1,0);
	
  	var bizdate=[];
  	var yearmonth=[];
  	var docnum=[];
  	var biztype=[];
  	var accordmoney=[];
  	var overloanmoney=[];
  	var baddebtmoney=[];
  	var bailmoney=[];
  	var shouldcorpus=[];
  	var shouldinterest=[];
  	var shouldpunishinterest=[];
  	var realcorpus=[];
  	var realinterest=[];
  	var realpunishinterest=[];
  	var sumMoney = [];
  	var loantype=[];
  	var batchNum=[];
  	var i=0;

	<%
			String opertname=(String)request.getAttribute("opertname");
			String time=(String)request.getAttribute("time");
    	    LoanDeskaccQueryTbAF loanDeskaccQueryTbAF =(LoanDeskaccQueryTbAF)request.getAttribute("loanDeskaccQueryTbAF");
            List taillist = loanDeskaccQueryTbAF.getAlllist();
	%>
			<%
  			for(int j=0;j<taillist.size();j++)
  			{
  				LoandeskaccqueryTbDTO dto=(LoandeskaccqueryTbDTO)taillist.get(j);
 	%>
				 bizdate[i]="<%=dto.getBizdate()%>";
				 yearmonth[i]="<%=dto.getYearmonth()%>";
				 docnum[i]="<%=dto.getDocnum()%>";
				 biztype[i]="<%=dto.getBiztype()%>";
				 accordmoney[i]="<%=dto.getAccordmoney()%>";
				 overloanmoney[i]="<%=dto.getOverloanmoney()%>";
				 baddebtmoney[i]="<%=dto.getBaddebtmoney()%>";
				 bailmoney[i]="<%=dto.getBailmoney()%>";
				 shouldcorpus[i]="<%=dto.getShouldcorpus()%>";
				 shouldinterest[i]="<%=dto.getShouldinterest()%>";
				 shouldpunishinterest[i]="<%=dto.getShouldpunishinterest()%>";
				 realcorpus[i]="<%=dto.getRealcorpus()%>";
				 realinterest[i]="<%=dto.getRealinterest()%>";
				 realpunishinterest[i]="<%=dto.getRealpunishinterest()%>";
				 sumMoney[i]="<%=dto.getSumCorpusInterest()%>";
				 loantype[i]="<%=dto.getLoantype()%>";
				 batchNum[i]="<%=dto.getBatchNum()%>";
			    
 	          	i++; 
 	<%
 			}
 	%>
	     	document.forms(0).Cell1.S(2,3,0,"<%=loanDeskaccQueryTbAF.getContractid()%>");
		    document.forms(0).Cell1.S(2,4,0,"<%=loanDeskaccQueryTbAF.getBorrowername()%>");
		    document.forms(0).Cell1.S(2,5,0,"<%=loanDeskaccQueryTbAF.getLoanbank()%>");
		    document.forms(0).Cell1.S(2,6,0,"<%=loanDeskaccQueryTbAF.getLoanlimit()%>");
		    document.forms(0).Cell1.d(2,7,0,"<%=loanDeskaccQueryTbAF.getOverplusloanmoney()%>");
		    document.forms(0).Cell1.d(4,7,0,"<%=loanDeskaccQueryTbAF.getOveaerloanrepay()%>");
		    document.forms(0).Cell1.d(2,9,0,"<%=loanDeskaccQueryTbAF.getSrealcorpus()%>");
		    document.forms(0).Cell1.d(2,10,0,"<%=loanDeskaccQueryTbAF.getSrealpunishinterest()%>");
		    document.forms(0).Cell1.d(2,11,0,"<%=loanDeskaccQueryTbAF.getOweinterest()%>");
		    document.forms(0).Cell1.S(2,13,0,"<%=opertname%>");
		    
	     	document.forms(0).Cell1.S(4,3,0,"<%=loanDeskaccQueryTbAF.getLoankouacc()%>");
	      	document.forms(0).Cell1.S(4,4,0,"<%=loanDeskaccQueryTbAF.getCardnum()%>");
	      	document.forms(0).Cell1.d(4,5,0,"<%=loanDeskaccQueryTbAF.getLoanmoney()%>");
	      	document.forms(0).Cell1.S(4,6,0,"<%=loanDeskaccQueryTbAF.getLoanmode()%>");
	      	document.forms(0).Cell1.S(2,8,0,"<%=loanDeskaccQueryTbAF.getOverlimited()%>");
	      	document.forms(0).Cell1.d(2,12,0,"<%=loanDeskaccQueryTbAF.getCorputInterest()%>");
	      	document.forms(0).Cell1.S(4,8,0,"<%=loanDeskaccQueryTbAF.getOtherArrearage()%>");
	      	document.forms(0).Cell1.d(4,9,0,"<%=loanDeskaccQueryTbAF.getSrealinterest()%>");
	     	document.forms(0).Cell1.d(4,10,0,"<%=loanDeskaccQueryTbAF.getOwercorpus()%>");
	      	document.forms(0).Cell1.d(4,11,0,"<%=loanDeskaccQueryTbAF.getOwepunishinterest()%>");
	      	document.forms(0).Cell1.d(4,12,0,"<%=loanDeskaccQueryTbAF.getShouldCorputInterest()%>");
	      	document.forms(0).Cell1.S(4,13,0,"<%=time%>");
		     
    	var totalLine=bizdate.length;			//总的行数 数组的长度
		var totalPageLine=40;					//每页显示多少行
		var pageTotal=totalLine/totalPageLine;	//总共分几页
		var pageCurrent=1;						//当前页
		var completeline=0;						//记录行的
		
		var moneytotal1=0;
		var moneytotal2=0;
		var moneytotal3=0;
		var moneytotal4=0;
		var moneytotal5=0;
		var moneytotal6=0;
		var moneytotal7=0;		
		var moneytotal8=0;
		var moneytotal9=0;
		var moneytotal10=0;
		var moneytotal11=0;
		
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

				document.forms(0).Cell1.S(9,47,1,"<%=opertname%>");
				document.forms(0).Cell1.S(11,47,1,"<%=time%>");
				
				pageCurrent++;	
				completeline=k-2;				
				document.forms(0).Cell1.insertSheetFromFile("<%=path%>/sysloan/querystatistics/report/particularglta1.cll",0,1,pageCurrent);
				document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页")	
				document.forms(0).Cell1.PrintSetCustomPaper(3400,2800,1);
				document.forms(0).Cell1.PrintSetMargin(100,100,100,100);
				document.forms(0).Cell1.PrintSetAlign(1,0);
			}
			if(pageCurrent==1)
			{		
				document.forms(0).Cell1.S(5,2,1,"<%=loanDeskaccQueryTbAF.getContractid()%>");
		    	document.forms(0).Cell1.S(10,2,1,"<%=loanDeskaccQueryTbAF.getBorrowername()%>");
				document.forms(0).Cell1.S(1,k+4,1,bizdate[k]);
				document.forms(0).Cell1.S(2,k+4,1,yearmonth[k]);
				document.forms(0).Cell1.S(3,k+4,1,docnum[k]);
				document.forms(0).Cell1.S(4,k+4,1,biztype[k]);
				document.forms(0).Cell1.d(5,k+4,1,accordmoney[k]);
				document.forms(0).Cell1.d(6,k+4,1,overloanmoney[k]);
				document.forms(0).Cell1.d(7,k+4,1,realcorpus[k]);
				document.forms(0).Cell1.d(8,k+4,1,realinterest[k]);
				document.forms(0).Cell1.d(9,k+4,1,realpunishinterest[k]);
				document.forms(0).Cell1.d(10,k+4,1,sumMoney[k]);
				document.forms(0).Cell1.S(11,k+4,1,loantype[k]);
				document.forms(0).Cell1.S(12,k+4,1,batchNum[k]);
				
				moneytotal1=moneytotal1+parseFloat(accordmoney[k]);
				moneytotal2=moneytotal2+parseFloat(overloanmoney[k]);
				moneytotal8=moneytotal8+parseFloat(realcorpus[k]);
				moneytotal9=moneytotal9+parseFloat(realinterest[k]);
				moneytotal10=moneytotal10+parseFloat(realpunishinterest[k]);
				moneytotal11=moneytotal11+parseFloat(sumMoney[k]);
				
			}else{//向第一页后的所有页插数据
				document.forms(0).Cell1.S(5,2,pageCurrent,"<%=loanDeskaccQueryTbAF.getContractid()%>");
		    	document.forms(0).Cell1.S(10,2,pageCurrent,"<%=loanDeskaccQueryTbAF.getBorrowername()%>");
				document.forms(0).Cell1.S(1,k-completeline+2,pageCurrent,bizdate[k]);
				document.forms(0).Cell1.S(2,k-completeline+2,pageCurrent,yearmonth[k]);
				document.forms(0).Cell1.S(3,k-completeline+2,pageCurrent,docnum[k]);
				document.forms(0).Cell1.S(4,k-completeline+2,pageCurrent,biztype[k]);
				document.forms(0).Cell1.d(5,k-completeline+2,pageCurrent,accordmoney[k]);
				document.forms(0).Cell1.d(6,k-completeline+2,pageCurrent,overloanmoney[k]);
				document.forms(0).Cell1.d(7,k-completeline+2,pageCurrent,realcorpus[k]);
				document.forms(0).Cell1.d(8,k-completeline+2,pageCurrent,realinterest[k]);
				document.forms(0).Cell1.d(9,k-completeline+2,pageCurrent,realpunishinterest[k]);
				document.forms(0).Cell1.d(10,k-completeline+2,pageCurrent,sumMoney[k]);
				document.forms(0).Cell1.S(11,k-completeline+2,pageCurrent,loantype[k]);
				document.forms(0).Cell1.S(12,k-completeline+2,pageCurrent,batchNum[k]);
				
				moneytotal1=moneytotal1+parseFloat(accordmoney[k]);
				moneytotal2=moneytotal2+parseFloat(overloanmoney[k]);
				moneytotal8=moneytotal8+parseFloat(realcorpus[k]);
				moneytotal9=moneytotal9+parseFloat(realinterest[k]);
				moneytotal10=moneytotal10+parseFloat(realpunishinterest[k]);
				moneytotal11=moneytotal11+parseFloat(sumMoney[k]);
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
				document.forms(0).Cell1.d(5,totalLine+5,pageCurrent,moneytotal1);
				document.forms(0).Cell1.d(6,totalLine+5,pageCurrent,moneytotal2);
				document.forms(0).Cell1.d(7,totalLine+5,pageCurrent,moneytotal8);
				document.forms(0).Cell1.d(8,totalLine+5,pageCurrent,moneytotal9);
				document.forms(0).Cell1.d(9,totalLine+5,pageCurrent,moneytotal10);
				document.forms(0).Cell1.d(10,totalLine+5,pageCurrent,moneytotal11);
				document.forms(0).Cell1.S(9,47,pageCurrent,"<%=opertname%>");
				document.forms(0).Cell1.S(11,47,pageCurrent,"<%=time%>");
				document.forms(0).Cell1.DeleteRow(totalLine+6,46-totalLine-5,pageCurrent);
				document.forms(0).Cell1.ReDraw();
<%--					document.forms(0).Cell1.S(12,totalLine+6,pageCurrent,"操作员");--%>
<%--					document.forms(0).Cell1.S(13,totalLine+6,pageCurrent,"<%=opertname%>");--%>
<%--					document.forms(0).Cell1.S(14,totalLine+6,pageCurrent,"操作时间");--%>
<%--					document.forms(0).Cell1.S(15,totalLine+6,pageCurrent,"<%=time%>");--%>
				
<%--					document.forms(0).Cell1.DeleteRow(totalLine+7,50-totalLine-6,pageCurrent);--%>
<%--					document.forms(0).Cell1.ReDraw();--%>
			}
			else
			{
				document.forms(0).Cell1.S(1,totalLine-completeline+2,pageCurrent,"本页小计");
				document.forms(0).Cell1.SetFormula (5, totalLine-completeline+2, pageCurrent, "Sum(E4:E"+(totalLine-(completeline-1))+")");
				document.forms(0).Cell1.SetFormula (6, totalLine-completeline+2, pageCurrent, "Sum(F4:F"+(totalLine-(completeline-1))+")");
				document.forms(0).Cell1.SetFormula (7, totalLine-completeline+2, pageCurrent, "Sum(G4:G"+(totalLine-(completeline-1))+")");
				document.forms(0).Cell1.SetFormula (8, totalLine-completeline+2, pageCurrent, "Sum(H4:H"+(totalLine-(completeline-1))+")");
				document.forms(0).Cell1.SetFormula (9, totalLine-completeline+2, pageCurrent, "Sum(I4:I"+(totalLine-(completeline-1))+")");
				document.forms(0).Cell1.SetFormula (10, totalLine-completeline+2, pageCurrent, "Sum(J4:J"+(totalLine-(completeline-1))+")");
			
				document.forms(0).Cell1.S(1,totalLine-completeline+3,pageCurrent,"总计");
				document.forms(0).Cell1.d(5,totalLine-completeline+3,pageCurrent,moneytotal1);
				document.forms(0).Cell1.d(6,totalLine-completeline+3,pageCurrent,moneytotal2);
				document.forms(0).Cell1.d(7,totalLine-completeline+3,pageCurrent,moneytotal8);
				document.forms(0).Cell1.d(8,totalLine-completeline+3,pageCurrent,moneytotal9);
				document.forms(0).Cell1.d(9,totalLine-completeline+3,pageCurrent,moneytotal10);
				document.forms(0).Cell1.d(10,totalLine-completeline+3,pageCurrent,moneytotal11);
				document.forms(0).Cell1.S(9,47,pageCurrent,"<%=opertname%>");
				document.forms(0).Cell1.S(11,47,pageCurrent,"<%=time%>");
				document.forms(0).Cell1.DeleteRow(totalLine-completeline+4,46-(totalLine-completeline+3),pageCurrent);
				document.forms(0).Cell1.ReDraw();
				
<%--					document.forms(0).Cell1.S(12,totalLine-completeline+4,pageCurrent,"操作员");--%>
<%--					document.forms(0).Cell1.S(13,totalLine-completeline+4,pageCurrent,"<%=opertname%>");--%>
<%--					document.forms(0).Cell1.S(14,totalLine-completeline+4,pageCurrent,"操作时间");--%>
<%--					document.forms(0).Cell1.S(15,totalLine-completeline+4,pageCurrent,"<%=time%>");--%>
<%--				    document.forms(0).Cell1.DeleteRow(totalLine-completeline+5,50-(totalLine-completeline+4),pageCurrent);--%>
<%--					document.forms(0).Cell1.ReDraw();--%>
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
function hehe()
{ 
	var contractIdHL="<%=loanDeskaccQueryTbAF.getContractid()%>";
	location='<%=path%>/sysloan/loanDeskaccQueryTbForwardAC.do?contractIdHl='+contractIdHL;
}
</script>
	<body onload="load();">
		<form action="">
			<table align="center">
				<tr>
					<OBJECT id=Cell1
						style="LEFT:0px;WIDTH:800px;  TOP:0px;HEIGHT:400px"
						codeBase="http://192.168.1.44:8088/hafmis/CellWeb5.CAB#version=5,3,7,321"
						classid="clsid:3F166327-8030-4881-8BD2-EA25350E574A" VIEWASTEXT>
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
						<INPUT id="Button1" onclick="printsheet()" type="button"
							value=" 打印 " name="Button1">
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
						<INPUT id="Button3" onclick="javascript:history.back();" type="button" value=" 返回 ">
					</td>
			</table>
		</form>
	</body>
</html>
