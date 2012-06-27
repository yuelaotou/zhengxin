<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page import="org.xpup.hafmis.sysfinance.bookmng.subject.action.SubjectTbShowAC" %>
<%
   Object pagination= request.getSession(false).getAttribute(SubjectTbShowAC.PAGINATION_KEY);
   request.setAttribute("pagination",pagination);
   String path = request.getContextPath();
   List subjectList=(List)request.getAttribute("subjectList");
 %>
<%@ page import="java.util.List"%>
<%@ page import="org.xpup.hafmis.sysfinance.bookmng.subject.dto.SubjectDTO"%>
<%@ page import="java.util.ArrayList"%>
<html:html>
 <head>
	<title>财务系统-会计科目-资产类</title>
</head>
<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css" />
<script src="<%=path%>/js/common.js"></script>
<script>
var s1="";
var s2="";
var s3="";
var s4="";
  function reportErrors(){
  
  	for(i=0;i<document.all.length;i++){
		if(document.all.item(i).type=="submit"){
			if(document.all.item(i).value=="添加"){
				s1=i;
			}
			if(document.all.item(i).value=="删除"){
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
	document.all.item(s2).disabled="true";
	document.all.item(s3).disabled="true";
	document.all.item(s4).disabled="true";
  
	<logic:messagesPresent>
	var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
	alert(message);
	</logic:messagesPresent>
	}

function checkradio(i){
	if(i==0){
		document.all.item(s1).disabled="";
		document.all.item(s2).disabled="";
		document.all.item(s3).disabled="";
		document.all.item(s4).disabled="true";
  	}
  	if(i==1){
		document.all.item(s1).disabled="";
		document.all.item(s2).disabled="true";
		document.all.item(s3).disabled="true";
		document.all.item(s4).disabled="";
  	}  
  		
}

function is_delete(){
	var id=document.getElementsByName("radioname");
	for(var i=0;i<id.length;i++){
		if(id[i].checked){
			var x=confirm("确定删除该科目?");
			if(x){ 
		  		return true;
			}else{
		 	 	return false;
			}
		}
	}
	alert("请选择要删除的科目！！");
	return false;
}

function is_canceled(){
	var id=document.getElementsByName("radioname");
	for(var i=0;i<id.length;i++){
		if(id[i].checked){
			var x=confirm("确定作废该科目?");
			if(x){ 
		  		return true;
			}else{
		 	 	return false;
			}
		}
	}
	alert("请选择要作废的科目！！");
	return false;
}

function is_canceledquash(){
	var id=document.getElementsByName("radioname");
	for(var i=0;i<id.length;i++){
		if(id[i].checked){
			var x=confirm("确定要撤销作废?");
			if(x){ 
		  		return true;
			}else{
		 	 	return false;
			}
		}
	}
	alert("请选择要撤销作废的科目！！");
	return false;
}

function hide(id){
	var arr=document.subjectShowAF.elements;
	for(var i=0;i<arr.length;i++){
		if(arr[i].id.indexOf(id)==0&&arr[i].id!=id){
			showOrHide(id,arr[i].id,document.getElementById("hid"+id).name);
		}
	}
}

function showOrHide(sid,id,parent_level){
	var obj=document.getElementById("tr"+id);
	var hid=document.getElementById("hid"+id).name;
	
	if(obj.style.display=="none"&&parseInt(hid)==parseInt(parent_level)+1){
		obj.style.display="";
		document.getElementById("img"+id).src="<%=path %>/img/fold.gif";
		document.getElementById("img"+sid).src="<%=path %>/img/open.gif";
	}else{
		obj.style.display="none";
		if(parseInt(hid)==parseInt(parent_level)+1){
			document.getElementById("img"+sid).src="<%=path %>/img/fold.gif";
	  	}
	}
}

</script>
<body bgcolor="#FFFFFF" text="#000000" onLoad="reportErrors();" onContextmenu="return false">
<html:form action="/subjectMaintainAC">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td>     
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="7"><img src="<%=path %>/img/table_left.gif" width="7" height="37"></td>
          <td background="<%=path %>/img/table_bg_line.gif"></td>
          <td background="<%=path %>/img/table_bg_line.gif">
              <table border="0" cellspacing="0" cellpadding="0">
                <tr> 
                  <td width="112" height="37" background="<%=path %>/img/buttong.gif" align="center" valign="top"  style="PADDING-top: 7px"><a href="subjectTaForwardAC.do" class=a2>资产</a></td>
                  <td width="112" height="37" background="<%=path %>/img/buttonbl.gif" align="center"   style="PADDING-top: 7px" valign="top">负债</td>
                  <td width="112" height="37" background="<%=path %>/img/buttong.gif" align="center"   style="PADDING-top: 7px" valign="top"><a href="subjectTcForwardAC.do" class=a2>权益</a></td>
                  <td width="112" height="37" background="<%=path %>/img/buttong.gif" align="center"   style="PADDING-top: 7px" valign="top"><a href="subjectTdForwardAC.do" class=a2>成本</a></td>
                  <td width="112" height="37" background="<%=path %>/img/buttong.gif" align="center"   style="PADDING-top: 7px" valign="top"><a href="subjectTeForwardAC.do" class=a2>损益</a></td>
                </tr>
              </table>
           </td>
           <td background="<%=path %>/img/table_bg_line.gif" align="right"> 
            <table width="300" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="37"><img src="<%=path %>/img/title_banner.gif" width="37" height="24"></td>
                <td width="148" class=font14 bgcolor="#FFFFFF" align="center" valign="bottom"><font color="00B5DB">账套管理&gt;会计科目</font></td>
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
    <td class=td3 height="7" style="line-height:150%"> 
        
  <%
  		
  		List rank1=new ArrayList();
  		List rank2=new ArrayList();
  		List rank3=new ArrayList();
  		List rank4=new ArrayList();
  		List rank5=new ArrayList();
  		List rank6=new ArrayList();
  		for(int i=0;i<subjectList.size();i++){
  			SubjectDTO subject=(SubjectDTO)subjectList.get(i);
  			switch(Integer.parseInt((String)subject.getSubjectLevel())){
  				case 1:
  				rank1.add(subject);
  				break;
  				case 2:
  				rank2.add(subject);
  				break;
  				case 3:
  				rank3.add(subject);
  				break;
  				case 4:
  				rank4.add(subject);
  				break;
  				case 5:
  				rank5.add(subject);
  				break;
  				case 6:
  				rank6.add(subject);
  				break;
  				default:
  				continue;
  			}
  		}
  %>
  <TABLE width="95%" border="0" cellspacing="1" cellpadding="1" align="center">
  <%	
  		SubjectDTO subject1=null;
  		SubjectDTO subject2=null;
  		SubjectDTO subject3=null;
  		SubjectDTO subject4=null;
  		SubjectDTO subject5=null;
  		SubjectDTO subject6=null;
  		for(int i=0;i<rank1.size();i++){
  			subject1=new SubjectDTO();
  			subject1=(SubjectDTO)rank1.get(i);
  			%>
  			<tr id='tr<%=i %>' >
	        <td>
  				<input type="hidden" id="hid<%=i%>" name="1">
  				<a onclick="hide('<%=i %>');"><img id="img<%=i%>" src="<%=path %>/img/fold.gif"/></a>
  				<INPUT id='<%=i%>' type="radio" name="radioname" value="<%=subject1.getSubjectId() %>" onclick="return checkradio(<%=subject1.getSubjectSt() %>);" >
  				<a href="#" onclick="document.URL='subjectModifyAC.do?sortcodeflag=1&subjectId=<%=subject1.getSubjectId() %>'" class="font14"><%=subject1.getSubjectCode() %>&nbsp;<%=subject1.getSubjectName() %></a>
  			</td>
  			</tr>
  			<%for(int j=0;j<rank2.size();j++){
  				subject2=new SubjectDTO();
  				subject2=(SubjectDTO)rank2.get(j);
  				if(subject2.getParentSubjectCode().equals(subject1.getSubjectCode())){
  					%>
  					<tr id='tr<%=i+"_"+j%>' style="display:none" >
  					  <td>&nbsp;&nbsp;&nbsp;&nbsp;
  					  	<input type="hidden" id="hid<%=i+"_"+j%>" name="2">
  						<a onclick="hide('<%=i+"_"+j%>')"><img id="img<%=i+"_"+j%>" src="<%=path %>/img/fold.gif"/></a>
		  				<INPUT id='<%=i+"_"+j%>' type="radio" name="radioname" value="<%=subject2.getSubjectId() %>" onclick="return checkradio(<%=subject2.getSubjectSt() %>);" >
		  				<a href="#" onclick="document.URL='subjectModifyAC.do?sortcodeflag=1&subjectId=<%=subject2.getSubjectId() %>'" class="font14"><%=subject2.getSubjectCode() %>&nbsp;<%=subject2.getSubjectName() %></a>
		  			  </td>
		  			</tr>
  					<%for(int k=0;k<rank3.size();k++){
  						subject3=new SubjectDTO();
  						subject3=(SubjectDTO)rank3.get(k);
  						if(subject3.getParentSubjectCode().equals(subject2.getSubjectCode())){
  							%>
  							<tr id='tr<%=i+"_"+j+"-"+k%>' style="display:none" >
				  			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  					  			<input type="hidden" id="hid<%=i+"_"+j+"-"+k%>" name="3">
  								<a onclick="hide('<%=i+"_"+j+"-"+k%>')"><img id="img<%=i+"_"+j+"-"+k%>" src="<%=path %>/img/fold.gif"/></a>
  								<INPUT id='<%=i+"_"+j+"-"+k%>' type="radio" name="radioname" value="<%=subject3.getSubjectId() %>" onclick="return checkradio(<%=subject3.getSubjectSt() %>);" >
				  				<a href="#" onclick="document.URL='subjectModifyAC.do?sortcodeflag=1&subjectId=<%=subject3.getSubjectId() %>'" class="font14"><%=subject3.getSubjectCode() %>&nbsp;<%=subject3.getSubjectName() %></a>
				  			</td>
				  			</tr>
				  			<%for(int m=0;m<rank4.size();m++){
				  				subject4=new SubjectDTO();
				  				subject4=(SubjectDTO)rank4.get(m);
				  				if(subject4.getParentSubjectCode().equals(subject3.getSubjectCode())){
				  				%>
	  							<tr id='tr<%=i+"_"+j+"-"+k+"_"+m%>' style="display:none" >
					  			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  					  				<input type="hidden" id="hid<%=i+"_"+j+"-"+k+"_"+m%>" name="4">
  									<a onclick="hide('<%=i+"_"+j+"-"+k+"_"+m%>')"><img id="img<%=i+"_"+j+"-"+k+"_"+m%>" src="<%=path %>/img/fold.gif"/></a>
					  				<INPUT id='<%=i+"_"+j+"-"+k+"_"+m%>' type="radio" name="radioname" value="<%=subject4.getSubjectId() %>" onclick="return checkradio(<%=subject4.getSubjectSt() %>);" >
					  				<a href="#" onclick="document.URL='subjectModifyAC.do?sortcodeflag=1&subjectId=<%=subject4.getSubjectId() %>'" class="font14"><%=subject4.getSubjectCode() %>&nbsp;<%=subject4.getSubjectName() %></a>
					  			</td>
					  			</tr>
					  			<%for(int n=0;n<rank5.size();n++){
					  				subject5=new SubjectDTO();
					  				subject5=(SubjectDTO)rank5.get(n);
					  				if(subject5.getParentSubjectCode().equals(subject4.getSubjectCode())){
					  				%>
		  							<tr id='tr<%=i+"_"+j+"-"+k+"_"+m+"-"+n%>' style="display:none" >
						  			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  					  					<input type="hidden" id="hid<%=i+"_"+j+"-"+k+"_"+m+"-"+n%>" name="5">
  										<a onclick="hide('<%=i+"_"+j+"-"+k+"_"+m+"-"+n%>')"><img id="img<%=i+"_"+j+"-"+k+"_"+m+"-"+n%>" src="<%=path %>/img/fold.gif"/></a>
						  				<INPUT id='<%=i+"_"+j+"-"+k+"_"+m+"-"+n%>' type="radio" name="radioname" value="<%=subject5.getSubjectId() %>" onclick="return checkradio(<%=subject5.getSubjectSt() %>);" >
						  				<a href="#" onclick="document.URL='subjectModifyAC.do?sortcodeflag=1&subjectId=<%=subject5.getSubjectId() %>'" class="font14" ><%=subject5.getSubjectCode() %>&nbsp;<%=subject5.getSubjectName() %></a>
						  			</td>
						  			</tr>
						  			<%for(int h=0;h<rank6.size();h++){
						  				subject6=new SubjectDTO();
						  				subject6=(SubjectDTO)rank6.get(h);
						  				if(subject6.getParentSubjectCode().equals(subject5.getSubjectCode())){
						  				%>
			  							<tr id='tr<%=i+"_"+j+"-"+k+"_"+m+"-"+n+"_"+h%>' style="display:none" >
							  			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  					  						<input type="hidden" id="hid<%=i+"_"+j+"-"+k+"_"+m+"-"+n+"_"+h%>" name="6">
  											<a onclick="hide('<%=i+"_"+j+"-"+k+"_"+m+"-"+n+"_"+h%>')"><img id="img<%=i+"_"+j+"-"+k+"_"+m+"-"+n+"_"+h%>" src="<%=path %>/img/fold.gif"/></a>
							  				<INPUT id='<%=i+"_"+j+"-"+k+"_"+m+"-"+n+"_"+h%>' type="radio" name="radioname" value="<%=subject6.getSubjectId() %>" onclick="return checkradio(<%=subject6.getSubjectSt() %>);" >
							  				<a href="#" onclick="document.URL='subjectModifyAC.do?sortcodeflag=1&subjectId=<%=subject6.getSubjectId() %>'" class="font14"><%=subject6.getSubjectCode() %>&nbsp;<%=subject6.getSubjectName() %></a>
							  			</td>
							  			</tr>
  												<%
  												}
  											}
  										}
  									}
  								}
  							}
  						}
  					}
  				}
  			}
  		}
  %>
  </TABLE>
        
	</td>
  </tr>
  <tr>
    <td class=td3 height="8">
        <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
          <tr valign="bottom"> 
            <td  colspan="7" bgcolor="#FFFFFF" align="center" height="30"> 
              <table border="0" cellspacing="0" cellpadding="0">
                <tr> 
                <html:hidden name="subjectShowAF" property="sortcodeflag" />
                  <td width="70" height="40"> 
                	<html:submit property="method" styleClass="buttona" ><bean:message key="button.add"/></html:submit>
                  </td>
                  <td width="70"> 
                	<html:submit property="method" styleClass="buttona" onclick="return is_delete();"><bean:message key="button.delete"/></html:submit>
                  </td>
                  <td width="70"> 
                	<html:submit property="method" styleClass="buttona" onclick="return is_canceled();"><bean:message key="button.canceled"/></html:submit>
                  </td>
                  <td width="70"> 
                	<html:submit property="method" styleClass="buttona" onclick="return is_canceledquash();"><bean:message key="button.canceled.quash"/></html:submit>
                  </td>
                </tr>
              </table>
            </td>
          </tr>
        </table>
    </td>
  </tr>
  
</table>
</html:form>
</body>
</html:html>