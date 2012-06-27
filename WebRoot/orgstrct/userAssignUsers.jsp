<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ page import="java.util.List" %>
<%@ include file="/checkUrl.jsp"%>
<%
   String path=request.getContextPath();
   List orglist=(List)request.getAttribute("userslist");
   List list=(List)request.getAttribute("sparelist");
   String disabled1="";
   String disabled2="";
   if(orglist != null){
	   if(orglist.size()<=0){
	   	disabled2="disabled";
	   }else{
	   	disabled2="";
	   }
   }
   if(list != null){
	   if(list.size()<=0){
	   	disabled1="disabled";
	   }else{
	   	disabled1="";
	   }
   }
 %>
<html>
<head>
<title>住房公积金管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
<script src="<%=path%>/js/common.js">
</head>
<script language="javascript"  src="js/common.js">
</script>
<script type="text/javascript" src="js/init.js">
</script>
<script type="text/javascript" src="js/changeType.js">
</script>

<script language="JavaScript">
<!--
<!--
function MM_reloadPage(init) {  //reloads the window if Nav4 resized
  if (init==true) with (navigator) {if ((appName=="Netscape")&&(parseInt(appVersion)==4)) {
    document.MM_pgW=innerWidth; document.MM_pgH=innerHeight; onresize=MM_reloadPage; }}
  else if (innerWidth!=document.MM_pgW || innerHeight!=document.MM_pgH) location.reload();
}
MM_reloadPage(true);
// -->

function MM_findObj(n, d) { //v4.0
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && document.getElementById) x=document.getElementById(n); return x;
}

function MM_showHideLayers() { //v3.0
  var i,p,v,obj,args=MM_showHideLayers.arguments;
  for (i=0; i<(args.length-2); i+=3) if ((obj=MM_findObj(args[i]))!=null) { v=args[i+2];
    if (obj.style) { obj=obj.style; v=(v=='show')?'visible':(v='hide')?'hidden':v; }
    obj.visibility=v; }
}
//-->
</script>
<script src="js/common.js"></script>
<script>
function gotousers(){
	 var queryString = "userAssignUserAFindAAC.do?";
        var username = document.all.users.value;
        queryString = queryString + "username="+username; 	 
	    findInfo(queryString);
}
function displays(name){
	document.all.users.value=name;
	showlist();
}

function showlist() {
  document.mform.submit();
}
function loads(){
<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
	document.all.users.value=document.all.username.value;
}

function disp1(){
	document.getElementById("method").value="save";
}
function disp2(){
	document.getElementById("method").value="delete";
}
function disp3(){
	document.getElementById("method").value="saveAll";
}
function disp4(){
	document.getElementById("method").value="deleteAll";
}

</script>

<body bgcolor="#FFFFFF" text="#000000" onload="loads();" onContextmenu="return false">

<html:form action="/userAssignUsersMaintainAC">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" height="95%">
  <tr height="5%">
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
                  <td width="148" class=font14 bgcolor="#FFFFFF" align="center"  valign="middle"><a href="#" class=a1>权限管理</a><a href="#" class=a1></a></td>
                  <td width="115" class=font14>&nbsp;</td>
              </tr>
            </table>
          </td>
          <td width="9"><img src="<%=path%>/img/table_right.gif" width="9" height="37"></td>
        </tr>
      </table>
    </td>
  </tr>
  <tr height="95%"> 
    <td class=td3>
      <table border="0" width="95%"  height="5%"  cellspacing=1  cellpadding=0 align="center" > 
        
        <tr id="gjtr">
          <td colspan="4"><table width="100%" border="0" align="center"  cellpadding=0 cellspacing=1  >
			<tr>
              <td width="9%"  > </td>
              <td width="7%"   class="td1" align="center">用户：</td>
              <td width="14%"  >
              <html:select property="users" styleClass="input4" onchange="gotousers();" >
              	<html:option value=""></html:option>
              	<logic:notEmpty name="userAssignUsersAF"  property="userlist">
              	<logic:iterate name="userAssignUsersAF" id="element" property="userlist">
              	<option value="<bean:write name="element" property="username"/>"><bean:write name="element" property="username"/></option>
              	</logic:iterate>
			     </logic:notEmpty>
              </html:select>
              </td>
              <td width="20%" ></td>
              <td width="9%" ></td>
              <td width="9%"  ></td>
              <td width="22%" >
              </td>
              <td width="10%"  ></td>
            </tr>
			
          </table></td>
        </tr>
      </table>
      <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" height="90%">
        <tr>
          <td height="16" align="right">
          <input type="hidden" name="username" value="<bean:write name="userAssignUsersAF" property="username"/>">
          </td>
        </tr>
        <tr>
          <td width="100%" height="16" align="center">
		  
		  <table width="95%" height="100%" border="0" cellspacing="1" cellpadding="0" align="center" onMouseOver="MM_showHideLayers('Layer3','','hide')"  bgcolor="C4F0FF">
          <tr bgcolor="C4F0FF"> 
            <td width="45%" height="30" class=font14 align="center" bgcolor="FFFFFF" ><b>已分配</b></td>
            <td  width="10%" align="center" height="30" bgcolor="FFFFFF">&nbsp;</td>
            <td width="45%" height="30" align="center" class=font14 style="line-height:150%" bgcolor="FFFFFF"><b>未分配</b></td>
          </tr>

          <tr bgcolor="FFFFFF"  > 
            <td width="45%" height="250" valign="top" > 
			<div style="LEFT: 0px; OVERFLOW: scroll; WIDTH: 100%; POSITION: static; TOP: 0px; HEIGHT: 100%">
              <table  id="mtable"  border="0" width="87%"  cellspacing=0  cellpadding=0 align="center"  >
              
		        <logic:notEmpty name="userslist" >
				<logic:iterate name="userslist" id="element">
                <tr> 
                  <td height="25" /> 
                    <table width="130%" border="0" cellspacing="0" cellpadding="2">         
                      <tr> 
                        <td  height="25" width="20">
                        <input type="checkbox" name="subusername" value="<bean:write name="element" property="username"/>" onclick=""></td>
                        <td  height="25" class="font14"><bean:write name="element" property="username"/></td>
                      </tr>
                      <tr bgcolor="#48B9E3"> 
                        <td colspan="5" height="1"></td>
                      </tr>
                    </table>
                  </td>
                </tr>
		        </logic:iterate>
		        </logic:notEmpty>
              </table>
             </div id="lefthp"> 
            </td>
            <td  width="10%" align="center" height="250"> 
              <table width="95%" border="0" cellspacing="0" cellpadding="0">
                <tr> 
                  <td align="center"><input type="hidden" name="method"/>
                  <input type="submit" value="&lt;" onclick=" return disp1();" <%=disabled1%>></td>
                </tr>
				<tr> 
                  <td align="center" height="11"><input type="submit" value="&gt;" onclick="return disp2();" <%=disabled2%>></td>
                </tr>
                
                <tr> 
                  <td align="center"><input type="submit" value="&lt;&lt;" onclick="return disp3();" <%=disabled1%>></td>
                </tr>
				<tr> 
                  <td align="center"><input type="submit" value="&gt;&gt;" onclick="return disp4();" <%=disabled2%>></td>
                </tr>
              </table>
              <br>
            </td>
            <td width="45%" height="250" valign="top" class=font14 style="line-height:150%"> 
             <div style="LEFT: 0px; OVERFLOW: scroll; WIDTH: 100%; POSITION: static; TOP: 0px; HEIGHT: 100%">
			 <table  id="gjtr" border="0" width="87%"  cellspacing=0  cellpadding=0 align="center"  >
                    <logic:notEmpty name="sparelist" >
					<logic:iterate name="sparelist" id="element">
                <tr> 
                  <td height="25" /> 
                    <table width="130%" border="0" cellspacing="0" cellpadding="2">
                      <tr>  
                        <td  height="25" width="20">
                        <input id="s" type="checkbox" name="ssubusername" value="<bean:write name="element" property="username"/>" onclick=""></td>
                        <td  height="25" class="font14"><bean:write name="element" property="username"/></td>
                      </tr>
                      <tr bgcolor="#48B9E3"> 
                        <td colspan="5" height="1"></td>
                      </tr>
                    </table>
                  </td>
                </tr>
			        </logic:iterate>
			        </logic:notEmpty>
              </table>
			  </div>
            </td>
          </tr>
          
          <table width="100%" border="0" cellspacing="0" cellpadding="3" align="center">
          <tr> 
            <td   bgcolor="#FFFFFF" height="30" width="45%" valign="bottom" align="center" >
			
            </td>
            
            
          </tr>
        
        <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
          <tr> 
            <td   bgcolor="#FFFFFF" height="30" width="45%" valign="bottom" align="center" >
			
            </td>
            
            
          </tr>
        </table>
		  
		  
		  </td>
        </tr>
      </table> 
	  
	 
        <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
          <tr > 
            <td class=td5 ></td>
          </tr>
        </table>
       
      </td>
  </tr>
</table>
</html:form>
<form action="userAssignUsersShowAC.do" method="POST" name="mform" id="Form1">
    </form>
</body>
</html>
