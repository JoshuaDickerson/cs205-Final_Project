<?php
/**
* @author JoshuaDickerson joshuajdickerson@gmail.com
* the GamedataController object is the controller fired when the game posts a gameState object
*/
include_once "Models/Gamestate.php";
class GamedataController{
	private $POST;
 	private $actions = array();
 	private $view;
 	public $modelObj;
 	private $testArray = array();
 	public $jsonObj;
	
	function __construct($actions, $POST, $debug = false){
		$this->jsonObj = json_decode($POST['gameState']);
		$this->addState();
	}


	public function addGame(){

	}

	public function addState(){
		// logThis("inside add state");
		$gs = new GameState($this->jsonObj); 
	} // end addState

	public function getVars(){
		return false;
	}
} // end class def



// pkGameID
// fkUserID_p1
// fkUserID_p2
// fldMode
// fldWinCond

?>