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
				<div class="title">Rat-A-Tat-Cat</div>
				<div class="welcome">Logged in as:  <span class="username_string"></span></div>
			</div>
		</div>
		<div class="row-fluid row2">
			<div class="span3 leftBar">
				<ul>
					<li><a class="leftbarLink newGameLink css3button">New Game</a></li>
					<li><a href="<? echo HOSTNAME.BASEDIR; ?>Statistics/?data=totalUserScore" class=" css3button leftbarLink leaderBoardLink">Leaderboard</a></li>
					<li><a href="<? echo HOSTNAME.BASEDIR; ?>User/?logout=true" class=" css3button leftbarLink leaderBoardLink">logout</a></li>
				</ul>
			</div>
			<div class="span9 rightNest">
				<APPLET width="640" height="480" archive="<? echo HOSTNAME.BASEDIR; ?>Views/test7.jar, <? echo HOSTNAME.BASEDIR; ?>Views/gson-2.2.2.jar, <? echo HOSTNAME.BASEDIR; ?>Views/httpclient-4.2.3.jar, <? echo HOSTNAME.BASEDIR; ?>Views/httpcore-4.2.3.jar, <? echo HOSTNAME.BASEDIR; ?>Views/joda-time-2.2.jar, <? echo HOSTNAME.BASEDIR; ?>Views/commons-net-3.2.jar" code="finalProject.class"></APPLET>
			</div>
		</div>
	</div>
</body>
