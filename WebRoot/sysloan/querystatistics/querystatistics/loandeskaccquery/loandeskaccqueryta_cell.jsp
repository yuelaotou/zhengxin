<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.util.List"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic"%>
<%@ page
	import="org.xpup.hafmis.sysloan.querystatistics.querystatistics.loandeskaccquery.dto.LoandeskaccqueryTaDTO"%>
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
	loginReg();
	document.forms(0).Cell1.openfile("<%=path%>/sysloan/querystatistics/report/loandeskaccqueryta.cll","");
	var contranctid=[];
	var loankouacc=[];
	var borrowername=[];
	var cardnum=[];
	var orgname=[];
	var paybank=[];
	var loanmoney=[];
	var loanbalance=[];//贷款余额
    var loanlimit=[];
	var loanstartdate=[];
	var contact_st=[];
	var ovareloanrepay=[];
	var nobackmoney=[];
	var ballbalance=[];
	var monthpay=[];
	var realcorpus=[];
	var realinterest=[];
	var realpunishinterest=[];
	var owemonth=[];
	var houseArea=[];
	var isloanPay=[];
	
	var i=0;
	
	<%
			String opertname=(String)request.getAttribute("opertname");
			String time=(String)request.getAttribute("time");
			String bank = (String) request.getAttribute("paybank_");
			if(bank==null)
				bank = "";
    		List list=(List) request.getAttribute("cellList");
  			LoandeskaccqueryTaDTO LoandeskaccqueryTaDTO=new LoandeskaccqueryTaDTO();
  			for(int j=0;j<list.size();j++)
  			{
  				LoandeskaccqueryTaDTO=(LoandeskaccqueryTaDTO)list.get(j);
 	%>		
				contranctid[i]="<%=LoandeskaccqueryTaDTO.getContactid()%>";
				borrowername[i]="<%=LoandeskaccqueryTaDTO.getBorrowername()%>";
				loankouacc[i]="<%=LoandeskaccqueryTaDTO.getLoankouacc()%>";
				cardnum[i]="<%=LoandeskaccqueryTaDTO.getCardnum()%>";
				orgname[i]="<%=LoandeskaccqueryTaDTO.getOrgName()%>";
				paybank[i]="<%=LoandeskaccqueryTaDTO.getPaybank()%>";
				loanmoney[i]="<%=LoandeskaccqueryTaDTO.getLoanmoney()%>";
				loanbalance[i]="<%=LoandeskaccqueryTaDTO.getLoanBalance()%>";
				loanlimit[i]="<%=LoandeskaccqueryTaDTO.getLoanlimit()%>";
				loanstartdate[i]="<%=LoandeskaccqueryTaDTO.getLoanstartdate()%>";
				contact_st[i]="<%=LoandeskaccqueryTaDTO.getContact_st()%>";
				ovareloanrepay[i]="<%=LoandeskaccqueryTaDTO.getOvareloanrepay()%>";
				nobackmoney[i]="<%=LoandeskaccqueryTaDTO.getNobackmoney()%>";
				ballbalance[i]="<%=LoandeskaccqueryTaDTO.getBallbalance()%>";
				monthpay[i]="<%=LoandeskaccqueryTaDTO.getMonthpay()%>";
				realcorpus[i]="<%=LoandeskaccqueryTaDTO.getRealcorpus()%>";
				realinterest[i]="<%=LoandeskaccqueryTaDTO.getRealinterest()%>";
				realpunishinterest[i]="<%=LoandeskaccqueryTaDTO.getRealpunishinterest()%>";
				owemonth[i]="<%=LoandeskaccqueryTaDTO.getOwemonth()%>";
				houseArea[i]="<%=LoandeskaccqueryTaDTO.getAreaFrist()%>";
				isloanPay[i]="<%=LoandeskaccqueryTaDTO.getIsloanPay()%>";

				i++;
 	<%
 			}
 	%>

		var totalLine=contranctid.length;			//总的行数
		
		var totalPageLine=40;					//每页显示多少行
		var pageTotal=totalLine/totalPageLine;	//总共分几页
		var pageCurrent=0;						//当前页  如果没有头表就设置成0
		var completeline=0;						//记录行的
		var moneytotal1=0;
		var moneytotal2=0;
		var moneytotal3=0;
		var moneytotal4=0;
		var moneytotal5=0;
		var moneytotal6=0;
		var moneytotal7=0;
		var moneytotal8=0;
		var moneytotal9=0;
				
		document.forms(0).Cell1.InsertRow(5, totalLine+3, 0);	
		for(k = 0 ; k < totalLine; k++){
<%--			if(k%totalPageLine==0&&k>0)--%>
<%--			{//插入新页.对上一页求和的完成--%>
<%--				document.forms(0).Cell1.S(1,45,pageCurrent,"本页小计");		--%>
<%--				document.forms(0).Cell1.SetFormula (7, 45, pageCurrent, "Sum(G4:G"+(totalPageLine+4)+")" );			--%>
<%--				document.forms(0).Cell1.SetFormula (10, 45, pageCurrent, "Sum(J4:J"+(totalPageLine+4)+")" );							--%>
<%--				document.forms(0).Cell1.SetFormula (11, 45, pageCurrent, "Sum(K4:K"+(totalPageLine+4)+")" );							--%>
<%--                document.forms(0).Cell1.SetFormula (12, 45, pageCurrent, "Sum(L4:L"+(totalPageLine+4)+")" );			--%>
<%--				document.forms(0).Cell1.SetFormula (13, 45, pageCurrent, "Sum(M4:M"+(totalPageLine+4)+")" );			--%>
<%--				document.forms(0).Cell1.SetFormula (14, 45, pageCurrent, "Sum(N4:N"+(totalPageLine+4)+")" );			--%>
<%--				document.forms(0).Cell1.SetFormula (15, 45, pageCurrent, "Sum(O4:O"+(totalPageLine+4)+")" );			--%>
<%--				document.forms(0).Cell1.SetFormula (16, 45, pageCurrent, "Sum(P4:P"+(totalPageLine+4)+")" );	--%>
<%--				document.forms(0).Cell1.SetFormula (17, 45, pageCurrent, "Sum(Q4:Q"+(totalPageLine+4)+")" );			--%>
<%--				document.forms(0).Cell1.DeleteRow(46,5,pageCurrent);--%>
<%--				pageCurrent++;--%>
<%--				completeline=k-2;--%>
<%--							--%>
<%--				document.forms(0).Cell1.insertSheetFromFile("<%=path%>/sysloan/querystatistics/report/loandeskaccqueryta.cll",0,1,pageCurrent);--%>
<%--				document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页")	--%>
<%--			}--%>
			if(pageCurrent==0)
			{			
				document.forms(0).Cell1.S(3,3,0,"<%=bank%>");
				document.forms(0).Cell1.S(1,k+5,0,contranctid[k]);
				document.forms(0).Cell1.S(2,k+5,0,loankouacc[k]);
				document.forms(0).Cell1.S(3,k+5,0,borrowername[k]);
				document.forms(0).Cell1.S(4,k+5,0,cardnum[k]);
				document.forms(0).Cell1.S(5,k+5,0,orgname[k]);
				document.forms(0).Cell1.S(6,k+5,0,paybank[k]);
				document.forms(0).Cell1.d(7,k+5,0,loanmoney[k]);
				document.forms(0).Cell1.S(8,k+5,0,loanlimit[k]);
				document.forms(0).Cell1.S(9,k+5,0,loanstartdate[k]);
				document.forms(0).Cell1.d(10,k+5,0,loanbalance[k]);
				document.forms(0).Cell1.d(11,k+5,0,ovareloanrepay[k]);
				document.forms(0).Cell1.d(12,k+5,0,nobackmoney[k]);
				document.forms(0).Cell1.d(13,k+5,0,ballbalance[k]);
				document.forms(0).Cell1.d(14,k+5,0,monthpay[k]);
				document.forms(0).Cell1.d(15,k+5,0,realcorpus[k]);
				document.forms(0).Cell1.d(16,k+5,0,realinterest[k]);
				document.forms(0).Cell1.d(17,k+5,0,realpunishinterest[k]);
				document.forms(0).Cell1.S(18,k+5,0,owemonth[k]);
				document.forms(0).Cell1.S(19,k+5,0,houseArea[k]);
				document.forms(0).Cell1.S(20,k+5,0,isloanPay[k]);
				

				
				moneytotal1=moneytotal1+parseFloat(loanmoney[k]);
				moneytotal2=moneytotal2+parseFloat(ovareloanrepay[k]);
				moneytotal3=moneytotal3+parseFloat(nobackmoney[k]);
				moneytotal4=moneytotal4+parseFloat(ballbalance[k]);
				moneytotal5=moneytotal5+parseFloat(monthpay[k]);
				moneytotal6=moneytotal6+parseFloat(realcorpus[k]);
				moneytotal7=moneytotal7+parseFloat(realinterest[k]);
				moneytotal8=moneytotal8+parseFloat(realpunishinterest[k]);
				moneytotal9=moneytotal9+parseFloat(loanbalance[k]);
			}else{
				document.forms(0).Cell1.S(3,3,pageCurrent,"<%=bank%>");
				document.forms(0).Cell1.S(1,k-completeline+3,pageCurrent,contranctid[k]);
				document.forms(0).Cell1.S(2,k-completeline+3,pageCurrent,loankouacc[k]);
				document.forms(0).Cell1.S(3,k-completeline+3,pageCurrent,borrowername[k]);
				document.forms(0).Cell1.S(4,k-completeline+3,pageCurrent,cardnum[k]);
				document.forms(0).Cell1.S(5,k-completeline+3,pageCurrent,orgname[k]);
				document.forms(0).Cell1.S(6,k-completeline+3,pageCurrent,paybank[k]);
				document.forms(0).Cell1.d(7,k-completeline+3,pageCurrent,loanmoney[k]);
				document.forms(0).Cell1.S(8,k-completeline+3,pageCurrent,loanlimit[k]);
				document.forms(0).Cell1.S(9,k-completeline+3,pageCurrent,loanstartdate[k]);
				document.forms(0).Cell1.d(10,k-completeline+3,pageCurrent,loanbalance[k]);
				document.forms(0).Cell1.d(11,k-completeline+3,pageCurrent,ovareloanrepay[k]);
				document.forms(0).Cell1.d(12,k-completeline+3,pageCurrent,nobackmoney[k]);
				document.forms(0).Cell1.d(13,k-completeline+3,pageCurrent,ballbalance[k]);
				document.forms(0).Cell1.d(14,k-completeline+3,pageCurrent,monthpay[k]);
				document.forms(0).Cell1.d(15,k-completeline+3,pageCurrent,realcorpus[k]);
				document.forms(0).Cell1.d(16,k-completeline+3,pageCurrent,realinterest[k]);
				document.forms(0).Cell1.d(17,k-completeline+3,pageCurrent,realpunishinterest[k]);
				document.forms(0).Cell1.S(18,k-completeline+3,pageCurrent,owemonth[k]);
				document.forms(0).Cell1.S(19,k-completeline+3,pageCurrent,houseArea[k]);
				document.forms(0).Cell1.S(20,k-completeline+3,pageCurrent,isloanPay[k]);
				
				moneytotal1=moneytotal1+parseFloat(loanmoney[k]);
				moneytotal2=moneytotal2+parseFloat(ovareloanrepay[k]);
				moneytotal3=moneytotal3+parseFloat(nobackmoney[k]);
				moneytotal4=moneytotal4+parseFloat(ballbalance[k]);
				moneytotal5=moneytotal5+parseFloat(monthpay[k]);
				moneytotal6=moneytotal6+parseFloat(realcorpus[k]);
				moneytotal7=moneytotal7+parseFloat(realinterest[k]);
				moneytotal8=moneytotal8+parseFloat(realpunishinterest[k]);
				moneytotal9=moneytotal9+parseFloat(loanbalance[k]);
			}	
		}
		
		if(completeline==0){
			document.forms(0).Cell1.S(1,totalLine+5,pageCurrent,"小计");
		
			document.forms(0).Cell1.SetFormula (7,totalLine+5, pageCurrent, "Sum(G4:G"+(totalLine+4)+")" );
			document.forms(0).Cell1.SetFormula (10,totalLine+5, pageCurrent, "Sum(J4:J"+(totalLine+4)+")" );
			document.forms(0).Cell1.SetFormula (11,totalLine+5, pageCurrent, "Sum(K4:K"+(totalLine+4)+")" );
			document.forms(0).Cell1.SetFormula (12,totalLine+5, pageCurrent, "Sum(L4:L"+(totalLine+4)+")" );
			document.forms(0).Cell1.SetFormula (13,totalLine+5, pageCurrent, "Sum(M4:M"+(totalLine+4)+")" );
			document.forms(0).Cell1.SetFormula (14,totalLine+5, pageCurrent, "Sum(N4:N"+(totalLine+4)+")" );
			document.forms(0).Cell1.SetFormula (15,totalLine+5, pageCurrent, "Sum(O4:O"+(totalLine+4)+")" );
			document.forms(0).Cell1.SetFormula (16,totalLine+5, pageCurrent, "Sum(P4:P"+(totalLine+4)+")" );
			document.forms(0).Cell1.SetFormula (17,totalLine+5, pageCurrent, "Sum(Q4:Q"+(totalLine+4)+")" );
			
			document.forms(0).Cell1.S(1,totalLine+6,pageCurrent,"总计");
			document.forms(0).Cell1.SetFormula (7, totalLine+6, pageCurrent, "Sum(G4:G"+(totalLine+4)+")" );
			document.forms(0).Cell1.SetFormula (10, totalLine+6, pageCurrent, "Sum(J4:J"+(totalLine+4)+")" );
			document.forms(0).Cell1.SetFormula (11, totalLine+6, pageCurrent, "Sum(K4:K"+(totalLine+4)+")" );
			document.forms(0).Cell1.SetFormula (12, totalLine+6, pageCurrent, "Sum(L4:L"+(totalLine+4)+")" );
			document.forms(0).Cell1.SetFormula (13, totalLine+6, pageCurrent, "Sum(M4:M"+(totalLine+4)+")" );
			document.forms(0).Cell1.SetFormula (14, totalLine+6, pageCurrent, "Sum(N4:N"+(totalLine+4)+")" );
			document.forms(0).Cell1.SetFormula (15, totalLine+6, pageCurrent, "Sum(O4:O"+(totalLine+4)+")" );
			document.forms(0).Cell1.SetFormula (16, totalLine+6, pageCurrent, "Sum(P4:P"+(totalLine+4)+")" );
			document.forms(0).Cell1.SetFormula (17, totalLine+6, pageCurrent, "Sum(Q4:Q"+(totalLine+4)+")" );
			

			document.forms(0).Cell1.S(14,totalLine+7,pageCurrent,"操作员");
			document.forms(0).Cell1.S(15,totalLine+7,pageCurrent,"<%=opertname%>");
			document.forms(0).Cell1.S(16,totalLine+7,pageCurrent,"操作时间");
			document.forms(0).Cell1.S(17,totalLine+7,pageCurrent,"<%=time%>");
			//document.forms(0).Cell1.DeleteRow(totalLine+7,50-totalLine-5,pageCurrent);
		}else{
			document.forms(0).Cell1.S(1,totalLine-completeline+3,pageCurrent,"本页小计");
			document.forms(0).Cell1.SetFormula (7, totalLine-completeline+3, pageCurrent, "Sum(G4:G"+(totalLine-(completeline-2))+")" );
			document.forms(0).Cell1.SetFormula (10, totalLine-completeline+3, pageCurrent, "Sum(J4:J"+(totalLine-(completeline-2))+")" );
			document.forms(0).Cell1.SetFormula (11, totalLine-completeline+3, pageCurrent, "Sum(K4:K"+(totalLine-(completeline-2))+")" );
			document.forms(0).Cell1.SetFormula (12, totalLine-completeline+3, pageCurrent, "Sum(L4:L"+(totalLine-(completeline-2))+")" );
			document.forms(0).Cell1.SetFormula (13, totalLine-completeline+3, pageCurrent, "Sum(M4:M"+(totalLine-(completeline-2))+")" );
			document.forms(0).Cell1.SetFormula (14, totalLine-completeline+3, pageCurrent, "Sum(N4:N"+(totalLine-(completeline-2))+")" );
			document.forms(0).Cell1.SetFormula (15, totalLine-completeline+3, pageCurrent, "Sum(O4:O"+(totalLine-(completeline-2))+")" );
			document.forms(0).Cell1.SetFormula (16, totalLine-completeline+3, pageCurrent, "Sum(P4:P"+(totalLine-(completeline-2))+")" );
			document.forms(0).Cell1.SetFormula (17, totalLine-completeline+3, pageCurrent, "Sum(Q4:Q"+(totalLine-(completeline-2))+")" );

			document.forms(0).Cell1.S(1,totalLine-completeline+4,pageCurrent,"总计");
			document.forms(0).Cell1.d(7, totalLine-completeline+4, pageCurrent,moneytotal1);
			document.forms(0).Cell1.d(10, totalLine-completeline+4, pageCurrent,moneytotal9);
			document.forms(0).Cell1.d(11, totalLine-completeline+4, pageCurrent,moneytotal2);
			document.forms(0).Cell1.d(12, totalLine-completeline+4, pageCurrent,moneytotal3);
			document.forms(0).Cell1.d(13, totalLine-completeline+4, pageCurrent,moneytotal4);
			document.forms(0).Cell1.d(14, totalLine-completeline+4, pageCurrent,moneytotal5);
			document.forms(0).Cell1.d(15, totalLine-completeline+4, pageCurrent,moneytotal6);
			document.forms(0).Cell1.d(16, totalLine-completeline+4, pageCurrent,moneytotal7);
			document.forms(0).Cell1.d(17, totalLine-completeline+4, pageCurrent,moneytotal8);

			document.forms(0).Cell1.S(14,totalLine-completeline+5,pageCurrent,"操作员");
			document.forms(0).Cell1.S(15,totalLine-completeline+5,pageCurrent,"<%=opertname%>");
			document.forms(0).Cell1.S(16,totalLine-completeline+5,pageCurrent,"操作时间");
			document.forms(0).Cell1.S(17,totalLine-completeline+5,pageCurrent,"<%=time%>");
		    //document.forms(0).Cell1.DeleteRow(totalLine-completeline+6,50-(totalLine-completeline+5),pageCurrent);
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
					<OBJECT id="Cell1"
						style="LEFT:0px;WIDTH:900px;  TOP:0px;HEIGHT:500px"
						codeBase="http://192.168.1.44:8088/hafmis/CellWeb5.CAB#version=5,3,7,321"
						classid="clsid:3F166327-8030-4881-8BD2-EA25350E574A" VIEWASTEXT>
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
						<INPUT id="Button1" onclick="printsheet()" type="button"
							value=" 打印 " name="Button1">
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
						<INPUT id="Button3" onclick="javascript:history.back();"
							type="button" value=" 返回 ">
					</td>
			</table>
		</form>
	</body>
</html>
