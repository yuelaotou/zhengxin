<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.util.List"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic"%>
<%@ page import="org.xpup.hafmis.syscollection.paymng.monthpay.dto.*"%>
<%@ page import="org.xpup.hafmis.syscollection.paymng.monthpay.form.MonthpayJYAF"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	String url = (String) request.getSession().getAttribute("URL");
	if (url == null || "".equals(url)) {
		url = "javascript:history.back();";
	} else {
		url = path + url;
	}
%>
<html>
	<head>
		<script src="<%=path%>/js/common.js">
</script>
	</head>
	<script type="text/javascript">
	function load(){	

	loginReg();
	document.forms(0).Cell1.openfile("<%=path%>/syscollection/paymng/report/pay_wsh.cll","");
	document.forms(0).Cell1.setSheetLabel(0,"第1页");//这句话必须放在加载文件的下边
	<%
    		MonthpayJYAF monthpayJYAF=(MonthpayJYAF)request.getAttribute("monthpayJYAF");
    		String m=monthpayJYAF.getSumPay().toString();
	%>
		
		var sumPay = "<%=monthpayJYAF.getSumPay()%>";
		var payWay = "<%=monthpayJYAF.getPayWay()%>"
		var payKind = "<%=monthpayJYAF.getPayKind()%>";
		var noteNum = "<%=monthpayJYAF.getNoteNum()%>";
		if(noteNum=="null")
		{
		  noteNum="";
		}

		
		document.forms(0).Cell1.S(51,1,0,noteNum);
        
		var idlist=[];
		var namelist=[];
		var pyamonth=[];
		var orgpay=[];
		var emppay=[];
		var sumpay=[];
		var status=[];
		var i=0;
		<%
    		List list=(List) request.getSession().getAttribute("cellList");
    		String bizDate=(String) request.getSession().getAttribute("bizDate");
    		String userName=(String) request.getSession().getAttribute("userName");
    		String collectionBankName=(String) request.getSession().getAttribute("collectionBankName");
  			MonthpayTbWindowDto dto=null;
  			for(int j=0;j<list.size();j++)
  			{
  				dto=(MonthpayTbWindowDto)list.get(j);	
 		%>		
				idlist[i]=format("<%=dto.getEmpid()%>")+"<%=dto.getEmpid()%>";
				namelist[i]="<%=dto.getEmpName()%>";
				pyamonth[i]="<%=dto.getPayMonth()%>";
				orgpay[i]=<%=dto.getOrgpay()%>;
				emppay[i]=<%=dto.getEmppay()%>;
				sumpay[i]=<%=dto.getSumpay()%>;
				status[i]="<%=dto.getEmpStatus()%>";
				i++;
 		<%
 			}
 		%>
		var totalLine=namelist.length;			//总的行数
		var totalPageLine=12;					//每页显示多少行
		var pageTotal=totalLine/totalPageLine;	//总共分几页
		var pageCurrent=1;						//当前页
		var completeline=0;						//记录行的
		var moneytotal=0;	
		var orgsum=0;
		var empsum=0;
		//总的合计
		document.forms(0).Cell1.insertSheetFromFile("<%=path%>/syscollection/paymng/report/orgaddpaylist.cll",0,1,pageCurrent);
		document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页");
		for(var k = 0 ; k < totalLine; k++){
			if(k%totalPageLine==0&&k>0)
			{
				document.forms(0).Cell1.S(1,16,pageCurrent,"本页小计");
				document.forms(0).Cell1.SetFormula (4, 16, pageCurrent, "Sum(D2:D"+(totalPageLine+3)+")" );
				document.forms(0).Cell1.SetFormula (5, 16, pageCurrent, "Sum(E2:E"+(totalPageLine+3)+")" );
				document.forms(0).Cell1.SetFormula (6, 16, pageCurrent, "Sum(F2:F"+(totalPageLine+3)+")" );
				
				document.forms(0).Cell1.S(1,17,pageCurrent,"归集银行:");
				document.forms(0).Cell1.S(2,17,pageCurrent,"<%=collectionBankName%>");
				document.forms(0).Cell1.MergeCells(2, 17, 3, 17);
				document.forms(0).Cell1.S(4,17,pageCurrent,"制表人:");				
			    document.forms(0).Cell1.S(5,17,pageCurrent,"<%=userName%>");
				document.forms(0).Cell1.S(6,17,pageCurrent,"操作日期:");
				document.forms(0).Cell1.S(7,17,pageCurrent,"<%=bizDate%>");
				document.forms(0).Cell1.ClearGridLine(1,17, 7, 17, pageCurrent);
				document.forms(0).Cell1.DrawGridLine(1, 16, 7, 16, pageCurrent, 2, -1);
				document.forms(0).Cell1.DeleteRow(18,1,pageCurrent);
				document.forms(0).Cell1.ReDraw();
				
				pageCurrent++;	
				completeline=k-2;				
				document.forms(0).Cell1.insertSheetFromFile("<%=path%>/syscollection/paymng/report/orgaddpaylist.cll",0,1,pageCurrent);
				document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页");
				document.forms(0).Cell1.PrintSetCustomPaper(2400,1200,1);
				document.forms(0).Cell1.PrintSetMargin(180,200,180,320);
				document.forms(0).Cell1.PrintSetAlign(1,0);
			}
			if(pageCurrent==1)
			{   
			    document.forms(0).Cell1.S(2,2,1,"<%=dto.getOrgname()%>");
			    document.forms(0).Cell1.S(6,2,1,formatTen("<%=dto.getOrgid()%>")+"<%=dto.getOrgid()%>");
				document.forms(0).Cell1.S(1,k+4,1,idlist[k]);
				document.forms(0).Cell1.S(2,k+4,1,namelist[k]);
				document.forms(0).Cell1.S(3,k+4,1,pyamonth[k]);
				document.forms(0).Cell1.d(4,k+4,1,orgpay[k]);
				document.forms(0).Cell1.d(5,k+4,1,emppay[k]);
				document.forms(0).Cell1.d(6,k+4,1,sumpay[k]);
				document.forms(0).Cell1.s(7,k+4,1,status[k]);
				orgsum=orgsum+parseFloat(orgpay[k]);
				empsum=empsum+parseFloat(emppay[k]);
				moneytotal=moneytotal+parseFloat(sumpay[k]);
			}else{//向第一页后的所有页插数据
			    document.forms(0).Cell1.S(2,2,pageCurrent,"<%=dto.getOrgname()%>");
			    document.forms(0).Cell1.S(6,2,pageCurrent,formatTen("<%=dto.getOrgid()%>")+"<%=dto.getOrgid()%>");
				document.forms(0).Cell1.S(1,k-completeline+2,pageCurrent,idlist[k]);
				document.forms(0).Cell1.S(2,k-completeline+2,pageCurrent,namelist[k]);
				document.forms(0).Cell1.S(3,k-completeline+2,pageCurrent,pyamonth[k]);
				document.forms(0).Cell1.d(4,k-completeline+2,pageCurrent,orgpay[k]);
				document.forms(0).Cell1.d(5,k-completeline+2,pageCurrent,emppay[k]);
				document.forms(0).Cell1.d(6,k-completeline+2,pageCurrent,sumpay[k]);
				document.forms(0).Cell1.s(7,k-completeline+2,pageCurrent,status[k]);
				orgsum=orgsum+parseFloat(orgpay[k]);
				empsum=empsum+parseFloat(emppay[k]);
				moneytotal=moneytotal+parseFloat(sumpay[k]);
			}
		}
		
		if(completeline==0)//查询出来的记录可以在一页显示的时候会进入
		{			
			document.forms(0).Cell1.S(1,totalLine+4,pageCurrent,"本页小计");
			document.forms(0).Cell1.SetFormula (4, totalLine+4, pageCurrent, "Sum(D2:D"+(totalLine+3)+")" );//,loopcell() > 5
			document.forms(0).Cell1.SetFormula (5, totalLine+4, pageCurrent, "Sum(E2:E"+(totalLine+3)+")" );//,loopcell() > 5
			document.forms(0).Cell1.SetFormula (6, totalLine+4, pageCurrent, "Sum(F2:F"+(totalLine+3)+")" );//,loopcell() > 5
			document.forms(0).Cell1.S(1,totalLine+5,pageCurrent,"总计");
			document.forms(0).Cell1.d(4,totalLine+5,pageCurrent,orgsum);
			document.forms(0).Cell1.d(5,totalLine+5,pageCurrent,empsum);
			document.forms(0).Cell1.d(6,totalLine+5,pageCurrent,moneytotal);
			
			
			document.forms(0).Cell1.S(1,totalLine+6,pageCurrent,"归集银行:");
			document.forms(0).Cell1.S(2,totalLine+6,pageCurrent,"<%=collectionBankName%>");
			document.forms(0).Cell1.MergeCells(2, totalLine+6, 3, totalLine+6);
			document.forms(0).Cell1.S(4,totalLine+6,pageCurrent,"制表人:");				
		    document.forms(0).Cell1.S(5,totalLine+6,pageCurrent,"<%=userName%>");
			document.forms(0).Cell1.S(6,totalLine+6,pageCurrent,"操作日期:");
			document.forms(0).Cell1.S(7,totalLine+6,pageCurrent,"<%=bizDate%>");				

			document.forms(0).Cell1.ClearGridLine(1,totalLine+6, 7, totalLine+6, 0);
			document.forms(0).Cell1.DrawGridLine(1, totalLine+5, 7, totalLine+5, 0, 2, -1);
			
			document.forms(0).Cell1.DeleteRow(totalLine+7,12-totalLine,pageCurrent);
      		document.forms(0).Cell1.ReDraw();
		}else{
			document.forms(0).Cell1.S(1,totalLine-completeline+2,pageCurrent,"本页小计");
			document.forms(0).Cell1.SetFormula (4, totalLine-completeline+2, pageCurrent, "Sum(D2:D"+(totalLine-(completeline-1))+")" );
			document.forms(0).Cell1.SetFormula (5, totalLine-completeline+2, pageCurrent, "Sum(E2:E"+(totalLine-(completeline-1))+")" );
			document.forms(0).Cell1.SetFormula (6, totalLine-completeline+2, pageCurrent, "Sum(F2:F"+(totalLine-(completeline-1))+")" );
			document.forms(0).Cell1.S(1,totalLine-completeline+3,pageCurrent,"总计");
			document.forms(0).Cell1.d(4,totalLine-completeline+3,pageCurrent,orgsum);
			document.forms(0).Cell1.d(5,totalLine-completeline+3,pageCurrent,empsum);
			document.forms(0).Cell1.d(6,totalLine-completeline+3,pageCurrent,moneytotal);
			
			document.forms(0).Cell1.S(1,totalLine-completeline+4,pageCurrent,"归集银行:");
			document.forms(0).Cell1.S(2,totalLine-completeline+4,pageCurrent,"<%=collectionBankName%>");
			document.forms(0).Cell1.MergeCells(2, totalLine-completeline+4, 3, totalLine-completeline+4);
			document.forms(0).Cell1.S(4,totalLine-completeline+4,pageCurrent,"制表人:");				
		    document.forms(0).Cell1.S(5,totalLine-completeline+4,pageCurrent,"<%=userName%>");
			document.forms(0).Cell1.S(6,totalLine-completeline+4,pageCurrent,"操作日期:");
			document.forms(0).Cell1.S(7,totalLine-completeline+4,pageCurrent,"<%=bizDate%>");
			
			document.forms(0).Cell1.ClearGridLine(1,totalLine-completeline+4, 7, totalLine-completeline+4, 0);
			document.forms(0).Cell1.DrawGridLine(1, totalLine-completeline+3, 7, totalLine-completeline+3, 0, 2, -1);
			
			document.forms(0).Cell1.DeleteRow(totalLine-completeline+5,18-(totalLine-completeline+4),pageCurrent);
			document.forms(0).Cell1.ReDraw();
		}
	
		document.forms(0).Cell1.AllowExtend=false;
		document.forms(0).Cell1.AllowDragdrop=false;
		
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
					<td>
						<input type="button" name="print" value="打印预览"
							onclick="printPreview();" />
					</td>
					<td>
						<INPUT id="Button1" onclick="printsheet()" type="button"
							value=" 打印 " name="Button1">
					</td>
					<td>
						<INPUT id="Button1" onclick="Button1_onclick()" type="button"
							value="另存为Excel" name="Button1">
					</td>
					<td>
						<INPUT id="Button1" onclick="Button2_onclick()" type="button"
							value="另存为pdf" name="Button1">
					</td>
					<td>
						<INPUT id="Button3" style="WIDTH: 100px"
							onclick="Button3_onclick()" type="button" value="页面设置">
					</td>
					<td>
						<INPUT id="Button3" style="WIDTH: 100px"
							onclick="Button4_onclick()" type="button" value="查找对话框">
					</td>
					<td>
						<INPUT id="Button3" style="WIDTH: 100px"
							onclick="Button5_onclick()" type="button" value="excel导入">
					</td>
					<td>
						<INPUT id="Button3" onclick="location.href('<%=url%>')"
							type="button" value=" 返回 ">
					</td>
			</table>
		</form>
	</body>
</html>
