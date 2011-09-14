/*
	DOMAssistant is developed by Robert Nyman, http://www.robertnyman.com, and it is released according to the
	Creative Commons Attribution-ShareAlike 2.5 license (http://creativecommons.org/licenses/by-sa/2.5/deed.en)
	For more information, please see http://www.robertnyman.com/domassistant
	
	This module by Robert Nyman, http://www.robertnyman.com
*/
DOMAssistant.initContent = function (){
	this.addContentMethods();
};

DOMAssistant.addContentMethods = function (){
	if(typeof HTMLElement == "function"){
		HTMLElement.prototype.prev = DOMAssistant.prev;
		HTMLElement.prototype.next = DOMAssistant.next;
		HTMLElement.prototype.create = DOMAssistant.create;
		HTMLElement.prototype.setAttributes = DOMAssistant.setAttributes;
		HTMLElement.prototype.addContent = DOMAssistant.addContent;
		HTMLElement.prototype.replaceContent = DOMAssistant.replaceContent;
		HTMLElement.prototype.remove = DOMAssistant.remove;
	}
	this.methodsToAdd.push(["prev", this.prev]);
	this.methodsToAdd.push(["next", this.next]);
	this.methodsToAdd.push(["create", this.create]);
	this.methodsToAdd.push(["setAttributes", this.setAttributes]);
	this.methodsToAdd.push(["addContent", this.addContent]);
	this.methodsToAdd.push(["replaceContent", this.replaceContent]);
	this.methodsToAdd.push(["remove", this.remove]);
};

DOMAssistant.prev = function (){
	var prevSib = this.previousSibling;
	while(prevSib && prevSib.nodeType != 1){
		prevSib = prevSib.previousSibling;
	}
	return prevSib;
};

DOMAssistant.next = function (){
	var nextSib = this.nextSibling;
	while(nextSib && nextSib.nodeType != 1){
		nextSib = nextSib.nextSibling;
	}
	return nextSib;
};

DOMAssistant.create = function (name, attr, append, content){
	var elm = document.createElement(name);
	elm = DOMAssistant.$(elm);
	if(attr){
		elm.setAttributes(attr);
	}
	if(typeof content != "undefined"){
		elm.addContent(content);
	}
	if(append){
		this.addContent(elm);	
	}
	return elm;
};

DOMAssistant.setAttributes = function (attr){	
	for(var i in attr){
		if(/class/i.test(i)){
			this.className = attr[i];
		}
		else{
			this.setAttribute(i, attr[i]);
		}	
	}
};

DOMAssistant.addContent = function (content){
	var retVal = null;
	if(typeof content == "string"){
		retVal = this.innerHTML += content;
	}
	else{		
		retVal = this.appendChild(content);
	}
	return retVal;
};

DOMAssistant.replaceContent = function (newContent){
	for(var i=(this.childNodes.length - 1); i>=0; i--){
    	this.childNodes[i].parentNode.removeChild(this.childNodes[i]);
    }
	this.addContent(newContent);
};

DOMAssistant.remove = function (){
	this.parentNode.removeChild(this);
};

DOMAssistant.initContent();