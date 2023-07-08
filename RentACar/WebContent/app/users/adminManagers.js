Vue.component("admin-managers", {
	data: function () {
	    return {
	    	modal_username: "",
	    	modal_password: "",
	    	modal_firstname: "",
	    	modal_lastname: "",
	    	modal_gender: "",
	    	modal_birthday: null,
	    	modal_errorMessage:"",
	    	managers:null,
			errorMessage:"",
	    }
},
template: ` 
	<div class="container">
	<div class="row">
		<div class="col-md-12 text-center mb-3 mt-3"><h1>Managers</h1></div>
		<!-- Button trigger add modal -->
		<button type="button" style="font-weight: 700;" class="btn btn-primary mb-3 offset-md-10 col-md-2" data-bs-toggle="modal" data-bs-target="#addManagerModal">
		  Add
		</button>
		<table class="table table-bordered" style="background-color: white; opacity: 0.9;">
			<thead>
				<tr style="background-color: #212529; text-align: center; color: white;">
					<th>Fullname</th>
					<th>Username</th>
					<th>Password</th>
					<th>Gender</th>
					<th>Birthday</th>
				</tr>
			</thead>
			<tbody>
				<tr v-for="manager in managers">
					<td>{{manager.firstname}} {{manager.lastname}}</td>
					<td>{{manager.username}}</td>
					<td>{{manager.password}}</td>
					<td>{{manager.gender}}</td>
					<td>{{manager.birthday}}</td>
					<td>
        			</td>
				</tr>
			</tbody>
		</table>
	</div>
	<!-- Modal -->
		<div class="modal fade" id="addManagerModal" tabindex="-1" aria-labelledby="Modal" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title">Add manager</h5>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      <div class="modal-body">
		      <!--   MODAL FORM    -->
		        <div class="form-floating mb-2">
			      <input type="text" class="form-control" name="username" v-model="modal_username" placeholder="Username">
			      <label for="floatingInput">Username</label>
			    </div>
			    <div class="form-floating mb-2">
			      <input type="password" class="form-control" name="password" v-model="modal_password" placeholder="Password">
			      <label for="floatingPassword">Password</label>
			    </div>
			    <div class="form-floating mb-2">
			      <input type="text" class="form-control" name="firstname" v-model="modal_firstname" placeholder="Firstname">
			      <label for="floatingInput">Firstname</label>
			    </div>
			    <div class="form-floating mb-2">
			      <input type="text" class="form-control" name="lastname" v-model="modal_lastname" placeholder="Lastname">
			      <label for="floatingInput">Lastname</label>
			    </div>
			    <div class="form-floating mb-2">
			    <select class="form-control" id="genderSelect" name="gender" v-model="modal_gender" placeholder="Gender">
				    <option value="Male">Male</option>
				    <option value="Female">Female</option>
			     </select>
			     <label for="floatingInput">Gender</label>
			     </div>
			    <div class="form-floating mb-2">
			      <input type="date" class="form-control" name="birthday" v-model="modal_birthday" placeholder="Birthday">
			      <label for="floatingInput">Birthday</label>
			    </div>
		      <!--   MODAL FORM  END  -->
	    		<span class="errorMessage mr-3">{{errorMessage}}</span>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
		        <button type="button" class="btn btn-primary" v-on:click="checkForm()">Add</button>
		      </div>
		    </div>
		  </div>
		</div>
</div>
 `
	,
	mounted() {
	axios.get("rest/user/allUsersByRole?role=MANAGER")
	  .then(response => {
	    this.managers = response.data;
	  });
	},
	methods: {
		checkForm: function () {
			if(this.modal_username == "" || this.modal_password == "" || this.modal_firstname == "" || this.modal_lastname == "" || this.modal_gender == "" || this.modal_birthday == null || this.modal_birthday == ""){
				this.errorMessage = "All fields are required!";
			} else {
				this.errorMessage = "";
				var birthdayInMS = new Date(this.modal_birthday).getTime();
				var s = {username:this.modal_username, password:this.modal_password, firstname:this.modal_firstname, lastname:this.modal_lastname, gender:this.modal_gender, birthday:birthdayInMS, role:"MANAGER"};
				axios
				.post("rest/user/register", s)
				.then(response => {
					if(response.data==""){
						this.errorMessage = "Username already exists!";
					} else {
						this.errorMessage = "";
							axios
							.get("rest/user/allUsersByRole?role=MANAGER")
							.then(responseAllManagers => {
								this.managers = responseAllManagers.data;
								$("#addManagerModal").modal('hide');
							});
					}
				});
			}
		}
	}
})