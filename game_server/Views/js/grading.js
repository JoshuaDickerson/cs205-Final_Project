$(document).ready(function(){

	$('.score').bind('keyup mouseup change', function(){
		var score = calculateScore($(this).val());
		
		if(score == "no"){
			alert("you must enter a number between 0 and 100")
			$(this).val(0);

		}else{
			$(this).parent().find('.inputScore').val(score);
		}

		var val = 0;
		$('.inputScore').each(function(){
			if($(this).val()==""){$(this).val(0);}
			val = parseInt($(this).val()) + val;
		})
		$('.calcScore').html(val);
	});

});


function calculateScore(score){
	if(score == ""){
		score = 0;
	}
	
	if(score>100 || score<0){
		return "no";
	}else{
		return score;
	}
}



