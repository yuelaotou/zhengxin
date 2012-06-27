<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ page import="org.xpup.hafmis.syscollection.tranmng.tranout.action.*"%>
<%@ include file="/checkUrl.jsp"%>


<%
	String path = request.getContextPath();
	Object pagination = request.getSession(false).getAttribute(
			Tran_showAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String typetran = (String) request.getAttribute("typetran");
	String statetype = (String) request.getAttribute("statetype");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script language="javascript" src="<%=path%>/js/common.js"></script>

</head>

<script language="javascript">
var s1="";
var s2="";
var s3="";
var s4="";
var s5="";
var s6="";
var s7="";
var s8="";
var s9="";
var s11="";
var typetran=<%=typetran%>;
var statetype="<%=statetype%>";
function loads(){
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	if(""!=message){
		alert(message);
	}
	
	</logic:messagesPresent>
	var orgid=document.tranAF.elements["outOrgId"].value;
	var orgname=document.tranAF.elements["outOrgname"].value;
	var orginid=document.tranAF.elements["inOrgId"].value;
	var orginname=document.tranAF.elements["inOrgName"].value;
	var tranStatus=document.tranAF.elements["tranStatus"].value;
	if(orgid != "")
	document.tranAF.elements["outOrgId"].value=format(orgid)+orgid;
	if(orginid != "")
	document.tranAF.elements["inOrgId"].value=format(orginid)+orginid;
	if(orginname != ""&&orginid != ""){

		document.tranAF.elements["inOrgId"].disabled="true";
		document.all.orgButton.disabled="true";
	}
	var counts=document.all.counts.value;
	
	var pdpb1=0;
	var pdpb2=0;
	
	for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
			if(document.all.item(i).value=="批量导出"){
				s1=i;
			}
			if(document.all.item(i).value=="批量导入"){
				s2=i;
			}
			if(document.all.item(i).value=="添加"){
				s3=i;
			}
			if(document.all.item(i).value=="删除"){
				s4=i;
			}
			if(document.all.item(i).value=="全部删除"){
				s5=i;
			}
			if(document.all.item(i).value=="完成转出"){
				s6=i;
			}
			if(document.all.item(i).value=="登记"){
				s11=i;
			}
    <security:orghave>
			if(typetran==2){
			if(document.all.item(i).value=="提取单位版数据"){
				s7=i;
			}
			pdpb2=11;
			}
			if(typetran==1){
			if(document.all.item(i).value=="提交数据"){
				s8=i;
			}
			if(document.all.item(i).value=="撤销提交数据"){
				s9=i;
			}
			pdpb1=11;
			}
    </security:orghave>
		}
	}
	
	if(orgid=="" && orgname==""){
  		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="true";
		document.all.item(s4).disabled="true";
		document.all.item(s5).disabled="true";
	    document.all.item(s6).disabled="true";
	    document.all.item(s11).disabled="true";
    <security:orghave>
		if(typetran==2){
		if(pdpb2=='11'){
			document.all.item(s7).disabled="true";
		}
		}
	    if(counts==0){
		if(typetran==1){
		if(pdpb1=='11'){
			document.all.item(s8).disabled="true";
	        document.all.item(s9).disabled="true";
		}
		}
	    }
    </security:orghave>
	}else if(tranStatus != ""){
  		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="";
		document.all.item(s4).disabled="true";
		document.all.item(s5).disabled="true";
	    document.all.item(s6).disabled="true";
	    document.all.item(s11).disabled="true";
	    if(counts==0){
    <security:orghave>
		if(typetran==1){
		if(pdpb1=='11'){
			document.all.item(s8).disabled="true";
	        document.all.item(s9).disabled="true";
		}
		}
    </security:orghave>
	    }
	}else if(counts==0){
  		document.all.item(s1).disabled="";
		document.all.item(s2).disabled="";
		document.all.item(s3).disabled="";
		document.all.item(s4).disabled="true";
		document.all.item(s5).disabled="true";
	    document.all.item(s6).disabled="true";
	    document.all.item(s11).disabled="true";
    <security:orghave>
		if(typetran==1){
		if(pdpb1=='11'){
			document.all.item(s8).disabled="true";
	        document.all.item(s9).disabled="true";
		}
		}
    </security:orghave>
	}else if(counts>0){
    <security:orghave>
		if(typetran==2){
		if(pdpb2=='11'){
			document.all.item(s7).disabled="true";
		}
		}
    </security:orghave>
  		document.all.item(s1).disabled="true";
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="";
		document.all.item(s4).disabled="";
		document.all.item(s5).disabled="";
	    document.all.item(s6).disabled="true";
		document.tranAF.elements["inOrgId"].disabled="true";
		document.all.orgButton.disabled="true";
	    if(typetran==1){
		if(pdpb1=='11'){
		alert(statetype);
		 if(statetype=="未提取"){
		   document.all.item(s8).disabled="true";
		   document.all.item(s9).disabled="";}
		   if(statetype=="已提取"){
		   document.all.item(s8).disabled="true";
		   document.all.item(s9).disabled="true";}
		   if(statetype=="未提交"){
		   document.all.item(s8).disabled="";
		   document.all.item(s9).disabled="true";}
		   }
		}
	} 

}
  
  
  function executeAjax() { 
        var queryString = "tran_FindAAC.do?";  
        var id = document.tranAF.elements["outOrgId"].value;   
        if(id==""){
        	gotoOrg('2','0');
        }
        queryString = queryString + "id="+id; 	   
	    findInfo(queryString);  
	
  }
  // 转入单位
  function executeAjaxIn(){
    var queryString = "tran_FindImpOrgNameAAC.do?";  
        var id = document.tranAF.elements["inOrgId"].value;   
        var lin = document.tranAF.elements["outOrgId"].value;
        if(id==""){
        	gotoOrgNo('2','7');
        }  
        queryString = queryString + "inOrgId="+id; 	     
	    findInfo(queryString); 
  }



// 转出单位名称及票据号
function displays(id,name,note_Num,str,f,yg){
	if((str!="")&&(f==""))
  {
  	alert(str);
  }
  	if((str=="")&&(f!=""))
  {
  	alert('该单位存在未记账的提取业务！');
  }
  	if((str!="")&&(f!=""))
  {
  	alert('该单位存在未记账的错账调整和提取业务！');
  }
	if(yg!="a"){
		alert('该单位所在的归集银行日结日期与登陆日期不符，不允许做业务！');
		document.tranAF.elements["outOrgId"].value="";
		return false;
	}
  document.tranAF.elements["id"].value=formatTen(id)+id;
  document.tranAF.elements["name"].value=name;
  // document.tranAF.elements["note_Num"].value=note_Num;   
  
  showlist() ;
   
}
// 转入单位名称
function displays2(inorgid,inorgname,str){
	if(str != ""){
		alert(str);
		document.tranAF.elements["inOrgId"].value=""; 
	    document.tranAF.elements["inOrgName"].value="";
		document.tranAF.elements["inOrgId"].focus();
	}else{
	  document.tranAF.elements["inOrgId"].value=formatTen(inorgid)+inorgid;  
	  document.tranAF.elements["inOrgName"].value=inorgname;
	  
        var id = document.tranAF.elements["inOrgId"].value;   
        var lin = document.tranAF.elements["outOrgId"].value;
        if(id==""){
        	gotoOrgNo('2','7');
        }
  }
}

function showlist() {
  document.Form1.submit();
}


// 删除提示
function remove()
{
  var x = confirm("是否删除该记录!!");
  if(!x){
     return false;
    }
}
// 删除提示
function removeAll()
{
  var x = confirm("是否删除所有记录!!");
  if(!x){
     return false;
    }
 }

function gotoOver(){
	
	if(confirm("是否打印转出凭证？")){
		document.all.report.value="print";
	}else{
		document.all.report.value="noprint";
	}
     var inOrgId=document.tranAF.elements["inOrgId"].value;
     var inOrgName=document.tranAF.elements["inOrgName"].value;
      var noteNum=document.tranAF.elements["noteNumber"].value;

 	document.all.inid.value=inOrgId;
 	document.all.orgInName.value=inOrgName;
 	document.all.noteNum.value=noteNum;
	
}
function gotoImports(){

  var inOrgId=document.tranAF.elements["inOrgId"].value;
     var inOrgName=document.tranAF.elements["inOrgName"].value;
      var noteNum=document.tranAF.elements["noteNumber"].value;
	
 	document.all.inid.value=inOrgId;
 	document.all.orgInName.value=inOrgName;
 	document.all.noteNum.value=noteNum;
	
}
function gotoSave(){
	document.all.noteNum.value=document.tranAF.noteNumber.value;
	document.all.inid.value=document.tranAF.inOrgId.value;
}
function gotoEnter1(){
	if(event.keyCode==13){
		event.keyCode = 9;
		executeAjax();
	}
}
function gotoEnter2(){
	if(event.keyCode==13){
		event.keyCode = 9;
		executeAjaxIn();
	}
}
function gotoOrg(status,indexs){
	gotoOrgpop(status,'<%=path%>',indexs);
}
function gotoOrgNo(status,indexs){
	gotoOrgpopNo(status,'<%=path%>','7');
}

function exports(){

      var inOrgId=document.tranAF.elements["inOrgId"].value;
      var inOrgName=document.tranAF.elements["inOrgName"].value;
      var noteNum=document.tranAF.elements["noteNumber"].value;

 	  var outOrgId=document.tranAF.elements["outOrgId"].value;
      var outOrgname=document.tranAF.elements["outOrgname"].value;
	  outOrgname=encodeURI(outOrgname);
	  // inOrgName=encodeURI(inOrgName);
      outOrgname=encodeURI(outOrgname);
     // inOrgName=encodeURI(inOrgName);
 	document.all.inid.value=inOrgId;
    document.all.orgInName.value=inOrgName;
 	document.all.noteNum.value=noteNum;
	
	window.open ('<%=path%>/syscollection/tranmng/tranout/tranoutexport_array.jsp?inOrgId='+ inOrgId +'&noteNum='+noteNum+'&outOrgId='+outOrgId+'&outOrgname='+outOrgname+'&inOrgName='+inOrgName,'newwindow','height=190,width=350,top='+(window.screen.availHeight-190)/2+',left='+(window.screen.availWidth-350)/2+',toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
	return false;
}
function sun(borrowerName,cardNum){
	borrowerName =encodeURI(borrowerName);
	if(borrowerName.indexOf("_")>0){
		borrowerName=borrowerName+"_"+document.all.outOrgId.value;
	}
	window.open ('<%=path%>/sysloan/showEmpAccountPopListAC.do?borrowerName='+borrowerName+'&cardNum='+cardNum,'window','height=450,width=700,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',scrollbars=yes, status=yes');
	return false;
}
function sun1(borrowerName,cardNum){
	borrowerName =encodeURI(borrowerName);
	if(borrowerName.indexOf("_")>0){
		borrowerName=borrowerName+"_"+document.all.inOrgId.value;
	}
	window.open ('<%=path%>/sysloan/showEmpAccountPopListAC.do?borrowerName='+borrowerName+'&cardNum='+cardNum,'window','height=450,width=700,top='+(window.screen.availHeight-450)/2+',left='+(window.screen.availWidth-700)/2+',scrollbars=yes, status=yes');
	return false;
}
</script>
<body bgcolor="#FFFFFF" text="#000000" onload="loads();">
	<jsp:include page="../../../inc/sort.jsp">
		<jsp:param name="url" value="tran_showAC.do" />
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
										valign="top" style="PADDING-top: 7px">
										办理转出
									</td>
									<td width="112" height="37"
										background="<%=path%>/img/buttong.gif" align="center"
										style="PADDING-top: 7px" valign="top">
										<a href="tranoutTbForwardURLAC.do" class=a2>转出维护</a>
									</td>
								</tr>
							</table>
						</td>
						<td background="<%=path%>/img/table_bg_line.gif" align="right">
							<table width="300" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="37">
										<img src="<%=path%>/img/title_banner.gif" width="37"
											height="24">
									</td>
									<td class=font14 bgcolor="#FFFFFF" align="center"
										valign="bottom">
										<font color="00B5DB">转入转出<font color="00B5DB">&gt;&gt;办理转出</font>
									</td>
									<td width="15" class=font14>
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
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					align="center">
					<tr>
						<td height="35">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="22" bgcolor="#CCCCCC" align="center" width="117">
										<b class="font14">转 出 信 息</b>
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
				<html:form action="/tran_showAC.do" style="margin: 0"
					focus="outOrgId">
					<table border="0" width="95%" id="table1" cellspacing=1
						cellpadding=3 align="center">
						<tr>
							<td class="td1" width="17%">
								转出单位编号
							</td>
							<td width="22%">
								<logic:equal name="tranAF" property="type" value="0">
									<html:text name="tranAF" property="outOrgId" readonly="true"
										styleClass="input3" />
									<input type="hidden" name="types"
										value='<bean:write name="tranAF" property="type" />'>
								</logic:equal>
								<logic:notEqual name="tranAF" property="type" value="0">
									<html:text name="tranAF" property="outOrgId"
										ondblclick="executeAjax();" onkeydown="gotoEnter1()"
										styleClass="input3" />
									<input type="hidden" name="types"
										value='<bean:write name="tranAF" property="type" />'>
								</logic:notEqual>
							</td>
							<td width="11%">
								<logic:equal name="tranAF" property="type" value="0">
									<input type="button" name="submit4" value="..." class="buttona"
										disabled="disabled">
								</logic:equal>
								<logic:notEqual name="tranAF" property="type" value="0">
									<input type="button" name="submit4" value="..." class="buttona"
										onclick="gotoOrg('2','0');">
								</logic:notEqual>
							</td>
							<td width="17%" class="td1">
								转出单位名称
							</td>
							<td width="33%">
								<html:text name="tranAF" property="outOrgname"
									styleClass="input3" readonly="true" />
							</td>
						</tr>
						<tr>
							<td class="td1" width="17%">
								转入单位编号
							</td>
							<td width="22%">
								<html:text name="tranAF" property="inOrgId"
									ondblclick="executeAjaxIn();" onkeydown="gotoEnter2()"
									styleClass="input3" />
							</td>
							<td width="11%">
								<html:button property="orgButton" styleClass="buttona"
									onclick="gotoOrgNo('2','7');">...</html:button>
							</td>
							<td width="17%" class="td1">
								转入单位名称
							</td>
							<td width="33%">
								<html:text name="tranAF" property="inOrgName"
									styleClass="input3" readonly="true" />
							</td>
						</tr>
						<tr>
							<td class="td1" width="17%">
								结算号
							</td>
							<td colspan="2">
								<html:text name="tranAF" property="noteNumber"
									styleClass="input3" />
							</td>
							<td width="17%">
								&nbsp;
							</td>
							<td width="33%">
								&nbsp;
								<html:hidden name="tranAF" property="tranStatus" />
							</td>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td class=h4>
								合计：人数
								<u>： <bean:write name="pagination" property="nrOfElements" />
								</u>转出金额
								<u> ：<bean:write name="tranAF" property="sumBalance" /> </u>
								<security:orgcannot>利息
								<u> ：<bean:write name="tranAF" property="sumInterest" /> </u>转出总额
								<u> ：<bean:write name="tranAF" property="sumMoney" /> </u>
								</security:orgcannot>

							</td>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="117">
											<b class="font14">办理转出列表</b>
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
					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr bgcolor="1BA5FF">
							<td align="center" height="6" colspan="1"></td>
						</tr>
					</table>
				</html:form>
				<html:form action="/tran_MaintainAC.do" style="margin: 0">
					<table width="95%" border="0" bgcolor="#76BEE9" cellspacing="1"
						cellpadding="3" align="center">
						<tr>
							<td align="center" bgcolor="C4F0FF">
								<input type="hidden" name="report" value="">
								&nbsp;
							</td>

							<td align="center" class=td2>
								<a href="javascript:sort('tranOutTail.empId')">职工编号</a>
								<logic:equal name="pagination" property="orderBy"
									value="tranOutTail.empId">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>

							<td align="center" class=td2>
								<a href="javascript:sort('tranOutTail.empName')">职工姓名</a>
								<logic:equal name="pagination" property="orderBy"
									value="tranOutTail.empName">
									<logic:equal name="pagination" property="orderother"
										value="ASC">▲</logic:equal>
									<logic:equal name="pagination" property="orderother"
										value="DESC">▼</logic:equal>
								</logic:equal>
							</td>

							<td align="center" class=td2>
								证件号码
							</td>
							<td align="center" class=td2>
								转出金额
							</td>
							<security:orgcannot>
								<td align="center" class=td2>
									利息
								</td>
								<td align="center" class=td2>
									<a href="javascript:sort('tranOutTail.sumMoney')">转出总额</a>
									<logic:equal name="pagination" property="orderBy"
										value="tranOutTail.sumMoney">
										<logic:equal name="pagination" property="orderother"
											value="ASC">▲</logic:equal>
										<logic:equal name="pagination" property="orderother"
											value="DESC">▼</logic:equal>
									</logic:equal>
								</td>
							</security:orgcannot>
							<td align="center" class=td2>
								转移原因
							</td>
							<td align="center" class=td2>
								转入职工编号
							</td>
						</tr>
						<logic:notEmpty name="tranAF" property="list">
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate name="tranAF" property="list" id="element"
								indexId="i">
								<%
											j++;
											if (j % 2 == 0) {
												strClass = "td8";
											} else {
												strClass = "td9";
											}
								%>
								<tr id="tr<%=i%>" onclick='gotoClickpp("<%=i%>",idAF);'
									onMouseOver='this.style.background="#eaeaea"'
									onMouseOut='gotoColorpp("<%=i%>",idAF);' class="<%=strClass%>">
									<td valign="top">
										<p align="left">
											<input id="s<%=i%>" type="radio" name="id"
												value="<bean:write name="element" property="id"/>"
												<%if(new Integer(0).equals(i)) out.print("checked"); %>>
										</p>
									</td>
									<td>
										<p>
											<a href="#"
												onclick="return sun('<bean:write name="element" property="empName" />_<bean:write name="element" property="empId"/>','<bean:write name="element" property="emp.empInfo.cardNum" />');">
												<bean:write name="element" property="empId" format="000000" />
											</a>
									</td>
									<td>
										<bean:write name="element" property="empName" />
									</td>
									<td>
										<bean:write name="element" property="emp.empInfo.cardNum" />
									</td>
									<td>
										<bean:write name="element" property="sumBalance" />
									</td>
									<security:orgcannot>
										<td>
											<bean:write name="element" property="sumInterest" />
										</td>
										<td>
											<bean:write name="element" property="sumMoney" />
										</td>
										<td>
											<bean:write name="element" property="tranReason" />
										</td>
									</security:orgcannot>
									<logic:notEqual name="element" property="tranin_empid"
										value="0">
										<td>
											<p>
												<a href="#"
													onclick="return sun1('aaa_<bean:write name="element" property="tranin_empid" />','<bean:write name="element" property="reserveaC" />');">
													<bean:write name="element" property="tranin_empid" /> </a>
										</td>
									</logic:notEqual>
									<logic:equal name="element" property="tranin_empid" value="0">
										<td>
											&nbsp;
										</td>
									</logic:equal>
								</tr>
								<input type="hidden" name="tranStatus"
									value="<bean:write name="tranAF" property="tranStatus" />">
							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="tranAF" property="list">
							<tr>
								<td colspan="9" height="30" style="color:red">
									没有找到与条件相符合结果！
								</td>
							</tr>
						</logic:empty>
					</table>

					<table width="95%" border="0" cellspacing="0" cellpadding="3"
						align="center">
						<tr>
							<td>
								<input type="Hidden" name="outid"
									value="<bean:write name="tranAF" property="outOrgId"/>">
								<input type="Hidden" name="inid" value="">
								<input type="Hidden" name="headid"
									value="<bean:write name="tranAF" property="headId"/>">
								<input type="Hidden" name="counts"
									value="<bean:write name="pagination" property="nrOfElements"/>">
								<input type="Hidden" name="orgInName" value="">
								<input type="Hidden" name="noteNum" value="">
								<input type="Hidden" name="orgOutName"
									value="<bean:write name="tranAF" property="outOrgname"/>">
								<input type="Hidden" name="pkid"
									value="<bean:write name="tranAF" property="pkid"/>" />
								<logic:equal name="tranAF" property="type" value="0">
									<input type="hidden" name="types"
										value='<bean:write name="tranAF" property="type" />'>
								</logic:equal>
								<logic:equal name="tranAF" property="type" value="1">
									<input type="hidden" name="types"
										value='<bean:write name="tranAF" property="type" />'>
								</logic:equal>
							</td>
						</tr>
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
											<jsp:include page="/inc/pagination.jsp">
												<jsp:param name="url" value="tran_showAC.do" />
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
										<security:orghave>
											<security:orgcannot>
												<td>
													<html:submit property="method" styleClass="buttonc">
														<bean:message key="button.pickup.data" />
													</html:submit>
												</td>
											</security:orgcannot>
										</security:orghave>
										<td>
											<html:submit property="method" styleClass="buttona"
												onclick="return exports()">
												<bean:message key="button.exports" />
											</html:submit>
										</td>
										<td>
											<html:submit property="method" styleClass="buttona"
												onclick="return gotoImports()">
												<bean:message key="button.imports" />
											</html:submit>
										</td>
										<td>
											<html:submit property="method" styleClass="buttona"
												onclick="return gotoSave()">
												<bean:message key="button.add" />
											</html:submit>
										</td>
										<td>
											<html:submit property="method" styleClass="buttona"
												onclick="return remove()">
												<bean:message key="button.delete" />
											</html:submit>
										</td>
										<td>
											<html:submit property="method" styleClass="buttona"
												onclick="return removeAll()">
												<bean:message key="button.deleteall" />
											</html:submit>
										</td>
										<td>
											<html:submit property="method" styleClass="buttona"
												onclick="return gotoOver()">
												<bean:message key="button.over.tranout" />
											</html:submit>
										</td>
										<td>
											<html:submit property="method" styleClass="buttona">
												<bean:message key="button.dengji.yangg" />
											</html:submit>
										</td>
										<security:orghave>
											<security:orgcan>
												<td>
													<html:submit property="method" styleClass="buttona">
														<bean:message key="button.referring.data" />
													</html:submit>
												</td>
											</security:orgcan>
										</security:orghave>
										<security:orghave>
											<security:orgcan>
												<td>
													<html:submit property="method" styleClass="buttonc">
														<bean:message key="button.pproval.data" />
													</html:submit>
												</td>
											</security:orgcan>
										</security:orghave>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</html:form>
				<form action="tran_showAC.do" method="POST" name="Form1" id="Form1">
				</form>
			</td>
		</tr>
	</table>
</body>
</html:html>

<script type="text/javascript">

function s(status){
	gotoOrgpop(status,'<%=path%>');
}

</script>
