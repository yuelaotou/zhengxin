<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="org.xpup.hafmis.syscollection.chgbiz.chgperson.dto.ChgPersonCellListListExportDTO"%>
<%@ page import="java.util.List" %>
<%
String path = request.getContextPath();
 
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>凭证维护打印</title>
<script language="javascript" src="<%=path%>/js/common.js"></script>
  </head>
  <script type="text/javascript">
  	function load(){
  		loginReg();
	  	document.forms(0).Cell1.openfile("<%=path%>/syscollection/chgbiz/chgperson/report/chgpersonList.cll","");
 
	  	var chgType=[];// 变更类型
		var empId=[];// 职工编号
	    var name=[];// 职工姓名
	    var cardNum=[];// 证件号码
	    var temp_oldPayStatus=[];// 变更后职工状态	    
	    var salaryBase=[];// 工资基数
	    var orgPay=[];// 单位缴额
	    var empPay=[];// 职工缴额
	    var sumPay=[];// 缴额合计
	    var temp_chgreason=[];// 变更原因
	  	var orgId=[];// 单位编号
	  	var orgName=[];//单位名称
	  	var chgMonth=[];// 变更年月
		var userName=[];
	  	var bizDate=[];
	  	var collectionBankName=[];
	  	var i=0
     	
     <%
    	List celllist = (List) request.getAttribute("celllist");
    	String userName = (String) request.getAttribute("userName");
    	String bizDate = (String) request.getAttribute("bizDate"); 	
    	String collectionBankName = (String) request.getAttribute("collectionBankName");
	    for(int j=0;j<celllist.size();j++){
			ChgPersonCellListListExportDTO dto=(ChgPersonCellListListExportDTO)celllist.get(j);

 	%>
 	        chgType[i]="<%=dto.getChgType()%>";	
		 	empId[i]="<%=dto.getEmpId()%>";	 	
		 	name[i]="<%=dto.getName()%>";	
		 	cardNum[i]="<%=dto.getCardNum()%>";	
		 	temp_oldPayStatus[i]="<%=dto.getTemp_oldPayStatus()%>";	
		 	salaryBase[i]="<%=dto.getSalaryBase()%>";	
		 	orgPay[i]="<%=dto.getOrgPay()%>";	
		 	empPay[i]="<%=dto.getEmpPay()%>";	
		 	sumPay[i]="<%=dto.getSumPay()%>";	
		 	temp_chgreason[i]="<%=dto.getTemp_chgreason()%>";	
		 	orgId[i]="<%=dto.getOrgId()%>";	
		 	orgName[i]="<%=dto.getOrgName()%>";	
		 	chgMonth[i]="<%=dto.getChgMonth()%>";		 	
		 	userName[i]="<%=userName%>";	
		 	bizDate[i]="<%=bizDate%>";	
		 	collectionBankName[i]="<%=collectionBankName%>";	
 	    	i++; 
	<%
 			}
 	%>
 		var totalLine=empId.length;				//总的行数
		var totalPageLine=14;					//每页显示多少行
		var pageTotal=totalLine/totalPageLine;	//总共分几页
		var pageCurrent=0;						//当前页
		var completeline=0;						//记录行的
		
		var sumDebit=0;
		var sumCredit=0;
		
		for(k = 0 ; k < totalLine; k++){

			if(k%totalPageLine==0&&k>0){
			
									
                    document.forms(0).Cell1.DeleteRow(totalLine+7,totalPageLine-(totalLine-1),pageCurrent);
					document.forms(0).Cell1.ReDraw();
				document.forms(0).Cell1.ReDraw();
				pageCurrent++;	
				completeline=k-2;				
				document.forms(0).Cell1.insertSheetFromFile("<%=path%>/syscollection/chgbiz/chgperson/report/chgpersonList_1.cll",0,1,pageCurrent);
				document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页")	
			}
			if(pageCurrent==0){
			
				document.forms(0).Cell1.S(1,2,pageCurrent,"归集银行");
					document.forms(0).Cell1.S(2,2,pageCurrent,"<%=collectionBankName%>");
					document.forms(0).Cell1.S(9,2,pageCurrent,"操作员");
					document.forms(0).Cell1.S(10,2,pageCurrent,"<%=userName%>");
					document.forms(0).Cell1.S(5,2,pageCurrent,"操作日期");
					document.forms(0).Cell1.S(6,2,pageCurrent,"<%=bizDate%>");
				document.forms(0).Cell1.S(6,3,pageCurrent,"0"+orgId[k]);
				document.forms(0).Cell1.S(2,3,pageCurrent,orgName[k]);
				document.forms(0).Cell1.S(9,3,pageCurrent,chgMonth[k]);
				
			    document.forms(0).Cell1.S(1,k+6,pageCurrent,chgType[k]);
				document.forms(0).Cell1.S(2,k+6,pageCurrent,empId[k]);
				document.forms(0).Cell1.S(3,k+6,pageCurrent,name[k]);
				document.forms(0).Cell1.S(4,k+6,pageCurrent,cardNum[k]);
				document.forms(0).Cell1.S(5,k+6,pageCurrent,temp_oldPayStatus[k]);
				document.forms(0).Cell1.S(6,k+6,pageCurrent,salaryBase[k]);
				document.forms(0).Cell1.S(7,k+6,pageCurrent,orgPay[k]);
				document.forms(0).Cell1.S(8,k+6,pageCurrent,empPay[k]);
				document.forms(0).Cell1.S(9,k+6,pageCurrent,sumPay[k]);
				document.forms(0).Cell1.S(10,k+6,pageCurrent,temp_chgreason[k]); 
		 

			}else{//向第一页后的所有页插数据
				document.forms(0).Cell1.S(1,2,pageCurrent,"归集银行");
					document.forms(0).Cell1.S(2,2,pageCurrent,"<%=collectionBankName%>");
					document.forms(0).Cell1.S(9,2,pageCurrent,"操作员");
					document.forms(0).Cell1.S(10,2,pageCurrent,"<%=userName%>");
					document.forms(0).Cell1.S(5,2,pageCurrent,"操作日期");
					document.forms(0).Cell1.S(6,2,pageCurrent,"<%=bizDate%>");
				document.forms(0).Cell1.S(9,3,pageCurrent,"0"+orgId[k]);
				document.forms(0).Cell1.S(2,3,pageCurrent,orgName[k]);
				document.forms(0).Cell1.S(6,3,pageCurrent,chgMonth[k]);
				
				document.forms(0).Cell1.S(1,k-completeline+4,pageCurrent,chgType[k]);
				document.forms(0).Cell1.S(2,k-completeline+4,pageCurrent,empId[k]);
				document.forms(0).Cell1.S(3,k-completeline+4,pageCurrent,name[k]);
				document.forms(0).Cell1.S(4,k-completeline+4,pageCurrent,cardNum[k]);
				document.forms(0).Cell1.S(5,k-completeline+4,pageCurrent,temp_oldPayStatus[k]);
				document.forms(0).Cell1.S(6,k-completeline+4,pageCurrent,salaryBase[k]);
				document.forms(0).Cell1.S(7,k-completeline+4,pageCurrent,orgPay[k]);
				document.forms(0).Cell1.S(8,k-completeline+4,pageCurrent,empPay[k]);
				document.forms(0).Cell1.S(9,k-completeline+4,pageCurrent,sumPay[k]);
				document.forms(0).Cell1.S(10,k-completeline+4,pageCurrent,temp_chgreason[k]); 
		 
				
			}	
		}
		if(completeline==0)//查询出来的记录可以在一页显示的时候会进入
				{			
					
					document.forms(0).Cell1.ReDraw();
				}
				else
				{
 
					
						
					
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
  <body onload = "load();" onContextmenu="return false">
    <form action="">
    	<table align="center">
			<tr><OBJECT id=Cell1 style= "LEFT:0px;WIDTH:700px;  TOP:0px;HEIGHT:400px" codeBase="http://192.168.1.44:8088/hafmis/CellWeb5.CAB#version=5,3,7,321" classid=clsid:3F166327-8030-4881-8BD2-EA25350E574A VIEWASTEXT><PARAM NAME="_Version" VALUE="65536"><PARAM NAME="_ExtentX" VALUE="10266"><PARAM NAME="_ExtentY" VALUE="7011"><PARAM NAME="_StockProps" VALUE= "0"></OBJECT></tr> 
			<tr>
			<td><input type="button" name="print" value = "打印预览" onclick = "printPreview();"/></td>
			<td><INPUT id="Button1" onclick="printsheet()" type="button" value=" 打印 " name="Button1"></td>
			<td><INPUT id="Button1" onclick="Button1_onclick()" type="button" value="另存为Excel" name="Button1"></td>
			<td><INPUT id="Button1" onclick="Button2_onclick()" type="button" value="另存为pdf" name="Button1"></td>
			<td><INPUT id="Button3" style="WIDTH: 90px" onclick="Button3_onclick()" type="button" value="页面设置"></td>
			<td><INPUT id="Button3" style="WIDTH: 90px" onclick="Button4_onclick()" type="button" value="查找对话框"></td>
			<td><INPUT id="Button3" style="WIDTH: 90px" onclick="Button5_onclick()" type="button" value="excel导入"></td>
			<td><INPUT id="Button3" onclick="javascript:history.back();" type="button" value=" 返回 ">	</td>
		</table>
	</form>
  </body>
</html>
