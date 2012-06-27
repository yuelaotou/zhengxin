<script>
function showSending() {
  sending.style.visibility="visible";
}
function showSendingDis(obj) {
  sending.style.visibility="visible";
  obj.disabled="true";
}
</script>
<script language="JavaScript">
<!--
function MM_findObj(n,d) { //v4.0
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && document.getElementById) x=document.getElementById(n); return x;
}

function MM_showHideLayers() { //v3.0
  var i,p,v,obj,args=MM_showHideLayers.arguments;
  for (i=0; i<(args.length-2); i+=3) if ((obj=MM_findObj(args[i]))!=null) { v=args[i+2];
    if (obj.style) { obj=obj.style; v=(v=='show')?'visible':(v='hide')?'hidden':v; }
    obj.visibility=v; }
}
//-->
</script>
<script language="JavaScript">
<!--
function MM_reloadPage(init) {  //reloads the window if Nav4 resized
  if (init==true) with (navigator) {if ((appName=="Netscape")&&(parseInt(appVersion)==4)) {
    document.MM_pgW=innerWidth; document.MM_pgH=innerHeight; onresize=MM_reloadPage; }}
  else if (innerWidth!=document.MM_pgW || innerHeight!=document.MM_pgH) location.reload();
}
MM_reloadPage(true);
// -->
</script>
<style type="text/css">
<!--
.f14 {  font-size: 14px}
.table {  FILTER: alpha(opacity=60)}
-->
</style>
<script language="JavaScript">
function setPosiTion(e){
var t=e.offsetTop;
var l=e.offsetLeft;
while(e=e.offsetParent){
t+=e.offsetTop;
l+=e.offsetLeft;
}

sending.style.left=l-180;
sending.style.top=t-35;

}
</script>

<div id="sending" style="position:absolute; left:137px; top:69px; width:270px; height:217px; z-index:1;visibility:hidden" > 
  <table width=500 height=200 border=0 cellspacing=2 cellpadding=0 bgcolor="#368ECF" class="table" >
    <tr>
      <td bgcolor=#E1F4FD align=center class="f14">Please Wait!!!...</td>
    </tr>
  </table>
</div>