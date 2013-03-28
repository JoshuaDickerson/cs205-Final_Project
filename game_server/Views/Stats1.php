<?php
if($this->vars != "line"){
    $chartJS = file_get_contents("Views/js/areaChart.js");
    echo $chartJS;
}else{
    $chartJS = file_get_contents("Views/js/lineChart.js");
    echo $chartJS;
}

?>
    <body>
        <div id="chartdiv" style="width:100%; height:400px; background:#efefef"></div>
    </body>

</html>