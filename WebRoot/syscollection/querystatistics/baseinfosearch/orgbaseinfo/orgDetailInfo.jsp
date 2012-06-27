<%@ page language="java" pageEncoding="gbk"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security" %>
<%@ include file="/checkUrl.jsp"%>
<% String path = request.getContextPath(); %>

<html:html lang="true">
  <head>
    <html:base />
    <title>单位信息列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  <script language="javascript" src="<%=path%>/js/common.js"></script>
  <link rel="stylesheet" href="<%=path %>/css/index.css" type="text/css">
  <body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false" >
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="7"><img src="<%=path %>/img/table_left.gif" width="7" height="37"></td>
          <td background="<%=path %>/img/table_bg_line.gif" width="55">&nbsp;</td>
          <td width="235" background="<%=path %>/img/table_bg_line.gif">&nbsp;</td>
          <td background="<%=path %>/img/table_bg_line.gif" align="right"> 
            <table width="300" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="37"><img src="<%=path %>/img/title_banner.gif" width="37" height="24"></td>
                  <td width="148" class=font14 bgcolor="#FFFFFF" align="center" valign="bottom"><font color="00B5DB">统计查询&gt;单位基本信息</font></td>
                <td width="115" class=font14>&nbsp;</td>
              </tr>
            </table>
          </td>
          <td width="9"><img src="<%=path %>/img/table_right.gif" width="9" height="37"></td>
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
                <td height="22" bgcolor="#CCCCCC" align="center" width="117"><b class="font14">自 然 
                   信 息</b></td>
                <td height="22" background="<%=path %>/img/bg2.gif" align="center">&nbsp;</td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
        <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=3 align="center" >
          <tr> 
            <td width="17%" class="td1">单位名称</td>
            <td width="33%"> 
              <html:text property="orgInfo.name" name="orginfo" styleClass="input3" readonly="true"></html:text>
            </td>
            <td class="td1" width="17%">办事处名称</td>
            <td width="33%"  > 
              <html:text property="orgInfo.temp_officename" name="orginfo" styleClass="input3" readonly="true"></html:text>
            </td>
          </tr>
          <tr> 
            <td width="17%"   class="td1">组织机构代码</td>
            <td width="33%" > 
              <html:text property="orgInfo.code" name="orginfo" styleClass="input3" readonly="true"></html:text>
            </td>
            <td width="17%" class="td1" >税务登记号</td>
            <td width="33%"  > 
              <html:text property="orgInfo.taxRegNum" name="orginfo" styleClass="input3" readonly="true"></html:text>
            </td>
          </tr>
          <tr> 
            <td width="17%"   class="td1">单位法人</td>
            <td width="33%"> 
              <html:text property="orgInfo.artificialPerson" name="orginfo" styleClass="input3" readonly="true"></html:text>
            </td>
            <td width="17%" class="td1" >单位性质</td>
            <td width="33%"  > 
              <html:text property="orgInfo.temp_character" name="orginfo" styleClass="input3" readonly="true"></html:text>
            </td>
          </tr>
          <tr> 
            <td width="17%"   class="td1">所属行业</td>
            <td width="33%"  > 
              <html:text property="orgInfo.temp_industry" name="orginfo" styleClass="input3" readonly="true"></html:text>
            </td>
            <td width="17%" class="td1" >主管部门</td>
            <td width="33%"  > 
              <html:text property="orgInfo.temp_deptInCharge" name="orginfo" styleClass="input3" readonly="true"></html:text>
            </td>
          </tr>
          <tr> 
            <td width="17%"   class="td1">单位地址</td>
            <td width="33%"  > 
              <html:text property="orgInfo.address" name="orginfo" styleClass="input3" readonly="true"></html:text>
            </td>
            <td width="17%" class="td1" >邮政编码</td>
            <td width="33%"  > 
              <html:text property="orgInfo.postalcode" name="orginfo" styleClass="input3" readonly="true"></html:text>
            </td>
          </tr>
          <tr> 
            <td   class="td1" width="17%">单位电话</td>
            <td width="33%"  > 
              <html:text property="orgInfo.tel" name="orginfo" styleClass="input3" readonly="true"></html:text>
            </td>
            <td class="td1" width="17%" >所在地区</td>
            <td width="33%"  > 
              <html:text property="orgInfo.temp_region" name="orginfo" styleClass="input3" readonly="true"></html:text>
            </td>
          </tr>
          <tr> 
            <td   class="td1" width="17%">单位发薪银行</td>
            <td width="33%"  > 
              <html:text property="orgInfo.payBank.name" name="orginfo" styleClass="input3" readonly="true"></html:text>
            </td>
            <td class="td1" width="17%" >发薪行帐号</td>
            <td width="33%"  > 
              <html:text property="orgInfo.payBank.accountNum" name="orginfo" styleClass="input3" readonly="true"></html:text>
            </td>
          </tr>
          <tr> 
            <td   class="td1" width="17%">发薪日</td>
            <td width="33%"  > 
              <html:text property="orgInfo.paydate" name="orginfo" styleClass="input3" readonly="true"></html:text>
            </td>
            <td class="td1" width="17%" >归集银行</td>
            <td width="33%"  > 
              <html:text property="orgInfo.temp_collectionBankname" name="orginfo" styleClass="input3" readonly="true"></html:text>
            </td>
          </tr>
          <tr> 
            <td   class="td1" width="17%">单位经办人</td>
            <td width="33%"  > 
              <html:text property="orgInfo.transactor.name" name="orginfo" styleClass="input3" readonly="true"></html:text>
            </td>
            <td class="td1" width="17%" >经办人E-mial</td>
            <td width="33%"  > 
              <html:text property="orgInfo.transactor.email" name="orginfo" styleClass="input3" readonly="true"></html:text>
            </td>
          </tr>
          <tr> 
            <td width="17%"   class="td1">经办人电话</td>
            <td width="33%"  > 
              <html:text property="orgInfo.transactor.telephone" name="orginfo" styleClass="input3" readonly="true"></html:text>
            </td>
            <td width="17%" class="td1" >经办人移动电话</td>
            <td width="33%"  > 
              <html:text property="orgInfo.transactor.mobileTelephone" name="orginfo" styleClass="input3" readonly="true"></html:text>
            </td>
          </tr>
          <tr id="tr2"> 
            <td width="17%"   class="td1">稽查员</td>
            <td width="33%"> 
              <html:text property="orgInfo.inspector" name="orginfo" styleClass="input3" readonly="true"></html:text>
            </td>
            <td width="17%" class="td1" >开户日期</td>
            <td width="33%" > 
              <html:text property="orgInfo.openDate" name="orginfo" styleClass="input3" readonly="true"></html:text>
            </td>
          </tr>
          <tr id="tr2">
            <td width="17%"   class="td1">单位状态</td>
            <td width="33%">
              <html:text property="orgInfo.temp_openstatus" name="orginfo" styleClass="input3" readonly="true"></html:text>
            </td>
            <td width="17%" class="td1" ></td>
            <td width="33%" >&nbsp;</td>
          </tr>
        </table>
	    <br>
      </td>
  </tr>
</table>
<table width="95%" border="0" cellspacing="0" cellpadding="3"
	align="center">
	<tr valign="bottom">
		<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="70"> 
               					<input type="button" name="Submit42" value="关闭" class="buttona" onClick="javascript:window.close();" ></td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</body>
</html:html>
