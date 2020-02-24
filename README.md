# <p align="center">PROYECTO PSP</p>
### <p align="center">ATCS, Atención a clientes</p>

<p align="center">
<img src="/img/img1.jpg" height="300" width="400"/>
</p>


#### Author
&nbsp;&nbsp;&nbsp;  This proyect has been produced by <b>David Camacho</b> and <b>Alexis Ruiz</b> with the license Creative Commons:  <img src="/img/Licencia-Tipo2.png" height="25" width="75"/>  

</br>
</br>
</br>
</br>
</br>
</br>
</br>

# <p align="center">Índice:</p>
> **[1- Activity 1 y 2: Definitions and Requirements.](#1)**
>
> **[2- Activity 3: Analysis of functional requirements.](#2)**
>
> **[3- Activity 4: Business Object Model.](#3)**
>
> **[4- Activity 5: Interface of Application.](#4)**
>
> **[5- Activity 6: Threads.](#5)**
>
> **[6- Activity 7: Sequence Diagram.](#6)**
>
> **[7- Manual](./Manual.md)**


</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>


<a name="1"></a>
# ACTIVITY 1 y 2, Definition and Requierements
### <b>TITLE:</b>
*ATCS, Atención a clientes*

### <b> SUMMARY: </b>
Our idea is based on developing a program that allows X clients to request any type of information that this requires.

This program will be compose and developer the next funcionalities to realize in all course:

1. <b>Threads:</b> We use the threads for represent the client, for the graphic interface and connect the red using two channel protocol, UDP / TCP.
2. <b>Sockets:</b> Our application will have a server (realized with protocol UDP / TCP) and have our thread client with our administrators attends this clients.
3. We have <b>standard protocols</b> as TCP to use corporative mail as possible community channel.
4. We'll implement <b>security</b>, access and diferent roles for kind of administrators.
5. We'll developer <b>microservices</b> in the cloud, for take and to stock the information of us clients, as well as realized surveys.



The proyect idea is:

We have three classes (Client, Service, Administradtor) where us clients, open consults and this will be send to the server using threads, and this thread will be put the information in a database.

One time the information will be in a database, the server communicate with the administrators, and this provide a solution to the clients. Using this form, we can use the "Product-Consumer" technique where us product will be the clients, the queue is the server and the administrator be consumers.

One the administrator manage the tickets, this send to the server, where he realized the changes in the database, example: (close ticket, and choose ticket as pending of the administrator).

Not all the tickets, can be solve for the administrator, we implements roles where each administrator can visualez X kind of tickets and X range.

One time the administrator send the informationto the server and this modified in the database, we communicate to the client the information of ticket, As well as send mail the satisfation poll.

For the last, all this things stay in the cloud, to could realize reports in the future.


</br>
</br>
</br>
</br>
</br>
</br>

<a name="2"></a>

# ACTIVITY 3: Análisis de requisitos funcionales.

</br>
</br>

### Use case diagrams:
<p align="center">
  <p>Client</p>
  <img src="/img/CasoDeUsoClient.jpg" height="400" width="600"/>
  </br>
  </br>
  </br>
  <p>System Administrator</p>
  <img src="/img/CasoDeUsoSysAdmin.jpg" height="400" width="600"/>
  </br>
  </br>
  </br>
  <p>Incidence Administrator</p>
  <img src="/img/CasoDeUsoIncidenceAdmin.jpg" height="400" width="600"/></p>
</p>
</br>
</br>

### Description use case:
<p align="center">
	
<table>
	<tr>
		<td><b>Identifier:</b></td>
		<td>CU1.0</td>
 	</tr>
	<tr>
		<td><b>Actors:</b></td>
		<td>Administrator</td>
 	</tr>
	<tr>
		<td><b>Descripction:</b></td>
		<td>Administrator logs into the system</td>
 	</tr>
 	<tr>
  		<td><b>Precondition:</b></td>
   		<td>None</td>
 	 	</tr>
	<tr>
  		<td><b>Normal Sequence:</b></td>
			<td> 	<p>1. System shows the login view.</p>
		     		<p>2. Admin inserts credentials.</p>
				<p>3. System sends a byte with value 10</p>
				<p>4. Server prepares to recieve admin credentials</p>
				<p>5. Server validates credentials and recognize admin rol</p>
		     		<p>6. Server sends back administrator's rol.</p>
		     		<p>7. System recognize the role of admin and opens the view that corresponds.</p>
				<p>8. System creates a socket and sends a byte to the server with the admin rol</p>		
				<p>9. Server interprets the byte recieved</p>
				<p>	if rol==Incidence Admin</p>
				<p>	   9.1. Server sends an arraylist with all the linked incidences</p>
				<p>        10. System creates an IncidenceAdminView</p>
				<p>	   11. System charge incidences.
				<p>	   12. System shows view.</p>
				<p>	if rol==System Admin</p>
				<p>	   9.1.  Server sends an arraylist with all the Incidence Admins</p>
				<p>	   10. System creates a SystemAdminView.</p>
				<p>	   11. System charge Incidence Admins.
				<p>	   12. System shows view.</p>
		</td>
 	</tr>
	<tr>
  		<td><b>Postcondition:</b></td>
   		<td>System open and shows the view of the rol logged.</td>
 	</tr>
	<tr>
  		<td>
		     <p><b>Alternative 1:</b></p>
		     <p>Admin inserts a non valid credentials</p>
		</td>
		<td>
			<p>5. Server doesn't find any administrator whith the recieved credentials</p>
		     	<p>6. Server sends back an empty String</p>
			<p>7. System shows a message "User/Password is wrong"
		</td>
 	</tr>

</table>

<br>
<br>

<table>
	<tr>
		<td><b>Identifier:</b></td>
		<td>CU1.1</td>
 	</tr>
	<tr>
		<td><b>Actors:</b></td>
		<td>System administrator</td>
 	</tr>
	<tr>
		<td><b>Descripction:</b></td>
		<td>System administrator inserts a sql query</td>
 	</tr>
 	<tr>
  		<td><b>Precondition:</b></td>
   		<td>Admin is logged in the system as system administrator</td>
 	</tr>
	<tr>
  		<td><b>Normal Sequence:</b></td>
		<td>	<p>1. Admin inserts sql query.</p>
			<p>2. System sends a byte with value 11.</p>
			<p>3. Server interprets the the byte value.</p>
			<p>4. Server prepares to read the sql statement.</p>
			<p>5. System sends the sql statement</p>
		     	<p>6. Server interprets the query and returns true or false if its been suscesfully executed or not</p>
		</td>
 	</tr>
	<tr>
  		<td><b>Postcondition:</b></td>
   		<td>Nothing</td>
 	</tr>
	<tr>
  		<td><b>Alternatives:</b></td>
   		<td>none</td>
 	</tr>
</table>

<br>
<br>
		
<table>
	<tr>
		<td><b>Identifier:</b></td>
		<td>CU1.2</td>
 	</tr>
	<tr>
		<td><b>Actors:</b></td>
		<td>System administrator</td>
 	</tr>
	<tr>
		<td><b>Descripction:</b></td>
		<td>System administrator creates a new table in the database</td>
 	</tr>
 	<tr>
  		<td><b>Precondition:</b></td>
   		<td>Admin is logged in the system as system administrator</td>
 	</tr>
	<tr>
  		<td><b>Normal Sequence:</b></td>
		<td> 	<p>1. Admin inserts table name</p>
			<p>2. Admin clicks in "Create Table".</p>
		  	<p>3. System sends to server a byte with value 12.</p>
			<p>4. Server interprets the the byte value.</p>
			<p>5. Server prepares to read the table name.</p>
			<p>6. System sends the table name.</p>
			<p>7. Server recieves the name and creates it.</p>
			<p>8. Server sends back a message "Table created"</p>	
		</td>
 	</tr>
	<tr>
  		<td><b>Postcondition:</b></td>
   		<td>Nothing</td>
 	</tr>
	<tr>
  		<td>
		    	<p><b>Alternative 1</b></p>
		    	<p>Table already exist.</p>
		</td>
		<td>
		 	<p>7. Server sends back a message "Table already exists"</p>
		    	<p>8. Systems shows the message.</p>
		    	<p>9. System returns to main menu.</p>
		</td>
 	</tr>
</table>

<br>
<br>
					
<table>
	<tr>
		<td><b>Identifier:</b></td>
		<td>CU1.3</td>
 	</tr>
	<tr>
		<td><b>Actors:</b></td>
		<td>System administrator</td>
 	</tr>
	<tr>
		<td><b>Descripction:</b></td>
		<td>System administrator creates a new incidence administrator</td>
 	</tr>
 	<tr>
  		<td><b>Precondition:</b></td>
   		<td>Admin is logged in the system as system administrator</td>
 	</tr>
	<tr>
  		<td><b>Normal Sequence:</b></td>
		<td> 	<p>1. Admin clicks in "New".</p>
		     	<p>2. System creates a new item Incidence Admin in the list.</p>
		     	<p>3. Admin inserts Incidence Admin details.</p>
		     	<p>4. System sends to server a byte with value 13.</p>
			<p>5. Server interprets the the byte value.</p>
			<p>6. Server prepares to read the Incidence admin Object.</p>
			<p>7. System sends the object.</p>
			<p>8. Server recieves the object, creates administrator and sends back a message "Adminstrator created"</p>
		</td>
 	</tr>
	<tr>
  		<td><b>Postcondition:</b></td>
   		<td>Nothing</td>
 	</tr>
	<tr>
  		<td>
		    <p><b>Alternative 1</b></p>
		    <p>Incidence admin already exist.</p>
		</td>
		<td>	<p>10. Server recieves the credentias and sends back a message "Adminstrator already exist".</p>
			<p>11. System shows message.</p>
			<p>12. System returns to main menu.</p>
		</td>
 	</tr>
</table>

<br>
<br>
					
<table>
	<tr>
		<td><b>Identifier:</b></td>
		<td>CU1.4</td>
 	</tr>
	<tr>
		<td><b>Actors:</b></td>
		<td>System administrator</td>
 	</tr>
	<tr>
		<td><b>Descripction:</b></td>
		<td>System administrator deletes an Incidence administrator</td>
 	</tr>
 	<tr>
  		<td><b>Precondition:</b></td>
   		<td>Admin is logged in the system as system administrator</td>
 	</tr>
	<tr>
  		<td><b>Normal Sequence:</b></td>
		<td> 	<p>1. Admin selects an Incidence admin from the list.</p>
			<p>2. Admin clicks in "Delete".</p>
		  	<p>3. System sends to server a byte with value 14.</p>
			<p>4. Server interprets the the byte value.</p>
			<p>5. Server prepares to read administrator's name.</p>
			<p>6. System sends the name.</p>
			<p>7. Server recieves the credentials, deletes administrator and sends back a message "Adminstrator created".</p>
		</td>
 	</tr>
	<tr>
  		<td><b>Postcondition:</b></td>
   		<td>Nothing</td>
 	</tr>
	<tr>
  		<td>
		    <p><b>Alternatives</b></p>
		</td>
		<td>	
			<p>None</p>
		</td>
 	</tr>
</table>

<br>
<br>
					
<table>
	<tr>
		<td><b>Identifier:</b></td>
		<td>CU2.1</td>
 	</tr>
	<tr>
		<td><b>Actors:</b></td>
		<td>Incidence administrator</td>
 	</tr>
	<tr>
		<td><b>Descripction:</b></td>
		<td>Incidence Administrator reads an incidence</td>
 	</tr>
 	<tr>
  		<td><b>Precondition:</b></td>
   		<td>Admin is logged in the system as incidence administrator</td>
 	</tr>
	<tr>
  		<td><b>Normal Sequence:</b></td>
		<td> 	
		     	<p>1. The incidence administrator choose an incidence.</p>
		     	<p>2. The application show all the incidence details.</p>
		</td>
 	</tr>
	<tr>
  		<td><b>Postcondition:</b></td>
   		<td>System shows information of an incidence</td>
 	</tr>
	<tr>
  		<td>
		    <p><b>Alternatives:</b></p>
		</td>
		<td>
		    <p>None</p>
		</td>
 	</tr>

</table>

<br>
<br>

<table>
	<tr>
		<td><b>Identifier:</b></td>
		<td>CU2.2</td>
 	</tr>
	<tr>
		<td><b>Actors:</b></td>
		<td>Incidence administrator</td>
 	</tr>
	<tr>
		<td><b>Descripction:</b></td>
		<td>Incidence Administrator close an incidence</td>
 	</tr>
 	<tr>
  		<td><b>Precondition:</b></td>
   		<td>Admin is logged in the system as incidence administrator</td>
 	</tr>
	<tr>
  		<td><b>Normal Sequence:</b></td>
		<td>	<p>1. The incidence administrator choose an incidence.</p>
		    	<p>2. The application show all the incidence details.</p>
		    	<p>3. The administrator mark the selected incidence as close.</p>
			<p>4. System sends a byte to the server with value 22</p>
			<p>5. Server prepares to recieve an Incidence Object</p>
			<p>6. System sends incidence changed.</p>
			<p>7. Server updates Incidence recieved.</p> 
		</td>
 	</tr>
	<tr>
  		<td><b>Postcondition:</b></td>
   		<td>The incidence is set as closed.</td>
 	</tr>
	<tr>
  		<td>
		    <p><b>Alternatives:</b></p>
		</td>
		<td>
		    <p>None</p>
		</td>
 	</tr>

</table>

<br>
<br>

<table>
	<tr>
		<td><b>Identifier:</b></td>
		<td>CU2.3</td>
 	</tr>
	<tr>
		<td><b>Actors:</b></td>
		<td>Incidence administrator</td>
 	</tr>
	<tr>
		<td><b>Descripction:</b></td>
		<td>Incidence Administrator assign an incidence to another incidence administrator</td>
 	</tr>
 	<tr>
  		<td><b>Precondition:</b></td>
   		<td>Admin is logged in the system as incidence administrator</td>
 	</tr>
	<tr>
  		<td><b>Normal Sequence:</b></td>
		<td>	<p>1. The incidence administrator choose an incidence.</p>
		     	<p>2. The application show all the incidence details.</p>
		     	<p>3. Incidence admin assign the incidence to the another administrator.</p>
			<p>4. System sends a byte to the server with value 23</p>
			<p>5. Server prepares to recieve an Incidence Object</p>
			<p>6. System sends incidence changed.</p>
			<p>7. Server updates Incidence recieved.</p>
		</td>
 	</tr>
	<tr>
  		<td><b>Postcondition:</b></td>
   		<td>The incidence administrator assigned to the selected incidence is changed to the new admin selected.</td>
 	</tr>
	<tr>
  		<td>
		    <p><b>Alternatives:</b></p>
		</td>
		<td>
		    <p>None</p>
		</td>
 	</tr>

</table>

<br>
<br>

<table>
	<tr>
		<td><b>Identifier:</b></td>
		<td>CU2.4</td>
 	</tr>
	<tr>
		<td><b>Actors:</b></td>
		<td>Incidence administrator</td>
 	</tr>
	<tr>
		<td><b>Descripction:</b></td>
		<td>Incidence Administrator marks an incidence as pending</td>
 	</tr>
 	<tr>
  		<td><b>Precondition:</b></td>
   		<td>Admin is logged in the system as incidence administrator</td>
 	</tr>
	<tr>
  		<td><b>Normal Sequence:</b></td>
		<td>	<p>1. The incidence administrator choose an incidence.</p>
		    	<p>2. The application show all the incidence details.</p>
		    	<p>3. The administrator mark the selected incidence as pending.</p>
			<p>4. System sends a byte to the server with value 24</p>
			<p>5. Server prepares to recieve an Incidence Object</p>
			<p>6. System sends incidence changed.</p>
			<p>7. Server updates Incidence recieved.</p> 
		</td>
 	</tr>
	<tr>
  		<td><b>Postcondition:</b></td>
   		<td>The system sets the selected incidence as pending</td>
 	</tr>
	<tr>
  		<td>
		    <p><b>Alternatives:</b></p>
		</td>
		<td>
		    <p>None</p>
		</td>
 	</tr>

</table>

<br>
<br>

<table>
	<tr>
		<td><b>Identifier:</b></td>
		<td>CU3.1</td>
 	</tr>
	<tr>
		<td><b>Actors:</b></td>
		<td>Client</td>
 	</tr>
	<tr>
		<td><b>Descripction:</b></td>
		<td>Client Login</td>
 	</tr>
 	<tr>
  		<td><b>Precondition:</b></td>
   		<td>Nothing</td>
 	</tr>
	<tr>
  		<td><b>Normal Sequence:</b></td>
		<td>	<p>1. System shows the login view.</p>
		    	<p>2. Client insert an e-mail.</p>
		   	<p>3. System validates the email.</p>
			<p>4. System sends a byte with value 31</p>
			<p>5. Server prepares to recieve client email</p>
			<p>6. System sends client's email.
			<p>7. Server sends back an arraylist with all the linked incidences</p>
			<p>8. System shows the view of client</p>
			<p>9. System charge all the incidences linked</p>
		</td>
 	</tr>
	<tr>
  		<td><b>Postcondition:</b></td>
   		<td>System open and shows the main view.</td>
 	</tr>
	<tr>
  		<td>
		     <p><b>Alternative 1:</b></p>
		     <p>Client inserts a non valid e-mail</p>
		</td>
		<td>
		    <p>3. Client inserts the e-mail.</p>
		    <p>4. System alerts that the e-mail is not valid.</p>
		    <p>5. Written email is removed.</p>
		</td>
 	</tr>

</table>


<br>
<br>

<table>
	<tr>
		<td><b>Identifier:</b></td>
		<td>CU3.2</td>
 	</tr>
	<tr>
		<td><b>Actors:</b></td>
		<td>Client</td>
 	</tr>
	<tr>
		<td><b>Descripction:</b></td>
		<td>Client creates a new incidence.</td>
 	</tr>
 	<tr>
  		<td><b>Precondition:</b></td>
   		<td>Client is logged in the system</td>
 	</tr>
	<tr>
  		<td><b>Normal Sequence:</b></td>
		<td> 	<p>1. The client inserts detail of incidence.</p>
			<p>2. The client clicks in send incidence.</p>
			<p>3. System sends an object incidence of type new</p>
			<p>4. Server recognize incidence's type and save the incidence in database.</p>
		</td>
 	</tr>
	<tr>
  		<td><b>Postcondition:</b></td>
   		<td>Incidence is sended</td>
 	</tr>
	<tr>
  		<td>
		     <p><b>Alternatives</b></p>
		</td>
		<td>
		     <p>None</p>
		</td>
 	</tr>
</table>

<br>
<br>

<table>
	<tr>
		<td><b>Identifier:</b></td>
		<td>CU3.3</td>
 	</tr>	<tr>
		<td><b>Actors:</b></td>
		<td>Client</td>
 	</tr>
	<tr>
		<td><b>Descripction:</b></td>
		<td>Client reads an incidence.</td>
 	</tr>
 	<tr>
  		<td><b>Precondition:</b></td>
   		<td>Client is logged in the system</td>
 	</tr>
	<tr>
  		<td><b>Normal Sequence:</b></td>
		<td> <p>1. The client click on an incidence.</p>
		     <p>2. The system shows all details from this incidence.</p>
		</td>
 	</tr>
	<tr>
  		<td><b>Postcondition:</b></td>
   		<td>System shows information of an incidence.</td>
 	</tr>
	<tr>
  		<td>
		     <p><b>Alternatives</b></p>
		</td>
		<td>
		     <p>None</p>
		</td>
 	</tr>
</table>

<br>
<br>

<table>
	<tr>
		<td><b>Identifier:</b></td>
		<td>CU3.4</td>
 	</tr>	<tr>
		<td><b>Actors:</b></td>
		<td>Client</td>
 	</tr>
	<tr>
		<td><b>Descripction:</b></td>
		<td>Client aswer an incidence</td>
 	</tr>
 	<tr>
  		<td><b>Precondition:</b></td>
   		<td>Client is logged in the system</td>
 	</tr>
	<tr>
  		<td><b>Normal Sequence:</b></td>
		<td> 	<p>1. The client click on an incidence.</p>
		     	<p>2. The system shows all details from this incidence.</p>
		     	<p>3. The client writes an aswer and send it.</p>
			<p>4. System sends an object incidence of type answer</p>
			<p>5. Server recognize incidence's type</p>
			<p>6. Server find incidence in database by its id</p>
			<p>7. Server concats answer to the incidence's text</p>
		</td>
 	</tr>
	<tr>
  		<td><b>Postcondition:</b></td>
   		<td>The answer writen by the client is concatenated.</td>
 	</tr>
	<tr>
  		<td>
		     <p><b>Alternatives</b></p>
		</td>
		<td>
		     <p>None</p>
		</td>
 	</tr>
</table>

<br>
<br>

<table>
	<tr>
		<td><b>Identifier:</b></td>
		<td>CU3.5</td>
 	</tr>	<tr>
		<td><b>Actors:</b></td>
		<td>Client</td>
 	</tr>
	<tr>
		<td><b>Descripction:</b></td>
		<td>Client opnes support chat</td>
 	</tr>
 	<tr>
  		<td><b>Precondition:</b></td>
   		<td>Client is logged in the system</td>
 	</tr>
	<tr>
  		<td><b>Normal Sequence:</b></td>
		<td> 	<p>1. Client opens support chat.</p>
			<p>2. System sends a byte to the server with value 35.</p>
			<p>3. System starts a thread waiting for the server answer.</p>
			<p>4. Server interpretes the byte value.</p>
			<p>5. Server a multicast socket for the chat.</p>
			<p>6. Server sends back the multicast socket object.</p>
			<p>7. System use the multicast socket recieved to comunicate.</p>
		</td>
 	</tr>
	<tr>
  		<td><b>Postcondition:</b></td>
   		<td>Nothing.</td>
 	</tr>
	<tr>
  		<td>
		     <p><b>Alternatives</b></p>
		</td>
		<td>
		     <p>None</p>
		</td>
 	</tr>
</table>









</p>

</br>
</br>
</br>
</br>
</br>
</br>

<a name="3"></a>
# ACTIVITY 4: Business Object Model.

### Entity Relationship diagrams:

<p align="center">
  <img src="/img/entidad.jpg" height="400" width="800"/>
  </br>
  </br>
  </br>
  </br>
</p>

## Class Diagrams


### Admin Application

<p align="center">
  <img src="/img/adminClass.jpg" height="500" width="800"/>
</p>

</br>
</br>

### Server Application

<p align="center">
  <img src="/img/serverClass.jpg" height="500" width="800"/>
</p>

</br>
</br>

### Client Application

<p align="center">
  <img src="/img/clientClass.jpeg" height="500" width="800"/>
</p>

</br>
</br>
</br>


### Class Description


<table>
	<tr>
		<td><b>Class:</b></td>
		<td>AdminLoginView</td>
 	</tr>	
	<tr>
		<td><b>Description:</b></td>
		<td>Interface where aministrators login.</td>
 	</tr>
		
</table>
<table>
	<tr>
		<td><b>Class:</b></td>
		<td>MainAdmin</td>
 	</tr>	
	<tr>
		<td><b>Description:</b></td>
		<td>Class that act as controller for Admin application</td>
 	</tr>
		
</table>
<table>
	<tr>
		<td><b>Class:</b></td>
		<td>Server</td>
 	</tr>	
	<tr>
		<td><b>Description:</b></td>
		<td>Class that act as server, which recieves manage</br>
		    messages from the Client and Admin applications.
		</td>
 	</tr>
		
</table>

<table>
	<tr>
		<td><b>Class:</b></td>
		<td>Model</td>
 	</tr>	
	<tr>
		<td><b>Description</b></td>
		<td>Class that has the necessary methods to allow Server</br>
		    interact with the database</td>
 	</tr>
		
</table>

<table>
	<tr>
		<td><b>Class:</b></td>
		<td>ATCSJDBC</td>
 	</tr>	
	<tr>
		<td><b>Description:</b></td>
		<td>Class that has the necessary methods to interact with</br>
		    the database</td>
 	</tr>
		
</table>



</br> 
</br> 
</br> 


### Methods Descriptions

<table>
	<tr>
		<td><b>Class:</b></td>
		<td>AdminLoginView</td>
 	</tr>	
	<tr>
		<td><b>getCredentials():</b></td>
		<td>Recovers the username and password that the</br>
		    user inserts. Creates an arraylist that contains</br>
		    that data and returns it.</td>
 	</tr>
		
</table>
<table>
	<tr>
		<td><b>Class:</b></td>
		<td>Server</td>
 	</tr>	
	<tr>
		<td><b>getLogin():</b></td>
		<td>Returns the result of calling getLogin method from class Model.</td>
 	</tr>
		
</table>

<table>
	<tr>
		<td><b>Class:</b></td>
		<td>Model</td>
 	</tr>	
	<tr>
		<td><b>getLogin():</b></td>
		<td>Returns the result of calling getLogin method from class ATCSJDBC.</td>
 	</tr>
		
</table>

<table>
	<tr>
		<td><b>Class:</b></td>
		<td>ATCSJDBC</td>
 	</tr>	
	<tr>
		<td><b>getLogin():</b></td>
		<td>Executes a sql statement that looks for an Admin with </br>
		    the credentials passed. If finds it, return the rol of the</br>
		    admin finded, if not, returns an empty String.</td>
 	</tr>
		
</table>



</br>
</br>
</br>
<a name="5"></a>

# ACTIVITY 6: Threads

### <p>Admin Aplication</p>
<p><b>Threads:</b></p>
<ul>
  <li>
	<p>hiloChat</p>
  	<p>This thread will be receiving messages from the chat</p>
  </li>
</ul>
</br>
</br>

### <p>Client Aplication</p>
<p></b>Threads:</b><p/>
<ul>
  <li>
	<p>hiloChat</p>
  	<p>This thread will be receiving messages from the chat</p>
  </li>
</ul>
</br>
</br>

### <p>Server Aplication</p>
<p></b>Threads:</b></p>
<ul>
  <li>
	<p>ThreadCliente</p>
  	<p>This Thread will be created every time a client</br>
	   connects to the server and will act as controller</br> 
	   of that conection. The thread has a switch in its</br>
	   which will decide what operation make depending</br>
	   on the byte recieved.</p>
</li>
  </br>
  <li>
	<p>ThreadAdminLogin</p>
  	<p>
		This thread is created when an admin tries to</br>
		make a login, the thread will execute the method</br>
		findUser() from the server and sends back the admin</br>
		rol to the application.
	</p>
</li>
</br>
<li>
	<p>ThreadConfChat</p>
	<p>Configure the multicast socket and send</br>
	   it to the client and administrator who</br>
	   will communicate through the chat
	</p>
</li>
</br>  
<li>
	<p>ThreadIncidenceAdmin</p>
	<p>This Thread will be created every time an Incidence</br>
	   admin connects to the server and will act as</br>
	   controller of that conection. The thread has a</br> 
	   switch in its which will decide what operation</br>
	   make depending on the byte recieved.</br>
	</p>
  </li>
  </br>
  <li>
	<p>ThreadSystemAdmin</p>
  	<p>This Thread will be created every time a System</br>
	   admin connects to the server and will act as</br>
	   controller of that conection. The thread has a</br>
	   switch in its which will decide what operation</br>
	   make depending on the byte recieved</p>
</li>
</ul>


</br>
</br>
</br>
</br>
<a name="6"></a>

# ACTIVITY 7: Sequence Diagrams
</br>
</br>

## Admin
</br>
</br>


### AdminLogin CU 1.0
</br>

<p align="center">
  <img src="/img/secuencia/adminLogin.jpg" height="500" width="800"/>
</p>

</br>
</br>


