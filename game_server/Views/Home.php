<?php
if(isset($_SESSION['notifications'])){
	echo $_SESSION['notifications'];
}

require_once "Header.php";
?>

<div class="loginContainer">
	<div class="loginNest">
		<form action="<? echo BASEDIR; ?>User/?doLogin=yes" method="post">
			<ul>
				<li>Username</li>
				<li><input type="text" name="fldUsername"></li>
				<li>Password</li>
				<li><input type="password" name="fldPassword"></li>
				<li><input type="submit" value="submit" class="loginButton"></li>
			</ul>
		</form>
	</div>
</div>

<applet code="Smiley.class" width="300" height="200">
</applet>