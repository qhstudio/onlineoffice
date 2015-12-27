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
						<table class="table doc-table table-condensed" id="sort-list">
							<tr>
								<th>类型</th>
								<th>子类型</th>
								<th>操作</th>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog"
		aria-labelledby="mySmallModalLabel">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">...</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/content/public/footer.jspf"%>
</body>
<script type="text/javascript">
	$("#left-list-nav").find("a").eq(5).addClass("active");
	$.post("admin/json-doc-types",
					function(data) {
						//alert(data.doc-types.length[0]);
						for (var i = 0; i < data.doctypes.length; i++) {
							var cstr = "";
							for (var j = 0; j < data.doctypes[i].childrenDocType.length; j++) {
								cstr += "<a class='btn btn-info btn-sm' data-toggle='modal' data-target='.bs-example-modal-sm'>"
										+"<i class='type-id hidden-content'>"+data.doctypes[i].childrenDocType[j].typeId+"</i>"+ data.doctypes[i].childrenDocType[j].typeName
										+ "</a> ";
							}
							var str = "<tr><td>"
									+ "<a class='btn btn-primary btn-sm'>"
									+ data.doctypes[i].typeName
									+ "</a>"
									+ "</td>"
									+ "<td>"
									+ cstr
									+ "</td>"
									+ "<td><a class='btn btn-danger btn-sm'  data-toggle='modal' data-target='.bs-example-modal-sm'><i class='glyphicon glyphicon-plus'></i>增加</a></td></tr>";
							$("#sort-list").append(str);
						}
					});
</script>
</html>