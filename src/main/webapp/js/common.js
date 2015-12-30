$("#left-list-nav").find("a").eq(3).addClass("active");
function changeDocAuthority(docId,mythis){
	var docau = $(mythis).val();
	$.post("doc/change-au",{"docId":docId,"docAuthority":docau});
}