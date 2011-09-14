/*
	DOMAssistant is developed by Robert Nyman, http://www.robertnyman.com, and it is released according to the
	Creative Commons Attribution-ShareAlike 2.5 license (http://creativecommons.org/licenses/by-sa/2.5/deed.en)
	For more information, please see http://www.robertnyman.com/domassistant
	
	This module by Robert Nyman, http://www.robertnyman.com
*/
DOMAssistant.initEvents = function (){
	this.addEventMethods();
};

DOMAssistant.addEventMethods = function (){
	if(typeof HTMLElement == "function"){
		HTMLElement.prototype.addEvent = DOMAssistant.addEvent;
		HTMLElement.prototype.handleEvent = DOMAssistant.handleEvent;
		HTMLElement.prototype.removeEvent = DOMAssistant.removeEvent;
	}
	this.methodsToAdd.push(["addEvent", this.addEvent]);
	this.methodsToAdd.push(["handleEvent", this.handleEvent]);	
	this.methodsToAdd.push(["removeEvent", this.removeEvent]);
};

DOMAssistant.addEvent = function (evt, func){
	if(this.addEventListener){
		this.addEventListener(evt, func, false);
	}
	else{
		if(!this.events){
			this.events = {};
		}
		if(!this.events[evt]){
			this.events[evt] = [];
		}							
		this.events[evt].push(func);
		this["on" + evt] = DOMAssistant.handleEvent;
	}
	return true;
};

DOMAssistant.handleEvent = function (evt){
	var evt = evt || event;
	var eventType = evt.type;
	var eventColl = this.events[eventType];
	for (var i=0; i<eventColl.length; i++) {
		eventColl[i].call(this, evt);
	}
};

DOMAssistant.removeEvent = function (evt, func){
	if(this.removeEventListener){
		this.removeEventListener(evt, func, false);
	}
	else if(this.events){
		var eventColl = this.events[evt];
		for (var i=0; i<eventColl.length; i++) {
			if(eventColl[i] == func){
				delete eventColl[i]
				eventColl.splice(i, 1);
			}
		}
	}
};

DOMAssistant.preventDefault = function (evt){
	if(evt && evt.preventDefault){
		evt.preventDefault();
	}
	else{
		event.returnValue = false;
	}
};

DOMAssistant.cancelBubble = function (evt){
	if(evt && evt.stopPropagation){
		evt.stopPropagation();
	}
	else{
		event.cancelBubble = true;
	}
};

DOMAssistant.initEvents();