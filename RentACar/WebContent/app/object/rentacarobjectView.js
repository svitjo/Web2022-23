Vue.component("rentacarobject-view", {
	data: function () {
		    return {
		    	rentacarobject: {
		    		id: "",
		    		objectName: "",
		    		vehicle: null,
		    		objectStatus: null,
		    		location: null,
		    		logo: ""
		    	},
		    }
	},
mounted() {
		this.rentacarobject.id=this.$route.params.id;
		console.log(this.rentacarobject);
		axios
		.post("rest/rentacarobject/getById",this.rentacarobject )
		.then(response => {
			this.rentacarobject = response.data;
			console.log(this.rentacarobject);
		});
		},
template: ` 
<div class="container py-5">
    <div class="row featurette">
        <div class="col-md-6">
            <h2 class="featurette-heading">{{rentacarobject.objectName}}</h2>
            <p style="color: blue">{{rentacarobject.objectStatus}}</p>
            <p class="lead">{{rentacarobject.location.streetName}}, {{rentacarobject.location.city}} {{rentacarobject.location.zipCode}}</p>
            <p class="lead text-muted">{{rentacarobject.location.longitude}}, {{rentacarobject.location.latitude}}</p>
        </div>
        <div class="col-md-6">
            <img v-bind:src="rentacarobject.logo" class="bd-placeholder-img bd-placeholder-img-lg featurette-image img-fluid" width="500" height="500" alt="Rent-a-Car Object Image">
        </div>
    </div>
    <hr class="featurette-divider">
    <ul class="nav nav-tabs" id="rentacarobjectTab" role="tablist">
        <li class="nav-item" role="presentation">
            <button class="nav-link active" id="vehicle-tab" data-bs-toggle="tab" data-bs-target="#vehicles" type="button" role="tab" aria-controls="products" aria-selected="true">Vehicles</button>
        </li>
        <li class="nav-item" role="presentation">
            <button class="nav-link" id="comments-tab" data-bs-toggle="tab" data-bs-target="#comments" type="button" role="tab" aria-controls="comments" aria-selected="false">Comments</button>
        </li>
    </ul>
    <div class="tab-content" id="rentacarobjectTabContent">
        <div class="tab-pane fade show active" id="vehicles" role="tabpanel" aria-labelledby="vehicles-tab">Vehicles</div>
        <div class="tab-pane fade" id="comments" role="tabpanel" aria-labelledby="comments-tab">Comments</div>
    </div>
</div>

`
});