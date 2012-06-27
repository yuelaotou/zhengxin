<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@page import="org.xpup.hafmis.syscollection.common.biz.emppop.action.EmployeesShowAC" %>
<%@ include file="/checkUrl.jsp"%>
<%
	Object pagination = session.getAttribute(EmployeesShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String path=request.getContextPath();

%>
<html>
<head>
<title>开户销户</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet"  href="<%=path%>/css/index.css" type="text/css">

</head>

<script language="javascript" src="<%=path%>/js/common.js"/>
<script language="javascript" src="js/common.js"/>
<script language="javascript">

function disp1() {
	document.getElementById("method").value="save";
}
function disp2() {
  	document.getElementById("method").value="modify";
}
function disp3() {
  	document.getElementById("method").value="ret";
}
</script>
<script type="text/javascript">



function executeAjax(){
}
</script>
<script>

function enterNextFocus3(){
	if(event.keyCode==13){
		event.keyCode=9;
		open_findEmp(orgid,st);
	}
}
function empFind(orgid,st,cardnum,empname){
alert(st);
	open_findEmp('<%=path%>',orgid,st,cardnum,empname);
}


</script>
<body bgcolor="#FFFFFF" text="#000000">
<html:form action="/save_employee" method="post"> 
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="7">
									<img src="img/table_left.gif" width="7" height="37">
								</td>
								<td background="img/table_bg_line.gif" width="55">
									&nbsp;
								</td>
								<td width="235" background="img/table_bg_line.gif"><a href="开户销户_单位开户_单位开户.htm"><img src="../img/table_bannerb1.gif" width="116" height="37"></a><a href="开户销户_单位开户_单位列表.htm"><img border="0" height="37" src="img/table_bannerb2.gif" width="119"></a></td>
								<td background="img/table_bg_line.gif" align="right">
									<table width="300" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="37">
												<img src="img/title_banner.gif" width="37" height="24">
											</td>
											<td width="148" class=font14 bgcolor="#FFFFFF" align="center"
												valign="bottom">
												<a href="#" class=a1>开户销户</a><font color="00B5DB">&gt;</font><a
													href="#" class=a1>单位开户</a>
											</td>
											<td width="115" class=font14>
												&nbsp;
											</td>
										</tr>
									</table>
								</td>
								<td width="9">
									<img src="img/table_right.gif" width="9" height="37">
								</td>
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
                <td height="22" bgcolor="#CCCCCC" align="center" width="117"><b class="font14">基 
                  本 信 息</b></td>
                <td height="22" background="images/bg2.gif" align="center">&nbsp;</td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
      <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=3 align="center" >
     	<logic:notPresent name="modifyBeanOrg">
							<tr>
								<td width="13%" class="td1">
									职工编号
								</td>
								<td width="18%">
									<input type="text" name="code" class="input3" onkeydown="enterNextFocus3();" ondblclick="open_findEmp(111,2,1);">
								</td>
								<td>
									<!--<html:button property="bu1" styleClass="input3" ondblclick="open_findOrg();">...</html:button>-->
									<input type="button"  class="buttona"  value="..."  onClick="empFind(181,'null','null','null');">
								</td>
							</tr>
							</logic:notPresent>            
        <tr> 
          <td width="17%" class="td1"  >职工姓名</td>
          <td width="33%" colspan="2"   > 
            <html:text  property="empInfo.name" readonly="true" styleClass="input3"/>
          </td>
          <td width="17%" class="td1"  >身份证号</td>
          <td width="33%"  > 
            <html:text  property="empInfo.cardNum" readonly="true" styleClass="input3"/>
          </td>
        </tr>
        <tr> 
          <td width="17%" class="td1"  >性别</td>
          <td width="33%" colspan="2"  > 
            <html:text  property="empInfo.sex" readonly="true" styleClass="input3"/>
          </td>
          
        </tr>
        <tr> 
          <td width="17%" class="td1"  >所在部门</td>
          <td width="33%" colspan="2"   > 
            <html:text  property="empInfo.department" readonly="true" styleClass="input3"/>
          </td>
          <td width="17%" class="td1"  >联系电话</td>
          <td width="33%"  > 
            <html:text  property="empInfo.tel" readonly="true" styleClass="input3"/>
          </td>
        </tr>
        
        <tr> 
          <td width="17%" class="td1"  >移动电话</td>
          <td width="33%" colspan="2"   > 
            <html:text  property="empInfo.mobileTle" readonly="true" styleClass="input3"/>
          </td>
          <td width="17%" class="td1"  >工资基数</td>
          <td width="33%"  >t<html:text  property="emp.salaryBase"  styleClass="input3"    styleId="txtsearch"></html:text>
          </td>
        </tr>
      </table>


 	<table width="95%" border="0" cellspacing="0" cellpadding="3"
							align="center">
							<tr valign="bottom">
								<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
									<table border="0" cellspacing="0" cellpadding="0">
										<tr>
									<td width="70"><input type="button" name="Submit6" value="确定" class="buttona" ></td>
	                                <td width="70"><input type="button" name="Submit6" value="取消" class="buttona" ></td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
								</td>
				</tr>
			</table>
</html:form>
</body>
<script language="javascript">
	function easyStyle(){
		window.location.href="工资基数调整_简约风格.html";
	}
</script>
</html>
