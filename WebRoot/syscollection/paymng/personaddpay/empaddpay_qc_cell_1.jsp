<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.List" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic" %>
<%@ page import="org.xpup.hafmis.syscollection.paymng.personaddpay.form.*" %>
<%@ page import="org.xpup.hafmis.syscollection.paymng.personaddpay.dto.*" %>
<%@ include file="/checkUrl.jsp"%>
<%
   String path=request.getContextPath();
 %>
<html>
  <head>
  <script src="<%=path%>/js/common.js">
</script>
  <script type="text/javascript">
	function load(){	

	loginReg();
	document.forms(0).Cell1.openfile("<%=path%>/syscollection/paymng/report/pay_wsh.cll","");
	document.forms(0).Cell1.setSheetLabel(0,"第1页");//这句话必须放在加载文件的下边
	var empid=[];
    var empname=[];
    var orgpaymoney=[];
    var emppaymoney=[];
    var paymoney=[];
    var beginmonth=[];
    var endmonth=[];
    var reason=[];
    var money=0;
    var userName="";
    var i=0;
	<%
    		PersonAddPayAF personAddPayAF=(PersonAddPayAF) request.getSession().getAttribute("f");
    		List list=(List)personAddPayAF.getPersonAddPayList();
  		    PersonAddPayDTO dto=(PersonAddPayDTO)list.get(0);
  		    
		for(int j=0;j<list.size();j++)
  			{
  				PersonAddPayDTO dto1=(PersonAddPayDTO)list.get(j);
  				
 	%>
 		empid[i]="<%=dto1.getEmpId()%>";	
 		empname[i]="<%=dto1.getEmpName()%>";
 		// orgpaymoney[i]="<%=dto1.getOrgPaySum().toString()%>";
 		// emppaymoney[i]="<%=dto1.getEmpPaySum().toString()%>";
 		orgpaymoney[i]="<%=dto1.getEmpPaySum().toString()%>";
 		emppaymoney[i]="<%=dto1.getOrgPaySum().toString()%>";
 		paymoney[i]="<%=dto1.getAddPayAmount()%>";
 		money=money+<%=dto1.getAddPayAmount()%>;
 		beginmonth[i]="<%=dto1.getAddPayBeginYearMonth()%>";
 		endmonth[i]="<%=dto1.getAddPayEndYearMonth()%>";
 		reason[i]="<%=dto1.getAddPayReason()%>";
 		 	            
 	    i++; 
 	<%
 			}
 	%>
		var noteNum="<%=personAddPayAF.getNoteNum()%>";
		if(noteNum=="null")
		{
		  noteNum="";
		}



		
	
		
		document.forms(0).Cell1.S(51,1,0,noteNum);

         
         
         
	
	
	
	document.forms(0).Cell1.insertSheetFromFile("<%=path%>/syscollection/paymng/report/empaddpayList_jj.cll",0,1,1);
		document.forms(0).Cell1.setSheetLabel(1,"第2页");//这句话必须放在加载文件的下边
	var num=[];
	var empid=[];
    var empname=[];
    var orgpaymoney=[];
    var emppaymoney=[];
    var paymoney=[];
    var beginmonth=[];
    var endmonth=[];
    var reason=[];
    var cardNum=[];
    var payMode=[];
    var salaryBase=[];
    var orgRate=[];
    var counts=[];
	var bizDate="";
	var userName="";
	var collBankName = "";
	var i=0;
	<%
    		PersonAddPayAF personAddPayAF_1=(PersonAddPayAF) request.getSession().getAttribute("f");
    		List list_1=(List)personAddPayAF_1.getPersonAddPayList();
  		    PersonAddPayDTO dto_1=(PersonAddPayDTO)list_1.get(0);
  			for(int j=0;j<list_1.size();j++)
  			{
  				PersonAddPayDTO dto1=(PersonAddPayDTO)list.get(j);						
 	%>		
 				num[i]="<%=j+1%>";
				empid[i]="<%=dto1.getEmpId()%>";	
		 		empname[i]="<%=dto1.getEmpName()%>";
		 		orgpaymoney[i]="<%=dto1.getEmpPaySum().toString()%>";
		 		emppaymoney[i]="<%=dto1.getOrgPaySum().toString()%>";
		 		paymoney[i]="<%=dto1.getAddPayAmount()%>";
		 		beginmonth[i]="<%=dto1.getAddPayBeginYearMonth()%>";
		 		endmonth[i]="<%=dto1.getAddPayEndYearMonth()%>";
		 		reason[i]="<%=dto1.getAddPayReason()%>";
		 		cardNum[i]="<%=dto1.getCardNum()%>";
		 		payMode[i]="<%=dto1.getPayMode()%>";
		 		salaryBase[i]="<%=dto1.getSalaryBase()%>";
		 		orgRate[i]="<%=dto1.getOrgRate()%>";
		 		counts[i]="<%=dto1.getMonthCounts()%>";
		 		if(payMode[i]=="null"){
		 			payMode[i]="";
		 		}
				i++;
 	<%
 			}
 	%>				
	
 	   			
		var totalLine=empid.length;			//总的行数 数组的长度
		var totalPageLine=10;					//每页显示多少行
		var pageTotal=totalLine/totalPageLine;	//总共分几页
		var pageCurrent=1;						//当前页
		var completeline=0;						//记录行的				
		var salarySum=0;
		var countSum=0;
		var orgSum=0;
		var empSum=0;
		var orgempSum=0;
		var iPage = getInt(totalLine,totalPageLine);
		var orgId = "";
		var name="<%=personAddPayAF_1.getTransactorName()%>";
		var tel="<%=personAddPayAF_1.getTransactorTel()%>";
		if(name == "null"){
		 name = "";
		}
		if(tel == "null"){
			tel="";
		}
		for(k = 0 ; k < totalLine; k++){
			if(k%totalPageLine==0&&k>0)
			{
			   
				// document.forms(0).Cell1.S(1,44,pageCurrent,"本页小计");
				//document.forms(0).Cell1.S(1,totalPageLine+4,pageCurrent,"本页小计");
			    document.forms(0).Cell1.SetFormula (7, totalPageLine+5, pageCurrent, "Sum(G5:G"+(totalPageLine+4)+")" );
			   // document.forms(0).Cell1.SetFormula (11, totalPageLine+5, pageCurrent, "Sum(K5:K"+(totalPageLine+4)+")" );
			    document.forms(0).Cell1.SetFormula (12, totalPageLine+5, pageCurrent, "Sum(L5:L"+(totalPageLine+4)+")" );
			    document.forms(0).Cell1.SetFormula (13, totalPageLine+5, pageCurrent, "Sum(M5:M"+(totalPageLine+4)+")" );
			    document.forms(0).Cell1.SetFormula (14, totalPageLine+5, pageCurrent, "Sum(N5:N"+(totalPageLine+4)+")" );
				//document.forms(0).Cell1.DeleteRow(totalPageLine+5,(totalPageLine+6)-(totalPageLine+4),pageCurrent);
				document.forms(0).Cell1.ReDraw();
				pageCurrent++;	
				completeline=k-2;				
				document.forms(0).Cell1.insertSheetFromFile("<%=path%>/syscollection/paymng/report/empaddpayList_jj.cll",0,1,pageCurrent);
				document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页")	
			}
			if(pageCurrent==1)
			{
				document.forms(0).Cell1.S(5,2,1,formatTen("<%=dto_1.getOrgId()%>")+"<%=dto_1.getOrgId()%>");
				document.forms(0).Cell1.S(9,2,1,"<%=personAddPayAF_1.getPayment_bank_name()%>");
				//document.forms(0).Cell1.S(7,2,0,bizDate);
				document.forms(0).Cell1.S(1,k+5,1,num[k]);
				document.forms(0).Cell1.S(2,k+5,1,empid[k]);
				document.forms(0).Cell1.s(3,k+5,1,empname[k]);
				document.forms(0).Cell1.s(4,k+5,1,cardNum[k]);
				document.forms(0).Cell1.s(6,k+5,1,payMode[k]);
				document.forms(0).Cell1.d(7,k+5,1,salaryBase[k]);
				document.forms(0).Cell1.D(8,k+5,1,orgRate[k]);
				document.forms(0).Cell1.S(9,k+5,1,beginmonth[k]);
				document.forms(0).Cell1.S(10,k+5,1,endmonth[k]);
				document.forms(0).Cell1.s(11,k+5,1,counts[k]);
				document.forms(0).Cell1.d(12,k+5,1,orgpaymoney[k]);
				document.forms(0).Cell1.d(13,k+5,1,emppaymoney[k]);
				document.forms(0).Cell1.d(14,k+5,1,paymoney[k]);
				salarySum=salarySum+parseFloat(salaryBase[k]);
				countSum=countSum+parseFloat(counts[k]);
				orgSum=orgSum+parseFloat(orgpaymoney[k]);
				empSum=empSum+parseFloat(emppaymoney[k]);
				orgempSum=orgempSum+parseFloat(paymoney[k]);
					document.forms(0).Cell1.SetFormula (7, totalPageLine+5, pageCurrent, "Sum(G5:G"+(totalLine+4)+")" );//,loopcell() > 5
				//	document.forms(0).Cell1.SetFormula (11, totalPageLine+5, pageCurrent, "Sum(K5:K"+(totalLine+4)+")" );//,loopcell() > 5
					document.forms(0).Cell1.SetFormula (12, totalPageLine+5, pageCurrent, "Sum(L5:L"+(totalLine+4)+")" );
					document.forms(0).Cell1.SetFormula (13, totalPageLine+5, pageCurrent, "Sum(M5:M"+(totalLine+4)+")" );
					document.forms(0).Cell1.SetFormula (14, totalPageLine+5, pageCurrent, "Sum(N5:N"+(totalLine+4)+")" );
					//document.forms(0).Cell1.S(1,totalLine+5,pageCurrent,"总计");
					//document.forms(0).Cell1.d(7,totalPageLine+6,pageCurrent,salarySum);
					//document.forms(0).Cell1.d(9,totalPageLine+6,pageCurrent,countSum);
					//document.forms(0).Cell1.d(10,totalPageLine+6,pageCurrent,orgSum);
					//document.forms(0).Cell1.d(11,totalPageLine+6,pageCurrent,empSum);
					//document.forms(0).Cell1.d(12,totalPageLine+6,pageCurrent,orgempSum);
					
					
					document.forms(0).Cell1.s(3,totalPageLine+9,pageCurrent,name);
					document.forms(0).Cell1.s(5,totalPageLine+9,pageCurrent,tel);
					document.forms(0).Cell1.s(8,totalPageLine+9,pageCurrent,"第"+(pageCurrent+1)+"页");
					document.forms(0).Cell1.s(10,totalPageLine+9,pageCurrent,"共"+iPage+"页");
			}else{//向第一页后的所有页插数据
				//document.forms(0).Cell1.S(1,2,pageCurrent,"第"+(pageCurrent+1)+"/"+iPage+"页");
				//document.forms(0).Cell1.S(3,2,pageCurrent,collBankName);
				//document.forms(0).Cell1.S(5,2,pageCurrent,userName);
				//document.forms(0).Cell1.S(7,2,pageCurrent,bizDate);
				document.forms(0).Cell1.S(5,2,pageCurrent,formatTen("<%=dto_1.getOrgId()%>")+"<%=dto_1.getOrgId()%>");
				document.forms(0).Cell1.S(9,2,pageCurrent,"<%=personAddPayAF_1.getPayment_bank_name()%>");
				document.forms(0).Cell1.S(1,k-completeline+3,pageCurrent,num[k]);
				document.forms(0).Cell1.S(2,k-completeline+3,pageCurrent,empid[k]);
				document.forms(0).Cell1.s(3,k-completeline+3,pageCurrent,empname[k]);
				document.forms(0).Cell1.s(4,k-completeline+3,pageCurrent,cardNum[k]);
				document.forms(0).Cell1.s(6,k-completeline+3,pageCurrent,payMode[k]);
				document.forms(0).Cell1.d(7,k-completeline+3,pageCurrent,salaryBase[k]);
				document.forms(0).Cell1.s(8,k-completeline+3,pageCurrent,orgRate[k]);
				document.forms(0).Cell1.S(9,k-completeline+3,pageCurrent,beginmonth[k]);
				document.forms(0).Cell1.S(10,k-completeline+3,pageCurrent,endmonth[k]);
				document.forms(0).Cell1.s(11,k-completeline+3,pageCurrent,counts[k]);
				document.forms(0).Cell1.d(12,k-completeline+3,pageCurrent,orgpaymoney[k]);
				document.forms(0).Cell1.d(13,k-completeline+3,pageCurrent,emppaymoney[k]);
				document.forms(0).Cell1.d(14,k-completeline+3,pageCurrent,paymoney[k]);
				salarySum=salarySum+parseFloat(salaryBase[k]);
				countSum=countSum+parseFloat(counts[k]);
				orgSum=orgSum+parseFloat(orgpaymoney[k]);
				empSum=empSum+parseFloat(emppaymoney[k]);
				orgempSum=orgempSum+parseFloat(paymoney[k]);			
					
					document.forms(0).Cell1.s(3,totalPageLine+9,pageCurrent,name);
					document.forms(0).Cell1.s(5,totalPageLine+9,pageCurrent,tel);
					document.forms(0).Cell1.s(8,totalPageLine+9,pageCurrent,"第"+(pageCurrent+1)+"页");
					document.forms(0).Cell1.s(10,totalPageLine+9,pageCurrent,"共"+iPage+"页");
			}	
		}
				if(completeline==0)//查询出来的记录可以在一页显示的时候会进入
				{
				     
					//document.forms(0).Cell1.S(1,totalLine+4,pageCurrent,"本页小计");
					document.forms(0).Cell1.SetFormula (7, totalPageLine+5, pageCurrent, "Sum(G5:G"+(totalLine+4)+")" );//,loopcell() > 5
				//	document.forms(0).Cell1.SetFormula (11, totalPageLine+5, pageCurrent, "Sum(K5:K"+(totalLine+4)+")" );//,loopcell() > 5
					document.forms(0).Cell1.SetFormula (12, totalPageLine+5, pageCurrent, "Sum(L5:L"+(totalLine+4)+")" );
					document.forms(0).Cell1.SetFormula (13, totalPageLine+5, pageCurrent, "Sum(M5:M"+(totalLine+4)+")" );
					document.forms(0).Cell1.SetFormula (14, totalPageLine+5, pageCurrent, "Sum(N5:N"+(totalLine+4)+")" );
					//document.forms(0).Cell1.S(1,totalLine+5,pageCurrent,"总计");
					document.forms(0).Cell1.d(7,totalPageLine+6,pageCurrent,salarySum);
				//	document.forms(0).Cell1.d(11,totalPageLine+6,pageCurrent,countSum);
					document.forms(0).Cell1.d(12,totalPageLine+6,pageCurrent,orgSum);
					document.forms(0).Cell1.d(13,totalPageLine+6,pageCurrent,empSum);
					document.forms(0).Cell1.d(14,totalPageLine+6,pageCurrent,orgempSum);
					
					
					document.forms(0).Cell1.s(3,totalPageLine+9,pageCurrent,name);
					document.forms(0).Cell1.s(5,totalPageLine+9,pageCurrent,tel);
					document.forms(0).Cell1.s(8,totalPageLine+9,pageCurrent,"第"+(pageCurrent+1)+"页");
					document.forms(0).Cell1.s(10,totalPageLine+9,pageCurrent,"共"+iPage+"页");
					//document.forms(0).Cell1.s(1,totalLine+6,pageCurrent,"制表人");
					//document.forms(0).Cell1.s(2,totalLine+6,pageCurrent,userName);
					
					//document.forms(0).Cell1.s(3,totalLine+6,pageCurrent,"操作日期");
					//document.forms(0).Cell1.s(4,totalLine+6,pageCurrent,bizDate);
					//document.forms(0).Cell1.DeleteRow(totalLine+6,(totalPageLine+9)-(totalLine+8),pageCurrent);
					document.forms(0).Cell1.ReDraw();
				}
				else
				{
					//document.forms(0).Cell1.S(1,totalLine-completeline+2,pageCurrent,"本页小计");
					document.forms(0).Cell1.SetFormula (7, totalPageLine+5, pageCurrent, "Sum(G5:G"+(totalLine-(completeline-2))+")" );
				//	document.forms(0).Cell1.SetFormula (11, totalPageLine+5, pageCurrent, "Sum(K5:K"+(totalLine-(completeline-2))+")" );
					document.forms(0).Cell1.SetFormula (12,totalPageLine+5, pageCurrent, "Sum(L5:L"+(totalLine-(completeline-2))+")" );
					document.forms(0).Cell1.SetFormula (13,totalPageLine+5, pageCurrent, "Sum(M5:M"+(totalLine-(completeline-2))+")" );
					document.forms(0).Cell1.SetFormula (14,totalPageLine+5, pageCurrent, "Sum(N5:N"+(totalLine-(completeline-2))+")" );
					//document.forms(0).Cell1.S(1,totalLine-completeline+3,pageCurrent,"总计");
					document.forms(0).Cell1.d(7,totalPageLine+6,pageCurrent,salarySum);
				//	document.forms(0).Cell1.d(11,totalPageLine+6,pageCurrent,countSum);
					document.forms(0).Cell1.d(12,totalPageLine+6,pageCurrent,orgSum);
					document.forms(0).Cell1.d(13,totalPageLine+6,pageCurrent,empSum);
					document.forms(0).Cell1.d(14,totalPageLine+6,pageCurrent,orgempSum);
					
					document.forms(0).Cell1.s(3,totalPageLine+9,pageCurrent,name);
					document.forms(0).Cell1.s(5,totalPageLine+9,pageCurrent,tel);
					document.forms(0).Cell1.s(8,totalPageLine+9,pageCurrent,"第"+(pageCurrent+1)+"页");
					document.forms(0).Cell1.s(10,totalPageLine+9,pageCurrent,"共"+iPage+"页");
					
					//document.forms(0).Cell1.s(1,totalLine-completeline+4,pageCurrent,"归集银行");
					//document.forms(0).Cell1.MergeCells(2,totalLine-completeline+4,3,totalLine-completeline+4);
					//document.forms(0).Cell1.s(2,totalLine-completeline+4,pageCurrent,collectionBank[0]);
					
					//document.forms(0).Cell1.s(1,totalLine-completeline+4,pageCurrent,"制表人");
					//document.forms(0).Cell1.s(2,totalLine-completeline+4,pageCurrent,userName);
					
					//document.forms(0).Cell1.s(3,totalLine-completeline+4,pageCurrent,"操作日期");
					//document.forms(0).Cell1.s(4,totalLine-completeline+4,pageCurrent,bizDate);
					//document.forms(0).Cell1.DeleteRow(totalLine-completeline+4,totalPageLine-(totalLine-completeline-3),pageCurrent);
					document.forms(0).Cell1.ReDraw();
				}	
				document.forms(0).Cell1.AllowExtend=false;
				document.forms(0).Cell1.AllowDragdrop=false;
				
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
  <body onload = "load();" onContextmenu="return false"> 
    <form action="">
    <table align="center">
<tr><OBJECT id=Cell1 style= "LEFT:0px;WIDTH:700px;  TOP:0px;HEIGHT:400px" codeBase="http://192.168.1.44:8088/hafmis/CellWeb5.CAB#version=5,3,7,321" classid=clsid:3F166327-8030-4881-8BD2-EA25350E574A VIEWASTEXT><PARAM NAME="_Version" VALUE="65536"><PARAM NAME="_ExtentX" VALUE="10266"><PARAM NAME="_ExtentY" VALUE="7011"><PARAM NAME="_StockProps" VALUE= "0"></OBJECT></tr> 
<tr>
<td><input type="button" name="print" value = "打印预览" onclick = "printPreview();"/></td>
<td><INPUT id="Button1" onclick="printsheet()" type="button" value=" 打印 " name="Button1"></td>
<td><INPUT id="Button1" onclick="Button1_onclick()" type="button" value="另存为Excel" name="Button1"></td>
<td><INPUT id="Button1" onclick="Button2_onclick()" type="button" value="另存为pdf" name="Button1"></td>
<td><INPUT id="Button3" style="WIDTH: 100px" onclick="Button3_onclick()" type="button" value="页面设置"></td>
<td><INPUT id="Button3" style="WIDTH: 100px" onclick="Button4_onclick()" type="button" value="查找对话框"></td>
<td><INPUT id="Button3" style="WIDTH: 100px" onclick="Button5_onclick()" type="button" value="excel导入"></td>
<td><INPUT id="Button1" onclick="javascript:history.back();" type="button" value=" 返回 "></td>	
</table>
</form>
  </body>
</html>
