<?php
/**
* @author JoshuaDickerson joshuajdickerson@gmail.com
* the GamedataController object is the controller fired when the game posts a gameState object
*/
// include_once "Models/Gamestate.php";
class GamedataController{
	private $POST;
 	private $actions = array();
 	private $view;
 	public $modelObj;
 	private $testArray = array();
 	public $jsonObj;
	
	function __construct($actions, $POST, $debug = false){
		$this->jsonObj = json_decode($POST['gameState']);
		$this->parseAction($actions);
		// $this->addGame();
		// $this->addState();
	}

	function parseAction($actions){
		// takes the actions to be performed on the 
		// controller and perfomrs them if they exist
		$children = array_keys($actions);
		$methods = array_values($actions);

		if(count($children) != count($methods)){
			// if there are a different number of actions than variables
			// throw an error
			// please add my functionality
		}
		else{
			foreach($children as $value){
				// as long as there are an equal number of methods and variables
				// do --> for every action perform the switch statement
				switch ($value) {
					case "gamedata":
						require_once "Models/Game.php";
						$gameObj = new Game();
						$gameObj->buildGameFromJson($this->jsonObj);
						// $this->addGame();
						// $this->addState();
				    break;
				    default:
				       // echo "i is not equal to 0, 1 or 2";
				} // end switch
			} // end foreach
		} // end else

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
	} // end addGame()

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
	} // end emailToUserID()


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