<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@page import="org.xpup.hafmis.syscollection.querystatistics.collfncomparison.action.CollFnComparisonEmpInfoShowAC" %>
<%
String path = request.getContextPath();
Object pagination = session.getAttribute(CollFnComparisonEmpInfoShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
    <link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
  </head>
<script language="javascript" src="<%=path%>/js/common.js"></script>
<script language="JavaScript">
var tr="tr0";
	var oldColor="#ffffff";                            //原来的颜色
	var newColor="#E8FCFD";                     //要变成的颜色
	function chgBGColor(oTD){
		oldColor=oTD.style.backgroundColor;//保存当前颜色
		oTD.style.backgroundColor=newColor;//改变表格颜色
		newColor=oldColor;                 //改变下次要变成的颜色
	}
	function gettr(trindex) {
	tr=trindex;
}
function gettr1(trindex) {
   tr=trindex;
   var empId=document.getElementById(tr).childNodes[1].childNodes[0].innerHTML;
   var empName=document.getElementById(tr).childNodes[2].childNodes[0].innerHTML;
   var name =encodeURI(empName);
   gotoOrgAccount(empId,name);
}
function gotoOrgAccount(id,name){
  location="collFnComparisonEmpAccountShowAC.do?emp_Id="+id+"&emp_Name="+name;
}
</script>
<body bgcolor="#FFFFFF" text="#000000"  onContextmenu="return false">

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
                  <td width="148" class=font14 bgcolor="#FFFFFF" align="center" valign="bottom"><font color="00B5DB">新版职工账&gt;职工信息</font></td>
                <td width="115" class=font14>&nbsp;</td>
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
    <html:form action="/collFnComparisonEmpInfoFindAC" style="margin: 0">
      <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td height="35"> 
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td height="22" bgcolor="#CCCCCC" align="center" width="117"><b class="font14">查 询 条 件</b></td>
                <td height="22" background="<%=path%>/img/bg2.gif" align="center">&nbsp;</td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
        <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=3 align="center" >
          <tr> 
          <td width="13%" class="td1"  >单位编号</td>
            <td width="20%" > 
              <html:text name="collFnComparisonEmpInfoAF" property="orgId" styleClass="input3"  maxlength="50" readonly="true"></html:text>          </td>
            <td width="13%" class="td1"  >单位名称</td>
            <td width="20%" colspan="3"> 
              <html:text name="collFnComparisonEmpInfoAF" property="orgName" styleClass="input3" maxlength="50" readonly="true"></html:text>
            </td>
        </tr>
        <tr> 
          	<td width="13%" class="td1"  >职工编号</td>
            <td width="20%" > 
              <html:text name="collFnComparisonEmpInfoAF" property="empId" styleClass="input3"  maxlength="50"></html:text>          </td>
            <td width="13%" class="td1"  >职工姓名</td>
            <td width="20%"> 
              <html:text name="collFnComparisonEmpInfoAF" property="empName" styleClass="input3" maxlength="50"></html:text>
            </td>
            <td width="13%" class="td1"  >证件号码</td>
            <td width="20%"> 
              <html:text name="collFnComparisonEmpInfoAF" property="cardNum" styleClass="input3" maxlength="50"></html:text>
            </td>
        </tr>
      </table>
      <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
            <td align="right"><br>
            <input type="submit" name="submit1"  class=buttona value="查询"/></td>
        </tr>
      </table>
      <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
            <td align="right"></td>
        </tr>
      </table>
     </html:form>
      <html:form action="/collFnComparisonEmpInfoMaintainAC" style="margin: 0">
	  <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td height="35"> 
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td height="22" bgcolor="#CCCCCC" align="center" width="117"><b class="font14">职 
                  工 列 表 </b></td>
                <td height="22" background="<%=path%>/img/bg2.gif" align="center">&nbsp;</td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr bgcolor="1BA5FF" > 
          <td align="center" height="6" colspan="1" ></td>
        </tr>
      </table>
        <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
          <tr > 
            <td align="center" height="23" bgcolor="C4F0FF" >　</td>
            <td align="center" class=td2 ><a href="javascript:sort('a.id')">职工编号</a>
            <logic:equal name="pagination" property="orderBy" value="a.id">
          	  <logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
          	  <logic:equal name="pagination" property="orderother" value="DESC">▼</logic:equal>
          	</logic:equal></td>
            <td align="center" class=td2 ><a href="javascript:sort('b.name')">职工姓名</a>
            <logic:equal name="pagination" property="orderBy" value="b.name">
          	  <logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
          	  <logic:equal name="pagination" property="orderother" value="DESC">▼</logic:equal>
          	</logic:equal></td>
            <td align="center" class=td2 > 证件号码</td>
            <td align="center" class=td2 > 信息链接</td>
            <td align="center" class=td2 > 工资基数</td>
            <td align="center" class=td2 >单位缴额</td>
            <td align="center" class=td2 >职工缴额</td>
            <td align="center" class=td2 >缴额合计</td>
            <td align="center" class=td2 ><a href="javascript:sort('a.pay_status')">职工状态</a>
            <logic:equal name="pagination" property="orderBy" value="a.pay_status">
          	  <logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
          	  <logic:equal name="pagination" property="orderother" value="DESC">▼</logic:equal>
          	</logic:equal></td>
            <td align="center" class=td2 ><a href="javascript:sort('a.org_pay_month')">单位缴至年月</a>
            <logic:equal name="pagination" property="orderBy" value="a.org_pay_month">
          	  <logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
          	  <logic:equal name="pagination" property="orderother" value="DESC">▼</logic:equal>
          	</logic:equal></td>
            <td align="center" class=td2 ><a href="javascript:sort('a.emp_pay_month')">个人缴至年月</a>
            <logic:equal name="pagination" property="orderBy" value="a.emp_pay_month">
          	  <logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
          	  <logic:equal name="pagination" property="orderother" value="DESC">▼</logic:equal>
          	</logic:equal></td>
          </tr>
          <logic:notEmpty name="collFnComparisonEmpInfoAF" property="list"> 
          <% int j=0;
			String strClass="";%>
          <logic:iterate name="collFnComparisonEmpInfoAF" property="list" id="resultdto" indexId="i">
          <%j++;
			if (j%2==0) {strClass = "td8";
			}
		    else {strClass = "td9";
		    }%> 
          <tr  id="tr<%=i%>" onClick='gotoClickpp("<%=i %>", idAF);gettr("tr<%=i %>");' onMouseOver='this.style.background="#eaeaea"'  onMouseOut='gotoColorpp("<%=i %>", idAF);' class="<%=strClass%>"  onDblClick=""> 
            <td > 
              <input id="s<%=i %>" type="radio" name="id" value="<bean:write name="resultdto" property="empid"/>,<bean:write name="resultdto" property="empname"/>" <%if(new Integer(0).equals(i)) out.print("checked"); %> />
            </td>
            <td valign="top"><p><bean:write name="resultdto" property="empid"/></p></td>
            <td valign="top"><p><bean:write name="resultdto" property="empname"/></p></td>
            <td valign="top"><p><bean:write name="resultdto" property="cardnum"/></p></td>
            <td valign="top">
            <a href="#" onClick="gettr1('tr<%=i %>');">职工账</a>
            </td>
            <td valign="top"><p><bean:write name="resultdto" property="salarybase"/></p></td>
            <td valign="top"><p><bean:write name="resultdto" property="orgpay"/></p></td>
            <td valign="top"><p><bean:write name="resultdto" property="emppay"/></p></td>
            <td valign="top"><p><bean:write name="resultdto" property="paycount"/></p></td>
            <td valign="top"><p><bean:write name="resultdto" property="paystatus"/></p></td>
            <td valign="top"><p><bean:write name="resultdto" property="orgpaymonth"/></p></td>
            <td valign="top"><p><bean:write name="resultdto" property="emppaymonth"/></p></td>
          </tr>
          <tr > 
            <td colspan="12" class=td5 ></td>
          </tr>
          </logic:iterate>
          </logic:notEmpty>
        <logic:empty name="collFnComparisonEmpInfoAF" property="list">
        <tr> 
          <td colspan="18" height="30" style="color:red">没有找到与条件相符合结果！</td>
	    </tr>
		<tr > 
          <td colspan="18" class=td5 ></td>
        </tr>
        </logic:empty>
        </table>
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr class="td1"> 
		  <td>
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr> 
			  	<td align="left">共<bean:write name="pagination" property="nrOfElements"/> 项</td>
                <td align="right"><jsp:include page="../../../inc/pagination.jsp"><jsp:param name="url" value="collFnComparisonEmpInfoShowAC.do"/></jsp:include></td>
              </tr>
            </table>
          </td>
	    </tr>
      </table>
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr valign="bottom"> 
          <td  colspan="10" bgcolor="#FFFFFF" align="center" height="30"> 
              <table border="0" cellspacing="0" cellpadding="0">
                <tr> 
                <logic:notEmpty name="collFnComparisonEmpInfoAF" property="list">
				  <td width="70">
				  <html:submit property="method" styleClass="buttona">
						<bean:message key="button.print" />
				  </html:submit>
				  </td>
				  </logic:notEmpty>
				  <logic:empty name="collFnComparisonEmpInfoAF" property="list">
					  <td width="70">
					  <html:submit property="method" styleClass="buttona" disabled="true">
							<bean:message key="button.print" />
					  </html:submit>
					  </td>
				  </logic:empty>
				  <td width="70"> 
                    <html:submit property="method" styleClass="buttona">
							<bean:message key="button.back" />
					  </html:submit>                 
                  </td>
              </table>
          </td>
        </tr>
      </table>
      </html:form>
    </td>
  </tr>
</table>

</body>
</html:html>
