Vue.component("main-header", {
	data: function () {
		    return {
		    	userLogged: false
		    }
	},
	template: ` 
<div class="header-container bg-white">
<div class="container bg-white">
    <header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4">
      <a href="/RentACar/#/" class="d-flex align-items-center col-md-3 mb-2 mb-md-0 text-dark text-decoration-none">
      <h3>Rent A Car</h3>
      </a>
      <div class="col-md-3 text-end" v-if="!userLogged">
        <a type="button" href="/RentACar/#/login" class="btn btn-outline-primary me-2">Login</a>
        <a type="button" href="/RentACar/#/register" class="btn btn-primary">Sign-up</a>
      </div>
      <div class="col-md-3 text-end" v-if="userLogged">
        <a type="button" href="/RentACar/#/login" v-on:click="logout()" class="btn btn-primary">Log out</a>
      </div>
    </header>
</div>  
</div>  
`,
	mounted() {
		let user = localStorage.user?localStorage.user:"";
		this.userLogged = user==""?false:true;
	},
	
	methods: {
		logout: function () {
			localStorage.user = "";
		}
	}
});