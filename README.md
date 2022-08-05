# ediarum.JAR

© 2011-2022 by Berlin-Brandenburg Academy of Sciences and Humanities

Part of ediarum <https://www.ediarum.org/index.html> (ediarum@bbaw.de)

Developed by TELOTA, a DH working group of the Berlin-Brandenburg Academey of Sciences and Humanities
<https://www.bbaw.de/telota> (telota@bbaw.de)

Developer:

* Martin Fechner

## What does it do?

ediarum.JAR is a Java library designed for the oXygen XML-Editor
(<http://www.oxygenxml.com>) and part of ediarum.

ediarum is a package of three different combinable software solutions and technologies: [eXistdb](http://www.exist-db.org), [Oxygen XML Author](http://oxygenxml.com/xml_author.html) and [ConTeXt](http://wiki.contextgarden.net). The package and multiple extensions - not the software himself - are developed by [TELOTA](http://www.bbaw.de/telota) - an iniatitve for the "Digital Humanities" at the [Berlin-Brandenburg Academy of Sciences and Humanities](http://www.bbaw.de). Together these options create a digital work environment in which manuscripts and other TEI-XML documents can easily be transcribed and edited. Note that ediarum must always be customized for the individual project. 

The central software component is Oxygen XML Author. TELOTA developed for each project an "Oxygen XML framework" (also known as "documenttype association"). Oxygen XML offers out-of-the-box various Java operations that enable users to mark up an XML document by simply clicking a button. With ediarum.jar we have extended these operations by adding a few more - especially operations for working with a central index xml file in an database.

## Documentation

This following pages document the Java operations which are offered by the Java archive ediarum.JAR and which extend the functionalities avaible in [Oxygen XML](http://www.oxygenxml.com) for frameworks.

* For the documentation of the Java operations in english see [JAVA_OPERATIONS.md](JAVA_OPERATIONS_EN.md
).
* For the documentation of the Java operations in german see [JAVA_OPERATIONS_DE.md](JAVA_OPERATIONS_DE.md).

### Installation

The file ediarum.jar offers additionals Java operations for the use in an Oxygen XML framework.
If you would like to only use these functions you just have to download the file ediarum.jar and store it into your framework.
Then you have to reference to this JAR file in your Oxygen framework definition in the tab "Classpath".
After this you will find the additional Java operations in the list when creating or editing an action.

There is a step-by-step tutorial in german: ["Implementing index functions in Oxygen XML Frameworks" on digiversity](http://digiversity.net/2013/tutorial-indexfunktionen-fuer-oxygen-xml-frameworks/).

## License

ediarum.JAR is free software: you can redistribute it and/or modify
it under the terms of the GNU Lesser General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

ediarum.JAR is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public License
along with ediarum.JAR.  If not, see <http://www.gnu.org/licenses/>.
