<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.util.List"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic"%>
<%@ page
	import="org.xpup.hafmis.syscollection.querystatistics.clearinterestinfo.yearclearstatistics.dto.*"%>
<%@ page import="org.xpup.hafmis.common.util.BusiTools"%>
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
	document.forms(0).Cell1.openfile("<%=path%>/syscollection/querystatistics/clearinterestinfo/yearclearstatistics/report/clearlist.cll","");

	var orgid=[];
	var orgname=[];
	var empcounts=[];
	var oldpreblance=[];
	var oldcurblance=[];
	var preinterest=[];
	var curinterest=[];
	var preblance=[];
	var curblance=[];
	var blance=[];
	var i=0;
	<%
			session = request.getSession();
    		List list=(List) session.getAttribute("listcount");
  			YearclearstatisticsHeadDTO dto=null;
  			for(int j=0;j<list.size();j++)
  			{
  				dto=(YearclearstatisticsHeadDTO)list.get(j);
  				String tempid = dto.getOrgcode().toString();
                String orgid = 	BusiTools.convertTenNumber(tempid);
 	%>	
				orgid[i]="<%=orgid%>";
				orgname[i]="<%=dto.getOrgname()%>";
				empcounts[i]=<%=dto.getEmpcounts()%>;
				oldpreblance[i]=<%=dto.getOldpreblance().add(dto.getOldcurblance())%>;
				oldcurblance[i]=<%=dto.getOldcurblance()%>;
				preinterest[i]=<%=dto.getPreinterest().add(dto.getCurinterest())%>;
				curinterest[i]=<%=dto.getCurinterest()%>;
				preblance[i]=<%=dto.getBlance()%>;
				curblance[i]=<%=dto.getCurblance()%>;
				blance[i]=<%=dto.getBlance()%>;
				i++;
 	<%
 			}
 	%>
 		var totalLine=orgname.length;			//总的行数
		var totalPageLine=10;					//每页显示多少行
		var pageTotal=totalLine/totalPageLine;	//总共分几页
		var pageCurrent=0;						//当前页
		var completeline=0;						//记录行的
		var emptotal=0;						    //总的合计-人数
		var oldpretotal=0;				        //总的合计-结息前往年余额
		var oldcurtotal=0;				        //总的合计-结息前本年余额
		var preinteresttotal=0;					//总的合计-往年利息
		var curinteresttotal=0;				    //总的合计-本年利息
		var preblancetotal=0;				    //总的合计-往年余额
		var curblancetotal=0;					//总的合计-本年余额
		var blancetotal=0;				        //总的合计-余额
		var iPage = getInt(totalLine,totalPageLine);
		for(k = 0 ; k < totalLine; k++){
			if(k%totalPageLine==0&&k>0)
			{
				document.forms(0).Cell1.S(6,1,pageCurrent,"第 "+(pageCurrent+1) + "/" + iPage+" 页");
				document.forms(0).Cell1.S(1,totalPageLine+4,pageCurrent,"本页小计");
				document.forms(0).Cell1.SetFormula (3, totalPageLine+4, pageCurrent, "Sum(C2:C"+(totalPageLine+3)+")" );
				document.forms(0).Cell1.SetFormula (4, totalPageLine+4, pageCurrent, "Sum(D2:D"+(totalPageLine+3)+")" );
				document.forms(0).Cell1.SetFormula (5, totalPageLine+4, pageCurrent, "Sum(E2:E"+(totalPageLine+3)+")" );
				document.forms(0).Cell1.SetFormula (6, totalPageLine+4, pageCurrent, "Sum(F2:F"+(totalPageLine+3)+")" );
				document.forms(0).Cell1.DeleteRow(14,1,pageCurrent);
				document.forms(0).Cell1.ReDraw();
				pageCurrent++;	
				completeline=k-2;				
				document.forms(0).Cell1.insertSheetFromFile("<%=path%>/syscollection/querystatistics/clearinterestinfo/yearclearstatistics/report/clearlist2.cll",0,1,pageCurrent);
				document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页")	
			    document.forms(0).Cell1.PrintSetCustomPaper(2400,1280,1);
			    document.forms(0).Cell1.PrintSetMargin(150,100,150,100);
			}
			if(pageCurrent==0)
			{
				document.forms(0).Cell1.S(1,k+4,0,orgid[k]);
				document.forms(0).Cell1.S(2,k+4,0,orgname[k]);
				document.forms(0).Cell1.d(3,k+4,0,empcounts[k]);
				document.forms(0).Cell1.d(4,k+4,0,oldpreblance[k]);
				//document.forms(0).Cell1.d(5,k+4,0,oldcurblance[k]);
				document.forms(0).Cell1.d(5,k+4,0,preinterest[k]);
				//document.forms(0).Cell1.d(7,k+4,0,curinterest[k]);
				document.forms(0).Cell1.d(6,k+4,0,preblance[k]);
				//document.forms(0).Cell1.d(9,k+4,0,curblance[k]);
				//document.forms(0).Cell1.d(10,k+4,0,blance[k]);
				
				emptotal=emptotal+parseFloat(empcounts[k]);
				oldpretotal=oldpretotal+parseFloat(oldpreblance[k]);
				oldcurtotal=oldcurtotal+parseFloat(oldcurblance[k]);
				preinteresttotal=preinteresttotal+parseFloat(preinterest[k]);
				curinteresttotal=curinteresttotal+parseFloat(curinterest[k]);
				preblancetotal=preblancetotal+parseFloat(preblance[k]);
				curblancetotal=curblancetotal+parseFloat(curblance[k]);
				blancetotal=blancetotal+parseFloat(blance[k]);
				
			}else{//向第一页后的所有页插数据
				document.forms(0).Cell1.S(1,k-completeline+2,pageCurrent,orgid[k]);
				document.forms(0).Cell1.S(2,k-completeline+2,pageCurrent,orgname[k]);
				document.forms(0).Cell1.d(3,k-completeline+2,pageCurrent,empcounts[k]);
				document.forms(0).Cell1.d(4,k-completeline+2,pageCurrent,oldpreblance[k]);
				//document.forms(0).Cell1.d(5,k-completeline+2,pageCurrent,oldcurblance[k]);
				document.forms(0).Cell1.d(5,k-completeline+2,pageCurrent,preinterest[k]);
				//document.forms(0).Cell1.d(7,k-completeline+2,pageCurrent,curinterest[k]);
				document.forms(0).Cell1.d(6,k-completeline+2,pageCurrent,preblance[k]);
				//document.forms(0).Cell1.d(9,k-completeline+2,pageCurrent,curblance[k]);
				//document.forms(0).Cell1.d(10,k-completeline+2,pageCurrent,blance[k]);
				
				emptotal=emptotal+parseFloat(empcounts[k]);
				oldpretotal=oldpretotal+parseFloat(oldpreblance[k]);
				oldcurtotal=oldcurtotal+parseFloat(oldcurblance[k]);
				preinteresttotal=preinteresttotal+parseFloat(preinterest[k]);
				curinteresttotal=curinteresttotal+parseFloat(curinterest[k]);
				preblancetotal=preblancetotal+parseFloat(preblance[k]);
				curblancetotal=curblancetotal+parseFloat(curblance[k]);
				blancetotal=blancetotal+parseFloat(blance[k]);
			}	
		}
		
				if(completeline==0)//查询出来的记录可以在一页显示的时候会进入
				{
					document.forms(0).Cell1.S(6,1,pageCurrent,"第 "+ iPage + "/" + iPage+" 页");
					document.forms(0).Cell1.S(1,totalLine+4,pageCurrent,"总计");
					document.forms(0).Cell1.d(3,totalLine+4,pageCurrent,emptotal);
					document.forms(0).Cell1.d(4,totalLine+4,pageCurrent,oldpretotal);
					document.forms(0).Cell1.d(5,totalLine+4,pageCurrent,oldcurtotal);
					document.forms(0).Cell1.d(6,totalLine+4,pageCurrent,preinteresttotal);
					document.forms(0).Cell1.DeleteRow(totalLine+5,totalPageLine-(totalLine),pageCurrent);
					document.forms(0).Cell1.ReDraw();
				}	
								
				else
				{
					document.forms(0).Cell1.S(6,1,pageCurrent,"第 "+ iPage + "/" + iPage+" 页");
					document.forms(0).Cell1.S(1,totalLine-completeline+2,pageCurrent,"总计");
					document.forms(0).Cell1.d(3,totalLine-completeline+2,pageCurrent,emptotal);
					document.forms(0).Cell1.d(4,totalLine-completeline+2,pageCurrent,oldpretotal);
					document.forms(0).Cell1.d(5,totalLine-completeline+2,pageCurrent,oldcurtotal);
					document.forms(0).Cell1.d(6,totalLine-completeline+2,pageCurrent,preinteresttotal);
					document.forms(0).Cell1.DeleteRow(totalLine-completeline+3,totalPageLine-(totalLine-completeline-2),pageCurrent);
					document.forms(0).Cell1.ReDraw();
				}
					
				document.forms(0).Cell1.AllowExtend=false;
				document.forms(0).Cell1.AllowDragdrop=false;
				document.forms(0).Cell1.WorkbookReadonly=true;	
				document.forms(0).Cell1.PrintSetSheetOpt(3);
				document.forms(0).Cell1.PrintSetPrintRange(1,0);

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
					<INPUT id="Button1" onclick="printsheet()" type="button"
						value=" 打印 " name="Button1">
					<INPUT id="Button3" onclick="javascript:history.back();"
						type="button" value=" 返回 ">
				</tr>
			</table>
		</form>
	</body>
</html>
