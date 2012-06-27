<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/taglib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/taglib/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/taglib/xpup-security.tld" prefix="security"%>
<%@ page
	import="org.xpup.hafmis.sysloan.loancallback.loancallback.action.LoancallbackTaShowAC"%>
<%@ include file="/checkUrl.jsp"%>
<jsp:include flush="true" page="/waiting.inc"/>
<%
			Object pagination = request.getSession(false).getAttribute(
			LoancallbackTaShowAC.PAGINATION_KEY);
	request.setAttribute("pagination", pagination);
	String path = request.getContextPath();
	String matter = (String)request.getAttribute("matter");
%>
<html:html>
<head>
	<title>个贷管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="<%=path%>/css/index.css" type="text/css">
</head>
<script src="<%=path%>/js/common.js">
<script type="text/javascript" src="<%=path%>/js/picture.js"></script>
<script type="text/javascript">
var cctt="";
var v_aheadCorpus = 0;
var v_aheadInterest = 0;
function executeAjax() {
        var queryString = "<%=path%>/sysloan/loancallbackTaFindAAC.do?";
        var id = document.loancallbackTaAF.elements["borrowerInfoDTO.contractId"].value.trim();
        if(id == ""){
        	gotoSearch();
        }else{
	        queryString = queryString + "id="+id; 	 
		    findInfo(queryString);
	    }
}
function gotoSearch(){
	gotoContractpop('11','<%=path%>','0','0');
	//gotoLoankouaccpop('11','<%=path%>','0');
}
function gotoEnter(){
	if(event.keyCode==13){
		event.keyCode = 9;
		executeAjax();
	}
}
function display(id){
	showInfo();
}
function showInfo(){
	document.Form1.submit();
}
function loads(){
	var matter="<%=matter%>";
	if(matter!="null"){
		alert(matter);
	}
	<logic:messagesPresent>
		var message = "<html:messages id="error"><bean:write name="error"/></html:messages>"
		alert(message);
	</logic:messagesPresent>
	var bizType=document.forms[0].elements["biztype"].value;
	if(bizType==3){
		document.all.chgMonth.disabled="true";
	}
	// 得到提前还款本金，将其放到变量中判断在点击确定按钮时是否改变(如果改变需要点击回车键计算金额)
	v_aheadCorpus = document.forms[0].elements["aheadCorpus"].value;
	v_aheadInterest = document.forms[0].elements["aheadInterest"].value;
	var bizType=document.forms[0].elements["biztype"].value;
	var plLoanReturnType=document.loancallbackTaAF.elements["plType"].value;
	var name = document.loancallbackTaAF.elements["borrowerInfoDTO.borrowerName"].value;
	var isAmend = document.loancallbackTaAF.isAmend.value;
	var isAmendLine = document.loancallbackTaAF.isAmendLine.value;
	if(isAmend=="0"){
		document.forms[0].elements["realMoney"].readOnly="readonly";
	}
	if(plLoanReturnType==1){
		document.all.disp1.disabled="true";
		document.all.disp2.disabled="true";
	}else{
		document.all.select1.disabled="true";
		document.all.radio1.disabled="true";
		document.all.radio2.disabled="true";
		document.all.radio3.disabled="true";
		document.all.disp3.disabled="true";//以银行为主确定不可用
		document.forms[0].button32.style.display="none";
		document.forms[0].elements["realMoney"].readOnly="readonly";
		document.forms[0].elements["aheadCorpus"].readOnly="readonly";
		document.forms[0].elements["deadLine"].readOnly="readonly";
		document.loancallbackTaAF.elements["borrowerInfoDTO.loanKouAcc"].disabled="true";
	}
	if(name == ""){
		document.all.disp3.disabled="true";
		document.all.disp2.disabled="true";
	}
	if(bizType=="2"){
		document.forms[0].elements["aheadCorpus"].disabled="disabled";
		document.forms[0].elements["days"].disabled="disabled";
		document.forms[0].elements["aheadInterest"].disabled="disabled";
		document.forms[0].elements["loanPoundageMoney"].disabled="disabled";
		//document.forms[0].elements["deadLine"].disabled="disabled";
		document.forms[0].elements["corpusInterest"].disabled="disabled";
	}else if(bizType=="3"){
<%--		if(isAmendLine=="0"){--%>
<%--			document.forms[0].elements["deadLine"].readOnly="readonly";--%>
<%--		}--%>
		document.forms[0].elements["monthYear"].disabled="disabled";
<%--		document.forms[0].elements["aheadCorpus"].focus();--%>
	}else if(bizType=="4"){
		document.forms[0].elements["monthYear"].disabled="disabled";
		document.forms[0].elements["aheadCorpus"].readOnly="readonly";
		//document.forms[0].elements["deadLine"].readOnly="readonly";
		document.forms[0].elements["realMoney"].readOnly="readonly";
	}
	var loanMode=document.forms[0].elements["borrowerInfoDTO.loanMode"].value.trim();
	if(loanMode=="等额本金"){
		document.forms[0].elements["corpusInterest"].value="";
	}
	if(loanMode=="一年期"||loanMode=="两年期"){
		document.all.radio2.disabled="true";
		document.all.radio3.disabled="true";
	}
	var count = "<bean:write name="pagination" property="nrOfElements"/>";
	var m=document.forms[0].elements["sumMoney"].value.trim();
	if(bizType=="2"){
		if(count=="0"){
			document.all.disp3.disabled="true";
			document.forms[0].elements["monthYear"].value="";
		}
	}else{
		if(parseFloat(m)<0){
			document.all.disp3.disabled="true";			
		}
	}
}
function gotoMonth(){
	var month=document.forms[0].elements["monthYear"].value.trim();
	var ovaerLoanRepay=document.forms[0].elements["ovaerLoanRepay"].value.trim();
	var contractId=document.forms[0].elements["borrowerInfoDTO.contractId"].value.trim();
	location.href="loancallbackTaChangeMonthAC.do?month="+month+"&ovaerLoanRepay="+ovaerLoanRepay+"&contractId="+contractId;
}
function gotoType1(){
	var bizType=document.forms[0].elements["biztype"].value;
	if(bizType!="2"){
		location.href="loancallbackTaShowAC.do?bizType=2";
	}
}
function gotoType2(){
	var bizType=document.forms[0].elements["biztype"].value;
	if(bizType != "3"){
		location.href="loancallbackTaShowAC.do?bizType=3";
	}
}
function gotoType3(){
	var bizType=document.forms[0].elements["biztype"].value;
	if(bizType != "4"){
		location.href="loancallbackTaShowAC.do?bizType=4";
	}
}
function clear(){
		document.forms[0].elements["aheadCorpus"].value="";
		document.forms[0].elements["days"].value="";
		document.forms[0].elements["aheadInterest"].value="";
		document.forms[0].elements["loanPoundageMoney"].value="";
		document.forms[0].elements["deadLine"].value="";
		document.forms[0].elements["corpusInterest"].value="";
		document.forms[0].elements["sumCorpus"].value="";
		document.forms[0].elements["sumInterest"].value="";
		document.forms[0].elements["ovaerLoanRepay"].value="";
		document.forms[0].elements["realMoney"].value="";
		document.forms[0].elements["overOccurMoney"].value="";
		document.forms[0].elements["sumMoney"].value="";

}
<%--function gotoEnter1(){--%>
<%--	if(event.keyCode==13){--%>
<%--		event.keyCode = 9;--%>
<%--		executeAjax1();--%>
<%--	}--%>
<%----%>
<%--}--%>
<%--function gotoEnterLine(){--%>
<%--	if(event.keyCode==13){--%>
<%--		event.keyCode = 9;--%>
<%--		executeAjaxLine();--%>
<%--	}--%>
<%----%>
<%--}--%>
function executeAjax1(eee){
cctt="1";
document.all.disp3.disabled="true";	


			
			var dead=document.all.dead.value.trim();
			var chgMonth=document.all.chgMonth.value.trim();
			var deadLine=document.all.deadLine.value.trim();
			
       		 document.all.deadLine.value=parseFloat(chgMonth)+parseFloat(dead);
			document.all.lastlimit.value=parseFloat(chgMonth)+parseFloat(dead);

	var plLoanReturnType=document.loancallbackTaAF.elements["plType"].value;
	if(plLoanReturnType==1){
		var queryString = "<%=path%>/sysloan/loancallbackTaAheadAAC.do?";
        var aheadMoney = document.loancallbackTaAF.elements["aheadCorpus"].value.trim();
	var overplusCorpus = document.all.overplusCorpus.value;
	var minMoney = document.all.minMoney.value;
	var type = document.loancallbackTaAF.elements["aheadTypeS"].value.trim();
        if(aheadMoney == ""){
        	alert("请输入提前还款金额！");
        	return false;
        }else if(parseFloat(aheadMoney)>=parseFloat(overplusCorpus)){
	    	alert("提前还款金额不能大于等于剩余本金！");
	    	return false;
	    }else if(parseFloat(aheadMoney)<parseFloat(overplusCorpus)){
	    	if(type == "1"||type == "4"||type == "5"){
			    if(parseFloat(aheadMoney)<parseFloat(minMoney)){
			    	alert("提前还款金额不能小于允许最低还款金额！");
			    	return false;
			    }
		    }
	    } 
	    if(aheadMoney != "0"){
	        if(isNaN(aheadMoney)){
	        	alert("请输入正确金额！");
	        	return false;
	        }
	    }
        if(parseFloat(aheadMoney)<0){
        	alert("请输入正确金额！");
        	return false;
        }else{
        	var line = document.loancallbackTaAF.elements["deadLine"].value.trim();
	        queryString = queryString + "aheadMoney="+aheadMoney+"&deadLine="+line+"&type=1"; 	 
		    findInfo(queryString,eee);
	    }
	}
	
showselect();
}
function findInfo(queryString,eee) {
 createXMLHttpRequest();  
	    xmlHttp.onreadystatechange = handleStateChange;
	    xmlHttp.open("GET", queryString, false);
	    xmlHttp.send(null);  
	    //check_date(eee) 
}
function executeAjaxLine(){
	var plLoanReturnType=document.loancallbackTaAF.elements["plType"].value;
	var plLoanRturnType=document.loancallbackTaAF.elements["aheadTypeS"].value;
	if(plLoanReturnType==1){
		var queryString = "<%=path%>/sysloan/loancallbackTaAheadAAC.do?";
	    var aheadMoney = document.loancallbackTaAF.elements["aheadCorpus"].value.trim();
<%--	    alert(aheadMoney);--%>
	    var line = document.loancallbackTaAF.elements["deadLine"].value.trim();
	    var cIMoney = document.loancallbackTaAF.elements["sumCorpus"].value.trim();
	    
	    
	      
          var monthYear = document.loancallbackTaAF.elements["monthYear"].value.trim();
          var contractId = document.loancallbackTaAF.elements["borrowerInfoDTO.contractId"].value.trim();
          var sumCorpus = document.loancallbackTaAF.elements["sumCorpus"].value.trim();
            var sumInterest = document.loancallbackTaAF.elements["sumInterest"].value.trim();
        	var loanMode=document.forms[0].elements["borrowerInfoDTO.loanMode"].value.trim();
	    
	    if(plLoanRturnType.trim()==1){
		queryString = queryString + "aheadMoney="+aheadMoney+"&deadLine="+line+"&cIMoney="+cIMoney
		+"&monthYear="+monthYear+"&contractId="+contractId+
		"&sumCorpus="+sumCorpus+"&sumInterest="+sumInterest+"&loanMode="+loanMode; 
		}	 
		if(plLoanRturnType.trim()==3||plLoanRturnType.trim()==2){
			queryString = queryString + "aheadMoney=0"+"&deadLine="+line+"&cIMoney=0"
		+"&monthYear="+monthYear+"&contractId="+contractId+
		"&sumCorpus=0"+"&sumInterest=0"+"&loanMode="+loanMode; 	 
		}
		if(plLoanRturnType.trim()==4||plLoanRturnType.trim()==5){
			queryString = queryString + "aheadMoney="+aheadMoney+"&deadLine="+line+"&cIMoney="+cIMoney
		+"&monthYear="+monthYear+"&contractId="+contractId+
		"&sumCorpus="+sumCorpus+"&sumInterest="+sumInterest+"&loanMode="+loanMode;  
		}
		if(line==""){
			alert("请输入期限！");
		    return false;
		}
		if(isNaN(line)){
		    alert("请输入正确期限！");
		    return false;
		}
        if(parseFloat(line)<0){
        	alert("请输入正确期限！");
        	return false;
        }
		else{
		   findInfo(queryString);
		}
	}
}
function displays(aheadCorpus,days,aheadInterest,loanPoundageMoney,deadLine,corpusInterest,
	sumCorpus,sumInterest,sumMoney,ovaerLoanRepay,realMoney,overplusInterestAll,interestAll,overOccurMoney){
	
	var loanMode=document.forms[0].elements["borrowerInfoDTO.loanMode"].value.trim();
	if(loanMode=="等额本金"){
		document.forms[0].elements["corpusInterest"].value="";
	}else{
		document.forms[0].elements["corpusInterest"].value=corpusInterest;
	}
		document.forms[0].elements["aheadCorpus"].value=aheadCorpus;
		document.forms[0].elements["days"].value=days;
		document.forms[0].elements["aheadInterest"].value=aheadInterest;
		document.forms[0].elements["loanPoundageMoney"].value=loanPoundageMoney;
		document.forms[0].elements["deadLine"].value=deadLine;
		
		document.forms[0].elements["sumCorpus"].value=sumCorpus;
		document.forms[0].elements["sumInterest"].value=sumInterest;
		document.forms[0].elements["ovaerLoanRepay"].value=ovaerLoanRepay;
		document.forms[0].elements["realMoney"].value=realMoney;
		document.forms[0].elements["overOccurMoney"].value=overOccurMoney;

<%--		document.forms[0].elements["overplusInterestAll"].value=overplusInterestAll;--%>
<%--		document.forms[0].elements["interestAll"].value=interestAll;--%>
		
		document.forms[0].elements["sumMoney"].value=sumMoney;
		v_aheadCorpus=aheadCorpus;
		
}
function display1(corpusInterest,overplusInterestAll,interestAll){

	var loanMode=document.forms[0].elements["borrowerInfoDTO.loanMode"].value.trim();
	
	if(loanMode=="等额本金"){
		document.forms[0].elements["corpusInterest"].value="";
	}else{
		document.forms[0].elements["corpusInterest"].value=corpusInterest;
	}
	
<%--		document.forms[0].elements["overplusInterestAll"].value=overplusInterestAll;--%>
<%--		document.forms[0].elements["interestAll"].value=interestAll;--%>
	
}
function gotoSure(){
	//全额扣款，本次实收金额可以修改，但要大于等于0小于等于默认显示的实收金额），
	var bizType=document.forms[0].elements["biztype"].value;
	if(bizType=="3"){
		var aheadTypeS = document.loancallbackTaAF.elements["aheadTypeS"].value.trim();
		if(aheadTypeS==""){
			alert("请选择提前还款类型！");
			return false;
		}
	}
	
	var money = document.loancallbackTaAF.elements["realMoney"].value.trim();
	var money1 = document.loancallbackTaAF.elements["realMoney1"].value.trim();
	var aheadMoney = document.loancallbackTaAF.elements["aheadCorpus"].value.trim();
	var line = document.loancallbackTaAF.elements["deadLine"].value.trim();
	
	var corpusInterest = document.loancallbackTaAF.elements["corpusInterest"].value.trim();
	var overplusCorpus = document.all.overplusCorpus.value;
	var minMoney = document.all.minMoney.value;
	var loanMode=document.forms[0].elements["borrowerInfoDTO.loanMode"].value.trim();
	var type = document.loancallbackTaAF.elements["aheadType"].value.trim();
	var plLoanRturnType=document.loancallbackTaAF.elements["aheadTypeS"].value;
	if(plLoanRturnType.trim()==4||plLoanRturnType.trim()==5){
		
		if(plLoanRturnType.trim()==4){
			
			var dead=document.all.dead.value.trim();
			var deadLine=document.all.chgMonth.value.trim();
			if(parseFloat(aheadMoney)<=0){
        		alert("请输入正确提前还款金额！");
        		return false;
       		 }
			if(deadLine==""){
				alert("请输入期限！");
				return false;
			}
			if(isNaN(deadLine)){
		   		 alert("请输入正确期限！");
		   		 return false;
			}
        	if(parseFloat(deadLine)<=0){
        		alert("请输入正确期限！");
        		return false;
       		 }
       		  if(parseFloat(deadLine)%12!=0){
        		alert("请输入正确期限,12的整数倍！");
        		return false;
       		 }
<%--			if(parseFloat(dead)>parseFloat(deadLine)){--%>
<%--				alert("剩余期限为"+dead+",请输入大于剩余期限的数值!");--%>
<%--				return false;--%>
<%--			}--%>
		}
		if(plLoanRturnType.trim()==5){
			
			var dead=document.all.dead.value.trim();
			var deadLine=document.all.chgMonth.value.trim();
			if(parseFloat(aheadMoney)<=0){
        		alert("请输入正确提前还款金额！");
        		return false;
       		 }
			if(deadLine==""){
				alert("请输入期限！");return false;
			}
			if(isNaN(deadLine)){
		   		 alert("请输入正确期限！");return false;
			}
        	if(parseFloat(deadLine)<=0){
        		alert("请输入正确期限！");return false;
       		 }
       		  if(parseFloat(deadLine)%12!=0){
        		alert("请输入正确期限,12的整数倍！");
        		return false;
       		 }
<%--			if(parseFloat(dead)<parseFloat(deadLine)){--%>
<%--				alert("剩余期限为"+dead+",请输入小于剩余期限的数值!");return false;--%>
<%--			}--%>
		}
	}
	if(plLoanRturnType.trim()==2||plLoanRturnType.trim()==3){
		
		if(plLoanRturnType.trim()==2){
			
			var dead=document.all.dead.value.trim();
			var deadLine=document.all.chgMonth.value.trim();
			if(deadLine==""){
				alert("请输入期限！");return false;
			}
			if(isNaN(deadLine)){
		   		 alert("请输入正确期限！");return false;
			}
        	if(parseFloat(deadLine)<=0){
        		alert("请输入正确期限！");return false;
       		 }
       		  if(parseFloat(deadLine)%12!=0){
        		alert("请输入正确期限,12的整数倍！");
        		return false;
       		 }
<%--			if(parseFloat(dead)>parseFloat(deadLine)){--%>
<%--				alert("剩余期限为"+dead+",请输入大于剩余期限的数值!");return false;--%>
<%--			}--%>
		}
		if(plLoanRturnType.trim()==3){
			
			var dead=document.all.dead.value.trim();
			var deadLine=document.all.chgMonth.value.trim();
			if(deadLine==""){
				alert("请输入期限！");return false;
			}
			if(isNaN(deadLine)){
		   		 alert("请输入正确期限！");return false;
			}
        	if(parseFloat(deadLine)<=0){
        		alert("请输入正确期限！");return false;
       		 }
       		  if(parseFloat(deadLine)%12!=0){
        		alert("请输入正确期限,12的整数倍！");
        		return false;
       		 }
<%--			if(parseFloat(dead)<parseFloat(deadLine)){--%>
<%--				alert("剩余期限为"+dead+",请输入小于剩余期限的数值!");return false;--%>
<%--			}--%>
		}
	}
	if(corpusInterest==0){
		document.loancallbackTaAF.elements["corpusInterest"].value="0";
	}
	if(bizType=="3"){
		if(plLoanRturnType=="1"||plLoanRturnType=="4"||plLoanRturnType=="5"){
			if(v_aheadCorpus!=aheadMoney){
			alert('请在修改了提前还款本金后点击回车键计算相关金额！');
			return false;
		}
	    if(parseFloat(aheadMoney)<0){
	        alert("请输入正确金额！");
	        return false;
	    }else if(parseFloat(aheadMoney)>=parseFloat(overplusCorpus)){
	    	alert("提前还款金额不能大于等于剩余本金！");
	    	return false;
	    }else if(parseFloat(aheadMoney)<parseFloat(overplusCorpus)){
	    	if(type == "3"){
			    if(parseFloat(aheadMoney)<parseFloat(minMoney)){
			    	alert("提前还款金额不能小于允许最低还款金额！");
			    	return false;
			    }
	    	}
	    } 
	    if(parseFloat(line)<0){
	       alert("请输入正确期限！");
	        return false;
	    }
	    if(money==""){
			alert("本次实收金额不能为空！");
			return false;
		}
		if(parseFloat(money)<0){
			alert("实收金额不能小于零！");
			return false;
		}else if(parseFloat(money)>parseFloat(money1)){
			alert("本次实收金额不能大于默认的实收金额！");
			return false;
		}
		}		
		
	}else{
	    if(parseFloat(aheadMoney)<0){
	        alert("请输入正确金额！");
	        return false;
	    }
	    if(parseFloat(line)<0){
	       alert("请输入正确期限！");
	        return false;
	    }
	    if(money==""){
			alert("本次实收金额不能为空！");
			return false;
		}
		if(parseFloat(money)<0){
			alert("实收金额不能小于零！");
			return false;
		}else if(parseFloat(money)>parseFloat(money1)){
			alert("本次实收金额不能大于默认的实收金额！");
			return false;
		}
    }
	
<%--	if(confirm("是否打印回收凭证？")){--%>
<%--		document.loancallbackTaAF.report.value="print";--%>
<%--	}else{--%>
		document.loancallbackTaAF.report.value="noprint";
<%--	}--%>
}

function gotoDelete(){
	if(!confirm("是否确定删除该笔业务？")){
		return false;
	}
	document.all.corpusInterest.value="0";
}
function clickMoney(){
	var flsshType=document.all.aheadTypeS.value.trim();
	if(flsshType==""){
		alert("请先选择提前还款类型!");
	}

}
function clickday(){
	var flsshType=document.all.aheadTypeS.value.trim();
	if(flsshType==""){
		alert("请先选择提前还款类型!");
	}
	var plLoanRturnType=document.loancallbackTaAF.elements["aheadTypeS"].value;
	if(plLoanRturnType.trim()==4||plLoanRturnType.trim()==5){
		if(cctt==""){
		
			alert("请先输入还款金额!");
		}
	}
	

}
function blurday(){
document.all.disp3.disabled="true";	

	var plLoanRturnType=document.loancallbackTaAF.elements["aheadTypeS"].value;
	if(plLoanRturnType.trim()==4||plLoanRturnType.trim()==5){
		
		if(plLoanRturnType.trim()==4){
			
			var dead=document.all.dead.value.trim();
			var chgMonth=document.all.chgMonth.value.trim();
			var deadLine=document.all.deadLine.value.trim();
			
			if(chgMonth==""){
				alert("请输入期限！");
				return false;
			}
			if(isNaN(chgMonth)){
		   		 alert("请输入正确期限！");
		   		 return false;
			}
        	if(parseFloat(chgMonth)<=0){
        		alert("请输入正确期限！");
        		return false;
       		 }
       		 if(parseFloat(chgMonth)%12!=0){
        		alert("请输入正确期限,12的整数倍！");
        		document.all.chgMonth.value="0";
        		return false;
       		 }
       		 document.all.deadLine.value=parseFloat(chgMonth)+parseFloat(dead);
			document.all.lastlimit.value=parseFloat(chgMonth)+parseFloat(dead);
<%--			if(parseFloat(dead)>parseFloat(deadLine)){--%>
<%--				alert("剩余期限为"+dead+",请输入大于剩余期限的数值!");--%>
<%--				return false;--%>
<%--			}--%>
		}
		if(plLoanRturnType.trim()==5){
			
			var dead=document.all.dead.value.trim();
			var chgMonth=document.all.chgMonth.value.trim();
			var deadLine=document.all.deadLine.value.trim();
			
			if(chgMonth==""){
				alert("请输入期限！");return false;
			}
			if(isNaN(chgMonth)){
		   		 alert("请输入正确期限！");return false;
			}
        	if(parseFloat(chgMonth)<=0){
        		alert("请输入正确期限！");return false;
       		 }
       		 if(parseFloat(chgMonth)%12!=0){
        		alert("请输入正确期限,12的整数倍！");
        		document.all.chgMonth.value="0";
        		return false;
       		 }
       		 document.all.deadLine.value=parseFloat(dead)-parseFloat(chgMonth);
			document.all.lastlimit.value=parseFloat(dead)-parseFloat(chgMonth);
<%--			if(parseFloat(dead)<parseFloat(deadLine)){--%>
<%--				alert("剩余期限为"+dead+",请输入小于剩余期限的数值!");return false;--%>
<%--			}--%>
		}
	}
	if(plLoanRturnType.trim()==2||plLoanRturnType.trim()==3){
		
		if(plLoanRturnType.trim()==2){
			
			var dead=document.all.dead.value.trim();
			var chgMonth=document.all.chgMonth.value.trim();
			var deadLine=document.all.deadLine.value.trim();
			
			if(chgMonth==""){
				alert("请输入期限！");return false;
			}
			if(isNaN(chgMonth)){
		   		 alert("请输入正确期限！");return false;
			}
        	if(parseFloat(chgMonth)<=0){
        		alert("请输入正确期限！");return false;
       		 }
       		 if(parseFloat(chgMonth)%12!=0){
        		alert("请输入正确期限,12的整数倍！");
        		document.all.chgMonth.value="0";
        		return false;
       		 }
       		 document.all.deadLine.value=parseFloat(chgMonth)+parseFloat(dead);
			document.all.lastlimit.value=parseFloat(chgMonth)+parseFloat(dead);
<%--			if(parseFloat(dead)>parseFloat(deadLine)){--%>
<%--				alert("剩余期限为"+dead+",请输入大于剩余期限的数值!");return false;--%>
<%--			}--%>
		}
		if(plLoanRturnType.trim()==3){
			
			var dead=document.all.dead.value.trim();
			var chgMonth=document.all.chgMonth.value.trim();
			var deadLine=document.all.deadLine.value.trim();
			
			if(chgMonth==""){
				alert("请输入期限！");return false;
			}
			if(isNaN(chgMonth)){
		   		 alert("请输入正确期限！");return false;
			}
        	if(parseFloat(chgMonth)<=0){
        		alert("请输入正确期限！");return false;
       		 }
       		 if(parseFloat(chgMonth)%12!=0){
        		alert("请输入正确期限,12的整数倍！");
        		document.all.chgMonth.value="0";
        		return false;
       		 }
       		 document.all.deadLine.value=parseFloat(dead)-parseFloat(chgMonth);
			document.all.lastlimit.value=parseFloat(dead)-parseFloat(chgMonth);
<%--			if(parseFloat(dead)<parseFloat(deadLine)){--%>
<%--				alert("剩余期限为"+dead+",请输入小于剩余期限的数值!");return false;--%>
<%--			}--%>
		}
	}
		var plLoanReturnType=document.loancallbackTaAF.elements["plType"].value;
	var plLoanRturnType=document.loancallbackTaAF.elements["aheadTypeS"].value;
	if(plLoanReturnType==1){
		var queryString = "<%=path%>/sysloan/loancallbackTaAheadAAC.do?";
	    var aheadMoney = document.loancallbackTaAF.elements["aheadCorpus"].value.trim();
<%--	    alert(aheadMoney);--%>
	    var line =0; 
	    var cIMoney = document.loancallbackTaAF.elements["sumCorpus"].value.trim();
	    
	    if(plLoanRturnType==1){
	    	line=document.all.dead.value.trim();
	    }
	    if(plLoanRturnType==2||plLoanRturnType==4){
	    	line=parseFloat(document.all.dead.value.trim())+parseFloat(document.all.chgMonth.value.trim());
	    }  
	    if(plLoanRturnType==3||plLoanRturnType==5){
	    	line=parseFloat(document.all.dead.value.trim())-parseFloat(document.all.chgMonth.value.trim());
	    }
          var monthYear = document.loancallbackTaAF.elements["monthYear"].value.trim();
          var contractId = document.loancallbackTaAF.elements["borrowerInfoDTO.contractId"].value.trim();
          var sumCorpus = document.loancallbackTaAF.elements["sumCorpus"].value.trim();
            var sumInterest = document.loancallbackTaAF.elements["sumInterest"].value.trim();
        	var loanMode=document.forms[0].elements["borrowerInfoDTO.loanMode"].value.trim();
	    
	    if(plLoanRturnType.trim()==1){
		queryString = queryString + "aheadMoney="+aheadMoney+"&deadLine="+line+"&cIMoney="+cIMoney
		+"&monthYear="+monthYear+"&contractId="+contractId+
		"&sumCorpus="+sumCorpus+"&sumInterest="+sumInterest+"&loanMode="+loanMode; 
		}	 
		if(plLoanRturnType.trim()==3||plLoanRturnType.trim()==2){
			queryString = queryString + "aheadMoney=0"+"&deadLine="+line+"&cIMoney=0"
		+"&monthYear="+monthYear+"&contractId="+contractId+
		"&sumCorpus=0"+"&sumInterest=0"+"&loanMode="+loanMode; 	 
		}
		if(plLoanRturnType.trim()==4||plLoanRturnType.trim()==5){
			queryString = queryString + "aheadMoney="+aheadMoney+"&deadLine="+line+"&cIMoney="+cIMoney
		+"&monthYear="+monthYear+"&contractId="+contractId+
		"&sumCorpus="+sumCorpus+"&sumInterest="+sumInterest+"&loanMode="+loanMode;  
		}
		if(line==""){
			alert("请输入期限！");
		    return false;
		}
		if(isNaN(line)){
		    alert("请输入正确期限！");
		    return false;
		}
        if(parseFloat(line)<0){
        	alert("请输入正确期限！");
        	return false;
        }
		else{
		   findInfo(queryString);
		}
	}
	showselect_1();
}
function changeType(){
	var flsshType=document.all.aheadTypeS.value.trim();
	if(flsshType==""){
		alert("请先选择提前还款类型!");
	}
	if(flsshType=="1"){
		document.all.aheadCorpus.disabled="";
		document.all.chgMonth.value="0";
		document.all.chgMonth.disabled="true";
	}
	if(flsshType=="4"){
		document.all.aheadCorpus.disabled="";
		document.all.chgMonth.disabled="";
		document.all.chgMonth.value="0";
	}
	if(flsshType=="5"){
		document.all.aheadCorpus.disabled="";
		document.all.chgMonth.disabled="";
		document.all.chgMonth.value="0";
	}
	if(flsshType=="2"){
		document.all.aheadCorpus.value="0";
		document.all.aheadCorpus.disabled="true";
		document.all.chgMonth.disabled="";
		document.all.aheadInterest1.value=v_aheadInterest;
		document.forms[0].elements["aheadInterest"].value="0";
		document.forms[0].elements["sumInterest"].value="0";
		document.forms[0].elements["sumCorpus"].value="0";
		document.forms[0].elements["sumMoney"].value="0";
		document.forms[0].elements["realMoney"].value="0";
	}
	if(flsshType=="3"){
		document.all.aheadCorpus.value="0";
		document.all.aheadCorpus.disabled="true";
		document.all.chgMonth.disabled="";
		document.forms[0].elements["aheadInterest"].value="0";
		document.all.aheadInterest1.value=v_aheadInterest;
		document.forms[0].elements["sumInterest"].value="0";
		document.forms[0].elements["sumCorpus"].value="0";
		document.forms[0].elements["sumMoney"].value="0";
		document.forms[0].elements["realMoney"].value="0";
		executeAjaxSomeBack();
	}
}
function check_date(eee){
	setPosiTion(eee);MM_showHideLayers('sending','','show');MM_showHideLayers('Layer1','','show');MM_showHideLayers('Layer2','','hide');    
}
function executeAjaxSomeBack() {
	var queryString = "<%=path%>/sysloan/loancallbackSomeBackAAC.do?";
	var id = document.loancallbackTaAF.elements["borrowerInfoDTO.contractId"].value.trim();
	var salary = document.loancallbackTaAF.elements["sumSalary"].value.trim();
	if(id == ""){
		gotoSearch();
	}else{
		queryString = queryString + "id="+id+ "&salary="+salary; 	 
		findInfo(queryString);
	}
}
function displaySomeBack(time){
	var limit = document.loancallbackTaAF.elements["dead"].value.trim();
	if(time*12>limit){
		alert("不可以缩短期限！");
	}else{
		alert("最多可以缩短"+parseInt((limit-time*12)/12)+"年！");
	}
	
}
</script>

<body bgcolor="#FFFFFF" text="#000000" onload="loads();">
<jsp:include page="/syscommon/picture/progressbar_wsh.jsp"/>
<jsp:include page="/syscommon/picture/progressbar_wsh_1.jsp"/>
	<html:form action="/loancallbackTaMaintainAC" style="margin: 0">
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
											办理回收
										</td>
										<td width="112" height="37"
											background="<%=path%>/img/buttong.gif" align="center"
											style="PADDING-top: 7px" valign="top">
											<a href="<%=path%>/sysloan/loancallbackTbForwardURLAC.do"
												class=a2>回收维护</a>
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
										<td width="234" class=font14 bgcolor="#FFFFFF" align="center"
											valign="bottom">
											<font color="00B5DB">回收贷款&gt;回收贷款</font>
										</td>
										<td width="29" class=font14>
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
										<td height="22" bgcolor="#CCCCCC" align="center" width="176">
											<b class="font14">借 款 人 信 息</b>
										</td>
										<td height="22" background="<%=path%>/img/bg2.gif"
											align="center" width="734">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table width="95%" id="table9" cellspacing=1 cellpadding=3
						align="center">
						<tr>
							<td width="17%" class=td1>
								合同编号
								<input type="hidden" name="report" value="">
							</td>
							<td class="td4" width="20%">
								<html:text property="borrowerInfoDTO.contractId"
									ondblclick="executeAjax();" onkeydown="gotoEnter();"
									styleClass="input3" />
							</td>
							<td class="td4">
								<input type="button" class="buttona" value="..." name="button32"
									onclick="gotoSearch();">
							</td>
							<td class="td1" width="17%">
								扣款账号
							</td>
							<td class="td4" width="33%">
								<html:text property="borrowerInfoDTO.loanKouAcc"
									styleClass="input3" readonly="true" />
							</td>
						</tr>
						<tr>
							<td width="17%" class=td1>
								借款人姓名
							</td>
							<td class="td4" colspan="2">
								<html:text property="borrowerInfoDTO.borrowerName"
									styleClass="input3" readonly="true" />
							</td>
							<td width="17%" class=td1>
								证件类型
							</td>
							<td class="td4" width="33%">
								<html:text property="borrowerInfoDTO.cardKind"
									styleClass="input3" readonly="true" />
							</td>
						</tr>
						<tr>
							<td class=td1 width="17%">
								证件号码
							</td>
							<td class="td4" colspan="2">
								<html:text property="borrowerInfoDTO.cardNum"
									styleClass="input3" readonly="true" />
							</td>
							<td class=td1 width="17%">
								剩余本金
							</td>
							<td class="td4" width="33%">
								<html:text property="borrowerInfoDTO.overplusLoanMoney"
									styleClass="input3" readonly="true" />
							</td>
						</tr>
						<tr>
							<td class=td1 width="17%">
								还款方式
							</td>
							<td class="td4" colspan="2">
								<html:text property="borrowerInfoDTO.loanMode"
									styleClass="input3" readonly="true" />
							</td>
							<td class=td1 width="17%">
								还至日
							</td>
							<td class="td4" width="33%">
								<html:hidden property="borrowerInfoDTO.loanRepayDay" />
								<html:select property="monthYear" styleClass="input4"
									onchange="gotoMonth();" styleId="select1">
									<html:option value=""></html:option>
									<html:options collection="monthYearList" property="value"
										labelProperty="label" />

								</html:select>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="174">
											<b class="font14">应还款信息列表</b>
										</td>
										<td width="736" height="22" align="center"
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
						<tr align="center" bgcolor="C4F0FF">
							<td align="center" class=td1>
								还款年月
							</td>
							<td align="center" class=td2>
								还款类型
							</td>
							<td align="center" class=td2>
								应还本金
							</td>
							<td align="center" class=td2>
								应还利息
							</td>
							<td align="center" class=td2>
								逾期天数
							</td>
							<td align="center" class=td2>
								逾期利息
							</td>
							<td align="center" class=td2>
								应还本息合计
							</td>
							<td align="center" class=td2>
								每月利率
							</td>
						</tr>

						<logic:notEmpty name="loancallbackTaAF" property="shouldBackList">
							<%
									int j = 0;
									String strClass = "";
							%>
							<logic:iterate name="loancallbackTaAF" property="shouldBackList"
								id="element">
								<%
											j++;
											if (j % 2 == 0) {
												strClass = "td8";
											} else {
												strClass = "td9";
											}
								%>
								<tr id="tr1" class="<%=strClass%>" onDblClick="">
									<td valign="top">
										<bean:write name="element" property="loanKouYearmonth" />
									</td>
									<td valign="top">
										<bean:write name="element" property="loanKouType" />
									</td>
									<td valign="top">
										<bean:write name="element" property="shouldCorpus" />
									</td>
									<td valign="top">
										<bean:write name="element" property="shouldInterest" />
									</td>
									<td valign="top">
										<bean:write name="element" property="days" />
									</td>
									<td valign="top">
										<bean:write name="element" property="punishInterest" />
									</td>
									<td valign="top">
										<bean:write name="element" property="ciMoney" />
									</td>
									<td valign="top">
										<bean:write name="element" property="show_loanRate" />
									</td>
								</tr>

							</logic:iterate>
						</logic:notEmpty>
						<logic:empty name="loancallbackTaAF" property="shouldBackList">
							<tr>
								<td colspan="9" height="30" style="color:red">
									没有找到与条件相符合的结果！
								</td>
							</tr>

						</logic:empty>
					</table>
					<table width="95%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<td height="35">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="22" bgcolor="#CCCCCC" align="center" width="172">
											<b class="font14"> 还 款 信 息</b>
										</td>
										<td height="22" background="<%=path%>/img/bg2.gif"
											align="center" width="738">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table width="95%" id="table9" cellspacing=1 cellpadding=3
						align="center">
						<tr>
							<td width="17%" class=td1>
								业务类型
							</td>
							<td class="td4">
								<html:radio property="bizType" value="2" onclick="gotoType1();"
									styleId="radio1" />
								单笔回收
								<html:radio property="bizType" value="3" onclick="gotoType2();"
									styleId="radio2" />
								部分提前还款
								<html:radio property="bizType" value="4" onclick="gotoType3();"
									styleId="radio3" />
								一次性清还
							</td>
							<td class="td1" width="17%">
								提前还款类型
								<input type="hidden" name="biztype"
									value="<bean:write name="loancallbackTaAF" property="bizType"/>">
								<input type="hidden" name="balance"
									value="<bean:write name="loancallbackTaAF" property="loanBalance"/>">
								<input type="hidden" name="plType"
									value="<bean:write name="plLoanReturnType"/>">
								<html:hidden name="loancallbackTaAF" property="param" />
								<html:hidden name="loancallbackTaAF" property="aheadCheckId" />
								<html:hidden name="loancallbackTaAF" property="aheadType" />
								<html:hidden property="borrowerInfoDTO.loanBankId" />
								<html:hidden property="borrowerInfoDTO.officeCode" />
								<html:hidden property="isAmend" />
								<html:hidden property="isAmendLine" />
								<html:hidden property="minMoney" />
								<html:hidden property="aheadInterest1"/>
							</td>
							<td width="21%" colspan="2">

								<logic:equal name="loancallbackTaAF" property="bizType"
									value="3">
									<html:select property="aheadTypeS" styleClass="input4"
										name="loancallbackTaAF" onkeydown="enterNextFocus1();"
										onchange="changeType()">
										
										<option value="1">
											提前部分还款
										</option>
										<option value="2">
											延长还款期限
										</option>
										<option value="3">
											缩短还款期限
										</option>
										<option value="4">
											提前部分还款、延长还款期限
										</option>
										<option value="5">
											提前部分还款、缩短还款期限
										</option>
									</html:select>
								</logic:equal>
								<logic:notEqual name="loancallbackTaAF" property="bizType"
									value="3">
									<html:select property="aheadTypeS" styleClass="input4"
										name="loancallbackTaAF" onkeydown="enterNextFocus1();"
										disabled="true">
										<option value=""></option>
										<option value="1">
											提前部分还款
										</option>
										<option value="2">
											延长还款期限
										</option>
										<option value="3">
											缩短还款期限
										</option>
										<option value="4">
											提前部分还款、延长还款期限
										</option>
										<option value="5">
											提前部分还款、缩短还款期限
										</option>
									</html:select>
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<td width="17%" class=td1>
								提前还款本金
								<font color="#FF0000">*</font>
								<br>
							</td>
							<td class="td4">
								<logic:equal name="loancallbackTaAF" property="bizType"
									value="2">
									<html:text property="aheadCorpus" styleClass="input3"
										disabled="true" />
								</logic:equal>
								<html:hidden property="overplusCorpus" />
								<logic:notEqual name="loancallbackTaAF" property="bizType"
									value="2">
									<html:text property="aheadCorpus" styleClass="input3"
										onclick="clickMoney();" onblur="return executeAjax1(this);" />
								</logic:notEqual>
							</td>
							<td class="td1" width="17%">
								占用天数
							</td>
							<td class="td4" width="33%">
								<logic:equal name="loancallbackTaAF" property="bizType"
									value="2">
									<html:text property="days" styleClass="input3" disabled="true" />
								</logic:equal>
								<logic:notEqual name="loancallbackTaAF" property="bizType"
									value="2">
									<html:text property="days" styleClass="input3" readonly="true" />
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<td width="17%" class=td1>
								提前还款利息
							</td>
							<td class="td4">
								<logic:equal name="loancallbackTaAF" property="bizType"
									value="2">
									<html:text property="aheadInterest" styleClass="input3"
										disabled="true" />
								</logic:equal>
								<logic:notEqual name="loancallbackTaAF" property="bizType"
									value="2">
									<html:text property="aheadInterest" styleClass="input3"
										readonly="true" />
								</logic:notEqual>
							</td>
							<td width="17%" class=td1>
								手续费金额
							</td>
							<td class="td4" width="33%">
								<logic:equal name="loancallbackTaAF" property="bizType"
									value="2">
									<html:text property="loanPoundageMoney" styleClass="input3"
										disabled="true" />
								</logic:equal>
								<logic:notEqual name="loancallbackTaAF" property="bizType"
									value="2">
									<html:text property="loanPoundageMoney" styleClass="input3"
										readonly="true" />
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<td class=td1 width="17%">
								剩余期限(月)
								
							</td>
							<td class="td4">
								<logic:equal name="loancallbackTaAF" property="bizType"
									value="3">
									<html:text property="dead" styleClass="input3"
										readonly="true"/>
									<input type="hidden" name="deadLine"
										value="<bean:write name="loancallbackTaAF" property="dead"/>">
								</logic:equal>
								<logic:notEqual name="loancallbackTaAF" property="bizType"
									value="3">

									<html:text property="dead" styleClass="input3"
										disabled="true" />
								</logic:notEqual>
							</td>
							<td class=td1 width="17%">
								变更期限（月）<font color="#FF0000">*</font>
							</td>
							<td class="td4" width="33%">
								<logic:equal name="loancallbackTaAF" property="bizType"
									value="2">
									<html:text property="chgMonth" styleClass="input3"
										disabled="true" />
								</logic:equal>
								<logic:notEqual name="loancallbackTaAF" property="bizType"
									value="2">
									<html:text property="chgMonth" styleClass="input3"
										 onclick="clickday();"
										onblur="blurday();"/>
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<td class=td1 width="17%">
								提前还款后剩余期限(月)
								<font color="#FF0000">*</font>
							</td>
							<td class="td4">
								<logic:equal name="loancallbackTaAF" property="bizType"
									value="3">
									<html:text property="lastlimit" styleClass="input3"
										 readonly="true"/>
								</logic:equal>
								<logic:notEqual name="loancallbackTaAF" property="bizType"
									value="3">

									<html:text property="lastlimit" styleClass="input3"
										disabled="true" />
								</logic:notEqual>
							</td>
							<td class=td1 width="17%">
								提前还款后月还本息
							</td>
							<td class="td4" width="33%">
								<logic:equal name="loancallbackTaAF" property="bizType"
									value="2">
									<html:text property="corpusInterest" styleClass="input3"
										disabled="true" />
								</logic:equal>
								<logic:notEqual name="loancallbackTaAF" property="bizType"
									value="2">
									<html:text property="corpusInterest" styleClass="input3"
										readonly="true" />
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<td class=td1 width="17%">
								本次总还款本金
							</td>
							<td class="td4">
								<html:text property="sumCorpus" styleClass="input3"
									readonly="true" />
							</td>
							<td class=td1 width="17%">
								本次总还款利息
							</td>
							<td class="td4" width="33%">
								<html:text property="sumInterest" styleClass="input3"
									readonly="true" />
							</td>
						</tr>
						<tr>
							<td class=td1 width="17%">
								本次总还款金额
							</td>
							<td class="td4">
								<html:text property="sumMoney" styleClass="input3"
									readonly="true" />
							</td>
							<td class=td1 width="17%">
								挂账余额
							</td>
							<td class="td4" width="33%">
								<html:text property="ovaerLoanRepay" styleClass="input3"
									readonly="true" />
							</td>
						</tr>
						<tr>
							<td class=td1 width="17%">
								本次实收金额
								<font color="#FF0000">*</font>
							</td>
							<td class="td4">
								<html:text property="realMoney" styleClass="input3" />
								<input type="hidden" name="realMoney1"
									value="<bean:write name="loancallbackTaAF" property="realMoney"/>">
							</td>
							<td class=td1 width="17%">
								挂账发生额
							</td>
							<td width="33%">
								<html:text property="overOccurMoney" styleClass="input3"
									readonly="true" />
							</td>
						</tr>
<tr>
							<td class=td1 width="17%">
								工资基数
								<font color="#FF0000">*</font>
							</td>
							<td class="td4">
								<html:text property="sumSalary" styleClass="input3"
									readonly="true" />
							</td>
							<td width="17%">
							
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
											<html:submit property="method" styleClass="buttona"
												styleId="disp1" onclick="">
												<bean:message key="button.import" />
											</html:submit>
										</td>
										<td width="70">
											<html:submit property="method" styleClass="buttona"
												styleId="disp2" onclick="return gotoDelete();">
												<bean:message key="button.delete" />
											</html:submit>
										</td>
										<td width="70">
											<html:submit property="method" styleClass="buttona"
												styleId="disp3" onclick="return gotoSure();">
												<bean:message key="button.sure" />
											</html:submit>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
		</table>
	</html:form>
	<form action="<%=path%>/sysloan/loancallbackTaShowAC.do" method="POST"
		name="Form1" id="Form1">
	</form>
</body>
</html:html>

