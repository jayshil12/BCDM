/**
 * 
 */
function handleUserTypeSelection() {
	let user_type = document.getElementById("user_type").value;
	let staffSection = document.getElementById("staff_fields");
	let studentSection = document.getElementById("student_fields");
	
	if (user_type === "staff") {
		staffSection.classList.toggle("d-none");
		studentSection.classList.add("d-none");
	} else if (user_type === "student") {
		staffSection.classList.add("d-none");
		studentSection.classList.toggle("d-none");
	} else {
		staffSection.classList.add("d-none");
		studentSection.classList.add("d-none");
	}
}

//this function handles the submission of the form
function handleSubmit() {
	let allAreFilled = true;
	
	const listOfFields = document.getElementById("registration_form").querySelectorAll("[required]");
	
	for (let i=listOfFields.length-1; i >= 0; i--) {
		if (!listOfFields[i].checkValidity()) {
			listOfFields[i].reportValidity();
			allAreFilled = false;
		}
	}
	
	if (allAreFilled) {
		if (document.getElementById("password-1").value === document.getElementById("password-2").value) {
			document.getElementById("registration_form").submit();
		} else {
			alert("Password does not match");
		}
		
	}
}

function showErrorMessage(message) {
	let flag = confirm(message);
	let rootFolderUri = getContextPath(window.location.pathname);
	
	if (flag) {
		window.location.href = rootFolderUri + "/login/login.jsp";
	} else {
		window.location.href = rootFolderUri + "/registration/createuser.jsp";
	}
}

function getContextPath(url) {
   return url.substring(0, url.indexOf("/",2));
}