
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

var myTime=0;

function counter(){
 	myTime++
 	per.innerHTML="<font size=2 color=darkblue>&nbsp;&nbsp;"+myTime+"%</font>";

 	if (myTime<100)setTimeout("counter()",300); 
 	
	if (myTime==100)
	{
			var sele = document.getElementById("showbankinfo");
			sele.style.display = "none";
			
			//myTime=0;
	}
 
} 
function showselect()
{
	var fso, f, f1, fc, s;   

    fso = new ActiveXObject("Scripting.FileSystemObject");   
   	f = fso.GetFolder("d:\\picture\\");   
    fc = new Enumerator(f.files); 
    if(fc.atEnd()){
    	alert("没有可上传的图片，上传失败！");
    	return false;
    }
	var sele = document.getElementById("showbankinfo");
	if(sele.style.display == "none"){
		sele.style.display = "";
	}
	counter();
}


</SCRIPT>

	</head>

	<body>

		<div id="showbankinfo"
			style="position:absolute; display:none;  background-color:#F99F6F; left:500px; top:200px; width:300px; height:30px; z-index:1;">
			<table width="300" height="30" border="0" cellpadding="0"
				cellspacing="0">
				<tr height="30">
					<td height="30" colspan="2">


						<table border="0" cellpadding="0" cellspacing="0" width="50%"
							align=center>
							<tr>
								<td width="51%" noWrap>
									<p align="right">
										<FONT face=宋体 color=navy size=2><strong>上传中，请稍后</strong>：</FONT>
									</p>
								</td>
								<td width="4%" height="8%" bordercolor="#000000">
									<marquee id="jdt" align="middle" direction="right"
										scrolldelay=200 bgcolor="gainsboro" scrollamount=1
										style="BORDER-RIGHT: black 1px outset; BORDER-TOP: black 1px outset; FONT-SIZE: xx-small; BORDER-LEFT: black 1px outset; WIDTH: 180px; COLOR: #000080; BORDER-BOTTOM: black 1px outset; HEIGHT: 13px"
										behavior="slide">
										███████████████████████████████████████████████████████████████████████████████
									</marquee>
								</td>
								<td width="45%" align=left>
									<div id="per"></div>
								</td>

								<marquee id="jdt" align="middle" direction="right"
									scrolldelay=200 bgcolor="gainsboro" scrollamount=1
									style="BORDER-RIGHT: black 1px outset; BORDER-TOP: black 1px outset; FONT-SIZE: xx-small; BORDER-LEFT: black 1px outset; WIDTH: 180px; COLOR: #000080; BORDER-BOTTOM: black 1px outset; HEIGHT: 13px"
									behavior="slide"></marquee>

							</tr>
						</table>

					</td>
				</tr>
			</table>
		</div>

	</body>
</html>
