<%@ page contentType="text/html;charset=UTF-8" language="java"
	 import="java.util.*"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic"%>
<%@ page
	import="org.xpup.hafmis.syscollection.common.domain.entity.ChgOrgRate"%>
<%@ page import="org.xpup.hafmis.common.util.BusiTools" %>

<%
String path = request.getContextPath();
%>
<html>
	<head>
		<title>统计查询>>变更信息>>汇缴比例调整>>打印列表</title>
		<script src="<%=path%>/js/common.js">
</script>
	</head>

	<script type="text/javascript">
	function load(){
	loginReg();	
	document.forms(0).Cell1.openfile("<%=path%>/syscollection/querystatistics/chgbiz/chgorgrate/report/chgorgrateList.cll","");
	
	var officeList=[];
	var collectionBankList=[];
	var orgIdList=[];
	var orgNameList=[];
	var preOrgRateList=[];
	var preEmpRateList=[];
	var orgRateList=[];
	var empRateList=[];
	var sumPreOrgEmpList=[];
	var sumOrgEmpList=[];
	var chgMonthList=[];
	var bizDateList=[];
	var chgStatusList=[];
	var rateList=[];
	var data_1=[];
	var data_2=[];
	var data_3=[];
	var i=0;
	<%
	List list=(List)request.getSession().getAttribute("thechgorgrateList");
	ChgOrgRate cor = null;
	for(int j=0;j<list.size();j++){
	cor = (ChgOrgRate)list.get(j);
	String tempid = cor.getOrg().getId().toString();
    String orgid = BusiTools.convertTenNumber(tempid);
	%>
       officeList[i]="<%=cor.getReserveaB() %>";   //--1
	   collectionBankList[i]="<%=cor.getReserveaC() %>";//--2
	   orgIdList[i]="<%=orgid%>";//--3
	   orgNameList[i]="<%=cor.getOrg().getOrgInfo().getName()%>";//--4
	   preOrgRateList[i]="<%=cor.getPreOrgRate()%>";//--5
	   preEmpRateList[i]="<%=cor.getPreEmpRate()%>";//--6
	   orgRateList[i]="<%=cor.getOrgRate()%>";//--7
	   empRateList[i]="<%=cor.getEmpRate()%>";//--8
	   sumPreOrgEmpList[i]="<%=cor.getPreSumPay()%>";//--9
	   sumOrgEmpList[i]="<%=cor.getSumPay()%>";//--10
	   chgMonthList[i]="<%=cor.getChgMonth()%>";//--11
       bizDateList[i]="<%=cor.getBizDate()%>";//--12
	   chgStatusList[i]="<%=cor.getTemp_chgStatus()%>";//--13
	   rateList[i]="<%=cor.getRate_() %>";//---14
	   data_1[i]="<%=cor.getChgPersonCount() %>";//---14
	   data_2[i]="<%=cor.getMonth() %>";//---14
	   data_3[i]="<%=cor.getPay() %>";//---14
	   i++;
	<%
	}
	%>
	  var totalLine=orgNameList.length;      //总行数
	  var totalPageline=45;                  //每页显示40行
	  var pageTotal=totalLine/totalPageline; //计算共几页
	  var pageCurrent=0;                     //当前页
	  var completeline=0;                    //记录行
	  var preSum=0;                      //合计一(调整前应缴总额)
	  var Sum=0;                         //合计二(调整后应缴总额)
	  var premoneytotal=0;               //总计一(调整前应缴总额)
	  var moneytotal=0;                  //总计二(调整后应缴总额)  
	  for(k=0;k<totalLine;k++){
	  if(k%totalPageline==0 && k>0){
        document.forms(0).Cell1.S(1,totalPageline+4,pageCurrent,"本页小计:");
		document.forms(0).Cell1.SetFormula (8, totalPageline+4, pageCurrent, "Sum(H4:H"+(totalPageline+3)+")" );//,loopcell() > 5
		document.forms(0).Cell1.SetFormula (9, totalPageline+4, pageCurrent, "Sum(I4:I"+(totalPageline+3)+")" );
		document.forms(0).Cell1.DeleteRow(50,2,pageCurrent);
     	document.forms(0).Cell1.ReDraw();   //重绘表格
     	pageCurrent++;                     //加一页
     	completeline=k-2;
      	document.forms(0).Cell1.insertSheetFromFile("<%=path%>/syscollection/querystatistics/chgbiz/chgorgrate/report/chgorgrateList_1.cll",0,1,pageCurrent);
	    document.forms(0).Cell1.setSheetlabel(pageCurrent,"第"+(pageCurrent+1)+"页");
	  }
	  if(pageCurrent==0){
	    document.forms(0).Cell1.S(1,k+4,0,orgIdList[k]);
	    document.forms(0).Cell1.S(2,k+4,0,orgNameList[k]);
	    document.forms(0).Cell1.S(3,k+4,0,data_1[k]);
	    document.forms(0).Cell1.S(4,k+4,0,preOrgRateList[k]);
	    document.forms(0).Cell1.S(5,k+4,0,preEmpRateList[k]);
	    document.forms(0).Cell1.S(6,k+4,0,orgRateList[k]);
	    document.forms(0).Cell1.S(7,k+4,0,empRateList[k]);
	    document.forms(0).Cell1.d(8,k+4,0,sumPreOrgEmpList[k]);
	    document.forms(0).Cell1.d(9,k+4,0,sumOrgEmpList[k]);
	    document.forms(0).Cell1.d(10,k+4,0,data_3[k]);
	    
	    document.forms(0).Cell1.s(11,k+4,0,data_2[k]);
	    document.forms(0).Cell1.S(12,k+4,0,chgMonthList[k]);
	    document.forms(0).Cell1.S(13,k+4,0,bizDateList[k]);
	    premoneytotal=premoneytotal+parseFloat(sumPreOrgEmpList[k]);
	    moneytotal=moneytotal+parseFloat(sumOrgEmpList[k]);
	  }else{//向第一页后的所有页插数据
	    document.forms(0).Cell1.S(1,k-completeline+2,pageCurrent,orgIdList[k]);
	    document.forms(0).Cell1.S(2,k-completeline+2,pageCurrent,orgNameList[k]);
	    document.forms(0).Cell1.S(3,k-completeline+2,pageCurrent,data_1[k]);
	    document.forms(0).Cell1.S(4,k-completeline+2,pageCurrent,preOrgRateList[k]);
	    document.forms(0).Cell1.S(5,k-completeline+2,pageCurrent,preEmpRateList[k]);
	    document.forms(0).Cell1.S(6,k-completeline+2,pageCurrent,orgRateList[k]);
	    document.forms(0).Cell1.S(7,k-completeline+2,pageCurrent,empRateList[k]);
	    document.forms(0).Cell1.d(8,k-completeline+2,pageCurrent,sumPreOrgEmpList[k]);
	    document.forms(0).Cell1.d(9,k-completeline+2,pageCurrent,sumOrgEmpList[k]);
	    document.forms(0).Cell1.d(10,k-completeline+2,pageCurrent,data_3[k]);
	    document.forms(0).Cell1.S(11,k-completeline+2,pageCurrent,data_2[k]);
	    document.forms(0).Cell1.S(12,k-completeline+2,pageCurrent,chgMonthList[k]);
	    document.forms(0).Cell1.S(13,k-completeline+2,pageCurrent,bizDateList[k]);
	    premoneytotal=premoneytotal+parseFloat(sumPreOrgEmpList[k]);
	    moneytotal=moneytotal+parseFloat(sumOrgEmpList[k]);
	   }
	  }
	  if(completeline==0){  //查询出来的记录可以在一页显示的时候会进入
                document.forms(0).Cell1.S(1,totalLine+4,pageCurrent,"本页小计:");
				document.forms(0).Cell1.SetFormula (8, totalLine+4, pageCurrent, "Sum(H4:H"+(totalLine+3)+")" );//,loopcell() > 5
				document.forms(0).Cell1.SetFormula (9, totalLine+4, pageCurrent, "Sum(I4:I"+(totalLine+3)+")" );
				document.forms(0).Cell1.S(1,totalLine+5,pageCurrent,"总计:");
				document.forms(0).Cell1.d(8,totalLine+5,pageCurrent,premoneytotal);
				document.forms(0).Cell1.d(9,totalLine+5,pageCurrent,moneytotal);
				document.forms(0).Cell1.DeleteRow(totalLine+6,51-(totalLine+5),pageCurrent);
				document.forms(0).Cell1.ReDraw();
	  }else{
                document.forms(0).Cell1.S(1,totalLine-completeline+2,pageCurrent,"本页小计:");
				document.forms(0).Cell1.SetFormula (8, totalLine-completeline+2, pageCurrent, "Sum(H4:H"+(totalLine-completeline-1)+")" );//,loopcell() > 5
				document.forms(0).Cell1.SetFormula (9, totalLine-completeline+2, pageCurrent, "Sum(I4:I"+(totalLine-completeline-1)+")" );
				document.forms(0).Cell1.S(1,totalLine-completeline+3,pageCurrent,"总计:");
				document.forms(0).Cell1.d(8,totalLine-completeline+3,pageCurrent,premoneytotal);
				document.forms(0).Cell1.d(9,totalLine-completeline+3,pageCurrent,moneytotal);
				document.forms(0).Cell1.DeleteRow(totalLine-completeline+4,totalPageline-(totalLine-completeline-3),pageCurrent);
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
//	function LoginRegister()//注册CELL
//    { 
//        alert(document.all('Cell1').Login( "username","" ,"04000234", "1231332223234"));
//    }
    	
    function printsheet(){//真正的打印
		var k=document.forms(0).Cell1.GetCurSheet();//显示打印预览那个页面--固定
		document.forms(0).Cell1.PrintSheet(1,k);//固定...
	}
</script>
<script language="JScript.Encode">
eval(unescape('function%20LoginRegister%28%29//%u6CE8%u518CCELL%0D%0A%20%7B%20%0D%0A%20document.forms%280%29.Cell1.Login%28%22%u6C88%u9633%u91D1%u8F6F%u79D1%u6280%u6709%u9650%u516C%u53F8%22%2C%22%22%2C%2213100104509%22%2C%220740-1662-7775-3003%22%29%3B%20%20%20%20%0D%0A%20%7D'))
</script>
	<body onload="load();" onContextmenu="return false">
	
		<form action="">
			<table align="center">
				<tr>
					<OBJECT id=Cell1
						style="LEFT:0px;WIDTH:700px;  TOP:0px;HEIGHT:400px"
						codeBase="http://192.168.1.44:8088/hafmis/CellWeb5.CAB#version=5,3,7,321"
						classid=clsid:3F166327-8030-4881-8BD2-EA25350E574A VIEWASTEXT>
						<PARAM NAME="_Version" VALUE="65536">
						<PARAM NAME="_ExtentX" VALUE="10266">
						<PARAM NAME="_ExtentY" VALUE="7011">
						<PARAM NAME="_StockProps" VALUE="0">
					</OBJECT>
				</tr>
				<tr>
					<input type="button" name="print" value="打印预览"
						onclick="printPreview();" />
					<INPUT id="Button1" onclick="Button1_onclick()" type="button"
						value="另存为Excel" name="Button1">
					<INPUT id="Button1" onclick="Button2_onclick()" type="button"
						value="另存为pdf" name="Button1">
					<INPUT id="Button3" style="WIDTH: 100px"
						onclick="Button3_onclick()" type="button" value="页面设置">
					<INPUT id="Button3" style="WIDTH: 100px"
						onclick="Button4_onclick()" type="button" value="查找对话框">
					<INPUT id="Button3" style="WIDTH: 100px"
						onclick="Button5_onclick()" type="button" value="excel导入">
					<INPUT id="Button1" onclick="printsheet()" type="button" value=" 打印 " name="Button1">

<INPUT id="Button3" onclick="javascript:history.back();" type="button" value=" 返回 ">

			</table>
		</form>
	</body>
</html>
