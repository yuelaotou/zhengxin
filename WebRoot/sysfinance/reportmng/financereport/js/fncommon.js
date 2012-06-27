function fPopUpExpressDlg(path)
{
	showx = event.screenX - event.offsetX - 4 - 210 ; // + deltaX;
	showy = event.screenY - event.offsetY + 18; // + deltaY;
	newWINwidth = 210 + 4 + 18;
	retval = window.showModalDialog(path+"/sysfinance/reportmng/financereport/definereport/Expresionss.jsp", "", "dialogWidth:700px; dialogHeight:330px; dialogLeft:"+showx+"px; dialogTop:"+showy+"px; status:no; directories:yes;scrollbars:no;Resizable=no; "  );

	return retval;
}

function insert_row(s){
var j=s
if(j==0){
document.all.cal0.value='+'
document.all.dis_div1.style.display='block'}
if(j==1){
document.all.cal1.value='+'
document.all.dis_div2.style.display='block'}
if(j==2){
document.all.cal2.value='+'
document.all.dis_div3.style.display='block'}
if(j==3){
document.all.cal3.value='+'
document.all.dis_div4.style.display='block'}
if(j==4){
document.all.cal4.value='+'
document.all.dis_div5.style.display='block'}
if(j==5){
document.all.cal5.value='+'
document.all.dis_div6.style.display='block'}
if(j==6){
document.all.cal6.value='+'
document.all.dis_div7.style.display='block'}
if(j==7){
document.all.cal7.value='+'
document.all.dis_div8.style.display='block'}
if(j==8){
document.all.cal8.value='+'
document.all.dis_div9.style.display='block'}
if(j==9){
document.all.cal9.value='+'
document.all.dis_div10.style.display='block'}
if(j==10){
document.all.cal10.value='+'
document.all.dis_div11.style.display='block'}
if(j==11){
document.all.cal11.value='+'
document.all.dis_div12.style.display='block'}
if(j==12){
document.all.cal12.value='+'
document.all.dis_div13.style.display='block'}
if(j==13){
document.all.cal13.value='+'
document.all.dis_div14.style.display='block'}
if(j==14){
document.all.cal14.value='+'
document.all.dis_div15.style.display='block'}
if(j==15){
document.all.cal15.value='+'
document.all.dis_div16.style.display='block'}
if(j==16){
document.all.cal16.value='+'
document.all.dis_div17.style.display='block'}
if(j==17){
document.all.cal17.value='+'
document.all.dis_div18.style.display='block'}
if(j==18){
document.all.cal18.value='+'
document.all.dis_div19.style.display='block'}
if(j==19){
document.all.cal19.value='+'}
if(j!=19){
j++;
loadMessage(j);
}
}

function insert_row2(s){
var j=s
if(j==0){
document.all.cal0.value='-'
document.all.dis_div1.style.display='block'}
if(j==1){
document.all.cal1.value='-'
document.all.dis_div2.style.display='block'}
if(j==2){
document.all.cal2.value='-'
document.all.dis_div3.style.display='block'}
if(j==3){
document.all.cal3.value='-'
document.all.dis_div4.style.display='block'}
if(j==4){
document.all.cal4.value='-'
document.all.dis_div5.style.display='block'}
if(j==5){
document.all.cal5.value='-'
document.all.dis_div6.style.display='block'}
if(j==6){
document.all.cal6.value='-'
document.all.dis_div7.style.display='block'}
if(j==7){
document.all.cal7.value='-'
document.all.dis_div8.style.display='block'}
if(j==8){
document.all.cal8.value='-'
document.all.dis_div9.style.display='block'}
if(j==9){
document.all.cal9.value='-'
document.all.dis_div10.style.display='block'}
if(j==10){
document.all.cal10.value='-'
document.all.dis_div11.style.display='block'}
if(j==11){
document.all.cal11.value='-'
document.all.dis_div12.style.display='block'}
if(j==12){
document.all.cal12.value='-'
document.all.dis_div13.style.display='block'}
if(j==13){
document.all.cal13.value='-'
document.all.dis_div14.style.display='block'}
if(j==14){
document.all.cal14.value='-'
document.all.dis_div15.style.display='block'}
if(j==15){
document.all.cal15.value='-'
document.all.dis_div16.style.display='block'}
if(j==16){
document.all.cal16.value='-'
document.all.dis_div17.style.display='block'}
if(j==17){
document.all.cal17.value='-'
document.all.dis_div18.style.display='block'}
if(j==18){
document.all.cal18.value='-'
document.all.dis_div19.style.display='block'}
if(j==19){
document.all.cal19.value='-'}
if(j!=19){
j++;
loadMessage(j);
}
}




