<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/WEB-INF/content/public/commons.jspf"%>
</head>
<body>
	<%@ include file="/WEB-INF/content/public/head.jspf"%>
	<div class="container content-box">
		<div class="panel">
			<div class="panel-heading">
				<ol class="breadcrumb">
					<li><a href="main/index">首页</a></li>
					<li class="active">所有分类</li>
				</ol>
				<table class="table doc-table table-condensed" id="sort-list">
				</table>
			</div>
			<div class="panel-body">
				<ul class="list-group doc-list" id="doc-list-ul">

				</ul>
				<p id="get-more-btn-p">
					<button onclick="getMoreResult()" id="get-more-btn">查看更多</button>
				</p>
			</div>
		</div>
	</div>
	<input type="hidden" value="${param.searchId}" id="searchId">
	<%@ include file="/WEB-INF/content/public/footer.jspf"%>
</body>
<script src="js/index.js" type="text/javascript" charset="utf-8"></script>
<script>
	var searchId = $("#searchId").val();
	
	var nowPage = 0;
	
	if(searchId == null || searchId.trim()==""){
		searchId = 0;
	}
	
	function getMoreResult() {
		docTypeId = searchId;
		$.post("main/recentListInfoByType",
						{
							pageNum : nowPage,
							docTypeId : docTypeId
						},
						function(data) {
							
							if (data.docs.length < 10) {
								$("#get-more-btn").html("");
							}else{
								$("#get-more-btn").html("查看更多");
							}
							
							for (var i = 0; i < data.docs.length; i++) {
								var str = "<li class=" + "'list-group-item'" + "><a href="
										+ "'main/doc-result.action?docId="
										+ data.docs[i].docId
										+ "'"
										+ "><img src=" + "'img/icon-"+ data.docs[i].docFoot+".png'" +"> "
										+ data.docs[i].docName
										+ "</a><p>上传者:"
										+ data.docs[i].docOwnUser.userName
										+ "&nbsp;&nbsp;&nbsp;上传时间："
										+ data.docs[i].docDate
										+ " </p><p>"
										+ data.docs[i].docDesc + "</p></li>";
								$("#doc-list-ul").append(str);
							}
						});
		nowPage++;
	}

	var $now = null;
	var newNode = false;
	
	$("#left-list-nav").find("a").eq(5).addClass("active");
	$
			.post(
					"admin/json-doc-types",
					function(data) {
						//alert(data.doc-types.length[0]);
						for (var i = 0; i < data.doctypes.length; i++) {
							var cstr = "";
							for (var j = 0; j < data.doctypes[i].childrenDocType.length; j++) {
								if (data.doctypes[i].childrenDocType[j].typeId == searchId) {
									cstr += "<button onclick='mybtnClick(this)' class='btn btn-primary btn-sm mybtn disabled'>"
											+ "<i class='type-id hidden-content'>"
											+ data.doctypes[i].childrenDocType[j].typeId
											+ "</i>"
											+ "<i class='type-desc hidden-content'>"
											+ data.doctypes[i].childrenDocType[j].typeDesc
											+ "</i>"
											+ "<b class='type-name'>"
											+ data.doctypes[i].childrenDocType[j].typeName
											+ "</b>" + "</button> ";
								} else {
									cstr += "<button onclick='mybtnClick(this)' class='btn btn-default btn-sm mybtn'>"
											+ "<i class='type-id hidden-content'>"
											+ data.doctypes[i].childrenDocType[j].typeId
											+ "</i>"
											+ "<i class='type-desc hidden-content'>"
											+ data.doctypes[i].childrenDocType[j].typeDesc
											+ "</i>"
											+ "<b class='type-name'>"
											+ data.doctypes[i].childrenDocType[j].typeName
											+ "</b>" + "</button> ";
								}

							}
							if (data.doctypes[i].typeId == searchId) {
								var str = "<tr><td>"
										+ "<button onclick='mybtnClick(this)' class='btn btn-primary btn-sm mybtn disabled' >"
										+ "<i class='type-id  hidden-content'>"
										+ data.doctypes[i].typeId
										+ "</i>"
										+ "<i class='type-desc hidden-content'>"
										+ data.doctypes[i].typeDesc + "</i>"
										+ "<b class='type-name'>"
										+ data.doctypes[i].typeName + "</b>"
										+ "</button>" + "</td>" + "<td>" + cstr
										+ "</td>"
							} else {
								var str = "<tr><td>"
										+ "<button onclick='mybtnClick(this)' class='btn btn-default btn-sm mybtn' >"
										+ "<i class='type-id  hidden-content'>"
										+ data.doctypes[i].typeId
										+ "</i>"
										+ "<i class='type-desc hidden-content'>"
										+ data.doctypes[i].typeDesc + "</i>"
										+ "<b class='type-name'>"
										+ data.doctypes[i].typeName + "</b>"
										+ "</button>" + "</td>" + "<td>" + cstr
										+ "</td>"
							}

							$("#sort-list").append(str);
							//$(str).insertBefore($("#sort-list"));
						}
						getMoreResult(searchId);
					});

	function mybtnClick(mythis) {
		$this = $(mythis);
		var r_id = $this.find(".type-id").eq(0).html();
		var r_name = $this.find(".type-name").eq(0).html();
		var r_desc = $this.find(".type-desc").eq(0).html();
		var r_p_id = $this.parent().parent().find(".type-id").eq(0).html();
		//alert(r_id+" "+r_name);
		searchId = r_id;
		$("#doc-list-ul").html("");
		nowPage = 0;
		$("#sort-list").find("button").removeClass("btn-primary").removeClass(
				"disabled").addClass("btn-default");
		$this.removeClass("btn-default").addClass("btn-primary").addClass(
				"disabled");
		getMoreResult();

	}
	
	 getMoreResult();
</script>
</html>