<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ include file="/checkUrl.jsp"%>
<%
String path = request.getContextPath();
String settingType=(String)request.getAttribute("settingType");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>中国人民银行导出数据_参数设置</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
	<script src="<%=path%>/js/common.js">
	</script>


<script language="javascript">
var oldColor="#ffffff";                            //原来的颜色
var newColor="#E8FCFD";                     //要变成的颜色
function chgBGColor(oTD){
	oldColor=oTD.style.backgroundColor;//保存当前颜色
	oTD.style.backgroundColor=newColor;//改变表格颜色
	newColor=oldColor;                 //改变下次要变成的颜色
}
function changeType(f){
var para=f;
if(para=="kh")
  {
document.all["kh"].style.display="inline";
document.all["khbutton"].style.display="inline";
document.all["qf"].style.display="none";
document.all["qfbutton"].style.display="none";
document.all["fc"].style.display="none";
document.all["fcbutton"].style.display="none";
  }else if(para=="qf")
  {
document.all["kh"].style.display="none";
document.all["khbutton"].style.display="none";
document.all["qf"].style.display="inline";
document.all["qfbutton"].style.display="inline";
document.all["fc"].style.display="none";
document.all["fcbutton"].style.display="none";
  }
  else
  {
document.all["kh"].style.display="none";
document.all["khbutton"].style.display="none";
document.all["qf"].style.display="none";
document.all["qfbutton"].style.display="none";
document.all["fc"].style.display="inline";
document.all["fcbutton"].style.display="inline";
  }
}

//根据下拉框的不同选择不同的表

function changeType_wl(f){
var para=f;
if(para=="A")
  {
document.all["kh"].style.display="inline";
document.all["khbutton"].style.display="inline";
document.all["fc"].style.display="none";
document.all["fcbutton"].style.display="none";
  }else
  {
document.all["kh"].style.display="none";
document.all["khbutton"].style.display="none";
document.all["fc"].style.display="inline";
document.all["fcbutton"].style.display="inline";
  }
}

//下拉框的内容是分隔符时出现的信息
function init_wl(){
var settingType="<%=settingType%>";
if(settingType=="A"){
document.all["kh"].style.display="inline";
document.all["khbutton"].style.display="inline";
document.all["fc"].style.display="none";
document.all["fcbutton"].style.display="none";
}
if(settingType=="B"){
document.all["kh"].style.display="none";
document.all["khbutton"].style.display="none";
document.all["fc"].style.display="inline";
document.all["fcbutton"].style.display="inline";
}
if(settingType==""){
document.all["kh"].style.display="inline";
document.all["khbutton"].style.display="inline";
document.all["fc"].style.display="none";
document.all["fcbutton"].style.display="none";
}


}


        
        function  check(){
        	var s;
        	var digits = "0123456789";
            var i = 0;
            var sLength = 0;
        	s=document.forms[0].elements["paramDTO.length1"].value.trim();
        	sLength=s.length;
        	if(sLength==0){
        	alert("请输入第一条的长度！");
        	return false;
        	}
               while ((i < sLength))
                {
                        var c = s.charAt(i);
                       if (digits.indexOf(c) == -1) 
                       {
                       		alert("第一条长度不是数字");
                       		return false;
                       	}
                        i++;
                }
        	s=document.forms[0].elements["paramDTO.length2"].value.trim();
        	sLength=s.length;
        	i=0;
        	if(sLength==0){
        	alert("请输入第二条的长度！");
        	return false;
        	}
               while ((i < sLength))
                {
                        var c = s.charAt(i);
                       if (digits.indexOf(c) == -1) 
                       {
                       		alert("第二条长度不是数字");
                       		return false;
                       	}
                        i++;
                }
        	s=document.forms[0].elements["paramDTO.length3"].value.trim();
        	sLength=s.length;
        	i=0;
        	if(sLength==0){
        	alert("请输入第三条的长度！");
        	return false;
        	}
               while ((i < sLength))
                {
                        var c = s.charAt(i);
                       if (digits.indexOf(c) == -1) 
                       {
                       		alert("第三条长度不是数字");
                       		return false;
                       	}
                        i++;
                }
        	s=document.forms[0].elements["paramDTO.length4"].value.trim();
        	sLength=s.length;
        	i=0;
        	if(sLength==0){
        	alert("请输入第四条的长度！");
        	return false;
        	}
               while ((i < sLength))
                {
                        var c = s.charAt(i);
                       if (digits.indexOf(c) == -1) 
                       {
                       		alert("第四条长度不是数字");
                       		return false;
                       	}
                        i++;
                }
        	s=document.forms[0].elements["paramDTO.length5"].value.trim();
        	sLength=s.length;
        	i=0;
        	if(sLength==0){
        	alert("请输入第五条的长度！");
        	return false;
        	}
               while ((i < sLength))
                {
                        var c = s.charAt(i);
                       if (digits.indexOf(c) == -1) 
                       {
                       		alert("第五条长度不是数字");
                       		return false;
                       	}
                        i++;
                }
        	s=document.forms[0].elements["paramDTO.length6"].value.trim();
        	sLength=s.length;
        	i=0;
        	if(sLength==0){
        	alert("请输入第六条的长度！");
        	return false;
        	}
               while ((i < sLength))
                {
                        var c = s.charAt(i);
                       if (digits.indexOf(c) == -1) 
                       {
                       		alert("第六条长度不是数字");
                       		return false;
                       	}
                        i++;
                }
        	s=document.forms[0].elements["paramDTO.length7"].value.trim();
        	sLength=s.length;
        	i=0;
        	if(sLength==0){
        	alert("请输入第七条的长度！");
        	return false;
        	}
               while ((i < sLength))
                {
                        var c = s.charAt(i);
                       if (digits.indexOf(c) == -1) 
                       {
                       		alert("第七条长度不是数字");
                       		return false;
                       	}
                        i++;
                }
        	s=document.forms[0].elements["paramDTO.length8"].value.trim();
        	sLength=s.length;
        	i=0;
        	if(sLength==0){
        	alert("请输入第八条的长度！");
        	return false;
        	}
               while ((i < sLength))
                {
                        var c = s.charAt(i);
                       if (digits.indexOf(c) == -1) 
                       {
                       		alert("第八条长度不是数字");
                       		return false;
                       	}
                        i++;
                }
        	s=document.forms[0].elements["paramDTO.length9"].value.trim();
        	sLength=s.length;
        	i=0;
        	if(sLength==0){
        	alert("请输入第九条的长度！");
        	return false;
        	}
               while ((i < sLength))
                {
                        var c = s.charAt(i);
                       if (digits.indexOf(c) == -1) 
                       {
                       		alert("第九条长度不是数字");
                       		return false;
                       	}
                        i++;
                }
        	s=document.forms[0].elements["paramDTO.length10"].value.trim();
        	sLength=s.length;
        	i=0;
        	if(sLength==0){
        	alert("请输入第十条的长度！");
        	return false;
        	}
               while ((i < sLength))
                {
                        var c = s.charAt(i);
                       
                       if (digits.indexOf(c) == -1) 
                       {
                       		alert("第十条长度不是数字");
                       		return false;
                       	}
                        i++;
                }
        	s=document.forms[0].elements["paramDTO.length11"].value.trim();
        	sLength=s.length;
        	i=0;
        	if(sLength==0){
        	alert("请输入第十一条的长度！");
        	return false;
        	}
               while ((i < sLength))
                {
                        var c = s.charAt(i);
                       if (digits.indexOf(c) == -1) 
                       {
                       		alert("第十一条长度不是数字");
                       		return false;
                       	}
                        i++;
                }
        	s=document.forms[0].elements["paramDTO.length12"].value.trim();
        	sLength=s.length;
        	i=0;
        	if(sLength==0){
        	alert("请输入第十二条的长度！");
        	return false;
        	}
               while ((i < sLength))
                {
                        var c = s.charAt(i);
                       if (digits.indexOf(c) == -1) 
                       {
                       		alert("第十二条长度不是数字");
                       		return false;
                       	}
                        i++;
                }
        	s=document.forms[0].elements["paramDTO.length13"].value.trim();
        	sLength=s.length;
        	i=0;
        	if(sLength==0){
        	alert("请输入第十三条的长度！");
        	return false;
        	}
               while ((i < sLength))
                {
                        var c = s.charAt(i);
                       
                       if (digits.indexOf(c) == -1) 
                       {
                       		alert("第十三条长度不是数字");
                       		return false;
                       	}
                        i++;
                }
        	s=document.forms[0].elements["paramDTO.length14"].value.trim();
        	sLength=s.length;
        	i=0;
        	if(sLength==0){
        	alert("请输入第十四条的长度！");
        	return false;
        	}
               while ((i < sLength))
                {
                        var c = s.charAt(i);
                       if (digits.indexOf(c) == -1) 
                       {
                       		alert("第十四条长度不是数字");
                       		return false;
                       	}
                        i++;
                }
        	s=document.forms[0].elements["paramDTO.length15"].value.trim();
        	sLength=s.length;
        	i=0;if(sLength==0){
        	alert("请输入第十五条的长度！");
        	return false;
        	}
               while ((i < sLength))
                {
                        var c = s.charAt(i);
                       if (digits.indexOf(c) == -1) 
                       {
                       		alert("第十五条长度不是数字");
                       		return false;
                       	}
                        i++;
                }
        	s=document.forms[0].elements["paramDTO.length16"].value.trim();
        	sLength=s.length;
        	i=0;
        	if(sLength==0){
        	alert("请输入第十六条的长度！");
        	return false;
        	}
               while ((i < sLength))
                {
                        var c = s.charAt(i);
                       if (digits.indexOf(c) == -1) 
                       {
                       		alert("第十六条长度不是数字");
                       		return false;
                       	}
                        i++;
                }
        	s=document.forms[0].elements["paramDTO.length17"].value.trim();
        	sLength=s.length;
        	i=0;
        	if(sLength==0){
        	alert("请输入第十七条的长度！");
        	return false;
        	}
               while ((i < sLength))
                {
                        var c = s.charAt(i);
                       if (digits.indexOf(c) == -1) 
                       {
                       		alert("第十七条长度不是数字");
                       		return false;
                       	}
                        i++;
                }
        	s=document.forms[0].elements["paramDTO.length18"].value.trim();
        	sLength=s.length;
        	i=0;
        	if(sLength==0){
        	alert("请输入第十八条的长度！");
        	return false;
        	}
               while ((i < sLength))
                {
                        var c = s.charAt(i);
                       if (digits.indexOf(c) == -1) 
                       {
                       		alert("第十八条长度不是数字");
                       		return false;
                       	}
                        i++;
                }
        	s=document.forms[0].elements["paramDTO.length19"].value.trim();
        	sLength=s.length;
        	i=0;
        	if(sLength==0){
        	alert("请输入第十九条的长度！");
        	return false;
        	}
               while ((i < sLength))
                {
                        var c = s.charAt(i);
                       if (digits.indexOf(c) == -1) 
                       {
                       		alert("第十九条长度不是数字");
                       		return false;
                       	}
                        i++;
                }
        	s=document.forms[0].elements["paramDTO.length20"].value.trim();
        	sLength=s.length;
        	i=0;
        	if(sLength==0){
        	alert("请输入第二十条的长度！");
        	return false;
        	}
               while ((i < sLength))
                {
                        var c = s.charAt(i);
                       if (digits.indexOf(c) == -1) 
                       {
                       		alert("第二十条长度不是数字");
                       		return false;
                       	}
                        i++;
                }
        	
        	
        	
        	
        	
        	
        	
        	
        
        	
        	
        	
        	
        	
        	
        	
        
        	
        	var align;
        	align=document.forms[0].elements["paramDTO.align1"];
        	if(align[0].checked==false&&align[1].checked==false){
				alert('第一条对齐方式必须二选一！');
				return false;
			}
        	align=document.forms[0].elements["paramDTO.align2"];
        	if(align[0].checked==false&&align[1].checked==false){
				alert('第二条对齐方式必须二选一！');
				return false;
			}
        	align=document.forms[0].elements["paramDTO.align3"];
        	if(align[0].checked==false&&align[1].checked==false){
				alert('第三条对齐方式必须二选一！');
				return false;
			}
        	align=document.forms[0].elements["paramDTO.align4"];
        	if(align[0].checked==false&&align[1].checked==false){
				alert('第四条对齐方式必须二选一！');
				return false;
			}
        	align=document.forms[0].elements["paramDTO.align5"];
        	if(align[0].checked==false&&align[1].checked==false){
				alert('第五条对齐方式必须二选一！');
				return false;
			}
        	align=document.forms[0].elements["paramDTO.align6"];
        	if(align[0].checked==false&&align[1].checked==false){
				alert('第六条对齐方式必须二选一！');
				return false;
			}
        	align=document.forms[0].elements["paramDTO.align7"];
        	if(align[0].checked==false&&align[1].checked==false){
				alert('第七条对齐方式必须二选一！');
				return false;
			}
        	align=document.forms[0].elements["paramDTO.align8"];
        	if(align[0].checked==false&&align[1].checked==false){
				alert('第八条对齐方式必须二选一！');
				return false;
			}
        	align=document.forms[0].elements["paramDTO.align9"];
        	if(align[0].checked==false&&align[1].checked==false){
				alert('第九条对齐方式必须二选一！');
				return false;
			}
        	align=document.forms[0].elements["paramDTO.align10"];
        	if(align[0].checked==false&&align[1].checked==false){
				alert('第十条对齐方式必须二选一！');
				return false;
			}
        	align=document.forms[0].elements["paramDTO.align11"];
        	if(align[0].checked==false&&align[1].checked==false){
				alert('第十一条对齐方式必须二选一！');
				return false;
			}
        	align=document.forms[0].elements["paramDTO.align12"];
        	if(align[0].checked==false&&align[1].checked==false){
				alert('第十二条对齐方式必须二选一！');
				return false;
			}
        	align=document.forms[0].elements["paramDTO.align13"];
        	if(align[0].checked==false&&align[1].checked==false){
				alert('第十三条对齐方式必须二选一！');
				return false;
			}
        	align=document.forms[0].elements["paramDTO.align14"];
        	if(align[0].checked==false&&align[1].checked==false){
				alert('第十四条对齐方式必须二选一！');
				return false;
			}
        	align=document.forms[0].elements["paramDTO.align15"];
        	if(align[0].checked==false&&align[1].checked==false){
				alert('第十五条对齐方式必须二选一！');
				return false;
			}
        	align=document.forms[0].elements["paramDTO.align16"];
        	if(align[0].checked==false&&align[1].checked==false){
				alert('第十六条对齐方式必须二选一！');
				return false;
			}
        	align=document.forms[0].elements["paramDTO.align17"];
        	if(align[0].checked==false&&align[1].checked==false){
				alert('第十七条对齐方式必须二选一！');
				return false;
			}
        	align=document.forms[0].elements["paramDTO.align18"];
        	if(align[0].checked==false&&align[1].checked==false){
				alert('第十八条对齐方式必须二选一！');
				return false;
			}
        	align=document.forms[0].elements["paramDTO.align19"];
        	if(align[0].checked==false&&align[1].checked==false){
				alert('第十九条对齐方式必须二选一！');
				return false;
			}
        	align=document.forms[0].elements["paramDTO.align20"];
        	if(align[0].checked==false&&align[1].checked==false){
				alert('第二十对齐方式必须二选一！');
				return false;
			}
        	
        	var format;
        	format=document.forms[0].elements["paramDTO.format1"];
        	if(format[0].checked==false&&format[1].checked==false){
				alert('第一条文档格式必须二选一！');
				return false;
			}
        	
        	format=document.forms[0].elements["paramDTO.format2"];
        	if(format[0].checked==false&&format[1].checked==false){
				alert('第二条文档格式必须二选一！');
				return false;
			}
        	format=document.forms[0].elements["paramDTO.format3"];
        	if(format[0].checked==false&&format[1].checked==false){
				alert('第三条文档格式必须二选一！');
				return false;
			}
        	format=document.forms[0].elements["paramDTO.format4"];
        	if(format[0].checked==false&&format[1].checked==false){
				alert('第四条文档格式必须二选一！');
				return false;
			}
        	format=document.forms[0].elements["paramDTO.format5"];
        	if(format[0].checked==false&&format[1].checked==false){
				alert('第五条文档格式必须二选一！');
				return false;
			}
        	format=document.forms[0].elements["paramDTO.format6"];
        	if(format[0].checked==false&&format[1].checked==false){
				alert('第六条文档格式必须二选一！');
				return false;
			}
        	format=document.forms[0].elements["paramDTO.format7"];
        	if(format[0].checked==false&&format[1].checked==false){
				alert('第七条文档格式必须二选一！');
				return false;
			}
        	format=document.forms[0].elements["paramDTO.format8"];
        	if(format[0].checked==false&&format[1].checked==false){
				alert('第八条文档格式必须二选一！');
				return false;
			}
        	format=document.forms[0].elements["paramDTO.format9"];
        	if(format[0].checked==false&&format[1].checked==false){
				alert('第九条文档格式必须二选一！');
				return false;
			}
        	format=document.forms[0].elements["paramDTO.format10"];
        	if(format[0].checked==false&&format[1].checked==false){
				alert('第十条文档格式必须二选一！');
				return false;
			}
        	format=document.forms[0].elements["paramDTO.format11"];
        	if(format[0].checked==false&&format[1].checked==false){
				alert('第十一条文档格式必须二选一！');
				return false;
			}
        	format=document.forms[0].elements["paramDTO.format12"];
        	if(format[0].checked==false&&format[1].checked==false){
				alert('第十二条文档格式必须二选一！');
				return false;
			}
        	format=document.forms[0].elements["paramDTO.format13"];
        	if(format[0].checked==false&&format[1].checked==false){
				alert('第十三条文档格式必须二选一！');
				return false;
			}
        	format=document.forms[0].elements["paramDTO.format14"];
        	if(format[0].checked==false&&format[1].checked==false){
				alert('第十四条文档格式必须二选一！');
				return false;
			}
        	format=document.forms[0].elements["paramDTO.format15"];
        	if(format[0].checked==false&&format[1].checked==false){
				alert('第十五条文档格式必须二选一！');
				return false;
			}
        	format=document.forms[0].elements["paramDTO.format16"];
        	if(format[0].checked==false&&format[1].checked==false){
				alert('第十六条文档格式必须二选一！');
				return false;
			}
        	format=document.forms[0].elements["paramDTO.format17"];
        	if(format[0].checked==false&&format[1].checked==false){
				alert('第十七条文档格式必须二选一！');
				return false;
			}
        	format=document.forms[0].elements["paramDTO.format18"];
        	if(format[0].checked==false&&format[1].checked==false){
				alert('第十八条文档格式必须二选一！');
				return false;
			}
        	format=document.forms[0].elements["paramDTO.format19"];
        	if(format[0].checked==false&&format[1].checked==false){
				alert('第十九条文档格式必须二选一！');
				return false;
			}
        	format=document.forms[0].elements["paramDTO.format20"];
        	if(format[0].checked==false&&format[1].checked==false){
				alert('第二十条文档格式必须二选一！');
				return false;
			}
        }
   function check1(){
   s=document.forms[0].elements["paramDTO.mark"].value.trim();
        	sLength=s.length;
        	i=0;
        	if(sLength==0){
        	alert("请输入分隔符号！");
        	return false;
        	}
    
   }   
</script>




<body bgcolor="#FFFFFF" text="#000000" onLoad="init_wl();">
<html:form action="/paramSaveAC.do">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="7"><img src="<%=path%>/img/table_left.gif" width="7" height="37"></td>
          <td background="<%=path%>/img/table_bg_line.gif" width="55">&nbsp;</td>
          <td width="235" background="<%=path%>/img/table_bg_line.gif"></td>
          <td background="<%=path%>/img/table_bg_line.gif" align="right"> 
            <table width="300" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="37"><img src="<%=path%>/img/title_banner.gif" width="37" height="24"></td>
                <td width="148" class=font14 bgcolor="#FFFFFF" align="center" valign="bottom"><font color="00B5DB">人民银行数据导出</font></td>
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
          <td height="35"><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td height="22" bgcolor="#CCCCCC" align="center" width="93"><b class="font14">参 数 设 置</b></td>
              <td width="601" height="22" align="center" background="<%=path%>/img/bg2.gif">&nbsp;</td>
            </tr>
          </table></td>
        </tr>
      </table>
      <table width="95%" border="0" cellspacing="1" cellpadding="0" align="center">
        <tr>
          <td width="17%" class="td1">设置类型</td>
          <td   align="center" width="33%" > 
            
          
          <html:select name="paramAF" property="paramDTO.settingType"
									styleClass="input4" onchange="changeType_wl(this.value);">
									<html:option   value="A">分隔符</html:option>
									<html:option  value="B">固定长度</html:option>
								</html:select>
          
          
          </td>
          <td width="17%" >&nbsp;</td>
          <td width="33%"  >&nbsp;</td>
        </tr>
      </table>
      <table border="0" width="95%"  cellspacing=1  cellpadding=3 align="center" id="kh">
        <tr>
          <td   class="td1" width="17%">分隔符号<font color="#FF0000">*</font></td>
          <td width="33%"   ><html:text name="paramAF"  property="paramDTO.mark"  styleClass="input3"  onkeydown="enterNextFocus1();"/>
            </td>
          <td  width="17%" >&nbsp;</td>
          <td width="33%" >&nbsp;</td>
        </tr>
      </table>
	    
      <table border="0" width="95%"  cellspacing=1  cellpadding=3 align="center" id="fc">
          <tr>
            <td width="6%"   class="td1">1<font color="#FF0000">&nbsp;</font></td>
            <td width="94%"   class="td1">数据格式版本号<font color="#FF0000">*</font>：长度
               <html:text name="paramAF"  property="paramDTO.length1"  styleClass="input3"  style="background-color:White;width=7%;" onkeydown="enterNextFocus1();"/>           
            ；对齐方式
            
           
           <html:radio name="paramAF" property="paramDTO.align1"
									value="1" onkeydown="enterNextFocus1();" />
左对齐 　
<html:radio name="paramAF" property="paramDTO.align1"
									value="2" onkeydown="enterNextFocus1();" />
右对齐； 文档格式
<html:radio name="paramAF" property="paramDTO.format1"
									value="1" onkeydown="enterNextFocus1();" />
中文 　
<html:radio name="paramAF" property="paramDTO.format1"
									value="2" onkeydown="enterNextFocus1();" />
英文；</td>
          </tr>
          
          
          
		
          
           <tr>
            <td width="6%"   class="td1">1<font color="#FF0000">&nbsp;</font></td>
            <td width="94%"   class="td1">数据上报机构<font color="#FF0000">*</font>：长度
               <html:text name="paramAF"  property="paramDTO.length2"  styleClass="input3"  style="background-color:White;width=7%;" onkeydown="enterNextFocus1();"/>           
            ；对齐方式
           <html:radio name="paramAF" property="paramDTO.align2"
									value="1" onkeydown="enterNextFocus1();" />
左对齐 　
<html:radio name="paramAF" property="paramDTO.align2"
									value="2" onkeydown="enterNextFocus1();" />
右对齐； 文档格式
<html:radio name="paramAF" property="paramDTO.format2"
									value="1" onkeydown="enterNextFocus1();" />
中文 　
<html:radio name="paramAF" property="paramDTO.format2"
									value="2" onkeydown="enterNextFocus1();" />
英文；</td>
          </tr>
          
          
		  <tr>
            <td width="6%"   class="td1">3</td>
            <td width="94%"   class="td1">账户记录总数<font color="#FF0000">*</font>：长度
              <html:text name="paramAF"  property="paramDTO.length3"  styleClass="input3"  style="background-color:White;width=7%;" onkeydown="enterNextFocus1();"/>           
            ；对齐方式
           <html:radio name="paramAF" property="paramDTO.align3"
									value="1" onkeydown="enterNextFocus1();" />
左对齐 　
<html:radio name="paramAF" property="paramDTO.align3"
									value="2" onkeydown="enterNextFocus1();" />
右对齐； 文档格式
<html:radio name="paramAF" property="paramDTO.format3"
									value="1" onkeydown="enterNextFocus1();" />
中文 　
<html:radio name="paramAF" property="paramDTO.format3"
									value="2" onkeydown="enterNextFocus1();" />
英文；</td>
          </tr>
		  <tr>
            <td width="6%"   class="td1">4</td>
            <td width="94%"   class="td1">报文生成时间<font color="#FF0000">*</font>：长度
              <html:text name="paramAF"  property="paramDTO.length4"  styleClass="input3"  style="background-color:White;width=7%;" onkeydown="enterNextFocus1();"/>           
            ；对齐方式
           <html:radio name="paramAF" property="paramDTO.align4"
									value="1" onkeydown="enterNextFocus1();" />
左对齐 　
<html:radio name="paramAF" property="paramDTO.align4"
									value="2" onkeydown="enterNextFocus1();" />
右对齐； 文档格式
<html:radio name="paramAF" property="paramDTO.format4"
									value="1" onkeydown="enterNextFocus1();" />
中文 　
<html:radio name="paramAF" property="paramDTO.format4"
									value="2" onkeydown="enterNextFocus1();" />
英文；</td>
          </tr>
          
          
          <tr>
            <td width="6%"   class="td1">5<font color="#FF0000">&nbsp;</font></td>
            <td width="94%"   class="td1">联系人<font color="#FF0000">*</font>：长度
               <html:text name="paramAF"  property="paramDTO.length5"  styleClass="input3"  style="background-color:White;width=7%;" onkeydown="enterNextFocus1();"/>           
            ；对齐方式
            
           
           <html:radio name="paramAF" property="paramDTO.align5"
									value="1" onkeydown="enterNextFocus1();" />
左对齐 　
<html:radio name="paramAF" property="paramDTO.align5"
									value="2" onkeydown="enterNextFocus1();" />
右对齐； 文档格式
<html:radio name="paramAF" property="paramDTO.format5"
									value="1" onkeydown="enterNextFocus1();" />
中文 　
<html:radio name="paramAF" property="paramDTO.format5"
									value="2" onkeydown="enterNextFocus1();" />
英文；</td>
          </tr>
          
          
          
		
          
           <tr>
            <td width="6%"   class="td1">6<font color="#FF0000">&nbsp;</font></td>
            <td width="94%"   class="td1">联系电话<font color="#FF0000">*</font>：长度
               <html:text name="paramAF"  property="paramDTO.length6"  styleClass="input3"  style="background-color:White;width=7%;" onkeydown="enterNextFocus1();"/>           
            ；对齐方式
           <html:radio name="paramAF" property="paramDTO.align6"
									value="1" onkeydown="enterNextFocus1();" />
左对齐 　
<html:radio name="paramAF" property="paramDTO.align6"
									value="2" onkeydown="enterNextFocus1();" />
右对齐； 文档格式
<html:radio name="paramAF" property="paramDTO.format6"
									value="1" onkeydown="enterNextFocus1();" />
中文 　
<html:radio name="paramAF" property="paramDTO.format6"
									value="2" onkeydown="enterNextFocus1();" />
英文；</td>
          </tr>
          
          
		  <tr>
            <td width="6%"   class="td1">7</td>
            <td width="94%"   class="td1">个人识别编码<font color="#FF0000">*</font>：长度
              <html:text name="paramAF"  property="paramDTO.length7"  styleClass="input3"  style="background-color:White;width=7%;" onkeydown="enterNextFocus1();"/>           
            ；对齐方式
           <html:radio name="paramAF" property="paramDTO.align7"
									value="1" onkeydown="enterNextFocus1();" />
左对齐 　
<html:radio name="paramAF" property="paramDTO.align7"
									value="2" onkeydown="enterNextFocus1();" />
右对齐； 文档格式
<html:radio name="paramAF" property="paramDTO.format7"
									value="1" onkeydown="enterNextFocus1();" />
中文 　
<html:radio name="paramAF" property="paramDTO.format7"
									value="2" onkeydown="enterNextFocus1();" />
英文；</td>
          </tr>
		  <tr>
            <td width="6%"   class="td1">8</td>
            <td width="94%"   class="td1">发生地点<font color="#FF0000">*</font>：长度
              <html:text name="paramAF"  property="paramDTO.length8"  styleClass="input3"  style="background-color:White;width=7%;" onkeydown="enterNextFocus1();"/>           
            ；对齐方式
           <html:radio name="paramAF" property="paramDTO.align8"
									value="1" onkeydown="enterNextFocus1();" />
左对齐 　
<html:radio name="paramAF" property="paramDTO.align8"
									value="2" onkeydown="enterNextFocus1();" />
右对齐； 文档格式
<html:radio name="paramAF" property="paramDTO.format8"
									value="1" onkeydown="enterNextFocus1();" />
中文 　
<html:radio name="paramAF" property="paramDTO.format8"
									value="2" onkeydown="enterNextFocus1();" />
英文；</td>
          </tr>
          
          
          
            <tr>
            <td width="6%"   class="td1">9<font color="#FF0000">&nbsp;</font></td>
            <td width="94%"   class="td1">数据发生机构<font color="#FF0000">*</font>：长度
               <html:text name="paramAF"  property="paramDTO.length9"  styleClass="input3"  style="background-color:White;width=7%;" onkeydown="enterNextFocus1();"/>           
            ；对齐方式
            
           
           <html:radio name="paramAF" property="paramDTO.align9"
									value="1" onkeydown="enterNextFocus1();" />
左对齐 　
<html:radio name="paramAF" property="paramDTO.align9"
									value="2" onkeydown="enterNextFocus1();" />
右对齐； 文档格式
<html:radio name="paramAF" property="paramDTO.format9"
									value="1" onkeydown="enterNextFocus1();" />
中文 　
<html:radio name="paramAF" property="paramDTO.format9"
									value="2" onkeydown="enterNextFocus1();" />
英文；</td>
          </tr>
          
          
          
		
          
           <tr>
            <td width="6%"   class="td1">10<font color="#FF0000">&nbsp;</font></td>
            <td width="94%"   class="td1">个人账号<font color="#FF0000">*</font>：长度
               <html:text name="paramAF"  property="paramDTO.length10"  styleClass="input3"  style="background-color:White;width=7%;" onkeydown="enterNextFocus1();"/>           
            ；对齐方式
           <html:radio name="paramAF" property="paramDTO.align10"
									value="1" onkeydown="enterNextFocus1();" />
左对齐 　
<html:radio name="paramAF" property="paramDTO.align10"
									value="2" onkeydown="enterNextFocus1();" />
右对齐； 文档格式
<html:radio name="paramAF" property="paramDTO.format10"
									value="1" onkeydown="enterNextFocus1();" />
中文 　
<html:radio name="paramAF" property="paramDTO.format10"
									value="2" onkeydown="enterNextFocus1();" />
英文；</td>
          </tr>
          
          
		  <tr>
            <td width="6%"   class="td1">11</td>
            <td width="94%"   class="td1">姓名<font color="#FF0000">*</font>：长度
              <html:text name="paramAF"  property="paramDTO.length11"  styleClass="input3"  style="background-color:White;width=7%;" onkeydown="enterNextFocus1();"/>           
            ；对齐方式
           <html:radio name="paramAF" property="paramDTO.align11"
									value="1" onkeydown="enterNextFocus1();" />
左对齐 　
<html:radio name="paramAF" property="paramDTO.align11"
									value="2" onkeydown="enterNextFocus1();" />
右对齐； 文档格式
<html:radio name="paramAF" property="paramDTO.format11"
									value="1" onkeydown="enterNextFocus1();" />
中文 　
<html:radio name="paramAF" property="paramDTO.format11"
									value="2" onkeydown="enterNextFocus1();" />
英文；</td>
          </tr>
		  <tr>
            <td width="6%"   class="td1">12</td>
            <td width="94%"   class="td1">身份证号<font color="#FF0000">*</font>：长度
              <html:text name="paramAF"  property="paramDTO.length12"  styleClass="input3"  style="background-color:White;width=7%;" onkeydown="enterNextFocus1();"/>           
            ；对齐方式
           <html:radio name="paramAF" property="paramDTO.align12"
									value="1" onkeydown="enterNextFocus1();" />
左对齐 　
<html:radio name="paramAF" property="paramDTO.align12"
									value="2" onkeydown="enterNextFocus1();" />
右对齐； 文档格式
<html:radio name="paramAF" property="paramDTO.format12"
									value="1" onkeydown="enterNextFocus1();" />
中文 　
<html:radio name="paramAF" property="paramDTO.format12"
									value="2" onkeydown="enterNextFocus1();" />
英文；</td>
          </tr>
          
          
          <tr>
            <td width="6%"   class="td1">13<font color="#FF0000">&nbsp;</font></td>
            <td width="94%"   class="td1">单位名称<font color="#FF0000">*</font>：长度
               <html:text name="paramAF"  property="paramDTO.length13"  styleClass="input3"  style="background-color:White;width=7%;" onkeydown="enterNextFocus1();"/>           
            ；对齐方式
            
           
           <html:radio name="paramAF" property="paramDTO.align13"
									value="1" onkeydown="enterNextFocus1();" />
左对齐 　
<html:radio name="paramAF" property="paramDTO.align13"
									value="2" onkeydown="enterNextFocus1();" />
右对齐； 文档格式
<html:radio name="paramAF" property="paramDTO.format13"
									value="1" onkeydown="enterNextFocus1();" />
中文 　
<html:radio name="paramAF" property="paramDTO.format13"
									value="2" onkeydown="enterNextFocus1();" />
英文；</td>
          </tr>
          
          
          
		
          
           <tr>
            <td width="6%"   class="td1">14<font color="#FF0000">&nbsp;</font></td>
            <td width="94%"   class="td1">单位性质<font color="#FF0000">*</font>：长度
               <html:text name="paramAF"  property="paramDTO.length14"  styleClass="input3"  style="background-color:White;width=7%;" onkeydown="enterNextFocus1();"/>           
            ；对齐方式
           <html:radio name="paramAF" property="paramDTO.align14"
									value="1" onkeydown="enterNextFocus1();" />
左对齐 　
<html:radio name="paramAF" property="paramDTO.align14"
									value="2" onkeydown="enterNextFocus1();" />
右对齐； 文档格式
<html:radio name="paramAF" property="paramDTO.format14"
									value="1" onkeydown="enterNextFocus1();" />
中文 　
<html:radio name="paramAF" property="paramDTO.format14"
									value="2" onkeydown="enterNextFocus1();" />
英文；</td>
          </tr>
          
          
		  <tr>
            <td width="6%"   class="td1">15</td>
            <td width="94%"   class="td1">开户日期<font color="#FF0000">*</font>：长度
              <html:text name="paramAF"  property="paramDTO.length15"  styleClass="input3"  style="background-color:White;width=7%;" onkeydown="enterNextFocus1();"/>           
            ；对齐方式
           <html:radio name="paramAF" property="paramDTO.align15"
									value="1" onkeydown="enterNextFocus1();" />
左对齐 　
<html:radio name="paramAF" property="paramDTO.align15"
									value="2" onkeydown="enterNextFocus1();" />
右对齐； 文档格式
<html:radio name="paramAF" property="paramDTO.format15"
									value="1" onkeydown="enterNextFocus1();" />
中文 　
<html:radio name="paramAF" property="paramDTO.format15"
									value="2" onkeydown="enterNextFocus1();" />
英文；</td>
          </tr>
		  <tr>
            <td width="6%"   class="td1">16</td>
            <td width="94%"   class="td1">初缴年月<font color="#FF0000">*</font>：长度
              <html:text name="paramAF"  property="paramDTO.length16"  styleClass="input3"  style="background-color:White;width=7%;" onkeydown="enterNextFocus1();"/>           
            ；对齐方式
           <html:radio name="paramAF" property="paramDTO.align16"
									value="1" onkeydown="enterNextFocus1();" />
左对齐 　
<html:radio name="paramAF" property="paramDTO.align16"
									value="2" onkeydown="enterNextFocus1();" />
右对齐； 文档格式
<html:radio name="paramAF" property="paramDTO.format16"
									value="1" onkeydown="enterNextFocus1();" />
中文 　
<html:radio name="paramAF" property="paramDTO.format16"
									value="2" onkeydown="enterNextFocus1();" />
英文；</td>
          </tr> 
          
           <tr>
            <td width="6%"   class="td1">17<font color="#FF0000">&nbsp;</font></td>
            <td width="94%"   class="td1">缴至年月<font color="#FF0000">*</font>：长度
               <html:text name="paramAF"  property="paramDTO.length17"  styleClass="input3"  style="background-color:White;width=7%;" onkeydown="enterNextFocus1();"/>           
            ；对齐方式
            
           
           <html:radio name="paramAF" property="paramDTO.align17"
									value="1" onkeydown="enterNextFocus1();" />
左对齐 　
<html:radio name="paramAF" property="paramDTO.align17"
									value="2" onkeydown="enterNextFocus1();" />
右对齐； 文档格式
<html:radio name="paramAF" property="paramDTO.format17"
									value="1" onkeydown="enterNextFocus1();" />
中文 　
<html:radio name="paramAF" property="paramDTO.format17"
									value="2" onkeydown="enterNextFocus1();" />
英文；</td>
          </tr>
          
          
          
		
          
           <tr>
            <td width="6%"   class="td1">18<font color="#FF0000">&nbsp;</font></td>
            <td width="94%"   class="td1">公积金缴交状态<font color="#FF0000">*</font>：长度
               <html:text name="paramAF"  property="paramDTO.length18"  styleClass="input3"  style="background-color:White;width=7%;" onkeydown="enterNextFocus1();"/>           
            ；对齐方式
           <html:radio name="paramAF" property="paramDTO.align18"
									value="1" onkeydown="enterNextFocus1();" />
左对齐 　
<html:radio name="paramAF" property="paramDTO.align18"
									value="2" onkeydown="enterNextFocus1();" />
右对齐； 文档格式
<html:radio name="paramAF" property="paramDTO.format18"
									value="1" onkeydown="enterNextFocus1();" />
中文 　
<html:radio name="paramAF" property="paramDTO.format18"
									value="2" onkeydown="enterNextFocus1();" />
英文；</td>
          </tr>
          
          
		  <tr>
            <td width="6%"   class="td1">19</td>
            <td width="94%"   class="td1">最近一次缴交日期<font color="#FF0000">*</font>：长度
              <html:text name="paramAF"  property="paramDTO.length19"  styleClass="input3"  style="background-color:White;width=7%;" onkeydown="enterNextFocus1();"/>           
            ；对齐方式
           <html:radio name="paramAF" property="paramDTO.align19"
									value="1" onkeydown="enterNextFocus1();" />
左对齐 　
<html:radio name="paramAF" property="paramDTO.align19"
									value="2" onkeydown="enterNextFocus1();" />
右对齐； 文档格式
<html:radio name="paramAF" property="paramDTO.format19"
									value="1" onkeydown="enterNextFocus1();" />
中文 　
<html:radio name="paramAF" property="paramDTO.format19"
									value="2" onkeydown="enterNextFocus1();" />
英文；</td>
          </tr>
		  <tr>
            <td width="6%"   class="td1">20</td>
            <td width="94%"   class="td1">月缴存额<font color="#FF0000">*</font>：长度
              <html:text name="paramAF"  property="paramDTO.length20"  styleClass="input3"  style="background-color:White;width=7%;" onkeydown="enterNextFocus1();"/>           
            ；对齐方式
           <html:radio name="paramAF" property="paramDTO.align20"
									value="1" onkeydown="enterNextFocus1();" />
左对齐 　
<html:radio name="paramAF" property="paramDTO.align20"
									value="2" onkeydown="enterNextFocus1();" />
右对齐； 文档格式
<html:radio name="paramAF" property="paramDTO.format20"
									value="1" onkeydown="enterNextFocus1();" />
中文 　
<html:radio name="paramAF" property="paramDTO.format20"
									value="2" onkeydown="enterNextFocus1();" />
英文；</td>
          </tr> 
          
          
          
          
          
          
		 
      </table>
	    
      <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" >
        <tr valign="bottom">
          <td  colspan="7" bgcolor="#FFFFFF" align="center" height="30"><table border="0" cellspacing="0" cellpadding="0" id="khbutton">
              <tr>
                <td width="70"><input type="submit" name="Submit" value="确定" onclick="return check1();" class="buttona" >
                </td>
              </tr>
            </table>
              <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
                <tr valign="bottom">
                  <td  colspan="7" bgcolor="#FFFFFF" align="center" height="30"><table border="0" cellspacing="0" cellpadding="0" id="fcbutton">
                      <tr>
                        <td width="70"><input type="submit" name="Submit" value="确定" onclick="return check();" class="buttona" >
                        </td>
                      </tr>
                    </table>
            </table>
      </table>
      </td>
      </tr>
      </table></html:form>
    </body>
   
</html>	  