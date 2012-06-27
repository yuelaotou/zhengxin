<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.lang.*" import="java.util.*"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic" %>
<%@ page import="org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pastyearscontrast.dto.PastyearscontrasDTO" %>
<%@ page import="org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.pastyearscontrast.form.PastyearscontrasAF" %>
<%@ include file="/../checkUrl.jsp"%>

<%
   String path=request.getContextPath();
 %>
<html>
  <head>
  <title>统计查询>>缴存提取统计>>历年基础数据比照>>打印列表</title>
  <script src="<%=path%>/js/common.js">
</script>
  </head>
  
  <script type="text/javascript">
	function load(){	
	document.forms(0).Cell1.openfile("<%=path%>/syscollection/querystatistics/paymentpickstatistics/pastyearscontrast/report/pastyearscontrast.cll","");
	<%
	  PastyearscontrasAF pastyearscontrasAF = new PastyearscontrasAF();
	  pastyearscontrasAF = (PastyearscontrasAF)request.getSession().getAttribute("thePastyearscontrasAF");
	%>
	  office ="<%=pastyearscontrasAF.getOfficeCode() %>"
      bank = "<%=pastyearscontrasAF.getCollectionBank() %>"
      character = "<%=pastyearscontrasAF.getOrgChracter() %>"
      dept = "<%=pastyearscontrasAF.getDept() %>"
      ragion = "<%=pastyearscontrasAF.getRagion() %>"
      startDate = "<%=pastyearscontrasAF.getStartDate() %>"
      endDate = "<%=pastyearscontrasAF.getEndDate() %>"
      
      if(office=="null"){
        office = "";
      }
      if(bank=="null"){
        bank = "";
      }
      if(character=="null"){
        character = "";
      }
      if(dept =="null"){
        dept = "";
      }
      if(ragion=="null"){
        ragion = "";
      }
      if(startDate=="null"){
       startDate = "";
      }
      if(endDate=="null"){
       endDate = "";
      }
	  document.forms(0).Cell1.S(3,3,0,office);
	  document.forms(0).Cell1.S(8,3,0,bank);
	  document.forms(0).Cell1.S(3,4,0,character);
	  document.forms(0).Cell1.S(8,4,0,dept);
	  document.forms(0).Cell1.S(3,5,0,startDate);
	  document.forms(0).Cell1.S(5,5,0,endDate);
	  document.forms(0).Cell1.S(8,5,0,ragion);
	  
	  document.forms(0).Cell1.S(4,10,0,"<%=pastyearscontrasAF.getPastyearscontrasDTO().getOrgOpen() %>");
	  document.forms(0).Cell1.S(4,11,0,"<%=pastyearscontrasAF.getPastyearscontrasDTO().getEmpOpen() %>");
	  document.forms(0).Cell1.S(4,13,0,"<%=pastyearscontrasAF.getPastyearscontrasDTO().getPayCount() %>");
	  document.forms(0).Cell1.S(7,13,0,"<%=pastyearscontrasAF.getPastyearscontrasDTO().getPayChg() %>");
	  document.forms(0).Cell1.S(4,14,0,"<%=pastyearscontrasAF.getPastyearscontrasDTO().getRateCount() %>");
	  document.forms(0).Cell1.S(7,14,0,"<%=pastyearscontrasAF.getPastyearscontrasDTO().getRateChg() %>");
	  document.forms(0).Cell1.S(4,15,0,"<%=pastyearscontrasAF.getPastyearscontrasDTO().getLaborageCount() %>"); 
	  document.forms(0).Cell1.S(7,15,0,"<%=pastyearscontrasAF.getPastyearscontrasDTO().getLaborageChg() %>"); 
	  document.forms(0).Cell1.S(4,16,0,"<%=pastyearscontrasAF.getPastyearscontrasDTO().getEmpCount() %>");  
	  document.forms(0).Cell1.S(7,16,0,"<%=pastyearscontrasAF.getPastyearscontrasDTO().getEmpChg()%>"); 
	  document.forms(0).Cell1.S(4,17,0,"<%=pastyearscontrasAF.getPastyearscontrasDTO().getSumCount()%>"); 
	  document.forms(0).Cell1.S(7,17,0,"<%=pastyearscontrasAF.getPastyearscontrasDTO().getSumChg() %>");    
	  
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
