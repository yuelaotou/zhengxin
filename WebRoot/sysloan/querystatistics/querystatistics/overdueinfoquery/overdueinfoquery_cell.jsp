<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.util.List"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic"%>
<%@ page
	import="org.xpup.hafmis.sysloan.querystatistics.querystatistics.overdueinfoquery.dto.OverDueinfoQueryShowListDTO"%>
<%@ include file="/checkUrl.jsp"%>

<%
	String path = request.getContextPath();
	List list = (List) request.getAttribute("printList");
	String url = (String) request.getAttribute("URL");
	if (url == null) {
		url = "overDueinfoQueryShowListAC.do";
	}
%>
<html>
	<head>
		<script src="<%=path%>/js/common.js"></script>
	</head>
	<script type="text/javascript">
	function load(){	
	loginReg();
	document.forms(0).Cell1.openfile("<%=path%>/sysloan/querystatistics/report/overdueinfoquery.cll","");
	var contractId= [];
	var borrowerName=[];
	var cardNum=[];	
	var orgName=[];
	var houseTel=[];
	var homeMobile=[];
	var orgTel=[];
	var name=[];
    var card_num=[];
    var org_name=[];
    var home_mobile=[];
    var org_tel=[];
    var home_addr=[];
	var loan_money=[];
    var loan_time_limit=[];
    var overplus_loan_money=[];
	var oweCorpus=[];
	var oweInterest=[];
	var punishInterest=[];
    var corpusInterestAll=[];
    var nextCorpusInterest=[];
    var nextMoney=[];
	var oweMonth=[];
    var agreement=[];
    var applyDate=[];
	var oweDate=[];
  
	var i=0;
	<%	String opertname=(String)request.getAttribute("opertname");
		String time=(String)request.getAttribute("time");
		OverDueinfoQueryShowListDTO overDueinfoQueryShowListDTO = new OverDueinfoQueryShowListDTO();
		for(int j=0;j<list.size();j++){
		overDueinfoQueryShowListDTO=(OverDueinfoQueryShowListDTO)list.get(j);
	%>
	    //把数据传到JS的数组里面..
	 	contractId[i] ="<%=overDueinfoQueryShowListDTO.getContractId()%>";
		borrowerName[i] ="<%=overDueinfoQueryShowListDTO.getBorrowerName()%>";
		cardNum[i] ="<%=overDueinfoQueryShowListDTO.getCardNum()%>";
		orgName[i] ="<%=overDueinfoQueryShowListDTO.getOrgName()%>";
		houseTel[i]= "<%=overDueinfoQueryShowListDTO.getHouseTel()%>";
		homeMobile[i] = "<%=overDueinfoQueryShowListDTO.getHomeMobile()%>";
		orgTel[i] ="<%=overDueinfoQueryShowListDTO.getOrgTel()%>";
		
		name[i] ="<%=overDueinfoQueryShowListDTO.getName()%>";
		card_num[i] ="<%=overDueinfoQueryShowListDTO.getCard_num()%>";
		org_name[i] ="<%=overDueinfoQueryShowListDTO.getOrg_name()%>";
		home_mobile[i] ="<%=overDueinfoQueryShowListDTO.getHome_mobile()%>";
		org_tel[i] ="<%=overDueinfoQueryShowListDTO.getOrg_tel()%>";
		home_addr[i] ="<%=overDueinfoQueryShowListDTO.getHome_addr()%>";
		loan_money[i] ="<%=overDueinfoQueryShowListDTO.getLoan_money()%>";
		loan_time_limit[i] ="<%=overDueinfoQueryShowListDTO.getLoan_time_limit()%>";
		overplus_loan_money[i] ="<%=overDueinfoQueryShowListDTO.getOverplus_loan_money()%>";
		
	    oweCorpus[i] ="<%=overDueinfoQueryShowListDTO.getOweCorpus()%>";
		oweInterest[i] ="<%=overDueinfoQueryShowListDTO.getOweInterest()%>"; 
		punishInterest[i]= "<%=overDueinfoQueryShowListDTO.getPunishInterest()%>";
		corpusInterestAll[i]= "<%=overDueinfoQueryShowListDTO.getCorpusInterestAll()%>";
		nextCorpusInterest[i]= "<%=overDueinfoQueryShowListDTO.getNextCorpusInterest()%>";
		nextMoney[i]= "<%=overDueinfoQueryShowListDTO.getNextMoney()%>";
		oweMonth[i]= "<%=overDueinfoQueryShowListDTO.getOweMonth()%>";
		agreement[i]= "<%=overDueinfoQueryShowListDTO.getAgreement()%>";
		applyDate[i]= "<%=overDueinfoQueryShowListDTO.getApplyDate()%>";
		oweDate[i]= "<%=overDueinfoQueryShowListDTO.getOweDate()%>";
		i++;
	<%}%>
	var totalLine=contractId.length;		//总的行数 数组的长度
	var totalPageLine=40;					//每页显示多少行--除了第一行
	var pageTotal=totalLine/totalPageLine;	//总共分几页 总行数/每页的行数
	var pageCurrent=0;						//当前页
	var completeline=0;						//记录行的
	var oweCorpusTotle=0;					//欠还本金-总额
	var oweInterestTotle=0;					//欠还利息-总额
	var punishInterestTotle=0;				// 欠还罚息利息-总额
	var corpusInterestAllTotle=0;				// 欠还本息合计

	for(k = 0 ; k < totalLine; k++){
		if(k%totalPageLine==0&&k>0)
		{
			document.forms(0).Cell1.S(1,totalPageLine+4,pageCurrent,"本页小计");
			document.forms(0).Cell1.SetFormula (17, totalPageLine+4, pageCurrent, "Sum(Q4:Q"+(totalPageLine+3)+")" );
			document.forms(0).Cell1.SetFormula (18, totalPageLine+4, pageCurrent, "Sum(R4:R"+(totalPageLine+3)+")" );
			document.forms(0).Cell1.SetFormula (19, totalPageLine+4, pageCurrent, "Sum(S4:S"+(totalPageLine+3)+")" );
			document.forms(0).Cell1.SetFormula (20, totalPageLine+4, pageCurrent, "Sum(T4:T"+(totalPageLine+3)+")" );
			document.forms(0).Cell1.DeleteRow(45,2,pageCurrent);
			document.forms(0).Cell1.ReDraw();//重画一个表格
			pageCurrent++;//当前页++	
			completeline=k-2;
			//绘制标签 param 	表页索引号。param 要设置的表页页签名称					
			document.forms(0).Cell1.insertSheetFromFile("<%=path%>/sysloan/querystatistics/report/overdueinfoquery.cll",0,1,pageCurrent);
			document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页")	
		}
		if(pageCurrent==0)
		{	
			document.forms(0).Cell1.S(1,k+4,0,contractId[k]);
			document.forms(0).Cell1.S(2,k+4,0,borrowerName[k]);
			document.forms(0).Cell1.S(3,k+4,0,cardNum[k]);
			document.forms(0).Cell1.S(4,k+4,0,orgName[k]);
			document.forms(0).Cell1.S(5,k+4,0,houseTel[k]);
			document.forms(0).Cell1.S(6,k+4,0,homeMobile[k]);
			document.forms(0).Cell1.S(7,k+4,0,orgTel[k]);
			
			document.forms(0).Cell1.S(8,k+4,0,name[k]);
			document.forms(0).Cell1.S(9,k+4,0,card_num[k]);
			document.forms(0).Cell1.S(10,k+4,0,org_name[k]);
			document.forms(0).Cell1.S(11,k+4,0,home_mobile[k]);
			document.forms(0).Cell1.S(12,k+4,0,org_tel[k]);
			document.forms(0).Cell1.S(13,k+4,0,home_addr[k]);
			document.forms(0).Cell1.S(14,k+4,0,loan_money[k]);
			document.forms(0).Cell1.S(15,k+4,0,loan_time_limit[k]);
			document.forms(0).Cell1.d(16,k+4,0,overplus_loan_money[k]);
			
			document.forms(0).Cell1.d(17,k+4,0,oweCorpus[k]);
			document.forms(0).Cell1.d(18,k+4,0,oweInterest[k]);
			document.forms(0).Cell1.d(19,k+4,0,punishInterest[k]);
			
			document.forms(0).Cell1.d(20,k+4,0,corpusInterestAll[k]);
			document.forms(0).Cell1.d(21,k+4,0,nextCorpusInterest[k]);
			document.forms(0).Cell1.d(22,k+4,0,nextMoney[k]);

			document.forms(0).Cell1.S(23,k+4,0,oweMonth[k]);
			
			document.forms(0).Cell1.S(24,k+4,0,agreement[k]);
			document.forms(0).Cell1.S(25,k+4,0,applyDate[k]);
			
			document.forms(0).Cell1.S(26,k+4,0,oweDate[k]);
			oweCorpusTotle=oweCorpusTotle+parseFloat(oweCorpus[k]);
			oweInterestTotle=oweInterestTotle+parseFloat(oweInterest[k]);
			punishInterestTotle=punishInterestTotle+parseFloat(punishInterest[k]);
			corpusInterestAllTotle=corpusInterestAllTotle+parseFloat(corpusInterestAll[k]);
			}
		else{//向第一页后的所有页插数据
			document.forms(0).Cell1.S(1,k-completeline+2,pageCurrent,contractId[k]);
			document.forms(0).Cell1.S(2,k-completeline+2,pageCurrent,borrowerName[k]);
			document.forms(0).Cell1.S(3,k-completeline+2,pageCurrent,cardNum[k]);
			document.forms(0).Cell1.S(4,k-completeline+2,pageCurrent,orgName[k]);
			document.forms(0).Cell1.S(5,k-completeline+2,pageCurrent,houseTel[k]);
			document.forms(0).Cell1.S(6,k-completeline+2,pageCurrent,homeMobile[k]);
			document.forms(0).Cell1.S(7,k-completeline+2,pageCurrent,orgTel[k]);
			
			document.forms(0).Cell1.S(8,k-completeline+2,pageCurrent,name[k]);
			document.forms(0).Cell1.S(9,k-completeline+2,pageCurrent,card_num[k]);
			document.forms(0).Cell1.S(10,k-completeline+2,pageCurrent,org_name[k]);
			document.forms(0).Cell1.S(11,k-completeline+2,pageCurrent,home_mobile[k]);
			document.forms(0).Cell1.S(12,k-completeline+2,pageCurrent,org_tel[k]);
			document.forms(0).Cell1.S(13,k-completeline+2,pageCurrent,home_addr[k]);
			document.forms(0).Cell1.S(14,k-completeline+2,pageCurrent,loan_money[k]);
			document.forms(0).Cell1.S(15,k-completeline+2,pageCurrent,loan_time_limit[k]);
			document.forms(0).Cell1.d(16,k-completeline+2,pageCurrent,overplus_loan_money[k]);
			
			document.forms(0).Cell1.d(17,k-completeline+2,pageCurrent,oweCorpus[k]);
			document.forms(0).Cell1.d(18,k-completeline+2,pageCurrent,oweInterest[k]);
			document.forms(0).Cell1.d(19,k-completeline+2,pageCurrent,punishInterest[k]);
			
			document.forms(0).Cell1.d(20,k-completeline+2,pageCurrent,corpusInterestAll[k]);
			document.forms(0).Cell1.d(21,k-completeline+2,pageCurrent,nextCorpusInterest[k]);
			document.forms(0).Cell1.d(22,k-completeline+2,pageCurrent,nextMoney[k]);

			document.forms(0).Cell1.S(23,k-completeline+2,pageCurrent,oweMonth[k]);
			
			document.forms(0).Cell1.S(24,k-completeline+2,pageCurrent,agreement[k]);
			document.forms(0).Cell1.S(25,k-completeline+2,pageCurrent,applyDate[k]);
			
			document.forms(0).Cell1.S(26,k-completeline+2,pageCurrent,oweDate[k]);
			oweCorpusTotle=oweCorpusTotle+parseFloat(oweCorpus[k]);
			oweInterestTotle=oweInterestTotle+parseFloat(oweInterest[k]);
			punishInterestTotle=punishInterestTotle+parseFloat(punishInterest[k]);
			corpusInterestAllTotle=corpusInterestAllTotle+parseFloat(corpusInterestAll[k]);
			
		}		
	}
	if(completeline==0)//查询出来的记录可以在一页显示的时候会进入
	{
		document.forms(0).Cell1.S(1,totalLine+4,pageCurrent,"本页小计");
		document.forms(0).Cell1.SetFormula (17, totalLine+4, pageCurrent, "Sum(Q4:Q"+(totalLine+3)+")" );
		document.forms(0).Cell1.SetFormula (18, totalLine+4, pageCurrent, "Sum(R4:R"+(totalLine+3)+")" );
		document.forms(0).Cell1.SetFormula (19, totalLine+4, pageCurrent, "Sum(S4:S"+(totalLine+3)+")" )
		document.forms(0).Cell1.SetFormula (20, totalLine+4, pageCurrent, "Sum(T4:T"+(totalLine+3)+")" )
		document.forms(0).Cell1.S(1,totalLine+5,pageCurrent,"总计");
		document.forms(0).Cell1.d(17,totalLine+5,pageCurrent,oweCorpusTotle);
		document.forms(0).Cell1.d(18,totalLine+5,pageCurrent,oweInterestTotle);
		document.forms(0).Cell1.d(19,totalLine+5,pageCurrent,punishInterestTotle);		
		document.forms(0).Cell1.d(20,totalLine+5,pageCurrent,corpusInterestAllTotle);		
		document.forms(0).Cell1.S(10,totalLine+6,pageCurrent,"操作员");
		document.forms(0).Cell1.S(11,totalLine+6,pageCurrent,"<%=opertname%>");
		document.forms(0).Cell1.S(12,totalLine+6,pageCurrent,"操作时间");
		document.forms(0).Cell1.S(13,totalLine+6,pageCurrent,"<%=time%>");
		document.forms(0).Cell1.DeleteRow(totalLine+7,46-totalLine-6,pageCurrent);
		document.forms(0).Cell1.ReDraw();
		
	}	
	else
	{
		document.forms(0).Cell1.S(1,totalLine-completeline+2,pageCurrent,"本页小计");   
		
		document.forms(0).Cell1.SetFormula (17, totalLine-completeline+2, pageCurrent, "Sum(Q4:Q"+(totalLine-(completeline-1))+")" );
		document.forms(0).Cell1.SetFormula (18, totalLine-completeline+2, pageCurrent, "Sum(R4:R"+(totalLine-(completeline-1))+")" );
		document.forms(0).Cell1.SetFormula (19, totalLine-completeline+2, pageCurrent, "Sum(S4:S"+(totalLine-(completeline-1))+")" );
		document.forms(0).Cell1.SetFormula (20, totalLine-completeline+2, pageCurrent, "Sum(T4:T"+(totalLine-(completeline-1))+")" );
		document.forms(0).Cell1.S(1,totalLine-completeline+3,pageCurrent,"总计");
		document.forms(0).Cell1.d(17,totalLine-completeline+3,pageCurrent,oweCorpusTotle);
		document.forms(0).Cell1.d(18,totalLine-completeline+3,pageCurrent,oweInterestTotle);
		document.forms(0).Cell1.d(19,totalLine-completeline+3,pageCurrent,punishInterestTotle);
		document.forms(0).Cell1.d(20,totalLine-completeline+3,pageCurrent,punishInterestTotle);
		document.forms(0).Cell1.S(10,totalLine-completeline+4,pageCurrent,"操作员");
		document.forms(0).Cell1.S(11,totalLine-completeline+4,pageCurrent,"<%=opertname%>");
		document.forms(0).Cell1.S(12,totalLine-completeline+4,pageCurrent,"操作时间");
		document.forms(0).Cell1.S(13,totalLine-completeline+4,pageCurrent,"<%=time%>");
		document.forms(0).Cell1.DeleteRow(totalLine-completeline+5,46-(totalLine-completeline+4),pageCurrent);
		document.forms(0).Cell1.ReDraw();
	}
		
				document.forms(0).Cell1.PrintSetSheetOpt(3);
 				document.forms(0).Cell1.PrintSetPrintRange(1,0);
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

	<body  onload="load();">
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
						<INPUT id="Button3" onclick="location.href='<%=url%>'"
							type="button" value=" 返回 " />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
