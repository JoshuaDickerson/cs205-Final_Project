<?php
// session_start();
require_once 'Models/User.php';
require_once "Configuration/log.php";
if(isset($_POST['gameState'])){
	logThis($_POST['gameState'], false);
}
session_start();
if(!isset($_SESSION['user'])){
	$user = new UserModel;
	$_SESSION['user'] = $user;
}
require 'Controllers/Controller.php';
