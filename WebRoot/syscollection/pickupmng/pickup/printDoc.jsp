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
   	String bizdate=(String)request.getAttribute("date"); 
   	if(url.equals("D")){
   		url=path+"/syscollection/querystatistics/operationflow/orgoperationflow/empOperationFlowTaShowAC.do";
   	}
%>
<html:html lang="true">
<head>
<script src="<%=path%>/js/common.js">
</script>
<title>提取打印</title>
<script type="text/javascript">
    function load(){//打开文件...
    loginReg();
    
    
    
    
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
		document.forms(0).Cell1.S(2,7,0,balance-interest[0]);
		document.forms(0).Cell1.S(7,7,0,interest[0]);
		document.forms(0).Cell1.S(2,10,0,type[0]);
		document.forms(0).Cell1.S(2,4,0,"营口市住房公积金管理中心");//<%=book.getFOrgName()%>
		document.forms(0).Cell1.S(2,5,0,fBankid);
		document.forms(0).Cell1.S(7,6,0,"<%=book.getFOrgBank()%>");
		document.forms(0).Cell1.S(7,8,0,"人民币(小写) "+balance);
		balance=ChangeToBig(balance);
		document.forms(0).Cell1.S(4,8,0,balance);
		document.forms(0).Cell1.S(2,16,0,operater);
		document.forms(0).Cell1.S(6,16,0,checkperson);
    	//前边3句话那是固定的....不能改的...如果改了就显示不正确了
	//	document.forms(0).Cell1.openfile("<%=path%>/syscollection/pickupmng/print/pickupcell_yk2.cll","");
		document.forms(0).Cell1.insertSheetFromFile("<%=path%>/syscollection/pickupmng/print/pickupcell_yk2.cll",0,1,1);
		document.forms(0).Cell1.setSheetLabel(1,"第2页");//这句话必须放在加载文件的下边
		
		var balance = "<%=book.getPickBalance()%>";
		balance = ""+balance;//转换成字符串
/**		var count = balance.indexOf(".",0);//查询是否有.出现在字符串里面..表示从第0个位置开始查找..
		var len = balance.length;//获取字符串的长度
		var length;
		if(count==-1){//如果没有小数点..
			length=len;
		}else{//如果小数点...把小数点在字符串出现的位置给length
			length=count;
		}
		var temp;
		for(var i=0;i<length;i++){//从后向前插入（从元起）
			temp=balance.substring((length-i-1),length-i);//获得元以上的数据
			document.forms(0).Cell1.S((15-i),9,0,temp);//15是cell->元的列数
		}
		if(count==-1){//没有小数点的时候 插入为0..
			document.forms(0).Cell1.S(16,9,0,"0");
			document.forms(0).Cell1.S(17,9,0,"0");
		}else{
			var result = balance.split(".");
			var dis = result[1];//获得小数点后边的数字
			document.forms(0).Cell1.S(16,9,0,dis.substring(0,1));
			document.forms(0).Cell1.S(17,9,0,dis.substring(1,2));
		}
		balance=ChangeToBig(balance);
*/
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
		var notenum="<%=book.getNote_num()%>";
		if(notenum=="null"){
		    notenum="";
		}
		var checkperson="<%=book.getCheckperson()%>";
		if(checkperson=="null"){
		    checkperson="";
		}
		var operater="<%=book.getOperater()%>";
		if(operater=="null"){
		    operater="";
		}
		var other_card_num="<%=book.getOther_card_num()%>";
		if(other_card_num=="null"){
		    other_card_num="";
		}
		var pzempid="<%=book.getEmpid()%>";
		var id=[];
		var name=[];
		var card=[];
		var pickBalance=[];
		var interest=[];
		var sum=[];
		var type=[];
		var reason = [];
		var empcard="";
		var empreason="";
		var empname="";
		var i = 0;
		<%
			PickTail tail_1 = null; 
			for(int j=0;j<list.size();j++){
			tail_1 = (PickTail)list.get(j);
			
			//System.out.println("tail.getPickReason()="+tail.getPickReason());
			//System.out.println("tail.getPickDisplayType()="+tail.getPickDisplayType());
			//empname=tail.getEmp().getEmpInfo().getName();
		%>
			/*以上是打印全部凭证的功能*/
			//第一页是openFile ...第二页以后必须全用inertSheetFromFile
			
			id[i] = format("<%=tail_1.getEmpId()%>")+<%=tail_1.getEmpId()%>;
			
			name[i] = "<%=tail_1.getEmp().getEmpInfo().getName()%>";
			card[i] = "<%=tail_1.getEmp().getEmpInfo().getCardNum()%>";
			pickBalance[i] = "<%=tail_1.getPickSalary()%>";
			interest[i] ="<%=tail_1.getPickInterest()%>";
			sum[i] ="<%=tail_1.getTotal()%>";
			type[i] = "<%=tail_1.getReason()%>";
			reason[i] = "<%=tail_1.getPickDisplayType()%>";
			if(pzempid=="<%=tail_1.getEmpId()%>")
			{
			  empname=name[i];
			  empcard=card[i];
			  empreason=type[i];
			}
			i++;//这个地方的i是脚本的i  而不是循环中java的i;
		<%}%>
		document.forms(0).Cell1.S(2,3,1,"<%=book.getFOrgBank()%>");
		document.forms(0).Cell1.S(4,3,1,"<%=book.getBizdate()%>");
	    document.forms(0).Cell1.S(7,3,1,notenum);
         document.forms(0).Cell1.S(17,3,1,docnum);
         
		document.forms(0).Cell1.S(2,4,1,"<%=book.getSOrgName()%>");//这个单元格式虽然是5行的3列或4列..但是你必须指定的是第3列
		document.forms(0).Cell1.S(2,5,1,format("<%=book.getOrgid()%>")+"<%=book.getOrgid()%>");	
		document.forms(0).Cell1.S(7,4,1,sBank);
		document.forms(0).Cell1.S(7,5,1,"<%=tail_1.getEmp().getEmpInfo().getCardNum()%>");
		document.forms(0).Cell1.S(2,6,1,"<%=tail_1.getEmp().getEmpInfo().getName()%>");
		document.forms(0).Cell1.S(11,6,1,other_card_num);
		document.forms(0).Cell1.S(2,7,1,format("<%=tail_1.getEmpId()%>")+<%=tail_1.getEmpId()%>);
		document.forms(0).Cell1.S(7,7,1,"<%=tail_1.getReason()%>");
		document.forms(0).Cell1.S(2,8,1,balance-interest[0]);
		document.forms(0).Cell1.S(7,8,1,interest[0]);
		document.forms(0).Cell1.S(10,9,1,"人民币(小写) "+balance);
		
		document.forms(0).Cell1.S(2,16,1,operater);
		document.forms(0).Cell1.S(5,16,1,checkperson);
		balance=ChangeToBig(balance);
		document.forms(0).Cell1.S(4,9,1,balance);
		
		
		////////////////打印尾表////////////

				//document.forms(0).Cell1.S(列,行,页数,"本页小计");
	/**			document.forms(0).Cell1.S(1,k+4,1,id[k]);//这个地方也要写第二页
				document.forms(0).Cell1.S(2,k+4,1,name[k]);
				document.forms(0).Cell1.S(3,k+4,1,card[k]);
				document.forms(0).Cell1.d(4,k+4,1,pickBalance[k]);
				document.forms(0).Cell1.d(5,k+4,1,interest[k]);
				document.forms(0).Cell1.d(6,k+4,1,sum[k]);
				document.forms(0).Cell1.S(7,k+4,1,type[k]);
				document.forms(0).Cell1.S(8,k+4,1,reason[k]);
		*/		
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
