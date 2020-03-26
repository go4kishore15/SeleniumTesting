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
		<li class="nav-item">
			<a class="nav-link" href="home">Home</a>
		</li>
		<li class="nav-item">
			<a class="nav-link" href="txr">Pay</a>
		</li>
		<li class="nav-item"><a class="nav-link" href="accounts">Accounts</a>
		<li class="nav-item">
			<a class="nav-link" href="logout">logout</a>
		</li>
	</ul>

	<hr />

	<div class="row">
		<div class="col-5 col-sm-5 col-md-5">
			<form action="txr" method="post">
				<div class="card">
					<div class="card-header">Txr Form</div>
					<div class="card-body">
						<div class="form-group">
							<label>From Account:</label> 
							<input name="fromAccNum" class="form-control">
						</div>
						<div class="form-group">
							<label>To Account:</label> 
							<input name="toAccNum"class="form-control">
						</div>
						<div class="form-group">
							<label>Amount:</label> 
							<input name="amount" class="form-control">
						</div>
						<button class="btn btn-primary">Txr</button>
					</div>
				</div>
			</form>
		</div>
		<div class="col-5 col-sm-5 col-md-5">
		<table class="bg-info table table-bordered">
			<tr><th>Account</th><th>Balance</th></tr>
			<tr><td>1</td><td>${account.balance}</td></tr>
			<tr><td>2</td><td>${otherAccount.balance}</td></tr>
		</table>
		</div>
	</div>

</body>
</html>