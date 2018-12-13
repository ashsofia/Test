
function amTaskPerResourceChart(themeType){ var chart = AmCharts.makeChart("chartdivResources",{"type": "serial", "theme": themeType, "dataProvider": [
{   "name": "PALUCH, Michal",      "points":11,     "color": "#7F8DA9",     "bullet": "https://www.amcharts.com/lib/images/faces/A04.png"    }
,
{   "name": "EL OUADGHRIRI, Sami Hicham",      "points":6,     "color": "#7F8DA9",     "bullet": "https://www.amcharts.com/lib/images/faces/A04.png"    }
,
{   "name": "VONJISOA, Yoann",      "points":6,     "color": "#7F8DA9",     "bullet": "https://www.amcharts.com/lib/images/faces/A04.png"    }
,
{   "name": "EDORH TOSSA, Leon",      "points":3,     "color": "#7F8DA9",     "bullet": "https://www.amcharts.com/lib/images/faces/A04.png"    }
,
{   "name": "SULINSKI, Norbert",      "points":5,     "color": "#7F8DA9",     "bullet": "https://www.amcharts.com/lib/images/faces/A04.png"    }
,
{   "name": "HADJAB, Rachid",      "points":6,     "color": "#7F8DA9",     "bullet": "https://www.amcharts.com/lib/images/faces/A04.png"    }
,
{   "name": "RAMKUMAR, Matheswaran",      "points":14,     "color": "#7F8DA9",     "bullet": "https://www.amcharts.com/lib/images/faces/A04.png"    }
,
], "valueAxes": [{  "maximum": 25, "minimum": 0, "axisAlpha": 0,  "dashLength": 4, "position": "left" }],"startDuration": 1, "graphs": [{   "balloonText": "<span style='font-size:13px;'>[[category]]: <b>[[value]]</b></span>",  "bulletOffset": 10,   "bulletSize": 52,  "colorField": "color",  "cornerRadiusTop": 8, "customBulletField": "bullet",   "fillAlphas": 0.8,   "lineAlpha": 0, "type": "column", "valueField": "points"   }],"marginTop": 0,"marginRight": 0,"marginLeft": 0,"marginBottom": 0,    "autoMargins": false,    "categoryField": "name",    "categoryAxis": {        "axisAlpha": 0,        "gridAlpha": 0,        "inside": true,        "tickLength": 0    },    "export": {    	"enabled": true     }});}