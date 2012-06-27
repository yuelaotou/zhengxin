<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page  import="java.lang.*" import="java.util.*"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ page import="org.xpup.hafmis.syscollection.querystatistics.collfncomparison.dto.CollFnComparisonEmpAccountDTO" %>
<%
String path = request.getContextPath();
%>

<html>
  <head>
    <title>My JSP 'empaccountprint_print.jsp' starting page</title>
  </head>
<script src="<%=path%>/js/common.js"></script>

<script>
	function load(){
		var head = [];
		
		var collDateArray = new Array();
	  	var collDocNumArray = new Array();
	  	var bizTypeArray = new Array();
	  	var bizStatusArray = new Array();
	  	var debitArray = new Array();
	  	var creditArray = new Array();
	  	
	  	var interestArray = new Array();
	  	var directionArray = new Array();
	  	var moneySumArray = new Array();
		
		var i = 0;
		loginReg();
			<%	
				List list = (List)request.getAttribute("print_list");
	    		for(int j=0;j<list.size();j++){
	    		Object[] obj = (Object[])list.get(j);
	    		
	    		List tempList = (List)obj[1];
	    		String headinfo = (String)obj[0];
	    		int sum = 0;
	    		List list_ = new ArrayList();
	    		for(int l=0;l<tempList.size();l++){
	    			CollFnComparisonEmpAccountDTO olddto = (CollFnComparisonEmpAccountDTO)tempList.get(l);
	    			if(l>0){
		    			if(l==39){
			    			CollFnComparisonEmpAccountDTO dto1 = new CollFnComparisonEmpAccountDTO();
			    			CollFnComparisonEmpAccountDTO olddto1 = (CollFnComparisonEmpAccountDTO)tempList.get(l-1);
			    			dto1.setCollDate(olddto1.getCollDate());
			    			dto1.setCollDocNum(olddto1.getCollDocNum());
			    			dto1.setBizType("过次页");
			    			dto1.setBizStatus(olddto1.getBizStatus());
			    			dto1.setDebit(olddto1.getDebit());
			    			dto1.setCredit(olddto1.getCredit());
			    			dto1.setInterest(olddto1.getInterest());
			    			dto1.setDirection(olddto1.getDirection());
			    			dto1.setMoneySum(olddto1.getMoneySum());
		    			
		    				list_.add(dto1);
		    				CollFnComparisonEmpAccountDTO dto2 = new CollFnComparisonEmpAccountDTO();
			    			dto2.setCollDate(olddto1.getCollDate());
			    			dto2.setCollDocNum(olddto1.getCollDocNum());
			    			dto2.setBizType("承前页");
			    			dto2.setBizStatus(olddto1.getBizStatus());
			    			dto2.setDebit(olddto1.getDebit());
			    			dto2.setCredit(olddto1.getCredit());
			    			dto2.setInterest(olddto1.getInterest());
			    			dto2.setDirection(olddto1.getDirection());
			    			dto2.setMoneySum(olddto1.getMoneySum());
		    			
		    				list_.add(dto2);
		    			}else if((l-1)%38==0&&l>39){
		    				CollFnComparisonEmpAccountDTO dto1 = new CollFnComparisonEmpAccountDTO();
		    				CollFnComparisonEmpAccountDTO olddto1 = (CollFnComparisonEmpAccountDTO)tempList.get(l-1);
			    			dto1.setCollDate(olddto1.getCollDate());
			    			dto1.setCollDocNum(olddto1.getCollDocNum());
			    			dto1.setBizType("过次页");
			    			dto1.setBizStatus(olddto1.getBizStatus());
			    			dto1.setDebit(olddto1.getDebit());
			    			dto1.setCredit(olddto1.getCredit());
			    			dto1.setInterest(olddto1.getInterest());
			    			dto1.setDirection(olddto1.getDirection());
			    			dto1.setMoneySum(olddto1.getMoneySum());
		    			
		    				list_.add(dto1);
		    				CollFnComparisonEmpAccountDTO dto2 = new CollFnComparisonEmpAccountDTO();
			    			dto2.setCollDate(olddto1.getCollDate());
			    			dto2.setCollDocNum(olddto1.getCollDocNum());
			    			dto2.setBizType("承前页");
			    			dto2.setBizStatus(olddto1.getBizStatus());
			    			dto2.setDebit(olddto1.getDebit());
			    			dto2.setCredit(olddto1.getCredit());
			    			dto2.setInterest(olddto1.getInterest());
			    			dto2.setDirection(olddto1.getDirection());
			    			dto2.setMoneySum(olddto1.getMoneySum());
		    			
		    				list_.add(dto2);
		    			}
	    			}
	    			list_.add(olddto);
	    		}
	    	%>
	    		head[i]="<%=headinfo%>";
	    		
	    		var collDate = [];
				var collDocNum = [];
				var bizType = [];
				var bizStatus = [];
				var debit = [];
				var credit = [];
				var interest = [];
				var direction = [];
				var moneySum = [];
				var j = 0;
			<%  		
					for(int k=0;k<list_.size();k++){
						
			   			CollFnComparisonEmpAccountDTO dto = (CollFnComparisonEmpAccountDTO)list_.get(k);
			%>
						collDate[j] = "<%=dto.getCollDate()%>";
						collDocNum[j] = "<%=dto.getCollDocNum() %>";
						bizType[j] = "<%=dto.getBizType() %>";
						bizStatus[j] = "<%=dto.getBizStatus() %>";
						debit[j] = "<%=dto.getDebit() %>";
						credit[j] = "<%=dto.getCredit()%>"; 
						interest[j] = "<%=dto.getInterest() %>";
						direction[j] = "<%=dto.getDirection()%>";
						moneySum[j] = "<%=dto.getMoneySum()%>";

						j++;
				<%}%>
						collDateArray[i] = collDate;
					  	collDocNumArray[i] = collDocNum;
					  	bizTypeArray[i] = bizType;
					  	bizStatusArray[i] = bizStatus;
					  	debitArray[i] = debit;
					  	creditArray[i] = credit;
					  	
					  	interestArray[i] = interest;
					  	directionArray[i] = direction;
					  	moneySumArray[i] = moneySum;
				i++;
			<%
				}
			%>
			
			
			var totalLine=head.length;		//总的页数
			var pageCurrent=0;						//当前页
			for(l = 0 ; l < totalLine; l++){
			
				if(l==0){
					document.forms(0).Cell1.openfile("<%=path%>/syscollection/querystatistics/collfncomparison/report/empaccount_print.cll","");
				}else{
	
					document.forms(0).Cell1.ReDraw();
		 			pageCurrent = pageCurrent+1;
		 			document.forms(0).Cell1.insertSheetFromFile("<%=path%>/syscollection/querystatistics/collfncomparison/report/empaccount_print.cll",0,1,pageCurrent);
					document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页");	
				}
				document.forms(0).Cell1.S(1,2,pageCurrent,head[l]);
				var totalLine1=collDateArray[l].length;		//总的行数
				var totalPageLine=40;					//每页显示多少行
				var pageTotal=totalLine1/totalPageLine;	//总共分几页
				var completeline=0;						//记录行的
				var iPage = getInt(totalLine1,totalPageLine);
				var temp_page = 1;
				document.forms(0).Cell1.S(1,44,pageCurrent,"第"+(pageCurrent+1)+"页");
				for(m=0;m<totalLine1;m++){
					if(m%totalPageLine==0&&m>0){
						temp_page = temp_page +	1;
						document.forms(0).Cell1.ReDraw();
						completeline=m-2;	
						pageCurrent = pageCurrent+1;
						document.forms(0).Cell1.insertSheetFromFile("<%=path%>/syscollection/querystatistics/collfncomparison/report/empaccount_print.cll",0,1,pageCurrent);
						document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页");
						document.forms(0).Cell1.S(1,2,pageCurrent,head[l]);
						document.forms(0).Cell1.S(1,44,pageCurrent,"第"+(pageCurrent+1)+"页");
					}
				if(m<=totalPageLine-1){//插入数据的方法
					document.forms(0).Cell1.S(1,m+4,pageCurrent,collDateArray[l][m]);
					document.forms(0).Cell1.S(2,m+4,pageCurrent,collDocNumArray[l][m]);
					document.forms(0).Cell1.S(3,m+4,pageCurrent,bizTypeArray[l][m]);
					//document.forms(0).Cell1.S(4,m+4,pageCurrent,bizStatusArray[l][m]);
					
					document.forms(0).Cell1.d(4,m+4,pageCurrent,debitArray[l][m]);
					document.forms(0).Cell1.d(5,m+4,pageCurrent,creditArray[l][m]);
					document.forms(0).Cell1.d(6,m+4,pageCurrent,interestArray[l][m]);
					document.forms(0).Cell1.S(7,m+4,pageCurrent,directionArray[l][m]);
					document.forms(0).Cell1.d(8,m+4,pageCurrent,moneySumArray[l][m]);
				}else{//向第一页后的所有页插数据
				
					document.forms(0).Cell1.S(1,m-completeline+2,pageCurrent,collDateArray[l][m]);
					document.forms(0).Cell1.S(2,m-completeline+2,pageCurrent,collDocNumArray[l][m]);
					document.forms(0).Cell1.S(3,m-completeline+2,pageCurrent,bizTypeArray[l][m]);
					//document.forms(0).Cell1.S(4,m-completeline+2,pageCurrent,bizStatusArray[l][m]);
					
					document.forms(0).Cell1.d(4,m-completeline+2,pageCurrent,debitArray[l][m]);
					document.forms(0).Cell1.d(5,m-completeline+2,pageCurrent,creditArray[l][m]);
					document.forms(0).Cell1.d(6,m-completeline+2,pageCurrent,interestArray[l][m]);
					document.forms(0).Cell1.S(7,m-completeline+2,pageCurrent,directionArray[l][m]);
					document.forms(0).Cell1.d(8,m-completeline+2,pageCurrent,moneySumArray[l][m]);
				}	
				}
			}
			
	}
	// 取总页数
	function getInt(i,k) { 
		var page=0; 
		var j; 
		j=Math.round(i/k)-i/k; 
		if (j>=0) 
		page=Math.round(i/k)-1; 
		if (j<=0) 
		page=Math.round(i/k); 
		if(j!=0)
		page=page+1;
		if(j==0)
		page=i/k;
		return page; 
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
<body  onload = "load();"> 
<form action="">
<table align="center">
	<tr><OBJECT id=Cell1 style= "LEFT:0px;WIDTH:700px;  TOP:0px;HEIGHT:400px" codeBase="http://192.168.1.44:8088/hafmis/CellWeb5.CAB#version=5,3,7,321" classid=clsid:3F166327-8030-4881-8BD2-EA25350E574A VIEWASTEXT><PARAM NAME="_Version" VALUE="65536"><PARAM NAME="_ExtentX" VALUE="10266"><PARAM NAME="_ExtentY" VALUE="7011"><PARAM NAME="_StockProps" VALUE= "0"></OBJECT></tr> 
	<tr><input type="button" name="print" value = "打印预览" onclick = "printPreview();"/>
		<INPUT id="Button1" onclick="Button1_onclick()" type="button" value="另存为Excel" name="Button1">
		<INPUT id="Button1" onclick="Button2_onclick()" type="button" value="另存为pdf" name="Button1">
		<INPUT id="Button3" style="WIDTH: 90px" onclick="Button3_onclick()" type="button" value="页面设置">
		<INPUT id="Button3" style="WIDTH: 90px" onclick="Button4_onclick()" type="button" value="查找对话框">
		<INPUT id="Button3" style="WIDTH: 90px" onclick="Button5_onclick()" type="button" value="excel导入">
		<INPUT id="Button1" onclick="printsheet()" type="button" value=" 打印 " name="Button1">
		<INPUT id="Button3" onclick="javascript:history.back()" type="button" value=" 返回 ">		
	</tr>
</table>
</form>
</body>
</html>
