
<%@ page language="java"
	import="java.util.*,java.io.*,jcifs.smb.ImFilenameFilter"
	pageEncoding="GBK"%>

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
		<%
			int len = 0;
			String filepath = request.getParameter("path");
			if (filepath == null)
				filepath = request.getParameter("hpath");

			if (filepath != null && !filepath.equalsIgnoreCase("")) {
				File file = new File(filepath);
				if (file.exists()) {
					String[] files = file.list(new ImFilenameFilter());
					len = files.length;

				}
			}
		%>
		<title>证件浏览</title>
		<SCRIPT type="text/javascript">
	cu=0;
	num=<%=len%>;
	pa='<%=filepath%>';
    function next()
    {
    	var nex = document.getElementById("nex"); 
		var pre = document.getElementById("pre"); 
    	if(cu<num-1)
    		cu++;
   	 	var pic = document.getElementById("pic");      
    	pic.src="<%=path%>/browse?current="+cu+"&path="+pa;
    	if(cu==num-1)
    		nex.style.visibility="hidden";
    	pre.style.visibility="visible";
    }
    
    function previous()
    {  
    	var nex = document.getElementById("nex"); 
		var pre = document.getElementById("pre"); 
    	if(cu>0)
    		cu--;
     	var pic = document.getElementById("pic");      
    	pic.src = "<%=path%>/browse?current="+cu+"&path="+pa;
    	if(cu==0)
   			pre.style.visibility="hidden";
    	nex.style.visibility="visible";
    }
  	function tobig()
    {  
     	var pic = document.getElementById("pic");
     	pic.width=pic.width*1.2;
     	pic.heigth=pic.heigth*1.2;
    }
    function tosmall()
    {  
     	var pic = document.getElementById("pic");
     	pic.width=pic.width*0.8;
     	pic.heigth=pic.heigth*0.8;
    }
    i=0;
    function toround(){
    	i++;
		if (i>4)
			i=1;
    	var pic = document.getElementById("pic");
    	pic.style.filter="progid:DXImageTransform.Microsoft.BasicImage( Rotation="+i+")";
    }
</SCRIPT>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
	</head>

	<body bgcolor="#E0DA9E">

		<FORM action="browse.jsp">
			<div align="center">
				<input id="pre" type="submit"
					onClick="javascript:previous();return false;" value="上一张图片" />
				<input id="nex" type="submit"
					onClick="javascript:next();return false;" value="下一张图片" />
				<INPUT type="hidden" name="hpath" value="<%=filepath%>" />
				<input id="big" type="submit"
					onClick="javascript:tobig();return false;" value="放大" />
				<input id="small" type="submit"
					onClick="javascript:tosmall();return false;" value="缩小" />
				<input id="round" type="submit"
					onClick="javascript:toround();return false;" value="旋转" />
			</div>
			<br />
			<div align="center">
				<IMG name="pic" src="<%=path%>/browse?current=0&path=<%=filepath%>"
					width="80%" heigth="50%" alt="路径错误，图片无法显示！">
			</div>
		</FORM>
	</body>
</html>
