<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ page import="org.xpup.hafmis.sysloan.systemmaintain.hafoperateLog.action.HafoperatorLogShowAC" %>
<%@ include file="/checkUrl.jsp"%>
<%  
   Object pagination= request.getSession(false).getAttribute(HafoperatorLogShowAC.PAGINATION_KEY);
   request.setAttribute("pagination",pagination);
 %>
<html:html>
<head>
<title>日志操作查询</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/index.css" type="text/css">
<script src="<%=request.getContextPath()%>/js/common.js"></script>

<script type="text/javascript">
function loads(){
	document.hafoperateLogAF.opmodle.value="";
	document.hafoperateLogAF.oper.value="";
	document.hafoperateLogAF.starttime.value="";
	document.hafoperateLogAF.endtime.value="";
	document.hafoperateLogAF.opaction.value="";
}
function checkData(){
	var starttime=document.hafoperateLogAF.starttime;
	var endtime=document.hafoperateLogAF.endtime;
   if(starttime!=null&&starttime!=""){
		if(!checkDate(starttime)){
			document.hafoperateLogAF.starttime.focus();
			return false;
		}
	}
   if(endtime!=null&&endtime!=""){
		if(!checkDate(endtime)){
			document.hafoperateLogAF.endtime.focus();
			return false;
		}
	}
}
</script>
</head>

<body bgcolor="#FFFFFF" text="#000000" onload="loads();"  >
<jsp:include page="../../../inc/sort.jsp"><jsp:param name="url" value="hafoperatorLogShowAC.do"/></jsp:include>

<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td>
    
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="7"><img src="<%=request.getContextPath()%>/img/table_left.gif" width="7" height="37"></td>
          <td background="<%=request.getContextPath()%>/img/table_bg_line.gif" width="55">&nbsp;</td>
          <td width="235" background="<%=request.getContextPath()%>/img/table_bg_line.gif">
            
           <td background="<%=request.getContextPath()%>/img/table_bg_line.gif" align="right"> 
            <table width="300" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="37"><img src="<%=request.getContextPath()%>/img/title_banner.gif" width="37" height="24"></td>
                <td width="148" class=font14 bgcolor="#FFFFFF" align="center" valign="bottom"><font color="00B5DB">日志查询</font></td>
                <td width="115" class=font14>&nbsp;</td>
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
                  <td height="22" bgcolor="#CCCCCC" align="center" width="200"><b class="font14">查 
                    询 条 件</b></td>
                <td height="22" background="<%=request.getContextPath()%>/img/bg2.gif" align="center">&nbsp;</td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
      <html:form action="/hafoperatorLogFindAC.do">
        <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=0 align="center" >
          <tr> 
            <td width="17%"   class="td1">操作模块</td>
            <td width="33%" colspan="3"  > 
             <html:select property="opmodle" styleClass="input4" >
              <html:option value=""></html:option>
              <html:optionsCollection property="opmodlemap" name="hafoperateLogAF" label="value" value="key"/>
             </html:select> 
            </td>
            <td width="17%" class="td1" >操作员</td>
            <td width="33%" colspan="3"  > 
             <html:select property="oper" styleClass="input4" >
              <html:option value=""></html:option>                    
              <html:options  collection="operList1" property="value" labelProperty="label"/>
             </html:select> 
            </td>
          </tr>
          <tr> 
            <td width="17%"   class="td1">起止日期<font color="#FF0000" >*</font></td>
            <td width="15%"  > 
                <html:text name="hafoperateLogAF" property="starttime" styleClass="input3" styleId="txtsearch"></html:text>
            </td>
            <td width="4%" align="center" >至</td>
            <td width="14%"  >
                <html:text name="hafoperateLogAF" property="endtime" styleClass="input3" styleId="txtsearch"></html:text>
            </td>
            <td width="17%" class="td1" >操作动作</td>
            <td width="33%"  > 
             <html:select property="opaction" styleClass="input4" >
              <html:option value=""></html:option>
              <html:optionsCollection property="opactionmap" name="hafoperateLogAF" label="value" value="key"/>
             </html:select> 
            </td>
          </tr>
        </table>
      <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
            <td align="right">
                <html:submit styleClass="buttona" onclick="return checkData();" ><bean:message key="button.search" /></html:submit>
            </td>
        </tr>
      </table>
      </html:form>
      
       <html:form action="/hafoperatorLogMaintainAC.do">
        <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td height="35"> 
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                  <td height="22" bgcolor="#CCCCCC" align="center" width="160"><b class="font14">日 志 信 息 列 表</b></td>
                  <td height="22" background="<%=request.getContextPath()%>/img/bg2.gif" align="center" width="724">&nbsp;</td>
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
        <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
          <tr > 
            <td align="center" height="23" bgcolor="C4F0FF" >　</td>
            <td align="center" class=td2 >
	          	<a href="javascript:sort('plOperateLog.opModel')">操作模块</a>
	          	<logic:equal name="pagination" property="orderBy" value="plOperateLog.opModel">
	          	  <logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
	          	  <logic:equal name="pagination" property="orderother" value="DESC">▼</logic:equal>
	          	</logic:equal>
            </td>
            <td align="center" class=td2 >
	          	<a href="javascript:sort('plOperateLog.opButton')">操作动作</a>
	          	<logic:equal name="pagination" property="orderBy" value="plOperateLog.opButton">
	          	  <logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
	          	  <logic:equal name="pagination" property="orderother" value="DESC">▼</logic:equal>
	          	</logic:equal>
            </td>
            <td align="center" class=td2 >
	          	<a href="javascript:sort('plOperateLog.opIp')">操作IP</a>
	          	<logic:equal name="pagination" property="orderBy" value="plOperateLog.opIp">
	          	  <logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
	          	  <logic:equal name="pagination" property="orderother" value="DESC">▼</logic:equal>
	          	</logic:equal>
            </td>
            <td align="center" class=td2 >
	          	<a href="javascript:sort('plOperateLog.opBizId')">操作业务ID</a>
	          	<logic:equal name="pagination" property="orderBy" value="plOperateLog.opBizId">
	          	  <logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
	          	  <logic:equal name="pagination" property="orderother" value="DESC">▼</logic:equal>
	          	</logic:equal>
            </td>
            <td align="center" class=td2 >操作时间</td>
          	<td align="center" class=td2 >
	          	<a href="javascript:sort('plOperateLog.operator')">操作员</a>
	          	<logic:equal name="pagination" property="orderBy" value="plOperateLog.operator">
	          	  <logic:equal name="pagination" property="orderother" value="ASC">▲</logic:equal>
	          	  <logic:equal name="pagination" property="orderother" value="DESC">▼</logic:equal>
	          	</logic:equal>
            </td>
          </tr>
          
           <logic:notEmpty name="hafoperateLogAF" property="list">
            <logic:iterate id="element" name="hafoperateLogAF" property="list" indexId="i">
           <tr  class=td4  onDblClick=""> 
            <td valign="top" >   
            </td>  
            <td ><bean:write name="element" property="opModelshow"/> </td>
            <td ><bean:write name="element" property="opButtonshow"/> </td>
            <td ><bean:write name="element" property="opIp"/></td>
            <td ><bean:write name="element" property="opBizId"/> </td>
            <td ><bean:write name="element" property="opTime"/> </td>
            <td ><bean:write name="element" property="operator"/> </td>
            </tr>
            <tr > 
             <td colspan="14" class=td5 ></td>
            </tr>
            </logic:iterate>
          </logic:notEmpty>
         
         <logic:empty name="hafoperateLogAF" property="list">
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
		  <td>
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr> 
			  	<td align="left">共<bean:write name="pagination" property="nrOfElements"/> 项</td>
                <td align="right"><jsp:include page="../../../inc/pagination.jsp"><jsp:param name="url" value="hafoperatorLogShowAC.do"/></jsp:include></td>
              </tr>
            </table>
          </td>
	    </tr>
      </table>
      
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr valign="bottom"> 
          <td  colspan="7" bgcolor="#FFFFFF" align="center" height="30"> 
          
           <logic:notEmpty name="hafoperateLogAF" property="list">
              <table border="0" cellspacing="0" cellpadding="0">
                <tr> 
                  <td width="70"> 
                  <html:submit property="method" styleClass="buttona"><bean:message key="button.print"/></html:submit>&nbsp;
                  </td>
                </tr>
              </table>
           </logic:notEmpty>
                     
           <logic:empty name="hafoperateLogAF" property="list">
              <table border="0" cellspacing="0" cellpadding="0">
                <tr> 
                  <td width="70"> 
                  <html:submit property="method" styleClass="buttona" disabled="true"><bean:message key="button.print"/></html:submit>&nbsp;
                  </td>
                </tr>
              </table>
           </logic:empty>
           
          </td>
        </tr>
      </table>
		  </html:form>
      
      
      
    </td>
  </tr>
</table>

</body>
</html:html>