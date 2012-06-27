<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.List" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic" %>
<%@ page import="org.xpup.hafmis.sysfinance.treasurermng.depositcheckacc.dto.*" %>
<%@ include file="/checkUrl.jsp"%>

<%
   String path=request.getContextPath();
   List bdcList=(List)request.getSession().getAttribute("bdcList");
   List bcaList=(List)request.getSession().getAttribute("bcaList");
 %>
<html>
<head>
<script src="<%=path%>/js/common.js"></script>
</head>
<script type="text/javascript">
	function load(){
	loginReg();
	document.forms(0).Cell1.openfile("<%=path%>/sysfinance/treasurermng/report/depositcheckaccdbc.cll","");
  	var settDate=[];
  	var subjectCode=[];
    var settType=[];
    var settNum=[];
    var credenceChaNum=[];
    var debit=[];
    var credit=[];
    var credenceDate=[];
    var i=0;
	
	<%
		DepositCheckAccBdcDTO depositCheckAccBdcDTO = new DepositCheckAccBdcDTO();
		for(int j=0;j<bdcList.size();j++){
		depositCheckAccBdcDTO = (DepositCheckAccBdcDTO)bdcList.get(j);
	%>
	    //把数据传到JS的数组里面..
		settDate[i] = "<%=depositCheckAccBdcDTO.getSettDate()%>"; 
		subjectCode[i] = "<%=depositCheckAccBdcDTO.getSubjectCode()%>";
		settType[i] = "<%=depositCheckAccBdcDTO.getTemp_settType()%>";
		settNum[i] = "<%=depositCheckAccBdcDTO.getSettNum()%>";
		credenceChaNum[i] = "<%=depositCheckAccBdcDTO.getSettNum()%>";
		debit[i] = "<%=depositCheckAccBdcDTO.getDebit()%>";
		credit[i] = "<%=depositCheckAccBdcDTO.getCredit()%>";
		credenceDate[i] = "<%=depositCheckAccBdcDTO.getCredenceDate()%>";
		i++;
	<%}%>
	var totalLine=debit.length;				//总的行数 数组的长度
	var totalPageLine=40;					//每页显示多少行--除了第一行
	var pageTotal=totalLine/totalPageLine;	//总共分几页 总行数/每页的行数
	var pageCurrent=0;						//当前页
	var completeline=0;						//记录行的
	var debittotal=0;						//借方金额合计
	var credittotal=0;						//贷方金额合计
	var m=0;
	
	for(k = 0 ; k < totalLine; k++){
		if(k%totalPageLine==0&&k>0)
		{
			document.forms(0).Cell1.S(1,totalPageLine+4,pageCurrent,"本页小计");
			document.forms(0).Cell1.SetFormula (6, totalPageLine+4, pageCurrent, "Sum(F4:F"+(totalPageLine+3)+")" );
			document.forms(0).Cell1.SetFormula (7, totalPageLine+4, pageCurrent, "Sum(G4:G"+(totalPageLine+3)+")" );
			document.forms(0).Cell1.ReDraw();//重画一个表格
			pageCurrent++;//当前页++	
			completeline=k-2;
			//绘制标签 param 	表页索引号。param 要设置的表页页签名称
			document.forms(0).Cell1.insertSheetFromFile("<%=path%>/sysfinance/treasurermng/report/depositcheckaccdbc.cll",0,1,pageCurrent);
  			document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页");	
		}
		if(pageCurrent==0)
		{	
			document.forms(0).Cell1.S(1,k+4,0,settDate[k]);
			document.forms(0).Cell1.S(2,k+4,0,subjectCode[k]);
			document.forms(0).Cell1.S(3,k+4,0,settType[k]);
			document.forms(0).Cell1.S(4,k+4,0,settNum[k]);
			document.forms(0).Cell1.S(5,k+4,0,credenceChaNum[k]);
			document.forms(0).Cell1.d(6,k+4,0,debit[k]);
			document.forms(0).Cell1.d(7,k+4,0,credit[k]);
			document.forms(0).Cell1.S(8,k+4,0,credenceDate[k]);
			debittotal=debittotal+parseFloat(debit[k]);
			credittotal=credittotal+parseFloat(credit[k]);
			}
		else{//向第一页后的所有页插数据
			document.forms(0).Cell1.S(1,k-completeline+2,pageCurrent,settDate[k]);
			document.forms(0).Cell1.S(2,k-completeline+2,pageCurrent,subjectCode[k]);
			document.forms(0).Cell1.S(3,k-completeline+2,pageCurrent,settType[k]);
			document.forms(0).Cell1.S(4,k-completeline+2,pageCurrent,settNum[k]);
			document.forms(0).Cell1.S(5,k-completeline+2,pageCurrent,credenceChaNum[k]);
			document.forms(0).Cell1.d(6,k-completeline+2,pageCurrent,debit[k]);
			document.forms(0).Cell1.d(7,k-completeline+2,pageCurrent,credit[k]);
			document.forms(0).Cell1.S(8,k-completeline+2,pageCurrent,credenceDate[k]);
			debittotal=debittotal+parseFloat(debit[k]);
			credittotal=credittotal+parseFloat(credit[k]);
		}	
		m=k;
	}
	if(completeline==0)//查询出来的记录可以在一页显示的时候会进入
	{
		document.forms(0).Cell1.S(1,totalLine+4,pageCurrent,"本页小计");
		document.forms(0).Cell1.SetFormula (6, totalLine+4, pageCurrent, "Sum(F4:F"+(totalLine+3)+")" );
		document.forms(0).Cell1.SetFormula (7, totalLine+4, pageCurrent, "Sum(G4:G"+(totalLine+3)+")" );
		document.forms(0).Cell1.S(1,totalLine+5,pageCurrent,"总计");
		document.forms(0).Cell1.d(6,totalLine+5,pageCurrent,debittotal);
		document.forms(0).Cell1.d(7,totalLine+5,pageCurrent,credittotal);
		document.forms(0).Cell1.DeleteRow(totalLine+6,45-totalLine-5,pageCurrent);
		document.forms(0).Cell1.ReDraw();
	}	
	else
	{
		document.forms(0).Cell1.S(1,totalLine-completeline+2,pageCurrent,"本页小计");   
		document.forms(0).Cell1.SetFormula (6, totalLine-completeline+2, pageCurrent, "Sum(F4:F"+(totalLine-(completeline-1))+")" );
		document.forms(0).Cell1.SetFormula (7, totalLine-completeline+2, pageCurrent, "Sum(G4:G"+(totalLine-(completeline-1))+")" );
		document.forms(0).Cell1.S(1,totalLine-completeline+3,pageCurrent,"总计");
		document.forms(0).Cell1.d(6,totalLine-completeline+3,pageCurrent,debittotal);
		document.forms(0).Cell1.d(7,totalLine-completeline+3,pageCurrent,credittotal);
		document.forms(0).Cell1.DeleteRow(totalLine-completeline+4,45-(totalLine-completeline+3),pageCurrent);
		document.forms(0).Cell1.ReDraw();
	}
	if(m==(totalLine-1)||m==0){
		pageCurrent++;//当前页++	
		document.forms(0).Cell1.insertSheetFromFile("<%=path%>/sysfinance/treasurermng/report/depositcheckaccdca.cll",0,1,pageCurrent);
		document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页");	
		var settDatee=[];
	  	var subjectCodee=[];
	    var settTypee=[];
	    var settNume=[];
	    var debite=[];
	    var credite=[];
	    var p=0;
		
		<%
			DepositCheckAccBcaDTO depositCheckAccBcaDTO = new DepositCheckAccBcaDTO();
			for(int q=0;q<bcaList.size();q++){
			depositCheckAccBcaDTO = (DepositCheckAccBcaDTO)bcaList.get(q);
		%>
		    //把数据传到JS的数组里面..
			settDatee[p] = "<%=depositCheckAccBcaDTO.getSettDate()%>"; 
			subjectCodee[p] = "<%=depositCheckAccBcaDTO.getSubjectCode()%>";
			settTypee[p] = "<%=depositCheckAccBcaDTO.getTemp_settType()%>";
			settNume[p] = "<%=depositCheckAccBcaDTO.getSettNum()%>";
			debite[p] = "<%=depositCheckAccBcaDTO.getDebit()%>";
			credite[p] = "<%=depositCheckAccBcaDTO.getCredit()%>";
			p++;
		<%}%>
		var totalLinee=debite.length;				//总的行数 数组的长度
		var totalPageLinee=40;					//每页显示多少行--除了第一行
		var pageTotale=totalLinee/totalPageLinee;	//总共分几页 总行数/每页的行数
		var pageCurrente=pageCurrent;						//当前页
		var completelinee=0;						//记录行的
		var debitetotal=0;						//借方金额合计
		var creditetotal=0;						//贷方金额合计
		
		for(n = 0 ; n < totalLinee; n++){
			if(n%totalPageLinee==0&&n>0)
			{
				document.forms(0).Cell1.S(1,totalPageLinee+4,pageCurrent,"本页小计");
				document.forms(0).Cell1.SetFormula (5, totalPageLinee+4, pageCurrent, "Sum(E4:E"+(totalPageLinee+3)+")" );
				document.forms(0).Cell1.SetFormula (6, totalPageLinee+4, pageCurrent, "Sum(F4:F"+(totalPageLinee+3)+")" );
				document.forms(0).Cell1.ReDraw();//重画一个表格
				pageCurrent++;//当前页++	
				completelinee=n-2;
				//绘制标签 param 	表页索引号。param 要设置的表页页签名称
				document.forms(0).Cell1.insertSheetFromFile("<%=path%>/sysfinance/treasurermng/report/depositcheckaccdca.cll",0,1,pageCurrent);
	  			document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页");	
			}
			if(pageCurrente==pageCurrent)
			{	
				document.forms(0).Cell1.S(1,n+4,pageCurrent,settDatee[n]);
				document.forms(0).Cell1.S(2,n+4,pageCurrent,subjectCodee[n]);
				document.forms(0).Cell1.S(3,n+4,pageCurrent,settTypee[n]);
				document.forms(0).Cell1.S(4,n+4,pageCurrent,settNume[n]);
				document.forms(0).Cell1.d(5,n+4,pageCurrent,debite[n]);
				document.forms(0).Cell1.d(6,n+4,pageCurrent,credite[n]);
				debitetotal=debitetotal+parseFloat(debite[n]);
				creditetotal=creditetotal+parseFloat(credite[n]);
			}
			else{//向第一页后的所有页插数据
				document.forms(0).Cell1.S(1,n-completelinee+2,pageCurrent,settDatee[n]);
				document.forms(0).Cell1.S(2,n-completelinee+2,pageCurrent,subjectCodee[n]);
				document.forms(0).Cell1.S(3,n-completelinee+2,pageCurrent,settTypee[n]);
				document.forms(0).Cell1.S(4,n-completelinee+2,pageCurrent,settNume[n]);
				document.forms(0).Cell1.d(5,n-completelinee+2,pageCurrent,debite[n]);
				document.forms(0).Cell1.d(6,n-completelinee+2,pageCurrent,credite[n]);
				debitetotal=debitetotal+parseFloat(debite[n]);
				creditetotal=creditetotal+parseFloat(credite[n]);
			}	
		}
		if(completelinee==0)//查询出来的记录可以在一页显示的时候会进入
		{
			document.forms(0).Cell1.S(1,totalLinee+4,pageCurrent,"本页小计");
			document.forms(0).Cell1.SetFormula (5, totalLinee+4, pageCurrent, "Sum(E4:E"+(totalLinee+3)+")" );
			document.forms(0).Cell1.SetFormula (6, totalLinee+4, pageCurrent, "Sum(F4:F"+(totalLinee+3)+")" );
			document.forms(0).Cell1.S(1,totalLinee+5,pageCurrent,"总计");
			document.forms(0).Cell1.d(5,totalLinee+5,pageCurrent,debitetotal);
			document.forms(0).Cell1.d(6,totalLinee+5,pageCurrent,creditetotal);
			document.forms(0).Cell1.DeleteRow(totalLinee+6,45-totalLinee-5,pageCurrent);
			document.forms(0).Cell1.ReDraw();
		}	
		else
		{
			document.forms(0).Cell1.S(1,totalLinee-completelinee+2,pageCurrent,"本页小计");   
			document.forms(0).Cell1.SetFormula (5, totalLinee-completelinee+2, pageCurrent, "Sum(E4:E"+(totalLinee-(completelinee-1))+")" );
			document.forms(0).Cell1.SetFormula (6, totalLinee-completelinee+2, pageCurrent, "Sum(F4:F"+(totalLinee-(completelinee-1))+")" );
			document.forms(0).Cell1.S(1,totalLinee-completelinee+3,pageCurrent,"总计");
			document.forms(0).Cell1.d(5,totalLinee-completelinee+3,pageCurrent,debitetotal);
			document.forms(0).Cell1.d(6,totalLinee-completelinee+3,pageCurrent,creditetotal);
			document.forms(0).Cell1.DeleteRow(totalLinee-completelinee+4,45-(totalLinee-completelinee+3),pageCurrent);
			document.forms(0).Cell1.ReDraw();
		}
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

<body  onContextmenu="return false" onload = "load();"> 
<form action="">
<table align="center">
	<tr><OBJECT id=Cell1 style= "LEFT:0px;WIDTH:700px;  TOP:0px;HEIGHT:400px" codeBase="http://192.168.1.44:8088/hafmis/CellWeb5.CAB#version=5,3,7,321" classid=clsid:3F166327-8030-4881-8BD2-EA25350E574A VIEWASTEXT><PARAM NAME="_Version" VALUE="65536"><PARAM NAME="_ExtentX" VALUE="10266"><PARAM NAME="_ExtentY" VALUE="7011"><PARAM NAME="_StockProps" VALUE= "0"></OBJECT></tr> 
	<tr><input type="button" name="print" value = "打印预览" onclick = "printPreview();"/>
		<INPUT id="Button1" onclick="Button1_onclick()" type="button" value="另存为Excel" name="Button1">
		<INPUT id="Button1" onclick="Button2_onclick()" type="button" value="另存为pdf" name="Button1">
		<INPUT id="Button3" style="WIDTH: 90px" onclick="Button3_onclick()" type="button" value="页面设置">
		<INPUT id="Button3" style="WIDTH: 90px" onclick="Button4_onclick()" type="button" value="查找对话框">
		<INPUT id="Button3" style="WIDTH: 90px" onclick="Button5_onclick()" type="button" value="excel导入">
		<INPUT id="Button1" onclick="printsheet()" type="button" value=" 打印 " name="Button1">
		<INPUT id="Button3" onclick="location.href='depositCheckAccShowAC.do'" type="button" value=" 返回 ">		
	</tr>
</table>
</form>
</body>
</html>
