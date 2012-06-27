<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page
	import="org.xpup.hafmis.sysfinance.accounthandle.credencecheck.dto.CredencecheckShowListDTO"%>
<%@ page import="java.util.List"%>
<%
	String path = request.getContextPath();
	String url = (String) request.getAttribute("url");
	request.setAttribute("type", "1");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>凭证维护打印</title>
		<script src="<%=path%>/js/common.js"></script>
	</head>
	<script type="text/javascript">
  	function load(){
  		loginReg();
	  	document.forms(0).Cell1.openfile("<%=path%>/sysfinance/accounthandle/report/credenceinfo_print.cll","");
	  	
	  	var credenceDate=[];
	  	var credencenum=[];
	    var debit=[];
	    var credit=[];
	    var makeBill=[];
	    var checkpsn=[];
	    var settNum=[];
	  	var credenceSt=[];
	  	
	  	var i=0
     	
     <%
    	List countList = (List) request.getSession().getAttribute("countList");
	    for(int j=0;j<countList.size();j++){
			CredencecheckShowListDTO dto=(CredencecheckShowListDTO)countList.get(j);

 	%>
		 	credenceDate[i]="<%=dto.getCredenceDate()%>";	
		 	credencenum[i]="<%=dto.getCredenceNum()%>";
	 		debit[i]="<%=dto.getDebit()%>";
	 		credit[i]="<%=dto.getCredit()%>";
	 		makeBill[i]="<%=dto.getRealName()%>";
	 		checkpsn[i]="<%=dto.getCheckpsn()%>";
	 		settNum[i]="<%=dto.getSettNum()%>";
	 		credenceSt[i]="<%=dto.getCredenceSt()%>";
 	    	i++; 
	<%
 			}
 	%>
 		var totalLine=settNum.length;				//总的行数
		var totalPageLine=36;					//每页显示多少行
		var pageTotal=totalLine/totalPageLine;	//总共分几页
		var pageCurrent=0;						//当前页
		var completeline=0;						//记录行的
		
		var sumDebit=0;
		var sumCredit=0;
		
		for(k = 0 ; k < totalLine; k++){

			if(k%totalPageLine==0&&k>0){
				document.forms(0).Cell1.S(1,totalPageLine+4,pageCurrent,"本页小计");
				document.forms(0).Cell1.SetFormula (3, totalPageLine+4, pageCurrent, "Sum(C4:C"+(totalPageLine+3)+")" );
				document.forms(0).Cell1.SetFormula (4, totalPageLine+4, pageCurrent, "Sum(D4:D"+(totalPageLine+3)+")" );
				
				document.forms(0).Cell1.ReDraw();
				pageCurrent++;	
				completeline=k-2;				
				document.forms(0).Cell1.insertSheetFromFile("<%=path%>/sysfinance/accounthandle/report/credenceinfo_print.cll",0,1,pageCurrent);
				document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页")	
			}
			if(pageCurrent==0){
				document.forms(0).Cell1.S(1,k+4,pageCurrent,credenceDate[k]);
				document.forms(0).Cell1.S(2,k+4,pageCurrent,credencenum[k]);
				document.forms(0).Cell1.d(3,k+4,pageCurrent,debit[k]);
				document.forms(0).Cell1.d(4,k+4,pageCurrent,credit[k]);
				document.forms(0).Cell1.s(5,k+4,pageCurrent,makeBill[k]);
				document.forms(0).Cell1.s(6,k+4,pageCurrent,checkpsn[k]);
				document.forms(0).Cell1.s(7,k+4,pageCurrent,credenceSt[k]);
				
				sumDebit=sumDebit+parseFloat(debit[k]);
				sumCredit=sumCredit+parseFloat(credit[k]);

			}else{//向第一页后的所有页插数据
			
				document.forms(0).Cell1.S(1,k-completeline+2,pageCurrent,credenceDate[k]);
				document.forms(0).Cell1.S(2,k-completeline+2,pageCurrent,credencenum[k]);
				document.forms(0).Cell1.d(3,k-completeline+2,pageCurrent,debit[k]);
				document.forms(0).Cell1.d(4,k-completeline+2,pageCurrent,credit[k]);
				document.forms(0).Cell1.s(5,k-completeline+2,pageCurrent,makeBill[k]);
				document.forms(0).Cell1.s(6,k-completeline+2,pageCurrent,checkpsn[k]);
				document.forms(0).Cell1.s(7,k-completeline+2,pageCurrent,credenceSt[k]);
				
				sumDebit=sumDebit+parseFloat(debit[k]);
				sumCredit=sumCredit+parseFloat(credit[k]);
				
			}	
		}
		if(completeline==0)//查询出来的记录可以在一页显示的时候会进入
		{
			document.forms(0).Cell1.S(1,totalLine+4,pageCurrent,"本页小计");
			document.forms(0).Cell1.SetFormula (3, totalLine+4, pageCurrent, "Sum(C4:C"+(totalLine+3)+")" );
			document.forms(0).Cell1.SetFormula (4, totalLine+4, pageCurrent, "Sum(D4:D"+(totalLine+3)+")" );
				
			document.forms(0).Cell1.S(1,totalLine+5,pageCurrent,"总计");
			document.forms(0).Cell1.d(3,totalLine+5,pageCurrent,sumDebit);
			document.forms(0).Cell1.d(4,totalLine+5,pageCurrent,sumCredit);
			
			document.forms(0).Cell1.ReDraw();
		}
		else
		{
			document.forms(0).Cell1.S(1,totalLine-completeline+2,pageCurrent,"本页小计");
			document.forms(0).Cell1.SetFormula (3, totalLine-completeline+2, pageCurrent, "Sum(C4:C"+(totalLine-(completeline-1))+")" );
			document.forms(0).Cell1.SetFormula (4, totalLine-completeline+2, pageCurrent, "Sum(D4:D"+(totalLine-(completeline-1))+")" );

			document.forms(0).Cell1.S(1,totalLine-completeline+3,pageCurrent,"总计");
			document.forms(0).Cell1.d(3,totalLine-completeline+3,pageCurrent,sumDebit);
			document.forms(0).Cell1.d(4,totalLine-completeline+3,pageCurrent,sumCredit);

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
					<OBJECT id="Cell1"
						style="LEFT:0px;WIDTH:800px;  TOP:0px;HEIGHT:500px"
						codeBase="http://192.168.1.44:8088/hafmis/CellWeb5.CAB#version=5,3,7,321"
						classid="clsid:3F166327-8030-4881-8BD2-EA25350E574A" VIEWASTEXT>
						<PARAM NAME="_Version" VALUE="65536">
						<PARAM NAME="_ExtentX" VALUE="10266">
						<PARAM NAME="_ExtentY" VALUE="7011">
						<PARAM NAME="_StockProps" VALUE="0">
					</OBJECT>
				</tr>
				<tr align="center">
					<td>
						<input type="button" name="print" value="打印预览"
							onclick="printPreview();" />
						<INPUT id="Button1" onclick="printsheet()" type="button"
							value=" 打印 " name="Button1">
						<INPUT id="Button1" onclick="Button1_onclick()" type="button"
							value="另存为Excel" name="Button1">
						<INPUT id="Button1" onclick="Button2_onclick()" type="button"
							value="另存为pdf" name="Button1">
						<INPUT id="Button3" style="WIDTH: 90px"
							onclick="Button3_onclick()" type="button" value="页面设置">
						<INPUT id="Button3" style="WIDTH: 90px"
							onclick="Button4_onclick()" type="button" value="查找对话框">
						<INPUT id="Button3" style="WIDTH: 90px"
							onclick="Button5_onclick()" type="button" value="excel导入">
						<INPUT id="Button3" onclick="javascript:history.back();"
							type="button" value=" 返回 ">
					</td>
			</table>
		</form>
	</body>
</html>
