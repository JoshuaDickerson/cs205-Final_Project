<?php 
echo $this->vars['graphData'];
?>
    <body>
    	<input type="hidden" id="chartDat" value=<?echo "'".$this->vars['graphData']."'"; ?>>
        <div id="chartdiv" style="width:100%; height:400px; background:#efefef"></div>
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

