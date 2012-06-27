<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page
	import="org.xpup.hafmis.sysloan.querystatistics.querystatistics.queryPayOffRecords.dto.QueryPayOffRecordsTaListDTO"%>
 <%@ page
	import="org.xpup.hafmis.sysloan.querystatistics.querystatistics.queryPayOffRecords.form.QueryPayOffRecordsTaShowAF"%>
	
<%@ page import="java.util.List"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>凭证维护打印</title>

	</head>
	<script type="text/javascript">
  	function load(){
  		document.forms(0).Cell1.Login("沈阳金软科技有限公司","","13100104509","0740-1662-7775-3003"); 
	  	document.forms(0).Cell1.openfile("<%=path%>/sysloan/querystatistics/report/queryPayOffRecords.cll","");
 						  
	  	var contractId=[]; 
		var borrowerName=[]; 
	    var loanKouAcc=[]; 
	    var loanMoney=[]; 
	    var loanTimeLimit=[]; 
	    var loanStartDate=[]; 
	    var loanRepayDay=[]; 
	    var loanPayOffDate=[]; 
	    var interest=[]; 
	    var corpus=[]; 
 
	  	
	  	
	  	
	  	var i=0
     	
     <%
     	String bizDate=(String) request.getAttribute("bizDate");
    	String userName=(String) request.getAttribute("userName");
    	List celllist = (List) request.getSession().getAttribute("celllist");
    	QueryPayOffRecordsTaShowAF queryPayOffRecordsTaShowAF = (QueryPayOffRecordsTaShowAF) request.getSession().getAttribute("cellqueryPayOffRecordsTaShowAF");
        String loanMoneySum =queryPayOffRecordsTaShowAF.getLoanMoneySum().toString();// 贷款金额 ：
        String interestSum = queryPayOffRecordsTaShowAF.getInterestSum().toString();// 利息总额， ：
        String corpusSum = queryPayOffRecordsTaShowAF.getCorpusSum().toString();// 还款总额， ：
  
     
 
	    for(int j=0;j<celllist.size();j++){
			QueryPayOffRecordsTaListDTO dto=(QueryPayOffRecordsTaListDTO)celllist.get(j);
		 

 	%>
 	        contractId[i]="<%=dto.getContractId()%>";	
	  	    borrowerName[i]="<%=dto.getBorrowerName()%>";	
	  	    loanKouAcc[i]="<%=dto.getLoanKouAcc()%>";	
	  	    loanMoney[i]="<%=dto.getLoanMoney()%>";	
	  	    loanTimeLimit[i]="<%=dto.getLoanTimeLimit()%>";	
	  	    loanStartDate[i]="<%=dto.getLoanStartDate()%>";	
	        loanRepayDay[i]="<%=dto.getLoanRepayDay()%>";	
	  	    loanPayOffDate[i]="<%=dto.getLoanPayOffDate()%>";	
	        interest[i]="<%=dto.getInterest()%>";		  	
	        corpus[i]="<%=dto.getCorpus()%>";	

 
 	    	i++; 
	<%
 			}
 	%>
 		var totalLine=contractId.length;				//总的行数
		var totalPageLine=40;					//每页显示多少行
		var pageTotal=totalLine/totalPageLine;	//总共分几页
		var pageCurrent=0;						//当前页
		var completeline=0;						//记录行的
		
 
		document.forms(0).Cell1.InsertRow(4, totalLine+3, 0);
		for(k = 0 ; k < totalLine; k++){

<%--			if(k%totalPageLine==0&&k>0){--%>
<%--			--%>
<%----%>
<%--				--%>
<%--					document.forms(0).Cell1.S(1,totalPageLine+4,pageCurrent,"本页小计");--%>
<%--					document.forms(0).Cell1.SetFormula (3, totalPageLine+4, pageCurrent, "Sum(C4:C"+(totalPageLine+3)+")" );--%>
<%--					document.forms(0).Cell1.SetFormula (5, totalPageLine+4, pageCurrent, "Sum(E4:E"+(totalPageLine+3)+")" );--%>
<%--					document.forms(0).Cell1.SetFormula (6, totalPageLine+4, pageCurrent, "Sum(F4:F"+(totalPageLine+3)+")" );--%>
<%--				--%>
<%-- --%>
<%-- 			        document.forms(0).Cell1.S(1,totalPageLine+5,pageCurrent,"制表人:");				--%>
<%--		            document.forms(0).Cell1.S(2,totalPageLine+5,pageCurrent,"<%=userName%>");--%>
<%--		            document.forms(0).Cell1.S(3,totalPageLine+5,pageCurrent,"操作日期:");--%>
<%--		            document.forms(0).Cell1.S(4,totalPageLine+5,pageCurrent,"<%=bizDate%>");	--%>
<%----%>
<%--		--%>
<%--		      	document.forms(0).Cell1.DeleteRow(46,1,pageCurrent);--%>
<%--				document.forms(0).Cell1.ReDraw();--%>
<%--				pageCurrent++;	--%>
<%--				completeline=k-2;				--%>
<%--				document.forms(0).Cell1.insertSheetFromFile("<%=path%>/sysloan/querystatistics/report/queryPayOffRecords_1.cll",0,1,pageCurrent);--%>
<%--				document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页")	--%>
<%--			}--%>
			if(pageCurrent==0){
				
			   // document.forms(0).Cell1.S(1,k+4,0,loanKouAcc[k]);
			    document.forms(0).Cell1.S(1,k+4,0,contractId[k]);
				document.forms(0).Cell1.S(2,k+4,0,borrowerName[k]);
				document.forms(0).Cell1.D(3,k+4,0,loanMoney[k]);
 				document.forms(0).Cell1.S(4,k+4,0,loanTimeLimit[k]);
				document.forms(0).Cell1.D(5,k+4,0,interest[k]);
 
				document.forms(0).Cell1.D(6,k+4,0,corpus[k]);
				document.forms(0).Cell1.S(7,k+4,0,loanStartDate[k]);
 		        document.forms(0).Cell1.S(8,k+4,0,loanRepayDay[k]);
				document.forms(0).Cell1.S(9,k+4,0,loanPayOffDate[k]);
  

			}else{//向第一页后的所有页插数据
 
	           // document.forms(0).Cell1.S(1,k-completeline+2,pageCurrent,loanKouAcc[k]);
			    document.forms(0).Cell1.S(1,k-completeline+2,pageCurrent,contractId[k]);
				document.forms(0).Cell1.S(2,k-completeline+2,pageCurrent,borrowerName[k]);
				document.forms(0).Cell1.D(3,k-completeline+2,pageCurrent,loanMoney[k]);
 				document.forms(0).Cell1.S(4,k-completeline+2,pageCurrent,loanTimeLimit[k]);
				document.forms(0).Cell1.D(5,k-completeline+2,pageCurrent,interest[k]);
 
				document.forms(0).Cell1.D(6,k-completeline+2,pageCurrent,corpus[k]);
				document.forms(0).Cell1.S(7,k-completeline+2,pageCurrent,loanStartDate[k]);
 		        document.forms(0).Cell1.S(8,k-completeline+2,pageCurrent,loanRepayDay[k]);
				document.forms(0).Cell1.S(9,k-completeline+2,pageCurrent,loanPayOffDate[k]);
				
			}	
		}
		if(completeline==0)//查询出来的记录可以在一页显示的时候会进入
				{			
				
					document.forms(0).Cell1.S(1,totalLine+4,pageCurrent,"本页小计");
					document.forms(0).Cell1.SetFormula (3, totalLine+4, pageCurrent, "Sum(C4:C"+(totalLine+3)+")" );
					document.forms(0).Cell1.SetFormula (5, totalLine+4, pageCurrent, "Sum(E4:E"+(totalLine+3)+")" );
					document.forms(0).Cell1.SetFormula (6, totalLine+4, pageCurrent, "Sum(F4:F"+(totalLine+3)+")" );
					document.forms(0).Cell1.S(1,totalLine+5,pageCurrent,"合计");
					document.forms(0).Cell1.SetFormula (3, totalLine+5, pageCurrent, "<%=loanMoneySum%>" );
					document.forms(0).Cell1.SetFormula (5, totalLine+5, pageCurrent, "<%=interestSum%>");
					document.forms(0).Cell1.SetFormula (6, totalLine+5, pageCurrent, "<%=corpusSum%>");
					

			        document.forms(0).Cell1.S(1,totalLine+6,pageCurrent,"制表人:");				
		            document.forms(0).Cell1.S(2,totalLine+6,pageCurrent,"<%=userName%>");
		            document.forms(0).Cell1.S(3,totalLine+6,pageCurrent,"操作日期:");
		            document.forms(0).Cell1.S(4,totalLine+6,pageCurrent,"<%=bizDate%>");	
									
           
					document.forms(0).Cell1.ReDraw();
				}
				else
				{
 
					document.forms(0).Cell1.S(1,totalLine-completeline+2,pageCurrent,"本页小计");   	
					//F1 第F列的第1行: N9 到第N列的第9行  求和
					document.forms(0).Cell1.SetFormula (3, totalLine-completeline+2, pageCurrent, "Sum(C4:C"+(totalLine-(completeline-1))+")" );
					document.forms(0).Cell1.SetFormula (5, totalLine-completeline+2, pageCurrent, "Sum(E4:E"+(totalLine-(completeline-1))+")" );
					document.forms(0).Cell1.SetFormula (6, totalLine-completeline+2, pageCurrent, "Sum(F4:F"+(totalLine-(completeline-1))+")" );
					document.forms(0).Cell1.S(1,totalLine-completeline+3,pageCurrent,"合计");
					//F1 第F列的第1行: N9 到第N列的第9行  求和
					document.forms(0).Cell1.SetFormula (3, totalLine-completeline+3, pageCurrent,"<%=loanMoneySum%>" );
					document.forms(0).Cell1.SetFormula (5, totalLine-completeline+3, pageCurrent, "<%=interestSum%>");
					document.forms(0).Cell1.SetFormula (6, totalLine-completeline+3, pageCurrent, "<%=corpusSum%>");
					
                  	document.forms(0).Cell1.S(1,totalLine-completeline+4,pageCurrent,"制表人:");				
		            document.forms(0).Cell1.S(2,totalLine-completeline+4,pageCurrent,"<%=userName%>");
		            document.forms(0).Cell1.S(3,totalLine-completeline+4,pageCurrent,"操作日期:");
		            document.forms(0).Cell1.S(4,totalLine-completeline+4,pageCurrent,"<%=bizDate%>");
 					document.forms(0).Cell1.DeleteRow(totalLine-completeline+5,46-(totalLine-completeline+4),pageCurrent);
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
						<INPUT id="Button3" style="WIDTH: 90px"
							onclick="Button3_onclick()" type="button" value="页面设置">
					</td>
					<td>
						<INPUT id="Button3" style="WIDTH: 90px"
							onclick="Button4_onclick()" type="button" value="查找对话框">
					</td>
					<td>
						<INPUT id="Button3" style="WIDTH: 90px"
							onclick="Button5_onclick()" type="button" value="excel导入">
					</td>
					<td>
						<INPUT id="Button3" onclick="javascript:history.back();"
							type="button" value=" 返回 ">
					</td>
			</table>
		</form>
	</body>
</html>
