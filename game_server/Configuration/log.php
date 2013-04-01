<?php

function logThis($var, $lineNum = true, $logType = null){
	$toLog = print_r($var, true);
	$bt = debug_backtrace();
  	$caller = array_shift($bt);
  	$file = $caller['file'];
  	$line = $caller['line'];
  	if($lineNum){
		$toLog = $toLog."\n".$file.":".$line;
	}else{
		$toLog = $toLog."\n";
	}
	if($logType == 'dbError'){
		$fHandle = fopen('Configuration/db_error_log.txt', 'a+');
	}else{
		$fHandle = fopen('Configuration/log.txt', 'a+');
	}
	fwrite($fHandle, "<br />*****************************************<br />".date('Y/m/d-h:i:s').": ".$toLog."\n");
}


?>