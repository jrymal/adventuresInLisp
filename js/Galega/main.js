var KB_UP=38;
var KB_DOWN=40;
var KB_LEFT=37;
var KB_RIGHT=39;
var KB_SPACE=32;

var ROOT_TAG = document.querySelector("#game");
var START_DIV = document.querySelector("#start");

var intervalLoop;



function startScreen() {
  START_DIV.style.display = 'block';

  ROOT_TAG.onmousemove=null;
  document.onkeydown=null;
  
}


function startGame() {
  START_DIV.style.display = 'none';
  
  ROOT_TAG.onmousemove=function(event){
    //alert("mouse:"+event.which);
  };
  
  document.onkeydown=function(event){
    //alert("key:"+event.which);
  };
  
  intervalLoop = setInterval(drawGalaga, 20);
}

function drawLoop() {
  
}

startScreen();