<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="kor">
<style>
.va-middle {
	font-size: 20px;
}

.page-link {
	font-size: 20px;
}

.mb-20 {
	font-size: 2rem;
	font-weight: 'bold';
}
.btn-outline-primary {
  font-size: 18px; /* 원하는 크기로 설정 */
}
.selected {
  background-color: #007bff; // 선택된 배경색
  color: #ffffff; // 선택된 텍스트 색상
}
</style>
<body>
<!-- 헤더 -->
   <jsp:include page="/WEB-INF/views/tiles/header.jsp"/>
   <!-- 배너 -->
   <c:set var="boardName" value="문의게시판" />
   <header class="xl bg-img bg-fixed" style="height: 300px; padding-top: 200px;">
      <div class="container text-center">
         <h1 class="page-title">QNA</h1>
         <p class="w-50 m-x-auto mb-30"><c:out value="${boardName}" /></p>
      </div>
      <!-- / container -->
   </header>

	<div id="preloader">
		<div class="preloader">
			<span></span> <span></span>
		</div>
	</div>

	<div id="top"></div>
	<!-- / top -->

	<!-- 큰 컨테이너  -->
	<section class="big">
		<div class="container">
			<h2 class="hidden">Qna Board List</h2>
			<!-- 키워드 -->
			<ul class="list-inline text-center mb-30" >
				<li><a href="<c:url value="/qnaboard/list"/>" class="btn btn-outline-primary m-y-10 mr-10" >전체</a></li>
				<li id="keyword-button"><a href="<c:url value="/qnaboard/list?keyword=member"/>" class="btn btn-outline-primary m-y-10 mr-10" >일반회원</a>
				</li>
				<li id="keyword-button"><a href="<c:url value="/qnaboard/list?keyword=trade"/>" class="btn btn-outline-primary m-y-10 mr-10" >상권회원</a>
				</li>
			</ul>

			<div>
				<!-- 검색 -->
				<!-- select -->
				<div class="card">
					<div class="card-body"
						style="padding-bottom: 20px; padding-top: 20px; padding-left: 50px; padding-right: 50px;">
						<form action="list" method="get"
							class="d-flex align-items-center justify-content-between">
							<input type="hidden" name="pageNum" value="1">
							<div class="col-md-3 tablet-top" style="padding-right: 10px">
								<select class="custom-select" id="select" name="searchType">
									<option value="TC"  ${pager.searchCondition.searchType == 'TC' ? 'selected' : ''}>제목+내용</option>
									<option value="T" ${pager.searchCondition.searchType == 'T' ? 'selected' : ''}>제목</option>
									<option value="W" ${pager.searchCondition.searchType == 'W' ? 'selected' : ''}>작성자</option>
									<!-- 나머지 옵션 -->
								</select>
								<!-- / custom-select -->
							</div>
							<!-- / column -->
							<div class="input-group input-w-overlap-btn mb-0">
								<input type="text" class="form-control pill" name="searchKeyword"
									placeholder="검색어를 입력하세요." value="${pager.searchCondition.searchKeyword }"/><span class="input-group-btn">
									<button id="search-button"
										class="btn btn-sm btn-primary lh-0 overlapping-btn big-btn pill"
										type="button">
										<i class="fas fa-search mr-5">검색</i>
									</button>
								</span>
								<!-- / input-group-btn -->
							</div>
							<!-- / input-group -->
						</form>
					</div>
				</div>
				<!-- 검색입력창 -->

				<!-- 글쓰기버튼 -->
				<a href="<c:url value='/qnaboard/write${pager.searchCondition.getQueryString()}'/>"
					class="btn btn-primary-gradient m-y-10 mr-10"
					style="float: right; display: block"><span
					class="fas fa-edit mr-10"></span> <span style="font-size: 20px">글쓰기</span></a>
			</div>

			<div>
				<ul class="row portfolio project-grid lightbox list-unstyled mb-0"
					id="grid" style="clear: both">
					<!--====================================================================================================  -->
					<!-- project : 게시글 list 출력 -->
					<c:set var="i" value="0"/>
					<c:forEach items="${qnaBoardList}" var="qnaBoard">
						<li class="col-md-12 col-lg-0 project">
							<a href="<c:url value='/qnaboard/read${pager.searchCondition.getQueryString()}&qnaBno=${qnaBoard.qnaBno}'/>">
								<!-- &pageNum=${pageNum} -->
								<div class="promo-box">
									<div class="cta p-0">
										<div class="row v-center">
											<div class="col-lg-12 text-left tablet-lg-center">
												<p class="mb-20">${qnaBoard.qnaTitle}</p>
												<p class="lead mb-20">${qnaBoard.qnaContent}&nbsp;&nbsp;...</p>
												<p class="lead mb-10">${qnaBoard.qnaViewCnt}</p>
												<p class="fs-16 post-meta-small mt-15 mb-0"
													style="text-align: right">
													<i class="far fa-calendar-alt mr-5">${qnaBoard.qnaRegdate}</i>
													<!-- <i class="far fa-calendar-alt mr-5">${qnaBoard.nickname}</i> -->
													<span class="m-x-10 text-muted">|</span> 
													<i class="fas fa-tag mr-10"></i>
													<c:choose>
														<c:when test="${qnaBoard.qnaKeyword == 'member'}">일반회원</c:when>
														<c:when test="${qnaBoard.qnaKeyword == 'trade'}">상권회원</c:when>
													</c:choose>
												</p>
											</div>
											<!-- / column -->
										</div>
										<!-- / row -->
									</div>
									<!-- / cta -->
								</div>
								<!-- / promo-box -->
							</a>
						</li>
					</c:forEach>
					<!--====================================================================================================  -->
				</ul>
			</div>
		</div>
		<!-- / container -->
	</section>


	<nav aria-label="pagination-center">
		<ul class="pagination justify-content-center">
			<!-- 이전 블록 이동 표시 -->
			<c:choose>
				<c:when test="${pager.startPage != 1 }">
					<li class="page-item"><a class="page-link"
						href="<c:url value='/qnaboard/list${pager.searchCondition.getQueryString(pager.startPage-1)}'/>">
						<i class="fas fa-arrow-left mb-5"></i></a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item disabled"><a class="page-link" href="#x">
						<i class="fas fa-arrow-left mb-5"></i></a></li>
				</c:otherwise>
			</c:choose>
			<!-- 페이지 목록 출력 -->
			<c:forEach var="i" begin="${pager.startPage}" end="${pager.endPage }">
				<c:choose>
					<c:when test="${i == pager.searchCondition.pageNum}">
						<li class="page-item active"><a class="page-link"
							href="<c:url value='/qnaboard/list${pager.searchCondition.getQueryString(i)}'/>">${i}</a></li>
					</c:when>
					<c:otherwise>
						<li class="page-item"><a class="page-link"
							href="<c:url value='/qnaboard/list${pager.searchCondition.getQueryString(i)}'/>">${i}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<!--  다음 블록 이동 표시  -->
			<c:choose>
				<c:when test="${pager.endPage != pager.totalPage}">
					<li class="page-item"><a class="page-link"
						href="<c:url value='/qnaboard/list${pager.searchCondition.getQueryString(pager.endPage+1)}'/>">
							<i class="fas fa-arrow-right mb-5"></i></a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item disabled"><a class="page-link" href="#x">
						<i class="fas fa-arrow-right mb-5"></i></a></li>
				</c:otherwise>
			</c:choose>

		</ul>
	</nav>
	<!-- / pagination-center -->

	<a href="#top" class="scroll-to-top is-visible smooth-scroll"
		data-nav-status="toggle"><i class="fas fa-chevron-up"></i></a>

	<!-- footer 영역 -->
	<jsp:include page="/WEB-INF/views/tiles/footer.jsp"/>

	<!-- core JavaScript -->
	<script
		src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
	<!-- / core JavaScript -->

	<!-- preloader -->
	<script src="${pageContext.request.contextPath}/assets/js/preloader.js"></script>
	<!-- / preloader -->

	<!-- hide nav -->
	<script src="${pageContext.request.contextPath}/assets/js/hide-nav.js"></script>
	<!-- / hide nav -->

	<!-- portfolio script -->
	<script
		src="${pageContext.request.contextPath}/assets/js/jquery.shuffle.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/portfolio.js"></script>
	<script>
		$(document).ready(function() {
			
			var keywordInput = $('input[name="searchKeyword"]');
				$('#search-button').click(function() {
					// 선택한 검색 유형과 키워드를 가져옵니다.
					var searchType = $('#select').val();
					var searchKeyword = keywordInput.val();
					var pageNum = $('input[name="pageNum"]').val();
					
					if (searchKeyword === '') {
				        keywordInput.val('검색어를 입력하지 않으셨습니다.'); // 값을 직접 변경
				        keywordInput.css('color', 'red'); // 텍스트 색상 변경
				        return;
				    } else {
				        keywordInput.css('color', 'black'); // 일반 색상으로 되돌림
				    }
					
					// url 생성
					var url = "list?pageNum=" + pageNum + "&searchType=" + searchType + "&searchKeyword=" + searchKeyword;

					//리다이렉트합니다.
					window.location.href = url;
				});
				 keywordInput.focus(function() {
				        if (keywordInput.val() === '검색어를 입력하지 않으셨습니다.') {
				            keywordInput.val(''); // 오류 메시지를 지움
				            keywordInput.css('color', 'black'); // 일반 텍스트 색상으로 되돌림
				        }
				    });

			
			if (Modernizr.touch) {
				// show the close overlay button
				$('.close-overlay').removeClass('hidden');
				// handle the adding of hover class when clicked
				$('.img').click(function(e) {
					if (!$(this).hasClass('hover')) {
						$(this).addClass('hover');
					}
				});
				// handle the closing of the overlay
				$('.close-overlay').click(function(e) {
					e.preventDefault();
					e.stopPropagation();
					if ($(this).closest('.img').hasClass('hover')) {
						$(this).closest('.img').removeClass('hover');
					}
				});
			} else {
				// handle the mouseenter functionality
				$('.img').mouseenter(function() {
					$(this).addClass('hover');
				})
				// handle the mouseleave functionality
				.mouseleave(function() {
					$(this).removeClass('hover');
				});
			}
		});
	</script>
	<!-- / portfolio script -->
</body>
</html>