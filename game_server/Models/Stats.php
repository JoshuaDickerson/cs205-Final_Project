<?php
// model to access statistical data for graphing with amcharts
class Stats{
	function __construct(){

	}

	public function gamesOverTime(){
		// we want to produce the data for a simple line graph
		// showing the number of games played over time

		// begin by getting all the games from the DB
		$array = array(
			'tableName'=>'tblGame'
		);
		$dbWrapper = new InteractDB('select', $array);

		$dataArr = array();
		$daysArr = array();
		$daysCount = array();
		for($ii=0; $ii<count($dbWrapper->returnedRows); $ii++){
			// split our date into a character array for parsing
			$dateArr = str_split($dbWrapper->returnedRows[$ii]['fldGameStarted']);
			// parse into years, months, days, hours, minutes
			$year = $dateArr[0].$dateArr[1].$dateArr[2].$dateArr[3];
			$month = $dateArr[4].$dateArr[5];
			$day = $dateArr[6].$dateArr[7];
			$hour = $dateArr[8].$dateArr[9];
			$minute = $dateArr[10].$dateArr[11];
			// build our data array
			$dataArr[$ii]['day'] = $day;
			$dataArr[$ii]['year'] = $year;
			$dataArr[$ii]['month'] = $month;
			$dataArr[$ii]['timestamp'] = $year.$month.$day;
			if(!in_array($day, $daysArr)){
				array_push($daysArr, $day);
			}
		}

		// logThis($daysArr);
		$outArr = array();
		for($ii=0; $ii<count($daysArr); $ii++){
			$counter = 0;
			for($zz=0; $zz<count($dataArr); $zz++){
				if($dataArr[$zz]['day'] == $daysArr[$ii]){
					$dateStamp = new DateTime($dataArr[$zz]['timestamp']);
					$daysCount[$ii]['date'] = $dateStamp->format("Y-m-d H:i:s");
					$daysCount[$ii]['dateStr'] = $daysCount[$ii]['date'];
					$counter++;
				}
			}
			// $daysCount[$ii]['date'] = "Mon Nov ".$daysArr[$ii]." 2013";
			
		$daysCount[$ii]['value'] = $counter;
		}
		// return json_encode($dbWrapper->returnedRows);
		// logThis($daysCount);
		return json_encode($daysCount);
	}

	public function totalUserScore(){
		$dbWrapper = new InteractDB('select', array('tableName'=>'tblScores'));
		return $dbWrapper->returnedRows;
	}
}

// Mon Nov 02 2009 00:00:00 GMT-0500 (EST)

?>