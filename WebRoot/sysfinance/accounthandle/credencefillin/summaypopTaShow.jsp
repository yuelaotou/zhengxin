<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%
   String summayi=(String)session.getAttribute("summayi");
   String path=request.getContextPath();
 %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
<title>摘要列表</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/index.css" type="text/css">
<script src="<%=request.getContextPath()%>/js/common.js">
</script>
</head>
  
<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false">

<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
  
  <tr> 
    <td class=td3>
      <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td height="35"><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td height="22" bgcolor="#CCCCCC" align="center" width="187"><b class="font14">摘要列表</b></td>
              <td width="443" height="22" align="center" background="<%=path%>/img/bg2.gif">&nbsp;</td>
            </tr>
          </table></td>
        </tr>
      </table>
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr bgcolor="1BA5FF" > 
          <td align="center" height="6" colspan="1" ></td>
        </tr>
      </table>
      <form name="idAF" action="" style="margin: 0">
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
          <tr> 
            <td class=td2 > </td>
            <td align="center" class=td2 >摘要名称</td>
          </tr>
   	<logic:notEmpty name="summaylist">
		<logic:iterate id="summaylist" name="summaylist" indexId="i">
        <tr id="tr<%=i %>" class=td4 onclick='gotoClick("tr<%=i %>","s<%=i %>",idAF);'  onDblClick='loanqdSummayValues("<%=summayi%>");'> 
          <td valign="top">
          <p align="center">
            <input id="s<%=i %>" type="radio" name="id" value="<bean:write name="summaylist" property="bookParameterName"/>" <%if(new Integer(0).equals(i)) out.print("checked"); %>>
          </p>
          </td>
          <td valign="top"><p align="center"><bean:write name="summaylist" property="bookParameterName"/></p></td>
        </tr>
        <tr > 
          <td colspan="2" class=td5 ></td>
        </tr>
        </logic:iterate>
        </logic:notEmpty>
        <logic:empty name="summaylist">
        <tr> 
          <td  height="30" style="color:red">没有找到与条件相符合结果！</td>
	    </tr>
		<tr > 
          <td class=td5 ></td>
        </tr>
        </logic:empty>
        </table>
        </form>
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr valign="bottom"> 
          <td  colspan="7" bgcolor="#FFFFFF" align="center" height="30"> 
            <table border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="70"> 
                 <input type="button" name="sure" value="确 定" class="buttona" onclick='loanqdSummayValues("<%=summayi%>");'>
                </td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</body>
</html:html>
