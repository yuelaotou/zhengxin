<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ page import="org.xpup.hafmis.sysloan.loancallback.loanerlogout.action.LoanerlogoutTbShowAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
String path = request.getContextPath();
Object pagination= request.getSession().getAttribute(LoanerlogoutTbShowAC.PAGINATION_KEY);
   request.setAttribute("pagination",pagination);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
    <title>个贷管理</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script src="<%=path%>/js/common.js">
	</script>
	
</head>
<script>
function ondelete(){
  var x;
  x=confirm("是否反注销该信息！");
  if(x){
    return true;
  }else
  {
    return false;
  }
}
 function reportErrors() {

	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
	}
function onsearch(){
	
	var cardNum = document.all.cardNum.value.trim();
	if(cardNum.trim()!=""){
		return isIdCardNo(cardNum);
	}
	return true;
}
</script> 
  
<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false" onload="reportErrors();">
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
                <td width="112" height="37" background="<%=path%>/img/buttong.gif" align="center" valign="top"  style="PADDING-top: 7px"><a href="loanerlogoutTaForwardAC.do" class=a2>&#19994;&#21153;&#21150;&#29702;</a></td>
                <td width="112" height="37" background="<%=path%>/img/buttonbl.gif" align="center"   style="PADDING-top: 7px" valign="top">&#19994;&#21153;&#32500;&#25252;</td>
              </tr>
            </table>
          </td>
          <td background="<%=path%>/img/table_bg_line.gif" align="right"> 
            <table width="300" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="37"><img src="<%=path%>/img/title_banner.gif" width="37" height="24"></td>
                <td width="234" class=font14 bgcolor="#FFFFFF" align="center" valign="bottom"><font color="#00b5db">&#36151;&#27454;</font><font color="#00b5db">&#22238;&#25910;</font><font color="#00b5db">&gt;&#36151;&#27454;&#25143;&#27880;&#38144;</font></td>
                <td width="29" class=font14>&nbsp;</td>
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
                <td height="22" bgcolor="#CCCCCC" align="center" width="207"><b class="font14">查 询 条 件</b></td>
                <td width="718" height="22" align="center" background="<%=path%>/img/bg2.gif">&nbsp;</td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
      <html:form action="/loanerlogoutTbFindAC.do"  style="margin: 0">
      <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=0 align="center" > 
        <tr> 
          <td width="13%"  class="td1">扣款账号</td>
          <td width="18%"  > 
            <html:text name="loanerlogoutTbAF"  property="loanKouAcc"  styleClass="input3" onkeydown="enterNextFocus1();"/>           
          </td>
          <td width="11%" class="td1" >合同编号</td>
          <td width="21%"  > 
            <html:text name="loanerlogoutTbAF"  property="contractId"  styleClass="input3" onkeydown="enterNextFocus1();"/>
          </td>
        </tr>
		<tr> 
          <td width="13%" class="td1">借款人姓名</td>
          <td width="18%"  > 
            <html:text name="loanerlogoutTbAF"  property="borrowerName"  styleClass="input3" onkeydown="enterNextFocus1();"/>          
          </td>
          <td width="11%" class="td1" >证件号码</td>
          <td width="21%"  > 
            <html:text name="loanerlogoutTbAF"  property="cardNum"  onblur="return onsearch();" styleClass="input3" onkeydown="enterNextFocus1();"/>          
          </td>
        </tr>
		<tr> 
          <td width="13%" class="td1">放款银行</td>
          <td width="18%"  > 
            <html:select property="loanBankId" styleClass="input4" name="loanerlogoutTbAF" onkeydown="enterNextFocus1();">
          							<option value=""></option>
									<html:options  collection="loanbankList1" property="value" labelProperty="label" />
							</html:select>          
		  </td>
          <td width="11%" class="td1" ></td>
          <td width="21%" class="td7" > 
                   </td>
          
        </tr>
		
      </table>
	  <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td align="right"><html:submit styleClass="buttona" onclick="return onsearch();"><bean:message key="button.search"/></html:submit></td>
        </tr>
      </table>
      </html:form>
      <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td height="35"> 
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
              <td height="22" bgcolor="#CCCCCC" align="center" width="180"><b class="font14">贷款户注销列表</b></td>
              <td width="715" height="22" align="center" background="<%=path%>/img/bg2.gif">&nbsp;</td>
            </tr>
            </table>
          </td>
        </tr>
      </table>
      <html:form action="/loanerlogoutTbMaintainAC.do"  style="margin: 0">
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr bgcolor="1BA5FF" > 
          <td align="center" height="6" colspan="1" ></td>
        </tr>
      </table>
      
        <table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1" cellpadding="3" align="center">
          <tr align="center" bgcolor="C4F0FF"> 
            <td height="23" bgcolor="C4F0FF" >&nbsp;</td>
            <td align="center" class=td2 >扣款账号</td>
            <td align="center" class=td2 >合同编号</td>
            <td align="center" class=td2 >借款人姓名</td>
            <td align="center" class=td2 >证件号码</td>
          </tr>
          <logic:notEmpty name="loanerlogoutTbAF" property="list">
          <% int j=0;
			String strClass="";
		%>
          <logic:iterate id="e" name="loanerlogoutTbAF" property="list" indexId="i">
          <%j++;
			if (j%2==0) {strClass = "td8";
			}
		    else {strClass = "td9";
		    }
		%>
          <tr id="tr<%=i%>"
									onclick='gotoClickpp("<%=i %>", idAF);' onMouseOver='this.style.background="#eaeaea"' onMouseOut='gotoColorpp("<%=i %>", idAF);' class="<%=strClass%>" onDblClick="">
            <td > 
              <input id="s<%=i%>" type="radio" name="id"
											value="<bean:write name="e" property="contractId"/>"
											<%if(new Integer(0).equals(i)) out.print("checked"); %>>
            </td>
            <td ><bean:write name="e" property="loanKouAcc"/></td>
            <td ><bean:write name="e" property="contractId"/></td>
            <td ><bean:write name="e" property="borrowerName"/></td>
            <td ><bean:write name="e" property="cardNum"/></td>
          </tr>
          
          </logic:iterate>
          </logic:notEmpty>
          <logic:empty name="loanerlogoutTbAF" property="list">
          <tr>
				<td colspan="11" height="30" style="color:red">
									没有找到与条件相符合的结果！
									<br>
								</td>
							</tr>
          </logic:empty>
        </table>
      <table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr class="td1">
							<td align="center">
            
             <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr class="td1"> 
		  <td>
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr> 
			  	<td align="left">共<bean:write name="pagination" property="nrOfElements"/> 项</td>
                <td align="right"><jsp:include page="../../../inc/pagination.jsp"><jsp:param name="url" value="loanerlogoutTbShowAC.do"/></jsp:include></td>
              </tr>
          </table></td>
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
              	<logic:empty name="loanerlogoutTbAF" property="list">
                <td width="69" align="right">
                	<html:submit property="method" styleClass="buttona" disabled="true"><bean:message key="button.trun.logout"/></html:submit>
                </td>
                <td>
                	<html:submit property="method" styleClass="buttona" disabled="true"><bean:message key="button.print"/></html:submit>
                </td>
                </logic:empty>
                <logic:notEmpty name="loanerlogoutTbAF" property="list">
	                <td width="69" align="right">
	                	<html:submit property="method" styleClass="buttona" onclick="return ondelete();"><bean:message key="button.trun.logout"/></html:submit>
	                </td>
	                <td>
	                	<html:submit property="method" styleClass="buttona"><bean:message key="button.print"/></html:submit>
	                </td>
                </logic:notEmpty>
              </tr>
            </table>
          </td>
        </tr>
      </table>
      </html:form>
    </td>
  </tr>
</table>
</body>
</html:html>
