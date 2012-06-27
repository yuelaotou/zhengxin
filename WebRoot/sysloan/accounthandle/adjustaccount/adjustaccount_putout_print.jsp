<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic" %>
<%@ page import="org.xpup.hafmis.sysloan.loancallback.loancallback.form.LoancallbackTaAF"%>
<%@ include file="/checkUrl.jsp"%>
<%
String path = request.getContextPath();
String bizDate = (String)request.getAttribute("bizDate");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<script src="<%=path%>/js/common.js"></script>
  </head>
  <script type="text/javascript">
  	function load(){
  		loginReg();
	  	document.forms(0).Cell1.openfile("<%=path%>/sysloan/accounthandle/report/adjustaccountputout.cll","");
	    <%
    	    LoancallbackTaAF af =(LoancallbackTaAF)request.getAttribute("LoancallbackTaAF");
    	    String docNum=af.getDocNum();
    	    String noteNum=af.getNoteNum();
    	    String borrowerName=af.getBorrowerInfoDTO().getBorrowerName();
    	    String contractId=af.getBorrowerInfoDTO().getContractId();
    	    String loanKouAcc=af.getBorrowerInfoDTO().getLoanKouAcc();
    	    String loanAcc = af.getLoanAcc();
    	    String interestAcc = af.getInterestAcc();
    	    String bankName = af.getBankName();
    		String overOccurMoney = af.getOverOccurMoney().toString();
    		String makeOP = af.getMakeOP();
    		String clearOP = af.getClearOP();
    		String sumCorpus = af.getSumCorpus().toString();
    		String sumInterest = af.getSumInterest().toString();
    		String interest = af.getInterest().toString();
    		String overdueInterest = af.getOverdueInterest().toString();
    		String punishInterest = af.getPunishInterest().toString();
    		String bizType = af.getBizType();
    		String months = af.getMonths();
    		String monthYear = af.getMonthYear();
    		String realMoney = af.getRealMoney().toString();
    		String realCorpus = af.getRealCorpus().toString();
    		String realOverdueCorpus = af.getRealOverduCorpus().toString();
    		String sumMoney = af.getSumCorpus().add(af.getSumInterest()).toString();
	%>
		var makeP="<%=makeOP%>";
 	    var clearP="<%=clearOP%>";
 	    if(makeP=="null"){
 	    	makeP="";
 	    }
 	    if(clearP=="null"){
 	    	clearP="";
 	    }
		var sumPayC = <%=overOccurMoney%>;
		var chiao="0";
		var fin="0";
		sumPayC=""+sumPayC;
		var count=sumPayC.indexOf(".",0);
		var length;
		len=sumPayC.length;
		if(count==-1){
			length=len;
		}else{
			length=count;
		}
		if(sumPayC>0){
			for(var i=0;i<length;i++){//从后向前插入（从元起）
				var temp1=sumPayC.substring((length-i-1),length-i);
				document.forms(0).Cell1.S((16-i),9,0,temp1);
			}
			if(count!=-1){//计算小数后面
				if((len-count)>2)//两位
				{
					chiao=sumPayC.substring(count+1,sumPayC.length-1);
					fin=sumPayC.substring(count+2,sumPayC.length);	
					document.forms(0).Cell1.S(17,9,0,chiao);
					document.forms(0).Cell1.S(18,9,0,fin);
				}
				else{//只有一位
					chiao=sumPayC.substring(count+1,sumPayC.length)
					document.forms(0).Cell1.S(17,9,0,chiao);
					document.forms(0).Cell1.S(18,9,0,"0");
				}	
			}else{
				document.forms(0).Cell1.S(17,9,0,chiao);
				document.forms(0).Cell1.S(18,9,0,fin);
			}
		}else if(sumPayC<0){
			for(var i=0;i<length;i++){//从后向前插入（从元起）
				var temp1=sumPayC.substring((length-i-1),length-i);
				document.forms(0).Cell1.S((16-i),9,0,temp1);
			}
			if(count!=-1){//计算小数后面
				if((len-count)>2)//两位
				{
					chiao=sumPayC.substring(count+1,sumPayC.length-1);
					fin=sumPayC.substring(count+2,sumPayC.length);	
					document.forms(0).Cell1.S(17,9,0,chiao);
					document.forms(0).Cell1.S(18,9,0,fin);
				}
				else{//只有一位
					chiao=sumPayC.substring(count+1,sumPayC.length)
					document.forms(0).Cell1.S(17,9,0,chiao);
					document.forms(0).Cell1.S(18,9,0,"0");
				}	
			}else{
				document.forms(0).Cell1.S(17,9,0,chiao);
				document.forms(0).Cell1.S(18,9,0,fin);
			}
		}
		
			 sumPayC=ChangeToBig(sumPayC);
			 if(sumPayC=="整"){
			 	sumPayC="";
			 }
			 var docNum="<%=docNum%>";
			 if(docNum=="null"){
			 	docNum="";
			 }
			 var noteNum="<%=noteNum%>";
			 if(noteNum=="null"){
			 	noteNum="";
			 }
		     document.forms(0).Cell1.S(1,4,0,"<%=bizDate%>");
		     document.forms(0).Cell1.S(8,4,0,docNum);
		     document.forms(0).Cell1.S(15,4,0,noteNum);
		     document.forms(0).Cell1.S(3,5,0,"<%=borrowerName%>");
		     document.forms(0).Cell1.S(9,5,0,"<%=contractId%>");
		     document.forms(0).Cell1.S(3,6,0,"<%=loanKouAcc%>");
		     document.forms(0).Cell1.S(9,6,0,"<%=loanAcc%>");
		     document.forms(0).Cell1.S(3,7,0,"<%=bankName%>");
		     document.forms(0).Cell1.S(3,8,0,sumPayC);
		     document.forms(0).Cell1.S(2,10,0,"实还金额：<%=sumMoney%>   实收金额：<%=realMoney%>");
		     document.forms(0).Cell1.d(4,14,0,"<%=overOccurMoney%>");
		     document.forms(0).Cell1.d(6,14,0,"<%=realCorpus%>");
		     document.forms(0).Cell1.d(8,14,0,"<%=realOverdueCorpus%>");
		     document.forms(0).Cell1.S(4,16,0,makeP);
		     document.forms(0).Cell1.S(7,16,0,clearP);
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
  <body onload = "load();" >
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
