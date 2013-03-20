<?php

class LogController{
	function __construct(){
		// echo "working";
		$logdata = file_get_contents("Configuration/log.txt");
		echo $logdata;
	}
}// end LogController class def

?>