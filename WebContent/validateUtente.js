function validateUtenteForm() {
	form = document.forms["utenteForm"];

	document.getElementById("confermaPasswordTextId").innerHTML = "";
	document.getElementById("emailTextId").innerHTML = "";

	nome = form["nomeInputId"];
	if (isBlank(nome.value)) {
		nome.focus();
		return false;
	}
	cognome = form["cognomeInputId"];
	if (isBlank(cognome.value)) {
		cognome.focus();
		return false;
	}
	username = form["usernameInputId"];
	if (isBlank(username.value)) {
		username.focus();
		return false;
	}
	password = form["passwordInputId"];
	if (isBlank(password.value)) {
		password.focus();
		return false;
	}

	confermaPassword = form["confermaPasswordInputId"];
	if (confermaPassword != null) {
		if (isBlank(confermaPassword.value)) {
			confermaPassword.focus();
			return false;
		}

		if (!(confermaPassword.value === password.value)) {
			confermaPassword.focus();
			document.getElementById("confermaPasswordTextId").innerHTML = "La password di conferma non coincide";
			return false;
		}

	}

	email = form["emailInputId"];
	if (isBlank(email.value)) {
		email.focus();
		return false;
	}
	if (!isValidEmail(email.value)) {
		email.focus();
		document.getElementById("emailTextId").innerHTML = "Formato email non valido";
		return false;
	}

}

function isBlank(string) {
	return !string || !string.trim();
}

function isValidEmail(email) {
	return email
			.match("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
}
