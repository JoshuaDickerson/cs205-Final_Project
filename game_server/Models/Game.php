<?php
/**
* The Game object takes a game ID and builds a model of that game, including a stack
* of Gamestate objects
*/
include_once "Models/Gamestate.php";
include_once "Models/Player.php";
class Game{
	public $gameID;
	public $uid1;
	public $uid2;
	public $p1Email;
	public $p2Email;
	public $mode;
	public $winCon;
	public $beginTime;
	public $isComplete = false;
	public $player1_obj;
	public $player2_obj;
	public $gameStartTime = 0;
	public $gameEndTime = 0;
	public $totalNumStates = 0;
	public $states = array();


	function __construct($gameIDstring = false){
		if($gameIDstring){
			$this->gameID = $gameIDstring;
			$this->buildGameFromID($gameIDstring);
		}
	} // end construct

	public function buildGameFromID($gameid){
		// get game data from db
		$array = array(
			'tableName'=>'tblGame',
			'pkGameID'=>$gameid
		);
		$dbWrapper = new InteractDB('select', $array);
		// our ID should be unique
		if(count($dbWrapper->returnedRows) == 1){
			$row = $dbWrapper->returnedRows[0];
			$this->gameID = $row['pkGameID'];
			$this->uid1 = $row['fkUserID_p1'];
			$this->uid2 = $row['fkUserID_p2'];
			$this->mode = $row['fldMode'];
			$this->winCon = $row['fldWinCond'];
			$this->gameStartTime = $row['fldGameStarted'];
			$this->gameEndTime = $row['fldGameCompleted'];
			$this->p1Email = $this->userIDToEmail($this->uid1);
			$this->p2Email = $this->userIDToEmail($this->uid2);

			// get our states
			$array = array(
				'tableName'=>'tblStates',
				'fkGameID'=>$this->gameID
			);
			$qry = "SELECT * FROM tblStates WHERE fkGameID='".$this->gameID."' ORDER BY pkStateID DESC";
			$dbWrapper = new InteractDB();
			$dbWrapper->customStatement($qry);
			// $dbWrapper = new InteractDB('select', $array);
			$this->totalNumStates = count($dbWrapper->returnedRows);
			// var_dump($dbWrapper->returnedRows);
			$rows = $dbWrapper->returnedRows;
			for($ii=0; $ii<$this->totalNumStates; $ii++){
				$this->states[$ii]['stateID'] = $rows[$ii]['pkStateID'];
				$this->states[$ii]['p1Snapshot'] = $rows[$ii]['fldPlayer1Snapshot'];
				$this->states[$ii]['p2Snapshot'] = $rows[$ii]['fldPlayer2Snapshot'];
				$this->states[$ii]['gameOver'] = $rows[$ii]['fldGameOver'];
				$this->states[$ii]['roundOver'] = $rows[$ii]['fldRoundOver'];
				$this->states[$ii]['roundCount'] = $rows[$ii]['fldRoundCount'];
			}
		}else{
			// more than one row pulled
			return false;
		}
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
		// $this->player1_obj = new Player($jsonObj->allPlayers[0]);
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
		// if we don't have this game, add it
		if(count($dbWrapper->returnedRows) < 1){
		// 	// first get the player IDs
			$micro = microtime(true);
			$timestamp = date("YmdHis").($micro*100);
			$this->uid1 = $this->emailToUserID($this->p1Email);
			$this->uid2 = $this->emailToUserID($this->p2Email);
			$array2 = array(
				'tableName'=>'tblGame',
				'fldGameStarted'=>$timestamp,
				'pkGameID'=>$this->gameID, 
				'fkUserID_p1'=>$this->uid1,
				'fkUserID_p2'=>$this->uid2,
				'fldMode'=>$this->mode,
				'fldWinCond'=>$this->winCon
			);

			$dbWrapper = new InteractDB('insert', $array2);
		}
	} // end addGame()

	public function addState($jsonObj){
		if($jsonObj->gameOver == 1){
			$micro = microtime(true);
			$timestamp = date("YmdHis").($micro*100);
			$qry = "UPDATE tblGame SET fldGameCompleted='".$timestamp."' WHERE pkGameID='".$this->gameID."'";
			$dbWrapper =  new InteractDB();
			$dbWrapper->customStatement($qry);
		}
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

	public function userIDToEmail($userID){
		$array = array(
			'tableName'=>'tblUserAccount',
			'pkUserID'=>$userID
		);
		$dbWrapper=new InteractDB('select', $array);
		if(count($dbWrapper->returnedRows) > 0){
			$userEmail = $dbWrapper->returnedRows[0]['fldEmail'];
			// $userID = 888;
		}else{
			$userEmail = "computer";
		}
		return $userEmail;
	} // end emailToUserID()


	public function getVars(){
	}

} // end class def
?>