<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
	var seatsTogheter;
	var numberOfSeats;
	var seatArr = [];
	function increase() {

	  var el = parseInt($('#numberOfSeats').text());
	  $('#numberOfSeats').text(el+1);
	  numberOfSeats =el+1;
	}
	
	function decrease() {
		
	 var el = parseInt($('#numberOfSeats').text());
	 if(el > 1){
	  $('#numberOfSeats').text(el-1); 
	  numberOfSeats =el-1;
	 }

	}

	function clickSeat(row, col) {
		seatsTogheter = $('#seatsTogheter:checked').val();
		
		if(seatsTogheter){
			
			
			seats = [];
			$("#seats").val(seats);

			$("#seatList").find("input").prop("checked", false);
			//Check if number of seats are within bounds
			if(($('.row').first().children().length >= (col+numberOfSeats))){
				
					
				
				$("#seatList").find('[value=' + seatArr.join('], [value=') + ']').prop("checked", false);
				var nextCol;
				var nextRow;
				seatArr = [];
				seats = [];
				//Creates indexes for seats to select
				for(var i = 0; i < numberOfSeats; i++){
					nextCol = col+i;
					seatArr.push(row+""+nextCol);
					seats.push([row, nextCol]);
				}
				var seatsSelected = $("#seatList").find('[value=' + seatArr.join('], [value=') + ']');

				var disabled = $(seatsSelected).filter(function( index ) {
						    return $( this ).attr( "id" ) === "disabled";
							}).length;
				
				if(disabled === 0){
					$("#seats").val(seats);
					$("#seatList").find('[value=' + seatArr.join('], [value=') + ']').prop("checked", true);
				}


			}
		} else {
			seats.push([ row, col ]);
			$("#seats").val(seats);
		}
		
	}

	function resetBooking(){
		$("#seatList").find("[type=checkbox]").prop("checked", false);
		seats = [];
	}
	
	function canMakeBooking(){
		if(seats.length === 0){
			alert("Can't book 0 seats");
			return;
		}
		
		document.getElementById("submitForm").submit();
	}
	

	
	$(document).ready(
			
			function() {
				$("input.seat").click(
						function() {
							clickSeat(parseInt($(this).parent().index()),
									parseInt($(this).index()));
						});
				
				$('#seatsTogheter').click(function(){
					resetBooking();
				});		
				numberOfSeats = parseInt($('#numberOfSeats').text());
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
				<li><a href="/">All theatres</a></li>
				<c:forEach items="${theatres}" var="theatre">
					<li><a href="/theatre/${theatre.id}">${theatre.name}</a></li>
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
					<div class="text">${show.start}-${show.end}</div>
					<br />

					<div class="label">Book ticket</div>

					<div class="booking-row">
						<div class="booking-col">
							<div class="checkSeatsTogether"> <input type="checkbox" id="seatsTogheter" />Seats together</div>
							<button class="modifyNumSeats" onclick="increase()">+</button>
							<div id="numberOfSeats">1</div>
							<button class="modifyNumSeats" onclick="decrease()">-</button>
						</div>
						<div class="booking-col">
							<div id="seatList">
								---------Cinema screen---------
								<c:forEach var="i" begin="0"
									end="${theatres[0].getSEAT_ROWS()-1}">
									<div class="row">
										<c:forEach var="j" begin="0"
											end="${theatres[0].getSEAT_COLS()-1}">
											<c:set var="key" value="row${i}col${j}" />
											<c:choose>
												<c:when test="${not empty tickets[key]}">
													<input disabled id="disabled" type="checkbox" class="seat"
														value="${i}${j}" />
												</c:when>
												<c:otherwise>
													<input type="checkbox" class="seat" name="bookedSeats"
														value="${i}${j}" />
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</div>
								</c:forEach>
							</div>
						</div>
						<div class="booking-col">
<!-- 							Customer information -->
							<form:form mehtod="post" id="submitForm" action="/booking/${show.id}">
								<input id="seats" name="seats" type="hidden">
<!-- 								<input type="text" placeholder="First name"> -->
<!-- 								<br /> -->
<!-- 								<input type="text" placeholder="Last name"> -->
<!-- 								<br /> -->
<!-- 								<input type="text" placeholder="E-mail"> -->
<!-- 								<br /> -->

								<button id="submitButton" type="button" onclick="canMakeBooking()">Submit</button>
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