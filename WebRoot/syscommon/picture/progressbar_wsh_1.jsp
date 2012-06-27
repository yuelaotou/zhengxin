<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>文件上传</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

		<!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->

		<SCRIPT LANGUAGE=javascript>

var myTime1=0;

function counter_1(){
 	myTime1++
 	per_1.innerHTML="<font size=2 color=darkblue>&nbsp;&nbsp;"+myTime1+"%</font>";

 	if (myTime1<100)setTimeout("counter_1()",10); 
 	
	if (myTime1==100)
	{
		var sele1 = document.getElementById("showbankinfo_1");
		sele1.style.display = "none";
		document.all.disp3.disabled="";
		myTime1=0;
	}
} 
function showselect_1()
{
	var   fso,   f,   f1,   fc,   s;   
	var sele1 = document.getElementById("showbankinfo_1");
	
	if(sele1.style.display == "none"){

		sele1.style.display = "";
		
	}
	counter_1();
}


</SCRIPT>

	</head>

	<body>
		<div id="showbankinfo_1"
			style="position:absolute; display:none;  background-color:#F99F6F; left:600px; top:350px; width:350px; height:30px; z-index:1;">
			<table width="300" height="30" border="0" cellpadding="0"
				cellspacing="0">
				<tr height="30">
					<td height="30" colspan="2">
						<table border="0" cellpadding="0" cellspacing="0" width="50%"
							align=center>
							<tr>
								<td width="51%" noWrap>
									<p align="right">
										<FONT face=宋体 color=navy size=2><strong>计算月还本息中，请稍后</strong>：</FONT>
									</p>
								</td>
								<td>
									<div id="per_1"></div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</div>

	</body>
</html>
