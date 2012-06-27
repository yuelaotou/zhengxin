<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.List" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic" %>
<%@ page import="org.xpup.hafmis.syscollection.common.domain.entity.*" %>
<%@ include file="/checkUrl.jsp"%>
<%
   String path=request.getContextPath();
   String bank="";
   if( request.getAttribute("bankname")!=null){
   		bank=(String)request.getAttribute("bankname");
   }
   String setDate="";
   if( request.getAttribute("setDate")!=null){
   		setDate=(String)request.getAttribute("setDate");
   		
   }
   String setDate2="";
   if( request.getAttribute("setDate2")!=null){
   		setDate=(String)request.getAttribute("setDate2");
   } 
   String username="";
   if( request.getAttribute("username")!=null){
   		username=(String)request.getAttribute("username");
   } 
 %>
<html>
  <head>
  
  <script src="<%=path%>/js/common.js">
</script>
  </head>
  <script type="text/javascript">
  	// 取总页数
	function getInt(i,k) { 
		var page=0; 
		var j; 
		j=Math.round(i/k)-i/k; 
		if (j>=0) 
		page=Math.round(i/k)-1; 
		if (j<=0) 
		page=Math.round(i/k); 
		if(j!=0)
		page=page+1;
		if(j==0)
		page=i/k;
		return page; 
	}
	function load(){	

	loginReg();
	document.forms(0).Cell1.openfile("<%=path%>/syscollection/accounthandle/clearaccount/report/report.cll","");
	document.forms(0).Cell1.setSheetLabel(pageCurrent,"第1页")
	
	var noteNum=[];
	var docNum=[];
	var orgid=[];
	var orgname=[];
	var bis_type=[];
	var settDate=[];
	var personTotal=[];
	var moneyTotal=[];
	var interest=[];
	var setdirection=[];
	var status=[];
	var reserveaA=[];
	var checkPerson=[];
	var clearPerson=[];
	var i=0;
	<%
    		List list=(List) request.getSession().getAttribute("cellList");
  			OrgHAFAccountFlow orgHAFAccountFlow=null;
  			for(int j=0;j<list.size();j++)
  			{
  				orgHAFAccountFlow=(OrgHAFAccountFlow)list.get(j);	
 	%>			
				noteNum[i]="<%=orgHAFAccountFlow.getNoteNum() %>";
				if(noteNum[i]=="null")
				noteNum[i]="";
				docNum[i]="<%=orgHAFAccountFlow.getDocNum()%>";
				if(docNum[i]=="null")
				docNum[i]="";
				orgid[i]=format("<%=orgHAFAccountFlow.getOrg().getId().toString() %>")+"<%=orgHAFAccountFlow.getOrg().getId().toString() %>";
				orgname[i]="<%=orgHAFAccountFlow.getOrg().getOrgInfo().getName()%>";
				bis_type[i]="<%=orgHAFAccountFlow.getBis_type() %>";
				settDate[i]="<%=orgHAFAccountFlow.getSettDate() %>";
				personTotal[i]=<%=orgHAFAccountFlow.getPersonTotal() %>;
				moneyTotal[i]=<%=orgHAFAccountFlow.getMoneyTotal() %>;
				interest[i]=<%=orgHAFAccountFlow.getInterest()%>;
				setdirection[i]="<%=orgHAFAccountFlow.getSetdirection()%>";
				status[i]="<%=orgHAFAccountFlow.getStatus() %>";
				reserveaA[i]="<%=orgHAFAccountFlow.getReserveaA_() %>";
				if(reserveaA[i]=="null")
					reserveaA[i]="";
				checkPerson[i]="<%=orgHAFAccountFlow.getCheckPersonStr() %>";
				clearPerson[i]="<%=orgHAFAccountFlow.getClearPersonStr() %>";
				if(checkPerson[i]=="null")
				checkPerson[i]="";
				if(clearPerson[i]=="null")
				clearPerson[i]="";
				i++;
 	<%
 			}
 	%>
 		var totalLine=orgname.length;			//总的行数
		var totalPageLine=12;					//每页显示多少行
		var pageTotal=totalLine/totalPageLine;	//总共分几页
		var pageCurrent=0;						//当前页
		var completeline=0;						//记录行的
		var counttotal=0;						//总的合计-人数
		var setmoneytotal=0;				    //总的合计-发生额
		var setinteresttotal=0;				    //总的合计-利息
		var dat="";
		if("<%=setDate2%>".length!=0){
			dat=' 至 '+"<%=setDate2%>";
		}else{
			dat=' 至 '+"<%=setDate%>";
		}
		var iPage = getInt(totalLine,totalPageLine);
		var temp_page = 1;
		
		for(k = 0 ; k < totalLine; k++){
			if(k%totalPageLine==0&&k>0)
			{
				document.forms(0).Cell1.S(1,totalPageLine+6,pageCurrent,"本页小计");
				document.forms(0).Cell1.SetFormula (4, totalPageLine+6, pageCurrent, "Sum(D6:D"+(totalPageLine+4)+")" );
				document.forms(0).Cell1.SetFormula (5, totalPageLine+6, pageCurrent, "Sum(E6:E"+(totalPageLine+4)+")" );
				//document.forms(0).Cell1.SetFormula (9, totalPageLine+5, pageCurrent, "Sum(I2:I"+(totalPageLine+3)+")" );
				document.forms(0).Cell1.DeleteRow(19,1,pageCurrent);
				document.forms(0).Cell1.ReDraw();
				pageCurrent++;	
				completeline=k-2;	
				temp_page = temp_page +	1;		
				
				document.forms(0).Cell1.insertSheetFromFile("<%=path%>/syscollection/accounthandle/clearaccount/report/report_yg.cll",0,1,pageCurrent);
				
				document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页")	
			}
			
		if(pageCurrent==0)
			{
				document.forms(0).Cell1.S(2,4,0,"<%=bank%>");
				document.forms(0).Cell1.S(4,4,0,"<%=setDate%>"+dat);
				document.forms(0).Cell1.S(7,4,0,"<%=username%>");
				document.forms(0).Cell1.S(8,4,0,"第"+temp_page+"/"+iPage+"页");
				
				document.forms(0).Cell1.S(1,k+6,0,docNum[k]);
				document.forms(0).Cell1.S(2,k+6,0,orgname[k]);
				document.forms(0).Cell1.S(3,k+6,0,bis_type[k]);
				document.forms(0).Cell1.d(4,k+6,0,moneyTotal[k]);
				document.forms(0).Cell1.d(5,k+6,0,interest[k]);
				document.forms(0).Cell1.S(6,k+6,0,reserveaA[k]);
				document.forms(0).Cell1.S(7,k+6,0,reserveaA[k]);
				document.forms(0).Cell1.S(8,k+6,0,clearPerson[k]);
				counttotal=counttotal+parseFloat(personTotal[k]);
				setmoneytotal=setmoneytotal+parseFloat(moneyTotal[k]);
				setinteresttotal=setinteresttotal+parseFloat(interest[k]);
			}else{//向第一页后的所有页插数据
				document.forms(0).Cell1.S(2,4,pageCurrent,"<%=bank%>");
				document.forms(0).Cell1.S(4,4,pageCurrent,"<%=setDate%>"+dat);
				document.forms(0).Cell1.S(7,4,pageCurrent,"<%=username%>");
				document.forms(0).Cell1.S(8,4,pageCurrent,"第"+temp_page+"/"+iPage+"页");
				document.forms(0).Cell1.S(1,k-completeline+4,pageCurrent,docNum[k]);
				document.forms(0).Cell1.S(2,k-completeline+4,pageCurrent,orgname[k]);
				document.forms(0).Cell1.S(3,k-completeline+4,pageCurrent,bis_type[k]);
				document.forms(0).Cell1.d(4,k-completeline+4,pageCurrent,moneyTotal[k]);
				document.forms(0).Cell1.d(5,k-completeline+4,pageCurrent,interest[k]);
				
				document.forms(0).Cell1.S(6,k-completeline+4,pageCurrent,reserveaA[k]);
				document.forms(0).Cell1.S(7,k-completeline+4,pageCurrent,reserveaA[k]);
				document.forms(0).Cell1.S(8,k-completeline+4,pageCurrent,clearPerson[k]);
				counttotal=counttotal+parseFloat(personTotal[k]);
				setmoneytotal=setmoneytotal+parseFloat(moneyTotal[k]);
				setinteresttotal=setinteresttotal+parseFloat(interest[k]);
			}	
		}
		
				if(completeline==0)//查询出来的记录可以在一页显示的时候会进入
				{
					document.forms(0).Cell1.S(1,totalLine+6,pageCurrent,"本页小计");
					document.forms(0).Cell1.SetFormula (4, totalLine+6, pageCurrent, "Sum(D2:D"+(totalLine+4)+")" );
					document.forms(0).Cell1.SetFormula (5, totalLine+6, pageCurrent, "Sum(E2:E"+(totalLine+4)+")" );
					//document.forms(0).Cell1.SetFormula (9, totalLine+5, pageCurrent, "Sum(I2:I"+(totalLine+3)+")" );
					document.forms(0).Cell1.S(1,totalLine+7,pageCurrent,"总计");
					//document.forms(0).Cell1.d(7,totalLine+6,pageCurrent,counttotal);
				document.forms(0).Cell1.d(4,totalLine+7,pageCurrent,setmoneytotal);
					document.forms(0).Cell1.d(5,totalLine+7,pageCurrent,setinteresttotal);
					document.forms(0).Cell1.ReDraw();
					
					document.forms(0).Cell1.DeleteRow(totalLine+8,totalPageLine-totalLine,pageCurrent);
				}	
								
				else
				{
					document.forms(0).Cell1.S(1,totalLine-completeline+4,pageCurrent,"本页小计");   
					//F1 第F列的第1行: N9 到第N列的第9行  求和 
					document.forms(0).Cell1.SetFormula (4, totalLine-completeline+4, pageCurrent, "Sum(D2:D"+(totalLine-(completeline-3))+")" );
					document.forms(0).Cell1.SetFormula (5, totalLine-completeline+4, pageCurrent, "Sum(E2:E"+(totalLine-(completeline-3))+")" );
					//document.forms(0).Cell1.SetFormula (9, totalLine-completeline+3, pageCurrent, "Sum(I2:I"+(totalLine-(completeline-1))+")" );
					document.forms(0).Cell1.S(1,totalLine-completeline+5,pageCurrent,"总计");
					//document.forms(0).Cell1.d(4,totalLine-completeline+5,pageCurrent,counttotal);
					document.forms(0).Cell1.d(4,totalLine-completeline+5,pageCurrent,setmoneytotal);
					document.forms(0).Cell1.d(5,totalLine-completeline+5,pageCurrent,setinteresttotal);
					document.forms(0).Cell1.ReDraw();
					document.forms(0).Cell1.DeleteRow(totalLine-completeline+6,(totalPageLine)-(totalLine-completeline-2),pageCurrent);
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
