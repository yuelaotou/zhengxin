<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic"%>
<%@ page
	import="org.xpup.hafmis.syscollection.querystatistics.collbyfund.dto.CollByFundDTO"%>
<%@ page import="java.util.List"%>
<%@ page import="java.math.BigDecimal"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	String userName = (String) request.getAttribute("userName");
	String bizDate = (String) request.getAttribute("bizDate");
%>
<html>
	<head>
		<script src="<%=path%>/js/common.js">
</script>
	</head>
	<script type="text/javascript">
	function getInt(i,k) { 
		var page=0; 
		var j; 
		j=Math.round(i/k)-i/k; 
		if (j>=0) 
		page=Math.round(i/k)-1; 
		if (j<=0) 
		page=Math.round(i/k); 
		if(j!=0)
		page=page+1;
		if(j==0)
		page=i/k;
		return page; 
	}
	function load(){	
	loginReg();
	document.forms(0).Cell1.openfile("<%=path%>/syscollection/querystatistics/collbyfund/report/collbyfund.cll","");
	var orgId = [];
	var orgName = [];
    var contractId=[];
    var yearMonth=[];
    var money=[];
	var empId=[];
	var empName=[];
	var kouDate=[];
	var batchNum=[];
	var i=0;

	<%
    	    List list =(List)request.getSession().getAttribute("collByFundprintList");
			BigDecimal moneyAll = new BigDecimal(0);
	%>
			<%
  			for(int j=0;j<list.size();j++)
  			{
  				CollByFundDTO dto=(CollByFundDTO)list.get(j);
  				moneyAll=moneyAll.add(dto.getMoney());
 	%>
 				orgId[i]=formatTen("<%=dto.getOrgId()%>")+"<%=dto.getOrgId()%>";
 				orgName[i]="<%=dto.getOrgName()%>";
			    contractId[i]="<%=dto.getContractId()%>";
			    yearMonth[i]="<%=dto.getYearMonth()%>";
			    money[i]="<%=dto.getMoney()%>";
			    empId[i]=format("<%=dto.getEmpId()%>")+"<%=dto.getEmpId()%>";
			    empName[i]="<%=dto.getEmpName()%>";
			    kouDate[i]="<%=dto.getKouDate()%>";
			    batchNum[i]="<%=dto.getBatchNum()%>";
 	          	i++; 
 	<%
 			}
 	%>		     
	    var totalLine=orgId.length;			//总的行数 数组的长度
		var totalPageLine=12;					//每页显示多少行
		var pageTotal=totalLine/totalPageLine;	//总共分几页
		var pageCurrent=0;						//当前页
		var completeline=0;						//记录行的
		
		var iPage = getInt(totalLine,totalPageLine);
		var temp_page = 1;
			
		for(k = 0 ; k < totalLine; k++){
			if(k%totalPageLine==0&&k>0)
			{      
				document.forms(0).Cell1.S(9,1,pageCurrent,"第"+temp_page+"/"+iPage+"页");
				document.forms(0).Cell1.S(1,15,pageCurrent,"本页小计:");
				document.forms(0).Cell1.SetFormula (5, 15, pageCurrent, "Sum(E3:E14)" );
				document.forms(0).Cell1.S(3,16,pageCurrent,"<%=bizDate%>");
				document.forms(0).Cell1.S(8,16,pageCurrent,"<%=userName%>");
				document.forms(0).Cell1.ReDraw();
				pageCurrent++;
				completeline=k-2;
				temp_page = temp_page +	1;
				document.forms(0).Cell1.insertSheetFromFile("<%=path%>/syscollection/querystatistics/collbyfund/report/collbyfund1.cll",0,1,pageCurrent);
				document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页")	
			}
			if(pageCurrent==0)
			{
				document.forms(0).Cell1.S(1,k+3,0,orgId[k]);
				document.forms(0).Cell1.S(2,k+3,0,orgName[k]);
				document.forms(0).Cell1.S(3,k+3,0,contractId[k]);
				document.forms(0).Cell1.S(4,k+3,0,yearMonth[k]);
				document.forms(0).Cell1.d(5,k+3,0,money[k]);
				document.forms(0).Cell1.s(6,k+3,0,empId[k]);
				document.forms(0).Cell1.S(7,k+3,0,empName[k]);
				document.forms(0).Cell1.s(8,k+3,0,kouDate[k]);
				document.forms(0).Cell1.s(9,k+3,0,batchNum[k]);
			}else{//向第一页后的所有页插数据
				document.forms(0).Cell1.S(1,k-completeline+1,pageCurrent,orgId[k]);
				document.forms(0).Cell1.S(2,k-completeline+1,pageCurrent,orgName[k]);
				document.forms(0).Cell1.S(3,k-completeline+1,pageCurrent,contractId[k]);
				document.forms(0).Cell1.S(4,k-completeline+1,pageCurrent,yearMonth[k]);
				document.forms(0).Cell1.d(5,k-completeline+1,pageCurrent,money[k]);
				document.forms(0).Cell1.s(6,k-completeline+1,pageCurrent,empId[k]);
				document.forms(0).Cell1.S(7,k-completeline+1,pageCurrent,empName[k]);
				document.forms(0).Cell1.s(8,k-completeline+1,pageCurrent,kouDate[k]);
				document.forms(0).Cell1.s(9,k-completeline+1,pageCurrent,batchNum[k]);
			}
		}
		if(completeline==0)//查询出来的记录可以在一页显示的时候会进入
		{
			document.forms(0).Cell1.S(9,1,pageCurrent,"第1/1页");
			document.forms(0).Cell1.S(1,k-completeline+3,pageCurrent,"总计:");
			document.forms(0).Cell1.d(5, k-completeline+3, pageCurrent, "<%=moneyAll%>");
			document.forms(0).Cell1.S(3,16,pageCurrent,"<%=bizDate%>");
			document.forms(0).Cell1.S(8,16,pageCurrent,"<%=userName%>");
			document.forms(0).Cell1.DeleteRow(k-completeline+4,totalPageLine-(totalLine),pageCurrent);
			document.forms(0).Cell1.ReDraw();
		}
		else
		{
			document.forms(0).Cell1.S(9,1,pageCurrent,"第"+temp_page+"/"+iPage+"页");
			document.forms(0).Cell1.S(1,totalLine-completeline+1,pageCurrent,"总计:");
			document.forms(0).Cell1.S(3,16,pageCurrent,"<%=bizDate%>");
			document.forms(0).Cell1.S(8,16,pageCurrent,"<%=userName%>");
			document.forms(0).Cell1.d(5,totalLine-completeline+1,pageCurrent,"<%=moneyAll%>");
			document.forms(0).Cell1.DeleteRow(totalLine-completeline+2,totalPageLine-(totalLine-completeline-2),pageCurrent);
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
		document.forms(0).Cell1.ExportPdfFile("d:\\aa.pdf",-1,1,1);
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
	<body onload="load();">
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
						<INPUT id="Button1" onclick="printsheet()" type="button"
							value=" 打印 " name="Button1">
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
						<INPUT id="Button3" onclick="javascript:history.back();"
							type="button" value=" 返回 ">
					</td>
			</table>
		</form>
	</body>
</html>
