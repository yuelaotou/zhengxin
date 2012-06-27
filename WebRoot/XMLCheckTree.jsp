<%@ page contentType="text/html; charset=GBK" %>
<HTML>
<HEAD>
<TITLE></TITLE>
<link rel="stylesheet" type="text/css" href="css/XMLSelTree.css">
</HEAD>
<body topmargin="2" leftmargin="2" marginheight="0" marginwidth="0" bgcolor="#F1F1F1">
<DIV id="SrcDiv" onselectstart="selectstart()"></DIV>
<BODY>
</HTML>

<SCRIPT LANGUAGE=javascript src="js/XMLSelTree.js"></SCRIPT>
<SCRIPT LANGUAGE=javascript>
	<!--
	var m_sXMLFile	= "CheckMenuNode.jsp";						// 主菜单项文件
	var m_sXSLPath	= "xls/";									// xsl文件相对路径
	var m_oSrcDiv	= SrcDiv;								// HTML标记(菜单容器，菜单在此容器显示)

	function window.onload()
	{
		InitTree(m_sXMLFile, m_sXSLPath, m_oSrcDiv);
	}

	//-->
</SCRIPT>
