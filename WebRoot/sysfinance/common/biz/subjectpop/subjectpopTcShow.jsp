<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page import="org.xpup.hafmis.sysfinance.common.biz.subjectpop.action.SubjectpopTaShowAC" %>
<%
   Object pagination= request.getSession(false).getAttribute(SubjectpopTaShowAC.PAGINATION_KEY);
   request.setAttribute("pagination",pagination);
   String path = request.getContextPath();
   String indexs=(String)session.getAttribute("indexs");
   List subjectList=(List)request.getAttribute("subjectList");
 %>
<%@ page import="java.util.List"%>
<%@ page import="org.xpup.hafmis.sysfinance.bookmng.subject.dto.SubjectDTO"%>
<%@ page import="java.util.ArrayList"%>
<html:html>
 <head>
	<title>财务系统-会计科目-负债类</title>
</head>
<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css" />
<script src="<%=path%>/js/common.js"></script>
<script>
function dbfnqdValues(radioid){
	dbfnValues("<%=indexs%>",radioid);
}

function is_checked(){
	var id=document.getElementsByName("radioname");
	for(var i=0;i<id.length;i++){
		if(id[i].checked){
			fnqdValues("<%=indexs%>",null);
			return true;
		}
	}
	alert("请选择科目！！");
	return false;
}

function hide(id){
	var arr=document.subjectpopShowAF.elements;
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
<body bgcolor="#FFFFFF" text="#000000" onContextmenu="return false">
<html:form action="subjectpopTaShowAC">
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
                  <td width="112" height="37" background="<%=path %>/img/buttong.gif" align="center" valign="top"  style="PADDING-top: 7px"><a href="subjectpopTaShowAC.do" class=a2>资产</a></td>
                  <td width="112" height="37" background="<%=path %>/img/buttong.gif" align="center"   style="PADDING-top: 7px" valign="top"><a href="subjectpopTbShowAC.do" class=a2>负债</a></td>
                  <td width="112" height="37" background="<%=path %>/img/buttonbl.gif" align="center"   style="PADDING-top: 7px" valign="top">权益</td>
                  <td width="112" height="37" background="<%=path %>/img/buttong.gif" align="center"   style="PADDING-top: 7px" valign="top"><a href="subjectpopTdShowAC.do" class=a2>成本</a></td>
                  <td width="112" height="37" background="<%=path %>/img/buttong.gif" align="center"   style="PADDING-top: 7px" valign="top"><a href="subjectpopTeShowAC.do" class=a2>损益</a></td>
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
  			<tr id='tr<%=i %>' onDblClick='dbfnqdValues("<%=subject1.getSubjectCode() %>");'>
	        <td>
  				<input type="hidden" id="hid<%=i%>" name="1">
  				<a onclick="hide('<%=i %>');"><img id="img<%=i%>" src="<%=path %>/img/fold.gif"/></a>
  				<INPUT id='<%=i%>' type="radio" name="radioname" value="<%=subject1.getSubjectCode() %>" >
  				<font class="font14"><%=subject1.getSubjectCode() %>&nbsp;<%=subject1.getSubjectName() %></font>
  			</td>
  			</tr>
  			<%for(int j=0;j<rank2.size();j++){
  				subject2=new SubjectDTO();
  				subject2=(SubjectDTO)rank2.get(j);
  				if(subject2.getParentSubjectCode().equals(subject1.getSubjectCode())){
  					%>
  					<tr id='tr<%=i+"_"+j%>' style="display:none" onDblClick='dbfnqdValues("<%=subject2.getSubjectCode() %>");'>
  					  <td>&nbsp;&nbsp;&nbsp;&nbsp;
  					  	<input type="hidden" id="hid<%=i+"_"+j%>" name="2">
  						<a onclick="hide('<%=i+"_"+j%>')"><img id="img<%=i+"_"+j%>" src="<%=path %>/img/fold.gif"/></a>
		  				<INPUT id='<%=i+"_"+j%>' type="radio" name="radioname" value="<%=subject2.getSubjectCode() %>" >
		  				<font class="font14"><%=subject2.getSubjectCode() %>&nbsp;<%=subject2.getSubjectName() %></font>
		  			  </td>
		  			</tr>
  					<%for(int k=0;k<rank3.size();k++){
  						subject3=new SubjectDTO();
  						subject3=(SubjectDTO)rank3.get(k);
  						if(subject3.getParentSubjectCode().equals(subject2.getSubjectCode())){
  							%>
  							<tr id='tr<%=i+"_"+j+"-"+k%>' style="display:none" onDblClick='dbfnqdValues("<%=subject3.getSubjectCode() %>");'>
				  			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  					  			<input type="hidden" id="hid<%=i+"_"+j+"-"+k%>" name="3">
  								<a onclick="hide('<%=i+"_"+j+"-"+k%>')"><img id="img<%=i+"_"+j+"-"+k%>" src="<%=path %>/img/fold.gif"/></a>
  								<INPUT id='<%=i+"_"+j+"-"+k%>' type="radio" name="radioname" value="<%=subject3.getSubjectCode() %>" >
				  				<font class="font14"><%=subject3.getSubjectCode() %>&nbsp;<%=subject3.getSubjectName() %></font>
				  			</td>
				  			</tr>
				  			<%for(int m=0;m<rank4.size();m++){
				  				subject4=new SubjectDTO();
				  				subject4=(SubjectDTO)rank4.get(m);
				  				if(subject4.getParentSubjectCode().equals(subject3.getSubjectCode())){
				  				%>
	  							<tr id='tr<%=i+"_"+j+"-"+k+"_"+m%>' style="display:none" onDblClick='dbfnqdValues("<%=subject4.getSubjectCode() %>");'>
					  			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  					  				<input type="hidden" id="hid<%=i+"_"+j+"-"+k+"_"+m%>" name="4">
  									<a onclick="hide('<%=i+"_"+j+"-"+k+"_"+m%>')"><img id="img<%=i+"_"+j+"-"+k+"_"+m%>" src="<%=path %>/img/fold.gif"/></a>
					  				<INPUT id='<%=i+"_"+j+"-"+k+"_"+m%>' type="radio" name="radioname" value="<%=subject4.getSubjectCode() %>" >
					  				<font class="font14"><%=subject4.getSubjectCode() %>&nbsp;<%=subject4.getSubjectName() %></font>
					  			</td>
					  			</tr>
					  			<%for(int n=0;n<rank5.size();n++){
					  				subject5=new SubjectDTO();
					  				subject5=(SubjectDTO)rank5.get(n);
					  				if(subject5.getParentSubjectCode().equals(subject4.getSubjectCode())){
					  				%>
		  							<tr id='tr<%=i+"_"+j+"-"+k+"_"+m+"-"+n%>' style="display:none" onDblClick='dbfnqdValues("<%=subject5.getSubjectCode() %>");'>
						  			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  					  					<input type="hidden" id="hid<%=i+"_"+j+"-"+k+"_"+m+"-"+n%>" name="5">
  										<a onclick="hide('<%=i+"_"+j+"-"+k+"_"+m+"-"+n%>')"><img id="img<%=i+"_"+j+"-"+k+"_"+m+"-"+n%>" src="<%=path %>/img/fold.gif"/></a>
						  				<INPUT id='<%=i+"_"+j+"-"+k+"_"+m+"-"+n%>' type="radio" name="radioname" value="<%=subject5.getSubjectCode() %>" >
						  				<font class="font14"><%=subject5.getSubjectCode() %>&nbsp;<%=subject5.getSubjectName() %></font>
						  			</td>
						  			</tr>
						  			<%for(int h=0;h<rank6.size();h++){
						  				subject6=new SubjectDTO();
						  				subject6=(SubjectDTO)rank6.get(h);
						  				if(subject6.getParentSubjectCode().equals(subject5.getSubjectCode())){
						  				%>
			  							<tr id='tr<%=i+"_"+j+"-"+k+"_"+m+"-"+n+"_"+h%>' style="display:none" onDblClick='dbfnqdValues("<%=subject6.getSubjectCode() %>");'>
							  			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  					  						<input type="hidden" id="hid<%=i+"_"+j+"-"+k+"_"+m+"-"+n+"_"+h%>" name="6">
  											<a onclick="hide('<%=i+"_"+j+"-"+k+"_"+m+"-"+n+"_"+h%>')"><img id="img<%=i+"_"+j+"-"+k+"_"+m+"-"+n+"_"+h%>" src="<%=path %>/img/fold.gif"/></a>
							  				<INPUT id='<%=i+"_"+j+"-"+k+"_"+m+"-"+n+"_"+h%>' type="radio" name="radioname" value="<%=subject6.getSubjectCode() %>" >
							  				<font class="font14"><%=subject6.getSubjectCode() %>&nbsp;<%=subject6.getSubjectName() %></font>
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
                <html:hidden name="subjectpopShowAF" property="sortcodeflag" />
                  <td width="70" height="40"> 
                 	<input type="button" name="sure" value="确 定" class="buttona" onclick='return is_checked();'>
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