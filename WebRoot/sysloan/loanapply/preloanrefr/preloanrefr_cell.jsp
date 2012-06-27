<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic"%>
<%@ page import="java.util.List"%>
<%@ page
	import="org.xpup.hafmis.sysloan.loanapply.preloanrefr.form.PreLoanRefrShowAF"%>
<%@ page
	import="org.xpup.hafmis.sysloan.loanapply.preloanrefr.dto.PreLoanRefrDTO"%>
<%
	String path = request.getContextPath();
	String url = (String) request.getAttribute("URL");
	if (url == null) {
		url = "preLoanRefrShowAC.do";
	}
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>贷前咨询</title>
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script src="<%=path%>/js/common.js"></script>
</head>
<script language="javascript"></script>
<script type="text/javascript">
	function load(){	
	
	loginReg();
    document.forms(0).Cell1.openfile("<%=path%>/sysloan/loanapply/report/preloanrefr.cll","");
    
	var yearlimit=[];
	var corpusInterest=[];
	var loanmoneyTotal=[];
	var interestTotal=[];
	var rate=[];
	var i=0;
	<%
		PreLoanRefrShowAF af=(PreLoanRefrShowAF)request.getAttribute("preLoanRefrShowAF");
		List list=af.getList();
		for(int j=0;j<list.size();j++)
		{
		  PreLoanRefrDTO dto=(PreLoanRefrDTO)list.get(j);
		  
 	%>
 	
	 		document.forms(0).Cell1.S(3,2,0,"<%=af.getPrintMoney()%>");
 		
		  yearlimit[i]="<%=dto.getYearlimit()%>";
		  corpusInterest[i]="<%=dto.getCorpusInterest()%>";
		  loanmoneyTotal[i]="<%=dto.getLoanmoneyTotal()%>";
		  interestTotal[i]="<%=dto.getInterestTotal()%>";
		  
		  rate[i]="<%=dto.getRate()%>";
		  i++;
		  
 	<%
		}
	%>
		var totalLine=yearlimit.length;			//总的行数	
		var totalPageLine=34;					//每页显示多少行
		var pageTotal=totalLine/totalPageLine;	//总共分几页
		var pageCurrent=0;						//当前页  如果没有头表就设置成0
		var completeline=0;						//记录行的
		
		for(k = 0 ; k < totalLine; k++){
			if(k%totalPageLine==0&&k>0)
			{//插入新页.对上一页求和的完成
				pageCurrent++;
				completeline=k-2;
				document.forms(0).Cell1.insertSheetFromFile("<%=path%>/sysloan/loanapply/report/preloanrefr.cll",0,1,pageCurrent);
				document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页")	
			}
			if(pageCurrent==0)
			{
				document.forms(0).Cell1.S(1,k+4,0,yearlimit[k]);
				document.forms(0).Cell1.d(2,k+4,0,corpusInterest[k]);
				document.forms(0).Cell1.d(3,k+4,0,loanmoneyTotal[k]);
				document.forms(0).Cell1.d(4,k+4,0,interestTotal[k]);	
				document.forms(0).Cell1.S(5,k+4,0,rate[k]);
				
			}else{//向第一页后的所有页插数据
				document.forms(0).Cell1.S(1,k-completeline+2,pageCurrent,yearlimit[k]);
				document.forms(0).Cell1.d(2,k-completeline+2,pageCurrent,corpusInterest[k]);
				document.forms(0).Cell1.d(3,k-completeline+2,pageCurrent,loanmoneyTotal[k]);
				document.forms(0).Cell1.d(4,k-completeline+2,pageCurrent,interestTotal[k]);
				document.forms(0).Cell1.S(5,k-completeline+2,pageCurrent,rate[k]);
				
			}	
		}
		if(completeline==0){//查询出来的记录可以在一页显示的时候会进入
			//document.forms(0).Cell1.S(1,totalLine+4,pageCurrent,"操作员");
			document.forms(0).Cell1.S(2,totalLine+4,pageCurrent,"");
			//document.forms(0).Cell1.S(3,totalLine+4,pageCurrent,"操作时间");
			document.forms(0).Cell1.S(4,totalLine+4,pageCurrent,"");

			document.forms(0).Cell1.DeleteRow(totalLine+5,35-totalLine-4,pageCurrent);
		}else{
			//document.forms(0).Cell1.S(1,totalLine-completeline+2,pageCurrent,"操作员");
			//document.forms(0).Cell1.S(3,totalLine-completeline+2,pageCurrent,"操作时间");
					
		    document.forms(0).Cell1.DeleteRow(totalLine-completeline+3,35-(totalLine-completeline+2),pageCurrent);
		}	
		document.forms(0).Cell1.AllowExtend=false;
		document.forms(0).Cell1.AllowDragdrop=false;
		document.forms(0).Cell1.WorkbookReadonly=true;		
	}  
	
	function printPreview(){
		var k=document.forms(0).Cell1.GetCurSheet();//显示打印预览那个页面
		document.forms(0).Cell1.printPreviewEx(1,k,false);
	}
	function printsheet(){//真正的打印
		var k=document.forms(0).Cell1.GetCurSheet();//显示打印预览那个页面--固定
		document.forms(0).Cell1.PrintSheet(1,k);//固定...
	}
	function Button1_onclick()
	{
		document.forms(0).Cell1.SaveFile();
	}
	function Button2_onclick()
	{
		document.forms(0).Cell1.PrintPageSetup();
	}
	function Button3_onclick()
	{
		document.forms(0).Cell1.FindDialogEx( 0,"");
	}
	function Button4_onclick()
	{
		document.forms(0).Cell1.ImportExcelDlg();
	}
</script>

	<script language="JScript.Encode">
eval(unescape('function%20LoginRegister%28%29//%u6CE8%u518CCELL%0D%0A%20%7B%20%0D%0A%20document.forms%280%29.Cell1.Login%28%22%u6C88%u9633%u91D1%u8F6F%u79D1%u6280%u6709%u9650%u516C%u53F8%22%2C%22%22%2C%2213100104509%22%2C%220740-1662-7775-3003%22%29%3B%20%20%20%20%0D%0A%20%7D'))
</script>
	<body bgcolor="#FFFFFF" text="#000000" onload="load();" onContextmenu="return false">
		<form>
			<table width="95%" border="0" cellspacing="0" cellpadding="0"
				align="center">
				<tr>
					<td>
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="7">
									<img src="<%=path%>/img/table_left.gif" width="7" height="37">
								</td>
								<td background="<%=path%>/img/table_bg_line.gif" width="55">
									&nbsp;
								</td>
								<td width="235" background="<%=path%>/img/table_bg_line.gif">
									&nbsp;
								</td>
								<td background="<%=path%>/img/table_bg_line.gif" align="right">
									<table width="300" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="37">
												<img src="<%=path%>/img/title_banner.gif" width="37"
													height="24">
											</td>
											<td width="189" class=font14 bgcolor="#FFFFFF" align="center"
												valign="bottom">
												<p>
													<font color="00B5DB">贷款申请&gt;贷前咨询</font>
												</p>
											</td>
											<td width="74" class=font14>
												&nbsp;
											</td>
										</tr>
									</table>
								</td>
								<td width="9">
									<img src="<%=path%>/img/table_right.gif" width="9" height="37">
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class=td3>
						<table width="95%" border="0" cellspacing="0" cellpadding="0"
							align="center">
							<tr>
								<td height="35">
									<table border="0" cellspacing="0" cellpadding="0"
										align="center">
										<tr>
										<tr>
											<OBJECT id=Cell1
												style="LEFT:0px;WIDTH:880px; TOP:0px;HEIGHT:550px"
												codeBase="http://192.168.1.44:8088/hafmis/CellWeb5.CAB#version=5,3,7,321"
												classid=clsid:3F166327-8030-4881-8BD2-EA25350E574A
												VIEWASTEXT>
												<PARAM NAME="_Version" VALUE="65536">
												<PARAM NAME="_ExtentX" VALUE="10266">
												<PARAM NAME="_ExtentY" VALUE="7011">
												<PARAM NAME="_StockProps" VALUE="0">
											</OBJECT>
										</tr>
										<tr align="center">
										 <td>
											<INPUT type="button" value="打印预览" onclick="printPreview();"
												name="dispviwe">
											<INPUT type="button" value=" 打印 " onclick="printsheet()"
												name="dispprint">
											<INPUT type="button" value="另存为Excel"
												onclick="Button1_onclick()" 
												name="dispother">
											<INPUT type="button" value="页面设置" onclick="Button2_onclick()"
												name="dispset">
											<INPUT type="button" value="查找对话框" onclick="Button3_onclick()"
												name="dispquery">
											<INPUT type="button" value=" 返回 "
												onclick="location.href='<%=url%>'" >
										 </td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
