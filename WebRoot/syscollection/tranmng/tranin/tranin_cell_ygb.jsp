<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.List" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic" %>
<%@ page import="org.xpup.hafmis.syscollection.common.domain.entity.TranOutTail" %>
<%@ page import="org.xpup.hafmis.syscollection.common.domain.entity.TranOutHead" %>
<%@ page import="org.xpup.hafmis.syscollection.common.domain.entity.Emp" %>
<%@ page import="org.xpup.hafmis.syscollection.common.domain.entity.TranInTail" %>
<%@ page import="org.xpup.hafmis.syscollection.tranmng.tranout.form.TranTbPrintAF" %>
<%@ page import="java.math.BigDecimal" %>
<%@ include file="/checkUrl.jsp"%>
<%
   String path=request.getContextPath();
   String url=(String)request.getAttribute("url");
   if(url == null){
   	url="tran_showFindTbAC.do";
   }
   String bank = "";
   if(request.getAttribute("AA101bank")!=null){
   	bank = (String)request.getAttribute("AA101bank");
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
	document.forms(0).Cell1.openfile("<%=path%>/syscollection/tranmng/report/tranoutlist.cll","");
	//document.forms(0).Cell1.insertSheetFromFile("<%=path%>/syscollection/tranmng/report/tranoutlist.cll",0,1,1);
    //document.forms(0).Cell1.setSheetLabel(1,"第2页");
    var empid=[];
    var nameList=[];
	var cardNum=[];
	var integral=[];
	var num=[];
	var tranoutsum=[];
	var inempid=[];
	var reason=[];
	var date="";
	var i=0;
	<%
    	    TranTbPrintAF tranTbPrintAF = (TranTbPrintAF)request.getAttribute("tranTbPrintAF");
    	    List taillist=tranTbPrintAF.getList();
    		String userName=(String) request.getAttribute("userName");
    	    TranOutHead tranOutHead = null;
    	    String inorgid=tranTbPrintAF.getInOrgId();
    	    String inorgname=tranTbPrintAF.getInOrgName();
    		String name_a="";
    		String tel_a="";
	%>
			<%
  			for(int j=0;j<taillist.size();j++)
  			{
				TranInTail tail = (TranInTail)taillist.get(j);
	  			BigDecimal biga=new BigDecimal("0.00");
	  			biga=biga.add(tail.getPreIntegral()).add(tail.getCurIntegral()).add(tail.getPreInterest()).add(tail.getCurInterest());
  				
 	%>
 	            integral[i]="<%=biga%>";
 	            if(<%=tail.getEmpId()==null || tail.getEmpId().intValue()==0 %>){
 	            	inempid[i]="";
 	            }else{
					inempid[i]=format("<%=tail.getEmpId() %>")+"<%=tail.getEmpId() %>";
				}
 	            nameList[i]="<%=tail.getName()%>";
				cardNum[i]="<%=tail.getCardNum() %>";
				tranoutsum[i]="<%=tail.getPreBalance().add(tail.getCurBalance()).add(tail.getPreInterest()).add(tail.getCurInterest())%>";  
 	          	num[i]=i+1;  
 	          	i++; 
 	<%
 			}
 	%>

	    var totalLine=nameList.length;			//总的行数 数组的长度
		var totalPageLine=10;					//每页显示多少行
		var iPage = getInt(totalLine,totalPageLine);
		var pageCurrent=0;						//当前页
		var completeline=0;						//记录行的
		
		for(k = 0 ; k < totalLine; k++){
			if(k%totalPageLine==0&&k>0)
			{
				document.forms(0).Cell1.S(8,4,pageCurrent,formatTen("<%=inorgid%>")+"<%=inorgid%>");
				document.forms(0).Cell1.S(8,3,pageCurrent,"<%=inorgname%>");	
				document.forms(0).Cell1.S(2,19,pageCurrent,"<%=name_a%>");	
				document.forms(0).Cell1.S(4,19,pageCurrent,"<%=tel_a%>");
				date = "<%=tranTbPrintAF.getStartDate() %>";
				document.forms(0).Cell1.S(3,5,pageCurrent,date.substring(0,4)+"年"+date.substring(4,6)+"月"+date.substring(6,8)+"日");
				document.forms(0).Cell1.S(8,5,pageCurrent,"第"+(pageCurrent+1)+"/"+iPage+"页");
				document.forms(0).Cell1.SetFormula (6, 17, pageCurrent, "Sum(F7:F16)" );
				document.forms(0).Cell1.SetFormula (6, 18, pageCurrent, "Sum(F7:F16)" );
				document.forms(0).Cell1.SetFormula (7, 17, pageCurrent, "Sum(G7:G16)" );
				document.forms(0).Cell1.SetFormula (7, 18, pageCurrent, "Sum(G7:G16)" );
				
				pageCurrent++;
				completeline=k-7;
				document.forms(0).Cell1.insertSheetFromFile("<%=path%>/syscollection/tranmng/report/tranoutlist.cll",0,1,pageCurrent);
				document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页");
				document.forms(0).Cell1.ReDraw();
			}
			if(pageCurrent==0){
				document.forms(0).Cell1.S(1,k+7,0,num[k]);
				document.forms(0).Cell1.S(2,k+7,0,nameList[k]);
				document.forms(0).Cell1.S(3,k+7,0,cardNum[k]);
				document.forms(0).Cell1.S(5,k+7,0,inempid[k]);
				document.forms(0).Cell1.d(6,k+7,0,tranoutsum[k]);
				document.forms(0).Cell1.d(7,k+7,0,integral[k]);
			}else{//向第一页后的所有页插数据
				document.forms(0).Cell1.S(1,k-completeline,pageCurrent,num[k]);
				document.forms(0).Cell1.S(2,k-completeline,pageCurrent,nameList[k]);
				document.forms(0).Cell1.S(3,k-completeline,pageCurrent,cardNum[k]);
				document.forms(0).Cell1.S(5,k-completeline,pageCurrent,inempid[k]);
				document.forms(0).Cell1.d(6,k-completeline,pageCurrent,tranoutsum[k]);
				document.forms(0).Cell1.d(7,k-completeline,pageCurrent,integral[k]);
			}
		}
				if(completeline==0){
					document.forms(0).Cell1.SetFormula (6, 17, 0, "Sum(F7:F16)" );
					document.forms(0).Cell1.SetFormula (6, 18, 0, "Sum(F7:F16)" );
					document.forms(0).Cell1.SetFormula (7, 17, 0, "Sum(G7:G16)" );
					document.forms(0).Cell1.SetFormula (7, 18, 0, "Sum(G7:G16)" );
					document.forms(0).Cell1.S(2,19,0,"<%=name_a%>");	
					document.forms(0).Cell1.S(4,19,0,"<%=tel_a%>");
					document.forms(0).Cell1.S(8,5,0,"第"+(pageCurrent+1)+"/"+iPage+"页");
					document.forms(0).Cell1.S(8,4,0,formatTen("<%=inorgid%>")+"<%=inorgid%>");
					document.forms(0).Cell1.S(8,3,0,"<%=inorgname%>");	
					date = "<%=tranTbPrintAF.getStartDate() %>";
					document.forms(0).Cell1.S(3,5,0,date.substring(0,4)+"年"+date.substring(4,6)+"月"+date.substring(6,8)+"日");
					document.forms(0).Cell1.DeleteRow(totalLine+7,totalPageLine-totalLine,0);
				}else{
					document.forms(0).Cell1.SetFormula (6, 17, pageCurrent, "Sum(F7:F16)" );
					document.forms(0).Cell1.SetFormula (6, 18, pageCurrent, "Sum(F7:F16)" );
					document.forms(0).Cell1.SetFormula (7, 17, pageCurrent, "Sum(G7:G16)" );
					document.forms(0).Cell1.SetFormula (7, 18, pageCurrent, "Sum(G7:G16)" );
					document.forms(0).Cell1.S(2,19,pageCurrent,"<%=name_a%>");	
					document.forms(0).Cell1.S(4,19,pageCurrent,"<%=tel_a%>");
					document.forms(0).Cell1.S(8,5,pageCurrent,"第"+(pageCurrent+1)+"/"+iPage+"页");
					document.forms(0).Cell1.S(8,4,pageCurrent,formatTen("<%=inorgid%>")+"<%=inorgid%>");
					document.forms(0).Cell1.S(8,3,pageCurrent,"<%=inorgname%>");	
					date = "<%=tranTbPrintAF.getStartDate() %>";
					document.forms(0).Cell1.S(3,5,pageCurrent,date.substring(0,4)+"年"+date.substring(4,6)+"月"+date.substring(6,8)+"日");
					document.forms(0).Cell1.DeleteRow(totalLine+7,totalPageLine-totalLine,0);
					document.forms(0).Cell1.DeleteRow(totalLine-completeline,totalPageLine-(totalLine-completeline-7),pageCurrent);
				}
				document.forms(0).Cell1.PrintSetSheetOpt(3);
 				document.forms(0).Cell1.PrintSetPrintRange(1,0);
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
<td><input type="button" name="print" value = "打印预览" onclick = "printPreview();"/><td>
<td><INPUT id="Button1" onclick="printsheet()" type="button" value=" 打印 " name="Button1"></td>
<td><INPUT id="Button1" onclick="Button1_onclick()" type="button" value="另存为Excel" name="Button1"><td>
<td><INPUT id="Button1" onclick="Button2_onclick()" type="button" value="另存为pdf" name="Button1"><td>
<td><INPUT id="Button3" style="WIDTH: 100px" onclick="Button3_onclick()" type="button" value="页面设置"><td>
<td><INPUT id="Button3" style="WIDTH: 100px" onclick="Button4_onclick()" type="button" value="查找对话框"><td>
<td><INPUT id="Button3" style="WIDTH: 100px" onclick="Button5_onclick()" type="button" value="excel导入"><td>
<td><INPUT id="Button3" onclick="javascript:history.back();" type="button" value=" 返回 "><td>	
</table>
</form>
  </body>
</html>
