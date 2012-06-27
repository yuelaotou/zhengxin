<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic"%>
<%@ page import="org.xpup.hafmis.sysloan.loancallback.loancallback.form.LoancallbackTaAF"%>
<%@ page import="org.xpup.hafmis.sysloan.loancallback.loancallback.dto.ShouldBackListDTO"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.math.BigDecimal"%>
<%@ page import="org.xpup.hafmis.common.util.BusiConst"%>
<%@ page import="org.xpup.hafmis.common.util.BusiTools"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	String url=(String)request.getAttribute("URL");
	String makePerson = (String)request.getAttribute("makePerson");
	String bizDates = (String)request.getAttribute("bizDate");
	if(url==null){
		url="javascript:history.back();";
	}
%>
<html>
	<head>
		<script src="<%=path%>/js/common.js">
</script>
	</head>
	<script type="text/javascript">
	function load(){	
	loginReg();
	document.forms(0).Cell1.openfile("<%=path%>/sysloan/loancallback/report/loancallback.cll","");
    //document.forms(0).Cell1.setSheetLabel(1,"第2页");
    var yearMonth=[];
    var loanKouAcc=[];
    var contractId=[];
    var nameList=[];
	var cardNum=[];
	var shouldCorpus=[];
	var shouldInterest=[];
	var shouldPunishInterest=[];
	var realCorpus=[];
	var realInterest=[];
	var realPunishInterest=[];
	var realMoney=[];
	var occurMoney=[];
	var money=[];
	var loanKouType=[];
	var loanBankName=[];
	var yesOrNo=[];
	var i=0;

	<%
    	    LoancallbackTaAF af =(LoancallbackTaAF)request.getAttribute("LoancallbackTaAF");
    	    List taillist = af.getShouldBackList();
    	    String docNum=af.getDocNum();
    	    String noteNum=af.getNoteNum();
    	    String borrowerName=af.getBorrowerInfoDTO().getBorrowerName();
    	    String contractId=af.getBorrowerInfoDTO().getContractId();
    	    String loanKouAcc=af.getBorrowerInfoDTO().getLoanKouAcc();
    	    String loanAcc = af.getLoanAcc();
    	    String interestAcc = af.getInterestAcc();
    	    String bankName = af.getBankName();
    		String overOccurMoney = af.getOverOccurMoney().toString();
    		String makeOP = af.getMakeOP();
    		String clearOP = af.getClearOP();
    		String sumCorpus = af.getSumCorpus().toString();
    		String sumInterest = af.getSumInterest().toString();
    		String interest = af.getInterest().add(af.getOverdueInterest()).toString();
    		String overdueInterest = af.getOverdueInterest().toString();
    		String punishInterest = af.getPunishInterest().toString();
    		String bizType = af.getBizType();
    		String months = af.getMonths();
    		String monthYear = af.getMonthYear();
    		String bizDate = af.getBizDate();
    		String realMoney = af.getRealMoney().toString();
    		String realCorpus = af.getRealCorpus().add(af.getRealOverduCorpus()).toString();
    		String realOverdueCorpus = af.getRealOverduCorpus().toString();
    		String sumMoney = af.getSumCorpus().add(af.getSumInterest()).toString();
    		String batchNum = af.getBatchNum();
    		String begainMonthYear = af.getBegainMonthYear();
    		BigDecimal tqhklx = af.getTqhklx();
    		BigDecimal tqhkbj = af.getTqhkbj();
    		String date = af.getDate();
    		String yga = af.getYga();
    		String ygb = af.getYgb();
    		String ygc = af.getYgc();
    		String ygd = af.getYgd();
    		String yge = af.getYge();
    		String beginMonth="0";
    		String size="0";
    		bizType=bizType.replace(',',' ');
    		String data_1=af.getData_1();
    		String data_2=af.getData_2();
    		String data_3=af.getData_3();
    		String data_4=af.getData_4();
    		String data_5=af.getData_5();
    		String data_6=af.getData_6();
    		String data_7=af.getData_7();
    		String data_8=af.getData_8();
    		String data_9=af.getData_9();
    		String data_10=af.getData_10();
    		String data_11=af.getData_11();
    		String cardNum=af.getCardNum();
	%>
          <%
           if("是".equals(batchNum)){//是公积金还贷  加载 住房贷款公积金还贷收款明细表模板
          %>
	          document.forms(0).Cell1.insertSheetFromFile("<%=path%>/sysloan/loancallback/report/loancallbackList2.cll",0,1,1);
	          document.forms(0).Cell1.PrintSetCustomPaper(2200,2800,1);	
				document.forms(0).Cell1.PrintSetMargin(0,0,0,0);
				document.forms(0).Cell1.PrintSetAlign(0,0);
			 
          <%
           }else{//不是公积金还贷  加载住房贷款收款明细表 模板 
          %>
          	document.forms(0).Cell1.insertSheetFromFile("<%=path%>/sysloan/loancallback/report/1.cll",0,1,1);
          	document.forms(0).Cell1.PrintSetCustomPaper(2200,2800,1);	
				document.forms(0).Cell1.PrintSetMargin(0,0,0,0);
				document.forms(0).Cell1.PrintSetAlign(1,0);
			
          <%
           }
          %>
          document.forms(0).Cell1.setSheetLabel(1,"第2页");
			<%
  			for(int j=0;j<taillist.size();j++)
  			{
  				ShouldBackListDTO dto=(ShouldBackListDTO)taillist.get(j);
  				if(taillist.size()>1&&j==0){
  					beginMonth=dto.getLoanKouYearmonth();
  					size=String.valueOf(taillist.size()-1);
  				}
 	%>
				 yearMonth[i]="<%=dto.getLoanKouYearmonth()%>";
			     loanKouAcc[i]="<%=dto.getLoanKouAcc()%>";
			     contractId[i]="<%=dto.getContractId()%>";
			     nameList[i]="<%=dto.getBorrowerName()%>";
			//	 cardNum[i]="";
				 shouldCorpus[i]="<%=dto.getShouldCorpus()%>";
				 shouldInterest[i]="<%=dto.getShouldInterest()%>";
				 shouldPunishInterest[i]="<%=dto.getShouldPunishInterest()%>";
				 realCorpus[i]="<%=dto.getRealCorpus()%>";
				 realInterest[i]="<%=dto.getRealInterest()%>";
				 realPunishInterest[i]="<%=dto.getRealPunishInterest()%>";
				 realMoney[i]="<%=dto.getRealMoney()%>";
				 occurMoney[i]="<%=dto.getOccurMoney()%>";
				 money[i]="<%=dto.getMoney()%>";
				 loanKouType[i]="<%=BusiTools.getBusiValue(Integer.parseInt(dto.getLoanKouType()) ,BusiConst.GIVEBACK)%>";
				 loanBankName[i]="<%=dto.getLoanBankName() %>";  
				 yesOrNo[i]="<%=dto.getYesOrNo() %>"; 
 	          	i++; 
 	<%
 			}
 	%>
 	    var makeP="<%=makeOP%>";
 	    var clearpsn = "<%=af.getClearAccountOP()%>";
 	    var clearP="<%=clearOP%>";
 	    if(makeP=="null"){
 	    	makeP="";
 	    }
 	    if(clearP=="null"){
 	    	clearP="";
 	    }
 	    if(clearpsn=="null"){
 	    	clearpsn="";
 	    }
		var sumPayC = <%=sumCorpus%>;
		var chiao="0";
		var fin="0";
		sumPayC=""+sumPayC;
		var count=sumPayC.indexOf(".",0);
		var length;
		len=sumPayC.length;
		if(count==-1){
			length=len;
		}else{
			length=count;
		}
		if(sumPayC>0){
			for(var i=0;i<length;i++){//从后向前插入（从元起）
				var temp1=sumPayC.substring((length-i-1),length-i);
				document.forms(0).Cell1.S((16-i),9,0,temp1);
			}
			if(count!=-1){//计算小数后面
				if((len-count)>2)//两位
				{
					chiao=sumPayC.substring(count+1,sumPayC.length-1);
					fin=sumPayC.substring(count+2,sumPayC.length);	
					document.forms(0).Cell1.S(17,9,0,chiao);
					document.forms(0).Cell1.S(18,9,0,fin);
				}
				else{//只有一位
					chiao=sumPayC.substring(count+1,sumPayC.length)
					document.forms(0).Cell1.S(17,9,0,chiao);
					document.forms(0).Cell1.S(18,9,0,"0");
				}	
			}else{
				document.forms(0).Cell1.S(17,9,0,chiao);
				document.forms(0).Cell1.S(18,9,0,fin);
			}
		}
		
			 sumPayC=ChangeToBig(sumPayC);
			 if(sumPayC=="整"){
			 	sumPayC="";
			 }
			 var docNum="<%=docNum%>";
			 if(docNum=="null"){
			 	docNum="";
			 }
			 var noteNum="<%=noteNum%>";
			 if(noteNum=="null"){
			 	noteNum="";
			 }
		     document.forms(0).Cell1.S(12,16,0,"<%=bizDate%>");
		     document.forms(0).Cell1.S(7,4,0,docNum);
		     document.forms(0).Cell1.S(13,4,0,noteNum);
		     document.forms(0).Cell1.S(3,5,0,"<%=borrowerName%>");
		     document.forms(0).Cell1.S(9,5,0,"<%=contractId%>");
		     document.forms(0).Cell1.S(3,6,0,"<%=loanKouAcc%>");
		     document.forms(0).Cell1.S(9,6,0,"<%=loanAcc%>");
		     document.forms(0).Cell1.S(3,7,0,"<%=bankName%>");
		     document.forms(0).Cell1.S(3,8,0,sumPayC);
		     document.forms(0).Cell1.S(2,10,0,"本次为<%=bizType%>从<%=begainMonthYear%>还至<%=monthYear%>,实还金额：<%=sumMoney%>,实收金额：<%=realMoney%>,提前还贷本金：<%=tqhkbj%>,提前还贷利息：<%=tqhklx%>,本期本金：<%=yga%>,本期利息：<%=ygb%>,逾期本金：<%=ygc%>,逾期利息：<%=ygd%>,罚息：<%=yge%>");
		     document.forms(0).Cell1.d(4,14,0,"<%=overOccurMoney%>");
		     document.forms(0).Cell1.d(6,14,0,"<%=realCorpus%>");
		     //document.forms(0).Cell1.d(8,14,0,"<%=realOverdueCorpus%>");
		     document.forms(0).Cell1.S(3,16,0,makeP);
		     document.forms(0).Cell1.S(5,16,0,clearpsn);
		     document.forms(0).Cell1.S(7,16,0,clearP);
		     
		     
		var sumPayI = <%=sumInterest%>;
		var chiaoI="0";
		var finI="0";
		sumPayI=""+sumPayI;
		var countI=sumPayI.indexOf(".",0);
		var lengthI;
		var lenI=sumPayI.length;
		if(countI==-1){
			lengthI=lenI;
		}else{
			lengthI=countI;
		}
		if(sumPayI>0){
			for(var i=0;i<lengthI;i++){//从后向前插入（从元起）
				var temp=sumPayI.substring((lengthI-i-1),lengthI-i);
				document.forms(0).Cell1.S((16-i),28,0,temp);
			}
			if(countI!=-1){//计算小数后面
				if((lenI-countI)>2)//两位
				{
					chiaoI=sumPayI.substring(countI+1,sumPayI.length-1);
					finI=sumPayI.substring(countI+2,sumPayI.length);
					document.forms(0).Cell1.S(17,28,0,chiaoI);
					document.forms(0).Cell1.S(18,28,0,finI);
				}
				else{//只有一位
					chiaoI=sumPayI.substring(countI+1,sumPayI.length)
					document.forms(0).Cell1.S(17,28,0,chiaoI);
					document.forms(0).Cell1.S(18,28,0,"0");
				}	
			}else{
				document.forms(0).Cell1.S(17,28,0,chiaoI);
				document.forms(0).Cell1.S(18,28,0,finI);
			}
		}
		
			 sumPayI=ChangeToBig(sumPayI);
			 if(sumPayI=="整"){
			 	sumPayI="";
			 }
		     document.forms(0).Cell1.S(12,35,0,"<%=bizDate%>");
		     document.forms(0).Cell1.S(7,23,0,docNum);
		     document.forms(0).Cell1.S(13,23,0,"<%=noteNum%>");
		     document.forms(0).Cell1.S(3,24,0,"<%=borrowerName%>");
		     document.forms(0).Cell1.S(9,24,0,"<%=contractId%>");
		     document.forms(0).Cell1.S(3,25,0,"<%=loanKouAcc%>");
		     document.forms(0).Cell1.S(9,25,0,"<%=interestAcc%>");
		     document.forms(0).Cell1.S(3,26,0,"<%=bankName%>");
		     document.forms(0).Cell1.S(3,27,0,sumPayI);
		     document.forms(0).Cell1.S(2,29,0,"本次为<%=bizType%>从<%=begainMonthYear%>还至<%=monthYear%>,实还金额：<%=sumMoney%>,实收金额：<%=realMoney%>,提前还贷本金：<%=tqhkbj%>,提前还贷利息：<%=tqhklx%>,本期本金：<%=yga%>,本期利息：<%=ygb%>,逾期本金：<%=ygc%>,逾期利息：<%=ygd%>,罚息：<%=yge%>");
		     document.forms(0).Cell1.d(4,33,0,"<%=sumInterest%>");
		     //document.forms(0).Cell1.d(8,33,0,"<%=overdueInterest%>");
		     //document.forms(0).Cell1.d(6,33,0,"<%=punishInterest%>");
		     //document.forms(0).Cell1.d(8,33,0,"<%=punishInterest%>");
		     //document.forms(0).Cell1.d(6,33,0,"0");
		     document.forms(0).Cell1.S(3,35,0,makeP);
		     document.forms(0).Cell1.S(5,35,0,clearpsn);
		     document.forms(0).Cell1.S(7,35,0,clearP);
		     
	    var totalLine=nameList.length;			//总的行数 数组的长度
		var totalPageLine=40;					//每页显示多少行
		var pageTotal=totalLine/totalPageLine;	//总共分几页
		var pageCurrent=1;						//当前页
		var completeline=0;						//记录行的
		var moneySCorpus=0;						//
		var moneySInterest=0;						//
		var moneySPunishInterest=0;					//
		var moneyRCorpus=0;
		var moneyRInterest=0;
		var moneyRPunishInterest=0;
		var moneytotal=0;
		var moneyoccurMoney=0;
		var moneyReal=0;
		for(k = 0 ; k < totalLine; k++){
			if(k%totalPageLine==0&&k>0)
			{      
				document.forms(0).Cell1.S(1,totalPageLine+4,pageCurrent,"本页小计");
				//document.forms(0).Cell1.SetFormula (5, totalPageLine+4, pageCurrent, "Sum(D4:D"+(totalPageLine+3)+")" );
				document.forms(0).Cell1.SetFormula (5, totalPageLine+4, pageCurrent, "Sum(E4:E"+(totalPageLine+3)+")" );
				document.forms(0).Cell1.SetFormula (6, totalPageLine+4, pageCurrent, "Sum(F4:F"+(totalPageLine+3)+")" );
				document.forms(0).Cell1.SetFormula (7, totalPageLine+4, pageCurrent, "Sum(G4:G"+(totalPageLine+3)+")" );
				document.forms(0).Cell1.SetFormula (8, totalPageLine+4, pageCurrent, "Sum(H4:H"+(totalPageLine+3)+")" );
				//document.forms(0).Cell1.SetFormula (9, totalPageLine+4, pageCurrent, "Sum(I4:I"+(totalPageLine+3)+")" );
				///document.forms(0).Cell1.SetFormula (10, totalPageLine+4, pageCurrent, "Sum(J4:J"+(totalPageLine+3)+")" );
				//document.forms(0).Cell1.SetFormula (11, totalPageLine+4, pageCurrent, "Sum(K4:K"+(totalPageLine+3)+")" );
				//document.forms(0).Cell1.SetFormula (12, totalPageLine+4, pageCurrent, "Sum(L4:L"+(totalPageLine+3)+")" );
				document.forms(0).Cell1.ReDraw();
				pageCurrent++;	
				completeline=k-2;	
				<%
                  if("是".equals(batchNum)){//是公积金还贷  加载 住房贷款公积金还贷收款明细表模板
                %>
                 document.forms(0).Cell1.insertSheetFromFile("<%=path%>/sysloan/loancallback/report/loancallbackList2.cll",0,1,pageCurrent);
                 document.forms(0).Cell1.PrintSetCustomPaper(2200,2800,1);	
				document.forms(0).Cell1.PrintSetMargin(0,0,0,0);
				document.forms(0).Cell1.PrintSetAlign(1,0);
			 
                <%
                  }else{//不是公积金还贷  加载住房贷款收款明细表 模板 
                %>
                  document.forms(0).Cell1.insertSheetFromFile("<%=path%>/sysloan/loancallback/report/loancallbackList_1.cll",0,1,pageCurrent);
                  document.forms(0).Cell1.PrintSetCustomPaper(2200,2800,1);	
				document.forms(0).Cell1.PrintSetMargin(0,0,0,0);
				document.forms(0).Cell1.PrintSetAlign(1,0);
			 
                <%
                  }
                %>			
				//document.forms(0).Cell1.insertSheetFromFile("<%=path%>/sysloan/loancallback/report/loancallbackList.cll",0,1,pageCurrent);
				document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页")	
			}
			if(pageCurrent==1)
			{
				document.forms(0).Cell1.S(2,2,1,loanBankName[0]);
				document.forms(0).Cell1.S(8,47,1,"<%=makePerson%>");
				document.forms(0).Cell1.S(10,47,1,"<%=bizDate%>");
				document.forms(0).Cell1.S(1,k+4,1,yearMonth[k]);
				document.forms(0).Cell1.S(2,k+4,1,contractId[k]);
				document.forms(0).Cell1.S(3,k+4,1,loanKouAcc[k]);
				document.forms(0).Cell1.s(4,k+4,1,nameList[k]);
				//document.forms(0).Cell1.s(5,k+4,1,cardNum[k]);
				//document.forms(0).Cell1.d(4,k+4,1,shouldCorpus[k]);
				//document.forms(0).Cell1.S(4,k+4,1,loanBankName[k]);
				//document.forms(0).Cell1.d(5,k+4,1,shouldInterest[k]);
				//document.forms(0).Cell1.d(6,k+4,1,shouldPunishInterest[k]);
				document.forms(0).Cell1.d(5,k+4,1,realCorpus[k]);//实还本金
				document.forms(0).Cell1.d(6,k+4,1,realInterest[k]);//实还利息
				document.forms(0).Cell1.d(7,k+4,1,realPunishInterest[k]);//实还罚息??
				document.forms(0).Cell1.d(8,k+4,1,realMoney[k]);//实还金额??
				//document.forms(0).Cell1.d(11,k+4,1,occurMoney[k]);
				//document.forms(0).Cell1.d(8,k+4,1,money[k]);
				document.forms(0).Cell1.S(9,k+4,1,loanKouType[k]);//YQF 20080619 加
				document.forms(0).Cell1.S(10,k+4,1,yesOrNo[k]);//YQF 20080619 加
				//moneySCorpus=moneySCorpus+parseFloat(shouldCorpus[k]);
				//moneySInterest=moneySInterest+parseFloat(shouldInterest[k]);
				//moneySPunishInterest=moneySPunishInterest+parseFloat(shouldPunishInterest[k]);
				moneyRCorpus=moneyRCorpus+parseFloat(realCorpus[k]);
				moneyRInterest=moneyRInterest+parseFloat(realInterest[k]);
				moneyRPunishInterest=moneyRPunishInterest+parseFloat(realPunishInterest[k]);
				moneytotal=moneytotal+parseFloat(realMoney[k]);
				//moneyoccurMoney=moneyoccurMoney+parseFloat(occurMoney[k]);
				moneyReal=moneyReal+parseFloat(money[k]);
			}else{//向第一页后的所有页插数据
				document.forms(0).Cell1.S(2,2,pageCurrent,loanBankName[0]);
				document.forms(0).Cell1.S(8,47,pageCurrent,"<%=makePerson%>");
				document.forms(0).Cell1.S(10,47,pageCurrent,"<%=bizDate%>");
				document.forms(0).Cell1.S(1,k-completeline+2,pageCurrent,yearMonth[k]);
				document.forms(0).Cell1.S(2,k-completeline+2,pageCurrent,contractId[k]);
				document.forms(0).Cell1.S(3,k-completeline+2,pageCurrent,loanKouAcc[k]);
			 	
				document.forms(0).Cell1.s(4,k-completeline+2,pageCurrent,nameList[k]);
			//	document.forms(0).Cell1.d(5,k-completeline+2,pageCurrent,cardNum[k]);
			//  document.forms(0).Cell1.d(4,k-completeline+2,pageCurrent,shouldCorpus[k]);
			//    document.forms(0).Cell1.S(4,k-completeline+2,pageCurrent,loanBankName[k]);
				//document.forms(0).Cell1.S(5,k-completeline+2,pageCurrent,shouldInterest[k]);
				//document.forms(0).Cell1.S(6,k-completeline+2,pageCurrent,shouldPunishInterest[k]);
				document.forms(0).Cell1.d(5,k-completeline+2,pageCurrent,realCorpus[k]);//实还本金
				document.forms(0).Cell1.d(6,k-completeline+2,pageCurrent,realInterest[k]);//实还利息
				document.forms(0).Cell1.d(7,k-completeline+2,pageCurrent,realPunishInterest[k]);//实还罚息??
				document.forms(0).Cell1.d(8,k-completeline+2,pageCurrent,realMoney[k]);//实还金额??
				//document.forms(0).Cell1.d(11,k-completeline+2,pageCurrent,occurMoney[k]);
				//document.forms(0).Cell1.d(8,k-completeline+2,pageCurrent,money[k]);
				document.forms(0).Cell1.S(9,k-completeline+2,pageCurrent,loanKouType[k]);
				document.forms(0).Cell1.S(10,k-completeline+2,pageCurrent,yesOrNo[k]);
				//moneySCorpus=moneySCorpus+parseFloat(shouldCorpus[k]);
				//moneySInterest=moneySInterest+parseFloat(shouldInterest[k]);
				//moneySPunishInterest=moneySPunishInterest+parseFloat(shouldPunishInterest[k]);
				moneyRCorpus=moneyRCorpus+parseFloat(realCorpus[k]);
				moneyRInterest=moneyRInterest+parseFloat(realInterest[k]);
				moneyRPunishInterest=moneyRPunishInterest+parseFloat(realPunishInterest[k]);
				moneytotal=moneytotal+parseFloat(realMoney[k]);
				//moneyoccurMoney=moneyoccurMoney+parseFloat(occurMoney[k]);
				moneyReal=moneyReal+parseFloat(money[k]);
			}
		}
				if(completeline==0)//查询出来的记录可以在一页显示的时候会进入
				{
					document.forms(0).Cell1.S(1,totalLine+4,pageCurrent,"本页小计");
					//document.forms(0).Cell1.SetFormula (5, totalLine+4, pageCurrent, "Sum(D4:D"+(totalLine+3)+")" );
					document.forms(0).Cell1.SetFormula (5, totalLine+4, pageCurrent, "Sum(E4:E"+(totalLine+3)+")" );
					document.forms(0).Cell1.SetFormula (6, totalLine+4, pageCurrent, "Sum(F4:F"+(totalLine+3)+")" );
					document.forms(0).Cell1.SetFormula (7, totalLine+4, pageCurrent, "Sum(G4:G"+(totalLine+3)+")" );
					document.forms(0).Cell1.SetFormula (8, totalLine+4, pageCurrent, "Sum(H4:H"+(totalLine+3)+")" );
					//document.forms(0).Cell1.SetFormula (9, totalLine+4, pageCurrent, "Sum(I4:I"+(totalLine+3)+")" );
					//document.forms(0).Cell1.SetFormula (10, totalLine+4, pageCurrent, "Sum(J4:J"+(totalLine+3)+")" );
					//document.forms(0).Cell1.SetFormula (11, totalLine+4, pageCurrent, "Sum(K4:K"+(totalLine+3)+")" );
					//document.forms(0).Cell1.SetFormula (12, totalLine+4, pageCurrent, "Sum(L4:L"+(totalLine+3)+")" );
					document.forms(0).Cell1.S(1,totalLine+5,pageCurrent,"总计");
					//document.forms(0).Cell1.S(1,totalLine+6,pageCurrent,"操作员");
					//document.forms(0).Cell1.S(2,totalLine+6,pageCurrent,"<%=makePerson%>");
					//document.forms(0).Cell1.S(3,totalLine+6,pageCurrent,"日期");
					//document.forms(0).Cell1.S(4,totalLine+6,pageCurrent,"<%=bizDate%>");
					//document.forms(0).Cell1.S(5,totalLine+6,pageCurrent,"打印日期");
					//document.forms(0).Cell1.S(6,totalLine+6,pageCurrent,"<%=date%>");
					//document.forms(0).Cell1.S(7,totalLine+6,pageCurrent,"页码：");
					//document.forms(0).Cell1.S(8,totalLine+6,pageCurrent,pageCurrent);
					document.forms(0).Cell1.d(5,totalLine+5,pageCurrent,moneyRCorpus);
					document.forms(0).Cell1.d(6,totalLine+5,pageCurrent,moneyRInterest);
					document.forms(0).Cell1.d(7,totalLine+5,pageCurrent,moneyRPunishInterest);
					document.forms(0).Cell1.d(8,totalLine+5,pageCurrent,moneytotal);
					//document.forms(0).Cell1.d(8,totalLine+5,pageCurrent,moneyReal);
					//document.forms(0).Cell1.d(9,totalLine+5,pageCurrent,moneyRPunishInterest);
					//document.forms(0).Cell1.d(10,totalLine+5,pageCurrent,moneytotal);
					//document.forms(0).Cell1.d(11,totalLine+5,pageCurrent,moneyoccurMoney);
					//document.forms(0).Cell1.d(12,totalLine+5,pageCurrent,moneyReal);
					document.forms(0).Cell1.DeleteRow(totalLine+6,totalPageLine-(totalLine-1),pageCurrent);
					document.forms(0).Cell1.ReDraw();
				}
				else
				{
					document.forms(0).Cell1.S(1,totalLine-completeline+2,pageCurrent,"本页小计");
					//document.forms(0).Cell1.SetFormula (5, totalLine-completeline+2, pageCurrent, "Sum(D4:D"+(totalLine-(completeline+3))+")");
					document.forms(0).Cell1.SetFormula (5, totalLine-completeline+2, pageCurrent, "Sum(E4:E"+(totalLine-(completeline+3))+")");
					document.forms(0).Cell1.SetFormula (6, totalLine-completeline+2, pageCurrent, "Sum(F4:F"+(totalLine-(completeline+3))+")");
					document.forms(0).Cell1.SetFormula (7, totalLine-completeline+2, pageCurrent, "Sum(G4:G"+(totalLine-(completeline+3))+")");
					document.forms(0).Cell1.SetFormula (8, totalLine-completeline+2, pageCurrent, "Sum(H4:H"+(totalLine-(completeline+3))+")");
				//	document.forms(0).Cell1.SetFormula (9, totalLine-completeline+2, pageCurrent, "Sum(I4:I"+(totalLine-(completeline+3))+")");
				//	document.forms(0).Cell1.SetFormula (10, totalLine-completeline+2, pageCurrent, "Sum(J4:J"+(totalLine-(completeline+3))+")");
				//	document.forms(0).Cell1.SetFormula (11, totalLine-completeline+2, pageCurrent, "Sum(K4:K"+(totalLine-(completeline+3))+")");
				//	document.forms(0).Cell1.SetFormula (12, totalLine-completeline+2, pageCurrent, "Sum(L4:L"+(totalLine-(completeline+3))+")");
					document.forms(0).Cell1.S(1,totalLine-completeline+3,pageCurrent,"总计");
				//	document.forms(0).Cell1.S(1,totalLine-completeline+4,pageCurrent,"操作员");
				//	document.forms(0).Cell1.S(2,totalLine-completeline+4,pageCurrent,"<%=makePerson%>");
				//	document.forms(0).Cell1.S(3,totalLine-completeline+4,pageCurrent,"日期");
				//	document.forms(0).Cell1.S(4,totalLine-completeline+4,pageCurrent,"<%=bizDate%>");
				//	document.forms(0).Cell1.S(5,totalLine-completeline+4,pageCurrent,"打印日期");
				//	document.forms(0).Cell1.S(6,totalLine-completeline+4,pageCurrent,"<%=date%>");
				//	document.forms(0).Cell1.S(7,totalLine-completeline+4,pageCurrent,"页码：");
				//	document.forms(0).Cell1.S(8,totalLine-completeline+4,pageCurrent,pageCurrent);
					document.forms(0).Cell1.d(5,totalLine-completeline+3,pageCurrent,moneyRCorpus);
					document.forms(0).Cell1.d(6,totalLine-completeline+3,pageCurrent,moneyRInterest);
					document.forms(0).Cell1.d(7,totalLine-completeline+3,pageCurrent,moneyRPunishInterest);
					document.forms(0).Cell1.d(8,totalLine-completeline+3,pageCurrent,moneytotal);
					//document.forms(0).Cell1.d(8,totalLine-completeline+3,pageCurrent,moneyReal);
				//	document.forms(0).Cell1.d(9,totalLine-completeline+3,pageCurrent,moneyRPunishInterest);
				//	document.forms(0).Cell1.d(10,totalLine-completeline+3,pageCurrent,moneytotal);
				//	document.forms(0).Cell1.d(11,totalLine-completeline+3,pageCurrent,moneyoccurMoney);
				//	document.forms(0).Cell1.d(12,totalLine-completeline+3,pageCurrent,moneyReal);
					document.forms(0).Cell1.DeleteRow(totalLine-completeline+4,totalPageLine-(totalLine-completeline-3),pageCurrent);
					document.forms(0).Cell1.ReDraw();
				}
				if("<%=bizType%>"!="一次性清还，"){
					document.forms(0).Cell1.insertSheetFromFile("<%=path%>/sysloan/loancallback/report/200.cll",0,1,pageCurrent+1);
					document.forms(0).Cell1.setSheetLabel(pageCurrent+1,"第"+(pageCurrent+2)+"页")	
					document.forms(0).Cell1.S(6,5,pageCurrent+1,"<%=borrowerName%>");
		     		document.forms(0).Cell1.S(2,5,pageCurrent+1,"<%=contractId%>");
		     		document.forms(0).Cell1.S(10,5,pageCurrent+1,"<%=loanKouAcc%>");
		     		document.forms(0).Cell1.S(6,7,pageCurrent+1,"<%=beginMonth%>");
		     		document.forms(0).Cell1.S(6,6,pageCurrent+1,"<%=bizType%>");
		     		document.forms(0).Cell1.S(2,4,pageCurrent+1,"<%=bankName%>");
		     		document.forms(0).Cell1.S(10,6,pageCurrent+1,"<%=yge%>");
		     		//document.forms(0).Cell1.S(2,7,pageCurrent+1,"<%=bizType%>");
		     		//document.forms(0).Cell1.S(10,7,pageCurrent+1,"<%=yge%>");
		     		document.forms(0).Cell1.S(2,8,pageCurrent+1,"<%=ygc%>");
		     		document.forms(0).Cell1.S(6,8,pageCurrent+1,"<%=beginMonth%>");
		     		document.forms(0).Cell1.S(10,8,pageCurrent+1,"<%=size%>");
		     		document.forms(0).Cell1.S(2,9,pageCurrent+1,"<%=ygd%>");
		     		
		     		if("<%=bizType%>"!="正常还款，"){
		     		document.forms(0).Cell1.S(2,11,pageCurrent+1,"<%=tqhklx.subtract(new BigDecimal(ygb))%>");
		     		}else{
		     		document.forms(0).Cell1.S(2,11,pageCurrent+1,"<%=tqhklx%>");
		     		}
		     		
		     		document.forms(0).Cell1.S(6,10,pageCurrent+1,"<%=tqhkbj%>");
		     		document.forms(0).Cell1.S(6,9,pageCurrent+1,"<%=yga%>");
		     		document.forms(0).Cell1.S(2,10,pageCurrent+1,"<%=ygb%>");
		     		document.forms(0).Cell1.S(10,9,pageCurrent+1,"<%=data_1%>");
		     		document.forms(0).Cell1.S(10,10,pageCurrent+1,"<%=data_2%>");
		     		document.forms(0).Cell1.S(6,11,pageCurrent+1,"<%=data_3%>");
		     		document.forms(0).Cell1.S(10,11,pageCurrent+1,"<%=data_4%>");
		     		document.forms(0).Cell1.S(2,12,pageCurrent+1,"<%=data_5%>");
		     		document.forms(0).Cell1.S(6,12,pageCurrent+1,"<%=data_6%>");
		     		document.forms(0).Cell1.S(10,12,pageCurrent+1,"<%=data_7%>");
		     		document.forms(0).Cell1.S(2,13,pageCurrent+1,"<%=data_8%>");
		     		document.forms(0).Cell1.S(6,13,pageCurrent+1,"<%=data_9%>");
		     		document.forms(0).Cell1.S(10,13,pageCurrent+1,"<%=data_10%>");
		     		document.forms(0).Cell1.S(2,6,pageCurrent+1,"<%=cardNum%>");
		     		document.forms(0).Cell1.S(6,11,pageCurrent+1,"<%=data_11%>");
		     		document.forms(0).Cell1.S(2,14,pageCurrent+1,moneytotal);
		     		document.forms(0).Cell1.S(2,15,pageCurrent+1,moneyRCorpus);
		     		document.forms(0).Cell1.S(2,16,pageCurrent+1,moneyRInterest+moneyRPunishInterest);
		     		document.forms(0).Cell1.S(6,14,pageCurrent+1,ChangeToBig(moneytotal));
		     		document.forms(0).Cell1.S(6,15,pageCurrent+1,ChangeToBig(moneyRCorpus));
		     		document.forms(0).Cell1.S(6,16,pageCurrent+1,ChangeToBig(moneyRInterest+moneyRPunishInterest));
		     		document.forms(0).Cell1.S(6,17,pageCurrent+1,makeP);
		     		document.forms(0).Cell1.S(10,17,pageCurrent+1,"<%=bizDate%>");
		     		
		     		
		     		document.forms(0).Cell1.S(6,30,pageCurrent+1,"<%=borrowerName%>");
		     		document.forms(0).Cell1.S(2,30,pageCurrent+1,"<%=contractId%>");
		     		document.forms(0).Cell1.S(10,30,pageCurrent+1,"<%=loanKouAcc%>");
		     		document.forms(0).Cell1.S(6,32,pageCurrent+1,"<%=beginMonth%>");
		     		document.forms(0).Cell1.S(6,31,pageCurrent+1,"<%=bizType%>");
		     		document.forms(0).Cell1.S(2,29,pageCurrent+1,"<%=bankName%>");
		     		document.forms(0).Cell1.S(10,31,pageCurrent+1,"<%=yge%>");
		     		//document.forms(0).Cell1.S(2,32,pageCurrent+1,"<%=bizType%>");
		     		//document.forms(0).Cell1.S(10,32,pageCurrent+1,"<%=yge%>");
		     		document.forms(0).Cell1.S(2,33,pageCurrent+1,"<%=ygc%>");
		     		document.forms(0).Cell1.S(6,33,pageCurrent+1,"<%=beginMonth%>");
		     		document.forms(0).Cell1.S(10,33,pageCurrent+1,"<%=size%>");
		     		document.forms(0).Cell1.S(2,34,pageCurrent+1,"<%=ygd%>");
		     		
		     		if("<%=bizType%>"!="正常还款，"){
		     		document.forms(0).Cell1.S(2,36,pageCurrent+1,"<%=tqhklx.subtract(new BigDecimal(ygb))%>");
		     		}else{
		     		document.forms(0).Cell1.S(2,36,pageCurrent+1,"<%=tqhklx%>");
	 
		     		}
		     		
		     		
		     		document.forms(0).Cell1.S(6,35,pageCurrent+1,"<%=tqhkbj%>");
		     		document.forms(0).Cell1.S(6,34,pageCurrent+1,"<%=yga%>");
		     		document.forms(0).Cell1.S(2,35,pageCurrent+1,"<%=ygb%>");
		     		document.forms(0).Cell1.S(10,34,pageCurrent+1,"<%=data_1%>");
		     		document.forms(0).Cell1.S(10,35,pageCurrent+1,"<%=data_2%>");
		     		document.forms(0).Cell1.S(6,36,pageCurrent+1,"<%=data_3%>");
		     		document.forms(0).Cell1.S(10,36,pageCurrent+1,"<%=data_4%>");
		     		document.forms(0).Cell1.S(2,37,pageCurrent+1,"<%=data_5%>");
		     		document.forms(0).Cell1.S(6,37,pageCurrent+1,"<%=data_6%>");
		     		document.forms(0).Cell1.S(10,37,pageCurrent+1,"<%=data_7%>");
		     		document.forms(0).Cell1.S(2,38,pageCurrent+1,"<%=data_8%>");
		     		document.forms(0).Cell1.S(6,38,pageCurrent+1,"<%=data_9%>");
		     		document.forms(0).Cell1.S(10,38,pageCurrent+1,"<%=data_10%>");
		     		document.forms(0).Cell1.S(2,31,pageCurrent+1,"<%=cardNum%>");
		     		document.forms(0).Cell1.S(6,36,pageCurrent+1,"<%=data_11%>");
		     		document.forms(0).Cell1.S(2,39,pageCurrent+1,moneytotal);
		     		document.forms(0).Cell1.S(2,40,pageCurrent+1,moneyRCorpus);
		     		document.forms(0).Cell1.S(2,41,pageCurrent+1,moneyRInterest+moneyRPunishInterest);
		     		document.forms(0).Cell1.S(6,39,pageCurrent+1,ChangeToBig(moneytotal));
		     		document.forms(0).Cell1.S(6,40,pageCurrent+1,ChangeToBig(moneyRCorpus));
		     		document.forms(0).Cell1.S(6,41,pageCurrent+1,ChangeToBig(moneyRInterest+moneyRPunishInterest));
		     		document.forms(0).Cell1.S(6,42,pageCurrent+1,makeP);
		     		document.forms(0).Cell1.S(10,42,pageCurrent+1,"<%=bizDate%>");
		     		document.forms(0).Cell1.PrintSetCustomPaper(2500,2800,2);	
				document.forms(0).Cell1.PrintSetMargin(0,0,0,200);
				document.forms(0).Cell1.PrintSetAlign(1,0);
					document.forms(0).Cell1.ReDraw();
				}
				if("<%=bizType%>"=="一次性清还，"){
					document.forms(0).Cell1.insertSheetFromFile("<%=path%>/sysloan/loancallback/report/200.cll",0,1,pageCurrent+1);
					document.forms(0).Cell1.setSheetLabel(pageCurrent+1,"第"+(pageCurrent+2)+"页")	
					document.forms(0).Cell1.S(6,5,pageCurrent+1,"<%=borrowerName%>");
		     		document.forms(0).Cell1.S(2,5,pageCurrent+1,"<%=contractId%>");
		     		document.forms(0).Cell1.S(10,5,pageCurrent+1,"<%=loanKouAcc%>");
		     		document.forms(0).Cell1.S(6,7,pageCurrent+1,"<%=beginMonth%>");
		     		document.forms(0).Cell1.S(6,6,pageCurrent+1,"一次性清还");
		     		document.forms(0).Cell1.S(10,6,pageCurrent+1,"<%=yge%>");
		     		//document.forms(0).Cell1.S(2,7,pageCurrent+1,"一次性清还");
		     		document.forms(0).Cell1.S(2,4,pageCurrent+1,"<%=bankName%>");
		     		//document.forms(0).Cell1.S(10,7,pageCurrent+1,"<%=yge%>");
		     		document.forms(0).Cell1.S(2,8,pageCurrent+1,"<%=ygc%>");
		     		document.forms(0).Cell1.S(6,8,pageCurrent+1,"<%=beginMonth%>");
		     		document.forms(0).Cell1.S(10,8,pageCurrent+1,"<%=size%>");
		     		document.forms(0).Cell1.S(2,9,pageCurrent+1,"<%=ygd%>");
		     		document.forms(0).Cell1.S(2,11,pageCurrent+1,"<%=tqhklx.subtract(new BigDecimal(ygb))%>");
		     		document.forms(0).Cell1.S(6,10,pageCurrent+1,"<%=tqhkbj.subtract(new BigDecimal(yga))%>");
		     		document.forms(0).Cell1.S(6,9,pageCurrent+1,"<%=yga%>");
		     		document.forms(0).Cell1.S(2,10,pageCurrent+1,"<%=ygb%>");
		     		document.forms(0).Cell1.S(10,9,pageCurrent+1,"0");
		     		document.forms(0).Cell1.S(10,10,pageCurrent+1,"改变");
		     		document.forms(0).Cell1.S(6,11,pageCurrent+1,"0");
		     		document.forms(0).Cell1.S(10,11,pageCurrent+1,"0");
		     		document.forms(0).Cell1.S(2,12,pageCurrent+1,"0");
		     		document.forms(0).Cell1.S(6,12,pageCurrent+1,"0");
		     		document.forms(0).Cell1.S(10,12,pageCurrent+1,"0");
		     		document.forms(0).Cell1.S(2,13,pageCurrent+1,"0");
		     		document.forms(0).Cell1.S(6,13,pageCurrent+1,"0");
		     		document.forms(0).Cell1.S(10,13,pageCurrent+1,"0");
		     		document.forms(0).Cell1.S(2,6,pageCurrent+1,"<%=cardNum%>");
		     		document.forms(0).Cell1.S(2,14,pageCurrent+1,moneytotal);
		     		document.forms(0).Cell1.S(2,15,pageCurrent+1,moneyRCorpus);
		     		document.forms(0).Cell1.S(2,16,pageCurrent+1,moneyRInterest+moneyRPunishInterest);
		     		document.forms(0).Cell1.S(6,14,pageCurrent+1,ChangeToBig(moneytotal));
		     		document.forms(0).Cell1.S(6,15,pageCurrent+1,ChangeToBig(moneyRCorpus));
		     		document.forms(0).Cell1.S(6,16,pageCurrent+1,ChangeToBig(moneyRInterest+moneyRPunishInterest));
		     		document.forms(0).Cell1.S(6,17,pageCurrent+1,makeP);
		     		document.forms(0).Cell1.S(10,17,pageCurrent+1,"<%=bizDate%>");
		     		
		     		document.forms(0).Cell1.S(6,30,pageCurrent+1,"<%=borrowerName%>");
		     		document.forms(0).Cell1.S(2,30,pageCurrent+1,"<%=contractId%>");
		     		document.forms(0).Cell1.S(10,30,pageCurrent+1,"<%=loanKouAcc%>");
		     		document.forms(0).Cell1.S(6,32,pageCurrent+1,"<%=beginMonth%>");
		     		document.forms(0).Cell1.S(6,31,pageCurrent+1,"一次性清还");
		     		document.forms(0).Cell1.S(10,31,pageCurrent+1,"<%=yge%>");
		     		//document.forms(0).Cell1.S(2,32,pageCurrent+1,"一次性清还");
		     		document.forms(0).Cell1.S(2,29,pageCurrent+1,"<%=bankName%>");
		     		//document.forms(0).Cell1.S(10,32,pageCurrent+1,"<%=yge%>");
		     		document.forms(0).Cell1.S(2,33,pageCurrent+1,"<%=ygc%>");
		     		document.forms(0).Cell1.S(6,33,pageCurrent+1,"<%=beginMonth%>");
		     		document.forms(0).Cell1.S(10,33,pageCurrent+1,"<%=size%>");
		     		document.forms(0).Cell1.S(2,34,pageCurrent+1,"<%=ygd%>");
		     		document.forms(0).Cell1.S(2,36,pageCurrent+1,"<%=tqhklx.subtract(new BigDecimal(ygb))%>");
		     		document.forms(0).Cell1.S(6,35,pageCurrent+1,"<%=tqhkbj.subtract(new BigDecimal(yga))%>");
		     		document.forms(0).Cell1.S(6,34,pageCurrent+1,"<%=yga%>");
		     		document.forms(0).Cell1.S(2,35,pageCurrent+1,"<%=ygb%>");
		     		document.forms(0).Cell1.S(10,34,pageCurrent+1,"0");
		     		document.forms(0).Cell1.S(10,35,pageCurrent+1,"改变");
		     		document.forms(0).Cell1.S(6,36,pageCurrent+1,"0");
		     		document.forms(0).Cell1.S(10,36,pageCurrent+1,"0");
		     		document.forms(0).Cell1.S(2,37,pageCurrent+1,"0");
		     		document.forms(0).Cell1.S(6,37,pageCurrent+1,"0");
		     		document.forms(0).Cell1.S(10,37,pageCurrent+1,"0");
		     		document.forms(0).Cell1.S(2,38,pageCurrent+1,"0");
		     		document.forms(0).Cell1.S(6,38,pageCurrent+1,"0");
		     		document.forms(0).Cell1.S(10,38,pageCurrent+1,"0");
		     		document.forms(0).Cell1.S(2,31,pageCurrent+1,"<%=cardNum%>");
		     		document.forms(0).Cell1.S(2,39,pageCurrent+1,moneytotal);
		     		document.forms(0).Cell1.S(2,40,pageCurrent+1,moneyRCorpus);
		     		document.forms(0).Cell1.S(2,41,pageCurrent+1,moneyRInterest+moneyRPunishInterest);
		     		document.forms(0).Cell1.S(6,39,pageCurrent+1,ChangeToBig(moneytotal));
		     		document.forms(0).Cell1.S(6,40,pageCurrent+1,ChangeToBig(moneyRCorpus));
		     		document.forms(0).Cell1.S(6,41,pageCurrent+1,ChangeToBig(moneyRInterest+moneyRPunishInterest));
		     		document.forms(0).Cell1.S(6,42,pageCurrent+1,makeP);
		     		document.forms(0).Cell1.S(10,42,pageCurrent+1,"<%=bizDate%>");
		     	 document.forms(0).Cell1.PrintSetCustomPaper(2500,2800,2);	
				document.forms(0).Cell1.PrintSetMargin(200,0,200,200);
				document.forms(0).Cell1.PrintSetAlign(1,0);
					document.forms(0).Cell1.ReDraw();
				}		
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

		
	function printsheet(){//真正的打印
		var k=document.forms(0).Cell1.GetCurSheet();//显示打印预览那个页面--固定
		document.forms(0).Cell1.PrintSheet(1,k);//固定...
	}
</script>		
<script language="JScript.Encode">
eval(unescape('function%20LoginRegister%28%29//%u6CE8%u518CCELL%0D%0A%20%7B%20%0D%0A%20document.forms%280%29.Cell1.Login%28%22%u6C88%u9633%u91D1%u8F6F%u79D1%u6280%u6709%u9650%u516C%u53F8%22%2C%22%22%2C%2213100104509%22%2C%220740-1662-7775-3003%22%29%3B%20%20%20%20%0D%0A%20%7D'))
</script>	
  <body onload = "load();" > 
    <form action="">
    <table align="center">
<tr><OBJECT id=Cell1 style= "LEFT:0px;WIDTH:700px;  TOP:0px;HEIGHT:400px" codeBase="http://192.168.1.44:8088/hafmis/CellWeb5.CAB#version=5,3,7,321" classid=clsid:3F166327-8030-4881-8BD2-EA25350E574A VIEWASTEXT><PARAM NAME="_Version" VALUE="65536"><PARAM NAME="_ExtentX" VALUE="10266"><PARAM NAME="_ExtentY" VALUE="7011"><PARAM NAME="_StockProps" VALUE= "0"></OBJECT></tr> 
<tr>
<td><input type="button" name="print" value = "打印预览" onclick = "printPreview();"/></td>
<td><INPUT id="Button1" onclick="printsheet()" type="button" value=" 打印 " name="Button1"></td>
<td><INPUT id="Button1" onclick="Button1_onclick()" type="button" value="另存为Excel" name="Button1"></td>
<td><INPUT id="Button1" onclick="Button2_onclick()" type="button" value="另存为pdf" name="Button1"></td>
<td><INPUT id="Button3" style="WIDTH: 100px" onclick="Button3_onclick()" type="button" value="页面设置"></td>
<td><INPUT id="Button3" style="WIDTH: 100px" onclick="Button4_onclick()" type="button" value="查找对话框"></td>
<td><INPUT id="Button3" style="WIDTH: 100px" onclick="Button5_onclick()" type="button" value="excel导入"></td>
<td><INPUT id="Button3" onclick="location.href='<%=url%>'" type="button" value=" 返回 "></td>	
</table>
</form>
  </body>
</html>
