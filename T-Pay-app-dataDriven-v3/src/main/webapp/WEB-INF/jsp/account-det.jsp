<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>T-pay</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" />
</head>
<body class="container">
	<hr />
	<h1>T-pay</h1>
	<hr />

	<hr />

	<ul class="nav nav-pills">
		<li class="nav-item"><a class="nav-link" href="home">Home</a></li>
		<li class="nav-item"><a class="nav-link" href="txr">Pay</a></li>
		<li class="nav-item"><a class="nav-link" href="accounts">Accounts</a>
		</li>
		<li class="nav-item"><a class="nav-link" href="logout">logout</a>
		</li>
	</ul>

	<hr />


	<div class="jumbotron">
		<table class="table table-bordered">
			<tr>
				<th>Account</th>
				<th>Balance</th>
			</tr>
			<tr>
				<td>1</td>
				<td>${account.balance}</td>
			</tr>
			<tr>
				<td>2</td>
				<td>${otherAccount.balance}</td>
			</tr>
		</table>
	</div>


</body>
</html>