var cursorPos = null;
var cat = null;

addEvent(window, 'load', init);

function init() {
  cat = new Cat();
  cursorPos = new Position();

  document.onmousemove = getMousePosition;
  
  animationLoop(); // start animating
};

function animationLoop() {
	  
	  cat.draw(cursorPos);
	  
	  var nextAnimation = 100;
	  
	  switch (cat.mood) {
	  case POUNCING:
		  nextAnimation = 500;
	      break;  
	  case RUNNING:
		  nextAnimation = 100;
		  break;
	  }
	  
	  // keep this at the end
	  setTimeout(animationLoop, nextAnimation);
};

function getMousePosition( evt ) {
	cursorPos.left = evt.pageX;
	cursorPos.top = evt.pageY;
	
	cat.moveEyes(cursorPos);
};

function addEvent(obj, evType, fn, useCapture){
	  if (obj.addEventListener){
	    obj.addEventListener(evType, fn, useCapture);
	    return true;
	  } else if (obj.attachEvent){
	    var r = obj.attachEvent("on"+evType, fn);
	    return r;
	  }
};
