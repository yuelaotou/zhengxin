<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.lang.*" import="java.util.*"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic" %>
<%@ page import="org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.collectionstatistics.dto.CollectionstatisticsExportDTO" %>
<%@ include file="/../checkUrl.jsp"%>

<%
   String path=request.getContextPath();
 %>
<html>
  <head>
  <title>统计查询>>缴存提取统计>>归集情况统计>>打印列表</title>
  <script src="<%=path%>/js/common.js">
</script>
  </head>
  
  <script type="text/javascript">
	function load(){	
	document.forms(0).Cell1.openfile

("<%=path%>/syscollection/querystatistics/paymentpickstatistics/collectionstatistics/report/theStatCollectCircsList.cll","");
	
	var officeList=[]; //--1
	var collectionBankList=[]; //--2
	var orgIdList=[]; //--3
	var orgNameList=[];//--4
	var orgCharacterList=[];//--5
	var deptInChargeList=[];//--6
	var regionList=[];//--7
	var lastMonthCollectList=[];//--8
	var monthPayList=[];//--9
	var orgAddPayList=[];//--10
	var personAddPayList=[];//--11
	var orgOverPayList=[];//--12
	var chgPayList=[];//--13
	var thisMonthCollectList=[];//--14
	var rateList=[];//--15
	var i=0;
	
     <%
	  List list = new ArrayList();
	 list=(List)request.getSession().getAttribute("printList");
	 CollectionstatisticsExportDTO ce = null;
	 for(int i=0;i<list.size();i++){
	 ce = (CollectionstatisticsExportDTO)list.get(i);
	
	%>
	
	   officeList[i]="<%=ce.getOfficeCode() %>";   //--1
	   collectionBankList[i]="<%=ce.getCollectionBank() %>";//--2
	   orgIdList[i]=format("<%=ce.getOrgId().toString() %>")+"<%=ce.getOrgId().toString()%>";//--3
	   orgNameList[i]="<%=ce.getOrgName() %>";//--4
	   orgCharacterList[i]="<%=ce.getOrgCharacter() %>";//--5
	   deptInChargeList[i]="<%=ce.getDeptInCharge() %>";//--6
	   regionList[i]="<%=ce.getRegion() %>";//--7
	   lastMonthCollectList[i]="<%=ce.getLastMonthCollect() %>";//--8
	   monthPayList[i]="<%=ce.getMonthPay() %>";//--9
	   orgAddPayList[i]="<%=ce.getOrgAddPay() %>";//--10
	   personAddPayList[i]="<%=ce.getOrgOverPay()%>";//--11
	   orgOverPayList[i]="<%=ce.getPersonAddPay()  %>";//--12  PS.个人补缴和单位挂账有可能是set的时候位置相互set错了
	   chgPayList[i]="<%=ce.getChgPay() %>";//--13
	   thisMonthCollectList[i]="<%=ce.getThisMonthCollect() %>";//---14
	   rateList[i]="<%=ce.getRate() %>";//---15
	 i++;
	<%
	}
	%>
	  var totalLine=orgNameList.length;      //总行数
	  var totalPageline=40;                  //每页显示15行
	  var pageTotal=totalLine/totalPageline; //计算共几页
	  var pageCurrent=0;                     //当前页
	  var completeline=0;                    //记录行
	  var lastSum=0;                      //合计上月归集
	  var thisSum=0;                      //合计本月归集
	  var monthSum=0;                     //合计正常汇缴
	  var orgaddSum=0;                    //合计单位补缴 
	  var personSum=0;                    //合计个人补缴
	  var orgoverSum=0;                   //合计单位挂账
	  var chgpaySum=0;                    //合计调缴存
	  
	   for(k=0;k<totalLine;k++){
	  if(k%totalPageline==0 && k>0){

	                document.forms(0).Cell1.S(7,totalPageline+4,pageCurrent,"本页小计:");
	                document.forms(0).Cell1.SetFormula(8,totalPageline+4,pageCurrent,"Sum(H3:H"+(totalPageline+2)+")" );
					document.forms(0).Cell1.SetFormula (9,totalPageline+4,pageCurrent,"Sum(I3:I"+(totalPageline+2)+")" );
                    document.forms(0).Cell1.SetFormula (10,totalPageline+4,pageCurrent,"Sum(J3:J"+(totalPageline+2)+")" );
	                document.forms(0).Cell1.SetFormula (11,totalPageline+4,pageCurrent,"Sum(K3:K"+(totalPageline+2)+")" );
                    document.forms(0).Cell1.SetFormula (12,totalPageline+4,pageCurrent,"Sum(L3:L"+(totalPageline+2)+")" );
	                document.forms(0).Cell1.SetFormula (13,totalPageline+4,pageCurrent,"Sum(M3:M"+(totalPageline+2)+")" );
	                document.forms(0).Cell1.SetFormula (14,totalPageline+4,pageCurrent,"Sum(N3:N"+(totalPageline+2)+")" );
	    document.forms(0).Cell1.ReDraw();   //重绘表格
	     pageCurrent++;                     //加一页
	     completeline=k-2;
	     document.forms(0).Cell1.insertSheetFromFile("<%=path%>/syscollection/querystatistics/paymentpickstatistics/collectionstatistics/report/theStatCollectCircsList.cll",0,1,pageCurrent);
	     document.forms(0).Cell1.setSheetlabel(pageCurrent,"第"+(pageCurrent+1)+"页");
	  }
	  if(pageCurrent==0){
	    
	    document.forms(0).Cell1.S(1,k+3,0,officeList[k]);
	    document.forms(0).Cell1.S(2,k+3,0,collectionBankList[k]);
	    document.forms(0).Cell1.S(3,k+3,0,orgIdList[k]);
	    document.forms(0).Cell1.S(4,k+3,0,orgNameList[k]);
	   
	    if(orgCharacterList[k] == "null"){
	      orgCharacterList[k] = "";
	    }
	    document.forms(0).Cell1.S(5,k+3,0,orgCharacterList[k]);
	    
	     if(deptInChargeList[k] == "null"){
	      deptInChargeList[k] = "";
	    }
	    document.forms(0).Cell1.S(6,k+3,0,deptInChargeList[k]);
	     if(regionList[k] == "null"){
	      regionList[k] = "";
	    }
	    document.forms(0).Cell1.S(7,k+3,0,regionList[k]);
	    
	    if(lastMonthCollectList[k] == "null"){
	      lastMonthCollectList[k] = 0;
	    }
	    document.forms(0).Cell1.d(8,k+3,0,lastMonthCollectList[k]);
	    if(monthPayList[k] == "null"){
	      monthPayList[k] = 0;
	    }
	    document.forms(0).Cell1.d(9,k+3,0,monthPayList[k]);
	    if(orgAddPayList[k] == "null"){
	      orgAddPayList[k] = 0;
	    }
	    document.forms(0).Cell1.d(10,k+3,0,orgAddPayList[k]);
	    if(personAddPayList[k] == "null"){
	      personAddPayList[k] = 0;
	    }
	    document.forms(0).Cell1.d(11,k+3,0,personAddPayList[k]);
	    if(orgOverPayList[k] == "null"){
	      orgOverPayList[k] = 0;
	    }
	    document.forms(0).Cell1.d(12,k+3,0,orgOverPayList[k]);
	    if(chgPayList[k] == "null"){
	      chgPayList[k] = 0;
	    }
	    document.forms(0).Cell1.d(13,k+3,0,chgPayList[k]);
	    if(thisMonthCollectList[k] == "null"){
	      thisMonthCollectList[k] = 0;
	    }
	    document.forms(0).Cell1.d(14,k+3,0,thisMonthCollectList[k]);
	    document.forms(0).Cell1.s(15,k+3,0,rateList[k]);
	    
	    lastSum=lastSum+parseFloat(lastMonthCollectList[k]);
	    thisSum=thisSum+parseFloat(thisMonthCollectList[k]);
	    monthSum=monthSum+parseFloat(monthPayList[k]); 
	    orgaddSum=orgaddSum+parseFloat(orgAddPayList[k]); 
	    personSum=personSum+parseFloat(personAddPayList[k]);  
	    orgoverSum=orgoverSum+parseFloat(orgOverPayList[k]); 
	    chgpaySum=chgpaySum+parseFloat(chgPayList[k]); 
	  
	  }else{//向第一页后的所有页插数据
	    document.forms(0).Cell1.S(1,k-completeline+1,pageCurrent,officeList[k]);
	    document.forms(0).Cell1.S(2,k-completeline+1,pageCurrent,collectionBankList[k]);
	    document.forms(0).Cell1.S(3,k-completeline+1,pageCurrent,orgIdList[k]);
	    document.forms(0).Cell1.S(4,k-completeline+1,pageCurrent,orgNameList[k]);
	    document.forms(0).Cell1.S(5,k-completeline+1,pageCurrent,orgCharacterList[k]);
	    document.forms(0).Cell1.S(6,k-completeline+1,pageCurrent,deptInChargeList[k]);
	    document.forms(0).Cell1.S(7,k-completeline+1,pageCurrent,regionList[k]);
	    document.forms(0).Cell1.d(8,k-completeline+1,pageCurrent,lastMonthCollectList[k]);
	    document.forms(0).Cell1.d(9,k-completeline+1,pageCurrent,monthPayList[k]);
	    document.forms(0).Cell1.d(10,k-completeline+1,pageCurrent,orgAddPayList[k]);
	    document.forms(0).Cell1.d(11,k-completeline+1,pageCurrent,personAddPayList[k]);
	    document.forms(0).Cell1.d(12,k-completeline+1,pageCurrent,orgOverPayList[k]);
	    document.forms(0).Cell1.d(13,k-completeline+1,pageCurrent,chgPayList[k]);
	    document.forms(0).Cell1.d(14,k-completeline+1,pageCurrent,thisMonthCollectList[k]);
	    document.forms(0).Cell1.S(15,k-completeline+1,pageCurrent,rateList[k]);
	    
	    lastSum=lastSum+parseFloat(lastMonthCollectList[k]);
	    thisSum=thisSum+parseFloat(thisMonthCollectList[k]);
	    monthSum=monthSum+parseFloat(monthPayList[k]); 
	    orgaddSum=orgaddSum+parseFloat(orgAddPayList[k]); 
	    personSum=personSum+parseFloat(personAddPayList[k]);  
	    orgoverSum=orgoverSum+parseFloat(orgOverPayList[k]); 
	    chgpaySum=chgpaySum+parseFloat(chgPayList[k]); 
	   }
	  }
	  if(completeline==0){  //查询出来的记录可以在一页显示的时候会进入
	                document.forms(0).Cell1.S(7,totalLine+4,pageCurrent,"本页小计:");
	                document.forms(0).Cell1.SetFormula (8,totalLine+4,pageCurrent,"Sum(H3:H"+(totalLine+3)+")" );
					document.forms(0).Cell1.SetFormula (9,totalLine+4,pageCurrent,"Sum(I3:I"+(totalLine+3)+")" );
                    document.forms(0).Cell1.SetFormula (10,totalLine+4,pageCurrent,"Sum(J3:J"+(totalLine+3)+")" );
	                document.forms(0).Cell1.SetFormula (11,totalLine+4,pageCurrent,"Sum(K3:K"+(totalLine+3)+")" );
                    document.forms(0).Cell1.SetFormula (12,totalLine+4,pageCurrent,"Sum(L3:L"+(totalLine+3)+")" );
	                document.forms(0).Cell1.SetFormula (13,totalLine+4,pageCurrent,"Sum(M3:M"+(totalLine+3)+")" );
	                document.forms(0).Cell1.SetFormula (14,totalLine+4,pageCurrent,"Sum(N3:N"+(totalLine+3)+")" );
	                
	                document.forms(0).Cell1.S(7,totalLine+5,pageCurrent,"总计:");
	                document.forms(0).Cell1.d(8,totalLine+5,pageCurrent,lastSum);
					document.forms(0).Cell1.d(9,totalLine+5,pageCurrent,monthSum);
					document.forms(0).Cell1.d(10,totalLine+5,pageCurrent,orgaddSum);
					document.forms(0).Cell1.d(11,totalLine+5,pageCurrent,personSum);
					document.forms(0).Cell1.d(12,totalLine+5,pageCurrent,orgoverSum);
					document.forms(0).Cell1.d(13,totalLine+5,pageCurrent,chgpaySum);
				    document.forms(0).Cell1.d(14,totalLine+5,pageCurrent,thisSum);
	                
	  }else{
	                document.forms(0).Cell1.S(7,totalLine-completeline+4,pageCurrent,"本页小计:");
	                document.forms(0).Cell1.SetFormula (8,totalLine-completeline+4,pageCurrent,"Sum(H3:H"+(totalLine-(completeline-3))+")" );
					document.forms(0).Cell1.SetFormula (9,totalLine-completeline+4,pageCurrent,"Sum(I3:I"+(totalLine-(completeline-3))+")" );
                    document.forms(0).Cell1.SetFormula (10,totalLine-completeline+4,pageCurrent,"Sum(J3:J"+(totalLine-(completeline-3))+")" );
	                document.forms(0).Cell1.SetFormula (11,totalLine-completeline+4,pageCurrent,"Sum(K3:K"+(totalLine-(completeline-3))+")" );
                    document.forms(0).Cell1.SetFormula (12,totalLine-completeline+4,pageCurrent,"Sum(L3:L"+(totalLine-(completeline-3))+")" );
	                document.forms(0).Cell1.SetFormula (13,totalLine-completeline+4,pageCurrent,"Sum(M3:M"+(totalLine-(completeline-3))+")" );
	                document.forms(0).Cell1.SetFormula (14,totalLine-completeline+4,pageCurrent,"Sum(N3:N"+(totalLine-(completeline-3))+")" );
	                
	                document.forms(0).Cell1.S(7,totalLine-completeline+5,pageCurrent,"总计:");
	                document.forms(0).Cell1.d(8,totalLine-completeline+5,pageCurrent,lastSum);
					document.forms(0).Cell1.d(9,totalLine-completeline+5,pageCurrent,monthSum);
					document.forms(0).Cell1.d(10,totalLine-completeline+5,pageCurrent,orgaddSum);
					document.forms(0).Cell1.d(11,totalLine-completeline+5,pageCurrent,personSum);
					document.forms(0).Cell1.d(12,totalLine-completeline+5,pageCurrent,orgoverSum);
					document.forms(0).Cell1.d(13,totalLine-completeline+5,pageCurrent,chgpaySum);
				    document.forms(0).Cell1.d(14,totalLine-completeline+5,pageCurrent,thisSum);
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
	function LoginRegister()//注册CELL
    { 
        alert(document.all('Cell1').Login( "username","" ,"04000234", "1231332223234"));
    }
    
    function printsheet(){//真正的打印
		var k=document.forms(0).Cell1.GetCurSheet();//显示打印预览那个页面--固定
		document.forms(0).Cell1.PrintSheet(1,k);//固定...
	}

	
</script>
  <body onload = "load();" onContextmenu="return false"> 
 
    <form action="">
    <table align="center">
<tr><OBJECT id=Cell1 style= "LEFT:0px;WIDTH:700px;  TOP:0px;HEIGHT:400px" codeBase="http://192.168.1.44:8088/hafmis/CellWeb5.CAB#version=5,3,7,321" 

classid=clsid:3F166327-8030-4881-8BD2-EA25350E574A VIEWASTEXT><PARAM NAME="_Version" VALUE="65536"><PARAM NAME="_ExtentX" VALUE="10266"><PARAM 

NAME="_ExtentY" VALUE="7011"><PARAM NAME="_StockProps" VALUE= "0"></OBJECT></tr> 
<tr><input type="button" name="print" value = "打印预览" onclick = "printPreview();"/>
<INPUT id="Button1" onclick="Button1_onclick()" type="button" value="另存为Excel" name="Button1">
<INPUT id="Button1" onclick="Button2_onclick()" type="button" value="另存为pdf" name="Button1">
<INPUT id="Button3" style="WIDTH: 100px" onclick="Button3_onclick()" type="button" value="页面设置">
<INPUT id="Button3" style="WIDTH: 100px" onclick="Button4_onclick()" type="button" value="查找对话框">
<INPUT id="Button3" style="WIDTH: 100px" onclick="Button5_onclick()" type="button" value="excel导入">	
<INPUT id="Button1" onclick="printsheet()" type="button" value=" 打印 " name="Button1">
<INPUT id="Button3" onclick="javascript:history.back();" type="button" value=" 返回 ">		
</table>
</form>
  </body>
</html>
