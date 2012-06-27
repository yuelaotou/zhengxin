<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page  import="java.lang.*" import="java.util.*"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic"%>
<%@ page import="org.xpup.hafmis.sysloan.loanapply.endorsecontract.form.EndorsecontractTcAF" %>
<%@ include file="/checkUrl.jsp"%>

<%
   String path=request.getContextPath();
 %>
<html>
  <head>
  <title>打印质押合同信息</title>
  <script src="<%=path%>/js/common.js">
</script>
  </head>
  
  <script type="text/javascript">
  function load(){	
	loginReg();
	document.forms(0).Cell1.openfile("<%=path%>/sysloan/loanapply/report/endorsecontractTc.cll","");
		var impawnPerson=[];
		var assistantOrgName=[];
	    var impawnMatterName=[];
	    var paperPersonName=[];
	    var carnum=[];
	    var paperName=[];
	    var tel=[];
	    
	    var debitter=[];
	    var office=[];
	    var impawnContractId=[];
		var impawnValue=[];
	    var cardKind=[];
	    var paperNum=[];
	    var mobile=[];
	    
	    var i=0
	<%
	 EndorsecontractTcAF endorsecontractTcAF = new EndorsecontractTcAF();
	 endorsecontractTcAF=(EndorsecontractTcAF)request.getSession().getAttribute("printendorsecontractTcAF");
	 String userName=(String)request.getAttribute("userName");
	 String plbizDate=(String)request.getAttribute("plbizDate");
	 String contractId = endorsecontractTcAF.getContractId();
	 
	 // 得到循环的列表
	 List list = endorsecontractTcAF.getList();
	 for(int j=0;j<list.size();j++){
	 	EndorsecontractTcAF temp_endorsecontractTcAF = (EndorsecontractTcAF)list.get(j);
		 if(contractId == null){
		 contractId="";
		 }
		 String impawnPerson = temp_endorsecontractTcAF.getImpawnPerson();
		 if(impawnPerson == null){
		 impawnPerson="";
		 }
		 String assistantOrgName = temp_endorsecontractTcAF.getAssistantOrgName();
		 if(assistantOrgName == null){
		 assistantOrgName="";
		 }
		 String impawnMatterName = temp_endorsecontractTcAF.getImpawnMatterName();
		 if(impawnMatterName == null){
		 impawnMatterName="";
		 }
		 String paperPersonName = temp_endorsecontractTcAF.getPaperPersonName();
		 if(paperPersonName == null){
		 paperPersonName="";
		 }
		 String paperNum = temp_endorsecontractTcAF.getPaperNum();
		 if(paperNum == null){
		 paperNum="";
		 }
		 String paperName = temp_endorsecontractTcAF.getPaperName();
		 if(paperName == null){
		 paperName="";
		 }
		 String tel = temp_endorsecontractTcAF.getTel();
		 if(tel == null){
		 tel="";
		 }
		 String debitter = endorsecontractTcAF.getDebitter();
		 if(debitter == null){
		 debitter="";
		 }
		 String office = temp_endorsecontractTcAF.getOffice();
		 if(office == null){
		 office="";
		 }
		 String impawnContractId = temp_endorsecontractTcAF.getImpawnContractId();
		 if(impawnContractId == null){
		 impawnContractId="";
		 }
		 String impawnValue = temp_endorsecontractTcAF.getImpawnValue();
		 if(impawnValue == null || "".equals(impawnValue)){
		 impawnValue="0";
		 }
		 String cardKind = temp_endorsecontractTcAF.getCardKind();
		 if(cardKind == null){
		 cardKind="";
		 }
		 String carnum = temp_endorsecontractTcAF.getCarNum();
		 if(carnum == null){
		 carnum="";
		 }
		 String mobile = temp_endorsecontractTcAF.getMobile();
		 if(mobile == null){
		 mobile="";
		 }%>
		 
		impawnPerson[i]="<%=impawnPerson%>";
		assistantOrgName[i]="<%=assistantOrgName%>";
	    impawnMatterName[i]="<%=impawnMatterName%>";
	    paperPersonName[i]="<%=paperPersonName%>";
	    carnum[i]="<%=carnum%>";
	    paperName[i]="<%=paperName%>";
	    tel[i]="<%=tel%>";
	    
	    debitter[i]="<%=debitter%>";
	    office[i]="<%=office%>";
	    impawnContractId[i]="<%=impawnContractId%>";
		impawnValue[i]="<%=impawnValue%>";
	    cardKind[i]="<%=cardKind%>";
	    paperNum[i]="<%=paperNum%>";
	    mobile[i]="<%=mobile%>";
	    i++;
	<%
	 }
	%>
	var totalLine=impawnPerson.length
		var pageCurrent=0;						//当前页
		for(k = 0 ; k < totalLine; k++){
		if(k>0){
			pageCurrent++;
			document.forms(0).Cell1.insertSheetFromFile("<%=path%>/sysloan/loanapply/report/endorsecontractTc.cll",0,1,pageCurrent);
			document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页");
		}
			document.forms(0).Cell1.S(2,3,pageCurrent,"<%=contractId %>");
			document.forms(0).Cell1.S(2,4,pageCurrent,impawnPerson[k]);
			document.forms(0).Cell1.S(2,5,pageCurrent,assistantOrgName[k]);
			document.forms(0).Cell1.S(2,6,pageCurrent,impawnMatterName[k]);
			document.forms(0).Cell1.S(2,7,pageCurrent,paperPersonName[k]);
			document.forms(0).Cell1.S(2,8,pageCurrent,carnum[k]);
			document.forms(0).Cell1.S(2,9,pageCurrent,paperName[k]);
			document.forms(0).Cell1.S(2,10,pageCurrent,tel[k]);
			document.forms(0).Cell1.S(2,11,pageCurrent,"<%=userName%>");
			
			document.forms(0).Cell1.S(4,3,pageCurrent,debitter[k]);
			document.forms(0).Cell1.S(4,4,pageCurrent,office[k]);
			document.forms(0).Cell1.S(4,5,pageCurrent,impawnContractId[k]);
			document.forms(0).Cell1.d(4,6,pageCurrent,impawnValue[k]);
			document.forms(0).Cell1.S(4,7,pageCurrent,cardKind[k]);
			document.forms(0).Cell1.S(4,8,pageCurrent,paperNum[k]);
			document.forms(0).Cell1.S(4,10,pageCurrent,mobile[k]);
			document.forms(0).Cell1.S(4,11,pageCurrent,"<%=plbizDate%>");
		}
		
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
