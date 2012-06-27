<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic"%>
<%@ page
	import="org.xpup.hafmis.syscollection.querystatistics.collbyfund.dto.CollByFundBankDTO"%>
<%@ page import="java.util.List"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	String userName = (String) request.getAttribute("userName");
	String batchNum = (String) request.getAttribute("batchNum");
	String bizdate = (String)request.getSession().getAttribute("collByFundprintDate");
%>
<html>
	<head>
		<script src="<%=path%>/js/common.js">
</script>
	</head>
	<script type="text/javascript">
	function load(){	
	loginReg();
	document.forms(0).Cell1.openfile("<%=path%>/syscollection/querystatistics/collbyfund/report/collbyfundbank.cll","");
	var collBankId=[]; 
    var money=[];
    var moneyAll=0;
	var count=[];
	var countAll=0;
	var i=0;
	<%
    	    List list =(List)request.getSession().getAttribute("collByFundbankList");
	%>
			<%
  			for(int j=0;j<list.size();j++)
  			{
  				CollByFundBankDTO dto=(CollByFundBankDTO)list.get(j);
 	%>
				collBankId[i]="<%=dto.getCollBankId()%>";
				money[i]="<%=dto.getMoney()%>";
				moneyAll=eval(moneyAll)+eval(money[i]);
				count[i]="<%=dto.getCount()%>";
				countAll=eval(countAll)+eval(count[i]);
				i++; 
 	<%
 			}
 	%>		     
	    var totalLine=collBankId.length;			//总的行数 数组的长度
		var totalPageLine=10;					//每页显示多少行
		var pageTotal=totalLine/totalPageLine;	//总共分几页
		var n=0;
		for(k = 0 ; k < totalLine; k++){
			document.forms(0).Cell1.S(1,k+4,0,collBankId[k]);
			document.forms(0).Cell1.d(2,k+4,0,money[k]);
			document.forms(0).Cell1.d(3,k+4,0,count[k]);
			if(k==totalLine-1){
				if(k!=0){
					n=1;
					document.forms(0).Cell1.S(1,k+5,0,"合计：");
					document.forms(0).Cell1.d(2,k+5,0,moneyAll);
					document.forms(0).Cell1.d(3,k+5,0,countAll);
				}
			}
		}
		if("<%=batchNum%>"!="null"){
			document.forms(0).Cell1.S(2,2,0,"批次号："+"<%=batchNum%>");
		}
		document.forms(0).Cell1.S(1,14,0,"业务日期："+"<%=bizdate%>");
		document.forms(0).Cell1.S(3,14,0,"操作员："+"<%=userName%>");
		if(n==0){
			document.forms(0).Cell1.DeleteRow(totalLine+4,14-totalLine-4,0);
		}else{
			document.forms(0).Cell1.DeleteRow(totalLine+5,14-totalLine-5,0);
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
