/*
	DOMAssistant is developed by Robert Nyman, http://www.robertnyman.com, and it is released according to the
	Creative Commons Attribution-ShareAlike 2.5 license (http://creativecommons.org/licenses/by-sa/2.5/deed.en)
	For more information, please see http://www.robertnyman.com/domassistant
	
	This module by Robert Nyman, http://www.robertnyman.com
*/
DOMAssistant.initCSS = function (){
	this.addCSSMethods();
};

DOMAssistant.addCSSMethods = function (){
	if(typeof HTMLElement == "function"){		
		HTMLElement.prototype.addClass = this.addClass;
		HTMLElement.prototype.removeClass = this.removeClass;
		HTMLElement.prototype.hasClass = this.hasClass;
		HTMLElement.prototype.getStyle = this.getStyle;
	}
	this.methodsToAdd.push(["addClass", this.addClass]);
	this.methodsToAdd.push(["removeClass", this.removeClass]);
	this.methodsToAdd.push(["hasClass", this.hasClass]);
	this.methodsToAdd.push(["getStyle", this.getStyle]);
};

DOMAssistant.addClass = function (className){
	var currentClass = this.className;
	if(!new RegExp(("(^|\\s)" + className + "(\\s|$)"), "i").test(currentClass)){
		this.className = currentClass + ((currentClass.length > 0)? " " : "") + className;
	}
	return this.className;
};

DOMAssistant.removeClass = function (className){
	var classToRemove = new RegExp(("(^|\\s)" + className + "(\\s|$)"), "i");
	this.className = this.className.replace(classToRemove, "").replace(/^\s+|\s+$/g, "");
	return this.className;
},

DOMAssistant.hasClass = function (className){
	return new RegExp(("(^|\\s)" + className + "(\\s|$)"), "i").test(this.className);
};

DOMAssistant.getStyle = function (cssRule){
	var cssVal = "";
	if(document.defaultView && document.defaultView.getComputedStyle){
		cssVal = document.defaultView.getComputedStyle(this, "").getPropertyValue(cssRule);
	}
	else if(this.currentStyle){
		cssVal = cssRule.replace(/\-(\w)/g, function (match, p1){
			return p1.toUpperCase();
		});
		cssVal = this.currentStyle[cssVal];
	}
	return cssVal;
};

DOMAssistant.initCSS();