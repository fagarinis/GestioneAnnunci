package it.gestioneannunci.model.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ValutaUtils {
	
	/**
	 * arrotonda una variabile double fino a n decimali
	 * @param places: numero decimali
	 * @return valore arrotondato
	 */
	public static Double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = BigDecimal.valueOf(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}

}
