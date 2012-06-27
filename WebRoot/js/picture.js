function excHz(url){
  var l=window.screen.width ;
  var w= window.screen.height; 
  var al=l/2-350;
  var aw=w/2-250;
  var ur="../syscommon/picture/browse.jsp?path="+url;
  var newwin=window.open(ur,"homeWin","toolbar=no,location=no,directories=no,status=yes,menubar=no,scrollbars=yes,resizable=yes,width=800,height=700,top="+aw+",left="+al+"");
  newwin.focus();

}

function   del()   
{   
      	var   fso,   f,   f1,   fc,   s;   
        fso   =   new   ActiveXObject("Scripting.FileSystemObject");   
        f   =   fso.GetFolder("d:\\picture\\");   
        fc   =   new   Enumerator(f.files);   
        s   =   "";   
        for   (;   !fc.atEnd();   fc.moveNext())   
        { 
              s   =   fc.item();   
              f3 = fso.GetFile(s);  
              f3.Delete();            
        }             
        //parent.window.location=delToUrl; 
} 
   