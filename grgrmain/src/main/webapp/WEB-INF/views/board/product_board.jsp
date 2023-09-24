<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<!-- Meta -->
<!-- Favicon -->
<link rel="icon"
	href="${pageContext.request.contextPath}/assets/images/favicon.png">
<!-- Site Title -->
<title>Soft UI - Neumorphism Style UI Kit</title>
<!-- Bootstrap 4 core CSS -->
<link
	href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Custom Styles -->
<link
	href="${pageContext.request.contextPath}/assets/css/nouislider.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/css/animate.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/assets/css/owl.carousel.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/css/style.css"
	rel="stylesheet">
<!-- Fonts -->
<link
	href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;600;800&display=swap"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Nunito+Sans:wght@300;600;800&display=swap"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/assets/css/fontawesome-all.min.css"
	rel="stylesheet" type="text/css">
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

.board-images {
	width: 100px;
}

#comment-reply {
	margin-bottom: 20px; /* 아래쪽 여백을 20px로 설정 */
	clear: both;
}

.comment-style {
	font-size: 15px;
	font-weight: normal;
}

.comment {
	border-top: 1px solid #97989d;
}

.product-space {
	overflow-wrap: break-word; /* 단어 단위로 개행 */
}
</style>
</head>

<body>
	<!-- 헤더 -->
	<jsp:include page="/WEB-INF/views/tiles/header.jsp" />
	<div id="preloader">
		<div class="preloader">
			<span></span> <span></span>
		</div>
	</div>
	<!-- 배너 -->
	<c:set var="boardName" value="판매게시판" />
	<header class="xl bg-img bg-fixed"
		style="background-image: url(${pageContext.request.contextPath}/images/market.jpg); background-size: cover;">
		<div class="container text-center">
			<h1 class="page-title">Market</h1>
			<p class="w-50 m-x-auto mb-30" style="color: white;">
				<c:out value="${boardName}" />
			</p>
		</div>
		<!-- / container -->
	</header>


	<div id="top"></div>
	<!-- / top -->

	<div class="container relative z-index-1">
		<h1 class="page-title text-left mb-15">상품 조회</h1>
		<nav class="breadcrumb relative z-index-1 pt-10 pl-5">
			<a class="breadcrumb-item" href="/main">Home</a> <a
				class="breadcrumb-item" href="/productboard/list">Market</a> <span
				class="breadcrumb-item active">Product</span>
		</nav>
	</div>
	<!-- / container -->

	<section class="big page-section">
		<div class="container">
			<div class="row">
				<div class="col-lg-6 tablet-lg-top-30 page-content">
					<div class="project-post">
						<div class="page-content">
							<div id="product-carousel" class="owl-carousel owl-theme">
								<c:forEach var="file" items="${productFiles}">
									<%-- <img src="${pageContext.request.contextPath}/resources/upload/${file.infoFileUpload}" --%>
									<img src="<c:url value="/upload/${file.productFileUpload}"/>"
										alt="${file.productFileOrigin }" class="board-images"
										width="200">
								</c:forEach>
							</div>
							<!-- / owl-carousel -->
						</div>
						<!-- / page-content -->
					</div>
					<!-- / project-post -->
				</div>
				<!-- / column -->

				<div class="col-lg-6 sidebar">
					<h3 class="mb-5 product-space">
						<c:out value="${productBoard.productTitle}" />
					</h3>
					<p class="fs-20 mb-15 product-space">
						₩
						<c:out value="${productBoard.productPrice}" />
					</p>
					<p class="lead m-y-40 product-space">
						<c:out value="${productBoard.productContent}" />
					</p>


					<div class="form-cart">
						<div
							class="input-group input-w-overlap-btn mb-0 md-input-group sm-input">
							<input type="number" step="1" min="1" max="10" name="cart"
								value="1" title="qty" class="form-control qty mr-10 rounded"
								id="quantityInput"> <span class="input-group-btn">
								<button class="btn btn-primary rounded" type="button"
									id="addToCartBtn">
									<i class="fas fa-shopping-cart mr-5"></i> <span>장바구니 담기</span>
								</button>
								<button class="btn btn-primary rounded" type="button"
									id="buyItemBtn">
									<i class="fas fa-shopping-cart mr-5"></i> <span>바로 구매</span>
								</button>
							</span>
						</div>
					</div>

					<c:if
						test="${loginUserStatus == '3' && productBoard.uno == sessionScope.loginUno}">
						<!-- 수정버튼 제출시 제출된 게시글로 진입 + 자신의 게시글에서 글목록 누를시 원래보던 페이지로 이동할 것  -->
						<a
							href="<c:url value='/productboard/modify${searchCondition.getQueryString()}&productId=${productBoard.productId}'/>"
							class="btn btn-xs btn-primary pill mt-3" style="font-size: 15px;"><span>수정</span></a>

						<!-- 자신의 글일시 해당 글번호의 글을 삭제할 수 있음 -->
						<a
							href="<c:url value='/productboard/remove${searchCondition.getQueryString()}&productId=${productBoard.productId}'/>"
							class="btn btn-xs btn-primary pill mt-3" style="font-size: 15px;"><span>삭제</span></a>
					</c:if>


					<c:if test="${sessionScope.loginUserStatus == 1 }">
						<!-- 관리자는 해당 글번호의 글을 숨김처리할 수 있음 -->
						<a
							href="<c:url value='/productboard/hide${searchCondition.getQueryString()}&productId=${productBoard.productId}'/>"
							class="btn btn-xs btn-primary pill mt-3" style="font-size: 15px;"><span>숨김</span></a>
					</c:if>
				</div>

				<!-- / column -->
			</div>
			<!-- / row -->

			<div class="card mt-30">
				<div class="card-body">
					<ul class="nav nav-tabs nav-pills" role="tablist">
						<li class="nav-item"><a class="nav-link" data-bs-toggle="tab"
							href="#info" role="tab"><i class="fas fa-tag mr-5"></i> 판매자
								정보</a></li>
						<li class="nav-item"><a class="nav-link" data-bs-toggle="tab"
							href="#reviews" role="tab"><i class="fas fa-star mr-5"></i>
								리뷰</a></li>
					</ul>
					<!-- / nav tabs -->
					<div class="tab-content">

						<div class="tab-pane fade mt-30" id="info" role="tabpanel">
							<div class="table-overflow">
								<table class="table table-xl border mb-0">
									<tbody>
										<tr>
											<td>
												<h4 class="fs-14 mb-0">판매자:</h4>
											</td>
											<td><c:out value="${userInfo.nickName}" /></td>
											<td>
												<h4 class="fs-14 mb-0">연락처:</h4>
											</td>
											<td><c:out value="${userInfo.phone}" /></td>
											<td><h4 class="fs-14 mb-0">이메일:</h4></td>
											<td><c:out value="${userInfo.email}" /></td>
											<td></td>
										</tr>
									</tbody>
								</table>
							</div>
							<!-- / table-overflow -->
						</div>
						<div class="tab-pane fade mt-30" id="reviews" role="tabpanel">
							<div class="table-overflow">
								<table class="table table-xl mb-0">
									<tbody>
										<tr>
											<td>
												<div class="list-media img-lg">

													<!-- / replyForm - 댓글 제출 폼 -->
													<div class="list-item"></div>
													<!-- / media-img -->
													<div class="info">
														<ul id="comments-list"
															style="border-bottom: solid 1px #97989d; padding-left: 0px">
														</ul>
														<!-- 댓글폼 넣어볼곳 -->
														<!-- 댓글 제출 폼 -->
														<div id="reply-form">
															<form id="replyForm">

																<textarea id="reply" class="form-control border-faded"
																	rows="15" placeholder="리뷰를 입력하세요"></textarea>
																<button type="button" id="submit-reply"
																	class="btn btn-primary-gradient w-100 mt-15">
																	<span>리뷰 등록</span>
																</button>
															</form>
														</div>
														<nav aria-label="pagination-center">
															<ul class="pagination justify-content-center">
															</ul>
														</nav>

													</div>
													<!-- / info -->
												</div> <!-- / list-item -->

											</td>
										</tr>
										<tr>

										</tr>
									</tbody>
								</table>
							</div>
							<!-- / table-overflow -->
						</div>
						<!-- / tab-pane -->
					</div>
					<!-- / tab-pane -->
				</div>
				<!-- / tab-content -->
			</div>
			<!-- / card-body -->
		</div>
		<!-- / card -->

		<!-------------------------------------------댓글영역 ----------------------------------------------->

		<!-- 대댓글 폼 : 대댓 버튼 누를시 댓글아래로 이동하여 style -> block-->
		<div id="comment-reply" data-group=""
			style="display: none; width: 95%; display: flex; justify-content: flex-end; align-items: center; margin: 10px;">
			<input class="form-control" type="text" name="productCommentContent"
				placeholder="대댓글을 입력하세요...">
			<div class="input-group-append">
				<button id="wrtRepBtn" type="button"
					class="btn btn-outline-secondary">등록</button>
			</div>
		</div>
		<!-- 대댓글 폼 -->

		<!-- / container -->
	</section>


	<a href="#top" class="scroll-to-top is-hidden smooth-scroll"
		data-nav-status="toggle"><i class="fas fa-chevron-up"></i></a>

	<!-- footer 영역 -->
	<jsp:include page="/WEB-INF/views/tiles/footer.jsp" />
	
	


	<script>
		$('#product-carousel').owlCarousel({
			loop : true,
			margin : 0,
			nav : false,
			dots : true,
			items : 1,
			animateIn : 'fadeIn',
			animateOut : 'fadeOut'
		});
	</script>
	<!-- / Owl Carousel -->
	
	<script>

		/* 장바구니 관련 스크립트 */
        $("#addToCartBtn").on("click", function() {
            const productId = ${productBoard.productId}
            const productCount = parseInt($("#quantityInput").val());
            console.log("productCount : "+ productCount);
            let loginUno = ${loginUno};
            
            // 수량 체크
            if (productCount > 10) {
                alert("최대 가능 수량은 " + 10 + " 개 입니다.");
                return; // 등록 X
            }
            
            // 공백 체크
             if (!productCount || isNaN(productCount) || productCount <1) {
                alert("수량을 입력하세요.");
                return; // 등록 X
            }
            // AJAX 요청
            $.ajax({
                url: "<c:url value="/cart/add"/>",
                type: 'POST',
                data: {
                    "productId": productId,
                    "uno": loginUno,
                    "productCount": productCount
                },
                success: function(result) {
                    
                    if (result === '1') {
                        alert("장바구니에 추가되었습니다.");
                    } else if (result === '2') {
                        alert("장바구니에 이미 추가되어 있습니다.");
                    } else {
                        alert("장바구니에 추가를 하지 못하였습니다.");
                    }
                },	
                error: function() {
                	
                    alert("오류가 발생하였습니다.");
                }
            });
        });
        
        /* 바로구매 관련 스크립트 */
        $("#buyItemBtn").on("click", function() {
            const productId = ${productBoard.productId}
            const productCount = parseInt($("#quantityInput").val());
            const totalPrice = ${productBoard.productPrice} * productCount;
            console.log("productCount : "+ productCount);
            console.log("totalPrice : "+ totalPrice);
            
            let loginUno = ${loginUno};
 
        	
            // 수량 체크
            if (productCount > 10) {
                alert("최대 가능 수량은 " + 10 + " 개 입니다.");
                return; // 등록 X
            }
            
            // 공백 체크
             if (!productCount || isNaN(productCount) || productCount <1) {
                alert("수량을 입력하세요.");
                return; // 등록 X
            }
            // AJAX 요청
            $.ajax({
                url: "<c:url value="/order/product"/>",
                type: 'POST',
                contentType: "application/json",
                data: JSON.stringify({
                    productId: productId,
                    uno: loginUno,
                    orderQuantity: productCount,
                    totalPrice: totalPrice
                }),
                success: function(result) {
                	alert("주문테이블에 저장");
                	if(result == "success")
                	location.reload();
                	//window.locatoin.href = '${pageContext.request.contextPath}/order/cart';
                },	
                error: function(xhr) {
                    alert(xhr.responseText);
                }
            });
        });

</script>
	

	<script>
		const url = new URL(window.location.href);
		const productId = url.searchParams.get("productId");
		let pageNum = 1;
		let loginUno;
		let productCommentNo;
		let loginUserStatus;
		
		//댓글 조회
		let showList = function(productId, pageNum) {
			$.ajax({
				type : 'GET', 
				url : "<c:url value="/productcomment/list"/>/"+ productId, // productId 값을 경로에 포함시킵니다.
				data: {"pageNum":pageNum},
				success : function(data) {
					
					if(data.productCommentList.length===0){
						var html="<li class='comment'>";
						html += '<p class="mb-0 comment-style" style="padding: 10px 30px 10px 50px; text-align:center">리뷰가 존재하지 않습니다. 첫번째 리뷰를 입력해주세요.</p>';
					
						html+="</li>";
						$("#comments-list").html(html);
						return;
					}
					
					loginUno = ${sessionScope.loginUno};
					loginUserStatus = ${sessionScope.loginUserStatus};
					console.log("loginUno :" + loginUno);
					console.log("loginUserStatus :" + loginUserStatus);
					
			            resetReplyForm();
			            renderComments(data.productCommentList);
			            renderPagination(data.commentPager);
			        
				},
				error : function(err) {
					
					console.error("댓글을 불러오는데 실패했습니다.", err);
				}
			});

		}

		function renderComments(comments) {
			var $commentList = $('#comments-list');

			$commentList.empty(); // 기존 댓글 내용을 지웁니다.

			comments.forEach(function(comment) {
				var commentHTML = toHtml(comment); 
				$commentList.append(commentHTML);
			});
		}
		
		let toHtml = function(comment) {
			let html = '<li class="comment " data-cno="' + comment.productCommentNo;
		    html += '" data-group="' + comment.productCommentGroup;
		    html += '" data-productId="' + comment.productId + '">';
			if (comment.productCommentBlindstate === 3) { // 댓글이 삭제
				html += '<p class="mb-0 comment-style"  style="display: none;">삭제된 리뷰입니다.</p>';
			} else if (comment.productCommentBlindstate == 2) { //게시자가 숨겼다면
				html += '<p class="mb-0 comment-style" style="padding: 10px 30px 10px 50px; text-align:center">관리자에의해 비공개된 댓글입니다.</p>';
			} else { // 정상적으로 보이는 게시물
				
				if(comment.productCommentGroup == comment.productCommentNo){ //부모댓글
					html += '<div class="comment-product" style="padding: 10px 30px;">';
					html += '<span class="comment-author" style="padding-right: 20px">'
							+ comment.nickname + '</span>';
					html += '<span class="comment-date">'+ comment.productCommentRegdate + '</span>';
					html += '</div>';
					html += '<p class="mb-0 comment-style" style="padding: 0px 30px 10px 40px;">'
							+ comment.productCommentContent;
					html += '<span id="comment-select" style="float: right;">';
					html += '<a href="#x" class="replyBtn"><i class="far fa-comments fs-15 mr-5"></i>대댓글</a>';
				
					if (comment.uno === loginUno) {
						//html += '<a href="#x" class="comment-modify"> <i class="far fa-comments fs-15 mr-5"></i>변경</a>';
						html += '<a href="#x" class="comment-remove"> <i class="far fa-comments fs-15 mr-5"></i>삭제</a>';
					}
					console.log("삭제 loginUno"+ loginUno);
					if (loginUserStatus === 1) {
						html += '<a href="#x" class="comment-hide"> <i class="far fa-comments fs-15 mr-5"></i>숨김</a>';
					}
					html += '</span>';
					html += '</p>';
					 
			 	} else { //자식댓글
			 		html += '<div class="comment-product" style="padding: 10px 30px;">';
					html += '<span class="comment-author" style="padding: 0 20px 0 80px">'
							+ comment.nickname + '</span>';
					html += '<span class="comment-date">'+ comment.productCommentRegdate + '</span>';
					html += '</div>';
					html += '<p class="mb-0 comment-style" style="padding: 0px 30px 10px 100px;">'	+ comment.productCommentContent;
					html += '<span id="comment-select" style="float: right;">';				
					if (comment.uno === loginUno) {
						//html += '<a href="#x" class="comment-modify"> <i class="far fa-comments fs-15 mr-5"></i>변경</a>';
						html += '<a href="#x" class="comment-remove"> <i class="far fa-comments fs-15 mr-5"></i>삭제</a>';
					}
					if (loginUserStatus === 1) {
						html += '<a href="#x" class="comment-hide"> <i class="far fa-comments fs-15 mr-5"></i>숨김</a>';
					}
					html += '</span>';
					html += '</p>';
				 
			 }
			}

			html += '</li>';
			return html;
		}

		function renderPagination(commentPager) {
			const paginationContainer = $('.pagination');
			let html = "";

			// 이전 페이지 
			if (commentPager.startPage == 1) {
				html += '<li class="page-item disabled">';
				html += '<a class="page-link" href="javascript:void(0)">';
				html += '<i class="fas fa-arrow-left mb-5"></i>';
				html += '</a></li>';
			} else {
				html += '<li class="page-item">';
				html += '<a class="page-link" href="javascript:void(0)" onclick="showList(productId, '
						+ commentPager.prevPage + ')">';
				html += '<i class="fas fa-arrow-left mb-5"></i>';
				html += '</a></li>';
			}

			// 페이지 번호
			for (let i = commentPager.startPage; i <= commentPager.endPage; i++) {
				const isActive = i === commentPager.pageNum ? 'active' : '';
				html += '<li class="page-item ' + isActive + '">';
				html += '<a class="page-link" href="javascript:void(0)" onclick="setPageNum('
						+ i + ')">' + i + '</a>';

				html += '</li>';
			}

			// 다음 페이지
			if (commentPager.endPage == commentPager.totalPage) {
				html += '<li class="page-item disabled">';
				html += '<a class="page-link" href="javascript:void(0)">';
				html += '<i class="fas fa-arrow-right mb-5"></i>';

				html += '</a></li>';
			} else {
				html += '<li class="page-item">';
				html += '<a class="page-link" href="javascript:void(0)" onclick="showList(productId, '
						+ commentPager.nextPage + ')">';
				html += '<i class="fas fa-arrow-right mb-5"></i>';
				html += '</a></li>';
			}

			// HTML 추가
			paginationContainer.html(html);
		}


		function setPageNum(selectedPageNum) {
			pageNum = selectedPageNum; // pageNum에 페이지 번호 저장
			showList(productId, pageNum);
		}
		
		function resetReplyForm() {
		    //$("#comment-reply").hide(); // 대댓글 폼을 숨깁니다.
		    $("#comment-reply").appendTo("#my-section"); // 대댓글 폼을 원래 위치로 되돌립니다.
		    $("#comment-reply").val("");
		}
		
		//페이지 로드시 실행될 것들
		$(document).ready(function() {

			$("#comment-reply").hide(); //대댓글 폼을 일단 숨김
		    showList(productId, pageNum); //댓글 list 출력
		    

		<!-- 대댓글 폼 -->

		 /* 대댓글 폼 : 대댓 버튼 누를시 댓글아래로 이동하여 style -> block */
		$("#comments-list").on("click", ".replyBtn", function() {
			 const commentContainer = $(this).closest('.comment');
			 console.log()
			 const commentGroup = commentContainer.data('group'); // 선택한 댓글의 data-group값 가져오기
			 $("#comment-reply input[name='productCommentContent']").val('');
			 $("#comment-reply").appendTo(commentContainer);
			 $("#comment-reply").data('group', commentGroup);
			 $("#comment-reply").show();
		});
		/* 대댓글 등록 버튼 클릭 */
		$("#wrtRepBtn").on("click", function() {
		    const commentGroup = $("#comment-reply").data('group');
		    const replyContent = $("#comment-reply input[name='productCommentContent']").val();

		    /* "/GRGR/infocomment/write?infoBno=" + infoBno, */
		    $.ajax({
		        type: "POST",
		        url: "<c:url value="/productcomment/write"/>/"+productId,
		        headers: {
		            'Content-Type': 'application/json'
		        },
		        data: JSON.stringify({
		        	"productId" : productId,
		            "productCommentGroup": commentGroup,
		            "productCommentContent": replyContent
		        }),   
		        success: function(response) {
		            showList(productId, pageNum);
		        },
		        error: function(err) {
		            console.error("대댓글 등록에 실패했습니다.", err.responseText);
		        }
		    });
		});

		
		$(document).on('click', function(event) {
		    // 대댓글 폼이나 대댓글 버튼 외 곳을 클릭하면 숨겨짐
		    if (!$(event.target).closest("#comment-reply").length && !$(event.target).closest(".replyBtn").length) {
		        $("#comment-reply").hide(); // 대댓글 폼 숨기기
		        // 대댓글 폼 이동 (section아래로 보냈음)
		        $("#comment-reply").appendTo("#my-section");
		    }
		});

		    // 댓글 삭제
		    $("#comments-list").on("click", ".comment-remove", function() {
		        let productCommentNo = $(this).closest('li.comment').data('cno');
		        console.log('productCommentNo :' + productCommentNo);

		        $.ajax({
		            type: 'PUT', 
		            url: "<c:url value="/productcomment/remove"/>/" + productCommentNo,
		            dataType: 'text',
		            success: function() {
		                const commentLi = $('li[data-cno="' + productCommentNo + '"]');
		                commentLi.find('p.mb-0').text("작성자에 의해 삭제된 댓글입니다.");
		                commentLi.find('.comment-product').remove();
		                commentLi.find('.comment-modify, .comment-remove').remove();
		                showList(productId, pageNum);
		            },
		            error: function(err) {
		                console.error("댓글 상태 변경에 실패했습니다.", err.responseText);
		            }
		        });
		    });

		    // 댓글 숨김
		    $("#comments-list").on("click", ".comment-hide", function() {
		        let productCommentNo = $(this).closest('li.comment').data('cno');
		        console.log('productCommentNo :' + productCommentNo);

		        $.ajax({
		            type: 'PUT',
		            url: "<c:url value="/productcomment/hide"/>/" + productCommentNo,
		            dataType: 'text',
		            success: function() {
		                const commentLi = $('li[data-cno="' + productCommentNo + '"]');
		                commentLi.find('p.mb-0').text("Blind 되었습니다.");
		                commentLi.find('.comment-product').remove();
		                commentLi.find('.comment-hide').remove();
		                showList(productId, pageNum);
		            },
		            error: function(err) {
		                console.error("댓글 상태 변경에 실패했습니다.", err.responseText);
		            }
		        });
		    });

		    // 댓글 등록
		    $("#submit-reply").click(function() {
		        let replyContent = $("#reply").val();

		        if (replyContent.trim() == '') {
		            alert('댓글을 입력해주세요');
		            $('#reply').focus();
		            return;
		        }
				/* '/GRGR/infocomment/write?infoBno=' + infoBno */
		        $.ajax({
		            type: 'POST',
		            url: "<c:url value="/productcomment/write"/>/"+productId,
		            headers: {
		                'Content-Type': 'application/json'
		            },
		            data: JSON.stringify({
		            	productId: productId,
		                productCommentContent: replyContent,
		            }),
		            success: function(data) {
		              //  alert(data);
		                showList(productId, pageNum);
		                $('#reply').val('');
		                
		            },
		            error: function(err) {
		                console.error("댓글을 불러오는데 실패했습니다.", err.responseText);
		            }
		        });
		    });
		});
	</script>

	<%--
    // minInput과 maxInput 요소 가져오기
    const quantityInput = document.getElementById("quantityInput");

    // minInput과 maxInput 요소의 값을 productStock으로 설정
    quantityInput.min = "5"; // 최소값은 1로 설정
    quantityInput.max = "20"; // 최대값은 productStock 값으로 설정
</script>
--%>
	<!-- JavaScript 코드 -->


</body>
</html>