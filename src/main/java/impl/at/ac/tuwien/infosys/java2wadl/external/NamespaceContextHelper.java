/*
 * The contents of this file are subject to the terms
 * of the Common Development and Distribution License
 * (the "License").  You may not use this file except
 * in compliance with the License.
 *
 * You can obtain a copy of the license at
 * https://jaxp.dev.java.net/CDDLv1.0.html.
 * See the License for the specific language governing
 * permissions and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL
 * HEADER in each file and include the License file at
 * https://jaxp.dev.java.net/CDDLv1.0.html
 * If applicable add the following below this CDDL HEADER
 * with the fields enclosed by brackets "[]" replaced with
 * your own identifying information: Portions Copyright
 * [year] [name of copyright owner]
 */

/*
 * $Id: NamespaceContextHelper.java,v 1.2 2006-03-28 20:54:02 ndw Exp $
 * Copyright 2006 Sun Microsystems, Inc. All Rights Reserved.
 */

package at.ac.tuwien.infosys.java2wadl.external;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

import javax.xml.namespace.NamespaceContext;

import com.sun.org.apache.xerces.internal.util.XML11Char;

/**
 * Helper implementation of the javax.xml.namespace.NamespaceContext interface.
 * 
 * <p>
 * This class implements the JAXP (1.3+) {@link javax.xml.namespace.NamespaceContext} interface. This is the interface
 * used by the JAXP XPath APIs to establish namespace bindings for XPath expressions.
 * <p>
 * 
 * <p>
 * There are two errors (in retrospect) with respect to the namespace context in the XPath API. First, there's no way to
 * construct a new one. Given an XPath you can find out what context it is using, but if you want to construct a new
 * expression, there's no standard class that you can instantiate to build a new context. Second, the
 * {@link javax.xml.namespace.NamespaceContext} interface is obviously (again, in retrospect) missing a method that
 * returns an iterator that will allow you to find <emph>all</emph> the namespace URIs (and/or prefixes, which would be
 * equivalent) in the context.
 * </p>
 * 
 * <p>
 * This class addresses the first error by providing an object that you can instantiate that implements the
 * {@link javax.xml.namespace.NamespaceContext} interface. It's not a <emph>standard</emph> class, but it at least saves
 * you the trouble of writing it yourself. (Feel free to move it into your own package, of course.)
 * </p>
 * 
 * <p>
 * There's really no way to address the second error. An interface, like {@link javax.xml.namespace.NamespaceContext},
 * is immutable once released into the wild in the Java platform. (This is a consequence of backwards compatibility
 * rules.) To really address the problem, we'll have to invent a new interface or provide an alternative abstract class
 * that implementations will be required to use, or something. However, as an experiment, this class implements a couple
 * of extra methods that we might wish had been in the interface. These methods are carefully identified as
 * non-standard. Having them here really isn't all that useful because your underlying XPath implementation isn't likely
 * to return instances of this class.
 * </p>
 * 
 * <p>
 * There are three ways to instantiate this class:
 * </p>
 * 
 * <ol>
 * <li>The no-argument constructor produces an initially empty namespace context.</li>
 * <li>Another constructor takes a prefix and URI and produces a namespace context with that binding.</li>
 * <li>Finally, there's a constructor that takes a hash of namespace/uri pairs and produces a namespace context with
 * those initial bindings.</li>
 * <li>The obvious constructor, one that takes an existing {@link javax.xml.namespace.NamespaceContext} so that you can
 * extend it, isn't there because you can't get the current bindings from that interface; see the aforementioned bug.</li>
 * </ol>
 * 
 * <p>
 * After the object has been instantiated, you can call the {@link #add(String,String)} method to add additional
 * bindings to the namespace context. Because I'm not sure how and where the XPath API implementations might save
 * pointers to the context object, I've imposed a number of rules designed to make sure that the context remains
 * coherent:
 * </p>
 * 
 * <ul>
 * <li>Namespace bindings can only be added, not removed.</li>
 * <li>Once a prefix is bound, its binding cannot be changed.</li>
 * <li>The XML restrictions on the 'xml' prefix, the 'xmlns' prefix, and their respective namespace URIs are enforced.</li>
 * <li>Namespace prefixes must be valid NCNames (or "" for the default namespace). Note that unprefixed element and
 * attribute names in an XPath expression can <em>never</em> match a name that's in a namespace. In particular, setting
 * the default namespace won't have that effect.</li>
 * </ul>
 * 
 * <p>
 * Even with these rules, you can't assume that the context is thread safe. Don't allow it to be changed while someone
 * else is reading it.
 * </p>
 * 
 * <p>
 * <b>Other notes:</b>
 * </p>
 * 
 * <ul>
 * <li>There's no <code>getNamespaceURIs(String prefix)</code> method because there can be at most one URI bound to any
 * given prefix. Wrapping an interator around the mapping available with {@link #getNamespaceURI(String)} seemed silly.</li>
 * <li>This class relies on {@link org.apache.xerces.util.XML11Char} to test that the prefixes are valid NCNames. Note
 * that this means that they're valid XML 1.1 names. XML 1.1 names are a superset of XML 1.0 names and it didn't seem
 * worth the extra effort that would be required to allow the user to choose XML 1.0 or XML 1.1. You might not think
 * it's worth the effort to check at all. Fair enough.</li>
 * <li>I've used generics here and there to make the JDK 1.5 compiler stop complaining. Just delete them for JDK 1.4 and
 * everything should work fine.</li>
 * </ul>
 * 
 * @author <a href="mailto:Norman.Walsh@Sun.COM">Norman Walsh</a>
 * @see <a href="http://jaxp.dev.java.net/">Java API for XML Processing</a>
 * @see <a href="http://www.w3.org/TR/REC-xml-names/#ns-qualnames"> Namespaces in XML</a>
 * @see <a href="http://www.w3.org/XML/xml-names-19990114-errata"> Namespaces in XML Errata</a>
 */
@SuppressWarnings("unchecked")
public class NamespaceContextHelper implements NamespaceContext {
	private Hashtable<String, String> ns = new Hashtable<String, String>();

	/**
	 * Creates a new instance of NamespaceContextHelper.
	 * 
	 * <p>
	 * Creates an empty namespace context.
	 * </p>
	 */
	public NamespaceContextHelper() {}

	/**
	 * Creates a new instance of NamespaceContextHelper.
	 * 
	 * <p>
	 * Creates a namespace context with the bindings specified in <code>initialNamespaces</code>.
	 * </p>
	 */
	public NamespaceContextHelper(Hashtable initialNamespaces) {
		Enumeration keys = initialNamespaces.keys();
		while (keys.hasMoreElements()) {
			String prefix = (String) keys.nextElement();
			String uri = (String) initialNamespaces.get(prefix);

			add(prefix, uri);
		}
	}

	/**
	 * Creates a new instance of NamespaceContextHelper.
	 * 
	 * <p>
	 * Creates a namespace context with the specified <code>prefix</code> bound to <code>uri</code>.
	 * </p>
	 */
	public NamespaceContextHelper(String prefix, String uri) {
		add(prefix, uri);
	}

	/**
	 * Adds a new prefix/uri binding to the namespace context.
	 * 
	 * @throws NullPointerException
	 *             if the <code>prefix</code> or <code>uri</code> is <code>null</code>.
	 * @throws IllegalArgumentException
	 *             if the caller attempts to change the binding of <code>prefix</code>, if the caller attempts to bind
	 *             the prefix "<code>xml</code>" or the namespace "<code>http://www.w3.org/XML/1998/namespace</code>"
	 *             incorrectly, if the caller attempts to bind the prefix "<code>xmlns</code>" or the namespace "
	 *             <code>http://www.w3.org/2000/xmlns</code>", or if the <code>prefix</code> is not a valid NCName.
	 */
	public void add(String prefix, String uri) {
		if (prefix == null || uri == null) {
			throw new NullPointerException("Null prefix or uri passed to NamespaceContextHelper");
		}

		if (ns.containsKey(prefix)) {
			String curURI = (String) ns.get(prefix);
			if (uri.equals(curURI)) {
				return;
			}
			throw new IllegalArgumentException("Attempt to change binding in NamespaceContextHelper");
		}

		if ("xml".equals(prefix) && !"http://www.w3.org/XML/1998/namespace".equals(uri)) {
			throw new IllegalArgumentException(
					"The prefix 'xml' can only be bound to 'http://www.w3.org/XML/1998/namespace' in NamespaceContextHelper");
		}

		if ("http://www.w3.org/XML/1998/namespace".equals(uri) && !"xml".equals(prefix)) {
			throw new IllegalArgumentException(
					"The namespace 'http://www.w3.org/XML/1998/namespace' can only have the prefix 'xml' in NamespaceContextHelper");
		}

		if ("xmlns".equals(prefix) || "http://www.w3.org/2000/xmlns".equals(uri)) {
			throw new IllegalArgumentException(
					"Neither the prefix 'xmlns' nor the URI 'http://www.w3.org/2000/xmlns' can be bound in NamespaceContextHelper");
		}

		if ("".equals(prefix)) {
			ns.put(prefix, uri);
		} else {
			if (XML11Char.isXML11ValidNCName(prefix)) {
				ns.put(prefix, uri);
			} else {
				throw new IllegalArgumentException("Prefix is not a valid NCName in NamespaceContextHelper");
			}
		}
	}

	/** Implements the NamespaceContext getNamespaceURI method. */
	public String getNamespaceURI(String prefix) {
		return ns.get(prefix);
	}

	/** Implements the NamespaceContext getPrefix method. */
	public String getPrefix(String namespaceURI) {
		if (ns.containsValue(namespaceURI)) {
			Enumeration<String> keys = ns.keys();
			while (keys.hasMoreElements()) {
				String pfx = keys.nextElement();
				String uri = ns.get(pfx);
				if (namespaceURI.equals(uri)) {
					return pfx;
				}
			}
		}
		return null;
	}

	/**
	 * Implements a <emph>NON STANDARD</emph> method for finding all of the prefixes in the namespace context.
	 * 
	 * <p>
	 * Returns an iterator over all of the prefixes in the namespace context. Note that multiple prefixes may be bound
	 * to the same URI.
	 * </p>
	 */
	public Iterator getPrefixes() {
		return getPrefixes(null);
	}

	/** Implements the NamespaceContext getPrefixes method. */
	public Iterator getPrefixes(String namespaceURI) {
		return new NSIterator(ns, namespaceURI);
	}

	/**
	 * Implements a <emph>NON STANDARD</emph> method for finding all of the namespace URIs in the namespace context.
	 * 
	 * <p>
	 * Returns an iterator over all of the namespace URIs in the namespace context. Note that each namespace URI is
	 * returned exactly once, even if it is bound to several different prefixes.
	 * </p>
	 */
	public Iterator getNamespaceURIs() {
		// Make sure each URI is returned at most once...
		Hashtable<String, String> uriHash = new Hashtable<String, String>();
		Enumeration<String> keys = ns.keys();
		while (keys.hasMoreElements()) {
			String pfx = keys.nextElement();
			String uri = ns.get(pfx);
			if (!uriHash.containsKey(uri)) {
				uriHash.put(uri, pfx);
			}
		}

		return new NSIterator(uriHash, null);
	}

	/** Implements the Iterator interface over namespace bindings. */
	private class NSIterator implements Iterator {
		private Enumeration<String> keys;

		public NSIterator(Hashtable<String, String> hash, String value) {
			keys = hash.keys();
			if (value != null) {
				// We have to copy the hash to get only the keys that have the specified value
				Hashtable<String, String> vHash = new Hashtable<String, String>();
				while (keys.hasMoreElements()) {
					String key = keys.nextElement();
					String val = hash.get(key);
					if (val.equals(value)) {
						vHash.put(key, val);
					}
				}
				keys = vHash.keys();
			}
		}

		public boolean hasNext() {
			return keys.hasMoreElements();
		}

		public String next() {
			return keys.nextElement();
		}

		public void remove() {
			throw new UnsupportedOperationException("Cannot remove prefix in NamespaceContextHelper");
		}
	}

}
