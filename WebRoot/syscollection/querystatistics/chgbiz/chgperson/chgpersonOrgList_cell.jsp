<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.List" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic" %>
<%@ page import="org.xpup.hafmis.syscollection.common.domain.entity.*" %>
<%@ page import="org.xpup.hafmis.common.util.BusiTools" %>
<%@ include file="/checkUrl.jsp"%>

<%
   String path=request.getContextPath();
 %>
<html>
<head>
<script src="<%=path%>/js/common.js"></script>
</head>
<script type="text/javascript">
	function load(){	
	loginReg();
	document.forms(0).Cell1.openfile("<%=path%>/syscollection/querystatistics/chgbiz/chgperson/report/chgpersonOrgList_wl.cll","");

	var office=[];
	var bank=[];
	var orgid=[];
	var orgname=[];
	var chgcount=[];
	var oldsumpay=[];
	var newsumpay=[];
	var chgyearmonth=[];
	var chgdate=[];
	var chgstatus=[];
	var rate=[];
	var i=0;
	<%
			session = request.getSession();
    		List list=(List) session.getAttribute("cellList");
  			ChgPersonHead chgPersonHead=null;
  			for(int j=0;j<list.size();j++)
  			{
  				chgPersonHead=(ChgPersonHead)list.get(j);
  				String tempid = chgPersonHead.getOrg().getId().toString();
                String orgid = 	BusiTools.convertTenNumber(tempid);
 	%>	
				office[i]="<%=chgPersonHead.getOrg().getOrgInfo().getTemp_officename()%>";
				bank[i]="<%=chgPersonHead.getOrg().getOrgInfo().getTemp_collectionBankname()%>";
				//orgid[i]=format("<%=chgPersonHead.getOrg().getId()%>")+<%=chgPersonHead.getOrg().getId()%>;
				orgid[i]="<%=orgid%>"
				orgname[i]="<%=chgPersonHead.getOrg().getOrgInfo().getName()%>";
				chgcount[i]=<%=chgPersonHead.getSumChgPerson()%>;
				oldsumpay[i]=<%=chgPersonHead.getOldOldPayment()%>;
				newsumpay[i]=<%=chgPersonHead.getNewOldPayment()%>;
				chgyearmonth[i]="<%=chgPersonHead.getChngMonth()%>";
				chgdate[i]="<%=chgPersonHead.getBizDate()%>";
				chgstatus[i]="<%=chgPersonHead.getTemp_chgStatus()%>";
				rate[i]="<%=chgPersonHead.getTemp_rate()%>";
				i++;
 	<%
 			}
 	%>
 		var totalLine=orgname.length;			//总的行数
		var totalPageLine=45;					//每页显示多少行
		var pageTotal=totalLine/totalPageLine;	//总共分几页
		var pageCurrent=0;						//当前页
		var completeline=0;						//记录行的
		var counttotal=0;						//总的合计-人数
		var oldpaymenttotal=0;				    //总的合计-变更前应缴总额
		var newpaymenttotal=0;				    //总的合计-变更后应缴总额
		
		
		for(k = 0 ; k < totalLine; k++){
			if(k%totalPageLine==0&&k>0)
			{
				document.forms(0).Cell1.S(1,totalPageLine+3,pageCurrent,"本页小计");
				document.forms(0).Cell1.SetFormula (5, totalPageLine+3, pageCurrent, "Sum(E4:E"+(totalPageLine+2)+")" );
				document.forms(0).Cell1.SetFormula (6, totalPageLine+3, pageCurrent, "Sum(F4:F"+(totalPageLine+2)+")" );
				document.forms(0).Cell1.SetFormula (7, totalPageLine+3, pageCurrent, "Sum(G4:G"+(totalPageLine+2)+")" );
				document.forms(0).Cell1.DeleteRow(50,2,pageCurrent);
				document.forms(0).Cell1.ReDraw();
				pageCurrent++;	
				completeline=k-2;				
				document.forms(0).Cell1.insertSheetFromFile("<%=path%>/syscollection/querystatistics/chgbiz/chgperson/report/chgpersonOrgList_wl.cll",0,1,pageCurrent);
				document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页")	
			}
			if(pageCurrent==0)
			{
				document.forms(0).Cell1.S(1,k+4,0,office[k]);
				document.forms(0).Cell1.S(2,k+4,0,bank[k]);
				document.forms(0).Cell1.S(3,k+4,0,orgid[k]);
				document.forms(0).Cell1.S(4,k+4,0,orgname[k]);
				document.forms(0).Cell1.d(5,k+4,0,chgcount[k]);
				document.forms(0).Cell1.d(6,k+4,0,oldsumpay[k]);
				document.forms(0).Cell1.d(7,k+4,0,newsumpay[k]);
				document.forms(0).Cell1.S(8,k+4,0,chgyearmonth[k]);
				document.forms(0).Cell1.S(9,k+4,0,chgdate[k]);
				document.forms(0).Cell1.S(10,k+4,0,chgstatus[k]);
				document.forms(0).Cell1.S(11,k+4,0,rate[k]);
				counttotal=counttotal+parseFloat(chgcount[k]);
				oldpaymenttotal=oldpaymenttotal+parseFloat(oldsumpay[k]);
				newpaymenttotal=newpaymenttotal+parseFloat(newsumpay[k]);
			}else{//向第一页后的所有页插数据
				document.forms(0).Cell1.S(1,k-completeline+2,pageCurrent,office[k]);
				document.forms(0).Cell1.S(2,k-completeline+2,pageCurrent,bank[k]);
				document.forms(0).Cell1.S(3,k-completeline+2,pageCurrent,orgid[k]);
				document.forms(0).Cell1.S(4,k-completeline+2,pageCurrent,orgname[k]);
				document.forms(0).Cell1.d(5,k-completeline+2,pageCurrent,chgcount[k]);
				document.forms(0).Cell1.d(6,k-completeline+2,pageCurrent,oldsumpay[k]);
				document.forms(0).Cell1.d(7,k-completeline+2,pageCurrent,newsumpay[k]);
				document.forms(0).Cell1.S(8,k-completeline+2,pageCurrent,chgyearmonth[k]);
				document.forms(0).Cell1.S(9,k-completeline+2,pageCurrent,chgdate[k]);
				document.forms(0).Cell1.S(10,k-completeline+2,pageCurrent,chgstatus[k]);
				document.forms(0).Cell1.S(11,k-completeline+2,pageCurrent,rate[k]);
				counttotal=counttotal+parseFloat(chgcount[k]);
				oldpaymenttotal=oldpaymenttotal+parseFloat(oldsumpay[k]);
				newpaymenttotal=newpaymenttotal+parseFloat(newsumpay[k]);
			}	
		}
		

				if(completeline==0)//查询出来的记录可以在一页显示的时候会进入
				{
					document.forms(0).Cell1.S(1,totalLine+4,pageCurrent,"本页小计");
					document.forms(0).Cell1.SetFormula (5, totalLine+4, pageCurrent, "Sum(E4:E"+(totalLine+3)+")" );
					document.forms(0).Cell1.SetFormula (6, totalLine+4, pageCurrent, "Sum(F4:F"+(totalLine+3)+")" );
					document.forms(0).Cell1.SetFormula (7, totalLine+4, pageCurrent, "Sum(G4:G"+(totalLine+3)+")" );
					document.forms(0).Cell1.S(1,totalLine+5,pageCurrent,"总计");
					document.forms(0).Cell1.d(5,totalLine+5,pageCurrent,counttotal);
					document.forms(0).Cell1.d(6,totalLine+5,pageCurrent,oldpaymenttotal);
					document.forms(0).Cell1.d(7,totalLine+5,pageCurrent,newpaymenttotal);
					document.forms(0).Cell1.DeleteRow(totalLine+6,51-(totalLine+5),pageCurrent);
					document.forms(0).Cell1.ReDraw();
				}	
								
				else
				{
					document.forms(0).Cell1.S(1,totalLine-completeline+2,pageCurrent,"本页小计");   
					//F1 第F列的第1行: N9 到第N列的第9行  求和
					document.forms(0).Cell1.SetFormula (5, totalLine-completeline+2, pageCurrent, "Sum(E4:E"+(totalLine-(completeline-1))+")" );
					document.forms(0).Cell1.SetFormula (6, totalLine-completeline+2, pageCurrent, "Sum(F4:F"+(totalLine-(completeline-1))+")" );
					document.forms(0).Cell1.SetFormula (7, totalLine-completeline+2, pageCurrent, "Sum(G4:G"+(totalLine-(completeline-1))+")" );
					document.forms(0).Cell1.S(1,totalLine-completeline+3,pageCurrent,"总计");
					document.forms(0).Cell1.d(5,totalLine-completeline+3,pageCurrent,counttotal);
					document.forms(0).Cell1.d(6,totalLine-completeline+3,pageCurrent,oldpaymenttotal);
					document.forms(0).Cell1.d(7,totalLine-completeline+3,pageCurrent,newpaymenttotal);
					document.forms(0).Cell1.DeleteRow(totalLine-completeline+4,totalPageline-(totalLine-completeline-3),pageCurrent);
					document.forms(0).Cell1.ReDraw();
				}	
				document.forms(0).Cell1.AllowExtend=false;
				document.forms(0).Cell1.AllowDragdrop=false;
				document.forms(0).Cell1.WorkbookReadonly=true;	


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
<body onload = "load();"  onContextmenu="return false"> 
<form action="">
<table align="center">
	<tr><OBJECT id=Cell1 style= "LEFT:0px;WIDTH:700px;  TOP:0px;HEIGHT:400px" codeBase="http://192.168.1.44:8088/hafmis/CellWeb5.CAB#version=5,3,7,321" classid=clsid:3F166327-8030-4881-8BD2-EA25350E574A VIEWASTEXT><PARAM NAME="_Version" VALUE="65536"><PARAM NAME="_ExtentX" VALUE="10266"><PARAM NAME="_ExtentY" VALUE="7011"><PARAM NAME="_StockProps" VALUE= "0"></OBJECT></tr> 
	<tr><input type="button" name="print" value = "打印预览" onclick = "printPreview();"/>
		<INPUT id="Button1" onclick="Button1_onclick()" type="button" value="另存为Excel" name="Button1">
		<INPUT id="Button1" onclick="Button2_onclick()" type="button" value="另存为pdf" name="Button1">
		<INPUT id="Button3" style="WIDTH: 100px" onclick="Button3_onclick()" type="button" value="页面设置">
		<INPUT id="Button3" style="WIDTH: 100px" onclick="Button4_onclick()" type="button" value="查找对话框">
		<INPUT id="Button3" style="WIDTH: 100px" onclick="Button5_onclick()" type="button" value="excel导入">
		<INPUT id="Button1" onclick="printsheet()" type="button" value=" 打印 " name="Button1">
		<INPUT id="Button3" onclick="javascript:history.back();" type="button" value=" 返回 ">	
	</tr>
</table>
</form>
</body>
</html>
