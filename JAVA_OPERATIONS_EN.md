# Java operations (english documentation)

## Index functions

Functions for inserting references from an xml file to a central index file.

### Insert Register Operation

_org.bbaw.telota.ediarum.InsertRegisterOperation_

Opens a dialogue where the user can select an entry from an index. Inserts then an XML element (including an attribut like xml:id) at the position of the caret or around the selection. 

| Parameter  | Description                                                                                                                                                                                                |
| :--------- | :--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| URL        | URL of the central index xml file, e.g.: http://user:passwort@example.edu/persons.xml                                                                                                                      |
| node       | XPath expression to the selectable elements, e.g.: //person                                                                                                                                                |
| expression | The phrase displayed in the select list (strings are noted in "", attributes start with @, elements with /, kindelements with // and XPath expressions with .), e.g.: /name+", "+/firstname+" "+/biography |
| element    | The element to be inserted in the xml document, e.g.: `"<persName xmlns='http://www.tei-c.org/ns/1.0' key='" + @id + "' />"`                                                                               |

### Insert Register At Operation

_org.bbaw.telota.ediarum.InsertRegisterAtOperation_

Opens a dialogue where the user can select an entry from an index. An xml element with the corresponding ID will be inserted at the location _defined by an XPath_.

| Parameter      | Description                                                                                                                                                                                                |
| :------------- | :--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| URL            | URL of the central index xml file, e.g.: http://user:passwort@example.edu/persons.xml                                                                                                                      |
| node           | XPath expression to the selectable elements, e.g.: //person                                                                                                                                                |
| expression     | The phrase displayed in the select list (strings are noted in "", attributes start with @, elements with /, kindelements with // and XPath expressions with .), e.g.: /name+", "+/firstname+" "+/biography |
| element        | The element to be inserted in the xml document, e.g.: `"<persName xmlns='http://www.tei-c.org/ns/1.0' key='" + @id + "' />"`                                                                               |
| insertLocation | An XPath expression indicating the insert location for the fragment.                                                                                                                                       |
| insertPosition | The insert position relative to the node determined by the XPath expression. Can be: Before, Inside as first child, Inside as last child or After.                                                         |
| schemaAware    | Controlling if the insertion is schema aware or not.                                                                                                                                                       |

### Insert Register Attribute Operation

_org.bbaw.telota.ediarum.InsertRegisterAttributeOperation_

Opens a dialogue where the user can select an entry from an index. This operation _inserts an attribute_ with ID corresponding to the selected index entry in the xml element _defined via XPath_.

| Parameter                         | Description                                                                                                                                                                                                |
| :-------------------------------- | :--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| URL                               | URL of the central index xml file, e.g.: http://user:passwort@example.edu/persons.xml                                                                                                                      |
| node                              | XPath expression to the selectable elements, e.g.: //person                                                                                                                                                |
| expression                        | The phrase displayed in the select list (strings are noted in "", attributes start with @, elements with /, kindelements with // and XPath expressions with .), e.g.: /name+", "+/firstname+" "+/biography |
| attribute name                    | The name of the attribute to be inserted, e.g.: key                                                                                                                                                        |
| attribute value                   | Value of this attribute, e.g.: "someText" + @id                                                                                                                                                            |
| xpath to element of the attribute | A relative XPath expression to the element, starting at the current selected text, e.g.: ./child                                                                                                           |

### Insert Index Operation

_org.bbaw.telota.ediarum.InsertIndexOperation_

Opens a dialogue where the user can select an entry from an index. This operation inserts one or more element(s) with corresponding ID around selected text area. Thats not like the "SurroundWith"-Operation which comes (out-of-the-box) with Oxygen XML Editor. With this operation, it is possible to surround an text area with two different XML elements, which have an additional ID - beside the ID from the index entry - to reference to each other. Example:

	<index spanTo="#gfq_j3f_ng" indexName="places" corresp="#p12345">
	<term>Name of index entry</term></index> Lorem ipsum dolor sit amet, 
	consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore
	magna aliquyam<anchor type="indexedarea" xml:id="gfq_j3f_ng"/>

| Parameter  | Description                                                                                                                                                                                                                                                                                                                     |
| :--------- | :------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| URL        | URL of the central index xml file, e.g.: http://user:passwort@example.edu/places.xml                                                                                                                                                                                                                                            |
| node       | XPath expression to the selectable elements, e.g.: //place                                                                                                                                                                                                                                                                      |
| expression | The phrase displayed in the select list (strings are noted in "", attributes start with @, elements with /, kindelements with // and XPath expressions with .), e.g.: /name+", "+/country+" "+/shortdescription                                                                                                                 |
| id         | ID of elements in the XML document to reference to each other (Note that ist not the ID of the index entry!)                                                                                                                                                                                                                    |
| element    | The element to be inserted in the xml document at the [SELECTION], e.g.: `"<index xmlns='http://www.tei-c.org/ns/1.0' spanTo='$[ID]' indexName='places' corresp='" + @id + "'><term xmlns='http://www.tei-c.org/ns/1.0'>" + /name + "</term></index>$[SELECTION]<anchor xmlns='http://www.tei-c.org/ns/1.0' xml:id='$[ID]' />"` |

***


## Insert Reference For Link Target Operation

_org.bbaw.telota.ediarum.InsertReferenceForLinkTargetOperation_

Opens a dialogue that displays the possible link targets in the currently in Oxygen XML opened XML files. Inserts a XML element with an corresponding filepath and ID.

| Parameter       | Description                                                                                                                                                                                            |
| :-------------- | :----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| root            | Path to database, etwa: /exist/webdav/db/data/                                                                                                                                                         |
| xpath           | XPath expression to the XML element that will be linked, e.g.: //anchor                                                                                                                                |
| id-attribute    | Name of the attribute of the linked element, that carries the ID, e.g.: xml:id                                                                                                                         |
| id start prefix | Prefix of the ID of the start element that will be linked, e.g.: start_                                                                                                                                |
| id stop prefix  | Prefix of the  ID of the end element that will be linked, e.g.: stop_                                                                                                                                  |
| element         | The element that will be inserted; the variables $FILEPATH, $FILE_ID, $STARTPREFIX, $STOPPREFIX, $ID can be used, e.g.: <ref xmlns='http://www.tei-c.org/ns/1.0' target='$FILEPATH/#$STARTPREFIX$ID'/> |
| altern. element | The element that will be inserte if the target ist a whole file; the variables $FILEPATH, $FILE_ID can be used, e.g.: `<ref xmlns='http://www.tei-c.org/ns/1.0' target='$FILEPATH'/>`                  |

## Surround With Elements Operation

_org.bbaw.telota.ediarum.SurroundWithElementsOperation_

Inserts before and after the selection _differents_ elements in the XML document.

| Parameter | Description                                                                                                |
| :-------- | :--------------------------------------------------------------------------------------------------------- |
| id        | ID that will be used multiple times in the inserted elements                                               |
| elements  | The elements before and after the $[SELECTION]; the ID will bei inserted with the variable $[ID] eingefügt |

## Multiple Actions Operation

_org.bbaw.telota.ediarum.MultipleActionsOperation_

Defines multiple actions, which will be executed automatically one after another.

| Parameter     | Description                    |
| :------------ | :----------------------------- |
| first action  | action-ID of the first action  |
| second action | action-ID of the second action |
| third action  | action-ID of the third action  |
| fourth action | action-ID of the fourth action |
| fifth action  | action-ID of the fifth action  |

## Execute Command 

_org.bbaw.telota.ediarum.ExecuteCommandOperation_

Execute an external program.

| Parameter | Description                   |
| :-------- | :---------------------------- |
| Command   | Command that will be executed |

## Open URL Operation

_org.bbaw.telota.ediarum.OpenURLOperation_

Opens a file in an external program (with the system standard program for this type of file)

| Parameter | Description     |
| :-------- | :-------------- |
| URL       | URL of the file |
