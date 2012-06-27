<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic" %>
<%@ page import="org.xpup.hafmis.syscollection.paymng.monthpay.form.*" %>
<%@ page import="org.xpup.hafmis.syscollection.common.domain.entity.*"%>
<%@ page import="org.xpup.hafmis.syscollection.pickupmng.pickup.action.*" %>
<%@ include file="/checkUrl.jsp"%>
<%@ page import="org.xpup.hafmis.syscollection.pickupmng.pickup.dto.ApplyBookDTO"%>
<%
   String path=request.getContextPath();
   List list = (List) request.getAttribute("employee");
   ApplyBookDTO book = (ApplyBookDTO)request.getAttribute("apply");
   	String url=(String)request.getAttribute("PRINT");	
%>
<html:html lang="true">
<head>
<script src="<%=path%>/js/common.js">
</script>
<title>提取打印</title>
<script type="text/javascript">
    function load(){//打开文件...
    loginReg();
    	//前边3句话那是固定的....不能改的...如果改了就显示不正确了
		document.forms(0).Cell1.openfile("<%=path%>/syscollection/pickupmng/print/pickup_yk.cll","");
		//document.forms(0).Cell1.insertSheetFromFile("<%=path%>/syscollection/pickupmng/print/employee.cll",0,1,1);
		//document.forms(0).Cell1.setSheetLabel(1,"第2页");//这句话必须放在加载文件的下边
		
		var balance = "<%=book.getPickBalance()%>";
		balance = ""+balance;//转换成字符串

		var fBankid="<%=book.getFOrgNumber()%>";
		if(fBankid=="null"){
			fBankid="";
		}
		var sBankid="<%=book.getSOrgNumber()%>";
		if(sBankid=="null"){
			sBankid="";
		}
		var sBank="<%=book.getSOrgBank()%>";
		if(sBank=="null"){
			sBank="";
		}
		var docnum="<%=book.getDocnum()%>";
		if(docnum=="null"){
		    docnum="";
		}
		var notnum="<%=book.getNote_num()%>";
		if(notnum=="null"){
		    notnum="";
		}
		var checkperson="<%=book.getCheckperson()%>";
		if(checkperson=="null"){
		    checkperson="";
		}
		var operater="<%=book.getOperater()%>";
		if(operater=="null"){
		    operater="";
		}
		var id=[];
		var name=[];
		var card=[];
		var pickBalance=[];
		var interest=[];
		var interest_yg=0;
		var sum=[];
		var type=[];
		var reason = [];
	
		var i = 0;
		<%
			PickTail tail = null; 
			String pickperson="";
			for(int j=0;j<list.size();j++){
			tail = (PickTail)list.get(j);
			if(list.size()==1){
			pickperson=tail.getEmp().getEmpInfo().getName();
			}
			else{
			pickperson=list.size()+"";
			}
		%>
			/*以上是打印全部凭证的功能*/
			//第一页是openFile ...第二页以后必须全用inertSheetFromFile
			id[i] = format("<%=tail.getEmpId()%>")+<%=tail.getEmpId()%>;
			name[i] = "<%=tail.getEmp().getEmpInfo().getName()%>";
			card[i] = "<%=tail.getEmp().getEmpInfo().getCardNum()%>";
			pickBalance[i] = "<%=tail.getPickSalary()%>";
			interest[i] ="<%=tail.getPickInterest()%>";
			interest_yg=eval(interest_yg)+eval(interest[i]);
			sum[i] ="<%=tail.getTotal()%>";
			type[i] = "<%=tail.getReason()%>";
			reason[i] = "<%=tail.getPickDisplayType()%>";
			i++;//这个地方的i是脚本的i  而不是循环中java的i;
		<%}%>
		
		document.forms(0).Cell1.S(12,3,0,docnum);
		document.forms(0).Cell1.S(2,3,0,notnum);
		document.forms(0).Cell1.S(5,3,0,"<%=book.getBizdate()%>");
		document.forms(0).Cell1.S(7,4,0,"<%=book.getSOrgName()%>");//这个单元格式虽然是5行的3列或4列..但是你必须指定的是第3列
		document.forms(0).Cell1.S(7,5,0,"<%=pickperson%>");
		document.forms(0).Cell1.S(2,6,0,sBank);
		document.forms(0).Cell1.S(2,10,0,type[0]);
		document.forms(0).Cell1.S(2,4,0,"营口市住房公积金管理中心");//<%=book.getFOrgName()%>
		document.forms(0).Cell1.S(2,5,0,fBankid);
		document.forms(0).Cell1.S(7,6,0,"<%=book.getFOrgBank()%>");
		document.forms(0).Cell1.D(7,8,0,balance);
		document.forms(0).Cell1.S(2,7,0,eval(balance)-eval(interest_yg));
		document.forms(0).Cell1.S(7,7,0,interest_yg);
		balance=ChangeToBig(balance);
		document.forms(0).Cell1.S(3,8,0,balance);
		document.forms(0).Cell1.S(2,16,0,checkperson);
		document.forms(0).Cell1.S(6,16,0,operater);
		//document.forms(0).Cell1.S(3,6,0,sBankid);
		
		
		
		
		
		
		
		/////////不让改文本格的东西
		document.forms(0).Cell1.AllowExtend=false;
		document.forms(0).Cell1.AllowDragdrop=false;
	//	document.forms(0).Cell1.WorkbookReadonly=true;	
	}
	
	function printsheet(){//真正的打印
		var k=document.forms(0).Cell1.GetCurSheet();//显示打印预览那个页面--固定
		document.forms(0).Cell1.PrintSheet(1,k);//固定...
	}
	function printPreview(){
		var k=document.forms(0).Cell1.GetCurSheet();//显示打印预览那个页面
		document.forms(0).Cell1.printPreviewEx(1,k,false);//key代表当前页
	}
	function Button1_onclick()
	{
		document.forms(0).Cell1.SaveFile();
	}
	function Button2_onclick()
	{
		document.forms(0).Cell1.ExportPdfFile("d:\\aa",-1,1,1);//没有用
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
</head>
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
<td><INPUT id="Button3" onclick="location.href='<%=url%>'" type="button" value=" 返回 "></td>
</table>
</form>
</body>
</html:html>
