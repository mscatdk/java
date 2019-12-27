# java.debug

The intention of this application is to provide tools that can aid debugging another application. This application is not intended for production and it come with the following features.

## HTTP Request Details

__End-point:__ /java.debug/httpRequestDetails  

Sending a HTTP request to this endpoint will return a JSON object containing information about the request received by the application server. This can be helpfull when integration two systems and you are unsure if the client or the server is causing the problem. The JSON structure is also written to log.

## Class Loader

__End-point:__ java.debug/classLocator  
__Example:__ java.debug/classLocator?className=javax.servlet.http.HttpServlet  

Provide the location of the jar file from where the classloader has loaded a given class. This is super helpfull if the same class is being loaded multiple times.
