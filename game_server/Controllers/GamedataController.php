<?php
/* @author JoshuaDickerson joshuajdickerson@gmail.com
* the GamedataController object is the controller fired when the game posts a gameState object
*/
class GamedataController{
	function __construct($POST){
		$result = json_decode($POST['gameState']);
		if($result && $result != null){
			echo "json decoded";
		}
	}
} // end class def

?>