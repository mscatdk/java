<div class="panel panel-info">
	<div class="panel-heading">
		<h3 class="panel-title">Customer number: ${customerNumber} - Account number: ${accountNumber}</h3>
	</div>
	<div class="panel-body">
		<div>
			<table class="table table-user-information">
				<thead>
					<tr>
						<td>Movement Type</td>
						<td>From account</td>
						<td class="text-right" style="padding-right: 100px; white-space:nowrap;">Amount</td>
						<td>Date</td>
					</tr>
				</thead>
				<tbody>
					<c:if test="${!empty movementList}">
						<c:forEach var="movement" items="${movementList}">
		    				<tr>
		    					<td>${movement.movementTypeFormatted}</td>
		    					<td>${movement.fromAccount}</td>
		    					<td class="text-right" style="padding-right: 100px; white-space:nowrap;">${movement.amountFormated}</td>
		    					<td>${movement.dateOfEntryFormatted}</td>
		    				</tr>
		    				<c:out value="${window}"/> 
						</c:forEach>
					</c:if>
					<tr>
						<td>Balance</td>
						<td></td>
						<td class="text-right" style="padding-right: 100px; white-space:nowrap;">${account.balanceFormated}</td>
						<td><fmt:formatDate value="<%= new java.util.Date() %>" pattern="yyyy-MM-dd" /></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>