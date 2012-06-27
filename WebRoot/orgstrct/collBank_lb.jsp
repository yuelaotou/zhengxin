<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ page import="org.xpup.hafmis.orgstrct.action.CollBankTaShowAC" %>
<%@ include file="/checkUrl.jsp"%>
<%  
   Object pagination= request.getSession(false).getAttribute(CollBankTaShowAC.PAGINATION_KEY);
   request.setAttribute("pagination",pagination);
   String path=request.getContextPath();
   
 %>
<html:html>   
<head>
<title>权限管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
</head>
<script src="<%=path%>/js/common.js">
</script>
<script type="text/javascript">
var s1="";
var s2="";
var s3="";
var s4="";

var tr="tr0"; 
function gettr(trindex) {
  tr=trindex;
  update1();
}

function update1() {
  	var status=document.getElementById(tr).childNodes[3].childNodes[0].innerHTML;
  	if(status=='正常'){
  		document.all.item(s1).disabled="";
		document.all.item(s2).disabled="";
  		document.all.item(s3).disabled="";
		document.all.item(s4).disabled="true";
  	}else if(status=='作废'){
  		document.all.item(s1).disabled="";
		document.all.item(s2).disabled="true";
  		document.all.item(s3).disabled="true";
		document.all.item(s4).disabled="";
  	}
}
function loads(){
<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
	for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
			if(document.all.item(i).value=="添加"){
				s1=i;
			}
			if(document.all.item(i).value=="修改"){
				s2=i;
			}
			if(document.all.item(i).value=="作废"){
				s3=i;
			}
			if(document.all.item(i).value=="撤消作废"){
				s4=i;
			}
		}
	}
    var count = document.all.count.value;
    if(count==0){
  		document.all.item(s1).disabled="";
		document.all.item(s2).disabled="true";
  		document.all.item(s3).disabled="true";
		document.all.item(s4).disabled="true";
    }else{
		update1();
    }
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onload="loads();" onContextmenu="return false">
<jsp:include page="../inc/sort.jsp"><jsp:param name="url" value="collBankTaShowAC.do"/></jsp:include>

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
                  <td class=font14 bgcolor="#FFFFFF" align="center" valign="bottom"><a href="#" class=a1>权限管理</a><font color="00B5DB">&gt;</font><a href="#" class=a1>归集银行</a></td>
                  <td class=font14>&nbsp;</td>
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
    <html:form action="/collBankTaFindAC">
      <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td height="35"> 
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td height="22" bgcolor="#CCCCCC" align="center" width="117"><b class="font14">查 
                  询 条 件</b></td>
                <td height="22" background="<%=path%>/img/bg2.gif" align="center">&nbsp;</td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
        <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=3 align="center" >
          <tr> 
            <td width="17%" class="td1"  >办事处：</td>
            <td width="22%"   > 
              <html:select property="officecode" styleClass="input4">
              	<html:option value=""></html:option>
              	<logic:notEmpty name="collBankTaAF"  property="officelist">
              	<logic:iterate name="collBankTaAF" id="element" property="officelist">
              	<option value="<bean:write name="element" property="officeCode"/>"><bean:write name="element" property="officeName"/></option>
              	</logic:iterate>
              	</logic:notEmpty>
              </html:select>
            </td>
            <td width="11%"  > 
                    <html:submit property="method" styleClass="buttona"><bean:message key="button.search" bundle="orgstrct"/></html:submit>
            </td>
          </tr>
        </table>
		</html:form>
    <html:form action="/collBankTaMaintainAC">
      <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td height="35"> 
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td height="22" bgcolor="#CCCCCC" align="center" width="117"><b class="font14">归集银行列表</b></td>
                <td height="22" background="<%=path%>/img/bg2.gif" align="center">&nbsp;</td>
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
            <td align="center" class=td2 >办事处</td>
            <td align="center" class=td2 >归集银行</td>
            <td align="center" class=td2 >归集银行状态</td>
            <td align="center" class=td2 >归集银行账号</td>
            <td align="center" class=td2 >联系人</td>
            <td align="center" class=td2 >联系电话</td>
            <td align="center" class=td2 >中心名称</td>
          </tr>
          
        <logic:notEmpty name="collBankTaAF" property="collBanklist">
		<logic:iterate name="collBankTaAF" property="collBanklist" id="element" indexId="i">
	
        <tr id="tr<%=i%>" onclick='gotoClick("tr<%=i %>","s<%=i%>", idAF);gettr("tr<%=i%>");' onMouseOver='this.style.background="#eaeaea"'  onMouseOut='gotoColor("tr<%=i %>","s<%=i %>", idAF);' class=td4  onDblClick=""> 
        
          <td valign="top">
          <p align="left">
            <input id="s<%=i %>" type="radio" name="id" value="<bean:write name="element" property="bankid"/>" <%if(new Integer(0).equals(i)) out.print("checked"); %>>
          </p>
          </td> 
          <td valign="top"><p><bean:write name="element" property="officename" /></p></td>
          <td valign="top"><p><bean:write name="element" property="bankname" /></p></td>
          <td valign="top"><p><bean:write name="element" property="bankStatus" /></p></td>
          <td valign="top"><p><bean:write name="element" property="collectionbankacc" /></p></td>
          <td valign="top"><p><bean:write name="element" property="contactprsn" /></p></td>
          <td valign="top"><p><bean:write name="element" property="contacttel" /></p></td>
           <td valign="top"><p><bean:write name="element" property="centername" /></p></td>
           </tr>
        <tr > 
          <td colspan="10" class=td5 ><input type="hidden" name="count" value="1"></td>
        </tr>
        </logic:iterate>
        </logic:notEmpty>
        <logic:empty name="collBankTaAF"  property="collBanklist">
        <tr> 
          <td colspan="11" height="30" style="color:red">没有找到与条件相符合结果！</td>
	    </tr>
		<tr > 
          <td colspan="11" class=td5 ><input type="hidden" name="count" value="0"></td>
        </tr>
        </logic:empty>
      </table>
     
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr class="td1"> 
          <td align="center">
            <table width="585" height="19" border="0" cellpadding="0" cellspacing="0">
              <tr> 
			  	<td align="left">共<bean:write name="pagination" property="nrOfElements"/> 项</td>
                <td align="right"><jsp:include page="../inc/pagination.jsp"><jsp:param name="url" value="collBankTaShowAC.do"/></jsp:include></td>
              </tr>
            </table>          </td>
        </tr>
      </table>
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr valign="bottom"> 
          <td  colspan="7" bgcolor="#FFFFFF" align="center" height="30"> 
              <table border="0" cellspacing="0" cellpadding="0">
                <tr> 
                  <td width="70"> 
                    <html:submit property="method" styleClass="buttona"><bean:message key="button.add" bundle="orgstrct"/></html:submit>
                  </td>
                  <td width="70"> 
                    <html:submit property="method" styleClass="buttona"><bean:message key="button.update" bundle="orgstrct"/></html:submit>
                  </td>
                  <td width="70"> 
                    <html:submit property="method" styleClass="buttona"><bean:message key="button.cancel" bundle="orgstrct"/></html:submit>
                  </td>
                  <td width="70"> 
                    <html:submit property="method" styleClass="buttona"><bean:message key="button.delcancel" bundle="orgstrct"/></html:submit>
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
