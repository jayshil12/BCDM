/**
 * 
 */
function handleLoginSubmit() {
	let allAreFilled = true;
	
	const listOfFields = document.getElementById("login_form").querySelectorAll("[required]");
	
	for (let i=listOfFields.length-1; i >= 0; i--) {
		if (!listOfFields[i].checkValidity()) {
			listOfFields[i].reportValidity();
			allAreFilled = false;
		}
	}
	
	if (allAreFilled) {
		document.getElementById("login_form").submit();
	}
}