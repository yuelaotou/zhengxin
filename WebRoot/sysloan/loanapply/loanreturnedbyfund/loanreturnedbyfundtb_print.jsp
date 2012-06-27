<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.util.List"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic"%>
<%@ page
	import="org.xpup.hafmis.sysloan.loanapply.loanreturnedbyfund.dto.LoanreturnedbyfundTbDTO"%>
<%@ include file="/checkUrl.jsp"%>
<%@ page import="java.math.BigDecimal"%>

<%
	String path = request.getContextPath();
	List list = (List) request.getSession().getAttribute("cellList");
	String temp_date = (String) request.getSession().getAttribute(
			"date_temp");
	String print_name = (String) request.getSession().getAttribute(
			"print_name");
%>
<html>
	<head>
		<script src="<%=path%>/js/common.js"></script>
	</head>
	<script type="text/javascript">
	function load(){
	loginReg();	
	document.forms(0).Cell1.openfile("<%=path%>/sysloan/loanapply/loanreturnedbyfund/loanreturnedbyfundtb_print.cll","");
			var contractId=[];
			var name=[];
			var cardNum=[];
			var orgId=[];
			var orgName=[];
			var seq=[];
			var xieYiNum=[];
			var banLiRiQi=[];		
			var status=[];				
			var i=0;
			var temp_date="";
			var temp_name="";
			
			temp_date="<%=temp_date%>"; 
			temp_name="<%=print_name%>"; 
            <%
                
				LoanreturnedbyfundTbDTO loanreturnedbyfundTbDTO = new LoanreturnedbyfundTbDTO();
				for(int j=0;j<list.size();j++){
				loanreturnedbyfundTbDTO = (LoanreturnedbyfundTbDTO)list.get(j);			
			%>
			
			    //把数据传到JS的数组里面..
			    contractId[i] = "<%=loanreturnedbyfundTbDTO.getContractId()%>"; 
			    name[i] = "<%=loanreturnedbyfundTbDTO.getBorrowerName()%>";
				cardNum[i] = "<%=loanreturnedbyfundTbDTO.getCardNum()%>";
				orgId[i] = "<%=loanreturnedbyfundTbDTO.getOrgId()%>";
				orgName[i] = "<%=loanreturnedbyfundTbDTO.getOrgName()%>";
				seq[i] = "<%=loanreturnedbyfundTbDTO.getSeq()%>";
				xieYiNum[i] = "<%=loanreturnedbyfundTbDTO.getXieYuNum()%>";
				banLiRiQi[i] = "<%=loanreturnedbyfundTbDTO.getRiQi()%>";
				status[i] = "<%=loanreturnedbyfundTbDTO.getStatus()%>";
				
				i++;
			<%}%>
			var totalLine=contractId.length;			//总的行数 数组的长度
			var totalPageLine=27;					//每页显示多少行--除了第一行
			var pageTotal=totalLine/totalPageLine;	//总共分几页 总行数/每页的行数
			var pageCurrent=0;						//当前页
			var completeline=0;						//记录行的	
			var moneytotal1=0;		
			var iPage = getInt(totalLine,totalPageLine);
			var aa=totalLine%totalPageLine;
			var aaa=1;
			
			
			for(k = 0 ; k < totalLine; k++){
				if(k%totalPageLine==0&&k>0)
				{			
				pageCurrent++;
				completeline=k-2;		
				document.forms(0).Cell1.insertSheetFromFile("<%=path%>/sysloan/loanapply/loanreturnedbyfund/loanreturnedbyfundtb_print.cll",0,1,pageCurrent);
				document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页")	;
				}
				if(pageCurrent==0)
				{	document.forms(0).Cell1.s(1,k+4,0,""+xieYiNum[k]);
					document.forms(0).Cell1.s(2,k+4,0,""+contractId[k]);
					document.forms(0).Cell1.s(3,k+4,0,""+name[k]);
					document.forms(0).Cell1.s(4,k+4,0,""+cardNum[k]);
					document.forms(0).Cell1.S(5,k+4,0,""+orgId[k]);
					document.forms(0).Cell1.s(6,k+4,0,""+orgName[k]);
					document.forms(0).Cell1.s(9,k+4,0,""+seq[k]);
					document.forms(0).Cell1.s(8,k+4,0,""+status[k]);
					document.forms(0).Cell1.s(7,k+4,0,""+banLiRiQi[k]);
					
					document.forms(0).Cell1.s(4,2,0,""+temp_date);
					document.forms(0).Cell1.s(2,31,0,"制表人:"+temp_name);
					document.forms(0).Cell1.s(6,31,0,"营口市住房公积金管理中心.");
					}
				else{//向第一页后的所有页插数据
					document.forms(0).Cell1.s(1,k-completeline+2,pageCurrent,xieYiNum[k]);
					document.forms(0).Cell1.s(2,k-completeline+2,pageCurrent,contractId[k]);
					document.forms(0).Cell1.s(3,k-completeline+2,pageCurrent,name[k]);
					document.forms(0).Cell1.s(4,k-completeline+2,pageCurrent,cardNum[k]);
					document.forms(0).Cell1.S(5,k-completeline+2,pageCurrent,orgId[k]);
					document.forms(0).Cell1.s(6,k-completeline+2,pageCurrent,orgName[k]);
					document.forms(0).Cell1.s(9,k-completeline+2,pageCurrent,seq[k]);
					document.forms(0).Cell1.s(8,k-completeline+2,pageCurrent,status[k]);
					document.forms(0).Cell1.s(7,k-completeline+2,pageCurrent,banLiRiQi[k]);
					document.forms(0).Cell1.s(4,2,pageCurrent,""+temp_date);
					
				    if(pageCurrent+aaa==iPage){	
				    			  
				    aaa++;					
				    document.forms(0).Cell1.DeleteRow(aa+4,totalPageLine-aa,pageCurrent);
				    
				    }
				    else{
				      if(aaa!=1){
				      
				    document.forms(0).Cell1.s(2,aa+4,pageCurrent,"制表人:"+temp_name);
					document.forms(0).Cell1.s(6,aa+4,pageCurrent,"营口市住房公积金管理中心.");
				      }
				    document.forms(0).Cell1.s(2,31,pageCurrent,"制表人:"+temp_name);
					document.forms(0).Cell1.s(6,31,pageCurrent,"营口市住房公积金管理中心.");
					
				    }
		            
				}		
			}	
	document.forms(0).Cell1.AllowExtend=false;
	document.forms(0).Cell1.AllowDragdrop=false;
	document.forms(0).Cell1.WorkbookReadonly=true;	

}
	// 取总页数
	function getInt(i,k) { 
		var page=0; 
		var j; 
		j=Math.round(i/k)-i/k; 
		if (j>=0) 
		page=Math.round(i/k)-1; 
		if (j<=0) 
		page=Math.round(i/k); 
		if(j!=0)
		page=page+1;
		if(j==0)
		page=i/k;
		return page; 
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
	function back(){
	document.location='loanreturnedbyfundTbShowAC.do';
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
						style="LEFT:0px;WIDTH:850px;  TOP:0px;HEIGHT:500px"
						codeBase="http://192.168.1.44:8088/hafmis/CellWeb5.CAB#version=5,3,7,321"
						classid=clsid:3F166327-8030-4881-8BD2-EA25350E574A VIEWASTEXT>
						<PARAM NAME="_Version" VALUE="65536">
						<PARAM NAME="_ExtentX" VALUE="10266">
						<PARAM NAME="_ExtentY" VALUE="7011">
						<PARAM NAME="_StockProps" VALUE="0">
					</OBJECT>
				</tr>
				<tr>
					<td align="center">
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
						<INPUT id="Button1" onclick="printsheet()" type="button"
							value=" 打印 " name="Button1">
						<INPUT id="Button3" onclick="back();" type="button" value=" 返回 ">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>


