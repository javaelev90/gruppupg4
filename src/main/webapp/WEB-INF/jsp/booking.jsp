<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<title>Biograf AB</title>
<link rel="stylesheet" type"text/css" href="style.css" />
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
			<li><a href="#">Hemsida</a></li>
			<li><a href="#">Salong 1</a></li>
			<li><a href="#">Salong 2</a></li>
			<li><a href="#">Salong 3</a></li>
			<li><a href="#">Salong 4</a></li>
		</ul>
	</div>
	<div class="main">
		<div class="movie">
			<div class="poster">
				<img src="img/terminator.jpg" />
			</div>
			<div class="labelText">
				<div class="label">The Terminator</div>
				<div class="text">A seemingly indestructible android is sent
					from 2029 to 1984 to assassinate a waitress, whose unborn son will
					lead humanity in a war against the machines, while a soldier from
					that war is sent to protect her at all costs.</div>
				<br />
				<div class="label">Boka biljett</div>
				<form>
					Rad<input type="seats"> Column<input type="seats"><br />
					<br /> <input type="text" placeholder="Förnamn"><br />
					<br /> <input type="text" placeholder="Efternamn"><br />
					<br /> <input type="text" placeholder="E-post"><br />
					<br /> <input type="submit" value="Submit">
				</form>
			</div>
		</div>
	</div>
</div>
<div class="footer">
	<div>Webmaster: Karl-Gustav, Jesper, Tommy</div>
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