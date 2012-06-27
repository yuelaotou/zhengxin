<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic"%>
<%@ page
	import="org.xpup.hafmis.sysloan.loanapply.issuenotice.dto.IssuenoticePrintDTO"%>
<%@ page import="java.math.BigDecimal"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	String url = "issuenoticeTdShowAC.do";
	IssuenoticePrintDTO dto = (IssuenoticePrintDTO) request
			.getAttribute("issuenoticePrintDTO");
	String bankName = dto.getBankName();
	String bankAcc_1 = dto.getOutAccount();
	String bankAcc_2 = dto.getInAccount();
	BigDecimal money = dto.getMoney();
	String userName = dto.getUsername();
	String lastPerson = dto.getLastPerson();
	String vipchkPerson = dto.getVipchkPerson();
	String finchkPerson = dto.getFinchkPerson();
	String bizdate=dto.getBizDate();
%>
<html>
	<head>
		<script src="<%=path%>/js/common.js"></script>
	</head>
	<script type="text/javascript">
	function formatCHN(number){
		switch (number){
			case "1" :
				return "壹" ; 
			case "2":
				return "贰" ;
			case "3":
				return "叁" ;
			case "4":
				return "肆" ;
			case "5":
				return "伍" ;
			case "6":
				return "陆" ;
			case "7":
				return "柒" ;
			case "8":
				return "捌" ;
			case "9":
				return "玖" ;
			case "0":
				return "零" ;
		}
	}
	function formatSeven(v){
	if(v=="")
	{
		return "";
	}
	var l=10-v.length;
	var f="";
	for(i=0;i<l;i++)   
    {
    	f+="0";
  	}
  	return f+v;
}
	function load(){
		loginReg();
		document.forms(0).Cell1.openfile("<%=path%>/sysloan/loanapply/issuenotice/226.cll","");
		<%    		
		 		 	
		 		
		%>
		var bizDateYear;
		var bizDateMonth;
		var bizDate;
		var userName;
		bizDateYear="<%=bizdate.substring(0, 4)%>";		
				bizDateMonth="<%=bizdate.substring(4, 6)%>";	
				bizDate="<%=bizdate.substring(6, 8)%>";	
					
            
		
			var pageCurrent=0;						//当前页			
			var m=ChangeToBig("12345678.7");
				var chiao="零";
		var fin="零";
		var money="<%=money%>";
		money=formatSeven(money);
		var count=money.indexOf(".",0);
		var length;
		len=money.length;
		if(count==-1){
			length=len;
		}else{
			length=count;
		}
		for(var i=0;i<length;i++){//从后向前插入（从元起）
			var temp=money.substring((length-i-1),length-i);
			
			if(i==0){
				document.forms(0).Cell1.S(36,19,0,formatCHN(temp));
			}
			if(i==1){
				document.forms(0).Cell1.S(32,19,0,formatCHN(temp));
			}
			if(i==2){
				document.forms(0).Cell1.S(27,19,0,formatCHN(temp));
			}
			if(i==3){
				document.forms(0).Cell1.S(23,19,0,formatCHN(temp));
			}
			if(i==4){
				document.forms(0).Cell1.S(19,19,0,formatCHN(temp));
			}
			if(i==5){
				document.forms(0).Cell1.S(14,19,0,formatCHN(temp));
			}
			if(i==6){
				document.forms(0).Cell1.S(10,19,0,formatCHN(temp));
			}
			//document.forms(0).Cell1.S((15-i),9,0,temp);
		}
		if(count!=-1){//计算小数后面
			if((len-count)>2)//两位
			{
				chiao=money.substring(count+1,money.length-1);
				fin=money.substring(count+2,money.length);
				
				document.forms(0).Cell1.S(41,19,0,formatCHN(chiao));
				document.forms(0).Cell1.S(45,19,0,formatCHN(fin));
			}
			else{//只有一位
				chiao=money.substring(count+1,money.length)
				document.forms(0).Cell1.S(41,19,0,formatCHN(chiao));
				document.forms(0).Cell1.S(45,19,0,"零");
				}	
		}else{
			document.forms(0).Cell1.S(41,19,0,chiao);
			document.forms(0).Cell1.S(45,19,0,fin);
			}
		document.forms(0).Cell1.S(9,7,0,"<%=bankName%>");
		document.forms(0).Cell1.S(22,7,0,bizDateYear);
		document.forms(0).Cell1.S(32,7,0,bizDateMonth);
		document.forms(0).Cell1.S(38,7,0,bizDate);
		document.forms(0).Cell1.S(9,9,0,"营口市住房公积金管理中心");
		document.forms(0).Cell1.S(9,13,0,"个贷");
		document.forms(0).Cell1.S(9,16,0,"<%=bankAcc_1%>");
		document.forms(0).Cell1.S(38,16,0,"<%=bankAcc_2%>");
		document.forms(0).Cell1.S(55,19,0,"<%=money%>");
		document.forms(0).Cell1.S(9,22,0,"发放个人住房贷款");
		
		document.forms(0).Cell1.S(11,30,0,"<%=lastPerson%>");
		document.forms(0).Cell1.S(23,30,0,"<%=finchkPerson%>");
		document.forms(0).Cell1.S(37,30,0,"<%=vipchkPerson%>");
		document.forms(0).Cell1.S(52,30,0,"<%=userName%>");
						
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
							value="另存为Excel" name="Button1">
					</td>
					<td>
						<INPUT id="Button1" onclick="Button2_onclick()" type="button"
							value="另存为pdf" name="Button1">
					</td>
					<td>
						<INPUT id="Button3" style="WIDTH: 100px"
							onclick="Button3_onclick()" type="button" value="页面设置">
					</td>
					<td>
						<INPUT id="Button3" style="WIDTH: 100px"
							onclick="Button4_onclick()" type="button" value="查找对话框">
					</td>
					<td>
						<INPUT id="Button3" style="WIDTH: 100px"
							onclick="Button5_onclick()" type="button" value="excel导入">
					</td>
					<td>
						<INPUT id="Button1" onclick="printsheet()" type="button"
							value=" 打印 " name="Button1">
					</td>
					<td>
						<INPUT id="Button3" onclick="javascript:document.URL='<%=url%>' "
							type="button" value=" 返回 ">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>



