function validateAnnuncioForm() {
	form = document.forms["annuncioForm"];

	testoAnnuncio = form["testoAnnuncioInputId"];
	if (isBlank(testoAnnuncio.value)) {
		testoAnnuncio.focus();
		return false;
	}
	
	prezzo = form["prezzoInputId"];
	if (isBlank(prezzo.value)) {
		prezzo.focus();
		return false;
	}
}

function isBlank(string) {
	return !string || !string.trim();
}