<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.List" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic" %>
<%@ page import="org.xpup.hafmis.sysloan.querystatistics.loanaccountquery.particulargl.dto.ParticularglDTO" %>
<%@page
	import="org.xpup.hafmis.sysloan.querystatistics.checkqueryplfn.action.CheckQueryPlFnTBShowAC"%>
<%@ include file="/checkUrl.jsp"%>

<%
   String path=request.getContextPath();
   List list=(List) request.getSession().getAttribute("countList_wsh");
    String timest=(String)request.getSession().getAttribute("bizdateB");
   String timeend=(String)request.getSession().getAttribute("bizdateE");
   Object pagination = session
			.getAttribute(CheckQueryPlFnTBShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
 %>
<html>
<head>
<script src="<%=path%>/js/common.js"></script>
</head>
<script type="text/javascript">
	function load(){
	loginReg();
	document.forms(0).Cell1.openfile("<%=path%>/sysloan/querystatistics/checkqueryplfn/report/print_print.cll","");
	var contractId=[];//合同编号
  	var collsett_date=[];//日期
  	var doc_num=[];//凭证号
    var biz_type=[];//摘要
    var biz_status=[];//状态
    var debit=[];//借方金额
    var credit=[];//贷方金额
    var distorybalance=[];//销户利息
    var punishinterest=[];//罚息
    var aspect=[];//方向
    var moneysum=[];//余额
	var i=0;
	
	<%
		ParticularglDTO particularglDTO=new ParticularglDTO();
		for(int j=0;j<list.size();j++){
		particularglDTO = (ParticularglDTO)list.get(j);
	%>
	    //把数据传到JS的数组里面..
	    contractId[i] = "<%=particularglDTO.getContractId()%>"; 
		collsett_date[i] = "<%=particularglDTO.getOcyear()%>"; 
		doc_num[i] = "<%=particularglDTO.getDocNum()%>";
		biz_type[i] = "<%=particularglDTO.getBizType()%>";
		
		debit[i] = "<%=particularglDTO.getThisborrower()%>";
		credit[i] = "<%=particularglDTO.getThispaymoney()%>";
		distorybalance[i] = "<%=particularglDTO.getThisinterest()%>";
		punishinterest[i] = "<%=particularglDTO.getThispunishinterest()%>";
		aspect[i] = "<%=particularglDTO.getDerection()%>";
		moneysum[i] = "<%=particularglDTO.getLastcorpus()%>";
		i++;
	<%}%>
	var totalLine=debit.length;				//总的行数 数组的长度
	var totalPageLine=40;					//每页显示多少行--除了第一行
	var pageTotal=totalLine/totalPageLine;	//总共分几页 总行数/每页的行数
	var pageCurrent=0;						//当前页
	var completeline=0;						//记录行的
	var contract=""                         //每页合同  
	var flag=0;								//标识list的满页分页
	
	for(k = 0 ; k < totalLine; k++){
	
	
			if(k=='0'){
				contract=contractId[k];
				
			}
			
			if(contractId[k]!=contract){//不为这个科目的话,创建新的页面  
				contract=contractId[k];
				document.forms(0).Cell1.ReDraw();
				pageCurrent++;	
				completeline=k-1;
				flag=pageCurrent*40+1;
				document.forms(0).Cell1.insertSheetFromFile("<%=path%>/sysloan/querystatistics/checkqueryplfn/report/print_print.cll",0,1,pageCurrent);
				document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页");
			}
	
	
		
		if(flag%totalPageLine==0&&k>0)
		{	
			if(pageCurrent==0){	
				document.forms(0).Cell1.S(1,totalPageLine+4,pageCurrent,collsett_date[k-1]);
				document.forms(0).Cell1.S(2,totalPageLine+4,pageCurrent,doc_num[k-1]);
				document.forms(0).Cell1.S(3,totalPageLine+4,pageCurrent,"过次页");
				
				document.forms(0).Cell1.d(4,totalPageLine+4,pageCurrent,debit[k-1]);
				document.forms(0).Cell1.d(5,totalPageLine+4,pageCurrent,credit[k-1]);
				document.forms(0).Cell1.d(6,totalPageLine+4,pageCurrent,distorybalance[k-1]);
				document.forms(0).Cell1.d(7,totalPageLine+4,pageCurrent,punishinterest[k-1]);
				document.forms(0).Cell1.S(8,totalPageLine+4,pageCurrent,aspect[k-1]);
				document.forms(0).Cell1.d(9,totalPageLine+4,pageCurrent,moneysum[k-1]);
			}else{
			
			
					if(contractId[completeline+1]!=contractId[completeline]){//不为这个科目的话,创建新的页面  
						
						document.forms(0).Cell1.S(1,totalPageLine+3,pageCurrent,collsett_date[k-1]);
						document.forms(0).Cell1.S(2,totalPageLine+3,pageCurrent,doc_num[k-1]);
						document.forms(0).Cell1.S(3,totalPageLine+3,pageCurrent,"过次页");
						
						document.forms(0).Cell1.d(4,totalPageLine+3,pageCurrent,debit[k-1]);
						document.forms(0).Cell1.d(5,totalPageLine+3,pageCurrent,credit[k-1]);
						document.forms(0).Cell1.d(6,totalPageLine+3,pageCurrent,distorybalance[k-1]);
						document.forms(0).Cell1.d(7,totalPageLine+3,pageCurrent,punishinterest[k-1]);
						document.forms(0).Cell1.S(8,totalPageLine+3,pageCurrent,aspect[k-1]);
						document.forms(0).Cell1.d(9,totalPageLine+3,pageCurrent,moneysum[k-1]);
						
					}else{
				document.forms(0).Cell1.S(1,totalPageLine+5,pageCurrent,collsett_date[k-1]);
				document.forms(0).Cell1.S(2,totalPageLine+5,pageCurrent,doc_num[k-1]);
				document.forms(0).Cell1.S(3,totalPageLine+5,pageCurrent,"过次页");
				
				document.forms(0).Cell1.d(4,totalPageLine+5,pageCurrent,debit[k-1]);
				document.forms(0).Cell1.d(5,totalPageLine+5,pageCurrent,credit[k-1]);
				document.forms(0).Cell1.d(6,totalPageLine+5,pageCurrent,distorybalance[k-1]);
				document.forms(0).Cell1.d(7,totalPageLine+5,pageCurrent,punishinterest[k-1]);
				document.forms(0).Cell1.S(8,totalPageLine+5,pageCurrent,aspect[k-1]);
				document.forms(0).Cell1.d(9,totalPageLine+5,pageCurrent,moneysum[k-1]);
				}
			}
			document.forms(0).Cell1.ReDraw();//重画一个表格
			pageCurrent++;//当前页++	
			completeline=k-2;
			//绘制标签 param 	表页索引号。param 要设置的表页页签名称
			document.forms(0).Cell1.insertSheetFromFile("<%=path%>/sysloan/querystatistics/checkqueryplfn/report/print_print.cll",0,1,pageCurrent);
  			document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页")	
		}
		if(pageCurrent==0)
		{	document.forms(0).Cell1.S(1,2,0,"合同编号："+contractId[k]+",发生日期："+<%=timest%>+"--"+<%=timeend%>);
			document.forms(0).Cell1.S(1,k+4,0,collsett_date[k]);
			document.forms(0).Cell1.S(2,k+4,0,doc_num[k]);
			document.forms(0).Cell1.S(3,k+4,0,biz_type[k]);
			
			document.forms(0).Cell1.d(4,k+4,0,debit[k]);
			document.forms(0).Cell1.d(5,k+4,0,credit[k]);
			document.forms(0).Cell1.d(6,k+4,0,distorybalance[k]);
			document.forms(0).Cell1.d(7,k+4,0,punishinterest[k]);
			document.forms(0).Cell1.S(8,k+4,0,aspect[k]);
			document.forms(0).Cell1.d(9,k+4,0,moneysum[k]);
			document.forms(0).Cell1.s(9,46,0,"第"+(pageCurrent+1)+"页");
			
			flag++;
			}
		else{//向第一页后的所有页插数据
		document.forms(0).Cell1.S(1,2,pageCurrent,"合同编号："+contractId[completeline+1]+",发生日期："+<%=timest%>+"--"+<%=timeend%>);
			document.forms(0).Cell1.S(1,4,pageCurrent,collsett_date[completeline+1]);
			document.forms(0).Cell1.S(2,4,pageCurrent,doc_num[completeline+1]);
			if(contractId[completeline+1]!=contractId[completeline]){
			
				document.forms(0).Cell1.S(3,4,pageCurrent,biz_type[completeline+1]);
				
			}else{
			
				document.forms(0).Cell1.S(3,4,pageCurrent,"承前页");
			}
			
			
			document.forms(0).Cell1.d(4,4,pageCurrent,debit[completeline+1]);
			document.forms(0).Cell1.d(5,4,pageCurrent,credit[completeline+1]);
			document.forms(0).Cell1.d(6,4,pageCurrent,distorybalance[completeline+1]);
			document.forms(0).Cell1.d(7,4,pageCurrent,punishinterest[completeline+1]);
			document.forms(0).Cell1.S(8,4,pageCurrent,aspect[completeline+1]);
			document.forms(0).Cell1.d(9,4,pageCurrent,moneysum[completeline+1]);
				
			document.forms(0).Cell1.S(1,k-completeline+3,pageCurrent,collsett_date[k]);
			document.forms(0).Cell1.S(2,k-completeline+3,pageCurrent,doc_num[k]);
			document.forms(0).Cell1.S(3,k-completeline+3,pageCurrent,biz_type[k]);
			
			document.forms(0).Cell1.d(4,k-completeline+3,pageCurrent,debit[k]);
			document.forms(0).Cell1.d(5,k-completeline+3,pageCurrent,credit[k]);
			document.forms(0).Cell1.d(6,k-completeline+3,pageCurrent,distorybalance[k]);
			document.forms(0).Cell1.d(7,k-completeline+3,pageCurrent,punishinterest[k]);
			document.forms(0).Cell1.S(8,k-completeline+3,pageCurrent,aspect[k]);
			document.forms(0).Cell1.d(9,k-completeline+3,pageCurrent,moneysum[k]);
			document.forms(0).Cell1.s(9,46,pageCurrent,"第"+(pageCurrent+1)+"页");
			flag++;
		}		
	}
	if(completeline==0)//查询出来的记录可以在一页显示的时候会进入
	{
		
		document.forms(0).Cell1.ReDraw();
	}	
	else
	{	
		
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
		<INPUT id="Button3" onclick="location.href='checkQueryPlFnTBShowAC.do'" type="button" value=" 返回 ">		
	</tr>
</table>
</form>
</body>
</html>
