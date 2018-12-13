function amGanttChart(){
var chart = AmCharts.makeChart( "chartdiv", {
  "type": "gantt",
  "theme": "light",
  "marginRight": 70,
  "period": "DD",
  "dataDateFormat": "YYYY-MM-DD",
  "columnWidth": 0.5,
  "valueAxis": {
    "type": "date"
  },
  "brightnessStep": 7,
  "graph": {
    "fillAlphas": 1,
    "lineAlpha": 1,
    "lineColor": "#fff",
    "fillAlphas": 0.85,
    "balloonText": "<b>[[task]]</b>:<br />[[open]] -- [[value]]"
  },
  "rotate": true,
  "categoryField": "category",
  "segmentsField": "segments",
  "colorField": "color",
  "startDateField": "start",
  "endDateField": "end",
  "dataProvider": [ {
    "category": "JIRA TASK #1 \n In Work \n 05-09-17 to 09-09-17",
    "segments": [ {
      "start": "2017-09-05 12:00:00",
      "end": "2017-09-06 18:00:00",
	  "color": "#00ff00",
      "task": "Logged Days \n Good"
    }, {
      "start": "2017-09-06",
      "end": "2017-09-07",
	  "color": "#ffff00",
      "task": "Logged Days \n okay"
    }, {
      "start": "2017-09-07",
      "end": "2017-09-08",
	  "color": "#FF0000",
      "task": "Logged Days \n severe"
    }, {
      "start": "2017-09-08",
      "end": "2017-09-09",
	  "color": "#ffff00",
      "task": "Logged Days \n okay"
    }, {
      "start": "2017-09-09",
      "end": "2017-09-10",
	  "color": "#00ff00",
      "task": "Logged Days \n good"
    }]
  }, {
    "category": "JIRA TASK #2 \n Completed \n 06-09-17 to 08-09-17",
    "segments": [ {
      "start": "2017-09-06",
      "end": "2017-09-07",
	  "color": "#00ff00",
      "task": "Logged Days \n Good"
    }, {
      "start": "2017-09-07",
      "end": "2017-09-08",
	  "color": "#FF0000",
      "task": "Logged Days \n severe"
    }, {
      "start": "2017-09-08",
      "end": "2017-09-09",
	  "color": "#FF0000",
      "task": "Logged Days \n severe"
    }, {
      "start": "2017-09-09",
      "end": "2017-09-10",
	  "color": "#00ff00",
      "task": "Logged Days \n Good"
    } ]
  }, {
    "category": "JIRA TASK #3 \n In Work \n 07-09-17 to 10-09-17",
    "segments": [ {
      "start": "2017-09-07",
      "end": "2017-09-08",
	  "color": "#FF0000",
      "task": "Logged Days \n severe"
    }, {
      "start": "2017-09-08",
      "end": "2017-09-09",
	  "color": "#ffff00",
      "task": "Logged Days \n Okay"
    } , {
      "start": "2017-09-09",
      "end": "2017-09-10",
	  "color": "#ffff00",
      "task": "Logged Days \n Okay"
    } , {
      "start": "2017-09-10",
      "end": "2017-09-11",
	  "color": "#00ff00",
      "task": "Logged Days \n Good"
    } ]
  } ],
  "valueScrollbar": {
    "autoGridCount": true
  },
  "chartCursor": {
    "cursorColor": "#55bb76",
    "valueBalloonsEnabled": false,
    "cursorAlpha": 0,
    "valueLineAlpha": 0.5,
    "valueLineBalloonEnabled": true,
    "valueLineEnabled": true,
    "zoomable": false,
    "valueZoomable": true
  },
  "export": {
    "enabled": true
  }
} );
}