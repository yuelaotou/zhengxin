<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.List" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic" %>
<%@ page import="org.xpup.hafmis.syscollection.common.domain.entity.TranOutTail" %>
<%@ page import="org.xpup.hafmis.syscollection.tranmng.tranout.form.TranTbPrintAF" %>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	String url = (String)request.getAttribute("url");
	if(url==null){
		url = "to_tran_showTbAC";
	}
	String bizDate = (String)request.getAttribute("bizDate");
	List totalList = (List)request.getAttribute("printList");
	List list = null;
 %>
<html>
<head>
<script src="<%=path%>/js/common.js">
</script>
</head>
  <script type="text/javascript">
	function load(){	
	loginReg();
	document.forms(0).Cell1.openfile("<%=path%>/syscollection/tranmng/report/tranout_all_cell.cll","");
	var inOrgId=[];
	var inOrgName=[];
	var inOrgOpenBank=[];
	var inOrgCollBank=[];
	var inAccountNum=[];
	var outOrgId=[];
	var outOrgName=[];
	var outOrgOpenBank=[];
	var outOrgCollBank=[];
	var outAccountNum=[];
	var docNum=[];
    var tranoutempidArray = new Array();
    var empNameArray = new Array();
	var cardNumArray = new Array();
	var tranoutsumArray = new Array();
	var bigtranoutsumArray = new Array();
	var traninempidArray = new Array();
	var tranreasonArray = new Array();
	var sumMoneyArray = new Array();
	var salaryBaseArray = new Array();
	var curIntegralArray = new Array();
	//定义二维数组需要两个变量
	var a=0;
	var date="<%=bizDate %>";
	var count = 0;
	<%
		for(int k=0;k<totalList.size();k++){
   	    	TranTbPrintAF tranTbPrintAF = (TranTbPrintAF)totalList.get(k);
 	%>	
	 		inOrgId[a] = "<%=tranTbPrintAF.getInOrgId() %>";    
			inOrgName[a] = "<%=tranTbPrintAF.getInOrgName() %>";
			inOrgOpenBank[a] = "<%=tranTbPrintAF.getInOrgOpenBank() %>";
			inOrgCollBank[a] = "<%=tranTbPrintAF.getInOrgCollBank() %>";
			inAccountNum[a] = "<%=tranTbPrintAF.getInPayBankAccNum() %>";
			outOrgId[a] = "<%=tranTbPrintAF.getOutOrgId() %>";    
			outOrgName[a] = "<%=tranTbPrintAF.getOutOrgname() %>";
			outOrgOpenBank[a] = "<%=tranTbPrintAF.getOutOrgOpenBank() %>";
			outOrgCollBank[a] = "<%=tranTbPrintAF.getOutOrgCollBank() %>";
			outAccountNum[a] = "<%=tranTbPrintAF.getOutPayBankAccNum() %>";
			docNum[a] = "<%=tranTbPrintAF.getDoc_num() %>";
			var tranoutempid=[];
		    var empName=[];
			var cardNum=[];
			var tranoutsum=[];
			var bigtranoutsum=[];
			var traninempid=[];
			var tranreason=[];
			var salaryBase=[];
			var curIntegral=[];
			var sumMoney=[];
			var b = 0;
		<% 
			list = tranTbPrintAF.getList();
			for(int j=0;j<list.size();j++){
 				
				TranOutTail tail = (TranOutTail)list.get(j);
		%>
				traninempid[b]="<%=tail.getTranin_empid() %>";
				tranoutempid[b]="<%=tail.getEmp().getId() %>";
				empName[b]="<%=tail.getEmp().getEmpInfo().getName() %> ";
				cardNum[b]="<%=tail.getEmp().getEmpInfo().getCardNum() %>";
				tranreason[b]="<%=tail.getTranReason() %>";
				tranoutsum[b]="<%=tail.getSumBalance() %>";
				sumMoney[b]="<%=tail.getSumMoney() %>";
				curIntegral[b]="<%=tail.getCurIntegral() %>";
				salaryBase[b]="<%=tail.getEmp().getSalaryBase() %>";
				bigtranoutsum[b]=ChangeToBig(tranoutsum[b]);
				b++;
	 	<%	
	 		}
	 	
	 	%>
	 		traninempidArray[a] = traninempid;
	 		empNameArray[a] = empName;
		 	cardNumArray[a] = cardNum;
		 	tranoutsumArray[a] = tranoutsum;
			bigtranoutsumArray[a] = bigtranoutsum;
			tranoutempidArray[a] = tranoutempid;
		 	tranreasonArray[a] = tranreason;
		 	curIntegralArray[a] = curIntegral;
		 	salaryBaseArray[a] = salaryBase;
		 	sumMoneyArray[a] = sumMoney;
	 		a++;
	 <%
		}
 	%>   
 		var totalLine=inOrgId.length;		//总的页数
		var pageCurrent=0;						//当前页
		for(l = 0 ; l < totalLine; l++){
			var totalLine1=cardNumArray[l].length;		//总的行数
			for(m=0;m<totalLine1;m++){
				if(l==0&&m==0){
					document.forms(0).Cell1.openfile("<%=path%>/syscollection/tranmng/report/tranout_all_cell.cll","");
				}else{
					document.forms(0).Cell1.ReDraw();
		 			pageCurrent++;
		 			document.forms(0).Cell1.insertSheetFromFile("<%=path%>/syscollection/tranmng/report/tranout_all_cell.cll",0,1,pageCurrent);
					document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页");	
				}
				document.forms(0).Cell1.S(5,4,pageCurrent,date.substring(0,4)+"年"+date.substring(4,6)+"月"+date.substring(6,8)+"日");
				document.forms(0).Cell1.S(9,4,pageCurrent,docNum[l]);
				document.forms(0).Cell1.S(6,7,pageCurrent,inOrgId[l]);
			    document.forms(0).Cell1.S(6,8,pageCurrent,inOrgName[l]);
				document.forms(0).Cell1.S(6,9,pageCurrent ,inOrgOpenBank[l]);
				document.forms(0).Cell1.S(6,10,pageCurrent ,inAccountNum[l]);
				document.forms(0).Cell1.S(6,11,pageCurrent ,inOrgCollBank[l]);
				
				document.forms(0).Cell1.S(2,7,pageCurrent,outOrgId[l]);
			    document.forms(0).Cell1.S(2,8,pageCurrent,outOrgName[l]);
				document.forms(0).Cell1.S(2,9,pageCurrent ,outOrgOpenBank[l]);
				document.forms(0).Cell1.S(2,10,pageCurrent ,outAccountNum[l]);
				document.forms(0).Cell1.S(2,11,pageCurrent ,outOrgCollBank[l]);
				
				document.forms(0).Cell1.S(1,14,pageCurrent,tranoutempidArray[l][m]);
				document.forms(0).Cell1.S(2,14,pageCurrent,empNameArray[l][m]);
				document.forms(0).Cell1.S(3,14,pageCurrent,cardNumArray[l][m]);
				document.forms(0).Cell1.S(5,14,pageCurrent,tranoutsumArray[l][m]);
				document.forms(0).Cell1.S(6,14,pageCurrent,curIntegralArray[l][m]);
				document.forms(0).Cell1.S(7,14,pageCurrent,sumMoneyArray[l][m]);
				document.forms(0).Cell1.S(8,14,pageCurrent,salaryBaseArray[l][m]);
				document.forms(0).Cell1.S(9,14,pageCurrent,traninempidArray[l][m]);
			}
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
  <body onload = "load();" onContextmenu="return false"> 
    <form action="">
    <table align="center">
<tr><OBJECT id=Cell1 style= "LEFT:0px;WIDTH:700px;  TOP:0px;HEIGHT:400px" codeBase="http://192.168.1.44:8088/hafmis/CellWeb5.CAB#version=5,3,7,321" classid=clsid:3F166327-8030-4881-8BD2-EA25350E574A VIEWASTEXT><PARAM NAME="_Version" VALUE="65536"><PARAM NAME="_ExtentX" VALUE="10266"><PARAM NAME="_ExtentY" VALUE="7011"><PARAM NAME="_StockProps" VALUE= "0"></OBJECT></tr> 
<tr>
<td><input type="button" name="print" value = "打印预览" onclick = "printPreview();"/><td>
<td><INPUT id="Button1" onclick="printsheet()" type="button" value=" 打印 " name="Button1"></td>
<td><INPUT id="Button1" onclick="Button1_onclick()" type="button" value="另存为Excel" name="Button1"><td>
<td><INPUT id="Button1" onclick="Button2_onclick()" type="button" value="另存为pdf" name="Button1"><td>
<td><INPUT id="Button3" style="WIDTH: 100px" onclick="Button3_onclick()" type="button" value="页面设置"><td>
<td><INPUT id="Button3" style="WIDTH: 100px" onclick="Button4_onclick()" type="button" value="查找对话框"><td>
<td><INPUT id="Button3" style="WIDTH: 100px" onclick="Button5_onclick()" type="button" value="excel导入"><td>
<td><INPUT id="Button3" onclick="location.href='<%=url%>'" type="button" value=" 返回 "><td>	
</table>
</form>
  </body>
</html>
