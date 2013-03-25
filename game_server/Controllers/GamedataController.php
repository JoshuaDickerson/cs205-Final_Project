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
		// logThis("-----working-------");
		$this->jsonObj = json_decode($POST['gameState']);
		$this->addGame();
		$this->addState();
	}


	public function addGame(){
		// check if we already have this game in the DB
		$gameID = $this->jsonObj->uniqueID;
		$array = array(
			'tableName'=>'tblGame',
			'pkGameID'=>$gameID
		);
		$dbWrapper = new InteractDB('select', $array);
		// logThis($dbWrapper);
		// if we don't have this game, add it
		if(count($dbWrapper->returnedRows) < 1){
		// 	// first get the player IDs
			$p1Email = $this->jsonObj->allPlayers[0]->playername;
			// logThis($p1Email);
			$p2Email = $this->jsonObj->allPlayers[1]->playername;
			$uid1 = $this->emailToUserID($p1Email);
			// logThis($uid1);
			$uid2 = $this->emailToUserID($p2Email);
			$array2 = array(
				'tableName'=>'tblGame',
				'pkGameID'=>$gameID, 
				'fkUserID_p1'=>$uid1,
				'fkUserID_p2'=>$uid2,
				'fldMode'=>$this->jsonObj->state->mode,
				'fldWinCond'=>$this->jsonObj->state->winCon
			);

			$dbWrapper = new InteractDB('insert', $array2);
			// logThis($dbWrapper);
		}
	}

	public function addState(){
		// logThis("inside add state");
		$gs = new Gamestate($this->jsonObj, true);
	} // end addState

	public function emailToUserID($userEmailString){
		$array = array(
			'tableName'=>'tblUserAccount',
			'fldEmail'=>$userEmailString
		);
		$dbWrapper=new InteractDB('select', $array);
		if(count($dbWrapper->returnedRows) > 0){
			$userID = $dbWrapper->returnedRows[0]['pkUserID'];
			// $userID = 888;
		}else{
			$userID = 666;
		}

		return $userID;
	}

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