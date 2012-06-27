<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ page import="org.xpup.hafmis.sysloan.loancallback.baddebtdestroy.action.BadDebtDestroyTaShowAC" %>
<%@ include file="/checkUrl.jsp"%>
<%
   Object pagination= request.getSession(false).getAttribute(BadDebtDestroyTaShowAC.PAGINATION_KEY);
   request.setAttribute("pagination",pagination);
   String path=request.getContextPath();
 %>
<html:html>
<head>
<title>个贷管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
</head>
<script src="<%=path%>/js/common.js">
</script>
<script type="text/javascript">

</script>

<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false">
<html:form action="/badDebtDestroyTbPrintWindowAC">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="7"><img src="<%=path%>/img/table_left.gif" width="7" height="37"></td>
          <td background="<%=path%>/img/table_bg_line.gif" width="55">&nbsp;</td>
            <td width="235" background="<%=path%>/img/table_bg_line.gif">
			<table border="0" cellspacing="0" cellpadding="0">
                <tr> 
                  <td width="112" height="37" align="center" valign="top"  style="PADDING-top: 7px"></td>
                  <td width="112" height="37" align="center"   style="PADDING-top: 7px" valign="top"></td>
                </tr>
              </table>
            </td>
          <td background="<%=path%>/img/table_bg_line.gif" align="right"> 
            <table width="300" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="37"><img src="<%=path%>/img/title_banner.gif" width="37" height="24"></td>
                <td width="189" class=font14 bgcolor="#FFFFFF" align="center" valign="bottom"><p><font color="00B5DB">回收贷款&gt;呆账核销</font></p>
                  </td>
                <td width="74" class=font14>&nbsp;</td>
              </tr>
            </table>
          </td>
          <td width="9"><img src="<%=path%>/img/table_right.gif" width="9" height="37"></td>
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
                  <td height="22" bgcolor="#CCCCCC" align="center" width="163"><b class="font14">借 款 人 信 息</b></td>
                  <td height="22" background="<%=path%>/img/bg2.gif" align="center" width="747">&nbsp;</td>
                </tr>
              </table>
            </td>
          </tr>
        </table>
        <table  width="95%" id="table9" cellspacing=1  cellpadding=3 align="center" >
          <tr> 
            <td width="17%"class=td1>贷款账号<br>
            </td>
            <td class="td4" width="33%"> 
              <html:text property="borrowerInfoDTO.loanKouAcc" styleClass="input3" readonly="true"/>
            </td>
            <td class="td1" width="17%">合同编号</td>
            <td class="td4" width="33%" colspan="2"> 
              <html:text property="borrowerInfoDTO.contractId" styleClass="input3" readonly="true"/>
            </td>
          </tr>
          <tr> 
            <td width="17%"class=td1> 借款人姓名</td>
            <td class="td4"> 
              <html:text property="borrowerInfoDTO.borrowerName" styleClass="input3" readonly="true"/>
             </td>
            <td width="17%"class=td1> 证件类型</td>
            <td  class="td4" width="33%">
              <html:text property="borrowerInfoDTO.cardKind" styleClass="input3" readonly="true"/>
            </td>
          </tr>
          <tr> 
            <td class=td1 width="17%">证件号码</td>
            <td class="td4">
              <html:text property="borrowerInfoDTO.cardNum" styleClass="input3" readonly="true"/>
            </td>
            <td class=td1 width="17%">剩余本金<br>
            </td>
            <td class="td4" width="33%"> 
              <html:text property="borrowerInfoDTO.overplusLoanMoney" styleClass="input3" readonly="true"/>
            </td>
          </tr>
          <tr> 
            <td class=td1 width="17%">还款方式</td>
            <td class="td4"> 
              <html:text property="borrowerInfoDTO.loanMode" styleClass="input3" readonly="true"/>
            </td>
            <td class=td1 width="17%">还至年月</td>
            <td  class="td4" width="33%">
          <html:text property="monthYear" styleClass="input3" readonly="true"/>
            </td>
          </tr>
          <tr> 
            <td class=td1 width="17%" height="26">核销单位</td>
            <td class="td4" height="26"> 
              <html:text property="orgType" styleClass="input3" readonly="true"/>
            </td>
            <td class=td1 width="17%" height="26">单位名称</td>
            <td  class="td4" width="33%" height="26"> 
              <html:text property="orgName" styleClass="input3" readonly="true"/>
            </td>
          </tr>
        </table>
        <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
          <tr> 
            <td height="35"> 
              <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr> 
                  <td height="22" bgcolor="#CCCCCC" align="center" width="169"><b class="font14">应 还 款 列 表</b></td>
                  <td width="741" height="22" align="center" background="<%=path%>/img/bg2.gif">&nbsp;</td>
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
            <td align="center" class=td1 >还款年月 </td>
            <td align="center" class=td2 > 还款类型</td>
            <td align="center" class=td2 > 应还本金</td>
            <td align="center" class=td2 > 应还利息</td>
            <td align="center" class=td2 > 应还罚息利息 </td>
            <td align="center" class=td2 > 应还本息合计 </td>
            <td align="center" class=td2 > 实还本金</td>
            <td align="center" class=td2 > 实还利息</td>
            <td align="center" class=td2 > 罚息利息 </td>
            <td align="center" class=td2 >实还本息合计</td>
            <td align="center" class=td2 > 逾期天数</td>
            <td align="center" class=td2 >每月利率</td>
          </tr>
        <logic:notEmpty name="badDebtDestroyTaAF"  property="shouldBackList">
		<logic:iterate name="badDebtDestroyTaAF"  property="shouldBackList" id="element">
          <tr id="tr1"  class=td4 onDblClick=""> 
            <td valign="top"><bean:write name="element" property="loanKouYearmonth"/></td>
            <td valign="top"><bean:write name="element" property="loanKouType"/></td>
            <td valign="top"><bean:write name="element" property="shouldCorpus"/></td>
            <td valign="top"><bean:write name="element" property="shouldInterest"/></td>
            <td valign="top"><bean:write name="element" property="punishInterest"/></td>
            <td valign="top"><bean:write name="element" property="ciMoney"/></td>
            <td valign="top"><bean:write name="element" property="realCorpus"/></td>
            <td valign="top"><bean:write name="element" property="realInterest"/></td>
            <td valign="top"><bean:write name="element" property="realPunishInterest"/></td>
            <td valign="top"><bean:write name="element" property="realCiMoney"/></td>
            <td valign="top"><bean:write name="element" property="days"/></td>
            <td valign="top"><bean:write name="element" property="show_loanRate"/></td>
          </tr>
          <tr > 
            <td colspan="19" class=td5 ></td>
          </tr>
        </logic:iterate>
        </logic:notEmpty>
        <logic:empty name="badDebtDestroyTaAF"  property="shouldBackList">
        <tr> 
          <td colspan="19" height="30" style="color:red">没有找到与条件相符合的结果！</td>
	    </tr>
		<tr > 
          <td colspan="19" class=td5 ></td>
        </tr>
        </logic:empty>      
        </table>
        <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
          <tr> 
            <td height="35"> 
              <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr> 
                  <td height="22" bgcolor="#CCCCCC" align="center" width="172"><b class="font14"> 
                    还 款 信 息</b></td>
                  <td height="22" background="<%=path%>/img/bg2.gif" align="center" width="738">&nbsp;</td>
                </tr>
              </table>
            </td>
          </tr>
        </table>
        <table  width="95%" id="table9" cellspacing=1  cellpadding=3 align="center" >
          <tr> 
            <td class=td1 width="17%">本次总还款本金</td>
            <td class="td4">
              <html:text property="sumCorpus" styleClass="input3" readonly="true"/>
            </td>
            <td class=td1 width="17%"> 本次总还款利息</td>
            <td class="td4" width="33%">  
              <html:text property="sumInterest" styleClass="input3" readonly="true"/>
            </td>
          </tr>
          <tr> 
            <td class=td1 width="17%"> 本次总还款金额 </td>
            <td class="td4"> 
              <html:text property="sumMoney" styleClass="input3" readonly="true"/>
            </td>
            <td class=td1 width="17%"> 本次实收金额</td>
            <td  class="td4" width="33%"> 
              <html:text property="realMoney" styleClass="input3" readonly="true"/>
            </td>
          </tr>
        </table>
        <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
          <tr valign="bottom"> 
            <td  colspan="7" bgcolor="#FFFFFF" align="center" height="30"> 
              <table border="0" cellspacing="0" cellpadding="0">
                <tr> 
                  <td width="70"> 
                  <input type="hidden" value="<bean:write name="headId"/>" name="headId">
                    <input type="submit" class="buttona" value="打印">
                  </td>
                  <td width="70">
                    <input type="button" class="buttona" value="关闭" onclick="window.close();">
                  </td>
                </tr>
              </table>
            </td>
          </tr>
        </table>
      </td>
  </tr>
</table>
</html:form>
</body>
</html:html>
