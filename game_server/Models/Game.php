<?php
/**
* The Game object takes a game ID and builds a model of that game, including a stack
* of Gamestate objects
*/
include_once "Models/Gamestate.php";
include_once "Models/Player.php";
class Game{
	private $gameID;
	private $uid1;
	private $uid2;
	private $p1Email;
	private $p2Email;
	private $mode;
	private $winCon;
	private $beginTime;
	private $isComplete = false;
	private $player1_obj;
	private $player2_obj;


	function __construct($gameIDstring = false){
		if($gameIDstring){
			$this->gameID = $gameIDstring;
			$this->buildGameFromID($gameIDstring);
		}
	} // end construct

	public function buildGameFromID(){
		// get game data from db
		// load each state
	}

	public function buildGameFromJson($jsonObj){
		// we have a jsonObject and want to build a game object from it
		$this->gameID = $jsonObj->uniqueID;
		$this->mode = $jsonObj->state->mode;
		$this->winCon = $jsonObj->state->winCon;
		$this->p1Email = $jsonObj->allPlayers[0]->playername;
		$this->p2Email = $jsonObj->allPlayers[1]->playername;
		// build our players
		$this->player1_obj = new Player($jsonObj->allPlayers[0]);
		$this->player2_obj = new Player($jsonObj->allPlayers[1]);
		// logThis($this->player1_obj);
		$this->addGame();
		$this->addState($jsonObj);
	}

	public function addGame(){
		// check if we already have this game in the DB
		// $gameID = $this->jsonObj->uniqueID;
		$array = array(
			'tableName'=>'tblGame',
			'pkGameID'=>$this->gameID
		);
		$dbWrapper = new InteractDB('select', $array);
		// logThis($dbWrapper);
		// if we don't have this game, add it
		if(count($dbWrapper->returnedRows) < 1){
		// 	// first get the player IDs
			$this->uid1 = $this->emailToUserID($this->p1Email);
			// logThis($uid1);
			$this->uid2 = $this->emailToUserID($this->p2Email);
			$array2 = array(
				'tableName'=>'tblGame',
				'pkGameID'=>$this->gameID, 
				'fkUserID_p1'=>$this->uid1,
				'fkUserID_p2'=>$this->uid2,
				'fldMode'=>$this->mode,
				'fldWinCond'=>$this->winCon
			);

			$dbWrapper = new InteractDB('insert', $array2);
			// logThis($dbWrapper);
		}
	} // end addGame()

	public function addState($jsonObj){
		// logThis("inside add state");
		$gs = new Gamestate($jsonObj, true);
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
	} // end emailToUserID()


	public function getVars(){
		return false;
	}

} // end class def
?>