<%@ page language="java"%>
<%@ page contentType="text/html;charset=UTF-8" import="java.util.List" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic" %>
<%@ page import="org.xpup.hafmis.syscollection.querystatistics.operationflow.empoperationflow.dto.*" %>
<%@ include file="/checkUrl.jsp"%>
<%
   String path=request.getContextPath();
   String bizDate = (String)request.getAttribute("bizDate");
   String operator = (String)request.getAttribute("operator");
 %>
<html>
  <head>
  <script src="<%=path%>/js/common.js">
</script>
  </head>
  <script type="text/javascript">
	function load(){
	loginReg();
	document.forms(0).Cell1.openfile("<%=path%>/syscollection/accounthandle/clearaccount/report/empoperationflow.cll","");

	var noteNum=[];
	var docNum=[];
	var orgid=[];
	var orgname=[];
	var empid=[];
	var empname=[];
	var opStatus=[];
	var opDate=[];
	var opMoney=[];
	var opInterest=[];
	var opDirection=[];
	var opType=[];
	var reason=[];
	var reserveaA=[];
	var checkPerson=[];
	var clearPerson=[];
	var empId=[];
	var empName=[];
	var i=0;
	<%
    		List list=(List) request.getAttribute("cellList");
    		EmpOperationFlowDTO dto1 = (EmpOperationFlowDTO)list.get(0);
    		String orgId = dto1.getOrgid().toString();
    		String orgName = dto1.getOrgname();
    		String empId = dto1.getEmpid().toString();
    		String empName = dto1.getEmpname();
  			EmpOperationFlowDTO dto=null;
  			for(int j=0;j<list.size();j++)
  			{
  				dto=(EmpOperationFlowDTO)list.get(j);	
 	%>			
				noteNum[i]="<%=dto.getNoteNum()%>";
				if(noteNum[i]=="null")
					noteNum[i]="";
				docNum[i]="<%=dto.getDocNum()%>";
				if(docNum[i]=="null")
					docNum[i]="";
				orgid[i]=format("<%=dto.getOrgid()%>")+"<%=dto.getOrgid()%>";
				orgname[i]="<%=dto.getOrgname()%>";
				
				opStatus[i]="<%=dto.getOpStatus()%>";
				opDate[i]="<%=dto.getOpDate()%>";
				if(opDate[i]=="null")
					opDate[i]="";
				opMoney[i]="<%=dto.getOpMoney()%>";
				opInterest[i]="<%=dto.getOpInterest()%>";
				opDirection[i]="<%=dto.getOpDirection()%>";
				opType[i]="<%=dto.getOpType()%>";
				reason[i]="<%=dto.getReason()%>";
				reserveaA[i]="<%=dto.getReserveaA()%>";
				checkPerson[i]="<%=dto.getCheckPerson()%>";
				clearPerson[i]="<%=dto.getClearPerson()%>";
				empId[i]="<%=dto.getEmpid()%>";
				empName[i]="<%=dto.getEmpname()%>";
				i++;
 	<%
 			}
 	%>
			var totalLine=orgname.length;			//总的行数
			var totalPageLine=12;					//每页显示多少行
			var pageTotal=totalLine/totalPageLine;	//总共分几页
			var pageCurrent=0;						//当前页
			var completeline=0;						//记录行的
			var moneytotal=0;
			var interesttotal=0;	
			//总的合计
		for(k = 0 ; k < totalLine; k++){
		if(k%totalPageLine==0&&k>0)
			{
				document.forms(0).Cell1.S(2,17,pageCurrent,"本页小计");
				document.forms(0).Cell1.SetFormula (13, 17, pageCurrent, "Sum(M2:M"+(totalPageLine+4)+")" );
				document.forms(0).Cell1.SetFormula (14, 17, pageCurrent, "Sum(N2:N"+(totalPageLine+4)+")" );
				document.forms(0).Cell1.ReDraw();
				pageCurrent++;	
				completeline=k-2;				
				document.forms(0).Cell1.insertSheetFromFile("<%=path%>/syscollection/accounthandle/clearaccount/report/empoperationflow1.cll",0,1,pageCurrent);
				document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页")	;

			}
		if(pageCurrent==0)
			{
				document.forms(0).Cell1.S(7,3,0,"<%=orgId%>");
				document.forms(0).Cell1.S(10,3,0,"<%=orgName%>");
				
				document.forms(0).Cell1.S(15,3,0,"<%=operator%>");
				document.forms(0).Cell1.S(18,3,0,"<%=bizDate%>");
				document.forms(0).Cell1.S(2,k+5,0,empId[k]);
				document.forms(0).Cell1.S(7,k+5,0,empName[k]);
				document.forms(0).Cell1.S(8,k+5,0,noteNum[k]);
				document.forms(0).Cell1.S(9,k+5,0,docNum[k]);
				//document.forms(0).Cell1.S(5,k+6,0,orgid[k]);
				//document.forms(0).Cell1.S(6,k+6,0,orgname[k]);
				//document.forms(0).Cell1.S(5,k+6,0,empid[k]);
				//document.forms(0).Cell1.s(6,k+6,0,empname[k]);
				document.forms(0).Cell1.s(10,k+5,0,opType[k]);
				document.forms(0).Cell1.s(11,k+5,0,opDate[k]);
				document.forms(0).Cell1.d(13,k+5,0,opMoney[k]);
				document.forms(0).Cell1.d(14,k+5,0,opInterest[k]);
				//document.forms(0).Cell1.s(11,k+6,0,opDirection[k]);
				//document.forms(0).Cell1.s(12,k+6,0,opStatus[k]);
				document.forms(0).Cell1.s(15,k+5,0,reason[k]);
				document.forms(0).Cell1.s(16,k+5,0,reserveaA[k]);
				document.forms(0).Cell1.s(17,k+5,0,checkPerson[k]);
				document.forms(0).Cell1.s(18,k+5,0,clearPerson[k]);
				moneytotal=moneytotal+parseFloat(opMoney[k]);
				interesttotal=interesttotal+parseFloat(opInterest[k]);
			}else{//向第一页后的所有页插数据
				document.forms(0).Cell1.S(7,3,pageCurrent,"<%=orgId%>");
				document.forms(0).Cell1.S(10,3,pageCurrent,"<%=orgName%>");
				
				document.forms(0).Cell1.S(15,3,pageCurrent,"<%=operator%>");
				document.forms(0).Cell1.S(18,3,pageCurrent,"<%=bizDate%>");
				
				
				document.forms(0).Cell1.S(2,k-completeline+3,pageCurrent,empId[k]);
				document.forms(0).Cell1.S(7,k-completeline+3,pageCurrent,empName[k]);
				document.forms(0).Cell1.S(8,k-completeline+3,pageCurrent,noteNum[k]);
				document.forms(0).Cell1.S(9,k-completeline+3,pageCurrent,docNum[k]);
				//document.forms(0).Cell1.S(3,k-completeline+4,pageCurrent,orgid[k]);
				//document.forms(0).Cell1.S(4,k-completeline+4,pageCurrent,orgname[k]);
				//document.forms(0).Cell1.S(5,k-completeline+4,pageCurrent,empid[k]);
				//document.forms(0).Cell1.s(6,k-completeline+4,pageCurrent,empname[k]);
				document.forms(0).Cell1.s(10,k-completeline+3,pageCurrent,opType[k]);
				document.forms(0).Cell1.S(11,k-completeline+3,pageCurrent,opDate[k]);
				document.forms(0).Cell1.d(13,k-completeline+3,pageCurrent,opMoney[k]);
				document.forms(0).Cell1.d(14,k-completeline+3,pageCurrent,opInterest[k]);
				//document.forms(0).Cell1.s(11,k-completeline+4,pageCurrent,opDirection[k]);
				//document.forms(0).Cell1.s(12,k-completeline+4,pageCurrent,opStatus[k]);
				document.forms(0).Cell1.s(15,k-completeline+3,pageCurrent,reason[k]);
				document.forms(0).Cell1.s(16,k-completeline+3,pageCurrent,reserveaA[k]);
				document.forms(0).Cell1.s(17,k-completeline+3,pageCurrent,checkPerson[k]);
				document.forms(0).Cell1.s(18,k-completeline+3,pageCurrent,clearPerson[k]);
				moneytotal=moneytotal+parseFloat(opMoney[k]);
				interesttotal=interesttotal+parseFloat(opInterest[k]);
			}
		}
			if(completeline==0)//查询出来的记录可以在一页显示的时候会进入
				{			
					document.forms(0).Cell1.S(2,totalLine+5,pageCurrent,"本页小计");
					document.forms(0).Cell1.SetFormula (13, totalLine+5, pageCurrent, "Sum(M2:M"+(totalLine+4)+")" );
					document.forms(0).Cell1.SetFormula (14, totalLine+5, pageCurrent, "Sum(N2:N"+(totalLine+4)+")" );
					document.forms(0).Cell1.S(2,totalLine+6,pageCurrent,"总计");
					document.forms(0).Cell1.d(13,totalLine+6,pageCurrent,moneytotal);
					document.forms(0).Cell1.d(14,totalLine+6,pageCurrent,interesttotal);
					//document.forms(0).Cell1.DeleteRow(totalLine+8,27-totalLine,pageCurrent);
					//document.forms(0).Cell1.ReDraw();
				}else
				{
					document.forms(0).Cell1.S(2,totalLine-completeline+3,pageCurrent,"本页小计");
					document.forms(0).Cell1.SetFormula (13, totalLine-completeline+3, pageCurrent, "Sum(M2:M"+(totalLine-completeline+2)+")" );
					document.forms(0).Cell1.SetFormula (14, totalLine-completeline+3, pageCurrent, "Sum(N2:N"+(totalLine-completeline+2)+")" );
					document.forms(0).Cell1.S(2,totalLine-completeline+4,pageCurrent,"总计");
					document.forms(0).Cell1.d(13,totalLine-completeline+4,pageCurrent,moneytotal);
					document.forms(0).Cell1.d(14,totalLine-completeline+4,pageCurrent,interesttotal);
					//document.forms(0).Cell1.DeleteRow(totalLine-completeline+8,27-(totalLine-completeline),pageCurrent);
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
