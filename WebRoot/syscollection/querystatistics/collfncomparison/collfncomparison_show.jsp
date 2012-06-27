<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@page import="org.xpup.hafmis.syscollection.querystatistics.collfncomparison.action.CollFnComparisonShowAC" %>
<%
String path = request.getContextPath();
Object pagination = session.getAttribute(CollFnComparisonShowAC.PAGINATION_KEY);
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
function gettr3(trindex) {
   tr=trindex;
   var id=document.getElementById(tr).childNodes[1].childNodes[0].innerHTML;
   var name=document.getElementById(tr).childNodes[2].childNodes[0].innerHTML;
   var orgname =encodeURI(name);
   gotoOrgAccount1(id,orgname);
}
function gotoOrgAccount1(id,name){
  location="collFnComparisonEmpInfoShowAC.do?id="+id+"&name="+name;
}
function gettr1(trindex) {
   tr=trindex;
   var id=document.getElementById(tr).childNodes[1].childNodes[0].innerHTML;
   var name=document.getElementById(tr).childNodes[2].childNodes[0].innerHTML;
   name =encodeURI(name);
   gotoOrgAccount(id,name);
}
function gotoOrgAccount(id,name){
  location="collFnComparisonOrgAccountForwardAC.do?id="+id+"&name="+name;
}
</script>
<body bgcolor="#FFFFFF" text="#000000">
<table width="1200" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="7"><img src="<%=path%>/img/table_left.gif" width="7" height="37"></td>
          <td background="<%=path%>/img/table_bg_line.gif" width="55">&nbsp;</td>
            <td width="235" background="<%=path%>/img/table_bg_line.gif">&nbsp; </td>
          <td background="<%=path%>/img/table_bg_line.gif" align="right"> 
            <table width="300" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="37"><img src="<%=path%>/img/title_banner.gif" width="37" height="24"></td>
                  <td class=font14 bgcolor="#FFFFFF" align="center" valign="bottom"><font color="00B5DB">统计查询&gt;新版单位账</font></td>
                  <td class=font14>&nbsp;</td>
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
    <html:form action="/collFnComparisonFindAC" style="margin: 0">
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
        <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=0 align="center" >
          <tr> 
            <td width="17%"   class="td1">办事处</td>
            <td width="33%"  >
              <html:select name="collFnComparisonAF" property="dto.officecode" styleClass="input4" >
						<html:options collection="officeList1" property="value" labelProperty="label" />
			  </html:select>
            </td>
            <td width="17%" class="td1" >归集银行</td>
            <td width="33%"  >
              <html:select name="collFnComparisonAF" property="dto.collectionBankId" styleClass="input4" >
						<html:option value=""></html:option>
						<html:options collection="collBankList1" property="value" labelProperty="label" />
			  </html:select>
            </td>
          </tr>
          <tr> 
            <td width="17%"   class="td1">单位编号</td>
            <td width="33%"  > 
              <html:text name="collFnComparisonAF" property="dto.orgId" styleClass="input3"  maxlength="50"></html:text>
            </td>
            <td width="17%" class="td1" >单位名称</td>
            <td width="33%"  > 
              <html:text name="collFnComparisonAF" property="dto.orgName" styleClass="input3" maxlength="50"></html:text>
            </td>
          </tr>
        </table>
      <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
            <td align="right"><br>
            <input type="submit" name="submit1"  class=buttona value="查询"/></td>
        </tr>
      </table>
      </html:form>
      <html:form action="/collFnComparisonMaintainAC" style="margin: 0">
        <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td height="35"> 
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td height="22" bgcolor="#CCCCCC" align="center" width="117"><b class="font14">单 
                  位 列 表 </b></td>
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
            <td align="center" class=td2 ><a href="javascript:sort('a1.id')">单位编号</a>
            <logic:equal name="pagination" property="orderBy" value="a1.id">
          	  <logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
          	  <logic:equal name="pagination" property="orderother" value="DESC">▼</logic:equal>
          	</logic:equal>
            </td>
            <td align="center" class=td2 ><a href="javascript:sort('b1.name')">单位名称</a>
            <logic:equal name="pagination" property="orderBy" value="b1.name">
          	  <logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
          	  <logic:equal name="pagination" property="orderother" value="DESC">▼</logic:equal>
          	</logic:equal>
          	</td>
            <td align="center" class=td2 ><a href="javascript:sort('b1.officecode')">办事处名称</a>
            <logic:equal name="pagination" property="orderBy" value="b1.officecode">
          	  <logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
          	  <logic:equal name="pagination" property="orderother" value="DESC">▼</logic:equal>
          	</logic:equal></td>
            <td align="center" class=td2 ><a href="javascript:sort('b1.collection_bank_id')">归集银行</a>
            <logic:equal name="pagination" property="orderBy" value="b1.collection_bank_id">
          	  <logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
          	  <logic:equal name="pagination" property="orderother" value="DESC">▼</logic:equal>
          	</logic:equal></td>
            <td align="center" class=td2 >信息链接</td>
            <td align="center" class=td2 >职工总数</td>
            <td align="center" class=td2 >工资总额</td>
            <td align="center" class=td2 >单位缴率</td>
            <td align="center" class=td2 >职工缴率</td>
            <td align="center" class=td2 >单位缴额</td>
            <td align="center" class=td2 >职工缴额 </td>
            <td align="center" class=td2 >汇缴总额</td>
            <td align="center" class=td2 >公积金余额</td>
            <td align="center" class=td2 >挂账余额</td>
            <td align="center" class=td2 >账面余额</td>
            <td align="center" class=td2 >单位缴至年月</td>
            <td align="center" class=td2 >个人缴至年月</td>
            <td align="center" class=td2 ><a href="javascript:sort('b1.open_date')">开户日期</a>
            <logic:equal name="pagination" property="orderBy" value="b1.open_date">
          	  <logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
          	  <logic:equal name="pagination" property="orderother" value="DESC">▼</logic:equal>
          	</logic:equal></td>
          </tr>
          <logic:notEmpty name="collFnComparisonAF" property="list"> 
          <% int j=0;
			String strClass="";%>
          <logic:iterate name="collFnComparisonAF" property="list" id="resultdto" indexId="i"> 
          <%j++;
			if (j%2==0) {strClass = "td8";
			}
		    else {strClass = "td9";
		    }%>
          <tr id="tr<%=i%>" onClick='gotoClickpp("<%=i %>", idAF);' onMouseOver='this.style.background="#eaeaea"'  onMouseOut='gotoColorpp("<%=i %>", idAF);' class="<%=strClass%>"  onDblClick=""> 
            <td > 
	           <input id="s<%=i %>" type="radio" name="id" value="<bean:write name="resultdto" property="orgId"/>,<bean:write name="resultdto" property="orgName"/>" <%if(new Integer(0).equals(i)) out.print("checked"); %> />
            </td>
            <td valign="top"><p><bean:write name="resultdto" property="orgId" format="000000"/></p></td>
            <td valign="top"><p><bean:write name="resultdto" property="orgName" /></p></td>
            <td valign="top"><p><bean:write name="resultdto" property="officecode" /></p></td>
            <td valign="top"><p><bean:write name="resultdto" property="collectionBankId" /></p></td>
            <td valign="top">
            <a href="#" onClick="gettr1('tr<%=i %>');">单位账</a> | <a href="#" onClick="gettr3('tr<%=i %>');">职工信息</a>
            </td>
            <td valign="top"><p><bean:write name="resultdto" property="empSum" /></p></td>
            <td valign="top"><p><bean:write name="resultdto" property="salarySum" /></p></td>
            <td valign="top"><p><bean:write name="resultdto" property="orgRate" format="0.00"/></p></td>
            <td valign="top"><p><bean:write name="resultdto" property="empRate" format="0.00"/></p></td>
            <td valign="top"><p><bean:write name="resultdto" property="orgPay" /></p></td>
            <td valign="top"><p><bean:write name="resultdto" property="empPay" /></p></td>
            <td valign="top"><p><bean:write name="resultdto" property="paySum" /></p></td>
            <td valign="top"><p><bean:write name="resultdto" property="balance" /></p></td>
            <td valign="top"><p><bean:write name="resultdto" property="overPay" /></p></td>
            <td valign="top"><p><bean:write name="resultdto" property="paybalance" /></p></td>
            <td valign="top"><p><bean:write name="resultdto" property="orgPayMonth" /></p></td>
            <td valign="top"><p><bean:write name="resultdto" property="empPayMonth" /></p></td>
            <td valign="top"><p><bean:write name="resultdto" property="openDate" /></p></td>
          </tr>
          <tr > 
            <td colspan="20" class=td5 ></td>
          </tr>
        </logic:iterate>
         </logic:notEmpty>
		<logic:empty name="collFnComparisonAF" property="list">
        <tr> 
          <td colspan="20" height="30" style="color:red">没有找到与条件相符合结果！</td>
	    </tr>
		<tr > 
          <td colspan="20" class=td5 ></td>
        </tr>
        </logic:empty>
        </table>
        
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr class="td1"> 
		  <td>
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr> 
			  	<td align="left">共<bean:write name="pagination" property="nrOfElements"/> 项</td>
                <td align="right"><jsp:include page="../../../inc/pagination.jsp"><jsp:param name="url" value="collFnComparisonShowAC.do"/></jsp:include></td>
              </tr>
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
