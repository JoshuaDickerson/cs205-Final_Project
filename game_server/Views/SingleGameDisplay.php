<?php
// var_dump($this->vars);
echo "<div style='margin-left:20px'>";
echo "<br /><br />";
echo "<b>gameID:</b> ".$this->vars['gameObj']->gameID."<br />";
echo "<b>Game Begin:</b> ".$this->vars['gameObj']->gameStartTime."<br />";
echo "<b>User1:</b> ".$this->vars['gameObj']->p1Email."<br />";
echo "<b>User2:</b> ".$this->vars['gameObj']->p2Email."<br />";
echo "<b>Mode:</b> ".$this->vars['gameObj']->mode."<br />";
echo "<b>Win Condition:</b> ".$this->vars['gameObj']->winCon."<br />";
echo "<b>Total # States:</b> ".$this->vars['gameObj']->totalNumStates."<br /><br />";
echo "<h3>Game States</h3>";
for($ii=0; $ii<count($this->vars['gameObj']->states); $ii++){
	echo "----------<br />";
	echo "State ID: ".$this->vars['gameObj']->states[$ii]['stateID']."<br />";
	echo "p1Snapshot: ".$this->vars['gameObj']->states[$ii]['p1Snapshot']."<br />";
	echo "p2Snapshot: ".$this->vars['gameObj']->states[$ii]['p2Snapshot']."<br />";
	echo "Game Over: ".$this->vars['gameObj']->states[$ii]['gameOver']."<br />";
	echo "Round Over: ".$this->vars['gameObj']->states[$ii]['roundOver']."<br />";
	echo "Round Count: ".$this->vars['gameObj']->states[$ii]['roundCount']."<br />";
	echo "----------<br />";
}
echo "</div><br />";
?>