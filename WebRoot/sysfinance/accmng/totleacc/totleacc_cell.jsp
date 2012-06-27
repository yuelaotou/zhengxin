<%@ page language="java" pageEncoding="UTF-8" import="java.util.List"%>
<%@ page
	import="org.xpup.hafmis.sysfinance.accmng.totleacc.dto.TotleaccDTO"%>
<%
	String path = request.getContextPath();
	String url = (String) request.getAttribute("url");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<script src="<%=path%>/js/common.js"></script>
	</head>
	<script type="text/javascript">
	function load(){	
	loginReg();
	document.forms(0).Cell1.openfile("<%=path%>/sysfinance/accmng/totleacc/totalaccTable.cll","");
	document.forms(0).Cell1.insertSheetFromFile("<%=path%>/sysfinance/accmng/totleacc/totalacc_print.cll",0,1,1);	
	document.forms(0).Cell1.ShowPageBreak("0");  
	document.forms(0).Cell1.setSheetLabel(1,"第2页");
	document.forms(0).Cell1.AllowSizeColInGrid =[true];
	var subjectCode=[];//科目代码
	var subjectname=[];//科目名称
	var credenceDate=[];//日期
	var credcharnum=[];// 凭证字号
	var summay=[];//摘要
	var debit=[];//贷方金额
	var credit=[];//借方金额
	var dirtection=[];//方向
	var money=[];//余额
	var i=0;
		<%
    		List list=(List) request.getSession().getAttribute("list");
  			TotleaccDTO dto=null;
  			for(int j=0;j<list.size();j++){
  				dto=(TotleaccDTO)list.get(j);
  				%>
					subjectCode[i]="<%=dto.getSubjectCode()%>";
					subjectname[i]="<%=dto.getSubjectname()%>";
					credenceDate[i]="<%=dto.getCredenceDate()%>";
					credcharnum[i]="<%=dto.getCredcharnum()%>";
					summay[i]="<%=dto.getSummay()%>";
					debit[i]=<%=dto.getDebit()%>;
					credit[i]=<%=dto.getCredit()%>;
					dirtection[i]="<%=dto.getDirtection()%>";
					money[i]=<%=dto.getMoney()%>;
					i++;
  				<%
 			}
 		%> 	
 		var totalLine=subjectCode.length;		//总的行数
		var totalPageLine=10000000000000000;					//每页显示多少行
		var pageCurrent=1;						//当前页
		var completeline=0;						//记录行的
		var s=0;								//为判断时候为当前科目使用
		var subject;							//科目代码
		var subjectcodename=[];					//科目代码(目录)
		var pagesize=[];					    //页码(目录)
		var totalPageLine1=40;					//每页显示多少行
		for(j = 0 ; j < totalLine; j++){
			if(j=='0'){
				subject=subjectCode[j];
				subjectcodename[s]="("+subjectCode[j]+")"+subjectname[j];
				pagesize[s]="1";
				s++;
			}
			if(j%totalPageLine==0&&j>0){
				document.forms(0).Cell1.ReDraw();
				pageCurrent++;	
				completeline=j-2;
				document.forms(0).Cell1.insertSheetFromFile("<%=path%>/sysfinance/accmng/totleacc/totalacc_print.cll",0,1,pageCurrent);
				document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页");
			}
			if(subjectCode[j]!=subject){//不为这个科目的话,创建新的页面  
				subject=subjectCode[j];
				document.forms(0).Cell1.ReDraw();
				pageCurrent++;	
				completeline=j-2;
				document.forms(0).Cell1.insertSheetFromFile("<%=path%>/sysfinance/accmng/totleacc/totalacc_print.cll",0,1,pageCurrent);				
				document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页");
				document.forms(0).Cell1.PrintSetCustomPaper(2100,2970,1);
				document.forms(0).Cell1.PrintSetMargin(100,50,100,50);
				document.forms(0).Cell1.PrintSetAlign(1,0);
				subjectcodename[s]="("+subjectCode[j]+")"+subjectname[j];
				pagesize[s]=pageCurrent;
				s++;
			}
			if(pageCurrent==1){
				document.forms(0).Cell1.S(1,3,1,"科目:("+subjectCode[j]+")"+subjectname[j]+"");
				//document.forms(0).Cell1.S(1,j+5,1,subjectCode[j]);
				//document.forms(0).Cell1.S(2,j+5,1,subjectname[j]);
				document.forms(0).Cell1.S(1,j+5,1,credenceDate[j]);
				document.forms(0).Cell1.S(2,j+5,1,credcharnum[j]);
				document.forms(0).Cell1.S(3,j+5,1,summay[j]);
				document.forms(0).Cell1.d(4,j+5,1,debit[j]);
				document.forms(0).Cell1.d(5,j+5,1,credit[j]);
				document.forms(0).Cell1.S(6,j+5,1,dirtection[j]);
				document.forms(0).Cell1.d(7,j+5,1,money[j]);
				document.forms(0).Cell1.S(1,41,1,"第"+(pageCurrent)+"页");
			}else{//向第一页后的所有页插数据
				document.forms(0).Cell1.S(1,3,pageCurrent,"科目:("+subjectCode[j]+")"+subjectname[j]+"");
				//document.forms(0).Cell1.S(1,j-completeline+3,pageCurrent,subjectCode[j]);
				//document.forms(0).Cell1.S(2,j-completeline+3,pageCurrent,subjectname[j]);
				document.forms(0).Cell1.S(1,j-completeline+3,pageCurrent,credenceDate[j]);
				document.forms(0).Cell1.S(2,j-completeline+3,pageCurrent,credcharnum[j]);
				document.forms(0).Cell1.S(3,j-completeline+3,pageCurrent,summay[j]);
				document.forms(0).Cell1.d(4,j-completeline+3,pageCurrent,debit[j]);
				document.forms(0).Cell1.d(5,j-completeline+3,pageCurrent,credit[j]);
				document.forms(0).Cell1.S(6,j-completeline+3,pageCurrent,dirtection[j]);
				document.forms(0).Cell1.d(7,j-completeline+3,pageCurrent,money[j]);
				document.forms(0).Cell1.S(1,41,pageCurrent,"第"+(pageCurrent)+"页");
				
			}	
			if(j==(totalLine-1)){//插入目录
			 	var intotalLine=subjectcodename.length;		//总的行数
				var inpageCurrent=pageCurrent+2;			//当前页
				var incompleteline=0;						//记录行的
				var linemenu="…………………………………………………………………………………………………………………………………………………………………………………………………………";
				
				document.forms(0).Cell1.insertSheetFromFile("<%=path%>/sysfinance/accmng/totleacc/listaccMenu.cll",0,1,(pageCurrent+1));
				document.forms(0).Cell1.setSheetLabel((pageCurrent+1),"第"+(pageCurrent+2)+"页");
				document.forms(0).Cell1.PrintSetCustomPaper(2100,2970,1);
				document.forms(0).Cell1.PrintSetMargin(100,50,100,50);
				document.forms(0).Cell1.PrintSetAlign(1,0);
						
				for(k=0;k<intotalLine;k++){
					if(k%totalPageLine1==0&&k>0){
						document.forms(0).Cell1.ReDraw();
						pageCurrent++;	
						incompleteline=k-2;
						document.forms(0).Cell1.insertSheetFromFile("<%=path%>/sysfinance/accmng/totleacc/listaccMenu.cll",0,1,(pageCurrent+1));
						document.forms(0).Cell1.setSheetLabel((pageCurrent+1),"第"+(pageCurrent+2)+"页");
						document.forms(0).Cell1.PrintSetCustomPaper(2100,2970,1);
						document.forms(0).Cell1.PrintSetMargin(100,50,100,50);
						document.forms(0).Cell1.PrintSetAlign(1,0);
						document.forms(0).Cell1.ShowPageBreak("0");
					}
					if(inpageCurrent==(pageCurrent+2)){
						document.forms(0).Cell1.S(1,k+4,(pageCurrent+1),subjectcodename[k]+linemenu);
						//document.forms(0).Cell1.S(4,k+4,(pageCurrent+1),linemenu);
						document.forms(0).Cell1.S(9,k+4,(pageCurrent+1),pagesize[k]);
					}else{//向第一页后的所有页插数据
						document.forms(0).Cell1.S(1,k-incompleteline+2,(pageCurrent+1),subjectcodename[k]+linemenu);
						//document.forms(0).Cell1.S(4,k-incompleteline+2,(pageCurrent+1),linemenu);
						document.forms(0).Cell1.S(9,k-incompleteline+2,(pageCurrent+1),pagesize[k]);
					}	
				}				
			}
		}
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
		document.forms(0).Cell1.ExportPdfFile("d:\\aa.pdf",-1,1,1);
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
	<script LANGUAGE=javascript FOR=Cell1 EVENT=MouseLClick(col,row,updn)>
Cell1_MouseLClick(col, row, updn);
</script>
	<script language="JScript.Encode">
eval(unescape('function%20LoginRegister%28%29//%u6CE8%u518CCELL%0D%0A%20%7B%20%0D%0A%20document.forms%280%29.Cell1.Login%28%22%u6C88%u9633%u91D1%u8F6F%u79D1%u6280%u6709%u9650%u516C%u53F8%22%2C%22%22%2C%2213100104509%22%2C%220740-1662-7775-3003%22%29%3B%20%20%20%20%0D%0A%20%7D'))
</script>
	<body onload="load();">
		<form action="">
			<table align="center">
				<tr>
					<OBJECT id=Cell1
						style="LEFT:0px;WIDTH:800px;  TOP:0px;HEIGHT:500px"
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
						<INPUT id="Button3" onclick="javascript:history.back();"
							type="button" value=" 返回 ">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
