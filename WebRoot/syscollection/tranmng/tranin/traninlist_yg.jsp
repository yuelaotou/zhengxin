<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.List" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic" %>
<%@ page import="org.xpup.hafmis.syscollection.tranmng.tranout.dto.*" %>
<%@ page import="org.xpup.hafmis.syscollection.common.domain.entity.TranInHead" %>
<%@ page import="org.xpup.hafmis.syscollection.tranmng.tranout.form.*" %>
<%@ include file="/checkUrl.jsp"%>
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
	document.forms(0).Cell1.openfile("<%=path%>/syscollection/tranmng/report/traninout_list.cll","");

	var docnum=[];
	var inorgid=[];
	var inorgname=[];
	var outorgid=[];
	var outorgname=[];
	var personcount=[];//人数
	var summoney=[];//转出总额
	var setdate=[];//转出日期
	var i=0;
	<%
			List list=(List)request.getSession().getAttribute("printlist_yg");
  			TranInHead tranInHead = null;
  			for(int j=0;j<list.size();j++)
  			{
  				tranInHead=(TranInHead)list.get(j);
 	%>		
				docnum[i]="<%=tranInHead.getDocNum()%>";
				if(<%=tranInHead.getTranInOrg().getId()==null%>){
					inorgid[i]="";
				}else{
					inorgid[i]=formatTen("<%=tranInHead.getTranInOrg().getId()+""%>")+"<%=tranInHead.getTranInOrg().getId()+""%>";
				}
				
				inorgname[i]="<%=tranInHead.getTranInOrg().getOrgInfo().getName()%>";
				if(<%=tranInHead.getTranOutOrg().getId()==null%>){
					outorgid[i]="";
				}else{
					outorgid[i]=formatTen("<%=tranInHead.getTranOutOrg().getId()+""%>")+"<%=tranInHead.getTranOutOrg().getId()+""%>";
				}
				outorgname[i]="<%=tranInHead.getTranOutOrg().getOrgInfo().getName()%>";
				personcount[i]="<%=tranInHead.getCountTranInpeople()%>";
				summoney[i]="<%=tranInHead.getTranInSumBalance()%>";
				setdate[i]=<%=tranInHead.getSettDate()%>;
				i++;
 	<%
 			}
 	%>				
 	   	bizDate="<%=(String)request.getAttribute("bizDate")%>";
 	   	userName="<%=(String)request.getAttribute("userName")%>";	
 	   	collBankName="<%=(String)request.getSession().getAttribute("collBankName_yg")%>";				
		var totalLine=inorgid.length;			//总的行数 数组的长度
		var totalPageLine=12;					//每页显示多少行
		var pageTotal=totalLine/totalPageLine;	//总共分几页
		var pageCurrent=0;						//当前页
		var completeline=0;						//记录行的
		var counts=0;                           //人数合计
		var moneys=0;    						//钱的合计
		var iPage = getInt(totalLine,totalPageLine);
		for(k = 0 ; k < totalLine; k++){
			if(k%totalPageLine==0&&k>0)
			{
				document.forms(0).Cell1.S(1,totalPageLine+5,pageCurrent,"本页小计");
			    document.forms(0).Cell1.SetFormula (6, totalPageLine+5, pageCurrent, "Sum(F5:F"+(totalPageLine+4)+")" );
			    document.forms(0).Cell1.SetFormula (7, totalPageLine+5, pageCurrent, "Sum(G5:G"+(totalPageLine+4)+")" );
				document.forms(0).Cell1.DeleteRow(totalPageLine+6,(totalPageLine+6)-(totalPageLine+4),pageCurrent);
				document.forms(0).Cell1.ReDraw();
				pageCurrent++;	
				completeline=k-2;				
				document.forms(0).Cell1.insertSheetFromFile("<%=path%>/syscollection/tranmng/report/traninout_list.cll",0,1,pageCurrent);
				document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页")	
			}
			if(pageCurrent==0)
			{
				document.forms(0).Cell1.S(1,2,0,"第"+(pageCurrent+1)+"/"+iPage+"页");
				document.forms(0).Cell1.S(3,2,0,collBankName);
				document.forms(0).Cell1.S(6,2,0,userName);
				document.forms(0).Cell1.S(8,2,0,bizDate);
				document.forms(0).Cell1.S(1,k+5,0,docnum[k]);
				document.forms(0).Cell1.S(2,k+5,0,inorgid[k]);
				document.forms(0).Cell1.s(3,k+5,0,inorgname[k]);
				document.forms(0).Cell1.s(4,k+5,0,outorgid[k]);
				document.forms(0).Cell1.s(5,k+5,0,outorgname[k]);
				document.forms(0).Cell1.d(6,k+5,0,personcount[k]);
				document.forms(0).Cell1.d(7,k+5,0,summoney[k]);
				document.forms(0).Cell1.s(8,k+5,0,setdate[k]);
				counts=counts+parseFloat(personcount[k]);
				moneys=moneys+parseFloat(summoney[k]);
			}else{//向第一页后的所有页插数据
				document.forms(0).Cell1.S(1,2,pageCurrent,"第"+(pageCurrent+1)+"/"+iPage+"页");
				document.forms(0).Cell1.S(3,2,pageCurrent,collBankName);
				document.forms(0).Cell1.S(6,2,pageCurrent,userName);
				document.forms(0).Cell1.S(8,2,pageCurrent,bizDate);
				document.forms(0).Cell1.S(1,k-completeline+3,pageCurrent,docnum[k]);
				document.forms(0).Cell1.S(2,k-completeline+3,pageCurrent,inorgid[k]);
				document.forms(0).Cell1.s(3,k-completeline+3,pageCurrent,inorgname[k]);
				document.forms(0).Cell1.s(4,k-completeline+3,pageCurrent,outorgid[k]);
				document.forms(0).Cell1.s(5,k-completeline+3,pageCurrent,outorgname[k]);
				document.forms(0).Cell1.d(6,k-completeline+3,pageCurrent,personcount[k]);
				document.forms(0).Cell1.d(7,k-completeline+3,pageCurrent,summoney[k]);
				document.forms(0).Cell1.s(8,k-completeline+3,pageCurrent,setdate[k]);
				counts=counts+parseFloat(personcount[k]);
				moneys=moneys+parseFloat(summoney[k]);
			}	
		}
				if(completeline==0)//查询出来的记录可以在一页显示的时候会进入
				{
				     
					document.forms(0).Cell1.S(1,totalLine+5,pageCurrent,"本页小计");
					document.forms(0).Cell1.SetFormula (6, totalLine+5, pageCurrent, "Sum(F5:F"+(totalLine+4)+")" );//,loopcell() > 5
					document.forms(0).Cell1.SetFormula (7, totalLine+5, pageCurrent, "Sum(G5:G"+(totalLine+4)+")" );//,loopcell() > 5
					document.forms(0).Cell1.S(1,totalLine+6,pageCurrent,"总计");
					document.forms(0).Cell1.d(6,totalLine+6,pageCurrent,counts);
					document.forms(0).Cell1.d(7,totalLine+6,pageCurrent,moneys);
					
					document.forms(0).Cell1.DeleteRow(totalLine+7,(totalPageLine+9)-(totalLine+8),pageCurrent);
					document.forms(0).Cell1.ReDraw();
				}
				else
				{
					document.forms(0).Cell1.S(1,totalLine-completeline+3,pageCurrent,"本页小计");
					document.forms(0).Cell1.SetFormula (6, totalLine-completeline+3, pageCurrent, "Sum(F5:F"+(totalLine-(completeline-2))+")" );
					document.forms(0).Cell1.SetFormula (7, totalLine-completeline+3, pageCurrent, "Sum(G5:G"+(totalLine-(completeline-2))+")" );
					document.forms(0).Cell1.S(1,totalLine-completeline+4,pageCurrent,"总计");
					document.forms(0).Cell1.d(6,totalLine-completeline+4,pageCurrent,counts);
					document.forms(0).Cell1.d(7,totalLine-completeline+4,pageCurrent,moneys);
					
					document.forms(0).Cell1.DeleteRow(totalLine-completeline+5,totalPageLine-(totalLine-completeline-3),pageCurrent);
					document.forms(0).Cell1.ReDraw();
				}	
				document.forms(0).Cell1.AllowExtend=false;
				document.forms(0).Cell1.AllowDragdrop=false;
				document.forms(0).Cell1.WorkbookReadonly=true;	
				document.forms(0).Cell1.PrintSetSheetOpt(3);
 		document.forms(0).Cell1.PrintSetPrintRange(1,0);
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
