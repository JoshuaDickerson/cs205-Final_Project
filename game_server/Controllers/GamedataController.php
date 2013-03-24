<?php
/* @author JoshuaDickerson joshuajdickerson@gmail.com
* the GamedataController object is the controller fired when the game posts a gameState object
*/
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
		// we need to update our db tables
		// which one is the human? get human name. ***** This restricts us to 2 players, with only 1 human
		if($jsonObj->allPlayers[0]->isHuman){
			$humanPlayer = $jsonObj->allPlayers[0]->playername;
		}else{
			$humanPlayer = $jsonObj->allPlayers[1]->playername;
		}
		// get the userID from database, using human name
		$array = array('tableName'=>'tblUserAccount', 'fldUsername'=>$humanPlayer);
		$dbWrapper = new InteractDB('select', $array);
	}
} // end class def



// pkGameID
// fkUserID_p1
// fkUserID_p2
// fldMode
// fldWinCond

?>