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


<table>
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
   		<td>  1. Admin clicks in "Delete Database"
		      2. System asks for database name
		      3. Admin inserts the name
		      4. System deletes the database 
		</td>
 	</tr>
	<tr>
  		<td><b>Postcondition:</b></td>
   		<td>Nothing</td>
 	</tr>
	<tr>
  		<td><b>Alternative 1</b></td>
   		<td>$400</td>
 	</tr>
</table>
					


<table>
	<tr>
		<td><b>Descripction:</b></td>
		<td>Savings</td>
 	</tr>
 	<tr>
  		<td><b>Precondition:</b></td>
   		<td>$100</td>
 	</tr>
	<tr>
  		<td><b>Normal Sequence:</b></td>
   		<td>$750</td>
 	</tr>
	<tr>
  		<td><b>Postcondition:</b></td>
   		<td>$250</td>
 	</tr>
	<tr>
  		<td><b>Alternative 1</b></td>
   		<td>$400</td>
 	</tr>
</table>
					








<p align="center">
  </br>
  </br>
  </br>
  </br>
  <img src="/img/descripcion2.jpg" height="500" width="800"/>
  </br>
  </br>
  </br>
  </br>
  <img src="/img/descripcion3.jpg" height="500" width="800"/>
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

