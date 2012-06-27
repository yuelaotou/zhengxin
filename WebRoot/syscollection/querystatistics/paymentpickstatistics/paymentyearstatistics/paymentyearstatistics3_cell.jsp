<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page  import="java.lang.*" import="java.util.*" import="java.math.BigDecimal"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic"%>
<%@ page import="org.xpup.hafmis.syscollection.querystatistics.paymentpickstatistics.paymentyearstatistics.dto.PaymentyearstatisticsDTO" %>
<%@ include file="/checkUrl.jsp"%>

<%
   String path=request.getContextPath();
 %>
<html>
  <head>
  <title>市本级</title>
  <script src="<%=path%>/js/common.js">
  </script>
  </head>

<script type="text/javascript">
	function load(){	
	loginReg();
	//加载模板文件..
	document.forms(0).Cell1.openfile("<%=path%>/syscollection/querystatistics/paymentpickstatistics/paymentyearstatistics/report/paymentyearstatistics3.cll","");

	<%
	 PaymentyearstatisticsDTO paymentyearstatisticsDTO = new PaymentyearstatisticsDTO();
	 paymentyearstatisticsDTO=(PaymentyearstatisticsDTO)request.getSession().getAttribute("paymentyearstatisticsDTO");
	 String userName=paymentyearstatisticsDTO.getOperator();
	 String date=paymentyearstatisticsDTO.getDate();
	 
	 //一月
        int nh_hs1 = paymentyearstatisticsDTO.getNh_hs1();//农行户数
        int nh_rs1 = paymentyearstatisticsDTO.getNh_rs1();//农行人数
        BigDecimal nh_je1 = paymentyearstatisticsDTO.getNh_je1();//农行人数
        int zh_hs1 = paymentyearstatisticsDTO.getZh_hs1();//中行户数
        int zh_rs1 = paymentyearstatisticsDTO.getZh_rs1();//中行人数
        BigDecimal zh_je1 = paymentyearstatisticsDTO.getZh_je1();//中行人数
        int gh_hs1 = paymentyearstatisticsDTO.getGh_hs1();//工行户数
        int gh_rs1 = paymentyearstatisticsDTO.getGh_rs1();//工行人数
        BigDecimal gh_je1 = paymentyearstatisticsDTO.getGh_je1();//行人数
        int jh_hs1 = paymentyearstatisticsDTO.getJh_hs1();//建行户数
        int jh_rs1 = paymentyearstatisticsDTO.getJh_rs1();//建行人数
        BigDecimal jh_je1 = paymentyearstatisticsDTO.getJh_je1();//建行人数
        BigDecimal sum_1 = paymentyearstatisticsDTO.getSum_1();//一月合计
  
//二月
        int nh_hs2 = paymentyearstatisticsDTO.getNh_hs2();//农行户数
        int nh_rs2 = paymentyearstatisticsDTO.getNh_rs2();//农行人数
        BigDecimal nh_je2 = paymentyearstatisticsDTO.getNh_je2();//农行人数
        int zh_hs2 = paymentyearstatisticsDTO.getZh_hs2();//中行户数
        int zh_rs2 = paymentyearstatisticsDTO.getZh_rs2();//中行人数
        BigDecimal zh_je2 = paymentyearstatisticsDTO.getZh_je2();//中行人数
        int gh_hs2 = paymentyearstatisticsDTO.getGh_hs2();//工行户数
        int gh_rs2 = paymentyearstatisticsDTO.getGh_rs2();//工行人数
        BigDecimal gh_je2 = paymentyearstatisticsDTO.getGh_je2();//行人数
        int jh_hs2 = paymentyearstatisticsDTO.getJh_hs2();//建行户数
        int jh_rs2 = paymentyearstatisticsDTO.getJh_rs2();//建行人数
        BigDecimal jh_je2 = paymentyearstatisticsDTO.getJh_je2();//建行人数
        BigDecimal sum_2 = paymentyearstatisticsDTO.getSum_2();//一月合计
  
//三月
        int nh_hs3 = paymentyearstatisticsDTO.getNh_hs3();//农行户数
        int nh_rs3 = paymentyearstatisticsDTO.getNh_rs3();//农行人数
        BigDecimal nh_je3 = paymentyearstatisticsDTO.getNh_je3();//农行人数
        int zh_hs3 = paymentyearstatisticsDTO.getZh_hs3();//中行户数
        int zh_rs3 = paymentyearstatisticsDTO.getZh_rs3();//中行人数
        BigDecimal zh_je3 = paymentyearstatisticsDTO.getZh_je3();//中行人数
        int gh_hs3 = paymentyearstatisticsDTO.getGh_hs3();//工行户数
        int gh_rs3 = paymentyearstatisticsDTO.getGh_rs3();//工行人数
        BigDecimal gh_je3 = paymentyearstatisticsDTO.getGh_je3();//行人数
        int jh_hs3 = paymentyearstatisticsDTO.getJh_hs3();//建行户数
        int jh_rs3 = paymentyearstatisticsDTO.getJh_rs3();//建行人数
        BigDecimal jh_je3 = paymentyearstatisticsDTO.getJh_je3();//建行人数
        BigDecimal sum_3 = paymentyearstatisticsDTO.getSum_3();//一月合计
  
//四月
        int nh_hs4 = paymentyearstatisticsDTO.getNh_hs4();//农行户数
        int nh_rs4 = paymentyearstatisticsDTO.getNh_rs4();//农行人数
        BigDecimal nh_je4 = paymentyearstatisticsDTO.getNh_je4();//农行人数
        int zh_hs4 = paymentyearstatisticsDTO.getZh_hs4();//中行户数
        int zh_rs4 = paymentyearstatisticsDTO.getZh_rs4();//中行人数
        BigDecimal zh_je4 = paymentyearstatisticsDTO.getZh_je4();//中行人数
        int gh_hs4 = paymentyearstatisticsDTO.getGh_hs4();//工行户数
        int gh_rs4 = paymentyearstatisticsDTO.getGh_rs4();//工行人数
        BigDecimal gh_je4 = paymentyearstatisticsDTO.getGh_je4();//行人数
        int jh_hs4 = paymentyearstatisticsDTO.getJh_hs4();//建行户数
        int jh_rs4 = paymentyearstatisticsDTO.getJh_rs4();//建行人数
        BigDecimal jh_je4 = paymentyearstatisticsDTO.getJh_je4();//建行人数
        BigDecimal sum_4 = paymentyearstatisticsDTO.getSum_4();//一月合计
  
//五月
        int nh_hs5 = paymentyearstatisticsDTO.getNh_hs5();//农行户数
        int nh_rs5 = paymentyearstatisticsDTO.getNh_rs5();//农行人数
        BigDecimal nh_je5 = paymentyearstatisticsDTO.getNh_je5();//农行人数
        int zh_hs5 = paymentyearstatisticsDTO.getZh_hs5();//中行户数
        int zh_rs5 = paymentyearstatisticsDTO.getZh_rs5();//中行人数
        BigDecimal zh_je5 = paymentyearstatisticsDTO.getZh_je5();//中行人数
        int gh_hs5 = paymentyearstatisticsDTO.getGh_hs5();//工行户数
        int gh_rs5 = paymentyearstatisticsDTO.getGh_rs5();//工行人数
        BigDecimal gh_je5 = paymentyearstatisticsDTO.getGh_je5();//行人数
        int jh_hs5 = paymentyearstatisticsDTO.getJh_hs5();//建行户数
        int jh_rs5 = paymentyearstatisticsDTO.getJh_rs5();//建行人数
        BigDecimal jh_je5 = paymentyearstatisticsDTO.getJh_je5();//建行人数
        BigDecimal sum_5 = paymentyearstatisticsDTO.getSum_5();//一月合计
  
//六月
        int nh_hs6 = paymentyearstatisticsDTO.getNh_hs6();//农行户数
        int nh_rs6 = paymentyearstatisticsDTO.getNh_rs6();//农行人数
        BigDecimal nh_je6 = paymentyearstatisticsDTO.getNh_je6();//农行人数
        int zh_hs6 = paymentyearstatisticsDTO.getZh_hs6();//中行户数
        int zh_rs6 = paymentyearstatisticsDTO.getZh_rs6();//中行人数
        BigDecimal zh_je6 = paymentyearstatisticsDTO.getZh_je6();//中行人数
        int gh_hs6 = paymentyearstatisticsDTO.getGh_hs6();//工行户数
        int gh_rs6 = paymentyearstatisticsDTO.getGh_rs6();//工行人数
        BigDecimal gh_je6 = paymentyearstatisticsDTO.getGh_je6();//行人数
        int jh_hs6 = paymentyearstatisticsDTO.getJh_hs6();//建行户数
        int jh_rs6 = paymentyearstatisticsDTO.getJh_rs6();//建行人数
        BigDecimal jh_je6 = paymentyearstatisticsDTO.getJh_je6();//建行人数
        BigDecimal sum_6 = paymentyearstatisticsDTO.getSum_6();//一月合计
  
//七月
        int nh_hs7 = paymentyearstatisticsDTO.getNh_hs7();//农行户数
        int nh_rs7 = paymentyearstatisticsDTO.getNh_rs7();//农行人数
        BigDecimal nh_je7 = paymentyearstatisticsDTO.getNh_je7();//农行人数
        int zh_hs7 = paymentyearstatisticsDTO.getZh_hs7();//中行户数
        int zh_rs7 = paymentyearstatisticsDTO.getZh_rs7();//中行人数
        BigDecimal zh_je7 = paymentyearstatisticsDTO.getZh_je7();//中行人数
        int gh_hs7 = paymentyearstatisticsDTO.getGh_hs7();//工行户数
        int gh_rs7 = paymentyearstatisticsDTO.getGh_rs7();//工行人数
        BigDecimal gh_je7 = paymentyearstatisticsDTO.getGh_je7();//行人数
        int jh_hs7 = paymentyearstatisticsDTO.getJh_hs7();//建行户数
        int jh_rs7 = paymentyearstatisticsDTO.getJh_rs7();//建行人数
        BigDecimal jh_je7 = paymentyearstatisticsDTO.getJh_je7();//建行人数
        BigDecimal sum_7 = paymentyearstatisticsDTO.getSum_7();//一月合计
  
//八月
        int nh_hs8 = paymentyearstatisticsDTO.getNh_hs8();//农行户数
        int nh_rs8 = paymentyearstatisticsDTO.getNh_rs8();//农行人数
        BigDecimal nh_je8 = paymentyearstatisticsDTO.getNh_je8();//农行人数
        int zh_hs8 = paymentyearstatisticsDTO.getZh_hs8();//中行户数
        int zh_rs8 = paymentyearstatisticsDTO.getZh_rs8();//中行人数
        BigDecimal zh_je8 = paymentyearstatisticsDTO.getZh_je8();//中行人数
        int gh_hs8 = paymentyearstatisticsDTO.getGh_hs8();//工行户数
        int gh_rs8 = paymentyearstatisticsDTO.getGh_rs8();//工行人数
        BigDecimal gh_je8 = paymentyearstatisticsDTO.getGh_je8();//行人数
        int jh_hs8 = paymentyearstatisticsDTO.getJh_hs8();//建行户数
        int jh_rs8 = paymentyearstatisticsDTO.getJh_rs8();//建行人数
        BigDecimal jh_je8 = paymentyearstatisticsDTO.getJh_je8();//建行人数
        BigDecimal sum_8 = paymentyearstatisticsDTO.getSum_8();//一月合计
  
//九月
        int nh_hs9 = paymentyearstatisticsDTO.getNh_hs9();//农行户数
        int nh_rs9 = paymentyearstatisticsDTO.getNh_rs9();//农行人数
        BigDecimal nh_je9 = paymentyearstatisticsDTO.getNh_je9();//农行人数
        int zh_hs9 = paymentyearstatisticsDTO.getZh_hs9();//中行户数
        int zh_rs9 = paymentyearstatisticsDTO.getZh_rs9();//中行人数
        BigDecimal zh_je9 = paymentyearstatisticsDTO.getZh_je9();//中行人数
        int gh_hs9 = paymentyearstatisticsDTO.getGh_hs9();//工行户数
        int gh_rs9 = paymentyearstatisticsDTO.getGh_rs9();//工行人数
        BigDecimal gh_je9 = paymentyearstatisticsDTO.getGh_je9();//行人数
        int jh_hs9 = paymentyearstatisticsDTO.getJh_hs9();//建行户数
        int jh_rs9 = paymentyearstatisticsDTO.getJh_rs9();//建行人数
        BigDecimal jh_je9 = paymentyearstatisticsDTO.getJh_je9();//建行人数
        BigDecimal sum_9 = paymentyearstatisticsDTO.getSum_9();//一月合计
  
//十月
        int nh_hs10 = paymentyearstatisticsDTO.getNh_hs10();//农行户数
        int nh_rs10 = paymentyearstatisticsDTO.getNh_rs10();//农行人数
        BigDecimal nh_je10 = paymentyearstatisticsDTO.getNh_je10();//农行人数
        int zh_hs10 = paymentyearstatisticsDTO.getZh_hs10();//中行户数
        int zh_rs10 = paymentyearstatisticsDTO.getZh_rs10();//中行人数
        BigDecimal zh_je10 = paymentyearstatisticsDTO.getZh_je10();//中行人数
        int gh_hs10 = paymentyearstatisticsDTO.getGh_hs10();//工行户数
        int gh_rs10 = paymentyearstatisticsDTO.getGh_rs10();//工行人数
        BigDecimal gh_je10 = paymentyearstatisticsDTO.getGh_je10();//行人数
        int jh_hs10 = paymentyearstatisticsDTO.getJh_hs10();//建行户数
        int jh_rs10 = paymentyearstatisticsDTO.getJh_rs10();//建行人数
        BigDecimal jh_je10 = paymentyearstatisticsDTO.getJh_je10();//建行人数
        BigDecimal sum_10 = paymentyearstatisticsDTO.getSum_10();//一月合计
  
//十一月
        int nh_hs11 = paymentyearstatisticsDTO.getNh_hs11();//农行户数
        int nh_rs11 = paymentyearstatisticsDTO.getNh_rs11();//农行人数
        BigDecimal nh_je11 = paymentyearstatisticsDTO.getNh_je11();//农行人数
        int zh_hs11 = paymentyearstatisticsDTO.getZh_hs11();//中行户数
        int zh_rs11 = paymentyearstatisticsDTO.getZh_rs11();//中行人数
        BigDecimal zh_je11 = paymentyearstatisticsDTO.getZh_je11();//中行人数
        int gh_hs11 = paymentyearstatisticsDTO.getGh_hs11();//工行户数
        int gh_rs11 = paymentyearstatisticsDTO.getGh_rs11();//工行人数
        BigDecimal gh_je11 = paymentyearstatisticsDTO.getGh_je11();//行人数
        int jh_hs11 = paymentyearstatisticsDTO.getJh_hs11();//建行户数
        int jh_rs11 = paymentyearstatisticsDTO.getJh_rs11();//建行人数
        BigDecimal jh_je11 = paymentyearstatisticsDTO.getJh_je11();//建行人数
        BigDecimal sum_11 = paymentyearstatisticsDTO.getSum_11();//一月合计
  
//十二月
        int nh_hs12 = paymentyearstatisticsDTO.getNh_hs12();//农行户数
        int nh_rs12 = paymentyearstatisticsDTO.getNh_rs12();//农行人数
        BigDecimal nh_je12 = paymentyearstatisticsDTO.getNh_je12();//农行人数
        int zh_hs12 = paymentyearstatisticsDTO.getZh_hs12();//中行户数
        int zh_rs12 = paymentyearstatisticsDTO.getZh_rs12();//中行人数
        BigDecimal zh_je12 = paymentyearstatisticsDTO.getZh_je12();//中行人数
        int gh_hs12 = paymentyearstatisticsDTO.getGh_hs12();//工行户数
        int gh_rs12 = paymentyearstatisticsDTO.getGh_rs12();//工行人数
        BigDecimal gh_je12 = paymentyearstatisticsDTO.getGh_je12();//行人数
        int jh_hs12 = paymentyearstatisticsDTO.getJh_hs12();//建行户数
        int jh_rs12 = paymentyearstatisticsDTO.getJh_rs12();//建行人数
        BigDecimal jh_je12 = paymentyearstatisticsDTO.getJh_je12();//建行人数
        BigDecimal sum_12 = paymentyearstatisticsDTO.getSum_12();//一月合计
  
  //合计
        int sum_a = paymentyearstatisticsDTO.getSum_a();//
        int sum_b = paymentyearstatisticsDTO.getSum_b();//
        BigDecimal sum_c = paymentyearstatisticsDTO.getSum_c();//
        int sum_d = paymentyearstatisticsDTO.getSum_d();
        int sum_e = paymentyearstatisticsDTO.getSum_e();//
        BigDecimal sum_f = paymentyearstatisticsDTO.getSum_f();//
        int sum_g = paymentyearstatisticsDTO.getSum_g();//
        int sum_h = paymentyearstatisticsDTO.getSum_h();//
        BigDecimal sum_i = paymentyearstatisticsDTO.getSum_i();//
        int sum_j = paymentyearstatisticsDTO.getSum_j();//
        int sum_k = paymentyearstatisticsDTO.getSum_k();//
        BigDecimal sum_l = paymentyearstatisticsDTO.getSum_l();//
        BigDecimal sum_m = paymentyearstatisticsDTO.getSum_m();//
	%>
	
	    document.forms(0).Cell1.S(1,4,0,"<%=paymentyearstatisticsDTO.getOffice() %>");
	    document.forms(0).Cell1.S(3,4,0,"<%=date %>");
	    document.forms(0).Cell1.S(5,21,0,"<%=userName %>");
	    //-------一月-------
	    
	    //农行
	    document.forms(0).Cell1.S(2,7,0,"<%=nh_hs1 %>");
		document.forms(0).Cell1.S(3,7,0,"<%=nh_rs1 %>");
		document.forms(0).Cell1.d(4,7,0,"<%=nh_je1 %>");
		
		//合计
		document.forms(0).Cell1.d(5,7,0,"<%=sum_1 %>");
		
	    //-------二月-------
	    
	    //农行
	    document.forms(0).Cell1.S(2,8,0,"<%=nh_hs2 %>");
		document.forms(0).Cell1.S(3,8,0,"<%=nh_rs2 %>");
		document.forms(0).Cell1.d(4,8,0,"<%=nh_je2 %>");
		
		//合计
		document.forms(0).Cell1.d(5,8,0,"<%=sum_2 %>");
		
	    //-------三月-------
	    
	    //农行
	    document.forms(0).Cell1.S(2,9,0,"<%=nh_hs3 %>");
		document.forms(0).Cell1.S(3,9,0,"<%=nh_rs3 %>");
		document.forms(0).Cell1.d(4,9,0,"<%=nh_je3 %>");
		
		//合计
		document.forms(0).Cell1.d(5,9,0,"<%=sum_3 %>");
		
	    //-------四月-------
	    
	    //农行
	    document.forms(0).Cell1.S(2,10,0,"<%=nh_hs4 %>");
		document.forms(0).Cell1.S(3,10,0,"<%=nh_rs4 %>");
		document.forms(0).Cell1.d(4,10,0,"<%=nh_je4 %>");
		
		//合计
		document.forms(0).Cell1.d(5,10,0,"<%=sum_4 %>");
		
	    //-------五月-------
	    
	    //农行
	    document.forms(0).Cell1.S(2,11,0,"<%=nh_hs5 %>");
		document.forms(0).Cell1.S(3,11,0,"<%=nh_rs5 %>");
		document.forms(0).Cell1.d(4,11,0,"<%=nh_je5 %>");
		
		//合计
		document.forms(0).Cell1.d(5,11,0,"<%=sum_5 %>");
		
	    //-------六月-------
	    
	    //农行
	    document.forms(0).Cell1.S(2,12,0,"<%=nh_hs6 %>");
		document.forms(0).Cell1.S(3,12,0,"<%=nh_rs6 %>");
		document.forms(0).Cell1.d(4,12,0,"<%=nh_je6 %>");
		
		//合计
		document.forms(0).Cell1.d(5,12,0,"<%=sum_6 %>");
		
	    //-------七月-------
	    
	    //农行
	    document.forms(0).Cell1.S(2,13,0,"<%=nh_hs7 %>");
		document.forms(0).Cell1.S(3,13,0,"<%=nh_rs7 %>");
		document.forms(0).Cell1.d(4,13,0,"<%=nh_je7 %>");
		
		//合计
		document.forms(0).Cell1.d(5,13,0,"<%=sum_7 %>");
		
	    //-------八月-------
	    
	    //农行
	    document.forms(0).Cell1.S(2,14,0,"<%=nh_hs8 %>");
		document.forms(0).Cell1.S(3,14,0,"<%=nh_rs8 %>");
		document.forms(0).Cell1.d(4,14,0,"<%=nh_je8 %>");
		
		//合计
		document.forms(0).Cell1.d(5,14,0,"<%=sum_8 %>");
		
	    //-------九月-------
	    
	    //农行
	    document.forms(0).Cell1.S(2,15,0,"<%=nh_hs9 %>");
		document.forms(0).Cell1.S(3,15,0,"<%=nh_rs9 %>");
		document.forms(0).Cell1.d(4,15,0,"<%=nh_je9 %>");
		
		//合计
		document.forms(0).Cell1.d(5,15,0,"<%=sum_9 %>");
		
	    //-------十月-------
	    
	    //农行
	    document.forms(0).Cell1.S(2,16,0,"<%=nh_hs10 %>");
		document.forms(0).Cell1.S(3,16,0,"<%=nh_rs10 %>");
		document.forms(0).Cell1.d(4,16,0,"<%=nh_je10 %>");
		
		//合计
		document.forms(0).Cell1.d(5,16,0,"<%=sum_10 %>");
		
	    //-------十一月-------
	    
	    //农行
	    document.forms(0).Cell1.S(2,17,0,"<%=nh_hs11 %>");
		document.forms(0).Cell1.S(3,17,0,"<%=nh_rs11 %>");
		document.forms(0).Cell1.d(4,17,0,"<%=nh_je11 %>");
		
		//合计
		document.forms(0).Cell1.d(5,17,0,"<%=sum_11 %>");
		
	    //-------十二月-------
	    
	    //农行
	    document.forms(0).Cell1.S(2,18,0,"<%=nh_hs12 %>");
		document.forms(0).Cell1.S(3,18,0,"<%=nh_rs12 %>");
		document.forms(0).Cell1.d(4,18,0,"<%=nh_je12 %>");
		
		//合计
		document.forms(0).Cell1.d(5,18,0,"<%=sum_12 %>");
		
		//-------合计-------
		
		//农行
	    document.forms(0).Cell1.S(2,19,0,"<%=sum_a %>");
		document.forms(0).Cell1.S(3,19,0,"<%=sum_b %>");
		document.forms(0).Cell1.d(4,19,0,"<%=sum_c %>");
		
		//合计
		document.forms(0).Cell1.d(5,19,0,"<%=sum_m %>");
}

function printPreview(){
		var k=document.forms(0).Cell1.GetCurSheet();//显示打印预览那个页面
		document.forms(0).Cell1.printPreviewEx(1,k,false);
	}
	function Button1_onclick()
	{
		document.forms(0).Cell1.SaveFile();
	}
	function Button2_onclick()
	{
		document.forms(0).Cell1.ExportPdfFile("d:\\aa",-1,1,1);
	}
	function Button3_onclick()
	{
		document.forms(0).Cell1.PrintPageSetup();
	}
	function Button4_onclick()
	{
		document.forms(0).Cell1.FindDialogEx( 0,"");
	}
		function Button5_onclick()
	{
		document.forms(0).Cell1.ImportExcelDlg();
	}
	function printsheet(){//真正的打印
		var k=document.forms(0).Cell1.GetCurSheet();//显示打印预览那个页面--固定
		document.forms(0).Cell1.PrintSheet(1,k);//固定...
	}		
</script>
	<script language="JScript.Encode">
eval(unescape('function%20LoginRegister%28%29//%u6CE8%u518CCELL%0D%0A%20%7B%20%0D%0A%20document.forms%280%29.Cell1.Login%28%22%u6C88%u9633%u91D1%u8F6F%u79D1%u6280%u6709%u9650%u516C%u53F8%22%2C%22%22%2C%2213100104509%22%2C%220740-1662-7775-3003%22%29%3B%20%20%20%20%0D%0A%20%7D'))
</script>
	<body onContextmenu="return false" onload="load();">
		<form action="">
			<table align="center">
				<tr>
					<OBJECT id=Cell1
						style="LEFT:0px;WIDTH:700px;  TOP:0px;HEIGHT:400px"
						codeBase="http://192.168.1.44:8088/hafmis/CellWeb5.CAB#version=5,3,7,321"
						classid=clsid:3F166327-8030-4881-8BD2-EA25350E574A VIEWASTEXT>
						<PARAM NAME="_Version" VALUE="65536">
						<PARAM NAME="_ExtentX" VALUE="10266">
						<PARAM NAME="_ExtentY" VALUE="7011">
						<PARAM NAME="_StockProps" VALUE="0">
					</OBJECT>
				</tr>
				<tr>
					<td>
						<input type="button" name="print" value="打印预览"
							onclick="printPreview();" />
					</td>
					<td>
						<INPUT id="Button1" onclick="Button1_onclick()" type="button"
							value="另存为Excel" name="Button1" />
					</td>
					<td>
						<INPUT id="Button1" onclick="Button2_onclick()" type="button"
							value="另存为pdf" name="Button1" />
					</td>
					<td>
						<INPUT id="Button3" style="WIDTH: 100px"
							onclick="Button3_onclick()" type="button" value="页面设置" />
					</td>
					<td>
						<INPUT id="Button3" style="WIDTH: 100px"
							onclick="Button4_onclick()" type="button" value="查找对话框" />
					</td>
					<td>
						<INPUT id="Button3" style="WIDTH: 100px"
							onclick="Button5_onclick()" type="button" value="excel导入" />
					</td>
					<td>
						<INPUT id="Button1" onclick="printsheet()" type="button"
							value=" 打印 " name="Button1" />
					</td>
					<td>
						<INPUT id="Button3" onclick="location.href='to_paymentyearstatisticsShowAC.do'"
							type="button" value=" 返回 " />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
