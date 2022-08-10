package org.bbaw.telota.ediarum;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;

import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.codec.binary.Base64;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XMLUtils {

	/**
	 *
	 * @return
	 * @throws ParserConfigurationException
	 */
	public static DocumentBuilder getDocumentBuilder() throws ParserConfigurationException {
		// Das neue Dokument wird vorbereitet.
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		domFactory.setNamespaceAware(true);
		return domFactory.newDocumentBuilder();
	}

	/**
	 *
	 * @param builder
	 * @param indexURI
	 * @return
	 * @throws SAXException
	 * @throws IOException
	 */
	public static Document getDocument(DocumentBuilder builder, String indexURI) throws SAXException, IOException {
		// Wenn es sich um eine URL mit Authentifizierung handelt, ..
		InputStream is;
		if (indexURI.indexOf('@')>-1) {
			// .. werden die Verbindungsdaten gelesen ..
			String authString = indexURI.substring(indexURI.indexOf("://")+3, indexURI.indexOf('@'));
			String webPage = indexURI.substring(0, indexURI.indexOf("://")+3)+indexURI.substring(indexURI.indexOf('@')+1);
			byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
			String authStringEnc = new String(authEncBytes);

			// .. und eine Verbindung mit Login geöffnet.
			URL url = new URL(webPage);
			URLConnection urlConnection = url.openConnection();
			urlConnection.setRequestProperty("Authorization", "Basic " + authStringEnc);
			is = urlConnection.getInputStream();
		} else {
			// Im anderen Fall wird direkt eine Verbindung geöffnet.
			URL url = new URL(indexURI);
			URLConnection urlConnection = url.openConnection();
			is = urlConnection.getInputStream();
		}

		// Dann wird die Datei gelesen.
		return builder.parse(new InputSource(is));
	}

	public static XPath getXPath(String namespaceDecl) throws SAXException, IOException {
		// Die xPath-Routinen werden vorbereitet.
		XPath xpath = XPathFactory.newInstance().newXPath();
		// Für Namespaces:
		String[] namespaceSplit = namespaceDecl.split(" ");
		String[][] namespaces = new String[namespaceSplit.length][2];
		for (int i=0; i<namespaceSplit.length; i++) {
			String currentNamespace = namespaceSplit[i];
			int k = currentNamespace.indexOf(":");
			namespaces[i][0] = currentNamespace.substring(0, k);
			namespaces[i][1] = currentNamespace.substring(k+1);
		}
		final String[][] namespacesfinal = namespaces;
//		final PrefixResolver resolver = new PrefixResolverDefault(indexDoc);
		NamespaceContext ctx = new NamespaceContext() {

			@Override
			public String getNamespaceURI(String prefix) {
//				return resolver.getNamespaceForPrefix(prefix);
				String uri = null;
				for (int i=0; i<namespacesfinal.length; i++) {
					if (prefix.equals(namespacesfinal[i][0])) {
						uri = namespacesfinal[i][1];
					}
				}
				return uri;
			}

			@Override
			public String getPrefix(String namespaceURI) {
				return null;
			}

			@Override
			public Iterator getPrefixes(String namespaceURI) {
				return null;
			}

		};
		xpath.setNamespaceContext(ctx);
		return xpath;
	}

	public static String parseXPathAnnotation(String str, Element currentElement, XPath xpath) throws XPathExpressionException {
		String parsedStr = "";

		// Falls im Ausdruck der Hinweis "$XPATH{..}" vorkommt, ..
		int k = str.indexOf("$XPATH{");
		while (k>=0) {
			// .. wird der String davor als Text eingefügt, ..
			parsedStr += str.substring(0, k);
			str = str.substring(k);
			int l = str.indexOf("}");
			// .. und der Ausdruck selbst ausgewertet:
			String xpathExpression = str.substring("$XPATH{".length(), l);
			// Jedes Glied kann entweder als Attribut, ..
			if (xpathExpression.startsWith("@")) {
				String attributeName = xpathExpression.substring(1);
				parsedStr += currentElement.getAttribute(attributeName);
				// .. als nachkommendes Element ..
			} else if (xpathExpression.startsWith("//")) {
				// .. (was direkt gelesen werden kann und schnell geht, ..
				String elementName = xpathExpression.substring(2);
				if (elementName.contains(":")) {
					elementName = elementName.substring(elementName.indexOf(":")+1);
				}
				if (currentElement.getElementsByTagName(elementName).getLength()>0){
					parsedStr += currentElement.getElementsByTagName(elementName).item(0).getTextContent();
				} else {
					// .. oder welches über eine X-Path-Abfrage gelesen werden kann und lange dauert), ..
					XPathExpression queryExpr = xpath.compile("."+xpathExpression);

					NodeList elementNodes = (NodeList) queryExpr.evaluate(currentElement, XPathConstants.NODESET);
					if (elementNodes.getLength()>0 && elementNodes.item(0).getNodeType() == Node.ELEMENT_NODE){
						parsedStr += elementNodes.item(0).getTextContent();
					}
				}
				// .. als direktes Kindelement (was schnell geht) ..
			} else if (xpathExpression.startsWith("/")) {
				String elementName = xpathExpression.substring(1);
				if (elementName.contains(":")) {
					elementName = elementName.substring(elementName.indexOf(":")+1);
				}
				if (currentElement.getElementsByTagName(elementName).getLength()>0){
					parsedStr += currentElement.getElementsByTagName(elementName).item(0).getTextContent();
				}
				// .. oder als X-Path-Ausdruck (was sehr lange dauert) verstanden werden.
			} else if (xpathExpression.startsWith(".")) {
				XPathExpression queryExpr = xpath.compile(xpathExpression);
				NodeList elementNodes = (NodeList) queryExpr.evaluate(currentElement, XPathConstants.NODESET);
				if (elementNodes.getLength()>0 && elementNodes.item(0).getNodeType() == Node.ELEMENT_NODE){
					parsedStr += elementNodes.item(0).getTextContent();
				}
				// Für X-Path-Ausdrücke mit Funktionen:
			} else if (xpathExpression.startsWith("#")) {
				XPathExpression queryExpr = xpath.compile(xpathExpression.substring(1));
				String elementString = (String) queryExpr.evaluate(currentElement, XPathConstants.STRING);
				parsedStr += elementString;
			}					// Der übrige Ausdruck wird danach ausgewertet.
			str = str.substring(l+1);
			k = str.indexOf("$XPATH{");
		};
		// Falls "$XPATH[..]" nicht mehr auftaucht, wird der Rest angehängt.
		parsedStr += str;

		return parsedStr;
	}

}
