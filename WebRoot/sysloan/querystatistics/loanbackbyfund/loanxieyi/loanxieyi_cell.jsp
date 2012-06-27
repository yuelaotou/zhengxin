<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic"%>
<%@ page import="org.xpup.hafmis.sysloan.querystatistics.loanbackbyfund.loanxieyi.dto.LoanXieYiDTO"%>
<%@ page import="java.util.List"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	String makePerson = (String)request.getAttribute("userName");
	String bizDate = (String)request.getAttribute("plbizDate");
	String endDate=(String)request.getAttribute("endDate");
   String startDate=(String)request.getAttribute("startDate");
   String loanBankName=(String)request.getAttribute("loanBankName");
   String makebillPerson=(String)request.getAttribute("makeBillPerson");  
   String checkPerso=(String)request.getAttribute("checkPerson");
   
   String clearPerson=(String)request.getAttribute("clearPerson");
   if(makebillPerson==null){
   		makebillPerson="";
   }
   if(checkPerso==null){
   		checkPerso="";
   }
   if(clearPerson==null){
   		clearPerson="";
   }
	String url=(String)request.getAttribute("URL");
	if(url==null){
		url="javascript:history.back();";
	}
%>
<html>
	<head>
		<script src="<%=path%>/js/common.js">
</script>
	</head>
	<script type="text/javascript">
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
	function load(){	
	loginReg();
	document.forms(0).Cell1.openfile("<%=path%>/sysloan/accounthandle/report/loanxieyi.cll","");
	var docNum=[]; 
    var contractId=[];
    var nameList=[];
	var bizType=[];
	var occurMoney=[];
	var sumReclaimMoney=[];
	var badDebt=[];
	var putUpMoney=[];
	var bail=[];
	var bizDate=[];
	var bizSt=[];
	var realCorpus = [];
	var realInterest = [];
	var realPunishInterest = [];
	var loanBankName = [];
	var isBack = [];
	var i=0;

	<%
    	    List list =(List)request.getAttribute("printlist");
    	    String makePerson1 = "";
    	    String checkPerson = "";
    	    String clearAccPerson = "";
    	    
	%>
			<%
  			for(int j=0;j<list.size();j++)
  			{
  				LoanXieYiDTO dto=(LoanXieYiDTO)list.get(j);
  				
 	%>
 				 docNum[i]="<%=dto.getXieYiNum() %>";
 				 if(docNum[i]=="null"){
 				 	docNum[i]="";
 				 }
			     contractId[i]="<%=dto.getContractId()%>";
			     if(contractId[i]=="null"){
			     	contractId[i]="";
			     }
			     nameList[i]="<%=dto.getBorrowerName()%>";
			     if(nameList[i]=="null"){
			     	nameList[i]="";
			     }
				 bizType[i]="<%=dto.getBorrowerCardNum()%>";
				 occurMoney[i]="<%=dto.getBorrowerCardNum()%>";
				 if(occurMoney[i]=="null"||occurMoney[i]==""){
				 	occurMoney[i]="";
				 }
				 sumReclaimMoney[i]="<%=dto.getBorrowerEmpId()%>";
				 if(sumReclaimMoney[i]=="null" || sumReclaimMoney[i]==""){
				 	sumReclaimMoney[i]="";
				 }
				 badDebt[i]="<%=dto.getFuzhuName()%>";
				 if(badDebt[i]=="null" || badDebt[i]==""){
				 	badDebt[i]="";
				 }
				 putUpMoney[i]="<%=dto.getFuzhuCardNum()%>";
				 if(putUpMoney[i]=="null" || putUpMoney[i]==""){
				 	putUpMoney[i]="";
				 }
				 bail[i]="<%=dto.getFuzhuEmpId()%>";
				 if(bail[i]=="null" || bail[i]==""||bail[i]=="00null"){
				 	bail[i]="";
				 }
				 bizDate[i]="<%=dto.getBizDate() %>";
				 if(bizDate[i]=="null" || bizDate[i]==""){
				 	bizDate[i]="";
				 }
				 bizSt[i]="<%=dto.getStopDate() %>"; 
				 if(bizSt[i]=="null" || bizSt[i]==""){
				 	bizSt[i]="";
				 }
 	          	i++; 
 	<%
 			}
 	%>		     
	    var totalLine=nameList.length;			//总的行数 数组的长度
		var totalPageLine=26;					//每页显示多少行
		var pageTotal=totalLine/totalPageLine;	//总共分几页
		var pageCurrent=0;						//当前页
		var completeline=0;						//记录行的
		
		var iPage = getInt(totalLine,totalPageLine);
			var temp_page = 1;
		document.forms(0).Cell1.InsertRow(4, totalLine, 0);	
		for(k = 0 ; k < totalLine; k++){
<%--			if(k%totalPageLine==0&&k>0)--%>
<%--			{      --%>
<%--				--%>
<%--				document.forms(0).Cell1.DeleteRow(30,2,pageCurrent);--%>
<%--				document.forms(0).Cell1.ReDraw();--%>
<%--				pageCurrent++;	--%>
<%--				completeline=k-2;		--%>
<%--				temp_page = temp_page +	1;				--%>
<%--				document.forms(0).Cell1.insertSheetFromFile("<%=path%>/sysloan/accounthandle/report/loanxieyi_1.cll",0,1,pageCurrent);--%>
<%--				document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页")	--%>
<%--			}--%>
			if(pageCurrent==0)
			{
				document.forms(0).Cell1.S(10,2,0,"第"+temp_page+"/"+iPage+"页");
				
				document.forms(0).Cell1.S(5,2,0,"<%=bizDate%>");
				document.forms(0).Cell1.S(9,2,0,"<%=makePerson%>");
				document.forms(0).Cell1.S(1,k+4,0,docNum[k]);
				document.forms(0).Cell1.S(2,k+4,0,contractId[k]);
				document.forms(0).Cell1.S(3,k+4,0,nameList[k]);
				document.forms(0).Cell1.S(4,k+4,0,bizType[k]);
				document.forms(0).Cell1.s(5,k+4,0,sumReclaimMoney[k]);
				document.forms(0).Cell1.s(6,k+4,0,badDebt[k]);
				document.forms(0).Cell1.s(7,k+4,0,putUpMoney[k]);
				document.forms(0).Cell1.s(9,k+4,0,bizDate[k]);;
				document.forms(0).Cell1.s(8,k+4,0,bail[k]);
				document.forms(0).Cell1.s(10,k+4,0,bizSt[k]);
			}else{//向第一页后的所有页插数据
				document.forms(0).Cell1.S(10,2,pageCurrent,"第"+temp_page+"/"+iPage+"页");
				document.forms(0).Cell1.S(5,2,pageCurrent,"<%=bizDate%>");
				document.forms(0).Cell1.S(9,2,pageCurrent,"<%=makePerson%>");
			    document.forms(0).Cell1.S(1,k-completeline+2,pageCurrent,docNum[k]);
				document.forms(0).Cell1.S(2,k-completeline+2,pageCurrent,contractId[k]);
				document.forms(0).Cell1.S(3,k-completeline+2,pageCurrent,nameList[k]);
				document.forms(0).Cell1.s(4,k-completeline+2,pageCurrent,bizType[k]);
				document.forms(0).Cell1.s(5,k-completeline+2,pageCurrent,sumReclaimMoney[k]);
				document.forms(0).Cell1.s(6,k-completeline+2,pageCurrent,badDebt[k]);
				document.forms(0).Cell1.s(7,k-completeline+2,pageCurrent,putUpMoney[k]);
				document.forms(0).Cell1.s(9,k-completeline+2,pageCurrent,bizDate[k]);
				document.forms(0).Cell1.s(8,k-completeline+2,pageCurrent,bail[k]);
				document.forms(0).Cell1.s(10,k-completeline+2,pageCurrent,bizSt[k]);
			}
		}
			
				if(completeline==0)
				{
					
					document.forms(0).Cell1.DeleteRow(totalLine+4,totalPageLine-(totalLine-2),pageCurrent);
					document.forms(0).Cell1.ReDraw();
				}
				else
				{
					document.forms(0).Cell1.DeleteRow(totalLine-completeline+2,totalPageLine-(totalLine-completeline-4),pageCurrent);
					
					document.forms(0).Cell1.ReDraw();
				}	
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
  <body onload = "load();" > 
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
