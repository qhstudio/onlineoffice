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
		<div class="row">
			<div class="col-md-2 mycenter-left-nav">
				<%@ include file="/WEB-INF/content/public/left-list.jspf"%>
			</div>
			<div class="col-md-10">
				<div class="panel panel-default">
					<div class="panel-heading">分类管理</div>
					<div class="panel-body">
						<table class="table doc-table table-condensed">
							<tr>
								<th>类型</th>
								<th>子类型</th>
								<th>操作</th>
							</tr>
							<tr id="sort-list">
								<td><button onclick="mybtnClick(this)"
										class="btn btn-danger btn-sm mybtn">
										<i class="type-id  hidden-content"></i><i
											class='glyphicon glyphicon-arrow-down'></i> 增加
									</button></td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="myModal" class="modal fade bs-example-modal-sm" tabindex="-1"
		role="dialog" aria-labelledby="mySmallModalLabel">
		<div class="modal-dialog modal-sm">
			<div class="modal-content" id="modal-content">
				<div class="modal-body">
					<form id="my-form" action="admin/doc-type-submit" method="post">
						<input type="hidden" id="typeId" name="typeId"> <input
							type="hidden" id="p-typeId" name="parentType.typeId">
						<div class="form-group">
							<label for="inputName">类别名称</label> <input type="text"
								name="typeName" class="form-control" id="inputName"
								placeholder="类别名称">
						</div>
						<div class="form-group">
							<label for="inputName">类别描述</label> <input type="text"
								name="typeDesc" class="form-control" id="typeDesc"
								placeholder="类别描述">
						</div>
						<div class="form-group">
							<div class="col-sm-6">
								<button type="button" class="btn btn-primary btn-block"
									id="submit-btn">提交</button>
							</div>
							<div class="col-sm-6">
								<button type="button" class="btn btn-danger btn-block"
									id="del-btn">删除</button>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer"></div>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/content/public/footer.jspf"%>
</body>
<script type="text/javascript">
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
								cstr += "<button onclick='mybtnClick(this)' class='btn btn-success btn-sm mybtn'>"
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
							var str = "<tr><td>"
									+ "<button onclick='mybtnClick(this)' class='btn btn-primary btn-sm mybtn' >"
									+ "<i class='type-id  hidden-content'>"
									+ data.doctypes[i].typeId
									+ "</i>"
									+ "<i class='type-desc hidden-content'>"
									+ data.doctypes[i].typeDesc
									+ "</i>"
									+ "<b class='type-name'>"
									+ data.doctypes[i].typeName
									+ "</b>"
									+ "</button>"
									+ "</td>"
									+ "<td>"
									+ cstr
									+ "</td>"
									+ "<td><button onclick='mybtnClick(this)' class='btn btn-danger btn-sm  mybtn' ><i class='glyphicon glyphicon-arrow-left'></i> 增加</button></td></tr>";
							//$("#sort-list").append(str);
							$(str).insertBefore($("#sort-list"));
						}

					});

	function mybtnClick(mythis) {
		var $this = $(mythis);
		$now = $this;

		var r_id = $this.find(".type-id").eq(0).html();
		if (r_id == null || r_id.trim() == "") {
			newNode = true;
			$("#del-btn").attr("disabled", "true");
		} else {
			newNode = false;
			$("#del-btn").removeAttr("disabled");
		}
		var r_name = $this.find(".type-name").eq(0).html();
		var r_desc = $this.find(".type-desc").eq(0).html();
		var r_p_id = $this.parent().parent().find(".type-id").eq(0).html();

		$("#typeId").val("");
		$("#inputName").val("");
		$("#typeDesc").val("");
		$("#p-typeId").val("");

		$("#typeId").val(r_id);
		$("#inputName").val(r_name);
		$("#typeDesc").val(r_desc);
		$("#p-typeId").val(r_p_id);
		if (null == $("#p-typeId").val() || "" == $("#p-typeId").val().trim()) {
		}
		$('#myModal').modal('show')

	}
	$("#submit-btn")
			.click(
					function() {
						var str = $("#my-form").serializeArray();
						$
								.post(
										"admin/doc-type-submit",
										str,
										function(data) {
											data = data.doctype
											var str = "<i class='type-id hidden-content'>"
													+ data.typeId
													+ "</i><i class='type-desc hidden-content'>"
													+ data.typeDesc
													+ "</i><b class='type-name'>"
													+ data.typeName + "</b>";
											if (newNode == true) {
												//alert($now.parent().parent().html())

												if (null == $("#p-typeId")
														.val()
														|| "" == $("#p-typeId")
																.val().trim()) {
													//$now.parent().parent().parent().html()
													istr = "<tr><td>"
															+ "<button onclick='mybtnClick(this)' class='btn btn-primary btn-sm mybtn' >"
															+ "<i class='type-id  hidden-content'>"
															+ data.typeId
															+ "</i>"
															+ "<i class='type-desc hidden-content'>"
															+ data.typeDesc
															+ "</i>"
															+ "<b class='type-name'>"
															+ data.typeName
															+ "</b>"
															+ "</button>"
															+ "</td>"
															+ "<td>"
															+ "</td>"
															+ "<td><button onclick='mybtnClick(this)' class='btn btn-danger btn-sm  mybtn' ><i class='glyphicon glyphicon-arrow-left'></i> 增加</button></td></tr>";

													$(istr).insertBefore(
															$("#sort-list"));
												} else {
													$now
															.parent()
															.parent()
															.find("td")
															.eq(1)
															.append(
																	"<button onclick='mybtnClick(this)' class='btn btn-success btn-sm mybtn'>"
																			+ str
																			+ "</button> ");
												}
												newNode = false;
											} else {
												$now.html(str)
											}

										});

						$('#myModal').modal('hide');
					})

	$("#del-btn").click(function() {
		var str = $("#my-form").serializeArray();
		/*$.post("admin/delete-doc-type",str,function(data){
			if($("#typeId").val() == $("#p-typeId").val()){
				$now.parent().parent().remove();
			}else{
				$now.remove();
			}
			
			$('#myModal').modal('hide');
		})
		 */
		$.ajax({
			type : "POST",
			url : "admin/delete-doc-type",
			data : str,
			dataType : "json",
			success : function(data) {
				if ($("#typeId").val() == $("#p-typeId").val()) {
					$now.parent().parent().remove();
				} else {
					$now.remove();
				}

				$('#myModal').modal('hide');
			},
			error : function(data) {
				alert("删除失败，该类别中拥有文档!");
				$('#myModal').modal('hide');
			}
		});

	})
</script>
</html>