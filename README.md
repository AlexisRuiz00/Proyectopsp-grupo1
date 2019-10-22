




















#ACTIVIDAD 1#
##<b> TÍTULO: </b>
### ATCS: Atención a clientes

##<b> RESUMEN: </b>
Nuestro idea se basa en desarrollar un programa que permite a X clientes solicitar cualquier tipo de información que este requiera.

Este programa estará compuesto y envolverá las siguientes funcionalidades a realizar durante el período del curso:

<b>Hilos:</b> Se usarán hilos para representar al cliente, para la interfaz gráfica y para conectarse a la red a través de un canal UDP / TCP.
<b>Sockets:</b> Nuestra aplicación contendrá un servidor (realizado mediante protocolo UDP / TCP) que conectará nuestro hilo cliente con nuestros administradores encargados de atender a estas personas.
Dispondremos de <b>protocolos</b> estándares como TCP para utilizar el correo corporativo como posible medio de comunicación.
Implementaremos <b>seguridad</b>, acceso y roles distintos para los tipos de administradores.
Desarrollaremos <b>microservicios</b> en la nube, para poder recopilar y almacenar la información de nuestros clientes, así como realizar encuestas para conocer la opinión de los mismos.

La idea del proyecto es:

Existen 3 posturas (Cliente, Servidor, Administrador) donde nuestros clientes, abrirán una serie de consultas y estas serán enviadas a través de hilos al servidor, que será el encargado de almacenarlas en una base de datos.

Una vez almacenadas en la base de datos, el servidor se encarga de comunicarse con los distintos administradores, los cuales, serán los encargados de solucionar el problema del cliente. De esta forma, podremos utilizar la técnica de “Producto Consumidor” donde nuestro producto serán los clientes, la cola el servidor y nuestros consumidores los administradores.

Una vez el administrador gestione la consulta del cliente, esta se envía de nuevo al servidor, donde se encarga de realizar las modificaciones oportunas en la base de datos, como por ejemplo, (consulta cerrada, consulta pendiente, a la espera de administrador). 

No todas las consultas, las podrá resolver un administrador, se trata de implementar “roles” donde cada administrador podrá visualizar X tipo de consulta y de X rango.

Una vez el administrador haya enviado la información necesaria al servidor y este lo haya modificado en la base de datos, se le notificará al cliente el resultado de su consulta, así como a poder ser enviar un formulario de satisfacción al correo que proporciona el mismo cliente.
