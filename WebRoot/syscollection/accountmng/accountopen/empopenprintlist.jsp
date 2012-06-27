<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.List" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic" %>
<%@ page import="org.xpup.hafmis.syscollection.accountmng.accountopen.dto.*" %>
<%@ include file="/checkUrl.jsp"%>
<%@ page import="java.math.BigDecimal"%>
<%
   String path=request.getContextPath();
 %>
<html>
  <head>
  <script src="<%=path%>/js/common.js">
</script>
  <script type="text/javascript">
	function load(){	

	loginReg();
	document.forms(0).Cell1.openfile("<%=path%>/syscollection/accountmng/accountopen/report/empopenmingxi.cll","");
	var empid=[];
	var empname=[];
	var cardnum=[];
	var salary=[];
	var orgpay=[];
	var emppay=[];
	var sumpay=[];
	var empagentnum=[];
	var opendate=[];
	var orgid=[];
	var orgname=[];
	var i=0;
	
	var id_count=[];
	var sex=[];
	var TRANSACTOR_NAME=[];
	var ARTIFICIAL_PERSON=[];
	<%		
			String bizdate=(String)request.getAttribute("bizdate");
			String printperson=(String)request.getAttribute("printperson");
			String collbank=(String)request.getAttribute("collbank");
    		List list=(List) request.getAttribute("printlist");
  			PrintListDTO dto=null;
  			
  			for(int j=0;j<list.size();j++)
  			{
  				dto=(PrintListDTO)list.get(j);
  								
  				
 	%>			
				empid[i]="<%=dto.getEmpid()%>";
				empname[i]="<%=dto.getEmpname()%>";
				cardnum[i]="<%=dto.getCardnum()%>";
				salary[i]="<%=dto.getSalarybase()%>";
				orgpay[i]="<%=dto.getOrgpay()%>";
				emppay[i]="<%=dto.getEmppay()%>";
				sumpay[i]="<%=dto.getSumpay()%>";
				empagentnum[i]="<%=dto.getEmpagentnum()%>";
				opendate[i]="<%=dto.getOpendate()%>";
				orgid[i]="<%=dto.getOrgid()%>";
				orgname[i]="<%=dto.getOrgname()%>";
				id_count[i]="<%=dto.getId_count()%>";
				sex[i]="<%=dto.getSex()%>";
				TRANSACTOR_NAME[i]="<%=dto.getTRANSACTOR_NAME()%>";
				ARTIFICIAL_PERSON[i]="<%=dto.getARTIFICIAL_PERSON()%>";
				i++;
				
 	<%
 			}
 	%>								
		var totalLine=empid.length;			//总的行数 数组的长度
		var totalPageLine=40;					//每页显示多少行
		var pageTotal=totalLine/totalPageLine;	//总共分几页
		var pageCurrent=0;						//当前页
		var completeline=0;						//记录行的
		var orgsum=0;                           //单位合计
		var empsum=0;                           //职工合计
		var salarysum=0;						//工资基数合计
		var moneytotal=0;						//总的合计
		var iPage = getInt(totalLine,totalPageLine);
		var icount = 1;
		for(k = 0 ; k < totalLine; k++){
			if(k%totalPageLine==0&&k>0){   		
				document.forms(0).Cell1.S(1,totalPageLine+5,pageCurrent,"本页小计");
				document.forms(0).Cell1.SetFormula (5, totalPageLine+5, pageCurrent, "Sum(E5:E"+(totalPageLine+4)+")" );
				document.forms(0).Cell1.SetFormula (6, totalPageLine+5, pageCurrent, "Sum(F5:F"+(totalPageLine+4)+")" );
				document.forms(0).Cell1.SetFormula (7, totalPageLine+5, pageCurrent, "Sum(G5:G"+(totalPageLine+4)+")" );
				document.forms(0).Cell1.SetFormula (8, totalPageLine+5, pageCurrent, "Sum(H5:H"+(totalPageLine+4)+")" );
				document.forms(0).Cell1.ReDraw();
				pageCurrent++;	
				icount++;
				completeline=k-2;																			
				document.forms(0).Cell1.insertSheetFromFile("<%=path%>/syscollection/accountmng/accountopen/report/empopenmingxi2.cll",0,1,pageCurrent);
				document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页")
				
			}
			if(pageCurrent==0){
				document.forms(0).Cell1.S(2,2,0,orgid[k]);
				document.forms(0).Cell1.S(4,2,0,orgname[k]);
				document.forms(0).Cell1.S(6,2,0,"<%=collbank%>");
				document.forms(0).Cell1.S(8,2,0,"共"+iPage+"页/第"+icount+"页");
				document.forms(0).Cell1.S(1,k+5,0,id_count[k]);
				document.forms(0).Cell1.S(2,k+5,0,empname[k]);
				document.forms(0).Cell1.S(3,k+5,0,sex[k]);
				document.forms(0).Cell1.S(4,k+5,0,cardnum[k]);
				document.forms(0).Cell1.d(5,k+5,0,salary[k]);
				document.forms(0).Cell1.d(6,k+5,0,sumpay[k]);
				document.forms(0).Cell1.d(7,k+5,0,emppay[k]);
				document.forms(0).Cell1.d(8,k+5,0,orgpay[k]);
			//	                         
				document.forms(0).Cell1.S(2,46,0,"单位负责人:");
				document.forms(0).Cell1.S(3,46,0,ARTIFICIAL_PERSON[k]);
				document.forms(0).Cell1.S(4,46,0,"财务主管："+TRANSACTOR_NAME[k]);
				document.forms(0).Cell1.S(5,46,0,"制表人：");
				document.forms(0).Cell1.S(6,46,0,"<%=printperson%>");
				document.forms(0).Cell1.S(7,46,0,"操作日期:");
				document.forms(0).Cell1.S(8,46,0,"<%=bizdate%>");
				
				salarysum=salarysum+parseFloat(salary[k]);
				orgsum=orgsum+parseFloat(orgpay[k]);
				empsum=empsum+parseFloat(emppay[k]);
				moneytotal=moneytotal+parseFloat(sumpay[k]);
			}else{//向第一页后的所有页插数据		
				document.forms(0).Cell1.S(2,2,pageCurrent,orgid[k]);
				document.forms(0).Cell1.S(4,2,pageCurrent,orgname[k]);
				document.forms(0).Cell1.S(6,2,pageCurrent,"<%=collbank%>");
				document.forms(0).Cell1.S(8,2,pageCurrent,"共"+iPage+"页/第"+icount+"页");
				document.forms(0).Cell1.S(1,k-completeline+3,pageCurrent,id_count[k]);
				document.forms(0).Cell1.S(2,k-completeline+3,pageCurrent,empname[k]);
				document.forms(0).Cell1.S(3,k-completeline+3,pageCurrent,sex[k]);
				document.forms(0).Cell1.S(4,k-completeline+3,pageCurrent,cardnum[k]);
				document.forms(0).Cell1.d(5,k-completeline+3,pageCurrent,salary[k]);
				document.forms(0).Cell1.d(6,k-completeline+3,pageCurrent,sumpay[k]);
				document.forms(0).Cell1.d(7,k-completeline+3,pageCurrent,emppay[k]);
				document.forms(0).Cell1.d(8,k-completeline+3,pageCurrent,orgpay[k]);
				
				document.forms(0).Cell1.S(2,46,pageCurrent,"单位负责人:");
				document.forms(0).Cell1.S(3,46,pageCurrent,ARTIFICIAL_PERSON[k]);
				document.forms(0).Cell1.S(4,46,pageCurrent,"财务主管："+TRANSACTOR_NAME[k]);
				document.forms(0).Cell1.S(5,46,pageCurrent,"制表人：");
		    	document.forms(0).Cell1.S(6,46,pageCurrent,"<%=printperson%>");
		    	document.forms(0).Cell1.S(7,46,pageCurrent,"操作日期:");
		    	document.forms(0).Cell1.S(8,46,pageCurrent,"<%=bizdate%>");
				salarysum=salarysum+parseFloat(salary[k]);
				orgsum=orgsum+parseFloat(orgpay[k]);
				empsum=empsum+parseFloat(emppay[k]);
				moneytotal=moneytotal+parseFloat(sumpay[k]);
				
			}		
			
		}
		
		
		
		
	
				if(completeline==0)//查询出来的记录可以在一页显示的时候会进入
				{
					document.forms(0).Cell1.S(1,totalLine+5,pageCurrent,"本页小计");
					document.forms(0).Cell1.SetFormula (5, totalLine+5, pageCurrent, "Sum(E5:E"+(totalLine+4)+")" );
					document.forms(0).Cell1.SetFormula (6, totalLine+5, pageCurrent, "Sum(F5:F"+(totalLine+4)+")" );
					document.forms(0).Cell1.SetFormula (7, totalLine+5, pageCurrent, "Sum(G5:G"+(totalLine+4)+")" );
					document.forms(0).Cell1.SetFormula (8, totalLine+5, pageCurrent, "Sum(H5:H"+(totalLine+4)+")" );
					document.forms(0).Cell1.S(1,totalLine+6,pageCurrent,"总计");
					document.forms(0).Cell1.d(5,totalLine+6,pageCurrent,salarysum);
					document.forms(0).Cell1.d(6,totalLine+6,pageCurrent,moneytotal);
					document.forms(0).Cell1.d(7,totalLine+6,pageCurrent,empsum);
					document.forms(0).Cell1.d(8,totalLine+6,pageCurrent,orgsum);
				//	 document.forms(0).Cell1.MergeCells(2,totalLine+7,3,totalLine+7);
				//	 document.forms(0).Cell1.ReDraw();
				//	document.forms(0).Cell1.S(1,totalLine+7,pageCurrent,"归集银行");
				//	document.forms(0).Cell1.S(2,totalLine+7,pageCurrent,"<%=collbank%>");
				//	document.forms(0).Cell1.S(5,totalLine+7,pageCurrent,"制表人");
				//	document.forms(0).Cell1.S(6,totalLine+7,pageCurrent,"<%=printperson%>");
				//	document.forms(0).Cell1.S(1,totalLine+8,pageCurrent,"操作日期");
				//	document.forms(0).Cell1.S(2,totalLine+8,pageCurrent,"<%=bizdate%>");
//					document.forms(0).Cell1.DrawGridLine(1,totalLine+6,8,totalLine+6,2);
//					document.forms(0).Cell1.DrawGridLine(1,totalLine+6,8,totalLine+6,3);
//					document.forms(0).Cell1.DrawGridLine(1,totalLine+6,8,totalLine+6,5);
//					document.forms(0).Cell1.ReDraw();				
					document.forms(0).Cell1.DeleteRow(totalLine+8,totalPageLine-(totalLine+8),pageCurrent);
					document.forms(0).Cell1.ReDraw();
				}
				else
				{
				    document.forms(0).Cell1.S(1,totalLine-completeline+3,pageCurrent,"本页小计");
					document.forms(0).Cell1.SetFormula (5, totalLine-completeline+3, pageCurrent, "Sum(E5:E"+(totalLine-(completeline-2))+")" );
					document.forms(0).Cell1.SetFormula (6, totalLine-completeline+3, pageCurrent, "Sum(F5:F"+(totalLine-(completeline-2))+")" );
					document.forms(0).Cell1.SetFormula (7, totalLine-completeline+3, pageCurrent, "Sum(G5:G"+(totalLine-(completeline-2))+")" );
					document.forms(0).Cell1.SetFormula (8, totalLine-completeline+3, pageCurrent, "Sum(H5:H"+(totalLine-(completeline-2))+")" );
					document.forms(0).Cell1.S(1,totalLine-completeline+4,pageCurrent,"总计");
					document.forms(0).Cell1.d(5,totalLine-completeline+4,pageCurrent,salarysum);
					document.forms(0).Cell1.d(6,totalLine-completeline+4,pageCurrent,moneytotal);
					document.forms(0).Cell1.d(7,totalLine-completeline+4,pageCurrent,empsum);
				    document.forms(0).Cell1.d(8,totalLine-completeline+4,pageCurrent,orgsum);
				//	document.forms(0).Cell1.MergeCells(2,totalLine+6,3,totalLine+6);
				//	document.forms(0).Cell1.ReDraw();
				//	document.forms(0).Cell1.S(1,totalLine-completeline+5,pageCurrent,"归集银行");
				//	document.forms(0).Cell1.S(2,totalLine-completeline+5,pageCurrent,"<%=collbank%>");
				//	document.forms(0).Cell1.S(5,totalLine-completeline+5,pageCurrent,"制表人");
				//	document.forms(0).Cell1.S(6,totalLine-completeline+5,pageCurrent,"<%=printperson%>");
				//	document.forms(0).Cell1.S(1,totalLine-completeline+6,pageCurrent,"操作日期");
				//	document.forms(0).Cell1.S(2,totalLine-completeline+6,pageCurrent,"<%=bizdate%>");
					document.forms(0).Cell1.DeleteRow(totalLine-completeline+6,totalPageLine-(totalLine-completeline+6),pageCurrent);
					document.forms(0).Cell1.ReDraw();
					
				}	
}
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
<tr>
<td><input type="button" name="print" value = "打印预览" onclick = "printPreview();"/></td>
<td><INPUT id="Button1" onclick="printsheet()" type="button" value=" 打印 " name="Button1"></td>
<td><INPUT id="Button1" onclick="Button1_onclick()" type="button" value="另存为Excel" name="Button1"></td>
<td><INPUT id="Button1" onclick="Button2_onclick()" type="button" value="另存为pdf" name="Button1"></td>
<td><INPUT id="Button3" style="WIDTH: 100px" onclick="Button3_onclick()" type="button" value="页面设置"></td>
<td><INPUT id="Button3" style="WIDTH: 100px" onclick="Button4_onclick()" type="button" value="查找对话框"></td>
<td><INPUT id="Button3" style="WIDTH: 100px" onclick="Button5_onclick()" type="button" value="excel导入"></td>
<td><INPUT id="Button1" onclick="javascript:history.back();" type="button" value=" 返回 "></td>	
</table>
</form>
  </body>
</html>
