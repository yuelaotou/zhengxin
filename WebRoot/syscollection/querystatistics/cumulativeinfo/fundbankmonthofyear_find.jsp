<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security" %>
<%@ include file="/checkUrl.jsp"%>
<%@ page import="org.xpup.hafmis.syscollection.querystatistics.cumulativeinfo.dto.Fundbankmonthofyeardto"%>
<%@ page import="org.xpup.hafmis.syscollection.querystatistics.cumulativeinfo.dto.Printdto"%>
<%  
   String path = request.getContextPath();
%> 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
<script language="javascript">

function checkYears(month)
{
  var tempMonth;
  eval("tempMonth=document.all."+month)
  var strMonth = tempMonth.value;
  if(strMonth.length!=4)
  {
    alert("请输入四位的年，格式为2000！");
    tempMonth.value="";
    tempMonth.focus();
    return false;
  } 

var officeCode = document.forms[0].elements["officeCode"].value;
 if (officeCode==""){
  alert("必须选择办事处与归集银行");
  return false;
 }
 
   return true;
}

function executeAjax1(){
	var officeCode = document.forms[0].elements["officeCode"].value;
	var url = "fundbankmonthofyearAjaxAC.do?officeCode="+officeCode;
	findInfo123(url);
}
function findInfo123(url) {
 createXMLHttpRequest();
	    xmlHttp.onreadystatechange = handleStateChange123;
	    xmlHttp.open("GET", url, true);
	    xmlHttp.send(null);   
}
function handleStateChange123() {
  if(xmlHttp.readyState == 4) {
      if(xmlHttp.status == 200) {        
        var xmlDoc = xmlHttp.responseXML;
		var values = xmlDoc.getElementsByTagName("value");
		 
		var texts  = xmlDoc.getElementsByTagName("text");		
		var selectObj = document.getElementById("childNode");
		selectObj.length = 0;
		
		for ( i=0; i < values.length; i++ ) {
			var childOption = new Option(texts[i].firstChild.data,values[i].firstChild.data);
			selectObj.options[selectObj.length++] = childOption;
			if(i==0){
			document.all.collectionBankId.value=values[i].firstChild.data;
			}
		}	
      }
   }
}
function getChildValue(){
 var selectObj = document.getElementById("childNode");
  document.all.collectionBankId.value=selectObj.options[selectObj.selectedIndex].value;
}

	function load(){
		document.all.collectionBankId.value="";
		document.all.officeCode.value="";
		document.all.queryTime.value="";
		<logic:present name="printList" scope="request">
			document.forms(1).Cell1.Login("沈阳金软科技有限公司","","13100104509","0740-1662-7775-3003"); 
			document.forms(1).Cell1.openfile("<%=path%>/syscollection/querystatistics/cumulativeinfo/report/fundbankmonthofyear_cell.cll","");
				<%
				Printdto print_dto=(Printdto)request.getAttribute("dto");	
								
			%>		
	
			<%
			   
				List list=(List)request.getAttribute("printList");
				Fundbankmonthofyeardto dto=null;
                for(int i=0;i<list.size();i++){				
				dto =(Fundbankmonthofyeardto) list.get(i);
			//	System.out.println("jsp ...dto.getPay_fund()="+dto.getPay_fund());
				
			%>			
			var k =<%=i%>;;		
			
		    document.forms(1).Cell1.d(2,8+k,0,<%=dto.getPay_fund()%>);
			document.forms(1).Cell1.d(3,8+k,0,<%=dto.getOrg_neworg()%>);
			document.forms(1).Cell1.d(4,8+k,0,<%=dto.getPerson_neworg()%>);
			document.forms(1).Cell1.d(5,8+k,0,<%=dto.getPay_neworg()%>);
			document.forms(1).Cell1.d(6,8+k,0,<%=dto.getOrg_rate()%>);
			document.forms(1).Cell1.d(7,8+k,0,<%=dto.getPay_rate()%>);
			document.forms(1).Cell1.d(8,8+k,0,<%=dto.getOrg_salary()%>);	
			document.forms(1).Cell1.d(9,8+k,0,<%=dto.getPay_salary()%>);
			document.forms(1).Cell1.d(10,8+k,0,<%=dto.getAfter_pay()%>);
			document.forms(1).Cell1.d(11,8+k,0,<%=dto.getOrg_over()%>);
			document.forms(1).Cell1.d(12,8+k,0,<%=dto.getPay_over()%>);
			document.forms(1).Cell1.d(13,8+k,0,<%=dto.getOrg_overs()%>);
			document.forms(1).Cell1.d(14,8+k,0,<%=dto.getPay_overs()%>);
			
			
			
		 <%}%>;
 document.forms(1).Cell1.SetFormula (2, 20, 0, "Sum(B8:B19)");
  document.forms(1).Cell1.SetFormula (3, 20, 0, "Sum(C8:C19)");
   document.forms(1).Cell1.SetFormula (4, 20, 0, "Sum(D8:D19)");
    document.forms(1).Cell1.SetFormula (5, 20, 0, "Sum(E8:E19)");
     document.forms(1).Cell1.SetFormula (6, 20, 0, "Sum(F8:F19)");
      document.forms(1).Cell1.SetFormula (7, 20, 0, "Sum(G8:G19)");
       document.forms(1).Cell1.SetFormula (8, 20, 0, "Sum(H8:H19)");
        document.forms(1).Cell1.SetFormula (9, 20, 0, "Sum(I8:I19)");
         document.forms(1).Cell1.SetFormula (10, 20, 0, "Sum(J8:J19)");
          document.forms(1).Cell1.SetFormula (11, 20, 0, "Sum(K8:K19)");
           document.forms(1).Cell1.SetFormula (12, 20, 0, "Sum(L8:L19)");
            document.forms(1).Cell1.SetFormula (13, 20, 0, "Sum(M8:M19)");
             document.forms(1).Cell1.SetFormula (14, 20, 0, "Sum(N8:N19)");
    
		    document.forms(1).Cell1.S(4,1,0,"<%=print_dto.getBank_name()%>");//银行名称;
			document.forms(1).Cell1.S(2,5,0,"<%=print_dto.getBank_name()%>");//银行名称;
			 
		    //document.forms(1).Cell1.S(3,21,0,"<%=print_dto.getOperater()%>");//负责人;
		      document.forms(1).Cell1.S(6,21,0,"<%=print_dto.getOperater()%>");//制表人;
		     document.forms(1).Cell1.S(10,21,0,"<%=print_dto.getDate()%>");//年月日;
		     document.forms(1).Cell1.S(1,1,0,"<%=request.getAttribute("queryTime")%>");
			
			document.forms(1).Cell1.AllowExtend=false;
			document.forms(1).Cell1.AllowDragdrop=false;
			//document.forms(1).Cell1.WorkbookReadonly=true;
			
		</logic:present>
		<logic:notPresent name="printList" scope="request">
			
		</logic:notPresent>
	}
	<logic:present name="printList" scope="request">
		function printPreview(){
			var k=document.forms(1).Cell1.GetCurSheet();//显示打印预览那个页面
			document.forms(1).Cell1.printPreviewEx(1,k,false);
		}
		function Button1_onclick()
		{
			document.forms(1).Cell1.SaveFile();
		}
		function Button2_onclick()
		{
			document.forms(1).Cell1.ExportPdfFile("d:\\aa.pdf",-1,1,1);
		}
		function Button3_onclick()
		{
			document.forms(1).Cell1.PrintPageSetup();
		}
		function Button4_onclick()
		{
			document.forms(1).Cell1.FindDialogEx( 0,"");
		}
		function Button5_onclick()
		{
			document.forms(1).Cell1.ImportExcelDlg();
		}
		function printsheet()//真正的打印
		{
			var k=document.forms(1).Cell1.GetCurSheet();//显示打印预览那个页面--固定
			document.forms(1).Cell1.PrintSheet(1,k);//固定...
		}
	</logic:present>
</script>
<script language="JScript.Encode">
eval(unescape('function%20LoginRegister%28%29//%u6CE8%u518CCELL%0D%0A%20%7B%20%0D%0A%20document.forms%281%29.Cell1.Login%28%22%u6C88%u9633%u91D1%u8F6F%u79D1%u6280%u6709%u9650%u516C%u53F8%22%2C%22%22%2C%2213100104509%22%2C%220740-1662-7775-3003%22%29%3B%20%20%20%20%0D%0A%20%7D'))
</script>	
<script type="text/javascript">
	function a(){
		var p = "<%=path%>";
		officeAjax(p);
	}
</script>
    <title>归集银行统计表.年表.</title>
  </head>
  <link rel="stylesheet" href="<%=path %>/css/index.css" type="text/css">
  <script language="javascript" src="<%=path%>/js/common.js"></script>
  <body bgcolor="#FFFFFF" text="#000000" onload="load();" onContextmenu="return false">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr> 
    <td class=td3>
      <html:form action="/fundbankmonthofyearFindAC.do" >
      <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td height="35"> 
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td height="22" bgcolor="#CCCCCC" align="center" width="117"><b class="font14">按银行统计年度报表查询</b></td>
                <td height="22" background="<%=path %>/img/bg2.gif" align="center">&nbsp;</td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
      
        <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=0 align="center" >
          <tr> 
            <td width="17%"   class="td1">办事处<font color="#FF0000">*</font></td>
            <td width="33%"  >
              <html:select property="officeCode" styleClass="input4" onchange="executeAjax1();">
          						<option value=""></option>
          						<html:options  collection="officeList1" property="value" labelProperty="label"/>
          					</html:select>
            </td>
            <td width="17%" class="td1" >归集银行<font color="#FF0000">*</font></td>
            <td>
									<td width="35%" height="18" colspan="2" align="center">
								<html:hidden property="collectionBankId" name="cumulativeinfoFindAF" />
								<select class="input4" id="childNode" onChange="getChildValue()" >
								</select>
							</td>
							
          </tr>
          <tr>
          	<td width="17%"   class="td1">查询年度<font color="#FF0000">*</font></td>
            <td width="33%"  >
              <html:text name="cumulativeinfoFindAF" property="queryTime"  styleClass="input3" styleId="txtsearch"></html:text>
            </td>
          </tr>
      </table>
      <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
            <td align="right"><br>
            <html:submit styleClass="buttona" onclick="return checkYears('queryTime');">查询</html:submit>
        </tr>
      </table>
      </html:form>
      <logic:present name="printList" scope="request">
		<form action="">
		    <table align="center">
				<tr><OBJECT id=Cell1 style= "LEFT:0px;WIDTH:760px;  TOP:0px;HEIGHT:300px" codeBase="http://192.168.1.44:8088/hafmis/CellWeb5.CAB#version=5,3,7,321" classid=clsid:3F166327-8030-4881-8BD2-EA25350E574A VIEWASTEXT><PARAM NAME="_Version" VALUE="65536"><PARAM NAME="_ExtentX" VALUE="10266"><PARAM NAME="_ExtentY" VALUE="7011"><PARAM NAME="_StockProps" VALUE= "0"></OBJECT></tr> 
				<tr><input type="button" name="print" value = "打印预览" onclick = "printPreview();"/>
				<INPUT id="Button1" onclick="Button1_onclick()" type="button" value="另存为Excel" name="Button1">
				<INPUT id="Button1" onclick="Button2_onclick()" type="button" value="另存为pdf" name="Button1">
				<INPUT id="Button3" style="WIDTH: 100px" onclick="Button3_onclick()" type="button" value="页面设置">
				<INPUT id="Button3" style="WIDTH: 100px" onclick="Button4_onclick()" type="button" value="查找对话框">
				<INPUT id="Button3" style="WIDTH: 100px" onclick="Button5_onclick()" type="button" value="excel导入">	
				<INPUT id="Button1" onclick="printsheet()" type="button" value=" 打印 " name="Button1">
				<INPUT id="Button3" onclick="javascript:history.back();" type="button" value=" 返回 ">	
			</table>
		</form>
	</logic:present>
	</td>
  </tr>
</table>
</body>
</html:html>
