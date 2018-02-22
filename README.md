## html-minification-maven-plugin
A simple HTML minification plugin to be used in maven projects during build process. Build over [serg472/htmlcompressor](https://github.com/serg472/htmlcompressor) library, this plugin provides an easy to use plugin based configuration of the library so that simultaneous minification of html files become possible during build process only. Honestly speaking , this was a requirement in my current project.

## Usage
Below code provides a simple plugin code fragment to be placed in your pom.xml file to make this code work.
```sh
....
<plugin>
  <groupId>com.htmlminifier</groupId>
  <artifactId>html-maven-plugin</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <executions>
      <execution>
        <phase>package</phase>
        <goals>
          <goal>html-minifier</goal>
        </goals>
      </execution>
   </executions>
   <configuration>
     <source-folder>src/main/java/html</source-folder> <!-- required field -->
     <target-folder>src/main/java/minified-html</target-folder> <!-- required field -->
     <walk-recursively>false</walk-recursively> <!-- required field -->
     <compression-enabled>true</compression-enabled> <!-- required field -->
   </configuration>
</plugin>
....
```
## Advanced Options
Optional configuration parameters (Example contains default values) - 
```sh
....
<configuration>
  ...
  <remove-multispaces>true</remove-multispaces>
  <remove-intertag-spaces>true</remove-intertag-spaces>
  <remove-quotes>true</remove-quotes>
  <simplify-doctype>true</simplify-doctype>
  <remove-script-attrs>false</remove-script-attrs>
  <remove-style-attrs>false</remove-style-attrs>
  <remove-link-attrs>false</remove-link-attrs>
  <remove-form-attrs>false</remove-form-attrs>
  <remove-input-attrs>false</remove-input-attrs>
  <remove-javascript-protocol>false</remove-javascript-protocol>
  <remove-http-protocol>true</remove-http-protocol>
  <remove-https-protocol>true</remove-https-protocol>
  <preserve-original-linebreaks>false</preserve-original-linebreaks>
  <minify-css>true</minify-css>
  <minify-js>true</minify-js>
</configuration>
....
```
