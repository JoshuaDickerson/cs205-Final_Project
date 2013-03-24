<?php
/**
* @author JoshuaDickerson joshuajdickerson@gmail.com
* the GameState is constructed from json and mirrors the 
* java gameState  
**/
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

	function __construct($jsonObj){
		$this->jsonToState($jsonObj);
		$this->uniqueID = $jsonObj->uniqueID;
		$this->winCon = $jsonObj->state->winCon;
		$this->mode = $jsonObj->state->mode;
		$this->numPlayers = $jsonObj->numPlayers;
		// add to db
		$this->addToDB();
	}

	public function jsonToState($jsonObj){
		$this->player1 = new Player($jsonObj->allPlayers[0]);
		$this->player2 = new Player($jsonObj->allPlayers[1]);
	}

	public function addToDB(){
		$array = array(
			'tableName'=>"tblStates",
			'fldPlayer1Snapshot'=>$jsonObj->allPlayers[0],
			'fldPlayer2Snapshot'=>$jsonObj->allPlayers[1],
			'fkGameID'=>$this->gameID
		);
		$dbWrapper = new InteractDB('insert', $array);
	}
}
?>