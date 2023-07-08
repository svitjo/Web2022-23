Vue.component("rentacarobjects", {
	data: function () {
		    return {
		    	rentacarobjects:null,
		    	route: ""
		    }
	},
mounted() {
		this.route = "/RentACar/#/";
		this.route += this.getCurrentRole()==""?"rentacarobject/":this.getCurrentRole()+"/rentacarobject/";
		axios
		.get("rest/rentacarobject/sortedRentACarObjects")
		.then(response => {
			this.rentacarobjects = response.data;
		});
		},
methods: {
			getCurrentRole : function(){
				let userString = localStorage.user;
				if(!userString || userString == "") {
					return "";
				} else {
					let user = JSON.parse(userString);
					return user.role.toLowerCase();
				}

				},
			getFullRoute : function(event) {
				console.log(event.target);
			}
			},
template: ` 
	<div>
	<div class="container py-5">
      <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
        <div class="col" v-for="rentacarobject in rentacarobjects">
          <div class="card shadow-sm">
          <img v-bind:src="rentacarobject.logo" class="bg-placeholder-img card-img-top" width="100%" height="225">
            <div class="card-body">
              <h2>{{rentacarobject.name}}</h2
              <div>
              <p style="margin-bottom: .1rem;"> {{rentacarobject.location.streetName}} </p>
              <p>{{rentacarobject.location.zipCode}} {{rentacarobject.location.city}}</p>
              </div>
              <div class="d-flex justify-content-between align-items-center">
                <div class="btn-group">
                   <a v-bind:href="route + rentacarobject.id" class="btn btn-sm btn-outline-secondary">View</a>
                </div>
               </div>
            </div>
          </div>
        </div>
      </div>
    </div>
	</div> 
`
});