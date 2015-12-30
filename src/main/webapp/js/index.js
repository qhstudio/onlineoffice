/**
 * 
 */
var $loginBox = $("#login-box");


function showLoginBox() {
	var BrowerWidth = $(document).width();
	$loginBox.css("left",(BrowerWidth/2-200)>0?(BrowerWidth/2-200):0);
	$loginBox.show("fast");
}

function closeNode(mythis){
	$(mythis).parent().parent().parent().hide("fast");
}

