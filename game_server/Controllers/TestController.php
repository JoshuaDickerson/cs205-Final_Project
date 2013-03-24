<?php
// require_once "Models/User.php";
// require_once "../Controllers/AbstractController.php";
//
class TestController{
	private $POST;
	private $actions = array();
	private $view = "TestSuite";
	public $modelObj;
	public $vars; 
	private $testArray = array();

 	function __construct($actions = null, $POST = null){
		$this->POST = $POST;
	 	$this->actions = $actions;
	 	$this->parseAction($this->actions);
	 	
	 }

	private function parseAction($actions){
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
					case "test":
						switch($actions['test']){
							case "dbConnect":
								$this->vars[0] = $this->testDB();
							break;
							case "gamedata_controller":
							break;
							case "user_controller":
							break;
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

	public function testDB(){
		echo "<b>Testing Database<br />";
		echo "-------------------------</b><br />";

		// test db here
		$dbWrapper = new InteractDB();
		$connErr = $dbWrapper->getError();
		if($connErr){
			echo "Connected to datastore failed: ".$connErr;
		}else{
			echo "Connected to datastore: true";
		}

		echo "<br />-------------------<br /><b>Active database tables</b><br />";
		$tblArr = $dbWrapper->getTables();
		foreach($tblArr as $tbl){
			echo "----</br />";
			echo "Table name: ".$tbl['table_name']."<br />";
			echo "Table engine: " .$tbl['engine']."<br />";
		}
	}

	public function buildUser(){
		$userModel = new User();
	}

	public function testUser(){

	}

	public function getVars(){
		return $this->vars;
	}

} // end User class


?>
