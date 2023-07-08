const Homepage = { template: '<homepage></homepage>' }

const router = new VueRouter({
	  mode: 'hash',
	  routes: [
		  { 
		    path: '/', 
		    component: Homepage
		  }]
});

var app = new Vue({
	router,
	el: '#appContent'
});
