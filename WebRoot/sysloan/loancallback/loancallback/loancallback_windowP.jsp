<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ page import="org.xpup.hafmis.sysloan.loancallback.loancallback.action.LoancallbackTbShowWindowAC" %>
<%@ include file="/checkUrl.jsp"%>
<%
   Object pagination= request.getSession(false).getAttribute(LoancallbackTbShowWindowAC.PAGINATION_KEY);
   request.setAttribute("pagination",pagination);
   String path=request.getContextPath();
 %>
<html:html>
<head>
<title>贷款发放</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
</head>
<script src="<%=path%>/js/common.js"></script>

<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false">
<html:form action="/loancallbackTbPrintWindowAC">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="7"><img src="<%=path%>/img/table_left.gif" width="7" height="37"></td>
          <td background="<%=path%>/img/table_bg_line.gif" width="55">&nbsp;</td>
            <td background="<%=path%>/img/table_bg_line.gif">
<table border="0" cellspacing="0" cellpadding="0">
                <tr> 
                  <td width="112" height="37"  align="center" valign="top"  style="PADDING-top: 7px"></td>
                  <td width="112" height="37" align="center"   style="PADDING-top: 7px" valign="top"></td>
                </tr>
              </table>
            </td>
          <td background="<%=path%>/img/table_bg_line.gif" align="right"> 
            <table width="300" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="37"><img src="<%=path%>/img/title_banner.gif" width="37" height="24"></td>
                <td width="163" class=font14 bgcolor="#FFFFFF" align="center" valign="bottom"><font color="00B5DB">回收贷款&gt;银行代扣导入</font></td>
                <td width="100" class=font14>&nbsp;</td>
              </tr>
            </table>          </td>
          <td width="9"><img src="<%=path%>/img/table_right.gif" width="9" height="37"></td>
        </tr>
      </table>
    </td>
  </tr>
  <tr> 
    <td class=td3>	  
	  <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
          <tr> 
            <td class=h4>合计：人数<u>：<bean:write name="totaldto" property="counts"/> </u> 应还本金 <u>：<bean:write name="totaldto" property="shouldCorpus"/>  </u>
			应还利息<u>：<bean:write name="totaldto" property="shouldInterest"/>  </u>应还逾期利息<u>：<bean:write name="totaldto" property="shouldPunishInterest"/>  </u>实还本金 <u>：<bean:write name="totaldto" property="realCorpus"/>  </u>
			实还利息<u>：<bean:write name="totaldto" property="realInterest"/>  </u>实还逾期利息<u>：<bean:write name="totaldto" property="realPunishInterest"/>  </u>总还款额<u>：<bean:write name="totaldto" property="realMoney"/>  </u>
			挂账发生额<u>：<bean:write name="totaldto" property="occurMoney"/>  </u>实收金额<u>：<bean:write name="totaldto" property="money"/>  </u>			
			</td>
          </tr>
        </table>
      <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td height="35"><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
                  <td height="22" bgcolor="#CCCCCC" align="center" width="197"><b class="font14">银行代扣数据导入列表</b></td>
                  <td width="728" height="22" align="center" background="<%=path%>/img/bg2.gif">&nbsp;</td>
            </tr>
          </table></td>
        </tr>
      </table>
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr bgcolor="1BA5FF" > 
          <td align="center" height="6" colspan="1" ></td>
        </tr>
      </table>
        <table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1" cellpadding="3" align="center">
          <tr align="center" bgcolor="C4F0FF"> 
            <td class="td2">扣款账号</td>
            <td class="td2">合同编号</td>
            <td class="td2"><a href="javascript:sort('borrower.borrowerName')">
            借款人姓名</a>
            <logic:equal name="pagination" property="orderBy" value="borrower.borrowerName">
          	  <logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
          	  <logic:equal name="pagination" property="orderother" value="DESC">▼</logic:equal>
          	</logic:equal></td>
            <td class="td2">证件号码</td>
            <td class="td2">还款类型</td>
            <td class="td2">还款年月</td>
            <td class="td2">应还本金</td>
            <td class="td2">应还利息</td>
            <td class="td2">应还罚息</td>
            <td class="td2">实还本金</td>
            <td class="td2">实还利息</td>
            <td class="td2">实还逾期利息</td>
            <td class="td2">逾期天数</td>
            <td class="td2">总还款额 </td>
            <td class="td2">挂账发生额</td>
            <td class="td2">实收金额</td>
          </tr>
          <logic:notEmpty name="batchList" >
          <% int j=0;
			String strClass="";
		%>
		<logic:iterate name="batchList" id="element" indexId="i">
	<%j++;
			if (j%2==0) {strClass = "td8";
			}
		    else {strClass = "td9";
		    }
		%>
        <tr id="tr1"  class="<%=strClass%>" onMouseOver='this.style.background="#eaeaea"'  onMouseOut='this.style.background="#ffffff"' > 
        
          <td valign="top"><p><bean:write name="element" property="loanKouAcc"/></p></td>
          <td valign="top"><p><bean:write name="element" property="contractId"/></p></td>
          <td valign="top"><p><bean:write name="element" property="borrowerName"/></p></td>
          <td valign="top"><p><bean:write name="element" property="cardNum"/></p></td>
          <td valign="top"><p><bean:write name="element" property="loanKouType" /></p></td>
          <td valign="top"><p><bean:write name="element" property="loanKouYearmonth"/></p></td>
          <td valign="top"><p><bean:write name="element" property="shouldCorpus"/></p></td>
          <td valign="top"><p><bean:write name="element" property="shouldInterest"/></p></td>
          <td valign="top"><p><bean:write name="element" property="shouldPunishInterest"/></p></td>
          <td valign="top"><p><bean:write name="element" property="realCorpus"/></p></td>
          <td valign="top"><p><bean:write name="element" property="realInterest"/></p></td>
          <td valign="top"><p><bean:write name="element" property="realPunishInterest"/></p></td>
          <td valign="top"><p><bean:write name="element" property="days"/></p></td>
          <td valign="top"><p><bean:write name="element" property="realMoney"/></p></td>
          <td valign="top"><p><bean:write name="element" property="occurMoney"/></p></td>
          <td valign="top"><p><bean:write name="element" property="money"/></p></td>
           </tr>
         
        </logic:iterate>
        </logic:notEmpty>
        <logic:empty name="batchList">
        <tr> 
          <td colspan="16" height="30" style="color:red">没有找到与条件相符合的结果！</td>
	    </tr>
		
        </logic:empty>
        </table>
      
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr class="td1"> 
         <td align="left">共<bean:write name="pagination" property="nrOfElements"/> 项</td>
         <td align="right"><jsp:include page="../../../inc/pagination.jsp"><jsp:param name="url" value="loancallbackTbShowWindowAC.do"/></jsp:include></td>       
        </tr>
      </table>
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr valign="bottom"> 
          <td  colspan="7" bgcolor="#FFFFFF" align="center" height="30"> 
            <table border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="70"> 
                <input type="hidden" value="5" name="bizType">
                    <html:submit property="method" styleClass="buttona">
						<bean:message key="button.print" />
					</html:submit>
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
