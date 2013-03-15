$(document).ready(function(){
	$('.score').bind('keyup mouseup change', function(){
		// any time a scoring element is manipulated
		// update the score

		// get the score and weed out all the "bad" values
		// var score will be "no" if it is < 0 or > 100
		var score = calculateScore($(this).val());
		var val = 0;

		if(score == "no"){
			// if they entered a "bad" val let em know
			alert("you must enter a number between 0 and 100")
			$(this).val(0);
		}else{
			// otherwise update the paired input score
			$(this).parent().find('.inputScore').val(score);
		}

		$('.inputScore').each(function(){
			if($(this).val()==""){$(this).val(0);}
			val = parseInt($(this).val()) + val;
		})
		var topPosition = $(this).position();
		$('.calcScore').html(val);
		// $('.totalScore').css({'top': topPosition.top});
	}); // end .bind


	$('.studentNames').mouseup(function(){
		// slides the assignment scoring down.
		$('.scoringSlider').slideDown();
	});

	


// get the pointer coords so that we can show the settings tab
        $(document).mousemove(function(e){
                var mouseX = e.pageX;
                var mouseY = e.pageY;
                var windowW = $(document).width();
                // if our mouse is to the right, and our panel is hidden, show panel
                if((mouseX < 40) && (mouseY < 700 && mouseY > 120) && !$("#popup").hasClass("active")){
                        $("#popup").animate({left: '+=180'}, 70).addClass("active");
                }

                // if the panel is visible, hide it when we move our mouse to left
                if($("#popup").hasClass("active") && mouseX > 150){
                        $("#popup").animate({left: '-=180'}, 70).removeClass("active");
                }

        });

});


// this binds our click on the student names to a
// JQuery event handler
$(document).on("click", ".studentNames li", function(){
		$('.choose h2').html($(this).html());
		// set our form action
		$('#scoreForm').attr('action', 'http://slimdowndesign.com/classcontroller_1.2/Assignment/?gradeStudentAssignment=yes');

		// give our hidden inputs some values to pass along to the 'catcher'
		$("input[name='assignmentID']").val($(this).attr('assignmentID'));
		$("input[name='studentID']").val($(this).attr('userID'));

		// check if there's already been a graded assignment for this s	var assignmentID = $(this).attr('assignmentID');
		var courseID = $("input[name='courseID']").val();
		var studentID = $("input[name='studentID']").val();
		// var studentID = 3; 

		// alert(assignmentID);
		var query = 'http://slimdowndesign.com/classcontroller_1.2/Assignment/?getGradedAssignment='+assignmentID+'&courseID='+courseID+'&studentID='+studentID+'&output=json';
		// alert(query);
		$.getJSON(query, function(data2) {
			// console.log(data2);
			// we have to make sure that the data2 object was created properly before we make changes
			if(data2 != null && typeof data2['criteria'] != 'undefined' && data2['criteria'] != null && data2['criteria'].length > 0){
				var criteria = [];
				for(ii=0; ii<data2['criteria'].length; ii++){
					var critText = data2['criteria'][ii][0];
					var critScore = data2['criteria'][ii][1];
					// alert(data[ii]['fldCriteria']);
					criteria.push("<li><input type='text' class='criteriaInputText' name='criteria["+ii+"][]' size='60' value='"+critText+
							"'><input type='text' name='criteria["+ii+"][]' class='criteriaInputNumber score' maxlength='3' width='3' value='"+critScore+"'><input type='radio' name='criteria["+ii+"][]' value='1' class='score'>Yes"+
							"<input type='radio' name='crtieria["+ii+"][]' value='0' class='score'>No"+
							"<input type='hidden' class='inputScore'></li>");
				}
				// alert(data['studentNames'][0]);
				// for each student ID we need to get the student names
				$('.scoring').html(criteria);
				
			}else{
				var query = 'http://slimdowndesign.com/classcontroller_1.2/Assignment/?loadAssignmentByID='+assignmentID+'&getGradingCriteria=true&output=json';
				var criteria = [];
				$.getJSON(query, function(data){
					console.log(data);
					for(ii=0; ii<data.length; ii++){
						// alert(data[ii]['fldCriteria']);
						criteria.push("<li><input type='text' class='criteriaInputText' name='criteria["+ii+"][]' size='60' value='"+data[ii]['fldCriteria']+
						"'><input type='text' name='criteria["+ii+"][]' class='criteriaInputNumber score' value='0' maxlength='3' width='3'><input type='radio' name='criteria["+ii+"][]' value='1' class='score'>Yes"+
						"<input type='radio' name='crtieria["+ii+"][]' value='0' class='score'>No"+
						"<input type='hidden' class='inputScore'></li>");
					}
					// alert(data['studentNames'][0]);
					// for each student ID we need to get the student names
					$('.scoring').html();
					$('.scoring').html(criteria);

				});
			}

			$('.scoringSlider').show();
			// alert(data['studentNames'][0]);
			// for each student ID we need to get the student names
		 });


});

// this binds our click on the number input for the criteria to a
// JQuery event handler
$(document).on("keyup", ".criteriaInputNumber", function(){
		var score = calculateScore($(this).val());
		var $criteriaTextInput = $(this).parent().find('.criteriaInputText');
		var placeholder = $(this).parent().find('.criteriaInputText').attr('placeholder');
		$(this).parent().find('.criteriaInputText').attr('value', placeholder);
		// alert(score);
		var val = 0;

		if(score == "no"){
			// if they entered a "bad" val let em know
			alert("you must enter a number between 0 and 100")
			$(this).val(0);
		}else{
			// otherwise update the paired input score
			$(this).parent().find('.inputScore').val(score);
		}

		$('.inputScore').each(function(){
			if($(this).val()==""){$(this).val(0);}
			val = parseInt($(this).val()) + val;
		})
		var topPosition = $(this).position();
		$('.calcScore').html(val);
		$("input[name='finalScore']").val(val);
});

// $(document).on("keyup", ".criteriaInputText", function(){
// 		var score = calculateScore($(this).val());
// 		// alert(score);
// 		var val = 0;

// 		if(score == "no"){
// 			// if they entered a "bad" val let em know
// 			alert("you must enter a number between 0 and 100")
// 			$(this).val(0);
// 		}else{
// 			// otherwise update the paired input score
// 			$(this).parent().find('.inputScore').val(score);
// 		}

// 		$('.inputScore').each(function(){
// 			if($(this).val()==""){$(this).val(0);}
// 			val = parseInt($(this).val()) + val;
// 		})
// 		var topPosition = $(this).position();
// 		$('.calcScore').html(val);
// });


function calculateScore(score){
	// calculates score out of 100
	if(score == ""){
		score = 0;
	}
	
	if(score>100 || score<0){
		return "no";
	}else{
		return score;
	}
} // end calculateScore