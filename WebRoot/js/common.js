var normal = "#FFFFFF";
var selected = "#EEFBFF";
var overed = "#EAEAEA";
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
function onRowMouseOver(tr) {
	tr.style.background = overed;
}

function onRowMouseOut(tr, rb) {
	if(rb.checked === undefined || rb.checked === false) {
		tr.style.background = normal;
	}
	else {
		tr.style.background = selected;
	}
}  
function onRowClick(tr, rb) { 
	var trs = document.all("tr_n");
	for(i = 0;i < trs.length; i++) {
		trs[i].style.background = normal;
	}
	rb.checked = true;
	tr.style.background = selected;
}

function enterToTab(){
  if(event.srcElement.type != 'button' && event.keyCode == 13){
    event.keyCode = 9;
  }
}
//ajax
var xmlHttp;

function createXMLHttpRequest() {
    if (window.ActiveXObject) {
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    } 
    else if (window.XMLHttpRequest) {
        xmlHttp = new XMLHttpRequest();
    }
} 

function findInfo(queryString) {
 createXMLHttpRequest();  
	    xmlHttp.onreadystatechange = handleStateChange;
	    xmlHttp.open("GET", queryString, false);
	    xmlHttp.send(null);   
}

function handleStateChange() {
  if(xmlHttp.readyState == 4) {
      if(xmlHttp.status == 200) {
         var x=xmlHttp.responseText;
         eval(x);
      }
   }
}
//联想输入法
function findThinkInfo(key,queryString,searchShowDiv) {
 createXMLHttpRequest();  
	    xmlHttp.onreadystatechange =function (){handleSearchSuggest(key,searchShowDiv)};
	    xmlHttp.open("GET", queryString, true);
	    xmlHttp.send(null);   
}
//联想输入法
function handleSearchSuggest(key,searchShowDiv) {
	if (xmlHttp.readyState == 4) {
		var ss = document.getElementById(searchShowDiv);
		ss.innerHTML = '';
		var str = xmlHttp.responseText.split("\n");
		if(str.length - 1>0){
		if(str.length - 1==1 && document.getElementById(key).value==str[0]){
		document.getElementById(searchShowDiv).innerHTML = '';
		eval("document.all." + searchShowDiv).style.display='none';
			}
			else{
			eval("document.all." + searchShowDiv).style.display='';
		for(i=0; i < str.length - 1; i++) {
			var suggest = '<div id='+i+' onmouseover="javascript:suggestOver(\''+i+'\');" ';
			suggest += 'onmouseout="javascript:suggestOut(\''+i+'\');" ';
			suggest += 'onclick="javascript:setSearch(key,this.innerHTML,searchShowDiv);" ';
			suggest += 'class="suggest_link">' + str[i] + '</div>';
			ss.innerHTML += suggest;
			//alert(ss.innerHTML);
			}
			}	
	   }
	   else{eval("document.all." + searchShowDiv).style.display='none';}
	}
}

//联想输入法
function suggestOver(div_value) {
	document.getElementById(div_value).className = 'suggest_link_over';
}
//联想输入法
function suggestOut(div_value) {
	document.getElementById(div_value).className = 'suggest_link';
}
//联想输入法
function setSearch(key,value,searchShowDiv) {
	document.getElementById(key).value = value;
	document.getElementById(searchShowDiv).innerHTML = '';
	eval("document.all." + searchShowDiv).style.display='none';
	document.getElementById(key).focus();
}

//单选记录颜色改变
var oldColor="#ffffff";                            //原来的颜色
var newColor="#E8FCFD";                     //要变成的颜色
function chgBGColor(oTD){
	oldColor=oTD.style.backgroundColor;//保存当前颜色
	oTD.style.backgroundColor=newColor;//改变表格颜色
	newColor=oldColor;                 //改变下次要变成的颜色
}

//当输入内容后鼠标点击下一行时，自动显示上一行同一个字段的内容
function copyPreRow(textNow,textPre){
var textNowContent=document.getElementById(textNow).value;
if(textNowContent==""){
document.getElementById(textNow).value=document.getElementById(textPre).value;
}
}
//会车换行
function goEnter(){
if(event.keyCode==13){
		event.keyCode=9;
		findEmployeeInfo();
	}
}


String.prototype.trim = function() 
{
	return this.replace(/(^\s*)|(\s*$)/g, "");
}
  


//高级查询
function gotoGaJi(){
	if(gjtr.style.display=="none"){
		gjtr.style.display="";
	//	document.forms[0].elements["textfield4"].focus();
	}else{
		gjtr.style.display="none";
	}
}



//回车移动光标Tab
function enterNextFocus1(){
	if(event.keyCode==13){
		event.keyCode=9;
	}
}


//单击行,选中单选按钮
/*
function gotoClick(id){
	var temp;
	eval("temp=document.forms[0]."+id);
	temp.checked="true";
}



*/
//双击选中单选按钮并关闭窗口
function gotoCheck1(id){
		var temp;
		eval("temp=document.forms[0]."+id);
		temp.checked="true";
		window.close();
}

function returnValues(){
	var b=document.getElementsByName("id");
	for(i=0;i<b.length;i++)   
    {
    	if(b[i].checked==true){
    		window.returnValue=b[i].value;
    	}
  	}
  	window.close();
}


//确定时调用
function qdValues(indexs){

	var a;
	var b=document.getElementsByName("id");
	for(i=0;i<b.length;i++)   
    {
    	if(b[i].checked==true){
    		a=b[i].value;
    	}
  	}
	if(""+a!="undefined"){
		//window.opener.document.all.code.value=format(a)+a;
		//window.opener.document.forms[0].elements["orgPaymentAgreement.organization.id"].value=format(a)+a;
		var obj=window.opener.document.getElementsByTagName("input");
      	for(i=0;i<obj.length;i++){
      	  if(obj[i+eval(indexs)].type=="text"){
            obj[i+eval(indexs)].value=format(a)+a;
         //   opener.executeAjax();
            window.close();
           	break;
          }
  		}
  		if(indexs=="0"){
  			opener.executeAjax();
  		}else{
  			opener.executeAjaxIn();
  		}
		
	}else{
		opener.clears();
	}
	window.close();
}

//个人贷款确定时调用
function loanqdValues(indexs){

	var a;
	var b=document.getElementsByName("id");
	for(i=0;i<b.length;i++)   
    {
    	if(b[i].checked==true){
    		a=b[i].value;
    	}
  	}
	if(""+a!="undefined"){
		//window.opener.document.all.code.value=format(a)+a;
		//window.opener.document.forms[0].elements["orgPaymentAgreement.organization.id"].value=format(a)+a;
		var obj=window.opener.document.getElementsByTagName("input");
      	for(i=0;i<obj.length;i++){
      	  if(obj[i+eval(indexs)].type=="text"){
            obj[i+eval(indexs)].value=a;
           // opener.executeAjax();
            window.close();
        	break;
          }
  		}
  		if(indexs=="0"){
  			opener.executeAjax();
  		}else{
  			opener.executeAjaxIn();
  		}
		
	}else{
		opener.clears();
	}
	window.close();
}
//凭证录入摘要列表弹出框确定
function loanqdSummayValues(summayi){

	var a;
	var b=document.getElementsByName("id");
	var summayid;
  	var str="summay"+summayi;
  	var str1="freeSummay"+summayi;
  	eval("summayid=window.opener.document.all."+str);
  	eval("freeSummayid=window.opener.document.all."+str1);
	for(i=0;i<b.length;i++)   
    {
    	if(b[i].checked==true){
    		a=b[i].value;
    	}
  	}
	if(""+a!="undefined"){
		summayid.value=a;
		freeSummayid.focus();
		window.close();
      	
	}else{
		opener.clears();
	}
	window.close();
}
//担保公司弹出框确定时调用
//*此方法已改写到担保公司弹出框页面*
//function loanassistantorgpopqdValues(indexs){
//
	//var a;
	//var b=document.getElementsByName("id");
	//for(i=0;i<b.length;i++)   
    //{
    	//if(b[i].checked==true){
    		//a=b[i].value;
    	//}
  	//}
	//if(""+a!="undefined"){
		//window.opener.document.all.code.value=format(a)+a;
		//window.opener.document.forms[0].elements["orgPaymentAgreement.organization.id"].value=format(a)+a;
		//var obj=window.opener.document.getElementsByTagName("input");
      	//for(i=0;i<obj.length;i++){
      	  //if(obj[i+eval(indexs)].type=="text"){
            //obj[i+eval(indexs)].value=a;
            //window.close();
        	//break;
          //}
  		//}
  	//	
	//	
	//}else{
		//opener.clears();
	//}
	//window.close();
//}

//财务-会计科目弹出
function fnqdValues(indexs,radioid){
	var a;
	var b=document.getElementsByName("radioname");
	for(i=0;i<b.length;i++)   
    {
    	if(b[i].checked==true){
    		a=b[i].value;
    	}
  	}
	if(""+a!="undefined"){
		//window.opener.document.all.code.value=format(a)+a;
		//window.opener.document.forms[0].elements["orgPaymentAgreement.organization.id"].value=format(a)+a;
		var obj=window.opener.document.getElementsByTagName("input");
      	for(i=0;i<obj.length;i++){
      	  if(obj[i+eval(indexs)].type=="text"){
            obj[i+eval(indexs)].value=a;
            //obj[i+eval(indexs)].focus();
           // opener.executeAjax();
            window.close();
        	break;
          }
  		}
  		if(indexs=="0"){
  			opener.executeAjax();
  		}else{
  			opener.executeAjaxIn(indexs);
  		}
		
	}else{
		opener.clears();
	}
	window.close();
}

function dbfnValues(indexs,radioid){
	var a=radioid;
	if(""+a!="undefined"){
		//window.opener.document.all.code.value=format(a)+a;
		//window.opener.document.forms[0].elements["orgPaymentAgreement.organization.id"].value=format(a)+a;
		var obj=window.opener.document.getElementsByTagName("input");
      	for(i=0;i<obj.length;i++){
      	  if(obj[i+eval(indexs)].type=="text"){
            obj[i+eval(indexs)].value=a;
            //obj[i+eval(indexs)].focus();
           // opener.executeAjax();
            window.close();
        	break;
          }
  		}
  		if(indexs=="0"){
  			opener.executeAjax();
  		}else{
  			opener.executeAjaxIn(indexs);
  		}
		
	}else{
		opener.clears();
	}
	window.close();
}

//取消时调用
function qdClears(){
	clears();
	window.close();
}

function clears(){
	var obj=window.opener.document.getElementsByTagName("input");   
  	for(i=0;i<obj.length;i++){
      	if(obj[i].type=="text"){   
            obj[i].value="";
        }   
  	}
}

function clearsZuoFie(){
	var obj=document.getElementsByTagName("input");   
  	for(i=0;i<obj.length;i++){
      	if(obj[i].type=="text"){   
            obj[i].value="";
        }   
  	}
}

var old_temp="tr0";
function gotoClick(id1,id2,form){
	var temp1;
	var temp2;
	var temp3;
	eval("temp1="+id1);
	eval("temp3="+old_temp);
	eval("temp2=form."+id2);
	temp2.checked="true";
	temp3.style.background="#ffffff";
	temp1.style.background="#EEFBFF";
	old_temp=id1;
}
function gotoColor(id1,id2,form){
	var temp1;
	var temp2;
	eval("temp1="+id1);
	eval("temp2=form."+id2);
	if(temp2.checked){
		temp1.style.background="#EEFBFF";
	}else{
		temp1.style.background="#ffffff";
	}
}



//获得列表ID
function getCheckId(){
var checkid;
var i=0;
if (document.getElementsByName("id").length!=1){
for(i;i<document.getElementsByName("id").length;i++){  
if(document.all.id[i].checked){
checkid=document.all.id[i].value;
}
}
}else{
checkid=document.all.id.value;
}
return checkid;
}


//身份证验证
function isIdCardNo(num)
      {
        if (!isNumberX(num)) {alert("身份证输入错误！"); return false;}
        var len = num.length, re;
        if (len == 15)
          re = new RegExp(/^(\d{6})()?(\d{2})(\d{2})(\d{2})(\d{3})$/);
        else if (len == 18)
          re = new RegExp(/^(\d{6})()?(\d{4})(\d{2})(\d{2})(\d{3})(\d)$/);
        else {alert("输入身份证位数不正确！"); return false;}
        var a = num.match(re);
        if (a != null)
        {
          if (len == 15){
            var D = new Date("19"+a[3]+"/"+a[4]+"/"+a[5]);
            var B = D.getYear()==a[3]&&(D.getMonth()+1)==a[4]&&D.getDate()==a[5];
          }
          else
          {
            var D = new Date(a[3]+"/"+a[4]+"/"+a[5]);
            var B = D.getFullYear()==a[3]&&(D.getMonth()+1)==a[4]&&D.getDate()==a[5];
          }
          if (!B) {alert("输入的身份证号 "+ a[0] +" 里出生日期不对！"); return false;}
        }
        return true;
      }
      
      
      //数字转化为大写的金额
function ChangeToBig(valuett)
{
  tag = false;
	var intFen,i;
	var strArr,strCheck,strFen,strDW,strNum,strBig,strNow;
	if (isNaN(valuett))   //数据非法时提示，并返回空串
	{
		strErr = "数据"+valuett+"非法！"
		alert(strErr);
		return "";
	}
	strCheck = valuett+".";
	strArr = strCheck.split(".");
	strCheck = strArr[0];
	if(strCheck.length>12)   //数据大于等于一万亿时提示无法处理
	{
		strErr = "数据"+valuett+"过大，无法处理！"
		alert(strErr);
		return "";
	}
	try
	{
          if(valuett<0){
            tag = true;
            valuett = -valuett;
          }

		i = 0;
		strBig = "";
		intFen = valuett*100;          //转换为以分为单位的数值

		num = new Number(intFen);
		intFen = num.toFixed(2);

		strFen = intFen.toString();
		strArr = strFen.split(".");
		strFen = strArr[0];
		intFen = strFen.length;      //获取长度
		strArr = strFen.split("");	//将各个数值分解到数组内
		while(intFen!=0)   //分解并转换
		{
			i = i+1;
			switch(i)              //选择单位
			{
				case 1:strDW = "分";break;
				case 2:strDW = "角";break;
				case 3:strDW = "圆";break;
				case 4:strDW = "拾";break;
				case 5:strDW = "佰";break;
				case 6:strDW = "仟";break;
				case 7:strDW = "万";break;
				case 8:strDW = "拾";break;
				case 9:strDW = "佰";break;
				case 10:strDW = "仟";break;
				case 11:strDW = "亿";break;
				case 12:strDW = "拾";break;
				case 13:strDW = "佰";break;
				case 14:strDW = "仟";break;
			}
			switch (strArr[intFen-1])              //选择数字
			{

				case "1":strNum = "壹";break;
				case "2":strNum = "贰";break;
				case "3":strNum = "叁";break;
				case "4":strNum = "肆";break;
				case "5":strNum = "伍";break;
				case "6":strNum = "陆";break;
				case "7":strNum = "柒";break;
				case "8":strNum = "捌";break;
				case "9":strNum = "玖";break;
				case "0":strNum = "零";break;
			}

			//处理特殊情况
			strNow = strBig.split("");
			//分为零时的情况
			if((i==1)&&(strArr[intFen-1]=="0"))
				strBig = "整";
			//角为零时的情况
			else if((i==2)&&(strArr[intFen-1]=="0"))
			{    //角分同时为零时的情况
				if(strBig!="整")
					strBig = "零"+strBig;
			}
			//元为零的情况
			else if((i==3)&&(strArr[intFen-1]=="0"))
				strBig = "圆"+strBig;
			//拾－仟中一位为零且其前一位（元以上）不为零的情况时补零
			else if((i<7)&&(i>3)&&(strArr[intFen-1]=="0")&&(strNow[0]!="零")&&(strNow[0]!="圆"))
				strBig = "零"+strBig;
			//拾－仟中一位为零且其前一位（元以上）也为零的情况时跨过
			else if((i<7)&&(i>3)&&(strArr[intFen-1]=="0")&&(strNow[0]=="零"))
				{}
			//拾－仟中一位为零且其前一位是元且为零的情况时跨过
			else if((i<7)&&(i>3)&&(strArr[intFen-1]=="0")&&(strNow[0]=="圆"))
				{}
			//当万为零时必须补上万字
			else if((i==7)&&(strArr[intFen-1]=="0"))
				strBig ="万"+strBig;
			//拾万－仟万中一位为零且其前一位（万以上）不为零的情况时补零
			else if((i<11)&&(i>7)&&(strArr[intFen-1]=="0")&&(strNow[0]!="零")&&(strNow[0]!="万"))
				strBig = "零"+strBig;
			//拾万－仟万中一位为零且其前一位（万以上）也为零的情况时跨过
			else if((i<11)&&(i>7)&&(strArr[intFen-1]=="0")&&(strNow[0]=="万"))
				{}
			//拾万－仟万中一位为零且其前一位为万位且为零的情况时跨过
			else if((i<11)&&(i>7)&&(strArr[intFen-1]=="0")&&(strNow[0]=="零"))
				{}
			//万位为零且存在仟位和十万以上时，在万仟间补零
			else if((i<11)&&(i>8)&&(strArr[intFen-1]!="0")&&(strNow[0]=="万")&&(strNow[2]=="仟"))
				strBig = strNum+strDW+"万零"+strBig.substring(1,strBig.length);
			//单独处理亿位
			else if(i==11)
			{
				//亿位为零且万全为零存在仟位时，去掉万补为零
				if((strArr[intFen-1]=="0")&&(strNow[0]=="万")&&(strNow[2]=="仟"))
					strBig ="亿"+"零"+strBig.substring(1,strBig.length);
				//亿位为零且万全为零不存在仟位时，去掉万
				else if((strArr[intFen-1]=="0")&&(strNow[0]=="万")&&(strNow[2]!="仟"))
					strBig ="亿"+strBig.substring(1,strBig.length);
				//亿位不为零且万全为零存在仟位时，去掉万补为零
				else if((strNow[0]=="万")&&(strNow[2]=="仟"))
					strBig = strNum+strDW+"零"+strBig.substring(1,strBig.length);
				//亿位不为零且万全为零不存在仟位时，去掉万
				else if((strNow[0]=="万")&&(strNow[2]!="仟"))
					strBig = strNum+strDW+strBig.substring(1,strBig.length);
				//其他正常情况
				else
						strBig = strNum+strDW+strBig;
			}
			//拾亿－仟亿中一位为零且其前一位（亿以上）不为零的情况时补零
			else if((i<15)&&(i>11)&&(strArr[intFen-1]=="0")&&(strNow[0]!="零")&&(strNow[0]!="亿"))
				strBig = "零"+strBig;
			//拾亿－仟亿中一位为零且其前一位（亿以上）也为零的情况时跨过
			else if((i<15)&&(i>11)&&(strArr[intFen-1]=="0")&&(strNow[0]=="亿"))
				{}
			//拾亿－仟亿中一位为零且其前一位为亿位且为零的情况时跨过
			else if((i<15)&&(i>11)&&(strArr[intFen-1]=="0")&&(strNow[0]=="零"))
				{}
			//亿位为零且不存在仟万位和十亿以上时去掉上次写入的零
			else if((i<15)&&(i>11)&&(strArr[intFen-1]!="0")&&(strNow[0]=="零")&&(strNow[1]=="亿")&&(strNow[3]!="仟"))
				strBig = strNum+strDW+strBig.substring(1,strBig.length);
			//亿位为零且存在仟万位和十亿以上时，在亿仟万间补零
			else if((i<15)&&(i>11)&&(strArr[intFen-1]!="0")&&(strNow[0]=="零")&&(strNow[1]=="亿")&&(strNow[3]=="仟"))
				strBig = strNum+strDW+"亿零"+strBig.substring(2,strBig.length);
			else
				strBig = strNum+strDW+strBig;
			strFen = strFen.substring(0,intFen-1);
			intFen = strFen.length;
			strArr = strFen.split("");
		}
                if(tag){
                  strBig = "负"+strBig;
                }
		return strBig;

	}catch(err){
		return "";      //若失败则返回原值
	}

}
//去空格
String.prototype.trim = function() 
{
	return this.replace(/(^\s*)|(\s*$)/g, "");

}

    //检验利率 允许为0
function checkRate(money){
	var re =/^0(\.[0-9]{1,2})$/;
	var r = money.match(re);
	var rd=/^0(\.0{1,2})$/;
	var k=money.match(rd);
    if(money==0){
	   return true;
	}
	if (r==null || !(k==null))
	{
		return false;
	}
	    return true;
    }
  
     //检验利率 不允许为0
function checkRateOther(money){
	var re =/^0(\.[0-9]{1,2})$/;
	var r = money.match(re);
	var rd=/^0(\.0{1,2})$/;
	var k=money.match(rd);
	if (r==null || !(k==null))
	{
		return false;
	}
	    return true;
    }
     
function format(v){
	if(v=="")
	{
		return "";
	}
	var l=6-v.length;
	var f="";
	for(i=0;i<l;i++)   
    {
    	f+="0";
  	}
  	return f;
}
function formatTen(v){
	if(v=="")
	{
		return "";
	}
	var l=10-v.length;
	var f="";
	for(i=0;i<l;i++)   
    {
    	f+="0";
  	}
  	return f;
}
//弹出单位信息
function gotoOrgpop(status,path,indexs){ 

	window.open(path+"/syscollection/common/biz/orgpop/orgpopTaFindAC.do?status="+status+"&indexs="+indexs+"&qx=yes","window","height=450,width=700,top="+(window.screen.availHeight-30-450)/2+",left="+(window.screen.availWidth-10-700)/2+",scrollbars=yes, status=yes"); 

}
//弹出单位信息
function gotoOrgpopNo(status,path,indexs){ 

	window.open(path+"/syscollection/common/biz/orgpop/orgpopTaFindAC.do?status="+status+"&indexs="+indexs+"&qx=no","window","height=450,width=700,top="+(window.screen.availHeight-30-450)/2+",left="+(window.screen.availWidth-10-700)/2+",scrollbars=yes, status=yes"); 

}
//合同弹出框  (afterLoanaccord：0为银行的权限，1为操作员的权限)
function gotoContractpop(status,path,indexs,afterLoanaccord){

  window.open(path+"/sysloan/contractpopFind.do?status="+status+"&indexs="+indexs+"&afterLoanaccord="+afterLoanaccord+"&qx=no","window","height=450,width=700,top="+(window.screen.availHeight-30-450)/2+",left="+(window.screen.availWidth-10-700)/2+",scrollbars=yes, status=yes"); 
  
}
//贷款账号弹出框
function gotoLoankouaccpop(status,path,indexs){

  window.open(path+"/sysloan/loanKouAccpopFindAC.do?status="+status+"&indexs="+indexs+"&qx=no","window","height=450,width=700,top="+(window.screen.availHeight-30-450)/2+",left="+(window.screen.availWidth-10-700)/2+",scrollbars=yes, status=yes"); 
  
}
//担保公司弹出框
function gotoAssistantorgpop(path,indexs){
 
 window.open(path+"/sysloan/assistantorgpopFind.do?indexs="+indexs+"&qx=no","window","height=450,width=700,top="+(window.screen.availHeight-30-450)/2+",left="+(window.screen.availWidth-10-700)/2+",scrollbars=yes, status=yes"); 
 
}
function open_findEmp(path,orgid,st,cardnum,empname,indexs){
 //returnValues=window.showModalDialog("showEmployeesListAC.do?orgid="+orgid+"&st="+st+"","window","dialogHeight:350px;dialogWidth:850px;dialogLeft:100;dialogTop:200;help:no;status=no;scroll=yes;center:yes"); 
window.open(path+"/syscollection/common/biz/emppop/showEmployeesListAC.do?orgid="+orgid+"&st="+st+"&cardnum="+cardnum+"&empname="+empname+"&indexs="+indexs,"window","height=450,width=700,top="+(window.screen.availHeight-30-450)/2+",left="+(window.screen.availWidth-10-700)/2+",scrollbars=yes, status=yes"); 

}
//财务-会计科目弹出框
function gotoSubjectpop(status,path,indexs){

  window.open(path+"/sysfinance/subjectpopTaShowAC.do?status="+status+"&indexs="+indexs+"&qx=no","window","height=450,width=700,top="+(window.screen.availHeight-30-450)/2+",left="+(window.screen.availWidth-10-700)/2+",scrollbars=yes, status=yes"); 
  
}
//摘要弹出框
function gotoSummaypop(path,summayi){

  window.open(path+"/sysfinance/summayTaShowAC.do?summayi="+summayi,"window","height=450,width=250,top="+(window.screen.availHeight-30-450)/2+",left="+(window.screen.availWidth-10-250)/2+",scrollbars=yes, status=yes"); 
  
}
/*---用来判断年月YYYYMMDD---*/
function checkMonth(month)
{
  var tempMonth;
  eval("tempMonth=document.all."+month)
  var strMonth = tempMonth.value;
  if(strMonth.length!=6)
  {
    alert("请输入六位的年月，格式为200410！");
    tempMonth.value="";
    tempMonth.focus();
    return false;
  }
  if(strMonth.substring(0,4)<1900){
    alert("年份应该大于1900！");
    tempMonth.value="";
    tempMonth.focus();
    return false;
  }
  if(strMonth.substring(4,6)>12 || strMonth.substring(4,6)<1)
  {
    alert("月份应该在1-12月之间！");
    tempMonth.value="";
    tempMonth.focus();
    return false;
  }

  return true;
}
/*---用来判断起始年月---*/
function checkTimes(month1,month2)
{
  var tempMonth1;
  var tempMonth2;
  eval("tempMonth1=document.all."+month1)
  eval("tempMonth2=document.all."+month2)
  var strMonth1 = tempMonth1.value;
  var strMonth2 = tempMonth2.value;
  if(strMonth1.length!=6)
  {
    alert("请输入六位的年月，格式为200410！");
    tempMonth1.value="";
    tempMonth1.focus();
    return false;
  }
  if(strMonth2.length!=6)
  {
    alert("请输入六位的年月，格式为200410！");
    tempMonth2.value="";
    tempMonth2.focus();
    return false;
  }
  if(strMonth1.substring(0,4)<1900){
    alert("年份应该大于1900！");
    tempMonth1.value="";
    tempMonth1.focus();
    return false;
  }
  if(strMonth2.substring(0,4)<1900){
    alert("年份应该大于1900！");
    tempMonth2.value="";
    tempMonth2.focus();
    return false;
  }
  if(strMonth1.substring(4,6)>12 || strMonth1.substring(4,6)<1)
  {
    alert("月份应该在1-12月之间！");
    tempMonth1.value="";
    tempMonth1.focus();
    return false;
  }
  if(strMonth2.substring(4,6)>12 || strMonth2.substring(4,6)<1)
  {
    alert("月份应该在1-12月之间！");
    tempMonth2.value="";
    tempMonth2.focus();
    return false;
  }
  if(strMonth1>strMonth2)
  {
    alert("起始年月应该不大于终止年月！");
    tempMonth1.value="";
    tempMonth1.focus();
    return false;
  }
  return true;
}

/*---用来判断日期YYYYMMDD---*/
function checkDate(date)
{
  var strDate = date.value;
  if(strDate.length!=8)
  {
    alert("请输入八位的日期格式，例如20070101！");
    return false;
  }
  if(strDate.substring(0,4)<1900){
    alert("年份应该大于1900！");
    return false;
  }
  if(strDate.substring(4,6)>12 || strDate.substring(4,6)<1)
  {
    alert("月份应该在1-12月之间！");
    return false;
  }
  var tempStrDate=strDate.substring(6,8);
  var tempStrMonth=strDate.substring(4,6);
 if(tempStrMonth==2&&tempStrDate>29)
  {
    alert("日期不能大于29！");
    return false;
  }else if((tempStrMonth==1||tempStrMonth==3||tempStrMonth==5||tempStrMonth==7||tempStrMonth==8||tempStrMonth==10||tempStrMonth==12)&&tempStrDate>31){
    alert("日期不能大于31！");
    return false;
  }else if((tempStrMonth==2||tempStrMonth==4||tempStrMonth==6||tempStrMonth==9||tempStrMonth==11)&&tempStrDate>30){
    alert("日期不能大于30！");
    return false;
  }
  return true;
}

//判断金额
function checkMoney(money){
var salarybase = money.match(/^([0-9]{1,10})(\.[0-9]{1,2})?$/)
if (salarybase==null)
			{	
        		alert("请正确录入金额！格式如：1087.23");
				return false;
			}else{
				return true;
			}

}
//判断格式是否为"200607"格式的年月
function checkYearMonth(ym){
	var temp_ym = ym.match(/^(([1][9]|[2][0])\d{2}([0][1-9]|[1][0-2]))$/); 
	if(temp_ym==null){
		        	alert("请正确录入年月！格式如：200606");
				    return false;
	}else{
		return true;
	}
	
}

//判断格式是否为"2006"格式的年
function checkYear(y){
	var temp_y = y.match(/^(([1][9]|[2][0])\d{2})$/); 
	if(temp_y==null){
		        	alert("请正确录入年！格式如：2006");
				    return false;
	}else{
		return true;
	}
	
}

//判断起始年月 是否小于 中止年月
function comparetoYM(ym1,ym2){
			if(ym1.substring(0, 4)<ym2.substring(0, 4)){
			 	return true;
	     	 }else if(ym1.substring(0, 4)==ym2.substring(0, 4)){
		      	  if(ym1.substring(4, 6)>ym2.substring(4, 6)){
		         	 alert("录入的起始月份大于中止月份！");
		         	 return false;
		      	  }else{
		      	  	return true;
		      	  }
	     	 }else{
	         	 alert("录入的起始年份大于中止年份！");
	         	 return false;
	     	 }
}
// 判断身份证
function isNumberX(String)
{ 
    var Letters = "1234567890xX-"; //可以自己增加可输入值
    var i;
    var c;
      if(String.charAt( 0 )=='-')
 return false;
      if( String.charAt( String.length - 1 ) == '-' )
          return false;
     for( i = 0; i < String.length; i ++ )
     {
          c = String.charAt( i );
   if (Letters.indexOf( c ) < 0)
          return false;
}
     return true;
}
function IsValidYear(psYear)
{
    var sYear = new String(psYear);


    if(psYear==null)
    {
        return false;
    }


    if(isNaN(psYear)==true)
    {
        return false;
    }

    if(sYear == "")
    {
        return true;
    }

    if(sYear.match(/[^0-9]/g)!=null)
    {
        return false;
    }

    var nYear = parseInt(sYear, 10);

    if((nYear < 0) || (9999 < nYear))
    {
        return false;
    }

    return true;
}


function IsValidMonth(psMonth)
{
    var sMonth = new String(psMonth);

    if(psMonth==null)
    {
        return false;
    }

    if(isNaN(psMonth)==true)
    {
        return false;
    }

    if(sMonth == "")
    {
        return true;
    }

    if(sMonth.match(/[^0-9]/g)!=null)
    {
        return false;
    }

    var nMonth = parseInt(sMonth,10);

    if((nMonth < 0) || (12 < nMonth))
    {
        return false;
    }

    return true;
}


function IsValidDay(psDay)
{
    var sDay  = new String(psDay);

    if(psDay==null)
    {
        return false;
    }

    if(isNaN(psDay)==true)
    {
        return false;
    }

    if(sDay == "")
    {
        return true;
    }

    if(sDay.match(/[^0-9]/g)!=null)
    {
        return false;
    }

    var nDay = parseInt(psDay, 10);

    if((nDay < 0) || (31 < nDay))
    {
        return false;
    }

    return true;
}


function IsValidDate(psDate)
{
var psYear=psDate.substring(0,4);
var psMonth=psDate.substring(4,6);
var psDay=psDate.substring(6,8);
    if(psYear==null || psMonth==null || psDay==null)
    {
        return false;
    }

    var sYear  = new String(psYear);
    var sMonth = new String(psMonth);
    var sDay   = new String(psDay);

    if(IsValidYear(sYear)==false)
    {
        return false;
    }

    if(IsValidMonth(sMonth)==false)
    {
        return false;
    }

    if(IsValidDay(sDay)==false)
    {
        return false;
    }

    var nYear  = parseInt(sYear,  10);
    var nMonth = parseInt(sMonth, 10);
    var nDay   = parseInt(sDay,   10);

    if(sYear=="" &&  sMonth=="" && sDay=="")
    {
        return true;
    }

    if(sYear=="" || sMonth=="" || sDay=="")
    {
        return false;
    }
    
    if(nMonth < 1 || 12 < nMonth)
    {
        return false;
    }
    if(nDay < 1 || 31 < nDay)
    {
        return false;
    }

    if(nMonth == 2)
    {
        if((nYear % 400 == 0) || (nYear % 4 == 0) && (nYear % 100 != 0))
        {
            if((nDay < 1) || (nDay > 29))
            {
                return false;
            }
        }
        else 
        {
            if((nDay < 1) || (nDay > 28))
            {
                return false;
            }
        }
    }
    else if((nMonth == 1)  || 
            (nMonth == 3)  || 
            (nMonth == 5)  || 
            (nMonth == 7)  || 
            (nMonth == 8)  || 
            (nMonth == 10) || 
            (nMonth == 12))
    {
        if((nDay < 1) || (31 < nDay))
        {
            return false;
        }
    }
    else 
    {
        if((nDay < 1) || (30 < nDay))
        {
            return false;
        }
    }

    return true;
}
//校验数字
function isNumber(String){ 
    var Letters = "1234567890-"; //可以自己增加可输入值
    var i;
    var c;
    if(String.charAt( 0 )=='-')
 		return false;
    if( String.charAt( String.length - 1 ) == '-' )
    	return false;
    for( i = 0; i < String.length; i ++ ){
	    c = String.charAt( i );
	   	if (Letters.indexOf( c ) < 0)
	    	return false;
	}
    return true;
}
// 校验数字与字母
function isNumLetter(numberLetter){
	var patrn=/^[a-zA-Z0-9]{1}([a-zA-Z0-9]){0,99}$/;
	if (!patrn.exec(numberLetter)) return false
	return true
}
// 校验姓名(中西文)
function isCHorEN(name){
	if(name==null || name=="") return true;
	var patrn=/^[\u0391-\uFFE5A-Za-z]+$/;
	if (!patrn.exec(name)) return false
	return true
}
//校验是否全由英文字母组成 
function isLetter(s) 
{
var patrn=/^[A-Za-z]+$/; 
if (!patrn.exec(s)) return false 
return true 
} 
//判断按键，输入只能是数字
function onlyNum()
{
if(!((event.keyCode>=48&&event.keyCode<=57)||(event.keyCode==189)||(event.keyCode>=96&&event.keyCode<=105)||(event.keyCode==109)||(event.keyCode==8)||(event.keyCode==13)||(event.keyCode==46)||(event.keyCode>=37&&event.keyCode<=40)))
{
event.returnValue=false;}
}

function officeAjax(path){
	var officeCode = document.all.officeCode.value;
	if(officeCode.length==0){
		officeCode="all";
	}
	var url = path+"/syscollection/collloanback/officeAndBankAAC.do?officeCode="+officeCode;
	officeFindInfo(url);
}
function officeFindInfo(url) {
 		createXMLHttpRequest();
	    xmlHttp.onreadystatechange = officeStateChange;
	    xmlHttp.open("GET", url, true);
	    xmlHttp.send(null);   
}
function officeStateChange() {
  if(xmlHttp.readyState == 4) {
      if(xmlHttp.status == 200) {
        var xmlDoc = xmlHttp.responseXML;
		var values = xmlDoc.getElementsByTagName("value");
		 
		var texts  = xmlDoc.getElementsByTagName("text");		
		var selectObj = document.getElementById("collectionBankId");
		selectObj.length = 0;
		
		var mynull = new Option("","");
		selectObj.options[selectObj.length++] = mynull;
		for ( i=0; i < values.length; i++ ) {
			var childOption = new Option(texts[i].firstChild.data,values[i].firstChild.data);
			selectObj.options[selectObj.length++] = childOption;
		}
		document.all.collectionBankId.value="";	
      }
   }
}
//列表显示  绿白相间
function gotoColorpp(i,form){
	var temp1;
	var temp2;
	eval("temp1=tr"+i);
	eval("temp2=form.s"+i);
	if(temp2.checked){
		temp1.style.background="#EEFBFF";
	}else{
		var ii=parseInt(i)+1;
		if (ii%2==0) {
			temp1.style.background="#FFFFFF";
		}else{
			temp1.style.background="#F2FFE5";
		}
	}
}
function gotoClickpp(i,form){
	var temp1;
	var temp2;
	var temp3;
	eval("temp1=tr"+i);
	eval("temp3="+old_temp);
	eval("temp2=form.s"+i);
	temp2.checked="true";
	var ii=old_temp.substr(2,3);
	ii=parseInt(ii)+1;
	if (ii%2==0) {
			temp3.style.background="#FFFFFF";
		}else{
			temp3.style.background="#F2FFE5";
		}
	temp1.style.background="#EEFBFF";
	old_temp="tr"+i;
}
//在点击复选的时候触发的事件用于给隐藏域赋值-->保存选中的id

function saveId(e)
{
	var s = document.all.rowArrayHid.value;
	if(e.checked)
	{
		document.all.rowArrayHid.value = s+e.value+"-";
	}else{
		var pos=s.indexOf(e.value,0);
		if(pos!="-1"){
			var s1 = s.substring(0,pos);
			var len = e.value.length;
			var s2 = s.substring(pos+len+1,s.length);
			document.all.rowArrayHid.value = s1+s2;
		}
	}
}
function loginReg(){
	document.forms(0).Cell1.Login("沈阳金软科技有限公司","","13100104509","0740-1662-7775-3003"); 
}
