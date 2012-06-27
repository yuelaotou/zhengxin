
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.List" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic" %>
<%@ page import="org.xpup.hafmis.sysloan.querystatistics.loanbusiquery.assureborrowerquery.dto.AssureborrowerqueryDTO" %>
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
	document.forms(0).Cell1.openfile("/hafmis/sysloan/querystatistics/loanbusiquery/assureborrowerquery/report/assureborrowerquery.cll","");

	var temp_debit=[];
	var temp_credit=[];
	var countDebit=[];
	var countCredit=[];
	var orgOverMoney=[];
	var curbalance=[];
	var orgOverPaybalance=[];
	var balance=[];
	var interest=[];
	var opTime=[];
	var i=0;
	<%
    		List list=(List) request.getSession().getAttribute("printList");
  			AssureborrowerqueryDTO dto=null;
  			for(int j=0;j<list.size();j++)
  			{
  				dto=(AssureborrowerqueryDTO)list.get(j);	
 	%>			
			
				temp_debit[i]="<%=dto.getOrgId() %>";
				temp_credit[i]="<%=dto.getOtherBorrowerOrgName() %>";
				countDebit[i]="<%=dto.getOtherBorrowerEmpId() %>";
				countCredit[i]="<%=dto.getOtherBorrowerName() %>";
				orgOverMoney[i]="<%=dto.getLoanMoney()%>";
				curbalance[i]="<%=dto.getType() %>";
				orgOverPaybalance[i]="<%=dto.getBorrowerEmpId()%>";
				balance[i]="<%=dto.getBorrowerName() %>";
				interest[i]="<%=dto.getContractId() %>";
				opTime[i]="<%=dto.getLoanKouAcc() %>";
				i++;
 	<%
 			}
 	%>
			var totalLine=temp_credit.length;			//总的行数
			var totalPageLine=40;					//每页显示多少行
			var pageTotal=totalLine/totalPageLine;	//总共分几页
			var pageCurrent=0;						//当前页
			var completeline=0;						//记录行的
		
			var sumorgOverMoney=0;
			
			//总的合计
		for(k = 0 ; k < totalLine; k++){
		if(k%totalPageLine==0&&k>0)
			{
				document.forms(0).Cell1.S(1,44,pageCurrent,"本页小计");
				
				document.forms(0).Cell1.SetFormula (5, 44, pageCurrent, "Sum(E2:E"+(totalPageLine+3)+")" );
				
				document.forms(0).Cell1.ReDraw();
				pageCurrent++;	
				completeline=k-2;				
				document.forms(0).Cell1.insertSheetFromFile("/hafmis/sysloan/querystatistics/loanbusiquery/assureborrowerquery/report/assureborrowerquery.cll",0,1,pageCurrent);
				document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页")	;

			}
		if(pageCurrent==0)
			{
				//document.forms(0).Cell1.S(1,k+4,0,office[k]);
				//document.forms(0).Cell1.S(2,k+4,0,collbank[k]);
				
				document.forms(0).Cell1.s(1,k+4,0,temp_debit[k]);
				document.forms(0).Cell1.s(2,k+4,0,temp_credit[k]);
				document.forms(0).Cell1.s(3,k+4,0,countDebit[k]);
				document.forms(0).Cell1.s(4,k+4,0,countCredit[k]);
				document.forms(0).Cell1.d(5,k+4,0,orgOverMoney[k]);
				document.forms(0).Cell1.s(6,k+4,0,curbalance[k]);
				document.forms(0).Cell1.s(7,k+4,0,orgOverPaybalance[k]);
				document.forms(0).Cell1.s(8,k+4,0,balance[k]);
				document.forms(0).Cell1.s(9,k+4,0,interest[k]);
				document.forms(0).Cell1.s(10,k+4,0,opTime[k]);
				
				sumorgOverMoney=sumorgOverMoney+parseFloat(orgOverMoney[k]);
				
			}else{//向第一页后的所有页插数据
			//	document.forms(0).Cell1.S(1,k-completeline+2,pageCurrent,office[k]);
			//	document.forms(0).Cell1.S(2,k-completeline+2,pageCurrent,collbank[k]);
				
				document.forms(0).Cell1.s(1,k-completeline+2,pageCurrent,temp_debit[k]);
				document.forms(0).Cell1.s(2,k-completeline+2,pageCurrent,temp_credit[k]);
				document.forms(0).Cell1.s(3,k-completeline+2,pageCurrent,countDebit[k]);
				document.forms(0).Cell1.s(4,k-completeline+2,pageCurrent,countCredit[k]);
				document.forms(0).Cell1.d(5,k-completeline+2,pageCurrent,orgOverMoney[k]);
				document.forms(0).Cell1.s(6,k-completeline+2,pageCurrent,curbalance[k]);
				document.forms(0).Cell1.s(7,k-completeline+2,pageCurrent,orgOverPaybalance[k]);
				document.forms(0).Cell1.s(8,k-completeline+2,pageCurrent,balance[k]);
				document.forms(0).Cell1.s(9,k-completeline+2,pageCurrent,interest[k]);
				document.forms(0).Cell1.s(10,k-completeline+2,pageCurrent,opTime[k]);
				
				sumorgOverMoney=sumorgOverMoney+parseFloat(orgOverMoney[k]);
			}
		}
		
			if(completeline==0)//查询出来的记录可以在一页显示的时候会进入
				{			
					document.forms(0).Cell1.S(1,totalLine+4,pageCurrent,"本页小计");
					
					document.forms(0).Cell1.SetFormula (5, totalLine+4, pageCurrent, "Sum(E2:E"+(totalLine+3)+")" );//,loopcell() > 5
					document.forms(0).Cell1.S(1,totalLine+5,pageCurrent,"总计");
				
					document.forms(0).Cell1.d(5,totalLine+5,pageCurrent,sumorgOverMoney);
					
					document.forms(0).Cell1.DeleteRow(totalLine+6,totalPageLine-(totalLine+5),pageCurrent);
		///		document.forms(0).Cell1.ReDraw();
				}else
				{
					document.forms(0).Cell1.S(1,totalLine-completeline+2,pageCurrent,"本页小计");
					
					document.forms(0).Cell1.SetFormula (5, totalLine-completeline+2, pageCurrent, "Sum(E2:E"+(totalLine-(completeline-1))+")" );
					document.forms(0).Cell1.S(1,totalLine-completeline+3,pageCurrent,"总计");
					
					document.forms(0).Cell1.d(5,totalLine-completeline+3,pageCurrent,sumorgOverMoney);
					document.forms(0).Cell1.DeleteRow(totalLine-completeline+4,totalPageLine-(totalLine-completeline+3),pageCurrent);
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
  <body onload = "load();" onContextmenu="return false"> 
    <form action="">
    <table align="center">
<tr><OBJECT id=Cell1 style= "LEFT:0px;WIDTH:700px;  TOP:0px;HEIGHT:400px" codeBase="http://192.168.1.44:8088/hafmis/CellWeb5.CAB#version=5,3,7,321" classid=clsid:3F166327-8030-4881-8BD2-EA25350E574A VIEWASTEXT><PARAM NAME="_Version" VALUE="65536"><PARAM NAME="_ExtentX" VALUE="10266"><PARAM NAME="_ExtentY" VALUE="7011"><PARAM NAME="_StockProps" VALUE= "0"></OBJECT></tr> 
<tr>
<td><input type="button" name="print" value = "打印预览" onclick = "printPreview();"/></td>
<td><INPUT id="Button1" onclick="printsheet()" type="button" value=" 打印 " name="Button1"></td>
<td><INPUT id="Button1" onclick="Button1_onclick()" type="button" value="另存为Excel" name="Button1"></td>
<td><INPUT id="Button1" onclick="Button2_onclick()" type="button" value="另存为pdf" name="Button1"></td>
<td><INPUT id="Button3" style="WIDTH: 100px" onclick="Button3_onclick()" type="button" value="页面设置"></td>
<td><INPUT id="Button3" style="WIDTH: 100px" onclick="Button4_onclick()" type="button" value="查找对话框"></td>
<td><INPUT id="Button3" style="WIDTH: 100px" onclick="Button5_onclick()" type="button" value="excel导入"></td>
<td><INPUT id="Button1" onclick="javascript:history.back();" type="button" value=" 返回 "></td>	
</table>
</form>
  </body>
</html>
