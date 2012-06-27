<%@ page language="java"%>
<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<html>
<head>
<title>缴存管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="css/index.css" type="text/css">
</head>
<script language="javascript" src="js/common.js"></script>

<body bgcolor="#FFFFFF" text="#000000">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="7"><img src="images/table_left.gif" width="7" height="37"></td>
          <td background="images/table_bg_line.gif" width="55">&nbsp;</td>
          <td background="images/table_bg_line.gif" align="right"> 
            <table width="300" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="37"><img src="images/title_banner.gif" width="37" height="24"></td>
                <td width="159" class=font14 bgcolor="#FFFFFF" align="center" valign="bottom"><a href="#" class=a1>缴存管理</a><font color="00B5DB">&gt;<a href="#" class=a1>缴存到账确认</a></font></td>
                <td width="104" class=font14>&nbsp;</td>
              </tr>
            </table>          </td>
          <td width="9"><img src="images/table_right.gif" width="9" height="37"></td>
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
                <td height="22" bgcolor="#CCCCCC" align="center" width="117"><b class="font14">基 
                  本 信 息</b></td>
                <td height="22" background="images/bg2.gif" align="center">&nbsp;</td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
      <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=3 align="center" >
        <tr> 
          <td width="17%" class="td1"  >结算号：</td>
          <td colspan="2"   ><input name="txtsearch45222" type="text" id="txtsearch45222" class="input3" onKeyDown="enterNextFocus1();" ></td>
          <td   ><input type="submit" name="Submit4" value="……" class="buttona" ></td>
        </tr>
        <tr> 
          <td width="17%"   class="td1">凭证编号：</td>
          <td colspan="2" ><input name="txtsearch452222" type="text" id="txtsearch452222" class="input3" onKeyDown="enterNextFocus1();" ></td>
          <td ><input type="submit" name="Submit42" value="……" class="buttona" ></td>
        </tr>
        <tr> 
          <td width="17%"   class="td1">单位编号：</td>
          <td colspan="2"  ><input name="txtsearch4522222" type="text" id="txtsearch4522222" class="input3" onKeyDown="enterNextFocus1();" ></td>
          <td  ><input type="submit" name="Submit423" value="……" class="buttona" ></td>
        </tr>
        <tr>
          <td   class="td1" width="17%">单位名称：</td>
          <td colspan="3"  ><input name="txtsearch45222222" type="text" id="txtsearch45222222" class="input3" onKeyDown="enterNextFocus1();" ></td>
        </tr>
        <tr>
          <td   class="td1">单位开户行：</td>
          <td  ><input name="txtsearch45221662" type="text" id="txtsearch45221662" class="input3"></td>
          <td class="td1"  >开户行账号：</td>
          <td  ><input name="txtsearch45221656" type="text" id="txtsearch45221655" class="input3"></td>
        </tr>
        <tr>
          <td   class="td1" width="17%">补缴人数：</td>
          <td width="33%"  >
<input name="txtsearch4522167" type="text" id="txtsearch4522166" class="input3"></td>
          <td class="td1" width="17%" >补缴金额：</td>
          <td width="33%"  >
<input name="txtsearch4522163" type="text" id="txtsearch4522162" class="input3"></td>
        </tr>
      </table>
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr valign="bottom"> 
          <td  colspan="7" bgcolor="#FFFFFF" align="center" height="34"> 
            <table width="165" height="20" border="0" cellpadding="0" cellspacing="0">
<tr> 
                <td width="58"><input type="submit" name="Submit22" value="补缴明细" class="buttona" onClick="document.URL='缴存管理_缴存到账确认_补缴明细列表.html'"></td>
                <td width="58"> 
                <input type="submit" name="Submit2" value="到账确认" class="buttona" onClick="btn1();">                </td>

			    <td width="49"><input type="button" name="Submit23" value="返回" class="buttona"  onClick="document.URL='缴存管理_缴存维护_业务维护.htm'"></td>
</tr>
            </table>
          </td>
        </tr>
      </table>
	  <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
          <tr valign="bottom"> 
            <td  colspan="7" bgcolor="#FFFFFF" align="right" height="30">&nbsp;</td>
          </tr>
      </table>
    </td>
  </tr>
</table>
</body>
</html>
