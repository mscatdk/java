<div class="panel panel-info">
	<div class="panel-heading">
		<h3 class="panel-title">Account Details</h3>
	</div>
	<div class="panel-body">
		<div>
			<table class="table table-striped table-hover table-condensed" se-id="accountDetailsTable">
				<tr>
					<th>Account Number</th>
					<th>Type</th>
					<th class="text-right" style="padding-right: 100px; white-space:nowrap;">Balance</th>
					<th>Status</th>
				</tr>
				<c:if test="${!empty accountList}">
					<c:forEach var="account" items="${accountList}">
						<div>
							<tr onclick="window.document.location='${pageContext.request.contextPath}/movementOverview?customerNumber=${customerNumber}&accountNumber=${account.accountNumber}';">
								<td>${account.accountNumber}</td>
								<td>${account.accountTypeFormatted}</td>
								<td class="text-right" style="padding-right: 100px; white-space:nowrap;">${account.balanceFormated }</td>
								<td>${account.accountStatusFormatted}</td>
							</tr>
						</div>
					</c:forEach>
				</c:if>
			</table>
		</div>
	</div>
</div>