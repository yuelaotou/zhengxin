<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page  import="java.lang.*" import="java.util.*" import="java.util.List" import="org.xpup.common.util.Pagination"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic"%>
<%@ page import="org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pickmonthreport.dto.PickMonRepInfoDTO" %>
<%@ page import="org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pickmonthreport.action.PickMonthReportShowAC" %>
<%@ include file="/checkUrl.jsp"%>

<%
   String path=request.getContextPath();
   String date=(String)request.getAttribute("bizDate");
   String operator=(String)request.getAttribute("operator");
   Pagination pagination = (Pagination) request.getSession().getAttribute(PickMonthReportShowAC.PAGINATION_KEY);
   String startDate = (String)pagination.getQueryCriterions().get("startDate");
   String endDate = (String)pagination.getQueryCriterions().get("endDate");
 %>
<html>
  <head>
  <title>提取统计月报表打印</title>
  <script src="<%=path%>/js/common.js">
  </script>
  </head>

<script type="text/javascript">
	function load(){	
	loginReg();
	//加载模板文件..
	document.forms(0).Cell1.openfile("<%=path%>/syscollection/querystatistics/paymentpickstatistics/pickmonthreport/report/pickmonthreport.cll","");
	var collBank=[];
	//购房
	var personCount_buyhouse=[];
	var pickMoney_buyhouse=[];
	var pickMoneyRate_buyhouse=[];
	//还贷
	var personCount_callback=[];
	var pickMoney_callback=[];
	var pickMoneyRate_callback=[];
	//其他
	var personCount_other=[];
	var pickMoney_other=[];
	var pickMoneyRate_other=[];
	//退休
	var personCount_retire=[];
	var pickMoney_retire=[];
	var pickMoneyRate_retire=[];
	//失业
	var personCount_jobless=[];
	var pickMoney_jobless=[];
	var pickMoneyRate_jobless=[];
	//死亡
	var personCount_death=[];
	var pickMoney_death=[];
	var pickMoneyRate_death=[];
	//扣款
	var personCount_deduct=[];
	var pickMoney_deduct=[];
	var pickMoneyRate_deduct=[];
	//合计
	var personCount_total=[];
	var pickMoney_total=[];
	
	var i=0;
	<%
		List list = (List) request.getSession().getAttribute("infoList");
		PickMonRepInfoDTO dto = null;
		for(int j=0;j<list.size();j++){
			dto = (PickMonRepInfoDTO)list.get(j);
	%>
			collBank[i]="<%=dto.getCollBank()%>";
			personCount_buyhouse[i] = "<%=dto.getPersonCount_buyhouse()%>";
			pickMoney_buyhouse[i] = "<%=dto.getPickMoney_buyhouse()%>";
			pickMoneyRate_buyhouse[i] = "<%=dto.getPickMoneyRate_buyhouse()%>";
			personCount_callback[i] = "<%=dto.getPersonCount_callback()%>";
			pickMoney_callback[i] = "<%=dto.getPickMoney_callback()%>";
			pickMoneyRate_callback[i] = "<%=dto.getPickMoneyRate_callback()%>";
			personCount_retire[i] = "<%=dto.getPersonCount_retire()%>";
			pickMoney_retire[i] = "<%=dto.getPickMoney_retire()%>";
			pickMoneyRate_retire[i] = "<%=dto.getPickMoneyRate_retire()%>";
			personCount_other[i] = "<%=dto.getPersonCount_other()%>";
			pickMoney_other[i] = "<%=dto.getPickMoney_other()%>";
			pickMoneyRate_other[i] = "<%=dto.getPickMoneyRate_other()%>";
			personCount_jobless[i] = "<%=dto.getPersonCount_jobless()%>";
			pickMoney_jobless[i] = "<%=dto.getPickMoney_jobless()%>";
			pickMoneyRate_jobless[i] = "<%=dto.getPickMoneyRate_jobless()%>";
			personCount_death[i] = "<%=dto.getPersonCount_death()%>";
			pickMoney_death[i] = "<%=dto.getPickMoney_death()%>";
			pickMoneyRate_death[i] = "<%=dto.getPickMoneyRate_death()%>";
			personCount_deduct[i] = "<%=dto.getPersonCount_deduct()%>";
			pickMoney_deduct[i] = "<%=dto.getPickMoney_deduct()%>";
			pickMoneyRate_deduct[i] = "<%=dto.getPickMoneyRate_deduct()%>";
			personCount_total[i] = "<%=dto.getPersonCount_total()%>";
			pickMoney_total[i] = "<%=dto.getPickMoney_total()%>";
			i++;
	<%
		}
	%>
		var totalLine=personCount_buyhouse.length;			//总的行数
		var totalPageLine=30;					//每页显示多少行
		var pageTotal=totalLine/totalPageLine;	//总共分几页
		var pageCurrent=0;						//当前页
		var completeline=0;						//记录行的
			
			//总的合计
		for(k = 0 ; k < totalLine; k++){
			
			if(k%totalPageLine==0&&k>0)
			{
				document.forms(0).Cell1.ReDraw();
				pageCurrent++;	
				completeline=k-2;				
				document.forms(0).Cell1.insertSheetFromFile("<%=path%>/syscollection/querystatistics/paymentpickstatistics/pickmonthreport/report/pickmonthreport.cll",0,1,pageCurrent);
				document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页")	;
	
			}
			document.forms(0).Cell1.s(8,3,0,"<%=startDate%>" + " 至 " + "<%=endDate%>");
			document.forms(0).Cell1.s(9,16,0,"<%=date%>");
			document.forms(0).Cell1.s(21,16,0,"<%=operator%>");
			document.forms(0).Cell1.s(1,k+6,0,collBank[k]);
		 	document.forms(0).Cell1.d(2,k+6,0,pickMoney_buyhouse[k]);
			document.forms(0).Cell1.s(3,k+6,0,personCount_buyhouse[k]);
		 	document.forms(0).Cell1.s(4,k+6,0,pickMoneyRate_buyhouse[k]);
		 	document.forms(0).Cell1.d(5,k+6,0,pickMoney_callback[k]);
		 	document.forms(0).Cell1.S(6,k+6,0,personCount_callback[k]);
	     	document.forms(0).Cell1.s(7,k+6,0,pickMoneyRate_callback[k]);
		 	document.forms(0).Cell1.d(8,k+6,0,pickMoney_other[k]);
	     	document.forms(0).Cell1.S(9,k+6,0,personCount_other[k]);
	     	document.forms(0).Cell1.s(10,k+6,0,pickMoneyRate_other[k]);
		 	document.forms(0).Cell1.d(11,k+6,0,pickMoney_retire[k]);
	     	document.forms(0).Cell1.S(12,k+6,0,personCount_retire[k]);
	     	document.forms(0).Cell1.s(13,k+6,0,pickMoneyRate_retire[k]);
		 	document.forms(0).Cell1.d(14,k+6,0,pickMoney_jobless[k]);
	     	document.forms(0).Cell1.S(15,k+6,0,personCount_jobless[k]);
	     	document.forms(0).Cell1.s(16,k+6,0,pickMoneyRate_jobless[k]);
		 	document.forms(0).Cell1.d(17,k+6,0,pickMoney_death[k]);
	     	document.forms(0).Cell1.S(18,k+6,0,personCount_death[k]);
	     	document.forms(0).Cell1.s(19,k+6,0,pickMoneyRate_death[k]);
		 	document.forms(0).Cell1.d(20,k+6,0,pickMoney_deduct[k]);
	     	document.forms(0).Cell1.S(21,k+6,0,personCount_deduct[k]);
	     	document.forms(0).Cell1.s(22,k+6,0,pickMoneyRate_deduct[k]);
		 	document.forms(0).Cell1.d(23,k+6,0,pickMoney_total[k]);
	     	document.forms(0).Cell1.S(24,k+6,0,personCount_total[k]);
		}
	
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
	<body onContextmenu="return false" onload="load();">
		<form action="">
			<table align="center">
				<tr>
					<OBJECT id=Cell1
						style="LEFT:0px;WIDTH:1000px;  TOP:0px;HEIGHT:500px"
						codeBase="http://192.168.1.44:8088/hafmis/CellWeb5.CAB#version=5,3,7,321"
						classid=clsid:3F166327-8030-4881-8BD2-EA25350E574A VIEWASTEXT>
						<PARAM NAME="_Version" VALUE="65536">
						<PARAM NAME="_ExtentX" VALUE="10266">
						<PARAM NAME="_ExtentY" VALUE="7011">
						<PARAM NAME="_StockProps" VALUE="0">
					</OBJECT>
				</tr>
				<tr align="center">
					<td>
						<input type="button" name="print" value="打印预览"
							onclick="printPreview();" />
						<INPUT id="Button1" onclick="Button1_onclick()" type="button"
							value="另存为Excel" name="Button1" />
						<INPUT id="Button1" onclick="Button2_onclick()" type="button"
							value="另存为pdf" name="Button1" />
						<INPUT id="Button3" style="WIDTH: 100px"
							onclick="Button3_onclick()" type="button" value="页面设置" />
						<INPUT id="Button3" style="WIDTH: 100px"
							onclick="Button4_onclick()" type="button" value="查找对话框" />
						<INPUT id="Button3" style="WIDTH: 100px"
							onclick="Button5_onclick()" type="button" value="excel导入" />
						<INPUT id="Button1" onclick="printsheet()" type="button"
							value=" 打印 " name="Button1" />
						<INPUT id="Button3" onclick="javascript:history.back();"
							type="button" value=" 返回 " />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
