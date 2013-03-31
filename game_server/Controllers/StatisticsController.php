<?php

class StatisticsController{
	private $POST;
	private $view = "Stats1"; 
	private $vars;
	
	function __construct($actions, $POST){
		$this->parseAction($actions);
	}

	public function parseAction($actions){
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
					case "data":
						require_once "Models/Stats.php";
					// we're adding new game data to the db
						if($actions['data'] == "gamesOverTime"){
							$statObj = new Stats();
							$this->vars['graphData'] = $statObj->gamesOverTime();
							$this->vars['graphType'] = "line";
						}else if($actions['data'] == "totalUserScore"){
							$statObj = new Stats();
							$this->vars['graphData'] = $statObj->totalUserScore();
							$this->vars['graphType'] = "column";
						}
					break;
					default:
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
} // end AnalyticsController class def 

?>