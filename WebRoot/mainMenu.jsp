<%@ page contentType="text/html; charset=GBK" %>
<%@page import="org.xpup.common.util.BSUtils" %>
<%@page import="org.xpup.security.wsf.bizsrvc.IMenuControlBS" %>
<%@page import="java.util.List" %>
<%@page import="org.xpup.security.common.domain.MenuItem" %>
<%@page import="org.xpup.hafmis.orgstrct.dto.SecurityInfo" %>
<%String path=request.getContextPath();%>
<html>
<link rel="stylesheet" type="text/css" href="<%=path %>/css/style.css">
<head>
</head>
<body leftmargin="0" topmargin="0" rightmargin="0" bottommargin="0" bgcolor="#CEE7F4" text="#000000">

<SCRIPT language=JavaScript>
	var doImage = doImage;
	var TType = TType;

	function mhHover(tbl, idx, cls)
	{
		var t, d;
		if (document.getElementById)
			t = document.getElementById(tbl);
		else
			t = document.all(tbl);
		if (t == null) return;
		if (t.getElementsByTagName)
			d = t.getElementsByTagName("TD");
		else
			d = t.all.tags("TD");
		if (d == null) return;
		if (d.length <= idx) return;
		d[idx].className = cls;
	}

//ajax
var xmlHttp;

function createXMLHttpRequest() {
    if (window.ActiveXObject) {
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    } 
    else if (window.XMLHttpRequest) {
        xmlHttp = new XMLHttpRequest();
    }
} 

function findInfo(queryString) {
 createXMLHttpRequest();  
	    xmlHttp.onreadystatechange = handleStateChange;
	    xmlHttp.open("GET", queryString, false);
	    xmlHttp.send(null);   
}

function handleStateChange() {
  if(xmlHttp.readyState == 4) {
      if(xmlHttp.status == 200) {
         var x=xmlHttp.responseText;
         eval(x);
      }
   }
}
//关闭系统的是否存在应扎账的业务验证
function checkclearaccount(path){
	    var queryString =path+"/syscommon/checkclearaccount/checkclearaccountAAC.do?";
	    findInfo(queryString);
}
function isclosesys(flag){
   if(flag==2){
	   var x=confirm("尚未进入系统中，是否继续执行本次操作！！！");
	   if(x){
	    parent.window.location="<%=request.getContextPath()%>/closesys.jsp";
	   }
   }else if(flag==0){
	   var x=confirm("您浏览的页面即将关闭，是否继续执行本次操作！！！");
	   if(x){
	    parent.window.location="<%=request.getContextPath()%>/closesys.jsp";
	   }
   }else{
	   var x=confirm("当前业务日期中存在未入账的业务，是否继续！！！");
	   if(x){
	    parent.window.location="<%=request.getContextPath()%>/closesys.jsp";
	   }
   }
}
function closesys(){
   checkclearaccount('<%=path %>');
}

function logoff(){
   var x=confirm("您即将重新登录系统，是否继续执行本次操作！！！");
   if(x){
    parent.window.location="<%=path%>/logoff.jsp";
   }
}
  
</SCRIPT>
<script type="text/javascript">

function over1(t) {
  t.style.color="#ff0000";
}
function out1(t) {
  t.style.color="#000000";
}


//function logoff() {
	//top.document.location.href="./security/logoff.jsp";
//}

//function changeSystem() {
	//.document.location.href="./systems.jsp";
//}
</script>
<DIV id=msviLocalToolbar>
  <TABLE height=19 cellSpacing=0 cellPadding=0  border=0 >
    <TBODY>
      <TR >
       
 <%    
     IMenuControlBS menuControlBS = (IMenuControlBS)BSUtils.getBusinessService("securityControlBS",session.getServletContext());
	 SecurityInfo securityInfo = (SecurityInfo)session.getAttribute("SecurityInfo");
	 if(securityInfo==null){%>
    <script language="JavaScript">
      parent.window.location="<%=path%>/login.jsp";
    </script>
   	<% }
	 String username = securityInfo.getUserInfo().getUsername();
	 String password = securityInfo.getUserInfo().getPassword();
	 List systems = menuControlBS.findAllRootMenu(username);
	 String parentId="";
	 if(session.getAttribute("systemId") == null) {
	  if(systems.size() > 0)
		parentId = (String)((MenuItem)systems.get(0)).getId();
	 }else{
	    parentId=(String)session.getAttribute("systemId");
	 }
	 if(parentId != null) {
	 String str = menuControlBS.getMenu(username, parentId);
	
	
	if(systems.size()>1){
		str=str+"<TD   noWrap class=lt0s align='center'><A href='./toChangeBizDateAction.do' target='rightFrame' ><font size='3' >|</font> <font size='2' > 切换系统</font></A></TD>";
	}
	
	str=str+"<TD  noWrap class=lt0 align='center' onclick='logoff();'><font size='3' >|</font> <font size='2' > 重新登录</font></TD>";
	str=str+"<TD  noWrap class=lt0 align='center' onclick='closesys();'><font size='3' >|</font> <font size='2' > 退出系统</font></TD>";
	str=str+"<TD  noWrap class=lt0 align='center'><A href='http://192.168.2.214:7001/HousingAccumulationFundWeb/index.jsp?name=cx&pass=0000' target='_blank' ><font size='3' >|</font> <font size='2' > 老系统</font></TD>";


	 out.print(str);
	 }else{        
          session.invalidate();       
        }
        
        %>
      </TR>
    </TBODY>
  </TABLE>
</DIV>

<TABLE height=3 cellSpacing=0 cellPadding=0 width="100%" border=0>
  <TBODY>
    <TR>
      <TD valign="top" noWrap bgcolor="#6699CC"> <div id="showtocpic" align="right" style="cursor:hand;display:none"></div>
        <div id="showtoctext" align="left" style="cursor:hand;display:none"></div></TD>
    </TR>
  </TBODY>
</TABLE>
</body>
</html>
