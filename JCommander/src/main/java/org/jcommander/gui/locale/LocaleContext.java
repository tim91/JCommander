package org.jcommander.gui.locale;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.SwingUtilities;

import org.jcommander.util.exception.SingletonException;


/**
 * A GUI context object.
 *
 * @author Dawid Weiss
 */
public class LocaleContext {
    /**
     * Listeners waiting for events happening
     * on this context.
     * 
     * @see ContextChangeListener
     */
    private final ArrayList<LocaleChangeListener> listeners
        = new ArrayList<LocaleChangeListener>();

	private Locale locale;
	private ResourceBundle bundle;
	private String baseName;

	
	/**
	 * @throws SingletonException 
	 * 
	 */
	public LocaleContext(String baseName) throws SingletonException {
		
		if(localeContext != null){
			throw new SingletonException();
		}
		
		this.baseName = baseName;
	}
	
	/**
	 * @return Returns currently active resource bundle.
	 */
	public ResourceBundle getBundle() {
		return bundle;
	}

    /**
     * @return Returns currently active locale.
     */
	public Locale getLocale() {
		return locale;
	}

    /**
     * Sets new locale to be used by the GUI. This
     * method triggers an event propagated to all listeners.
     */
	public void setLocale(Locale locale) {
		if (locale.equals(this.locale)) {
			// same locale, no change.
			return;
		}

//		// get resource bundle for this locale.
//		File file = new File(LanguageManager.LOCALE_PATH_RESOURCE);
//		
//		URL[] urls = new URL[1];
//		try {
//			urls[0] = file.toURI().toURL();
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		ClassLoader loader = new URLClassLoader(urls);
		ResourceBundle bundle = ResourceBundle.getBundle(baseName, locale, LocaleUtils.LOCALE_CLASS_LOADER);
		if (bundle == null) {
			throw new IllegalArgumentException("No resource bundle for: "
						+ locale.getLanguage());
		}
		this.locale = locale;
		this.bundle = bundle;
		fireContextChangedEvent();
	}

    /**
     * Adds a new {@link ContextChangeListener} to the list of
     * objects listening on the changes of this context.
     */
    public synchronized void addContextChangeListener(LocaleChangeListener listener) {
        listeners.add(listener);
    }

    /**
     * Fires an event to all listeners. The event may be delayed
     * but will be delivered eventually. The order of calls to this
     * method is always preserved. 
     */
	private void fireContextChangedEvent() {
        // Event dispatcher code wrapped in a runnable.
        final Runnable dispatcher = new Runnable() {
            public void run() {
                synchronized (LocaleContext.this) {
                    for (Iterator<LocaleChangeListener> i = listeners.iterator();i.hasNext();) {
                        i.next().localeChanged();
                    }
                }
            }
        };

        // The contract in ContextChangeListener states that
        // <code>contextChanged</code> method must be invoked
        // from the AWT thread. If we're the AWT thread, execute
        // immediately. Otherwise just enqueue the event dispatcher.
	    if (SwingUtilities.isEventDispatchThread()) {
            dispatcher.run();
        } else {
            SwingUtilities.invokeLater(dispatcher);
        }
	}
	
	private static LocaleContext localeContext = null;
	
	public static LocaleContext getContext(){
		
		if(localeContext == null){
			try {
				localeContext = new LocaleContext(LocaleUtils.LOCALE_BASE_NAME);
			} catch (SingletonException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Locale locale = Locale.getDefault();
			locale = new Locale("en");
			localeContext.setLocale(locale);
			return localeContext;
		}else{
			return localeContext;
		}
		
	}
} 