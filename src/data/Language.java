package data;

import java.util.Locale;
import java.util.ResourceBundle;

public class Language {
	public static ResourceBundle texts;
	public Language(String countryName) {
		texts= ResourceBundle
	            .getBundle("bundles.LangBundle", new Locale(countryName,countryName.toUpperCase()));
	}
}
