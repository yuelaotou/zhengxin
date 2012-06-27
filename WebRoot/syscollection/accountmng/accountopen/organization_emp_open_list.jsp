<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@page
	import="org.xpup.hafmis.syscollection.accountmng.accountopen.action.OrgOpenEmpShowListAC"%>
<%@page
	import="org.xpup.hafmis.syscollection.accountmng.accountopen.action.OrgOpenFindListAC"%>
<%@ include file="/checkUrl.jsp"%>
<%
			Object pagination = session
			.getAttribute(OrgOpenEmpShowListAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String path = request.getContextPath();
	request.getSession().getAttribute("orgkhCriteronsAF");
	String orgid = "";
	if (request.getAttribute("orgId") != null) {
		orgid = (String) request.getAttribute("orgId");
	}
%>

<html:html>
<head>
	<title>开户销户&gt;&gt;单位开户</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script language="javascript" src="<%=path%>/js/common.js" />



</head>
<script language="javascript">
</script>
<script>
var s1="";//几个按钮放几个变量
var s2="";
var s3="";
var s4="";
var s8="";
var isType="<%=request.getAttribute("isType")%>";
function loads(){

	for(i=0;i<document.all.length;i++){//固定写法
		if(document.all.item(i).type=="submit"){//如果按钮是提交
          
			if(document.all.item(i).value=="职工开户"){
				s3=i;
			}
			if(document.all.item(i).value=="查询"){
				s8=i;
			}
			if(isType==1){
	          if(document.all.item(i).value=="职工开户"){
					s5=i;
				}
				if(document.all.item(i).value=="提交数据"){
					s6=i;
				}
				if(document.all.item(i).value=="撤销提交数据"){
					s7=i;
				}
            }else{
	            //if(document.all.item(i).value=="修改"){
				//	s1=i;
				//}
				//if(document.all.item(i).value=="删除"){
				//	s2=i;
				//}
				if(document.all.item(i).value=="开户完成"){
					s4=i;
				}
            
            }
          
		}
	}    
	if(document.all.listCount.value==0){
	//listCount就是此列表的数据 也是list.size的长度...如果这个List is null 的话
	//在showAct里面放的list集合
	    if(isType==1){
	        document.all.item(s5).disabled="true";
			document.all.item(s6).disabled="true";
			document.all.item(s7).disabled="true";
	       }
			//document.all.item(s1).disabled="true";
			//document.all.item(s2).disabled="true";
			document.all.item(s3).disabled="true";
			document.all.item(s4).disabled="true";
		}else{
		update();
    	}	 	 	

}

var tr="tr0"; 
function gettr(trindex) {
  tr=trindex;
  update();
}
function update() {
var status=document.getElementById(tr).childNodes[9].innerHTML.trim();
if(isType==1){
     var statust=document.getElementById(tr).childNodes[9].innerHTML.trim();
     
     var countnum=document.getElementById(tr).childNodes[6].innerHTML.trim();
     if(countnum==0){
            document.all.item(s5).disabled="";
	  		document.all.item(s6).disabled="true";
			document.all.item(s7).disabled="true";
			if(status=="封存"){
			document.all.item(s3).disabled="true";
		  	}
		  	if(status=="注销"){
			document.all.item(s3).disabled="true";
		  	}
      }else{
	    if(statust=="未提交"){
			document.all.item(s5).disabled="";
	  		document.all.item(s6).disabled="";
			document.all.item(s7).disabled="true";
	  	}
	  	if(statust=="未提取"){
			document.all.item(s5).disabled="";
	  		document.all.item(s6).disabled="true";
			document.all.item(s7).disabled="";
	  	}  	
	  	if(statust=="已提取"){
			document.all.item(s5).disabled="";
	  		document.all.item(s6).disabled="true";
			document.all.item(s7).disabled="true";
	  	}
	  	if(status=="封存"){
		document.all.item(s3).disabled="true";
	  	}
	  	if(status=="注销"){
		document.all.item(s3).disabled="true";
	  	} 
	  }
  }else{
  	if(status=="开户中"){
		//document.all.item(s1).disabled="";
  		//document.all.item(s2).disabled="";
		document.all.item(s3).disabled="";
		document.all.item(s4).disabled="";
  	}
  	if(status=="正常"){
		//document.all.item(s1).disabled="";
  		//document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="";
		document.all.item(s4).disabled="true";
  	}  	
  	if(status=="封存"){
		//document.all.item(s1).disabled="true";
  		//document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="true";
		document.all.item(s4).disabled="true";
  	}
  	if(status=="注销"){
		//document.all.item(s1).disabled="true";
  		//document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="true";
		document.all.item(s4).disabled="true";
  	} 
  }
}

function aaa(){
  document.all.enddate.value=document.all.startdate.value.trim();
}
function openover(){
	var x=confirm("是否执行开户完成?");
		if(x){ 
		  	return true;
		}else{
		  return false;
		}
}
function ondelete(){
  var x=confirm("是否删除该条信息，一旦被删除将不能再恢复！");
  if(x){
return true;

  }else
  {
    return false;
  }
}
function reportsErrors(){
var orgid='<%=orgid%>';
	<logic:messagesPresent>
		var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
		if(orgid!=''){
			var x=confirm(message);
			if(x){
				document.URL='orgInfoRemoveAC.do?id='+orgid;
			}else{
			    return false;
			}
		}else{
			alert(message);
		}
	</logic:messagesPresent> 	
}

 function keyvalue()
{

   if(event.keyCode==13)
  {
     //event.keyCode=0;
      document.all.item(s8).focus();
     //document.getElementById("buttona").focus();
    return false;
 }
}

</script>
<body bgcolor="#FFFFFF" text="#000000"
	onLoad="loads(); reportsErrors();" onContextmenu="return true">
	<jsp:include page="../../../inc/sort.jsp">
		<jsp:param name="url" value="organization_open_show.do" />
	</jsp:include>

	<table width="95%" border="0" cellspacing="0" cellpadding="0"
		align="center">
		<tr>
			<td>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="7">
							<img src="<%=path%>/img/table_left.gif" width="7" height="37">
						</td>
						<td background="<%=path%>/img/table_bg_line.gif" width="55">
							&nbsp;
						</td>
						<td width="235" background="<%=path%>/img/table_bg_line.gif">
							<table border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="112" height="37"
										background="<%=path%>/img/buttonbl.gif" align="center"
										style="PADDING-top: 7px" valign="top">
										职工开户
									</td>
								</tr>
							</table>
						<td background="<%=path%>/img/table_bg_line.gif" align="right">
							<table width="300" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="37">
										<img src="<%=path%>/img/title_banner.gif" width="37"
											height="24">
									</td>
									<td width="148" class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">开户销户&gt;单位开户</font>
									</td>
									<td width="115" class=font14>
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
						<td width="9">
							<img src="<%=path%>/img/table_right.gif" width="9" height="37">
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td class=td3>
				<table width="95%" border="0" cellspacing="3" cellpadding="3"
					align="center">
					<tr>
						<td height="35">
							<table width="100%" border="0" cellspacing="3" cellpadding="3">
								<tr>
									<td height="22" bgcolor="#CCCCCC" align="center" width="117">
										<b class="font14">基 本 信 息</b>
									</td>
									<td height="22" background="<%=path%>/img/bg2.gif"
										align="center">
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>

				<html:form action="/find_organizations_kh_1" styleClass="margin: 0">
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=0 align="center">
						<tr>
							<td width="22%" class="td1">
								单位编号
							</td>
							<td colspan="3">
								<html:text property="orgid" styleClass="input3"
									onkeydown="return keyvalue();" styleId="txtsearch"></html:text>
								<html:hidden name="orgkhCriteronsAF" property="listCount"
									styleClass="input3" styleId="txtsearch"></html:hidden>
							</td>
							<td width="20%" class="td1">
								单位名称
							</td>
							<td width="31%">
								<html:text property="name" styleClass="input3"
									onkeydown="enterNextFocus1();" styleId="txtsearch"></html:text>
							</td>
						</tr>
						<tr>
							<td colspan="7">
								<table width="100%" border="0" align="center" cellpadding=0
									cellspacing=1 id="table1">
									<tr>
										<td width="22%" class="td1">
											单位状态
										</td>
										<td colspan="3">
											<html:select property="status" styleClass="input4"
												onkeydown="enterNextFocus1();">
												<html:option value=""></html:option>
												<html:optionsCollection property="statusMap"
													name="orgkhCriteronsAF" label="value" value="key" />
											</html:select>
										</td>
										<td width="20%" class="td1">
											缴存方式
										</td>
										<td width="31%">
											<html:select property="payMode" styleClass="input4"
												onkeydown="enterNextFocus1();">
												<html:option value=""></html:option>
												<html:optionsCollection property="payModeMap"
													name="orgkhCriteronsAF" label="value" value="key" />
											</html:select>
										</td>
									</tr>
									<tr>
										<td width="20%" class="td1">
											开户日期
										</td>
										<td width="11%">
											<html:text property="startdate" styleClass="input3"
												styleId="txtsearch"></html:text>
										</td>
										<td>
											至
										</td>
										<td width="11%">
											<html:text property="enddate" styleClass="input3"
												styleId="txtsearch"></html:text>
										</td>

										<td width="20%"></td>
										<td width="31%">

										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td align="right">
								<html:submit styleClass="buttona" property="buttona">查询</html:submit>
							</td>
						</tr>
					</table>
				</html:form>
				<html:form action="/organization_kh_maintain" style="margin: 0">
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="117">
											<b class="font14">单 位 列 表</b>
										</td>
										<td width="826" height="22" align="center"
											background="<%=path%>/img/bg2.gif">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr bgcolor="1BA5FF">
							<td align="center" height="6" colspan="1"></td>
						</tr>
					</table>
					<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
						cellpadding="3" align="center">
						<tr>
							<td align="center" bgcolor="C4F0FF">
								&nbsp;
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('orgs.id')">单位编号</a>
								<logic:equal name="pagination" property="orderBy"
									value="orgs.id">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>
							<td align="center" class=td2>
								<a href="javascript:sort('orgs.orgInfo.name')">单位名称</a>
								<logic:equal name="pagination" property="orderBy"
									value="orgs.orgInfo.name">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>

							<td align="center" class=td2>
								缴存方式
							</td>
							<td align="center" class=td2>
								办事处
							</td>
							<td align="center" class=td2>
								归集银行
							</td>
							<td align="center" class=td2>
								缴存人数
							</td>
							<td align="center" class=td2>
								缴存总额
							</td>
							<td align="center" class=td2>
								开户日期
							</td>

							<td align="center" class=td2>
								单位状态
							</td>
							<security:orgcan>
								<td align="center" class=td2>
									提交状态
								</td>
							</security:orgcan>
						</tr>
						<logic:notEmpty name="organizations">
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate id="organization" name="organizations" indexId="i">
								<%
											j++;
											if (j % 2 == 0) {
												strClass = "td8";
											} else {
												strClass = "td9";
											}
								%>
								<tr id="tr<%=i%>"
									onclick='gotoClickpp("<%=i%>", idAF);gettr("tr<%=i%>");'
									onMouseOver='this.style.background="#eaeaea"'
									onMouseOut='gotoColorpp("<%=i%>",idAF);' class="<%=strClass%>">
									<td valign="top">
										<p align="left">
											<input id="s<%=i%>" type="radio" name="id"
												value="<bean:write name="organization" property="id"/>"
												<%if(new Integer(0).equals(i)) out.print("checked"); %>>
										</p>
									</td>
									<td valign="top">
										<bean:write name="organization" property="id"
											format="0000000000" />
									</td>
									<td valign="top">
										<bean:write name="organization" property="orgInfo.name" />
									</td>
									<td valign="top">
										<bean:write name="organization" property="payModeStr" />
									</td>
									<td valign="top">
										<bean:write name="organization" property="orgInfo.officecode" />
									</td>
									<td valign="top">
										<bean:write name="organization"
											property="orgInfo.collectionBankId" />
									</td>
									<td valign="top">
										<bean:write name="organization" property="orgPayCount" />
									</td>
									<td valign="top">
										<bean:write name="organization" property="sumPay"
											format="0.00" />
									</td>
									<td valign="top">
										<bean:write name="organization" property="orgInfo.openDate" />
									</td>

									<td valign="top">
										<bean:write name="organization" property="orgInfo.openstatus" />
									</td>
									<security:orgcan>
										<td valign="top">
											<bean:write name="organization" property="orgStatus" />
										</td>
									</security:orgcan>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="organizations">
							<tr>
								<td colspan="11" height="30" style="color:red">
									没有找到与条件相符合结果！
								</td>
							</tr>
						</logic:empty>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr class="td1">
							<td>
								<table width="100%" border="0" align="center" cellpadding="0"
									cellspacing="0">
									<tr>
										<td align="left">
											共
											<bean:write name="pagination" property="nrOfElements" />
											项
										</td>
										<td align="right">

											<jsp:include page="../../../inc/pagination.jsp">
												<jsp:param name="url" value="orgOpenEmpShowListAC.do" />
											</jsp:include>

										</td>
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
										<td width="70">
											<html:submit property="method" styleClass="buttona">
												<bean:message key="button.emp.open" />
											</html:submit>
										</td>
										<security:orgcannot>
											<td width="70">
												<html:submit property="method" styleClass="buttona"
													onclick="return openover();">
													<bean:message key="button.open.over" />
												</html:submit>
											</td>
										</security:orgcannot>
										<security:orgcan>
											<td width="70">
												<html:submit property="method" styleClass="buttona">
													<bean:message key="button.referring.data" />
												</html:submit>
											</td>
											<td width="70">
												<html:submit property="method" styleClass="buttonb">
													<bean:message key="button.pproval.data" />
												</html:submit>
											</td>
										</security:orgcan>
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
