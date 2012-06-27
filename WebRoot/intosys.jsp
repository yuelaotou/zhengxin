<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%String path=request.getContextPath();%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title>金软科技有限公司</title>
  </head>
    <script type="text/javascript">
      function intosys(){     
      
         window.opener=null;
         window.close();
      }
      
      function toLogin(){
         var ww  = window.screen.width;   
         var hh  = window.screen.height-85;   
         //window.showModalDialog("<%=path%>/login.jsp","住房公积金干里系统","dialogWidth:"+ww+"1280px;dialogHeight:"+hh+";center:yes"); 
         document.URL=parent.window.open("<%=path%>/login.jsp","住房公积金干里系统","toolbar=no ,scrollbars=no,status=yes,top=0,left=0,width="+ww+",height="+hh); 
      }
    </script>
  <body onload="intosys();" onunload="toLogin();">
    This is my JSP page. <br>
  </body>
</html>
