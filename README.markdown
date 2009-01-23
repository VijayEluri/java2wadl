Java2Wadl Documentation
=======================

Introduction
------------

Java2Wadl is a strict implementation of the WADL-specification 
(https://wadl.dev.java.net/wadl20061109.pdf) and the JSR-311 
(http://jcp.org/en/jsr/detail?id=311) standard. Therefore it 
provides means to generate WADL-files for Java class hierarchies 
which are annotated with JSR-311 annotations.

It is designed to be as simple as possible and provides no further 
J2EE bindings or tools. It only features a single entry-point which makes 
it very easy to use and extend. 

Project Architecture
--------------------

Java2Wadl provides an API (src/main/java/api) and a default
implementation (src/main/java/impl). All implementation is 
written against the API, which makes it easy to exchange 
critical parts of the system later on.

Library Dependencies
--------------------

* JSR-311: jsr311-api-1.0.jar
* XML Tidy: Tidy-4aug2000.jar

You can find all the needed jars in the projects 
lib-directory. Add them to the classpath in order 
to use the application. In order to run the Unit-Tests 
you also need to add XMLUnit (xmlunit-1.2.jar) to 
the classpath.

Usage
-----

To use the application call _generate(Class type, String baseUri)_ 
from the Java2Wadl class.

As result you get a WADL object containing the generated 
WADL (xml content as String) and the referenced 
xml-schemas within a HashMap (mapping the schema names 
to their actual schema content). 

Caveats
-------

Collections cannot be mapped directly. As a workaround 
we suggest to introduce wrapper-classes (e.g. 
List<Customer> becomes Customers wrapping the 
List<Customer> object). 
  
Java2Wadl ignores all annotations for Fields, Constructors 
and PathParam on Methods. If you need these functionalities, 
we suggest to apply them directly on the method parameters.
The status-codes for thrown exceptions and all possible responses
are always defaulting to pre-defined values, since it 
is not possible to infer object-values at compile time with the
Java-reflection API. There is no workaround. The following 
annotations from the JSR-311 standard are 
ignored: 

* @CookieParam
* @TemplateParam
* @FormParam

