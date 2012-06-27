<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>	
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="org.xpup.hafmis.sysloan.common.biz.orgpop.action.OrgpopTaShowAC" %>
<%
	Object pagination= request.getSession(false).getAttribute(OrgpopTaShowAC.PAGINATION_KEY);
   request.setAttribute("pagination",pagination);
   String path=request.getContextPath();
   
   String indexs=(String)session.getAttribute("indexs");
   String qxs=(String)session.getAttribute("qxs");
%>

<html:html>
<head>
<base target="_self">
<title>公用&gt;&gt;查询单位</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
</head>
<script src="<%=path%>/js/common.js">

</script>
<script type="text/javascript" >
function loads(){
	var counts="<bean:write name="pagination" property="nrOfElements"/>";
     if(counts=="0"){
     	document.form1.elements["sure"].disabled="true";
     }
}
</script>
<body bgcolor="#FFFFFF" text="#000000"   onload="loads();">
<% System.out.println(path+"/inc/sort.jsp"); %>
<jsp:include page="../../../../inc/sort.jsp"><jsp:param name="url" value="orgpopTaShowAC.do"/></jsp:include>

<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="7"><img src="<%=path%>/img/table_left.gif" width="7" height="37"></td>
          <td background="<%=path%>/img/table_bg_line.gif" width="90">&nbsp;</td>
          <td width="235" background="<%=path%>/img/table_bg_line.gif"></td>
          <td background="<%=path%>/img/table_bg_line.gif" align="right"></td>
        </tr>
      </table>
    </td>
  </tr>  
  <tr> 
    <td class=td3>
      <html:form action="/orgpopTaFindAC" styleClass="margin: 0" target="_self">
      <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=0 align="center" >
	    <tr>
          <td width="13%"   class="td1">单位编号</td>
	      <td width="18%"  >
			<html:text name="orgpopFindAF" property="id" styleClass="input3" onkeydown="enterNextFocus1();"  styleId="txtsearch"></html:text>
          </td>
	      <td width="11%" class="td1" >单位名称</td>
	      <td width="21%"  >
			<html:text name="orgpopFindAF" property="name" styleClass="input3" onkeydown="enterNextFocus1();"  styleId="txtsearch"></html:text>
          </td>
	      </tr>
	      <tr>
          <td width="13%" class="td1">旧单位编号</td>
	      <td width="18%"  >
			<html:text name="orgpopFindAF" property="oldId" styleClass="input3" onkeydown="enterNextFocus1();"  styleId="txtsearch"></html:text>
          </td>
	      <td width="11%" class="td1" ></td>
	      <td width="21%"  >
			<input name="textfield3" type="text" class="input3" onKeyDown="enterNextFocus1();" disabled="disabled" />
          </td>
	      </tr>
      </table>
	  <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td align="right"><input type="hidden" name="qx" value="<%=qxs%>">
            <html:submit styleClass="buttona">查询</html:submit>
          </td>
        </tr>
      </table>
      </html:form>
      <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td height="35"> 
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td height="22" bgcolor="#CCCCCC" align="center" width="117"><b class="font14">单位列表</b></td>
                <td width="826" height="22" align="center" background="<%=path%>/img/bg2.gif">&nbsp;</td>
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
	  <form name="idAF" action="" style="margin: 0">
      <table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1" cellpadding="3" align="center">
          <tr align="center" bgcolor="C4F0FF"> 
            <td height="23" bgcolor="C4F0FF" >&nbsp;</td>
          <td align="center" class=td2 >
          	<a href="javascript:sort('orgs.id')">单位编号</a>
          	<logic:equal name="pagination" property="orderBy" value="orgs.id">
          	  <logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
          	  <logic:equal name="pagination" property="orderother" value="DESC">▼</logic:equal>
          	</logic:equal>
          </td>
		  <td align="center" class=td2 >
          	<a href="javascript:sort('orgs.oldOrgID')">旧单位编号</a>
          	<logic:equal name="pagination" property="orderBy" value="orgs.oldOrgID">
          	  <logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
          	  <logic:equal name="pagination" property="orderother" value="DESC">▼</logic:equal>
          	</logic:equal>
          </td>
          <td align="center" class=td2 >
            <a href="javascript:sort('orgs.orgInfo.name')">单位名称</a>
          	<logic:equal name="pagination" property="orderBy" value="orgs.orgInfo.name">
          	  <logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
          	  <logic:equal name="pagination" property="orderother" value="DESC">▼</logic:equal>
          	</logic:equal>
          </td>
          
          <td align="center" class=td2 >所在地区</td>
          <td align="center" class=td2 >单位经办人</td>
		</tr>
		<logic:notEmpty name="orgpopList">
			<% int j=0;
			String strClass="";
		%>
		<logic:iterate id="orgpopList" name="orgpopList" indexId="i">
		<%j++;
			if (j%2==0) {strClass = "td8";
			}
		    else {strClass = "td9";
		    }
		%>
        <tr id="tr<%=i%>" onclick='gotoClickpp("<%=i %>", idAF);' onMouseOver='this.style.background="#eaeaea"' onMouseOut='gotoColorpp("<%=i %>", idAF);' class="<%=strClass%>"  onDblClick='qdValues("<%=indexs%>");'> 
        
          <td valign="top">
          <p align="left">
            <input id="s<%=i %>" type="radio" name="id" value="<bean:write name="orgpopList" property="id"/>" <%if(new Integer(0).equals(i)) out.print

("checked"); %>>
          </p>
          </td>
          <td valign="top"><p><bean:write name="orgpopList" property="id" format="000000"/></p></td>
          <td valign="top"><p><bean:write name="orgpopList" property="oldOrgID" format="000000"/></p></td>
          <td valign="top"><p><bean:write name="orgpopList" property="orgInfo.name"/></p></td>    
          <td valign="top"><p><bean:write name="orgpopList" property="orgInfo.region"/></p></td>
          <td valign="top"><p><bean:write name="orgpopList" property="orgInfo.transactor.name"/></p></td>
        </tr>
        
        </logic:iterate>
        </logic:notEmpty>
        <logic:empty name="orgpopList">
        <tr> 
          <td colspan="11" height="30" style="color:red">没有找到与条件相符合结果！</td>
	    </tr>
		
        </logic:empty>
      </table>
       
      </form>
      <form name="form1">
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr class="td1"> 
		  <td>
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr> 
			  	<td align="left">共<bean:write name="pagination" property="nrOfElements"/> 项</td>
                <td align="right"><jsp:include page="../../../../inc/pagination.jsp"><jsp:param name="url" value="orgpopTaShowAC.do"/></jsp:include></td>
              </tr>
          </table></td>
	    </tr>
      </table>
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr valign="bottom"> 
          <td  colspan="7" bgcolor="#FFFFFF" align="center" height="30"> 
            <table border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="70">
                	<input type="button" name="sure" value="确 定" class="buttona" onclick='qdValues("<%=indexs%>");'>
                </td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
      </form>
     </td>
  </tr>
</table>
</body>
</html:html>
