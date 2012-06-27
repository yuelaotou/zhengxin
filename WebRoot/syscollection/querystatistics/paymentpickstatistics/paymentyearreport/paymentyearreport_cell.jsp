<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.*" import="java.util.List" import="org.xpup.common.util.Pagination"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic"%>
<%@ page import="org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.paymentyearreport.dto.PaymentYearReportDTO" %>
<%@ page import="org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.paymentyearreport.action.PaymentYearReportShowAC" %>
<%@ include file="/checkUrl.jsp"%>

<%
   String path=request.getContextPath();
   String year=(String)request.getAttribute("year");
   String bizDate=(String)request.getAttribute("bizDate");
   String operator=(String)request.getAttribute("operator");
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
	document.forms(0).Cell1.openfile("<%=path%>/syscollection/querystatistics/paymentpickstatistics/paymentyearreport/report/paymentyearreport.cll","");
	var collName=[];
	var count1=[];	
	var person1=[];	
	var money1=[];
	var count2=[];	
	var person2=[];	
	var money2=[];
	var count3=[];	
	var person3=[];	
	var money3=[];
	var count4=[];	
	var person4=[];	
	var money4=[];
	var count5=[];	
	var person5=[];	
	var money5=[];
	var count6=[];	
	var person6=[];	
	var money6=[];
	var count7=[];	
	var person7=[];	
	var money7=[];
	var count8=[];	
	var person8=[];	
	var money8=[];
	var count9=[];	
	var person9=[];	
	var money9=[];
	var count10=[];	
	var person10=[];	
	var money10=[];
	var count11=[];	
	var person11=[];	
	var money11=[];
	var count12=[];	
	var person12=[];	
	var money12=[];
	var countAll=[];	
	var personAll=[];	
	var moneyAll=[];
		
	var i=0;
	<%
		List list = (List) request.getSession().getAttribute("infoList");
		PaymentYearReportDTO dto = null;
		for(int j=0;j<list.size();j++){
			dto = (PaymentYearReportDTO)list.get(j);
	%>
			collName[i]="<%=dto.getCollName()%>";
			count1[i]="<%=dto.getCount1()%>";
			person1[i]="<%=dto.getPerson1()%>";
			money1[i]="<%=dto.getMoney1()%>";
			count2[i]="<%=dto.getCount2()%>";
			person2[i]="<%=dto.getPerson2()%>";
			money2[i]="<%=dto.getMoney2()%>";
			count3[i]="<%=dto.getCount3()%>";
			person3[i]="<%=dto.getPerson3()%>";
			money3[i]="<%=dto.getMoney3()%>";
			count4[i]="<%=dto.getCount4()%>";
			person4[i]="<%=dto.getPerson4()%>";
			money4[i]="<%=dto.getMoney4()%>";
			count5[i]="<%=dto.getCount5()%>";
			person5[i]="<%=dto.getPerson5()%>";
			money5[i]="<%=dto.getMoney5()%>";
			count6[i]="<%=dto.getCount6()%>";
			person6[i]="<%=dto.getPerson6()%>";
			money6[i]="<%=dto.getMoney6()%>";
			count7[i]="<%=dto.getCount7()%>";
			person7[i]="<%=dto.getPerson7()%>";
			money7[i]="<%=dto.getMoney7()%>";
			count8[i]="<%=dto.getCount8()%>";
			person8[i]="<%=dto.getPerson8()%>";
			money8[i]="<%=dto.getMoney8()%>";
			count9[i]="<%=dto.getCount9()%>";
			person9[i]="<%=dto.getPerson9()%>";
			money9[i]="<%=dto.getMoney9()%>";
			count10[i]="<%=dto.getCount10()%>";
			person10[i]="<%=dto.getPerson10()%>";
			money10[i]="<%=dto.getMoney10()%>";
			count11[i]="<%=dto.getCount11()%>";
			person11[i]="<%=dto.getPerson11()%>";
			money11[i]="<%=dto.getMoney11()%>";
			count12[i]="<%=dto.getCount12()%>";
			person12[i]="<%=dto.getPerson12()%>";
			money12[i]="<%=dto.getMoney12()%>";
			countAll[i]="<%=dto.getCountAll()%>";
			personAll[i]="<%=dto.getPersonAll()%>";
			moneyAll[i]="<%=dto.getMoneyAll()%>";
			i++;
	<%
		}
	%>
		var totalLine=collName.length;			//总的行数
		var totalPageLine=30;					//每页显示多少行
		var pageTotal=totalLine/totalPageLine;	//总共分几页
		var pageCurrent=0;						//当前页
		var completeline=0;						//记录行的
			
			//总的合计
		for(k = 0 ; k < totalLine; k++){
			document.forms(0).Cell1.s(20,2,0,"<%=year%>"+"年");
			document.forms(0).Cell1.s(21,18,0,"<%=operator%>");
			document.forms(0).Cell1.s(27,18,0,"<%=bizDate%>");
			document.forms(0).Cell1.s(1,k+5,0,collName[k]);
			document.forms(0).Cell1.s(2,k+5,0,count1[k]);
			document.forms(0).Cell1.s(3,k+5,0,person1[k]);
			document.forms(0).Cell1.d(4,k+5,0,money1[k]);
			document.forms(0).Cell1.s(5,k+5,0,count2[k]);
			document.forms(0).Cell1.s(6,k+5,0,person2[k]);
			document.forms(0).Cell1.d(7,k+5,0,money2[k]);
			document.forms(0).Cell1.s(8,k+5,0,count3[k]);
			document.forms(0).Cell1.s(9,k+5,0,person3[k]);
			document.forms(0).Cell1.d(10,k+5,0,money3[k]);
			document.forms(0).Cell1.s(11,k+5,0,count4[k]);
			document.forms(0).Cell1.s(12,k+5,0,person4[k]);
			document.forms(0).Cell1.d(13,k+5,0,money4[k]);
			document.forms(0).Cell1.s(14,k+5,0,count5[k]);
			document.forms(0).Cell1.s(15,k+5,0,person5[k]);
			document.forms(0).Cell1.d(16,k+5,0,money5[k]);
			document.forms(0).Cell1.s(17,k+5,0,count6[k]);
			document.forms(0).Cell1.s(18,k+5,0,person6[k]);
			document.forms(0).Cell1.d(19,k+5,0,money6[k]);
			document.forms(0).Cell1.s(20,k+5,0,count7[k]);
			document.forms(0).Cell1.s(21,k+5,0,person7[k]);
			document.forms(0).Cell1.d(22,k+5,0,money7[k]);
			document.forms(0).Cell1.s(23,k+5,0,count8[k]);
			document.forms(0).Cell1.s(24,k+5,0,person8[k]);
			document.forms(0).Cell1.d(25,k+5,0,money8[k]);
			document.forms(0).Cell1.s(26,k+5,0,count9[k]);
			document.forms(0).Cell1.s(27,k+5,0,person9[k]);
			document.forms(0).Cell1.d(28,k+5,0,money9[k]);
			document.forms(0).Cell1.s(29,k+5,0,count10[k]);
			document.forms(0).Cell1.s(30,k+5,0,person10[k]);
			document.forms(0).Cell1.d(31,k+5,0,money10[k]);
			document.forms(0).Cell1.s(32,k+5,0,count11[k]);
			document.forms(0).Cell1.s(33,k+5,0,person11[k]);
			document.forms(0).Cell1.d(34,k+5,0,money11[k]);
			document.forms(0).Cell1.s(35,k+5,0,count12[k]);
			document.forms(0).Cell1.s(36,k+5,0,person12[k]);
			document.forms(0).Cell1.d(37,k+5,0,money12[k]);
			document.forms(0).Cell1.s(38,k+5,0,countAll[k]);
			document.forms(0).Cell1.s(39,k+5,0,personAll[k]);
			document.forms(0).Cell1.d(40,k+5,0,moneyAll[k]);
		}
		document.forms(0).Cell1.DeleteRow(totalLine+5,18-(totalLine+5),0);
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
	function printsheet(){
		var k=document.forms(0).Cell1.GetCurSheet();
		document.forms(0).Cell1.PrintSheet(1,k);
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
