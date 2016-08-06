# JavaUploadService

This application allow the client to upload a file to the server.

# Features

## [webjars](http://www.webjars.org/)
webjars are used to include client-side librabies i.e. bootstrap 3

## [multipart](http://docs.oracle.com/javaee/6/tutorial/doc/glraq.html)
Multipartconfig annotation is used to set the following properties.

@MultipartConfig(location="/tmp", fileSizeThreshold=1024*1024, 
    maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5)
    
## [Tomcat Embedded](https://tomcat.apache.org/maven-plugin-trunk/executable-war-jar.html)
The maven project builds a jar files containing the Tomcat JEE container. The tomcat7-maven-plugin is used in this application.

Alternative method: [Oracle](http://www.oracle.com/webfolder/technetwork/tutorials/obe/java/basic_app_embedded_tomcat/basic_app-tomcat-embedded.html)
