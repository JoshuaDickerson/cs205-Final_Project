<?php

class AnalyticsController{
	function __construct($SERVER){
		$array = array(
			'tableName' => 'tblAnalytics',
			'fldIPAddress'=>$SERVER['REMOTE_ADDR'], 
			'fkUserID'=>'000'
		);

		$dbWrapper = new InteractDB('insert', $array);
		logthis($dbWrapper->error);
	}
} // end AnalyticsController class def 

?>