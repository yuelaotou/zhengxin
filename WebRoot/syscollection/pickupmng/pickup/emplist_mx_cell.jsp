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
   	String date=(String)request.getAttribute("date");
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
    	//前边3句话那是固定的....不能改的...如果改了就显示不正确了
		//document.forms(0).Cell1.openfile("<%=path%>/syscollection/pickupmng/print/pickupcell_yk.cll","");
		
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
		var other_card_num="<%=book.getOther_card_num()%>";
		if(other_card_num=="null"){
		    other_card_num="";
		}
		var id=[];
		var name=[];
		var card=[];
		var pickBalance=[];
		var interest=[];
		var sum=[];
		var type=[];
		var reason = [];
		var interest_yg=0;
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
		document.forms(0).Cell1.S(2,7,0,eval(balance)-eval(interest_yg));
		document.forms(0).Cell1.S(7,7,0,interest_yg);
		document.forms(0).Cell1.S(2,10,0,type[0]);
		document.forms(0).Cell1.S(2,4,0,"营口市住房公积金管理中心");//<%=book.getFOrgName()%>
		document.forms(0).Cell1.S(2,5,0,fBankid);
		document.forms(0).Cell1.S(7,6,0,"<%=book.getFOrgBank()%>");
		document.forms(0).Cell1.S(7,8,0,"人民币(小写) "+balance);
		var balance_wsh = balance;
		balance=ChangeToBig(balance);
		document.forms(0).Cell1.S(4,8,0,balance);
		document.forms(0).Cell1.S(2,16,0,operater);
		document.forms(0).Cell1.S(6,16,0,checkperson);
		
		document.forms(0).Cell1.insertSheetFromFile("<%=path%>/syscollection/pickupmng/print/pickupcell_yk2.cll",0,1,1);
		document.forms(0).Cell1.setSheetLabel(1,"第2页");//这句话必须放在加载文件的下边
		document.forms(0).Cell1.PrintSetCustomPaper(2400,1280,1);
		document.forms(0).Cell1.PrintSetMargin(150,100,50,100);
		
		document.forms(0).Cell1.S(2,3,1,"<%=book.getFOrgBank()%>");
		document.forms(0).Cell1.S(4,3,1,"<%=book.getBizdate()%>");
	    document.forms(0).Cell1.S(7,3,1,notnum);
        document.forms(0).Cell1.S(17,3,1,docnum);
		document.forms(0).Cell1.S(2,4,1,"<%=book.getSOrgName()%>");//这个单元格式虽然是5行的3列或4列..但是你必须指定的是第3列
		document.forms(0).Cell1.S(2,5,1,format("<%=book.getOrgid()%>")+"<%=book.getOrgid()%>");	
		document.forms(0).Cell1.S(7,4,1,sBank);
		document.forms(0).Cell1.S(11,6,1,other_card_num);
		document.forms(0).Cell1.S(2,8,1,eval(balance_wsh)-eval(interest_yg));
		document.forms(0).Cell1.S(7,8,1,interest_yg);
		document.forms(0).Cell1.S(10,9,1,"人民币(小写) "+balance_wsh);
		document.forms(0).Cell1.S(4,9,1,balance);
		document.forms(0).Cell1.S(2,16,1,operater);
		document.forms(0).Cell1.S(5,16,1,checkperson);
		var typei = type[0];
		var sn = 0;
		for(var ia =0;ia<i;ia++){
			if(typei != type[ia]){
				sn = 1;
			}
		}
		if(sn == 0){
			document.forms(0).Cell1.S(7,7,1,typei);
		}
		
		document.forms(0).Cell1.insertSheetFromFile("<%=path%>/syscollection/pickupmng/print/employee_mx_yk.cll",0,1,2);
		document.forms(0).Cell1.setSheetLabel(2,"第3页");//这句话必须放在加载文件的下边
		document.forms(0).Cell1.PrintSetCustomPaper(2400,1280,2);
		document.forms(0).Cell1.PrintSetMargin(150,100,50,100);
		
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
		
		var checkperson="<%=book.getCheckperson()%>";
		if(checkperson=="null"){
			checkperson="";
		}
		var operater="<%=book.getOperater()%>";
		if(operater=="null"){
			operater="";
		}
		var docnum="<%=book.getDocnum()%>";
		if(docnum=="null"){
			docnum="";
		}
		var notnum="<%=book.getNote_num()%>";
		if(notnum=="null"){
			notnum="";
		}
		////////////////打印尾表////////////
		var id=[];
		var name=[];
		var card=[];
		var pickBalance=[];
		var interest=[];
		var sum=[];
		var type=[];
		var reason = [];
		var fangzhaohao = [];
		var i = 0;
		<%
			PickTail tail_1 = null; 
			for(int j=0;j<list.size();j++){
			tail_1 = (PickTail)list.get(j);
			tail_1.setTotal(tail_1.getPickSalary().add(tail_1.getPickInterest()));
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
			if(type[i]=='购房'){
				fangzhaohao[i] = "<%=tail_1.getReserveaA()%>";
				if(fangzhaohao[i] == "null"){
					fangzhaohao[i] = "";
				}
			}else{
				fangzhaohao[i] = "";
			}
			
			i++;//这个地方的i是脚本的i  而不是循环中java的i;
		<%}%>
		var totalLine=id.length;			//总的行数 数组的长度
		var totalPageLine=10;					//每页显示多少行--除了第一行
		var pageTotal=totalLine/totalPageLine;	//总共分几页 总行数/每页的行数
		var pageCurrent=2;						//当前页
		var completeline=0;						//记录行的
		var moneytotal=0;						//总的合计
		var moneyoutsum=0;						//总的合计－提取金额
		var moneyinterest=0;					//总的合计-利息
		
		for(k = 0 ; k < totalLine; k++){
			if(k%totalPageLine==0&&k>0)
			{
			    
				document.forms(0).Cell1.S(1,17,pageCurrent,"本页小计");
				document.forms(0).Cell1.SetFormula (7, 17, pageCurrent, "Sum(G5:G"+(totalPageLine+4)+")" );
				document.forms(0).Cell1.SetFormula (8, 17, pageCurrent, "Sum(H5:H"+(totalPageLine+4)+")" );
				document.forms(0).Cell1.SetFormula (9, 17, pageCurrent, "Sum(I5:I"+(totalPageLine+4)+")" );
				document.forms(0).Cell1.ReDraw();//重画一个表格
				
			    document.forms(0).Cell1.S(2,2,pageCurrent,"<%=book.getSOrgName()%>");//这个单元格式虽然是5行的3列或4列..但是你必须指定的是第3列
				document.forms(0).Cell1.S(2,3,pageCurrent,format("<%=book.getOrgid()%>")+"<%=book.getOrgid()%>");
				document.forms(0).Cell1.S(4,3,pageCurrent,"<%=book.getBizdate()%>");
				document.forms(0).Cell1.S(6,3,pageCurrent,notnum);
				document.forms(0).Cell1.S(9,3,pageCurrent,docnum);
				document.forms(0).Cell1.S(2,18,pageCurrent,operater);
				document.forms(0).Cell1.S(4,18,pageCurrent,checkperson);
				pageCurrent++;	//当前页++
				completeline=k-4;
				//绘制标签 param 	表页索引号。param 要设置的表页页签名称			
				document.forms(0).Cell1.insertSheetFromFile("<%=path%>/syscollection/pickupmng/print/employee_mx_yk1.cll",0,1,pageCurrent);
				document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页")	;
				document.forms(0).Cell1.PrintSetCustomPaper(2400,1280,pageCurrent);
				document.forms(0).Cell1.PrintSetMargin(150,100,50,100);
			}
			if(pageCurrent==2)//如果当前页为第一页的时候 插入数据-->这个地方应该写当前页是第2页了..因为第一页是凭证
			{
				document.forms(0).Cell1.S(1,k+5,pageCurrent,k+1);
				document.forms(0).Cell1.S(3,k+5,pageCurrent,name[k]);
				document.forms(0).Cell1.S(4,k+5,pageCurrent,card[k]);
				document.forms(0).Cell1.S(5,k+5,pageCurrent,type[k]);
				document.forms(0).Cell1.S(6,k+5,pageCurrent,fangzhaohao[k]);
				document.forms(0).Cell1.d(7,k+5,pageCurrent,pickBalance[k]);
				document.forms(0).Cell1.d(8,k+5,pageCurrent,interest[k]);
				document.forms(0).Cell1.d(9,k+5,pageCurrent,sum[k]);
				document.forms(0).Cell1.S(2,k+5,pageCurrent,id[k]);//这个地方也要写第二页
				moneyoutsum=moneyoutsum+parseFloat(pickBalance[k]);
				moneyinterest=moneyinterest+parseFloat(interest[k]);
				moneytotal=moneytotal+parseFloat(sum[k]);
			}else{//向第一页后的所有页插数据
				document.forms(0).Cell1.S(1,k-completeline+1,pageCurrent,k+1);
				document.forms(0).Cell1.S(3,k-completeline+1,pageCurrent,name[k]);
				document.forms(0).Cell1.S(4,k-completeline+1,pageCurrent,card[k]);
				document.forms(0).Cell1.S(5,k-completeline+1,pageCurrent,type[k]);
				document.forms(0).Cell1.S(6,k-completeline+1,pageCurrent,fangzhaohao[k]);
				document.forms(0).Cell1.d(7,k-completeline+1,pageCurrent,pickBalance[k]);
				document.forms(0).Cell1.d(8,k-completeline+1,pageCurrent,interest[k]);
				document.forms(0).Cell1.d(9,k-completeline+1,pageCurrent,sum[k]);
				document.forms(0).Cell1.S(2,k-completeline+1,pageCurrent,id[k]);//这个地方也要写第二页
				moneyoutsum=moneyoutsum+parseFloat(pickBalance[k]);
				moneyinterest=moneyinterest+parseFloat(interest[k]);
				moneytotal=moneytotal+parseFloat(sum[k]);
			}
		}		
				if(completeline==0)//查询出来的记录可以在一页显示的时候会进入
				{
					document.forms(0).Cell1.S(2,2,pageCurrent,"<%=book.getSOrgName()%>");//这个单元格式虽然是5行的3列或4列..但是你必须指定的是第3列
					document.forms(0).Cell1.S(2,3,pageCurrent,format("<%=book.getOrgid()%>")+"<%=book.getOrgid()%>");
					document.forms(0).Cell1.S(4,3,pageCurrent,"<%=book.getBizdate()%>");
					document.forms(0).Cell1.S(6,3,pageCurrent,notnum);
					document.forms(0).Cell1.S(9,3,pageCurrent,docnum);
					document.forms(0).Cell1.S(2,18,pageCurrent,operater);
					document.forms(0).Cell1.S(4,18,pageCurrent,checkperson);
					
<%--				    document.forms(0).Cell1.S(1,totalLine+5,pageCurrent,"合计：");--%>
<%--					document.forms(0).Cell1.S(2, totalLine+5, pageCurrent, totalLine+"(人)" );--%>
<%--					document.forms(0).Cell1.S(1,totalLine+6,pageCurrent,"本页小计");--%>
<%--					document.forms(0).Cell1.SetFormula (7, totalLine+6, pageCurrent, "Sum(G5:G"+(totalLine+4)+")" );--%>
<%--					document.forms(0).Cell1.SetFormula (8, totalLine+6, pageCurrent, "Sum(H5:H"+(totalLine+4)+")" );--%>
<%--				    document.forms(0).Cell1.SetFormula (9, totalLine+6, pageCurrent, "Sum(I5:I"+(totalLine+4)+")" );--%>
					document.forms(0).Cell1.S(1,17,pageCurrent,"合计:");
					document.forms(0).Cell1.d(7,17,pageCurrent,moneyoutsum);
					document.forms(0).Cell1.d(8,17,pageCurrent,moneyinterest);
					document.forms(0).Cell1.d(9,17,pageCurrent,moneytotal);
					//document.forms(0).Cell1.DeleteRow(totalLine+9,totalPageLine-(totalLine+8),pageCurrent);
					document.forms(0).Cell1.ReDraw();
				}
				else
				{
					document.forms(0).Cell1.S(2,2,pageCurrent,"<%=book.getSOrgName()%>");//这个单元格式虽然是5行的3列或4列..但是你必须指定的是第3列
					document.forms(0).Cell1.S(2,3,pageCurrent,format("<%=book.getOrgid()%>")+"<%=book.getOrgid()%>");
					document.forms(0).Cell1.S(4,3,pageCurrent,"<%=book.getBizdate()%>");
					document.forms(0).Cell1.S(6,3,pageCurrent,notnum);
					document.forms(0).Cell1.S(9,3,pageCurrent,docnum);
					document.forms(0).Cell1.S(2,18,pageCurrent,operater);
					document.forms(0).Cell1.S(4,18,pageCurrent,checkperson);
					
<%--				    document.forms(0).Cell1.S(1,totalLine-completeline+1,pageCurrent,"合计：");--%>
<%--				    document.forms(0).Cell1.S(2, totalLine-completeline+1, pageCurrent, totalLine+"(人)" );--%>
<%--					document.forms(0).Cell1.S(1,totalLine-completeline+2,pageCurrent,"本页小计");--%>
<%--					document.forms(0).Cell1.SetFormula (7, totalLine-completeline+2, pageCurrent, "Sum(G5:G"+(totalLine-(completeline+0))+")");--%>
<%--					document.forms(0).Cell1.SetFormula (8, totalLine-completeline+2, pageCurrent, "Sum(H5:H"+(totalLine-(completeline+0))+")");--%>
<%--					document.forms(0).Cell1.SetFormula (9, totalLine-completeline+2, pageCurrent, "Sum(I5:I"+(totalLine-(completeline+0))+")");--%>
					document.forms(0).Cell1.S(1,17,pageCurrent,"总计:");
					document.forms(0).Cell1.d(7,17,pageCurrent,moneyoutsum);
					document.forms(0).Cell1.d(8,17,pageCurrent,moneyinterest);
					document.forms(0).Cell1.d(9,17,pageCurrent,moneytotal);
					//document.forms(0).Cell1.DeleteRow(totalLine-completeline+5,totalPageLine-(totalLine-completeline+4),pageCurrent);
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
<body onload = "load();" > 
<form action="">
<table align="center">
<tr><OBJECT id=Cell1 style= "LEFT:0px;WIDTH:850px;  TOP:0px;HEIGHT:450px" codeBase="http://192.168.1.44:8088/hafmis/CellWeb5.CAB#version=5,3,7,321" classid=clsid:3F166327-8030-4881-8BD2-EA25350E574A VIEWASTEXT><PARAM NAME="_Version" VALUE="65536"><PARAM NAME="_ExtentX" VALUE="10266"><PARAM NAME="_ExtentY" VALUE="7011"><PARAM NAME="_StockProps" VALUE= "0"></OBJECT></tr> 
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
