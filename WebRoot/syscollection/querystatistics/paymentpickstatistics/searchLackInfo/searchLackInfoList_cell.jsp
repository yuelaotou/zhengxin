<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.List" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic" %>
<%@ page import="org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.searchLackInfo.dto.*" %>
<%@ page import="org.xpup.hafmis.common.util.BusiTools" %>
<%@ include file="/checkUrl.jsp"%>
<%
   String path=request.getContextPath();
 %>
<html>
<head>
<script src="<%=path%>/js/common.js"></script>
</head>
<script type="text/javascript">
	function load(){	
	loginReg();
	document.forms(0).Cell1.openfile("<%=path%>/syscollection/querystatistics/paymentpickstatistics/searchLackInfo/report/searchlackInfo_list_jj.cll","");

	var orgid=[];
	var orgname=[];
	var orgmonth=[];
	var orgpaylack=[];
	var emppaylack=[];
	var sumpaylack=[];
	var transactorname=[];
	var tel=[];
	var address=[];
	var org_pay_month=[];
	var emp_pay_month=[];
	var org_rate=[];
	var emp_rate=[];
	var orgyue=[];
	var yjehj=[];
	var zcjcrs=[];
	var num=[];
	var orgPayMonth=[];
	var bank=[];
	var bankAcc=[];
	var empmonth=[];
	var empPayMonth=[];
	var postalcode=[];
	var payDay=[];
	var sumpay=[];
	var i=0;
	<%
			session = request.getSession();
    		List list=(List) session.getAttribute("listcount");
  			SearchLackInfoContentDTO dto=null;
  			for(int j=0;j<list.size();j++)
  			{
  				dto=(SearchLackInfoContentDTO)list.get(j);
  				String tempid = dto.getOrgcode().toString();
				String orgid = 	BusiTools.convertTenNumber(tempid);
 	%>	
				num[i]="<%=j+1%>";//序号
				orgid[i]="<%=orgid%>";//单位编号
				orgname[i]="<%=dto.getOrgname()%>";//单位名称
				zcjcrs[i]="<%=dto.getZcjcrs()%>";//欠缴人数
				orgpaylack[i]=<%=dto.getOrgPay()%>;//单位欠缴额
				emppaylack[i]=<%=dto.getEmpPay()%>;//职工欠缴额
				orgmonth[i]="<%=dto.getOrgMonth()%>";//单位欠月数
				empmonth[i]="<%=dto.getEmpMonth()%>";//职工欠月数
				sumpaylack[i]=<%=dto.getSumPay()%>;//欠缴总额
				sumpay[i]=<%=dto.getSumPay_1()%>;//欠缴总额
				orgPayMonth[i]=<%=dto.getOrgPayMonth()%>;//单位缴至年月
				empPayMonth[i]=<%=dto.getEmpPayMonth()%>;//职工缴至年月
				orgyue[i]="<%=dto.getOrgBalance()%>";//单位余额
				address[i]="<%=dto.getAddress()%>";//单位地址
				if(address[i]=="null"){
					address[i]="";
				}
				tel[i]="<%=dto.getTel()%>";//联系电话
				if(tel[i]=="null"){
					tel[i]="";
				}
				transactorname[i]="<%=dto.getTransactor_name()%>";//联系人
				if(transactorname[i]=="null"){
					transactorname[i]="";
				}
				postalcode[i]="<%=dto.getPostalcode()%>";//邮政编码
				if(postalcode[i]=="null"){
					postalcode[i]="";
				}
				bank[i]="<%=dto.getPaybankName()%>";//开户行
				if(bank[i]=="null"){
					bank[i]="";
				}
				bankAcc[i]="<%=dto.getPaybankAcc()%>";//开户行账号
				if(bankAcc[i]=="null"){
					bankAcc[i]="";
				}
				payDay[i]="<%=dto.getPaydate()%>";//发薪日
				if(payDay[i]=="null"){
					payDay[i]="";
				}
				i++;
 	<%
 			}
 	%>
 		var totalLine=orgname.length;			//总的行数
		var totalPageLine=2000;					//每页显示多少行
		var pageTotal=totalLine/totalPageLine;	//总共分几页
		var pageCurrent=0;						//当前页
		var completeline=0;						//记录行的
		var sumtotal=0;						    //总的合计-欠缴总额
		var orgtotal=0;				            //总的合计-欠单位缴额
		var emptotal=0;				            //总的合计-欠职工缴额
		var rsCount=0;//人数
		var sumorgmonth=0;
		var sumempmonth=0;
		var orgbalance=0;
		
		for(k = 0 ; k < totalLine; k++){
			if(k%totalPageLine==0&&k>0)
			{
				document.forms(0).Cell1.S(2,totalPageLine+4,pageCurrent,"本页小计");
				document.forms(0).Cell1.SetFormula (4, totalPageLine+4, pageCurrent, "Sum(D4:D"+(totalPageLine+3)+")" );
				document.forms(0).Cell1.SetFormula (5, totalPageLine+4, pageCurrent, "Sum(E4:E"+(totalPageLine+3)+")" );
				document.forms(0).Cell1.SetFormula (6, totalPageLine+4, pageCurrent, "Sum(F4:F"+(totalPageLine+3)+")" );
				document.forms(0).Cell1.SetFormula (7, totalPageLine+4, pageCurrent, "Sum(G4:G"+(totalPageLine+3)+")" );
				document.forms(0).Cell1.SetFormula (8, totalPageLine+4, pageCurrent, "Sum(H4:H"+(totalPageLine+3)+")" );
				document.forms(0).Cell1.SetFormula (9, totalPageLine+4, pageCurrent, "Sum(I4:I"+(totalPageLine+3)+")" );
				document.forms(0).Cell1.SetFormula (12, totalPageLine+4, pageCurrent, "Sum(L4:L"+(totalPageLine+3)+")" );
				document.forms(0).Cell1.DeleteRow(totalPageLine+5,(totalPageLine+6)-(totalPageLine+4),pageCurrent);
				document.forms(0).Cell1.ReDraw();
				pageCurrent++;	
				completeline=k-2;				
				document.forms(0).Cell1.insertSheetFromFile("<%=path%>/syscollection/querystatistics/paymentpickstatistics/searchLackInfo/report/searchlackInfo_list_jj_1.cll",0,1,pageCurrent);
				document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页")	
			}
			if(pageCurrent==0)
			{
				document.forms(0).Cell1.S(1,k+4,0,num[k]);
				document.forms(0).Cell1.S(2,k+4,0,orgid[k]);
				document.forms(0).Cell1.S(3,k+4,0,orgname[k]);
				document.forms(0).Cell1.d(4,k+4,0,zcjcrs[k]);
				document.forms(0).Cell1.d(5,k+4,0,orgpaylack[k]);
				document.forms(0).Cell1.d(6,k+4,0,emppaylack[k]);
				document.forms(0).Cell1.D(7,k+4,0,orgmonth[k]);
				document.forms(0).Cell1.D(8,k+4,0,empmonth[k]);
				document.forms(0).Cell1.D(9,k+4,0,sumpaylack[k]);
				document.forms(0).Cell1.S(10,k+4,0,orgPayMonth[k]);
				document.forms(0).Cell1.S(11,k+4,0,empPayMonth[k]);
				document.forms(0).Cell1.D(12,k+4,0,orgyue[k]);
				document.forms(0).Cell1.S(13,k+4,0,address[k]);
				document.forms(0).Cell1.S(14,k+4,0,tel[k]);
				document.forms(0).Cell1.S(15,k+4,0,transactorname[k]);
				document.forms(0).Cell1.S(16,k+4,0,postalcode[k]);
				document.forms(0).Cell1.S(17,k+4,0,bank[k]);
				document.forms(0).Cell1.S(18,k+4,0,bankAcc[k]);
				document.forms(0).Cell1.S(19,k+4,0,payDay[k]);
				document.forms(0).Cell1.d(20,k+4,0,sumpay[k]);
				rsCount=rsCount+parseFloat(zcjcrs[k]);
				orgtotal=orgtotal+parseFloat(orgpaylack[k]);
				emptotal=emptotal+parseFloat(emppaylack[k]);
				sumorgmonth=sumorgmonth+parseFloat(orgmonth[k]);
				sumempmonth=sumempmonth+parseFloat(empmonth[k]);
				sumtotal=sumtotal+parseFloat(sumpaylack[k]);
				orgbalance=orgbalance+parseFloat(orgyue[k]);
			}else{//向第一页后的所有页插数据
				document.forms(0).Cell1.S(1,k-completeline+2,pageCurrent,num[k]);
				document.forms(0).Cell1.S(2,k-completeline+2,pageCurrent,orgid[k]);
				document.forms(0).Cell1.S(3,k-completeline+2,pageCurrent,orgname[k]);
				document.forms(0).Cell1.S(4,k-completeline+2,pageCurrent,zcjcrs[k]);
				document.forms(0).Cell1.d(5,k-completeline+2,pageCurrent,orgpaylack[k]);
				document.forms(0).Cell1.d(6,k-completeline+2,pageCurrent,emppaylack[k]);
				document.forms(0).Cell1.D(7,k-completeline+2,pageCurrent,orgmonth[k]);
				document.forms(0).Cell1.D(8,k-completeline+2,pageCurrent,empmonth[k]);
				document.forms(0).Cell1.D(9,k-completeline+2,pageCurrent,sumpaylack[k]);
				document.forms(0).Cell1.S(10,k-completeline+2,pageCurrent,orgPayMonth[k]);
				document.forms(0).Cell1.S(11,k-completeline+2,pageCurrent,empPayMonth[k]);
				document.forms(0).Cell1.D(12,k-completeline+2,pageCurrent,orgyue[k]);
				document.forms(0).Cell1.S(13,k-completeline+2,pageCurrent,address[k]);
				document.forms(0).Cell1.S(14,k-completeline+2,pageCurrent,tel[k]);
				document.forms(0).Cell1.S(15,k-completeline+2,pageCurrent,transactorname[k]);
				document.forms(0).Cell1.S(16,k-completeline+2,pageCurrent,postalcode[k]);
				document.forms(0).Cell1.S(17,k-completeline+2,pageCurrent,bank[k]);
				document.forms(0).Cell1.S(18,k-completeline+2,pageCurrent,bankAcc[k]);
				document.forms(0).Cell1.S(19,k-completeline+2,pageCurrent,payDay[k]);
				document.forms(0).Cell1.d(20,k-completeline+2,pageCurrent,sumpay[k]);
				rsCount=rsCount+parseFloat(zcjcrs[k]);
				orgtotal=orgtotal+parseFloat(orgpaylack[k]);
				emptotal=emptotal+parseFloat(emppaylack[k]);
				sumorgmonth=sumorgmonth+parseFloat(orgmonth[k]);
				sumempmonth=sumempmonth+parseFloat(empmonth[k]);
				sumtotal=sumtotal+parseFloat(sumpaylack[k]);
				orgbalance=orgbalance+parseFloat(orgyue[k]);
			}	
		}
		

				if(completeline==0)//查询出来的记录可以在一页显示的时候会进入
				{
					document.forms(0).Cell1.S(2,totalLine+4,pageCurrent,"本页小计");
					document.forms(0).Cell1.SetFormula (4, totalLine+4, pageCurrent, "Sum(D4:D"+(totalLine+3)+")" );
					document.forms(0).Cell1.SetFormula (5, totalLine+4, pageCurrent, "Sum(E4:E"+(totalLine+3)+")" );
					document.forms(0).Cell1.SetFormula (6, totalLine+4, pageCurrent, "Sum(F4:F"+(totalLine+3)+")" );
					document.forms(0).Cell1.SetFormula (7, totalLine+4, pageCurrent, "Sum(G4:G"+(totalLine+3)+")" );
					document.forms(0).Cell1.SetFormula (8, totalLine+4, pageCurrent, "Sum(H4:H"+(totalLine+3)+")" );
					document.forms(0).Cell1.SetFormula (9, totalLine+4, pageCurrent, "Sum(I4:I"+(totalLine+3)+")" );
					document.forms(0).Cell1.SetFormula (12, totalLine+4, pageCurrent, "Sum(L4:L"+(totalLine+3)+")" );
					document.forms(0).Cell1.S(2,totalLine+5,pageCurrent,"总计");
					document.forms(0).Cell1.d(4,totalLine+5,pageCurrent,rsCount);
					document.forms(0).Cell1.d(5,totalLine+5,pageCurrent,emptotal);
					document.forms(0).Cell1.d(6,totalLine+5,pageCurrent,sumtotal);
					document.forms(0).Cell1.d(7,totalLine+5,pageCurrent,sumorgmonth);
					document.forms(0).Cell1.d(8,totalLine+5,pageCurrent,sumempmonth);
					document.forms(0).Cell1.d(9,totalLine+5,pageCurrent,sumtotal);
					document.forms(0).Cell1.d(12,totalLine+5,pageCurrent,orgbalance);
					document.forms(0).Cell1.DeleteRow(totalLine+6,totalPageLine-(totalLine+5),pageCurrent);
					document.forms(0).Cell1.ReDraw();
				}	
								
				else
				{
					document.forms(0).Cell1.S(2,totalLine-completeline+2,pageCurrent,"本页小计");   
					//F1 第F列的第1行: N9 到第N列的第9行  求和
					document.forms(0).Cell1.SetFormula (4, totalLine-completeline+2, pageCurrent, "Sum(D4:D"+(totalLine-(completeline-1))+")" );
					document.forms(0).Cell1.SetFormula (5, totalLine-completeline+2, pageCurrent, "Sum(E4:E"+(totalLine-(completeline-1))+")" );
					document.forms(0).Cell1.SetFormula (6, totalLine-completeline+2, pageCurrent, "Sum(F4:F"+(totalLine-(completeline-1))+")" );
					document.forms(0).Cell1.SetFormula (7, totalLine-completeline+2, pageCurrent, "Sum(G4:G"+(totalLine-(completeline-1))+")" );
					document.forms(0).Cell1.SetFormula (8, totalLine-completeline+2, pageCurrent, "Sum(H4:H"+(totalLine-(completeline-1))+")" );
					document.forms(0).Cell1.SetFormula (9, totalLine-completeline+2, pageCurrent, "Sum(I4:I"+(totalLine-(completeline-1))+")" );
					document.forms(0).Cell1.SetFormula (12, totalLine-completeline+2, pageCurrent, "Sum(L4:L"+(totalLine-(completeline-1))+")" );
					document.forms(0).Cell1.S(2,totalLine-completeline+3,pageCurrent,"总计");
					document.forms(0).Cell1.d(4,totalLine-completeline+3,pageCurrent,rsCount);
					document.forms(0).Cell1.d(5,totalLine-completeline+3,pageCurrent,emptotal);
					document.forms(0).Cell1.d(6,totalLine-completeline+3,pageCurrent,sumtotal);
					document.forms(0).Cell1.d(7,totalLine-completeline+3,pageCurrent,sumorgmonth);
					document.forms(0).Cell1.d(8,totalLine-completeline+3,pageCurrent,sumempmonth);
					document.forms(0).Cell1.d(9,totalLine-completeline+3,pageCurrent,sumtotal);
					document.forms(0).Cell1.d(12,totalLine-completeline+3,pageCurrent,orgbalance);
					document.forms(0).Cell1.DeleteRow(totalLine-completeline+4,totalPageLine-(totalLine-completeline-3),pageCurrent);
					document.forms(0).Cell1.ReDraw();
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
<body onload = "load();"  onContextmenu="return false"> 
<form action="">
<table align="center">
	<tr><OBJECT id=Cell1 style= "LEFT:0px;WIDTH:700px;  TOP:0px;HEIGHT:400px" codeBase="http://192.168.1.44:8088/hafmis/CellWeb5.CAB#version=5,3,7,321" classid=clsid:3F166327-8030-4881-8BD2-EA25350E574A VIEWASTEXT><PARAM NAME="_Version" VALUE="65536"><PARAM NAME="_ExtentX" VALUE="10266"><PARAM NAME="_ExtentY" VALUE="7011"><PARAM NAME="_StockProps" VALUE= "0"></OBJECT></tr> 
	<tr><td><input type="button" name="print" value = "打印预览" onclick = "printPreview();"/></td>
		<td><INPUT id="Button1" onclick="Button1_onclick()" type="button" value="另存为Excel" name="Button1"></td>
		<td><INPUT id="Button1" onclick="Button2_onclick()" type="button" value="另存为pdf" name="Button1"></td>
		<td><INPUT id="Button3" style="WIDTH: 100px" onclick="Button3_onclick()" type="button" value="页面设置"></td>
		<td><INPUT id="Button3" style="WIDTH: 100px" onclick="Button4_onclick()" type="button" value="查找对话框"></td>
		<td><INPUT id="Button3" style="WIDTH: 100px" onclick="Button5_onclick()" type="button" value="excel导入"></td>
		<td><INPUT id="Button1" onclick="printsheet()" type="button" value=" 打印 " name="Button1"></td>
		<td><INPUT id="Button3" onclick="javascript:history.back();" type="button" value=" 返回 ">	</td>
	</tr>
</table>
</form>
</body>
</html>
