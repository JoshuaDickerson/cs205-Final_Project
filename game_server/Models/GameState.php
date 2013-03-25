<?php
/**
* @author JoshuaDickerson joshuajdickerson@gmail.com
* the GameState is constructed from json and mirrors the 
* java gameState  
**/
logThis("included");
require_once "Models/Player.php";
require_once "Models/InteractDB.php";

class GameState{
	private $gameID
	private $status;
	private $winCon;
	private $player1;
	private $player2;
	private $mode;
	private $numPlayers;
	private $roundCount;
	private $gameOver= false;
	private $roundOver = false;

	function __construct($jsonObj, $addToDB = false){
		$this->jsonToState($jsonObj);
		$this->uniqueID = $jsonObj->uniqueID;
		$this->gameOver = $jsonObj->gameOver;
		$this->roundOver = $jsonObj->roundOver;
		$this->roundCount = $jsonObj->roundCount;
		$this->winCon = $jsonObj->state->winCon;
		$this->mode = $jsonObj->state->mode;
		$this->numPlayers = $jsonObj->numPlayers;
		// if GamedataController sets $addToDB as true, add to db
		if($addToDB){
			$this->addToDB();
		}
	}

	public function jsonToState($jsonObj){
		$this->player1 = new Player($jsonObj->allPlayers[0]);
		$this->player2 = new Player($jsonObj->allPlayers[1]);
	}

	public function addToDB(){
		if($this->gameOver){
			$gameO = 1;
		}else{
			$gameO = 0;
		}

		if($this->roundOver){
			$roundO = 1;
		}else{
			$roundO = 0;
		}

		$array = array(
			'tableName'=>"tblStates",
			'fldPlayer1Snapshot'=>$jsonObj->allPlayers[0],
			'fldPlayer2Snapshot'=>$jsonObj->allPlayers[1],
			'fkGameID'=>$this->gameID,
			'fldGameOver'=>$gameO,
			'fldRoundOver'=>$roundO,
			'fldRoundCount'=>$this->roundCount
		);
		$dbWrapper = new InteractDB('insert', $array);
	}
}
?>