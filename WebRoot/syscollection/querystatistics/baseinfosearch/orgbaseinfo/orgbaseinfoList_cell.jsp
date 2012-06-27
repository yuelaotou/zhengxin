
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.List" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic" %>
<%@ page import="org.xpup.hafmis.syscollection.querystatistics.baseinfosearch.orgbaseinfo.dto.OrgBaseInfoDTO" %>
<%@ page import="org.xpup.hafmis.common.util.BusiTools" %>
<%@ include file="/checkUrl.jsp"%>
<%
   String path=request.getContextPath();
%>
<html>
  <head>
  <script src="<%=path%>/js/common.js">
</script>
  </head>
  <script type="text/javascript">
	function load(){	
	loginReg();
	document.forms(0).Cell1.openfile("<%=path%>/syscollection/querystatistics/baseinfosearch/orgbaseinfo/report/orgbaseinfoList.cll","");

	var office=[];
	var collbank=[];
	var orgid=[];
	var orgname=[];
	var orgNature=[];  // 单位性质
	var depart=[];
	var paybank=[];
	var addr=[];  // 所在地区
	var orger=[];  // 经办人
	var phone=[];
	var moblie=[];
	var supervisor=[];  //稽查员
	var openday=[];
	var i=0;
	<%
    		List list=(List) request.getAttribute("printlist");
  			OrgBaseInfoDTO org=null;
  			for(int j=0;j<list.size();j++)
  			{
  				org=(OrgBaseInfoDTO)list.get(j);
  				String tempid = org.getId().toString();
  				String orgid = 	BusiTools.convertTenNumber(tempid);
 	%>			
				
				 orgid[i]="<%=orgid %>";
			     orgname[i]="<%=org.getOrgName()%>";
				 office[i]="<%=org.getOfficecode()%>";
				 collbank[i]="<%=org.getCollectionBankId()%>";
				 orgNature[i]="<%=org.getCharacter()%>";
				 depart[i]="<%=org.getDeptInCharge()%>";
				 paybank[i]="<%=org.getPaybankName()%>";
				 if(paybank[i]=="null")
				 {
				   paybank[i]="";
				 }
				 addr[i]="";
				  if( addr[i]=="null")
				 {
				    addr[i]="";
				 }
				 orger[i]="<%=org.getTransactorName()%>";
				 if( orger[i]=="null")
				 {
				    orger[i]="";
				 }
			     phone[i]="<%=org.getTransactorTel()%>";
			      if( phone[i]=="null")
				 {
				    phone[i]="";
				 }
			     moblie[i]="<%=org.getTransactorMobile()%>";
			       if(moblie[i]=="null")
				 {
				     moblie[i]="";
				 }
				 supervisor[i]="<%=org.getOpenStatus()%>";
				    if( supervisor[i]=="null")
				 {
				      supervisor[i]="";
				 }
				openday[i]="<%=org.getOpenDate()%>";
				i++;
 	<%
 			}
 	%>
			var totalLine=office.length;			//总的行数
			var totalPageLine=42;					//每页显示多少行
			var pageTotal=totalLine/totalPageLine;	//总共分几页
			var pageCurrent=0;						//当前页
			var completeline=0;						//记录行的
			
			//总的合计
		for(k = 0 ; k < totalLine; k++){
		if(k%totalPageLine==0&&k>0)
			{
				//  document.forms(0).Cell1.S(1,44,pageCurrent,"本页小计");
				// document.forms(0).Cell1.SetFormula (5, 44, pageCurrent, "Sum(E2:E"+(totalPageLine+3)+")" );
				// document.forms(0).Cell1.SetFormula (6, 44, pageCurrent, "Sum(F2:F"+(totalPageLine+3)+")" );
				// document.forms(0).Cell1.SetFormula (7, 44, pageCurrent, "Sum(G2:G"+(totalPageLine+3)+")" );
				// document.forms(0).Cell1.SetFormula (8, 44, pageCurrent, "Sum(H2:H"+(totalPageLine+3)+")" );
				// document.forms(0).Cell1.SetFormula (9, 44, pageCurrent, "Sum(I2:I"+(totalPageLine+3)+")" );
				// document.forms(0).Cell1.SetFormula (10, 44, pageCurrent, "Sum(J2:J"+(totalPageLine+3)+")" );
				// document.forms(0).Cell1.SetFormula (11, 44, pageCurrent, "Sum(K2:K"+(totalPageLine+3)+")" );
				// document.forms(0).Cell1.SetFormula (12, 44, pageCurrent, "Sum(L2:L"+(totalPageLine+3)+")" );
				// document.forms(0).Cell1.SetFormula (13, 44, pageCurrent, "Sum(M2:M"+(totalPageLine+3)+")" );
				document.forms(0).Cell1.ReDraw();
				pageCurrent++;	
				completeline=k-2;				
				document.forms(0).Cell1.insertSheetFromFile("<%=path%>/syscollection/querystatistics/baseinfosearch/orgbaseinfo/report/orgbaseinfoList_1.cll",0,1,pageCurrent);
				document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页")	;

			}
		if(pageCurrent==0)
			{
				 document.forms(0).Cell1.s(1,k+3,0,orgid[k]);
				 document.forms(0).Cell1.S(2,k+3,0,orgname[k]);
				 document.forms(0).Cell1.S(3,k+3,0,office[k]);
				 document.forms(0).Cell1.S(4,k+3,0,collbank[k]);
				 document.forms(0).Cell1.S(5,k+3,0,orgNature[k]);
				 document.forms(0).Cell1.S(6,k+3,0,depart[k]);
			     document.forms(0).Cell1.s(7,k+3,0,paybank[k]);
				 //document.forms(0).Cell1.s(8,k+3,0, addr[k]);
				 document.forms(0).Cell1.s(9,k+3,0,orger[k]);
				 document.forms(0).Cell1.s(10,k+3,0, phone[k]);
				 document.forms(0).Cell1.s(11,k+3,0, moblie[k]);
			     document.forms(0).Cell1.s(12,k+3,0,supervisor[k]);
				 document.forms(0).Cell1.s(13,k+3,0,openday[k]);
				
				
			}else{//向第一页后的所有页插数据
				document.forms(0).Cell1.S(1,k-completeline+1,pageCurrent,orgid[k]);
				document.forms(0).Cell1.S(2,k-completeline+1,pageCurrent,orgname[k]);
				document.forms(0).Cell1.S(3,k-completeline+1,pageCurrent,office[k]);
				document.forms(0).Cell1.S(4,k-completeline+1,pageCurrent,collbank[k]);
				document.forms(0).Cell1.s(5,k-completeline+1,pageCurrent,orgNature[k]);
				document.forms(0).Cell1.s(6,k-completeline+1,pageCurrent,depart[k]);
				document.forms(0).Cell1.s(7,k-completeline+1,pageCurrent,paybank[k]);
				//document.forms(0).Cell1.s(8,k-completeline+1,pageCurrent,addr[k]);
				document.forms(0).Cell1.s(9,k-completeline+1,pageCurrent,orger[k]);
				document.forms(0).Cell1.s(10,k-completeline+1,pageCurrent,phone[k]);
				document.forms(0).Cell1.s(11,k-completeline+1,pageCurrent,moblie[k]);
				document.forms(0).Cell1.s(12,k-completeline+1,pageCurrent,supervisor[k]);
				document.forms(0).Cell1.s(13,k-completeline+1,pageCurrent,openday[k]);
			
			}
		}
		
			if(completeline==0)//查询出来的记录可以在一页显示的时候会进入
				{			
					// document.forms(0).Cell1.S(1,totalLine+4,pageCurrent,"本页小计");
					// document.forms(0).Cell1.SetFormula (5, totalLine+4, pageCurrent, "Sum(E2:E"+(totalLine+3)+")" );//,loopcell() > 5
					// document.forms(0).Cell1.SetFormula (6, totalLine+4, pageCurrent, "Sum(F2:F"+(totalLine+3)+")" );//,loopcell() > 5
					// document.forms(0).Cell1.SetFormula (7, totalLine+4, pageCurrent, "Sum(G2:G"+(totalLine+3)+")" );//,loopcell() > 5
					// document.forms(0).Cell1.SetFormula (8, totalLine+4, pageCurrent, "Sum(H2:H"+(totalLine+3)+")" );//,loopcell() > 5
					// document.forms(0).Cell1.SetFormula (9, totalLine+4, pageCurrent, "Sum(I2:I"+(totalLine+3)+")" );//,loopcell() > 5
					// document.forms(0).Cell1.SetFormula (10, totalLine+4, pageCurrent, "Sum(J2:J"+(totalLine+3)+")" );//,loopcell() > 5
					// document.forms(0).Cell1.SetFormula (11, totalLine+4, pageCurrent, "Sum(K2:K"+(totalLine+3)+")" );//,loopcell() > 5
					// document.forms(0).Cell1.SetFormula (12, totalLine+4, pageCurrent, "Sum(L2:L"+(totalLine+3)+")" );//,loopcell() > 5
					// document.forms(0).Cell1.SetFormula (13, totalLine+4, pageCurrent, "Sum(M2:M"+(totalLine+3)+")" );//,loopcell() > 5
					 document.forms(0).Cell1.DeleteRow(totalLine+3,totalPageLine-(totalLine+2),pageCurrent);
		      		 ////document.forms(0).Cell1.ReDraw();
				}else
				{
					// document.forms(0).Cell1.S(1,totalLine-completeline+2,pageCurrent,"本页小计");
					// document.forms(0).Cell1.SetFormula (5, totalLine-completeline+2, pageCurrent, "Sum(E2:E"+(totalLine-(completeline-1))+")" );
					// document.forms(0).Cell1.SetFormula (6, totalLine-completeline+2, pageCurrent, "Sum(F2:F"+(totalLine-(completeline-1))+")" );
					// document.forms(0).Cell1.SetFormula (7, totalLine-completeline+2, pageCurrent, "Sum(G2:G"+(totalLine-(completeline-1))+")" );
					// document.forms(0).Cell1.SetFormula (8, totalLine-completeline+2, pageCurrent, "Sum(H2:H"+(totalLine-(completeline-1))+")" );
					// document.forms(0).Cell1.SetFormula (9, totalLine-completeline+2, pageCurrent, "Sum(I2:I"+(totalLine-(completeline-1))+")" );
					// document.forms(0).Cell1.SetFormula (10, totalLine-completeline+2, pageCurrent, "Sum(J2:J"+(totalLine-(completeline-1))+")" );
					// document.forms(0).Cell1.SetFormula (11, totalLine-completeline+2, pageCurrent, "Sum(K2:K"+(totalLine-(completeline-1))+")" );
					// document.forms(0).Cell1.SetFormula (12, totalLine-completeline+2, pageCurrent, "Sum(L2:L"+(totalLine-(completeline-1))+")" );
					// document.forms(0).Cell1.SetFormula (13, totalLine-completeline+2, pageCurrent, "Sum(M2:M"+(totalLine-(completeline-1))+")" );
					// document.forms(0).Cell1.S(1,totalLine-completeline+3,pageCurrent,"总计");
					// document.forms(0).Cell1.d(5,totalLine-completeline+3,pageCurrent,sumprebalance);
					// document.forms(0).Cell1.d(6,totalLine-completeline+3,pageCurrent,sumtemp_debit);
					// document.forms(0).Cell1.d(7,totalLine-completeline+3,pageCurrent,sumtemp_credit);
					// document.forms(0).Cell1.d(8,totalLine-completeline+3,pageCurrent,sumcountDebit);
					// document.forms(0).Cell1.d(9,totalLine-completeline+3,pageCurrent,sumcountCredit);
					// document.forms(0).Cell1.d(10,totalLine-completeline+3,pageCurrent,sumorgOverMoney);
					// document.forms(0).Cell1.d(11,totalLine-completeline+3,pageCurrent,sumcurbalance);
					// document.forms(0).Cell1.d(12,totalLine-completeline+3,pageCurrent,sumorgOverPaybalance);
					// document.forms(0).Cell1.d(13,totalLine-completeline+3,pageCurrent,sumbalance);
					document.forms(0).Cell1.DeleteRow(totalLine-completeline+1,totalPageLine-(totalLine-completeline),pageCurrent);
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
