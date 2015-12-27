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
			<!--描述：分类-->
			<div class="col-md-3">
				<div class="left-nav">
					<div class="left-inner">
						<div class="left-inner-panel-head">
							<a href="#">生活悠闲</a>>
						</div>
						<div class="left-inner-panel-content">
							<a href="#">美食食谱</a> <a href="#">游戏攻略</a> <a href="#">运动健身</a> <a
								href="#">运动健身</a> <a href="#">运动健身</a> <a href="#">运动健身</a>
						</div>
					</div>
					<div class="left-inner">
						<div class="left-inner-panel-head">
							<a href="#">生活悠闲</a>>
						</div>
						<div class="left-inner-panel-content">
							<a href="#">美食食谱</a> <a href="#">游戏攻略</a> <a href="#">运动健身</a> <a
								href="#">美食食谱</a> <a href="#">游戏攻略</a> <a href="#">运动健身</a>
						</div>
					</div>
					<div class="left-inner">
						<div class="left-inner-panel-head">
							<a href="#">生活悠闲</a>>
						</div>
						<div class="left-inner-panel-content">
							<a href="#">美食食谱</a> <a href="#">游戏攻略</a> <a href="#">运动健身</a> <a
								href="#">美食食谱</a> <a href="#">游戏攻略</a> <a href="#">运动健身</a>
						</div>
					</div>
					<div class="left-inner">
						<div class="left-inner-panel-head">
							<a href="#">生活悠闲</a>>
						</div>
						<div class="left-inner-panel-content">
							<a href="#">美食食谱</a> <a href="#">游戏攻略</a> <a href="#">运动健身</a> <a
								href="#">美食食谱</a> <a href="#">游戏攻略</a> <a href="#">运动健身</a>
						</div>
					</div>
					<div class="left-inner">
						<div class="left-inner-panel-more">
							<a href="#">查看更多</a>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-9 right-nav">
				<!--描述：焦点图-->
				<div id="carousel-example-generic" class="carousel slide"
					data-ride="carousel">
					<!-- Indicators -->
					<ol class="carousel-indicators">
						<li data-target="#carousel-example-generic" data-slide-to="0"
							class="active"></li>
						<li data-target="#carousel-example-generic" data-slide-to="1"></li>
						<li data-target="#carousel-example-generic" data-slide-to="2"></li>
					</ol>

					<!-- Wrapper for slides -->
					<div class="carousel-inner" role="listbox">
						<div class="item active">
							<a href="#"><img src="img/c1.jpg" alt=""></a>

							<div class="carousel-caption">
								<h3>气质美美</h3>
								<p>气质美美过圣诞~</p>
							</div>

						</div>
						<div class="item">
							<a href="#"><img src="img/c2.jpg" alt=""></a>
							<div class="carousel-caption">
								<h3>气质美美</h3>
								<p>气质美美过圣诞~</p>
							</div>
						</div>
						<div class="item">
							<a href="#"><img src="img/c3.jpg" alt=""></a>
							<div class="carousel-caption">
								<h3>气质美美</h3>
								<p>气质美美过圣诞~</p>
							</div>
						</div>
					</div>

					<!-- Controls -->
					<a class="left carousel-control" href="#carousel-example-generic"
						role="button" data-slide="prev"> <span
						class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
						<span class="sr-only">Previous</span>
					</a> <a class="right carousel-control" href="#carousel-example-generic"
						role="button" data-slide="next"> <span
						class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
						<span class="sr-only">Next</span>
					</a>
				</div>

			</div>
		</div>
		<hr />
		<!--描述：首页最近热门-->
		<div class="row index-middle">
			<div class="col-md-4">
				<div class="panel">
					<div class="panel-heading">
						<a href="#">最新上传</a><a href="#" class="rigtht-more">查看更多</a>
					</div>
					<div class="panel-body">
						<ul>
							<li><a href="#"><img src="img/wordicon.jpg">
									高二物理选修3-1模块综合测试高二物理选修3-1模块综合测试</a></li>
							<li><a href="#"><img src="img/wordicon.jpg">
									高二物理选修3-1模块综合测试高二物理选修3-1模块综合测试</a></li>
							<li><a href="#"><img src="img/wordicon.jpg">
									高二物理选修3-1模块综合测试高二物理选修3-1模块综合测试</a></li>
							<li><a href="#"><img src="img/wordicon.jpg">
									高二物理选修3-1模块综合测试高二物理选修3-1模块综合测试</a></li>
							<li><a href="#"><img src="img/wordicon.jpg">
									高二物理选修3-1模块综合测试高二物理选修3-1模块综合测试</a></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="panel">
					<div class="panel-heading">
						<a href="#">热门文档</a><a href="#" class="rigtht-more">查看更多</a>
					</div>
					<div class="panel-body">
						<ul>
							<li><a href="#"><img src="img/wordicon.jpg">
									高二物理选修3-1模块综合测试高二物理选修3-1模块综合测试</a></li>
							<li><a href="#"><img src="img/wordicon.jpg">
									高二物理选修3-1模块综合测试高二物理选修3-1模块综合测试</a></li>
							<li><a href="#"><img src="img/wordicon.jpg">
									高二物理选修3-1模块综合测试高二物理选修3-1模块综合测试</a></li>
							<li><a href="#"><img src="img/wordicon.jpg">
									高二物理选修3-1模块综合测试高二物理选修3-1模块综合测试</a></li>
							<li><a href="#"><img src="img/wordicon.jpg">
									高二物理选修3-1模块综合测试高二物理选修3-1模块综合测试</a></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="panel">
					<div class="panel-heading">
						<a href="#">个人信息</a>
					</div>
					<div class="panel-body">
						<div class="row" id="personal-center">
							<div class="col-md-5">
								<img src="img/pass.png" alt="..." class="img-thumbnail">
							</div>
							<div class="col-md-5">
								<a class="btn btn-block btn-warning" href="doc/upload">上传文档</a>
								<a class="btn btn-block btn-warning">我的文库</a> <a
									class="btn btn-block btn-warning">消息 <span class="badge">3</span></a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	
<%@ include file="/WEB-INF/content/public/footer.jspf"%>
</body>
</html>