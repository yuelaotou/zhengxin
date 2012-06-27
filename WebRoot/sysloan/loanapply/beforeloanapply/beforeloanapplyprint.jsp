<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.util.List"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic"%>
<%@ page
	import="org.xpup.hafmis.sysloan.loanapply.beforeloanapply.dto.BeforeLoanApplyDTO"%>
<%@ page
	import="java.math.BigDecimal"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	String url = (String) request.getAttribute("URL");
	if (url == null) {
		url = "beforeLoanApplyForwardURLAC.do";
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
		document.forms(0).Cell1.openfile("<%=path%>/sysloan/loanapply/report/beforeloanapply.cll","");
    var loanKouYearmonth=[];
    var shouldCorpus=[];
	var shouldInterest=[];
	var ciMoney=[];
	var i=0;
	<%
		List list=(List)request.getSession().getAttribute("sun_yg_list");
			for(int j=0;j<list.size();j++)
  			{
  				BeforeLoanApplyDTO dto = new BeforeLoanApplyDTO();
  				dto=(BeforeLoanApplyDTO)list.get(j);
  				
 	%>
 	            loanKouYearmonth[i]="<%=dto.getPayMonth()%>";
 	            shouldCorpus[i]=<%=dto.getShouldCorpus()%>;
				shouldInterest[i]=<%=dto.getShouldInterest()%>;
				ciMoney[i]=<%=dto.getMonthBackMoney()%>;
 	          	i++; 
 	<%
 			}
 	%>
	 
        var totalLine=loanKouYearmonth.length;	//总的行数 数组的长度
		var totalPageLine=36;					//每页显示多少行
		var pageTotal=totalLine/totalPageLine;	//总共分几页
		var pageCurrent=0;						//当前页
		var completeline=0;						//记录行的
		var moneytotal=0;						//总的合计应还本金
		var moneyinsum=0;						//总的合计应还利息
		var moneyinterest=0;					//总的合计本月本息
		for(k = 0;k < totalLine; k++){
			if(k%totalPageLine==0&&k>0)
			{	
				document.forms(0).Cell1.S(1,totalPageLine+4,pageCurrent,"本页小计");
				document.forms(0).Cell1.SetFormula (2, totalPageLine+4, pageCurrent, "Sum(B4:B"+(totalPageLine+2)+")" );
				document.forms(0).Cell1.SetFormula (3, totalPageLine+4, pageCurrent, "Sum(C4:C"+(totalPageLine+2)+")" );
				document.forms(0).Cell1.SetFormula (4, totalPageLine+4, pageCurrent, "Sum(D4:D"+(totalPageLine+2)+")" );
				document.forms(0).Cell1.DeleteRow(totalPageLine+5,(totalPageLine+6)-(totalPageLine+4),pageCurrent);
				document.forms(0).Cell1.ReDraw();
				pageCurrent++;	
				completeline=k-2;
				document.forms(0).Cell1.insertSheetFromFile("<%=path%>//sysloan/loanapply/report/beforeloanapply.cll",0,1,pageCurrent);
				document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页")					
			}
			if(pageCurrent==0)
			{   
			    document.forms(0).Cell1.S(1,k+4,0,loanKouYearmonth[k]);
				document.forms(0).Cell1.d(2,k+4,0,shouldCorpus[k]);
				document.forms(0).Cell1.d(3,k+4,0,shouldInterest[k]);
				document.forms(0).Cell1.d(4,k+4,0,ciMoney[k]);
				moneytotal=moneytotal+parseFloat(shouldCorpus[k]);
				moneyinsum=moneyinsum+parseFloat(shouldInterest[k]);
				moneyinterest=moneyinterest+parseFloat(ciMoney[k]);
			}
			else{//向第一页后的所有页插数据
				
				document.forms(0).Cell1.S(1,k-completeline+2,pageCurrent,loanKouYearmonth[k]);
				document.forms(0).Cell1.d(2,k-completeline+2,pageCurrent,shouldCorpus[k]);
				document.forms(0).Cell1.d(3,k-completeline+2,pageCurrent,shouldInterest[k]);
				document.forms(0).Cell1.d(4,k-completeline+2,pageCurrent,ciMoney[k]);
				moneytotal=moneytotal+parseFloat(shouldCorpus[k]);
				moneyinsum=moneyinsum+parseFloat(shouldInterest[k]);
				moneyinterest=moneyinterest+parseFloat(ciMoney[k]);
			}		
		}
		     if(completeline==0)//查询出来的记录可以在一页显示的时候会进入
				{
					document.forms(0).Cell1.S(1,totalLine+4,pageCurrent,"本页小计");
					document.forms(0).Cell1.SetFormula (2, totalLine+4, pageCurrent, "Sum(B4:B"+(totalLine+3)+")" );
					document.forms(0).Cell1.SetFormula (3, totalLine+4, pageCurrent, "Sum(C4:C"+(totalLine+3)+")" );
					document.forms(0).Cell1.SetFormula (4, totalLine+4, pageCurrent, "Sum(D4:D"+(totalLine+3)+")" );
					document.forms(0).Cell1.S(1,totalLine+5,pageCurrent,"合计");
					document.forms(0).Cell1.SetFormula (2, totalLine+5, pageCurrent,moneytotal);
					document.forms(0).Cell1.SetFormula (3, totalLine+5, pageCurrent,moneyinsum);
					document.forms(0).Cell1.SetFormula (4, totalLine+5, pageCurrent,moneyinterest);
					document.forms(0).Cell1.DeleteRow(totalLine+6,(totalPageLine-totalLine-1),pageCurrent);
				}
				else
				{
					document.forms(0).Cell1.S(1,totalLine-completeline+2,pageCurrent,"本页小计");
					document.forms(0).Cell1.SetFormula (2, totalLine-completeline+2, pageCurrent, "Sum(B4:B"+(totalLine-completeline+1)+")" );
					document.forms(0).Cell1.SetFormula (3, totalLine-completeline+2, pageCurrent, "Sum(C4:C"+(totalLine-completeline+1)+")" );
					document.forms(0).Cell1.SetFormula (4, totalLine-completeline+2, pageCurrent, "Sum(D4:D"+(totalLine-completeline+1)+")" );
					
				    document.forms(0).Cell1.S(1,totalLine-completeline+3,pageCurrent,"合计");
					document.forms(0).Cell1.SetFormula (2, totalLine-completeline+3, pageCurrent,moneytotal);
					document.forms(0).Cell1.SetFormula (3, totalLine-completeline+3, pageCurrent,moneyinsum);
					document.forms(0).Cell1.SetFormula (4, totalLine-completeline+3, pageCurrent,moneyinterest);
					document.forms(0).Cell1.ReDraw();
					document.forms(0).Cell1.DeleteRow(totalLine-completeline+4,totalPageLine-(totalLine-completeline-1),pageCurrent);
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
