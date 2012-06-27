<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page  import="java.lang.*" import="java.util.*"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic"%>
<%@ page import="org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTdAF" %>
<%@ include file="/checkUrl.jsp"%>

<%
   String path=request.getContextPath();
 %>
<html>
  <head>
  <title>打印保证人信息</title>
  <script src="<%=path%>/js/common.js">
</script>
  </head>
  
  <script type="text/javascript">
   function load(){	
	loginReg();
	document.forms(0).Cell1.openfile("<%=path%>/sysloan/loanapply/report/endorsecontractTd.cll","");
		var empId=[];
		var cardKind=[];
	    var sex=[];
	    var salary=[];
	    var balance=[];
	    var tel=[];
	    var homeTel=[];
	    var homeMai=[];
	    var orgName=[];
	    var orgTel=[];
	    
	    var debitter=[];
	    var empName=[];
		var cardNum=[];
	    var birthday=[];
	    var monthPay=[];
	    var empSt=[];
	    var mobile=[];
	    var homeAddr=[];
	    var orgId=[];
	    var orgAddr=[];
	    var orgMail=[];
	    var i=0
	<%
	 EndorsecontractTdAF endorsecontractTdAF = new EndorsecontractTdAF();
	 endorsecontractTdAF=(EndorsecontractTdAF)request.getSession().getAttribute("printendorsecontractTdAF");
	 String userName=(String)request.getAttribute("userName");
	 String plbizDate=(String)request.getAttribute("plbizDate");
	 String contractId=endorsecontractTdAF.getContractId();
	 
	 // 得到循环的列表
	 List list = endorsecontractTdAF.getList();
	 
	 for(int j=0;j<list.size();j++){
		 	EndorsecontractTdAF temp_endorsecontractTdAF = (EndorsecontractTdAF)list.get(j);
		 if(contractId==null){
		 contractId="";
		 }
		 String empId=temp_endorsecontractTdAF.getHiddenEmpId();
		 if(empId==null){
		 empId="";
		 }
		 String cardKind=temp_endorsecontractTdAF.getCardKind();
		 if(cardKind==null){
		 cardKind="";
		 }
		 String sex=temp_endorsecontractTdAF.getSex();
		 if(sex==null){
		 sex="";
		 }
		 String salary=temp_endorsecontractTdAF.getSalary();
		 if(salary==null || "".equals(salary)){
		 salary="0";
		 }
		 String balance=temp_endorsecontractTdAF.getBalance();
		 if(balance==null || "".equals(balance)){
		 balance="0";
		 }
		 String tel=temp_endorsecontractTdAF.getTel();
		 if(tel==null){
		 tel="";
		 }
		 String homeTel=temp_endorsecontractTdAF.getHomeTel();
		 if(homeTel==null){
		 homeTel="";
		 }
		 String homeMai=temp_endorsecontractTdAF.getHomeMai();
		 if(homeMai==null){
		 homeMai="";
		 }
		 String orgName=temp_endorsecontractTdAF.getOrgName();
		 if(orgName==null){
		 orgName="";
		 }
		 String orgTel=temp_endorsecontractTdAF.getOrgTel();
		 if(orgTel==null){
		 orgTel="";
		 }
		 
		 String debitter=endorsecontractTdAF.getDebitter();
		 if(debitter==null){
		 debitter="";
		 }
		 String empName=temp_endorsecontractTdAF.getEmpName();
		 if(empName==null){
		 empName="";
		 }
		 String cardNum=temp_endorsecontractTdAF.getCardNum();
		 if(cardNum==null){
		 cardNum="";
		 }
		 String birthday=temp_endorsecontractTdAF.getBirthday();
		 if(birthday==null){
		 birthday="";
		 }
		 String monthPay=temp_endorsecontractTdAF.getMonthPay();
		 if(monthPay==null || "".equals(monthPay)){
		 monthPay="0";
		 }
		 String empSt=temp_endorsecontractTdAF.getEmpSt();
		 if(empSt==null){
		 empSt="";
		 }
		 String mobile=temp_endorsecontractTdAF.getMobile();
		 if(mobile==null){
		 mobile="";
		 }
		 String homeAddr=temp_endorsecontractTdAF.getHomeAddr();
		 if(homeAddr==null){
		 homeAddr="";
		 }
		 String orgId=temp_endorsecontractTdAF.getOrgId();
		 if(orgId==null){
		 orgId="";
		 }
		 String orgAddr=temp_endorsecontractTdAF.getOrgAddr();
		 if(orgAddr==null){
		 orgAddr="";
		 }
		 String orgMail=temp_endorsecontractTdAF.getOrgMail();
		 if(orgMail==null){
		 orgMail="";
		 }
		 %>
		empId[i]="<%=empId%>";
		cardKind[i]="<%=cardKind%>";
	    sex[i]="<%=sex%>";
	    salary[i]="<%=salary%>";
	    balance[i]="<%=balance%>";
	    tel[i]="<%=tel%>";
	    homeTel[i]="<%=homeTel%>";
	    homeMai[i]="<%=homeMai%>";
	    orgName[i]="<%=orgName%>";
	    orgTel[i]="<%=orgTel%>";
	    
	    debitter[i]="<%=debitter%>";
	    empName[i]="<%=empName%>";
		cardNum[i]="<%=cardNum%>";
	    birthday[i]="<%=birthday%>";
	    monthPay[i]="<%=monthPay%>";
	    empSt[i]="<%=empSt%>";
	    mobile[i]="<%=mobile%>";
	    homeAddr[i]="<%=homeAddr%>";
	    orgId[i]="<%=orgId%>";
	    orgAddr[i]="<%=orgAddr%>";
	    orgMail[i]="<%=orgMail%>";
	 
		i++;
	<% 
	 }
	%>
		var totalLine=empId.length
		var pageCurrent=0;						//当前页
		for(k = 0 ; k < totalLine; k++){
		if(k>0){
			pageCurrent++;
			document.forms(0).Cell1.insertSheetFromFile("<%=path%>/sysloan/loanapply/report/endorsecontractTd.cll",0,1,pageCurrent);
			document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页");
		}
			
			document.forms(0).Cell1.S(2,3,pageCurrent,"<%=contractId %>");
			document.forms(0).Cell1.S(2,4,pageCurrent,empId[k]);
			document.forms(0).Cell1.S(2,5,pageCurrent,cardKind[k]);
			document.forms(0).Cell1.S(2,6,pageCurrent,sex[k]);
			document.forms(0).Cell1.d(2,7,pageCurrent,salary[k]);
			document.forms(0).Cell1.d(2,8,pageCurrent,balance[k]);
			document.forms(0).Cell1.S(2,9,pageCurrent,tel[k]);
			document.forms(0).Cell1.S(2,10,pageCurrent,homeTel[k]);
		    document.forms(0).Cell1.S(2,11,pageCurrent,homeMai[k]);
		    document.forms(0).Cell1.S(2,12,pageCurrent,orgName[k]);
		    document.forms(0).Cell1.S(2,13,pageCurrent,orgTel[k]);
			document.forms(0).Cell1.S(2,14,pageCurrent,"<%=userName%>");
			
			document.forms(0).Cell1.S(4,3,pageCurrent,debitter[k]);
			document.forms(0).Cell1.S(4,4,pageCurrent,empName[k]);
			document.forms(0).Cell1.S(4,5,pageCurrent,cardNum[k]);
			document.forms(0).Cell1.S(4,6,pageCurrent,birthday[k]);
			document.forms(0).Cell1.d(4,7,pageCurrent,monthPay[k]);
			document.forms(0).Cell1.S(4,8,pageCurrent,empSt[k]);
			document.forms(0).Cell1.S(4,9,pageCurrent,mobile[k]);
			document.forms(0).Cell1.S(4,10,pageCurrent,homeAddr[k]);
			document.forms(0).Cell1.S(4,11,pageCurrent,orgId[k]);
			document.forms(0).Cell1.S(4,12,pageCurrent,orgAddr[k]);
			document.forms(0).Cell1.S(4,13,pageCurrent,orgMail[k]);
			document.forms(0).Cell1.S(4,14,pageCurrent,"<%=plbizDate%>");
		}
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
  <body onContextmenu="return false" onload="load();"> 
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
					</td>
					<td>
						<INPUT id="Button1" onclick="Button1_onclick()" type="button"
							value="另存为Excel" name="Button1" />
					</td>
					<td>
						<INPUT id="Button1" onclick="Button2_onclick()" type="button"
							value="另存为pdf" name="Button1" />
					</td>
					<td>
						<INPUT id="Button3" style="WIDTH: 100px"
							onclick="Button3_onclick()" type="button" value="页面设置" />
					</td>
					<td>
						<INPUT id="Button3" style="WIDTH: 100px"
							onclick="Button4_onclick()" type="button" value="查找对话框" />
					</td>
					<td>
						<INPUT id="Button3" style="WIDTH: 100px"
							onclick="Button5_onclick()" type="button" value="excel导入" />
					</td>
					<td>
						<INPUT id="Button1" onclick="printsheet()" type="button"
							value=" 打印 " name="Button1" />
					</td>
					<td>
						<INPUT id="Button3" onclick="location.href='to_EndorsecontractTeShowAC.do'"
							type="button" value=" 返回 " />
					</td>
				</tr>
			</table>
		</form>
  </body>
</html>
