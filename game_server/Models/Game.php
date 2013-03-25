<?php
/**
* The Game object takes a game ID and builds a model of that game, including a stack
* of Gamestate objects
*/
class Game{
	private $gameID;
	private $uid_p1;
	private $uid_p2;
	private $mode;
	private $winCon;
	
	function __construct($gameIDstring){
		$this->gameID = $gameIDstring;
	} // end construct

	public function buildGameFromID(){
		// get game data from db
		// load each state 
	}
} // end class def