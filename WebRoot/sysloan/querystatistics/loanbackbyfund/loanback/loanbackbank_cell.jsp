<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic"%>
<%@ page import="org.xpup.hafmis.sysloan.querystatistics.loanbackbyfund.loanback.dto.LoanBackBankDTO"%>
<%@ page import="java.util.List"%>
<%@ page import="java.math.BigDecimal"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	String makePerson = (String)request.getAttribute("userName");
	String bisDate = (String)request.getAttribute("plbizDate");
   	String printDate = (String)request.getSession().getAttribute("loanbackprintdate");
	String url=(String)request.getAttribute("URL");
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
	document.forms(0).Cell1.openfile("<%=path%>/sysloan/querystatistics/loanbackbyfund/loanback/report/bankback.cll","");
	var collbankname=[]; 
    var corpus=[];
    var interest=[];
	var corpusInterest=[];
	var all=[];
	var count=[];
	var i=0;

	<%
    	    List list =(List)request.getSession().getAttribute("getBankList_yg");
    	    String batchNum = (String)request.getAttribute("batchNum");
    	    BigDecimal corpus = new BigDecimal(0.00);
    	    BigDecimal interest = new BigDecimal(0.00);
    	    BigDecimal corpusInterest = new BigDecimal(0.00);
    	    BigDecimal all = new BigDecimal(0.00);
    	    int count = 0;
	%>
			<%
  			for(int j=0;j<list.size();j++)
  			{
  				LoanBackBankDTO dto=(LoanBackBankDTO)list.get(j);
  				corpus=corpus.add(dto.getCorpus());
  				interest=interest.add(dto.getInterest());
  				corpusInterest=corpusInterest.add(dto.getCorpusInterest());
  				all=all.add(dto.getAll());
  				count += dto.getCount();
 	%>
 				 collbankname[i]="<%=dto.getCollbankname() %>";
 				 if(collbankname[i]=="null"){
 				 	collbankname[i]="";
 				 }
			     corpus[i]="<%=dto.getCorpus()%>";
			     if(corpus[i]=="null"){
			     	corpus[i]="";
			     }
			     interest[i]="<%=dto.getInterest()%>";
			     if(interest[i]=="null"){
			     	interest[i]="";
			     }
			     corpusInterest[i]="<%=dto.getCorpusInterest()%>";
			     if(corpusInterest[i]=="null"){
			     	corpusInterest[i]="";
			     }
			     all[i]="<%=dto.getAll()%>";
			     if(all[i]=="null"){
			     	all[i]="";
			     }
			     count[i]="<%=dto.getCount()%>";
			     if(count[i]=="null"){
			     	count[i]="";
			     }
 	          	i++; 
 	<%
 			}
 	%>		     
	    var totalLine=collbankname.length;			//总的行数 数组的长度
		var totalPageLine=10;					//每页显示多少行
		var pageTotal=totalLine/totalPageLine;	//总共分几页
		var n=0;
		for(k = 0 ; k < totalLine; k++){
			document.forms(0).Cell1.S(1,k+4,0,collbankname[k]);
			document.forms(0).Cell1.d(2,k+4,0,corpus[k]);
			document.forms(0).Cell1.d(3,k+4,0,interest[k]);
			document.forms(0).Cell1.d(4,k+4,0,corpusInterest[k]);
			document.forms(0).Cell1.d(5,k+4,0,all[k]);
			document.forms(0).Cell1.d(6,k+4,0,count[k]);
			if(k==totalLine-1){
				if(k!=0){
					n=1;
					document.forms(0).Cell1.S(1,k+5,0,"合计：");
					document.forms(0).Cell1.d(2,k+5,0,"<%=corpus%>");
					document.forms(0).Cell1.d(3,k+5,0,"<%=interest%>");
					document.forms(0).Cell1.d(4,k+5,0,"<%=corpusInterest%>");
					document.forms(0).Cell1.d(5,k+5,0,"<%=all%>");
					document.forms(0).Cell1.d(6,k+5,0,"<%=count%>");
				}
			}
		}
		if("<%=batchNum%>"!="null"){
			document.forms(0).Cell1.S(4,2,0,"批次号："+"<%=batchNum%>");
		}
		document.forms(0).Cell1.S(1,14,0,"业务日期："+"<%=printDate%>");
		document.forms(0).Cell1.S(4,14,0,"操作员："+"<%=makePerson%>");
		if(n==0){
			document.forms(0).Cell1.DeleteRow(totalLine+4,12-totalLine-2,0);
		}else{
			document.forms(0).Cell1.DeleteRow(totalLine+5,12-totalLine-3,0);
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
<td><INPUT id="Button3" onclick="javascript:history.back();" type="button" value=" 返回 "></td>	
</table>
</form>
  </body>
</html>
