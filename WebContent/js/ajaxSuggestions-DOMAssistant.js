/*
	AJAX Suggestions is developed by Robert Nyman, http://www.robertnyman.com, and it is released according to the
	Creative Commons Deed license (http://creativecommons.org/licenses/GPL/2.0/)
	For more information, please see http://www.robertnyman.com/ajax-suggestions
*/
// ---
var ajaxSuggestions = {
	// Settings
	elmIdToPresentResultsIn : "search-results",
	elmIdResultsContainer : "search-result-suggestions",
	charactersBeforeSearch : 2,
	timeBeforeSuggest : 200, // In milliseconds
	sameWidthAsInputElm : false,
	offsetLeft: 0,
	offsetTop : 0,
	urlExt : "search=",
	addSearchTermToQueryString : true,
	addKeyNavigationEvents : true,
	hideResultsOnDocumentClick : true,
	itemClassName : "item",
	itemSelectedClassName : "selected",
	itemInsertValueIntoInputClassName : "choose-value",
	itemInsertValueSetFocusToInput : true,
	hideResultsWhenInsertValueIsSelected : true,
	itemSeparator : ";",
	turnAutoCompleteOff : true,
	// Object properties
	xmlHttp : null,
	elements : [],
	timer : null,
	currentElm : null,
	currentKeyEvent : null,
	suggestionsForElm : null,
	elmToPresentResultsIn : null,
	elmResultsContainer : null,
	suggestions : [],
	resultIndex : 0,
	selectedItem : -1,
	resultsAreVisible : false,
	valueAddedFromResultsListToInput : false,
	
	init : function (){
		this.xmlHttp = this.createXmlHttp();
		if(this.xmlHttp){
			this.elements = document.getElementsByClassName("ajax-suggestion", "input");
			this.applyEvents();
			this.elmToPresentResultsIn = $(this.elmIdToPresentResultsIn);
			this.elmResultsContainer = $(this.elmIdResultsContainer);
			$(window).addEvent("unload", this.closeSession);
			if(this.addKeyNavigationEvents){
				$(document).addEvent("keydown", ajaxSuggestions.preventDefaultForArrowKeys);
				$(document).addEvent("keypress", ajaxSuggestions.preventDefaultForArrowKeys);
				$(document).addEvent("keyup", ajaxSuggestions.navigateResults);
			}
			if(this.hideResultsOnDocumentClick){
				$(document).addEvent("click", this.clearResultsElement);
			}
		}
	},
	
	createXmlHttp : function (){
		this.xmlHttp = null;
		if(typeof XMLHttpRequest != "undefined"){
			this.xmlHttp = new XMLHttpRequest();
		}
		else if(typeof window.ActiveXObject != "undefined"){
			try {
				this.xmlHttp = new ActiveXObject("Msxml2.XMLHTTP.4.0");
			}
			catch(e){
				try {
					this.xmlHttp = new ActiveXObject("MSXML2.XMLHTTP");
				}
				catch(e){
					try {
						this.xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
					}
					catch(e){
						this.xmlHttp = null;
					}
				}
			}
		}
		return this.xmlHttp;
	},
	
	applyEvents : function (){
		var element;
		for(var i=0; i<this.elements.length; i++){
			element = $(this.elements[i]);
			if(this.turnAutoCompleteOff){
				element.setAttribute("autocomplete", "off");
			}
			element.addEvent("keyup", this.startSuggestionsTimer);
			if(this.hideResultsOnDocumentClick){
				element.addEvent("click", this.preventInputClickBubbling);
			}
		};
	},
	
	startSuggestionsTimer : function (evt){
		clearTimeout(ajaxSuggestions.timer);
		ajaxSuggestions.currentElm = this;
		ajaxSuggestions.currentKeyEvent = evt.keyCode;
		ajaxSuggestions.timer = setTimeout("ajaxSuggestions.getSuggestions()", ajaxSuggestions.timeBeforeSuggest);
	},
	
	getSuggestions : function (){
		var value = this.currentElm.value;
		if(!/13|27|37|39/.test(this.currentKeyEvent)){
			var url = this.currentElm.className.replace(/.*url-([\w\/\?\.-]+).*/, "$1");
			if(!this.valueAddedFromResultsListToInput){
				ajaxSuggestions.clearResults(true);
			}
			if(value.length > this.charactersBeforeSearch && url.length > 0){
				this.makeSuggestionCall(value, url);
			}
			else if(value.length == 0 || !this.valueAddedFromResultsListToInput){
				ajaxSuggestions.clearResults();
			}
		}
	},
	
	makeSuggestionCall : function (value, url){
		var regExpValue = new RegExp(("^" + value + "$"), "i");
		var exists = false;
		var suggestionItem;
		var url = url + ((/\?/.test(url))? "&" : "?") + this.urlExt + ((this.addSearchTermToQueryString)? value : "");
		for(var i=0; i<this.suggestions.length; i++){
			suggestionItem = this.suggestions[i];
			if(regExpValue.test(suggestionItem[0]) && url == suggestionItem[2]){
				exists = true;
				this.precedingResultIndex = this.resultIndex;
				this.resultIndex = i;
				this.presentResult();
				break;
			}
		};
		if(!exists){
			this.xmlHttp.onreadystatechange = function (){};
			this.xmlHttp.abort();
			this.currentValue = value;
			this.currentURL = url;
			this.xmlHttp.open("GET", url, true);
			this.xmlHttp.onreadystatechange = this.getResults;
			this.xmlHttp.send(null);
		}
	},
	
	getResults : function (){
		if(ajaxSuggestions.xmlHttp.readyState == 4 && ajaxSuggestions.xmlHttp.responseText.length > 0){
			ajaxSuggestions.loadResults();
		}
	},
	
	loadResults : function (){
		this.resultIndex = this.suggestions.length;
		this.suggestions.push([this.currentValue, this.xmlHttp.responseText, this.currentURL]);
		this.presentResult();
	},
	
	presentResult : function (){
		this.elmToPresentResultsIn.replaceContent(this.suggestions[this.resultIndex][1]);
		var coordinates = this.getCoordinates();
		var elm = this.elmResultsContainer.style;
		elm.left = coordinates[0] + this.offsetLeft + "px";
		elm.top = coordinates[1] + this.currentElm.offsetHeight + this.offsetTop + "px";
		if(this.sameWidthAsInputElm){
			elm.width = this.currentElm.offsetWidth + "px";
		}
		this.applyResultEvents();
		elm.display = "block";
		this.resultsAreVisible = true;
		if(this.addKeyNavigationEvents && /38|40/.test(this.currentKeyEvent)){
			if(!this.valueAddedFromResultsListToInput){
				this.selectedItem = -1;
			}
			this.navigateResults(null, this.currentKeyEvent);
		}
	},
	
	clearResults : function (justClear){
		if(this.elmResultsContainer && this.elmToPresentResultsIn){
			if(!justClear){
				this.elmResultsContainer.style.display = "none";
				this.resultsAreVisible = false;
			}
			this.elmToPresentResultsIn.replaceContent("");
			this.selectedItem = -1;
		}
	},
	
	clearResultsElement : function (){
		ajaxSuggestions.clearResults();
	},
	
	navigateResults : function (evt, keyCode){
		if(ajaxSuggestions.currentElm && ajaxSuggestions.elmToPresentResultsIn){
			var results = ajaxSuggestions.elmToPresentResultsIn.getElementsByClassName(ajaxSuggestions.itemClassName);
			var selectedItem = (!evt && keyCode == 38)? results.length : ajaxSuggestions.selectedItem;
			var keyCode = keyCode || evt.keyCode;
			var navigateUp = keyCode == 37 || keyCode == 38;
			var navigateDown = keyCode == 39 || keyCode == 40;
			if(results.length > 0 && (navigateUp || navigateDown)){
				if(navigateUp){
					if((selectedItem - 1) >= 0){
						selectedItem--;
					}
					else{
						selectedItem = -1;
					}
				}		
				else if(navigateDown){
					if((selectedItem + 1) < results.length){
						selectedItem++;
					}
					else{
						selectedItem = -1;
					}
				}
				for(var i=0; i<results.length; i++){
					$(results[i]).removeClass(ajaxSuggestions.itemSelectedClassName);
				};
				ajaxSuggestions.selectedItem = selectedItem;
				var elmToFocus = ajaxSuggestions.currentElm;
				if(selectedItem > -1){
					results[selectedItem].addClass(ajaxSuggestions.itemSelectedClassName);
					elmToFocus = results[selectedItem];
				}
				try{
					elmToFocus.focus();
				}
				catch(e){
					// Just in case... :-)
				}
				if(evt){
					DOMAssistant.preventDefault(evt);
					DOMAssistant.cancelBubble(evt);
				}
				return false;
			}
			else if(keyCode == 27){
				ajaxSuggestions.clearResults();
				try{
					ajaxSuggestions.currentElm.focus();
				}
				catch(e){
					// Just in case... :-)
				}
			}
		}
	},
	
	applyResultEvents : function (){
		var insertValueItems = this.elmToPresentResultsIn.getElementsByClassName(this.itemInsertValueIntoInputClassName, "a");
		var item;
		for(var i=0; i<insertValueItems.length; i++){
			item = $(insertValueItems[i]);
			item.inputRef = this.currentElm;
			item.addEvent("click", this.insertValueIntoField)
		};		
	},
	
	insertValueIntoField : function (evt){
		var input = this.inputRef;
		var value = this.getAttribute("href");
		if(!new RegExp(value).test(input.value)){
			input.value = ((input.value.length > 0 && /;/i.test(input.value))? (input.value + value) : value) + ajaxSuggestions.itemSeparator;
		}
		DOMAssistant.preventDefault(evt);
		DOMAssistant.cancelBubble(evt);
		if(ajaxSuggestions.itemInsertValueSetFocusToInput){
			try{
				input.focus();
			}
			catch(e){
				// Just in case... :-)
			}
		}
		if(ajaxSuggestions.hideResultsWhenInsertValueIsSelected){
			ajaxSuggestions.clearResults();
		}
		ajaxSuggestions.valueAddedFromResultsListToInput = true;
	},
	
	preventInputClickBubbling : function (evt){
		DOMAssistant.preventDefault(evt);
		DOMAssistant.cancelBubble(evt);
		return false;
	},
	
	preventDefaultForArrowKeys : function (evt){
		var keyCode = evt.keyCode;
		var navigateUp = keyCode == 37 || keyCode == 38;
		var navigateDown = keyCode == 39 || keyCode == 40;
		if((!evt.ctrlKey && !evt.metaKey) && ajaxSuggestions.resultsAreVisible && (navigateUp || navigateDown)){
			DOMAssistant.preventDefault(evt);
			DOMAssistant.cancelBubble(evt);
			return false;
		}	
	},
	
	getCoordinates : function (){
		var elm = this.currentElm;
		var offsetLeft = 0;
		var offsetTop = 0;
		while(elm.offsetParent){
			offsetLeft += elm.offsetLeft;
			offsetTop += elm.offsetTop;
			if(elm.scrollTop > 0){
				offsetTop -= elm.scrollTop;
			}
			elm = elm.offsetParent;
		}
		return [offsetLeft, offsetTop];
	},
	
	closeSession : function (){
		delete ajaxSuggestions;
		ajaxSuggestions = null;
	}	
};
// ---