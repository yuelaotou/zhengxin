function selectAll(elem) {
  var obj=document.all(elem);
  for (i = 0;i<obj.options.length ; i++) {
    if (obj.options[i].text != "") {
      obj.options[i].selected = true;
    }
  }
}

function toSelected(available, selected) {
	var a = document.all(available);
	var s = document.all(selected);
	var la = new Array();
	for(i=0,k=0;i<a.options.length;i++) {
		if(a.options[i].selected){
			s.options[s.options.length]=new Option(a.options[i].text,a.options[i].value);
			la[k++]=i;
		}
	}
	
	for(i=la.length-1;i>=0;i--) {
		a.options[la[i]]=null;
	}
}

function toAvailable(available, selected) {
	var a = document.all(available);
	var s = document.all(selected);
	var ls = new Array();
	for(i=0,k=0;i<s.options.length;i++) {
		if(s.options[i].selected){
			a.options[a.options.length]=new Option(s.options[i].text,s.options[i].value);
			ls[k++]=i;
		}
	}
	
	for(i=ls.length-1;i>=0;i--) {
		s.options[ls[i]]=null;
	}
}

function allToSelected(available, selected) {
	selectAll(available);
	toSelected(available, selected);
}

function allToAvailable(available, selected) {
	selectAll(selected);
	toAvailable(available, selected);
}