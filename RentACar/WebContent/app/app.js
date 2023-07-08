const Homepage = { template: '<homepage></homepage>' }
const Login = { template: '<login></login>' }
const Register = { template: '<register></register>' }
const Manager = {template: '<manager></manager>'}
const Customer = {template: '<customer></customer>'}
const Admin = {template: '<admin></admin>'}

const router = new VueRouter({
	  mode: 'hash',
	  routes: [
		  { 
		    path: '/', 
		    component: Homepage
		  },
		  { 
		    	path: '/login', 
		    	component: Login, 
		    	beforeEnter: (to, from, next) => {
		    		if(isLogged()){
		    			next("/"+getCurrentRole());
		    		} else {
		    			next();
		    		}
		         }
		    },
		    { 
		    	path: '/register', 
		    	component: Register,  
		    	beforeEnter: (to, from, next) => {
		    		if(isLogged()){
		    			next("/"+getCurrentRole());
		    		} else {
		    			next();
		    		}
		         }
		    },
		    { 
		    	path: '/manager', 
		    	component: Manager,
		    	beforeEnter: (to, from, next) => {
		    		if(loggedAs("MANAGER")){
		    			next();
		    		} else {
		    			next("/login");
		    		}
		         }
		    },
		    { 
		    	path: '/customer', 
		    	component: Customer,
		    	beforeEnter: (to, from, next) => {
		    		if(loggedAs("CUSTOMER")){
		    			next();
		    		} else {
		    			next("/login");
		    		}
		         }
		    },
		    { 
		    	path: '/admin', 
		    	component: Admin,
		    	beforeEnter: (to, from, next) => {
		    		if(loggedAs("ADMIN")){
		    			next();
		    		} else {
		    			next("/login");
		    		}
		         }
		    }
		  ]
});

var app = new Vue({
	router,
	el: '#appContent'
});

function getCurrentRole(){
	let user = JSON.parse(localStorage.user);
	return user.role.toLowerCase();
}

function isLogged(){
	let userString = localStorage.user?localStorage.user:"";
	if(userString !=""){
		return true;
	}
	return false;
}

function loggedAs(type) {
	let userString = localStorage.user?localStorage.user:"";
	if(userString !=""){
		let user = JSON.parse(localStorage.user);
		if(user.role == type){
			return true;
		}
		return false;
	}
	return false;
}