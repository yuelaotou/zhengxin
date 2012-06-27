<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>分配菜单</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link href="../css/common.css" rel="stylesheet" type="text/css" />
		<style>
		#menu{
		    width:96%;
		    height: 70%;
			margin: 3px 5px 5px 5px;
			padding: 0 20px 0 0;
			overflow-y: scroll;
			overflow-x: hidden;
		}
		</style>
		<script src="js/alai_tree_1_01.js" type=""></script>
		<script src="js/alai_tree_help_1_01.js" type=""></script>
		<script src="js/alai_tree_check_1_01.js" type=""></script>
		<script language="javascript" type="text/javascript">
<!--
function reportErrors() {
  <logic:messagesPresent>
  var errors = "<html:messages id="error"><bean:write name="error"/>\n</html:messages>"
  alert(errors);
  </logic:messagesPresent>
}

function cancel() {
  document.location.href="show_roles.do";
}

function save() {
  var nodes = tree.getSelectedNodes();
  var idString = "";
  for(i=0;i<nodes.length;i++) {
    idString += nodes[i].itemId + ",";
  }
  idStringAF.idString.value=idString;
  idStringAF.submit();
}

function init() {
  reportErrors();
}
var sys_1=0;
var sys_2=0;
var sys_3=0;
var sys_4=0;
function checks1(obj,rank){
var length=0;
var type="";
	type=document.getElementById("system_type_1").value;
	if(type==0){
		length=document.getElementById("system_1").value;
	}
	if(type==1){
		length=document.getElementById("system_2").value;
	}
	if(type==2){
		length=document.getElementById("system_3").value;
	}
	if(type==3){
		length=document.getElementById("system_4").value;
	}
sys_1=sys_1+1;
var bool=document.all.item(1).checked;
for(i=0;i<length;i++){
	if(sys_1%2==0){
	document.all.item(i).checked="";
	}else{
	document.all.item(i).checked="true";
	}
}
}


function checks2(obj,rank){
var length=0;
var length1=0;
var type="";
	type=document.getElementById("system_type_1").value;
	if(type==0){
		length1=document.getElementById("system_1").value;
	}
	if(type==1){
		length1=document.getElementById("system_2").value;
	}
	if(type==2){
		length1=document.getElementById("system_3").value;
	}
	if(type==3){
		length1=document.getElementById("system_4").value;
	}
	type=document.getElementById("system_type_2").value;
	if(type==0){
		length=document.getElementById("system_1").value;
		length=parseInt(length)+parseInt(length1);
	}
	if(type==1){
		length=document.getElementById("system_2").value;
		length=parseInt(length)+parseInt(length1);
	}
	if(type==2){
		length=document.getElementById("system_3").value;
		length=parseInt(length)+parseInt(length1);
	}
	if(type==3){
		length=document.getElementById("system_4").value;
		length=parseInt(length)+parseInt(length1);
	}
	
sys_2=sys_2+1;
for(i=length1;i<length;i++){
	if(sys_2%2==0){
	document.all.item(parseInt(i)).checked="";
	}else{
	document.all.item(parseInt(i)).checked="true";
	}
}
}

function checks3(obj,rank){
var length=0;
var length1=0;
var length2=0;
var type="";
	type=document.getElementById("system_type_1").value;
	if(type==0){
		length1=document.getElementById("system_1").value;
	}
	if(type==1){
		length1=document.getElementById("system_2").value;
	}
	if(type==2){
		length1=document.getElementById("system_3").value;
	}
	if(type==3){
		length1=document.getElementById("system_4").value;
	}
	type=document.getElementById("system_type_2").value;
	if(type==0){
		length2=document.getElementById("system_1").value;
		length2=parseInt(length2)+parseInt(length1);
	}
	if(type==1){
		length2=document.getElementById("system_2").value;
		length2=parseInt(length2)+parseInt(length1);
	}
	if(type==2){
		length2=document.getElementById("system_3").value;
		length2=parseInt(length2)+parseInt(length1);
	}
	if(type==3){
		length2=document.getElementById("system_4").value;
		length2=parseInt(length2)+parseInt(length1);
	}
	type=document.getElementById("system_type_3").value;
	if(type==0){
		length=document.getElementById("system_1").value;
		length=parseInt(length2)+parseInt(length);
	}
	if(type==1){
		length=document.getElementById("system_2").value;
		length=parseInt(length2)+parseInt(length);
	}
	if(type==2){
		length=document.getElementById("system_3").value;
		length=parseInt(length2)+parseInt(length);
	}
	if(type==3){
		length=document.getElementById("system_4").value;
		length=parseInt(length2)+parseInt(length);
	}
	
sys_3=sys_3+1;
var bool=document.all.item(1).checked;
for(i=length2;i<length;i++){
	if(sys_3%2==0){
	document.all.item(parseInt(i)).checked="";
	}else{
	document.all.item(parseInt(i)).checked="true";
	}
}
}
function checks4(obj,rank){
var length=0;
var length1=0;
var length2=0;
var length4=0
var type="";
	type=document.getElementById("system_type_1").value;
	if(type==0){
		length1=document.getElementById("system_1").value;
	}
	if(type==1){
		length1=document.getElementById("system_2").value;
	}
	if(type==2){
		length1=document.getElementById("system_3").value;
	}
	if(type==3){
		length1=document.getElementById("system_4").value;
	}
	type=document.getElementById("system_type_2").value;
	if(type==0){
		length2=document.getElementById("system_1").value;
		length2=parseInt(length2)+parseInt(length1);
	}
	if(type==1){
		length2=document.getElementById("system_2").value;
		length2=parseInt(length2)+parseInt(length1);
	}
	if(type==2){
		length2=document.getElementById("system_3").value;
		length2=parseInt(length2)+parseInt(length1);
	}
	if(type==3){
		length2=document.getElementById("system_4").value;
		length2=parseInt(length2)+parseInt(length1);
	}
	type=document.getElementById("system_type_3").value;
	if(type==0){
		length4=document.getElementById("system_1").value;
		length4=parseInt(length2)+parseInt(length4);
	}
	if(type==1){
		length4=document.getElementById("system_2").value;
		length4=parseInt(length2)+parseInt(length4);
	}
	if(type==2){
		length4=document.getElementById("system_3").value;
		length4=parseInt(length2)+parseInt(length4);
	}
	if(type==3){
		length4=document.getElementById("system_4").value;
		length4=parseInt(length2)+parseInt(length4);
	}
	type=document.getElementById("system_type_4").value;
	if(type==0){
		length=document.getElementById("system_1").value;
		length=parseInt(length4)+parseInt(length);
	}
	if(type==1){
		length=document.getElementById("system_2").value;
		length=parseInt(length4)+parseInt(length);
	}
	if(type==2){
		length=document.getElementById("system_3").value;
		length=parseInt(length4)+parseInt(length);
	}
	if(type==3){
		length=document.getElementById("system_4").value;
		length=parseInt(length4)+parseInt(length);
	}
	
sys_4=sys_4+1;
var bool=document.all.item(1).checked;
for(i=length4;i<length;i++){
	if(sys_4%2==0){
	document.all.item(parseInt(i)).checked="";
	}else{
	document.all.item(parseInt(i)).checked="true";
	}
}
}
//-->
</script>
	</head>
	<body onload="init()">
		<table class="basicinfo" cellspacing="1">
			<thead>
				<tr>
					<th colspan="4">
						角色菜单权限设置
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th width="25%">
						角色名称:
					</th>
					<td width="25%">
						<logic:notEmpty name="role">
							<bean:write name="role" property="name" />
						</logic:notEmpty>
					</td>
					<td colspan="2"></td>
				</tr>
				<tr>
					<th colspan="4">
						菜单权限
					</th>
				</tr>
			</tbody>
		</table>
		<div id="menu">
			<security:menuSelector name="id" accordingTo="role" />
		</div>
		<table class="basicinfo" cellspacing="1">
			<tfoot>
				<tr>
					<td>
						<input type="button" name="save" value="确定" class="button"
							onclick="save()">
						<input type="button" name="cancel" value="返回" class="button"
							onclick="cancel()">
					</td>
				</tr>
			</tfoot>
		</table>
		<html:form action="/assign_menu_to_role">
			<input type="hidden" name="idString" value="" />
			<input type="hidden" name="itemId" value="<bean:write name="id" />" />
		</html:form>
	</body>
</html>
