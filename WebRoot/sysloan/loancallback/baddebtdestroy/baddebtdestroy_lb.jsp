<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ page import="org.xpup.hafmis.sysloan.loancallback.baddebtdestroy.action.BadDebtDestroyTbShowAC" %>
<%@ include file="/checkUrl.jsp"%>
<%
   Object pagination= request.getSession(false).getAttribute(BadDebtDestroyTbShowAC.PAGINATION_KEY);
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
var s1="";
var s2="";
var s3="";

var tr="tr0"; 
function gettr(trindex) {
  tr=trindex;
  update1();
}
function loads(){
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
	document.badDebtDestroyTbAF.loanKouAcc.value="";
	document.badDebtDestroyTbAF.contractId.value="";
	document.badDebtDestroyTbAF.borrowerName.value="";
	document.badDebtDestroyTbAF.cardNum.value="";
	document.badDebtDestroyTbAF.docNum.value="";
	document.badDebtDestroyTbAF.loanBankId.value="";
	document.badDebtDestroyTbAF.status.value="";
	for(i=0;i<document.all.length;i++){//获得所有form
		if(document.all.item(i).type=="submit"){
			if(document.all.item(i).value=="删除"){
				s1=i;
			}
			if(document.all.item(i).value=="回收"){
				s2=i;
			}
			if(document.all.item(i).value=="打印"){
				s3=i;
			}
			
		}
	}
    var count = "<bean:write name="pagination" property="nrOfElements"/>";
    if(count == "0"){
  		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="true";
    }else{
		update1();
    }
}
function update1() {
  	var status=document.getElementById(tr).childNodes[9].childNodes[0].innerHTML;
  	if(status=='导出'){
  		document.all.item(s1).disabled="";
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="";
  	}else if(status=='导入'){
  		document.all.item(s1).disabled="";
		document.all.item(s2).disabled="";
		document.all.item(s3).disabled="";
  	}else if(status=='登记'){
  		document.all.item(s1).disabled="";
		document.all.item(s2).disabled="";
		document.all.item(s3).disabled="";
  	}else if(status=='确认'){
  		document.all.item(s1).disabled="";
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="";
  	}else if(status=='复核'){
  		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="";
  	}else if(status=='入账'){
  		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="";
  	}
}
function gotoShow(){
	return false;
}
function gotoDelete(){

	 if(!confirm("是否确定删除该笔业务？")){
		return false;
	}
}
function gotoCallback(){
if(!confirm("是否确定回收该笔业务？")){
		return false;
	}
}

function gotoPrint(){

}
</script>

<body bgcolor="#FFFFFF" text="#000000" onload="loads();" onContextmenu="return false">
  <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="7"><img src="<%=path%>/img/table_left.gif" width="7" height="37"></td>
          <td background="<%=path%>/img/table_bg_line.gif" width="55">&nbsp;</td>
            <td background="<%=path%>/img/table_bg_line.gif"><a href="特殊业务处理_呆帐核销_简约风格.html"> 
              </a>
				<table border="0" cellspacing="0" cellpadding="0">
                <tr> 
                  <td width="112" height="37" background="<%=path%>/img/buttong.gif" align="center" valign="top"  style="PADDING-top: 7px"><a href="<%=path%>/sysloan/badDebtDestroyTaForwardURLAC.do" class=a2>办理呆账核销</a></td>
                  <td width="112" height="37" background="<%=path%>/img/buttonbl.gif" align="center"   style="PADDING-top: 7px" valign="top">呆账核销维护</td>
                </tr>
              </table>
            </td>
          <td background="<%=path%>/img/table_bg_line.gif" align="right"> 
            <table width="300" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="37"><img src="<%=path%>/img/title_banner.gif" width="37" height="24"></td>
                <td width="185" class=font14 bgcolor="#FFFFFF" align="center" valign="bottom"><font color="00B5DB">回收贷款&gt;呆账核销</font></td>
                <td width="78" class=font14>&nbsp;</td>
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
      <html:form action="/badDebtDestroyTbFindAC" style="margin: 0">
      <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td height="35"> 
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td height="22" bgcolor="#CCCCCC" align="center" width="205"><b class="font14">查 询 条 件</b></td>
                <td width="720" height="22" align="center" background="<%=path%>/img/bg2.gif">&nbsp;</td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
        <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=0 align="center" >
          <tr> 
            <td width="13%"   class="td1">贷款账号 </td>
            <td width="18%"  > 
              <html:text property="loanKouAcc" styleClass="input3"/>
            </td>
            <td width="11%" class="td1" >合同编号</td>
            <td width="21%"  > 
              <html:text property="contractId" styleClass="input3"/>
            </td>
          </tr>
          <tr> 
            <td width="13%"   class="td1">借款人姓名</td>
            <td width="18%"  > 
              <html:text property="borrowerName" styleClass="input3"/>
            </td>
            <td width="11%" class="td1" >证件号码</td>
            <td width="21%"  > 
              <html:text property="cardNum" styleClass="input3"/>
            </td>
          </tr>
          <tr> 
            <td width="13%"   class="td1">发放银行</td>
            <td width="5%" > 
             <html:select property="loanBankId" styleClass="input4" onkeydown="">
			<html:option value=""></html:option>
              <html:options  collection="banklist" property="value" labelProperty="label"/>
            </html:select> 
            </td>
            <td width="4%" class="td1" >业务状态</td>
            <td width="9%"  >
              <html:select property="status" styleClass="input4" onkeydown="">
              <html:option value=""></html:option>
              <html:optionsCollection property="statusMap" name="badDebtDestroyTbAF" label="value" value="key"/>
              </html:select>
            </td>
          </tr>
          <tr> 
            <td width="13%"   class="td1">凭证编号</td>
            <td width="18%"  > 
              <html:text property="docNum" styleClass="input3"/>
            </td>
            <td width="11%" class="td1" >&nbsp;
            </td>
            <td width="9%" class="td7">
            &nbsp;
            </td>
          </tr>
        </table>
      <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td align="right">
          <html:submit property="method" styleClass="buttona" onclick="">
	      <bean:message key="button.search" />
	      </html:submit>
	      </td>
        </tr>
      </table>
	  </html:form>
      <html:form action="/badDebtDestroyTbMaintainAC" style="margin: 0">
	  <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
          <tr> 
            <td class=h4>合计：本次总还款本金<u>：<bean:write name="badDebtDestroyTbDTO" property="realCorpus"/> </u> 本次总还款利息 <u>：<bean:write name="badDebtDestroyTbDTO" property="realInterest"/>  </u>
			本次总还款金额<u>：<bean:write name="badDebtDestroyTbDTO" property="realMoney"/>  </u>
			</td>
          </tr>
        </table>
      <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td height="35"><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td height="22" bgcolor="#CCCCCC" align="center" width="203"><b class="font14">呆账核销列表</b></td>
              <td width="722" height="22" align="center" background="<%=path%>/img/bg2.gif">&nbsp;</td>
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
            <td align="center" class=td2 >凭证编号</td>
            <td align="center" class=td2 >合同编号</td>
            <td align="center" class=td2 >借款人姓名</td>
            <td align="center" class=td2 >证件号码</td>
            <td align="center" class=td2 >本次总还款本金</td>
            <td align="center" class=td2 >本次总还款利息</td>
            <td align="center" class=td2 >本次总还款金额</td>
            <td align="center" class=td2 >业务状态</td>
          </tr>
          <logic:notEmpty name="callbacklist" >
		<logic:iterate name="callbacklist" id="element" indexId="i">
        <tr id="tr<%=i%>" onclick='gotoClick("tr<%=i %>","s<%=i%>", idAF);gettr("tr<%=i %>");' onMouseOver='this.style.background="#eaeaea"'  onMouseOut='gotoColor("tr<%=i %>","s<%=i %>", idAF);' class=td4  onDblClick=""> 
          <td valign="top">
          <p align="left">
            <input id="s<%=i %>" type="radio" name="id" value="<bean:write name="element" property="id"/>" <%if(new Integer(0).equals(i)) out.print("checked"); %>>
          </p>
          </td> 
          <td valign="top"><p><bean:write name="element" property="loanKouAcc" /></p></td>
          <td valign="top"><p><bean:write name="element" property="docNum" /></p></td>
          <td valign="top"><p><bean:write name="element" property="contractId"/></p></td>
          <td valign="top"><p><a href="#" onClick="window.open('badDebtDestroyTbForwardURLWindowAC.do?contractId=<bean:write name="element" property="contractId"/>&headId=<bean:write name="element" property="id"/>','','width=1000,height=600,top='+(window.screen.availHeight-600)/2+',left='+(window.screen.availWidth-1000)/2+',scrollbars=yes,location=no, status=no');return gotoShow();">
          <bean:write name="element" property="borrowerName" /></a></p></td>
          <td valign="top"><p><bean:write name="element" property="cardNum" /></p></td>
          <td valign="top"><p><bean:write name="element" property="realCorpus"/></p></td>
          <td valign="top"><p><bean:write name="element" property="realInterest"/></p></td>
          <td valign="top"><p><bean:write name="element" property="realMoney"/></p></td>
          <td valign="top"><p><bean:write name="element" property="bizSt"/></p></td>
           </tr>
        <tr > 
          <td colspan="15" class=td5 ></td>
        </tr>
        </logic:iterate>
        </logic:notEmpty>
        <logic:empty name="callbacklist">
        <tr> 
          <td colspan="15" height="30" style="color:red">没有找到与条件相符合的结果！</td>
	    </tr>
		<tr > 
          <td colspan="15" class=td5 ></td>
        </tr>
        </logic:empty>
        </table>
       <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr class="td1"> 
         <td align="left">共<bean:write name="pagination" property="nrOfElements"/> 项</td>
         <td align="right"><jsp:include page="../../../inc/pagination.jsp"><jsp:param name="url" value="badDebtDestroyTbShowAC.do"/></jsp:include></td>       
        </tr>
      </table>
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr valign="bottom"> 
          <td  colspan="7" bgcolor="#FFFFFF" align="center" height="30"> 
              <table border="0" cellspacing="0" cellpadding="0">
                <tr> 
                  <td width="69" align="right"> 
                    <html:submit property="method" styleClass="buttona" onclick="return gotoDelete();">
						<bean:message key="button.delete" />
					</html:submit>
                  </td>
                  <td width="69" align="right"> 
                    <html:submit property="method" styleClass="buttona" onclick="return gotoCallback();">
						<bean:message key="button.callback" />
					</html:submit>
                  </td>
                  <td width="69" align="right"> 
                    <html:submit property="method" styleClass="buttona" onclick="return gotoPrint();">
						<bean:message key="button.print" />
					</html:submit>
                  </td>
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
