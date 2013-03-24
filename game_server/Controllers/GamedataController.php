<?php
/**
* @author JoshuaDickerson joshuajdickerson@gmail.com
* the GamedataController object is the controller fired when the game posts a gameState object
*/
require_once "Models/GameState.php";

class GamedataController{
	private $POST;
 	private $actions = array();
 	private $view;
 	public $modelObj;
 	private $testArray = array();
 	public $jsonObj;
	
	function __construct($actions, $POST, $debug = false){
		$this->jsonObj = json_decode($POST['gameState']);
	}


	public function addGame(){

	}

	public function addState(){
		$gs = new GameState($this->jsonObj); 
	} // end addState
} // end class def



// pkGameID
// fkUserID_p1
// fkUserID_p2
// fldMode
// fldWinCond

?>