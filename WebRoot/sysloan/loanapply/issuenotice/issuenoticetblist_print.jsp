<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.util.List"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic"%>
<%@ page
	import="org.xpup.hafmis.sysloan.loanapply.issuenotice.dto.IssuenoticeTbDTO"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page import="java.math.BigDecimal"%>

<%
	String path = request.getContextPath();
	List list = (List) request.getAttribute("cellList");
	String username = (String) request.getAttribute("username");
	String bizDate = (String) request.getAttribute("bizDate");
%>
<html>
	<head>
		<script src="<%=path%>/js/common.js"></script>
	</head>
	<script type="text/javascript">
	function load(){
	loginReg();	
	document.forms(0).Cell1.openfile("<%=path%>/sysloan/loanapply/issuenotice/issuenoticetb_print.cll","");
			var loanKouAcc=[];
			var contractId=[];
			var contractSt=[];
			var borrowerName=[];
			var cardNum=[];
			var sellerName=[];
			var newSellerPayBank=[];
			var newPayBankAcc=[];
			var remark=[];
			var developname=[];
			var operator=[];						
			var i=0;
            <%
				IssuenoticeTbDTO issuenoticeTbDTO = new IssuenoticeTbDTO();
				for(int j=0;j<list.size();j++){
				issuenoticeTbDTO = (IssuenoticeTbDTO)list.get(j);			
			%>
			    //把数据传到JS的数组里面..
			    contractId[i] = "<%=issuenoticeTbDTO.getContractId()%>"; 
			    contractSt[i] = "<%=issuenoticeTbDTO.getContractSt()%>";
				borrowerName[i] = "<%=issuenoticeTbDTO.getBorrowerName()%>";
				cardNum[i] = "<%=issuenoticeTbDTO.getCardNum()%>";
				sellerName[i] = "<%=issuenoticeTbDTO.getLoanBankId()%>";
				newSellerPayBank[i] = "<%=issuenoticeTbDTO.getLoanMoney()%>";
				newPayBankAcc[i] = "<%=issuenoticeTbDTO.getLoanTimeLimit()%>";
				developname[i] = "<%=issuenoticeTbDTO.getDeveloperName()%>";
				remark[i] = "<%=issuenoticeTbDTO.getLoanMonthRate().multiply(
								new BigDecimal(100)) + "%"%>";
				operator[i] = "<%=issuenoticeTbDTO.getTemp_loanMode()%>";
					
				i++;
			<%}%>
			var totalLine=contractId.length;			//总的行数 数组的长度
			var totalPageLine=21;					//每页显示多少行--除了第一行
			var pageTotal=totalLine/totalPageLine;	//总共分几页 总行数/每页的行数
			var pageCurrent=0;						//当前页
			var completeline=0;						//记录行的	
			var moneytotal1=0;		
			for(k = 0 ; k < totalLine; k++){
				if(k%totalPageLine==0&&k>0)
				{
				document.forms(0).Cell1.S(1,25,pageCurrent,"本页小计");
				document.forms(0).Cell1.SetFormula (3, 25, pageCurrent, "Sum(C4:C"+(24)+")" );	
				document.forms(0).Cell1.DeleteRow(26,1,pageCurrent);						
				pageCurrent++;
				completeline=k-2;		
				document.forms(0).Cell1.insertSheetFromFile("<%=path%>/sysloan/loanapply/issuenotice/issuenoticetb_print.cll",0,1,pageCurrent);
				document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页")	;
				}
				if(pageCurrent==0)
				{	document.forms(0).Cell1.S(9,2,0,"<%=username%>");
					document.forms(0).Cell1.S(2,2,0,"<%=bizDate%>");
					document.forms(0).Cell1.s(1,k+4,0,contractId[k]);
					//document.forms(0).Cell1.s(2,k+4,0,contractSt[k]);
					document.forms(0).Cell1.S(2,k+4,0,borrowerName[k]);
					//document.forms(0).Cell1.s(4,k+4,0,cardNum[k]);
					//document.forms(0).Cell1.s(5,k+4,0,sellerName[k]);
					document.forms(0).Cell1.d(3,k+4,0,newSellerPayBank[k]);
					document.forms(0).Cell1.s(4,k+4,0,developname[k]);
					//document.forms(0).Cell1.s(6,k+4,0,newPayBankAcc[k]);
					//document.forms(0).Cell1.s(7,k+4,0,remark[k]);
					//document.forms(0).Cell1.s(7,k+4,0,operator[k]);	
					moneytotal1=moneytotal1+parseFloat(newSellerPayBank[k]);
					}
				else{//向第一页后的所有页插数据
					document.forms(0).Cell1.S(9,2,pageCurrent,"<%=username%>");
					document.forms(0).Cell1.S(2,2,pageCurrent,"<%=bizDate%>");
					document.forms(0).Cell1.s(1,k-completeline+2,pageCurrent,contractId[k]);
					//document.forms(0).Cell1.s(2,k-completeline+2,pageCurrent,contractSt[k]);
					document.forms(0).Cell1.S(2,k-completeline+2,pageCurrent,borrowerName[k]);
					//document.forms(0).Cell1.s(4,k-completeline+2,pageCurrent,cardNum[k]);
					//document.forms(0).Cell1.s(5,k-completeline+2,pageCurrent,sellerName[k]);
					document.forms(0).Cell1.d(3,k-completeline+2,pageCurrent,newSellerPayBank[k]);
					document.forms(0).Cell1.s(4,k-completeline+2,pageCurrent,developname[k]);
					//document.forms(0).Cell1.s(7,k-completeline+2,pageCurrent,remark[k]);
					//document.forms(0).Cell1.s(9,k-completeline+2,pageCurrent,operator[k]);	
					moneytotal1=moneytotal1+parseFloat(newSellerPayBank[k]);		
				}		
			}		
	if(completeline==0){
			document.forms(0).Cell1.S(1,totalLine+4,pageCurrent,"本页小计");
			document.forms(0).Cell1.SetFormula (3,totalLine+4, pageCurrent, "Sum(C4:C"+(totalLine+3)+")" );
			document.forms(0).Cell1.S(1,totalLine+5,pageCurrent,"总计");
			document.forms(0).Cell1.SetFormula (3, totalLine+5, pageCurrent, "Sum(C4:C"+(totalLine+3)+")" );
			
			document.forms(0).Cell1.S(4,totalLine+6,pageCurrent,"<%=bizDate%>");

		}else{		
			document.forms(0).Cell1.S(1,totalLine-completeline+2,pageCurrent,"本页小计");
			document.forms(0).Cell1.SetFormula (3, totalLine-completeline+2, pageCurrent, "Sum(C4:C"+(totalLine-(completeline+3))+")" );
			document.forms(0).Cell1.S(1,totalLine-completeline+3,pageCurrent,"总计");
			document.forms(0).Cell1.d(3, totalLine-completeline+3, pageCurrent,moneytotal1);
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
	function back(){
	document.location='issuenoticeTbShowAC.do';
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
						<INPUT id="Button3" onclick="back();" type="button" value=" 返回 ">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>


