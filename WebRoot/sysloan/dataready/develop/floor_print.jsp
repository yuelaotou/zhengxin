<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/taglib/struts-html.tld" prefix="logic"%>
<%@ page
	import="org.xpup.hafmis.sysloan.dataready.develop.dto.FloorListDTO"%>
<%@ page
	import="org.xpup.hafmis.sysloan.dataready.develop.dto.FloorDevelopInfoDTO"%>
<%@ page import="java.util.List"%>
<%@ include file="/checkUrl.jsp"%>
<%
	String path = request.getContextPath();
	String url = (String) request.getAttribute("url");
	FloorDevelopInfoDTO floorDevelopInfoDTO = new FloorDevelopInfoDTO();
	floorDevelopInfoDTO = (FloorDevelopInfoDTO) request.getSession()
			.getAttribute("floorDevelopInfo");
	List floorListDTOList = (List) request
			.getAttribute("floorListDTOList");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<script src="<%=path%>/js/common.js"></script>
	</head>
	<script type="text/javascript">
	  function load(){
	  	loginReg();
	  	document.forms(0).Cell1.openfile("<%=path%>/sysloan/dataready/report/floorinfo.cll","");
	  	// 共有信息
	    var developerName;
	  	var developerCode;
	  	var code;
	  	var agrmtStDate;
		var agrmtEndDate;
		var payBank;
		var payBankAcc;
		var payBankB;
		var payBankAccB;
	    var developerAddr;
	    var artfclprsn;
	    var regFund;
	    // 楼盘信息
	    var floorName=[];
		var floorNum=[];
	    var region=[];
	    var itemTotleAmnt=[];
	    var itemFinishDegree=[];
	    var housePrice=[];
	    var buildingArea=[];
		var fundStatus=[];
	    var floorSt=[];
	    var floorFlag=[];
	    var i=0;
	    
     	developerName="<%=floorDevelopInfoDTO.getDeveloperName()%>";
     	developerCode="<%=floorDevelopInfoDTO.getDeveloperId()%>";
     	code="<%=floorDevelopInfoDTO.getCode()%>";
     	office="<%=floorDevelopInfoDTO.getOffice()%>";
     	agrmtStDate="<%=floorDevelopInfoDTO.getAgreementStartDate()%>";
     	agrmtEndDate="<%=floorDevelopInfoDTO.getAgreementEndDate()%>";
     	payBank="<%=floorDevelopInfoDTO.getDeveloperPaybankA()%>";
     	payBankAcc="<%=floorDevelopInfoDTO.getDeveloperPaybankAAcc()%>";
     	payBankB="<%=floorDevelopInfoDTO.getDeveloperPaybankB()%>";
     	payBankAccB="<%=floorDevelopInfoDTO.getDeveloperPaybankBAcc()%>";
     	developerAddr="<%=floorDevelopInfoDTO.getDeveloperAddr()%>";
     	artfclprsn="<%=floorDevelopInfoDTO.getArtfclprsn()%>";
     	regFund="<%=floorDevelopInfoDTO.getRegisterFundNumber()%>";
     	
     <%
    	FloorListDTO dto = new FloorListDTO();
	    for(int j=0;j<floorListDTOList.size();j++){
			dto=(FloorListDTO)floorListDTOList.get(j);
 	%>
		 	floorName[i]="<%=dto.getFloorName()%>";	
	 		floorNum[i]="<%=dto.getFloorNum()%>";
	 		region[i]="<%=dto.getRegion()%>";
	 		itemTotleAmnt[i]="<%=dto.getItemTotleAmnt()%>";
	 		itemFinishDegree[i]="<%=dto.getItemFinishDegree()%>";
	 		housePrice[i]="<%=dto.getHousePrice()%>";
	 		buildingArea[i]="<%=dto.getBuildingArea()%>";
	 		fundStatus[i]="<%=dto.getFundStatus()%>";
	 		floorSt[i]="<%=dto.getFloorSt()%>";
	 		floorFlag[i]="<%=dto.getFloorFlag()%>";
 	    	i++; 
	<%
 			}
 	%>
 	 	var totalLine=floorName.length;				//总的行数
		var totalPageLine=30;					//每页显示多少行
		var pageTotal=totalLine/totalPageLine;	//总共分几页
		var pageCurrent=0;						//当前页
		var completeline=0;						//记录行的
		for(k = 0, currentLine=0; k < totalLine; k++,currentLine++){

			if(k%totalPageLine==0&&k>0){
				//document.forms(0).Cell1.DeleteRow(33,33-totalPageLine-2,pageCurrent);
				//document.forms(0).Cell1.ReDraw();
				pageCurrent++;	
				completeline=k-2;				
				document.forms(0).Cell1.insertSheetFromFile("<%=path%>/sysloan/dataready/report/floorinfo.cll",0,1,pageCurrent);
				document.forms(0).Cell1.setSheetLabel(pageCurrent,"第"+(pageCurrent+1)+"页")	
			}
			if(pageCurrent==0){
				document.forms(0).Cell1.S(3,3,pageCurrent,developerCode);
				document.forms(0).Cell1.S(6,3,pageCurrent,developerName);
				document.forms(0).Cell1.S(3,4,pageCurrent,code);
				document.forms(0).Cell1.S(6,4,pageCurrent,office);
				document.forms(0).Cell1.S(3,5,pageCurrent,agrmtStDate);
				document.forms(0).Cell1.S(6,5,pageCurrent,agrmtEndDate);
				document.forms(0).Cell1.S(3,6,pageCurrent,payBank);
				document.forms(0).Cell1.S(6,6,pageCurrent,payBankAcc);
				document.forms(0).Cell1.S(3,7,pageCurrent,payBankB);
				document.forms(0).Cell1.S(6,7,pageCurrent,payBankAccB);
				document.forms(0).Cell1.S(3,8,pageCurrent,developerAddr);
				document.forms(0).Cell1.S(6,8,pageCurrent,artfclprsn);
				document.forms(0).Cell1.S(3,9,pageCurrent,regFund);
				if(floorFlag[k]==1){
					document.forms(0).Cell1.S(1,currentLine+11,pageCurrent,"准许销售楼盘");
					document.forms(0).Cell1.MergeCells(2, currentLine+11,8,currentLine+11);
					document.forms(0).Cell1.S(2,currentLine+11,pageCurrent,floorName[k]);
					currentLine++;
					//document.forms(0).Cell1.DrawGridLine(1, totalLine-completeline+3,4,totalLine-completeline+3,0, 0, 0);
					//document.form1.Cell1.MergeCells(2, totalLine-completeline+3, 8, totalLine-completeline+3);
					document.forms(0).Cell1.S(1,currentLine+11,pageCurrent,"楼盘号");
					document.forms(0).Cell1.S(2,currentLine+11,pageCurrent,"所属地区");
					document.forms(0).Cell1.S(3,currentLine+11,pageCurrent,"项目投资总额");
					document.forms(0).Cell1.S(4,currentLine+11,pageCurrent,"项目完成工程度(%)");
					document.forms(0).Cell1.S(5,currentLine+11,pageCurrent,"住宅价格(元/㎡)");
					document.forms(0).Cell1.S(6,currentLine+11,pageCurrent,"建筑面积(㎡)");
					document.forms(0).Cell1.S(7,currentLine+11,pageCurrent,"是否拨款");
					document.forms(0).Cell1.S(8,currentLine+11,pageCurrent,"状态");
				}else{				
					document.forms(0).Cell1.S(1,currentLine+11,pageCurrent,floorName[k]);
					document.forms(0).Cell1.S(2,currentLine+11,pageCurrent,region[k]);
					document.forms(0).Cell1.S(3,currentLine+11,pageCurrent,itemTotleAmnt[k]);
					document.forms(0).Cell1.S(4,currentLine+11,pageCurrent,itemFinishDegree[k]);
					document.forms(0).Cell1.S(5,currentLine+11,pageCurrent,housePrice[k]);
					document.forms(0).Cell1.S(6,currentLine+11,pageCurrent,buildingArea[k]);
					document.forms(0).Cell1.S(7,currentLine+11,pageCurrent,fundStatus[k]);
					document.forms(0).Cell1.S(8,currentLine+11,pageCurrent,floorSt[k]);
				}
			}else{//向第一页后的所有页插数据
				
				document.forms(0).Cell1.S(3,3,pageCurrent,developerCode);
				document.forms(0).Cell1.S(6,3,pageCurrent,developerName);
				document.forms(0).Cell1.S(3,4,pageCurrent,code);
				document.forms(0).Cell1.S(6,4,pageCurrent,office);
				document.forms(0).Cell1.S(3,5,pageCurrent,agrmtStDate);
				document.forms(0).Cell1.S(6,5,pageCurrent,agrmtEndDate);
				document.forms(0).Cell1.S(3,6,pageCurrent,payBank);
				document.forms(0).Cell1.S(6,6,pageCurrent,payBankAcc);
				document.forms(0).Cell1.S(3,7,pageCurrent,payBankB);
				document.forms(0).Cell1.S(6,7,pageCurrent,payBankAccB);
				document.forms(0).Cell1.S(3,8,pageCurrent,developerAddr);
				document.forms(0).Cell1.S(6,8,pageCurrent,artfclprsn);
				document.forms(0).Cell1.S(3,9,pageCurrent,regFund);
				if(floorFlag[k]==1){
					document.forms(0).Cell1.S(1,currentLine-completeline+9,pageCurrent,"准许销售楼盘");
					document.forms(0).Cell1.MergeCells(2, currentLine-completeline+9,8,currentLine-completeline+9);
					document.forms(0).Cell1.S(2,currentLine-completeline+9,pageCurrent,floorName[k]);
					currentLine++;
					//document.forms(0).Cell1.DrawGridLine(1, totalLine-completeline+3,4,totalLine-completeline+3,0, 0, 0);
					//document.form1.Cell1.MergeCells(2, totalLine-completeline+3, 8, totalLine-completeline+3);
					document.forms(0).Cell1.S(1,currentLine-completeline+9,pageCurrent,"楼盘号");
					document.forms(0).Cell1.S(2,currentLine-completeline+9,pageCurrent,"所属地区");
					document.forms(0).Cell1.S(3,currentLine-completeline+9,pageCurrent,"项目投资总额");
					document.forms(0).Cell1.S(4,currentLine-completeline+9,pageCurrent,"项目完成工程度(%)");
					document.forms(0).Cell1.S(5,currentLine-completeline+9,pageCurrent,"住宅价格(元/㎡)");
					document.forms(0).Cell1.S(6,currentLine-completeline+9,pageCurrent,"建筑面积(㎡)");
					document.forms(0).Cell1.S(7,currentLine-completeline+9,pageCurrent,"是否拨款");
					document.forms(0).Cell1.S(8,currentLine-completeline+9,pageCurrent,"状态");
				}else{				
					document.forms(0).Cell1.S(1,currentLine-completeline+9,pageCurrent,floorName[k]);
					document.forms(0).Cell1.S(2,currentLine-completeline+9,pageCurrent,region[k]);
					document.forms(0).Cell1.S(3,currentLine-completeline+9,pageCurrent,itemTotleAmnt[k]);
					document.forms(0).Cell1.S(4,currentLine-completeline+9,pageCurrent,itemFinishDegree[k]);
					document.forms(0).Cell1.S(5,currentLine-completeline+9,pageCurrent,housePrice[k]);
					document.forms(0).Cell1.S(6,currentLine-completeline+9,pageCurrent,buildingArea[k]);
					document.forms(0).Cell1.S(7,currentLine-completeline+9,pageCurrent,fundStatus[k]);
					document.forms(0).Cell1.S(8,currentLine-completeline+9,pageCurrent,floorSt[k]);
				}
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
					<OBJECT id="Cell1"
						style="LEFT:0px;WIDTH:850px;  TOP:0px;HEIGHT:500px"
						codeBase="http://192.168.1.44:8088/hafmis/CellWeb5.CAB#version=5,3,7,321"
						classid="clsid:3F166327-8030-4881-8BD2-EA25350E574A" VIEWASTEXT>
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
