<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@page import="org.xpup.hafmis.syscollection.querystatistics.collfncomparison.action.CollFnComparisonEmpAccountPopShowAC"%>
<%
String path = request.getContextPath();
Object pagination = session.getAttribute(CollFnComparisonEmpAccountPopShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String indexs=(String)session.getAttribute("CollFnComparisonEmpAccountPopShowAC_indexs");
%>
<html:html>
  <head>
    <title>职工信息查询</title>
    <link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
  </head>
  <script src="<%=path%>/js/common.js">

</script>
<script type="text/javascript" >
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
function loads(){
	var counts="<bean:write name="pagination" property="nrOfElements"/>";
     if(counts=="0"){
     	document.all.sure.disabled="true";
     }
}
function empAccountPopValues(indexs){

	var a;
	var b=document.getElementsByName("id");
	var c=document.getElementById(tr).childNodes[3].childNodes[0].innerHTML;
	
	var d=document.getElementById(tr).childNodes[2].childNodes[0].innerHTML;
	var e=document.getElementById(tr).childNodes[4].childNodes[0].innerHTML;
	var f=document.getElementById(tr).childNodes[5].childNodes[0].innerHTML;
	
	for(i=0;i<b.length;i++)   {
    	if(b[i].checked==true){
    		a=b[i].value;
    	}
  	}
	if(""+a!="undefined"){
		window.opener.document.forms[0].elements["empId"].value=c;
		window.opener.document.forms[0].elements["orgId"].value=a;
		window.opener.document.forms[0].elements["orgName"].value=d;
		window.opener.document.forms[0].elements["empName"].value=e;
		window.opener.document.forms[0].elements["cardNum"].value=f;
  		if(indexs=="0"){
  			opener.executeAjax();
  		}else{
  			opener.executeAjaxIn();
  		}
		
	}else{
		opener.clears();
	}
	window.close();
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false" onload="loads();">
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
      <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td height="35"> 
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td height="22" bgcolor="#CCCCCC" align="center" width="117"><b class="font14">职工列表</b></td>
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
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
		<tr>         
          <td align="center" bgcolor="C4F0FF" >&nbsp;</td>
          <td align="center" class=td2 >单位编号</td>
		  <td align="center" class=td2 >单位名称</td>
          <td align="center" class=td2 >职工编号</td>
          <td align="center" class=td2 >职工姓名</td>
          <td align="center" class=td2 >证件号码</td>
		</tr>
		<logic:notEmpty name="collFnComparisonEmpAccountPopAF" property="list"> 
         <logic:iterate id="dto" name="collFnComparisonEmpAccountPopAF" property="list" indexId="i">
        <tr id="tr<%=i%>" onclick='gotoClick("tr<%=i %>","s<%=i%>", idAF);gettr("tr<%=i%>");' onMouseOver='this.style.background="#eaeaea"'  onMouseOut='gotoColor("tr<%=i %>","s<%=i %>", idAF);' class=td4  onDblClick='empAccountPopValues("<%=indexs%>");'> 
        
          <td valign="top">
          <p align="left">
            <input id="s<%=i %>" type="radio" name="id" value="<bean:write name="dto" property="orgId"/>" <%if(new Integer(0).equals(i)) out.print("checked"); %>>
          </p>
          </td>
          <td valign="top"><p><bean:write name="dto" property="orgId" format="000000"/></p></td>
          <td valign="top"><p><bean:write name="dto" property="orgName"/></p></td>
          <td valign="top"><p><bean:write name="dto" property="empId"/></p></td>    
          <td valign="top"><p><bean:write name="dto" property="empName"/></p></td>
		<td valign="top"><p><bean:write name="dto" property="cardNum"/></p></td>
        </tr>
        <tr > 
          <td colspan="11" class=td5 ></td>
        </tr>
        </logic:iterate>
        </logic:notEmpty>
       <logic:empty name="collFnComparisonEmpAccountPopAF" property="list">
        <tr> 
          <td colspan="11" height="30" style="color:red">没有找到与条件相符合的结果！</td>
	    </tr>
		<tr > 
          <td colspan="11" class=td5 ></td>
        </tr>
        </logic:empty>
      </table>
      </form>
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr class="td1"> 
		  <td>
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr> 
			  	<td align="left">共<bean:write name="pagination" property="nrOfElements"/> 项</td>
                <td align="right"><jsp:include page="../../../inc/pagination.jsp"><jsp:param name="url" value="collFnComparisonEmpAccountPopShowAC.do"/></jsp:include></td>
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
                	<input type="button" name="sure" value="确 定" class="buttona" onclick='empAccountPopValues("<%=indexs%>");'>
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
