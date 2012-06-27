<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic"%>
<%@ page
	import="org.xpup.hafmis.syscollection.common.domain.entity.OrgChg"%>
<%@ page
	import="org.xpup.hafmis.syscollection.common.domain.entity.Org"%>
<%@ page
	import="org.xpup.hafmis.common.util.BusiTools"%>
<%@ page
	import="org.xpup.hafmis.common.util.BusiConst"%>
<%@ include file="/checkUrl.jsp"%>

<%
	String url = (String)request.getAttribute("url");
	String path = request.getContextPath();
	OrgChg orgChg = (OrgChg)request.getAttribute("orgChg");
	String bizDate = (String)request.getAttribute("bizDate");
	Org org = orgChg.getOrg();
	String count = org.getOrgPayCount().toString();
	String character = BusiTools.getBusiValue(Integer.parseInt(org.getOrgInfo().getCharacter()),BusiConst.NATUREOFUNITS);
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
	document.forms(0).Cell1.S(3,3,0,"<%=org.getOrgInfo().getName()%>");
	document.forms(0).Cell1.S(3,4,0,"<%=org.getOrgInfo().getAddress()%>");
	document.forms(0).Cell1.S(3,6,0,"<%=character%>");
	if("<%=org.getOrgInfo().getTransactor()!=null%>"){
		document.forms(0).Cell1.S(7,5,0,"<%=org.getOrgInfo().getTransactor().getName()%>");
		document.forms(0).Cell1.S(9,5,0,"<%=org.getOrgInfo().getTransactor().getTelephone()%>");
	}
	document.forms(0).Cell1.S(3,14,0,"<%=count%>");
	document.forms(0).Cell1.S(4,30,0,"<%=orgChg.getPerson()%>");
	document.forms(0).Cell1.S(7,30,0,"<%=orgChg.getMake()%>");
	document.forms(0).Cell1.S(3,29,0,"<%=bizDate.substring(0,4)%>"+"年"+"<%=bizDate.substring(4,6)%>"+"月"+"<%=bizDate.substring(6,8)%>"+"日(盖章)");
	document.forms(0).Cell1.S(8,29,0,"<%=bizDate.substring(0,4)%>"+"年"+"<%=bizDate.substring(4,6)%>"+"月"+"<%=bizDate.substring(6,8)%>"+"日(盖章)");
	if("<%=orgChg.getNewRate()%>"!=0)
		document.forms(0).Cell1.S(3,8,0,"<%=orgChg.getPreRate()%>");
	if("<%=orgChg.getNewRate()%>"!=0)
		document.forms(0).Cell1.S(6,8,0,"<%=orgChg.getNewRate()%>");
	if("<%=orgChg.getNewRate()%>"!=0)
		document.forms(0).Cell1.S(8,8,0,"<%=orgChg.getPayRate()%>");
		
	if("<%=orgChg.getNewPay()%>"!=0)
		document.forms(0).Cell1.S(3,10,0,"<%=orgChg.getPrePay()%>");
	if("<%=orgChg.getNewPay()%>"!=0)
		document.forms(0).Cell1.S(6,10,0,"<%=orgChg.getNewPay()%>");
	if("<%=orgChg.getNewPay()%>"!=0)
		document.forms(0).Cell1.S(8,10,0,"<%=orgChg.getPaySalary()%>");
		
	if("<%=orgChg.getAddMonth()!=null%>" && "<%=orgChg.getAddMonth()%>"!="null")
		document.forms(0).Cell1.S(3,13,0,"<%=orgChg.getAddMonth()%>");
	if("<%=orgChg.getAddStEndMonth()!=null%>" && "<%=orgChg.getAddStEndMonth()%>"!="null")
		document.forms(0).Cell1.S(5,13,0,"<%=orgChg.getAddStEndMonth()%>");
		
	if("<%=orgChg.getAddSum()%>"!=0)
		document.forms(0).Cell1.S(7,13,0,"<%=orgChg.getAddCount()%>");
	if("<%=orgChg.getAddSum()%>"!=0)
		document.forms(0).Cell1.S(8,13,0,"<%=orgChg.getAddSum()%>");
	if("<%=orgChg.getAddSum()%>"!=0)
		document.forms(0).Cell1.S(9,13,0,"<%=orgChg.getAddEmp()%>");
	if("<%=orgChg.getAddSum()%>"!=0)
		document.forms(0).Cell1.S(10,13,0,"<%=orgChg.getAddOrg()%>");
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
						<INPUT id="Button3" onclick="javascript:document.URL='<%=url%>';"
							type="button" value=" 返回 " />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
