 $(function() {
    $( "#from" ).datepicker({
      defaultDate: "+1w",
      changeMonth: true,
      changeYear: true,
      dateFormat: 'dd/mm/yy',
      maxDate:0,
      minDate:new Date(2006,3,21),
      numberOfMonths: 2,
      onClose: function( selectedDate ) {
    	  var minDate = $(this).datepicker('getDate');
    	    var newMin = new Date(minDate.setDate(minDate.getDate() + 1));
    	    $( "#to" ).datepicker( "option", "minDate", newMin );
      }
    });
    $( "#to" ).datepicker({
      defaultDate: "+1w",
      changeMonth: true,
      changeYear: true,
      dateFormat: 'dd/mm/yy',
      numberOfMonths: 2,
      maxDate:0,
      minDate:new Date(2006,3,22),
      onClose: function( selectedDate ) {
    	  var maxDate = $(this).datepicker('getDate');
    	    var newMax  = new Date(maxDate.setDate(maxDate.getDate() - 1));
    	    $( "#from" ).datepicker( "option", "maxDate",  newMax);
      }
    });
  });