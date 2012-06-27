<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.List" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic" %>
<%@ page import="org.xpup.hafmis.syscollection.common.domain.entity.TranOutTail" %>
<%@ page import="org.xpup.hafmis.syscollection.tranmng.tranout.form.TranTbPrintAF" %>
<%@ page import="org.xpup.hafmis.syscollection.common.domain.entity.Emp" %>
<%@ page import="java.math.BigDecimal" %>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	String url = (String)request.getAttribute("url");
	if(url==null){
		url = "tran_showFindTbAC.do";
	}
	String bizDate = (String)request.getAttribute("bizDate");
	String docNum = (String)request.getAttribute("docNum");
 %>
<html>
<head>
<script src="<%=path%>/js/common.js">
</script>
</head>
  <script type="text/javascript">
	function load(){	
	loginReg();
	document.forms(0).Cell1.openfile("<%=path%>/syscollection/tranmng/report/tranout_ws.cll","");
	var inOrgId="";
	var inOrgName="";
	var inOrgOpenBank="";
	var outOrgId="";
	var outOrgName="";
	var outOrgOpenBank="";
	var docNum="";
	var noteNum="";
    var tranoutacc="";
    var salary="";
    var balance="";
    var integral="";
    var empId=[];
    var empName=[];
	var cardNum=[];
	var tranoutsum=[];
	var bigtranoutsum=[];
	var traninacc="";
	var traninbankname="";
	var tranoutbankname="";
	var tranin_empid="";
	var tranreason=[];
	var date="";
	var i=0;
	<%
   	    TranTbPrintAF tranTbPrintAF = (TranTbPrintAF)request.getAttribute("tranTbPrintAF");
 	%>
 		
 		inOrgId = "<%=tranTbPrintAF.getInOrgId() %>";
		traninacc = "<%=tranTbPrintAF.getInPayBankAccNum()%>";
		inOrgName = "<%=tranTbPrintAF.getInOrgName() %>";
		inOrgOpenBank = "<%=tranTbPrintAF.getInOrgOpenBank() %>";
		tranoutacc = "<%=tranTbPrintAF.getOutPayBankAccNum() %>";
		traninbankname = "<%=tranTbPrintAF.getInOrgCollBank() %>";
		tranoutbankname = "<%=tranTbPrintAF.getOutOrgCollBank()%>";
		outOrgId = "<%=tranTbPrintAF.getOutOrgId() %>";    
		outOrgName = "<%=tranTbPrintAF.getOutOrgname() %>";
		outOrgOpenBank = "<%=tranTbPrintAF.getOutOrgOpenBank() %>";
		docNum = "<%=tranTbPrintAF.getDoc_num() %>";
		if(<%=tranTbPrintAF.getNote_num()==null %>){
			noteNum = "";
		}else{
			noteNum = "<%=tranTbPrintAF.getNote_num() %>";
		}
		date = "<%=tranTbPrintAF.getStartDate() %>";
	<% 
		List list  = tranTbPrintAF.getList();
		int size=1;
		if(list.size()>1){
			size=2;
		}
		for(int j =0;j<list.size();j++){
			TranOutTail tail = (TranOutTail)list.get(j);
				Emp emp_yg=tail.getEmp();
  				BigDecimal biga=new BigDecimal("0.00");
  				
  				if(tail.getPreIntegral()!=null && !tail.getPreIntegral().equals("")){
  					biga=biga.add(tail.getPreIntegral());
  				}
  				if(tail.getPreIntegralSubA()!=null && !tail.getPreIntegralSubA().equals("")){
  					biga=biga.add(tail.getPreIntegralSubA());
  				}
  				if(tail.getPreIntegralSubB()!=null && !tail.getPreIntegralSubB().equals("")){
  					biga=biga.add(tail.getPreIntegralSubB());
  				}
  				if(tail.getPreIntegralSubC()!=null && !tail.getPreIntegralSubC().equals("")){
  					biga=biga.add(tail.getPreIntegralSubB());
  				}
  				if(tail.getPreIntegralSubD()!=null && !tail.getPreIntegralSubD().equals("")){
  					biga=biga.add(tail.getPreIntegralSubD());
  				}
  				if(tail.getPreIntegralSubE()!=null && !tail.getPreIntegralSubE().equals("")){
  					biga=biga.add(tail.getPreIntegralSubE());
  				}
  				if(tail.getPreIntegralSubF()!=null && !tail.getPreIntegralSubF().equals("")){
  					biga=biga.add(tail.getPreIntegralSubF());
  				}
  				if(tail.getPreIntegralSubG()!=null && !tail.getPreIntegralSubG().equals("")){
  					biga=biga.add(tail.getPreIntegralSubG());
  				}
  				if(tail.getPreIntegralSubH()!=null && !tail.getPreIntegralSubH().equals("")){
  					biga=biga.add(tail.getPreIntegralSubH());
  				}
  				if(tail.getPreIntegralSubI()!=null && !tail.getPreIntegralSubI().equals("")){
  					biga=biga.add(tail.getPreIntegralSubI());
  				}
  				if(tail.getPreIntegralSubJ()!=null && !tail.getPreIntegralSubJ().equals("")){
  					biga=biga.add(tail.getPreIntegralSubJ());
  				}
  				if(tail.getPreIntegralSubK()!=null && !tail.getPreIntegralSubK().equals("")){
  					biga=biga.add(tail.getPreIntegralSubK());
  				}
  				if(tail.getPreIntegralSubL()!=null && !tail.getPreIntegralSubL().equals("")){
  					biga=biga.add(tail.getPreIntegralSubL());
  				}
  				if(tail.getCurIntegral()!=null && !tail.getCurIntegral().equals("")){
  					biga=biga.add(tail.getCurIntegral());
  				}
  				if(tail.getCurIntegralSubA()!=null && !tail.getCurIntegralSubA().equals("")){
  					biga=biga.add(tail.getCurIntegralSubA());
  				}
  				if(tail.getCurIntegralSubB()!=null && !tail.getCurIntegralSubB().equals("")){
  					biga=biga.add(tail.getCurIntegralSubB());
  				}
  				if(tail.getCurIntegralSubC()!=null && !tail.getCurIntegralSubC().equals("")){
  					biga=biga.add(tail.getCurIntegralSubB());
  				}
  				if(tail.getCurIntegralSubD()!=null && !tail.getCurIntegralSubD().equals("")){
  					biga=biga.add(tail.getCurIntegralSubD());
  				}
  				if(tail.getCurIntegralSubE()!=null && !tail.getCurIntegralSubE().equals("")){
  					biga=biga.add(tail.getCurIntegralSubE());
  				}
  				if(tail.getCurIntegralSubF()!=null && !tail.getCurIntegralSubF().equals("")){
  					biga=biga.add(tail.getCurIntegralSubF());
  				}
  				if(tail.getCurIntegralSubG()!=null && !tail.getCurIntegralSubG().equals("")){
  					biga=biga.add(tail.getCurIntegralSubG());
  				}
  				if(tail.getCurIntegralSubH()!=null && !tail.getCurIntegralSubH().equals("")){
  					biga=biga.add(tail.getCurIntegralSubH());
  				}
  				if(tail.getCurIntegralSubI()!=null && !tail.getCurIntegralSubI().equals("")){
  					biga=biga.add(tail.getCurIntegralSubI());
  				}
  				if(tail.getCurIntegralSubJ()!=null && !tail.getCurIntegralSubJ().equals("")){
  					biga=biga.add(tail.getCurIntegralSubJ());
  				}
  				if(tail.getCurIntegralSubK()!=null && !tail.getCurIntegralSubK().equals("")){
  					biga=biga.add(tail.getCurIntegralSubK());
  				}
  				if(tail.getCurIntegralSubL()!=null && !tail.getCurIntegralSubL().equals("")){
  					biga=biga.add(tail.getCurIntegralSubL());
  				}
			
	%>
			integral="<%=biga%>";
			if(<%=tail.getTranin_empid().intValue()==0%>){
				tranin_empid="";
			}else{
				tranin_empid=format("<%=tail.getTranin_empid()%>")+"<%=tail.getTranin_empid()%>";
			}
			balance="<%=tail.getPreBalance().add(tail.getCurBalance()).add(tail.getCurInterest()).add(tail.getPreInterest())%> ";
			salary="<%=tail.getEmp().getSalaryBase() %> ";
			empId[i]=format("<%=tail.getEmp().getEmpId() %> ")+"<%=tail.getEmp().getEmpId() %> ";
			empName[i]="<%=tail.getEmp().getEmpInfo().getName() %> ";
			cardNum[i]="<%=tail.getEmp().getEmpInfo().getCardNum() %>";
			tranreason[i]="<%=tail.getTranReason() %>";
			tranoutsum[i]="<%=tail.getSumMoney() %>";
			i++;
 	<%
 		}
 	%>   
 		var totalLine=empName.length;			//总的行数
		var totalPageLine=1;					//每页显示多少行
		var pageTotal=totalLine/totalPageLine;	//总共分几页
		var pageCurrent=0;						//当前页
		var completeline=0;						//记录行的
		var moneytotal=0;	
		var sumMoney=0;
				var chiao="0";
				var fin="0";
				for(k=0;k<tranoutsum.length;k++){
					sumMoney=eval(sumMoney)+eval(tranoutsum[k]);
				}
				sumMoney=sumMoney+"";
				
				document.forms(0).Cell1.S(3,5,0,outOrgName);
			    document.forms(0).Cell1.S(3,6,0,formatTen(outOrgId)+outOrgId);
				document.forms(0).Cell1.S(3,7,0 ,outOrgOpenBank);
				document.forms(0).Cell1.S(3,8,0,tranoutacc);
				document.forms(0).Cell1.S(3,9,0,tranoutbankname);
			    document.forms(0).Cell1.S(9,5,0,inOrgName);
				document.forms(0).Cell1.S(9,6,0,formatTen(inOrgId)+inOrgId);
				document.forms(0).Cell1.S(9,7,0 ,inOrgOpenBank);
				document.forms(0).Cell1.S(9,8,0,traninacc);
				document.forms(0).Cell1.S(9,9,0,traninbankname);
				
				if(<%=size==1%>){
					document.forms(0).Cell1.S(1,12,0,empId);//转出个人账号
					document.forms(0).Cell1.S(3,12,0,empName[0]);//姓名
					document.forms(0).Cell1.S(4,12,0,cardNum[0]);//证件号码
					document.forms(0).Cell1.S(6,12,0,tranreason[0]);//转移原因
					document.forms(0).Cell1.S(7,12,0,sumMoney);//发生额
					document.forms(0).Cell1.S(9,12,0,integral);//当期积数
					document.forms(0).Cell1.S(12,12,0,balance);//结息余额
					document.forms(0).Cell1.S(14,12,0,salary);//月工资额
					document.forms(0).Cell1.S(16,12,0,tranin_empid);//转入个人账号
				}
				
				document.forms(0).Cell1.S(1,3,0,date.substring(0,4)+"年 "+date.substring(4,6)+"月 "+date.substring(6,8)+"日");
				document.forms(0).Cell1.S(12,3,0,docNum);
				document.forms(0).Cell1.S(6,3,0,noteNum);
				
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
<td><input type="button" name="print" value = "打印预览" onclick = "printPreview();"/><td>
<td><INPUT id="Button1" onclick="printsheet()" type="button" value=" 打印 " name="Button1"></td>
<td><INPUT id="Button1" onclick="Button1_onclick()" type="button" value="另存为Excel" name="Button1"><td>
<td><INPUT id="Button1" onclick="Button2_onclick()" type="button" value="另存为pdf" name="Button1"><td>
<td><INPUT id="Button3" style="WIDTH: 100px" onclick="Button3_onclick()" type="button" value="页面设置"><td>
<td><INPUT id="Button3" style="WIDTH: 100px" onclick="Button4_onclick()" type="button" value="查找对话框"><td>
<td><INPUT id="Button3" style="WIDTH: 100px" onclick="Button5_onclick()" type="button" value="excel导入"><td>
<td><INPUT id="Button3" onclick="javascript:history.back();" type="button" value=" 返回 "><td>	
</table>
</form>
  </body>
</html>
