<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.List" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic" %>
<%@ page import="org.xpup.hafmis.syscollection.common.domain.entity.Org" %>
<%@ page import="org.xpup.hafmis.common.util.BusiTools" %>
<%@ page import="org.xpup.hafmis.common.util.BusiConst" %>
<%@ page import="java.lang.*" %>
<%@ include file="/checkUrl.jsp"%>
<%
   String path=request.getContextPath();
   Org org=(Org)request.getAttribute("org");
 %>
<html>
  <head>
  <script src="<%=path%>/js/common.js">
</script>
  </head>
  <script type="text/javascript">
	function load(){	
	loginReg();
	document.forms(0).Cell1.openfile("<%=path%>/syscollection/querystatistics/baseinfosearch/orgbaseinfo/report/org.cll","");
    var orgname="";
	var office="";
	var code="";
	var taxnum="";
	var artificialPerson="";// 单位法人
	var orgNature="";  // 单位性质
	var industry="";// 所属行业
	var depart=""; // 主管部门
	var address=""; 
	var mailcode="";
	var orgtele="";
	var region="";  // 所在地区
	var paybank="";
	var paybankcode="";
	var payday="";
	var collbank="";
	var orger="";  // 经办人
	var email="";
	var phone="";
	var moblie="";
	var supervisor="";  //稽查员
	var openday="";
	var opendays="";
	var orgstatus="";
	
	var orgRate="";
	var empRate="";
	var paypresion="";
	
	 orgname="<%=org.getOrgInfo().getName()%>";
	 office="<%=org.getOrgInfo().getTemp_officename()%>";
	  code="<%=org.getOrgInfo().getCode()%>";
	  if(code=="null")
	  {
	    code="";
	  }
     taxnum="<%=org.getOrgInfo().getTaxRegNum()%>";
     if(taxnum=="null")
	  {
	      taxnum="";
	  }
	 artificialPerson="<%=org.getOrgInfo().getArtificialPerson()%>";
	  if( artificialPerson=="null")
	  {
	       artificialPerson="";
	  }
	 orgNature="<%=org.getOrgInfo().getTemp_character()%>";
	 industry="<%=org.getOrgInfo().getTemp_industry()%>";
	 depart="<%=org.getOrgInfo().getTemp_deptInCharge()%>";
	 address="<%=org.getOrgInfo().getAddress()%>";
	   if(  address=="null")
	  {
	        address="";
	  }
	 region="<%=org.getOrgInfo().getTemp_region()%>";
	 mailcode="<%=org.getOrgInfo().getPostalcode()%>";
	 if(mailcode=="null")
	  {
	       	 mailcode="";
	  }
	 orgtele="<%=org.getOrgInfo().getTel()%>";
	   if(orgtele=="null")
	  {
	       	  orgtele="";
	  }
	 paybank="<%=org.getOrgInfo().getPayBank().getName()%>";
	 if(paybank=="null")
	  {
	       	   paybank="";
	  }
	 paybankcode="<%=org.getOrgInfo().getPayBank().getAccountNum()%>";
	  if(paybankcode=="null")
	  {
         paybankcode="";
	  }
	 payday="<%=org.getOrgInfo().getPaydate()%>";
	   if( payday=="null")
	  {
          payday="";
	  }
	 collbank="<%=org.getOrgInfo().getTemp_collectionBankname()%>";
	 orger="<%=org.getOrgInfo().getTransactor().getName()%>";
	  email="<%=org.getOrgInfo().getTransactor().getEmail()%>";
	  if(email=="null")
	  {
          email="";
	  }
	 phone="<%=org.getOrgInfo().getTransactor().getTelephone()%>"
	   if(phone=="null")
	  {
           phone="";
	  }
	 moblie="<%=org.getOrgInfo().getTransactor().getMobileTelephone()%>";
	    if(moblie=="null")
	  {
         moblie="";
	  }
	 supervisor="<%=org.getOrgInfo().getInspector()%>";
	     if( supervisor=="null")
	  {
          supervisor="";
	  }
	  orgRate="<%=org.getOrgRate()%>";
	     if( orgRate=="null")
	  {
          orgRate="";
	  }
	  empRate="<%=org.getEmpRate()%>";
	     if( empRate=="null")
	  {
          empRate="";
	  }
	  paypresion="<%=org.getPayPrecision()%>";
	     if( paypresion=="null")
	  {
          paypresion="";
	  }else{
	  	if(paypresion=="1"){
	  		paypresion="四舍五入到元";
	  	}
	  	if(paypresion=="2"){
	  		paypresion="舍元以下";
	  	}
	  	if(paypresion=="3"){
	  		paypresion="见角进元";
	  	}
	  	if(paypresion=="4"){
	  		paypresion="见分进角";
	  	}
	  	if(paypresion=="5"){
	  		paypresion="四舍五入到角";
	  	}
	  	if(paypresion=="6"){
	  		paypresion="舍角以下";
	  	}
	  	if(paypresion=="7"){
	  		paypresion="见角分进元";
	  	}
	  	if(paypresion=="8"){
	  		paypresion="四舍五入到分";
	  	}
	  }
	  	
	 openday="<%=org.getFirstPayMonth()%>";
	 opendays="<%=org.getOrgInfo().getOpenDate()%>";
	 orgstatus="<%=org.getOrgInfo().getTemp_openstatus()%>";
	   document.forms(0).Cell1.S(3,6,0,orgname);
	   document.forms(0).Cell1.S(3,4,0,office);
	   document.forms(0).Cell1.S(7,4,0,code);
	   document.forms(0).Cell1.S(7,6,0,taxnum);
	   document.forms(0).Cell1.S(3,8,0,artificialPerson);
	   document.forms(0).Cell1.S(7,8,0,orgNature);
	   document.forms(0).Cell1.S(3,10,0,industry);
	   document.forms(0).Cell1.S(7,10,0, depart);
	   document.forms(0).Cell1.S(3,12,0, address);
	   document.forms(0).Cell1.S(7,12,0, mailcode);
	   document.forms(0).Cell1.S(3,14,0, orgtele);
	   document.forms(0).Cell1.S(7,14,0, region);
	   document.forms(0).Cell1.S(3,16,0, paybank);
	   document.forms(0).Cell1.S(7,16,0, paybankcode);
	   document.forms(0).Cell1.S(3,18,0,  payday);
	   document.forms(0).Cell1.S(7,18,0, collbank);
	   document.forms(0).Cell1.S(3,20,0, orger);
	   document.forms(0).Cell1.S(7,20,0, email);
	   document.forms(0).Cell1.S(3,22,0, phone);
	   document.forms(0).Cell1.S(7,22,0,moblie);
	   document.forms(0).Cell1.S(3,24,0,supervisor);
	   document.forms(0).Cell1.S(3,28,0,openday.substr(0,6));
	   document.forms(0).Cell1.S(3,26,0, orgRate);
	   document.forms(0).Cell1.S(7,26,0,empRate);
	   document.forms(0).Cell1.S(7,28,0, paypresion);
	    document.forms(0).Cell1.S(7,24,0, "单一缴率");
	     document.forms(0).Cell1.S(3,42,0, opendays.substr(0,4)+"年"+opendays.substring(4,6)+"月"+opendays.substr(6,8)+"日");
	   //document.forms(0).Cell1.S(2,13,0,orgstatus);
	   
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
<td><INPUT id="Button1" onclick="javascript:history.back();" type="button" value=" 返回 "></td>	
</table>
</form>
  </body>
</html>
