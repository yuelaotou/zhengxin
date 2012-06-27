<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="html"%>
<%@ page
	import="org.xpup.hafmis.sysloan.dataready.develop.dto.DevelopTbListDTO"%>
<%@ page import="java.util.List"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	String url = (String) request.getAttribute("url");
	String bizDate = (String) request.getAttribute("bizDate");
	String userName = (String) request.getAttribute("userName");
	List developTbListDTOList = (List) request
			.getAttribute("developTbListDTOList");
%>
<html>
	<head>
		<script src="<%=path%>/js/common.js"></script>
	</head>
	<script type="text/javascript">
	  function load(){
	  	loginReg();
	  	document.forms(0).Cell1.openfile("<%=path%>/sysloan/dataready/report/develop_print.cll","");
	  	var developerId=[];
		var developerName=[];
	    var sumFloor=[];
	    var sumFloorNum=[];
	    var office=[];
	    var artfclprsn=[];
	    var developerPaybankA=[];
	    var developerPaybankB=[];
	    var developerSt=[];
	    var startDate=[];
	    var endDate=[];
	    var contractperson=[];
	    var contractperson_tel=[];
		var userName="<%=userName%>";
		var bizDate="<%=bizDate%>";
	    var i=0
	<%
		DevelopTbListDTO dto = new DevelopTbListDTO();
	    for(int j=0;j<developTbListDTOList.size();j++){
			dto=(DevelopTbListDTO)developTbListDTOList.get(j);
			if(dto.getContactPrsnA()==null){
				dto.setContactPrsnA("");
			}
			if(dto.getContactTelA()==null){
				dto.setContactTelA("");
			}
 	%>
		 	developerId[i]="<%=dto.getDeveloperId()%>";	
	 		developerName[i]="<%=dto.getDeveloperName()%>";
	 		sumFloor[i]="<%=dto.getSumFloor()%>";
	 		sumFloorNum[i]="<%=dto.getSumFloorNum()%>";
	 		office[i]="<%=dto.getOffice()%>";
	 		artfclprsn[i]="<%=dto.getArtfclprsn()%>";
	 		developerPaybankA[i]="<%=dto.getDeveloperPaybankA()%>";
	 		developerPaybankB[i]="<%=dto.getDeveloperPaybankB()%>";
	 		developerSt[i]="<%=dto.getDeveloperSt()%>";
	 		contractperson[i]="<%=dto.getContactPrsnA()%>";
	 		contractperson_tel[i]="<%=dto.getContactTelA()%>";
	 		startDate[i]="<%=dto.getAgreementStartDate()%>";
	 		endDate[i]="<%=dto.getAgreementEndDate()%>";
 		 	
 	    	i++; 
	<%
 			}
 	%>
 		var totalLine=developerId.length;				//总的行数
		var totalPageLine=40;					//每页显示多少行
		var pageTotal=totalLine/totalPageLine;	//总共分几页
		var pageCurrent=0;						//当前页
		var completeline=0;						//记录行的
		for(k = 0 ; k < totalLine; k++){

			if(k%totalPageLine==0&&k>0){
				//document.forms(0).Cell1.DeleteRow(33,33-totalPageLine-2,pageCurrent);
				//document.forms(0).Cell1.ReDraw();
				pageCurrent++;	
				completeline=k-2;				
				document.forms(0).Cell1.insertSheetFromFile("<%=path%>/sysloan/dataready/report/develop_print.cll",0,1,pageCurrent);
				document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页")	
			}
			if(pageCurrent==0){
				document.forms(0).Cell1.S(1,k+4,pageCurrent,developerId[k]);
				document.forms(0).Cell1.S(2,k+4,pageCurrent,developerName[k]);
				document.forms(0).Cell1.S(3,k+4,pageCurrent,sumFloor[k]);
				document.forms(0).Cell1.S(4,k+4,pageCurrent,sumFloorNum[k]);
				document.forms(0).Cell1.S(5,k+4,pageCurrent,office[k]);
				document.forms(0).Cell1.S(6,k+4,pageCurrent,artfclprsn[k]);
				document.forms(0).Cell1.S(7,k+4,pageCurrent,developerPaybankA[k]);
				document.forms(0).Cell1.S(8,k+4,pageCurrent,developerPaybankB[k]);
				document.forms(0).Cell1.S(9,k+4,pageCurrent,developerSt[k]);
				document.forms(0).Cell1.S(10,k+4,pageCurrent,contractperson[k]);
				document.forms(0).Cell1.S(11,k+4,pageCurrent,contractperson_tel[k]);
				document.forms(0).Cell1.S(12,k+4,pageCurrent,startDate[k]);
				document.forms(0).Cell1.S(13,k+4,pageCurrent,endDate[k]);
				document.forms(0).Cell1.S(8,2,pageCurrent,userName);
				document.forms(0).Cell1.S(12,2,pageCurrent,bizDate);
			}else{//向第一页后的所有页插数据
				document.forms(0).Cell1.S(1,k-completeline+2,pageCurrent,developerId[k]);
				document.forms(0).Cell1.S(2,k-completeline+2,pageCurrent,developerName[k]);
				document.forms(0).Cell1.S(3,k-completeline+2,pageCurrent,sumFloor[k]);
				document.forms(0).Cell1.S(4,k-completeline+2,pageCurrent,sumFloorNum[k]);
				document.forms(0).Cell1.S(5,k-completeline+2,pageCurrent,office[k]);
				document.forms(0).Cell1.S(6,k-completeline+2,pageCurrent,artfclprsn[k]);
				document.forms(0).Cell1.S(7,k-completeline+2,pageCurrent,developerPaybankA[k]);
				document.forms(0).Cell1.S(8,k-completeline+2,pageCurrent,developerPaybankB[k]);
				document.forms(0).Cell1.S(9,k-completeline+2,pageCurrent,developerSt[k]);
				document.forms(0).Cell1.S(10,k-completeline+2,pageCurrent,contractperson[k]);
				document.forms(0).Cell1.S(11,k-completeline+2,pageCurrent,contractperson_tel[k]);
				document.forms(0).Cell1.S(12,k-completeline+2,pageCurrent,startDate[k]);
				document.forms(0).Cell1.S(13,k-completeline+2,pageCurrent,endDate[k]);
				document.forms(0).Cell1.S(8,2,pageCurrent,userName);
				document.forms(0).Cell1.S(12,2,pageCurrent,bizDate);
			}	
			
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
	<body onload="load();" onContextmenu="return false">
		<form action="">
			<table align="center">
				<tr>
					<OBJECT id=Cell1
						style="LEFT:0px;WIDTH:900px;  TOP:0px;HEIGHT:500px"
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
						<INPUT id="Button1" onclick="printsheet();" type="button"
							value=" 打印 " name="Button1">
						<INPUT id="Button1" onclick="Button1_onclick()" type="button"
							value="另存为Excel" name="Button1">
						<INPUT id="Button1" onclick="Button2_onclick()" type="button"
							value="另存为pdf" name="Button1">
						<INPUT id="Button3" style="WIDTH: 90px"
							onclick="Button3_onclick()" type="button" value="页面设置">
						<INPUT id="Button3" style="WIDTH: 90px"
							onclick="Button4_onclick()" type="button" value="查找对话框">
						<INPUT id="Button3" style="WIDTH: 90px"
							onclick="Button5_onclick()" type="button" value="excel导入">
						<INPUT id="Button3" onclick="location.href='<%=url%>'"
							type="button" value=" 返回 ">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
