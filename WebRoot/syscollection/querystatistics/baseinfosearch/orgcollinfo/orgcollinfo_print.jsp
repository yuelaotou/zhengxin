<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.util.List"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic"%>
<%@ page
	import="org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.orgcollinfo.dto.OrgCollinfoSumDTO"%>
<%@ page import="org.xpup.hafmis.common.util.BusiTools"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	String officeOrBank = (String) request.getSession().getAttribute(
			"officeOrBank");
%>
<html>
	<head>
		<script src="<%=path%>/js/common.js"></script>
	</head>
	<script type="text/javascript">
	function load(){	
	loginReg();
	document.forms(0).Cell1.openfile("<%=path%>/syscollection/querystatistics/baseinfosearch/orgcollinfo/print/orgCollinfoPrint.cll","");
	var orgId=[];
	var orgName=[];
	var officecode=[];
	var collectionBankId=[];
	var empSum=[];
	var salarySum=[];
	var empRate=[];
	var orgRate=[];
	var orgPay=[];
	var empPay=[];
	var paySum=[];
	var balance=[];
	var overPay=[];
	var paybalance=[];
	var orgPayMonth=[];
	var empPayMonth=[];
	var openDate=[];
	var personCount=[];
	var character=[];
	
	var i=0;
	<%
    		List list=(List) request.getAttribute("pirnt_orgInfo1");

  			OrgCollinfoSumDTO dto=null;
  			for(int j=0;j<list.size();j++)
  			{
  				dto=(OrgCollinfoSumDTO)list.get(j);
  				String tempid = dto.getOrgId().toString();
                String orgid = BusiTools.convertTenNumber(tempid);
 	%>	
				orgId[i]="<%=orgid%>";
				orgName[i]="<%=dto.getOrgName()%>";
				officecode[i]="<%=dto.getOfficecode()%>";
				collectionBankId[i]="<%=dto.getCollectionBankId()%>";
				empSum[i]="<%=dto.getEmpSum()%>";
				salarySum[i]="<%=dto.getSalarySum()%>";
				
				empRate[i]="<%=dto.getEmpRate()%>";
				orgRate[i]="<%=dto.getOrgRate()%>";
				orgPay[i]="<%=dto.getOrgPay()%>";
				empPay[i]="<%=dto.getEmpPay()%>";
				paySum[i]="<%=dto.getPaySum()%>";
				balance[i]="<%=dto.getBalance()%>";
				
				overPay[i]="<%=dto.getOverPay()%>";
				paybalance[i]="<%=dto.getPaybalance()%>";
				orgPayMonth[i]="<%=dto.getOrgPayMonth()%>";
				empPayMonth[i]="<%=dto.getEmpPayMonth()%>";
				openDate[i]="<%=dto.getOpenDate()%>";
				personCount[i]="<%=dto.getPersonCount()%>";
				character[i]="<%=dto.getCharacter()%>";
				i++;
 	<%
 			}
 	%>
 		var totalLine=orgId.length;				//总的行数
		var totalPageLine=40;					//每页显示多少行
		var pageTotal=totalLine/totalPageLine;	//总共分几页
		var pageCurrent=0;						//当前页
		var completeline=0;						//记录行的
		
		var empSumtal=0;						//缴存人数合计
		var personCounttal=0;						//缴存人数合计
		var salarySumtal=0;						//工资总额合计
		var orgPaytal=0;						//单位缴额合计
		var empPaytal=0;						//职工缴额合计
		var paySumtal=0;						//汇缴总额合计
		var balancetal=0;						//公积金余额合计
		var overPaytal=0;						//挂账余额合计
		var paybalancetal=0;					//帐面余额合计
		
		
		
		for(k = 0 ; k < totalLine; k++){
			if(k%totalPageLine==0&&k>0)
			{
				if("<%=officeOrBank%>"=="office"){
					document.forms(0).Cell1.S(1,2,pageCurrent,"办事处:");
					document.forms(0).Cell1.S(2,2,pageCurrent,officecode[0]);
				}
				if("<%=officeOrBank%>"=="bank"){
					document.forms(0).Cell1.S(1,2,pageCurrent,"办事处:");
					document.forms(0).Cell1.S(2,2,pageCurrent,officecode[0]);
					document.forms(0).Cell1.S(5,2,pageCurrent,"归集银行:");
					document.forms(0).Cell1.S(7,2,pageCurrent,collectionBankId[0]);
				}
				document.forms(0).Cell1.DeleteRow(totalPageLine+3,45-(totalPageLine+3),pageCurrent);
				document.forms(0).Cell1.ReDraw();
				pageCurrent++;	
				completeline=k-2;				
				document.forms(0).Cell1.insertSheetFromFile("<%=path%>/syscollection/querystatistics/baseinfosearch/orgcollinfo/print/orgCollinfoPrint.cll",0,1,pageCurrent);
				document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页")	
			}
			if(pageCurrent==0)
			{
				document.forms(0).Cell1.S(1,k+4,0,orgId[k]);
				document.forms(0).Cell1.S(2,k+4,0,orgName[k]);
				document.forms(0).Cell1.S(3,k+4,0,officecode[k]);
				document.forms(0).Cell1.S(4,k+4,0,collectionBankId[k]);
				if(character[k]=='null'){
					character[k] = '';
				}
				document.forms(0).Cell1.S(5,k+4,0,character[k]);
				document.forms(0).Cell1.d(6,k+4,0,empSum[k]);
				document.forms(0).Cell1.d(7,k+4,0,personCount[k]);
				document.forms(0).Cell1.d(8,k+4,0,salarySum[k]);
				document.forms(0).Cell1.S(9,k+4,0,empRate[k]);
				document.forms(0).Cell1.S(10,k+4,0,orgRate[k]);
				document.forms(0).Cell1.d(11,k+4,0,orgPay[k]);
				document.forms(0).Cell1.d(12,k+4,0,empPay[k]);
				document.forms(0).Cell1.d(13,k+4,0,paySum[k]);
				document.forms(0).Cell1.d(14,k+4,0,balance[k]);
				document.forms(0).Cell1.d(15,k+4,0,overPay[k]);
				document.forms(0).Cell1.d(16,k+4,0,paybalance[k]);
				document.forms(0).Cell1.S(17,k+4,0,orgPayMonth[k]);
				document.forms(0).Cell1.S(18,k+4,0,empPayMonth[k]);
				document.forms(0).Cell1.S(19,k+4,0,openDate[k]);
				
				empSumtal=empSumtal+parseFloat(empSum[k]);						//缴存人数合计
				personCounttal=personCounttal+parseFloat(personCount[k]);		//总人数合计
				salarySumtal=salarySumtal+parseFloat(salarySum[k]);				//工资总额合计
				orgPaytal=orgPaytal+parseFloat(orgPay[k]);						//单位缴额合计
				empPaytal=empPaytal+parseFloat(empPay[k]);						//职工缴额合计
				paySumtal=paySumtal+parseFloat(paySum[k]);						//汇缴总额合计
				balancetal=balancetal+parseFloat(balance[k]);					//公积金余额合计
				overPaytal=overPaytal+parseFloat(overPay[k]);					//挂账余额合计
				paybalancetal=paybalancetal+parseFloat(paybalance[k]);			//帐面余额合计
				
			}else{//向第一页后的所有页插数据
				document.forms(0).Cell1.S(1,k-completeline+2,pageCurrent,orgId[k]);
				document.forms(0).Cell1.S(2,k-completeline+2,pageCurrent,orgName[k]);
				document.forms(0).Cell1.S(3,k-completeline+2,pageCurrent,officecode[k]);
				document.forms(0).Cell1.S(4,k-completeline+2,pageCurrent,collectionBankId[k]);
				if(character[k]=='null'){
					character[k] = '';
				}
				document.forms(0).Cell1.S(5,k-completeline+2,pageCurrent,character[k]);
				document.forms(0).Cell1.d(6,k-completeline+2,pageCurrent,empSum[k]);
				document.forms(0).Cell1.d(7,k-completeline+2,pageCurrent,personCount[k]);
				document.forms(0).Cell1.d(8,k-completeline+2,pageCurrent,salarySum[k]);
				document.forms(0).Cell1.S(9,k-completeline+2,pageCurrent,empRate[k]);
				document.forms(0).Cell1.S(10,k-completeline+2,pageCurrent,orgRate[k]);
				document.forms(0).Cell1.d(11,k-completeline+2,pageCurrent,orgPay[k]);
				document.forms(0).Cell1.d(12,k-completeline+2,pageCurrent,empPay[k]);
				document.forms(0).Cell1.d(13,k-completeline+2,pageCurrent,paySum[k]);
				document.forms(0).Cell1.d(14,k-completeline+2,pageCurrent,balance[k]);
				document.forms(0).Cell1.d(15,k-completeline+2,pageCurrent,overPay[k]);
				document.forms(0).Cell1.d(16,k-completeline+2,pageCurrent,paybalance[k]);
				document.forms(0).Cell1.S(17,k-completeline+2,pageCurrent,orgPayMonth[k]);
				document.forms(0).Cell1.S(18,k-completeline+2,pageCurrent,empPayMonth[k]);
				document.forms(0).Cell1.S(19,k-completeline+2,pageCurrent,openDate[k]);
				
				empSumtal=empSumtal+parseFloat(empSum[k]);						//缴存人数合计
				personCounttal=personCounttal+parseFloat(personCount[k]);		//总人数合计
				salarySumtal=salarySumtal+parseFloat(salarySum[k]);				//工资总额合计
				orgPaytal=orgPaytal+parseFloat(orgPay[k]);						//单位缴额合计
				empPaytal=empPaytal+parseFloat(empPay[k]);						//职工缴额合计
				paySumtal=paySumtal+parseFloat(paySum[k]);						//汇缴总额合计
				balancetal=balancetal+parseFloat(balance[k]);					//公积金余额合计
				overPaytal=overPaytal+parseFloat(overPay[k]);					//挂账余额合计
				paybalancetal=paybalancetal+parseFloat(paybalance[k]);			//帐面余额合计
				
			}	
		}
		if(completeline==0)//查询出来的记录可以在一页显示的时候会进入
		{
			document.forms(0).Cell1.S(1,totalLine+5,pageCurrent,"总计");
			document.forms(0).Cell1.S(6,totalLine+5,pageCurrent,empSumtal);
			document.forms(0).Cell1.d(7,totalLine+5,pageCurrent,personCounttal);
			document.forms(0).Cell1.d(8,totalLine+5,pageCurrent,salarySumtal);
			document.forms(0).Cell1.d(11,totalLine+5,pageCurrent,orgPaytal);
			document.forms(0).Cell1.d(12,totalLine+5,pageCurrent,empPaytal);
			document.forms(0).Cell1.d(13,totalLine+5,pageCurrent,paySumtal);
			document.forms(0).Cell1.d(14,totalLine+5,pageCurrent,balancetal);
			document.forms(0).Cell1.d(15,totalLine+5,pageCurrent,overPaytal);
			document.forms(0).Cell1.d(16,totalLine+5,pageCurrent,paybalancetal);
			document.forms(0).Cell1.DeleteRow(totalLine+6,40-totalLine,pageCurrent);
			document.forms(0).Cell1.ReDraw();
		}else{
			document.forms(0).Cell1.S(1,totalLine-completeline+3,pageCurrent,"总计");
			document.forms(0).Cell1.d(6,totalLine-completeline+3,pageCurrent,empSumtal);
			document.forms(0).Cell1.d(7,totalLine-completeline+3,pageCurrent,personCounttal);
			document.forms(0).Cell1.d(8,totalLine-completeline+3,pageCurrent,salarySumtal);
			document.forms(0).Cell1.d(11,totalLine-completeline+3,pageCurrent,orgPaytal);
			document.forms(0).Cell1.d(12,totalLine-completeline+3,pageCurrent,empPaytal);
			document.forms(0).Cell1.d(13,totalLine-completeline+3,pageCurrent,paySumtal);
			document.forms(0).Cell1.d(14,totalLine-completeline+3,pageCurrent,balancetal);
			document.forms(0).Cell1.d(15,totalLine-completeline+3,pageCurrent,overPaytal);
			document.forms(0).Cell1.d(16,totalLine-completeline+3,pageCurrent,paybalancetal);
			document.forms(0).Cell1.DeleteRow(totalLine-completeline+4,45-(totalLine-completeline+3),pageCurrent);
			document.forms(0).Cell1.ReDraw();
			if("<%=officeOrBank%>"=="office"){
				document.forms(0).Cell1.S(1,2,pageCurrent,"办事处:");
				document.forms(0).Cell1.S(2,2,pageCurrent,officecode[0]);
			}
			if("<%=officeOrBank%>"=="bank"){
				document.forms(0).Cell1.S(1,2,pageCurrent,"办事处:");
				document.forms(0).Cell1.S(2,2,pageCurrent,officecode[0]);
				document.forms(0).Cell1.S(5,2,pageCurrent,"归集银行:");
				document.forms(0).Cell1.S(7,2,pageCurrent,collectionBankId[0]);
			}
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
					<input type="button" name="print" value="打印预览"
						onclick="printPreview();" />
					<INPUT id="Button1" onclick="Button1_onclick()" type="button"
						value="另存为Excel" name="Button1">
					<INPUT id="Button1" onclick="Button2_onclick()" type="button"
						value="另存为pdf" name="Button1">
					<INPUT id="Button3" style="WIDTH: 100px"
						onclick="Button3_onclick()" type="button" value="页面设置">
					<INPUT id="Button3" style="WIDTH: 100px"
						onclick="Button4_onclick()" type="button" value="查找对话框">
					<INPUT id="Button3" style="WIDTH: 100px"
						onclick="Button5_onclick()" type="button" value="excel导入">
					<INPUT id="Button1" onclick="printsheet()" type="button"
						value=" 打印 " name="Button1">
					<INPUT id="Button3" onclick="javascript:history.back();"
						type="button" value="返回">
				</tr>
			</table>
		</form>
	</body>
</html>
