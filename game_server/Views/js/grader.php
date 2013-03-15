<?php

?>

<script type="text/javascript">
$(document).ready(function(){
	$('.classLink').hover(function(e){
		var position = $(this).offset();
		$(this).find('.grader').css({top:position.top-90, left:position.left-10}).toggle();
		$(this).mouseup(function(){
			$$(this).find('.grader').hide();
		});
		$(this).find('.grader').mouseup(function(){
			$(this).find('.grader').hide();
		});
	});

	$('.grader').click(function(){
		// alert('working');
		// alert($(this).parent().parent().find('a').html());
		var assignName = $(this).parent().parent().find('a').html();
		$('.workspace').load('http://localhost/classcontroller/Grading/?assignment='+assignName);
	});


});

</script>