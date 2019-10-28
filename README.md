# <p align="center">PROYECTO PSP</p>
### <p align="center">ATCS, Atención a clientes</p>

<p align="center">
<img src="/img/img1.jpg" height="600" width="800"/>
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
> **[1- Actividad 1](#1):**
>
> **[2- Actividad 2](#2):**

</br>
</br>
</br>
</br>
</br>
</br>
</br>
</br>


<a name="1"></a>
# ACTIVIDAD 1
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

Una vez almacenadas en la base de datos, el servidor se encarga de comunicarse con los distintos administradores, los cuales, serán los encargados de solucionar el problema del cliente. De esta forma, podremos utilizar la técnica de “Producto Consumidor” donde nuestro producto serán los clientes, la cola el servidor y nuestros consumidores los administradores.

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
# ACTIVIDAD 2

</br>
</br>

### Diagrama de casos de usos
<p align="center">
<img src="/img/diagramaUso.jpg" height="600" width="800"/>
</p>

</br>
</br>

### Descripcion de casos de usos
<p align="center">
  <img src="/img/descripcion1.jpg" height="600" width="800"/>
  </br>
  <img src="/img/descripcion2.jpg" height="600" width="800"/>
  </br>
  <img src="/img/descripcion3.jpg" height="600" width="800"/>
</p>



