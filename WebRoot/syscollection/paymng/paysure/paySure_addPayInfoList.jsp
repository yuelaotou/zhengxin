<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ page import="org.xpup.hafmis.syscollection.paymng.paysure.action.PaymentHeadFindAC" %>

<%
   Object pagination= request.getSession(false).getAttribute(PaymentHeadFindAC.PAGINATION_KEY);
   request.setAttribute("pagination",pagination);
 %>
<html>
<head>
<title>单位补缴_简约风格【单位补缴明细】</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="css/index.css" type="text/css">
</head>
<script src="js/common.js"></script>
<script language="javascript">
var oldColor="#ffffff";                            //原来的颜色
var newColor="#E8FCFD";                     //要变成的颜色
function chgBGColor(oTD){
	oldColor=oTD.style.backgroundColor;//保存当前颜色
	oTD.style.backgroundColor=newColor;//改变表格颜色
	newColor=oldColor;                 //改变下次要变成的颜色
}
</script>

<body bgcolor="#FFFFFF" text="#000000">
<form method="post">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="7"><img src="images/table_left.gif" width="7" height="37"></td>
          <td background="images/table_bg_line.gif" width="55">&nbsp;</td>
            <td width="235" background="images/table_bg_line.gif">&nbsp; </td>
          <td background="images/table_bg_line.gif" align="right"> 
            <table width="300" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="37"><img src="images/title_banner.gif" width="37" height="24"></td>
                <td width="133" class=font14 bgcolor="#FFFFFF" align="center" valign="bottom"><a href="#" class=a1>缴存管理</a><font color="00B5DB">&gt;</font><a href="#" class=a1>单位补缴</a></td>
                <td width="130" class=font14>&nbsp;</td>
              </tr>
            </table>
          </td>
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
                  <td height="22" bgcolor="#CCCCCC" align="center" width="117"><b class="font14">缴 
                    存 信 息</b></td>
                  <td height="22" background="images/bg2.gif" align="center">&nbsp;</td>
                </tr>
              </table>
            </td>
        </tr>
      </table>
	  

	  
        <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=3 align="center" >
          <tr> 
            <td width="17%" class="td1"  >单位编码：</td>
            <td   > 
              <input name="txtsearch45222" type="text" id="txtsearch45222" class="input3" >
            </td>
            <td width="17%" class="td1"  > 单位名称： </td>
            <td  width="33%"  > 
              <input name="txtsearch45224" type="text" id="txtsearch" class="input3" >
            </td>
          </tr>
          <tr> 
            <td width="17%"   class="td1">补缴年月：</td>
            <td > 
              <input name="txtsearch452222" type="text" id="txtsearch45222" class="input3" >
            </td>
            <td class="td1" width="17%" >&nbsp;</td>
            <td width="33%"  class=td7>&nbsp;</td>
          </tr>
          <tr> 
            <td width="17%"   class="td1">结算号：</td>
            <td  class=td7>&nbsp;</td>
            <td class="td1" width="17%" >凭证编号：</td>
            <td width="33%" class=td7 >&nbsp;</td>
          </tr>
        </table>
	  
	   <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
          <tr> 
            <td class=h4>总计： 补缴人数   单位补缴金额  个人补缴金额  补缴金额</td>
          </tr>
        </table>
		
      <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td height="35"> 
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td height="22" bgcolor="#CCCCCC" align="center" width="117"><b class="font14">单位补缴列表</b></td>
                <td height="22" background="images/bg2.gif" align="center">&nbsp;</td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr bgcolor="1BA5FF" > 
          <td align="center" height="6" colspan="1" ></td>
        </tr>
      </table>
        <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
          <tr > 
            <td align="center" height="23" bgcolor="C4F0FF" >　</td>
            <td align="center" class=td2 >职工编号▲</td>
            <td align="center" class=td2 >职工姓名▲</td>
            <td align="center" class=td2 >单位补缴金额</td>
            <td align="center" class=td2 >个人补缴金额</td>
            <td align="center" class=td2 >补缴金额▲</td>
          </tr>
          <tr id="tr1" align="center" valign="middle" onClick='gotoClick("tr1","r1");' onMouseOver='this.style.background="#eaeaea"'  onMouseOut='gotoColor("tr1","r1");'> 
            <td class="td4" height="24"> 
              <input type="radio" name="C1332" value="ON" id="r1"  checked  >            </td>
            <td >000001</td>
            <td >张兵</td>
            <td ></td>
            <td >&nbsp;</td>
            <td >1000.00</td>
          </tr>
          <tr > 
            <td colspan="6" class=td5 ></td>
          </tr>
          <tr id="tr4" align="center" valign="middle" onClick='gotoClick("tr4","r4");' onMouseOver='this.style.background="#eaeaea"' onMouseOut='gotoColor("tr4","r4");'> 
            <td class="td4"> 
              <input type="radio" name="C1332" value="ON" id="r4">            </td>
            <td >000002</td>
            <td >王建伦</td>
            <td ></td>
            <td >&nbsp;</td>
            <td >800.00</td>
          </tr>
          <tr > 
            <td colspan="6" class=td5 ></td>
          </tr>
        </table>
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr class="td1"> 
          <td align="center">
            <table width="300" border="0" cellspacing="0" cellpadding="0">
              <tr align="center"> 
                <td><a href="#">第一页</a></td>
                <td><a href="#"></a>&nbsp;&nbsp;&nbsp;&nbsp; <a href="#">上一页</a></td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp; <a href="#">下一页</a></td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp; <a href="#">最后一页</a></td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr valign="bottom"> 
          <td  colspan="7" bgcolor="#FFFFFF" align="center" height="30"> 
              <table border="0" cellspacing="0" cellpadding="0">
                <tr> 
                  <td width="70">
                    <input type="submit" name="Submit52" value="打印" class="buttona">
                  </td>
                  <td width="70">
                    <input name="button" type="button"   class="buttona" value="关闭" onClick="javascript:window.close();">
                  </td>
                </tr>
              </table>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
</form>
</body>
</html>
