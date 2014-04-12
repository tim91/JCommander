package org.jcommander.gui.locale;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;

import org.apache.log4j.Logger;

public class LocaleUtils {

	static Logger logger = Logger.getLogger("org.jcommander.util.LanguageManager");
	
	public static final String LOCALE_PATH_RESOURCE = "src/main/resources/locale";
	public static ClassLoader LOCALE_CLASS_LOADER = null;
	
	static
	{
		File file = new File(LocaleUtils.LOCALE_PATH_RESOURCE);
		
		URL[] urls = new URL[1];
		try {
			urls[0] = file.toURI().toURL();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		LOCALE_CLASS_LOADER = new URLClassLoader(urls);
	}
	
	public static final String LOCALE_BASE_NAME = "guiLocale";

	public static Locale getLocaleByLanguage(String language){
		for (Locale locale : Locale.getAvailableLocales()) {
			String lang = locale.getDisplayName(LocaleContext.getContext().getLocale());
			if(lang.equals(language.toLowerCase())){
				return locale;
			}
		}
		
		return null;
	}

	public static Iterator<Locale> getAvailableLanguages() {
		Set<Locale> languages = new HashSet<Locale>();
		for (Locale locale : Locale.getAvailableLocales()) {
			try {
				ResourceBundle rb = ResourceBundle.getBundle(LOCALE_BASE_NAME, locale,LOCALE_CLASS_LOADER);
				if(rb.getLocale().equals(locale)){
					
					/*
					 * Dzieki temu bede mial jezyk napisany w danym, ustawionym jezyku
					 */
//					String lan = locale.getDisplayName(LocaleContext.getContext().getLocale());
//					lan = lan.substring(0, 1).toUpperCase() + lan.substring(1);
					logger.debug("Znaleziono jezyk: " + locale);
					languages.add(locale);
				}
				
			} catch (MissingResourceException ex) {
				logger.debug("Nie znaleziono");
			}
		}

		return languages.iterator();
	}

}
