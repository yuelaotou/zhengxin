<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic"%>
<%@ page import="org.xpup.hafmis.sysloan.loancallback.loancallback.dto.ShouldBackListDTO"%>
<%@ page import="java.util.List"%>
<%@ page import="java.math.BigDecimal"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	String makePerson = (String)request.getAttribute("makePerson");
	String bizDates = (String)request.getAttribute("bizDate");
	String url=(String)request.getAttribute("URL");
	String bankName=(String)request.getAttribute("bankName");
	
	if(url==null){
		url="javascript:history.back();";
	}
%>
<html>
	<head>
		<script src="<%=path%>/js/common.js">
</script>
	</head>
	<script type="text/javascript">
	function load(){
	loginReg();
	document.forms(0).Cell1.openfile("<%=path%>/sysloan/loancallback/report/loancalbackBatchList.cll","");
    var yearMonth=[];
    var loanKouAcc=[];
    var contractId=[];
    var nameList=[];
	var cardNum=[];
	var shouldCorpus=[];
	var shouldInterest=[];
	var shouldPunishInterest=[];
	var realCorpus=[];
	var realInterest=[];
	var realPunishInterest=[];
	var realMoney=[];
	var occurMoney=[];
	var money=[];
	var loanType=[];
	var days=[];
	var i=0;

	<%
    	    List list =(List)request.getAttribute("batchList");
	%>
			<%
  			for(int j=0;j<list.size();j++)
  			{
  				ShouldBackListDTO dto=(ShouldBackListDTO)list.get(j);
  				if(dto.getRealCorpus().compareTo(new BigDecimal(0))!=0){
  				
 	%>
				     loanKouAcc[i]="<%=dto.getLoanKouAcc()%>";
				     contractId[i]="<%=dto.getContractId()%>";
				     nameList[i]="<%=dto.getBorrowerName()%>";
					 cardNum[i]="<%=dto.getCardNum()%>";
					 loanType[i]="<%=dto.getLoanKouType()%>";
					 yearMonth[i]="<%=dto.getLoanKouYearmonth()%>";
					 shouldCorpus[i]="<%=dto.getShouldCorpus()%>";
					 shouldInterest[i]="<%=dto.getShouldInterest()%>";
					 shouldPunishInterest[i]="<%=dto.getShouldPunishInterest()%>";
					 realCorpus[i]="<%=dto.getRealCorpus()%>";
					 realInterest[i]="<%=dto.getRealInterest()%>";
					 realPunishInterest[i]="<%=dto.getRealPunishInterest()%>";
					 days[i]="<%=dto.getDays()%>";	        
					 realMoney[i]="<%=dto.getRealMoney()%>";
					 occurMoney[i]="<%=dto.getOccurMoney()%>";
					 money[i]="<%=dto.getMoney()%>";    
	 	          	i++; 
 	<%
 				}
 			}
 	%>		     
	    var totalLine=nameList.length;			//总的行数 数组的长度
		var totalPageLine=40;					//每页显示多少行
		var pageTotal=totalLine/totalPageLine;	//总共分几页
		var pageCurrent=0;						//当前页
		var completeline=0;						//记录行的
		var moneySCorpus=0;						//
		var moneySInterest=0;						//
		var moneySPunishInterest=0;					//
		var moneyRCorpus=0;
		var moneyRInterest=0;
		var moneyRPunishInterest=0;
		var moneytotal=0;
		var moneyoccurMoney=0;
		var moneyReal=0;
		for(k = 0 ; k < totalLine; k++){
			if(k%totalPageLine==0&&k>0)
			{      
				document.forms(0).Cell1.DeleteRow(totalLine-completeline+4,totalPageLine-(totalLine-completeline-3),pageCurrent);
				document.forms(0).Cell1.ReDraw();
				pageCurrent++;	
				completeline=k-2;				
				document.forms(0).Cell1.insertSheetFromFile("<%=path%>/sysloan/loancallback/report/loancalbackBatchList1.cll",0,1,pageCurrent);
				document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页")	
			}
			if(pageCurrent==0)
			{	
				document.forms(0).Cell1.S(10,2,0,"<%=bankName%>");
				document.forms(0).Cell1.S(1,k+4,0,loanKouAcc[k]);
				document.forms(0).Cell1.S(2,k+4,0,contractId[k]);
				document.forms(0).Cell1.S(3,k+4,0,nameList[k]);
				document.forms(0).Cell1.s(4,k+4,0,cardNum[k]);
				document.forms(0).Cell1.s(5,k+4,0,loanType[k]);
				document.forms(0).Cell1.s(6,k+4,0,yearMonth[k]);
				document.forms(0).Cell1.d(7,k+4,0,shouldCorpus[k]);
				document.forms(0).Cell1.d(8,k+4,0,shouldInterest[k]);
				document.forms(0).Cell1.d(9,k+4,0,shouldPunishInterest[k]);
				document.forms(0).Cell1.d(10,k+4,0,realCorpus[k]);
				document.forms(0).Cell1.d(11,k+4,0,realInterest[k]);
				document.forms(0).Cell1.d(12,k+4,0,realPunishInterest[k]);
				document.forms(0).Cell1.d(13,k+4,0,days[k]);
				document.forms(0).Cell1.d(14,k+4,0,realMoney[k]);
				document.forms(0).Cell1.d(15,k+4,0,occurMoney[k]);
				document.forms(0).Cell1.d(16,k+4,0,money[k]);
				moneySCorpus=moneySCorpus+parseFloat(shouldCorpus[k]);
				moneySInterest=moneySInterest+parseFloat(shouldInterest[k]);
				moneySPunishInterest=moneySPunishInterest+parseFloat(shouldPunishInterest[k]);
				moneyRCorpus=moneyRCorpus+parseFloat(realCorpus[k]);
				moneyRInterest=moneyRInterest+parseFloat(realInterest[k]);
				moneyRPunishInterest=moneyRPunishInterest+parseFloat(realPunishInterest[k]);
				moneytotal=moneytotal+parseFloat(realMoney[k]);
				moneyoccurMoney=moneyoccurMoney+parseFloat(occurMoney[k]);
				moneyReal=moneyReal+parseFloat(money[k]);
			}else{//向第一页后的所有页插数据
				document.forms(0).Cell1.S(10,2,pageCurrent,"<%=bankName%>");
				document.forms(0).Cell1.S(1,k-completeline+2,pageCurrent,loanKouAcc[k]);
				document.forms(0).Cell1.S(2,k-completeline+2,pageCurrent,contractId[k]);
				document.forms(0).Cell1.S(3,k-completeline+2,pageCurrent,nameList[k]);
				document.forms(0).Cell1.s(4,k-completeline+2,pageCurrent,cardNum[k]);
				document.forms(0).Cell1.s(5,k-completeline+2,pageCurrent,loanType[k]);
				document.forms(0).Cell1.s(6,k-completeline+2,pageCurrent,yearMonth[k]);
				document.forms(0).Cell1.d(7,k-completeline+2,pageCurrent,shouldCorpus[k]);
				document.forms(0).Cell1.d(8,k-completeline+2,pageCurrent,shouldInterest[k]);
				document.forms(0).Cell1.d(9,k-completeline+2,pageCurrent,shouldPunishInterest[k]);
				document.forms(0).Cell1.d(10,k-completeline+2,pageCurrent,realCorpus[k]);
				document.forms(0).Cell1.d(11,k-completeline+2,pageCurrent,realInterest[k]);
				document.forms(0).Cell1.d(12,k-completeline+2,pageCurrent,realPunishInterest[k]);
				document.forms(0).Cell1.d(13,k-completeline+2,pageCurrent,days[k]);
				document.forms(0).Cell1.d(14,k-completeline+2,pageCurrent,realMoney[k]);
				document.forms(0).Cell1.d(15,k-completeline+2,pageCurrent,occurMoney[k]);
				document.forms(0).Cell1.d(16,k-completeline+2,pageCurrent,money[k]);
				moneySCorpus=moneySCorpus+parseFloat(shouldCorpus[k]);
				moneySInterest=moneySInterest+parseFloat(shouldInterest[k]);
				moneySPunishInterest=moneySPunishInterest+parseFloat(shouldPunishInterest[k]);
				moneyRCorpus=moneyRCorpus+parseFloat(realCorpus[k]);
				moneyRInterest=moneyRInterest+parseFloat(realInterest[k]);
				moneyRPunishInterest=moneyRPunishInterest+parseFloat(realPunishInterest[k]);
				moneytotal=moneytotal+parseFloat(realMoney[k]);
				moneyoccurMoney=moneyoccurMoney+parseFloat(occurMoney[k]);
				moneyReal=moneyReal+parseFloat(money[k]);
			}
		}
				if(completeline==0)//查询出来的记录可以在一页显示的时候会进入
				{
					
					document.forms(0).Cell1.S(1,totalLine+5,pageCurrent,"总计");
					document.forms(0).Cell1.S(1,totalLine+6,pageCurrent,"操作员");
					document.forms(0).Cell1.S(2,totalLine+6,pageCurrent,"<%=makePerson%>");
					document.forms(0).Cell1.S(3,totalLine+6,pageCurrent,"日期");
					document.forms(0).Cell1.S(4,totalLine+6,pageCurrent,"<%=bizDates%>");
					document.forms(0).Cell1.d(7,totalLine+5,pageCurrent,moneySCorpus);
					document.forms(0).Cell1.d(8,totalLine+5,pageCurrent,moneySInterest);
					document.forms(0).Cell1.d(9,totalLine+5,pageCurrent,moneySPunishInterest);
					document.forms(0).Cell1.d(10,totalLine+5,pageCurrent,moneyRCorpus);
					document.forms(0).Cell1.d(11,totalLine+5,pageCurrent,moneyRInterest);
					document.forms(0).Cell1.d(12,totalLine+5,pageCurrent,moneyRPunishInterest);
					document.forms(0).Cell1.d(14,totalLine+5,pageCurrent,moneytotal);
					document.forms(0).Cell1.d(15,totalLine+5,pageCurrent,moneyoccurMoney);
					document.forms(0).Cell1.d(16,totalLine+5,pageCurrent,moneyReal);
					document.forms(0).Cell1.DeleteRow(totalLine+7,totalPageLine-(totalLine+6),pageCurrent);
					document.forms(0).Cell1.ReDraw();
				}
				else{
					
					document.forms(0).Cell1.S(1,totalLine-completeline+2,pageCurrent,"总计");
					document.forms(0).Cell1.S(1,totalLine-completeline+3,pageCurrent,"操作员");
					document.forms(0).Cell1.S(2,totalLine-completeline+3,pageCurrent,"<%=makePerson%>");
					document.forms(0).Cell1.S(3,totalLine-completeline+3,pageCurrent,"日期");
					document.forms(0).Cell1.S(5,totalLine-completeline+3,pageCurrent,"<%=bizDates%>");
					document.forms(0).Cell1.d(7,totalLine-completeline+2,pageCurrent,moneySCorpus);
					document.forms(0).Cell1.d(8,totalLine-completeline+2,pageCurrent,moneySInterest);
					document.forms(0).Cell1.d(9,totalLine-completeline+2,pageCurrent,moneySPunishInterest);
					document.forms(0).Cell1.d(10,totalLine-completeline+2,pageCurrent,moneyRCorpus);
					document.forms(0).Cell1.d(11,totalLine-completeline+2,pageCurrent,moneyRInterest);
					document.forms(0).Cell1.d(12,totalLine-completeline+2,pageCurrent,moneyRPunishInterest);
					document.forms(0).Cell1.d(14,totalLine-completeline+2,pageCurrent,moneytotal);
					document.forms(0).Cell1.d(15,totalLine-completeline+2,pageCurrent,moneyoccurMoney);
					document.forms(0).Cell1.d(16,totalLine-completeline+2,pageCurrent,moneyReal);
					document.forms(0).Cell1.DeleteRow(totalLine-completeline+4,totalPageLine-(totalLine-completeline-3),pageCurrent);
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
<td><INPUT id="Button3" onclick="location.href='<%=url%>'" type="button" value=" 返回 "></td>	
</table>
</form>
  </body>
</html>
