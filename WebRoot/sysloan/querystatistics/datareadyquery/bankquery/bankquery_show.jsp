<%@ page contentType="text/html; charset=UTF-8" import="java.util.List"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ include file="/checkUrl.jsp"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ page
	import="org.xpup.hafmis.sysloan.querystatistics.datareadyquery.bankquery.action.BankQueryShowAC"%>
<%
	String path = request.getContextPath();
	Object pagination = request.getSession().getAttribute(
			BankQueryShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>
<html:html>
<head>
<title>统计查询</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
</head>
<script src="<%=path%>/js/common.js"></script>
<script>
</script>
<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="7"><img src="<%=path%>/img/table_left.gif" width="7" height="37"></td>
          <td background="<%=path%>/img/table_bg_line.gif" width="55">&nbsp;</td>
            <td background="<%=path%>/img/table_bg_line.gif"><a href="特殊业务处理_五级分类修改_简约风格.html"></a></td>
          <td background="<%=path%>/img/table_bg_line.gif" align="right"> 
              <table border="0" cellspacing="0" cellpadding="0">
                <tr> 
                <td width="37"><img src="<%=path%>/img/title_banner.gif" width="37" height="24"></td>
                  <td width="238" class=font14 bgcolor="#FFFFFF" align="center" valign="bottom"><font color="00B5DB">数据准备信息查询&gt;放款银行查询</font></td>
                  <td width="25" class=font14>&nbsp;</td>
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
            <td height="35"> 
              <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                  <td height="22" bgcolor="#CCCCCC" align="center" width="184"><b class="font14">查 询 条 件</b></td>
                  <td width="675" height="22" align="center" background="<%=path%>/img/bg2.gif">&nbsp;</td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
      <html:form action="/bankQueryFindAC.do"  style="margin: 0">
      <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=0 align="center" > 
        <tr> 
          <td width="13%"   class="td1">银行名称 </td>
          <td width="18%"  ><html:select property="loanBankId" styleClass="input4"
									name="bankQueryAF" onkeydown="enterNextFocus1();">
									<option value=""></option>
									<html:options collection="loanbankList1" property="value"
										labelProperty="label" />
								</html:select></td>
          <td width="11%" class="td1" >所在中心办事处</td>
            <td width="21%"  > 
              <html:select property="office" styleClass="input4"
									name="bankQueryAF" onkeydown="enterNextFocus1();">
									<option value=""></option>
									<html:options collection="officeList1" property="value"
										labelProperty="label" />
								</html:select>
            </td>
        </tr>
		<tr> 
          <td width="13%"   class="td1">银行行长</td>
          <td width="18%"  ><html:text name="bankQueryAF" property="bankPrisident"
									styleClass="input3" onkeydown="enterNextFocus1();"/></td>
          <td width="11%" class="td1" >联系人</td>
          <td width="21%"  > 
            <html:text name="bankQueryAF" property="contactPrsn"
									styleClass="input3" onkeydown="enterNextFocus1();"/>         </td>
        </table>
	  <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td align="right"><html:submit styleClass="buttona"><bean:message key="button.search"/></html:submit></td>
        </tr>
      </table>
      </html:form>
	  <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td height="35"><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
                  <td height="22" bgcolor="#CCCCCC" align="center" width="178"><b class="font14">发放银行信息列表</b></td>
              <td width="681" height="22" align="center" background="<%=path%>/img/bg2.gif">&nbsp;</td>
            </tr>
          </table></td>
        </tr>
      </table>
     <html:form action="/bankQueryMaintainAC.do"  style="margin: 0">
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr bgcolor="1BA5FF" > 
          <td align="center" height="6" colspan="1" ></td>
        </tr>
      </table>
       <table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1" cellpadding="3" align="center">
          <tr align="center" bgcolor="C4F0FF"> 
            <td height="23" bgcolor="C4F0FF" >&nbsp;</td>
            <td align="center" class=td2 >
            <a href="javascript:sort('loanbankid')">银行名称</a>
								<logic:equal name="pagination" property="orderBy"
									value="loanbankid">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal></td>
            <td align="center" class=td2 >
            <a href="javascript:sort('office')">所在中心办事处</a>
								<logic:equal name="pagination" property="orderBy"
									value="office">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal></td>
            <td align="center" class=td2 >中心委托贷款账号</td>
            <td align="center" class=td2 >中心利息账号</td>
            <td align="center" class=td2 >银行行长</td>
            <td align="center" class=td2 >行长电话 </td>
            <td align="center" class=td2 >联系人</td>
            <td align="center" class=td2 >联系人电话</td>
            <td align="center" class=td2 >联系人职务</td>
            <td align="center" class=td2 >银行状态</td>
          </tr>
          <logic:notEmpty name="bankQueryAF" property="list">
          <% int j=0;
			String strClass="";
		%>
          <logic:iterate id="element" name="bankQueryAF" property="list" indexId="i">
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
											value="<bean:write name="element" property="id"/>"
											<%if(new Integer(0).equals(i)) out.print("checked"); %>>
            </td>
          <td valign="top"><p><bean:write name="element" property="loanBankId" format="000000"/></p></td>
           <td valign="top"><p><bean:write name="element" property="office" /></p></td>         
            <td valign="top"><p><bean:write name="element" property="loanAcc" /></p></td>
          <td valign="top"><p><bean:write name="element" property="interestAcc" /></p></td>
          <td valign="top"><p><bean:write name="element" property="bankPrisident" /></p></td>
          <td valign="top"><p><bean:write name="element" property="bankPrisidentTel" /></p></td>
          <td valign="top"><p><bean:write name="element" property="contactPrsn" /></p></td> 
          <td valign="top"><p><bean:write name="element" property="contactTel" /></p></td>         
          <td valign="top"><p><bean:write name="element" property="business" /></p></td>         
          <td valign="top"><p><bean:write name="element" property="loanBnakSt" /></p></td>
           </tr>
       
        </logic:iterate>
        </logic:notEmpty>
        <logic:empty name="bankQueryAF"  property="list">
        <tr>
				<td colspan="11" height="30" style="color:red">
									没有找到与条件相符合的结果！
									<br>
								</td>
							</tr>
          </logic:empty>
          
        </table>
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr class="td1"> 
		  <td>
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr> 
			  	<td align="left">共<bean:write name="pagination" property="nrOfElements"/> 项</td>
                <td align="right"><jsp:include page="../../../../inc/pagination.jsp"><jsp:param name="url" value="bankQueryShowAC.do"/></jsp:include></td>
              </tr>
          </table></td>
	    </tr>
      </table>
     <table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr valign="bottom">
							<td colspan="7" bgcolor="#FFFFFF" align="center" height="30">
								<table border="0" cellspacing="0" cellpadding="0">
									<logic:empty name="bankQueryAF" property="list">											
											<td width="69" align="right">
												<html:submit property="method" styleClass="buttona"
													disabled="true">
													<bean:message key="button.print" />
												</html:submit>
											</td>
											<td width="69" align="right">
												<html:submit property="method" styleClass="buttona"
													disabled="true">
													<bean:message key="button.printone" />
												</html:submit>
											</td>
										</logic:empty>
										<logic:notEmpty name="bankQueryAF" property="list">											
											<td width="69" align="right">
												<html:submit property="method" styleClass="buttona"
													>
													<bean:message key="button.print" />
												</html:submit>
											</td>
											<td width="69" align="right">
												<html:submit property="method" styleClass="buttona">
													<bean:message key="button.printone" />
												</html:submit>
											</td>
										</logic:notEmpty>
								</table>
							</td>
						</tr>
					</table>
      </html:form>
    
</body>
</html:html>

