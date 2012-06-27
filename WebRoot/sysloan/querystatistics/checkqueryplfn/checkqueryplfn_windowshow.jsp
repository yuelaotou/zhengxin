<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@page import="org.xpup.hafmis.sysloan.accounthandle.adjustaccount.action.AdjustAccountPopShowAC" %>
<%@ include file="/checkUrl.jsp"%>
<%
String path = request.getContextPath();
Object pagination = session.getAttribute(AdjustAccountPopShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String indexs=(String)session.getAttribute("indexs");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
    <title>贷款管理</title>
    
   	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script language="javascript" src="<%=path%>/js/common.js"/>
  </head>
<script language="javascript">
	var oldColor="#ffffff";                     //原来的颜色
	var newColor="#E8FCFD";                     //要变成的颜色
	function chgBGColor(oTD){
		oldColor=oTD.style.backgroundColor;		//保存当前颜色
		oTD.style.backgroundColor=newColor;		//改变表格颜色
		newColor=oldColor;                 		//改变下次要变成的颜色
	}
</script>
<script>

var tr="tr0"; 
function gettr(trindex) {
	  tr=trindex;
}

function adjustAccountValues(indexs){

	var a;
	var b=document.getElementsByName("id");
	var c=document.getElementById(tr).childNodes[2].childNodes[0].innerHTML;
	for(i=0;i<b.length;i++)   {
    	if(b[i].checked==true){
    		a=b[i].value;
    	}
  	}
	if(""+a!="undefined"){
		window.opener.document.forms[0].elements["adjustAccountTaInfoDTO.docNum"].value=c;
		window.opener.document.forms[0].elements["adjustAccountTaInfoDTO.flowHeadId"].value=a;
  		if(indexs=="0"){
  			opener.executeAjax();
  		}else{
  			opener.executeAjaxIn();
  		}
		
	}else{
		opener.clears();
	}
	window.close();
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false">

<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="7"><img src="<%=path%>/img/table_left.gif" width="7" height="37"></td>
          <td background="<%=path%>/img/table_bg_line.gif" width="555">&nbsp;</td>
          <td width="635" background="<%=path%>/img/table_bg_line.gif"></td>
          <td background="<%=path%>/img/table_bg_line.gif" align="right"> 
            
          </td>
          <td width="1"><img src="<%=path%>/img/table_right.gif" width="9" height="37"></td>
        </tr>
      </table>
    </td>
  </tr>
  <tr> 
      <td class=td3> 
      <html:form action="/adjustAccountPopFindAC">
        <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
          <tr> 
            <td height="35"> 
              <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr> 
                  <td height="22" bgcolor="#CCCCCC" align="center" width="192"><b class="font14">查 询 条 件</b></td>
                  <td width="733" height="22" align="center" background="<%=path%>/img/bg2.gif">&nbsp;</td>
                </tr>
              </table>
            </td>
          </tr>
        </table>
        <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=3 align="center" >
          <tr> 
            <td width="13%"   class="td1">扣款账号</td>
            <td width="18%"  > 
              <html:text name="adjustAccountPopFindAF" property="adjustAccountPopFindDTO.loanKouAcc" styleClass="input3" onkeydown="enterNextFocus1();" maxlength="100"></html:text>
            </td>
            <td width="11%" class="td1" >借款人姓名</td>
            <td width="21%"  > 
              <html:text name="adjustAccountPopFindAF" property="adjustAccountPopFindDTO.borrowerName" styleClass="input3" onkeydown="enterNextFocus1();" maxlength="100"></html:text>
            </td>
          </tr>
          <tr>
            <td width="13%"   class="td1">业务日期</td>
            <td width="18%"  >
              <html:text name="adjustAccountPopFindAF" property="adjustAccountPopFindDTO.bizDate" styleClass="input3" onkeydown="enterNextFocus1();" maxlength="100"></html:text>
            </td>
            <td width="11%" class="td1" >业务类型</td>
            <td width="21%"  >
            <html:select name="adjustAccountPopFindAF" property="adjustAccountPopFindDTO.bizType" styleClass="input4" onkeydown="enterNextFocus1();">
	              <html:option value=""></html:option>
				  <html:optionsCollection property="bizTypeMap" name="adjustAccountPopFindAF" label="value" value="key" />
			  </html:select>
            </td>
          </tr>
          <tr>
            <td width="13%"   class="td1">放款银行</td>
            <td width="18%"  >
              <html:select name="adjustAccountPopFindAF" property="adjustAccountPopFindDTO.loanBankId" styleClass="input4" onkeydown="enterNextFocus1();">
				<html:option value=""></html:option>
				<html:options collection="loanbankList" property="value" labelProperty="label" />
			  </html:select>
            </td>
            <td width="11%" class="td1" >凭证编号</td>
            <td width="21%"  ><html:text name="adjustAccountPopFindAF" property="adjustAccountPopFindDTO.docNum" styleClass="input3" onkeydown="enterNextFocus1();" maxlength="100"></html:text></td>
          </tr>
          <tr align="right"> 
            <td colspan="4"> 
              <html:submit property="method" styleClass="buttona"><bean:message key="button.search"/></html:submit>
            </td>
          </tr>
        </table>
        </html:form>
        <form name="idAF" action="">
      <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td height="35"> 
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                  <td height="22" bgcolor="#CCCCCC" align="center" width="193"><b class="font14"> 
                    业 务 列 表 </b></td>
                <td width="732" height="22" align="center" background="<%=path%>/img/bg2.gif">&nbsp;</td>
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
        <table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1" cellpadding="3" align="center">
          <tr align="center" bgcolor="C4F0FF"> 
            <td height="23" bgcolor="C4F0FF" >&nbsp;</td>
            <td align="center" class=td2 >扣款账号</td>
            <td align="center" class=td2 >凭证编号</td>
            <td align="center" class=td2 >合同编号</td>
            <td align="center" class=td2 >业务日期</td>
            <td align="center" class=td2 >借款人姓名</td>
            <td align="center" class=td2 >证件号码</td>
            <td align="center" class=td2 >发放金额</td>
            <td align="center" class=td2 >本次实还金额</td>
            <td align="center" class=td2 >挂账发生额</td>
            <td align="center" class=td2 >实收金额</td>
            <td align="center" class=td2 >业务类型</td>
          </tr>
         <logic:notEmpty name="adjustAccountPopFindAF" property="list"> 
          <% int j=0;
			String strClass="";%>
         <logic:iterate id="adjustAccountPopListDTO" name="adjustAccountPopFindAF" property="list" indexId="i"> 
          <%j++;
			if (j%2==0) {strClass = "td8";
			}
		    else {strClass = "td9";
		    }%>
          <tr id="tr<%=i%>" onclick='gotoClickpp("<%=i %>", idAF);gettr("tr<%=i %>");'  onMouseOver='this.style.background="#eaeaea"'  onMouseOut='gotoColorpp("<%=i %>", idAF);' class="<%=strClass%>"  onDblClick='adjustAccountValues("<%=indexs%>");'>
          
            <td > 
              <input id="s<%=i %>" type="radio" name="id" value="<bean:write name="adjustAccountPopListDTO" property="flowHeadId"/>" <%if(new Integer(0).equals(i)) out.print("checked"); %>>
            
            </td>
            <td ><p><bean:write name="adjustAccountPopListDTO" property="loanKouAcc"/></p></td>
            <td ><p><bean:write name="adjustAccountPopListDTO" property="docNum"/></p></td>
            <td ><p><bean:write name="adjustAccountPopListDTO" property="contractId"/></p></td>
            <td ><p><bean:write name="adjustAccountPopListDTO" property="bizdate"/></p></td>
            <td ><p><bean:write name="adjustAccountPopListDTO" property="borrowerName"/></p></td>
            <td ><p><bean:write name="adjustAccountPopListDTO" property="cardNum"/></p></td>
            <td ><p><bean:write name="adjustAccountPopListDTO" property="occurMoney"/></p></td>
            <td ><p><bean:write name="adjustAccountPopListDTO" property="loanBackPay"/></p></td>
            <td ><p><bean:write name="adjustAccountPopListDTO" property="overPay"/></p></td>
            <td ><p><bean:write name="adjustAccountPopListDTO" property="factPay"/></p></td>
            <td ><p><bean:write name="adjustAccountPopListDTO" property="bizType"/></p></td>
            
          </tr>
          <tr > 
            <td colspan="15" class=td5 ></td>
          </tr>
		</logic:iterate>
        </logic:notEmpty>
        <logic:empty name="adjustAccountPopFindAF" property="list">
        <tr> 
          <td colspan="11" height="30" style="color:red">没有找到与条件相符合的结果！</td>
	    </tr>
		<tr > 
          <td colspan="11" class=td5 ></td>
        </tr>
        </logic:empty>
        </table>
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr class="td1"> 
            <td align="center"> 
              <table width="300" border="0" cellspacing="0" cellpadding="0">
                <tr align="center"> 
                  <td align="left">共<bean:write name="pagination" property="nrOfElements"/> 项</td>
                	<td align="right">  
                 	<jsp:include page="../../../inc/pagination.jsp"><jsp:param name="url" value="adjustAccountPopShowAC.do"/></jsp:include>
                </td>
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
              <logic:notEmpty name="adjustAccountPopFindAF" property="list">
                <td width="70"> 
                  <input type="button" name="Submit" value="确定" class="buttona" onClick='adjustAccountValues("<%=indexs%>");'>
                </td>
                <td width="70"> 
                  <input type="button" name="Submit2" value="取消" class="buttona" onClick="window.close();">
                </td>
              </logic:notEmpty>
              <logic:empty name="adjustAccountPopFindAF" property="list">
              <td width="70"> 
                  <input type="button" name="Submit" value="确定" class="buttona" onClick='adjustAccountValues("<%=indexs%>");' disabled="true">
                </td>
                <td width="70"> 
                  <input type="button" name="Submit2" value="取消" class="buttona" onClick="window.close();" disabled="true">
                </td>
              </logic:empty>
              </tr>
            </table>
          </td>
        </tr>
      </table>
		</form>  
    </td>
  </tr>
</table>

</body>
</html:html>
