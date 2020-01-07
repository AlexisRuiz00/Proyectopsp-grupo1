# <p align="center">PROYECTO PSP</p>
### <p align="center">ATCS, Atención a clientes</p>

<p align="center">
<img src="/img/img1.jpg" height="300" width="400"/>
</p>


#### Author
&nbsp;&nbsp;&nbsp;  Este material ha sido producido por <b>David Camacho</b> y <b>Alexis Ruiz</b> bajo licencia Creative Commons:  <img src="/img/Licencia-Tipo2.png" height="25" width="75"/>  

</br>
</br>
</br>
</br>
</br>
</br>
</br>

# <p align="center">Índice:</p>
> **[1- Actividad 1 y 2: Definición y Requisitos.](#1)**
>
> **[2- Actividad 3: Análisis de requisitos funcionales.](#2)**
>
> **[3- Actividad 4: Modelo de objeto de negocios.](#3)**

</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>


<a name="1"></a>
# ACTIVIDAD 1 y 2, Definición y Requisitos
### <b>TÍTULO:</b>
*ATCS, Atención a clientes*

### <b> RESUMEN: </b>
Nuestra idea se basa en desarrollar un programa que permite a X clientes solicitar cualquier tipo de información que este requiera.

Este programa estará compuesto y envolverá las siguientes funcionalidades a realizar durante el período del curso:

1. <b>Hilos:</b> Se usarán hilos para representar al cliente, para la interfaz gráfica y para conectarse a la red a través de un canal UDP / TCP.
2. <b>Sockets:</b> Nuestra aplicación contendrá un servidor (realizado mediante protocolo UDP / TCP) que conectará nuestro hilo cliente con nuestros administradores encargados de atender a estas personas.
3. Dispondremos de <b>protocolos</b> estándares como TCP para utilizar el correo corporativo como posible medio de comunicación.
4. Implementaremos <b>seguridad</b>, acceso y roles distintos para los tipos de administradores.
5. Desarrollaremos <b>microservicios</b> en la nube, para poder recopilar y almacenar la información de nuestros clientes, así como realizar encuestas para conocer la opinión de los mismos.



La idea del proyecto es:

Existen 3 posturas (Cliente, Servidor, Administrador) donde nuestros clientes, abrirán una serie de consultas y estas serán enviadas a través de hilos al servidor, que será el encargado de almacenarlas en una base de datos.

Una vez almacenadas en la base de datos, el servidor se encarga de comunicarse con los distintos administradores, los cuales, serán los encargados de solucionar el problema del cliente. De esta forma, podremos utilizar la técnica de “Productor-Consumidor” donde nuestro producto serán los clientes, la cola el servidor y nuestros consumidores los administradores.

Una vez el administrador gestione la consulta del cliente, esta se envía de nuevo al servidor, donde se encarga de realizar las modificaciones oportunas en la base de datos, como por ejemplo, (consulta cerrada, consulta pendiente, a la espera de administrador). 

No todas las consultas, las podrá resolver un administrador, se trata de implementar “roles” donde cada administrador podrá visualizar X tipo de consulta y de X rango.

Una vez el administrador haya enviado la información necesaria al servidor y este lo haya modificado en la base de datos, se le notificará al cliente el resultado de su consulta, así como a poder ser enviar un formulario de satisfacción al correo que proporciona el mismo cliente.

Por último, todo esto se almacenará en la nube, para poder realizar informes semanales en un futuro.


</br>
</br>
</br>
</br>
</br>
</br>

<a name="2"></a>
# ACTIVIDAD 3: Análisis de requisitos funcionales.

</br>
</br>

### Diagrama de casos de usos
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

### Descripcion de casos de usos
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
			<td> <p>1. System shows the login view.</p>
		     	<p>2. Admin inserts credentials.</p>
		     	<p>3. System validates the credentials.</p>
		     	<p>4. System recognize the role of admin and opens the view that corresponds.</p>
			<p>5. System creates a socket and sends a byte to the server with the admin rol</p>		
			<p>6. Server interprets the byte recieved</p>
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
		    <p>3. Admin inserts the credentials.</p>
		    <p>4. System alerts that the credentials are not valid.</p>
		    <p>5. Written credentials are removed.</p>
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
		<td> <p>1. Admin inserts sql query.</p>
		     <p>2. System interprets the query and returns true or false if its been suscesfully executed or not</p>
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
		<td>System administrator deletes a database</td>
 	</tr>
 	<tr>
  		<td><b>Precondition:</b></td>
   		<td>Admin is logged in the system as system administrator</td>
 	</tr>
	<tr>
  		<td><b>Normal Sequence:</b></td>
		<td> <p>1. Admin clicks in "Delete Database".</p>
		     <p>2. System asks for database name.</p>
		     <p>3. Admin inserts the name.</p>
		     <p>4. System deletes the database.</p> 
		</td>
 	</tr>
	<tr>
  		<td><b>Postcondition:</b></td>
   		<td>Nothing</td>
 	</tr>
	<tr>
  		<td>
		    <p><b>Alternative 1</b></p>
		    <p>Database doesn't exists</p>
		</td>
		<td><p>4. Systems alerts that database doesn't exists.</p>
		    <p>5. System returns to main menu.</p>
		</td>
 	</tr>
	<tr>
  		<td>
		    <p><b>Alternative 2</b></p>
		    <p>Admin press cancel.</p>
		</td>
		<td><p>3. Admin press cancel.</p>
		    <p>4. System returns to main menu.</p>
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
		<td>System administrator creates a new table in the database</td>
 	</tr>
 	<tr>
  		<td><b>Precondition:</b></td>
   		<td>Admin is logged in the system as system administrator</td>
 	</tr>
	<tr>
  		<td><b>Normal Sequence:</b></td>
		<td> <p>1. Admin clicks in "Create Table".</p>
		     <p>2. System asks for a name.</p>
		     <p>3. Admin inserts the name.</p>
		     <p>4. System shows a view to configure the table</p>
		     <p>5. Admin configure the table and press Accept for creating it.</p>
		     <p>6. System creates the table and returns to main menu.</p>	
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
		<td><p>5. System alerts that table already exists</p>
		    <p>6. System returns to main menu.</p>
		</td>
 	</tr>
	<tr>
  		<td>
		    <p><b>Alternative 2</b></p>
		    <p>Admin press cancel.</p>
		</td>
		<td><p>3. Admin press cancel.</p>
		    <p>4. System returns to main menu.</p>
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
		<td>System administrator creates a new incidence administrator</td>
 	</tr>
 	<tr>
  		<td><b>Precondition:</b></td>
   		<td>Admin is logged in the system as system administrator</td>
 	</tr>
	<tr>
  		<td><b>Normal Sequence:</b></td>
		<td> <p>1. Admin clicks in "Create Incidence Admin".</p>
		     <p>2. System ask for a name.</p>
		     <p>3. Admin inserts a name.</p>
		     <p>4. System ask for a password.</p>
		     <p>5. Admin inserts a password.</p>
		     <p>  6. System creates a new Incidence admin with the given credentials.</p>	
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
		<td><p>5. System alerts that Incidence Admin already exists</p>
		    <p>6. System returns to main menu.</p>
		</td>
 	</tr>
	<tr>
  		<td>
		    <p><b>Alternative 2</b></p>
		    <p>Admin press cancel.</p>
		</td>
		<td><p>3. Admin press cancel.</p>
		    <p>4. System returns to main menu.</p>
		</td>
 	</tr>
</table>

<br>
<br>
					
<table>
	<tr>
		<td><b>Identifier:</b></td>
		<td>CU1.5</td>
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
		<td> <p>1. Admin clicks in "Delete Incidence Admin".</p>
		     <p>2. System asks for admin name.</p>
		     <p>3. Admin inserts the name.</p>
		     <p>4. System deletes the incidence admin.</p>
		</td>
 	</tr>
	<tr>
  		<td><b>Postcondition:</b></td>
   		<td>Nothing</td>
 	</tr>
	<tr>
  		<td>
		    <p><b>Alternative 1</b></p>
		    <p>Incidence Admin doesn't exist.</p>
		</td>
		<td><p>5. System alerts that Incidence Admin doesn't exist</p>
		    <p>6. System returns to main menu.</p>
		</td>
 	</tr>
	<tr>
  		<td>
		    <p><b>Alternative 2</b></p>
		    <p>Admin press cancel.</p>
		</td>
		<td><p>3. Admin press cancel.</p>
		    <p>4. System returns to main menu.</p>
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
		<td> <p>1. The system show all incidences that are open and not resolved.</p>
		     <p>2. The incidence administrator choose an incidence.</p>
		     <p>3. The application show all the incidence details.</p>
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
		<td> <p>1. The system show all incidences that are open and not resolved.</p>
		     <p>2. The incidence administrator choose an incidence.</p>
		     <p>3. The application show all the incidence details.</p>
		     <p>4. Admin close the selected incidence.</p>	
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
		<td> <p>1. The system show all incidences that are open and not resolved.</p>
		     <p>2. The incidence administrator choose an incidence.</p>
		     <p>3. The application show all the incidence details.</p>
		     <p>4. Incidence admin assign the incidence to the another administrator.</p>	
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
		<td> <p>1. The system show all incidences that are open and not resolved.</p>
		     <p>2. The incidence administrator choose an incidence.</p>
		     <p>3. The application show all the incidence details.</p>
		     <p>4. The administrator mark the selected incidence as pending.</p>	
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
		<td> <p>1. System shows the login view.</p>
		     <p>2. Client insert an e-mail.</p>
		     <p>3. System validates the email.</p>
		     <p>4. System shows main view.</p>
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
		<td> <p>1. The client inserts detail of incidence.</p>
		     <p>2. The client clicks in send incidence.</p>
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
		<td> <p>1. The system consult for the incidences related the client email.</p>
		     <p>2. The system shows a list with the incidence.</p>
		     <p>3. The client click on an incidence.</p>
		     <p>4. The system shows all details from this incidence.</p>
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
		<td> <p>1. The system consult for the incidences related the client email.</p>
		     <p>2. The system shows a list with the incidence.</p>
		     <p>3. The client click on an incidence.</p>
		     <p>4. The system shows all details from this incidence.</p>
		     <p>5. The client writes an aswer and send it.</p>		
		</td>
 	</tr>
	<tr>
  		<td><b>Postcondition:</b></td>
   		<td>The answer writen by the client is saved.</td>
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
# ACTIVIDAD 4: Modelo de objetos de negocio.

### Diagrama Entidad-Relacion

<p align="center">
  <img src="/img/entidad.jpg" height="400" width="800"/>
  </br>
  </br>
  </br>
  </br>
  
### Diagrama de Clases

  <img src="/img/clases.jpg" height="500" width="800"/>
</p>

