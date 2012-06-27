<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.List" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic" %>
<%@ page import="org.xpup.hafmis.syscollection.collloanbackcheck.dto.CollLoanbackcheckDTO" %>
<%@ include file="/checkUrl.jsp"%>

<%
   String path=request.getContextPath();
   List list=(List) request.getSession().getAttribute("checkList");

 %>
<html>
<head>
<script src="<%=path%>/js/common.js"></script>
</head>
<script type="text/javascript">
	function load(){
	loginReg();
	document.forms(0).Cell1.openfile("<%=path%>/syscollection/collloanbackcheck/report/fund_pl.cll","");
  	var batchNum=[];
  	var docNum=[];
  	var contractId=[];
  	var borrowerName=[];
    var orgId=[];
    var orgName=[];
    var empId=[];
    var empName=[];
    var money=[];
    var settDate=[];
    var pickSatatus=[];
	var i=0;
	
	<%
		CollLoanbackcheckDTO collLoanbackcheckDTO = new CollLoanbackcheckDTO();
		for(int j=0;j<list.size();j++){
		collLoanbackcheckDTO = (CollLoanbackcheckDTO)list.get(j);
		
	%>
	    //把数据传到JS的数组里面..
		batchNum[i] = "<%=collLoanbackcheckDTO.getBatch_num()%>"; 
		docNum[i] = "<%=collLoanbackcheckDTO.getDoc_num()%>";
		contractId[i] = "<%=collLoanbackcheckDTO.getContractId()%>";
		borrowerName[i] = "<%=collLoanbackcheckDTO.getBorrowerName()%>";
		orgId[i] = "<%=collLoanbackcheckDTO.getOrg_id()%>"; 
		orgName[i] = "<%=collLoanbackcheckDTO.getOrg_name()%>";
		empId[i] = "<%=collLoanbackcheckDTO.getEmp_id()%>";
		empName[i] = "<%=collLoanbackcheckDTO.getEmp_name()%>";
		money[i] = "<%=collLoanbackcheckDTO.getMoney()%>";
		settDate[i] = "<%=collLoanbackcheckDTO.getSett_date()%>";
		pickSatatus[i] = "<%=collLoanbackcheckDTO.getPick_satatus()%>";
		i++;
	<%}%>
	var totalLine=batchNum.length;		//总的行数 数组的长度
	var totalPageLine=14;					//每页显示多少行--除了第一行
	var pageTotal=totalLine/totalPageLine;	//总共分几页 总行数/每页的行数
	var pageCurrent=0;						//当前页
	var completeline=0;						//记录行的
	var iPage = getInt(totalLine,totalPageLine);
	var icount = 1;
		
	var moneySum = "<%=request.getSession().getAttribute("totalDcitsum")%>";
	var totalSum = "<%=list.size()%>";
	var total_people_count="<%=request.getSession().getAttribute("people_count")%>";
	
	var userName = "<%=(String)request.getAttribute("userName")%>";
		var bizDate = "<%=(String)request.getAttribute("bizDate")%>";
     
	var moneytotal=0;
	
	for(k = 0 ; k < totalLine; k++){
		if(k%totalPageLine==0&&k>0)
		{
			//document.forms(0).Cell1.S(1,totalPageLine+4,pageCurrent,"本页小计");
			//document.forms(0).Cell1.SetFormula (6, totalPageLine+4, pageCurrent, "Sum(F4:F"+(totalPageLine+3)+")" );
			//document.forms(0).Cell1.SetFormula (7, totalPageLine+4, pageCurrent, "Sum(G4:G"+(totalPageLine+3)+")" );
			document.forms(0).Cell1.ReDraw();//重画一个表格
			pageCurrent++;//当前页++	
			completeline=k-2;
			icount++;
			
			//绘制标签 param 	表页索引号。param 要设置的表页页签名称
			document.forms(0).Cell1.insertSheetFromFile("<%=path%>/syscollection/collloanbackcheck/report/fund_pl.cll",0,1,pageCurrent);  				
			document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页")	
		}
		if(pageCurrent==0)
		{				
			//document.forms(0).Cell1.S(1,k+5,0,batchNum[k]);
			//document.forms(0).Cell1.S(2,k+5,0,docNum[k]);
			document.forms(0).Cell1.S(1,k+5,0,orgName[k]);
			document.forms(0).Cell1.S(2,k+5,0,"0"+orgId[k]);
			document.forms(0).Cell1.S(3,k+5,0,empName[k]);
			document.forms(0).Cell1.S(4,k+5,0,empId[k]);
			document.forms(0).Cell1.d(5,k+5,0,money[k]);
			document.forms(0).Cell1.S(6,k+5,0,contractId[k]);
			document.forms(0).Cell1.S(7,k+5,0,borrowerName[k]);
			document.forms(0).Cell1.S(8,k+5,0,settDate[k]);
			
			
			
			
			
			
			//document.forms(0).Cell1.S(11,k+5,0,pickSatatus[k]);
			
             document.forms(0).Cell1.S(1,3,0,"批次号:"+batchNum[k]); 
            document.forms(0).Cell1.S(6,20,0,userName);
            document.forms(0).Cell1.S(3,3,0,bizDate);
            document.forms(0).Cell1.S(8,3,0,icount+"/"+iPage);
            
               document.forms(0).Cell1.S(2,19,0,"笔数:"+totalSum); 
              document.forms(0).Cell1.S(3,19,0,"人数:"+total_people_count); 
               document.forms(0).Cell1.S(5,19,0,moneySum); 
               
			moneytotal=moneytotal+parseFloat(money[k]);
			}
		else{//向第一页后的所有页插数据
			
			//document.forms(0).Cell1.S(1,k-completeline+3,pageCurrent,batchNum[k]);
			//document.forms(0).Cell1.S(2,k-completeline+3,pageCurrent,docNum[k]);
			document.forms(0).Cell1.S(1,k-completeline+3,pageCurrent,orgName[k]);
            document.forms(0).Cell1.S(2,k-completeline+3,pageCurrent,"0"+orgId[k]);
            document.forms(0).Cell1.S(3,k-completeline+3,pageCurrent,empName[k]);
			document.forms(0).Cell1.S(4,k-completeline+3,pageCurrent,empId[k]);
			document.forms(0).Cell1.d(5,k-completeline+3,pageCurrent,money[k]);
		    document.forms(0).Cell1.S(6,k-completeline+3,pageCurrent,contractId[k]);
			document.forms(0).Cell1.S(7,k-completeline+3,pageCurrent,borrowerName[k]);
			
			document.forms(0).Cell1.S(8,k-completeline+3,pageCurrent,settDate[k]);
			
			//document.forms(0).Cell1.S(11,k-completeline+3,pageCurrent,pickSatatus[k]);
			
			 document.forms(0).Cell1.S(1,3,pageCurrent,"批次号:"+batchNum[k]); 
			document.forms(0).Cell1.S(6,20,pageCurrent,userName);
            document.forms(0).Cell1.S(3,3,pageCurrent,bizDate);
            document.forms(0).Cell1.S(8,3,pageCurrent,icount+"/"+iPage);
            
             document.forms(0).Cell1.S(2,19,pageCurrent,"笔数:"+totalSum); 
              document.forms(0).Cell1.S(3,19,pageCurrent,"人数:"+total_people_count); 
               document.forms(0).Cell1.S(5,19,pageCurrent,moneySum); 
            
            
			moneytotal=moneytotal+parseFloat(money[k]);
		}		
	}
	<%--
	if(completeline==0)//查询出来的记录可以在一页显示的时候会进入
	{
		//document.forms(0).Cell1.S(1,totalLine+4,pageCurrent,"本页小计");
		//document.forms(0).Cell1.SetFormula (6, totalLine+4, pageCurrent, "Sum(F4:F"+(totalLine+3)+")" );
		//document.forms(0).Cell1.SetFormula (7, totalLine+4, pageCurrent, "Sum(G4:G"+(totalLine+3)+")" );
		document.forms(0).Cell1.S(1,totalLine+5,pageCurrent,"部门");
		document.forms(0).Cell1.S(2,totalLine+5,pageCurrent,"归集科");
		document.forms(0).Cell1.S(3,totalLine+5,pageCurrent,"操作员");
		document.forms(0).Cell1.S(4,totalLine+5,pageCurrent,userName);
		document.forms(0).Cell1.S(5,totalLine+5,pageCurrent,"操作日期");
		document.forms(0).Cell1.S(6,totalLine+5,pageCurrent,bizDate);
		
		//document.forms(0).Cell1.S(8,totalLine+5,pageCurrent,"总计");
		//document.forms(0).Cell1.d(9,totalLine+5,pageCurrent,moneytotal);
		document.forms(0).Cell1.S(10,totalLine+5,pageCurrent,"笔数");
		document.forms(0).Cell1.S(10,totalLine+5,pageCurrent,"人数");
		//document.forms(0).Cell1.S(11,totalLine+5,pageCurrent,totalSum);
		
		document.forms(0).Cell1.DeleteRow(totalLine+6,45-totalLine-5,pageCurrent);
		document.forms(0).Cell1.ReDraw();
	}	
	else
	{
		//document.forms(0).Cell1.S(1,totalLine-completeline+2,pageCurrent,"本页小计");   
		//F1 第F列的第1行: N9 到第N列的第9行  求和
		//document.forms(0).Cell1.SetFormula (6, totalLine-completeline+2, pageCurrent, "Sum(F4:F"+(totalLine-(completeline-1))+")" );
		//document.forms(0).Cell1.SetFormula (7, totalLine-completeline+2, pageCurrent, "Sum(G4:G"+(totalLine-(completeline-1))+")" );
		document.forms(0).Cell1.S(1,totalLine-completeline+3,pageCurrent,"部门");
		document.forms(0).Cell1.S(2,totalLine-completeline+3,pageCurrent,"归集科");
		document.forms(0).Cell1.S(3,totalLine-completeline+3,pageCurrent,"操作员");
		document.forms(0).Cell1.S(4,totalLine-completeline+3,pageCurrent,userName);
		document.forms(0).Cell1.S(5,totalLine-completeline+3,pageCurrent,"操作日期");
		document.forms(0).Cell1.S(6,totalLine-completeline+3,pageCurrent,bizDate);
		
		document.forms(0).Cell1.S(8,totalLine-completeline+3,pageCurrent,"总计");
		document.forms(0).Cell1.d(9,totalLine-completeline+3,pageCurrent,moneytotal);
		document.forms(0).Cell1.S(10,totalLine-completeline+3,pageCurrent,"比数");
		document.forms(0).Cell1.S(11,totalLine-completeline+3,pageCurrent,totalSum);
		
		document.forms(0).Cell1.DeleteRow(totalLine-completeline+4,45-(totalLine-completeline+3),pageCurrent);
		document.forms(0).Cell1.ReDraw();
	}
	--%>
	document.forms(0).Cell1.AllowExtend=false;
	document.forms(0).Cell1.AllowDragdrop=false;
	document.forms(0).Cell1.WorkbookReadonly=true;	

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

<body  onContextmenu="return false" onload = "load();"> 
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
		<INPUT id="Button3" onclick="javascript:history.back();" type="button" value=" 返回 ">		
	</tr>
</table>
</form>
</body>
</html>
