Vue.component("admin-rentacarobjects", {
	data: function () {
	    return {
	    	rentacarobjects: null,
			errorMessage: "",
	    }
	},
	template: ` 
		<div class="container">
			<div class="row">
				<div class="col-md-12 text-center mb-3 mt-3"><h1>Rent A Car Objects</h1></div>
				<table class="table table-bordered" style="background-color: white; opacity: 0.9;">
					<thead>
						<tr style="background-color: #212529; text-align: center; color: white;">
							<th>Name</th>
							<th>Status</th>
							<th>Longitude</th>
							<th>Latitude</th>
							<th>Location</th>
							<th>Logo</th>
						</tr>
					</thead>
					<tbody>
						<tr v-for="rentacarobject in rentacarobjects">
							<td>{{ rentacarobject.objectName }}</td>
							<td>{{ rentacarobject.objectStatus }}</td>
							<td>{{ rentacarobject.location.longitude }}</td>
							<td>{{ rentacarobject.location.latitude }}</td>
							<td>{{ rentacarobject.location.streetName }}, {{ rentacarobject.location.city }} {{ rentacarobject.location.zipCode }}</td>
							<td>
								<img v-if="rentacarobject.logo !== ''" v-bind:src="rentacarobject.logo" alt="Rent-a-Car Object Image" width="40" height="40">
							</td>
						</tr>
						<tr v-if="rentacarobjects === null">
							<td colspan="6" class="text-center">Loading...</td>
						</tr>
						<tr v-else-if="errorMessage !== ''">
							<td colspan="6" class="text-center">{{ errorMessage }}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	`,
	mounted() {
		axios
		.get("rest/rentacarobject/allRentACarObjects")
		.then(response => {
			this.rentacarobjects = response.data;
		})
		.catch(error => {
			this.errorMessage = "Error retrieving rent-a-car objects.";
		});
	},
});
