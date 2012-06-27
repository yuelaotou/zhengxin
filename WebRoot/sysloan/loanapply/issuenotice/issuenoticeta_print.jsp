<%@ page contentType="text/html;charset=UTF-8" language="java"  import="java.util.List" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic"%>
<%@ page
	import="org.xpup.hafmis.sysloan.loanapply.issuenotice.form.IssuenoticeTaAF"%>
<%@ page import="org.xpup.hafmis.sysloan.loanapply.issuenotice.dto.IssuenoticeTaDTO" %>
<%@ page import="java.math.BigDecimal" %>	
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	request.removeAttribute("error");
	 List list=(List) request.getAttribute("cellList");
%>
<html>
	<head>
		<script src="<%=path%>/js/common.js">
</script>
	</head>
	<script type="text/javascript">
	function load(){	
	loginReg();
	document.forms(0).Cell1.openfile("<%=path%>/sysloan/loanapply/issuenotice/1026.cll","");	
	var contractId=[];
		var borrowerName=[];
		var cardKind=[];
		var cardNum=[];
		var loadBankId=[];
		var loadmoney=[];
		var moneydaxie=[];
		var loadLimitTime=[];
		var loadmonthRate=[];
		var monthinterest=[];
		var loadMode=[];
		var sellerBank=[];
		var sellerBankAcc=[];
		var x=[];
		var bizDate=[];	
		var orgname=[];
		var orgtel=[];
		var bailname=[];
		var firstloanmoney=[];
		var house=[];
		var loankouacc=[];	
		var houseaddr=[];
		var housearea=[];
		var housetotalprice=[];
		var redatepsn=[];
		var chkagainpsn=[];
		var vipchkagainpsn=[];
		var lastchkpsn=[];
		var i=0;
		<%    		
		 		 String bizdate=(String)request.getAttribute("bizDate");	
		 		 String userName=(String)request.getAttribute("username");
		%>
		var bizDateYear;
		var bizDateMonth;
		var bizDate;
		var userName;
		bizDateYear="<%=bizdate.substring(0,4)%>";		
				bizDateMonth="<%=bizdate.substring(4,6)%>";	
				bizDate="<%=bizdate.substring(6,8)%>";	
				userName="<%=userName%>";		
            <%
				IssuenoticeTaDTO issuenoticeTaDTO = new IssuenoticeTaDTO();
				for(int j=0;j<list.size();j++){
				issuenoticeTaDTO = (IssuenoticeTaDTO)list.get(j);	
			%>
			    //把数据传到JS的数组里面..
			    contractId[i] = "<%=issuenoticeTaDTO.getContractId()%>"; 
				borrowerName[i] = "<%=issuenoticeTaDTO.getBorrowerName()%>";
				cardKind[i] = "<%=issuenoticeTaDTO.getTemp_cardKind()%>";
				cardNum[i] = "<%=issuenoticeTaDTO.getCardNum()%>";
				loadBankId[i] = "<%=issuenoticeTaDTO.getLoanBankId()%>";
				loadmoney[i] = "<%=issuenoticeTaDTO.getLoanMoney()%>";
				moneydaxie[i] = ChangeToBig(loadmoney[i]);
				loadLimitTime[i] = "<%=issuenoticeTaDTO.getLoanTimeLimit()%>";
				loadmonthRate[i] = "<%=issuenoticeTaDTO.getLoanMonthRate().multiply(new BigDecimal(100))+"%"%>";
				monthinterest[i]="<%=issuenoticeTaDTO.getCorpusInterest()%>";
				loadMode[i] = "<%=issuenoticeTaDTO.getTemp_loanMode()%>";
				sellerBank[i] = "<%=issuenoticeTaDTO.getSellerPayBank()%>";
				sellerBankAcc[i] = "<%=issuenoticeTaDTO.getSellerPayBankAcc()%>";	
				x[i]= "<%=issuenoticeTaDTO.getBizDate()%>";	
				orgname[i]="<%=issuenoticeTaDTO.getOrgName()%>";
				orgtel[i]="<%=issuenoticeTaDTO.getOrgTel()%>";	
				bailname[i]="<%=issuenoticeTaDTO.getBailName()%>";	
				firstloanmoney[i]="<%=issuenoticeTaDTO.getFirstLoanMoney()%>";	
				house[i]="<%=issuenoticeTaDTO.getHouse()%>";
				loankouacc[i]="<%=issuenoticeTaDTO.getLoanKouAcc()%>";
				houseaddr[i]="<%=issuenoticeTaDTO.getHouseAddr()%>";
				housearea[i]="<%=issuenoticeTaDTO.getHouseArea()%>";
				housetotalprice[i]="<%=issuenoticeTaDTO.getHouseTotalPrice()%>";
				redatepsn[i]="<%=issuenoticeTaDTO.getRedatePerson()%>";	
				if(redatepsn[i]==null){
					redatepsn[i]="";
				}							
				chkagainpsn[i]="<%=issuenoticeTaDTO.getChkAgainPerson()%>";	
				if(chkagainpsn[i]==null){
					chkagainpsn[i]="";
				}							
				vipchkagainpsn[i]="<%=issuenoticeTaDTO.getVipChkAgainPerson()%>";	
				if(vipchkagainpsn[i]==null){
					vipchkagainpsn[i]="";
				}							
				lastchkpsn[i]="<%=issuenoticeTaDTO.getLastChkPerson()%>";	
				if(lastchkpsn[i]==null){
					lastchkpsn[i]="";
				}							
				i++;
			<%}%>
			var totalLine=contractId.length;			//总的行数 数组的长度			
			var pageCurrent=0;						//当前页			
			for(k = 0 ; k < totalLine; k++){
				if(k>0)
				{		
					document.forms(0).Cell1.ReDraw();//重画一个表格
					pageCurrent++;//当前页++	
					completeline=k-2;
					//绘制标签 param 	表页索引号。param 要设置的表页页签名称					
					document.forms(0).Cell1.insertSheetFromFile("<%=path%>/sysloan/loanapply/issuenotice/1026.cll",0,1,pageCurrent);
					document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页")	
				}
				if(pageCurrent==0){
		            var amountLength=loadmoney[k].length;
		            
		            for(var i=0;i<amountLength;i++){
			            var temp=loadmoney[k].substring((amountLength-i-1),amountLength-i);
			            document.forms(0).Cell1.S((62-i),18,0,temp);
			            document.forms(0).Cell1.S((62-i),67,0,temp);	
		            }	
					
					document.forms(0).Cell1.S(24,9,0,bizDateYear);//----------------
					document.forms(0).Cell1.S(31,9,0,bizDateMonth);//----------------
					document.forms(0).Cell1.S(38,9,0,bizDate);//----------------
					document.forms(0).Cell1.S(52,9,0,contractId[k]);//----------------
					document.forms(0).Cell1.S(8,12,0,borrowerName[k]);//----------------
					document.forms(0).Cell1.S(29,12,0,loankouacc[k]);//----------------
					document.forms(0).Cell1.S(58,12,0,loadBankId[k]);//----------------
					document.forms(0).Cell1.S(20,16,0,moneydaxie[k]);//----------------
					document.forms(0).Cell1.S(11,20,0,house[k]);//----------------
					document.forms(0).Cell1.S(32,20,0,orgtel[k]);
					document.forms(0).Cell1.S(11,23,0,sellerBank[k]);
					document.forms(0).Cell1.S(8,29,0,housearea[k]);
					document.forms(0).Cell1.S(33,29,0,housetotalprice[k]);
					document.forms(0).Cell1.S(46,26,0,houseaddr[k]);
					document.forms(0).Cell1.S(52,23,0,sellerBankAcc[k]);//----------------
					document.forms(0).Cell1.S(8,26,0,orgname[k]);//----------------
					//document.forms(0).Cell1.S(52,26,0,);
					document.forms(0).Cell1.S(52,20,0,loadLimitTime[k]+"(月)");
					document.forms(0).Cell1.S(60,29,0,monthinterest[k]);//----------------
					document.forms(0).Cell1.S(1,40,0,bizDateYear);
					document.forms(0).Cell1.S(7,40,0,bizDateMonth);
					document.forms(0).Cell1.S(9,40,0,bizDate);
					document.forms(0).Cell1.S(20,40,0,redatepsn[k]);
					document.forms(0).Cell1.S(36,40,0,chkagainpsn[k]);
					document.forms(0).Cell1.S(52,40,0,vipchkagainpsn[k]);
					document.forms(0).Cell1.S(60,40,0,lastchkpsn[k]);
					document.forms(0).Cell1.S(33,33,0,orgtel[k]);
					document.forms(0).Cell1.S(34,29,0,housetotalprice[k]);
					
					
					document.forms(0).Cell1.S(19,58,0,bizDateYear);
					document.forms(0).Cell1.S(25,58,0,bizDateMonth);
					document.forms(0).Cell1.S(31,58,0,bizDate);
					document.forms(0).Cell1.S(49,58,0,contractId[k]);
					document.forms(0).Cell1.S(8,61,0,borrowerName[k]);
					document.forms(0).Cell1.S(29,61,0,loankouacc[k]);
					document.forms(0).Cell1.S(58,61,0,loadBankId[k]);
					document.forms(0).Cell1.S(20,65,0,moneydaxie[k]);
					document.forms(0).Cell1.S(8,69,0,orgname[k]);
					document.forms(0).Cell1.S(32,69,0,orgtel[k]);
					document.forms(0).Cell1.S(52,69,0,houseaddr[k]);
					document.forms(0).Cell1.S(8,29,0,housearea[k]);
					document.forms(0).Cell1.S(32,73,0,housetotalprice[k]);
					document.forms(0).Cell1.S(60,73,0,monthinterest[k]);
					document.forms(0).Cell1.S(8,76,0,house[k]);
					document.forms(0).Cell1.S(52,76,0,sellerBank[k]+"-"+sellerBankAcc[k]);
					document.forms(0).Cell1.S(8,79,0,loadLimitTime[k]+"(月)");
					document.forms(0).Cell1.S(60,79,0,firstloanmoney[k]);
					document.forms(0).Cell1.S(1,89,0,bizDateYear);
					document.forms(0).Cell1.S(8,89,0,bizDateMonth);
					document.forms(0).Cell1.S(14,89,0,bizDate);
					document.forms(0).Cell1.S(25,89,0,redatepsn[k]);
					document.forms(0).Cell1.S(40,89,0,chkagainpsn[k]);
					document.forms(0).Cell1.S(52,89,0,vipchkagainpsn[k]);
					document.forms(0).Cell1.S(60,89,0,lastchkpsn[k]);
					
				}
				else{
				    var amountLength=loadmoney[k].length;
		            
		            for(var i=0;i<amountLength;i++){
			            var temp=loadmoney[k].substring((amountLength-i-1),amountLength-i);
			            document.forms(0).Cell1.S((62-i),18,pageCurrent,temp);
			            document.forms(0).Cell1.S((62-i),67,pageCurrent,temp);	
		            }	
					
					document.forms(0).Cell1.S(19,9,pageCurrent,bizDateYear);
					document.forms(0).Cell1.S(25,9,pageCurrent,bizDateMonth);
					document.forms(0).Cell1.S(31,9,pageCurrent,bizDate);
					document.forms(0).Cell1.S(49,9,pageCurrent,contractId[k]);
					document.forms(0).Cell1.S(8,12,pageCurrent,borrowerName[k]);
					document.forms(0).Cell1.S(29,12,pageCurrent,loankouacc[k]);
					document.forms(0).Cell1.S(58,12,pageCurrent,loadBankId[k]);
					document.forms(0).Cell1.S(20,16,pageCurrent,moneydaxie[k]);
					document.forms(0).Cell1.S(8,20,pageCurrent,orgname[k]);
					document.forms(0).Cell1.S(32,20,pageCurrent,orgtel[k]);
					document.forms(0).Cell1.S(52,20,pageCurrent,houseaddr[k]);
					document.forms(0).Cell1.S(8,23,pageCurrent,housearea[k]);
					document.forms(0).Cell1.S(32,23,pageCurrent,housetotalprice[k]);
					document.forms(0).Cell1.S(60,23,pageCurrent,monthinterest[k]);
					document.forms(0).Cell1.S(8,26,pageCurrent,house[k]);
					document.forms(0).Cell1.S(52,26,pageCurrent,sellerBank[k]+"-"+sellerBankAcc[k]);
					document.forms(0).Cell1.S(8,29,pageCurrent,loadLimitTime[k]+"(月)");
					document.forms(0).Cell1.S(60,29,pageCurrent,firstloanmoney[k]);
					document.forms(0).Cell1.S(1,40,pageCurrent,bizDateYear);
					document.forms(0).Cell1.S(8,40,pageCurrent,bizDateMonth);
					document.forms(0).Cell1.S(14,40,pageCurrent,bizDate);
					document.forms(0).Cell1.S(25,40,pageCurrent,redatepsn[k]);
					document.forms(0).Cell1.S(40,40,pageCurrent,chkagainpsn[k]);
					document.forms(0).Cell1.S(52,40,pageCurrent,vipchkagainpsn[k]);
					document.forms(0).Cell1.S(60,40,pageCurrent,lastchkpsn[k]);
					
					
					document.forms(0).Cell1.S(19,58,pageCurrent,bizDateYear);
					document.forms(0).Cell1.S(25,58,pageCurrent,bizDateMonth);
					document.forms(0).Cell1.S(31,58,pageCurrent,bizDate);
					document.forms(0).Cell1.S(49,58,pageCurrent,contractId[k]);
					document.forms(0).Cell1.S(8,61,pageCurrent,borrowerName[k]);
					document.forms(0).Cell1.S(29,61,pageCurrent,loankouacc[k]);
					document.forms(0).Cell1.S(58,61,pageCurrent,loadBankId[k]);
					document.forms(0).Cell1.S(20,65,pageCurrent,moneydaxie[k]);
					document.forms(0).Cell1.S(8,69,pageCurrent,orgname[k]);
					document.forms(0).Cell1.S(32,69,pageCurrent,orgtel[k]);
					document.forms(0).Cell1.S(52,69,pageCurrent,houseaddr[k]);
					document.forms(0).Cell1.S(8,73,pageCurrent,housearea[k]);
					document.forms(0).Cell1.S(32,73,pageCurrent,housetotalprice[k]);
					document.forms(0).Cell1.S(60,73,pageCurrent,monthinterest[k]);
					document.forms(0).Cell1.S(8,76,pageCurrent,house[k]);
					document.forms(0).Cell1.S(52,76,pageCurrent,sellerBank[k]+"-"+sellerBankAcc[k]);
					document.forms(0).Cell1.S(8,79,pageCurrent,loadLimitTime[k]+"(月)");
					document.forms(0).Cell1.S(60,79,pageCurrent,firstloanmoney[k]);
					document.forms(0).Cell1.S(1,89,pageCurrent,bizDateYear);
					document.forms(0).Cell1.S(8,89,pageCurrent,bizDateMonth);
					document.forms(0).Cell1.S(14,89,pageCurrent,bizDate);
					document.forms(0).Cell1.S(25,89,pageCurrent,redatepsn[k]);
					document.forms(0).Cell1.S(40,89,pageCurrent,chkagainpsn[k]);
					document.forms(0).Cell1.S(52,89,pageCurrent,vipchkagainpsn[k]);
					document.forms(0).Cell1.S(60,89,pageCurrent,lastchkpsn[k]);
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
	function back(){
	document.location="issuenoticeTaForwardAC.do";
	}	
</script>
	<script language="JScript.Encode">
		eval(unescape('function%20LoginRegister%28%29//%u6CE8%u518CCELL%0D%0A%20%7B%20%0D%0A%20document.forms%280%29.Cell1.Login%28%22%u6C88%u9633%u91D1%u8F6F%u79D1%u6280%u6709%u9650%u516C%u53F8%22%2C%22%22%2C%2213100104509%22%2C%220740-1662-7775-3003%22%29%3B%20%20%20%20%0D%0A%20%7D'))
  </script>
	<body onload="load();" >
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
			</table>
		</form>
	</body>
</html>

