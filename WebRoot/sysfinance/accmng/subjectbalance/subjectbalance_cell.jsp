<%@ page language="java" pageEncoding="UTF-8" import="java.util.List"%>
<%@ page
	import="org.xpup.hafmis.sysfinance.accmng.subjectbalance.dto.SubjectbalanceDTO"%>
<%
	String path = request.getContextPath();
	String username = (String) request.getAttribute("userName");
	String countCredenceNum = (String) request.getAttribute("countCredenceNum");
	String bizDate = (String) request.getAttribute("bizDate");
	String url = (String) request.getSession().getAttribute("url");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<script src="<%=path%>/js/common.js"></script>
	</head>
	<script type="text/javascript">
	function load(){
		loginReg();
		document.form1.Cell1.openfile("<%=path%>/sysfinance/accmng/report/subjectbalance.cll","");
	    var subjectCode=[];
	    var subjectName=[];
	    var firstRemainingDebit=[];
	    var firstRemainingCredit=[];
	    var thisIssueDebit=[];
	    var thisIssueCredit=[];
	    var thisYearDebit=[];
	    var thisYearCredit=[];
	    var lastRemainingDebit=[];
	    var lastRemainingCredit=[];
	    var yearDebit=[];
		var i=0;
		var url = "<%=url%>";
		<%
			List list = (List) request.getSession().getAttribute("totalList");
			SubjectbalanceDTO subjectbalanceDTO = null;
			for(int j=0;j<list.size();j++){
			subjectbalanceDTO = (SubjectbalanceDTO)list.get(j);
		%>
		    //把数据传到JS的数组里面..
			subjectCode[i] = "<%=subjectbalanceDTO.getSubjectCode()%>";
			subjectName[i] = "<%=subjectbalanceDTO.getSubjectName()%>"; 
			firstRemainingDebit[i] = "<%=subjectbalanceDTO.getFirstRemainingDebit()%>";
			firstRemainingCredit[i] = "<%=subjectbalanceDTO.getFirstRemainingCredit()%>";
			thisIssueDebit[i] = "<%=subjectbalanceDTO.getThisIssueDebit()%>";
			thisIssueCredit[i] = "<%=subjectbalanceDTO.getThisIssueCredit()%>";
			thisYearDebit[i] = "<%=subjectbalanceDTO.getThisYearDebit()%>";
			thisYearCredit[i] = "<%=subjectbalanceDTO.getThisYearCredit()%>";
			lastRemainingDebit[i] = "<%=subjectbalanceDTO.getLastRemainingDebit()%>";
			lastRemainingCredit[i] = "<%=subjectbalanceDTO.getLastRemainingCredit()%>";
			yearDebit[i] = "<%=subjectbalanceDTO.getYearDebit()%>";
			i++;
		<%}%>
		
		var totalLine=subjectCode.length;		//总的行数 数组的长度
		var totalPageLine=35;					//每页显示多少行--除了第一行
		var pageTotal=totalLine/totalPageLine;	//总共分几页 总行数/每页的行数
		var pageCurrent=0;						//当前页
		var completeline=0;						//记录行的
		var iPage = getInt(totalLine,totalPageLine);
		for(k = 0 ; k < totalLine; k++){
			if(k%totalPageLine==0&&k>0)
			{
				document.form1.Cell1.ReDraw();//重画一个表格
				pageCurrent++;//当前页++	
				completeline=k-2;
				//绘制标签 param 	表页索引号。param 要设置的表页页签名称
				document.form1.Cell1.insertSheetFromFile("<%=path%>/sysfinance/accmng/report/subjectbalance.cll",0,1,pageCurrent);	
				document.form1.Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页")	
			}
			if(pageCurrent==0)
			{	
				if(url!="null") {
					document.form1.Cell1.S(1,1,0,"总 账 科 目 汇 总 表");
					document.form1.Cell1.MergeCells(3,3,6,3);
					document.form1.Cell1.S(3,3,0,"<%=bizDate.substring(0,4)%>年<%=bizDate.substring(5,7)%>月汇总凭单自  1  号至  <%=countCredenceNum%>  号");
				}else{
					document.form1.Cell1.S(6,3,0,"<%=bizDate%>");
				}
				document.form1.Cell1.S(9,3,0,"第"+(pageCurrent+1)+"/"+iPage+"页");
				document.form1.Cell1.S(1,k+6,0,subjectCode[k]);
				document.form1.Cell1.S(2,k+6,0,subjectName[k]);
				document.form1.Cell1.d(3,k+6,0,yearDebit[k]);
				document.form1.Cell1.d(4,k+6,0,firstRemainingDebit[k]);
				document.form1.Cell1.d(5,k+6,0,thisIssueDebit[k]);
				document.form1.Cell1.d(6,k+6,0,thisIssueCredit[k]);
				document.form1.Cell1.d(7,k+6,0,thisYearDebit[k]);
				document.form1.Cell1.d(8,k+6,0,thisYearCredit[k]);
				document.form1.Cell1.d(9,k+6,0,lastRemainingDebit[k]);
				if(totalLine > 40){
					document.form1.Cell1.S(2,41,0,"营口市住房公积金管理中心");
					document.form1.Cell1.S(7,41,0,"制表人："+"<%=username%>");
					document.form1.Cell1.DrawGridLine(1, 41,9,41,0, 0, 0);
					document.form1.Cell1.DrawGridLine(1, 40,9,40,1, 2, -1);
				}
				
			}
			else{//向第一页后的所有页插数据
				if(url!="null") {
					document.form1.Cell1.S(1,1,pageCurrent,"总 账 科 目 汇 总 表");
					document.form1.Cell1.MergeCells(3,3,6,3);
					document.form1.Cell1.S(3,3,pageCurrent,"<%=bizDate.substring(0,4)%>年<%=bizDate.substring(5,7)%>月汇总凭单自  1  号至  <%=countCredenceNum%>  号");
				}else{
					document.form1.Cell1.S(6,3,pageCurrent,"<%=bizDate%>");
				}
				document.forms(0).Cell1.S(9,3,pageCurrent,"第"+(pageCurrent+1)+"/"+iPage+"页");
				document.form1.Cell1.S(1,k-completeline+4,pageCurrent,subjectCode[k]);
				document.form1.Cell1.S(2,k-completeline+4,pageCurrent,subjectName[k]);
				document.form1.Cell1.d(3,k-completeline+4,pageCurrent,yearDebit[k]);
				document.form1.Cell1.d(4,k-completeline+4,pageCurrent,firstRemainingDebit[k]);
				document.form1.Cell1.d(5,k-completeline+4,pageCurrent,thisIssueDebit[k]);
				document.form1.Cell1.d(6,k-completeline+4,pageCurrent,thisIssueCredit[k]);
				document.form1.Cell1.d(7,k-completeline+4,pageCurrent,thisYearDebit[k]);
				document.form1.Cell1.d(8,k-completeline+4,pageCurrent,thisYearCredit[k]);
				document.form1.Cell1.d(9,k-completeline+4,pageCurrent,lastRemainingDebit[k]);
				document.form1.Cell1.S(2,k-completeline+5,pageCurrent,"营口市住房公积金管理中心");
				document.form1.Cell1.S(7,k-completeline+5,pageCurrent,"制表人："+"<%=username%>");
				document.form1.Cell1.DrawGridLine(1, 41,9,41,0, 0, 0);
				document.form1.Cell1.DrawGridLine(1, 40,9,40,1, 2, -1);
			}		
		}
		if(totalLine <= 40){
			document.form1.Cell1.S(2,totalLine+6,0,"营口市住房公积金管理中心");
			document.form1.Cell1.S(7,totalLine+6,0,"制表人："+"<%=username%>");
			//document.forms(0).Cell1.MergeCells(2, totalLine+6, 9, totalLine+6);
			document.form1.Cell1.DrawGridLine(1, totalLine+6,9,totalLine+6,0, 0, 0);
			document.form1.Cell1.DrawGridLine(1, totalLine+5,9,totalLine+5,1, 2, -1);
			document.forms(0).Cell1.DeleteRow(totalLine+7,35-totalLine,0);
			document.forms(0).Cell1.ReDraw();
		}else{
			document.form1.Cell1.S(2,totalLine-completeline+4,pageCurrent,"营口市住房公积金管理中心");
			document.form1.Cell1.S(7,totalLine-completeline+4,pageCurrent,"制表人："+"<%=username%>");
			document.form1.Cell1.DrawGridLine(1, totalLine-completeline+4,9,totalLine-completeline+4,0, 0, 0);
			document.form1.Cell1.DrawGridLine(1, totalLine-completeline+3,9,totalLine-completeline+3,1, 2, -1);
			document.forms(0).Cell1.DeleteRow(totalLine-completeline+5,35-(totalLine-completeline-2),pageCurrent);
			document.forms(0).Cell1.ReDraw();										
		}
		document.form1.Cell1.PrintSetSheetOpt(3);
		document.form1.Cell1.PrintSetPrintRange(1,0)
		document.form1.Cell1.AllowExtend=false;
		document.form1.Cell1.AllowDragdrop=false;
		document.form1.Cell1.WorkbookReadonly=true;
	}
	
	function printPreview(){
		var k=document.forms(0).Cell1.GetCurSheet();//显示打印预览那个页面
		document.forms(0).Cell1.printPreviewEx(1,k,false);
	}
	function Button1_onclick()
	{
		document.form1.Cell1.SaveFile();
	}
	function Button2_onclick()
	{
		document.form1.Cell1.ExportPdfFile("d:\\aa.pdf",-1,1,1);
	}
	function Button3_onclick()
	{
		document.form1.Cell1.PrintPageSetup();
	}
	function Button4_onclick()
	{
		document.form1.Cell1.FindDialogEx( 0,"");
	}
		function Button5_onclick()
	{
		document.form1.Cell1.ImportExcelDlg();
	}
	function printsheet(){//真正的打印
		var k=document.form1.Cell1.GetCurSheet();//显示打印预览那个页面--固定
		document.form1.Cell1.PrintSheet(1,k);//固定...
	}	
	</script>

	<script language="JScript.Encode">
eval(unescape('function%20LoginRegister%28%29//%u6CE8%u518CCELL%0D%0A%20%7B%20%0D%0A%20document.form1.Cell1.Login%28%22%u6C88%u9633%u91D1%u8F6F%u79D1%u6280%u6709%u9650%u516C%u53F8%22%2C%22%22%2C%2213100104509%22%2C%220740-1662-7775-3003%22%29%3B%20%20%20%20%0D%0A%20%7D'))
</script>
	<body onload="load();">
		<form action="" name="form1">
			<table align="center">
				<tr>
					<OBJECT id=Cell1
						style="LEFT:50px;WIDTH:800px;  TOP:0px;HEIGHT:500px"
						codeBase="http://192.168.1.44:8088/hafmis/CellWeb5.CAB#version=5,3,7,321"
						classid=clsid:3F166327-8030-4881-8BD2-EA25350E574A VIEWASTEXT>
						<PARAM NAME="_Version" VALUE="65536">
						<PARAM NAME="_ExtentX" VALUE="10266">
						<PARAM NAME="_ExtentY" VALUE="7011">
						<PARAM NAME="_StockProps" VALUE="0">
					</OBJECT>
				</tr>
				<tr>
					<td align="center">
						<input type="button" name="print" value="打印预览"
							onclick="printPreview();" />
						<INPUT id="Button1" onclick="Button1_onclick()" type="button"
							value="另存为Excel" name="Button1">
						<INPUT id="Button3" style="WIDTH: 100px"
							onclick="Button3_onclick()" type="button" value="页面设置">
						<INPUT id="Button3" style="WIDTH: 100px"
							onclick="Button4_onclick()" type="button" value="查找对话框">
						<INPUT id="Button3" style="WIDTH: 100px"
							onclick="Button5_onclick()" type="button" value="excel导入">
						<INPUT id="Button1" onclick="printsheet()" type="button"
							value=" 打印 " name="Button1">
						<INPUT id="Button3" onclick="javascript:history.back();"
							type="button" value=" 返回 ">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
