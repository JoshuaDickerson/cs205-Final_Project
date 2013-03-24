<?php
class Player{
	public $playerName;
	public $isHuman; 
	public $id;
	public $score;
	public $hand;

	function __construct($jsonPlayer){
		$handJson = $jsonPlayer->myHand->hand;
		$this->playerName = $jsonPlayer->playername;
		$this->isHuman = $jsonPlayer->isHuman;
		$this->score = $jsonPlayer->score;
		$numCards = count($handJson);
		for($ii=0; $ii<$numCards; $ii++){
			$this->hand[$ii] = array(
				'rank'=>$handJson[$ii]->rank,
				'faceup'=>$handJson[$ii]->faceup,
				'isSpecial'=>$handJson[$ii]->isSpecial
			);
		}
	}// end construct
} // end Player class def
?>