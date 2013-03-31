<?php

function logThis($var, $lineNum = true){
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
	$fHandle = fopen('Configuration/log.txt', 'a+');
	fwrite($fHandle, "*****************************************\n".date('Y/m/d-h:i:s').": ".$toLog."\n");
}


?>