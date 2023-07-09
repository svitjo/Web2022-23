Vue.component("manager-rentacarobject", {
	data: function() {
		return {
			rentacarobject: {
				id: "",
				objectName: "",
				vehicle: "",
				objectStatus: "",
				location: {
					longitude: "",
					latitude: "",
					streetName: "",
					city: "",
					zipCode: ""
				},
				logo: "",
			}
			
		}
	},
mounted () {
		let u = JSON.parse(localStorage.user);
		  axios
		    .get("rest/rentacarobject/getByManagerId", {
		      params: {
		        managerId: u.managerId
		      }
		    })
		    .then(response => {
		      this.rentacarobject = response.data;
		      console.log(this.rentacarobject);
		    })
		    .catch(error => {
		      console.error(error);
		    });
	},
template: `
<div class="container py-5">
	<div class="row featurette">
      <div class="col-md-7 order-md-2">
        <h2 class="featurette-heading">{{rentacarobject.objectName}} <br/></h2>
        <p style="color:blue" class="lead">{{rentacarobject.objectStatus}}</p>
        <p class="lead">{{rentacarobject.location.streetName}}</p>
        <p class="lead">{{rentacarobject.location.zipCode}} {{rentacarobject.location.city}}</p>
      </div>
      <div class="col-md-5 order-md-1">
         <img v-bind:src="rentacarobject.logo" class="bd-placeholder-img bd-placeholder-img-lg featurette-image img-fluid" width="500" height="500">
        </div>
      
    </div> 
    <hr class="featurette-divider">
    <ul class="nav nav-tabs" id="rentacarobjectTab" role="tablist">
	  <li class="nav-item" role="presentation">
	    <button class="nav-link active" id="vehicle-tab" data-bs-toggle="tab" data-bs-target="#vehicle" type="button" role="tab" aria-controls="vehicle" aria-selected="true">Vehicles</button>
	  </li>
	  <li class="nav-item" role="presentation">
	    <button class="nav-link" id="comments-tab" data-bs-toggle="tab" data-bs-target="#comments" type="button" role="tab" aria-controls="comments" aria-selected="false">Comments</button>
	  </li>
	</ul>
	<div class="tab-content" id="rentacarobjectTabContent">
	  <div class="tab-pane fade show active" id="vehicle" role="tabpanel" aria-labelledby="vehicle-tab">Vehicles</div>
	  <div class="tab-pane fade" id="comments" role="tabpanel" aria-labelledby="comments-tab">Comments</div>
	</div>
 </div>
 `
})