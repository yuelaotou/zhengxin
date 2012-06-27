<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.util.List"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic"%>
<%@ page
	import="org.xpup.hafmis.sysloan.loanapply.loanapply.dto.LoanapplyTeListDTO"%>
<%@ include file="/checkUrl.jsp"%>
<%
String path = request.getContextPath();
%>
<html>
	<head>
	</head>
	<script src="<%=path%>/js/common.js">
</script>
	<script type="text/javascript">
function load(){	
	//加载默版文件..
loginReg();	
	document.forms(0).Cell1.openfile("<%=path%>/sysloan/loanapply/report/loanapplyall.cll","");
	var contranctid=[];
	var empid=[];
	var borrowername=[];
	var cardnum=[];
	var housestype=[];
	var loanmoney=[];
	var loanlimit=[];
	var contranctst=[];

	var i=0;
	
	<%
			String optername=(String)request.getAttribute("opertname");
			String time=(String)request.getAttribute("time");
    		List list=(List) request.getAttribute("cellList");
  			LoanapplyTeListDTO lDto=new LoanapplyTeListDTO();
  			for(int j=0;j<list.size();j++)
  			{
  				lDto=(LoanapplyTeListDTO)list.get(j);
 	%>		
				contranctid[i]="<%=lDto.getContractid()%>";
				empid[i]="<%=lDto.getEmpid()%>";
				if("<%=lDto.getEmpid()%>"!=""){
					empid[i]=format("<%=lDto.getEmpid()%>")+"<%=lDto.getEmpid()%>";				
				}
				borrowername[i]="<%=lDto.getBorrowername()%>";
				cardnum[i]="<%=lDto.getCardnum()%>";
				housestype[i]="<%=lDto.getBuyhousetype()%>";
				loanmoney[i]="<%=lDto.getLoanmoney()%>";
				loanlimit[i]="<%=lDto.getLoanlimit()%>";
				contranctst[i]="<%=lDto.getContract_st()%>";
			
				i++;
 	<%
 			}
 	%>
 	
 	
		var totalLine=contranctid.length;			//总的行数
		
		var totalPageLine=40;					//每页显示多少行
		var pageTotal=totalLine/totalPageLine;	//总共分几页
		var pageCurrent=0;						//当前页  如果没有头表就设置成0
		var completeline=0;						//记录行的
		var moneytotal1=0;
				
		
		for(k = 0 ; k < totalLine; k++){
			if(k%totalPageLine==0&&k>0)
			{//插入新页.对上一页求和的完成
				document.forms(0).Cell1.S(1,totalPageLine+4,pageCurrent,"本页小计");
				document.forms(0).Cell1.SetFormula (6, totalPageLine+4, pageCurrent, "Sum(F2:F"+(totalPageLine+3)+")" );							
				document.forms(0).Cell1.DeleteRow(totalPageLine+5,46-totalPageLine-4,pageCurrent);
				document.forms(0).Cell1.DeleteRow(50,6,pageCurrent);
				pageCurrent++;
				completeline=k-2;
							
				document.forms(0).Cell1.insertSheetFromFile("<%=path%>/sysloan/loanapply/report/loanapplyall.cll",0,1,pageCurrent);
				document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页")	
			}
			if(pageCurrent==0)
			{			
				document.forms(0).Cell1.S(1,k+4,0,contranctid[k]);
				document.forms(0).Cell1.S(2,k+4,0,empid[k]);
				document.forms(0).Cell1.S(3,k+4,0,borrowername[k]);
				document.forms(0).Cell1.S(4,k+4,0,cardnum[k]);
				document.forms(0).Cell1.S(5,k+4,0,housestype[k]);
				document.forms(0).Cell1.d(6,k+4,0,loanmoney[k]);
				document.forms(0).Cell1.S(7,k+4,0,loanlimit[k]);
				document.forms(0).Cell1.S(8,k+4,0,contranctst[k]);

				moneytotal1=moneytotal1+parseFloat(loanmoney[k]);
			}else{
				
				document.forms(0).Cell1.S(1,k-completeline+2,pageCurrent,contranctid[k]);
				document.forms(0).Cell1.S(2,k-completeline+2,pageCurrent,empid[k]);
				document.forms(0).Cell1.S(3,k-completeline+2,pageCurrent,borrowername[k]);
				document.forms(0).Cell1.S(4,k-completeline+2,pageCurrent,cardnum[k]);
				document.forms(0).Cell1.S(5,k-completeline+2,pageCurrent,housestype[k]);
				document.forms(0).Cell1.d(6,k-completeline+2,pageCurrent,loanmoney[k]);
				document.forms(0).Cell1.S(7,k-completeline+2,pageCurrent,loanlimit[k]);
				document.forms(0).Cell1.S(8,k-completeline+2,pageCurrent,contranctst[k]);
				moneytotal1=moneytotal1+parseFloat(loanmoney[k]);
			
			}	
		}
		
		if(completeline==0){
			document.forms(0).Cell1.S(1,totalLine+4,pageCurrent,"小计");
			document.forms(0).Cell1.SetFormula (6,totalLine+4, pageCurrent, "Sum(F2:F"+(totalLine+3)+")" );
			document.forms(0).Cell1.S(1,totalLine+5,pageCurrent,"总计");
			document.forms(0).Cell1.SetFormula (6, totalLine+5, pageCurrent, "Sum(F2:F"+(totalLine+3)+")" );
		document.forms(0).Cell1.S(5,totalLine+6,pageCurrent,"操作员");
		document.forms(0).Cell1.S(6,totalLine+6,pageCurrent,"<%=optername%>");
		document.forms(0).Cell1.S(7,totalLine+6,pageCurrent,"操作时间");
		document.forms(0).Cell1.S(8,totalLine+6,pageCurrent,"<%=time%>");
			document.forms(0).Cell1.DeleteRow(totalLine+7,50-totalLine-6,pageCurrent);
		}else{
			document.forms(0).Cell1.S(1,totalLine-completeline+2,pageCurrent,"本页小计");
			document.forms(0).Cell1.SetFormula (6, totalLine-completeline+2, pageCurrent, "Sum(F2:F"+(totalLine-(completeline+1))+")" );
			document.forms(0).Cell1.S(1,totalLine-completeline+3,pageCurrent,"总计");
			document.forms(0).Cell1.d(6, totalLine-completeline+3, pageCurrent,moneytotal1);
			
		document.forms(0).Cell1.S(5,totalLine-completeline+4,pageCurrent,"操作员");
		document.forms(0).Cell1.S(6,totalLine-completeline+4,pageCurrent,"<%=optername%>");
		document.forms(0).Cell1.S(7,totalLine-completeline+4,pageCurrent,"操作时间");
		document.forms(0).Cell1.S(8,totalLine-completeline+4,pageCurrent,"<%=time%>");
			document.forms(0).Cell1.DeleteRow(totalLine+7,50-totalLine-6,pageCurrent);
		}	
		document.forms(0).Cell1.AllowExtend=false;
		document.forms(0).Cell1.AllowDragdrop=false;
		document.forms(0).Cell1.WorkbookReadonly=true;	
}



 	function printPreview(){
		var k=document.forms(0).Cell1.GetCurSheet();
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
	<body onContextmenu="return false" onload="load();">
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
						<INPUT id="Button1" onclick="Button1_onclick()" type="button"
							value="另存为Excel" name="Button1" />
					</td>
					<td>
						<INPUT id="Button1" onclick="Button2_onclick()" type="button"
							value="另存为pdf" name="Button1" />
					</td>
					<td>
						<INPUT id="Button3" style="WIDTH: 100px"
							onclick="Button3_onclick()" type="button" value="页面设置" />
					</td>
					<td>
						<INPUT id="Button3" style="WIDTH: 100px"
							onclick="Button4_onclick()" type="button" value="查找对话框" />
					</td>
					<td>
						<INPUT id="Button3" style="WIDTH: 100px"
							onclick="Button5_onclick()" type="button" value="excel导入" />
					</td>
					<td>
						<INPUT id="Button1" onclick="printsheet()" type="button"
							value=" 打印 " name="Button1" />
					</td>
					<td>
					<INPUT id="Button3" onclick="javascript:history.back();"
							type="button" value=" 返回 ">
					</td>
			</table>
		</form>
	</body>
</html>
