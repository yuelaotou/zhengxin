<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page
	import="org.xpup.hafmis.sysloan.loanapply.loanvipcheck.dto.LoanVIPCheckDTO"%>
<%@ page import="java.util.List"%>
<%
	String path = request.getContextPath();
	String str = (String) request.getAttribute("str");
	String bizDate = (String) request.getAttribute("bizDate");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>凭证维护打印</title>
<script src="<%=path%>/js/common.js">
</script>
	</head>
	<script type="text/javascript">
  	function load(){
  		loginReg();
	  	document.forms(0).Cell1.openfile("<%=path%>/sysloan/loanapply/report/loanvipcheck.cll","");
	  	var contractId=[];// 合同编号
		var borrowerName=[];// 借款人姓名
	    var orgName=[];// 单位名称
	    var totlePrice=[];//房价（元s)（小数点保留两位)
	    var loanMoney=[];// 借款金额
	    var loanTimeLimit=[];
	    var houseArea=[];// 建筑面积（M2)小数点后保留两位）
	    var houseAddr=[];// 房屋坐落
	    var operator=[];// 操作员
	    var bizDate="<%=bizDate%>"; 
	  	var str="<%=str%>"; 
	  	var i=0
     	
     <%
    	List celllist = (List) request.getSession().getAttribute("printList");
	    for(int j=0;j<celllist.size();j++){
			LoanVIPCheckDTO dto=(LoanVIPCheckDTO)celllist.get(j);
 	%>
 	        contractId[i]="<%=dto.getContractId()%>";	
	  	    borrowerName[i]="<%=dto.getBorrowerName()%>";	
	  	    orgName[i]="<%=dto.getOrgName()%>";	
	  	    totlePrice[i]="<%=dto.getTotalPrice()%>";	
	  	    loanMoney[i]="<%=dto.getLoanMoney()%>";	
	  	    loanTimeLimit[i]="<%=dto.getLoanTimeLimit()%>";
	  	    houseArea[i]="<%=dto.getHouseArea()%>";	
	        houseAddr[i]="<%=dto.getHouseAddr()%>";	
	  	    operator[i]="<%=dto.getOperator()%>";	
 	    	i++; 
	<%
 			}
 	%>
 		var totalLine=contractId.length;				//总的行数
		var totalPageLine=24;					//每页显示多少行
		var pageTotal=totalLine/totalPageLine;	//总共分几页
		var pageCurrent=0;						//当前页
		var completeline=0;						//记录行的
		
		for(k = 0 ; k < totalLine; k++){

			if(k%totalPageLine==0&&k>0){
	            document.forms(0).Cell1.S(1,totalPageLine+4,pageCurrent,"本页小计");
	            document.forms(0).Cell1.S(2,totalPageLine+4, pageCurrent, totalPageLine+"户");
				document.forms(0).Cell1.SetFormula (4, totalPageLine+4, pageCurrent, "Sum(D2:D"+(totalPageLine+3)+")" );
				document.forms(0).Cell1.SetFormula (5, totalPageLine+4, pageCurrent, "Sum(E2:E"+(totalPageLine+3)+")" );
				document.forms(0).Cell1.SetFormula (7, totalPageLine+4, pageCurrent, "Sum(G2:G"+(totalPageLine+3)+")" );

				document.forms(0).Cell1.ReDraw();
				pageCurrent++;	
				completeline=k-2;				
				document.forms(0).Cell1.insertSheetFromFile("<%=path%>/sysloan/loanapply/report/loanvipcheck.cll",0,1,pageCurrent);
				document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页")	
			}
			if(pageCurrent==0){
			    document.forms(0).Cell1.S(3,1,0,bizDate.substring(0,4));
			    document.forms(0).Cell1.S(1,2,0,str);
			    document.forms(0).Cell1.S(8,2,0,bizDate.substring(0,4)+"-"+bizDate.substring(4,6)+"-"+bizDate.substring(6,8));
			    document.forms(0).Cell1.S(1,k+4,0,contractId[k]);
				document.forms(0).Cell1.S(2,k+4,0,borrowerName[k]);
				document.forms(0).Cell1.S(3,k+4,0,orgName[k]);
 				document.forms(0).Cell1.D(4,k+4,0,totlePrice[k]);
				document.forms(0).Cell1.D(5,k+4,0,loanMoney[k]);
				document.forms(0).Cell1.S(6,k+4,0,loanTimeLimit[k]);
				document.forms(0).Cell1.d(7,k+4,0,houseArea[k]);
 		        document.forms(0).Cell1.S(8,k+4,0,houseAddr[k]);
				document.forms(0).Cell1.S(9,k+4,0,operator[k]);

			}else{//向第一页后的所有页插数据
				document.forms(0).Cell1.S(3,1,pageCurrent,bizDate.substring(0,4));
				document.forms(0).Cell1.S(1,2,pageCurrent,str);
				document.forms(0).Cell1.S(8,2,pageCurrent,bizDate.substring(0,4)+"-"+bizDate.substring(4,6)+"-"+bizDate.substring(6,8));
				document.forms(0).Cell1.S(1,k-completeline+2,pageCurrent,contractId[k]);
				document.forms(0).Cell1.S(2,k-completeline+2,pageCurrent,borrowerName[k]);
				document.forms(0).Cell1.S(3,k-completeline+2,pageCurrent,orgName[k]);
 				document.forms(0).Cell1.D(4,k-completeline+2,pageCurrent,totlePrice[k]);
				document.forms(0).Cell1.D(5,k-completeline+2,pageCurrent,loanMoney[k]);
				document.forms(0).Cell1.S(6,k-completeline+2,pageCurrent,loanTimeLimit[k]);
				document.forms(0).Cell1.d(7,k-completeline+2,pageCurrent,houseArea[k]);
 		        document.forms(0).Cell1.S(8,k-completeline+2,pageCurrent,houseAddr[k]);
				document.forms(0).Cell1.S(9,k-completeline+2,pageCurrent,operator[k]);
			}	
		}
		if(completeline==0)//查询出来的记录可以在一页显示的时候会进入
		{
			document.forms(0).Cell1.S(1,totalLine+4,pageCurrent,"本页小计");
            document.forms(0).Cell1.S(2,totalLine+4,pageCurrent,totalLine+"户");
			document.forms(0).Cell1.SetFormula (4,totalLine+4, pageCurrent,"Sum(D2:D"+(totalLine+3)+")" );
			document.forms(0).Cell1.SetFormula (5,totalLine+4, pageCurrent,"Sum(E2:E"+(totalLine+3)+")" );
			document.forms(0).Cell1.SetFormula (7,totalLine+4, pageCurrent,"Sum(G2:G"+(totalLine+3)+")" );
			
			document.forms(0).Cell1.DeleteRow(totalLine+5,totalPageLine-totalLine,pageCurrent);
			document.forms(0).Cell1.ReDraw();
		}	
		else
		{
			document.forms(0).Cell1.S(1,totalLine-completeline+2,pageCurrent,"本页小计");   
			document.forms(0).Cell1.S(2,totalLine-completeline+2,pageCurrent,(totalLine-completeline-2)+"户");
			document.forms(0).Cell1.SetFormula (4,totalLine-completeline+2, pageCurrent,"Sum(D2:D"+(totalLine-completeline+1)+")" );
			document.forms(0).Cell1.SetFormula (5,totalLine-completeline+2, pageCurrent,"Sum(E2:E"+(totalLine-completeline+1)+")" );
			document.forms(0).Cell1.SetFormula (7,totalLine-completeline+2, pageCurrent,"Sum(G2:G"+(totalLine-completeline+1)+")" );
			
			document.forms(0).Cell1.DeleteRow(totalLine-completeline+3,totalPageLine+2-(totalLine-completeline),pageCurrent);
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
	<body onload="load();">
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
