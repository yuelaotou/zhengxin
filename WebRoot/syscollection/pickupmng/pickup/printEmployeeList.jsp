<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic" %>
<%@ page import="org.xpup.hafmis.syscollection.common.domain.entity.*"%>
<%@ page import="org.xpup.hafmis.syscollection.pickupmng.pickup.form.PickupGetCompanyIdAF" %>
<%@ include file="/checkUrl.jsp"%>
<%
	PickupGetCompanyIdAF print = (PickupGetCompanyIdAF)request.getSession().getAttribute("orgList");
	String path=request.getContextPath();
%>
<html:html lang="true">
  <head>
    <html:base />
    <title>打印职工</title>
	<meta http-equiv="pragma" content="no-cache">
  </head>
  <script type="text/javascript">
	function load(){//打开文件...
		document.forms(0).Cell1.openfile("<%=path%>/syscollection/pickupmng/print/employeeAndList.cll","");
		var orgId="<%=print.getId()%>";
		var orgName = "<%=print.getName()%>";
		var noteNumber = "<%=print.getNoteNumber()%>";
		var docNumber = "<%=print.getDocNumber()%>";
		var id=[];
		var name=[];
		var card=[];
		var pickBalance=[];
		var interest=[];
		var sum=[];
		var reason=[];
		var type=[];
		var i = 0;
		<%
			PickTail tail = null; 
			for(int j=0;j<print.getList().size();j++){
			tail = (PickTail)print.getList().get(j);
		%>
			id[i] = "<%=tail.getEmpId()%>";
			name[i] = "<%=tail.getEmp().getEmpInfo().getName()%>";
			card[i] = "<%=tail.getEmp().getEmpInfo().getCardNum()%>";
			pickBalance[i] = "<%=tail.getPickSalary()%>";
			interest[i] ="<%=tail.getPickInterest()%>";
			sum[i] ="<%=tail.getTotal()%>";
			type[i] = "<%=tail.getReason()%>";
			reason[i] = "<%=tail.getPickDisplayType()%>";
			i++;
		<%}%>
		/////////////////////////////
		var totalLine=id.length;			//总的行数 数组的长度
		var totalPageLine=10;					//每页显示多少行--除了第一行
		var pageTotal=totalLine/totalPageLine;	//总共分几页 总行数/每页的行数
		var pageCurrent=0;						//当前页
		var completeline=0;						//记录行的
		var moneytotal=0;						//总的合计
		for(k = 0 ; k < totalLine; k++){
			if(k%totalPageLine==0&&k>0)
			{
				document.forms(0).Cell1.ReDraw();//重画一个表格
				pageCurrent++;//当前页++	
				completeline=k-2;
				//绘制标签 param 	表页索引号。param 要设置的表页页签名称					
				document.forms(0).Cell1.insertSheetFromFile("<%=path%>/syscollection/pickupmng/print/employeeAndList.cll",0,1,pageCurrent);
				document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页")	
			}
			document.forms(0).Cell1.S(1,2,pageCurrent,orgId);
			document.forms(0).Cell1.S(2,2,pageCurrent,orgName);
			document.forms(0).Cell1.S(3,2,pageCurrent,noteNumber);
			document.forms(0).Cell1.S(4,2,pageCurrent,docNumber);
			if(pageCurrent==0)//如果当前页为第一页的时候 插入数据-->这个地方应该写当前页是第2页了..因为第一页是凭证
			{
				//document.forms(0).Cell1.S(列,行,页数,"本页小计");
				document.forms(0).Cell1.S(1,k+4,0,id[k]);//这个地方也要写第二页
				document.forms(0).Cell1.S(2,k+4,0,name[k]);
				document.forms(0).Cell1.S(3,k+4,0,card[k]);
				document.forms(0).Cell1.S(4,k+4,0,pickBalance[k]);
				document.forms(0).Cell1.S(5,k+4,0,interest[k]);
				document.forms(0).Cell1.S(6,k+4,0,sum[k]);
				document.forms(0).Cell1.S(7,k+4,0,type[k]);
				document.forms(0).Cell1.S(8,k+4,0,reason[k]);
			}else{//向第一页后的所有页插数据
				document.forms(0).Cell1.S(1,k-completeline+2,pageCurrent,id[k]);
				document.forms(0).Cell1.S(2,k-completeline+2,pageCurrent,name[k]);
				document.forms(0).Cell1.S(3,k-completeline+2,pageCurrent,card[k]);
				document.forms(0).Cell1.S(4,k-completeline+2,pageCurrent,pickBalance[k]);
				document.forms(0).Cell1.S(5,k-completeline+2,pageCurrent,interest[k]);
				document.forms(0).Cell1.S(6,k-completeline+2,pageCurrent,sum[k]);
				//d方法是插入蓝色的字,但是此方法必须插入的是数字
				//document.forms(0).Cell1.d(6,k-completeline,pageCurrent,sum[k]);
				document.forms(0).Cell1.S(7,k-completeline+2,pageCurrent,type[k]);
				document.forms(0).Cell1.S(8,k-completeline+2,pageCurrent,reason[k]);
			}	
		}		
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
<INPUT id="Button1" onclick="printsheet()" type="button" value=" 打印 " name="Button1">
<INPUT id="Button3" onclick="javascript:history.back();" type="button" value=" 返回 ">
<INPUT id="Button1" onclick="printsheet()" type="button" value="print" name="Button1">
<INPUT id="Button1" onclick="Button1_onclick()" type="button" value="另存为Excel" name="Button1">
<INPUT id="Button1" onclick="printsheet()" type="button" value="print" name="Button1">
<INPUT id="Button1" onclick="Button2_onclick()" type="button" value="另存为pdf" name="Button1">
<INPUT id="Button3" style="WIDTH: 100px" onclick="Button3_onclick()" type="button" value="页面设置">
<INPUT id="Button3" style="WIDTH: 100px" onclick="Button4_onclick()" type="button" value="查找对话框">
<INPUT id="Button3" style="WIDTH: 100px" onclick="Button5_onclick()" type="button" value="excel导入">	
</table>
</form>
  </body>
</html:html>
