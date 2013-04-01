<?php

class LogController{
	public $vars = array();
	public $view = "Log";
	function __construct(){
		// echo "working";
		$logdata = file_get_contents("Configuration/log.txt");
		$this->vars['logData'] = $logdata;
	}

	public function getView(){
		return $this->view;
	}

	public function getVars(){
		return $this->vars;
	}
}// end LogController class def

?>