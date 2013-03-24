<?php
//$routerObj = new Router($_SERVER);
// var_dump($routerObj);
 class Router{
 	private $requestURL;
 	private $userAgent;
 	private $clientIP;
 	private $controller = false;
 	private $model = "defailt model requested";
 	private $action = array();


 	function __construct($serverRequest){
 		$this->clientIP	= $serverRequest['REMOTE_ADDR'];
 		$this->userAgent = $serverRequest['HTTP_USER_AGENT'];
 		$this->requestURL = $serverRequest['REQUEST_URI'];
 		$this->parseURL($serverRequest['REQUEST_URI']);
 		// var_dump($serverRequest['REQUEST_URI']);
 	}

 	function parseURL($requestURL){
 		$requestArr = parse_url($requestURL);// takes the URL and parses it into an array indexed by (scheme, host, user, password, path, query) 		
 		$urlPath = explode("/", $requestArr['path']); //  this is for parsing urls like /users/ tells us which class we need
		if (isset($requestArr['query'])){
			$urlQuery = explode("&", $requestArr['query']); // this is for parsing urls like ?action=logout
			$numQuery = count($urlQuery);
		}
		$actionQueried = array();

		//iterate through our query, assigning actions to the action array
		if (isset($urlQuery)){
			foreach($urlQuery as $value){
				$splode = explode("=", $value);
				$actionQueried[$splode[0]] = $splode[1];
			}
		}

		// logThis($requestArr['path']);


		// // the following trims the leading and following "/" off of the path
		$dirTrim = str_replace(BASEDIR ,"",$requestArr['path']);
		$dirTrim = substr_replace($dirTrim ,"",-1);
		// $dirTrim = substr_replace($dirTrim ,"",0,1);
		// $basedirArr = explode("/", BASEDIR);
		// logThis($basedirArr);
		// logThis($urlPath);

		// for($ii=0; $ii<count($basedirArr); $ii++){
		// 	// $index = array_search($basedirArr[$ii], $urlPath);
		// 	// if($index){
		// 	// 	unset($urlPath[$index]);
		// 	// }
		// 	if($basedirArr[$ii] == "" || $basedirArr[$ii] == " "){
		// 		unset($basedirArr[$ii]);
		// 	}
		// }

		// // for($ii=0; $ii<count($urlPath); $ii++){
		// // 	if($urlPath[$ii] == "" || $urlPath[$ii] == " "){
		// // 		unset($urlPath[$ii]);
		// // 	}
		// // }

		
		// $dirTrim = implode("/", $basedirArr);
		// $dirTrim = substr_replace($dirTrim ,"",-1);
		// logThis($dirTrim);
		// logThis($urlPath);

		// all our model file names are like User.php. 
		// this takes the model query, lowecases the whole thing, then uppercases the first letter.
		// always resulting in the appropriate model name format.
		// if(isset($urlPath[1+array_search($dirTrim, $urlPath)])){
		// 	$ControllerQuery = $urlPath[1+array_search($dirTrim, $urlPath)];
		// 	$this->controller = ucfirst(strtolower($ControllerQuery));
		// }

		if(isset($urlPath[1+array_search($dirTrim, $urlPath)])){
			$ControllerQuery = $dirTrim;
			$this->controller = ucfirst(strtolower($ControllerQuery));
		}
				
		$this->action = $actionQueried;
	} // end parseURL


 	public function getDeviceType(){
 		$userAgent = $this->userAgent;
 	// this function tells us whether its a phone or not
 	// not sure if this should be in the router or the display
 		if(stristr($userAgent, 'mobile') || stristr($userAgent, 'android') || stristr($userAgent, 'iphone') || stristr($userAgent, 'ipod')){
 			return 2; // 2 is for phone
 		}
 		else {
 			return 1; // 1 is for desktop
 		} 
 	}


 	/* getters and setters */
 	public function getController(){
 		if ($this->controller){
			return $this->controller;
		}else{return false;}
	} // end getController

 	public function getActions(){
 		if(isset($this->action)){
 			return $this->action;
 		}else{return false;} 
 	} // end getActions

 	public function getUserAgent(){
 		return $this->userAgent;
 	}

 	public function getUserIP(){
 		return $this->clientIP;
 	}

 	public function getRequestURL(){
 		return $this->requestURL;
 	}


}

?>