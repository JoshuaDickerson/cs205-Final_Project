<?php
// var_dump($_SERVER);
?>
<script type="text/javascript">var chart;
var username = <? echo "'".$_SESSION['user']->getUserEmail()."'"; ?>;
var basedir = <? echo "'".HOSTNAME.BASEDIR."'"; ?>;
$(document).ready(function(){
// alert(basedir);
	$('.username_string').text(username);

	// $('.leaderBoardLink').click(function(){
	// 	// $('.rightNest').load("http://localhost/205_final_server/Statistics/");
	// });
});
</script>



<body>
	<div id="userHomeContainer">
		<div class="row-fluid row1">
			<div class="span12 topBar">
				<h2>Welcome back <span class="username_string"></span>!</h2>
			</div>
		</div>
		<div class="row-fluid row2">
			<div class="span3 leftBar">
				<ul>
					<li><a class="leftbarLink newGameLink css3button">Play a new game</a></li>
					<li><a href="<? echo HOSTNAME.BASEDIR; ?>Statistics/" class=" css3button leftbarLink leaderBoardLink">Check the leaderboard</a></li>
					<li><a href="<? echo HOSTNAME.BASEDIR; ?>User/?logout=true" class=" css3button leftbarLink leaderBoardLink">logout</a></li>
				</ul>
			</div>
			<div class="span9 rightNest">
			</div>
		</div>
	</div>
</body>