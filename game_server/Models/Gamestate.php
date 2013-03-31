<?php
/**
* @author JoshuaDickerson joshuajdickerson@gmail.com
* the GameState is constructed from json and mirrors the 
* java gameState  
**/
require_once "Models/Player.php";
require_once "Models/InteractDB.php";

class Gamestate{
	private $gameID;
	private $status;
	private $winCon;
	private $player1;
	private $player2;
	private $mode;
	private $numPlayers;
	private $roundCount = -1;
	private $gameOver= false;
	private $roundOver = false;
	private $dbError;

	function __construct($jsonObj, $addToDB = false){
		$this->jsonToState($jsonObj);
		$this->gameID = $jsonObj->uniqueID;
		$this->gameOver = $jsonObj->gameOver;
		if($this->gameOver){
			// logThis("game over *******************");
			// $this->addFinalScores();
		}
		$this->roundOver = $jsonObj->roundOver;
		if($jsonObj->roundCount != ""){
			$this->roundCount = $jsonObj->roundCount;
		}
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
			'fldPlayer1Snapshot'=>json_encode($this->player1),
			'fldPlayer2Snapshot'=>json_encode($this->player2),
			'fkGameID'=>$this->gameID,
			'fldGameOver'=>$gameO,
			'fldRoundOver'=>$roundO,
			'fldRoundCount'=>$this->roundCount
		);
		$dbWrapper = new InteractDB('insert', $array);
	} // end addToDB()

	public function addFinalScores(){
		$micro = microtime(true);
		$timestamp = date("YmdHis").($micro*100);
		$players[0]['id'] = $this->player1->getID();
		$players[1]['id'] = $this->player2->getID();
		$players[0]['score'] = $this->player1->getScore();
		$players[1]['score'] = $this->player2->getScore();
		for($ii=0; $ii<2; $ii++){
			$array = array(
				'tableName'=>'tblScores',
				'fkUserID'=>$players[$ii]['id'],
				'fldScore'=>$players[$ii]['score'],
				'fldTimestamp'=>$timestamp
			);
			$dbWrapper = new InteractDB('insert', $array);
			// logThis($dbWrapper);
		}
	} // end addFinalScores
} // end Gamestate class def
?>