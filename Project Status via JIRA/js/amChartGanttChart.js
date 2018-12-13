
function amGanttChart(themeType){var chart = AmCharts.makeChart( "chartdiv", {  "type": "gantt",  "theme": themeType,  "marginRight": 70,  "period": "DD",  "dataDateFormat": "YYYY-MM-DD",  "columnWidth": 0.5,  "valueAxis": {    "type": "date"  },  "brightnessStep": 7,  "graph": {    "fillAlphas": 1,    "lineAlpha": 1,    "lineColor": "#fff",    "fillAlphas": 0.85,    "balloonText": "<b>[[task]]</b>:<br />[[open]] -- [[value]]"},  "rotate": true,  "categoryField": "category",  "segmentsField": "segments",  "colorField": "color",  "startDateField": "start",  "endDateField": "end",  "dataProvider": [   
{"category": "RATPMIG-359",    "segments": [ 
{      "start": "2017-01-08",      "end": "2017-01-09",	  "color": "#00ff00",      "task": "Logged Days Good"    }
,
{      "start": "2017-02-08",      "end": "2017-02-09",	  "color": "#00ff00",      "task": "Logged Days Good"    }
,
{      "start": "2017-03-08",      "end": "2017-03-09",	  "color": "#00ff00",      "task": "Logged Days Good"    }
]},
{"category": "RATPMIG-420",    "segments": [ 
{      "start": "2017-02-08",      "end": "2017-02-09",	  "color": "#00ff00",      "task": "Logged Days Good"    }
,
{      "start": "2017-03-08",      "end": "2017-03-09",	  "color": "#00ff00",      "task": "Logged Days Good"    }
,
{      "start": "2017-04-08",      "end": "2017-04-09",	  "color": "#00ff00",      "task": "Logged Days Good"    }
,
{      "start": "2017-07-08",      "end": "2017-07-09",	  "color": "#00ff00",      "task": "Logged Days Good"    }
]},
{"category": "RATPMIG-422",    "segments": [ 
{      "start": "2017-01-08",      "end": "2017-01-09",	  "color": "#00ff00",      "task": "Logged Days Good"    }
,
{      "start": "2017-02-08",      "end": "2017-02-09",	  "color": "#00ff00",      "task": "Logged Days Good"    }
]},
{"category": "RATPMIG-426",    "segments": [ 
{      "start": "2017-01-08",      "end": "2017-01-09",	  "color": "#00ff00",      "task": "Logged Days Good"    }
]},
{"category": "RATPMIG-431",    "segments": [ 
{      "start": "2017-01-08",      "end": "2017-01-09",	  "color": "#00ff00",      "task": "Logged Days Good"    }
,
{      "start": "2017-02-08",      "end": "2017-02-09",	  "color": "#00ff00",      "task": "Logged Days Good"    }