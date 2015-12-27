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
					<div class="panel-heading">我的文档</div>
					<div class="panel-body">
						<table class="table doc-table table-condensed">
							<tr>
								<th>文档编号</th>
								<th>名称</th>
								<th>上传用户</th>
								<th>上传时间</th>
								<th>类别</th>
								<th>浏览权限</th>
								<th>操作</th>
							</tr>
							<s:iterator value="page.content">
								<tr>
									<td>${docId }</td>
									<td>${docName}</td>
									<td>${docOwnUser.userName }</td>
									<td>${docDate }</td>
									<td>${docType.parentType.typeName }:<br>${docType.typeName }</td>
									<td><select class="form-control"
										id="docAuthority${docId }">
											<option value="1">公开</option>
											<option value="2">会员</option>
											<option value="3">仅自己</option>
									</select> <script type="text/javascript">
										$("#docAuthority${docId}").val(
												'${docAuthority}');
									</script></td>
									<td><a href="doc/delete?docId=${docId }" class="btn btn-danger btn-sm">删除</a> <a
										href="doc/upload?docId=${docId }" class="btn btn-warning btn-sm">修改</a> <a
										href="main/doc-result.action?docId=${docId }"
										class="btn btn-info btn-sm">查看</a></td>
								</tr>
							</s:iterator>

						</table>
						<input type="hidden" value="doc/mydoc-list" id="actionValue">
						<%@ include file="/WEB-INF/content/public/pageView.jspf"%>
					</div>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="/WEB-INF/content/public/footer.jspf"%>
</body>
<script type="text/javascript">
	$("#left-list-nav").find("a").eq(3).addClass("active");
</script>
</html>
