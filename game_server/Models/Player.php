<?php
class Player{
	public $playerName;
	public $isHuman; 
	public $id;
	public $score;
	public $hand;
	public $totalScore;
	public $roundsWon;

	function __construct($jsonPlayer){
		$handJson = $jsonPlayer->myHand->hand;
		$this->playerName = $jsonPlayer->playername;
		$this->id = $this->emailToUserID($this->playerName);
		$this->isHuman = $jsonPlayer->isHuman;
		$this->score = $jsonPlayer->score;
		$this->totalScore = $jsonPlayer->totalScore;
		$numCards = count($handJson);
		for($ii=0; $ii<$numCards; $ii++){
			$this->hand[$ii] = array(
				'rank'=>$handJson[$ii]->rank,
				'faceup'=>$handJson[$ii]->faceup,
				'isSpecial'=>$handJson[$ii]->isSpecial
			);
		}
	}// end construct

	public function getScore(){
		return $this->score;
	}

	public function getID(){
		return $this->id;
	}

	public function emailToUserID(){
		$array = array(
			'tableName'=>'tblUserAccount',
			'fldEmail'=>$this->playerName
		);
		$dbWrapper=new InteractDB('select', $array);
		if(count($dbWrapper->returnedRows) > 0){
			$userID = $dbWrapper->returnedRows[0]['pkUserID'];
			// $userID = 888;
		}else{
			$userID = 666;
		}

		return $userID;
	} // end emailToUserID()
} // end Player class def
?>