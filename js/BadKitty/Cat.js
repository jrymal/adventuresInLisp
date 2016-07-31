

function Cat(type) {
	this.speed = 10;
	this.mood = RUNNING;
	
	this.head = document.getElementById('headObject'); // get the "foo" object
	this.head.leftEye = document.getElementById('leftEyeObject');
	this.head.rightEye = document.getElementById('rightEyeObject');
	this.head.leftEye.pupil = document.getElementById('leftEyePupilObject');
	this.head.rightEye.pupil = document.getElementById('rightEyePupilObject');
	
	this.chest = document.getElementById('chestObject');
	
	this.head.style.left = '0px'; // set its initial position to 0px
	this.head.style.top  = '0px'; // set its initial position to 0px

};


Cat.prototype.change = function( curPos, nextPos ) {
	
	pos = new Position();
	
	if (nextPos == null 
			|| curPos == null) {
		return curPos;
	}

	if (Math.abs(curPos.left - nextPos.left) < MAX_DIST 
			&& Math.abs(curPos.top - nextPos.top) < MAX_DIST) {
		return curPos;
	}
	
	switch (this.mood) {
	case POUNCING:
		// logarithmic
		pos.left = curPos.left + ((nextPos.left-curPos.left)/2);
		pos.top  = curPos.top + ((nextPos.top-curPos.top)/2);
		break;
	case RUNNING:
		// linear
		// ...way harder than you think
		
		// y = m*x + b
		
		slopeTop = nextPos.top - curPos.top;
		slopeBottom = nextPos.left - curPos.left; 
		
		if (slopeBottom == 0) {
			// ensure non 0 value
			slopeBottom = .001;
		}
		
		slope = slopeTop / slopeBottom;
		
		// if the slope is greater than 45 degrees, 
		// swap out to the reversed draw (evens out the speed)
		if (Math.abs(slope) < 1) {
			// normal y = mx+b stuff
			b = curPos.top - slope*curPos.left;
		
			pos.left = curPos.left + ((nextPos.left > curPos.left) ? this.speed : -this.speed);
	    	pos.top= slope * pos.left + b;
		} else {
			// reverse draw...inverse EVERYTHING
			slope = 1/slope;
			b = curPos.left - slope*curPos.top;
			
			pos.top = curPos.top + ((nextPos.top > curPos.top) ? this.speed : -this.speed);
	    	pos.left= slope * pos.top + b;
			
		}
	    break;
	}
	
	return pos;
};

Cat.prototype.placeChest = function(catPos) {
	this.chest.style.left = catPos.left + HEAD_BODY_OFFSET+'px';
	this.chest.style.top = catPos.top + HEAD_BOTTOM+'px';
};

Cat.prototype.placeHead = function(catPos) {
	this.head.style.left = catPos.left + 'px';
	this.head.style.top  = catPos.top + 'px';
	  
	this.head.leftEye.style.left = EYE_LEFT_PAD + 'px';
    this.head.leftEye.style.top  = EYE_LINE + 'px';
		
	this.head.rightEye.style.left = EYE_RIGHT_PAD + 'px';
	this.head.rightEye.style.top  = EYE_LINE  + 'px';
};

Cat.prototype.moveEyes = function(mousePos) {
	this.moveEye( mousePos, this.head.leftEye );
	this.moveEye( mousePos, this.head.rightEye );	
};

Cat.prototype.draw = function(mousePos){
	
	oldPos = new Position();
	oldPos.left = parseInt(this.head.style.left);
	oldPos.top  = parseInt(this.head.style.top);

	newPos = this.change(oldPos, mousePos);	
	
	this.moveEyes(mousePos);
	
	this.placeHead(newPos);
	//this.placeChest(newPos);
};

Cat.prototype.moveEye = function( mousePos, eyeObj ) {
	eyeRadius = 7;
	
	eye = new Position();
	eye.left = parseInt(this.head.style.left) + parseInt(eyeObj.style.left) + eyeRadius;
	eye.top = parseInt(this.head.style.top) + parseInt(eyeObj.style.top) + eyeRadius;
	
	d = new Position();
	d.left = mousePos.left - eye.left;
	d.top  = mousePos.top  - eye.top;

	R = Math.sqrt(d.left*d.left + d.top*d.top);

	pos = new Position();
	pos.left = (R < eyeRadius) ? d.left : d.left*eyeRadius / R;
	pos.top  = (R < eyeRadius) ? d.top  : d.top *eyeRadius / R;
	
	eyeObj.pupil.style.left = eyeRadius + pos.left + "px";
	eyeObj.pupil.style.top  = eyeRadius + pos.top  + "px";
};

