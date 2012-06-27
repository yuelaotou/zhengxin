<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.List" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ page import="org.xpup.hafmis.sysfinance.accmng.subjectdayreport.dto.SubjectdayreportDTO" %>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	List list = (List)request.getAttribute("lists");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
	  <head>
	    <title>科目日报表</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	  </head>
	  
	  <script src="<%=path%>/js/common.js"></script>
	  
  	  <script>
  	  	var stBT;
  	  	function tosubjectStart(st){
			stBT = st;
			gotoSubjectpop('0','<%=path%>','0');
		}
		function tosubjectEnd(st){
			stBT = st;
			gotoSubjectpop('0','<%=path%>','1');
		}
		var flag1="";
		function executeAjax(flag){
			stBT = '0';
			var subjectCodeStart=document.forms[0].elements["subjectCodeStart"].value.trim();
			if(flag1!=""){
				return true;
			}else{
				if(subjectCodeStart.length!=0){
				flag1=subjectCodeStart;
					var queryString = "subjectdayreportFindACC.do?";
					queryString = queryString + "subjectCode="+subjectCodeStart;
		     		findInfo(queryString);
				}
			}
		}
		function executeAjaxIn(flag){
			stBT = '1';
     		var subjectCodeEnd=document.forms[0].elements["subjectCodeEnd"].value.trim();
     		if(flag1!=""){
				return true;
			}else{
	     		if(subjectCodeEnd.length!=0){
	     		flag1=subjectCodeEnd;
	     			var queryString = "subjectdayreportFindACC.do?";
					queryString = queryString + "subjectCode="+subjectCodeEnd;
		     		findInfo(queryString);
	     		}
	     	}
		}
		function displays(error){
			if(error == "1"){
				flag1="";
				return true;
			}else{
				if(error == "2"){
					alert("输入的科目代码必须是一级科目!");
					if(stBT == '0'){
						flag1="";
						document.forms[0].elements["subjectCodeStart"].focus();
						document.forms[0].elements["subjectCodeStart"].value = "";
					}
					if(stBT == '1'){
						flag1="";
						document.forms[0].elements["subjectCodeEnd"].focus();
						document.forms[0].elements["subjectCodeEnd"].value = "";
					}
					return false;
				}else{
					alert("有其他的错误!");
					return false;
				}
			}
		}
		function checkDatas(){
			var subjectCodeStart = document.forms[0].elements["subjectCodeStart"].value.trim();
			var subjectCodeEnd = document.forms[0].elements["subjectCodeEnd"].value.trim();
			var subjectLevelEnd = document.forms[0].elements["subjectLevelEnd"].value.trim();
			var credenceDate = document.forms[0].elements["credenceDate"].value.trim();
			if(subjectCodeStart == null || subjectCodeStart == ""){
				alert("科目代码必须填写!");
				return false;
			}
			if(subjectCodeEnd == null || subjectCodeEnd == ""){
				alert("科目代码必须填写!");
				return false;
			}
			if(subjectLevelEnd == null || subjectLevelEnd == ""){
				alert("科目级次必须填写!");
				return false;
			}
			if(credenceDate == null || credenceDate == ""){
				alert("会计日期必须填写!");
				return false;
			}else{
				return checkDate1(credenceDate);
			}
		}
		function checkDate1(strDate)
		{
		  if(strDate.length!=8)
		  {
		    alert("请输入八位的日期格式，例如20070101！");
		    return false;
		  }
		  if(strDate.substring(0,4)<1900){
		    alert("年份应该大于1900！");
		    return false;
		  }
		  if(strDate.substring(4,6)>12 || strDate.substring(4,6)<1)
		  {
		    alert("月份应该在1-12月之间！");
		    return false;
		  }
		  var tempStrDate=strDate.substring(6,8);
		  var tempStrMonth=strDate.substring(4,6);
		 if(tempStrMonth==2&&tempStrDate>29)
		  {
		    alert("日期不能大于29！");
		    return false;
		  }else if((tempStrMonth==1||tempStrMonth==3||tempStrMonth==5||tempStrMonth==7||tempStrMonth==8||tempStrMonth==10||tempStrMonth==12)&&tempStrDate>31){
		    alert("日期不能大于31！");
		    return false;
		  }else if((tempStrMonth==2||tempStrMonth==4||tempStrMonth==6||tempStrMonth==9||tempStrMonth==11)&&tempStrDate>30){
		    alert("日期不能大于30！");
		    return false;
		  }
		  return true;
		}
		function tofocus(flag) //按回车置下一个位置
		{
			if(flag=='1'){
				document.forms[0].elements["subjectCodeEnd"].focus();
				return false;
			}else{
				document.forms[0].elements["subjectLevelEnd"].focus();
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
					document.form1.Cell1.openfile("<%=path%>/sysfinance/accmng/report/subjectdayreport.cll","");
		      <%}
		      %>
		    }
		 </script>
		 
							 <script type="text/javascript">
								function load(){
									loginReg();
									document.form1.Cell1.openfile("<%=path%>/sysfinance/accmng/report/subjectdayreport.cll","");
								    var subjectCode=[];
								    var subjectName=[];
								    var yesterdayRemainingSum=[];
								    var directionYesterday=[];
								    var todayDebit=[];
								    var todayCredit=[];
								    var todayRemainingSum=[];
								    var direction=[];
								    var todayDebitSum=[];
								    var todayCreditSum=[];
									var i=0;
									
									<%
										SubjectdayreportDTO subjectdayreportDTO = new SubjectdayreportDTO();
										for(int j=0;j<list.size();j++){
										subjectdayreportDTO = (SubjectdayreportDTO)list.get(j);
									%>
									    //把数据传到JS的数组里面..
										subjectCode[i] = "<%=subjectdayreportDTO.getSubjectCode()%>";
										subjectName[i] = "<%=subjectdayreportDTO.getSubjectName()%>"; 
										yesterdayRemainingSum[i] = "<%=subjectdayreportDTO.getYesterdayRemainingSum()%>";
										directionYesterday[i] = "<%=subjectdayreportDTO.getDirectionYesterday()%>";
										todayDebit[i] = "<%=subjectdayreportDTO.getTodayDebit()%>";
										todayCredit[i] = "<%=subjectdayreportDTO.getTodayCredit()%>";
										todayRemainingSum[i] = "<%=subjectdayreportDTO.getTodayRemainingSum()%>";
										direction[i] = "<%=subjectdayreportDTO.getDirection()%>";
										todayDebitSum[i] = "<%=subjectdayreportDTO.getTodayDebitSum()%>";
										todayCreditSum[i] = "<%=subjectdayreportDTO.getTodayCreditSum()%>";
										i++;
									<%}%>
									
									var totalLine=subjectCode.length;		//总的行数 数组的长度
									var totalPageLine=40;					//每页显示多少行--除了第一行
									var pageTotal=totalLine/totalPageLine;	//总共分几页 总行数/每页的行数
									var pageCurrent=0;						//当前页
									var completeline=0;						//记录行的
									
									for(k = 0 ; k < totalLine; k++){
										if(k%totalPageLine==0&&k>0)
										{
											document.form1.Cell1.ReDraw();//重画一个表格
											pageCurrent++;//当前页++	
											completeline=k-2;
											//绘制标签 param 	表页索引号。param 要设置的表页页签名称
											document.form1.Cell1.insertSheetFromFile("<%=path%>/sysfinance/accmng/report/subjectdayreport.cll",0,1,pageCurrent);					
											
											document.form1.Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页")	
										}
										if(pageCurrent==0)
										{	
											document.form1.Cell1.S(1,k+4,0,subjectCode[k]);
											document.form1.Cell1.S(2,k+4,0,subjectName[k]);
											document.form1.Cell1.d(3,k+4,0,yesterdayRemainingSum[k]);
											document.form1.Cell1.S(4,k+4,0,directionYesterday[k]);
											document.form1.Cell1.d(5,k+4,0,todayDebit[k]);
											document.form1.Cell1.d(6,k+4,0,todayCredit[k]);
											document.form1.Cell1.d(7,k+4,0,todayRemainingSum[k]);
											document.form1.Cell1.S(8,k+4,0,direction[k]);
											document.form1.Cell1.d(9,k+4,0,todayDebitSum[k]);
											document.form1.Cell1.d(10,k+4,0,todayCreditSum[k]);
										}
										else{//向第一页后的所有页插数据
											document.form1.Cell1.S(1,k-completeline+2,pageCurrent,subjectCode[k]);
											document.form1.Cell1.S(2,k-completeline+2,pageCurrent,subjectName[k]);
											document.form1.Cell1.d(3,k-completeline+2,pageCurrent,yesterdayRemainingSum[k]);
											document.form1.Cell1.S(4,k-completeline+2,pageCurrent,directionYesterday[k]);
											document.form1.Cell1.d(5,k-completeline+2,pageCurrent,todayDebit[k]);
											document.form1.Cell1.d(6,k-completeline+2,pageCurrent,todayCredit[k]);
											document.form1.Cell1.d(7,k-completeline+2,pageCurrent,todayRemainingSum[k]);
											document.form1.Cell1.S(8,k-completeline+2,pageCurrent,direction[k]);
											document.form1.Cell1.d(9,k-completeline+2,pageCurrent,todayDebitSum[k]);
											document.form1.Cell1.d(10,k-completeline+2,pageCurrent,todayCreditSum[k]);
										}
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
		 
	
 
  <body bgcolor="#FFFFFF" text="#000000" onLoad="islist();" onContextmenu="return false">
  <html:form styleId="form1" action="/subjectdayreportFindAC.do">
			<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
			  <tr>
			    <td>
			      <table width="100%" border="0" cellspacing="0" cellpadding="0">
			        <tr>
			          <td width="7"><img src="<%=path%>/img/table_left.gif" width="7" height="37"></td>
			          <td background="<%=path%>/img/table_bg_line.gif" width="55">&nbsp;</td>
			            <td width="235" background="<%=path%>/img/table_bg_line.gif">&nbsp;</td>
			          <td background="<%=path%>/img/table_bg_line.gif" align="right"> 
			            <table width="300" border="0" cellspacing="0" cellpadding="0">
			              <tr> 
			                <td width="37"><img src="<%=path%>/img/title_banner.gif" width="37" height="24"></td>
			                <td width="189" class=font14 bgcolor="#FFFFFF" align="center" valign="bottom">
			                    <p><font color="00B5DB">账簿管理&gt;科目日报表</font></p>
			                  </td>
			                <td width="74" class=font14>&nbsp;</td>
			              </tr>
			            </table>
			          </td>
			          <td width="9"><img src="<%=path%>/img/table_right.gif" width="9" height="37"></td>
			        </tr>
			      </table>
			    </td>
			  </tr>
			  <tr> 
			      <td class=td3> 
			        <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
			          <tr> 
			            <td height="35"> 
			              <table width="100%" border="0" cellspacing="0" cellpadding="0">
			                <tr> 
			                  <td height="22" bgcolor="#CCCCCC" align="center" width="148"><b class="font14">查 询 条 件</b></td>
			                  <td height="22" background="<%=path%>/img/bg2.gif" align="center" width="772">&nbsp;</td>
			                </tr>
			              </table>
			            </td>
			          </tr>
			        </table>
			        
			        <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=0 align="center" >
			          <tr> 
			            <td width="13%"   class="td1">科目代码<font color="#FF0000">*</font></td>
			            
			            <td width="11%"  > 
			              <html:text name="subjectdayreportAF" property="subjectCodeStart"
										styleClass="input3" onblur="executeAjax('0');" onkeydown="javascrip:if(window.event.keyCode==13){return tofocus('1')}" />
			            </td>
			            
			            <td width="3%"  ><input type="button" name="Submit12" value="..." class="buttona"
									onclick="tosubjectStart('0');" />
						</td>
									
			            <td width="3%" align="center"  >至</td>
			            
			            <td width="11%"  > 
			              <html:text name="subjectdayreportAF" property="subjectCodeEnd"
										styleClass="input3" onblur="executeAjaxIn('1');" onkeydown="javascrip:if(window.event.keyCode==13){return tofocus('2')}" />
			            </td>
			            
			            <td width="3%"  ><input type="button" name="Submit12" value="..." class="buttona"
									onclick="tosubjectEnd('1');" />
						</td>
						<td width="13%" class="td1" >科目级次<font color="#FF0000">*</font></td>
				            <td colspan="5" ><span class="td4"> 
				              <html:select property="subjectLevelEnd" styleClass="input4" name="subjectdayreportAF" onkeydown="enterNextFocus1();">
	          					<option value=""></option>
	          					<html:options  collection="paramValue1" property="value" labelProperty="label"/>
	          				</html:select>
				              </span></td>
			          </tr>
			          <tr> 
			            <td width="13%"   class="td1">会计日期<font color="#FF0000">*</font></td>
			            <td colspan="5"  > 
			              <html:text name="subjectdayreportAF" property="credenceDate"
										styleClass="input3" onkeydown="enterNextFocus1();"/>
			            </td>
			            <td width="11%" class="td1" >所属办事处<font color="#FF0000">*</font></td>
			            <td colspan="3"  ><span class="td4">
			              	<html:select property="officeName" styleClass="input4" name="subjectdayreportAF" onkeydown="enterNextFocus1();">
	          					<option value="全部">全部</option>
	          					<html:options  collection="officeList1" property="value" labelProperty="label"/>
	          				</html:select>
			            </span> </td>
			          </tr>
			        </table>
			        <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
			          <tr> 
			            <td align="right"> 
			              <html:submit property="method" styleClass="buttona" onclick="return checkDatas();"> <bean:message key="button.sure" /></html:submit>
			            </td>
			          </tr>
			        </table>
			        
			       
				        <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
				          <tr> 
				            <td height="10"> 
				              
				            </td>
				          </tr>
				        </table>
				        <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
				          <tr bgcolor="1BA5FF" > 
				            <td align="center" height="6" colspan="1" ></td>
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
											style="LEFT:0px;WIDTH:730px; TOP:0px;HEIGHT:400px"
											codeBase="http://192.168.1.44:8088/hafmis/CellWeb5.CAB#version=5,3,7,321"
											classid=clsid:3F166327-8030-4881-8BD2-EA25350E574A VIEWASTEXT>
											<PARAM NAME="_Version" VALUE="65536">
											<PARAM NAME="_ExtentX" VALUE="10266">
											<PARAM NAME="_ExtentY" VALUE="7011">
											<PARAM NAME="_StockProps" VALUE="0">
										</OBJECT>
									</tr>
									<tr>
										<INPUT type="button" value="打印预览" onclick="printPreview();"
											class="buttonb">
										<INPUT type="button" value=" 打印 " onclick="printsheet()"
											class="buttona">
										<INPUT type="button" value="另存为Excel"
											onclick="Button1_onclick()" class="buttonb">
										<INPUT type="button" value="页面设置" onclick="Button2_onclick()"
											class="buttonb">
										<INPUT type="button" value="查找对话框" onclick="Button3_onclick()"
											class="buttonb">
										<INPUT type="button" value="excel导入"
											onclick="Button4_onclick()" class="buttonb">
									</tr>
								</table>
							</td>
						</tr>
					</table>
				        <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
				          <tr valign="bottom"> 
				            <td  colspan="7" bgcolor="#FFFFFF" align="center" height="20"> 
				              
				            </td>
				          </tr>
				        </table>
			        
			        
			      </td>
			  </tr>
			</table>
			</html:form>
	</body>
</html:html>