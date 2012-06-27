<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.List" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic" %>
<%@ page import="org.xpup.hafmis.syscollection.common.domain.entity.*" %>
<%@ include file="/checkUrl.jsp"%>
<%
   String path=request.getContextPath();
 %>
<html>
  <head>
  </head>
  <script type="text/javascript">
	function load(){	

	document.forms(0).Cell1.openfile("<%=path%>/syscollection/paymng/report/orgOverPayList.cll","");

	var notenumlist=[];
	var docnumlist=[];
	var orgidlist=[];
	var orgnamelist=[];
    var amountlist=[];
	var datelist=[];
	var bizstatuslist=[];
	var i=0;
	<%      
	       
    		List list=(List) request.getSession().getAttribute("cellList");	  
  			for(int j=0;j<list.size();j++)
  			{
  				OrgExcessPayment orgExcessPayment=(OrgExcessPayment)list.get(j);
 	%>		
				notenumlist[i]=<%=orgExcessPayment.getNoteNum()%>;
				docnumlist[i]="<%=orgExcessPayment.getDocNum()%>";
				orgidlist[i]="<%=orgExcessPayment.getOrg().getId()%>";
				orgnamelist[i]="<%=orgExcessPayment.getOrg().getOrgInfo().getName()%>";
				amountlist[i]="<%=orgExcessPayment.getPayMoney()%>";
				datelist[i]="<%=orgExcessPayment.getSettDate()%>";
				bizstatuslist[i]="<%=orgExcessPayment.getPayStatus()%>";
				i++;
 	<%
 			}
 	%>
 	    
		var totalLine=notenumlist.length;		//总的行数
		var totalPageLine=15;					//每页显示多少行
		var pageTotal=totalLine/totalPageLine;	//总共分几页
		var pageCurrent=0;						//当前页
		var completeline=0;						//记录行的
		var moneytotal=0;						//总的合计
		for(k = 0 ; k < totalLine; k++){
			if(k%totalPageLine==0&&k>0)
			{
				document.forms(0).Cell1.S(5,13,pageCurrent,"本页小计");
				document.forms(0).Cell1.SetFormula (6, 13, pageCurrent, "Sum(E2:E"+(totalPageLine+1)+")" );
				document.forms(0).Cell1.ReDraw();
				pageCurrent++;	
				completeline=k-2;				
				document.forms(0).Cell1.insertSheetFromFile("<%=path%>/syscollection/paymng/report/orgOverPayList.cll",0,1,pageCurrent);
				document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页")	
			}
			if(pageCurrent==1)
			{
				document.forms(0).Cell1.S(1,k+2,0,notenumlist[k]);
				document.forms(0).Cell1.S(2,k+2,0,docnumlist[k]);
				document.forms(0).Cell1.S(3,k+2,0,orgidlist[k]);
				document.forms(0).Cell1.S(4,k+2,0,orgnamelist[k]);				
				document.forms(0).Cell1.d(5,k+2,0,amountlist[k]);
				document.forms(0).Cell1.S(6,k+2,0,datelist[k]);
				document.forms(0).Cell1.S(7,k+2,0,bizstatuslist[k]);

			
				moneytotal=moneytotal+parseFloat(amountlist[k]);
			}else{//向第一页后的所有页插数据
				document.forms(0).Cell1.S(1,k-completeline,pageCurrent,notenumlist[k]);
				document.forms(0).Cell1.S(2,k-completeline,pageCurrent,docnumlist[k]);
				document.forms(0).Cell1.S(3,k-completeline,pageCurrent,orgidlist[k]);
				document.forms(0).Cell1.S(4,k-completeline,pageCurrent,orgnamelist[k]);
				document.forms(0).Cell1.d(5,k-completeline,pageCurrent,amountlist[k]);
				document.forms(0).Cell1.S(6,k-completeline,pageCurrent,datelist[k]);
				document.forms(0).Cell1.S(7,k-completeline,pageCurrent,bizstatuslist[k]);
				

				moneytotal=moneytotal+parseFloat(amountlist[k]);
			}	
		}
				if(completeline==0)//查询出来的记录可以在一页显示的时候会进入
				{
					document.forms(0).Cell1.S(5,totalLine+2,pageCurrent,"本页小计");
					document.forms(0).Cell1.SetFormula (6, totalLine+2, pageCurrent, "Sum(E2:E"+(totalLine+1)+")" );//,loopcell() > 5
					document.forms(0).Cell1.S(5,totalLine+3,pageCurrent,"总计");
					document.forms(0).Cell1.d(6,totalLine+3,pageCurrent,moneytotal);
					document.forms(0).Cell1.DeleteRow(totalLine+4,152-(totalLine+3),pageCurrent);
					document.forms(0).Cell1.ReDraw();
				}
				else
				{
					document.forms(0).Cell1.S(5,totalLine-completeline,pageCurrent,"本页小计");
					document.forms(0).Cell1.SetFormula (6, totalLine-completeline, pageCurrent, "Sum(E2:E"+(totalLine-(completeline+1))+")" );
					document.forms(0).Cell1.S(5,totalLine-completeline+1,pageCurrent,"总计");
					document.forms(0).Cell1.d(6,totalLine-completeline+1,pageCurrent,moneytotal);
					document.forms(0).Cell1.DeleteRow(totalLine-completeline+2,152-(totalLine-completeline+1),pageCurrent);
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
	function LoginRegister()//注册CELL
    { 
        alert(document.all('Cell1').Login( "username","" ,"04000234", "1231332223234"));
    }	
			
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
</table>
</form>
</body>
</html>
