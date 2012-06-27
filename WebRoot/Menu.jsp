<%@ page contentType="text/html; charset=GBK" %>
<html>
<link rel="stylesheet" href="./css/index.css" type="text/css">
<script language="javascript">
	function movenext()
	{
		top.deeptree.movenext();
	}

	function moveprevious()
	{
		top.deeptree.moveprevious();
    }

	function hidetoc()
	{
		window.parent.midFrame.switchPoint.innerText=4;
		window.parent.CLIENTFRAMESET.cols="0,10,*";
    }

	function synctoc()
	{
   		top.location = top.location.pathname + "?url=" + top.content.location.pathname + top.content.location.search + top.content.location.hash;
	}

	function displaybutton()
	{
		document.all.showtoc.style.display = "block";
	}

	function mouseover(item)
	{
		switch (item)
		{
			case "moveprevious" :
				window.status = "Move up to the previous node in the tree [SHIFT + TAB]";
				document.all.imgMovePrevious.src = "./img/moveprevious2.gif";
				break;
            case "movenext" :
				window.status = "Move down to the next node in the tree [TAB]";
				document.all.imgMoveNext.src = "./img/movenext2.gif";
                break;
            case "synctoc" :
      			window.status = "刷新列表";
				document.all.imgSyncToc.src = "./img/synctoc2.gif"
				break;
			case "hidetoc" :
            	window.status = "隐藏列表";
      			document.all.imgHideToc.src = "./img/hidetoc2.gif"
      			break;
    	}
	}

	function mouseout(item)
	{
		switch (item)
		{
			case "moveprevious" :
      			window.status = "";
      			document.all.imgMovePrevious.src = "./img/moveprevious1.gif";
      			break;
    		case "movenext" :
      			window.status = "";
      			document.all.imgMoveNext.src = "./img/movenext1.gif";
                break;
    		case "synctoc" :
      			window.status = "";
      			document.all.imgSyncToc.src = "./img/synctoc1.gif"
      			break;
    		case "hidetoc" :
      			window.status = "";
      			document.all.imgHideToc.src = "./img/hidetoc1.gif"
      			break;
    	}
    }

	function selectstart()
	{
		window.event.cancelBubble = true;
		window.event.returnValue = false;
		return false;
    }

	function showDate()
	{
		var strDate = "";
		var toDay = new Date();
		strDate += toDay.getYear() + "年" + (toDay.getMonth()+1) + "月" + toDay.getDate() + "日 星期";
		if (toDay.getDay() == 1) strDate += "一";
		if (toDay.getDay() == 2) strDate += "二";
		if (toDay.getDay() == 3) strDate += "三";
		if (toDay.getDay() == 4) strDate += "四";
		if (toDay.getDay() == 5) strDate += "五";
		if (toDay.getDay() == 6) strDate += "六";
		if (toDay.getDay() == 7) strDate += "日";
		document.write(strDate);
	}

    function synctoc()
    {
        var IFrameSrc = document.all.MenuTree.src;
        document.all.MenuTree.src = IFrameSrc;
    }
</script>

<head>
<title>无限级树型菜单</title>
<meta name="Generator" content="Microsoft FrontPage 5.0">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">

</head>
<body bgcolor="#CEE7F4" text="#000000" leftmargin="0" topmargin="0">
<table width="150" height="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC">
  <tr bgcolor="#6699CC">
     <td height="20" bgcolor="#92C9E7" align="center" width="14%">&nbsp;</td>
          <td bgcolor="#92C9E7" width="86%">  
            <script language=javascript >showDate();</script>
          </td>
  </tr>
  <tr>
    <td height="20" align="left">
      <table border="0" cellpadding="0" cellspacing="0" >
        <tr>
          <td height="20" width="4"><img src="./img/1pix.gif" width="4"></td>
          <td height="20"><img id="imgMoveNext" src="./img/movenext1.gif" border="0" /></td>
          <td height="20" width="2"><img src="./img/1pix.gif" width="2"></td>
          <td height="20"><img id="imgMovePrevious" src="./img/moveprevious1.gif" border="0" /></td>
          <td height="20" width="2"><img src="./img/1pix.gif" width="2"></td>
        </tr>
      </table>
    </td>
    <td align="right"> <table border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="20"><img id="imgSyncToc" style="cursor:hand" onclick="synctoc();" onmouseover="mouseover('synctoc');" onmouseout="mouseout('synctoc');" src="./img/synctoc1.gif" title="刷新列表" /></td>
          <td height="20" width="4"><img src="./img/1pix.gif" width="10" height="20"></td>
          <td height="20"><img id="imgHideToc" style="cursor:hand" onclick="hidetoc();" onmouseover="mouseover('hidetoc');" onmouseout="mouseout('hidetoc');" src="./img/hidetoc1.gif" title="隐藏列表" /></td>
          <td height="20" width="4"><img src="./img/1pix.gif" width="4" height="20"></td>
        </tr>
      </table></td>
  </tr>
  <tr>
    <td colspan="2" align="left" bgcolor="CEE7F4">
		<iframe name="MenuTree" src="XMLMenuTree.jsp?nParentID=<%=(String)request.getParameter("nParentID")%>" height="100%" width="165" frameborder="0" scrolling="auto">MenuTree</iframe>
	</td>
  </tr>
</table>

</body>
</html>


