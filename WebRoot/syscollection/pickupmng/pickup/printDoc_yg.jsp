<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.util.*"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic"%>
<%@ page import="org.xpup.hafmis.syscollection.common.domain.entity.*"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page
	import="org.xpup.hafmis.syscollection.pickupmng.pickup.dto.ApplyBookDTO"%>
<%
   	String path=request.getContextPath();
   	List list = (List) request.getAttribute("employee");
   	ApplyBookDTO book = (ApplyBookDTO)request.getAttribute("apply");
   	String bizdate=(String)request.getAttribute("date"); 
   	
%>
<head>
	<script src="<%=path%>/js/common.js">
</script>
	<title>提取打印</title>
	<script type="text/javascript">
    function load(){//打开文件...
    loginReg();
		document.forms(0).Cell1.openfile("<%=path%>/syscollection/pickupmng/print/1shenpi9.cll","");
		//document.forms(0).Cell1.insertSheetFromFile("<%=path%>/syscollection/pickupmng/print/employee.cll",0,1,1);
		//document.forms(0).Cell1.setSheetLabel(1,"第2页");//这句话必须放在加载文件的下边
		
		var balance = "<%=book.getPickBalance()%>";
		/*var count = balance.indexOf(".",0);//查询是否有.出现在字符串里面..表示从第0个位置开始查找..
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
		var balance_d="";
		var number="";
		var year="<%=bizdate.substring(0,4)%>";
		var month="<%=bizdate.substring(5,7)%>";
		var day="<%=bizdate.substring(8,10)%>";
		var not_num="<%=book.getNote_num()%>";
		number="<%=list.size()%>";
		<%
			PickTail tail = null; 
			for(int j=0;j<list.size();j++){
			tail = (PickTail)list.get(j);
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
			if(pzempid=="<%=tail.getEmpId()%>")
			{
			  empname=name[i];
			  empcard=card[i];
			  empreason=type[i];
			}
			i++;//这个地方的i是脚本的i  而不是循环中java的i;
		<%}%>
	    document.forms(0).Cell1.S(2,2,0,year);//	
	    document.forms(0).Cell1.S(3,2,0,month);//
	    document.forms(0).Cell1.S(4,2,0,day);//
	    document.forms(0).Cell1.S(8,2,0,docnum);//
	    
	    
		document.forms(0).Cell1.S(2,7,0,"0"+"<%=book.getOrgid()%>");//单位账号
		if(number==1){
			document.forms(0).Cell1.S(4,7,0,format(pzempid)+pzempid);//职工账号
			document.forms(0).Cell1.S(6,7,0,empname);//职工姓名
			document.forms(0).Cell1.S(8,7,0,empcard);//职工身份证号
		}	
		balance_d=ChangeToBig(balance);
		document.forms(0).Cell1.S(2,8,0,balance_d);//大写金额
		document.forms(0).Cell1.D(6,8,0,balance);//提取金额
	  	document.forms(0).Cell1.S(7,9,0,other_card_num);
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
<body onload="load();" onContextmenu="return false">
	<form action="">
		<table align="center">
			<tr>
				<OBJECT id="Cell1" style="LEFT:0px;WIDTH:700px;  TOP:0px;HEIGHT:400px"
					codeBase="http://192.168.1.44:8088/hafmis/CellWeb5.CAB#version=5,3,7,321"
					classid="clsid:3F166327-8030-4881-8BD2-EA25350E574A" VIEWASTEXT>
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
					<INPUT id="Button1" onclick="printsheet()" type="button"
						value=" 打印 " name="Button1">
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
					<INPUT id="Button3" onclick="javascript:history.back();"
						type="button" value=" 返回 ">
				</td>
		</table>
	</form>
</body>
</html:html>
