<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<SCRIPT>
	function switchSysBar()
	{
		if (switchPoint.innerText=='')
		{
			switchPoint.innerText=4;
			window.parent.CLIENTFRAMESET.cols="0,10,*";
		}
		else
		{
			switchPoint.innerText='';
			window.parent.CLIENTFRAMESET.cols="190,5,*";
		}
	}
</SCRIPT>
<html>
<head>
<title>BANNER</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="height:100%">
<table width="110%"  height="100%" border="0" cellspacing="0" cellpadding="0">
  <tr valign="middle">
    <td height="100%" align="left" background="images/bg_3.gif" onClick="switchSysBar()"> 
     <font style=COLOR:BLACK;CURSOR:hand;FONT-FAMILY:Webdings;FONT-SIZE:9pt>
			<SPAN id=switchPoint>
				<font color="#000000" title="显示列表">4</font>
			</SPAN>
	</font>
    </td>
  </tr>
</table>

</body>
</html>
