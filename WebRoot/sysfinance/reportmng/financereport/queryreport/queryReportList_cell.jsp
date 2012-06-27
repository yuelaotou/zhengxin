<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic"%>
<%@ page
	import="org.xpup.hafmis.sysfinance.reportmng.financereport.queryreport.form.QueryReportAF"%>
<%@ page import="org.xpup.hafmis.common.util.BusiConst"%>
<%@ include file="/checkUrl.jsp"%>

<%
	String path = request.getContextPath();
%>
<html>
	<head>
		<script src="<%=path%>/js/common.js"></script>
	</head>
	<script type="text/javascript">
	function load(){	
	loginReg();
	<%
     		QueryReportAF queryReportAF=(QueryReportAF)request.getSession().getAttribute("queryReportAFPrint");
     		String tablename = queryReportAF.getTablename();
     		String year = queryReportAF.getYearmonth().substring(0,4);
     		String userName = (String) request.getAttribute("userName");
     		if(tablename.equals("资产负债表")){
     		%>
				document.forms(0).Cell1.openfile("<%=path%>/sysfinance/reportmng/financereport/queryreport/report/queryReportList.cll","");	
				document.forms(0).Cell1.S(5,2,0,"<%=queryReportAF.getYearmonth()%>");
				document.forms(0).Cell1.S(7,30,0,"制表人:<%=userName%>");
     		<%
     		}else if(tablename.equals("增值收益表")){
     		%>
				document.forms(0).Cell1.openfile("<%=path%>/sysfinance/reportmng/financereport/queryreport/report/queryReportList1.cll","");
				document.forms(0).Cell1.S(2,3,0,"<%=queryReportAF.getYearmonth()%>");
				document.forms(0).Cell1.S(4,25,0,"制表人:<%=userName%>");
     		<%
     		}else if(tablename.equals("增值收益分配表")){
     		%>
				document.forms(0).Cell1.openfile("<%=path%>/sysfinance/reportmng/financereport/queryreport/report/queryReportList2.cll","");
				document.forms(0).Cell1.S(2,3,0,"<%=year%>年度");
				document.forms(0).Cell1.S(2,20,0,"制表人:<%=userName%>");
     		<%
     		}      		
 	%>	
	
	var col1=[];	var col2=[];	var col3=[];	var col4=[];	var col5=[];
	var col6=[];	var col7=[];	var col8=[];	var col9=[];	var col10=[];	
	var col11=[];	var col12=[];	var col13=[];	var col14=[];	var col15=[];
	var col16=[];	var col17=[];	var col18=[];	var col19=[];	var col20=[];
	var col21=[];	var col22=[];	var col23=[];	var col24=[];	var col25=[];
		
	var col26=[];	var col27=[];	var col28=[];	var col29=[];	var col30=[];
	var col31=[];	var col32=[];	var col33=[];	var col34=[];	var col35=[];	
	var col36=[];	var col37=[];	var col38=[];	var col39=[];	var col10=[];	
	var col41=[];	var col42=[];   var col43=[];	var col44=[];	var col45=[];
	var col46=[];	var col47=[];	var col48=[];	var col49=[];	var col50=[];	
	
	var m=0;//行数
	var k=0;//列数
 	m=<%=Integer.parseInt(queryReportAF.getCol())%>;
 	k=<%=Integer.parseInt(queryReportAF.getRow())%>;
	//document.forms(0).Cell1.S(1,1,0,"<%=queryReportAF.getTablename()%>");
	<%
  			for(int i=1;i<(Integer.parseInt(queryReportAF.getCol()) + 1);i++){
  				for(int j=1;j<(Integer.parseInt(queryReportAF.getRow()) + 1);j++){
          		if ((queryReportAF.getValue("v" + i + "_" + j + "") != null)&& (!queryReportAF.getValue("v" + i + "_" + j + "").toString().equals(""))) {
	  				if(queryReportAF.getValue("v" + i + "_" + j + "").toString().substring(0, 1).equals(BusiConst.REPORTLOGO_FORMULA)){
	  					%>
	  						document.forms(0).Cell1.SetCellNumType(<%=j%>,<%=i + 3%>,0,1);
	  						document.forms(0).Cell1.SetCellDigital(<%=j%>,<%=i + 3%>,0,2);
	  						document.forms(0).Cell1.d(<%=j%>,<%=i + 3%>,0,<%=queryReportAF.getValue("" + i + "_" + j
											+ "")%>);
	  					<%
	  				}else{
 	%>	
					document.forms(0).Cell1.S(<%=j%>,<%=i + 3%>,0,"  <%=queryReportAF.getValue("" + i + "_" + j
											+ "")%>");
 	<%
	  				}
	  			}
 				}
 			}
 	%>
	}
	
	function commondcol(col){//得到指定列最适合的列宽]
		var width=document.forms(0).Cell1.GetColBestWidth(col);
		var page=document.forms(0).Cell1.GetCurSheet();
		document.forms(0).Cell1.SetColWidth(1,width,col,page);
	}
	function printsheet(){//真正的打印
		var k=document.forms(0).Cell1.GetCurSheet();//显示打印预览那个页面--固定
		document.forms(0).Cell1.PrintSheet(1,k);//固定...
	}
	function printPreview(){
		var k=document.forms(0).Cell1.GetCurSheet();//显示打印预览那个页面
		document.forms(0).Cell1.printPreviewEx(1,k,false);
	}
	function Button1_onclick()
	{
		document.forms(0).Cell1.SaveFile();
	}
	function Button3_onclick()
	{
		document.forms(0).Cell1.PrintPageSetup();
	}
	function Button5_onclick()
	{
		document.forms(0).Cell1.CellPropertyDlg();
	}
	function Button2_onclick()
	{   var col1;var row1;var col2;var row2;
    	col1=document.forms(0).Cell1.GetSelectRangeJ(0);
    	row1=document.forms(0).Cell1.GetSelectRangeJ(1);
    	col2=document.forms(0).Cell1.GetSelectRangeJ(2);
    	row2=document.forms(0).Cell1.GetSelectRangeJ(3);
    	document.forms(0).Cell1.MergeCells(col1,row1,col2,row2);

	}
	function Button4_onclick()
	{   var col1;var row1;var col2;var row2;
    	col1=document.forms(0).Cell1.GetSelectRangeJ(0);
    	row1=document.forms(0).Cell1.GetSelectRangeJ(1);
    	col2=document.forms(0).Cell1.GetSelectRangeJ(2);
    	row2=document.forms(0).Cell1.GetSelectRangeJ(3);
    	document.forms(0).Cell1.UnmergeCells(col1,row1,col2,row2);
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
						style="LEFT:0px;WIDTH:900px;  TOP:0px;HEIGHT:500px"
						codeBase="http://192.168.1.44:8088/hafmis/CellWeb5.CAB#version=5,3,7,321"
						classid=clsid:3F166327-8030-4881-8BD2-EA25350E574A VIEWASTEXT>
						<PARAM NAME="_Version" VALUE="65536">
						<PARAM NAME="_ExtentX" VALUE="10266">
						<PARAM NAME="_ExtentY" VALUE="7011">
						<PARAM NAME="_StockProps" VALUE="0">
					</OBJECT>
				</tr>
				<tr>
					<td>
						<input type="button" name="print" value="打印预览"
							onclick="printPreview();" />
						<INPUT id="Button1" onclick="printsheet()" type="button"
							value=" 打印 " name="Button1">
						<INPUT id="Button1" onclick="Button1_onclick()" type="button"
							value="另存为Excel" name="Button1">
						<INPUT id="Button3" style="WIDTH: 100px"
							onclick="Button3_onclick()" type="button" value="页面设置">
						<INPUT id="Button3" style="WIDTH: 100px"
							onclick="Button5_onclick()" type="button" value="设置单元格属性">
						<INPUT id="Button3" style="WIDTH: 100px"
							onclick="Button2_onclick()" type="button" value="合并单元格">
						<INPUT id="Button3" style="WIDTH: 100px"
							onclick="Button4_onclick()" type="button" value="取消合并单元格">
						<INPUT id="Button3" onclick="javascript:history.back();"
							type="button" value=" 返回 ">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
