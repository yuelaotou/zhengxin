<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.List" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic" %>
<%@ page import="org.xpup.hafmis.syscollection.querystatistics.collfncomparison.dto.CollFnComparisonOrgAccountDTO" %>
<%@ include file="/checkUrl.jsp"%>

<%
   String path=request.getContextPath();
   List list=(List) request.getSession().getAttribute("countList_gjp1");
   String timest=(String)request.getSession().getAttribute("timest_gjp1");
   String timeend=(String)request.getSession().getAttribute("timeend_gjp1");
 %>
<html>
<head>
<script src="<%=path%>/js/common.js"></script>
</head>
<script type="text/javascript">
	function load(){
	loginReg();
	document.forms(0).Cell1.openfile("<%=path%>/syscollection/querystatistics/collfncomparison/report/collfncomparisonorgaccount_print.cll","");
  	var collsett_date=[];//日期
  	var doc_num=[];//凭证号
    var biz_type=[];//摘要
    var debit=[];//借方金额
    var credit=[];//贷方金额
    var distorybalance=[];//销户利息
    var aspect=[];//方向
    var moneysum=[];//余额
    var orgid=[];//单位编号
    var orgname=[];//单位名称
	var i=0;
	
	<%
		CollFnComparisonOrgAccountDTO collFnComparisonOrgAccountDTO = new CollFnComparisonOrgAccountDTO();
		for(int j=0;j<list.size();j++){
		collFnComparisonOrgAccountDTO = (CollFnComparisonOrgAccountDTO)list.get(j);
	%>
	    //把数据传到JS的数组里面..
		collsett_date[i] = "<%=collFnComparisonOrgAccountDTO.getCollsett_date()%>"; 
		doc_num[i] = "<%=collFnComparisonOrgAccountDTO.getDoc_num()%>";
		biz_type[i] = "<%=collFnComparisonOrgAccountDTO.getTemp_collSt()%>";
		debit[i] = "<%=collFnComparisonOrgAccountDTO.getDebit()%>";
		credit[i] = "<%=collFnComparisonOrgAccountDTO.getCredit()%>";
		distorybalance[i] = "<%=collFnComparisonOrgAccountDTO.getDistorybalance()%>";
		aspect[i] = "<%=collFnComparisonOrgAccountDTO.getAspect()%>";
		moneysum[i] = "<%=collFnComparisonOrgAccountDTO.getMoneySum()%>";
		orgid[i] = "<%=collFnComparisonOrgAccountDTO.getOrg_id()%>";
		orgname[i] = "<%=collFnComparisonOrgAccountDTO.getName()%>";
		i++;
	<%}%>
	var totalLine=debit.length;				//总的行数 数组的长度
	var totalPageLine=40;					//每页显示多少行
	var pageTotal=totalLine/totalPageLine;	//总共分几页 总行数/每页的行数
	var pageCurrent=0;						//当前页
	var completeline=0;						//记录行的
	var flag=0;								//标识list的满页分页
	var gg="";								//标志是否有承前页
	for(k = 0 ; k < totalLine; k++){
		if(k>0){
			if(orgid[k]!=orgid[k-1]){
				document.forms(0).Cell1.ReDraw();//重画一个表格
				pageCurrent++;//当前页++	
				completeline=k-2;
				flag=pageCurrent*40+1;
				//绘制标签 param 	表页索引号。param 要设置的表页页签名称
				document.forms(0).Cell1.insertSheetFromFile("<%=path%>/syscollection/querystatistics/collfncomparison/report/collfncomparisonorgaccount_print.cll",0,1,pageCurrent);
	  			document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页")	;
			}else if(flag%totalPageLine==0&&k>0){
				if(pageCurrent==0){	
					document.forms(0).Cell1.S(1,totalPageLine+3,pageCurrent,collsett_date[k-1]);
					document.forms(0).Cell1.S(2,totalPageLine+3,pageCurrent,doc_num[k-1]);
					document.forms(0).Cell1.S(3,totalPageLine+3,pageCurrent,"过次页");
					document.forms(0).Cell1.d(4,totalPageLine+3,pageCurrent,debit[k-1]);
					document.forms(0).Cell1.d(5,totalPageLine+3,pageCurrent,credit[k-1]);
					document.forms(0).Cell1.d(6,totalPageLine+3,pageCurrent,distorybalance[k-1]);
					document.forms(0).Cell1.S(7,totalPageLine+3,pageCurrent,aspect[k-1]);
					document.forms(0).Cell1.d(8,totalPageLine+3,pageCurrent,moneysum[k-1]);
					flag++;
					
				}else{
					if(gg=="0"){
						document.forms(0).Cell1.S(1,totalPageLine+4,pageCurrent,collsett_date[k-1]);
						document.forms(0).Cell1.S(2,totalPageLine+4,pageCurrent,doc_num[k-1]);
						document.forms(0).Cell1.S(3,totalPageLine+4,pageCurrent,"过次页");
						document.forms(0).Cell1.d(4,totalPageLine+4,pageCurrent,debit[k-1]);
						document.forms(0).Cell1.d(5,totalPageLine+4,pageCurrent,credit[k-1]);
						document.forms(0).Cell1.d(6,totalPageLine+4,pageCurrent,distorybalance[k-1]);
						document.forms(0).Cell1.S(7,totalPageLine+4,pageCurrent,aspect[k-1]);
						document.forms(0).Cell1.d(8,totalPageLine+4,pageCurrent,moneysum[k-1]);
						flag++;
					}else{
						document.forms(0).Cell1.S(1,totalPageLine+3,pageCurrent,collsett_date[k-1]);
						document.forms(0).Cell1.S(2,totalPageLine+3,pageCurrent,doc_num[k-1]);
						document.forms(0).Cell1.S(3,totalPageLine+3,pageCurrent,"过次页");
						document.forms(0).Cell1.d(4,totalPageLine+3,pageCurrent,debit[k-1]);
						document.forms(0).Cell1.d(5,totalPageLine+3,pageCurrent,credit[k-1]);
						document.forms(0).Cell1.d(6,totalPageLine+3,pageCurrent,distorybalance[k-1]);
						document.forms(0).Cell1.S(7,totalPageLine+3,pageCurrent,aspect[k-1]);
						document.forms(0).Cell1.d(8,totalPageLine+3,pageCurrent,moneysum[k-1]);
						flag++;
					}
				}
			document.forms(0).Cell1.ReDraw();//重画一个表格
			pageCurrent++;//当前页++	
			completeline=k-2;
			flag=pageCurrent*40+1;
			//绘制标签 param 	表页索引号。param 要设置的表页页签名称
			document.forms(0).Cell1.insertSheetFromFile("<%=path%>/syscollection/querystatistics/collfncomparison/report/collfncomparisonorgaccount_print.cll",0,1,pageCurrent);
  			document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页")	;
			}
		}
		if(pageCurrent==0)
		{		
			if(flag==0){
				flag=1;
			}
			document.forms(0).Cell1.S(1,2,0,"单位编号："+orgid[k]+",单位名称："+orgname[k]+",发生日期："+<%=timest%>+"--"+<%=timeend%>);
			document.forms(0).Cell1.S(1,k+4,0,collsett_date[k]);
			document.forms(0).Cell1.S(2,k+4,0,doc_num[k]);
			document.forms(0).Cell1.S(3,k+4,0,biz_type[k]);
			document.forms(0).Cell1.d(4,k+4,0,debit[k]);
			document.forms(0).Cell1.d(5,k+4,0,credit[k]);
			document.forms(0).Cell1.d(6,k+4,0,distorybalance[k]);
			document.forms(0).Cell1.S(7,k+4,0,aspect[k]);
			document.forms(0).Cell1.d(8,k+4,0,moneysum[k]);
			document.forms(0).Cell1.s(8,45,0,"第"+(pageCurrent+1)+"页");
			flag++;
		}
		else{//向第一页后的所有页插数据
			document.forms(0).Cell1.S(1,2,pageCurrent,"单位编号："+orgid[k]+",单位名称："+orgname[k]+",发生日期："+<%=timest%>+"--"+<%=timeend%>);
			if(orgid[k]==orgid[completeline+1]&&((flag-1)%totalPageLine==0)){
				gg="0";
				document.forms(0).Cell1.S(1,4,pageCurrent,collsett_date[completeline+1]);
				document.forms(0).Cell1.S(2,4,pageCurrent,doc_num[completeline+1]);
				document.forms(0).Cell1.S(3,4,pageCurrent,"承前页");
				document.forms(0).Cell1.d(4,4,pageCurrent,debit[completeline+1]);
				document.forms(0).Cell1.d(5,4,pageCurrent,credit[completeline+1]);
				document.forms(0).Cell1.d(6,4,pageCurrent,distorybalance[completeline+1]);
				document.forms(0).Cell1.S(7,4,pageCurrent,aspect[completeline+1]);
				document.forms(0).Cell1.d(8,4,pageCurrent,moneysum[completeline+1]);
				document.forms(0).Cell1.s(8,45,pageCurrent,"第"+(pageCurrent+1)+"页");
			}else if(orgid[k]!=orgid[completeline+1]){
				gg="";
			}
			if(gg=="0"){
				document.forms(0).Cell1.S(1,k-completeline+3,pageCurrent,collsett_date[k]);
				document.forms(0).Cell1.S(2,k-completeline+3,pageCurrent,doc_num[k]);
				document.forms(0).Cell1.S(3,k-completeline+3,pageCurrent,biz_type[k]);
				document.forms(0).Cell1.d(4,k-completeline+3,pageCurrent,debit[k]);
				document.forms(0).Cell1.d(5,k-completeline+3,pageCurrent,credit[k]);
				document.forms(0).Cell1.d(6,k-completeline+3,pageCurrent,distorybalance[k]);
				document.forms(0).Cell1.S(7,k-completeline+3,pageCurrent,aspect[k]);
				document.forms(0).Cell1.d(8,k-completeline+3,pageCurrent,moneysum[k]);
				document.forms(0).Cell1.s(8,45,pageCurrent,"第"+(pageCurrent+1)+"页");
				flag++;
			}else{
				document.forms(0).Cell1.S(1,k-completeline+2,pageCurrent,collsett_date[k]);
				document.forms(0).Cell1.S(2,k-completeline+2,pageCurrent,doc_num[k]);
				document.forms(0).Cell1.S(3,k-completeline+2,pageCurrent,biz_type[k]);
				document.forms(0).Cell1.d(4,k-completeline+2,pageCurrent,debit[k]);
				document.forms(0).Cell1.d(5,k-completeline+2,pageCurrent,credit[k]);
				document.forms(0).Cell1.d(6,k-completeline+2,pageCurrent,distorybalance[k]);
				document.forms(0).Cell1.S(7,k-completeline+2,pageCurrent,aspect[k]);
				document.forms(0).Cell1.d(8,k-completeline+2,pageCurrent,moneysum[k]);
				document.forms(0).Cell1.s(8,45,pageCurrent,"第"+(pageCurrent+1)+"页");
				flag++;
			}
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
		<INPUT id="Button3" onclick="javascript:history.back()" type="button" value=" 返回 ">		
	</tr>
</table>
</form>
</body>
</html>
