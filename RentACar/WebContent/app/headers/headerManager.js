Vue.component("manager-header", {
	data: function () {
		    return {
		      products: null,
		      isManager: true,
		    }
	},
	template: ` 
<header v-bind:class="{ bgManager: isManager }" class="navbar navbar-dark sticky-top flex-md-nowrap p-0 shadow">
  <a class="navbar-brand col-md-2 me-0 px-3" href="/RentACar/#/manager">
    Rent a Car
  </a>
  <div class="col-md-10 d-flex justify-content-end align-items-center">
    <input class="form-control form-control-dark me-3" type="text" placeholder="Search" aria-label="Search">
    <a class="nav-link px-3" href="/RentACar/#/login" v-on:click="logout()">Sign out</a>
  </div>
</header>
`,
mounted() {
		let loggedUser = JSON.parse(localStorage.user);
		this.isManager = loggedUser.role=="MANAGER"?true:false;
	},
	methods: {
		logout: function () {
			localStorage.user = "";
		}
	}
});