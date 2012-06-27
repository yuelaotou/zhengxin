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
   	    		String bizDate=(String) request.getAttribute("bizDate");
    		String userName=(String) request.getAttribute("userName");
    		String collectionBankName=(String) request.getAttribute("collectionBankName");
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
		document.forms(0).Cell1.openfile("<%=path%>/syscollection/pickupmng/print/pickupcell_yk.cll","");
		document.forms(0).Cell1.insertSheetFromFile("<%=path%>/syscollection/pickupmng/print/employee.cll",0,1,1);
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
		var sBankid="<%=book.getSOrgBank()%>";
		if(sBankid=="null"){
			sBankid="";
		}
		var sBank="<%=book.getSOrgNumber()%>";
		if(sBank=="null"){
			sBank="";
		}
		document.forms(0).Cell1.S(3,5,0,"<%=book.getSOrgName()%>");//这个单元格式虽然是5行的3列或4列..但是你必须指定的是第3列
		document.forms(0).Cell1.S(3,7,0,sBank);
		document.forms(0).Cell1.S(3,6,0,sBankid);
		document.forms(0).Cell1.S(8,5,0,"<%=book.getFOrgName()%>");
		document.forms(0).Cell1.S(8,7,0,fBankid);
		document.forms(0).Cell1.S(8,6,0,"<%=book.getFOrgBank()%>");
		document.forms(0).Cell1.S(3,8,0,balance);
		
		
		////////////////打印尾表////////////
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
			i++;//这个地方的i是脚本的i  而不是循环中java的i;
		<%}%>
		var totalLine=id.length;			//总的行数 数组的长度
		var totalPageLine=40;					//每页显示多少行--除了第一行
		var pageTotal=totalLine/totalPageLine;	//总共分几页 总行数/每页的行数
		var pageCurrent=1;						//当前页
		var completeline=0;						//记录行的
		var moneytotal=0;						//总的合计
		var moneyoutsum=0;						//总的合计－提取金额
		var moneyinterest=0;					//总的合计-利息
		
		for(k = 0 ; k < totalLine; k++){
			if(k%totalPageLine==0&&k>0)
			{
				document.forms(0).Cell1.S(1,totalPageLine+4,pageCurrent,"本页小计");
				document.forms(0).Cell1.SetFormula (4, totalPageLine+4, pageCurrent, "Sum(D4:D"+(totalPageLine+3)+")" );
				document.forms(0).Cell1.SetFormula (5, totalPageLine+4, pageCurrent, "Sum(E4:E"+(totalPageLine+3)+")" );
				document.forms(0).Cell1.SetFormula (6, totalPageLine+4, pageCurrent, "Sum(F4:F"+(totalPageLine+3)+")" );
				
								document.forms(0).Cell1.S(1,45,pageCurrent,"归集银行:");
					document.forms(0).Cell1.S(2,totalPageLine+5,pageCurrent,"<%=collectionBankName%>");
					document.forms(0).Cell1.S(3,totalPageLine+5,pageCurrent,"制表人:");				
				    document.forms(0).Cell1.S(4,totalPageLine+5,pageCurrent,"<%=userName%>");
					document.forms(0).Cell1.S(5,totalPageLine+5,pageCurrent,"操作日期:");
					document.forms(0).Cell1.S(6,totalPageLine+5,pageCurrent,"<%=bizDate%>");
				
				document.forms(0).Cell1.ReDraw();//重画一个表格
				pageCurrent++;	//当前页++	
				completeline=k-2;		
				//绘制标签 param 	表页索引号。param 要设置的表页页签名称			
				document.forms(0).Cell1.insertSheetFromFile("<%=path%>/syscollection/pickupmng/print/employee.cll",0,1,pageCurrent);
				document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页")	
			
			}
			if(pageCurrent==1)//如果当前页为第一页的时候 插入数据-->这个地方应该写当前页是第2页了..因为第一页是凭证
			{
				//document.forms(0).Cell1.S(列,行,页数,"本页小计");
				document.forms(0).Cell1.S(1,k+4,1,id[k]);//这个地方也要写第二页
				document.forms(0).Cell1.S(2,k+4,1,name[k]);
				document.forms(0).Cell1.S(3,k+4,1,card[k]);
				document.forms(0).Cell1.d(4,k+4,1,pickBalance[k]);
				document.forms(0).Cell1.d(5,k+4,1,interest[k]);
				document.forms(0).Cell1.d(6,k+4,1,sum[k]);
				document.forms(0).Cell1.S(7,k+4,1,type[k]);
				document.forms(0).Cell1.S(8,k+4,1,reason[k]);
				
				moneyoutsum=moneyoutsum+parseFloat(pickBalance[k]);
				moneyinterest=moneyinterest+parseFloat(interest[k]);
				moneytotal=moneytotal+parseFloat(sum[k]);
				
			}else{//向第一页后的所有页插数据
				document.forms(0).Cell1.S(1,k-completeline+2,pageCurrent,id[k]);
				document.forms(0).Cell1.S(2,k-completeline+2,pageCurrent,name[k]);
				document.forms(0).Cell1.S(3,k-completeline+2,pageCurrent,card[k]);
				document.forms(0).Cell1.d(4,k-completeline+2,pageCurrent,pickBalance[k]);
				document.forms(0).Cell1.d(5,k-completeline+2,pageCurrent,interest[k]);
				document.forms(0).Cell1.d(6,k-completeline+2,pageCurrent,sum[k]);
				//d方法是插入蓝色的字,但是此方法必须插入的是数字
				//document.forms(0).Cell1.d(6,k-completeline,pageCurrent,sum[k]);
				document.forms(0).Cell1.S(7,k-completeline,pageCurrent,type[k]);
				document.forms(0).Cell1.S(8,k-completeline,pageCurrent,reason[k]);
				
				moneyoutsum=moneyoutsum+parseFloat(pickBalance[k]);
				moneyinterest=moneyinterest+parseFloat(interest[k]);
				moneytotal=moneytotal+parseFloat(sum[k]);
			}	
			
		}		
				if(completeline==0)//查询出来的记录可以在一页显示的时候会进入
				{
					document.forms(0).Cell1.S(1,totalLine+4,pageCurrent,"本页小计");
					document.forms(0).Cell1.SetFormula (4, totalLine+4, pageCurrent, "Sum(D4:D"+(totalLine+3)+")" );
					document.forms(0).Cell1.SetFormula (5, totalLine+4, pageCurrent, "Sum(E4:E"+(totalLine+3)+")" );
					document.forms(0).Cell1.SetFormula (6, totalLine+4, pageCurrent, "Sum(F4:F"+(totalLine+3)+")" );
					document.forms(0).Cell1.S(1,totalLine+5,pageCurrent,"总计");
					document.forms(0).Cell1.d(4,totalLine+5,pageCurrent,moneyoutsum);
					document.forms(0).Cell1.d(5,totalLine+5,pageCurrent,moneyinterest);
					document.forms(0).Cell1.d(6,totalLine+5,pageCurrent,moneytotal);
					
					document.forms(0).Cell1.S(1,totalLine+6,pageCurrent,"归集银行:");
					document.forms(0).Cell1.S(2,totalLine+6,pageCurrent,"<%=collectionBankName%>");
					document.forms(0).Cell1.S(3,totalLine+6,pageCurrent,"制表人:");				
				    document.forms(0).Cell1.S(4,totalLine+6,pageCurrent,"<%=userName%>");
					document.forms(0).Cell1.S(5,totalLine+6,pageCurrent,"操作日期:");
					document.forms(0).Cell1.S(6,totalLine+6,pageCurrent,"<%=bizDate%>");				
					
					
					document.forms(0).Cell1.DeleteRow(totalLine+7,totalPageLine-(totalLine+6),pageCurrent);
					document.forms(0).Cell1.ReDraw();
				}
				else
				{
					document.forms(0).Cell1.S(1,totalLine-completeline+2,pageCurrent,"本页小计");
					document.forms(0).Cell1.SetFormula (4, totalLine-completeline+2, pageCurrent, "Sum(D4:D"+(totalLine-(completeline+3))+")");
					document.forms(0).Cell1.SetFormula (5, totalLine-completeline+2, pageCurrent, "Sum(E4:E"+(totalLine-(completeline+3))+")");
					document.forms(0).Cell1.SetFormula (6, totalLine-completeline+2, pageCurrent, "Sum(F4:F"+(totalLine-(completeline+3))+")");
					document.forms(0).Cell1.S(1,totalLine-completeline+3,pageCurrent,"总计");
					document.forms(0).Cell1.d(4,totalLine-completeline+3,pageCurrent,moneyoutsum);
					document.forms(0).Cell1.d(5,totalLine-completeline+3,pageCurrent,moneyinterest);
					document.forms(0).Cell1.d(6,totalLine-completeline+3,pageCurrent,moneytotal);
					
					document.forms(0).Cell1.S(1,totalLine-completeline+4,pageCurrent,"归集银行:");
					document.forms(0).Cell1.S(2,totalLine-completeline+4,pageCurrent,"<%=collectionBankName%>");
					document.forms(0).Cell1.S(3,totalLine-completeline+4,pageCurrent,"制表人:");				
				    document.forms(0).Cell1.S(4,totalLine-completeline+4,pageCurrent,"<%=userName%>");
					document.forms(0).Cell1.S(5,totalLine-completeline+4,pageCurrent,"操作日期:");
					document.forms(0).Cell1.S(6,totalLine-completeline+4,pageCurrent,"<%=bizDate%>");
					
					document.forms(0).Cell1.DeleteRow(totalLine-completeline+5,totalPageLine-(totalLine-completeline+4),pageCurrent);
					document.forms(0).Cell1.ReDraw();
				}		
		/////////不让改文本格的东西
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
