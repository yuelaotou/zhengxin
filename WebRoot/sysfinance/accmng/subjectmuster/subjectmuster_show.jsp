<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.util.List"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ page
	import="org.xpup.hafmis.sysfinance.accmng.subjectmuster.dto.SubjectmusterDTO"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	List list = (List) request.getAttribute("lists");
	String credenceDateYearMonth = (String) request
			.getAttribute("credenceDateYearMonth");
	String credenceNum = (String) request.getAttribute("credenceNum");
	String userName = (String) request.getAttribute("userName");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>凭证汇总表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
</head>
<script src="<%=path%>/js/common.js"></script>

<script>
		function credenceMax(type){
			var credenceNumStart = document.forms[0].elements["credenceNumStart"].value.trim();
			var credenceNumEnd = document.forms[0].elements["credenceNumEnd"].value.trim();
			var credenceDateYear = document.forms[0].elements["credenceDateYear"].value.trim();
			var credenceDateStartMonths = document.forms[0].elements["credenceDateStartMonths"].value.trim();
			var date=credenceDateYear+credenceDateStartMonths;
			var queryString = "credenceMaxAAC.do?";
			if(type=='1'){
				if(credenceNumStart!=null && credenceNumStart!='' && credenceDateStartMonths!='' && credenceDateYear!='' && credenceDateStartMonths!=null && credenceDateYear!=null){
					queryString = queryString + "credenceNumStart="+credenceNumStart + "&type="+type+"&date="+date;
					findInfo(queryString);
				}
			}else{
				if(credenceNumEnd!=null && credenceNumEnd!='' && credenceDateStartMonths!='' && credenceDateYear!='' && credenceDateStartMonths!=null && credenceDateYear!=null){
					queryString = queryString + "credenceNumEnd="+credenceNumEnd + "&type="+type+"&date="+date;
					findInfo(queryString);
				}
			}
		}
		function displays(a,b){
			if(a=='1'){
				alert("当月最大凭证号是："+b);
				document.forms[0].elements["credenceNumStart"].focus();
				return false;
			}else if(a=='2'){
				alert("当月最大凭证号是："+b);
				document.forms[0].elements["credenceNumEnd"].focus();
				return false;
			}
		}
		
		function enterNextFocus_yg(){
			if(event.keyCode==13){
				if(document.forms[0].elements["credenceDateStartMonths"].value!=null && isNumber(document.forms[0].elements["credenceDateStartMonths"].value)){
					document.forms[0].elements["credenceDateEndMonths"].value=document.forms[0].elements["credenceDateStartMonths"].value;
					document.forms[0].elements["credenceDateEndMonths"].focus();
					return false;
				}
			}
		}
		
		function checkMonth_zl(months){
			if(!isNumber(months)){
				alert("会计期间必须是数字!");
				return false;
			}
			if(parseFloat(months)<1 ||parseFloat(months)>12){
				alert("会计期间期数要是,'(1--12之间)'！");
				return false;
			}else{
				return true;
			}
		}
		function checkDatas(){
			var credenceDateYear = document.forms[0].elements["credenceDateYear"].value.trim();
			var credenceDateStartMonths = document.forms[0].elements["credenceDateStartMonths"].value.trim();
			var credenceDateEndMonths = document.forms[0].elements["credenceDateEndMonths"].value.trim();
			var credenceNumStart = document.forms[0].elements["credenceNumStart"].value.trim();
			var credenceNumEnd = document.forms[0].elements["credenceNumEnd"].value.trim();
			var subjectLevel = document.forms[0].elements["subjectLevel"].value.trim();
			if(credenceDateYear == null || credenceDateYear == ""){
				alert("会计期间年度必须填写!");
				return false;
			}else{
				if(!checkYear(credenceDateYear)){
					return false;
				}
			}
			if(credenceDateStartMonths == null || credenceDateStartMonths == ""){
				alert("会计期间首期必须填写!");
				return false;
			}else{
				if(!checkMonth_zl(credenceDateStartMonths)){
					return false;
				}
			}
			if(credenceDateEndMonths == null || credenceDateEndMonths == ""){
				alert("会计期间末期必须填写!");
				return false;
			}else{
				if(!checkMonth_zl(credenceDateEndMonths)){
					return false;
				}
			}
			if(credenceNumStart==""){
					alert("请填写起始凭证号!");
					return false;
				}
			if(credenceNumEnd==""){
					alert("请填写终止凭证号!");
					return false;
				}
			if(!isNumber(credenceNumStart)){
					alert("凭证号必须是数字!");
					return false;
				}
			if(!isNumber(credenceNumEnd)){
					alert("凭证号必须是数字!");
					return false;
				}
			if(subjectLevel == null || subjectLevel == ""){
				alert("科目级次必须填写!");
				return false;
			}
		}
	  </script>

<script type="text/javascript">
		function islist(){
		   <%
		      if(list != null&&list.size()!=0){
		   %>
		        load();
		   <%
		     }else{%>
		     	loginReg();
				document.form1.Cell1.openfile("<%=path%>/sysfinance/accmng/report/subjectmuster1.cll","");
		   <%}
		   %>
		 }
	</script>

<script type="text/javascript">
		function load(){
			loginReg();
			document.form1.Cell1.openfile("<%=path%>/sysfinance/accmng/report/subjectmuster1.cll","");
		    var subjectCode=[];
		    var subjectName=[];
		    var debitSum=[];
		    var creditSum=[];
			var i=0;
			
			<%
				SubjectmusterDTO subjectmusterDTO = new SubjectmusterDTO();
				for(int j=0;j<list.size();j++){
				subjectmusterDTO = (SubjectmusterDTO)list.get(j);
			%>
			    //把数据传到JS的数组里面..
				subjectCode[i] = "<%=subjectmusterDTO.getSubjectCode()%>";
				subjectName[i] = "<%=subjectmusterDTO.getSubjectName()%>"; 
				debitSum[i] = "<%=subjectmusterDTO.getDebitSum()%>";
				creditSum[i] = "<%=subjectmusterDTO.getCreditSum()%>";
				i++;
			<%}%>
			
			var totalLine=subjectCode.length;		//总的行数 数组的长度
			var totalPageLine=35;					//每页显示多少行--除了第一行
			var pageTotal=totalLine/totalPageLine;	//总共分几页 总行数/每页的行数
			var pageCurrent=0;						//当前页
			var completeline=0;						//记录行的
			for(k = 0 ; k < totalLine; k++){
				if(k%totalPageLine==0&&k>0)
				{
					document.form1.Cell1.MergeCells(1, totalPageLine+5, 4, totalPageLine+5);
					document.form1.Cell1.S(1,totalPageLine+5,pageCurrent,"<%=userName%>");
					document.forms(0).Cell1.DrawGridLine(1, totalPageLine+5,4,totalPageLine+5,0, 0, 0);
					document.forms(0).Cell1.DrawGridLine(1, totalPageLine+4,4,totalPageLine+4,1, 2, -1);
					document.form1.Cell1.ReDraw();//重画一个表格
					pageCurrent++;//当前页++	
					completeline=k-2;
					//绘制标签 param 	表页索引号。param 要设置的表页页签名称
					document.form1.Cell1.insertSheetFromFile("<%=path%>/sysfinance/accmng/report/subjectmuster1.cll",0,1,pageCurrent);					
					
					document.form1.Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页")	
				}
				if(pageCurrent==0)
				{	
					//document.form1.Cell1.MergeCells(1, totalLine+4, 4, totalLine+4);
					document.form1.Cell1.S(1,3,0,"<%=credenceDateYearMonth%>");
					document.form1.Cell1.S(3,3,0,"<%=credenceNum%>");
					if(subjectCode[k]=="合计"){
						document.form1.Cell1.MergeCells(1,k+5,2,k+5);
						document.form1.Cell1.SetCellAlign(1,k+5,0,32+4);
					}
					document.form1.Cell1.S(1,k+5,0,subjectCode[k]);
					document.form1.Cell1.S(2,k+5,0,subjectName[k]);
					document.form1.Cell1.d(3,k+5,0,debitSum[k]);
					document.form1.Cell1.d(4,k+5,0,creditSum[k]);
					
				}
				else{//向第一页后的所有页插数据
					document.form1.Cell1.S(1,3,pageCurrent,"<%=credenceDateYearMonth%>");
					document.form1.Cell1.S(3,3,pageCurrent,"<%=credenceNum%>");
					if(subjectCode[k]=="合计"){
						document.form1.Cell1.MergeCells(1,k-completeline+3,2,k-completeline+3);
						document.form1.Cell1.SetCellAlign(1,k-completeline+3,0,32);
					}
					document.form1.Cell1.S(1,k-completeline+3,pageCurrent,subjectCode[k]);
					document.form1.Cell1.S(2,k-completeline+3,pageCurrent,subjectName[k]);
					document.form1.Cell1.d(3,k-completeline+3,pageCurrent,debitSum[k]);
					document.form1.Cell1.d(4,k-completeline+3,pageCurrent,creditSum[k]);
				}
			}
			if(completeline==0)//查询出来的记录可以在一页显示的时候会进入
			{
				document.form1.Cell1.MergeCells(1, totalLine+5, 4, totalLine+5);
				document.form1.Cell1.S(1,totalLine+5,pageCurrent,"<%=userName%>");
				document.forms(0).Cell1.DrawGridLine(1, totalLine+5,4,totalLine+5,0, 0, 0);
				document.forms(0).Cell1.DrawGridLine(1, totalLine+4,4,totalLine+4,1, 2, -1);
				document.forms(0).Cell1.DeleteRow(totalLine+6,totalPageLine+5-(totalLine+5),pageCurrent);
				document.forms(0).Cell1.ReDraw();
			}else{
				document.form1.Cell1.MergeCells(1, totalLine-completeline+3, 4, totalLine-completeline+3);
				document.form1.Cell1.S(1,totalLine-completeline+3,pageCurrent,"<%=userName%>");
				document.forms(0).Cell1.DrawGridLine(1, totalLine-completeline+3,4,totalLine-completeline+3,0, 0, 0);
				document.forms(0).Cell1.DrawGridLine(1, totalLine-completeline+2,4,totalLine-completeline+2,1, 2, -1);
				document.forms(0).Cell1.DeleteRow(totalLine-completeline+4,totalPageLine+5-(totalLine-completeline+3),pageCurrent);
				document.forms(0).Cell1.ReDraw();
			}
			document.form1.Cell1.AllowExtend=false;
			document.form1.Cell1.AllowDragdrop=false;
			document.form1.Cell1.WorkbookReadonly=true;
		}
		
		function printPreview(){
			var k=document.form1.Cell1.GetCurSheet();//显示打印预览那个页面
			document.form1.Cell1.printPreviewEx(1,k,false);
		}
		function printsheet(){//真正的打印
			var k=document.forms(0).Cell1.GetCurSheet();//显示打印预览那个页面--固定
			document.form1.Cell1.PrintSheet(1,k);//固定...
		}
		function Button1_onclick()
		{
			document.form1.Cell1.SaveFile();
		}
		function Button2_onclick()
		{
			document.form1.Cell1.PrintPageSetup();
		}
		function Button3_onclick()
		{
			document.form1.Cell1.FindDialogEx( 0,"");
		}
		function Button4_onclick()
		{
			document.form1.Cell1.ImportExcelDlg();
		}
	</script>

<script language="JScript.Encode">
eval(unescape('function%20LoginRegister%28%29//%u6CE8%u518CCELL%0D%0A%20%7B%20%0D%0A%20document.form1.Cell1.Login%28%22%u6C88%u9633%u91D1%u8F6F%u79D1%u6280%u6709%u9650%u516C%u53F8%22%2C%22%22%2C%2213100104509%22%2C%220740-1662-7775-3003%22%29%3B%20%20%20%20%0D%0A%20%7D'))
</script>

<body bgcolor="#FFFFFF" text="#000000" onLoad="islist();"
	onContextmenu="return false">
	<html:form styleId="form1" action="/subjcetmusterFindAC.do">
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
												<font color="00B5DB">账簿管理&gt;凭证汇总表</font>
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
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="148">
											<b class="font14">查 询 条 件</b>
										</td>
										<td height="22" background="<%=path%>/img/bg2.gif"
											align="center" width="772">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=0 align="center">
						<tr>
							<td width="13%" class="td1">
								会计期间
								<font color="#FF0000">*</font>
								<br>
							</td>
							<td width="10%">
								<html:text name="subjectmusterAF" property="credenceDateYear"
									styleClass="input3" onkeydown="enterNextFocus1();" />
							</td>
							<td width="3%" align="center">
								年
							</td>
							<td width="7%">
								<html:text name="subjectmusterAF"
									property="credenceDateStartMonths" styleClass="input3"
									onkeydown="return enterNextFocus_yg();" />
							</td>
							<td width="3%" align="center">
								月
							</td>
							<td width="3%" align="center">
								至
							</td>
							<td width="7%">
								<html:text name="subjectmusterAF"
									property="credenceDateEndMonths" styleClass="input3"
									onkeydown="enterNextFocus1();" />
							</td>
							<td width="3%" align="center">
								月
							</td>
							<td width="13%" class="td1">
								凭证号
								<font color="#FF0000">*</font>
							</td>
							<td width="17%">
								<html:text name="subjectmusterAF" property="credenceNumStart"
									styleClass="input3" onkeydown="enterNextFocus1();"
									onblur="credenceMax('1');" />
							</td>
							<td width="3%" align="center">
								至
							</td>
							<td width="17%">
								<html:text name="subjectmusterAF" property="credenceNumEnd"
									styleClass="input3" onkeydown="enterNextFocus1();"
									onblur="credenceMax('2');" />
							</td>
						</tr>
						<tr>
							<td width="13%" class="td1">
								所属办事处
								<font color="#FF0000">*</font>
								<br>
							</td>
							<td colspan="7">
								<span class="td4"> <html:select property="officeName"
										styleClass="input4" name="subjectmusterAF"
										onkeydown="enterNextFocus1();">
										<option value="全部">
											全部
										</option>
										<html:options collection="officeList1" property="value"
											labelProperty="label" />
										</html:select>
								</span>
							</td>
							<td width="13%" class="td1">
								科目级次
								<font color="#FF0000">*</font>
							</td>
							<td colspan="4" width="37%">
								<span class="td4"> <html:select property="subjectLevel"
										styleClass="input4" name="subjectmusterAF"
										onkeydown="enterNextFocus1();">
										<html:options collection="paramValue1" property="value"
											labelProperty="label" />
									</html:select> </span>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td align="right">
								<html:submit property="method" styleClass="buttona"
									onclick="return checkDatas();">
									<bean:message key="button.search" />
								</html:submit>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="10">

							</td>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr bgcolor="1BA5FF">
							<td align="center" height="6"></td>
						</tr>
					</table>
					<br>
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table border="0" cellspacing="0" cellpadding="0" align="center">
									<tr>
									<tr>
										<OBJECT id=Cell1
											style="LEFT:0px;WIDTH:900px; TOP:0px;HEIGHT:400px"
											codeBase="http://192.168.1.44:8088/hafmis/CellWeb5.CAB#version=5,3,7,321"
											classid=clsid:3F166327-8030-4881-8BD2-EA25350E574A VIEWASTEXT>
											<PARAM NAME="_Version" VALUE="65536">
											<PARAM NAME="_ExtentX" VALUE="10266">
											<PARAM NAME="_ExtentY" VALUE="7011">
											<PARAM NAME="_StockProps" VALUE="0">
										</OBJECT>
									</tr>
									<tr>
										<td align="center">
											<INPUT type="button" value="打印预览" onclick="printPreview();"
												class="buttonb">
											<INPUT type="button" value=" 打印 " onclick="printsheet()"
												class="buttona">
											<INPUT type="button" value="另存为Excel"
												onclick="Button1_onclick()" class="buttonb">
											<INPUT type="button" value="页面设置" onclick="Button2_onclick()"
												class="buttonb">
											<INPUT type="button" value="查找对话框"
												onclick="Button3_onclick()" class="buttonb">
											<INPUT type="button" value="excel导入"
												onclick="Button4_onclick()" class="buttonb">
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr valign="bottom">
							<td colspan="7" bgcolor="#FFFFFF" align="center" height="20">
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</html:form>
</body>
</html:html>
