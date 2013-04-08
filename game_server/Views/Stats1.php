<?php
	// print_r($this->vars);
	$gpd = "<option id='gpd'>Games Per Day</option>";
	$hs = "<option id='hs'>High Scores</option>";
?>
<script type="text/javascript" src=<?= "\"".BASEDIR."Views/js/amcharts/amcharts.js"."\""; ?>></script>
<script type="text/javascript">
<?
	if($this->vars['graphType'] == "line"){
		echo "var chartData = ".json_encode($this->vars['graphData']).";";
	    $chartJS = file_get_contents("Views/js/lineChart.js");
	    echo $chartJS;
	}else if($this->vars['graphType'] == "column"){
		echo "var chartData = ".json_encode($this->vars['graphData']).";";
		$chartJS = file_get_contents("Views/js/3dColumnStack.js");
		echo $chartJS;
	}
?>
    $(document).ready(function(){
    	$('#chartOptions').change(function(){
    		var val = $('#chartOptions option:selected').text();
    		if(val == "High Scores"){
    			window.location.replace("http://slimdowndesign.com/cs205-Final_Project/game_server/Statistics/?data=totalUserScore");
    		}else if(val == "Games Per Day"){
    			window.location.replace("http://slimdowndesign.com/cs205-Final_Project/game_server/Statistics/?data=gamesOverTime");
    		}
    	});
    });
    </script>

    <body>
    	<ul class="datasets">
    		<li>
    			<ul>
    				<li>
    					<div class="title">Rat-A-Tat-Cat</div>
    				<li style="margin-top:30px;">
		    			<a class="css3button" style="width:200px" href="http://slimdowndesign.com/cs205-Final_Project/game_server/User/?home=1">
		    				Back To Game
		    			</a>
		    		</li>
		    	</ul>
    		</li>
    		<li class="dataControl">
`				<ul>
					<li>
						Choose a data set to look at
					</li>
					<li>
						<select id="chartOptions">
							<? 
								if($_GET['data'] == "gamesOverTime"){
									echo $gpd.$hs;
								}else if($_GET['data'] == "totalUserScore"){
									echo $hs.$gpd;
								}else{
									echo $hs.$gpd;
								}
							?>
							<option></option>
							<option></option>
							<option></option>
						</select>
					</li>
				</ul>
    		</li>
    		<li class="dataItem">
    			<ul>
    				<li class="chartInfo">
    				</li>
    				<li>
				        <div id="chartdiv"></div>
				    </li>
				</ul>
			</li>
		</ul>
    </body>
</html>

