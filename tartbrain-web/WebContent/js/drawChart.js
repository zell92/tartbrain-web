// Load the Visualization API and the piechart package.
google.load("visualization", "1.1", {packages:['line', 'corechart',"gauge"]});




// Set a callback to run when the Google Visualization API is loaded.
google.setOnLoadCallback(drawChart);
google.setOnLoadCallback(drawChart2);
google.setOnLoadCallback(drawChart3);
google.setOnLoadCallback(drawChart5);
google.setOnLoadCallback(drawChart6);


// Callback that creates and populates a data table,
// instantiates the pie chart, passes in the data and
// draws it.
function drawChart() {

	// Create the data table.
	var data = new google.visualization.DataTable();

	data.addColumn('string', 'Sentimento');
	data.addColumn('number', 'Numero Tweet');

	data.addRows([
	              ['Positivo', window.positivi],
	              ['Negativo',window.negativi],
	              ['Neutro', window.neutri]

	              ]);


	var x='Analisi dei sentimenti \n  Keyword: \"'+window.keyword+'\"';
	if(window.nResults==0)
		x='Nessun risultato trovato per:\n  Keyword: \"'+window.keyword+'\"';

	if((window.user).length!=0)
		x=x+'\nUtente: \"'+window.user+'\"';

	if((window.dateStart).length!=0)
		x=x+'\nDal: \"'+window.dateStart+'\" Al: \"'+window.dateEnd+'\"';



	// Set chart options
	var options = {'title':x, 
			'width':600,
			'height':450,
			'colors':['#33CC00','#e0440e','#FF9900'],
			'is3D':true,};






	// Instantiate and draw our chart, passing in some options.
	var chart = new google.visualization.PieChart(document.getElementById('chart_div'));


	chart.draw(data, options);

}

function drawChart2() {
	var data = google.visualization.arrayToDataTable(
			window.datiChart
	);

	var options = {
			chart: {
				title: 'Analisi dei sentimenti',

			},

			colors:['#33CC00','#e0440e','#FF9900'],

			height: 350,
			wigth:200,

	};

	var chart = new google.charts.Line(document.getElementById('line_top_x'));
	if(nResults!=0)
		chart.draw(data, options);



}

function drawChart3() {

	var data = google.visualization.arrayToDataTable(
			window.datiChartTotali
	);




	var options = {
		
				title: 'Analisi dei tweet totali \nSentimento: '+ window.sentimento,

		
			hAxis: {
				title: 'Data',

				gridlines: {count: 10}
			},
			vAxis: {
				title: 'Numero di tweet'
			},
			height: 500,
	};


	var chart = new google.visualization.ColumnChart(
			document.getElementById('ex0'));
	if(nResults!=0)
		chart.draw(data, options);
}






// Callback that creates and populates a data table,
// instantiates the pie chart, passes in the data and
// draws it.

function drawChart5() {
	var data = google.visualization.arrayToDataTable(
			window.datiChartUser
	);

	var options = {

			chart: {
				title: 'Analisi dei sentimenti',

			},

			colors:['#33CC00','#e0440e','#FF9900'],

			height: 350,

	};

	var chart = new google.charts.Line(document.getElementById('line_top_xTot'));
	if(window.nResultsUser!=0)
		chart.draw(data, options);



}

function drawChart6() {

	var data = google.visualization.arrayToDataTable(
			window.datiChartTotaliUser
	);




	var options = {
		
				title: 'Analisi dei tweet totali',

	

			hAxis: {
				title: 'Data',

				gridlines: {count: 10}
			},
			vAxis: {
				title: 'Numero di tweet'
			},
			height: 500,
	};


	var chart = new google.visualization.ColumnChart(
			document.getElementById('ex0Tot'));
	if(window.nResultsUser!=0)
		chart.draw(data, options);
}


