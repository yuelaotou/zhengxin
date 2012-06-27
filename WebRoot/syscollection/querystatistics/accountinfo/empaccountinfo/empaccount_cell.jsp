<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.List" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic" %>
<%@ page import="org.xpup.hafmis.syscollection.common.domain.entity.EmpHAFAccountFlow" %>
<%@ page import="org.xpup.hafmis.common.util.BusiTools" %>
<%@ include file="/checkUrl.jsp"%>
<%

   String path=request.getContextPath();
 %>
<html>
  <head>
  <script src="<%=path%>/js/common.js">
</script>
  </head>
  <script type="text/javascript">
	function load(){
	loginReg();	
	document.forms(0).Cell1.openfile("<%=path%>/syscollection/querystatistics/accountinfo/empaccountinfo/report/empAccountList.cll","");
    var orgId=[];
	var orgName=[];
	var empId=[];
	var empName=[];
	var preBalance=[];
	var credit=[];
	var debit=[];
	var countCredit=[];
	var countDebit=[];
	var curbalanceOne=[];
	var displayTme=[];
	var balance=[];
	var i=0;
			<%
    		List list=(List)request.getAttribute("list");
  			EmpHAFAccountFlow empHAFAccountFlow=null;
  			for(int j=0;j<list.size();j++)
  			{
  				empHAFAccountFlow=(EmpHAFAccountFlow)list.get(j);
  				String tempid = empHAFAccountFlow.getOrg().getId().toString();
				String orgid = 	BusiTools.convertTenNumber(tempid);
 	%>
 	            
 	            var temp_org="<%=orgid %>";
 	            orgId[i]="<%=orgid %>";
 	            orgName[i]="<%=empHAFAccountFlow.getOrg().getOrgInfo().getName()  %>";
 	            var tempp_empId="<%=empHAFAccountFlow.getEmpId()   %>";
				empId[i]=(format(tempp_empId)+""+tempp_empId);
				empName[i]="<%=empHAFAccountFlow.getEmpName()  %>";
				preBalance[i]="<%=empHAFAccountFlow.getPrebalance() %>";
				credit[i]="<%=empHAFAccountFlow.getTemp_credit() %>";
				debit[i]="<%=empHAFAccountFlow.getTemp_debit() %>";
				countCredit[i]="<%=empHAFAccountFlow.getCountCredit() %>";
				countDebit[i]="<%=empHAFAccountFlow.getCountDebit() %>";
				curbalanceOne[i]="<%=empHAFAccountFlow.getCurbalance() %>";
				displayTme[i]="<%=empHAFAccountFlow.getDisplayTme() %>";
				balance[i]="<%=empHAFAccountFlow.getEmp().getBalance()%>";
 	          	i++; 
 	<%
 			}
 	%>
	    var totalLine=empId.length;			//总的行数
		var totalPageLine=25;					//每页显示多少行
		var pageTotal=totalLine/totalPageLine;	//总共分几页
		var pageCurrent=0;						//当前页
		var completeline=0;		                //记录行的
		var moneyCredit=0;						//总的合计贷方
		var moneyDebit=0;						//总的合计借方
		for(k = 0;k < totalLine; k++){
			if(k%totalPageLine==0&&k>0)
			{	
				document.forms(0).Cell1.S(1,totalPageLine+4,pageCurrent,"本页小计");
				document.forms(0).Cell1.SetFormula (5, totalPageLine+4, pageCurrent, "Sum(E4:E"+(totalPageLine+3)+")" );
				document.forms(0).Cell1.SetFormula (6, totalPageLine+4, pageCurrent, "Sum(F4:F"+(totalPageLine+3)+")" );
				document.forms(0).Cell1.SetFormula (7, totalPageLine+4, pageCurrent, "Sum(G4:G"+(totalPageLine+3)+")" );
				document.forms(0).Cell1.SetFormula (8, totalPageLine+4, pageCurrent, "Sum(H4:H"+(totalPageLine+3)+")" );
				document.forms(0).Cell1.SetFormula (9, totalPageLine+4, pageCurrent, "Sum(I4:I"+(totalPageLine+3)+")" );
				document.forms(0).Cell1.SetFormula (10,totalPageLine+4, pageCurrent, "Sum(J4:J"+(totalPageLine+3)+")" );
				document.forms(0).Cell1.SetFormula (11,totalPageLine+4, pageCurrent, "Sum(K4:K"+(totalPageLine+3)+")" );
				document.forms(0).Cell1.ReDraw();
		     pageCurrent++;		
		     completeline=k-2;		
			 document.forms(0).Cell1.insertSheetFromFile("/hafmis/syscollection/querystatistics/accountinfo/empaccountinfo/report/empAccountList.cll",0,1,pageCurrent);
			 document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页")	
			}
			if(pageCurrent==0)
			{
				
				document.forms(0).Cell1.S(1,k+4,0,orgId[k]);
				document.forms(0).Cell1.S(2,k+4,0,orgName[k]);
				document.forms(0).Cell1.S(3,k+4,0,empId[k]);
				document.forms(0).Cell1.S(4,k+4,0,empName[k]);
				document.forms(0).Cell1.d(5,k+4,0,preBalance[k]);
				document.forms(0).Cell1.d(6,k+4,0,credit[k]);
				document.forms(0).Cell1.d(7,k+4,0,debit[k]);
				document.forms(0).Cell1.d(8,k+4,0,countCredit[k]);
				document.forms(0).Cell1.d(9,k+4,0,countDebit[k]);
				document.forms(0).Cell1.d(10,k+4,0,curbalanceOne[k]);
				document.forms(0).Cell1.S(11,k+4,0,displayTme[k]);
				document.forms(0).Cell1.d(13,k+4,0,balance[k]);
				moneyCredit=moneyCredit+parseFloat(credit[k]);
				moneyDebit=moneyDebit+parseFloat(debit[k]);
				
			}
			else{//向第一页后的所有页插数据
				
				document.forms(0).Cell1.S(1,k-completeline+2,pageCurrent,orgId[k]);
				document.forms(0).Cell1.S(2,k-completeline+2,pageCurrent,orgName[k]);
				document.forms(0).Cell1.S(3,k-completeline+2,pageCurrent,empId[k]);
				document.forms(0).Cell1.S(4,k-completeline+2,pageCurrent,empName[k]);
				document.forms(0).Cell1.d(5,k-completeline+2,pageCurrent,preBalance[k]);
				document.forms(0).Cell1.d(6,k-completeline+2,pageCurrent,credit[k]);
				document.forms(0).Cell1.d(7,k-completeline+2,pageCurrent,debit[k]);
				document.forms(0).Cell1.d(8,k-completeline+2,pageCurrent,countCredit[k]);
				document.forms(0).Cell1.d(9,k-completeline+2,pageCurrent,countDebit[k]);
				document.forms(0).Cell1.d(10,k-completeline+2,pageCurrent,curbalanceOne[k]);
				document.forms(0).Cell1.S(11,k-completeline+2,pageCurrent,displayTme[k]);
				document.forms(0).Cell1.d(13,k-completeline+2,pageCurrent,balance[k]);
				moneyCredit=moneyCredit+parseFloat(credit[k]);
				moneyDebit=moneyDebit+parseFloat(debit[k]);
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
					document.forms(0).Cell1.d(6,totalLine+5,pageCurrent,moneyCredit);
					document.forms(0).Cell1.d(5,totalLine+5,pageCurrent,moneyDebit);
					document.forms(0).Cell1.DeleteRow(totalLine+6,totalPageLine-(totalLine+5)+7,pageCurrent);
					document.forms(0).Cell1.ReDraw();
				}
				else
				{
					document.forms(0).Cell1.S(1,totalLine-completeline+2,pageCurrent,"本页小计");
					document.forms(0).Cell1.SetFormula (5, totalLine-completeline+2, pageCurrent, "Sum(E4:E"+(totalLine-(completeline-1))+")");
					document.forms(0).Cell1.SetFormula (6, totalLine-completeline+2, pageCurrent, "Sum(F4:F"+(totalLine-(completeline-1))+")");
					document.forms(0).Cell1.SetFormula (7, totalLine-completeline+2, pageCurrent, "Sum(G4:G"+(totalLine-(completeline-1))+")");
					document.forms(0).Cell1.SetFormula (8, totalLine-completeline+2, pageCurrent, "Sum(H4:H"+(totalLine-(completeline-1))+")");
					document.forms(0).Cell1.SetFormula (9, totalLine-completeline+2, pageCurrent, "Sum(I4:I"+(totalLine-(completeline-1))+")");
					document.forms(0).Cell1.SetFormula (10,totalLine-completeline+2, pageCurrent, "Sum(J4:J"+(totalLine-(completeline-1))+")");
					document.forms(0).Cell1.S(1,totalLine-completeline+3,pageCurrent,"总计");
					document.forms(0).Cell1.d(6,totalLine-completeline+3,pageCurrent,moneyCredit);
					document.forms(0).Cell1.d(5,totalLine-completeline+3,pageCurrent,moneyDebit);
					document.forms(0).Cell1.DeleteRow(totalLine-completeline+4,totalPageLine-(totalLine-completeline+3)+7,pageCurrent);
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
  <body onload = "load();" onContextmenu="return false"> 
    <form action="">
    <table align="center">
<tr><OBJECT id=Cell1 style= "LEFT:0px;WIDTH:700px;  TOP:0px;HEIGHT:400px" codeBase="http://192.168.1.28:8888/hafmis/CellWeb5.CAB#version=5,3,7,321" classid=clsid:3F166327-8030-4881-8BD2-EA25350E574A VIEWASTEXT><PARAM NAME="_Version" VALUE="65536"><PARAM NAME="_ExtentX" VALUE="10266"><PARAM NAME="_ExtentY" VALUE="7011"><PARAM NAME="_StockProps" VALUE= "0"></OBJECT></tr> 
<tr><td><input type="button" name="print" value = "打印预览" onclick = "printPreview();"/></td>
<td><INPUT id="Button1" onclick="Button1_onclick()" type="button" value="另存为Excel" name="Button1"></td>
<td><INPUT id="Button1" onclick="Button2_onclick()" type="button" value="另存为pdf" name="Button1"></td>
<td><INPUT id="Button3" style="WIDTH: 100px" onclick="Button3_onclick()" type="button" value="页面设置"></td>
<td><INPUT id="Button3" style="WIDTH: 100px" onclick="Button4_onclick()" type="button" value="查找对话框"></td>
<td><INPUT id="Button3" style="WIDTH: 100px" onclick="Button5_onclick()" type="button" value="excel导入"></td>
<td><INPUT id="Button1" onclick="printsheet()" type="button" value=" 打印 " name="Button1"></td>
<td><INPUT id="Button3" onclick="javascript:history.back();" type="button" value=" 返回 "></td>
</table>
</form>
  </body>
</html>
