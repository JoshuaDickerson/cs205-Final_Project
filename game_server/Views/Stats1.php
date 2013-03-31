<?php 
?>
    <body>
    	<ul class="datasets">
    		<li class="dataControl">
`				<ul>
					<li>
						Choose a data set to look at
					</li>
					<li>
						<select>
							<option>Games Per Day</option>
							<option></option>
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
				    	<input type="hidden" id="chartDat" value=<?echo "'".$this->vars['graphData']."'"; ?>>
				        <div id="chartdiv"></div>
				    </li>
				</ul>
			</li>
		</ul>
    </body>
<?
// if($this->vars['graphType'] != "line"){
    // $chartJS = file_get_contents("Views/js/areaChart.js");
    // echo $chartJS;
// }else{
    $chartJS = file_get_contents("Views/js/lineChart.js");
    echo $chartJS;
// }

?>
</html>

