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
	public $routerObj;
	private $testArray = array();

 	function __construct($actions = null, $POST = null, $routerObj = null){
		$this->POST = $POST;
	 	$this->actions = $actions;
	 	$this->routerObj = $routerObj;
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
								$this->testRouter();
							break;
							case "gamedata_controller":
							break;
							case "user_controller":
							break;
							case "routerObj":
								$this->testRouter();
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
			echo "DB username/password: OK<br />";
			echo "Connected to datastore: true";
		}

		echo "<br />&nbsp;&nbsp;-------------------<br /><b>Active database tables</b><br />";
		$tblArr = $dbWrapper->getTables();
		foreach($tblArr as $tbl){
			echo "&nbsp;&nbsp;&nbsp;&nbsp;----</br />";
			echo "&nbsp;&nbsp;&nbsp;&nbsp;Table name: ".$tbl['table_name']."<br />";
			echo "&nbsp;&nbsp;&nbsp;&nbsp;Table engine: " .$tbl['engine']."<br />";
			$numRow = $dbWrapper->getNumRows($tbl['table_name']);
		}
	}

	public function testRouter(){
		echo "<br /><b>------------------------------<br />";
		echo "Testing MVC routing object";
		echo "<br />----------</b><br />";
		// print_r($this->routerObj);
		if($this->routerObj->getDeviceType()==1){$type = "desktop";}else{$type = "mobile";}
		echo "Client IP Address: ".$this->routerObj->getUserIP()."<br />";
		echo "Client User Agent: ".$this->routerObj->getUserAgent()."<br />";
		echo "Client Device Type: ".$type."<br />";
		echo "HTTP RequestURL: ".$this->routerObj->getRequestURL()."<br />";
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
