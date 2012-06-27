<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.util.List"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic"%>
<%@ page
	import="org.xpup.hafmis.syscollection.common.domain.entity.TranOutTail"%>
<%@ page
	import="org.xpup.hafmis.syscollection.common.domain.entity.TranOutHead"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	String url = (String) request.getAttribute("URL");
	if (url == null) {
		url = "tran_showFindTbAC.do";
	}
	String bank = "";
	if (request.getAttribute("AA101bank") != null) {
		bank = (String) request.getAttribute("AA101bank");
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
	document.forms(0).Cell1.openfile("<%=path%>/syscollection/tranmng/report/tranout_yg.cll","");
	//document.forms(0).Cell1.insertSheetFromFile("<%=path%>/syscollection/tranmng/report/tranoutlist.cll",0,1,1);
    //document.forms(0).Cell1.setSheetLabel(1,"第2页");
    var empid=[];
    var empidin="";
    var nameList=[];
	var cardNum=[];
	var reason="";
	var notea="";
	var date_yg="";
	var tranoutmoney=[];
	var tranoutinterest=[];
	var tranoutsum=[];
	var traninempid=[];
	var i=0;
	<%
    	    List taillist=(List)request.getAttribute("tranTailcelllist");
    	    
		    String bizDate=(String) request.getAttribute("bizDate");
    		String userName=(String) request.getAttribute("userName");
    		String collectionBankName=(String) request.getAttribute("collectionBankName");
    		String collectionBankNamein=(String) request.getAttribute("collectionBankNamein");
    	    TranOutHead tranOutHead = null;
    	    String orgname="";
    	    String outBankid="";
    	    String outBankname="";
    	    String inBankid="";
    	    String inBankname="";
    		if(taillist != null && taillist.size()>0){
    			TranOutTail tranOutTail =(TranOutTail)taillist.get(0);
    			tranOutHead = tranOutTail.getTranOutHead();
    		}
    		if(tranOutHead.getTranInOrg()==null){
    			orgname="  ";
    		}else{
    			orgname=tranOutHead.getTranInOrg().getOrgInfo().getName();
    			if(tranOutHead.getTranInOrg().getOrgInfo().getPayBank()!=null){		
		    	    inBankid=tranOutHead.getTranInOrg().getOrgInfo().getPayBank().getAccountNum();
		    	    inBankname=tranOutHead.getTranInOrg().getOrgInfo().getPayBank().getName();
    			}
    		}
    		if(tranOutHead.getTranOutOrg().getOrgInfo().getPayBank()!=null){
    			outBankid=tranOutHead.getTranOutOrg().getOrgInfo().getPayBank().getAccountNum();
    			outBankname=tranOutHead.getTranOutOrg().getOrgInfo().getPayBank().getName();
    		}
    		String note=tranOutHead.getNoteNum();
    		if(note==null){
    			note="";
    		}
	%>
			<%
  			for(int j=0;j<taillist.size();j++)
  			{
  				TranOutTail tranOutTails=(TranOutTail)taillist.get(j);
  				Integer inempid=tranOutTails.getTranin_empid();
  				String inempiid="";
  				if(inempid==null || inempid.intValue()==0){
  					inempiid="";
  				}else{
  				inempiid=inempid.toString();
  				}
 	%>
 	            empidin="<%=inempiid%>";
				empid[i]="<%=tranOutTails.getEmpId()%>";
 	            nameList[i]="<%=tranOutTails.getEmpName()%>";
 	            <%if(tranOutTails.getTranReason()==null){%>
 	            reason="";
 	            <%}else{%>
 	            reason="<%=tranOutTails.getTranReason()%>";
 	            <%}%>
 	            date_yg="<%=tranOutTails.getEmp().getOrgPayMonth()%>";
				cardNum[i]="<%=tranOutTails.getEmp().getEmpInfo().getCardNum()%>";
				tranoutmoney[i]="<%=tranOutTails.getSumBalance()%>";
				tranoutinterest[i]="<%=tranOutTails.getSumInterest()%>";
				tranoutsum[i]="<%=tranOutTails.getSumMoney()%>"; 
				
				traninempid[i]="<%=inempiid%>"; 	            
 	          	i++; 
 	<%
 			}
 	%>
 		var date = "<%=bizDate%>";
		var sumPay = <%=tranOutHead.getSumSalary()%>;
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
			document.forms(0).Cell1.S((18-i),8,0,temp);
			if(i==6){
				document.forms(0).Cell1.S(11,8,0,temp);
			}
		}
		if(count!=-1){//计算小数后面
			if((len-count)>2)//两位
			{
				chiao=sumPay.substring(count+1,count+2);
				fin=sumPay.substring(count+2,count+3);
				
				document.forms(0).Cell1.S(19,8,0,chiao);
				document.forms(0).Cell1.S(20,8,0,fin);
			}
			else{//只有一位
				chiao=sumPay.substring(count+1,count+2)
				document.forms(0).Cell1.S(19,8,0,chiao);
				document.forms(0).Cell1.S(20,8,0,"0");
				}	
		}else{
			document.forms(0).Cell1.S(19,8,0,chiao);
			document.forms(0).Cell1.S(20,8,0,fin);
			}
			 sumPay=ChangeToBig(sumPay);
		     document.forms(0).Cell1.S(12,3,0,formatTen("<%=tranOutHead.getTranOutOrg().getId()%>")+"<%=tranOutHead.getTranOutOrg().getId()%>");
		     document.forms(0).Cell1.S(12,2,0,"<%=tranOutHead.getTranOutOrg().getOrgInfo().getName()%>");
		     document.forms(0).Cell1.S(12,5,0,"<%=collectionBankName%>");//-------
		     //-------
		     document.forms(0).Cell1.S(3,7,0,sumPay);
		     document.forms(0).Cell1.S(5,3,0,formatTen("<%=tranOutHead.getTranInOrg().getId()%>")+"<%=tranOutHead.getTranInOrg().getId()%>");
		     document.forms(0).Cell1.S(5,2,0,"<%=orgname%>");
		     document.forms(0).Cell1.S(5,5,0,"<%=collectionBankNamein%>");//------
		     if(<%=taillist.size()==1%>){
			     document.forms(0).Cell1.S(5,4,0,format(empidin+"")+empidin);
			     document.forms(0).Cell1.S(12,4,0,format(empid+"")+empid);
			     document.forms(0).Cell1.S(4,6,0,nameList[0]);
			     document.forms(0).Cell1.S(7,6,0,cardNum[0]);
			     document.forms(0).Cell1.S(16,6,0,reason);
			     document.forms(0).Cell1.S(6,9,0,"   "+date_yg.substring(0,4)+"    "+date_yg.substring(4,6));
			     document.forms(0).Cell1.S(5,10,0,date.substring(0,4)+"    "+date.substring(4,6));
		     }
		     document.forms(0).Cell1.S(17,1,0,"<%=tranOutHead.getDocNum()==null?"":tranOutHead.getDocNum()%>");
		     document.forms(0).Cell1.S(7,1,0,date.substring(0,4));
		     document.forms(0).Cell1.S(8,1,0,date.substring(4,6));
		     document.forms(0).Cell1.S(10,1,0,date.substring(6,8));
			 document.forms(0).Cell1.AllowExtend=false;
			 document.forms(0).Cell1.AllowDragdrop=false;
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
	<body onload="load();" onContextmenu="return false">
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
					<td>
					<td>
						<INPUT id="Button1" onclick="printsheet()" type="button"
							value=" 打印 " name="Button1">
					</td>
					<td>
						<INPUT id="Button1" onclick="Button1_onclick()" type="button"
							value="另存为Excel" name="Button1">
					<td>
					<td>
						<INPUT id="Button1" onclick="Button2_onclick()" type="button"
							value="另存为pdf" name="Button1">
					<td>
					<td>
						<INPUT id="Button3" style="WIDTH: 100px"
							onclick="Button3_onclick()" type="button" value="页面设置">
					<td>
					<td>
						<INPUT id="Button3" style="WIDTH: 100px"
							onclick="Button4_onclick()" type="button" value="查找对话框">
					<td>
					<td>
						<INPUT id="Button3" style="WIDTH: 100px"
							onclick="Button5_onclick()" type="button" value="excel导入">
					<td>
					<td>
						<INPUT id="Button3" onclick="location.href='<%=url%>'"
							type="button" value=" 返回 ">
					<td>
			</table>
		</form>
	</body>
</html>
