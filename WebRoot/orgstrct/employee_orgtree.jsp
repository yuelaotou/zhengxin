<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/taglib/xpup-orgstrct.tld" prefix="orgstrct"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>组织机构</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<style>
		#menu{
		    width:360px;
		    height: 180px;
			margin: 3px 5px 5px 5px;
			padding: 0 20px 0 0;
			overflow-y: scroll;
			overflow-x: hidden;
		}
		</style>
		<script src="js/alai_tree_1_1.js" type=""></script>
		<script src="js/alai_tree_help.js" type=""></script>
		<script type="text/javascript">
		function doEditMenuItem(node) {
		  if(document.all("name") != undefined) {
		    document.all("name").value=node.label.innerHTML;
		    document.all("id").value=node.itemId;
		  }
		}
		function cancel() {
		  window.close();
		}
		function ok() {
		  if(document.all("id").value == "") {
		    alert("请先选择部门！");
		    return;
		  }
		  window.dialogArguments[0]=document.all("name").value;
		  window.dialogArguments[1]=document.all("id").value
		  window.close();
		}
		</script>
	</head>
	<body style="text-align: center">
		<br>
		<div id="menu">
			<orgstrct:orgUnitEditor />
			<script type="text/javascript">
			tree.ondblclick=function() {
				ok();
			}
			</script>
			<br />
		</div>
		您选择的是：
		<input type="text" id="name" name="name" value="" style="width:260px" readonly="readonly"/>
		<input type="hidden" id="id" name="id" value="">
		<br>
		<input type="button" value="确定" style="width:60px" onclick="ok()"> 
		<input type="button" value="取消" style="width:60px" onclick="cancel()">
	</body>
</html>
