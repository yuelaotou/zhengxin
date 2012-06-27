<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.List" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic" %>
<%@ page import="org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.orginandoutschedule.dto.*" %>
<%@ page import="org.xpup.hafmis.common.util.BusiTools" %>
<%@ include file="/checkUrl.jsp"%>

<%
   String path=request.getContextPath();
   List list=(List) request.getAttribute("printlist");
 %>
<html>
<head>
<script src="<%=path%>/js/common.js"></script>
</head>
<script type="text/javascript">
	function load(){
	loginReg();	
	document.forms(0).Cell1.openfile("<%=path%>/syscollection/querystatistics/paymentpickstatistics/orginandoutschedule/print/orginandoutschedule.cll","");
	var orgId=[];
			var orgName=[];
			var jzBalance=[];
			var jzgzBalance=[];
			var jzzmBalance=[];
			var gzPay=[];
			var payment=[];
			var addPay=[];
			var adjustAccount=[];
			var tranIn=[];
			var interest=[];
			var pick=[];
			var tranOut=[];
			var currentBalance=[];
			var gzBalance=[];
			var zmBalance=[];
			var i=0;
            <%
				ListDTO listDTO = new ListDTO();
				for(int j=0;j<list.size();j++){
				listDTO = (ListDTO)list.get(j);
				String tempid = listDTO.getOrgid().toString();
				String orgid = 	BusiTools.convertTenNumber(tempid);
			%>
			    //把数据传到JS的数组里面..
				orgId[i] = "<%=orgid%>";
				orgName[i] = "<%=listDTO.getOrgname()%>"; 
				jzBalance[i] = "<%=listDTO.getJzbalance()%>";
				jzgzBalance[i] = "<%=listDTO.getJzgzbalance()%>";
				jzzmBalance[i] = "<%=listDTO.getJzzmbalance()%>";
				gzPay[i] = "<%=listDTO.getGzpay()%>";
				payment[i] = "<%=listDTO.getPayment()%>";
				addPay[i] = "<%=listDTO.getAddpay()%>";
				adjustAccount[i] = "<%=listDTO.getAdjustaccount()%>";
				tranIn[i] = "<%=listDTO.getTanin()%>";
				interest[i] = "<%=listDTO.getInterest()%>";
				pick[i] = "<%=listDTO.getPick()%>";
				tranOut[i] = "<%=listDTO.getTranout()%>";
				currentBalance[i] = "<%=listDTO.getCurrentbalance()%>";
				gzBalance[i] = "<%=listDTO.getGzbalance()%>";
				zmBalance[i] = "<%=listDTO.getZmbalance()%>";
				i++;
			<%}%>
			var totalLine=orgId.length;				//总的行数 数组的长度
			var totalPageLine=40;					//每页显示多少行--除了第一行
			var pageTotal=totalLine/totalPageLine;	//总共分几页 总行数/每页的行数
			var pageCurrent=0;						//当前页
			var completeline=0;						//记录行的
			var jzbalancetotal=0;					//结转余额合计
			var jzgzbalancetotal=0;					//结转挂帐余额合计
			var jzzmbalancetotal=0;					//结转账面余额合计
			var gzpaytotal=0;						//挂帐金额合计
			var paymenttotal=0;						//汇缴合计
			var addpaytotal=0;						//补缴合计
			var adjustaccounttotal=0;				//调帐合计
			var tranintotal=0;						//转入合计
			var interesttotal=0;					//利息合计
			var picktotal=0;						//提取合计
			var tranouttotal=0;						//转出合计
			var currentbalancetotal=0;				//当前余额合计
			var gzbalancetotal=0;					//挂帐余额合计
			var zmbalancetotal=0;					//账面余额合计
			for(k = 0 ; k < totalLine; k++){
				if(k%totalPageLine==0&&k>0)
				{
					document.forms(0).Cell1.S(1,totalPageLine+4,pageCurrent,"本页小计");
					document.forms(0).Cell1.SetFormula (3, totalPageLine+4, pageCurrent, "Sum(C4:C"+(totalPageLine+3)+")" );
					document.forms(0).Cell1.SetFormula (4, totalPageLine+4, pageCurrent, "Sum(D4:D"+(totalPageLine+3)+")" );
					document.forms(0).Cell1.SetFormula (5, totalPageLine+4, pageCurrent, "Sum(E4:E"+(totalPageLine+3)+")" );
					document.forms(0).Cell1.SetFormula (6, totalPageLine+4, pageCurrent, "Sum(F4:F"+(totalPageLine+3)+")" );
					document.forms(0).Cell1.SetFormula (7, totalPageLine+4, pageCurrent, "Sum(G4:G"+(totalPageLine+3)+")" );
					document.forms(0).Cell1.SetFormula (8, totalPageLine+4, pageCurrent, "Sum(H4:H"+(totalPageLine+3)+")" );
					document.forms(0).Cell1.SetFormula (9, totalPageLine+4, pageCurrent, "Sum(I4:I"+(totalPageLine+3)+")" );
					document.forms(0).Cell1.SetFormula (10, totalPageLine+4, pageCurrent, "Sum(J4:J"+(totalPageLine+3)+")" );
					document.forms(0).Cell1.SetFormula (11, totalPageLine+4, pageCurrent, "Sum(K4:K"+(totalPageLine+3)+")" );
					document.forms(0).Cell1.SetFormula (12, totalPageLine+4, pageCurrent, "Sum(L4:L"+(totalPageLine+3)+")" );
					document.forms(0).Cell1.SetFormula (13, totalPageLine+4, pageCurrent, "Sum(M4:M"+(totalPageLine+3)+")" );
					document.forms(0).Cell1.SetFormula (14, totalPageLine+4, pageCurrent, "Sum(N4:N"+(totalPageLine+3)+")" );
					document.forms(0).Cell1.SetFormula (15, totalPageLine+4, pageCurrent, "Sum(O4:O"+(totalPageLine+3)+")" );
					document.forms(0).Cell1.SetFormula (16, totalPageLine+4, pageCurrent, "Sum(P4:P"+(totalPageLine+3)+")" );
					document.forms(0).Cell1.ReDraw();//重画一个表格
					pageCurrent++;//当前页++	
					completeline=k-2;
					//绘制标签 param 	表页索引号。param 要设置的表页页签名称					
					document.forms(0).Cell1.insertSheetFromFile("<%=path%>/syscollection/querystatistics/paymentpickstatistics/orginandoutschedule/print/orginandoutschedule.cll",0,1,pageCurrent);
					document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页")	
				}
				if(pageCurrent==0)
				{	
					document.forms(0).Cell1.S(1,k+4,0,orgId[k]);
					document.forms(0).Cell1.S(2,k+4,0,orgName[k]);
					document.forms(0).Cell1.d(3,k+4,0,jzBalance[k]);
					document.forms(0).Cell1.d(4,k+4,0,jzgzBalance[k]);
					document.forms(0).Cell1.d(5,k+4,0,jzzmBalance[k]);
					document.forms(0).Cell1.d(6,k+4,0,gzPay[k]);
					document.forms(0).Cell1.d(7,k+4,0,payment[k]);
					document.forms(0).Cell1.d(8,k+4,0,addPay[k]);
					document.forms(0).Cell1.d(9,k+4,0,adjustAccount[k]);
					document.forms(0).Cell1.d(10,k+4,0,tranIn[k]);
					document.forms(0).Cell1.d(11,k+4,0,interest[k]);
					document.forms(0).Cell1.d(12,k+4,0,pick[k]);
					document.forms(0).Cell1.d(13,k+4,0,tranOut[k]);
					document.forms(0).Cell1.d(14,k+4,0,currentBalance[k]);
					document.forms(0).Cell1.d(15,k+4,0,gzBalance[k]);
					document.forms(0).Cell1.d(16,k+4,0,zmBalance[k]);
					jzbalancetotal=jzbalancetotal+parseFloat(jzBalance[k]);
					jzgzbalancetotal=jzgzbalancetotal+parseFloat(jzgzBalance[k]);
					jzzmbalancetotal=jzzmbalancetotal+parseFloat(jzzmBalance[k]);
					gzpaytotal=gzpaytotal+parseFloat(gzPay[k]);
					paymenttotal=paymenttotal+parseFloat(payment[k]);
					addpaytotal=addpaytotal+parseFloat(addPay[k]);
					adjustaccounttotal=adjustaccounttotal+parseFloat(adjustAccount[k]);
					tranintotal=tranintotal+parseFloat(tranIn[k]);
					interesttotal=interesttotal+parseFloat(interest[k]);
					picktotal=picktotal+parseFloat(pick[k]);
					tranouttotal=tranouttotal+parseFloat(tranOut[k]);
					currentbalancetotal=currentbalancetotal+parseFloat(currentBalance[k]);
					gzbalancetotal=gzbalancetotal+parseFloat(gzBalance[k]);
					zmbalancetotal=zmbalancetotal+parseFloat(zmBalance[k]);	
					}
				else{//向第一页后的所有页插数据
					document.forms(0).Cell1.S(1,k-completeline+2,pageCurrent,orgId[k]);
					document.forms(0).Cell1.S(2,k-completeline+2,pageCurrent,orgName[k]);
					document.forms(0).Cell1.d(3,k-completeline+2,pageCurrent,jzBalance[k]);
					document.forms(0).Cell1.d(4,k-completeline+2,pageCurrent,jzgzBalance[k]);
					document.forms(0).Cell1.d(5,k-completeline+2,pageCurrent,jzzmBalance[k]);
					document.forms(0).Cell1.d(6,k-completeline+2,pageCurrent,gzPay[k]);
					document.forms(0).Cell1.d(7,k-completeline+2,pageCurrent,payment[k]);
					document.forms(0).Cell1.d(8,k-completeline+2,pageCurrent,addPay[k]);
					document.forms(0).Cell1.d(9,k-completeline+2,pageCurrent,adjustAccount[k]);
					document.forms(0).Cell1.d(10,k-completeline+2,pageCurrent,tranIn[k]);
					document.forms(0).Cell1.d(11,k-completeline+2,pageCurrent,interest[k]);
					document.forms(0).Cell1.d(12,k-completeline+2,pageCurrent,pick[k]);
					document.forms(0).Cell1.d(13,k-completeline+2,pageCurrent,tranOut[k]);
					document.forms(0).Cell1.d(14,k-completeline+2,pageCurrent,currentBalance[k]);
					document.forms(0).Cell1.d(15,k-completeline+2,pageCurrent,gzBalance[k]);
					document.forms(0).Cell1.d(16,k-completeline+2,pageCurrent,zmBalance[k]);
					jzbalancetotal=jzbalancetotal+parseFloat(jzBalance[k]);
					jzgzbalancetotal=jzgzbalancetotal+parseFloat(jzgzBalance[k]);
					jzzmbalancetotal=jzzmbalancetotal+parseFloat(jzzmBalance[k]);
					gzpaytotal=gzpaytotal+parseFloat(gzPay[k]);
					paymenttotal=paymenttotal+parseFloat(payment[k]);
					addpaytotal=addpaytotal+parseFloat(addPay[k]);
					adjustaccounttotal=adjustaccounttotal+parseFloat(adjustAccount[k]);
					tranintotal=tranintotal+parseFloat(tranIn[k]);
					interesttotal=interesttotal+parseFloat(interest[k]);
					picktotal=picktotal+parseFloat(pick[k]);
					tranouttotal=tranouttotal+parseFloat(tranOut[k]);
					currentbalancetotal=currentbalancetotal+parseFloat(currentBalance[k]);
					gzbalancetotal=gzbalancetotal+parseFloat(gzBalance[k]);
					zmbalancetotal=zmbalancetotal+parseFloat(zmBalance[k]);
					
				}		
			}
			
			if(completeline==0)//查询出来的记录可以在一页显示的时候会进入
				{
					document.forms(0).Cell1.S(1,totalLine+4,pageCurrent,"本页小计");
					document.forms(0).Cell1.SetFormula (3, totalLine+4, pageCurrent, "Sum(C4:C"+(totalLine+3)+")" );
					document.forms(0).Cell1.SetFormula (4, totalLine+4, pageCurrent, "Sum(D4:D"+(totalLine+3)+")" );
					document.forms(0).Cell1.SetFormula (5, totalLine+4, pageCurrent, "Sum(E4:E"+(totalLine+3)+")" );
					document.forms(0).Cell1.SetFormula (6, totalLine+4, pageCurrent, "Sum(F4:F"+(totalLine+3)+")" );
					document.forms(0).Cell1.SetFormula (7, totalLine+4, pageCurrent, "Sum(G4:G"+(totalLine+3)+")" );
					document.forms(0).Cell1.SetFormula (8, totalLine+4, pageCurrent, "Sum(H4:H"+(totalLine+3)+")" );
					document.forms(0).Cell1.SetFormula (9, totalLine+4, pageCurrent, "Sum(I4:I"+(totalLine+3)+")" );
					document.forms(0).Cell1.SetFormula (10, totalLine+4, pageCurrent, "Sum(J4:J"+(totalLine+3)+")" );
					document.forms(0).Cell1.SetFormula (11, totalLine+4, pageCurrent, "Sum(K4:K"+(totalLine+3)+")" );
					document.forms(0).Cell1.SetFormula (12, totalLine+4, pageCurrent, "Sum(L4:L"+(totalLine+3)+")" );
					document.forms(0).Cell1.SetFormula (13, totalLine+4, pageCurrent, "Sum(M4:M"+(totalLine+3)+")" );
					document.forms(0).Cell1.SetFormula (14, totalLine+4, pageCurrent, "Sum(N4:N"+(totalLine+3)+")" );
					document.forms(0).Cell1.SetFormula (15, totalLine+4, pageCurrent, "Sum(O4:O"+(totalLine+3)+")" );
					document.forms(0).Cell1.SetFormula (16, totalLine+4, pageCurrent, "Sum(P4:P"+(totalLine+3)+")" );
					document.forms(0).Cell1.S(1,totalLine+5,pageCurrent,"总计");
					document.forms(0).Cell1.d(3,totalLine+5,pageCurrent,jzbalancetotal);
					document.forms(0).Cell1.d(4,totalLine+5,pageCurrent,jzgzbalancetotal);
					document.forms(0).Cell1.d(5,totalLine+5,pageCurrent,jzzmbalancetotal);
					document.forms(0).Cell1.d(6,totalLine+5,pageCurrent,gzpaytotal);
					document.forms(0).Cell1.d(7,totalLine+5,pageCurrent,paymenttotal);
					document.forms(0).Cell1.d(8,totalLine+5,pageCurrent,addpaytotal);
					document.forms(0).Cell1.d(9,totalLine+5,pageCurrent,adjustaccounttotal);
					document.forms(0).Cell1.d(10,totalLine+5,pageCurrent,tranintotal);
					document.forms(0).Cell1.d(11,totalLine+5,pageCurrent,interesttotal);
					document.forms(0).Cell1.d(12,totalLine+5,pageCurrent,picktotal);
					document.forms(0).Cell1.d(13,totalLine+5,pageCurrent,tranouttotal);
					document.forms(0).Cell1.d(14,totalLine+5,pageCurrent,currentbalancetotal);
					document.forms(0).Cell1.d(15,totalLine+5,pageCurrent,gzbalancetotal);
					document.forms(0).Cell1.d(16,totalLine+5,pageCurrent,zmbalancetotal);
					document.forms(0).Cell1.DeleteRow(totalLine+6,totalPageLine-(totalLine+5),pageCurrent);
					document.forms(0).Cell1.ReDraw();
				}	
				else
				{
					document.forms(0).Cell1.S(1,totalLine-completeline+2,pageCurrent,"本页小计");   
					//F1 第F列的第1行: N9 到第N列的第9行  求和
					document.forms(0).Cell1.SetFormula (3, totalLine-completeline+2, pageCurrent, "Sum(C4:C"+(totalLine-(completeline-1))+")" );
					document.forms(0).Cell1.SetFormula (4, totalLine-completeline+2, pageCurrent, "Sum(D4:D"+(totalLine-(completeline-1))+")" );
					document.forms(0).Cell1.SetFormula (5, totalLine-completeline+2, pageCurrent, "Sum(E4:E"+(totalLine-(completeline-1))+")" );
					document.forms(0).Cell1.SetFormula (6, totalLine-completeline+2, pageCurrent, "Sum(F4:F"+(totalLine-(completeline-1))+")" );
					document.forms(0).Cell1.SetFormula (7, totalLine-completeline+2, pageCurrent, "Sum(G4:G"+(totalLine-(completeline-1))+")" );
					document.forms(0).Cell1.SetFormula (8, totalLine-completeline+2, pageCurrent, "Sum(H4:H"+(totalLine-(completeline-1))+")" );
					document.forms(0).Cell1.SetFormula (9, totalLine-completeline+2, pageCurrent, "Sum(I4:I"+(totalLine-(completeline-1))+")" );
					document.forms(0).Cell1.SetFormula (10, totalLine-completeline+2, pageCurrent, "Sum(J4:J"+(totalLine-(completeline-1))+")" );
					document.forms(0).Cell1.SetFormula (11, totalLine-completeline+2, pageCurrent, "Sum(K4:K"+(totalLine-(completeline-1))+")" );
					document.forms(0).Cell1.SetFormula (12, totalLine-completeline+2, pageCurrent, "Sum(L4:L"+(totalLine-(completeline-1))+")" );
					document.forms(0).Cell1.SetFormula (13, totalLine-completeline+2, pageCurrent, "Sum(M4:M"+(totalLine-(completeline-1))+")" );
					document.forms(0).Cell1.SetFormula (14, totalLine-completeline+2, pageCurrent, "Sum(N4:N"+(totalLine-(completeline-1))+")" );
					document.forms(0).Cell1.SetFormula (15, totalLine-completeline+2, pageCurrent, "Sum(O4:O"+(totalLine-(completeline-1))+")" );
					document.forms(0).Cell1.SetFormula (16, totalLine-completeline+2, pageCurrent, "Sum(P4:P"+(totalLine-(completeline-1))+")" );
					document.forms(0).Cell1.S(1,totalLine-completeline+3,pageCurrent,"总计");
					document.forms(0).Cell1.d(3,totalLine-completeline+3,pageCurrent,jzbalancetotal);
					document.forms(0).Cell1.d(4,totalLine-completeline+3,pageCurrent,jzgzbalancetotal);
					document.forms(0).Cell1.d(5,totalLine-completeline+3,pageCurrent,jzzmbalancetotal);
					document.forms(0).Cell1.d(6,totalLine-completeline+3,pageCurrent,gzpaytotal);
					document.forms(0).Cell1.d(7,totalLine-completeline+3,pageCurrent,paymenttotal);
					document.forms(0).Cell1.d(8,totalLine-completeline+3,pageCurrent,addpaytotal);
					document.forms(0).Cell1.d(9,totalLine-completeline+3,pageCurrent,adjustaccounttotal);
					document.forms(0).Cell1.d(10,totalLine-completeline+3,pageCurrent,tranintotal);
					document.forms(0).Cell1.d(11,totalLine-completeline+3,pageCurrent,interesttotal);
					document.forms(0).Cell1.d(12,totalLine-completeline+3,pageCurrent,picktotal);
					document.forms(0).Cell1.d(13,totalLine-completeline+3,pageCurrent,tranouttotal);
					document.forms(0).Cell1.d(14,totalLine-completeline+3,pageCurrent,currentbalancetotal);
					document.forms(0).Cell1.d(15,totalLine-completeline+3,pageCurrent,gzbalancetotal);
					document.forms(0).Cell1.d(16,totalLine-completeline+3,pageCurrent,zmbalancetotal);
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

<body  onContextmenu="return false" onload = "load();"> 
<form action="">
<table align="center">
	<tr><OBJECT id=Cell1 style= "LEFT:0px;WIDTH:700px;  TOP:0px;HEIGHT:400px" codeBase="http://192.168.1.44:8088/hafmis/CellWeb5.CAB#version=5,3,7,321" classid=clsid:3F166327-8030-4881-8BD2-EA25350E574A VIEWASTEXT><PARAM NAME="_Version" VALUE="65536"><PARAM NAME="_ExtentX" VALUE="10266"><PARAM NAME="_ExtentY" VALUE="7011"><PARAM NAME="_StockProps" VALUE= "0"></OBJECT></tr> 
	<tr><input type="button" name="print" value = "打印预览" onclick = "printPreview();"/>
		<INPUT id="Button1" onclick="Button1_onclick()" type="button" value="另存为Excel" name="Button1">
		<INPUT id="Button1" onclick="Button2_onclick()" type="button" value="另存为pdf" name="Button1">
		<INPUT id="Button3" style="WIDTH: 100px" onclick="Button3_onclick()" type="button" value="页面设置">
		<INPUT id="Button3" style="WIDTH: 100px" onclick="Button4_onclick()" type="button" value="查找对话框">
		<INPUT id="Button3" style="WIDTH: 100px" onclick="Button5_onclick()" type="button" value="excel导入">
		<INPUT id="Button1" onclick="printsheet()" type="button" value=" 打印 " name="Button1">
		<INPUT id="Button3" onclick="javascript:history.back();" type="button" value=" 返回 ">	
	</tr>
</table>
</form>
</body>
</html>
