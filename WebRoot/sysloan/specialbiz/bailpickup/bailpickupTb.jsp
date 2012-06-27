<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ page import="org.xpup.hafmis.sysloan.specialbiz.bailpickup.action.BailpickupTbShowAC" %>
<%@ include file="/checkUrl.jsp"%>     
<%
   String path = request.getContextPath();
   Object pagination= request.getSession(false).getAttribute(BailpickupTbShowAC.PAGINATION_KEY);
   request.setAttribute("pagination",pagination);
 %>
<html:html>
<head>
<title>特殊业务处理</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/index.css" type="text/css">
<script src="<%=request.getContextPath()%>/js/common.js">
</script>
</head>

<script>
var s1="";
var s2="";
var tr='tr0'; 
function loads(){
    document.forms[0].elements["loanBank"].value="";
    document.forms[0].elements["bizStatus"].value="";
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
	var count = "<bean:write name="pagination" property="nrOfElements"/>";
	for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
			if(document.all.item(i).value=="删除"){
				s1=i;
			}
			if(document.all.item(i).value=="打印"){
				s2=i;
			}
		}
	}
	if(count=="0"){
	document.all.item(s1).disabled="true";
	document.all.item(s2).disabled="true";
	}
	
}
function suredelete(){
 if(!confirm("确认要进行该次删除吗？")){
		return false;
	}
}
</script>

<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false" onload="loads();">
  <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="7"><img src="<%=request.getContextPath()%>/img/table_left.gif" width="7" height="37"></td>
          <td background="<%=request.getContextPath()%>/img/table_bg_line.gif" width="55">&nbsp;</td>
          <td width="235" background="<%=request.getContextPath()%>/img/table_bg_line.gif"> 
            <table border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="112" height="37" background="<%=request.getContextPath()%>/img/buttong.gif" align="center" valign="top"  style="PADDING-top: 7px"><a href="bailpickupShowAC.do" class=a2>办理保证金提取</a></td>
                <td width="112" height="37" background="<%=request.getContextPath()%>/img/buttonbl.gif" align="center"   style="PADDING-top: 7px" valign="top">保证金提取维护</td>
              </tr>
            </table>
          </td>
          <td background="<%=request.getContextPath()%>/img/table_bg_line.gif" align="right"> 
            <table width="300" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="37"><img src="<%=request.getContextPath()%>/img/title_banner.gif" width="37" height="24"></td>
                <td width="234" class=font14 bgcolor="#FFFFFF" align="center" valign="bottom"><font color="00B5DB">特殊业务处理&gt;保证金提取</font></td>
                <td width="29" class=font14>&nbsp;</td>
              </tr>
            </table>
          </td>
          <td width="9"><img src="<%=request.getContextPath()%>/img/table_right.gif" width="9" height="37"></td>
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
                <td height="22" bgcolor="#CCCCCC" align="center" width="203"><b class="font14">查 询 条 件</b></td>
                <td width="707" height="22" align="center" background="<%=request.getContextPath()%>/img/bg2.gif">&nbsp;</td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
      <html:form action="/bailpickupTbFindAC.do" method="post" style="margin: 0">
      <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=0 align="center" > 
        <tr> 
          <td width="13%"   class="td1">贷款账号 </td>
          <td width="18%"  ><html:text name="theBailpickupTbDTO" property="loanKouAcc" onkeydown="enterNextFocus1();" styleClass="input3"/></td>
          <td width="11%" class="td1" >合同编号</td>
          <td width="21%"  > 
            <html:text name="theBailpickupTbDTO" property="contractId" onkeydown="enterNextFocus1();" styleClass="input3"/>          </td>
        </tr>
		<tr> 
          <td width="13%"   class="td1">借款人姓名</td>
          <td width="18%"  > 
           <html:text name="theBailpickupTbDTO" property="borrowerName" onkeydown="enterNextFocus1();" styleClass="input3"/>         </td>
          <td width="11%" class="td1" >证件号码</td>
          <td width="21%"  > 
           <html:text name="theBailpickupTbDTO" property="cardNum" onkeydown="enterNextFocus1();" styleClass="input3"/>         </td>
        </tr>
		<tr> 
          <td width="13%"   class="td1">放款银行</td>
          <td width="18%"  >
          <html:select property="loanBank" onkeydown="enterNextFocus1();" styleClass="input3" >
              <html:option value=""></html:option>
              <html:optionsCollection property="bankList" name="theBailpickupTbDTO" label="collBankName" value="collBankId"/>
          </html:select> 
          </td>
          <td width="11%" class="td1" >业务状态</td>
          <td width="21%" >
          <html:select property="bizStatus" onkeydown="enterNextFocus1();" styleClass="input3" >
              <html:option value=""></html:option>
              <html:optionsCollection property="map" name="theBailpickupTbDTO" label="value" value="key"/>
          </html:select> 
          </td>
        </tr>
      </table>
	   <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td align="right"> 
             <html:submit property="method" styleClass="buttona"  onclick="return gotocheck();"><bean:message key="button.search" /></html:submit>
            </td>
          </tr>
        </table>
      </html:form>
       <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
          <tr> 
            <td class=h4>合计：提取保证金金额<u>：<bean:write name="theBailpickupTbDTO" property="sumOccurMoney"/></u>提取利息<u>：<bean:write name="theBailpickupTbDTO" property="sumOtherInterest"/></u>提取金额<u>：<bean:write name="theBailpickupTbDTO" property="sumPickupMoney"/></u></td>
          </tr>
        </table>
      <html:form action="/bailpickupTbMaintainAC.do" method="post" style="margin: 0">
      <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td height="35"><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td height="22" bgcolor="#CCCCCC" align="center" width="208"><b class="font14">提取保证金列表</b></td>
              <td width="702" height="22" align="center" background="<%=request.getContextPath()%>/img/bg2.gif">&nbsp;</td>
            </tr>
          </table></td>
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
          <td align="center" class=td2 >贷款账号</td>
          <td align="center" class=td2 >合同编号</td>
          <td align="center" class=td2 >借款人姓名</td>
          <td align="center" class=td2 >证件号码</td>
		  <td align="center" class=td2 >提取保证金金额</td>
		  <td align="center" class=td2 >提取利息</td>
		  <td align="center" class=td2 >提取金额</td>
        </tr>
        <logic:notEmpty name="theBailpickupTbDTO" property="list">
            <logic:iterate id="element" name="theBailpickupTbDTO" property="list" indexId="i">
                <tr id="tr<%=i%>" onclick='gotoClick("tr<%=i %>","s<%=i%>", idAF);' onMouseOver='this.style.background="#eaeaea"'  onMouseOut='gotoColor("tr<%=i %>","s<%=i %>", idAF);' class=td4  onDblClick=""> 
             <td valign="top" >
               <p align="left">
               <input id="s<%=i %>" type="radio" name="id" value="<bean:write name="element" property="id"/>" <%if(new Integer(0).equals(i)) out.print("checked"); %>>
              </p>
            </td>  
            <td><bean:write name="element" property="loanKouAcc"/></td>
            <td><bean:write name="element" property="contractId"/></td>
            <td><bean:write name="element" property="borrowerName"/></td>
            <td><bean:write name="element" property="cardNum"/></td>
            <td><bean:write name="element" property="occurMoney"/></td>
            <td><bean:write name="element" property="otherInterest"/></td>
            <td><bean:write name="element" property="pickupMoney"/></td>
            </tr>
            <tr > 
             <td colspan="17" class=td5 ></td>
            </tr>
            </logic:iterate>
          </logic:notEmpty>
        
         <input type="hidden" name="isList" value="<bean:write name="theBailpickupTbDTO" property="list"/>">
         <logic:empty name="theBailpickupTbDTO" property="list">
            <tr> 
             <td colspan="16" height="30" style="color:red">没有找到与条件相符合的结果！</td>
	       </tr>
		   <tr > 
             <td colspan="16" class=td5 ></td>
           </tr>
         </logic:empty>
         <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr class="td1"> 
          <td>
            <table width="100%" height="19" border="0" cellpadding="0" cellspacing="0">
          
             
              <tr> 
			  	<td align="left">共<bean:write name="pagination" property="nrOfElements"/> 项</td>
                <td align="right"><jsp:include page="../../../inc/pagination.jsp"><jsp:param name="url" value="bailpickupTbShowAC.do"/></jsp:include></td>
              </tr>   
                     
            </table>
          </td>
        </tr>
      </table>
       
    <table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
			<tr valign="bottom">
				<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
					<table border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="69" align="right">
								<html:submit property="method" styleClass="buttona" onclick="return suredelete();">
										<bean:message key="button.delete"/>
								</html:submit>&nbsp;
							</td>
							<td>
								<html:submit property="method" styleClass="buttona">
										<bean:message key="button.print" />
								</html:submit>
							</td>
						</tr>
					</table>
				</td>
			</tr>
     </table>
</table>
  </html:form>
</body>
</html:html>
