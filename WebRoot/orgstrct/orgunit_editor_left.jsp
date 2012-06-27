<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/xpup-orgstrct.tld" prefix="orgstrct"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title>组织机构定义</title>
	<style>
#menu{
    width:100%;
    height: 98%;
	margin: 3px 5px 5px 5px;
	padding: 0 20px 0 0;
	overflow-y: scroll;
	overflow-x: hidden;
}
</style>

	<script src="js/alai_menu.js" type=""></script>
	<script src="js/alai_tree_1_1.js" type=""></script>
	<script src="js/alai_tree_help.js" type=""></script>
	<script language="javascript" src="js/common.js"></script>

</head>
<body>
	<form name="tmp" action="orgunit_editor_left.jsp">
	</form>
	<br />
	<div id="menu">
		<orgstrct:orgUnitEditor />
		<br />
	</div>
	<script type="">

/*建立5个菜单实例对象*/
var m3=new alai_menu(110)
var item1=m3.add("添加顶级机构","js","onAddRootMenuItem()")

var item2=m3.add("添加下级机构","js","onAddSubMenuItem()")
var item3=m3.add("删除选定机构","js","onRemoveMenuItem()")
m3.seperate()
var item4=m3.add("刷新当前页面","js","onRefreshMenuItem()")
//var item4=m3.add("菜单项移至","js","onMoveMenuItem()")

/*显示右键菜单*/
document.body.oncontextmenu=function(){event.returnValue=false;}
document.body.onmousedown=function(){
  if(event.button==2){
   
    if(tree.count()>0){
	  item1.enable=false
	}	
    var node=tree.getSelectedNode();

    if(node==null) {
      item2.enable=false
      item3.enable=false
      //item4.enable=false
    }
    else {
      var length = node.getSibling().length
      item2.enable=true
      item3.enable=true
      /*if(length == 1)
        item4.enable=false
      else
        item4.enable=true*/
    }
    m3.show()
  }
}

function onAddRootMenuItem() {
  var url="maintain_orgunit.do?method=preCreate";
  open(url,"editArea");
}

function onAddSubMenuItem() {
  var node = tree.getSelectedNode();
  var url="maintain_orgunit.do?method=preCreate&parentId=" + node.itemId;
  open(url,"editArea");
}
  var xmlhttp; 
function createXMLHttpRequest(){
	if(window.ActiveXObject){
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	else{
		xmlhttp=new XMLHttpRequest();
	}
}
function onRemoveMenuItem() {
  if(!confirm("删除后将无法恢复，您真的要删除该菜单及其子菜单吗？"))return;
    var node = tree.getSelectedNode();
	createXMLHttpRequest();
	xmlhttp.onreadystatechange=getState;
	var url="find_flagAAC.do?code=" + node.itemId;
	xmlhttp.open("get",url,true);
	xmlhttp.send();
}
function getState(){
  if(xmlhttp.readystate==4)
{
	if(xmlhttp.status==200)
   {
   var flag=xmlhttp.responseText;
	if(flag!=0){
		alert("该办事处下存在归集银行,请先将该办事处下所有银行作废!");
		return false;					 	
	}else{
		var node = tree.getSelectedNode();
  		var url="maintain_orgunit.do?method=remove&orgUnitId=" + node.itemId;
  		open(url,"editArea");
	}
   }
 }
	
}

function onMoveMenuItem() {
  var node = tree.getSelectedNode();
  var pos = prompt("请输入菜单项新的索引值，索引值从1开始。",node.getSiblingIndex());
  if(pos == null) return;
  alert(pos);
}
function onRefreshMenuItem(){
  refresh()
}

function doEditMenuItem(node) {
  var url="maintain_orgunit.do?method=preModify&orgUnitId=" + node.itemId;
  open(url,"editArea");
}

function removeMenuItem(id) {
  var node = tree.getNodeById(id);
  if(node == null) {
    refresh();
    return;
  }
  tree.removeNode(node);
}

function addRootMenuItem(id,caption) {
  root.add(id,caption,"default","js","doEditMenuItem(node)")
}
function addSubMenuItem(parentId,id,caption) {
  var node=tree.getNodeById(parentId);
  if(node == null) {
    refresh();
    return;
  }
  node.add(id,caption,"default","js","doEditMenuItem(node)");
}
function editMenuItem(oldId,id,caption) {
  var node=tree.getNodeById(oldId);
  if(node == null) {
    refresh();
    return;
  }
  node.itemId=id;
  node.label.innerHTML=caption;
}

function refresh() {
  //open("orgunit_editor_left.jsp","_self");
  tmp.submit();
}
</script>
</body>
</html:html>
