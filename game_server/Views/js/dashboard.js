
$(document).ready(function(){

	$('#login').click(function(){
		$('#loginContainer').show();
	})

	$('#grading').click(function(){
		$('.workspace').load('../Grading/?assignment=12345');
	});

	// $('#newCriteria').click(function(){
	// 	$('.workspace').load('../Dashboard/?build=criteria');
	// });

	// $('.workspace').pjax('#newCriteria[data-pjax]');

	// $('#newClass').click(function(){
	// 	$('.workspace').load('../Class/?new=class&load=oldClasses');
	// });

	$('#loadClass').click(function(){
		$('.workspace').load('../Class/?load=11748');
	});



});

