$('td').filter(function() {
    return $(this).text().indexOf('Sentimento:positivo') >-1;
}).closest('tr').addClass('positivo');

$('td').filter(function() {
    return $(this).text().indexOf('Sentimento:negativo') >-1;
}).closest('tr').addClass('negativo');