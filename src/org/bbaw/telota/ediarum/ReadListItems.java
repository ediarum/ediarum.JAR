package org.bbaw.telota.ediarum;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ReadListItems {
	/**
	 * interne Variablen, die Einträge und IDs des Registers.
	 */
	private String[] eintrag, id;

	/**
	 * Der Konstruktor liest das Dokument der URL mit den benannten Knoten aus und konstruiert den Eintrag und die Id für jeden Knoten.
	 * @param indexURI Die URL zur Registerdatei
	 * @param node Der X-Path-Ausdruck für die Knoten der einzelnen Registereinträge
	 * @param eintragExp Der Ausdruck um einen Registereintrag zu konstruieren.
	 * @param idExp Der Ausdruck um die ID für einen Registereintrag zu konstruieren. Er setzt sich wie eintragExp zusammen.
	 * @throws AuthorOperationException
	 */
	public ReadListItems(String indexURI, String node, String eintragExp, String idExp, String namespaceDecl) {
		try {
			Document indexDoc = XMLUtils.getDocument(XMLUtils.getDocumentBuilder(), indexURI);
			XPath xpath = XMLUtils.getXPath(namespaceDecl);
			// Das XPath-Query wird definiert.
			//XPathExpression expr = xpath.compile(node);

			// Die Resultate werden ausgelesen..
			//Object result = expr.evaluate(indexDoc, XPathConstants.NODESET);
			//Object result = xpath.evaluate(node, indexDoc, XPathConstants.NODESET);
			NodeList registerNodes = (NodeList) (xpath.evaluate(node, indexDoc, XPathConstants.NODESET));
			
			// .. dann werden für die Einträge und IDs entsprechend lange Arrays angelegt.
			eintrag = new String[registerNodes.getLength()];
			id = new String[registerNodes.getLength()];
	
			// Für jeden Knoten ..
			for (int i=0; i < registerNodes.getLength(); i++) {
				Element currentElement = (Element) (registerNodes.item(i));
				// .. wird der Eintrag konstruiert.
				eintrag[i] = XMLUtils.parseXPathAnnotation(eintragExp, currentElement, xpath);
				// Genauso wird die ID konstruiert.
				id[i] = XMLUtils.parseXPathAnnotation(idExp, currentElement, xpath);
			}
		} catch (XPathExpressionException e) {
			throw new IllegalArgumentException(e); // do not ignore exception
			//e.printStackTrace();
		} catch (IOException e) {
			throw new IllegalArgumentException(e); // do not ignore exception
			//e.printStackTrace();
		} catch (ParserConfigurationException e) {
			throw new IllegalArgumentException(e); // do not ignore exception
			//e.printStackTrace();
		} catch (SAXException e) {
			throw new IllegalArgumentException(e); // do not ignore exception
			//e.printStackTrace();
		}
	}

	/**
	 * Diese Methode gibt das Array der Einträge aus dem Register zurück.
	 * @return das Array der Einträge
	 */
	public String[] getEintrag(){
		return eintrag;
	}

	/**
	 * Diese Methode gibt das Array der IDs aus dem Register zurück.
	 * @return das Array der IDs
	 */
	public String[] getID(){
		return id;
	}
}
