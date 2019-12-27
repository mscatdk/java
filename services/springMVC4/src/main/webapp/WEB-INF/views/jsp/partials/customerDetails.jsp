<div class="panel panel-info">
	<div class="panel-heading">
		<h3 class="panel-title">Customer Details</h3>
	</div>
	<div class="panel-body">
		<div>
			<table class="table table-user-information">
				<tbody>
					<tr>
						<td>Name:</td>
						<td>${customer.name}</td>
					</tr>
					<tr>
						<td>Customer Number:</td>
						<td>${customer.customerNumber}</td>
					</tr>
					<tr>
						<td>Date of Birth:</td>
						<td>${customer.dateFormated}</td>
					</tr>

					<tr>
					<tr>
						<td>Gender:</td>
						<td>${customer.gender}</td>
					</tr>
					<tr>
						<td>Home Address:</td>
						<td>${customer.homeAddress}</td>
					</tr>
					<tr>
						<td>Email:</td>
						<td><a href="mailto:${customer.eMailAddress}">${customer.eMailAddress}</a></td>
					</tr>
					<tr>
					<td>Phone Number:</td>
					<td>${customer.phoneNumber }</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>