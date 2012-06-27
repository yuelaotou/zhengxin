<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.util.*,java.math.BigDecimal"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic"%>
<%@ page
	import="org.xpup.hafmis.sysloan.loanaccord.printplan.form.PrintplanShowAF"%>
<%@ page
	import="org.xpup.hafmis.sysloan.loanaccord.printplan.dto.PrintplanListDTO"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	String url = (String) request.getAttribute("URL");
	if (url == null) {
		url = "printplanTaForwardUrlAC.do";
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
		document.forms(0).Cell1.openfile("<%=path%>/sysloan/loanaccord/report/printplan_jj.cll","");
	//document.forms(0).Cell1.insertSheetFromFile("<%=path%>/sysloan/loanaccord/report/printplanlist.cll",0,1,1);
    //document.forms(0).Cell1.setSheetLabel(1,"第2页");
    var loanKouYearmonth=[];
    var shouldCorpus=[];
	var shouldInterest=[];
	var ciMoney=[];
    var loanRate=[];
	var i=0;
	<%
	PrintplanShowAF printplanShowAF=(PrintplanShowAF)request.getSession().getAttribute("printplanShowAF");
	String loanTimeLimit = printplanShowAF.getPrintplanDTO().getLoanTimeLimit();
	String overplusLimite = printplanShowAF.getPrintplanDTO().getOverplusLimite();
	int count = new Integer(loanTimeLimit).intValue()-new Integer(overplusLimite).intValue();
	java.text.DateFormat format = new java.text.SimpleDateFormat("yyyyMMdd");
    String s = format.format(new Date());
	%>
	<%
    		List list=printplanShowAF.getPrintList();
  			
  			for(int j=0;j<list.size();j++)
  			{
  			PrintplanListDTO printplanListDTO=new PrintplanListDTO();
  				printplanListDTO=(PrintplanListDTO)list.get(j);
  				
 	%>
 	            loanKouYearmonth[i]="<%=printplanListDTO.getLoanKouYearmonth()%>";
 	            shouldCorpus[i]=<%=printplanListDTO.getShouldCorpus()%>;
				shouldInterest[i]=<%=printplanListDTO.getShouldInterest()%>;
				ciMoney[i]=<%=printplanListDTO.getCiMoney()%>;
				loanRate[i]="<%=printplanListDTO.getTemploanRate() %>";
 	          	i++; 
 	<%
 			}
 	%>
	  document.forms(0).Cell1.S(4,3,0,"<%=printplanShowAF.getPrintplanDTO().getBorrowerName()%>");
	  document.forms(0).Cell1.S(2,3,0,"<%=printplanShowAF.getPrintplanDTO().getContractId()%>");
	 //  document.forms(0).Cell1.S(3,6,0,"<%=printplanShowAF.getPrintplanDTO().getLoanTimeLimit()%>");
	  document.forms(0).Cell1.S(2,4,0,"<%=printplanShowAF.getPrintplanDTO().getCardNum() %>");
	//  document.forms(0).Cell1.S(7,3,0,"<%=printplanShowAF.getPrintplanDTO().getLoanKouAcc()%>");
	//  document.forms(0).Cell1.S(7,6,0,"<%=printplanShowAF.getPrintplanDTO().getLoanModeName()%>");
	//   document.forms(0).Cell1.S(7,7,0,"<%=printplanShowAF.getPrintplanDTO().getOverplusLimite() %>");
	  document.forms(0).Cell1.d(4,4,0,"<%=printplanShowAF.getPrintplanDTO().getLoanMoney() %>");
	//  document.forms(0).Cell1.S(7,4,0,"<%=printplanShowAF.getPrintplanDTO().getCardKindName() %>");
	//   document.forms(0).Cell1.S(3,8,0,"<%=printplanShowAF.getPrintplanDTO().getLoanBankName() %>");
	   document.forms(0).Cell1.d(2,5,0,"<%=printplanShowAF.getPrintplanDTO().getOverplusLoanMoney() %>");
	   document.forms(0).Cell1.S(2,6,0,"<%=count%>");
	   document.forms(0).Cell1.S(4,6,0,"<%=printplanShowAF.getPrintplanDTO().getLoanRepayDay()%>");
	   document.forms(0).Cell1.d(4,5,0,"<%=printplanShowAF.getInterest()%>");
	 
        var totalLine=loanKouYearmonth.length;	//总的行数 数组的长度
		var totalPageLine=13;					//每页显示多少行
		var pageTotal=totalLine/totalPageLine;	//总共分几页
		var pageCurrent=0;						//当前页
		var completeline=0;						//记录行的
		var moneytotal=0;						//总的合计应还本金
		var moneyinsum=0;						//总的合计应还利息
		var moneyinterest=0;					//总的合计本月本息
		for(k = 0;k < totalLine; k++){
			if(k%totalPageLine==0&&k>0)
			{	
		
		     pageCurrent++;	
			 completeline=k-2;				
			}
			if(pageCurrent==0)
			{   
			    document.forms(0).Cell1.S(1,k+8,0,loanKouYearmonth[k]);
				document.forms(0).Cell1.d(2,k+8,0,shouldCorpus[k]);
				document.forms(0).Cell1.d(3,k+8,0,shouldInterest[k]);
				document.forms(0).Cell1.d(4,k+8,0,ciMoney[k]);
				document.forms(0).Cell1.S(5,k+8,0,loanRate[k]);
			}
			else{//向第一页后的所有页插数据
				
				document.forms(0).Cell1.S(1,k-completeline+3,pageCurrent,loanKouYearmonth[k]);
				document.forms(0).Cell1.d(2,k-completeline+3,pageCurrent,shouldCorpus[k]);
				document.forms(0).Cell1.d(3,k-completeline+3,pageCurrent,shouldInterest[k]);
				document.forms(0).Cell1.d(4,k-completeline+3,pageCurrent,ciMoney[k]);
				document.forms(0).Cell1.d(5,k-completeline+3,pageCurrent,loanRate[k]);
				moneytotal=moneytotal+parseFloat(shouldCorpus[k]);
				moneyinsum=moneyinsum+parseFloat(shouldInterest[k]);
				moneyinterest=moneyinterest+parseFloat(ciMoney[k]);
			}		
		}
		     if(completeline==0)//查询出来的记录可以在一页显示的时候会进入
				{
					document.forms(0).Cell1.S(1,21,pageCurrent,"合计");
					document.forms(0).Cell1.SetFormula (2, 21, pageCurrent, "Sum(B8:B"+(totalLine+7)+")" );
					document.forms(0).Cell1.SetFormula (3, 21, pageCurrent, "Sum(C8:C"+(totalLine+7)+")" );
					document.forms(0).Cell1.SetFormula (4, 21, pageCurrent, "Sum(D8:D"+(totalLine+7)+")" );
					document.forms(0).Cell1.S(4,22,pageCurrent,"操作员");
					document.forms(0).Cell1.S(5,22,pageCurrent,"<%=printplanShowAF.getOperson()%>");
					document.forms(0).Cell1.S(1,22,pageCurrent,"打印时间");
					document.forms(0).Cell1.S(2,22,pageCurrent,"<%=s %>");
				}
				else
				{
				    document.forms(0).Cell1.S(1,totalLine-completeline+4,pageCurrent,"合计");
					document.forms(0).Cell1.SetFormula (2, totalLine-completeline+4, pageCurrent, "Sum(B5:B"+(totalLine-(completeline)+2)+")");
					document.forms(0).Cell1.SetFormula (3, totalLine-completeline+4, pageCurrent, "Sum(C5:C"+(totalLine-(completeline)+2)+")");
					document.forms(0).Cell1.SetFormula (4, totalLine-completeline+4, pageCurrent, "Sum(D5:D"+(totalLine-(completeline)+2)+")");
					document.forms(0).Cell1.S(4,totalLine-completeline+5,pageCurrent,"操作员");
					document.forms(0).Cell1.S(5,totalLine-completeline+5,pageCurrent,"<%=printplanShowAF.getOperson()%>");
					document.forms(0).Cell1.S(4,totalLine-completeline+6,pageCurrent,"打印时间");
					document.forms(0).Cell1.S(5,totalLine-completeline+6,pageCurrent,"<%=s%>");
					//document.forms(0).Cell1.DeleteRow(totalLine-completeline+2,totalPageLine-(totalLine-completeline+1),pageCurrent);
					document.forms(0).Cell1.ReDraw();
				}
				document.forms(0).Cell1.AllowExtend=false;
				document.forms(0).Cell1.AllowDragdrop=false;
				document.forms(0).Cell1.WorkbookReadonly=true;	
}
function printsheet(){//真正的打印
		var k=document.forms(0).Cell1.GetCurSheet();//显示打印预览那个页面--固定
		document.forms(0).Cell1.PrintSheet(1,k);//固定...
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
						<INPUT id="Button1" onclick="Button1_onclick()" type="button"
							value="另存为Excel" name="Button1">
					</td>
					<td>
						<INPUT id="Button1" onclick="Button2_onclick()" type="button"
							value="另存为pdf" name="Button1">
					</td>
					<td>
						<INPUT id="Button3" style="WIDTH: 100px"
							onclick="Button3_onclick()" type="button" value="页面设置">
					</td>
					<td>
						<INPUT id="Button3" style="WIDTH: 100px"
							onclick="Button4_onclick()" type="button" value="查找对话框">
					</td>
					<td>
						<INPUT id="Button3" style="WIDTH: 100px"
							onclick="Button5_onclick()" type="button" value="excel导入">
					</td>
					<td>
						<INPUT id="Button1" onclick="printsheet()" type="button"
							value=" 打印 " name="Button1">
					</td>
					<td>
						<INPUT id="Button3" onclick="javascript:history.back();"
							type="button" value=" 返回 ">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
