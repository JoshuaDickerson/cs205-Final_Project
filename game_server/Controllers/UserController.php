<?php

class UserController{
 	private $POST;
 	private $actions = array();
 	private $view;
 	public $modelObj;

 	function __construct($actions, $POST){
		$this->POST = $POST;
	 	$this->actions = $actions;
	 	$this->modelObj = $_SESSION['user'];
	 	$this->parseAction($this->actions);
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
					case "home":
					$this->view = 'userHome';
				    break;
				    case "doLogin":
					    $loginResult = $_SESSION['user']->login($this->POST['fldUsername'], $this->POST['fldPassword']);
					    if($loginResult){
					    	// if login worked redirect the user to his home page
					    	header('location: '.BASEDIR.'User/?home='.$_SESSION['user']->getUserID());
					    	// logThis("login succeeded");
					    	exit;
					    }else{
					    	// $_SESSION['notifications'] = "login Failed";
					    	header("location: ".BASEDIR);
					    	// logThis("login failed");
					    	exit;
					    }
				    break;
				    case "logOut":
				    	$_SESSION['user']->logout();
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

} // end User class


?>
