<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page
	import="org.xpup.hafmis.sysfinance.common.biz.credencepop.form.CredencePopShowAF"%>
<%@ page
	import="org.xpup.hafmis.sysfinance.common.biz.credencepop.dto.CredencePopInfoDTO"%>
<%@ page
	import="org.xpup.hafmis.sysfinance.common.biz.credencepop.dto.CredencePopListDTO"%>
<%@ page import="java.util.List"%>
<%@ page import="java.math.BigDecimal"%>
<%
	String path = request.getContextPath();
	String bizDate = (String) request.getAttribute("bizDate");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	</head>
	<script type="text/javascript">
  function load(){
  		loginReg();
	  	document.forms(0).Cell1.openfile("<%=path%>/sysfinance/common/biz/report/credencepop_print_jj.cll","");
	  	
	  	var summay=[];
		var freeSummay=[];
	    var subjectCode=[];
	    var debit=[];
	    var credit=[];
	    
	    var paramExplain;
	    var office;
	    var credenceCharacter;
	    var credenceNum;
	    var oldCredenceNum;
	    var credenceDate;
	    var credenceDate2;
	    var credenceDate3;
	    var bizDate=<%=bizDate%>;
	    
	  	var settType;
	  	var settNum;
	  	var settDate;
	  	var checkpsn;
	  	var clearpsn;
	  	var makebill;
	  	var accountpsn;
	  	var accountCharge;
	  	
	  	var sumDebit;
	  	var sumCredit;
	  	
	  	var i=0;
	  	<%
	  	CredencePopShowAF credencePopShowAF = (CredencePopShowAF) request.getSession().getAttribute("credencePopShowAF_print");
	  	CredencePopInfoDTO credencePopInfoDTO = credencePopShowAF.getCredencePopInfoDTO();
	  	credencePopInfoDTO.getCredenceDate().substring(0,4);
	  		%>
	  	paramExplain="<%=credencePopInfoDTO.getParamExplain()%>"
	  	office="<%=credencePopInfoDTO.getOffice()%>";
	  	credenceCharacter="<%=credencePopInfoDTO.getCredenceCharacter()%>";
	  	credenceNum="<%=credencePopInfoDTO.getCredenceNum()%>";
	  	oldCredenceNum="<%=credencePopInfoDTO.getOldCredenceNum()%>";
	  	
	  	credenceDate="<%=credencePopInfoDTO.getCredenceDate().substring(0,
									4)%>";
	  		credenceDate2="<%=credencePopInfoDTO.getCredenceDate().substring(4,
									6)%>";
	  			credenceDate3="<%=credencePopInfoDTO.getCredenceDate().substring(6,
									8)%>";
	  			
	  	settType="<%=credencePopInfoDTO.getSettType()%>";
	  	settNum="<%=credencePopInfoDTO.getSettNum()%>";
	  	settDate="<%=credencePopInfoDTO.getSettDate()%>";
	  	checkpsn="<%=credencePopInfoDTO.getCheckpsn()%>";
	  	clearpsn="<%=credencePopInfoDTO.getClearpsn()%>";
	  	makebill="<%=credencePopInfoDTO.getMakebill()%>";
	  	accountpsn="<%=credencePopInfoDTO.getAccountpsn()%>";
	  	accountCharge="<%=credencePopInfoDTO.getAccountCharge()%>";
	  	
	  	<%
    	List list = credencePopShowAF.getList();
    	BigDecimal sumDebit = new BigDecimal(0.00);
    	BigDecimal sumCredit = new BigDecimal(0.00);
	    for(int j=0;j<list.size();j++){
			CredencePopListDTO dto=(CredencePopListDTO)list.get(j);
			sumDebit = sumDebit.add(dto.getDebit());
			sumCredit = sumCredit.add(dto.getCredit());
 		%>
 			summay[i]="<%=dto.getSummay()%>";
 			freeSummay[i]="<%=dto.getFreeSummay()%>";
 			subjectCode[i]="<%=dto.getSubjectCode()%>"+"-"+"<%=dto.getSubjectName()%>";
 			debit[i]="<%=dto.getDebit()%>";
 			credit[i]="<%=dto.getCredit()%>";
 			i++;
 		<%
 			}
 		%>
 		sumDebit="<%=sumDebit%>";
 		sumCredit="<%=sumCredit%>";
 		if(checkpsn=="null"){
 			checkpsn = "";
 		}
 		var totalLine=subjectCode.length;				//总的行数
		var totalPageLine=8;					//每页显示多少行
		var pageTotal=totalLine/totalPageLine;	//总共分几页
		var pageCurrent=0;						//当前页
		var completeline=0;						//记录行的
		
		var iPage = getInt(totalLine,totalPageLine);
		
		for(k = 0 ; k < totalLine; k++){
			if(k%totalPageLine==0&&k>0){
				document.forms(0).Cell1.ReDraw();
				pageCurrent++;	
				completeline=k-2;				
				document.forms(0).Cell1.insertSheetFromFile("<%=path%>/sysfinance/common/biz/report/credencepop_print_jj.cll",0,1,pageCurrent);
				document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页")	
			}
			if(pageCurrent==0){
				//document.forms(0).Cell1.S(1,1,pageCurrent,paramExplain+"凭证");
                document.forms(0).Cell1.S(1,3,pageCurrent,"附原始凭证"+oldCredenceNum+"张");
				document.forms(0).Cell1.S(4,3,pageCurrent,credenceDate+"年"+credenceDate2+"月"+credenceDate3+"日");
				document.forms(0).Cell1.S(7,3,pageCurrent,credenceNum+"-"+(pageCurrent+1)+"/"+iPage);	
				
				document.forms(0).Cell1.S(1,k+5,pageCurrent,summay[k]);
<%--				document.forms(0).Cell1.S(3,k+5,pageCurrent,freeSummay[k]);--%>
				document.forms(0).Cell1.S(3,k+5,pageCurrent,subjectCode[k]);
				document.forms(0).Cell1.d(5,k+5,pageCurrent,debit[k]);
				document.forms(0).Cell1.d(7,k+5,pageCurrent,credit[k]);
				
				document.forms(0).Cell1.d(5,13,pageCurrent,sumDebit);
				document.forms(0).Cell1.d(7,13,pageCurrent,sumCredit);
				
				
				document.forms(0).Cell1.S(3,14,pageCurrent,checkpsn);
				document.forms(0).Cell1.S(6,14,pageCurrent,makebill);
				
				//document.forms(0).Cell1.S(2,13,pageCurrent,settType);
				//document.forms(0).Cell1.S(5,13,pageCurrent,settNum);
				//document.forms(0).Cell1.S(9,13,pageCurrent,settDate);
                //  document.forms(0).Cell1.S(2,14,pageCurrent,accountCharge);
				//document.forms(0).Cell1.S(4,14,pageCurrent,clearpsn);
				//document.forms(0).Cell1.S(8,14,pageCurrent,accountpsn);		
				//document.forms(0).Cell1.S(1,15,pageCurrent,();
				
			
			}else{//向第一页后的所有页插数据
			  //  document.forms(0).Cell1.S(1,1,pageCurrent,paramExplain+"凭证");
			    document.forms(0).Cell1.S(1,3,pageCurrent,"附原始凭证"+oldCredenceNum+"张");
				document.forms(0).Cell1.S(4,3,pageCurrent,credenceDate+"年"+credenceDate2+"月"+credenceDate3+"日");
				document.forms(0).Cell1.S(7,3,pageCurrent,credenceNum+"-"+(pageCurrent+1)+"/"+iPage);	
				
				
               	document.forms(0).Cell1.S(1,k-3,pageCurrent,summay[k]);
<%--				document.forms(0).Cell1.S(3,k-3,pageCurrent,freeSummay[k]);--%>
				document.forms(0).Cell1.S(3,k-3,pageCurrent,subjectCode[k]);
				document.forms(0).Cell1.d(5,k-3,pageCurrent,debit[k]);
				document.forms(0).Cell1.d(7,k-3,pageCurrent,credit[k]);
				
				document.forms(0).Cell1.d(5,13,pageCurrent,sumDebit);
				document.forms(0).Cell1.d(7,13,pageCurrent,sumCredit);
				
				
				document.forms(0).Cell1.S(3,14,pageCurrent,checkpsn);
				document.forms(0).Cell1.S(6,14,pageCurrent,makebill);
			}	
		}	
	
  }
  // 取总页数
	function getInt(i,k) { 
		var page=0; 
		var j; 
		j=Math.round(i/k)-i/k; 
		if (j>=0) 
		page=Math.round(i/k)-1; 
		if (j<=0) 
		page=Math.round(i/k); 
		if(j!=0)
		page=page+1;
		if(j==0)
		page=i/k;
		return page; 
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
	<script language="JScript.Encode" onContextmenu="return false">
		eval(unescape('function%20LoginRegister%28%29//%u6CE8%u518CCELL%0D%0A%20%7B%20%0D%0A%20document.forms%280%29.Cell1.Login%28%22%u6C88%u9633%u91D1%u8F6F%u79D1%u6280%u6709%u9650%u516C%u53F8%22%2C%22%22%2C%2213100104509%22%2C%220740-1662-7775-3003%22%29%3B%20%20%20%20%0D%0A%20%7D'))
  </script>
	<body onload="load();" onContextmenu="return false">
		<form action="">
			<table align="center">
				<tr>
					<OBJECT id=Cell1
						style="LEFT:0px;WIDTH:800px;  TOP:0px;HEIGHT:450px"
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
						<INPUT id="Button1" onclick="printsheet()" type="button"
							value=" 打印 " name="Button1">
						<INPUT id="Button1" onclick="Button1_onclick()" type="button"
							value="另存为Excel" name="Button1">
						<INPUT id="Button1" onclick="Button2_onclick()" type="button"
							value="另存为pdf" name="Button1">
						<INPUT id="Button3" style="WIDTH: 90px"
							onclick="Button3_onclick()" type="button" value="页面设置">
						<INPUT id="Button3" style="WIDTH: 90px"
							onclick="Button4_onclick()" type="button" value="查找对话框">
						<INPUT id="Button3" style="WIDTH: 90px"
							onclick="Button5_onclick()" type="button" value="excel导入">
						<INPUT id="Button3" onclick="javascript:history.back();"
							type="button" value=" 返回 ">
					</td>
			</table>
		</form>
	</body>
</html>
