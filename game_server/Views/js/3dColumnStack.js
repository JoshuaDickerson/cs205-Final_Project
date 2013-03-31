<script type="text/javascript">
 var chart;

            var chartData = [{
                country: "USA",
                year2004: 3.5,
                year2005: 4.2
            }, {
                country: "UK",
                year2004: 1.7,
                year2005: 3.1
            }, {
                country: "Canada",
                year2004: 2.8,
                year2005: 2.9
            }, {
                country: "Japan",
                year2004: 2.6,
                year2005: 2.3
            }, {
                country: "France",
                year2004: 1.4,
                year2005: 2.1
            }, {
                country: "Brazil",
                year2004: 2.6,
                year2005: 4.9
            }, {
                country: "Russia",
                year2004: 6.4,
                year2005: 7.2
            }, {
                country: "India",
                year2004: 8.0,
                year2005: 7.1
            }, {
                country: "China",
                year2004: 9.9,
                year2005: 10.1
            }];


AmCharts.ready(function () {
                // SERIAL CHART
                chart = new AmCharts.AmSerialChart();
                chart.dataProvider = chartData;
                chart.categoryField = "country";
                chart.color = "#FFFFFF";
                chart.fontSize = 14;
                chart.startDuration = 1;
                chart.plotAreaFillAlphas = 0.2;
                // the following two lines makes chart 3D
                chart.angle = 30;
                chart.depth3D = 60;

                // AXES
                // category
                var categoryAxis = chart.categoryAxis;
                categoryAxis.gridAlpha = 0.2;
                categoryAxis.gridPosition = "start";
                categoryAxis.gridColor = "#FFFFFF";
                categoryAxis.axisColor = "#FFFFFF";
                categoryAxis.axisAlpha = 0.5;
                categoryAxis.dashLength = 5;

                // value
                var valueAxis = new AmCharts.ValueAxis();
                valueAxis.stackType = "3d"; // This line makes chart 3D stacked (columns are placed one behind another)
                valueAxis.gridAlpha = 0.2;
                valueAxis.gridColor = "#FFFFFF";
                valueAxis.axisColor = "#FFFFFF";
                valueAxis.axisAlpha = 0.5;
                valueAxis.dashLength = 5;
                valueAxis.title = "GDP growth rate"
                valueAxis.titleColor = "#FFFFFF";
                valueAxis.unit = "%";
                chart.addValueAxis(valueAxis);

                // GRAPHS         
                // first graph
                var graph1 = new AmCharts.AmGraph();
                graph1.title = "2004";
                graph1.valueField = "year2004";
                graph1.type = "column";
                graph1.lineAlpha = 0;
                graph1.lineColor = "#D2CB00";
                graph1.fillAlphas = 1;
                graph1.balloonText = "GDP grow in [[category]] (2004): [[value]]";
                chart.addGraph(graph1);

                // second graph
                var graph2 = new AmCharts.AmGraph();
                graph2.title = "2005";
                graph2.valueField = "year2005";
                graph2.type = "column";
                graph2.lineAlpha = 0;
                graph2.lineColor = "#BEDF66";
                graph2.fillAlphas = 1;
                graph2.balloonText = "GDP grow in [[category]] (2005): [[value]]";
                chart.addGraph(graph2);

                chart.write("chartdiv");
            });
        </script>