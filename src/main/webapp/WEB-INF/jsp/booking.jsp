<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<title>Biograf AB</title>
<link rel="stylesheet" type="text/css" href="/resources/css/style.css" />
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
<script>
	var seats = [];

	function clickSeat(row, col) {
		seats.push([ row, col ]);
		$("#seats").val(seats);
		console.log(seats);
	}

	$(document).ready(
			function() {
				$("input.seat").click(
						function() {
							clickSeat(parseInt($(this).parent().index()) + 1,
									parseInt($(this).index()) + 1);
						});
			});
</script>
</head>
<body>
	<div class="header">
		<h1>Biograf AB</h1>
	</div>
	<div class="container">
		<div class="nav">
			<ul>
				<li><a href="/">Hemsida</a></li>
				<c:forEach items="${theatres}" var="theatre">
					<li><a href="/salong/${theatre.name}">${theatre.name}</a></li>
				</c:forEach>
			</ul>
		</div>
		<div class="main">
			<div class="movie">
				<div class="poster">
					<img src="/resources/img/${movie.name}.jpg" />
				</div>
				<div class="labelText">
					<div class="label">${movie.name}</div>
					<div class="text">${movie.description}</div>
					<br />
					<div class="text">${show.start}- ${show.end}</div>
					<br />

					<div class="label">Boka biljett</div>

					<div class="booking-row">

						<div class="booking-col">
							---------Bioduk---------
							<c:forEach var="i" begin="1" end="5">
								<div class="row">
									<c:forEach var="j" begin="1" end="5">
<%-- 										<c:choose> --%>
<%-- 											<c:when test="${}"> --%>
<!--  												<input disabled type="checkbox" class="seat" name="bookedSeats" /> -->
<%-- 											</c:when> --%>
<%-- 											<c:otherwise> --%>
<!--         										<input type="checkbox" class="seat" name="bookedSeats" /> -->
<%-- 											</c:otherwise> --%>
<%-- 										</c:choose> --%>
									</c:forEach>
								</div>
							</c:forEach>

						</div>
						<div class="booking-col">
							Kund information
							<form:form mehtod="post" action="/booking">
								<input id="seats" name="seats" type="hidden">
								<input type="text" placeholder="Förnamn">
								<br />
								<input type="text" placeholder="Efternamn">
								<br />
								<input type="text" placeholder="E-post">
								<br />
								<input type="submit" value="Submit">
							</form:form>
						</div>

					</div>

				</div>
			</div>
		</div>
	</div>
	<div class="footer">
		<div>Webmaster: Karl-Gustav, Emil, Anders</div>
		<div>Copyright &copy; 2018 Biograf AB</div>
		<div
			style="margin-top: 10px; margin-bottom: 5px; border-bottom: 1px solid white;">Contact
			information</div>
		<div style="padding-right: 5px;">
			Email: <a style="color: white;" href="mailto:info@me.com">info@me.com</a>
		</div>
		<div style="padding-right: 5px;">
			Phone: <a style="color: white;" href="tel:0701234567">070-123 45
				67</a>
		</div>
	</div>
</body>
</html>