<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.List" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic" %>
<%@ page import="org.xpup.hafmis.syscollection.paymng.personaddpay.form.*" %>
<%@ page import="org.xpup.hafmis.syscollection.paymng.personaddpay.dto.*" %>
<%@ include file="/checkUrl.jsp"%>
<%

   String path=request.getContextPath();
   String url=(String)request.getAttribute("empaddpURL");
 %>
<html>
  <head>
  <script src="<%=path%>/js/common.js">
</script>
  </head>
  <script type="text/javascript">
	function load(){	
	loginReg();
	// document.forms(0).Cell1.openfile("<%=path%>/syscollection/paymng/report/empaddpay.cll","");
	document.forms(0).Cell1.openfile("<%=path%>/syscollection/paymng/report/monthpay1.cll","");
	document.forms(0).Cell1.insertSheetFromFile("<%=path%>/syscollection/paymng/report/empaddpaylist.cll",0,1,1);
    document.forms(0).Cell1.setSheetLabel(1,"第2页");
    
    var empid=[];
    var empname=[];
    var orgpaymoney=[];
    var emppaymoney=[];
    var paymoney=[];
    var beginmonth=[];
    var endmonth=[];
    var reason=[];
    var money=0;
    var userName="";
    var i=0;
    userName="<%=(String)request.getSession().getAttribute("userName")%>";	1
	<%
    		PersonAddPayAF personAddPayAF=(PersonAddPayAF) request.getSession().getAttribute("f");
    		List list=(List)personAddPayAF.getPersonAddPayList();
  		    PersonAddPayDTO dto=(PersonAddPayDTO)list.get(0);
  		    
  		    for(int j=0;j<list.size();j++)
  			{
  				PersonAddPayDTO dto1=(PersonAddPayDTO)list.get(j);
  				
 	%>
 		empid[i]="<%=dto1.getEmpId()%>";	
 		empname[i]="<%=dto1.getEmpName()%>";
 		// orgpaymoney[i]="<%=dto1.getOrgPaySum().toString()%>";
 		// emppaymoney[i]="<%=dto1.getEmpPaySum().toString()%>";
 		orgpaymoney[i]="<%=dto1.getEmpPaySum().toString()%>";
 		emppaymoney[i]="<%=dto1.getOrgPaySum().toString()%>";
 		paymoney[i]="<%=dto1.getAddPayAmount()%>";
 		money=money+<%=dto1.getAddPayAmount()%>;
 		beginmonth[i]="<%=dto1.getAddPayBeginYearMonth()%>";
 		endmonth[i]="<%=dto1.getAddPayEndYearMonth()%>";
 		reason[i]="<%=dto1.getAddPayReason()%>";
 		 	            
 	    i++; 
 	<%
 			}
 	%>
 	    var payWay="<%=personAddPayAF.getPayWay()%>";
	    var payKind="<%=personAddPayAF.getPayKind()%>";
		var sumPay = money;
		var orgname= "<%=dto.getOrgName()%>";
		var person="<%=list.size()%>";
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
			// document.forms(0).Cell1.S((15-i),9,0,temp);
			document.forms(0).Cell1.S((20-i),7,0,temp);
		}
		if(count!=-1){//计算小数后面
			if((len-count)>2)//两位
			{
				chiao=sumPay.substring(count+1,sumPay.length-1);
				fin=sumPay.substring(count+2,sumPay.length);
				
				// document.forms(0).Cell1.S(16,9,0,chiao);
				// document.forms(0).Cell1.S(17,9,0,fin);
				document.forms(0).Cell1.S(21,7,0,chiao);
				document.forms(0).Cell1.S(22,7,0,fin);
			}
			else{//只有一位
				chiao=sumPay.substring(count+1,sumPay.length)
				// document.forms(0).Cell1.S(16,9,0,chiao);
				// document.forms(0).Cell1.S(17,9,0,"0");
				document.forms(0).Cell1.S(21,7,0,chiao);
				document.forms(0).Cell1.S(22,7,0,"0");
				}	
		}else{
			// document.forms(0).Cell1.S(16,9,0,chiao);
			// document.forms(0).Cell1.S(17,9,0,fin);
			document.forms(0).Cell1.S(21,7,0,chiao);
			document.forms(0).Cell1.S(22,7,0,fin);
		}
		
		sumPay=ChangeToBig(sumPay);
		/**
		document.forms(0).Cell1.S(3,7,0,"<%=personAddPayAF.getBankid() %>");
		document.forms(0).Cell1.S(3,6,0,"<%=personAddPayAF.getBankname() %>");
		document.forms(0).Cell1.S(8,5,0,"<%=personAddPayAF.getOffice() %>");
		document.forms(0).Cell1.S(8,6,0,"<%=personAddPayAF.getCollbankname() %>");
		
		document.forms(0).Cell1.S(3,4,0,formatTen("<%=dto.getOrgId()%>")+"<%=dto.getOrgId()%>");
		document.forms(0).Cell1.S(3,5,0,"<%=dto.getOrgName()%>");
		document.forms(0).Cell1.S(4,8,0,sumPay);		
		document.forms(0).Cell1.S(2,11,0,"<%=list.size()%>");
		*/
		document.forms(0).Cell1.S(4,2,0,formatTen("<%=dto.getOrgId()%>")+"<%=dto.getOrgId()%>");
	    document.forms(0).Cell1.S(3,5,0,"<%=dto.getOrgName()%>");
		document.forms(0).Cell1.S(7,2,0,"<%=personAddPayAF.getBizDate().substring(0,4)%>");
        document.forms(0).Cell1.S(9,2,0,"<%=personAddPayAF.getBizDate().substring(4,6)%>");
        document.forms(0).Cell1.S(11,2,0,"<%=personAddPayAF.getBizDate().substring(6,8)%>");
		document.forms(0).Cell1.S(5,3,0,"<%=personAddPayAF.getReceivables_orgname()%>");
        document.forms(0).Cell1.S(5,4,0,"<%=personAddPayAF.getReceivables_bank_acc()%>");
        document.forms(0).Cell1.S(5,5,0,"<%=personAddPayAF.getReceivables_bank_name()%>");
        document.forms(0).Cell1.S(14,3,0,"<%=personAddPayAF.getPayment_orgname()%>");
        document.forms(0).Cell1.S(14,4,0,"<%=personAddPayAF.getPayment_bank_acc()%>");
        document.forms(0).Cell1.S(14,5,0,"<%=personAddPayAF.getPayment_bank_name()%>");
        document.forms(0).Cell1.S(16,11,0,"<%=list.size()%>");
        document.forms(0).Cell1.S(19,11,0,money); 
        document.forms(0).Cell1.S(17,9,0, beginmonth[0].substr(4,5)+"-"+endmonth[0].substr(4,5));
         if(payWay==1)
        {
          document.forms(0).Cell1.S(4,8,0,"√");
        }
        if(payWay==2)
        {
           document.forms(0).Cell1.S(6,8,0,"√");
        }
         if(payWay==3)
        {
           document.forms(0).Cell1.S(7,8,0,"√");
        }
        if(payKind==1)
        {
           document.forms(0).Cell1.S(13,8,0,"√");
        }
        if(payKind==2)
        {
           document.forms(0).Cell1.S(16,8,0,"√");
        }
        if(payKind==3)
        {
           document.forms(0).Cell1.S(19,8,0,"√");
        }
		//
	    var totalLine=empid.length;			    //总的行数
		var totalPageLine=42;					//每页显示多少行
		var pageTotal=totalLine/totalPageLine;	//总共分几页
		var pageCurrent=1;						//当前页
		var completeline=0;		                //记录行的
		var orgsum=0;                           //单位合计
		var empsum=0;                           //职工合计
		var moneytotal=0;						//总的合计
		for(k = 0;k < totalLine; k++){
			if(k%totalPageLine==0&&k>0)
			{	
			 			
				document.forms(0).Cell1.S(1,44,pageCurrent,"本页小计");
				// document.forms(0).Cell1.SetFormula (3, 44, pageCurrent, "Sum(C4:C"+(totalPageLine+3)+")" );
				// document.forms(0).Cell1.SetFormula (4, 44, pageCurrent, "Sum(D4:D"+(totalPageLine+3)+")" );
				// document.forms(0).Cell1.SetFormula (5, 44, pageCurrent, "Sum(E4:E"+(totalPageLine+3)+")" );
			    document.forms(0).Cell1.SetFormula (4, totalPageLine+4, pageCurrent, "Sum(C4:C"+(totalPageLine+3)+")" );
				document.forms(0).Cell1.SetFormula (5, totalPageLine+4, pageCurrent, "Sum(D4:D"+(totalPageLine+3)+")" );
			    document.forms(0).Cell1.SetFormula (6, totalPageLine+4, pageCurrent, "Sum(E4:E"+(totalPageLine+3)+")" );
				document.forms(0).Cell1.DeleteRow(totalPageLine+5,(totalPageLine+6)-(totalPageLine+4),pageCurrent);
		     pageCurrent++;	
			 completeline=k-2;				
			 document.forms(0).Cell1.insertSheetFromFile("<%=path%>/syscollection/paymng/report/empaddpaylist.cll",0,1,pageCurrent);
			 document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页")	
			}
			if(pageCurrent==1)
			{
			     document.forms(0).Cell1.S(2,2,1,"<%=dto.getOrgName()%>");
			    document.forms(0).Cell1.S(7,2,1,formatTen("<%=dto.getOrgId()%>")+"<%=dto.getOrgId()%>");
				document.forms(0).Cell1.S(1,k+4,1,empid[k]);
				document.forms(0).Cell1.S(2,k+4,1,empname[k]);
				document.forms(0).Cell1.d(3,k+4,1,orgpaymoney[k]);
				document.forms(0).Cell1.d(4,k+4,1,emppaymoney[k]);
				document.forms(0).Cell1.d(5,k+4,1,paymoney[k]);
				document.forms(0).Cell1.S(6,k+4,1,beginmonth[k]);
				document.forms(0).Cell1.S(7,k+4,1,endmonth[k]);
				document.forms(0).Cell1.S(8,k+4,1,reason[k]);
				orgsum=orgsum+parseFloat(orgpaymoney[k]);
				empsum=empsum+parseFloat(emppaymoney[k]);
				moneytotal=moneytotal+parseFloat(paymoney[k]);
	
				
			}
			else{//向第一页后的所有页插数据
		        document.forms(0).Cell1.S(2,2,1,"<%=dto.getOrgName()%>");
			    document.forms(0).Cell1.S(7,2,1,formatTen("<%=dto.getOrgId()%>")+"<%=dto.getOrgId()%>");
				document.forms(0).Cell1.S(1,k-completeline,pageCurrent,empid[k]);
				document.forms(0).Cell1.S(2,k-completeline,pageCurrent,empname[k]);
				document.forms(0).Cell1.d(3,k-completeline,pageCurrent,orgpaymoney[k]);
				document.forms(0).Cell1.d(4,k-completeline,pageCurrent,emppaymoney[k]);
				document.forms(0).Cell1.d(5,k-completeline,pageCurrent,paymoney[k]);
				document.forms(0).Cell1.S(6,k-completeline,pageCurrent,beginmonth[k]);
				document.forms(0).Cell1.S(7,k-completeline,pageCurrent,endmonth[k]);
				document.forms(0).Cell1.S(8,k-completeline,pageCurrent,reason[k]);
				orgsum=orgsum+parseFloat(orgpaymoney[k]);
				empsum=empsum+parseFloat(emppaymoney[k]);
				moneytotal=moneytotal+parseFloat(paymoney[k]);

			}		
		}
		if(completeline==0)//查询出来的记录可以在一页显示的时候会进入
				{
					/**
					document.forms(0).Cell1.S(1,totalLine+4,pageCurrent,"本页小计");
					document.forms(0).Cell1.SetFormula (3, totalLine+4, pageCurrent, "Sum(C4:C"+(totalLine+3)+")" );//,loopcell() > 5
					document.forms(0).Cell1.SetFormula (4, totalLine+4, pageCurrent, "Sum(D4:D"+(totalLine+3)+")" );//,loopcell() > 5
					document.forms(0).Cell1.SetFormula (5, totalLine+4, pageCurrent, "Sum(E4:E"+(totalLine+3)+")" );//,loopcell() > 5
					document.forms(0).Cell1.S(1,totalLine+5,pageCurrent,"总计");
					document.forms(0).Cell1.d(3,totalLine+5,pageCurrent,orgsum);
					document.forms(0).Cell1.d(4,totalLine+5,pageCurrent,empsum);
					document.forms(0).Cell1.d(5,totalLine+5,pageCurrent,moneytotal);
					document.forms(0).Cell1.DeleteRow(totalLine+6,totalPageLine-(totalLine+5),pageCurrent);
					document.forms(0).Cell1.ReDraw();
					*/
					
					document.forms(0).Cell1.S(1,totalLine+4,pageCurrent,"本页小计");
					document.forms(0).Cell1.SetFormula (4, totalLine+4, pageCurrent, "Sum(C4:C"+(totalLine+3)+")" );//,loopcell() > 5
					document.forms(0).Cell1.SetFormula (5, totalLine+4, pageCurrent, "Sum(D4:D"+(totalLine+3)+")" );//,loopcell() > 5
					document.forms(0).Cell1.SetFormula (6, totalLine+4, pageCurrent, "Sum(E4:E"+(totalLine+3)+")" );//,loopcell() > 5
					document.forms(0).Cell1.S(1,totalLine+5,pageCurrent,"总计");
					document.forms(0).Cell1.d(3,totalLine+5,pageCurrent,orgsum);
					document.forms(0).Cell1.d(4,totalLine+5,pageCurrent,empsum);
					document.forms(0).Cell1.d(5,totalLine+5,pageCurrent,moneytotal);
					
					
					document.forms(0).Cell1.s(1,totalLine+6,pageCurrent,"归集银行");
					document.forms(0).Cell1.MergeCells(2,totalLine+6,3,totalLine+6);
					document.forms(0).Cell1.s(2,totalLine+6,pageCurrent,"<%=personAddPayAF.getCollbankname() %>");
					
					document.forms(0).Cell1.s(4,totalLine+6,pageCurrent,"制表人");
					document.forms(0).Cell1.s(5,totalLine+6,pageCurrent,userName);
					
					document.forms(0).Cell1.s(6,totalLine+6,pageCurrent,"操作日期");
					document.forms(0).Cell1.s(7,totalLine+6,pageCurrent,"<%=personAddPayAF.getBizDate()%>");
					document.forms(0).Cell1.DeleteRow(totalLine+7,(totalPageLine+6)-(totalLine+6),pageCurrent);
					document.forms(0).Cell1.ReDraw();
				}
				else
				{   
				  /**
					document.forms(0).Cell1.S(1,totalLine-completeline+2,pageCurrent,"本页小计");
					document.forms(0).Cell1.SetFormula (3, totalLine-completeline+2, pageCurrent, "Sum(C4:C"+(totalLine-(completeline-1))+")" );
					document.forms(0).Cell1.SetFormula (4, totalLine-completeline+2, pageCurrent, "Sum(D4:D"+(totalLine-(completeline-1))+")" );
					document.forms(0).Cell1.SetFormula (5, totalLine-completeline+2, pageCurrent, "Sum(E4:E"+(totalLine-(completeline-1))+")" );
					document.forms(0).Cell1.S(1,totalLine-completeline+3,pageCurrent,"总计");
					document.forms(0).Cell1.d(3,totalLine-completeline+3,pageCurrent,orgsum);
					document.forms(0).Cell1.d(4,totalLine-completeline+3,pageCurrent,empsum);
					document.forms(0).Cell1.d(5,totalLine-completeline+3,pageCurrent,moneytotal);
					document.forms(0).Cell1.DeleteRow(totalLine-completeline+4,totalPageLine-(totalLine-completeline+3),pageCurrent);
					document.forms(0).Cell1.ReDraw();*/
					
					document.forms(0).Cell1.S(1,totalLine-completeline+2,pageCurrent,"本页小计");
					document.forms(0).Cell1.SetFormula (3, totalLine-completeline+2, pageCurrent, "Sum(C4:C"+(totalLine-(completeline-1))+")" );
					document.forms(0).Cell1.SetFormula (4, totalLine-completeline+2, pageCurrent, "Sum(D4:D"+(totalLine-(completeline-1))+")" );
					document.forms(0).Cell1.SetFormula (5, totalLine-completeline+2, pageCurrent, "Sum(E4:E"+(totalLine-(completeline-1))+")" );
					document.forms(0).Cell1.S(1,totalLine-completeline+3,pageCurrent,"总计");
					document.forms(0).Cell1.d(3,totalLine-completeline+3,pageCurrent,orgsum);
					document.forms(0).Cell1.d(4,totalLine-completeline+3,pageCurrent,empsum);
					document.forms(0).Cell1.d(5,totalLine-completeline+3,pageCurrent,moneytotal);
					
					document.forms(0).Cell1.s(1,totalLine-completeline+4,pageCurrent,"归集银行");
					document.forms(0).Cell1.MergeCells(2,totalLine-completeline+4,3,totalLine-completeline+4);
					document.forms(0).Cell1.s(2,totalLine-completeline+4,pageCurrent,"<%=personAddPayAF.getCollbankname() %>");
					
					document.forms(0).Cell1.s(4,totalLine-completeline+4,pageCurrent,"制表人");
					document.forms(0).Cell1.s(5,totalLine-completeline+4,pageCurrent,userName);
					
					document.forms(0).Cell1.s(6,totalLine-completeline+4,pageCurrent,"操作日期");
					document.forms(0).Cell1.s(7,totalLine-completeline+4,pageCurrent,"<%=personAddPayAF.getBizDate()%>");
					document.forms(0).Cell1.DeleteRow(totalLine-completeline+5,totalPageLine-(totalLine-completeline-2),pageCurrent);
				}	
		//
		document.forms(0).Cell1.SetCurSheet(0);
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
<td><INPUT id="Button3" onclick="location.href='<%=url%>'" type="button" value=" 返回 "></td>	
</table>
</form>
  </body>
</html>
