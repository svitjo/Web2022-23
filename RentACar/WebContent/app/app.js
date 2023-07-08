const Homepage = { template: '<homepage></homepage>' }
const Login = { template: '<login></login>' }
const Register = { template: '<register></register>' }
const Manager = {template: '<manager></manager>'}
const ManagerHome = {template: '<manager-home></manager-home>'}
const Customer = {template: '<customer></customer>'}
const CustomerHome = {template: '<customer-home></customer-home>'}
const Admin = {template: '<admin></admin>'}
const AdminManagers = {template: '<admin-managers></admin-managers>'}
const AdminCustomers = {template: '<admin-customers></admin-customers>'}
const RentACarObjectView = {template: '<rentacarobject-view></rentacarobject-view>'}
const RentACarObjectViewWithHeader = {template: '<rentacarobject-view-wh></rentacarobject-view-wh>'}

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
	    		path: '/rentacarobject/:id',
	    		component:RentACarObjectViewWithHeader,
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
		    	children: [
		    		{
		    			path: '',
		    			component: ManagerHome
		    		}
		    	],
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
		    	children: [
		    		{
		    			path: '',
		    			component: CustomerHome
		    		}
		    	],
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
		    	children: [
		    		
		    		{
		    			path: 'managers',
		    			component:AdminManagers,
		    		},
		    		{
		    			path: 'customers',
		    			component:AdminCustomers,
		    		},
		    	],
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