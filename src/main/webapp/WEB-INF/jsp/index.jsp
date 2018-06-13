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
</head>
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
		<c:forEach items="${shows}" var="show" varStatus="status">
		<div class="movie">
			<div class="poster">
				<img src="/resources/img/${movies[status.index].name}.jpg" />
			</div>
			<div class="labelText">
				<div class="label">The Terminator</div>
				<div class="text">
					${movies[status.index].description}
				</div>
<!-- 				<div class="text">A seemingly indestructible android is sent -->
<!-- 					from 2029 to 1984 to assassinate a waitress, whose unborn son will -->
<!-- 					lead humanity in a war against the machines, while a soldier from -->
<!-- 					that war is sent to protect her at all costs.</div> -->
				<br />
<!-- 				<div class="text">2018-01-01 12:00 - 2018-01-01 14:00</div> -->
				<div class="text">${show.start} - ${show.end}</div>
			</div>
			<div class="book">
				<form:form type="seats" method="get" action="/booking/${show.id}" id="booking-form">
					Antal platser<input type="seats" name="seats">
				</form:form>
				<button form="booking-form" type="submit">Boka</button>
				<div class="text">Lediga platser: ${availableSeats[status.index]}</div>
				<div class="text">Pris: 120kr</div>
			</div>
		</div>
		</c:forEach>
<!-- 		<div class="movie"> -->
<!-- 			<div class="poster"> -->
<!-- 				<img src="img/scarface.jpg" /> -->
<!-- 			</div> -->
<!-- 			<div class="labelText"> -->
<!-- 				<div class="label">Scarface</div> -->
<!-- 				<div class="text">In Miami in 1980, a determined Cuban -->
<!-- 					immigrant takes over a drug cartel and succumbs to greed.</div> -->
<!-- 				<br /> -->
<!-- 				<div class="text">2018-01-01 12:00 - 2018-01-01 14:00</div> -->
<!-- 			</div> -->
<!-- 			<div class="book"> -->
<%-- 				<form type="seats"> --%>
<!-- 					Antal platser<input type="seats"><br> -->
<%-- 				</form> --%>
<!-- 				<button type="button">Boka</button> -->
<!-- 				<div class="text">Lediga platser: #</div> -->
<!-- 				<div class="text">Pris: 120kr</div> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="movie"> -->
<!-- 			<div class="poster"> -->
<!-- 				<img src="img/hittaNemo.jpg" /> -->
<!-- 			</div> -->
<!-- 			<div class="labelText"> -->
<!-- 				<div class="label">Hitta Nemo</div> -->
<!-- 				<div class="text">After his son is captured in the Great -->
<!-- 					Barrier Reef and taken to Sydney, a timid clownfish sets out on a -->
<!-- 					journey to bring him home.</div> -->
<!-- 				<br /> -->
<!-- 				<div class="text">2018-01-01 12:00 - 2018-01-01 14:00</div> -->
<!-- 			</div> -->
<!-- 			<div class="book"> -->
<%-- 				<form type="seats"> --%>
<!-- 					Antal platser<input type="seats"><br> -->
<%-- 				</form> --%>
<!-- 				<button type="button">Boka</button> -->
<!-- 				<div class="text">Lediga platser: #</div> -->
<!-- 				<div class="text">Pris: 120kr</div> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="movie"> -->
<!-- 			<div class="poster"> -->
<!-- 				<img src="img/tarzan.jpg" /> -->
<!-- 			</div> -->
<!-- 			<div class="labelText"> -->
<!-- 				<div class="label">The Legend of Tarzan</div> -->
<!-- 				<div class="text">Tarzan, having acclimated to life in London, -->
<!-- 					is called back to his former home in the jungle to investigate the -->
<!-- 					activities at a mining encampment.</div> -->
<!-- 				<br /> -->
<!-- 				<div class="text">2018-01-01 12:00 - 2018-01-01 14:00</div> -->
<!-- 			</div> -->
<!-- 			<div class="book"> -->
<%-- 				<form type="seats"> --%>
<!-- 					Antal platser<input type="seats"><br> -->
<%-- 				</form> --%>
<!-- 				<button type="button">Boka</button> -->
<!-- 				<div class="text">Lediga platser: #</div> -->
<!-- 				<div class="text">Pris: 120kr</div> -->
<!-- 			</div> -->
<!-- 		</div> -->
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
</html>