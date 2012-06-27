<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.List" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic" %>
<%@ page import="org.xpup.hafmis.syscollection.paymng.personaddpay.dto.*" %>
<%@ include file="/checkUrl.jsp"%>
<% 
String paymentid = (String)request.getSession().getAttribute("payment_id");
 String path=request.getContextPath();
%>
<html>
  <head>
  </head>
  <script type="text/javascript">
	function load(){	

	document.forms(0).Cell1.openfile("<%=path%>/syscollection/paymng/report/empaddpaylist.cll","");

	var idlist=[];
	var namelist=[];
	var pyamonth=[];
	var orgpay=[];
	var emppay=[];
	var sumpay=[];
	var status=[];
	var reason=[];
	var i=0;
	<%
    		List list=(List) request.getAttribute("cellList");
  			AddPayExpDTO dto=null;
  			for(int j=0;j<list.size();j++)
  			{
  				dto=(AddPayExpDTO)list.get(j);	
 	%>		
				idlist[i]=<%=dto.getEmpId()%>;
				namelist[i]="<%=dto.getEmpName()%>";
				pyamonth[i]="<%=dto.getOrgPay()%>";
				orgpay[i]=<%=dto.getEmpPay()%>;
				emppay[i]=<%=dto.getPaySum()%>;
				sumpay[i]=<%=dto.getBeginMonth()%>;
				status[i]="<%=dto.getEndMonth()%>";
				reason[i]="<%=dto.getReason()%>";
				i++;
 	<%
 			}
 	%>
			var totalLine=namelist.length;			//总的行数
			var totalPageLine=15;					//每页显示多少行
			var pageTotal=totalLine/totalPageLine;	//总共分几页
			var pageCurrent=0;						//当前页
			var completeline=0;						//记录行的
			var moneytotal=0;	
			//总的合计
		for(k = 0 ; k < totalLine; k++){
		if(k%totalPageLine==0&&k>0)
			{
				document.forms(0).Cell1.S(4,13,pageCurrent,"本页小计");
				document.forms(0).Cell1.SetFormula (5, 13, pageCurrent, "Sum(E2:E"+(totalPageLine+1)+")" );
				document.forms(0).Cell1.ReDraw();
				pageCurrent++;	
				completeline=k-2;				
				document.forms(0).Cell1.insertSheetFromFile("<%=path%>/syscollection/paymng/report/empaddpaylist.cll",0,1,pageCurrent);
				document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页")	
			}
		if(pageCurrent==0)
			{
				document.forms(0).Cell1.S(1,k+2,0,idlist[k]);
				document.forms(0).Cell1.S(2,k+2,0,namelist[k]);
				document.forms(0).Cell1.S(3,k+2,0,pyamonth[k]);
				document.forms(0).Cell1.S(4,k+2,0,orgpay[k]);
				document.forms(0).Cell1.d(5,k+2,0,emppay[k]);
				document.forms(0).Cell1.s(6,k+2,0,sumpay[k]);
				document.forms(0).Cell1.s(7,k+2,0,status[k]);
				document.forms(0).Cell1.s(8,k+2,0,reason[k]);
				moneytotal=moneytotal+parseFloat(emppay[k]);
			}else{//向第一页后的所有页插数据
				document.forms(0).Cell1.S(1,k-completeline,pageCurrent,idlist[k]);
				document.forms(0).Cell1.S(2,k-completeline,pageCurrent,namelist[k]);
				document.forms(0).Cell1.S(3,k-completeline,pageCurrent,pyamonth[k]);
				document.forms(0).Cell1.S(4,k-completeline,pageCurrent,orgpay[k]);
				document.forms(0).Cell1.d(5,k-completeline,pageCurrent,emppay[k]);
				document.forms(0).Cell1.s(6,k-completeline,pageCurrent,sumpay[k]);
				document.forms(0).Cell1.s(7,k-completeline,pageCurrent,status[k]);
				document.forms(0).Cell1.s(7,k-completeline,pageCurrent,reason[k]);
				moneytotal=moneytotal+parseFloat(emppay[k]);
			}
		}
		
			if(completeline==0)//查询出来的记录可以在一页显示的时候会进入
				{			
					document.forms(0).Cell1.S(4,totalLine+2,pageCurrent,"本页小计");
					document.forms(0).Cell1.SetFormula (5, totalLine+2, pageCurrent, "Sum(E2:E"+(totalLine+1)+")" );//,loopcell() > 5
					document.forms(0).Cell1.S(4,totalLine+3,pageCurrent,"总计");
					document.forms(0).Cell1.d(5,totalLine+3,pageCurrent,moneytotal);
					document.forms(0).Cell1.DeleteRow(totalLine+4,totalPageLine-(totalLine+3),pageCurrent);
		///		document.forms(0).Cell1.ReDraw();
				}else
				{
					document.forms(0).Cell1.S(4,totalLine-completeline,pageCurrent,"本页小计");
					document.forms(0).Cell1.SetFormula (5, totalLine-completeline, pageCurrent, "Sum(E2:E"+(totalLine-(completeline+1))+")" );
					document.forms(0).Cell1.S(4,totalLine-completeline+1,pageCurrent,"总计");
					document.forms(0).Cell1.d(5,totalLine-completeline+1,pageCurrent,moneytotal);
					document.forms(0).Cell1.DeleteRow(totalLine-completeline+2,totalPageLine-(totalLine-completeline+1),pageCurrent);
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
	function printsheet(){//真正的打印
		var k=document.forms(0).Cell1.GetCurSheet();//显示打印预览那个页面--固定
		document.forms(0).Cell1.PrintSheet(1,k);//固定...
	}	
</script>
  <body onload = "load();" onContextmenu="return false"> 
    <form action="">
    <table align="center">
<tr><OBJECT id=Cell1 style= "LEFT:0px;WIDTH:700px;  TOP:0px;HEIGHT:400px" codeBase="http://192.168.1.44:8088/hafmis/CellWeb5.CAB#version=5,3,7,321" classid=clsid:3F166327-8030-4881-8BD2-EA25350E574A VIEWASTEXT><PARAM NAME="_Version" VALUE="65536"><PARAM NAME="_ExtentX" VALUE="10266"><PARAM NAME="_ExtentY" VALUE="7011"><PARAM NAME="_StockProps" VALUE= "0"></OBJECT></tr> 
<tr>
<INPUT id="Button1" onclick="printsheet();" type="button" value=" 打印 " name="Button1">
<input type="button" name="print" value = "打印预览" onclick = "printPreview();"/>
<INPUT id="Button1" onclick="Button1_onclick()" type="button" value="另存为Excel" name="Button1">
<INPUT id="Button1" onclick="Button2_onclick()" type="button" value="另存为pdf" name="Button1">
<INPUT id="Button3" style="WIDTH: 90px" onclick="Button3_onclick()" type="button" value="页面设置">
<INPUT id="Button3" style="WIDTH: 90px" onclick="Button4_onclick()" type="button" value="查找对话框">
<INPUT id="Button3" style="WIDTH: 90px" onclick="Button5_onclick()" type="button" value="excel导入">
<INPUT id="Button3" onclick="location.href='personaddpayTbWindowForwardAC.do?&paymentid=<%=paymentid%>'" type="button" value=" 返回 ">	
</table>
</form>
  </body>
</html>
