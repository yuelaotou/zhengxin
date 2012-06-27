<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ page import="org.xpup.hafmis.sysloan.common.biz.assistantorgpop.action.AssistantorgpopShowAC" %>
<%@ include file="/checkUrl.jsp"%>
<%
   Object pagination= request.getSession(false).getAttribute(AssistantorgpopShowAC.PAGINATION_KEY);
   request.setAttribute("pagination",pagination);
   String indexs=(String)session.getAttribute("indexs");
 %>
<html:html>
<head>
<title>贷款管理--担保公司</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/index.css" type="text/css">
<script src="<%=request.getContextPath()%>/js/common.js">
</script>
</head>
<script language="javascript">
var tr="tr0"; 
	function gettr(trindex) {
	  tr=trindex;
	}
//担保公司弹出框确定时调用
function loanassistantorgpopqdValues(indexs){

	var a;
	var b=document.getElementsByName("id");
	var c=document.getElementById(tr).childNodes[1].innerHTML;
	for(i=0;i<b.length;i++)   
    {
    	if(b[i].checked==true){
    		a=b[i].value;
    	}
  	}
	if(""+a!="undefined"){
		//window.opener.document.all.code.value=format(a)+a;
		//window.opener.document.forms[0].elements["orgPaymentAgreement.organization.id"].value=format(a)+a;
		window.opener.document.forms[0].elements["loanassistantorgId"].value=c;
		var obj=window.opener.document.getElementsByTagName("input");
        window.opener.document.forms[0].elements["assistantOrgId"].value=a;
  		
		
	}else{
		opener.clears();
	}
	window.close();
}
function loads(){
 document.assistantorgpopAF.elements["id"].value="";
 document.assistantorgpopAF.elements["name"].value="";
 
 var counts="<bean:write name="pagination" property="nrOfElements"/>";
     if(counts=="0"){
     	document.form1.elements["sure"].disabled="true";
     }
}
</script>

<body bgcolor="#FFFFFF" text="#000000" onload="loads();" onContextmenu="return false">

<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="7"><img src="<%=request.getContextPath()%>/img/table_left.gif" width="7" height="37"></td>
          <td background="<%=request.getContextPath()%>/img/table_bg_line.gif" width="555">&nbsp;</td>
          <td width="635" background="<%=request.getContextPath()%>/img/table_bg_line.gif"></td>
          <td background="<%=request.getContextPath()%>/img/table_bg_line.gif" align="right"> 
            
          </td>
          <td width="1"><img src="<%=request.getContextPath()%>/img/table_right.gif" width="9" height="37"></td>
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
                  <td height="22" bgcolor="#CCCCCC" align="center" width="117"><b class="font14">查 询 条 件</b></td>
                  <td height="22" background="<%=request.getContextPath()%>/img/bg2.gif" align="center">&nbsp;</td>
                </tr>
              </table>
            </td>
          </tr>
        </table>
        <html:form action="/assistantorgpopFind.do" style="margin: 0">
        <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=3 align="center" >
          <tr> 
            <td width="13%"   class="td1">担保公司编号</td>
            <td width="18%"  > 
              <html:text name="assistantorgpopAF" property="id" styleClass="input3" styleId="txtsearch" ></html:text>
            </td>
            <td width="11%" class="td1" >担保公司名称</td>
            <td width="21%"  > 
              <html:text name="assistantorgpopAF" property="name" styleClass="input3" styleId="txtsearch" ></html:text>
            </td>
          </tr>
          <tr align="right"> 
            <td colspan="4"> 
              <html:submit property="method" styleClass="buttona"><bean:message key="button.search" /></html:submit>
            </td>
          </tr>
        </table>
        </html:form>
      <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td height="35"> 
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                  <td height="22" bgcolor="#CCCCCC" align="center" width="117"><b class="font14">担保公司列表</b></td>
                <td height="22" background="<%=request.getContextPath()%>/img/bg2.gif" align="center">&nbsp;</td>
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
        <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
          <tr > 
            <td align="center" height="23" bgcolor="C4F0FF" >　</td>
            <td align="center" class=td2 >
             <a href="javascript:sort('t.assistant_org_id')">担保公司编号</a>
            <logic:equal name="pagination" property="orderBy" value="t.assistant_org_id">
          	  <logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
          	  <logic:equal name="pagination" property="orderother" value="DESC">▼</logic:equal>
          	</logic:equal>
            </td>
            <td align="center" class=td2 >担保公司名称</td>
          </tr>
   	<logic:notEmpty name="assistantpopList">
		<logic:iterate id="assistantpopList" name="assistantpopList" indexId="i">
        <tr id="tr<%=i%>" onclick='gotoClick("tr<%=i %>","s<%=i%>", idAF);gettr("tr<%=i %>");' onMouseOver='this.style.background="#eaeaea"'  onMouseOut='gotoColor("tr<%=i %>","s<%=i %>", idAF);' class=td4  onDblClick='loanassistantorgpopqdValues("<%=indexs%>");'> 
        
          <td valign="top">
          <p align="left">
            <input id="s<%=i %>" type="radio" name="id" value="<bean:write name="assistantpopList" property="name"/>" <%if(new Integer(0).equals(i)) out.print

("checked"); %>>
          </p>
          </td>
          <td valign="top"><bean:write name="assistantpopList" property="id"/></td>
          <td valign="top"><p><bean:write name="assistantpopList" property="name"/></p></td>
        </tr>
        <tr > 
          <td colspan="12" class=td5 ></td>
        </tr>
        </logic:iterate>
        </logic:notEmpty>
        <logic:empty name="assistantpopList">
        <tr> 
          <td colspan="12" height="30" style="color:red">没有找到与条件相符合结果！</td>
	    </tr>
		<tr > 
          <td colspan="12" class=td5 ></td>
        </tr>
        </logic:empty>
        </table>
        </form>
         <form name="form1" style="margin: 0">
        <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr class="td1"> 
		  <td>
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr> 
			  	<td align="left">共<bean:write name="pagination" property="nrOfElements"/> 项</td>
                <td align="right"><jsp:include page="../../../../inc/pagination.jsp"><jsp:param name="url" value="assistantorgpopShow.do"/></jsp:include></td>
              </tr>
          </table>
          </td>
	    </tr>
      </table>
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr valign="bottom"> 
          <td  colspan="7" bgcolor="#FFFFFF" align="center" height="30"> 
            <table border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="70"> 
                 <input type="button" name="sure" value="确 定" class="buttona" onclick='loanassistantorgpopqdValues("<%=indexs%>");'>
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
