<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic"%>
<%@ page
	import="org.xpup.hafmis.syscollection.common.domain.entity.ChgOrgRate"%>
<%@ include file="/checkUrl.jsp"%>

<%
	String path = request.getContextPath();
	ChgOrgRate chgOrgRate = (ChgOrgRate)request.getAttribute("chgOrgRate");
%>
<html>
<head>
	<title></title>
	<script src="<%=path%>/js/common.js"></script>
</head>

<script type="text/javascript">
function load(){	
	loginReg();
	//加载模板文件..
	document.forms(0).Cell1.openfile("<%=path%>/syscollection/chgbiz/chgorgrate/report/chgapproval.cll","");
	var preOrgRate = "<%=chgOrgRate.getPreOrgRate()%>";
	var orgRate = "<%=chgOrgRate.getOrgRate()%>";
	var orgName = "<%=chgOrgRate.getOrg().getOrgInfo().getName()%>";
	var orgAddr = "<%=chgOrgRate.getOrg().getOrgInfo().getAddress()%>";
	var preSumPay = "<%=chgOrgRate.getPreSumPay()%>";
	var sumPay = "<%=chgOrgRate.getSumPay()%>";
	var transName = "";
	var transTel = "";
	<%
		if(chgOrgRate.getOrg().getOrgInfo().getTransactor()!=null) {
    %> 
			transName = "<%=chgOrgRate.getOrg().getOrgInfo().getTransactor().getName()%>";
			transTel = "<%=chgOrgRate.getOrg().getOrgInfo().getTransactor().getTelephone()%>";
	<%
		}
	%>
	var character = "<%=chgOrgRate.getCharacter()%>";
	var personCount = "<%=chgOrgRate.getPersonCount()%>";
	document.forms(0).Cell1.S(3,3,0,orgName);
	document.forms(0).Cell1.S(3,4,0,orgAddr);
	document.forms(0).Cell1.S(3,6,0,character);
	document.forms(0).Cell1.S(7,5,0,transName);
	document.forms(0).Cell1.S(9,5,0,transTel);
	document.forms(0).Cell1.d(3,8,0,preOrgRate);
	document.forms(0).Cell1.d(3,14,0,personCount);
	document.forms(0).Cell1.d(6,8,0,orgRate);
	document.forms(0).Cell1.d(8,8,0,parseFloat(sumPay)-parseFloat(preSumPay));
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
					<OBJECT id="Cell1"
						style="LEFT:0px;WIDTH:900px;  TOP:0px;HEIGHT:500px"
						codeBase="http://192.168.1.44:8088/hafmis/CellWeb5.CAB#version=5,3,7,321"
						classid=clsid:3F166327-8030-4881-8BD2-EA25350E574A VIEWASTEXT>
						<PARAM NAME="_Version" VALUE="65536">
						<PARAM NAME="_ExtentX" VALUE="10266">
						<PARAM NAME="_ExtentY" VALUE="7011">
						<PARAM NAME="_StockProps" VALUE="0">
					</OBJECT>
				</tr>
				<tr>
					<td align="center">
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
