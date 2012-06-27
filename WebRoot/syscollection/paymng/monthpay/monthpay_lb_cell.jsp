<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.List" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic" %>
<%@ page import="org.xpup.hafmis.syscollection.paymng.monthpay.dto.*" %>
<%@ include file="/checkUrl.jsp"%>
<%
   String path=request.getContextPath();
 %>
<html>
  <head>
  <script src="<%=path%>/js/common.js">
</script>
  <script type="text/javascript">
	function load(){	

	loginReg();
	document.forms(0).Cell1.openfile("<%=path%>/syscollection/paymng/report/monthpay_lb_cell.cll","");

	var notelist=[];
	var docnum=[];
	var orgid=[];
	var namelist=[];
	var paymonth=[];
	var paycount=[];
	var paymoney=[];
	var paymode=[];
	var setdate=[];
	var paydate=[];
	var paystatus=[];
	var bizDate="";
	var userName="";
	var i=0;
	<%
    		List list=(List) request.getAttribute("cellList");
  			MonthpayMaintainDto dto=null;
  			for(int j=0;j<list.size();j++)
  			{
  				dto=(MonthpayMaintainDto)list.get(j);
  								
  				
 	%>		
				notelist[i]="<%=dto.getNoteNum()%>";
				docnum[i]="<%=dto.getDocNum()%>";
				orgid[i]=formatTen("<%=dto.getOrgId()%>")+"<%=dto.getOrgId()%>";
				namelist[i]="<%=dto.getOrgName()%>";
				paymonth[i]="<%=dto.getPayMonth()%>";
				paycount[i]=<%=dto.getPayCount()%>;
				paymoney[i]=<%=dto.getPay()%>;
				paymode[i]="<%=dto.getPayMode()%>";
			    setdate[i]="<%=dto.getOpTime()%>";
				paydate[i]="<%=dto.getSettDate()%>";
				paystatus[i]="<%=dto.getPayStatus()%>";
				i++;
 	<%
 			}
 	%>				
 	   	bizDate="<%=(String)request.getAttribute("bizDate")%>";
 	   	userName="<%=(String)request.getAttribute("userName")%>";	
 	   	collBankName="<%=(String)request.getAttribute("collBankName")%>";				
		var totalLine=namelist.length;			//总的行数 数组的长度
		var totalPageLine=12;					//每页显示多少行
		var pageTotal=totalLine/totalPageLine;	//总共分几页
		var pageCurrent=0;						//当前页
		var completeline=0;						//记录行的
		var paycounts=0;                           //单位合计
		var paymoneys=0;    					//总的合计
		var iPage = getInt(totalLine,totalPageLine);
		for(k = 0 ; k < totalLine; k++){
			if(k%totalPageLine==0&&k>0)
			{
			   
				// document.forms(0).Cell1.S(1,44,pageCurrent,"本页小计");
				document.forms(0).Cell1.S(1,totalPageLine+4,pageCurrent,"本页小计");
			    document.forms(0).Cell1.SetFormula (5, totalPageLine+4, pageCurrent, "Sum(E4:E"+(totalPageLine+3)+")" );
			    document.forms(0).Cell1.SetFormula (6, totalPageLine+4, pageCurrent, "Sum(F4:F"+(totalPageLine+3)+")" );
				document.forms(0).Cell1.DeleteRow(totalPageLine+5,(totalPageLine+6)-(totalPageLine+4),pageCurrent);
				document.forms(0).Cell1.ReDraw();
				pageCurrent++;	
				completeline=k-2;				
				document.forms(0).Cell1.insertSheetFromFile("<%=path%>/syscollection/paymng/report/monthpay_lb_cell.cll",0,1,pageCurrent);
				document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页")	
			}
			if(pageCurrent==0)
			{
				document.forms(0).Cell1.S(1,2,0,"第"+(pageCurrent+1)+"/"+iPage+"页");
				document.forms(0).Cell1.S(3,2,0,collBankName);
				document.forms(0).Cell1.S(6,2,0,userName);
				document.forms(0).Cell1.S(8,2,0,bizDate);
				document.forms(0).Cell1.S(1,k+4,0,docnum[k]);
				document.forms(0).Cell1.S(2,k+4,0,orgid[k]);
				document.forms(0).Cell1.s(3,k+4,0,namelist[k]);
				document.forms(0).Cell1.s(4,k+4,0,paymonth[k]);
				document.forms(0).Cell1.d(5,k+4,0,paycount[k]);
				document.forms(0).Cell1.d(6,k+4,0,paymoney[k]);
				document.forms(0).Cell1.s(7,k+4,0,paymode[k]);
				document.forms(0).Cell1.s(8,k+4,0,paydate[k]);
				paycounts=paycounts+parseFloat(paycount[k]);
				paymoneys=paymoneys+parseFloat(paymoney[k]);
			}else{//向第一页后的所有页插数据
				document.forms(0).Cell1.S(1,2,pageCurrent,"第"+(pageCurrent+1)+"/"+iPage+"页");
				document.forms(0).Cell1.S(3,2,pageCurrent,collBankName);
				document.forms(0).Cell1.S(6,2,pageCurrent,userName);
				document.forms(0).Cell1.S(8,2,pageCurrent,bizDate);
				document.forms(0).Cell1.S(1,k-completeline+2,pageCurrent,docnum[k]);
				document.forms(0).Cell1.S(2,k-completeline+2,pageCurrent,orgid[k]);
				document.forms(0).Cell1.s(3,k-completeline+2,pageCurrent,namelist[k]);
				document.forms(0).Cell1.s(4,k-completeline+2,pageCurrent,paymonth[k]);
				document.forms(0).Cell1.d(5,k-completeline+2,pageCurrent,paycount[k]);
				document.forms(0).Cell1.d(6,k-completeline+2,pageCurrent,paymoney[k]);
				document.forms(0).Cell1.s(7,k-completeline+2,pageCurrent,paymode[k]);
				document.forms(0).Cell1.s(8,k-completeline+2,pageCurrent,paydate[k]);
				paycounts=paycounts+parseFloat(paycount[k]);
				paymoneys=paymoneys+parseFloat(paymoney[k]);
			}	
		}
				if(completeline==0)//查询出来的记录可以在一页显示的时候会进入
				{
				     
					document.forms(0).Cell1.S(1,totalLine+4,pageCurrent,"本页小计");
					document.forms(0).Cell1.SetFormula (5, totalLine+4, pageCurrent, "Sum(E4:E"+(totalLine+3)+")" );//,loopcell() > 5
					document.forms(0).Cell1.SetFormula (6, totalLine+4, pageCurrent, "Sum(F4:F"+(totalLine+3)+")" );//,loopcell() > 5
					document.forms(0).Cell1.S(1,totalLine+5,pageCurrent,"总计");
					document.forms(0).Cell1.d(5,totalLine+5,pageCurrent,paycounts);
					document.forms(0).Cell1.d(6,totalLine+5,pageCurrent,paymoneys);
					
					
					//document.forms(0).Cell1.s(1,totalLine+6,pageCurrent,"制表人");
					//document.forms(0).Cell1.s(2,totalLine+6,pageCurrent,userName);
					
					//document.forms(0).Cell1.s(3,totalLine+6,pageCurrent,"操作日期");
					//document.forms(0).Cell1.s(4,totalLine+6,pageCurrent,bizDate);
					document.forms(0).Cell1.DeleteRow(totalLine+6,(totalPageLine+9)-(totalLine+8),pageCurrent);
					document.forms(0).Cell1.ReDraw();
				}
				else
				{
					document.forms(0).Cell1.S(1,totalLine-completeline+2,pageCurrent,"本页小计");
					document.forms(0).Cell1.SetFormula (5, totalLine-completeline+2, pageCurrent, "Sum(E4:E"+(totalLine-(completeline-1))+")" );
					document.forms(0).Cell1.SetFormula (6, totalLine-completeline+2, pageCurrent, "Sum(F4:F"+(totalLine-(completeline-1))+")" );
					document.forms(0).Cell1.S(1,totalLine-completeline+3,pageCurrent,"总计");
					document.forms(0).Cell1.d(5,totalLine-completeline+3,pageCurrent,paycounts);
					document.forms(0).Cell1.d(6,totalLine-completeline+3,pageCurrent,paymoneys);
					
					//document.forms(0).Cell1.s(1,totalLine-completeline+4,pageCurrent,"归集银行");
					//document.forms(0).Cell1.MergeCells(2,totalLine-completeline+4,3,totalLine-completeline+4);
					//document.forms(0).Cell1.s(2,totalLine-completeline+4,pageCurrent,collectionBank[0]);
					
					//document.forms(0).Cell1.s(1,totalLine-completeline+4,pageCurrent,"制表人");
					//document.forms(0).Cell1.s(2,totalLine-completeline+4,pageCurrent,userName);
					
					//document.forms(0).Cell1.s(3,totalLine-completeline+4,pageCurrent,"操作日期");
					//document.forms(0).Cell1.s(4,totalLine-completeline+4,pageCurrent,bizDate);
					document.forms(0).Cell1.DeleteRow(totalLine-completeline+4,totalPageLine-(totalLine-completeline-3),pageCurrent);
					document.forms(0).Cell1.ReDraw();
				}	
				document.forms(0).Cell1.AllowExtend=false;
				document.forms(0).Cell1.AllowDragdrop=false;
				document.forms(0).Cell1.WorkbookReadonly=true;	
				document.forms(0).Cell1.PrintSetSheetOpt(3);
 				document.forms(0).Cell1.PrintSetPrintRange(1,0);
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
