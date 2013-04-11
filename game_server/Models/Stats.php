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

	public function usageOverTIme(){
		
		$query = "SELECT Count(*) as qty, month(fldCurrentTimestamp) ";
		$query .= "as month,day(fldCurrentTimestamp) as day,year(fldCurrentTimestamp) ";
		$query .= "as year FROM `tblGame` GROUP BY year(fldCurrentTimestamp), ";
		$query .= "month(fldCurrentTimestamp), day(fldCurrentTimestamp)";
		$dbWrapper = new InteractDB();
		$dbWrapper->customStatement($query);
		$rows = $dbWrapper->returnedRows;
		logThis($rows);
		$monthArr = array(
		31, 28, 31, 30,
			31, 30, 31, 31,
			30, 3130,31
		);
		$beginMonth = $rows[0]['month'];
		$beginDay = $rows[0]['day'];
		$endMonth = $rows[count($rows)-1]['month'];
		$endDay = $rows[count($rows)-1]['day'];
		$spanMonths = $endMonth - $beginMonth;
		$dateQty = array(); // hold our return values
		$counter = 0;
		// -------- we only have a single month of data
		if($spanMonths == 0){
			// only 1 month of data
			$numDays = $endDay-$beginDay;
			for($ii=$beginDay; $ii<=$endDay; $ii++){
				// for every day
				for($zz=0; $zz<count($rows); $zz++){
					if($ii == $rows[$zz]['day']){
						$dateQty[$counter]['day'] = $rows[$zz]['day'];
						$dateQty[$counter]['qty'] = $rows[$zz]['qty'];
						$dateQty[$counter]['month'] = $rows[$zz]['month'];
						$dateQty[$counter]['year'] = $rows[$zz]['year'];
						$dateQty[$counter]['date'] = $beginMonth."-".$rows[$zz]['day'];
						$zz = count($rows);
					}else{
						$dateQty[$counter]['day'] = $ii;
						$dateQty[$counter]['qty'] = 0;
						$dateQty[$counter]['month'] = $beginMonth;
						$dateQty[$counter]['year'] = $rows[$zz]['year'];
						$dateQty[$counter]['date'] = $beginMonth."-".$ii;
					}
				}
				$counter++;
			}
		}else{
		// ------- we have several months of data
			// loop over each month
			for($mm=$beginMonth; $mm<=$endMonth; $mm++){
				// logThis($mm);
				// logThis("begin day ".$beginDay);
				// logThis("end month ".$endMonth);
				// deal with the first month
				if($mm==$beginMonth){
					// logThis("in Begin Month");
					// for every day from the beginDay to the last day of the month
					logThis($monthArr[$mm-1]);
					for($ii=$beginDay; $ii<=$monthArr[$mm-1]; $ii++){
						// check all of our database rows
						for($zz=0; $zz<count($rows); $zz++){
							// if we have an entry for the month and day that matches
							if($ii == $rows[$zz]['day'] && $rows[$zz]['month'] == $mm){
								// set our date...
								$dateQty[$counter]['day'] = $rows[$zz]['day'];
								$dateQty[$counter]['qty'] = $rows[$zz]['qty'];
								$dateQty[$counter]['month'] = $rows[$zz]['month'];
								$dateQty[$counter]['year'] = $rows[$zz]['year'];
								$dateQty[$counter]['date'] = $mm."-".$rows[$zz]['day'];
								// ...and move on
								$zz = count($rows);
							}else{
								// otherwise set our qty for that day and month to 0
								$dateQty[$counter]['day'] = $ii;
								$dateQty[$counter]['qty'] = 0;
								$dateQty[$counter]['month'] = $mm;
								$dateQty[$counter]['year'] = $rows[$zz]['year'];
								$dateQty[$counter]['date'] = $mm."-".$ii;
							}
						}
						$counter++;
					}
				}else if($mm!=$endMonth){
					// our month increment is not the last month
					for($ii=0; $ii<=$monthArr[$mm-1]; $ii++){
						//  create a value for each day
						for($zz=0; $zz<count($rows); $zz++){
							if($ii == $rows[$zz]['day'] && $rows[$zz]['month'] == $mm){
								$dateQty[$counter]['day'] = $rows[$zz]['day'];
								$dateQty[$counter]['qty'] = $rows[$zz]['qty'];
								$dateQty[$counter]['month'] = $rows[$zz]['month'];
								$dateQty[$counter]['year'] = $rows[$zz]['year'];
								$dateQty[$counter]['date'] = $mm."-".$rows[$zz]['day'];
								$zz = count($rows);
							}else{
								$dateQty[$counter]['day'] = $ii;
								$dateQty[$counter]['qty'] = 0;
								$dateQty[$counter]['month'] = $mm;
								$dateQty[$counter]['year'] = $rows[$zz]['year'];
								$dateQty[$counter]['date'] = $mm."-".$ii;
							}
						}
						$counter++;
					}
				}else if ($mm==$endMonth){
					for($ii=0; $ii<=$endDay; $ii++){
						// for every day
						for($zz=0; $zz<count($rows); $zz++){
							if($ii == $rows[$zz]['day'] && $rows[$zz]['month'] == $mm){
								$dateQty[$counter]['day'] = $rows[$zz]['day'];
								$dateQty[$counter]['qty'] = $rows[$zz]['qty'];
								$dateQty[$counter]['month'] = $rows[$zz]['month'];
								$dateQty[$counter]['year'] = $rows[$zz]['year'];
								$dateQty[$counter]['date'] = $mm."-".$rows[$zz]['day'];
								$zz = count($rows);
							}else{
								$dateQty[$counter]['day'] = $ii;
								$dateQty[$counter]['qty'] = 0;
								$dateQty[$counter]['month'] = $mm;
								$dateQty[$counter]['year'] = $rows[$zz]['year'];
								$dateQty[$counter]['date'] = $mm."-".$ii;
							}
						}
						$counter++;
					}
				}
			}
		}

		return $dateQty;
	}

	public function totalUserScore(){
		$dbWrapper = new InteractDB('select', array('tableName'=>'tblScores'));
		$userIDArr = array();
		foreach($dbWrapper->returnedRows as $row){
			// get a unique array of user ID's
			if(!in_array($row['fkUserID'], $userIDArr)){
				array_push($userIDArr, $row['fkUserID']);
			}
		}
		// sort our unique IDS for presentation
		sort($userIDArr);
		// return $userIDArr;
		// foreach userID, sum their score
		for($ii=0; $ii<count($userIDArr); $ii++){
			$dbWrapper2 = new InteractDB();
			$dbWrapper2->customStatement("SELECT sum(fldScore) from tblScores where fkUserID = ".$userIDArr[$ii].";");
			$scores[$ii]['playerID'] = $userIDArr[$ii];
			foreach($dbWrapper2->returnedRows as $row){
				$scores[$ii]['value'] = $row[0];
			}
		}
		
		return $scores;
	}
}

// Mon Nov 02 2009 00:00:00 GMT-0500 (EST)

?>