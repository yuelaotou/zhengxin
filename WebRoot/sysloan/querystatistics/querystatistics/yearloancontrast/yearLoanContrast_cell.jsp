<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page  import="java.lang.*" import="java.util.*"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic"%>
<%@ page import="org.xpup.hafmis.sysloan.querystatistics.querystatistics.yearloancontrast.form.YearLoanContrastAF" %>
<%@ page import="org.xpup.hafmis.sysloan.querystatistics.querystatistics.yearloancontrast.dto.YearLoanContrastDTO" %>
<%@ include file="/checkUrl.jsp"%>

<%
   String path=request.getContextPath();
 %>
<html>
  <head>
  <title>打印年度贷款对照表</title>
  <script src="<%=path%>/js/common.js">
  </script>
  </head>
  
  <script type="text/javascript">
   function load(){	
	loginReg();
	document.forms(0).Cell1.openfile("<%=path%>/sysloan/querystatistics/report/yearloancontrast.cll","");
		 var  month = [];//月份
  
 		 var  b_ffhs = [];//本年发放户数
  		 var  b_ffje = [];//本年发放金额
 		 var  b_jjmj = [];//本年建筑面积
         var  b_ffzj = [];//本年房屋总价
  
         var  w_ffhs = [];//往年发放户数
         var  w_ffje = [];//往年发放金额
         var  w_jjmj = [];//往年建筑面积
         var  w_ffzj = [];//往年房屋总价
  
         var  t_ffhs = [];//同比增长发放户数
         var  t_ffje = [];//同比增长发放金额
         var  t_jjmj = [];//同比增长建筑面积
         var  t_ffzj = [];//同比增长房屋总价
	     var i=0
	<%
	 YearLoanContrastAF yearLoanContrastAF = new YearLoanContrastAF();
	 yearLoanContrastAF=(YearLoanContrastAF)request.getSession().getAttribute("yearLoanContrastAF");
	 String userName=(String)request.getAttribute("userName");
	 String plbizDate=(String)request.getAttribute("plbizDate");
	 
	 // 得到循环的列表
	 List list = yearLoanContrastAF.getList();
	 
	 for(int j=0;j<list.size();j++){
		 	YearLoanContrastDTO dto = (YearLoanContrastDTO)list.get(j);
		 
		 %>
		   month[i]="<%=dto.getMonth() %>";//月份
  
 		   b_ffhs[i]="<%=dto.getB_ffhs() %>";//本年发放户数
  		   b_ffje[i]="<%=dto.getB_ffje() %>";//本年发放金额
 		   b_jjmj[i]="<%=dto.getB_jjmj() %>";//本年建筑面积
           b_ffzj[i]="<%=dto.getB_ffzj() %>";//本年房屋总价
  
           w_ffhs[i]="<%=dto.getW_ffhs() %>";//往年发放户数
           w_ffje[i]="<%=dto.getW_ffje() %>";//往年发放金额
           w_jjmj[i]="<%=dto.getW_jjmj() %>";//往年建筑面积
           w_ffzj[i]="<%=dto.getW_ffzj() %>";//往年房屋总价
  
           t_ffhs[i]="<%=dto.getT_ffhs() %>";//同比增长发放户数
           t_ffje[i]="<%=dto.getT_ffje() %>";//同比增长发放金额
           t_jjmj[i]="<%=dto.getT_jjmj() %>";//同比增长建筑面积
           t_ffzj[i]="<%=dto.getT_ffzj() %>";//同比增长房屋总价
	 
		  i++;
	<% 
	 }
	%>
		var totalLine=month.length
		var pageCurrent=0;						//当前页
		for(k = 0 ; k < totalLine; k++){
		//if(k>0){
			//pageCurrent++;
			//document.forms(0).Cell1.insertSheetFromFile("<%=path%>/sysloan/querystatistics/report/yearloancontrast.cll",0,1,pageCurrent);
			//document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页");
		//}
			document.forms(0).Cell1.S(1,6+k,pageCurrent,month[k]);
			document.forms(0).Cell1.S(2,6+k,pageCurrent,b_ffhs[k]);
			document.forms(0).Cell1.S(3,6+k,pageCurrent,b_ffje[k]);
			document.forms(0).Cell1.S(4,6+k,pageCurrent,b_jjmj[k]);
			document.forms(0).Cell1.S(5,6+k,pageCurrent,b_ffzj[k]);
			document.forms(0).Cell1.S(6,6+k,pageCurrent,w_ffhs[k]);
			document.forms(0).Cell1.S(7,6+k,pageCurrent,w_ffje[k]);
			document.forms(0).Cell1.S(8,6+k,pageCurrent,w_jjmj[k]);
			document.forms(0).Cell1.S(9,6+k,pageCurrent,w_ffzj[k]);
			document.forms(0).Cell1.S(10,6+k,pageCurrent,t_ffhs[k]);
			document.forms(0).Cell1.S(11,6+k,pageCurrent,t_ffje[k]);
			document.forms(0).Cell1.S(12,6+k,pageCurrent,t_jjmj[k]);
			document.forms(0).Cell1.S(13,6+k,pageCurrent,t_ffzj[k]);
			
		}
		document.forms(0).Cell1.S(2,4,pageCurrent,"<%=yearLoanContrastAF.getThisyear() %>");
		document.forms(0).Cell1.S(6,4,pageCurrent,"<%=yearLoanContrastAF.getLastyear() %>");
		
		document.forms(0).Cell1.S(2,3,pageCurrent,"<%=yearLoanContrastAF.getOfficeName() %>");
		document.forms(0).Cell1.S(2,19,pageCurrent,"<%=userName%>");
		document.forms(0).Cell1.S(11,19,pageCurrent,"<%=plbizDate%>");
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
  <body onload = "load();"> 
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
						<INPUT id="Button3" onclick="location.href='to_yearLoanContrastShowAC.do'"
							type="button" value=" 返回 " />
					</td>
				</tr>
			</table>
		</form>
  </body>
</html>
