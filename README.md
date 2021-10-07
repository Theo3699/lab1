# lab1
## Created a servlet that:
- receives the following parameters key:String, value:int, mock:boolean and sync:boolean.
- if mock is true than the servlet simply returns a confirmation message.
- if mock is false, the servlet writes in a text file called repository a line containing the key, repeated value times, along with the timestamp of the request, and returns the content of the repository, as an HTML page containing all the lines that were created, ordered by key.
- if sync is false, then the servlet will not use any synchronized method when writing in the file.
- if the serverlet is invoked from a desktop application, it responds with a simple text instead of an HTML page.
- writes in the server log the following information about each request: the HTTP method used, the IP-address of the client, the user-agent, the client language(s) and the parameters of the request

