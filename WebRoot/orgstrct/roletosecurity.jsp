<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ page import="java.util.List" %>
<%@ page import="org.xpup.hafmis.orgstrct.dto.OrgDto" %>
<%@ include file="/checkUrl.jsp"%>
<%
   String path=request.getContextPath();
   List orglist=(List)request.getAttribute("roleOrglist");
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
function gotoroles(){
	 var queryString = "roleToSecurityFindOrgAAC.do?";
        var roleid = document.all.roles.value;
        queryString = queryString + "roleid="+roleid; 	 
	    findInfo(queryString);
}
function displays(roleid){
	document.all.roles.value=roleid;
	showlist();
}
function displaysOff(officecode){
	document.all.offices.value=officecode;
	showlist();
}
function displaysBank(collBankId){
	document.all.banks.value=collBankId;
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
	document.all.roles.value=document.all.rolename.value;
	document.all.offices.value=document.all.officename.value;
	document.all.banks.value=document.all.bankname.value;
}
function gotoOffice(){
	 var queryString = "roleToSecurityFindOfficeAAC.do?";
        var offices = document.all.offices.value;
        queryString = queryString + "officecode="+offices; 	 
	    findInfo(queryString);
}
function gotoBank(){
	 var queryString = "roleToSecurityFindBankAAC.do?";
        var collBankId = document.all.banks.value;
        queryString = queryString + "collBankId="+collBankId; 	 
	    findInfo(queryString);
}


function gotoOpen(i){
var temp;
	var temp1;
	var temp2;
	var temp3;
	var temp4;
	eval("temp1=imgOpen"+i);
	eval("temp2=imgClose"+i);
	eval("temp3=imgCloseb"+i);
	   	eval("temp=tr"+i);
		temp.style.display="";
 	  

	temp1.style.display="none";
	temp2.style.display="";
}
function gotoClose(i){
	var temp;
	var temp1;
	var temp2;
	var temp3;
	var temp4;
	var temp5;
	eval("temp1=imgOpen"+i);
	eval("temp2=imgClose"+i);
		eval("temp=tr"+i);
	    if(temp.style.display==""){
		temp.style.display="none";
	 	}
 
	temp2.style.display="none";
	temp1.style.display="";

}
function gotoOpen1(i){
	var temp;
	var temp1;
	var temp2;
	eval("temp1=imgOpenb"+i);
	eval("temp2=imgCloseb"+i);
	   	eval("temp=tro"+i);
		temp.style.display=""; 
	temp.style.display="";
	temp1.style.display="none";
	temp2.style.display="";

}
function gotoClose1(i){
	var temp;
	var temp1;
	var temp2;
	eval("temp1=imgOpenb"+i);
	eval("temp2=imgCloseb"+i);
	   	eval("temp=tro"+i);
		if(temp.style.display==""){
			temp.style.display="none";
		}
  
	temp2.style.display="none";
	temp1.style.display="";

}

function checks(obj,rank){
	var prefix=obj.id.split("_")[0];
	var suffix=obj.id.split("-")[1];
	var arrays=roleToSecurityAF.all;
	
	switch(rank){
	case 1:
		//对2级和3级节点进行操作
    	for(var i=0;i<arrays.length;i++){
	    	if(arrays[i].id.length>1&&arrays[i].id.split("_")[0]==obj.id){
	    		arrays[i].checked=obj.checked;
	    	}
	    }
	    break;
	case 2:
	    //1级节点选中
		var parentNode=document.getElementById(prefix);
		if(!parentNode.checked&&obj.checked){
			parentNode.checked=true;
		}
		//对3级节点进行操作
		for(var i=0;i<arrays.length;i++){
	    	if(arrays[i].id.length>1&&arrays[i].id.split("-")[0]==obj.id){
	    		arrays[i].checked=obj.checked;
	    	}
	    }
	    break;
	case 3:
	    //1级节点选中
		var parentNode1=document.getElementById(prefix);
		if(!parentNode1.checked&&obj.checked){
			parentNode1.checked=true;
		}
		//2级节点选中
		var parentNode2=document.getElementById(obj.id.split("-")[0]);
		if(!parentNode2.checked&&obj.checked){
			parentNode2.checked=true;
		}
	    break;
	}
}
function sgotoOpen(i){
var temp;
	var temp1;
	var temp2;
	var temp3;
	var temp4;
	eval("temp1=simgOpen"+i);
	eval("temp2=simgClose"+i);
	eval("temp3=simgCloseb"+i);
	   	eval("temp=str"+i);
		temp.style.display="";
 	  

	temp1.style.display="none";
	temp2.style.display="";
}
function sgotoClose(i){
	var temp;
	var temp1;
	var temp2;
	var temp3;
	var temp4;
	var temp5;
	eval("temp1=simgOpen"+i);
	eval("temp2=simgClose"+i);
		eval("temp=str"+i);
	    if(temp.style.display==""){
		temp.style.display="none";
	 	}
 
	temp2.style.display="none";
	temp1.style.display="";

}
function sgotoOpen1(i){
	var temp;
	var temp1;
	var temp2;
	eval("temp1=simgOpenb"+i);
	eval("temp2=simgCloseb"+i);
	   	eval("temp=stro"+i);
		temp.style.display=""; 
	temp.style.display="";
	temp1.style.display="none";
	temp2.style.display="";

}
function sgotoClose1(i){
	var temp;
	var temp1;
	var temp2;
	eval("temp1=simgOpenb"+i);
	eval("temp2=simgCloseb"+i);
	   	eval("temp=stro"+i);
		if(temp.style.display==""){
			temp.style.display="none";
		}
  
	temp2.style.display="none";
	temp1.style.display="";

}
function schecks(obj,rank){
	var prefix=obj.id.split("_")[0];
	var suffix=obj.id.split("-")[1];
	var arrays=roleToSecurityAF.all;
	
	switch(rank){
	case 1:
		//对2级和3级节点进行操作
    	for(var i=0;i<arrays.length;i++){
	    	if(arrays[i].id.length>1&&arrays[i].id.split("_")[0]==obj.id){
	    		arrays[i].checked=obj.checked;
	    	}
	    }
	    break;
	case 2:
	    //1级节点选中
		var parentNode=document.getElementById(prefix);
		if(!parentNode.checked&&obj.checked){
			parentNode.checked=true;
		}
		//对3级节点进行操作
		for(var i=0;i<arrays.length;i++){
	    	if(arrays[i].id.length>1&&arrays[i].id.split("-")[0]==obj.id){
	    		arrays[i].checked=obj.checked;
	    	}
	    }
	    break;
	case 3:
	    //1级节点选中
		var parentNode1=document.getElementById(prefix);
		if(!parentNode1.checked&&obj.checked){
			parentNode1.checked=true;
		}
		//2级节点选中
		var parentNode2=document.getElementById(obj.id.split("-")[0]);
		if(!parentNode2.checked&&obj.checked){
			parentNode2.checked=true;
		}
	    break;
	}
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
function disp5(){
	if(document.all.roles.value==""){
		alert("请选择用户！");
		return false;
	}
	if(document.all.offices.value==""){
		alert("请选择办事处！");
		return false;
	}
	document.getElementById("method").value="assignOffice";
}
function disp6(){
	if(document.all.roles.value==""){
		alert("请选择用户！");
		return false;
	}
	if(document.all.banks.value==""){
		alert("请选择银行！");
		return false;
	}
	document.getElementById("method").value="assignBank";
}
</script>

<body bgcolor="#FFFFFF" text="#000000" onload="loads();" onContextmenu="return false">

<html:form action="/roleToSecurityMaintainAC">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" height="95%" >
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
      <table border="0" width="95%"  height="5%" cellspacing=1  cellpadding=0 align="center" > 
        
        <tr id="gjtr">
          <td colspan="4"><table width="100%" border="0" align="center"  cellpadding=0 cellspacing=1  >
			<tr>
              <td width="7%"   class="td1" >角色：</td>
              <td width="14%"  >
              <html:select property="roles" styleClass="input4" onchange="gotoroles();" >
              	<html:option value=""></html:option>
              	<logic:notEmpty name="roleToSecurityAF"  property="rolelist">
              	<logic:iterate name="roleToSecurityAF" id="element" property="rolelist">
              	<option value="<bean:write name="element" property="id"/>"><bean:write name="element" property="name"/></option>
              	</logic:iterate>
			     </logic:notEmpty>
              </html:select>
              </td>
              <td width="9%" class="td1" > 办事处：</td>
              <td width="20%" >
              <html:select property="offices" styleClass="input4" onchange="gotoOffice();">
              	<html:option value=""></html:option>
              	<logic:notEmpty name="roleToSecurityAF"  property="officelist">
              	<logic:iterate name="roleToSecurityAF" id="element" property="officelist">
              	<option value="<bean:write name="element" property="id"/>"><bean:write name="element" property="name"/></option>
              	</logic:iterate>
			     </logic:notEmpty>
              </html:select></td>
              <td width="9%" ><input type="submit" name="submit12"  class=buttona value="分配" onclick="return disp5();"/></td>
              <td width="9%" class="td1" >归集银行：</td>
              <td width="22%" >
              <html:select property="banks" styleClass="input4" onchange="gotoBank();">
              	<html:option value=""></html:option>
              	<logic:notEmpty name="roleToSecurityAF"  property="banklist">
              	<logic:iterate name="roleToSecurityAF" id="element" property="banklist">
              	<option value="<bean:write name="element" property="collBankId"/>"><bean:write name="element" property="collBankName"/></option>
              	</logic:iterate>
			     </logic:notEmpty>
              </html:select></td>
              <td width="10%"  ><input type="submit" name="submit1"  class=buttona value="分配" onclick="return disp6();"/></td>
            </tr>
			
          </table></td>
        </tr>
      </table>
      <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" height="90%">
        <tr height="5%">
          <td height="16" align="right">
          <input type="hidden" name="rolename" value="<bean:write name="roleToSecurityAF" property="rolename"/>">
          <input type="hidden" name="officename" value="<bean:write name="roleToSecurityAF" property="officename"/>">
          <input type="hidden" name="bankname" value="<bean:write name="roleToSecurityAF" property="bankname"/>">
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
          <%
  	      	String oldoffname="";
		  	String oldbankname="";
		  	    int j=0;
		  	    int k=0;
		  	    int m=0;
		  	for(int i=0;i<orglist.size();i++){		  	   
			  	OrgDto dto=(OrgDto)orglist.get(i);
			  	String officename=dto.getOfficename();
			  	String officeid=dto.getOfficeid();
			  	String bankname=dto.getBankname();
			  	String bankid=dto.getBankid();
			  	String orgname=dto.getOrgname();
			  	String orgid=dto.getOrgid();
			   if(!oldoffname.equals(officename)){
			   if(i != 0){
			   %>
	   			</table></td></tr>
	   			</table></td></tr>
	   			<%
	   			}
	   			%>
                <tr> 
                  <td height="25" /> 
                    <table width="130%" border="0" cellspacing="0" cellpadding="2">
                    
                      <tr> 
                      <td  id="imgOpen<%=i%>"  height="25" width="20" onclick="gotoOpen('<%=i%>');">
                      <img src="<%=path%>/img/NodeImg1.gif"></td>
                      <td  id="imgClose<%=i%>"  height="25" width="20" style="display:none" onclick="gotoClose('<%=i%>');">
                      <img src="<%=path%>/img/NodeImg2.gif"></td>
                        <td  height="25" width="20"><input id="<%=j%>" type="checkbox" name="officeid" value="<%=officeid%>" onclick="checks(this,1);"></td>
                        <td  height="25" class="font14"><%=officename%>&nbsp;</td>
                      </tr>
                      <tr bgcolor="#48B9E3"> 
                        <td colspan="5" height="1"></td>
                      </tr>
                    </table>
                  </td>
                </tr>
                
	   <tr  id="tr<%=i%>" style="display:none"><td colspan="5"><table id="mtable<%=i%>" border="0" width="100%">
                      <%
                    k=0;  
	   }else{
	    j=j-1;
	   }
	
	   %>
	   <%
	   			   if(!oldbankname.equals(bankname)){
	   					if(oldoffname.equals(officename)){
	   					
	    %>
	    
	   			</table></td></tr>
	    <%
	   					}
	   			%>
	   		<tr> 
                        <td height="25" > 
                          <table width="100%" border="0" cellspacing="0" cellpadding="2">
                            <tr> 
                              <td  height="25" width="20">&nbsp;</td>
                      <td  id="imgOpenb<%=i%>"  height="25" width="20" onclick="gotoOpen1('<%=i%>');">
                      <img src="<%=path%>/img/NodeImg1.gif"></td>
                      <td  id="imgCloseb<%=i%>"  height="25" width="20" style="display:none" onclick="gotoClose1('<%=i%>');">
                      <img src="<%=path%>/img/NodeImg2.gif"></td>
							  <td   height="25" width="20"><input id="<%=j%>_<%=k%>" type="checkbox" name="bankid" value="<%=bankid%>" onclick="checks(this,2);"></td>
                              <td height="25" class="font14"><%=bankname%>&nbsp;</td>
                            </tr>
                            <tr> 
                              <td colspan="5" height="1" bgcolor="#48B9E3"></td>
                            </tr>
                          </table>
                        </td>
                      </tr>
                      
	   <tr  id="tro<%=i%>" style="display:none"><td colspan="5"><table border="0" width="100%">
                      <%
                      m=0;
	   }else{
	   	k=k-1;
	   }
	   %>
	     <tr > 
                        <td height="25" > 
                          <table width="100%" border="0" cellspacing="0" cellpadding="2">
                            <tr> 
                              <td  height="25" width="20" >&nbsp;</td>
                              <td  height="25" width="20" >&nbsp;</td>
							  <td  height="25" width="20" >&nbsp;</td>
							   <td  height="25" width="20" >
							   <input id="<%=j%>_<%=k%>-<%=m%>" type="checkbox" name="orgid" value="<%=orgid%>" onclick="checks(this,3);">
							   </td>
                              <td  height="25" class="font14"><%=orgname%>&nbsp;</td>
                            </tr>
                            <tr> 
                              <td colspan="5" height="1" bgcolor="#48B9E3"></td>
                            </tr>
                          </table>
                        </td>
                      </tr>
                      <%
	     oldoffname=officename;
	     oldbankname=bankname;
	        j++;
          k++;
           m++;
	     %>
                  </td>
                </tr>
             <%
                  	}
           if(orglist.size()>0){
           %>
          </table>
          </td>
          </tr>
          </table>
          </td>
          </tr>
             <%
          }
          %>
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
                 <%
  	      	String soldoffname="";
		  	String soldbankname=""; 	  	
	          j=0;
	          k=0;
	          m=0;
			for(int i=0;i<list.size();i++){
			OrgDto orgDto=(OrgDto)list.get(i);
				String sofficename=orgDto.getOfficename();
				String sofficeid=orgDto.getOfficeid();
			  	String sbankname=orgDto.getBankname();
			  	String sbankid=orgDto.getBankid();
			  	String sorgname=orgDto.getOrgname();
			  	String sorgid=orgDto.getOrgid();
			   if(!soldoffname.equals(sofficename)){
			   if(i != 0){
			   %>
	   			</table></td></tr>
	   			</table></td></tr>
	   			<%
	   			}
	   			%>	   
                <tr> 
                  <td height="25" /> 
                    <table width="130%" border="0" cellspacing="0" cellpadding="2">
                    
                      <tr>  
                      <td  id="simgOpen<%=i%>"  height="25" width="20" onclick="sgotoOpen('<%=i%>');">
                      <img src="<%=path%>/img/NodeImg1.gif"></td>
                      <td  id="simgClose<%=i%>"  height="25" width="20" style="display:none" onclick="sgotoClose('<%=i%>');">
                      <img src="<%=path%>/img/NodeImg2.gif"></td>
                        <td  height="25" width="20"><input id="s<%=j%>" type="checkbox" name="sofficeid" value="<%=sofficeid%>" onclick="schecks(this,1);"></td>
                        <td  height="25" class="font14"><%=sofficename%></td>
                      </tr>
                      <tr bgcolor="#48B9E3"> 
                        <td colspan="5" height="1"></td>
                      </tr>
                    </table>
                  </td>
                </tr>
	   		<tr> 
                
	   <tr  id="str<%=i%>" style="display:none"><td colspan="5"><table border="0" width="100%">
                      <%
                    k=0;  
	   }else{
	    j=j-1;
	   }
	
	   %>
	   <%
			   if(!soldbankname.equals(sbankname)){
	   					if(soldoffname.equals(sofficename)){
	   					
	    %>
	    
	   			</table></td></tr>
	    <%
	   					}
	   			%><tr>
                        <td height="25" > 
                          <table width="100%" border="0" cellspacing="0" cellpadding="2">
                            <tr> 
                              <td  height="25" width="20">&nbsp;</td>
                      <td  id="simgOpenb<%=i%>"  height="25" width="20" onclick="sgotoOpen1('<%=i%>');">
                      <img src="<%=path%>/img/NodeImg1.gif"></td>
                      <td  id="simgCloseb<%=i%>"  height="25" width="20" style="display:none" onclick="sgotoClose1('<%=i%>');">
                      <img src="<%=path%>/img/NodeImg2.gif"></td>
							  <td   height="25" width="20"><input id="s<%=j%>_<%=k%>" type="checkbox" name="sbankid" value="<%=sbankid%>" onclick="schecks(this,2);"></td>
                              <td height="25" class="font14"><%=sbankname%></td>
                            </tr>
                            <tr> 
                              <td colspan="5" height="1" bgcolor="#48B9E3"></td>
                            </tr>
                          </table>
                        </td>
                      </tr>
                      
	   <tr  id="stro<%=i%>" style="display:none"><td colspan="5"><table border="0" width="100%">
                      <%
                      m=0;
	   }else{
	   	k=k-1;
	   }
	   %>
	     <tr> 
                        <td height="25" > 
                          <table width="100%" border="0" cellspacing="0" cellpadding="2">
                            <tr> 
                              <td  height="25" width="20" >&nbsp;</td>
                              <td  height="25" width="20" >&nbsp;</td>
							  <td  height="25" width="20" >&nbsp;</td>
							   <td  height="25" width="20" >
							   <input id="s<%=j%>_<%=k%>-<%=m%>" type="checkbox" name="sorgid" value="<%=sorgid%>" onclick="schecks(this,3);">
							   </td>
                              <td  height="25" class="font14"><%=sorgname%>
							  </td>
                            </tr>
                            <tr> 
                              <td colspan="5" height="1" bgcolor="#48B9E3"></td>
                            </tr>
                          </table>
                        </td>
                      </tr>
                      <%
	     soldoffname=sofficename;
	     soldbankname=sbankname;
	        j++;
          k++;
           m++;
	     %>
                  </td>
                </tr>
             <%
                  	}
           if(list.size()>0){
           %>
          </table>
          </td>
          </tr>
          </table>
          </td>
          </tr>
             <%
          }
          %>
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
<form action="roleToSecurityShowAC.do" method="POST" name="mform" id="Form1">
    </form>
</body>
</html>
