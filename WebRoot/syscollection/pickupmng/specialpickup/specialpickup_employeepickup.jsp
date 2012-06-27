<%@ page language="java" pageEncoding="gbk"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security" %>
<link rel="stylesheet" href="../../../css/index.css" type="text/css">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
  <head>
    <html:base />
    
    <title>提取情况.jsp</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body bgcolor="#FFFFFF" text="#000000">

<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="7"><img src="../../../img/table_left.gif" width="7" height="37"></td>
          <td background="../../../img/table_bg_line.gif" width="55">&nbsp;</td>
          <td width="235" background="../../../img/table_bg_line.gif"><a href="提取管理_单位提取办理_简约风格.html"></a></td>
          <td background="../../../img/table_bg_line.gif" align="right"> 
            <table width="300" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="37"><img src="../../../img/title_banner.gif" width="37" height="24"></td>
                <td width="190" class=font14 bgcolor="#FFFFFF" align="center" valign="bottom"> <font color="00B5DB">职工提取情况 </font></td>
                <td width="73" class=font14>&nbsp;</td>
              </tr>
            </table>
          </td>
          <td width="9"><img src="../../../img/table_right.gif" width="9" height="37"></td>
        </tr>
      </table>
    </td>
  </tr>
  <tr> 
    <td class=td3> 
      <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td height="35"> 
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td height="22" bgcolor="#CCCCCC" align="center" width="127"><b class="font14">查询条件</b></td>
                <td width="816" height="22" align="right" background="../../../img/bg2.gif">&nbsp; 
                </td>
              </tr>
            </table>
			<table width="100%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr bgcolor="1BA5FF" > 
          <td align="center" height="6" colspan="1" ></td>
        </tr>
      </table>
          </td>
        </tr>
      </table>
	  <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=0 align="center">
        <tr> 
          <td width="17%" height="35" class="td1">提取时间</td>
          <td width="33%"><input name="textfield" type="text" id="txtsearch" class="input3" onKeyDown="enterNextFocus1();" ></td>
          <td width="17%" class="td1">提取原因</td>
          <td width="33%"><input name="textfield2" type="text" id="textfield" class="input3" onKeyDown="enterNextFocus1();" ></td>
        </tr>
		 <tr> 
          <td height="35" colspan="3">&nbsp;</td>
          <td width="33%" align="right"><input type="submit" name="Submit2" class="buttona" value="查询" /></td>
		 </tr>
      </table>
	  <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
          <tr> 
            <td class=h4>合计：提取金额<u>：1</u> 销户利息<u>：1</u> 提取总额<u>：2</u></td>
          </tr>
      </table>
	  
      <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr>
          <td height="22" bgcolor="#CCCCCC" align="center" width="127"><b class="font14">提取信息</b></td>
          <td width="816" height="22" align="right" background="../../../img/bg2.gif">&nbsp;</td>
        </tr>
      </table>
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr bgcolor="1BA5FF" > 
          <td align="center" height="6" colspan="1" ></td>
        </tr>
      </table>
	  
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <form name="form1">
          <tr > 
            <td align="center" bgcolor="C4F0FF" >提取金额</td>
            <td align="center" class=td2 >销户利息</td>
            <td align="center" class=td2 >提取总额▲</td>
            <td align="center" class=td2 >提取类型</td>
            <td align="center" class=td2 >提取原因▲</td>
            <td align="center" class=td2 >提取时间</td>
          </tr>
          <tr id="tr1"  onMouseOver='this.style.background="#eaeaea"'  onMouseOut='this.style.background="#ffffff"' > 
            <td valign="top">3000.00</td>
            <td valign="top">&nbsp;</td>
            <td valign="top">&nbsp;</td>
            <td valign="top">&nbsp;</td>
            <td valign="top">偿还贷款</td>
            <td valign="top">2006-01-03</td>
          </tr>
          <tr > 
            <td colspan="7" class=td5 ></td>
          </tr>
          <tr id="tr2" onMouseOver='this.style.background="#eaeaea"'  onMouseOut='this.style.background="#ffffff"' > 
            <td valign="top">02</td>
            <td valign="top">&nbsp;</td>
            <td valign="top">&nbsp;</td>
            <td valign="top">&nbsp;</td>
            <td valign="top">3000.00</td>
            <td valign="top">2005-10-23</td>
          </tr>
          <tr > 
            <td colspan="7" class=td5 ></td>
          </tr>
          <tr id="tr2" onMouseOver='this.style.background="#eaeaea"'  onMouseOut='this.style.background="#ffffff"' > 
            <td valign="top">03</td>
            <td valign="top">&nbsp;</td>
            <td valign="top">&nbsp;</td>
            <td valign="top">&nbsp;</td>
            <td valign="top">3000.00</td>
            <td valign="top">2004-11-08</td>
          </tr>
          <tr > 
            <td colspan="7" class=td5 ></td>
          </tr>
          <tr id="tr2" onMouseOver='this.style.background="#eaeaea"'  onMouseOut='this.style.background="#ffffff"' > 
            <td valign="top">04</td>
            <td valign="top">&nbsp;</td>
            <td valign="top">&nbsp;</td>
            <td valign="top">&nbsp;</td>
            <td valign="top">3000.00</td>
            <td valign="top">&nbsp;</td>
          </tr>
          <tr > 
            <td colspan="7" class=td5 ></td>
          </tr>
          <tr > 
            <td colspan="7" class=td5 ></td>
          </tr>
       </form>
      </table>
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr class="td1"> 
		  <td>&nbsp; </td>
	    </tr>
      </table>
      <table width="75%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr>
          <td align="center"><input type="submit" name="Submit22" value="关闭" class="buttona" onClick="javascript:window.close();"></td>
        </tr>
      </table>
    </table></body>

</html:html>
