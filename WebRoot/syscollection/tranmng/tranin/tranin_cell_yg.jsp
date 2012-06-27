<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.List" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic" %>
<%@ page import="org.xpup.hafmis.syscollection.tranmng.tranin.form.*" %>
<%@ page import="org.xpup.hafmis.syscollection.common.domain.entity.TranInTail" %>
<%@ page import="org.xpup.hafmis.syscollection.common.domain.entity.TranOutTail" %>
<%@ include file="/checkUrl.jsp"%>
<%

   String path=request.getContextPath();
   String url=(String)request.getAttribute("URL");
   if(url == null){
   	url="showTraninListURLAC.do";
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
	document.forms(0).Cell1.openfile("/hafmis/syscollection/tranmng/report/tranout_yg.cll","");
    var empid=[];
    var reason=[];
    var nameList=[];
	var cardNum=[];
	var tempBalance=[];
    var tempInterest=[];
    var traninsum=[];	

	var i=0;

	<%
    	   TraninVidAF traninVidAF=(TraninVidAF)request.getAttribute("traninVidAF");
    	   	String bizDate=(String) request.getAttribute("bizDate");
    		String userName=(String) request.getAttribute("userName");
    		String collectionBankName=(String) request.getAttribute("collectionBankName");
    		String collectionBankName_out=(String) request.getAttribute("collectionBankName_out");
    		String collectionBankId=(String) request.getAttribute("collectionBankId");
    		String collectionBankId_out=(String) request.getAttribute("collectionBankId_out");
    		String m=traninVidAF.getSumTranInAmount().toString();
	%>
			<%
    		List list=traninVidAF.getList();
    		List lista=traninVidAF.getLista();
  			TranInTail tranInTail=null;
  			for(int j=0;j<list.size();j++)
  			{
  				tranInTail=(TranInTail)list.get(j);
  				TranOutTail tranOutTail=(TranOutTail)lista.get(j);
 	%>
 	            
 	            if(<%=tranInTail.getEmpId()==null || tranInTail.getEmpId().intValue()==0 %>){
 	            	empid[i]="";
 	            }else{
					empid[i]=format("<%=tranInTail.getEmpId() %>")+"<%=tranInTail.getEmpId() %>";
				}
 	            <%if(tranOutTail.getTranReason()==null){%>
 	            reason[i]="";
 	            <%}else{%>
 	            reason[i]="<%=tranOutTail.getTranReason()%>";
 	            <%}%>
 	            nameList[i]="<%=tranInTail.getName() %>";
				cardNum[i]="<%=tranInTail.getCardNum()%>";
				tempBalance[i]="<%=tranInTail.getCurBalance().add(tranInTail.getPreBalance()) %>";
				tempInterest[i]="<%=tranInTail.getCurInterest().add(tranInTail.getPreInterest()) %>";
				traninsum[i]=parseFloat(tempBalance[i])+parseFloat(tempInterest[i]);
 	          	i++; 
 	<%
 			}
 			TranInTail tranInTail_yg=(TranInTail)list.get(0);
 			
 			String note=tranInTail_yg.getTranInHead().getNoteNum();
    		if(note==null){
    			note="";
    		}
 	%>
 	    var date = "<%=bizDate%>";
		var sumPay = "<%=traninVidAF.getSumTranInAmount().toString() %>";
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
			document.forms(0).Cell1.S((15-i),11,0,temp);
		}
		if(count!=-1){//计算小数后面
			if((len-count)>2)//两位
			{
				chiao=sumPay.substring(count+1,sumPay.length-1);
				fin=sumPay.substring(count+2,sumPay.length);
				
				document.forms(0).Cell1.S(16,11,0,chiao);
				document.forms(0).Cell1.S(17,11,0,fin);
			}
			else{//只有一位
				chiao=sumPay.substring(count+1,sumPay.length)
				document.forms(0).Cell1.S(16,11,0,chiao);
				document.forms(0).Cell1.S(17,11,0,"0");
				}	
		}else{
			document.forms(0).Cell1.S(16,11,0,chiao);
			document.forms(0).Cell1.S(17,11,0,fin);
			}
			 sumPay=ChangeToBig(sumPay);
			 
		     document.forms(0).Cell1.S(3,5,0,formatTen("<%=traninVidAF.getOutOrgId()%>")+"<%=traninVidAF.getOutOrgId()%>");
		     document.forms(0).Cell1.S(3,6,0,"<%=traninVidAF.getOutOrgName()%>");
		     document.forms(0).Cell1.S(3,7,0,"<%=collectionBankName_out%>");
		     document.forms(0).Cell1.S(3,8,0,"<%=collectionBankId_out%>");
		     document.forms(0).Cell1.S(4,10,0,sumPay);
		     document.forms(0).Cell1.S(8,5,0,formatTen("<%=traninVidAF.getInOrgId()%>")+"<%=traninVidAF.getInOrgId()%>");
		     document.forms(0).Cell1.S(8,6,0,"<%=traninVidAF.getInOrgName()%>");
		     document.forms(0).Cell1.S(8,7,0,"<%=collectionBankName%>");
		     document.forms(0).Cell1.S(8,8,0,"<%=collectionBankId%>");
		     document.forms(0).Cell1.S(2,9,0,nameList[0]);
		     document.forms(0).Cell1.S(6,9,0,cardNum[0]);
		     document.forms(0).Cell1.S(13,9,0,reason[0]);
		     document.forms(0).Cell1.S(13,4,0,"<%=note%>");
		     document.forms(0).Cell1.S(7,4,0,date.substring(0,4)+"年"+date.substring(4,6)+"月"+date.substring(6,8)+"日"+"  结算号:");
		     
	   
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
  <body onload = "load();" onContextmenu="return false"> 
    <form action="">
    <table align="center">
<tr><OBJECT id=Cell1 style= "LEFT:0px;WIDTH:700px;  TOP:0px;HEIGHT:400px" codeBase="http://192.168.1.44:8088/hafmis/CellWeb5.CAB#version=5,3,7,321" classid=clsid:3F166327-8030-4881-8BD2-EA25350E574A VIEWASTEXT><PARAM NAME="_Version" VALUE="65536"><PARAM NAME="_ExtentX" VALUE="10266"><PARAM NAME="_ExtentY" VALUE="7011"><PARAM NAME="_StockProps" VALUE= "0"></OBJECT></tr> 
<tr><td><input type="button" name="print" value = "打印预览" onclick = "printPreview();"/></td>
<td><INPUT id="Button1" onclick="Button1_onclick()" type="button" value="另存为Excel" name="Button1"></td>
<td><INPUT id="Button1" onclick="Button2_onclick()" type="button" value="另存为pdf" name="Button1"></td>
<td><INPUT id="Button3" style="WIDTH: 100px" onclick="Button3_onclick()" type="button" value="页面设置"></td>
<td><INPUT id="Button3" style="WIDTH: 100px" onclick="Button4_onclick()" type="button" value="查找对话框"></td>
<td><INPUT id="Button3" style="WIDTH: 100px" onclick="Button5_onclick()" type="button" value="excel导入"></td>	
<td><INPUT id="Button1" onclick="printsheet()" type="button" value=" 打印 " name="Button1"></td>
<td><INPUT id="Button3" onclick="location.href='<%=url%>'" type="button" value=" 返回 "></td>
</tr>
</table>
</form>
  </body>
</html>
