Vue.component("admin", {
	data: function () {
		    return {
		      products: null
		    }
	},
	template: ` 
<div>
<link href="assets/css/admin.css" rel="stylesheet" type="text/css" />
	<div class="min-cover"> 
		<admin-header></admin-header>
		<div class="container-fluid">
		  <div class="row">
		    <nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
		      <div class="position-sticky pt-3">
		        <ul class="nav flex-column">
		        <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
		          <span>Manage entities</span>
		        </h6>
		          <li class="nav-item">
		            <a class="nav-link active" aria-current="page" href="/RentACar/#/admin">
		              Dashboard
		            </a>
		          </li>
		           <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
		          <span>My data</span>
		        </h6>
		        </ul>
		      </div>
		    </nav>
		    <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
				<router-view></router-view>
		    </main>
		  </div>
		</div>
	</div>
</div>
`
});
