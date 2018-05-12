# Broken pipe

The sole purpose of this project is to though a Broken Pipe exception. The server will wait for a connection and send a large payload once a client connects. The client will connect and close the socket causing a java.net.SocketException: Broken pipe on the server site.
