<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>맛집 리스트</title>
<!-- Meta -->
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="Soft UI - Neumorphism Style UI Kit" />
<meta name="author" content="kingstudio.ro" />
<!-- Favicon -->
<link rel="icon"
	href="${pageContext.request.contextPath}/assets/images/favicon.png" />
<!-- Site Title -->
<title>Soft UI - Neumorphism Style UI Kit</title>
<!-- Bootstrap 4 core CSS -->
<link
	href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css"
	rel="stylesheet" />
<!-- Custom Styles -->
<link href="${pageContext.request.contextPath}/assets/css/animate.css"
	rel="stylesheet" />
<link href="${pageContext.request.contextPath}/assets/css/style.css"
	rel="stylesheet" />
<!-- Fonts -->
<link
	href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;600;800&display=swap"
	rel="stylesheet" />
<link
	href="https://fonts.googleapis.com/css2?family=Nunito+Sans:wght@300;600;800&display=swap"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/assets/css/fontawesome-all.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=14d52cf2170b402e29c245507937cea1"></script>
	
</head>
<body>

	<!-- 헤더 -->
<jsp:include page="/WEB-INF/views/tiles/header.jsp" />

<!-- / top -->
<div class="container">
    <h1 class="mt-5">맛집 리스트</h1>
    <hr>
	<div id="map" style="width: 100%; height: 500px;"></div>
	<hr>
    <div class="mb-4">
    
    	
    
        <button class="btn btn-primary" data-category="전체">전체</button>
        <button class="btn btn-primary" data-category="한식">한식</button>
        <button class="btn btn-primary" data-category="일식">일식</button>
        <button class="btn btn-primary" data-category="중식">중식</button>
        <button class="btn btn-primary" data-category="양식">양식</button>
        <button class="btn btn-primary" data-category="기타">기타</button>
        <!-- 다른 카테고리 버튼들 추가 -->
    </div>
    <div class="row mt-4">
        <c:forEach items="${matzibList}" var="matzib" varStatus="loop">
            <div class="col-md-4 mb-4" data-category="${matzib.category_name.split(" > ")[1]}">
                <div class="card">
                    <div class="card-body">

                        <h5 class="card-title">${matzib.place_name}</h5>
                        <p class="card-text">카테고리: ${matzib.category_name.split(" > ")[1]}</p>
                        <p class="card-text">전화번호: ${matzib.phone}</p>
                        <p class="card-text">주소: ${matzib.address_name}</p>
                        <p class="card-text">x: ${matzib.x}</p>
                        <p class="card-text">y: ${matzib.y}</p>
                        <a href="#" class="btn btn-primary" data-toggle="modal"
                            data-target="#linkModal_${loop.index}">상세 정보 보기</a>
                    </div>
                </div>
            </div>
            <div class="modal fade" id="linkModal_${loop.index}" tabindex="-1" role="dialog"
                aria-labelledby="linkModalLabel_${loop.index}" aria-hidden="true">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content" width="1100">
                        <div class="modal-header">
                            <h5 class="modal-title" id="linkModalLabel_${loop.index}">상세 정보 보기</h5>
                            <button type="button" class="close" data-dismiss="modal"
                                aria-label="닫기">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body" width="1000" height="1000">
                            <iframe src="${matzib.place_url}" width="1000" height="1000"></iframe>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<!-- footer 영역 추가-->
<jsp:include page="/WEB-INF/views/tiles/footer.jsp" />


<script
    src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script
    src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<!-- jQuery UI 라이브러리 추가 -->
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
$(document).ready(function() {
    
    $(".modal-content").draggable({
        handle: ".modal-header" // 드래그 핸들로 모달 헤더를 사용
    });
});
</script>

<script>
var matzibData = [
    <c:forEach items="${matzibList}" var="matzib" varStatus="loop">
        {
            name: "${matzib.place_name}",
            category: "${matzib.category_name.split(" > ")[1]}",
            phone: "${matzib.phone}",
            address: "${matzib.address_name}",
            x: ${matzib.x},
            y: ${matzib.y}
        },
    </c:forEach>
];
</script>

<!-- 카카오 맵 초기화 및 업체 마커 표시 -->
<script>
$(document).ready(function () {
    var mapContainer = document.getElementById('map'); // 지도를 표시할 div
    var map;
    var userMarker; // 현재 위치를 표시할 마커

    // 모든 업체 마커를 담을 배열
    var markers = [];

    // 카테고리 필터링 함수
    function filterCategory(category) {
    // 모든 카드와 마커 숨기기
    $(".col-md-4").hide();
    markers.forEach(function (marker) {
        marker.setMap(null);
    });

    // 선택한 카테고리의 카드만 표시하고 해당하는 마커만 지도에 표시
    if (category === "전체") {
        $(".col-md-4").show();
        markers.forEach(function (marker, index) {
            marker.setMap(map);
        });
    }  else if (category === "기타") {
        // 한식, 양식, 일식, 중식을 제외한 나머지 카테고리만 표시
        $(".col-md-4[data-category!='한식'][data-category!='양식'][data-category!='일식'][data-category!='중식']").show();
        markers.forEach(function (marker, index) {
            if (matzibData[index].category !== "한식" &&
                matzibData[index].category !== "양식" &&
                matzibData[index].category !== "일식" &&
                matzibData[index].category !== "중식") {
                marker.setMap(map);
            }
        });
    } 
    else {
        $(".col-md-4[data-category='" + category + "']").show();
        markers.forEach(function (marker, index) {
            if (matzibData[index].category === category) {
                marker.setMap(map);
            }
        });
    }
}

    // 현재 위치를 받아와 지도에 표시하는 함수
    function showUserLocation(position) {
        var latitude = position.coords.latitude; 
        var longitude = position.coords.longitude; 

        var userMarkerPosition = new kakao.maps.LatLng(latitude, longitude);

        // 현재 위치 마커 생성 및 지도에 표시
        userMarker = new kakao.maps.Marker({
            position: userMarkerPosition,
            image: new kakao.maps.MarkerImage(
            		'${pageContext.request.contextPath}/images/now_location_marker.png', 
                new kakao.maps.Size(65, 65), // 마커 이미지 크기
                {
                    offset: new kakao.maps.Point(17, 47)
                }
            )
        });

        userMarker.setMap(map);

        // 지도 중심을 현재 위치로 이동
        map.setCenter(userMarkerPosition);
    }

    // 초기 지도 설정과 업체 마커 표시
    function initializeMap() {
        var mapOption = {
            center: new kakao.maps.LatLng(37.566826, 126.9786567),
            level: 5
        };

        // 지도 생성
        map = new kakao.maps.Map(mapContainer, mapOption);

        // 업체 마커 생성 및 표시
        matzibData.forEach(function (data, index) {
            var markerPosition = new kakao.maps.LatLng(data.y, data.x);

            var marker = new kakao.maps.Marker({
                position: markerPosition,
                clickable: true,
                title: data.name
            });

            markers.push(marker);

            // 초기에는 모든 마커를 지도에 표시
            marker.setMap(map);

            marker.addListener('click', function () {
                $('#linkModal_' + index).modal('show');
            });
        });
    }

    // 페이지 로드 시 초기 지도 설정과 업체 마커 표시
    initializeMap();

    // 현재 위치를 받아와서 표시
    if ("geolocation" in navigator) {
        navigator.geolocation.getCurrentPosition(showUserLocation);
    } else {
        alert("Geolocation을 지원하지 않는 브라우저입니다.");
    }

    // 카테고리 버튼 클릭 이벤트 처리
    $("button.btn-primary").click(function () {
        var category = $(this).data("category");
        
        
        filterCategory(category);
    });
});

</script>
</body>
</html>