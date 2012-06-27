<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.util.List"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic"%>
<%@ page import="org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.particulargl.form.ParticularglTaAF"%>

<%@ page import="org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.particulargl.dto.ParticularglDTO"%>
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
	document.forms(0).Cell1.insertSheetFromFile("<%=path%>/sysloan/querystatistics/report/particularglta.cll",0,1,1);
    document.forms(0).Cell1.setSheetLabel(1,"第2页");
    document.forms(0).Cell1.PrintSetCustomPaper(2200,2800,1);
    document.forms(0).Cell1.PrintSetMargin(150,100,150,100);
    document.forms(0).Cell1.PrintSetAlign(1,0);

  var ocyear=[];
  var firstcorpus=[];
  var thisborrower=[];
  var thispaymoney=[];
  var thisinterest=[];
  var thispunishinterest=[];
  var thisloanrepay=[];
  var thisballbalance=[];
  var thisbaddebtmoney=[];
  var lastcorpus=[];

  var i=0;

	<%
			String opertname=(String)request.getAttribute("opertname");
			String time=(String)request.getAttribute("time");
    	    ParticularglTaAF particularglTaAF =(ParticularglTaAF)request.getAttribute("particularglTaAF");
                     List taillist = particularglTaAF.getAlllist();
	%>

			<%
  			for(int j=0;j<taillist.size();j++)
  			{
  			ParticularglDTO dto=(ParticularglDTO)taillist.get(j);
  				
 	%>
				
				 ocyear[i]="<%=dto.getOcyear() %>";
				 firstcorpus[i]="<%=dto.getFirstcorpus() %>";
				 thisborrower[i]="<%=dto.getThisborrower() %>";
				 thispaymoney[i]="<%=dto.getThispaymoney() %>";
				 thisinterest[i]="<%=dto.getThisinterest() %>";
				 thispunishinterest[i]="<%=dto.getThispunishinterest() %>";
				 thisloanrepay[i]="<%=dto.getThisloanrepay() %>";
				 thisballbalance[i]="<%=dto.getThisballbalance() %>";
				 thisbaddebtmoney[i]="<%=dto.getThisbaddebtmoney() %>";
				 lastcorpus[i]="<%=dto.getLastcorpus() %>"; 
			    
 	          	i++; 
 	<%
 			}
 	%>
		     document.forms(0).Cell1.S(2,3,0,"<%=particularglTaAF.getContractid() %>");
		     document.forms(0).Cell1.S(2,4,0,"<%=particularglTaAF.getBorrowername() %>");
		     document.forms(0).Cell1.S(2,5,0,"<%=particularglTaAF.getLoanbank() %>");
		     document.forms(0).Cell1.S(2,6,0,"<%=particularglTaAF.getLoanlimit() %>");
		     document.forms(0).Cell1.d(2,7,0,"<%=particularglTaAF.getOverplusloanmoney() %>");
		     document.forms(0).Cell1.d(2,8,0,"<%=particularglTaAF.getOveaerloanrepay() %>");
		     document.forms(0).Cell1.d(2,9,0,"<%=particularglTaAF.getSrealcorpus() %>");
		     document.forms(0).Cell1.d(2,10,0,"<%=particularglTaAF.getSrealpunishinterest() %>");
		     document.forms(0).Cell1.d(2,11,0,"<%=particularglTaAF.getOweinterest() %>");
		     document.forms(0).Cell1.S(2,12,0,"<%=opertname %>");
		    
		      document.forms(0).Cell1.S(4,3,0,"<%=particularglTaAF.getLoankouacc() %>");
		      document.forms(0).Cell1.S(4,4,0,"<%=particularglTaAF.getCardnum() %>");
		      document.forms(0).Cell1.d(4,5,0,"<%=particularglTaAF.getLoanmoney() %>");
		      document.forms(0).Cell1.S(4,6,0,"<%=particularglTaAF.getLoanmode() %>");
		      document.forms(0).Cell1.d(4,7,0,"<%=particularglTaAF.getNobackmoney() %>");
		      document.forms(0).Cell1.d(4,8,0,"<%=particularglTaAF.getBallbalance() %>");
		      document.forms(0).Cell1.d(4,9,0,"<%=particularglTaAF.getSrealinterest() %>");
		      document.forms(0).Cell1.d(4,10,0,"<%=particularglTaAF.getOwercorpus() %>");
		      document.forms(0).Cell1.d(4,11,0,"<%=particularglTaAF.getOwepunishinterest() %>");
		     
		      document.forms(0).Cell1.S(4,12,0,"<%=time %>");
		      
		      
		     
	    var totalLine=ocyear.length;			//总的行数 数组的长度
		var totalPageLine=31;					//每页显示多少行
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
				document.forms(0).Cell1.SetFormula (11, totalPageLine+4, pageCurrent, "Sum(K4:K"+(totalPageLine+3)+")" );
				document.forms(0).Cell1.SetFormula (12, totalPageLine+4, pageCurrent, "Sum(L4:L"+(totalPageLine+3)+")" );
				document.forms(0).Cell1.SetFormula (13, totalPageLine+4, pageCurrent, "Sum(M4:M"+(totalPageLine+3)+")" );
				document.forms(0).Cell1.SetFormula (14, totalPageLine+4, pageCurrent, "Sum(N4:N"+(totalPageLine+3)+")" );
				
				pageCurrent++;	
				completeline=k-2;				
				document.forms(0).Cell1.insertSheetFromFile("<%=path%>/sysloan/querystatistics/report/particularglta.cll",0,1,pageCurrent);
				document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页");
			    document.forms(0).Cell1.PrintSetCustomPaper(2200,2800,pageCurrent);
			    document.forms(0).Cell1.PrintSetMargin(150,100,150,100);
			    document.forms(0).Cell1.PrintSetAlign(1,0);
			}
			if(pageCurrent==1)
			{		
				document.forms(0).Cell1.S(1,k+4,1,ocyear[k]);
				document.forms(0).Cell1.d(2,k+4,1,firstcorpus[k]);
				document.forms(0).Cell1.d(3,k+4,1,thisborrower[k]);
				document.forms(0).Cell1.d(4,k+4,1,thispaymoney[k]);
				document.forms(0).Cell1.d(5,k+4,1,thisinterest[k]);
				document.forms(0).Cell1.d(6,k+4,1,thispunishinterest[k]);
				document.forms(0).Cell1.d(7,k+4,1,thisloanrepay[k]);
				document.forms(0).Cell1.d(8,k+4,1,thisballbalance[k]);
				document.forms(0).Cell1.d(9,k+4,1,thisbaddebtmoney[k]);
				document.forms(0).Cell1.d(10,k+4,1,lastcorpus[k]);
				
		
				moneytotal1=moneytotal1+parseFloat(firstcorpus[k]);
				moneytotal2=moneytotal2+parseFloat(thisborrower[k]);
				moneytotal3=moneytotal3+parseFloat(thispaymoney[k]);
				moneytotal4=moneytotal4+parseFloat(thisinterest[k]);
				moneytotal5=moneytotal5+parseFloat(thispunishinterest[k]);
				moneytotal6=moneytotal6+parseFloat(thisloanrepay[k]);
				moneytotal7=moneytotal7+parseFloat(thisballbalance[k]);
				moneytotal8=moneytotal8+parseFloat(thisbaddebtmoney[k]);
				moneytotal9=moneytotal9+parseFloat(lastcorpus[k]);
				
			}else{//向第一页后的所有页插数据
				document.forms(0).Cell1.S(1,k-completeline+2,pageCurrent,ocyear[k]);
				document.forms(0).Cell1.d(2,k-completeline+2,pageCurrent,thisborrower[k]);
				document.forms(0).Cell1.d(3,k-completeline+2,pageCurrent,thisborrower[k]);
				document.forms(0).Cell1.d(4,k-completeline+2,pageCurrent,thispaymoney[k]);
				document.forms(0).Cell1.d(5,k-completeline+2,pageCurrent,thisinterest[k]);
				document.forms(0).Cell1.d(6,k-completeline+2,pageCurrent,thispunishinterest[k]);
				document.forms(0).Cell1.d(7,k-completeline+2,pageCurrent,thisloanrepay[k]);
				document.forms(0).Cell1.d(8,k-completeline+2,pageCurrent,thisballbalance[k]);
				document.forms(0).Cell1.d(9,k-completeline+2,pageCurrent,thisbaddebtmoney[k]);
				document.forms(0).Cell1.d(10,k-completeline+2,pageCurrent,lastcorpus[k]);
				
				moneytotal1=moneytotal1+parseFloat(firstcorpus[k]);
				moneytotal2=moneytotal2+parseFloat(thisborrower[k]);
				moneytotal3=moneytotal3+parseFloat(thispaymoney[k]);
				moneytotal4=moneytotal4+parseFloat(thisinterest[k]);
				moneytotal5=moneytotal5+parseFloat(thispunishinterest[k]);
				moneytotal6=moneytotal6+parseFloat(thisloanrepay[k]);
				moneytotal7=moneytotal7+parseFloat(thisballbalance[k]);
				moneytotal8=moneytotal8+parseFloat(thisbaddebtmoney[k]);
				moneytotal9=moneytotal9+parseFloat(lastcorpus[k]);
			}
		}
				if(completeline==0)//查询出来的记录可以在一页显示的时候会进入
				{
					document.forms(0).Cell1.S(1,totalLine+4,pageCurrent,"本页小计");
					document.forms(0).Cell1.SetFormula (2, totalLine+4, pageCurrent, "Sum(B4:B"+(totalLine+3)+")" );
					document.forms(0).Cell1.SetFormula (3, totalLine+4, pageCurrent, "Sum(C4:C"+(totalLine+3)+")" );
					document.forms(0).Cell1.SetFormula (4, totalLine+4, pageCurrent, "Sum(D4:D"+(totalLine+3)+")" );
					document.forms(0).Cell1.SetFormula (5, totalLine+4, pageCurrent, "Sum(E4:E"+(totalLine+3)+")" );
					document.forms(0).Cell1.SetFormula (6, totalLine+4, pageCurrent, "Sum(F4:F"+(totalLine+3)+")" );
					document.forms(0).Cell1.SetFormula (7, totalLine+4, pageCurrent, "Sum(G4:G"+(totalLine+3)+")" );
					document.forms(0).Cell1.SetFormula (8, totalLine+4, pageCurrent, "Sum(H4:H"+(totalLine+3)+")" );
					document.forms(0).Cell1.SetFormula (9, totalLine+4, pageCurrent, "Sum(I4:I"+(totalLine+3)+")" );
					document.forms(0).Cell1.SetFormula (10, totalLine+4, pageCurrent, "Sum(J4:J"+(totalLine+3)+")" );
			
					document.forms(0).Cell1.S(1,totalLine+5,pageCurrent,"总计");
					document.forms(0).Cell1.SetFormula (2, totalLine+5, pageCurrent, "Sum(B4:B"+(totalLine+3)+")" );
					document.forms(0).Cell1.SetFormula (3, totalLine+5, pageCurrent, "Sum(C4:C"+(totalLine+3)+")" );
					document.forms(0).Cell1.SetFormula (4, totalLine+5, pageCurrent, "Sum(D4:D"+(totalLine+3)+")" );
					document.forms(0).Cell1.SetFormula (5, totalLine+5, pageCurrent, "Sum(E4:E"+(totalLine+3)+")" );
					document.forms(0).Cell1.SetFormula (6, totalLine+5, pageCurrent, "Sum(F4:F"+(totalLine+3)+")" );
					document.forms(0).Cell1.SetFormula (7, totalLine+5, pageCurrent, "Sum(G4:G"+(totalLine+3)+")" );
					document.forms(0).Cell1.SetFormula (8, totalLine+5, pageCurrent, "Sum(H4:H"+(totalLine+3)+")" );
					document.forms(0).Cell1.SetFormula (9, totalLine+5, pageCurrent, "Sum(I4:I"+(totalLine+3)+")" );
					document.forms(0).Cell1.SetFormula (10, totalLine+5, pageCurrent, "Sum(J4:J"+(totalLine+3)+")" );
					
					document.forms(0).Cell1.S(7,totalLine+6,pageCurrent,"操作员");
					document.forms(0).Cell1.S(8,totalLine+6,pageCurrent,"<%=opertname %>");
					document.forms(0).Cell1.S(9,totalLine+6,pageCurrent,"操作时间");
					document.forms(0).Cell1.S(10,totalLine+6,pageCurrent,"<%=time %>");
					
					document.forms(0).Cell1.DeleteRow(totalLine+7,35-totalLine-6,pageCurrent);
					document.forms(0).Cell1.ReDraw();
				}
				else
				{
					document.forms(0).Cell1.S(1,totalLine-completeline+2,pageCurrent,"本页小计");
					document.forms(0).Cell1.SetFormula (2, totalLine-completeline+2, pageCurrent, "Sum(B4:B"+(totalLine-(completeline+3))+")");
					document.forms(0).Cell1.SetFormula (3, totalLine-completeline+2, pageCurrent, "Sum(C4:C"+(totalLine-(completeline+3))+")");
					document.forms(0).Cell1.SetFormula (4, totalLine-completeline+2, pageCurrent, "Sum(D4:D"+(totalLine-(completeline+3))+")");
					document.forms(0).Cell1.SetFormula (5, totalLine-completeline+2, pageCurrent, "Sum(E4:E"+(totalLine-(completeline+3))+")");
					document.forms(0).Cell1.SetFormula (6, totalLine-completeline+2, pageCurrent, "Sum(F4:F"+(totalLine-(completeline+3))+")");
					document.forms(0).Cell1.SetFormula (7, totalLine-completeline+2, pageCurrent, "Sum(G4:G"+(totalLine-(completeline+3))+")");
					document.forms(0).Cell1.SetFormula (8, totalLine-completeline+2, pageCurrent, "Sum(H4:H"+(totalLine-(completeline+3))+")");
					document.forms(0).Cell1.SetFormula (9, totalLine-completeline+2, pageCurrent, "Sum(I4:I"+(totalLine-(completeline+3))+")");
					document.forms(0).Cell1.SetFormula (10, totalLine-completeline+2, pageCurrent, "Sum(J4:J"+(totalLine-(completeline+3))+")");
				
					document.forms(0).Cell1.S(1,totalLine-completeline+3,pageCurrent,"总计");
					document.forms(0).Cell1.d(2,totalLine-completeline+3,pageCurrent,moneytotal1);
					document.forms(0).Cell1.d(3,totalLine-completeline+3,pageCurrent,moneytotal2);
					document.forms(0).Cell1.d(4,totalLine-completeline+3,pageCurrent,moneytotal3);
					document.forms(0).Cell1.d(5,totalLine-completeline+3,pageCurrent,moneytotal4);
					document.forms(0).Cell1.d(6,totalLine-completeline+3,pageCurrent,moneytotal5);
					document.forms(0).Cell1.d(7,totalLine-completeline+3,pageCurrent,moneytotal6);
					document.forms(0).Cell1.d(8,totalLine-completeline+3,pageCurrent,moneytotal7);
					document.forms(0).Cell1.d(9,totalLine-completeline+3,pageCurrent,moneytotal8);
					document.forms(0).Cell1.d(10,totalLine-completeline+3,pageCurrent,moneytotal9);
					
					document.forms(0).Cell1.S(7,totalLine-completeline+3,pageCurrent,"操作员");
					document.forms(0).Cell1.S(8,totalLine-completeline+3,pageCurrent,"<%=opertname %>");
					document.forms(0).Cell1.S(9,totalLine-completeline+3,pageCurrent,"操作时间");
					document.forms(0).Cell1.S(10,totalLine-completeline+3,pageCurrent,"<%=time %>");
				
				
					 document.forms(0).Cell1.DeleteRow(totalLine-completeline+5,35-(totalLine-completeline+4),pageCurrent);
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
  <body onload = "load();"> 
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
<td><INPUT id="Button3" onclick="location.href='particularglTcShowAC.do'" type="button" value=" 返回 "></td>	
</table>
</form>
  </body>
</html>
