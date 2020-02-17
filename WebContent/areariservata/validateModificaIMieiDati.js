function validateUtenteForm() {

	form = document.forms["utenteForm"];

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

}

function isBlank(string) {
	return !string || !string.trim();
}