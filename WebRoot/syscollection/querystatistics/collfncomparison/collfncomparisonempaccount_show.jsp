<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@page import="org.xpup.hafmis.syscollection.querystatistics.collfncomparison.action.CollFnComparisonEmpAccountShowAC"%>
<%
String path = request.getContextPath();
Object pagination = session.getAttribute(CollFnComparisonEmpAccountShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
    <link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
  </head>
<script language="javascript" src="<%=path%>/js/common.js"></script>
<script language="javascript">
var tr="tr0";
	var oldColor="#ffffff";                            //原来的颜色
	var newColor="#E8FCFD";                     //要变成的颜色
	function chgBGColor(oTD){
		oldColor=oTD.style.backgroundColor;//保存当前颜色
		oTD.style.backgroundColor=newColor;//改变表格颜色
		newColor=oldColor;                 //改变下次要变成的颜色
	}
function gettr(trindex) {
	tr=trindex;
}
	var old_temp="tr0";
function gotoClick1(id1,id2,form){
	var temp1;
	var temp2;
	var temp3;
	eval("temp1="+id1);
	eval("temp3="+old_temp);
	eval("temp2=form."+id2);
	temp2.checked="true";
	old_temp=id1;
}
var vcount = 0;

var vresorgid;
var vresorgname;
var vresempid;
var vresempname;
var vcardnum;
function toorgpop(){
	var orgid=document.collFnComparisonEmpAccountAF.orgId.value.trim();
	var orgname=document.collFnComparisonEmpAccountAF.orgName.value.trim();
	var empid = document.collFnComparisonEmpAccountAF.empId.value.trim();
	var empname = document.collFnComparisonEmpAccountAF.empName.value.trim();
	var timeSt=document.collFnComparisonEmpAccountAF.timeSt.value.trim();
	var timeEnd=document.collFnComparisonEmpAccountAF.timeEnd.value.trim();
	if(timeSt==""){
		alert('请输入开始发生日期');
		return false;
	}
	if(timeEnd==""){
		alert('请输入结束发生日期');
		return false;
	}
	orgname =encodeURI(orgname);
	empname =encodeURI(empname);
	var queryString = "collFnComparisonEmpAccountPopAAC.do?";    
	   	queryString = queryString + "orgid="+orgid+"&orgname="+orgname+"&empid="+empid+"&empname="+empname+"&indexs="+'0';
	    findInfo(queryString);
	// 判断是否弹出页面
	if(vcount==0){
		document.forms[0].submit();
	}else if(vcount==1){
		document.forms[0].submit();
	}else{
		window.open("collFnComparisonEmpAccountPopShowAC.do?orgid="+orgid+"&orgname="+orgname+"&empid="+empid+"&empname="+empname+"&indexs="+'0',"window","height=450,width=700,top=300,left=300,scrollbars=yes, status=yes");
	}
	return false;
}
function executeAjax(){
	document.forms[0].submit();
}
function displays(resorgid,resorgname,resempid,resempname,cardnum,count){
	vcount = count;
	if(vcount==1){
		document.forms[0].elements["empId"].value=resempid;
		document.forms[0].elements["orgId"].value=resorgid;
		document.forms[0].elements["orgName"].value=resorgname;
		document.forms[0].elements["empName"].value=resempname;
		document.forms[0].elements["cardNum"].value=cardnum;
	}
}
function to_print(){
	window.open('<%=path%>/syscollection/querystatistics/collfncomparison/empAccountPrintPopForwardAC.do',"window","height=450,width=700,top=300,left=300,scrollbars=yes, status=yes");       
}
</script>
<body bgcolor="#FFFFFF" text="#000000"  onContextmenu="return false">

<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="7"><img src="<%=path%>/img/table_left.gif" width="7" height="37"></td>
          <td background="<%=path%>/img/table_bg_line.gif" width="55">&nbsp;</td>
          <td width="235" background="<%=path%>/img/table_bg_line.gif">&nbsp;</td>
          <td background="<%=path%>/img/table_bg_line.gif" align="right"> 
            <table width="300" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="37"><img src="<%=path%>/img/title_banner.gif" width="37" height="24"></td>
                  <td width="148" class=font14 bgcolor="#FFFFFF" align="center" valign="bottom"><font color="00B5DB">新版职工账&gt;职工账</font></td>
                <td width="115" class=font14>&nbsp;</td>
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
                <td height="22" bgcolor="#CCCCCC" align="center" width="117"><b class="font14">单 位 列 表</b></td>
                <td width="826" height="22" align="center" background="<%=path%>/img/bg2.gif">&nbsp;</td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
    <html:form action="/collFnComparisonEmpAccountFindAC" style="margin: 0">
        <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=3 align="center" >
          <tr> 
            <td width="17%" class="td1"  >单位编号</td>
            <td colspan="3"   > 
              <html:text name="collFnComparisonEmpAccountAF" property="orgId" styleClass="input3"  maxlength="50"></html:text>
            </td>
            <td width="17%" class="td1"  >单位名称</td>
            <td width="33%" colspan="3"  > 
              <html:text name="collFnComparisonEmpAccountAF" property="orgName" styleClass="input3"  maxlength="50"></html:text>
            </td>
          </tr>
          <tr> 
            <td width="17%"   class="td1">职工编号</td>
            <td width="33%" colspan="3"  > 
              <html:text name="collFnComparisonEmpAccountAF" property="empId" styleClass="input3"  maxlength="50"></html:text>
            </td>
            <td width="17%" class="td1" >职工姓名</td>
            <td width="33%"  colspan="3" > 
              <html:text name="collFnComparisonEmpAccountAF" property="empName" styleClass="input3"  maxlength="50"></html:text>
            </td>
          </tr>
          <tr> 
          <td width="17%"   class="td1">证件号码</td>
            <td width="33%" colspan="3"  > 
              <html:text name="collFnComparisonEmpAccountAF" property="cardNum" styleClass="input3"  maxlength="50"></html:text>
            </td>
            <td width="17%" class="td1" >发生日期<font color="#FF0000">*</font></td>
            <td width="15%"  > 
              <html:text name="collFnComparisonEmpAccountAF" property="timeSt" styleClass="input3"  maxlength="8" onkeydown="enterNextFocus1();"></html:text>
            </td>
            <td width="3%"  >至</td>
            <td width="15%"  > 
              <html:text name="collFnComparisonEmpAccountAF" property="timeEnd" styleClass="input3"  maxlength="8" onkeydown="enterNextFocus1();"></html:text>
            </td>	
          </tr>
        </table>
              <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
            <td align="right"><br>
            <input type="submit" name="submit1" onclick="return toorgpop();" class=buttona value="查询"/></td>
        </tr>
      </table>
      </html:form>
      <html:form action="/collFnComparisonEmpAccountMaintainAC" style="margin: 0">
      <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr> 
          <td height="35"> 
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                  <td height="22" bgcolor="#CCCCCC" align="center" width="142"><b class="font14">职工账列表 
                    </b></td>
                  <td height="22" background="<%=path%>/img/bg2.gif" align="center" width="762">&nbsp;</td>
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
          <tr> 
			<td align="center" class=td2 >日期</td>
			<td align="center" class=td2 >凭证号</td>
            <td align="center" class=td2 >摘要</td>
            <td align="center" class=td2 >借方</td>
            <td align="center" class=td2 >贷方</td>
            <td align="center" class=td2 >销户利息</td>
            <td align="center" class=td2 >方向 </td>
            <td align="center" class=td2 >余额</td>
          </tr>
          <logic:notEmpty name="collFnComparisonEmpAccountAF" property="list"> 
          <% int j=0;
			String strClass="";%>
          <logic:iterate name="collFnComparisonEmpAccountAF" property="list" id="resultdto" indexId="i"> 
            <logic:equal name="resultdto" property="type" value="">
          <%j++;
			if (j%2==0) {strClass = "td8";
			}
		    else {strClass = "td9";
		    }%>
          <tr id="tr<%=i%>"  onMouseOver='this.style.background="#eaeaea"'  onMouseOut='gotoColorpp("<%=i %>", idAF);' class="<%=strClass%>"  onDblClick=""> 
            <td style="display:none"> 
              <input id="s<%=i %>" type="radio" name="id" value="<bean:write name="resultdto" property="collDocNum"/>" <%if(new Integer(0).equals(i)) out.print("checked"); %> />
            </td>
            <td valign="top"><p><bean:write name="resultdto" property="collDate"/></p></td>
			<td valign="top"><p><bean:write name="resultdto" property="collDocNum"/></p></td>
            <td valign="top"><p><bean:write name="resultdto" property="bizType"/></p></td>
            <td valign="top"><p><bean:write name="resultdto" property="debit"/></p></td>
            <td valign="top"><p><bean:write name="resultdto" property="credit"/></p></td>
            <td valign="top"><p><bean:write name="resultdto" property="interest"/></p></td>
            <td valign="top"><p><bean:write name="resultdto" property="direction"/></p></td>
            <td valign="top"><p><bean:write name="resultdto" property="moneySum"/></p></td>
          </tr>
          <tr > 
          <td colspan="18" class=td5 ></td>
        	</tr>
        	
          </logic:equal>
          <logic:equal name="resultdto" property="type" value="0">
        
            <%j++;
			if (j%2==0) {strClass = "td8";
			}
		    else {strClass = "td9";
		    }%>
         <tr id="tr<%=i%>"  class=td4  onDblClick="" style="background-color:#FF6659;"> 
            <td style="display:none"> 
              <input id="s<%=i %>" type="radio" name="id" value="<bean:write name="resultdto" property="collDocNum"/>" <%if(new Integer(0).equals(i)) out.print("checked"); %> />
            </td>
            <td valign="top"><p><bean:write name="resultdto" property="collDate"/></p></td>
			<td valign="top"><p><bean:write name="resultdto" property="collDocNum"/></p></td>
            <td valign="top"><p><bean:write name="resultdto" property="bizType"/></p></td>
            <td valign="top"><p><bean:write name="resultdto" property="debit"/></p></td>
            <td valign="top"><p><bean:write name="resultdto" property="credit"/></p></td>
            <td valign="top"><p><bean:write name="resultdto" property="interest"/></p></td>
            <td valign="top"><p><bean:write name="resultdto" property="direction"/></p></td>
            <td valign="top"><p><bean:write name="resultdto" property="moneySum"/></p></td>
          </tr>
          <tr > 
          <td colspan="18" class=td5 ></td>
        	</tr>
             </logic:equal>
          </logic:iterate>
          </logic:notEmpty>
          <logic:empty name="collFnComparisonEmpAccountAF" property="list">
        <tr> 
          <td colspan="18" height="30" style="color:red">没有找到与条件相符合结果！</td>
	    </tr>
		<tr > 
          <td colspan="18" class=td5 ></td>
        </tr>
        </logic:empty>
        </table>
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr class="td1"> 
		  <td>
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr> 
			  	<td align="left">共<bean:write name="pagination" property="nrOfElements"/> 项</td>
                <td align="right"><jsp:include page="../../../inc/pagination.jsp"><jsp:param name="url" value="collFnComparisonEmpAccountShowAC.do"/></jsp:include></td>
              </tr>
            </table>
          </td>
	    </tr>
      </table>
      <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr valign="bottom"> 
          <td  colspan="10" bgcolor="#FFFFFF" align="center" height="30"> 
              <table border="0" cellspacing="0" cellpadding="0">
                <tr> 
                <logic:notEmpty name="collFnComparisonEmpAccountAF" property="list">
                  <td width="70">
					<input type="button" name="Submit4" value="打印" class="buttona" onclick="to_print();">
				  </td>
				  </logic:notEmpty>
				  <logic:empty name="collFnComparisonEmpAccountAF" property="list">
				  <td width="70">
				  <html:submit property="method" styleClass="buttona" disabled="true">
						<bean:message key="button.print" />
					</html:submit>
				  </td>
				  </logic:empty>
				  <td width="70"> 
                    <html:submit property="method" styleClass="buttona">
							<bean:message key="button.back" />
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
