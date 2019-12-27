<div class="row">
	<div class="col-sm-8">
		<div class="panel panel-info">
			<div class="panel-heading">
				<h3 class="panel-title">Money Transfer</h3>
			</div>
			<div class="panel-body">
				<form class="form-horizontal" action="moneyTransfer" method="post">
				  <div class="form-group">
				    <label class="control-label col-sm-3" for="fromAccount">From Account</label>
				    <div class="col-sm-9">
				      <select class="form-control" name="fromAccount" id="fromAccount">
				     	<c:if test="${!empty accountNumbers}">
							<c:forEach var="accountNumber" items="${accountNumbers}"> 	
				      			<option>${accountNumber}</option>
				      		</c:forEach>
						</c:if>
				      </select>
				    </div>
				  </div>
				  <div class="form-group">
				    <label class="control-label col-sm-3" for="toAccount">To Account</label>
				    <div class="col-sm-9">
						<input type="text" name="toAccount" class="form-control" id="toAccount" aria-describedby="To account">
				    </div>
				  </div>
				  <div class="form-group">
				    <label class="control-label col-sm-3" for="amount">Amount</label>
				    <div class="col-sm-9">
				      <div class="input-group">
				        <span class="input-group-addon">$</span>
				        <input type="number" name="amount" value="0" min="0" step="0.01" data-number-to-fixed="2" data-number-stepfactor="100" class="form-control currency" id="amount" />
				      </div>
				    </div>
				  </div>
				  <div class="col-sm-10"></div>
				  	<div class="form-group">
					  <button type="submit" class="btn btn-success">Commit</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>