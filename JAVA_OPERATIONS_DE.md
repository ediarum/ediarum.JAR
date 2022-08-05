# Java Operationen (deutsche Dokumentation)

## Indexfunktionen

Wie die Indexfunktionen in einem Oxygen XML Framework implementiert werden können, beschreibt auch schritt-für-schritt ein [Tutorial auf digiversity.net](http://digiversity.net/2013/tutorial-indexfunktionen-fuer-oxygen-xml-frameworks/).

### Insert Register Operation

_org.bbaw.telota.ediarum.InsertRegisterOperation_

Öffnet einen Dialog, in welchem aus einer Liste  ein Eintrag eines Registers ausgewählt werden kann. Fügt dann ein XML-Element mitsamt ID an oder um die _markierte_ Stelle im XML-Dokument herum ein.

| Parameter  | Beschreibung                                                                                                                                                                                                                      |
| :--------- | :-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| URL        | Die URL der Registerdatei, etwa: http://user:passwort@example.edu/personen.xml                                                                                                                                                    |
| node       | Der XPath-Ausdruck zu den wählbaren Elementen, etwa: //person                                                                                                                                                                     |
| expression | Der in der Auswahlliste erscheinende Ausdruck mit Sub-Elementen (dabei stehen Strings in "", Attribute beginnen mit @, Elemente mit /, Subelemente mit // und X-Path-Ausdrücke mit .), etwa: /name+", "+/vorname+" "+/lebensdaten |
| element    | Das an der Textstelle einzufügende Element, etwa: `"<persName xmlns='http://www.tei-c.org/ns/1.0' key='" + @id + "' />"`                                                                                                          |

### Insert Register At Operation

_org.bbaw.telota.ediarum.InsertRegisterAtOperation_

Öffnet einen Dialog, in welchem aus einer Liste ein Eintrag eines Registers ausgewählt werden kann. Ein Element mit der entsprechenden ID wird an einer _per XPath definierten_ Stelle im Dokument eingefügt.

| Parameter      | Beschreibung                                                                                                                                                           |
| :------------- | :--------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| URL            | Die URL der Registerdatei, etwa: http://user:passwort@example.edu/personen.xml                                                                                         |
| node           | Der XPath-Ausdruck zu den wählbaren Elementen, etwa: //person                                                                                                          |
| expression     | Der in der Auswahlliste erscheinende Ausdruck mit Sub-Elementen (beginnend mit '""', '@', '/', '//' oder '.'), etwa: //name+", "+//vorname+" "+//lebensdaten           |
| element        | Das an der Textstelle einzufügende Element, etwa: `"<persName xmlns='http://www.tei-c.org/ns/1.0' key='" + @id + "' />"`                                               |
| insertLocation | XPath-Ausdruck, der den Einfügeort des Elements definiert                                                                                                              |
| insertPosition | Die Einfügeposition relartiv zum Knoten, der im Parameter "insertLocation" definiert wurde. Kann lauten: Before, Inside as first child, Inside as last child or After. |
| schemaAware    | Kontrolliert, ob die Einfügung schemakonform ist oder nicht                                                                                                            |

### Insert Register Attribute Operation

_org.bbaw.telota.ediarum.InsertRegisterAttributeOperation_

Öffnet einen Dialog, in welchem aus einer Liste ein Eintrag eines Registers ausgewählt werden kann. Fügt ein Attribut mit der ID des ausgewählten Eintrags in das _per XPath definierte_ Element ein.

| Parameter                         | Beschreibung                                                                                                                                                 |
| :-------------------------------- | :----------------------------------------------------------------------------------------------------------------------------------------------------------- |
| URL                               | Die URL der Registerdatei, etwa: http://user:passwort@example.edu/personen.xml                                                                               |
| node                              | Der XPath-Ausdruck zu den wählbaren Elementen, etwa: //person                                                                                                |
| expression                        | Der in der Auswahlliste erscheinende Ausdruck mit Sub-Elementen (beginnend mit '""', '@', '/', '//' oder '.'), etwa: //name+", "+//vorname+" "+//lebensdaten |
| attribute name                    | Der Name des an der Textstelle einzufügenden Attributes, etwa: key                                                                                           |
| attribute value                   | Der Inhalt des an der Textstelle einzufügenden Attributes, etwa: "etwas Text" + @id                                                                          |
| xpath to element of the attribute | Ein relativer XPath-Ausdruck des die Textstelle umgebenden Elementes zu dem Element, wo das Attribut eingefügt werden soll: ./child                          |

### Insert Index Operation

_org.bbaw.telota.ediarum.InsertIndexOperation_

Öffnet einen Dialog, in welchem aus einer Liste ein Eintrag aus einem Register ausgewählt werden kann. Ein oder mehrere Element(e) mit der entsprechenden ID als Attribut werden um die im XML-Dokument _markierte_ Stelle herum eingefügt. Es handelt sich hier nicht um eine simple "SurroundWith"-Operation im Oxygen-XML-Framework. Im Gegenteil ist es dadurch möglich, ein Set aus zwei _unterschiedlichen_ XML-Elementen einzufügen, die - neben der ID des Registereintrags - beide eine weitere ID tragen, um auf das jeweils andere Element zu verweisen. Beispiel:

	<index spanTo="#gfq_j3f_ng" indexName="orte" corresp="#o12345">
	<term>Name/Titel des Registereintrags</term></index> Lorem ipsum dolor sit amet, 
	consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore
	magna aliquyam<anchor type="indexbereich" xml:id="gfq_j3f_ng"/>

| Parameter  | Beschreibung                                                                                                                                                                                                                                                                                                                                                 |
| :--------- | :----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| URL        | Die URL der Registerdatei, etwa: http://user:passwort@example.edu/personen.xml                                                                                                                                                                                                                                                                               |
| node       | Der XPath-Ausdruck zu den wählbaren Elementen, etwa: //person                                                                                                                                                                                                                                                                                                |
| expression | Der in der Auswahlliste erscheinende Ausdruck mit Sub-Elementen, etwa: /name+", "+/vorname+" "+/lebensdaten                                                                                                                                                                                                                                                  |
| id         | Die im Element mehrfach zu verwendende ID                                                                                                                                                                                                                                                                                                                    |
| element    | Das an der durch $SELECTION gekennzeichneten Textstelle einzufügende Element, etwa: `"<index xmlns='http://www.tei-c.org/ns/1.0' spanTo='$[ID]' indexName='personen' corresp='" + @id + "'><term xmlns='http://www.tei-c.org/ns/1.0'>" + /name + ", " + /vorname + "</term></index>$[SELECTION]<anchor xmlns='http://www.tei-c.org/ns/1.0' xml:id='$[ID]' />"` |

***


## Insert Link Operation

_org.bbaw.telota.ediarum.InsertLinkOperation_

Öffnet einen Dialog, der die möglichen Verweisziele in den geöffneten Dateien anzeigt. Bei Bestätigung wird an der Markierung ein entsprechendes Link-Element eingefügt.

| Parameter       | Beschreibung                                                                                                                                                                                                |
| :-------------- | :---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| root            | Der Pfad zur Datenbank, etwa: /exist/webdav/db/data/                                                                                                                                                        |
| xpath           | Der xPath-Ausdruck zu dem zu verlinkenden Element, etwa: //anchor                                                                                                                                           |
| id-attribute    | Der Name des ID-Attributes des zu verlinkenden Elements, etwa: xml:id                                                                                                                                       |
| id start prefix | Das ID-Prefix des vorderen zu verlinkenden Elements, etwa: start_                                                                                                                                           |
| id stop prefix  | Das ID-Prefix des hinteren zu verlinkenden Elements, etwa: stop_                                                                                                                                            |
| element         | Das einzufügende Element, mit dem $-Zeichen können $FILEPATH, $FILE_ID, $STARTPREFIX, $STOPPREFIX, $ID benutzt werden, etwa: <ref xmlns='http://www.tei-c.org/ns/1.0' target='$FILEPATH/#$STARTPREFIX$ID'/> |
| altern. element | Das einzufügende Element wenn auf eine ganze Datei verwiesen wird, mit dem $-Zeichen können $FILEPATH, $FILE_ID benutzt werden, etwa: `<ref xmlns='http://www.tei-c.org/ns/1.0' target='$FILEPATH'/>`       |

## Surround With Elements Operation

_org.bbaw.telota.ediarum.SurroundWithElementsOperation_

Fügt vor und hinter der Markierung verschiedene Elemente ein.

| Parameter | Beschreibung                                                                                                              |
| :-------- | :------------------------------------------------------------------------------------------------------------------------ |
| id        | Eine in den Elementen mehrfach zu verwendende ID                                                                          |
| elements  | Die vor und hinter der Markierung einzufügenden Elemente durch '$[SELECTION]' getrennt, die ID wird mit '$[ID]' eingefügt |

## Multiple Actions Operation

_org.bbaw.telota.ediarum.MultipleActionsOperation_

Definiert mehrere Aktionen, die nacheinander ausgeführt werden.

| Parameter     | Beschreibung              |
| :------------ | :------------------------ |
| first action  | Die ID der ersten Aktion  |
| second action | Die ID der zweiten Aktion |
| third action  | Die ID der dritten Aktion |
| fourth action | Die ID der vierten Aktion |
| fifth action  | Die ID der fünften Aktion |

## Execute Command 

_org.bbaw.telota.ediarum.ExecuteCommandOperation_

Führt ein externes Programm aus.

| Parameter | Beschreibung               |
| :-------- | :------------------------- |
| Command   | Das auszuführende Kommando |

## Open URL Operation

_org.bbaw.telota.ediarum.OpenURLOperation_

Öffnet eine Datei mit einem externen Programm oder im Browser.

| Parameter | Beschreibung                   |
| :-------- | :----------------------------- |
| URL       | Die URL der zu öffnenden Datei |
