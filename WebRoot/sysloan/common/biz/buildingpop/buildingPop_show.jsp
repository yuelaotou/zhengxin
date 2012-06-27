<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ page import="org.xpup.hafmis.sysloan.common.biz.buildingpop.action.BuildingPopShowAC" %>
<%@ include file="/checkUrl.jsp"%>
<%
   Object pagination= request.getSession(false).getAttribute(BuildingPopShowAC.PAGINATION_KEY);
   request.setAttribute("pagination",pagination);
   String indexs=(String)session.getAttribute("indexs");
   String objInput=(String)session.getAttribute("objInput");
   String path = request.getContextPath();
 %>
<html:html>
<head>
<title>贷款管理--担保公司</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
<script src="<%=path%>/js/common.js">
</script>
</head>
<script language="javascript">
var tr="tr0"; 
function gettr(trindex) {
  	tr=trindex;
}
//楼盘弹出框确定时调用
function buildingpopqdValues(indexs){
	var objInput = "<%=objInput %>";
	//var b=document.getElementsByName("id");
	var headId = document.getElementById(tr).childNodes[1].innerHTML;
	var floorName=document.getElementById(tr).childNodes[3].innerHTML;
	if(""+headId!="undefined"){
		opener.findFloorNum(headId,floorName);
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
          <td width="7"><img src="<%=path%>/img/table_left.gif" width="7" height="37"></td>
          <td background="<%=path%>/img/table_bg_line.gif" width="555">&nbsp;</td>
          <td width="635" background="<%=path%>/img/table_bg_line.gif"></td>
          <td background="<%=path%>/img/table_bg_line.gif" align="right"> 
            
          </td>
          <td width="1"><img src="<%=path%>/img/table_right.gif" width="9" height="37"></td>
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
                  <td height="22" background="<%=path%>/img/bg2.gif" align="center">&nbsp;</td>
                </tr>
              </table>
            </td>
          </tr>
        </table>
        <html:form action="/buildingPopFindAC.do" style="margin: 0">
        <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=3 align="center" >
          <tr> 
            <td width="13%"   class="td1">开发商编号</td>
            <td width="18%"  > 
              <html:text name="buildingPopShowAF" property="developerId" styleClass="input3" styleId="txtsearch" ></html:text>
            </td>
            <td width="11%" class="td1" >开发商名称</td>
            <td width="21%"  > 
              <html:text name="buildingPopShowAF" property="developerName" styleClass="input3" styleId="txtsearch" ></html:text>
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
      	<form name="idAF" action="" style="margin: 0">
        <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
          <tr > 
            <td align="center" height="23" bgcolor="C4F0FF" >　</td>
            <td align="center" class=td2 >开发商编号</td>
            <td align="center" class=td2 >开发商名称</td>
            <td align="center" class=td2 >楼盘名称</td>
          </tr>
   		<logic:notEmpty name="buildingPopShowAF" property="list">
		<logic:iterate id="element" name="buildingPopShowAF" property="list" indexId="i">
        <tr id="tr<%=i%>" onclick='gotoClick("tr<%=i %>","s<%=i%>", idAF);gettr("tr<%=i %>");' 
        	onMouseOver='this.style.background="#eaeaea"'  
        	onMouseOut='gotoColor("tr<%=i %>","s<%=i %>", idAF);' class=td4  
        	onDblClick='buildingpopqdValues("<%=indexs%>");'> 
        
          <td valign="top">
          <p align="left">
            <input id="s<%=i %>" type="radio" name="id" 
            	value="<bean:write name="element" property="developerId"/>" 
            	<%if(new Integer(0).equals(i)) out.print("checked"); %>>
          </p>
          </td>
          <td valign="top"><bean:write name="element" property="developerId"/></td>
          <td valign="top"><bean:write name="element" property="developerName"/></td>
          <td valign="top"><bean:write name="element" property="floorName"/></td>
        </tr>
        <tr > 
          <td colspan="4" class=td5 ></td>
        </tr>
        </logic:iterate>
        </logic:notEmpty>
        <logic:empty name="buildingPopShowAF" property="list">
        <tr> 
          <td colspan="4" height="30" style="color:red">没有找到与条件相符合结果！</td>
	    </tr>
		<tr > 
          <td colspan="4" class=td5 ></td>
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
                <td align="right"><jsp:include page="../../../../inc/pagination.jsp"><jsp:param name="url" value="buildingPopShowAC.do"/></jsp:include></td>
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
                 <input type="button" name="sure" value="确 定" class="buttona" onclick='buildingpopqdValues("<%=indexs%>");'>
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
