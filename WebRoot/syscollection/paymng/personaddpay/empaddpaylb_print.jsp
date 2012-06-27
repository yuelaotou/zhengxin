<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.List" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic" %>
<%@ page import="org.xpup.hafmis.syscollection.paymng.personaddpay.dto.*" %>
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

	document.forms(0).Cell1.openfile("<%=path%>/syscollection/paymng/report/empaddpay.cll","");
	document.forms(0).Cell1.insertSheetFromFile("<%=path%>/syscollection/paymng/report/personaddpaylist.cll",0,1,1);
    document.forms(0).Cell1.setSheetLabel(1,"第2页");
    var notenum=[];
    var docnum=[];
    var orgid=[];
    var orgname=[];
    var paymonth=[];
    var person=[];
    var amount=[];
    var paydate=[];
    var paystatus=[];
    var j=0;
	<%
    		EmpaddpayHeadPrintDTO dto=(EmpaddpayHeadPrintDTO)request.getAttribute("empaddpay");
    		List list=(List)request.getSession().getAttribute("empaddpayList");
    		for (int i=0;i<list.size();i++){
    		EmpaddpayMaintainDTO dto1=(EmpaddpayMaintainDTO)list.get(i);
    		
	%>
	    notenum[j]="<%=dto1.getNoteNum()%>";
	    docnum[j]="<%=dto1.getDocNum()%>";
	    orgid[j]="<%=dto1.getOrgId()%>";
	    orgname[j]="<%=dto1.getOrgName()%>";
	    paymonth[j]="<%=dto1.getPayMonth()%>";
	    person[j]="<%=dto1.getPersonCounts()%>";
	    amount[j]="<%=dto1.getPay()%>";
	    paydate[j]="<%=dto1.getOpTime()%>";
	    paystatus[j]="<%=dto1.getPayStatus()%>";
	    j++; 
 	<%
 			}
 	%>
		var sumPay = <%=dto.getPay()%>;
		var chiao="0";
		var fin="0";
		sumPay=""+sumPay;
		var count=sumPay.indexOf(".",0);
		var length;
		len=sumPay.length;
		if(count==-1){
			length=len;
		}else{
			length=count;
		}
		for(var i=0;i<length;i++){//从后向前插入（从元起）
			var temp=sumPay.substring((length-i-1),length-i);
			document.forms(0).Cell1.S((15-i),9,0,temp);
		}
		if(count!=-1){//计算小数后面
			if((len-count)>2)//两位
			{
				chiao=sumPay.substring(count+1,sumPay.length-1);
				fin=sumPay.substring(count+2,sumPay.length);
				
				document.forms(0).Cell1.S(16,9,0,chiao);
				document.forms(0).Cell1.S(17,9,0,fin);
			}
			else{//只有一位
				chiao=sumPay.substring(count+1,sumPay.length)
				document.forms(0).Cell1.S(16,9,0,chiao);
				document.forms(0).Cell1.S(17,9,0,"0");
				}	
		}else{
			document.forms(0).Cell1.S(16,9,0,chiao);
			document.forms(0).Cell1.S(17,9,0,fin);
		}
		sumPay=ChangeToBig(sumPay);
		
		document.forms(0).Cell1.S(3,5,0,"<%=dto.getOrgName()%>");
		document.forms(0).Cell1.S(4,8,0,sumPay);
		document.forms(0).Cell1.S(1,11,0,"<%=dto.getPayMonth()%>");
		document.forms(0).Cell1.S(2,11,0,"<%=dto.getPersonCounts()%>");
	    var totalLine=orgid.length;			    //总的行数
	    
		var totalPageLine=150;					//每页显示多少行
		var pageTotal=totalLine/totalPageLine;	//总共分几页
		var pageCurrent=1;						//当前页
		var completeline=0;		                //记录行的
		for(k = 0;k < totalLine; k++){
			if(k%totalPageLine==0&&k>0)
			{	
				
		     sumPay=ChangeToBig(sumPay);
		     document.forms(0).Cell1.S(3,5,0,"<%=dto.getOrgName()%>");
		     document.forms(0).Cell1.S(4,8,0,sumPay);
		     document.forms(0).Cell1.S(1,11,0,"<%=dto.getPayMonth()%>");
		     document.forms(0).Cell1.S(2,11,0,"<%=dto.getPersonCounts()%>");
		     pageCurrent++;	
			 completeline=k-2;				
			 document.forms(0).Cell1.insertSheetFromFile("<%=path%>/syscollection/paymng/report/personaddpaylist.cll",0,1,pageCurrent);
			 document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页")	
			}
			if(pageCurrent==1)
			{
			    
				document.forms(0).Cell1.S(1,k+2,1,notenum[k]);
				document.forms(0).Cell1.S(2,k+2,1,docnum[k]);
				document.forms(0).Cell1.S(3,k+2,1,orgid[k]);
				document.forms(0).Cell1.S(4,k+2,1,orgname[k]);
				document.forms(0).Cell1.S(5,k+2,1,paymonth[k]);
				document.forms(0).Cell1.S(6,k+2,1,person[k]);
				document.forms(0).Cell1.S(7,k+2,1,amount[k]);
				document.forms(0).Cell1.S(8,k+2,1,paydate[k]);
				document.forms(0).Cell1.S(9,k+2,1,paystatus[k]);

				
			}
			else{//向第一页后的所有页插数据
				 
				document.forms(0).Cell1.S(1,k-completeline,pageCurrent,notenum[k]);
				document.forms(0).Cell1.S(2,k-completeline,pageCurrent,docnum[k]);
				document.forms(0).Cell1.S(3,k-completeline,pageCurrent,orgid[k]);
				document.forms(0).Cell1.S(4,k-completeline,pageCurrent,orgname[k]);
				document.forms(0).Cell1.S(5,k-completeline,pageCurrent,paymonth[k]);
				document.forms(0).Cell1.S(6,k-completeline,pageCurrent,person[k]);
				document.forms(0).Cell1.S(7,k-completeline,pageCurrent,amount[k]);
				document.forms(0).Cell1.S(8,k-completeline,pageCurrent,paydate[k]);
				document.forms(0).Cell1.S(9,k-completeline,pageCurrent,paystatus[k]);
	
			}		
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
	function LoginRegister()//注册CELL
    { 
        alert(document.all('Cell1').Login( "username","" ,"04000234", "1231332223234"));
    }	
			
</script>
  <body onload = "load();" onContextmenu="return false"> 
    <form action="">
    <table align="center">
<tr><OBJECT id=Cell1 style= "LEFT:0px;WIDTH:700px;  TOP:0px;HEIGHT:400px" codeBase="http://192.168.1.44:8088/hafmis/CellWeb5.CAB#version=5,3,7,321" classid=clsid:3F166327-8030-4881-8BD2-EA25350E574A VIEWASTEXT><PARAM NAME="_Version" VALUE="65536"><PARAM NAME="_ExtentX" VALUE="10266"><PARAM NAME="_ExtentY" VALUE="7011"><PARAM NAME="_StockProps" VALUE= "0"></OBJECT></tr> 
<tr>
<input type="button" name="print" value = "打印预览" onclick = "printPreview();"/>
<INPUT id="Button1" onclick="Button1_onclick()" type="button" value="另存为Excel" name="Button1">
<INPUT id="Button1" onclick="Button2_onclick()" type="button" value="另存为pdf" name="Button1">
<INPUT id="Button3" style="WIDTH: 100px" onclick="Button3_onclick()" type="button" value="页面设置">
<INPUT id="Button3" style="WIDTH: 100px" onclick="Button4_onclick()" type="button" value="查找对话框">
<INPUT id="Button3" style="WIDTH: 100px" onclick="Button5_onclick()" type="button" value="excel导入">	
</table>
</form>
  </body>
</html>
