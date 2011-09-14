/*
	DOMAssistant is developed by Robert Nyman, http://www.robertnyman.com, and it is released according to the
	Creative Commons Attribution-ShareAlike 2.5 license (http://creativecommons.org/licenses/by-sa/2.5/deed.en)
	For more information, please see http://www.robertnyman.com/domassistant
*/
var DOMAssistant = {
	
	methodsToAdd : [],
	
	init : function (){
		this.applyMethod.call(window, "$", this.$);
		window.DOMAssistant = this;
		this.addBaseMethods();
	},
	
	addBaseMethods : function (){
		document.getElementsByClassName = this.getElementsByClassName;
		document.getElementsByAttribute = this.getElementsByAttribute;
		if(typeof HTMLElement == "function"){
			HTMLElement.prototype.getElementsByClassName = this.getElementsByClassName;
			HTMLElement.prototype.getElementsByAttribute = this.getElementsByAttribute;		
		}
		this.methodsToAdd.push(["getElementsByClassName", this.getElementsByClassName]);
		this.methodsToAdd.push(["getElementsByAttribute", this.getElementsByAttribute]);
	},
	
	applyMethod : function (method, func){
		if(typeof this[method] != "function"){
			this[method] = func;
		}
	},
	
	addMethods : function (elm){
		if(elm){
			var elms = (elm.constructor == Array)? elm : [elm];
			for(var i=0; i<elms.length; i++){	
				for(var j=0; j<this.methodsToAdd.length; j++){
	            	this.applyMethod.call(elms[i], this.methodsToAdd[j][0], this.methodsToAdd[j][1]);
	            }
			}
		}
	},
	
	$ : function (){
		var elm = null;
		if(document.getElementById){
			elm = (arguments.length > 1)? [] : null;
			var current;
			for(var i=0; i<arguments.length; i++){
				current = arguments[i];
				if(typeof current != "object"){
					current = document.getElementById(current);
				}
				if(arguments.length > 1){
					elm.push(current);
				}
				else{
					elm = current;
				}
			}
			DOMAssistant.addMethods(elm);
		}
		return elm;
    },
	
	getElementsByClassName : function (className, tag){
		var elms = ((!tag || tag == "*") && this.all)? this.all : this.getElementsByTagName(tag || "*");
		var returnElms = [];
		var className = className.replace(/\-/g, "\\-");
		var regExp = new RegExp("(^|\\s)" + className + "(\\s|$)");
		var elm;
		for(var i=0; i<elms.length; i++){
			elm = elms[i];		
			if(regExp.test(elm.className)){
				returnElms.push(elm);
			}
		}
		return (returnElms);
	},
	
	getElementsByAttribute : function (attr, attrVal, tag){
	    var elms = ((!tag || tag == "*") && this.all)? this.all : this.getElementsByTagName(tag || "*");
	    var returnElms = [];
	    if(typeof attrVal != "undefined"){
			var attrVal = new RegExp("(^|\\s)" + attrVal + "(\\s|$)");
		}
	    var current;
	    var currentAttr;
	    for(var i=0; i<elms.length; i++){
	        current = elms[i];
	        currentAttr = current.getAttribute(attr);
	        if(typeof currentAttr == "string" && currentAttr.length > 0){	
	            if(typeof attrVal == "undefined" || (attrVal && attrVal.test(currentAttr))){
					returnElms.push(current);
	            }
	        }
	    }
	    return returnElms;
	}	
}
DOMAssistant.init();