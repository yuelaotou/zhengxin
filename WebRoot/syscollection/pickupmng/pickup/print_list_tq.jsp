<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic" %>
<%@ page import="org.xpup.hafmis.syscollection.paymng.monthpay.form.*" %>
<%@ page import="org.xpup.hafmis.syscollection.common.domain.entity.*"%>
<%@ page import="org.xpup.hafmis.syscollection.pickupmng.pickup.action.*" %>
<%@ include file="/checkUrl.jsp"%>
<%@ page import="org.xpup.hafmis.syscollection.pickupmng.pickup.dto.ApplyBookDTO"%>
<%@ page import="org.xpup.hafmis.syscollection.pickupmng.pickup.dto.OrgSearchConditionDTO"%>
<%
   String path=request.getContextPath();
   List list = (List) request.getSession().getAttribute("picklist");
   ApplyBookDTO book = (ApplyBookDTO)request.getAttribute("apply");
   	String date=(String)request.getAttribute("date");
%>
<html:html lang="true">
<head>
<script src="<%=path%>/js/common.js">
</script>
<title>提取打印</title>
<script type="text/javascript">
    function load(){//打开文件...
    loginReg();
    	//前边3句话那是固定的....不能改的...如果改了就显示不正确了
		//document.forms(0).Cell1.openfile("<%=path%>/syscollection/pickupmng/print/print_list_org.cll","");
		
		document.forms(0).Cell1.openfile("<%=path%>/syscollection/pickupmng/print/print_list_org.cll","");
	//	document.forms(0).Cell1.setSheetLabel(1,"第2页");//这句话必须放在加载文件的下边
		
			var fBankid="<%=book.getFOrgBank()%>";
		if(fBankid=="null"){
			fBankid="";
		}
        var operater="<%=book.getOperater()%>";
		if(operater=="null"){
			operater="";
		}
		////////////////打印尾表////////////
		var orgid=[];
		var orgname=[];
		
		var noteNumber=[];
		var docNumber=[];
		
		var pickPersonCount=[];
		var pickBalance=[];
		var distroyInterest=[];
		var pickMoneyCount =[];
		var settDate = [];
		var i = 0;
		<%
			PickHead tail = null; 
			for(int j=0;j<list.size();j++){
			tail = (PickHead)list.get(j);
			
		%>
			/*以上是打印全部凭证的功能*/
			//第一页是openFile ...第二页以后必须全用inertSheetFromFile
			noteNumber[i] = "<%=tail.getNoteNum()%>";
			if(noteNumber[i]=="null"||noteNumber[i]==null){
			noteNumber[i]="";
		    }
			docNumber[i] = "<%=tail.getDocNum()%>";
			orgid[i] = "<%=tail.getOrg().getId()%>";
			orgname[i] = "<%=tail.getOrg().getOrgInfo().getName()%>";
			pickPersonCount[i] = "<%=tail.getPickPersonCount()%>";
			pickBalance[i] = "<%=tail.getPickBalance()%>";
			distroyInterest[i] = "<%=tail.getDistroyInterest()%>";
		    pickMoneyCount[i] = "<%=tail.getPickMoneyCount()%>";
			settDate[i] = "<%=tail.getSettDate()%>";
			 	
			i++;//这个地方的i是脚本的i  而不是循环中java的i;
		<%}%>
		var totalLine=i;			             //总的行数 数组的长度
		var totalPageLine=12;					//每页显示多少行--除了第一行
		var pageTotal=totalLine/totalPageLine;	//总共分几页 总行数/每页的行数
		var pageCurrent=0;						//当前页
		var completeline=0;						//记录行的
		
		var personcout=0;
		var moneytotal=0;						//总的合计
		var moneyoutsum=0;						//总的合计－提取金额
		var moneyinterest=0;					//总的合计-利息
		
		var iPage = getInt(totalLine,totalPageLine);
		var icount = 1;
		
		for(k = 0 ; k < totalLine; k++){
			if(k%totalPageLine==0&&k>0)
			{
			    
				document.forms(0).Cell1.S(1,totalPageLine+4,pageCurrent,"本页小计");
				document.forms(0).Cell1.SetFormula (6, totalPageLine+4, pageCurrent, "Sum(F4:F"+(totalPageLine+3)+")" );
				document.forms(0).Cell1.SetFormula (7, totalPageLine+4, pageCurrent, "Sum(G4:G"+(totalPageLine+3)+")" );
				document.forms(0).Cell1.SetFormula (8, totalPageLine+4, pageCurrent, "Sum(H4:H"+(totalPageLine+3)+")" );
				document.forms(0).Cell1.SetFormula (9, totalPageLine+4, pageCurrent, "Sum(I4:I"+(totalPageLine+3)+")" );
				document.forms(0).Cell1.ReDraw();//重画一个表格
				pageCurrent++;	//当前页++	
				icount++;
				completeline=k-3;		
				//绘制标签 param 	表页索引号。param 要设置的表页页签名称			
				document.forms(0).Cell1.insertSheetFromFile("<%=path%>/syscollection/pickupmng/print/print_list_org1.cll",0,1,pageCurrent);
				document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页")	
			
			}
			if(pageCurrent==0)//如果当前页为第一页的时候 插入数据-->这个地方应该写当前页是第2页了..因为第一页是凭证
			{
			    document.forms(0).Cell1.S(3,2,0,fBankid);//这个单元格式虽然是5行的3列或4列..但是你必须指定的是第3列
                document.forms(0).Cell1.S(5,2,0,"<%=date%>");
				document.forms(0).Cell1.S(10,2,0,"第"+icount+"/"+iPage+"页");
				
				//document.forms(0).Cell1.S(列,行,页数,"本页小计");
				document.forms(0).Cell1.S(1,k+4,0,k+1);
				document.forms(0).Cell1.S(2,k+4,0,noteNumber[k]);
				document.forms(0).Cell1.S(3,k+4,0,docNumber[k]);
				document.forms(0).Cell1.S(4,k+4,0,"0"+orgid[k]);
				document.forms(0).Cell1.S(5,k+4,0,orgname[k]);
				document.forms(0).Cell1.d(6,k+4,0,pickPersonCount[k]);
				document.forms(0).Cell1.d(7,k+4,0,pickBalance[k]);
				document.forms(0).Cell1.d(8,k+4,0,distroyInterest[k]);
				document.forms(0).Cell1.d(9,k+4,0,pickMoneyCount[k]);
				document.forms(0).Cell1.S(10,k+4,0,settDate[k]);
				
				document.forms(0).Cell1.S(3,17,0,operater);
				//document.forms(0).Cell1.S(2,k+5,0,id[k]);//这个地方也要写第二页
			
				personcout=personcout+parseFloat(pickPersonCount[k]);
				moneyoutsum=moneyoutsum+parseFloat(pickBalance[k]);
				moneyinterest=moneyinterest+parseFloat(distroyInterest[k]);
				moneytotal=moneytotal+parseFloat(pickMoneyCount[k]);
				
			}else{//向第一页后的所有页插数据
			        document.forms(0).Cell1.S(3,2,pageCurrent,fBankid);//这个单元格式虽然是5行的3列或4列..但是你必须指定的是第3列
                document.forms(0).Cell1.S(5,2,pageCurrent,"<%=date%>");
				document.forms(0).Cell1.S(10,2,pageCurrent,"第"+icount+"/"+iPage+"页");
				
				document.forms(0).Cell1.S(1,k-completeline+1,pageCurrent,k+1);
			    document.forms(0).Cell1.S(2,k-completeline+1,pageCurrent,noteNumber[k]);
				document.forms(0).Cell1.S(3,k-completeline+1,pageCurrent,docNumber[k]);
				document.forms(0).Cell1.S(4,k-completeline+1,"0"+pageCurrent,orgid[k]);
				document.forms(0).Cell1.S(5,k-completeline+1,pageCurrent,orgname[k]);
				document.forms(0).Cell1.d(6,k-completeline+1,pageCurrent,pickPersonCount[k]);
				document.forms(0).Cell1.d(7,k-completeline+1,pageCurrent,pickBalance[k]);
				document.forms(0).Cell1.d(8,k-completeline+1,pageCurrent,distroyInterest[k]);
				document.forms(0).Cell1.d(9,k-completeline+1,pageCurrent,pickMoneyCount[k]);
				document.forms(0).Cell1.S(10,k-completeline+1,pageCurrent,settDate[k]);
				
				document.forms(0).Cell1.S(3,17,pageCurrent,operater);
					
			//	document.forms(0).Cell1.S(2,k-completeline+1,pageCurrent,id[k]);//这个地方也要写第二页
				
				personcout=personcout+parseFloat(pickPersonCount[k]);
				moneyoutsum=moneyoutsum+parseFloat(pickBalance[k]);
				moneyinterest=moneyinterest+parseFloat(distroyInterest[k]);
				moneytotal=moneytotal+parseFloat(pickMoneyCount[k]);
			}	
			
		
				if(completeline==0)//查询出来的记录可以在一页显示的时候会进入
				{
				if(iPage==1){
				// document.forms(0).Cell1.S(1,totalLine+5,pageCurrent,"合计：");
					//document.forms(0).Cell1.S(2, totalLine+5, pageCurrent, totalLine+"(人)" );
					document.forms(0).Cell1.S(1,totalLine+4,pageCurrent,"本页小计");
					document.forms(0).Cell1.SetFormula (6, totalLine+4, pageCurrent, "Sum(F4:F"+(totalLine+3)+")" );
					document.forms(0).Cell1.SetFormula (7, totalLine+4, pageCurrent, "Sum(G4:G"+(totalLine+3)+")" );
					document.forms(0).Cell1.SetFormula (8, totalLine+4, pageCurrent, "Sum(H4:H"+(totalLine+3)+")" );
				    document.forms(0).Cell1.SetFormula (9, totalLine+4, pageCurrent, "Sum(I4:I"+(totalLine+3)+")" );
					document.forms(0).Cell1.S(1,totalLine+5,pageCurrent,"总计");
					
					document.forms(0).Cell1.d(6,totalLine+5,pageCurrent,personcout);
					document.forms(0).Cell1.d(7,totalLine+5,pageCurrent,moneyoutsum);
					document.forms(0).Cell1.d(8,totalLine+5,pageCurrent,moneyinterest);
					document.forms(0).Cell1.d(9,totalLine+5,pageCurrent,moneytotal);
					//document.forms(0).Cell1.DeleteRow(totalLine+9,totalPageLine-(totalLine+8),pageCurrent);
					document.forms(0).Cell1.ReDraw();
				
				}
				   
				}
				else
				{
				  if(iPage==icount){
				    document.forms(0).Cell1.S(1,totalLine-completeline+1,pageCurrent,"本页小计");
					document.forms(0).Cell1.SetFormula (6, totalLine-completeline+1, pageCurrent, "Sum(F4:F"+(totalLine-(completeline+0))+")");
					document.forms(0).Cell1.SetFormula (7, totalLine-completeline+1, pageCurrent, "Sum(G4:G"+(totalLine-(completeline+0))+")");
					document.forms(0).Cell1.SetFormula (8, totalLine-completeline+1, pageCurrent, "Sum(H4:H"+(totalLine-(completeline+0))+")");
					document.forms(0).Cell1.SetFormula (9, totalLine-completeline+1, pageCurrent, "Sum(I4:I"+(totalLine-(completeline+0))+")");
					document.forms(0).Cell1.S(1,totalLine-completeline+2,pageCurrent,"总计");
					
					document.forms(0).Cell1.d(6,totalLine-completeline+2,pageCurrent,personcout);
					document.forms(0).Cell1.d(7,totalLine-completeline+2,pageCurrent,moneyoutsum);
					document.forms(0).Cell1.d(8,totalLine-completeline+2,pageCurrent,moneyinterest);
					document.forms(0).Cell1.d(9,totalLine-completeline+2,pageCurrent,moneytotal);
				//	document.forms(0).Cell1.DeleteRow(totalLine-completeline+3,totalPageLine-(totalLine-completeline+3),pageCurrent);
					document.forms(0).Cell1.ReDraw();
				  }
					
				}		
		/////////不让改文本格的东西
		document.forms(0).Cell1.AllowExtend=false;
		document.forms(0).Cell1.AllowDragdrop=false;
		document.forms(0).Cell1.WorkbookReadonly=true;	
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

	}
	
	function printsheet(){//真正的打印
		var k=document.forms(0).Cell1.GetCurSheet();//显示打印预览那个页面--固定
		document.forms(0).Cell1.PrintSheet(1,k);//固定...
	}
	function printPreview(){
		var k=document.forms(0).Cell1.GetCurSheet();//显示打印预览那个页面
		document.forms(0).Cell1.printPreviewEx(1,k,false);//key代表当前页
	}
	function Button1_onclick()
	{
		document.forms(0).Cell1.SaveFile();
	}
	function Button2_onclick()
	{
		document.forms(0).Cell1.ExportPdfFile("d:\\aa",-1,1,1);//没有用
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
</head>
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
<td><INPUT id="Button3" onclick="location.href='javascript:history.back();'" type="button" value=" 返回 "></td>
</table>
</form>
</body>
</html:html>
	