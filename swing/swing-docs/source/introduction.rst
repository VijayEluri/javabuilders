.. highlight:: java

Introduction
============

Abstract
--------

**Swing JavaBuilder : making Swing development productive**

    "Just started on using the Swing JavaBuilder and i must say i like it. Just replaced 170 rules of Java code
    with only 13 lines YAML" *Comment posted the JavaBuilders forum*

The Swing JavaBuilder is a library whose sole goal is to maximize the productivity of a Swing developer.
It's main goal is tackling all the Swing pain points, in particular the complexity and verbosity of the API
and reducing it to the smallest amount of code possible.

This is accomplished by moving all the boring gruntwork of Swing interface creation to an external YAML
file, which has a 1-to-1 match with a backing Java class (e.g. a JFrame or JPanel) that is built from that
file. This allows to follow a pure MVC pattern where the YAML contains nothing but the view, while the
Java class is (mostly) the controller.

As an added bonus, the Swing JavaBuilder offers integrated support for data binding (using Beans
Binding), input validation, background task processing (using
SwingWorker) and last but not least, an integrated layout management DSL built-on top of the amazing
MigLayout layout manager

In essence, the Swing JavaBuilder is an aggregator of best-of-breed Swing solutions into one common,
integrated toolkit that makes creating Swing user interfaces a breeze

.. note::
 
    YAML is a file format that is a superset of JSON. We will cover it in more detail in future chapters. 
    It's very simple to understand, edit and maintain. It's main advantage over both XML and JSON is the lack of
    any opening or closing tags, since it implements hierarchical data relationships via whitespace indentation 
    (similar to the Python programming language).


Preface
-------

In 2007 or so, Sun Microsystems announced their JavaFX project, which aimed to deliver declarative UIs
and rich desktop functionality. Unfortunately, in what I've always believed to be a severely misguided
decision, this was accomplished by introducing a totally new language, instead of enhancing the core
Java abilities and the existing Swing UI toolkit.

I decided that there had to be a middle-of-the-road approach that could give Java UI developers the
productivity of declarative UIs without the need to throw out their current language skills out and focus on
an unproved and untested new language (whose features I wasn't particularly fond of anyway, but that's
a different story).

The JavaBuilders project was a result of this desire. It started off with many weeks of research and
evaluation of different options. This resulted finally in the creation of a generic declarative UI based
around the YAML format (which has many advantages over the XML or JSON formats) and the
integration of many leading open source libraries (for features such as databinding or input validation)
into one integrated solution.

The Swing JavaBuilder is the first production-ready implementation of the JavaBuilder engine, but it's
generic nature allows it to be configured for other UI toolkits as well. In the future a SWT JavaBuilder is
planned (and maybe even GTK+ and Qt versions as well).

I hope its adoption by you and your team will greatly increase your productivity and ensure a long and
healthy future for Java rich client development.

    *Jacek Furmankiewicz*
    
    JavaBuilders Technical Architect

P.S. Many thanks to our code contributors: Alexandre Navarro, Sébastien Gollion.

License
-------

All JavaBuilders code is released under the business-friendly Apache 2.0 license.
It is free to use in all projects, both open source and commercial.

*Third party libraries*

The Swing JavaBuilder depends on a number of well known open-source components, all of which are
released under business-friendly licenses such as BSD, Apache or LGPL.
We never link to any open source components released under viral licenses such as GPL.
Nevertheless, please make sure to evaluate each third party license with your legal team to ensure
compliance with its terms.

Installation
------------

Standard
^^^^^^^^

Start off with downloading the latest Swing JavaBuilder ZIP file distribution off the JavaBuilders.org
website.
 
In the root folder you will find the Swing JavaBuilder jar and in the /lib folder you will find all of its
dependencies. Add all of them to your project's classpath.
 
In the /samples folder you will find a sample application that you can use to get a better understanding of
how you can build complex user interfaces using this library.

Gradle
^^^^^^

If you are using Gradle, you will need to add the *jcenter* repository::

    repositories {
        jcenter()
    }


At the time of writing, the latest stable version was::

    compile "javabuilders:swing-core:1.3.0"


When creating a new Gradle project, it is recommended that you change the default setup to allow Java and resource YAML files
to be in the same source code folder, instead of being split across the *src/main/java* and *src/main/resources* folders::

        sourceSets {
            main {
                resources {
                    srcDir 'src/main/java'
                }
            }
            test {
                resources {
                    srcDir 'src/test/java'
                }
            }
        }

Maven
^^^^^

If you are using Maven, you can just point to our custom repository:

.. code-block:: xml

    <repositories> 
            <repository> 
                    <id>jcenter</id>
                    <url>http://jcenter.bintray.com</url>
            </repository> 
    </repositories>

Please check the www.javabuilders.org website for the latest version.

At the time of writing, the latest stable version was:

.. code-block:: xml

    <dependencies> 
            <dependency> 
                    <groupId>javabuilders</groupId>
                    <artifactId>swing-core</artifactId>
                    <version>1.3.0</version>
            </dependency> 
    </dependencies>
    
When creating a new Maven project, it is recommended that you change the default setup to allow Java and resource YAML files
to be in the same source code folder, instead of being split across the *src/main/java* and *src/main/resources* folders:
 
.. code-block:: xml 
 
    <build>
        <resources>
            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <include>**/*</include>
                </includes>
                <excludes>
                    <exclude>**/*.java</exclude>
                </excludes>
            </resource>
        </resources>
        <testResources>
            <testResource>
                <directory>src/test/java</directory>
                <includes>
                    <include>**/*</include>
                </includes>
                <excludes>
                    <exclude>**/*.java</exclude>
                </excludes>
            </testResource>
        </testResources>
        
            <plugins>
                <plugin>
                    <artifactId>maven-compiler-plugin</artifactId>
                    <configuration>
                        <compilerVersion>1.6</compilerVersion>
                        <source>1.6</source>
                        <target>1.6</target>
                        <includes>
                            <include>**/*.yml</include>
                            <include>**/*.java</include>
                        </includes>
                    </configuration>
                </plugin>

                <plugin>
                    <artifactId>maven-resources-plugin</artifactId>
                    <configuration>
                    </configuration>
                </plugin>
         </plugins>
     </build> 