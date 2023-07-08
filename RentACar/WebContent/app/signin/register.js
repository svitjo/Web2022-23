Vue.component("register", {
	data: function () {
		    return {
		    	username: "",
				password: "",
				confirmPassword: "",
				firstname: "",
				lastname: "",
				gender: "",
				birthday: null,
				errorMessage:"",
		    }
	},
	template: ` 
<div>
  <div class="min-cover">
    <main-header></main-header> 
    <main class="form-signin">
      <div id="registerForm">
        <h1 class="h3 mb-3 fw-normal">Create New Account</h1>

        <div class="form-floating mb-3">
          <input type="text" class="form-control" name="username" v-model="username" placeholder="Username">
          <label for="floatingInput">Username</label>
        </div>

        <div class="form-floating mb-3">
          <input type="password" class="form-control" name="password" v-model="password" placeholder="Password">
          <label for="floatingPassword">Password</label>
        </div>
        
        <div class="form-floating mb-3">
		  <input type="password" class="form-control" name="confirm_password" v-model="confirmPassword" placeholder="Confirm Password">
		  <label for="floatingPassword">Confirm Password</label>
		</div>

        <div class="form-floating mb-3">
          <input type="text" class="form-control" name="firstname" v-model="firstname" placeholder="Firstname">
          <label for="floatingInput">Firstname</label>
        </div>

        <div class="form-floating mb-3">
          <input type="text" class="form-control" name="lastname" v-model="lastname" placeholder="Lastname">
          <label for="floatingInput">Lastname</label>
        </div>

        <div class="form-floating mb-3">
          <select class="form-control" id="genderSelect" name="gender" v-model="gender" placeholder="Gender">
            <option value="1">Male</option>
            <option value="2">Female</option>
          </select>
          <label for="floatingInput">Gender</label>
        </div>

        <div class="form-floating mb-3">
          <input type="date" class="form-control" name="birthday" v-model="birthday" placeholder="Birthday">
          <label for="floatingInput">Birthday</label>
        </div>

        <span class="errorMessage">{{errorMessage}}</span>
        <button class="w-100 btn btn-lg btn-primary mt-3" type="button" v-on:click="checkForm()">Sign in</button>
      </div>
    </main>  
  </div>  
</div>

`
	,
	methods: {
		checkForm: function () {
			if(this.username == "" || this.password == "" || this.confirmPassword == "" || this.firstname == "" || this.lastname == "" || this.gender == "" || this.birthday == null || this.birthday == ""){
			    this.errorMessage = "Popuni sva polja!";
			} else if(this.password != this.confirmPassword) {
			    this.errorMessage = "Lozinke se ne podudaraju!";
			} else {
				this.errorMessage = "";
				var birthdayInMS = new Date(this.birthday).getTime();
				var selGender = document.getElementById("genderSelect");
				var genderString = selGender.options[selGender.selectedIndex].text;
				var s = {username:this.username, password:this.password, firstname:this.firstname, lastname:this.lastname, gender:genderString, birthday:birthdayInMS, role:"CUSTOMER"};
				axios
				.post("rest/user/register", s)
				.then(response => {
					if(response.data==""){
						this.errorMessage = "Username već postoji!";
					} else {
						this.errorMessage = "";
						localStorage.user =JSON.stringify(response.data);
						window.location.href = '/RentACar/#/customer';
					}
				});
			}
		}
	},
	
	filters: {
		dataFormat: function (value, format) {
			var parsed = moment(value);
			return parsed.format(format);
		}
	},
	});

function fixDate(users) {
	for (var u of users) {
		u.birthday = new Date(parseInt(u.birthday));
		console.log(u.birthday);
	}
	
	return users;
}