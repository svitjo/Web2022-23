Vue.component("rentacarobject-view-wh", {
	data: function () {
		    return {
		    	rentacarobject: {
		    		id: "",
		    		name: "",
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
<div>
	<div class="min-cover">
		<main-header></main-header> 
		<rentacarobject-view></rentacarobject-view>
	</div>
</div>
`
});