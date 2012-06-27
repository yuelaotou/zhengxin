<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.List" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic" %>
<%@ page import="org.xpup.hafmis.syscollection.accounthandle.clearaccount.form.*" %>
<%@ page import="org.xpup.hafmis.syscollection.common.domain.entity.*" %>
<%@ include file="/checkUrl.jsp"%>
<%
   String path=request.getContextPath();
 %>
<html>
<script src="<%=path%>/js/common.js">
</script>
<script type="text/javascript">
	function load(){	
	
	loginReg();
	
	var totalLine;		//总的行数
	var totalPageLine=48;					//每页显示多少行
	var pageTotal=totalLine/totalPageLine;	//总共分几页
	var pageCurrent=0;						//当前页
	var completeline=0;						//记录行的
	var moneytotal=0;		
	
	var notenum;
	var docnum;
	var operator;
	var collectionbank;
	var orgid;			//默认单位		
	var orgname;
	var bistype;
	var empid=[];
	var empname=[];
	var idcard=[];
	var money=[];
	var interests=[];
	var i=0; 
	var tranoutorgid;	//转出
	var tranoutorgname;
	<%
		ClearAccountDetailAF clearAccountDetailAF=(ClearAccountDetailAF) request.getAttribute("clearAccountDetailAF");
		if (clearAccountDetailAF.getType()=="0"){//其他形式的
  			List list=clearAccountDetailAF.getOtherList();
  			EmpHAFAccountFlow empHAFAccountFlow=null;
  			for(int j=0;j<list.size();j++){
  				empHAFAccountFlow=(EmpHAFAccountFlow)list.get(j);
  				double k=empHAFAccountFlow.getDebit().doubleValue()+empHAFAccountFlow.getCredit().doubleValue();
 	%>		 	
 				empid[i]=format("<%=empHAFAccountFlow.getEmp().getId()%>")+"<%=empHAFAccountFlow.getEmp().getId()%>";
 				empname[i]="<%=empHAFAccountFlow.getEmp().getEmpInfo().getName()%>";
 				idcard[i]="<%=empHAFAccountFlow.getEmp().getEmpInfo().getCardNum()%>";
 				money[i]="<%=k%>";
				i++;
 	<%
 			} 
 	%>
 			totalLine=empname.length;			//总的行数
 			
 			document.forms(0).Cell1.openfile("/hafmis/syscollection/accounthandle/clearaccount/report/clearaccountohter_O.cll","");    
			var temp_O="<%=clearAccountDetailAF.getNoteNum() %>";	
			if(temp_O==null){
				notenum="";
			}else{
				notenum=temp_O;
			}
			docnum="<%=clearAccountDetailAF.getDocNum()%>";
			operator="<%=clearAccountDetailAF.getOperator()%>";
			collectionbank="<%=clearAccountDetailAF.getBank()%>";
			orgid=format("<%=clearAccountDetailAF.getTraninId()%>")+"<%=clearAccountDetailAF.getTraninId()%>";
			orgname="<%=clearAccountDetailAF.getTraninName()%>";
			bistype="<%=clearAccountDetailAF.getBiz_type()%>";
			
			for(k = 0 ; k < totalLine; k++){
				if(k%totalPageLine==0&&k>0){
					pageCurrent++;	
					completeline=k-2;				
					document.forms(0).Cell1.insertSheetFromFile("/hafmis/syscollection/accounthandle/clearaccount/report/clearaccountohter_O.cll",0,1,pageCurrent);
					document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页")	
				}
				if(pageCurrent==0){
					document.forms(0).Cell1.S(2,3,0,collectionbank);
					document.forms(0).Cell1.S(6,3,0,bistype);
					document.forms(0).Cell1.S(2,4,0,orgid);
					document.forms(0).Cell1.S(6,4,0,orgname);
					document.forms(0).Cell1.S(2,5,0,notenum);
					document.forms(0).Cell1.S(6,5,0,docnum);
					document.forms(0).Cell1.S(2,6,0,operator);

					document.forms(0).Cell1.S(1,k+9,0,empid[k]);
					document.forms(0).Cell1.S(3,k+9,0,empname[k]);
					document.forms(0).Cell1.S(5,k+9,0,idcard[k]);
					document.forms(0).Cell1.S(7,k+9,0,money[k]);
				}else{//向第一页后的所有页插数据
					document.forms(0).Cell1.S(2,3,pageCurrent,collectionbank);
					document.forms(0).Cell1.S(6,3,pageCurrent,bistype);
					document.forms(0).Cell1.S(2,4,pageCurrent,orgid);
					document.forms(0).Cell1.S(6,4,pageCurrent,orgname);
					document.forms(0).Cell1.S(2,5,pageCurrent,notenum);
					document.forms(0).Cell1.S(6,5,pageCurrent,docnum);
					document.forms(0).Cell1.S(2,6,pageCurrent,operator);
					
					document.forms(0).Cell1.S(1,k-completeline+7,pageCurrent,empid[k]);
					document.forms(0).Cell1.S(3,k-completeline+7,pageCurrent,empname[k]);
					document.forms(0).Cell1.S(5,k-completeline+7,pageCurrent,idcard[k]);
					document.forms(0).Cell1.S(7,k-completeline+7,pageCurrent,money[k]);
				}
			}
				if(completeline==0){//查询出来的记录可以在一页显示的时候会进入
					document.forms(0).Cell1.DeleteRow(totalLine+9,totalPageLine-(totalLine+8),pageCurrent);
					document.forms(0).Cell1.ReDraw();
				}else{
					document.forms(0).Cell1.DeleteRow(totalLine-completeline+7,totalPageLine-(totalLine-completeline+6),pageCurrent);
					document.forms(0).Cell1.ReDraw();
				}
	<%	
		}	
		//-----------------------------------------------------------
		else if (clearAccountDetailAF.getType()=="1"){//转出
			List list=clearAccountDetailAF.getList();
	  		EmpHAFAccountFlow empHAFAccountFlow=null;
  			for(int j=0;j<list.size();j++){
  				empHAFAccountFlow=(EmpHAFAccountFlow)list.get(j);
  				double k=empHAFAccountFlow.getDebit().doubleValue()+empHAFAccountFlow.getCredit().doubleValue();
	%>		 	
 				empid[i]=format("<%=empHAFAccountFlow.getEmp().getId()%>")+"<%=empHAFAccountFlow.getEmp().getId()%>";
 				empname[i]="<%=empHAFAccountFlow.getEmp().getEmpInfo().getName()%>";
 				idcard[i]="<%=empHAFAccountFlow.getEmp().getEmpInfo().getCardNum()%>";
 				money[i]="<%=k%>";
 				interests[i]="<%=empHAFAccountFlow.getInterest()%>";
				i++;
 	<%
 			}
 	%>
 			totalLine=empname.length;			//总的行数
 			document.forms(0).Cell1.openfile("/hafmis/syscollection/accounthandle/clearaccount/report/clearaccounttranout_E.cll","");
			var temp_E="<%=clearAccountDetailAF.getNoteNum() %>";	
			if(temp_E==null){
				notenum="";
			}else{
				notenum=temp_E;
			}
			docnum="<%=clearAccountDetailAF.getDocNum()%>";
			operator="<%=clearAccountDetailAF.getOperator()%>";
			collectionbank="<%=clearAccountDetailAF.getBank()%>";
			bistype="<%=clearAccountDetailAF.getBiz_type()%>";
			
			tranoutorgid=format("<%=clearAccountDetailAF.getTranoutId()%>")+"<%=clearAccountDetailAF.getTranoutId()%>";
			tranoutorgname="<%=clearAccountDetailAF.getTranoutName()%>";
			
			for(k = 0 ; k < totalLine; k++){
				if(k%totalPageLine==0&&k>0){
					pageCurrent++;	
					completeline=k-2;				
					document.forms(0).Cell1.insertSheetFromFile("/hafmis/syscollection/accounthandle/clearaccount/report/clearaccounttranout_E.cll",0,1,pageCurrent);
					document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页")	
				}
				if(pageCurrent==0){
					document.forms(0).Cell1.S(3,3,0,collectionbank);
					document.forms(0).Cell1.S(7,3,0,bistype);
					document.forms(0).Cell1.S(3,4,0,tranoutorgid);
					document.forms(0).Cell1.S(7,4,0,tranoutorgname);
					document.forms(0).Cell1.S(3,5,0,notenum);
					document.forms(0).Cell1.S(7,5,0,docnum);
					document.forms(0).Cell1.S(3,6,0,operator);

					document.forms(0).Cell1.S(1,k+9,0,empid[k]);
					document.forms(0).Cell1.S(3,k+9,0,empname[k]);
					document.forms(0).Cell1.S(5,k+9,0,idcard[k]);
					document.forms(0).Cell1.S(7,k+9,0,money[k]);
					document.forms(0).Cell1.S(8,k+9,0,interests[k]);
					
				}else{//向第一页后的所有页插数据
					document.forms(0).Cell1.S(3,3,pageCurrent,collectionbank);
					document.forms(0).Cell1.S(7,3,pageCurrent,bistype);
					document.forms(0).Cell1.S(3,4,pageCurrent,tranoutorgid);
					document.forms(0).Cell1.S(7,4,pageCurrent,tranoutorgname);
					document.forms(0).Cell1.S(3,5,pageCurrent,notenum);
					document.forms(0).Cell1.S(7,5,pageCurrent,docnum);
					document.forms(0).Cell1.S(3,6,pageCurrent,operator);
					
					document.forms(0).Cell1.S(1,k-completeline+7,pageCurrent,empid[k]);
					document.forms(0).Cell1.S(3,k-completeline+7,pageCurrent,empname[k]);
					document.forms(0).Cell1.S(5,k-completeline+7,pageCurrent,idcard[k]);
					document.forms(0).Cell1.S(7,k-completeline+7,pageCurrent,money[k]);
					document.forms(0).Cell1.S(8,k-completeline+7,pageCurrent,interests[k]);
				}
			}
				if(completeline==0){//查询出来的记录可以在一页显示的时候会进入
					document.forms(0).Cell1.DeleteRow(totalLine+9,totalPageLine-(totalLine+8),pageCurrent);
					document.forms(0).Cell1.ReDraw();
				}else{
					document.forms(0).Cell1.DeleteRow(totalLine-completeline+7,totalPageLine-(totalLine-completeline+6),pageCurrent);
					document.forms(0).Cell1.ReDraw();
				}
	<%
		}
		//--------------------------------------------------------------------------------------------
		else if (clearAccountDetailAF.getType()=="2"){//转入
			List list=clearAccountDetailAF.getList();
	  		TranInTail tranInTail=null;
	  		for(int j=0;j<list.size();j++){
			   String money = "";
  			   tranInTail=(TranInTail)list.get(j);
	%>		
			   empid[i]=format("<%=tranInTail.getEmpId()%>")+"<%=tranInTail.getEmpId() %>";
			   empname[i]="<%= tranInTail.getName()%>";
			   idcard[i]="<%=tranInTail.getCardNum()%>";
			   money="<%=tranInTail.getReserveaA() %>";
			   interests[i]="<%=tranInTail.getReserveaB() %>";
			   i++;
 	<%
 			} 
 	%>
 			totalLine=empname.length;			//总的行数
 			document.forms(0).Cell1.openfile("/hafmis/syscollection/accounthandle/clearaccount/report/clearaccounttranin_F.cll","");
			var temp_F="<%=clearAccountDetailAF.getNoteNum() %>";	
			if(temp_F==null){
				notenum="";
			}else{
				notenum=temp_F;
			}
			docnum="<%=clearAccountDetailAF.getDocNum()%>";
			operator="<%=clearAccountDetailAF.getOperator()%>";
			collectionbank="<%=clearAccountDetailAF.getBank()%>";
			orgid=format("<%=clearAccountDetailAF.getTraninId()%>")+"<%=clearAccountDetailAF.getTraninId()%>";
			orgname="<%=clearAccountDetailAF.getTraninName()%>";
			bistype="<%=clearAccountDetailAF.getBiz_type() %>";
			
			for(k = 0 ; k < totalLine; k++){
				if(k%totalPageLine==0&&k>0){
					pageCurrent++;	
					completeline=k-2;				
					document.forms(0).Cell1.insertSheetFromFile("/hafmis/syscollection/accounthandle/clearaccount/report/clearaccounttranin_F.cll",0,1,pageCurrent);
					document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页")	
				}
				if(pageCurrent==0){
					document.forms(0).Cell1.S(3,3,0,collectionbank);
					document.forms(0).Cell1.S(7,3,0,bistype);
					document.forms(0).Cell1.S(3,4,0,orgid);
					document.forms(0).Cell1.S(7,4,0,orgname);
					document.forms(0).Cell1.S(3,5,0,notenum);
					document.forms(0).Cell1.S(7,5,0,docnum);
					document.forms(0).Cell1.S(3,6,0,operator);

					document.forms(0).Cell1.S(1,k+9,0,empid[k]);
					document.forms(0).Cell1.S(3,k+9,0,empname[k]);
					document.forms(0).Cell1.S(5,k+9,0,idcard[k]);
					document.forms(0).Cell1.S(7,k+9,0,money[k]);
					document.forms(0).Cell1.S(8,k+9,0,interests[k]);
				}else{//向第一页后的所有页插数据
					document.forms(0).Cell1.S(3,3,pageCurrent,collectionbank);
					document.forms(0).Cell1.S(7,3,pageCurrent,bistype);
					document.forms(0).Cell1.S(3,4,pageCurrent,orgid);
					document.forms(0).Cell1.S(7,4,pageCurrent,orgname);
					document.forms(0).Cell1.S(3,5,pageCurrent,notenum);
					document.forms(0).Cell1.S(7,5,pageCurrent,docnum);
					document.forms(0).Cell1.S(3,6,pageCurrent,operator);
					
					document.forms(0).Cell1.S(1,k-completeline+7,pageCurrent,empid[k]);
					document.forms(0).Cell1.S(3,k-completeline+7,pageCurrent,empname[k]);
					document.forms(0).Cell1.S(5,k-completeline+7,pageCurrent,idcard[k]);
					document.forms(0).Cell1.S(7,k-completeline+7,pageCurrent,money[k]);
					document.forms(0).Cell1.S(8,k-completeline+7,pageCurrent,interests[k]);
				}
			}
				if(completeline==0){//查询出来的记录可以在一页显示的时候会进入
					document.forms(0).Cell1.DeleteRow(totalLine+9,totalPageLine-(totalLine+8),pageCurrent);
					document.forms(0).Cell1.ReDraw();
				}else{
					document.forms(0).Cell1.DeleteRow(totalLine-completeline+7,totalPageLine-(totalLine-completeline+6),pageCurrent);
					document.forms(0).Cell1.ReDraw();
				}
	<%	
		}
		//-------------------------------------------------------------------------
		else if (clearAccountDetailAF.getType()=="3"){//提取
			List list=clearAccountDetailAF.getList();
  		EmpHAFAccountFlow empHAFAccountFlow=null;
  		for(int j=0;j<list.size();j++)
  		{
  			empHAFAccountFlow=(EmpHAFAccountFlow)list.get(j);
  			double k=empHAFAccountFlow.getDebit().doubleValue()+empHAFAccountFlow.getCredit().doubleValue();
  	//		empHAFAccountFlow.getInterest()
	%>	
		
				empid[i]=format("<%=empHAFAccountFlow.getEmp().getId()%>")+"<%=empHAFAccountFlow.getEmp().getId()%>";
 				empname[i]="<%=empHAFAccountFlow.getEmp().getEmpInfo().getName()%>";
 				idcard[i]="<%=empHAFAccountFlow.getEmp().getEmpInfo().getCardNum()%>";
 				money[i]="<%=k%>";
				i++;
 	<%
 			} 
 	%>
 			totalLine=empname.length;			//总的行数
 			document.forms(0).Cell1.openfile("/hafmis/syscollection/accounthandle/clearaccount/report/clearaccountohter.cll","");
			notenum="<%=clearAccountDetailAF.getNoteNum()%>";
			docnum="<%=clearAccountDetailAF.getDocNum()%>";
			operator="<%=clearAccountDetailAF.getOperator()%>";
			collectionbank="<%=clearAccountDetailAF.getBank()%>";
			orgid=format("<%=clearAccountDetailAF.getTraninId()%>")+"<%=clearAccountDetailAF.getTraninId()%>";
			orgname="<%=clearAccountDetailAF.getTraninName()%>";
			bistype="<%=clearAccountDetailAF.getBiz_type()%>";
					
			
			for(k = 0 ; k < totalLine; k++){
				if(k%totalPageLine==0&&k>0)
				{
					pageCurrent++;	
					completeline=k-2;				
					document.forms(0).Cell1.insertSheetFromFile("/hafmis/syscollection/accounthandle/clearaccount/report/clearaccountohter.cll",0,1,pageCurrent);
					document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页")	
				}
				if(pageCurrent==0)
				{
					document.forms(0).Cell1.S(1,2,0,notenum);
					document.forms(0).Cell1.S(2,2,0,docnum);
					document.forms(0).Cell1.S(3,2,0,operator);
					document.forms(0).Cell1.S(4,2,0,collectionbank);
					document.forms(0).Cell1.S(5,2,0,orgid);
					document.forms(0).Cell1.S(6,2,0,orgname);
					document.forms(0).Cell1.S(7,2,0,bistype);

					document.forms(0).Cell1.S(1,k+4,0,empid[k]);
					document.forms(0).Cell1.S(3,k+4,0,empname[k]);
					document.forms(0).Cell1.S(5,k+4,0,idcard[k]);
					document.forms(0).Cell1.S(7,k+4,0,money[k]);
				}else{//向第一页后的所有页插数据
					document.forms(0).Cell1.S(1,2,pageCurrent,notenum);
					document.forms(0).Cell1.S(2,2,pageCurrent,docnum);
					document.forms(0).Cell1.S(3,2,pageCurrent,operator);
					document.forms(0).Cell1.S(4,2,pageCurrent,collectionbank);
					document.forms(0).Cell1.S(5,2,pageCurrent,orgid);
					document.forms(0).Cell1.S(6,2,pageCurrent,orgname);
					document.forms(0).Cell1.S(7,2,pageCurrent,bistype);
					
					document.forms(0).Cell1.S(1,k-completeline+2,pageCurrent,empid[k]);
					document.forms(0).Cell1.S(3,k-completeline+2,pageCurrent,empname[k]);
					document.forms(0).Cell1.S(5,k-completeline+2,pageCurrent,idcard[k]);
					document.forms(0).Cell1.S(7,k-completeline+2,pageCurrent,money[k]);
				}
			}
				if(completeline==0)//查询出来的记录可以在一页显示的时候会进入
				{
					document.forms(0).Cell1.DeleteRow(totalLine+4,totalPageLine-(totalLine+3),pageCurrent);
					document.forms(0).Cell1.ReDraw();
				}
				else
				{
					document.forms(0).Cell1.DeleteRow(totalLine-completeline+2,totalPageLine-(totalLine-completeline+1),pageCurrent);
					document.forms(0).Cell1.ReDraw();
				}
	<%	
		}
		//-------------------------------------------------------------------------
		else if (clearAccountDetailAF.getType()=="5"){//汇缴
			List list=clearAccountDetailAF.getList();
	  		EmpHAFAccountFlow empHAFAccountFlow=null;
		  		for(int j=0;j<list.size();j++)
  		{
  			empHAFAccountFlow=(EmpHAFAccountFlow)list.get(j);
  			double k=empHAFAccountFlow.getDebit().doubleValue()+empHAFAccountFlow.getCredit().doubleValue();
  	//		empHAFAccountFlow.getInterest()
	%>		
				empid[i]=format("<%=empHAFAccountFlow.getEmp().getId()%>")+"<%=empHAFAccountFlow.getEmp().getId()%>";
 				empname[i]="<%=empHAFAccountFlow.getEmp().getEmpInfo().getName()%>";
 				idcard[i]="<%=empHAFAccountFlow.getEmp().getEmpInfo().getCardNum()%>";
 				money[i]="<%=k%>";
 				interests[i]="<%=empHAFAccountFlow.getInterest()%>";
				i++;
 	<%
 			} 
 	%>
 			totalLine=empname.length;			//总的行数
 			document.forms(0).Cell1.openfile("/hafmis/syscollection/accounthandle/clearaccount/report/clearaccounttranin.cll","");
			notenum="<%=clearAccountDetailAF.getNoteNum()%>";
			docnum="<%=clearAccountDetailAF.getDocNum()%>";
			operator="<%=clearAccountDetailAF.getOperator()%>";
			collectionbank="<%=clearAccountDetailAF.getBank()%>";
			orgid=format("<%=clearAccountDetailAF.getTraninId()%>")+"<%=clearAccountDetailAF.getTraninId()%>";
			orgname="<%=clearAccountDetailAF.getTraninName()%>";
			bistype="<%=clearAccountDetailAF.getBiz_type()%>";
			
			tranoutorgid="<%=clearAccountDetailAF.getTranoutId()%>";
			tranoutorgname="<%=clearAccountDetailAF.getTranoutName()%>";
			
			
			for(k = 0 ; k < totalLine; k++){
				if(k%totalPageLine==0&&k>0)
				{
					pageCurrent++;	
					completeline=k-2;				
					document.forms(0).Cell1.insertSheetFromFile("/hafmis/syscollection/accounthandle/clearaccount/report/clearaccounttranin.cll",0,1,pageCurrent);
					document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页")	
				}
				if(pageCurrent==0)
				{
					document.forms(0).Cell1.S(1,2,0,notenum);
					document.forms(0).Cell1.S(2,2,0,docnum);
					document.forms(0).Cell1.S(3,2,0,operator);
					document.forms(0).Cell1.S(4,2,0,collectionbank);
					document.forms(0).Cell1.S(5,2,0,tranoutorgid);
					document.forms(0).Cell1.S(6,2,0,tranoutorgname);
					document.forms(0).Cell1.S(7,2,0,orgid);


					document.forms(0).Cell1.S(1,k+4,0,empid[k]);
					document.forms(0).Cell1.S(2,k+4,0,empname[k]);
					document.forms(0).Cell1.S(4,k+4,0,idcard[k]);
					document.forms(0).Cell1.S(6,k+4,0,money[k]);
					document.forms(0).Cell1.S(7,k+4,0,interests[k]);
				}else{//向第一页后的所有页插数据
					document.forms(0).Cell1.S(1,2,pageCurrent,notenum);
					document.forms(0).Cell1.S(2,2,pageCurrent,docnum);
					document.forms(0).Cell1.S(3,2,pageCurrent,operator);
					document.forms(0).Cell1.S(4,2,pageCurrent,collectionbank);
					document.forms(0).Cell1.S(5,2,pageCurrent,orgid);
					document.forms(0).Cell1.S(6,2,pageCurrent,orgname);
					document.forms(0).Cell1.S(7,2,pageCurrent,bistype);
					
					document.forms(0).Cell1.S(1,k-completeline+2,pageCurrent,empid[k]);
					document.forms(0).Cell1.S(2,k-completeline+2,pageCurrent,empname[k]);
					document.forms(0).Cell1.S(4,k-completeline+2,pageCurrent,idcard[k]);
					document.forms(0).Cell1.S(6,k-completeline+2,pageCurrent,money[k]);
					document.forms(0).Cell1.S(7,k-completeline+2,pageCurrent,interests[k]);
				}
			}
				if(completeline==0)//查询出来的记录可以在一页显示的时候会进入
				{
					document.forms(0).Cell1.DeleteRow(totalLine+4,totalPageLine-(totalLine+3),pageCurrent);
					document.forms(0).Cell1.ReDraw();
				}
				else
				{
					document.forms(0).Cell1.DeleteRow(totalLine-completeline+2,totalPageLine-(totalLine-completeline+1),pageCurrent);
					document.forms(0).Cell1.ReDraw();
				}
	<%	
		
		
		
		
		}
		else if (clearAccountDetailAF.getType()=="6"){//单位补缴
			List list=clearAccountDetailAF.getList();
	  		EmpHAFAccountFlow empHAFAccountFlow=null;
		  		for(int j=0;j<list.size();j++)
  		{
  			empHAFAccountFlow=(EmpHAFAccountFlow)list.get(j);
  			double k=empHAFAccountFlow.getDebit().doubleValue()+empHAFAccountFlow.getCredit().doubleValue();
  	//		empHAFAccountFlow.getInterest()
	%>		
				empid[i]=format("<%=empHAFAccountFlow.getEmp().getId()%>")+"<%=empHAFAccountFlow.getEmp().getId()%>";
 				empname[i]="<%=empHAFAccountFlow.getEmp().getEmpInfo().getName()%>";
 				idcard[i]="<%=empHAFAccountFlow.getEmp().getEmpInfo().getCardNum()%>";
 				money[i]="<%=k%>";
 				interests[i]="<%=empHAFAccountFlow.getInterest()%>";
				i++;
 	<%
 			} 
 	%>
 			totalLine=empname.length;			//总的行数
 			document.forms(0).Cell1.openfile("/hafmis/syscollection/accounthandle/clearaccount/report/clearaccounttranin.cll","");
			notenum="<%=clearAccountDetailAF.getNoteNum()%>";
			docnum="<%=clearAccountDetailAF.getDocNum()%>";
			operator="<%=clearAccountDetailAF.getOperator()%>";
			collectionbank="<%=clearAccountDetailAF.getBank()%>";
			orgid=format("<%=clearAccountDetailAF.getTraninId()%>")+"<%=clearAccountDetailAF.getTraninId()%>";
			orgname="<%=clearAccountDetailAF.getTraninName()%>";
			bistype="<%=clearAccountDetailAF.getBiz_type()%>";
			
			tranoutorgid="<%=clearAccountDetailAF.getTranoutId()%>";
			tranoutorgname="<%=clearAccountDetailAF.getTranoutName()%>";
			
			
			for(k = 0 ; k < totalLine; k++){
				if(k%totalPageLine==0&&k>0)
				{
					pageCurrent++;	
					completeline=k-2;				
					document.forms(0).Cell1.insertSheetFromFile("/hafmis/syscollection/accounthandle/clearaccount/report/clearaccounttranin.cll",0,1,pageCurrent);
					document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页")	
				}
				if(pageCurrent==0)
				{
					document.forms(0).Cell1.S(1,2,0,notenum);
					document.forms(0).Cell1.S(2,2,0,docnum);
					document.forms(0).Cell1.S(3,2,0,operator);
					document.forms(0).Cell1.S(4,2,0,collectionbank);
					document.forms(0).Cell1.S(5,2,0,tranoutorgid);
					document.forms(0).Cell1.S(6,2,0,tranoutorgname);
					document.forms(0).Cell1.S(7,2,0,orgid);


					document.forms(0).Cell1.S(1,k+4,0,empid[k]);
					document.forms(0).Cell1.S(2,k+4,0,empname[k]);
					document.forms(0).Cell1.S(4,k+4,0,idcard[k]);
					document.forms(0).Cell1.S(6,k+4,0,money[k]);
					document.forms(0).Cell1.S(7,k+4,0,interests[k]);
				}else{//向第一页后的所有页插数据
					document.forms(0).Cell1.S(1,2,pageCurrent,notenum);
					document.forms(0).Cell1.S(2,2,pageCurrent,docnum);
					document.forms(0).Cell1.S(3,2,pageCurrent,operator);
					document.forms(0).Cell1.S(4,2,pageCurrent,collectionbank);
					document.forms(0).Cell1.S(5,2,pageCurrent,orgid);
					document.forms(0).Cell1.S(6,2,pageCurrent,orgname);
					document.forms(0).Cell1.S(7,2,pageCurrent,bistype);
					
					document.forms(0).Cell1.S(1,k-completeline+2,pageCurrent,empid[k]);
					document.forms(0).Cell1.S(2,k-completeline+2,pageCurrent,empname[k]);
					document.forms(0).Cell1.S(4,k-completeline+2,pageCurrent,idcard[k]);
					document.forms(0).Cell1.S(6,k-completeline+2,pageCurrent,money[k]);
					document.forms(0).Cell1.S(7,k-completeline+2,pageCurrent,interests[k]);
				}
			}
				if(completeline==0)//查询出来的记录可以在一页显示的时候会进入
				{
					document.forms(0).Cell1.DeleteRow(totalLine+4,totalPageLine-(totalLine+3),pageCurrent);
					document.forms(0).Cell1.ReDraw();
				}
				else
				{
					document.forms(0).Cell1.DeleteRow(totalLine-completeline+2,totalPageLine-(totalLine-completeline+1),pageCurrent);
					document.forms(0).Cell1.ReDraw();
				}
	<%	
		
		
		
		
		}
		
	%>
	
	
	
		document.forms(0).Cell1.AllowExtend=false;
		document.forms(0).Cell1.AllowDragdrop=false;
		document.forms(0).Cell1.WorkbookReadonly=true;	
	}
	function printPreview(){
		var k=document.forms(0).Cell1.GetCurSheet();//显示打印预览那个页面
		document.forms(0).Cell1.printPreviewEx(1,k,false);
	}
	function printsheet(){//真正的打印
		var k=document.forms(0).Cell1.GetCurSheet();//显示打印预览那个页面--固定
		document.forms(0).Cell1.PrintSheet(1,k);//固定...
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

</script>

<script language="JScript.Encode">
eval(unescape('function%20LoginRegister%28%29//%u6CE8%u518CCELL%0D%0A%20%7B%20%0D%0A%20document.forms%280%29.Cell1.Login%28%22%u6C88%u9633%u91D1%u8F6F%u79D1%u6280%u6709%u9650%u516C%u53F8%22%2C%22%22%2C%2213100104509%22%2C%220740-1662-7775-3003%22%29%3B%20%20%20%20%0D%0A%20%7D'))
</script>
	
<body onload = "load();" onContextmenu="return false"> 
<form action="">
<table align="center">
<tr><OBJECT id=Cell1 style= "LEFT:0px;WIDTH:700px;  TOP:0px;HEIGHT:400px" codeBase="http://192.168.1.44:8088/hafmis/CellWeb5.CAB#version=5,3,7,321" classid=clsid:3F166327-8030-4881-8BD2-EA25350E574A VIEWASTEXT><PARAM NAME="_Version" VALUE="65536"><PARAM NAME="_ExtentX" VALUE="10266"><PARAM NAME="_ExtentY" VALUE="7011"><PARAM NAME="_StockProps" VALUE= "0"></OBJECT></tr> 
<tr><input type="button" name="print" value = "打印预览" onclick = "printPreview();"/>
<INPUT id="Button1" onclick="Button1_onclick()" type="button" value="另存为Excel" name="Button1">
<INPUT id="Button3" style="WIDTH: 100px" onclick="Button3_onclick()" type="button" value="页面设置">
<INPUT id="Button3" style="WIDTH: 100px" onclick="Button4_onclick()" type="button" value="查找对话框">
<INPUT id="Button3" style="WIDTH: 100px" onclick="Button5_onclick()" type="button" value="excel导入">
<INPUT id="Button1" onclick="printsheet()" type="button" value=" 打印 " name="Button1">
<INPUT id="Button3" onclick="javascript:history.back();" type="button" value=" 返回 ">	
</table>
</form>
</body>
</html>
