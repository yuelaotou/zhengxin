<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.util.List"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic"%>
<%@ page
	import="org.xpup.hafmis.syscollection.common.domain.entity.TranOutTail"%>
<%@ page
	import="org.xpup.hafmis.syscollection.common.domain.entity.TranOutHead"%>
<%@ page import="org.xpup.hafmis.syscollection.common.domain.entity.Emp"%>
<%@ page
	import="org.xpup.hafmis.syscollection.tranmng.tranout.form.TranTbPrintAF"%>
<%@ page import="java.math.BigDecimal"%>
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
	String bizDate = (String) request.getAttribute("bizDate");
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
	//document.forms(0).Cell1.openfile("<%=path%>/syscollection/tranmng/report/tranoutlist.cll","");
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
	var date_yg="";
	var traninacc="";
    var tranoutacc="";
	var outOrgOpenBank="";
	var inOrgOpenBank="";
	var noteNum="";
	var traninbankname="";
	var tranoutbankname="";
	var i=0;
	<%
    	    TranTbPrintAF tranTbPrintAF = (TranTbPrintAF)request.getAttribute("tranTbPrintAF");
    	    List taillist=tranTbPrintAF.getList();
    		String userName=(String) request.getAttribute("userName");
    	    TranOutHead tranOutHead = null;
    	    String outorgid=tranTbPrintAF.getOutOrgId();
    	    String outorgname=tranTbPrintAF.getOutOrgname();
    	    String inorgid=tranTbPrintAF.getInOrgId();
    	    String inorgname=tranTbPrintAF.getInOrgName();
    		String name_a="";
    		String tel_a="";
	%>
		outOrgOpenBank = "<%=tranTbPrintAF.getOutOrgOpenBank()%>";
		inOrgOpenBank = "<%=tranTbPrintAF.getInOrgOpenBank()%>";
		traninbankname = "<%=tranTbPrintAF.getInOrgCollBank() %>";
		tranoutbankname = "<%=tranTbPrintAF.getOutOrgCollBank()%>";
		tranoutacc = "<%=tranTbPrintAF.getOutPayBankAccNum()%>";
		traninacc = "<%=tranTbPrintAF.getInPayBankAccNum()%>";
		if(<%=tranTbPrintAF.getNote_num() == null%>){
			noteNum = "";
		}else{
			noteNum = "<%=tranTbPrintAF.getNote_num()%>";
		}
		date = "<%=tranTbPrintAF.getStartDate()%>";
			<%
  			for(int j=0;j<taillist.size();j++)
  			{
  				TranOutTail tranOutTails=(TranOutTail)taillist.get(j);
  				name_a=tranOutTails.getTranOutHead().getTranOutOrg().getOrgInfo().getTransactor().getName();
  				tel_a=tranOutTails.getTranOutHead().getTranOutOrg().getOrgInfo().getTransactor().getTelephone();
  				Emp emp_yg=tranOutTails.getEmp();
  				String empname_yg=emp_yg.getEmpInfo().getName();
  				BigDecimal biga=new BigDecimal("0.00");
  				
  				if(tranOutTails.getPreIntegral()!=null && !tranOutTails.getPreIntegral().equals("")){
  					biga=biga.add(tranOutTails.getPreIntegral());
  				}
  				if(tranOutTails.getPreIntegralSubA()!=null && !tranOutTails.getPreIntegralSubA().equals("")){
  					biga=biga.add(tranOutTails.getPreIntegralSubA());
  				}
  				if(tranOutTails.getPreIntegralSubB()!=null && !tranOutTails.getPreIntegralSubB().equals("")){
  					biga=biga.add(tranOutTails.getPreIntegralSubB());
  				}
  				if(tranOutTails.getPreIntegralSubC()!=null && !tranOutTails.getPreIntegralSubC().equals("")){
  					biga=biga.add(tranOutTails.getPreIntegralSubB());
  				}
  				if(tranOutTails.getPreIntegralSubD()!=null && !tranOutTails.getPreIntegralSubD().equals("")){
  					biga=biga.add(tranOutTails.getPreIntegralSubD());
  				}
  				if(tranOutTails.getPreIntegralSubE()!=null && !tranOutTails.getPreIntegralSubE().equals("")){
  					biga=biga.add(tranOutTails.getPreIntegralSubE());
  				}
  				if(tranOutTails.getPreIntegralSubF()!=null && !tranOutTails.getPreIntegralSubF().equals("")){
  					biga=biga.add(tranOutTails.getPreIntegralSubF());
  				}
  				if(tranOutTails.getPreIntegralSubG()!=null && !tranOutTails.getPreIntegralSubG().equals("")){
  					biga=biga.add(tranOutTails.getPreIntegralSubG());
  				}
  				if(tranOutTails.getPreIntegralSubH()!=null && !tranOutTails.getPreIntegralSubH().equals("")){
  					biga=biga.add(tranOutTails.getPreIntegralSubH());
  				}
  				if(tranOutTails.getPreIntegralSubI()!=null && !tranOutTails.getPreIntegralSubI().equals("")){
  					biga=biga.add(tranOutTails.getPreIntegralSubI());
  				}
  				if(tranOutTails.getPreIntegralSubJ()!=null && !tranOutTails.getPreIntegralSubJ().equals("")){
  					biga=biga.add(tranOutTails.getPreIntegralSubJ());
  				}
  				if(tranOutTails.getPreIntegralSubK()!=null && !tranOutTails.getPreIntegralSubK().equals("")){
  					biga=biga.add(tranOutTails.getPreIntegralSubK());
  				}
  				if(tranOutTails.getPreIntegralSubL()!=null && !tranOutTails.getPreIntegralSubL().equals("")){
  					biga=biga.add(tranOutTails.getPreIntegralSubL());
  				}
  				if(tranOutTails.getCurIntegral()!=null && !tranOutTails.getCurIntegral().equals("")){
  					biga=biga.add(tranOutTails.getCurIntegral());
  				}
  				if(tranOutTails.getCurIntegralSubA()!=null && !tranOutTails.getCurIntegralSubA().equals("")){
  					biga=biga.add(tranOutTails.getCurIntegralSubA());
  				}
  				if(tranOutTails.getCurIntegralSubB()!=null && !tranOutTails.getCurIntegralSubB().equals("")){
  					biga=biga.add(tranOutTails.getCurIntegralSubB());
  				}
  				if(tranOutTails.getCurIntegralSubC()!=null && !tranOutTails.getCurIntegralSubC().equals("")){
  					biga=biga.add(tranOutTails.getCurIntegralSubB());
  				}
  				if(tranOutTails.getCurIntegralSubD()!=null && !tranOutTails.getCurIntegralSubD().equals("")){
  					biga=biga.add(tranOutTails.getCurIntegralSubD());
  				}
  				if(tranOutTails.getCurIntegralSubE()!=null && !tranOutTails.getCurIntegralSubE().equals("")){
  					biga=biga.add(tranOutTails.getCurIntegralSubE());
  				}
  				if(tranOutTails.getCurIntegralSubF()!=null && !tranOutTails.getCurIntegralSubF().equals("")){
  					biga=biga.add(tranOutTails.getCurIntegralSubF());
  				}
  				if(tranOutTails.getCurIntegralSubG()!=null && !tranOutTails.getCurIntegralSubG().equals("")){
  					biga=biga.add(tranOutTails.getCurIntegralSubG());
  				}
  				if(tranOutTails.getCurIntegralSubH()!=null && !tranOutTails.getCurIntegralSubH().equals("")){
  					biga=biga.add(tranOutTails.getCurIntegralSubH());
  				}
  				if(tranOutTails.getCurIntegralSubI()!=null && !tranOutTails.getCurIntegralSubI().equals("")){
  					biga=biga.add(tranOutTails.getCurIntegralSubI());
  				}
  				if(tranOutTails.getCurIntegralSubJ()!=null && !tranOutTails.getCurIntegralSubJ().equals("")){
  					biga=biga.add(tranOutTails.getCurIntegralSubJ());
  				}
  				if(tranOutTails.getCurIntegralSubK()!=null && !tranOutTails.getCurIntegralSubK().equals("")){
  					biga=biga.add(tranOutTails.getCurIntegralSubK());
  				}
  				if(tranOutTails.getCurIntegralSubL()!=null && !tranOutTails.getCurIntegralSubL().equals("")){
  					biga=biga.add(tranOutTails.getCurIntegralSubL());
  				}
  				
 	%>
 	            integral[i]="<%=biga%>";
				empid[i]=format("<%=tranOutTails.getEmpId().toString()%>")+"<%=tranOutTails.getEmpId().toString()%>";
				if(<%=tranOutTails.getTranin_empid().intValue() == 0%>){
					inempid[i]="";
				}else{
					inempid[i]=format("<%=tranOutTails.getTranin_empid().toString()%>")+"<%=tranOutTails.getTranin_empid().toString()%>";
				}
				date_yg="<%=tranOutTails.getEmp().getOrgPayMonth()%>";
 	            nameList[i]="<%=tranOutTails.getEmpName()%>";
				cardNum[i]="<%=tranOutTails.getEmp().getEmpInfo().getCardNum()%>";
				tranoutsum[i]="<%=tranOutTails.getSumMoney()%>";  
 	          	num[i]=i+1;
 	          	reason[i]="<%=tranOutTails.getTranReason()%>";    
 	          	i++; 
 	<%
 			}
 	%>

	    var totalLine=nameList.length;			//总的行数 数组的长度
		var totalPageLine=10;					//每页显示多少行
		var iPage = getInt(totalLine,totalPageLine);
		var pageCurrent=0;						//当前页
		var completeline=0;						//记录行的
		var moneys=0;
		var integrals=0;

			    for(var g=0;g<tranoutsum.length;g++){
			    	moneys=moneys+parseFloat(tranoutsum[g]);
			    }
			    
		var sumPay = moneys;
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
		     document.forms(0).Cell1.S(12,3,0,formatTen("<%=outorgid%>")+"<%=outorgid%>");
		     document.forms(0).Cell1.S(12,2,0,"<%=outorgname%>");
		     document.forms(0).Cell1.S(12,5,0,tranoutbankname);
		     //document.forms(0).Cell1.S(12,4,0,tranoutacc);
		     document.forms(0).Cell1.S(3,7,0,sumPay);
		     document.forms(0).Cell1.S(5,3,0,formatTen("<%=inorgid%>")+"<%=inorgid%>");
		     document.forms(0).Cell1.S(5,2,0,"<%=inorgname%>");
		     document.forms(0).Cell1.S(5,5,0,traninbankname);
		     //document.forms(0).Cell1.S(5,4,0,traninacc);
		     //document.forms(0).Cell1.S(4,6,pageCurrent+1,empName[0]);
		     //document.forms(0).Cell1.S(7,6,pageCurrent+1,cardNum[0]);
		     //document.forms(0).Cell1.S(16,6,pageCurrent+1,tranreason);
		     document.forms(0).Cell1.S(17,1,0,noteNum);
		     
		     document.forms(0).Cell1.S(7,1,0,date.substring(0,4));
		     document.forms(0).Cell1.S(8,1,0,date.substring(4,6));
		     document.forms(0).Cell1.S(10,1,0,date.substring(6,8));
		     document.forms(0).Cell1.S(6,9,0,"   "+date_yg.substring(0,4)+"    "+date_yg.substring(4,6));
		     document.forms(0).Cell1.S(5,10,0,date.substring(0,4)+"    "+date.substring(4,6));
				
				//document.forms(0).Cell1.PrintSetSheetOpt(3);
 				//document.forms(0).Cell1.PrintSetPrintRange(1,0);
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
