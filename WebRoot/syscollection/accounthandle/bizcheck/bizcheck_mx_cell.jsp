<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.lang.*" import="java.util.*" import="java.math.*"%>
<%@ page import="org.xpup.hafmis.syscollection.accounthandle.bizcheck.form.*" %>
<%@ page import="org.xpup.hafmis.syscollection.common.domain.entity.*" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic"%>
<%@ page
	import="org.xpup.hafmis.syscollection.common.domain.entity.ChgOrgRate"%>
<%@ include file="/../checkUrl.jsp"%>

<%
String path = request.getContextPath();
%>
<html>
	<head>
		<title>财务处理>>业务复核>>业务复核列表>>打印列表</title>
		<script src="<%=path%>/js/common.js">
</script>
	</head>

	<script type="text/javascript">
	function load(){	
	
    var empIdList=[];
    var empNameList=[];
    var carNumList=[];
    var moneyList=[];
    var interest=[];
    var i=0;
	<%
	BizcheckDetailAF bizcheckDetailAF=(BizcheckDetailAF) request.getSession().getAttribute("bizcheckDetailAF");
	EmpHAFAccountFlow empHAFAccountFlow=null;
	TranInTail tranInTail = null;
	String type = bizcheckDetailAF.getBiz_type();
	
	if(type.equals("补缴") || type.equals("调提取") || type.equals("调缴存") || type.equals("调其他") ){
	%>
	document.forms(0).Cell1.openfile("<%=path%>/syscollection/accounthandle/bizcheck/report/bizcheck1.cll","");
	<%
    
	List list = new ArrayList();
	list = (List)request.getSession().getAttribute("otherlist");
	for(int j=0;j<list.size();j++){
	empHAFAccountFlow = (EmpHAFAccountFlow)list.get(j);
	BigDecimal debit = empHAFAccountFlow.getDebit();
	BigDecimal credit = empHAFAccountFlow.getCredit();
	String money = "";
	if(debit.toString().equals("0") || debit.toString() == null){
	 money = credit.toString();
	}
	if(credit.toString().equals("0") || credit.toString() == null){
	 money = debit.toString();
	}
	if(credit.toString().equals("0") || credit.toString() == null && debit.toString().equals("0") || debit.toString() == null){
	money = "0.00";
	}
	%>
	   empIdList[i]=format("<%=empHAFAccountFlow.getEmpId()%>")+"<%=empHAFAccountFlow.getEmpId()%>";   //--1
	   empNameList[i]="<%=empHAFAccountFlow.getEmpName()%>";//--2
	   carNumList[i]="<%=empHAFAccountFlow.getEmp().getEmpInfo().getCardNum()%>";//--3
	   moneyList[i]="<%=money%>";//--4
	   interest[i]="<%=empHAFAccountFlow.getInterest() %>";
	   i++;
	<%
	}
	%>
	 var totalLine=empNameList.length;      //总行数
	  var totalPageline=15;                  //每页显示15行
	  var pageTotal=totalLine/totalPageline; //计算共几页
	  var pageCurrent=0;                     //当前页
	  var completeline=0;                    //记录行
	   for(k=0;k<totalLine;k++){
	  if(k%totalLine==0 && k>0){
	   // document.forms(0).Cell1.S(7,totalLine+4,pageCurrent,"本页小计:");
	   // document.forms(0).Cell1.SetFormula (8,totalLine+4,pageCurrent,"Sum(E3:E"+(totalLine+2)+")" );
	    document.forms(0).Cell1.ReDraw();   //重绘表格
	     pageCurrent++;                     //加一页
	     completeline=k-2;
	     document.forms(0).Cell1.insertSheetFromFile("<%=path%>/syscollection/accounthandle/bizcheck/report/bizcheck1.cll",0,1,pageCurrent);
	     document.forms(0).Cell1.setSheetlabel(pageCurrent,"第"+(pageCurrent+1)+"页");
	  }
	  if(pageCurrent==0){

	  var temp_noteNum="<%=bizcheckDetailAF.getNoteNum()%>";
	  var settdate="<%= bizcheckDetailAF.getSettDate() %>";
	  if(temp_noteNum==null){
	   document.forms(0).Cell1.S(3,2,0,"");
	  }else{
	    document.forms(0).Cell1.S(3,2,0,"<%=bizcheckDetailAF.getNoteNum()%>");
	    }
	    if(settdate==null){
	      document.forms(0).Cell1.S(7,5,0,"");
	    }else{
	      document.forms(0).Cell1.S(7,5,0,"<%= bizcheckDetailAF.getSettDate() %>");
	    }
	    document.forms(0).Cell1.S(7,2,0,"<%=bizcheckDetailAF.getDocNum()%>");
	    document.forms(0).Cell1.S(3,3,0,"<%=bizcheckDetailAF.getOperator()%>");
	    document.forms(0).Cell1.S(7,3,0,"<%=bizcheckDetailAF.getBank()%>");
	    document.forms(0).Cell1.S(3,4,0,"<%=bizcheckDetailAF.getTraninId()%>");
	    document.forms(0).Cell1.S(7,4,0,"<%=bizcheckDetailAF.getTraninName()%>");
	    document.forms(0).Cell1.S(3,5,0,"<%=bizcheckDetailAF.getBiz_type() %>");
	    document.forms(0).Cell1.S(8,5,0,"<%=bizcheckDetailAF.getSettDate() %>");
	    document.forms(0).Cell1.S(1,k+8,0,empIdList[k]);
	    document.forms(0).Cell1.S(3,k+8,0,empNameList[k]);
	    document.forms(0).Cell1.S(5,k+8,0,carNumList[k]);
	    document.forms(0).Cell1.S(8,k+8,0,moneyList[k]);
	    document.forms(0).Cell1.S(10,k+8,0,interest[k]);
	  	
	    //premoneytotal=premoneytotal+parseFloat(lastMonthCollectList[k]);
	   // moneytotal=moneytotal+parseFloat(thisMonthCollectList[k]);
	  }else{//向第一页后的所有页插数据
	    
	    document.forms(0).Cell1.S(1,k-completeline,pageCurrent,empIdList[k]);
	    document.forms(0).Cell1.S(2,k-completeline,pageCurrent,empNameList[k]);
	    document.forms(0).Cell1.S(3,k-completeline,pageCurrent,carNumList[k]);
	    document.forms(0).Cell1.S(4,k-completeline,pageCurrent,moneyList[k]);
	    document.forms(0).Cell1.S(5,k-completeline,pageCurrent,interest[k]);
	   
	   }
	  }
	<%
	}
	%>
	
	
	
	
	 <%
    if(type.equals("汇缴") || type.equals("单位补缴")){
	%>
	document.forms(0).Cell1.openfile("<%=path%>/syscollection/accounthandle/bizcheck/report/monthpay.cll","");
	
	<%
    
	List list = new ArrayList();
	list = (List)request.getSession().getAttribute("otherlist");
	for(int j=0;j<list.size();j++){
	empHAFAccountFlow = (EmpHAFAccountFlow)list.get(j);
	BigDecimal debit = empHAFAccountFlow.getDebit();
	BigDecimal credit = empHAFAccountFlow.getCredit();
	String money = "";
	if(debit.toString().equals("0") || debit.toString() == null){
	 money = credit.toString();
	}
	if(credit.toString().equals("0") || credit.toString() == null){
	 money = debit.toString();
	}
	if(credit.toString().equals("0") || credit.toString() == null && debit.toString().equals("0") || debit.toString() == null){
	money = "0.00";
	}
	%>
	   empIdList[i]=format("<%=empHAFAccountFlow.getEmpId()%>")+"<%=empHAFAccountFlow.getEmpId()%>";   //--1
	   empNameList[i]="<%=empHAFAccountFlow.getEmpName()%>";//--2
	   carNumList[i]="<%=empHAFAccountFlow.getEmp().getEmpInfo().getCardNum()%>";//--3
	   moneyList[i]="<%=money%>";//--4
	   interest[i]="<%=empHAFAccountFlow.getInterest() %>";
	   i++;
	<%
	}
	%>
	 var totalLine=empNameList.length;      //总行数
	  var totalPageline=15;                  //每页显示15行
	  var pageTotal=totalLine/totalPageline; //计算共几页
	  var pageCurrent=0;                     //当前页
	  var completeline=0;                    //记录行
	   for(k=0;k<totalLine;k++){
	  if(k%totalLine==0 && k>0){
	   // document.forms(0).Cell1.S(7,totalLine+4,pageCurrent,"本页小计:");
	   // document.forms(0).Cell1.SetFormula (8,totalLine+4,pageCurrent,"Sum(E3:E"+(totalLine+2)+")" );
	    document.forms(0).Cell1.ReDraw();   //重绘表格
	     pageCurrent++;                     //加一页
	     completeline=k-2;
	     document.forms(0).Cell1.insertSheetFromFile("<%=path%>/syscollection/accounthandle/bizcheck/report/bizcheck1.cll",0,1,pageCurrent);
	     document.forms(0).Cell1.setSheetlabel(pageCurrent,"第"+(pageCurrent+1)+"页");
	  }
	  if(pageCurrent==0){

	  var temp_noteNum="<%=bizcheckDetailAF.getNoteNum()%>";
	  var settdate="<%= bizcheckDetailAF.getSettDate() %>";
	  if(temp_noteNum==null){
	   document.forms(0).Cell1.S(3,2,0,"123");
	  }else{
	    document.forms(0).Cell1.S(3,2,0,"<%=bizcheckDetailAF.getNoteNum()%>");
	    }
	    if(settdate==null){
	      document.forms(0).Cell1.S(7,5,0,"");
	    }else{
	      document.forms(0).Cell1.S(7,5,0,"<%= bizcheckDetailAF.getSettDate() %>");
	    }
	    document.forms(0).Cell1.S(8,2,0,"<%=bizcheckDetailAF.getDocNum()%>");
	    document.forms(0).Cell1.S(3,3,0,"<%=bizcheckDetailAF.getOperator()%>");
	    document.forms(0).Cell1.S(8,3,0,"<%=bizcheckDetailAF.getBank()%>");
	    document.forms(0).Cell1.S(3,4,0,"<%=bizcheckDetailAF.getTraninId()%>");
	    document.forms(0).Cell1.S(8,4,0,"<%=bizcheckDetailAF.getTraninName()%>");
	    document.forms(0).Cell1.S(3,5,0,"<%=bizcheckDetailAF.getBiz_type() %>");
	    document.forms(0).Cell1.S(8,5,0,"<%=bizcheckDetailAF.getSettDate() %>");
	    document.forms(0).Cell1.S(1,k+8,0,empIdList[k]);
	    document.forms(0).Cell1.S(3,k+8,0,empNameList[k]);
	    document.forms(0).Cell1.S(5,k+8,0,carNumList[k]);
	    document.forms(0).Cell1.S(8,k+8,0,moneyList[k]);
	    document.forms(0).Cell1.S(10,k+8,0,interest[k]);
	  	
	    //premoneytotal=premoneytotal+parseFloat(lastMonthCollectList[k]);
	   // moneytotal=moneytotal+parseFloat(thisMonthCollectList[k]);
	  }else{//向第一页后的所有页插数据
	    
	    document.forms(0).Cell1.S(1,k-completeline,pageCurrent,empIdList[k]);
	    document.forms(0).Cell1.S(2,k-completeline,pageCurrent,empNameList[k]);
	    document.forms(0).Cell1.S(3,k-completeline,pageCurrent,carNumList[k]);
	    document.forms(0).Cell1.S(4,k-completeline,pageCurrent,moneyList[k]);
	   
	   }
	  }
	
	<%
	}
	%>
	
	
	
	
	<%
	if(type.equals("转出")){
    %>
	document.forms(0).Cell1.openfile("<%=path%>/syscollection/accounthandle/bizcheck/report/tranout.cll","");
	<%
    
	List list = new ArrayList();
	list = (List)request.getSession().getAttribute("otherlist");
	for(int j=0;j<list.size();j++){
	empHAFAccountFlow = (EmpHAFAccountFlow)list.get(j);
	BigDecimal debit = empHAFAccountFlow.getDebit();
	BigDecimal credit = empHAFAccountFlow.getCredit();
	String money = "";
	if(debit.toString().equals("0") || debit.toString() == null){
	 money = credit.toString();
	}
	if(credit.toString().equals("0") || credit.toString() == null){
	 money = debit.toString();
	}
	if(credit.toString().equals("0") || credit.toString() == null && debit.toString().equals("0") || debit.toString() == null){
	money = "0.00";
	}
	%>
	   empIdList[i]=format("<%=empHAFAccountFlow.getEmpId()%>")+"<%=empHAFAccountFlow.getEmpId()%>";   //--1
	   empNameList[i]="<%=empHAFAccountFlow.getEmpName()%>";//--2
	   carNumList[i]="<%=empHAFAccountFlow.getEmp().getEmpInfo().getCardNum()%>";//--3
	   moneyList[i]="<%=money%>";//--4
	   interest[i]="<%=empHAFAccountFlow.getInterest() %>";
	   i++;
	<%
	}
	%>
	 var totalLine=empNameList.length;      //总行数
	  var totalPageline=15;                  //每页显示15行
	  var pageTotal=totalLine/totalPageline; //计算共几页
	  var pageCurrent=0;                     //当前页
	  var completeline=0;                    //记录行
	   for(k=0;k<totalLine;k++){
	  if(k%totalLine==0 && k>0){
	   // document.forms(0).Cell1.S(7,totalLine+4,pageCurrent,"本页小计:");
	   // document.forms(0).Cell1.SetFormula (8,totalLine+4,pageCurrent,"Sum(E3:E"+(totalLine+2)+")" );
	    document.forms(0).Cell1.ReDraw();   //重绘表格
	     pageCurrent++;                     //加一页
	     completeline=k-2;
	     document.forms(0).Cell1.insertSheetFromFile("<%=path%>/syscollection/accounthandle/bizcheck/report/bizcheck1.cll",0,1,pageCurrent);
	     document.forms(0).Cell1.setSheetlabel(pageCurrent,"第"+(pageCurrent+1)+"页");
	  }
	  if(pageCurrent==0){

	  var temp_noteNum="<%=bizcheckDetailAF.getNoteNum()%>";
	  var settdate="<%= bizcheckDetailAF.getSettDate() %>";
	  if(temp_noteNum==null){
	   document.forms(0).Cell1.S(3,2,0,"123");
	  }else{
	    document.forms(0).Cell1.S(3,2,0,"<%=bizcheckDetailAF.getNoteNum()%>");
	    }
	    if(settdate==null){
	      document.forms(0).Cell1.S(7,5,0,"");
	    }else{
	      document.forms(0).Cell1.S(7,5,0,"<%= bizcheckDetailAF.getSettDate() %>");
	    }
	    document.forms(0).Cell1.S(8,2,0,"<%=bizcheckDetailAF.getDocNum()%>");
	    document.forms(0).Cell1.S(3,3,0,"<%=bizcheckDetailAF.getOperator()%>");
	    document.forms(0).Cell1.S(8,3,0,"<%=bizcheckDetailAF.getBank()%>");
	    document.forms(0).Cell1.S(3,4,0,"<%=bizcheckDetailAF.getTraninId()%>");
	    document.forms(0).Cell1.S(8,4,0,"<%=bizcheckDetailAF.getTraninName()%>");
	    document.forms(0).Cell1.S(3,5,0,"<%=bizcheckDetailAF.getBiz_type() %>");
	   // document.forms(0).Cell1.S(8,5,0,"<%=bizcheckDetailAF.getSettDate() %>");
	    document.forms(0).Cell1.S(1,k+8,0,empIdList[k]);
	    document.forms(0).Cell1.S(3,k+8,0,empNameList[k]);
	    document.forms(0).Cell1.S(5,k+8,0,carNumList[k]);
	    document.forms(0).Cell1.S(8,k+8,0,moneyList[k]);
	    document.forms(0).Cell1.S(10,k+8,0,interest[k]);
	  	
	    //premoneytotal=premoneytotal+parseFloat(lastMonthCollectList[k]);
	   // moneytotal=moneytotal+parseFloat(thisMonthCollectList[k]);
	  }else{//向第一页后的所有页插数据
	    
	    document.forms(0).Cell1.S(1,k-completeline,pageCurrent,empIdList[k]);
	    document.forms(0).Cell1.S(2,k-completeline,pageCurrent,empNameList[k]);
	    document.forms(0).Cell1.S(3,k-completeline,pageCurrent,carNumList[k]);
	    document.forms(0).Cell1.S(4,k-completeline,pageCurrent,moneyList[k]);
	   
	   }
	  }
    <%
    }
    %>
    
    
    
    
   <%
	if(type.equals("转入")){
    %>
	document.forms(0).Cell1.openfile("<%=path%>/syscollection/accounthandle/bizcheck/report/tranin.cll","");
	<%
    
	List list = new ArrayList();
	list = (List)request.getSession().getAttribute("ootherlist");
	for(int j=0;j<list.size();j++){
	tranInTail = (TranInTail)list.get(j);

	String money = "";

	%>
	   empIdList[i]=format("<%=tranInTail.getEmpId()%>")+"<%=tranInTail.getEmpId() %>";   //--1
	   empNameList[i]="<%= tranInTail.getName()%>";//--2
	   carNumList[i]="<%=tranInTail.getCardNum()%>";//--3
	   moneyList[i]="<%=tranInTail.getReserveaA() %>";//--4
	   interest[i]="<%=tranInTail.getReserveaB() %>";
	   i++;
	<%
	}
	%>
	 var totalLine=empNameList.length;      //总行数
	  var totalPageline=15;                  //每页显示15行
	  var pageTotal=totalLine/totalPageline; //计算共几页
	  var pageCurrent=0;                     //当前页
	  var completeline=0;                    //记录行
	   for(k=0;k<totalLine;k++){
	  if(k%totalLine==0 && k>0){
	   // document.forms(0).Cell1.S(7,totalLine+4,pageCurrent,"本页小计:");
	   // document.forms(0).Cell1.SetFormula (8,totalLine+4,pageCurrent,"Sum(E3:E"+(totalLine+2)+")" );
	    document.forms(0).Cell1.ReDraw();   //重绘表格
	     pageCurrent++;                     //加一页
	     completeline=k-2;
	     document.forms(0).Cell1.insertSheetFromFile("<%=path%>/syscollection/accounthandle/bizcheck/report/bizcheck1.cll",0,1,pageCurrent);
	     document.forms(0).Cell1.setSheetlabel(pageCurrent,"第"+(pageCurrent+1)+"页");
	  }
	  if(pageCurrent==0){

	  var temp_noteNum="<%=bizcheckDetailAF.getNoteNum()%>";
	  var settdate="<%= bizcheckDetailAF.getSettDate() %>";
	  if(temp_noteNum==null){
	   document.forms(0).Cell1.S(3,2,0,"123");
	  }else{
	    document.forms(0).Cell1.S(3,2,0,"<%=bizcheckDetailAF.getNoteNum()%>");
	    }
	    if(settdate==null){
	      document.forms(0).Cell1.S(7,5,0,"");
	    }else{
	      document.forms(0).Cell1.S(7,5,0,"<%= bizcheckDetailAF.getSettDate() %>");
	    }
	    document.forms(0).Cell1.S(8,2,0,"<%=bizcheckDetailAF.getDocNum()%>");
	    document.forms(0).Cell1.S(3,3,0,"<%=bizcheckDetailAF.getOperator()%>");
	    document.forms(0).Cell1.S(8,3,0,"<%=bizcheckDetailAF.getBank()%>");
	    document.forms(0).Cell1.S(3,4,0,"<%=bizcheckDetailAF.getTraninId()%>");
	    document.forms(0).Cell1.S(8,4,0,"<%=bizcheckDetailAF.getTraninName()%>");
	    document.forms(0).Cell1.S(3,5,0,"<%=bizcheckDetailAF.getBiz_type() %>");
	   // document.forms(0).Cell1.S(8,5,0,"<%=bizcheckDetailAF.getSettDate() %>");
	    document.forms(0).Cell1.S(1,k+8,0,empIdList[k]);
	    document.forms(0).Cell1.S(3,k+8,0,empNameList[k]);
	    document.forms(0).Cell1.S(5,k+8,0,carNumList[k]);
	    document.forms(0).Cell1.S(8,k+8,0,moneyList[k]);
	    document.forms(0).Cell1.S(10,k+8,0,interest[k]);
	  	
	    //premoneytotal=premoneytotal+parseFloat(lastMonthCollectList[k]);
	   // moneytotal=moneytotal+parseFloat(thisMonthCollectList[k]);
	  }else{//向第一页后的所有页插数据
	    
	    document.forms(0).Cell1.S(1,k-completeline,pageCurrent,empIdList[k]);
	    document.forms(0).Cell1.S(2,k-completeline,pageCurrent,empNameList[k]);
	    document.forms(0).Cell1.S(3,k-completeline,pageCurrent,carNumList[k]);
	    document.forms(0).Cell1.S(4,k-completeline,pageCurrent,moneyList[k]);
	   
	   }
	  }
    <%
    }
    %>
    
    
    
    
    <%
	if(type.equals("提取")){
    %>
	document.forms(0).Cell1.openfile("<%=path%>/syscollection/accounthandle/bizcheck/report/pickup.cll","");
	<%
    
	List list = new ArrayList();
	list = (List)request.getSession().getAttribute("otherlist");
	for(int j=0;j<list.size();j++){
	empHAFAccountFlow = (EmpHAFAccountFlow)list.get(j);
	BigDecimal debit = empHAFAccountFlow.getDebit();
	BigDecimal credit = empHAFAccountFlow.getCredit();
	String money = "";
	if(debit.toString().equals("0") || debit.toString() == null){
	 money = credit.toString();
	}
	if(credit.toString().equals("0") || credit.toString() == null){
	 money = debit.toString();
	}
	if(credit.toString().equals("0") || credit.toString() == null && debit.toString().equals("0") || debit.toString() == null){
	money = "0.00";
	}
	%>
	   empIdList[i]=format("<%=empHAFAccountFlow.getEmpId()%>")+"<%=empHAFAccountFlow.getEmpId()%>";   //--1
	   empNameList[i]="<%=empHAFAccountFlow.getEmpName()%>";//--2
	   carNumList[i]="<%=empHAFAccountFlow.getEmp().getEmpInfo().getCardNum()%>";//--3
	   moneyList[i]="<%=money%>";//--4
	   interest[i]="<%=empHAFAccountFlow.getInterest() %>";
	   i++;
	<%
	}
	%>
	 var totalLine=empNameList.length;      //总行数
	  var totalPageline=15;                  //每页显示15行
	  var pageTotal=totalLine/totalPageline; //计算共几页
	  var pageCurrent=0;                     //当前页
	  var completeline=0;                    //记录行
	   for(k=0;k<totalLine;k++){
	  if(k%totalLine==0 && k>0){
	   // document.forms(0).Cell1.S(7,totalLine+4,pageCurrent,"本页小计:");
	   // document.forms(0).Cell1.SetFormula (8,totalLine+4,pageCurrent,"Sum(E3:E"+(totalLine+2)+")" );
	    document.forms(0).Cell1.ReDraw();   //重绘表格
	     pageCurrent++;                     //加一页
	     completeline=k-2;
	     document.forms(0).Cell1.insertSheetFromFile("<%=path%>/syscollection/accounthandle/bizcheck/report/bizcheck1.cll",0,1,pageCurrent);
	     document.forms(0).Cell1.setSheetlabel(pageCurrent,"第"+(pageCurrent+1)+"页");
	  }
	  if(pageCurrent==0){

	  var temp_noteNum="<%=bizcheckDetailAF.getNoteNum()%>";
	  var settdate="<%= bizcheckDetailAF.getSettDate() %>";
	  if(temp_noteNum==null){
	   document.forms(0).Cell1.S(3,2,0,"123");
	  }else{
	    document.forms(0).Cell1.S(3,2,0,"<%=bizcheckDetailAF.getNoteNum()%>");
	    }
	    if(settdate==null){
	      document.forms(0).Cell1.S(7,5,0,"");
	    }else{
	      document.forms(0).Cell1.S(7,5,0,"<%= bizcheckDetailAF.getSettDate() %>");
	    }
	    document.forms(0).Cell1.S(8,2,0,"<%=bizcheckDetailAF.getDocNum()%>");
	    document.forms(0).Cell1.S(3,3,0,"<%=bizcheckDetailAF.getOperator()%>");
	    document.forms(0).Cell1.S(8,3,0,"<%=bizcheckDetailAF.getBank()%>");
	    document.forms(0).Cell1.S(3,4,0,"<%=bizcheckDetailAF.getTraninId()%>");
	    document.forms(0).Cell1.S(8,4,0,"<%=bizcheckDetailAF.getTraninName()%>");
	    document.forms(0).Cell1.S(3,5,0,"<%=bizcheckDetailAF.getBiz_type() %>");
	   // document.forms(0).Cell1.S(8,5,0,"<%=bizcheckDetailAF.getSettDate() %>");
	    document.forms(0).Cell1.S(1,k+8,0,empIdList[k]);
	    document.forms(0).Cell1.S(3,k+8,0,empNameList[k]);
	    document.forms(0).Cell1.S(5,k+8,0,carNumList[k]);
	    document.forms(0).Cell1.S(8,k+8,0,moneyList[k]);
	    document.forms(0).Cell1.S(10,k+8,0,interest[k]);
	  	
	    //premoneytotal=premoneytotal+parseFloat(lastMonthCollectList[k]);
	   // moneytotal=moneytotal+parseFloat(thisMonthCollectList[k]);
	  }else{//向第一页后的所有页插数据
	    
	    document.forms(0).Cell1.S(1,k-completeline,pageCurrent,empIdList[k]);
	    document.forms(0).Cell1.S(2,k-completeline,pageCurrent,empNameList[k]);
	    document.forms(0).Cell1.S(3,k-completeline,pageCurrent,carNumList[k]);
	    document.forms(0).Cell1.S(4,k-completeline,pageCurrent,moneyList[k]);
	   
	   }
	  }
    <%
    }
    %>
    
	  //if(completeline==0){  //查询出来的记录可以在一页显示的时候会进入
	               // document.forms(0).Cell1.S(7,totalLine+5,pageCurrent,"本页小计:");
	               // document.forms(0).Cell1.SetFormula (8,totalLine+4,pageCurrent,"Sum(E3:E"+(totalLine+2)+")" );
	
	 // }

	            document.forms(0).Cell1.AllowExtend=false;
				document.forms(0).Cell1.AllowDragdrop=false;
				document.forms(0).Cell1.WorkbookReadonly=true;	
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
	function LoginRegister()//注册CELL
    { 
        alert(document.all('Cell1').Login( "username","" ,"04000234", "1231332223234"));
    }
    	
    function printsheet(){//真正的打印
		var k=document.forms(0).Cell1.GetCurSheet();//显示打印预览那个页面--固定
		document.forms(0).Cell1.PrintSheet(1,k);//固定...
	}
</script>
	<body onload="load();" onContextmenu="return false">


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
					<input type="button" name="print" value="打印预览"
						onclick="printPreview();" />
					<INPUT id="Button1" onclick="Button1_onclick()" type="button"
						value="另存为Excel" name="Button1">
					<INPUT id="Button1" onclick="Button2_onclick()" type="button"
						value="另存为pdf" name="Button1">
					<INPUT id="Button3" style="WIDTH: 100px"
						onclick="Button3_onclick()" type="button" value="页面设置">
					<INPUT id="Button3" style="WIDTH: 100px"
						onclick="Button4_onclick()" type="button" value="查找对话框">
					<INPUT id="Button3" style="WIDTH: 100px"
						onclick="Button5_onclick()" type="button" value="excel导入">
					<INPUT id="Button1" onclick="printsheet()" type="button" value=" 打印 " name="Button1">

<INPUT id="Button3" onclick="javascript:history.back();" type="button" value=" 返回 ">

			</table>
		</form>
	</body>
</html>
