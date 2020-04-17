<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<title>ITMOA</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/font-awesome.min.css" rel="stylesheet">
<link href="css/animate.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<link href="css/style2.css" rel="stylesheet">
<link href="css/prettyPhoto.css" rel="stylesheet">
<!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
<script src="js/jquery.js"></script>
</head>
<style>
#join-content { margin-top: 100px; }
h2 {
  white-space: nowrap;
  width: 150px;
  height: 50px;
  text-align: center;
  margin: 40px auto;
  border-bottom: 3px solid #eb2b63;
}
th, td { white-space: nowrap; }
/* Customize the label (the container) */
.container {
  display: block;
  position: relative;
  padding-left: 35px;
  margin-bottom: 12px;
  cursor: pointer;
  font-size: 15px;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
}

/* Hide the browser's default checkbox */
.container input {
  position: absolute;
  opacity: 0;
  cursor: pointer;
  height: 0;
  width: 0;
}

/* Create a custom checkbox */
.checkmark {
  position: absolute;
  top: 0;
  left: 0%;
  height: 25px;
  width: 25px;
  background-color: #eee;
}

/* On mouse-over, add a grey background color */
.container:hover input ~ .checkmark {
  background-color: #ccc;
}

/* When the checkbox is checked, add a blue background */
.container input:checked ~ .checkmark {
  background-color: #eb2b63;
}

/* Create the checkmark/indicator (hidden when not checked) */
.checkmark:after {
  content: "";
  position: absolute;
  display: none;
}

/* Show the checkmark when checked */
.container input:checked ~ .checkmark:after {
  display: block;
}
input[type="text"] {
	padding: 0px 5px;
	border: 0.5px solid #cccccc;
	background: #hhhhhh;	
}
/* Style the checkmark/indicator */
.container .checkmark:after {
  left: 9px;
  top: 5px;
  width: 5px;
  height: 10px;
  border: solid white;
  border-width: 0 3px 3px 0;
  -webkit-transform: rotate(45deg);
  -ms-transform: rotate(45deg);
  transform: rotate(45deg);
  
}
input { border: 1px solid #333333; }
input[type="password"] {
	padding: 0px 5px;
	border: 0.5px solid #cccccc;
	background: #hhhhhh;	
}
input[class="addr-btn"] { 
	border: 0px;
	border-radius: 7px; 
	background-color: #eb2b63;
	color: white;
	width: 34%;
	height: 38px;
	margin: 5px;
}
textarea { border: 0px; resize: none; }
/* width */
textarea::-webkit-scrollbar { width: 10px; }
/* Track */
textarea::-webkit-scrollbar-track { background: #f1f1f1; }
/* Handle */
textarea::-webkit-scrollbar-thumb { background: #eb2b63; }
/* Handle on hover */
textarea::-webkit-scrollbar-thumb:hover { background: #eb2b63; }
th { text-align: right; width: 100px;}
#agreement { opacity: 0; }
#join-info { opacity: 0; }
#join-btn { width: 80%; margin: 0 auto; }
tr { height: 60px; }
table { margin-left: 5%; }
.text_1, .chkbox { margin-left: 25%; }
.chkbox {
	width: 320px;
	margin: 0px 0px 5% 45%; 
	text-align: center;
}
@media screen and (max-width: 1400px) {
	#agreement { margin-top: 0px; }
	#join-info { margin-top: 0px; }
	.text_1, .chkbox { margin: 0 auto; }
	table { margin-left: 17%; }
}
@media screen and (max-width: 1100px) {
	.text_1, .chkbox { margin: 0 auto; }
	table { margin: 12%; }
}
</style>
<!--/head-->
<body>

	<%
		String dto = (String) session.getAttribute("loginId");
	%>
	<%
		if (dto != null) {
	%>
	<script>
		alert("로그인 상태입니다");
		location.href = "loginIndex.do";
	</script>
	<%
		}
	%>

	<%
		if (dto == null) {
	%>
	<jsp:include page="topMenu.jsp" />
	<%
		}
	%>

	<script>
		// form 검증
		function chkSubmit() {
			frm = document.forms["frm"];

			var mb_id = frm["mb_id"].value.trim();
			var mb_pw = frm["mb_pw"].value.trim();
			var mb_pwOk = frm["mb_pwOk"].value.trim();
			var mb_email = frm["mb_email"].value.trim();
			var mb_zip = frm["mb_zip"].value.trim();
			var mb_add1 = frm["mb_add1"].value.trim();
			var mb_add2 = frm["mb_add2"].value.trim();
			var mb_name = frm["mb_name"].value.trim();

			if (mb_name == "") {
				alert("이름 입력해주세요");
				frm["mb_name"].focus();
				return false;
			}
			if (mb_id == "") {
				alert("아이디를 입력해주세요");
				frm["mb_id"].focus();
				return false;
			}
			if (mb_pw == "") {
				alert("비밀번호를 입력해주세요.");
				frm["mb_pw"].focus();
				return false;
			}
			if (mb_pwOk == "") {
				alert("비밀번호를 입력해주세요.");
				frm["mb_pwOk"].focus();
				return false;
			}
			if (mb_email == "") {
				alert("이메일을 입력해주세요.");
				frm["mb_email"].focus();
				return false;
			}
			if (mb_zip == "") {
				alert("우편번호를 입력해주세요.");
				frm["mb_zip"].focus();
				return false;
			}
			if (mb_add1 == "") {
				alert("주소를 입력해주세요.");
				frm["mb_add1"].focus();
				return false;
			}
			if (mb_add2 == "") {
				alert("주소를 입력해주세요.");
				frm["mb_add2"].focus();
				return false;
			}
			if (mb_pw != mb_pwOk) {
				alert("비밀번호를 제대로 입력해주세요");
				frm["mb_pwOk"].focus();
				return false;
			}

			return true;
		}
	</script>

	</header>
	<!--/header-->

	<form id="join-content" action="joinOk.do" name="frm" method="post" onsubmit="return chkSubmit()">

		<h2>회원 가입</h2>
		
		<div class="a col-sm-6" id="agreement">
			<div class="text_1">
				<jsp:include page="joinPolicy1.jsp"/><br>
			</div>
			<div class="chkbox">    			
    			<label class="container">아이티모아 이용약관 동의 (필수)
				  <input type="checkbox" checked="checked">
				  <span class="checkmark"></span>
				</label>
			</div>
			<div class="text_1">
				<jsp:include page="joinPolicy2.jsp"/><br>
			</div>
			<div class="chkbox">    			
    			<label class="container">개인정보 수집 및 이용에 대한 안내(필수)
				  <input type="checkbox" checked="checked">
				  <span class="checkmark"></span>
				</label>
			</div>
		</div>

		<div id="join-info" class="a col-sm-6">
			<div id="info1">
				<table>
					<tr>
						<th><label for="mb_name">이름</label></th>
						<td><input id="mb_name" class="info1" name="mb_name" type="text" placeholder="이름을 입력해주세요."></td>
					</tr>
					<tr>
						<th><label for="mb_id">아이디</label></th>
						<td><input id="mb_id" class="info1" name="mb_id" type="text" placeholder="아이디를 입력해주세요."></td>
					</tr>
					<tr>
						<span id="pw_policy">※ 영문 대문자, 숫자, 특수문자 조합 </span>
						<th><label for="mb_pw">비밀번호</label></th>
						<td><input id="mb_pw" class="info1" name="mb_pw" type="password" placeholder="비밀번호 를 입력해주세요."></td>
					</tr>
					<tr>
						<th><label for="mb_pwOk">비밀번호 확인</label></th>
						<td><input id="mb_pwOk" class="info1" name="mb_pwOk" type="password" placeholder="비밀번호를 입력해주세요."></td>
					</tr>
					<tr>
						<th><label for="mb_email">Email</label></th>
						<td><input id="mb_email" class="info1" name="mb_email" type="text" placeholder="이메일을 입력해주세요."></td>
					</tr>
					<tr>
						<th>주소</th>
						<td>
							<input type="text" id="sample6_postcode" name="mb_zip" placeholder="우편번호" style="float:left; width: 250px; height: 40px; border-radius: 7px; margin: 5px;" readonly="readonly">
							<input class="addr-btn" type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기">
						</td>
					</tr>
					<tr>
						<td></td>
						<td><input class="addr" type="text" id="sample6_address" name="mb_add1" placeholder="주소" readonly="readonly"></td>
					</tr>
					<tr>
						<td></td>
						<td><input class="addr" type="text" id="sample6_detailAddress" name="mb_add2" placeholder="상세주소"></td>
					</tr>
					<tr>
						<td></td>
						<td><input class="addr" type="text" id="sample6_extraAddress" style="display: none;" placeholder="상세주소"></td>
					</tr>
					<tr>
						<td></td>
						<td><input id="join-btn" type="submit" value="회원가입"></td>
					</tr>
				</table>
			</div>
		</div>
	</form>


	<script
		src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
		function sample6_execDaumPostcode() {
			new daum.Postcode(
					{
						oncomplete : function(data) {
							// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

							// 각 주소의 노출 규칙에 따라 주소를 조합한다.
							// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
							var addr = ''; // 주소 변수
							var extraAddr = ''; // 참고항목 변수

							//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
							if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
								addr = data.roadAddress;
							} else { // 사용자가 지번 주소를 선택했을 경우(J)
								addr = data.jibunAddress;
							}

							// 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
							if (data.userSelectedType === 'R') {
								// 법정동명이 있을 경우 추가한다. (법정리는 제외)
								// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
								if (data.bname !== ''
										&& /[동|로|가]$/g.test(data.bname)) {
									extraAddr += data.bname;
								}
								// 건물명이 있고, 공동주택일 경우 추가한다.
								if (data.buildingName !== ''
										&& data.apartment === 'Y') {
									extraAddr += (extraAddr !== '' ? ', '
											+ data.buildingName
											: data.buildingName);
								}
								// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
								if (extraAddr !== '') {
									extraAddr = ' (' + extraAddr + ')';
								}
								// 조합된 참고항목을 해당 필드에 넣는다.
								document.getElementById("sample6_extraAddress").value = extraAddr;

							} else {
								document.getElementById("sample6_extraAddress").value = '';
							}

							// 우편번호와 주소 정보를 해당 필드에 넣는다.
							document.getElementById('sample6_postcode').value = data.zonecode;
							document.getElementById("sample6_address").value = addr;
							// 커서를 상세주소 필드로 이동한다.
							document.getElementById("sample6_detailAddress")
									.focus();
						}
					}).open();
		}
	</script>

	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.prettyPhoto.js"></script>
	<script src="js/plugins.js"></script>
	<script src="js/init.js"></script>
	
	<script>
		$(document).ready(function() {
			$("#agreement").animate({
				opacity: 1
			}, 2000);
			$("#join-info").animate({
				opacity: 1
			}, 2000)
		})
	</script>
	
</body>
</html>