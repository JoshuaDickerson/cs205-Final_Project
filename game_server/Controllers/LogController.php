<?php

class LogController{
	function __construct(){
		// echo "working";
		$logdata = file_get_contents("Configuration/log.txt");
		echo $logdata;
	}

	public function getView(){
		return false;
	}

	public function getVars(){
		return 12;
	}
}// end LogController class def

?>