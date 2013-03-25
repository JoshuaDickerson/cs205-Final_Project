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
					// we're adding new game data to the db
						if($actions['gamedata'] == "newdata"){
							require_once "Models/Game.php";
							$gameObj = new Game();
							$gameObj->buildGameFromJson($this->jsonObj);
						}
					break;
					case "getGameByID":
					// we're building the game from the db
						if($actions['getGameByID'] != ""){
							require_once "Models/Game.php";
							$gameObj = new Game();
							$gameObj->buildGameFromID($actions['getGameByID']);
							$this->vars['gameObj'] = $gameObj;
							$this->view = "SingleGameDisplay";
						}
					break;
				    default:
				       // echo "i is not equal to 0, 1 or 2";
				} // end switch
			} // end foreach
		} // end else
	} // end parseAction()

	public function getView(){
		return $this->view;
	}

	public function getVars(){
		return $this->vars;
	}

} // end class def



// pkGameID
// fkUserID_p1
// fkUserID_p2
// fldMode
// fldWinCond

?>