<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic" %>
<%@ page import="org.xpup.hafmis.sysloan.common.biz.contractpop.action.ContractpopShowAC" %>
<%@ include file="/checkUrl.jsp"%>
<%
   String path = request.getContextPath();
   Object pagination= request.getSession(false).getAttribute(ContractpopShowAC.PAGINATION_KEY);
   request.setAttribute("pagination",pagination);
   String indexs=(String)session.getAttribute("indexs");
 %>
<html:html>
<head>
<title>个贷管理-----测试合同弹出框</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/index.css" type="text/css">
<script src="<%=request.getContextPath()%>/js/common.js">
</script>
</head>
<script language="javascript" src="js/common.js">
var oldColor="#ffffff";                            //原来的颜色
var newColor="#E8FCFD";                     //要变成的颜色
function chgBGColor(oTD){
	oldColor=oTD.style.backgroundColor;//保存当前颜色
	oTD.style.backgroundColor=newColor;//改变表格颜色
	newColor=oldColor;                 //改变下次要变成的颜色
}
</script>

<body bgcolor="#FFFFFF" text="#000000">
<form method="post">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
      <td> 
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr> 
            <td width="7"><img src="<%=request.getContextPath()%>/img/table_left.gif" width="7" height="37"></td>
            <td background="<%=request.getContextPath()%>/img/table_bg_line.gif" width="10">&nbsp;</td>
            <td background="<%=request.getContextPath()%>/img/table_bg_line.gif" valign="top"> 
              <table border="0" cellspacing="0" cellpadding="0">
                <tr> 
                  <td width="112" height="37" background="images/buttong.gif" align="center" valign="top"  style="PADDING-top: 7px"><a href="贷款申请_贷款申请_借款人基本信息.htm" class=a2>借款人信息</a></td>
                  <td width="112" height="37" background="images/buttonbl.gif" align="center"   style="PADDING-top: 7px" valign="top"><a href="贷款申请_贷款申请_辅助借款人基本信息.htm" class=a2>辅助借款人信息</a></td>
                  <td width="112" height="37" background="images/buttong.gif" align="center"   style="PADDING-top: 7px" valign="top"><a href="贷款申请_贷款申请_购房信息.htm" class=a2>购房信息</a></td>
                  <td width="112" height="37" background="images/buttong.gif" align="center"   style="PADDING-top: 7px" valign="top"><a href="贷款申请_贷款申请_借款人额度信息.htm" class=a2>借款人额度信息</a></td>
                  <td width="112" height="37" background="images/buttong.gif" align="center"   style="PADDING-top: 7px" valign="top"><a href="贷款申请_贷款申请_业务维护.htm" class=a2>申请贷款维护</a></td>
                </tr>
              </table>
            </td>
            <td background="<%=request.getContextPath()%>/img/table_bg_line.gif" align="right"> 
              <table width="200" border="0" cellspacing="0" cellpadding="0">
                <tr> 
                  <td width="37"><img src="<%=request.getContextPath()%>/img/title_banner.gif" width="37" height="24"></td>
                  <td width="228" class=font14 bgcolor="#FFFFFF" align="center" valign="bottom"><a href="#" class=a1>申请贷款</a><font color="00B5DB">&gt;</font><a href="#" class=a1>申请贷款</a></td>
                  <td width="35" class=font14>&nbsp;</td>
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
                  <td height="22" bgcolor="#CCCCCC" align="center" width="188"><b class="font14">辅助借款人基本信息</b></td>
                  <td height="22" background="<%=request.getContextPath()%>/img/bg2.gif" align="center" width="736">&nbsp;</td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
        <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=3 align="center" >
          <tr> 
            <td class=td1 width="12%" > 合同编号 </td>
            <td class="td4" width="8%" > 
              <input name="textfield30222" type="text" id="txtsearch45225" class="input3" >            </td>
            <td class="td4" > 
              <input type="button" name="submit4" value="..." class="buttona"onclick=" gotoContractpop('123','<%=path%>','0');">           </td>
            <td class="td1" width="12%" >借款人姓名</td>
            <td class="td4" width="17%" >
              <input name="textfield30228" type="text" id="txtsearch45225" class="input3" >            </td>
            <td class=td1 width="12%" ></td>
            <td class="td4" width="30%">&nbsp;</td>
          </tr>
		  <tr> 
            <td class=td1 width="12%" > 职工编号</td>
            <td class="td4" width="8%" > 
              <input name="textfield30222" type="text" id="txtsearch45225" class="input3" >            </td>
            <td class="td4" > 
              <input type="button" class="buttona" value="..." name="button" onClick="MM_openBrWindow('公用查询_职工列表2.htm','','width=600,height=400')" >            </td>
            <td class="td1" width="12%" >职工姓名<span class="STYLE1">*</span></td>
            <td class="td4" width="17%" >
              <input name="textfield30228" type="text" id="txtsearch45225" class="input3" >            </td>
            <td class=td1 width="12%" >与借款人关系<span class="STYLE1">*</span></td>
            <td class="td4" width="30%"><input name="textfield30221422" type="text" id="textfield3022142" class="input3" ></td>
          </tr>
          <tr>
            <td class=td1>性别<span class="STYLE1">*</span></td>
            <td class="td4"  colspan="2"><select name="select" id="select2"  class="input4" >
              <option>男</option>
              <option>女</option>
            </select></td>
            <td class=td1 >证件类型</td>
            <td class="td4"><select name="select5" id="select"  class="input4" >
              <option>身份证</option>
              <option>军官证</option>
            </select></td>
            <td class=td1>证件号码<span class="STYLE1">*</span></td>
            <td class="td4"><input name="textfield3022142" type="text" id="textfield302214" class="input3" ></td>
          </tr>
          <tr> 
            <td width="12%" class=td1>出生日期<span class="STYLE1">*</span></td>
            <td class="td4"  colspan="2">
              <input name="textfield30223" type="text" id="txtsearch45225" class="input3" >            </td>
            <td width="12%"class=td1 >年龄<span class="STYLE1">*</span></td>
            <td class="td4" width="17%">
              <input name="textfield30229" type="text" id="txtsearch45225" class="input3" >            </td>
            <td width="12%" class=td1>民族</td>
            <td class="td4" width="30%">
              <input name="textfield302215" type="text" id="txtsearch45225" class="input3" >            </td>
          </tr>
          <tr> 
            <td width="12%" class=td1>户籍所在地</td>
            <td class="td4"  colspan="2">
              <input name="textfield30224" type="text" id="txtsearch45225" class="input3" >            </td>
            <td width="12%"class=td1 >职务</td>
            <td class="td4" width="17%">
              <input name="textfield302210" type="text" id="txtsearch45225" class="input3" >            </td>
            <td width="12%" class=td1>职称</td>
            <td class="td4" width="30%">
              <input name="textfield302216" type="text" id="txtsearch45225" class="input3" >            </td>
          </tr>
          <tr> 
            <td width="12%" class=td1>婚姻状况</td>
            <td class="td4"  colspan="2">
              <input name="textfield30225" type="text" id="txtsearch45225" class="input3" >            </td>
            <td width="12%"class=td1 >文化程度</td>
            <td class="td4" width="17%">
              <input name="textfield302211" type="text" id="txtsearch45225" class="input3" >            </td>
            <td width="12%" class=td1>家庭住址</td>
            <td class="td4" width="30%">
              <input name="textfield302217" type="text" id="txtsearch45225" class="input3" >            </td>
          </tr>
          <tr>
            <td width="12%" class=td1>邮政编码</td>
            <td class="td4"  colspan="2">
              <input name="textfield30226" type="text" id="txtsearch45225" class="input3" >            </td>
            <td width="12%"class=td1 >移动电话</td>
            <td class="td4" width="17%">
              <input name="textfield302212" type="text" id="txtsearch45225" class="input3" >            </td>
            <td width="12%" class=td1>住宅电话</td>
            <td class="td4" width="30%">
              <input name="textfield302218" type="text" id="txtsearch45225" class="input3" >            </td>
          </tr>
        </table>
        <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
          <tr> 
            <td height="35"> 
              <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr> 
                  <td height="22" bgcolor="#CCCCCC" align="center" width="188"><b class="font14">辅助借款人公积金归集信息</b></td>
                  <td height="22" background="<%=request.getContextPath()%>/img/bg2.gif" align="center" width="722">&nbsp;</td>
                </tr>
              </table>
            </td>
          </tr>
        </table>
        <table border="0" width="95%" id="table1" cellspacing=1  cellpadding=3 align="center" >
          <tr> 
            <td width="12%" class=td1>单位编号</td>
            <td class="td4"  colspan="2"> 
              <input name="textfield302232" type="text" id="txtsearch45225" class="input3" >            </td>
            <td width="12%"class=td1 > 单位名称</td>
            <td class="td4" width="17%"> 
              <input name="textfield302292" type="text" id="txtsearch45225" class="input3" >            </td>
            <td width="12%" class=td1>单位电话</td>
            <td class="td4" width="30%"> 
              <input name="textfield3022152" type="text" id="txtsearch45225" class="input3" >            </td>
          </tr>
          <tr> 
            <td width="12%" class=td1>单位地址</td>
            <td class="td4"  colspan="2"> 
              <input name="textfield302242" type="text" id="txtsearch45225" class="input3" >            </td>
            <td width="12%"class=td1 >邮政编码</td>
            <td class="td4" width="17%"> 
              <input name="textfield3022102" type="text" id="txtsearch45225" class="input3" >            </td>
            <td width="12%" class=td1>账户余额</td>
            <td class="td4" width="30%"> 
              <input name="textfield3022162" type="text" id="txtsearch45225" class="input3" >            </td>
          </tr>
          <tr> 
            <td width="12%" class=td1>月工资额<font color="#FF0000">*</font></td>
            <td class="td4"  colspan="2"> 
              <input name="textfield302252" type="text" id="txtsearch45225" class="input3" >            </td>
            <td width="12%"class=td1 >月缴存额</td>
            <td class="td4" width="17%"> 
              <input name="textfield3022112" type="text" id="txtsearch45225" class="input3" >            </td>
            <td width="12%" class=td1>账户状态</td>
            <td class="td4" width="30%"> 
              <input name="textfield3022172" type="text" id="txtsearch45225" class="input3" >            </td>
          </tr>
          <tr> 
            <td width="12%" class=td1>初缴年月</td>
            <td class="td4"  colspan="2"> 
              <input name="textfield302262" type="text" id="txtsearch45225" class="input3" >            </td>
            <td width="12%"class=td1 >缴至年月</td>
            <td class="td4" width="17%"> 
              <input name="textfield3022122" type="text" id="txtsearch45225" class="input3" >            </td>
            <td width="12%" class=td1>&nbsp;</td>
            <td class="td7" width="30%">&nbsp;</td>
          </tr>
        </table>
        <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
          <tr valign="bottom"> 
            <td  colspan="7" bgcolor="#FFFFFF" align="center" height="30"> 
              <table border="0" cellspacing="0" cellpadding="0">
                <tr> 
                  <td width="110"> 
                    <input type="button" name="Submit2" value="查看职工明细账" class="buttonc" onClick="MM_openBrWindow('职工明细账.htm','','width=600,height=400')" >
                  </td>
                  <td width="70"> 
                    <input type="button" name="Submit" value="确定" class="buttona" >
                  </td>
                  <td width="70"> 
                    <input type="button" name="Submit3" value="扫描" class="buttona" >
                  </td>
                </tr>
              </table>
            </td>
          </tr>
        </table>
        <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
          <tr> 
            <td height="35"> 
              <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr> 
                  <td height="22" bgcolor="#CCCCCC" align="center" width="188"><b class="font14">辅助借款人列表</b></td>
                  <td width="703" height="22" align="center" background="images/bg2.gif">&nbsp;</td>
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
          <tr > 
            <td align="center" height="23" bgcolor="C4F0FF" >&nbsp;</td>
            <td align="center" class=td2 >职工编号<br>
            </td>
            <td align="center" class=td2 > 职工姓名</td>
            <td align="center" class=td2 >账户状态</td>
            <td align="center" class=td2 >账户余额</td>
            <td align="center" class=td2 >月工资额</td>
            <td align="center" class=td2 >月缴存额</td>
            <td align="center" class=td2 > 单位名称</td>
            <td align="center" class=td2 > 状态</td>
          </tr>
          <tr  class=td4 > 
            <td > 
              <input type="radio" name="radiobutton" value="radiobutton">
            </td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr > 
            <td colspan="10" class=td5 ></td>
          </tr>
          <tr class=td4 > 
            <td > 
              <input type="radio" name="radiobutton" value="radiobutton">
            </td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr > 
            <td colspan="10" class=td5 ></td>
          </tr>
          <tr  class=td4 > 
            <td > 
              <input type="radio" name="radiobutton" value="radiobutton">
            </td>
            <td >&nbsp;</td>
            <td >&nbsp;</td>
            <td >&nbsp;</td>
            <td >&nbsp;</td>
            <td >&nbsp;</td>
            <td >&nbsp;</td>
            <td >&nbsp;</td>
          </tr>
          <tr > 
            <td colspan="10" class=td5 ></td>
          </tr>
          <tr class=td4> 
            <td > 
              <input type="radio" name="radiobutton" value="radiobutton">
            </td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
          <tr > 
            <td colspan="10" class=td5 height="37" ></td>
          </tr>
        </table>
        <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
          <tr class="td1"> 
            <td>&nbsp; </td>
          </tr>
        </table>
        <table width="95%" border="0" cellspacing="0" cellpadding="3" align="center">
          <tr valign="bottom"> 
            <td  colspan="7" bgcolor="#FFFFFF" align="center" height="30"> 
              <table border="0" cellspacing="0" cellpadding="0">
                <tr> 
                  <td width="70"> 
                    <input type="submit" name="Submit4" value="修改" class="buttona"  >
                  </td>
                  <td width="70"> 
                    <input type="submit" name="Submit42" value="删除" class="buttona"  >
                  </td>
                </tr>
              </table>
            </td>
          </tr>
        </table>
      </td>
  </tr>
</table>
</form></body>
<script language="javascript">
	function easyStyle(){
		window.location.href="工资基数调整_简约风格.html";
	}
</script>
</html:html>

