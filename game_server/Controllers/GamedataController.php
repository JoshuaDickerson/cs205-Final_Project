<?php
/* @author JoshuaDickerson joshuajdickerson@gmail.com
* the GamedataController object is the controller fired when the game posts a gameState object
*/
class GamedataController{
	function __construct($POST){
		$jsonObj = json_decode($POST['gameState']);

		// we need to update our db tables
			// which one is the human? get human name. 
			if($jsonObj->allPlayers[0]->isHuman){
				$humanPlayer = $jsonObj->allPlayers[0]->playername;
			}else{
				$humanPlayer = $jsonObj->allPlayers[1]->playername;
			}

			// get the userID from database, using human name
			$array = array('tableName'=>'tblUserAccount', 'fldUsername'=>$humanPlayer);
			$dbWrapper = new InteractDB('select', $array);
	}
} // end class def

?>