<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.util.List"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic"%>
<%@ page
	import="org.xpup.hafmis.sysloan.common.domain.entity.OthersLoan"%>
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
document.forms(0).Cell1.Login("沈阳金软科技有限公司","","13100104509","0740-1662-7775-3003"); 
	document.forms(0).Cell1.openfile("<%=path%>/sysloan/loanapply/othersloan/othersLoanTcprint.cll","");
	var id=[];
	var name=[];
	var orgName=[];
	var cardNum=[];
	var salary=[];
	var monthPay=[];
	var balance=[];
	var opTime=[];
	var houseAddr=[];
	var houseTotalPrice=[];
	var houseArea=[];
	var loanMoney=[];
	var loanTime=[];
	var fundCity=[];
	var beiYong=[];
	var conntractId=[];
	var i=0;
	
	<%
			String optername=(String)request.getAttribute("opertname");
			String time=(String)request.getAttribute("time");
    		List list=(List) request.getAttribute("othersLoanprintAllList_wsh");
  			OthersLoan othersLoan=new OthersLoan();
  			for(int j=0;j<list.size();j++)
  			{
  				othersLoan=(OthersLoan)list.get(j);
 	%>		
 				name[i]="<%=othersLoan.getBorrowerName()%>";
				orgName[i]="<%=othersLoan.getBORROWERORGNAME()%>";
				cardNum[i]="<%=othersLoan.getBORROWERCARDNUM()%>"
				monthPay[i]="<%=othersLoan.getBORROWERMONTHPAY()%>"
				balance[i]="<%=othersLoan.getBORROWERBALANCE()%>"
				opTime[i]="<%=othersLoan.getASSISAGE()%>"
				houseAddr[i]="<%=othersLoan.getHOUSEADDR()%>"
				houseTotalPrice[i]="<%=othersLoan.getHOUSETOTALPRICE()%>"
				loanMoney[i]="<%=othersLoan.getLOANMONEY()%>"
				loanTime[i]="<%=othersLoan.getLOANTIME()%>"
				salary[i]="<%=othersLoan.getBORROWERSALARYBASE()%>"
				houseArea[i]="<%=othersLoan.getHOUSEAREA()%>"
				fundCity[i]="<%=othersLoan.getBORROWERADDR()%>"
				conntractId[i]="<%=othersLoan.getHOUSETYPE()%>"
				i++;
 	<%
 			}
 	%>
 	
 	
		var totalLine=name.length;			//总的行数
		
		var totalPageLine=40;					//每页显示多少行
		var pageTotal=totalLine/totalPageLine;	//总共分几页
		var pageCurrent=0;						//当前页  如果没有头表就设置成0
		var completeline=0;						//记录行的
		var moneytotal1=0;
				
		
		for(k = 0 ; k < totalLine; k++){
			if(k%totalPageLine==0&&k>0)
			{//插入新页.对上一页求和的完成
				//document.forms(0).Cell1.S(1,totalPageLine+4,pageCurrent,"本页小计");
				//document.forms(0).Cell1.SetFormula (6, totalPageLine+4, pageCurrent, "Sum(F2:F"+(totalPageLine+3)+")" );							
				//document.forms(0).Cell1.DeleteRow(totalPageLine+5,46-totalPageLine-4,pageCurrent);
				//document.forms(0).Cell1.DeleteRow(50,6,pageCurrent);
				pageCurrent++;
				completeline=k-2;
							
				document.forms(0).Cell1.insertSheetFromFile("<%=path%>/sysloan/loanapply/othersloan/othersLoanTcprint.cll",0,1,pageCurrent);
				document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页")	
			}
			if(pageCurrent==0)
			{			
				document.forms(0).Cell1.S(1,k+4,0,k+1);
				document.forms(0).Cell1.S(2,k+4,0,conntractId[k]);
				document.forms(0).Cell1.S(3,k+4,0,name[k]);
				document.forms(0).Cell1.S(4,k+4,0,orgName[k]);
				document.forms(0).Cell1.s(8,k+4,0,cardNum[k]);
				document.forms(0).Cell1.s(10,k+4,0,salary[k]);
				document.forms(0).Cell1.S(11,k+4,0,monthPay[k]);
				document.forms(0).Cell1.S(12,k+4,0,balance[k]);
				document.forms(0).Cell1.s(13,k+4,0,opTime[k]);
				document.forms(0).Cell1.S(14,k+4,0,houseAddr[k]);
				document.forms(0).Cell1.S(16,k+4,0,houseTotalPrice[k]);
				document.forms(0).Cell1.S(17,k+4,0,houseArea[k]);
				document.forms(0).Cell1.S(18,k+4,0,loanMoney[k]);
				document.forms(0).Cell1.s(19,k+4,0,loanTime[k]);
				document.forms(0).Cell1.S(20,k+4,0,fundCity[k]);
				
				
				moneytotal1=moneytotal1+parseFloat(loanMoney[k]);
			}else{
				
				document.forms(0).Cell1.S(1,k-completeline+2,pageCurrent,k+1);
				document.forms(0).Cell1.S(2,k-completeline+2,pageCurrent,conntractId[k]);
				document.forms(0).Cell1.S(3,k-completeline+2,pageCurrent,k+1);
				document.forms(0).Cell1.S(4,k-completeline+2,pageCurrent,name[k]);
				document.forms(0).Cell1.S(4,k-completeline+2,pageCurrent,orgName[k]);
				document.forms(0).Cell1.S(8,k-completeline+2,pageCurrent,cardNum[k]);
				document.forms(0).Cell1.S(10,k-completeline+2,pageCurrent,salary[k]);
				document.forms(0).Cell1.s(11,k-completeline+2,pageCurrent,monthPay[k]);
				document.forms(0).Cell1.S(12,k-completeline+2,pageCurrent,balance[k]);
				document.forms(0).Cell1.S(13,k-completeline+2,pageCurrent,opTime[k]);
				document.forms(0).Cell1.S(14,k-completeline+2,pageCurrent,houseAddr[k]);
				document.forms(0).Cell1.s(16,k-completeline+2,pageCurrent,houseTotalPrice[k]);
				document.forms(0).Cell1.S(17,k-completeline+2,pageCurrent,houseArea[k]);
				document.forms(0).Cell1.S(18,k-completeline+2,pageCurrent,loanMoney[k]);
				document.forms(0).Cell1.S(19,k-completeline+2,pageCurrent,loanTime[k]);
				document.forms(0).Cell1.S(20,k-completeline+2,pageCurrent,fundCity[k]);
				
				moneytotal1=moneytotal1+parseFloat(loanmoney[k]);
			
			}	
		}
		
		if(completeline==0){
			//document.forms(0).Cell1.S(1,totalLine+4,pageCurrent,"小计");
			//document.forms(0).Cell1.SetFormula (6,totalLine+4, pageCurrent, "Sum(F2:F"+(totalLine+3)+")" );
			//document.forms(0).Cell1.S(1,totalLine+5,pageCurrent,"总计");
			//document.forms(0).Cell1.SetFormula (6, totalLine+5, pageCurrent, "Sum(F2:F"+(totalLine+3)+")" );
		document.forms(0).Cell1.S(17,totalLine+4,pageCurrent,"操作员");
		document.forms(0).Cell1.S(18,totalLine+4,pageCurrent,"<%=optername%>");
		document.forms(0).Cell1.S(19,totalLine+4,pageCurrent,"操作时间");
		document.forms(0).Cell1.S(20,totalLine+4,pageCurrent,"<%=time%>");
			document.forms(0).Cell1.DeleteRow(totalLine+5,50-totalLine-4,pageCurrent);
		}else{
			//d//ocument.forms(0).Cell1.S(1,totalLine-completeline+2,pageCurrent,"本页小计");
			//document.forms(0).Cell1.SetFormula (6, totalLine-completeline+2, pageCurrent, "Sum(F2:F"+(totalLine-(completeline+1))+")" );
			///document.forms(0).Cell1.S(1,totalLine-completeline+3,pageCurrent,"总计");
			//document.forms(0).Cell1.d(6, totalLine-completeline+3, pageCurrent,moneytotal1);
			
		document.forms(0).Cell1.S(17,totalLine-completeline+4,pageCurrent,"操作员");
		document.forms(0).Cell1.S(18,totalLine-completeline+4,pageCurrent,"<%=optername%>");
		document.forms(0).Cell1.S(19,totalLine-completeline+4,pageCurrent,"操作时间");
		document.forms(0).Cell1.S(20,totalLine-completeline+4,pageCurrent,"<%=time%>");
			document.forms(0).Cell1.DeleteRow(totalLine+7,50-totalLine-6,pageCurrent);
		}	
		document.forms(0).Cell1.AllowExtend=false;
		document.forms(0).Cell1.AllowDragdrop=false;
		document.forms(0).Cell1.WorkbookReadonly=true;	
}



 	function printPreview(){
		var k=document.forms(0).Cell1.GetCurSheet();
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
	<body onContextmenu="return false" onload="load();">
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
							value="另存为Excel" name="Button1" />
					</td>
					<td>
						<INPUT id="Button1" onclick="Button2_onclick()" type="button"
							value="另存为pdf" name="Button1" />
					</td>
					<td>
						<INPUT id="Button3" style="WIDTH: 100px"
							onclick="Button3_onclick()" type="button" value="页面设置" />
					</td>
					<td>
						<INPUT id="Button3" style="WIDTH: 100px"
							onclick="Button4_onclick()" type="button" value="查找对话框" />
					</td>
					<td>
						<INPUT id="Button3" style="WIDTH: 100px"
							onclick="Button5_onclick()" type="button" value="excel导入" />
					</td>
					<td>
						<INPUT id="Button1" onclick="printsheet()" type="button"
							value=" 打印 " name="Button1" />
					</td>
					<td>
					<INPUT id="Button3" onclick="javascript:history.back();"
							type="button" value=" 返回 ">
					</td>
			</table>
		</form>
	</body>
</html>
