<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<title>Biograf AB</title>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" type="text/css" href="/resources/css/style.css" />
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

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


				<li><a href="#popup1">Add Show</a></li>

				<div id="popup1" class="overlay">
					<div class="popup">
						<h2>Add Show</h2>
						<a class="close" href="#">&times;</a>
						<div class="content">
							<div>
								<form:form mehtod="post" action="/">

									<select name="movie">
									<c:forEach items="${movies}" var="movie">
									<option value="${movie.key}">${movie.value.name}</option>
									
									</c:forEach>
	
									</select>
									
									<br />

									<select name="theatre">
									<c:forEach items="${theatres}" var="theatre">
									<option value="${theatre.id}">${theatre.name}</option>
									
									</c:forEach>
	
									</select>

									<br />
									<input type="datetime-local" placeholder="Start date" name="starttime">
									<br />
									<input type="datetime-local" placeholder="End date" name="endtime">
									<br />
									<input type="submit" value="Submit">
								</form:form>
							</div>
						</div>
					</div>
				</div>





			</ul>
		</div>
		<div class="main">
			<c:forEach items="${shows}" var="show" varStatus="status">
				<div class="movie">
					<div class="poster">
						<img src="/resources/img/${movies[show.movieId].name}.jpg" />
					</div>
					<div class="labelText">
						<div class="label">${movies[show.movieId].name}</div>
						<div class="text">${movies[show.movieId].description}</div>
						<br />
						<div class="text">${show.start}-${show.end}</div>
					</div>
					<div class="book">
						<form:form type="seats" method="get" action="/booking/${show.id}"
							id="booking-form${show.id}">
					Antal platser<input type="seats" name="seats">
						</form:form>
						<button form="booking-form${show.id}" type="submit">Boka</button>
						<div class="text">Lediga platser:
							${availableSeats[status.index]}</div>
						<div class="text">Pris: 120kr</div>
					</div>
				</div>
			</c:forEach>

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