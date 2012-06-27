/*******************************************************************************
		阿赖菜单控件程序经典windos风格版 由赖国欣设计于2003年5月，保留所有权利！
*********************************************************************************/
var MAX_Z_INDEX=30
var col_menu=[]; //menu collection
function alai_menu(width)
{
	var menu=this
	this.item=[]
	this.isShow=false
	this.bar=null
	this.copywrite="Copywrite by Alai(赖国欣) (c)2003，All right reserved!"
	this.body=document.createElement("div")
	document.body.insertAdjacentElement("beforeEnd",this.body)
	this.body.style.cssText="position:absolute;border:2 outset;background-color:buttonface;display:none;"
	this.body.style.pixelWidth=width==null?180:width
	var run=function(cmd,a0,a1,a2)
	{
		if(typeof(cmd)=="string")
		{	try{return eval(cmd);}
			catch(E){alert("run script string error:\n"+cmd);}
		}
		else if(typeof(cmd)=="function"){return cmd(a0,a1,a2);}
	}
	this.add=function(Text,exeType,exeArg,target)
	{
		var item=document.createElement("span")
		menu.body.insertAdjacentElement("beforeEnd",item)
		item.style.cssText="padding-top:2;padding-left:14;font-size:10pt;height:20;cursor:default;color:black;background-color:buttonface;width:100%;"
		item.innerHTML=Text
		menu.item[menu.item.length]=item
		item.enable=true
		item.execute=new Function()
		item.remove=function(){item.removeNode(true);}
		exeType=exeType==null?"":exeType
		switch(exeType.toLowerCase())
		{
			case "hide":
				item.execute=function(){menu.hide();}
				break;
			case "url":
				if(typeof(exeArg)!="string")break;
				if(target==null||target=="")target="_blank";
				item.execute=function(){menu.hide();open(exeArg,target);}
				break;
			case "js":
				if(typeof(exeArg)!="string")break;
				item.execute=function(){menu.hide();eval(exeArg)}
				break;
			case "sub":
				if(typeof(exeArg.body)=="undefined")break;
				item.execute=function(){menu.show(exeArg);}
				break;
		}
		item.onmousemove=function(){item.style.color=item.enable?"white":"gray";item.style.backgroundColor="darkblue";}
		item.onmouseout=function(){item.style.color=item.enable?"black":"gray";item.style.backgroundColor="buttonface";}
		item.onmousedown=item.onmouseup=function(){event.cancelBubble=true;}
		item.onclick=function(){event.cancelBubble=true;if(this.enable)run(item.execute);}
		return item
	}
	this.addLink=function(url_,text,target)
	{
		if(text==null || text=="")text=url_
		if(target==null || target=="")target="_blank"
		return menu.add(text,"url",url_,target)
	}
	this.seperate=function(){menu.body.insertAdjacentHTML("beforeEnd","<hr style='width:96%;'>");}
	this.show=function()
	{
		var a=arguments;
		var x,y,m=menu.body
		if(a.length==0)
		{
                        if((document.body.offsetWidth-event.clientX) > this.body.style.pixelWidth) {
                          x=event.clientX+document.body.scrollLeft+document.body.scrollLeft
                        }
                        else {
                          x=event.clientX - this.body.style.pixelWidth
                        }
                        if((document.body.offsetHeight-event.clientY) > 100) {
                          y=event.clientY+document.body.scrollTop
                        }
                        else {
                          y=event.clientY - 100;
                        }
		}
		else if(a.length==1 && typeof(a[0])=="object")
		{
			if(typeof(a[0].body)!="undefined")
			{
				m=a[0].body
				m.style.display="block"
				if(m.style.pixelWidth<document.body.offsetWidth-event.x)
				{	x=menu.body.style.pixelLeft+menu.body.offsetWidth}
				else
				{	x=menu.body.style.pixelLeft-m.style.pixelWidth}
				if(m.offsetHeight<document.body.offsetHeight-event.y)
				{	y=event.y+document.body.scrollTop-event.offsetY}
				else
				{	y=event.y-m.offsetHeight+document.body.scrollTop}
			}
			else
			{
				x=event.x+document.body.scrollLeft-event.offsetX-2
				y=event.y+document.body.scrollTop+a[0].offsetHeight-event.offsetY-4
			}
		}
		else if(a.length==2 && typeof(a[0])=="number" && typeof(a[1])=="number")
		{
			x=a[0];y=a[1];
		}
		else{alert("arguments type or number not match!");return;}
		for(var i=0;i<menu.item.length;i++)menu.item[i].style.color=menu.item[i].enable?"black":"gray"
		m.style.pixelLeft=x;
		m.style.pixelTop=y;
		m.style.display="block";
		m.style.zIndex=++MAX_Z_INDEX
		event.cancelBubble=true;
		menu.isShow=true
	}
	this.hide=function(){menu.body.style.display="none";menu.isShow=false;if(menu.bar!=null)menu.bar.style.border="1 solid buttonface";}
	this.hideAll=function(){for(var i=0;i<col_menu.length;i++)col_menu[i].hide();}
	col_menu[col_menu.length]=this
	document.body.onclick=this.hideAll
}
function menu_bar(top,left)
{
	var mb=this
	this.item=[]
	this.menu=[]
	this.body=document.createElement("div")
	document.body.insertAdjacentElement("beforeEnd",this.body)
	this.body.style.cssText="position:absolute;cursor:default;padding:2;background-color:buttonface;height:25;z-index:5;font-size:10pt;color:black;top:"+top+";left:"+left
	var chkShow=function(){for(var i=0;i<mb.menu.length;i++)if(mb.menu[i].isShow)return true;return false;}
	this.add=function(Text,menu)
	{
		var item=document.createElement("span")
		mb.body.insertAdjacentElement("beforeEnd",item)
		item.style.cssText="margin:0 7 0 3;padding:2 4 2 4;text-align:center;height:23;background-color:buttonface;"
		item.innerText=Text
		item.onmouseover=function()
		{
			this.style.border="1 ridge white"
			if(chkShow()){document.body.click();menu.show(this);this.style.border="1 inset";}
		}
		item.onmouseout=function()
		{
			if(!menu.isShow)this.style.border="1 solid buttonface"
		}
		item.onmousedown=item.onmouseup=function(){event.cancelBubble=true;menu.show(item);this.style.border="1 inset";}
		item.onclick=function(){event.cancelBubble=true;menu.show(this)}
		mb.item[mb.item.length]=item
		mb.menu[mb.menu.length]=menu
		menu.bar=item
		return item
	}
}
