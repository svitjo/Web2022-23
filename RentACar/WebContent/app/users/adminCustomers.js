Vue.component("admin-customers", {
	data: function () {
	    return {
	    	customers:null,
			errorMessage:"",
	    }
},
mounted() {
	axios.get("rest/user/allUsersByRole?role=CUSTOMER")
	  .then(response => {
	    this.customers = response.data;
	  });
},
template: ` 
	<div class="container">
		<div class="row">
			<div class="col-md-12 text-center mb-3 mt-3"><h1>Customers</h1></div>
			<!-- Button trigger add modal -->
			<table class="table table-bordered" style="background-color: white; opacity: 0.9;">
				<thead>
				<tr style="background-color: #212529; text-align: center; color: white;">
					<th>Fullname</th>
					<th>Username</th>
					<th>Password</th>
					<th>Gender</th>
					<th>Birthday</th>
					<th>Shopping Cart</th>
					<th>Points</th>
					<th>Type</th>
					<th>Orders</th>
				</tr>
				</thead>
				<tbody>
				<tr v-for="customer in customers">
					<td>{{customer.firstname}} {{customer.lastname}}</td>
					<td>{{customer.username}}</td>
					<td>{{customer.password}}</td>
					<td>{{customer.gender}}</td>
					<td>{{customer.birthday}}</td>
					<td>{{customer.shoppingCart}}</td>
					<td>{{customer.numberOfPoints}}</td>
					<td>{{customer.typeOfCustomer}}</td>
					<td>{{customer.allOrders}}</td>
				</tr>
				</tbody>
			</table>
		</div>
	</div>
 `
})