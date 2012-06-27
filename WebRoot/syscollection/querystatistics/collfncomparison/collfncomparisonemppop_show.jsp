<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@page import="org.xpup.hafmis.syscollection.querystatistics.collfncomparison.action.CollFnComparisonEmpPopShowAC"%>
<%
String path = request.getContextPath();
Object pagination = session.getAttribute(CollFnComparisonEmpPopShowAC.PAGINATION_KEY);
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
</script>
<body bgcolor="#FFFFFF" text="#000000">
<form name="idAF" action="" style="margin: 0">
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
                  <td width="148" class=font14 bgcolor="#FFFFFF" align="center" valign="bottom"><a href="#" class=a1>统计查询</a><font color="00B5DB">&gt;</font><a href="#" class=a1>职工流水</a></td>
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
      <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td height="35"> 
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td height="22" bgcolor="#CCCCCC" align="center" width="117"><b class="font14">基 
                  本 信 息</b></td>
                <td height="22" background="<%=path%>/img/bg2.gif" align="center">&nbsp;</td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
        <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=3 align="center" >
          <tr> 
            <td width="17%" class="td1"  >单位编号</td>
            <td > 
              <html:text name="collFnComparisonEmpPopAF" property="orgId" styleClass="input3"  maxlength="50" readonly="true"></html:text>            </td>
            <td width="17%" class="td1"  >单位名称</td>
            <td width="33%" colspan="3"  > 
              <html:text name="collFnComparisonEmpPopAF" property="orgName" styleClass="input3"  maxlength="50" readonly="true"></html:text>            </td>
          </tr>

          <tr> 
            <td width="17%"   class="td1">发生日期</td>
            <td width="15%"  > 
              <html:text name="collFnComparisonEmpPopAF" property="date" styleClass="input3"  maxlength="50" readonly="true"></html:text>            </td>
            <td width="17%" class="td1" >业务类型</td>
            <td width="33%"  colspan="3" > 
             <html:text name="collFnComparisonEmpPopAF" property="bizType" styleClass="input3"  maxlength="50" readonly="true"></html:text>            </td>
          </tr>
		            <tr> 
            <td width="17%"   class="td1">凭证编号</td>
            <td width="33%" ><html:text name="collFnComparisonEmpPopAF" property="docNum" styleClass="input3"  maxlength="50" readonly="true"></html:text></td>
            <td width="17%" class="td1" >&nbsp;</td>
            <td width="33%"  colspan="3" > 
              <input name="txtsearch452222" type="text" id="txtsearch" class="input3"  readonly="readonly">            </td>
          </tr>
          <tr>          </tr>
        </table>
        <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
        </tr>
      </table>

      <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td height="35"> 
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                  <td height="22" bgcolor="#CCCCCC" align="center" width="142"><b class="font14">列表 
                    </b></td>
                  <td height="22" background="<%=path%>/img/bg2.gif" align="center" width="762">&nbsp;</td>
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
          <tr class=td4> 

            <td align="center" class=td2 ><a href="javascript:sort('a102.emp_id')">职工编号</a>
            <logic:equal name="pagination" property="orderBy" value="a102.emp_id">
          	  <logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
          	  <logic:equal name="pagination" property="orderother" value="DESC">▼</logic:equal>
          	</logic:equal></td>
            <td align="center" class=td2 ><a href="javascript:sort('b002.name')">职工姓名</a>
            <logic:equal name="pagination" property="orderBy" value="b002.name">
          	  <logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
          	  <logic:equal name="pagination" property="orderother" value="DESC">▼</logic:equal>
          	</logic:equal></td>
            <td align="center" class=td2 >发生金额</td>
            <td align="center" class=td2 >发生利息 </td>
            <td align="center" class=td2 >发生方向</td>
          </tr>
          <logic:notEmpty name="collFnComparisonEmpPopAF" property="list"> 
          <logic:iterate name="collFnComparisonEmpPopAF" property="list" id="resultdto" indexId="i"> 
          <tr id="tr<%=i%>" onClick='gotoClick("tr<%=i %>","s<%=i%>", idAF);gettr("tr<%=i %>");' onMouseOver='this.style.background="#eaeaea"'  onMouseOut='gotoColor("tr<%=i %>","s<%=i %>", idAF);' class=td4  onDblClick=""> 
          <td style="display:none"> 
              <input id="s<%=i %>" type="radio" name="id" value="<bean:write name="resultdto" property="empId"/>" <%if(new Integer(0).equals(i)) out.print("checked"); %> />
            </td>
			<td valign="top"><p><bean:write name="resultdto" property="empId"/></p></td>
            <td valign="top"><p><bean:write name="resultdto" property="empName"/></p></td>
            <td valign="top"><p><bean:write name="resultdto" property="occur"/></p></td>
            <td valign="top"><p><bean:write name="resultdto" property="interest"/></p></td>
            <td valign="top"><p><bean:write name="resultdto" property="direction"/></p></td>
          </tr>
          <tr > 
            <td colspan="7" class=td5 ></td>
          </tr>
          </logic:iterate>
          </logic:notEmpty>
          <logic:empty name="collFnComparisonEmpPopAF" property="list">
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
          <td align="center">
            <table width="300" border="0" cellspacing="0" cellpadding="0">
            <td >共<bean:write name="pagination" property="nrOfElements"/> 项</td>
              <td >    
                 	<jsp:include page="../../../inc/pagination.jsp"><jsp:param name="url" value="collFnComparisonEmpPopShowAC.do"/></jsp:include>                 
                </td>
            </table>
          </td>
        </tr>
      </table>
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr valign="bottom"> 
          <td  colspan="10" bgcolor="#FFFFFF" align="center" height="30"> 
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</form>
</body>
</html:html>
