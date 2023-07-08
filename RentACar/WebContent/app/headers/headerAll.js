Vue.component("main-header", {
	data: function () {
		    return {
		      products: null
		    }
	},
	template: ` 
<div class="header-container bg-white">
  <div class="container bg-white">
    <header class="d-flex flex-wrap align-items-center justify-content-between py-3 mb-4">
      <a href="/RentACar/#/" class="d-flex align-items-center text-dark text-decoration-none">
        <h3>Rent a Car</h3>
      </a>
      <div class="col-md-auto text-end">
        <a type="button" href="/RentACar/#/login" class="btn btn-outline-primary me-2">Login</a>
        <a type="button" href="/RentACar/#/register" class="btn btn-primary">Sign-up</a>
      </div>
    </header>
  </div>
</div>  
`
});