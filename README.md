# CRUD en H2 con JDBC Puro

Dinámica de código facilito, donde se hace un ejemplo de CRUD sobre una base de datos, en este caso H2 en memoria, y con JDBC Puro

- Tiene la estructura
  - `config`: La capa de configuración se encarga de establecer los parámetros y valores del proyecto.
  - `model`: La capa de modelo se encarga de representar los datos del proyecto.
  - `repository`: La capa de repositorio se encarga de interactuar con la base de datos.
  - `service`: La capa de servicio se encarga de la lógica de negocio del proyecto.


- Solo tiene como modelo una tabla
  - `user`: contiene 3 campos: username, mail y un id (secuencia)

- [Guía para Desarrollo de un CRUD utilizando JDBC tradicional.pdf](https://drive.google.com/file/d/1-fZveV0H0gDMTQw1QdVmAUimfPm555XC/view)
- Si se quiere hacer con MySql aquí estaría una guía para bajarselo, solo es necesario cambiar el script para crear la tabla y el url para el driver de mysql
- [Guia para instalar MySQL.pdf](https://drive.google.com/file/d/1VRtcoVcQTyesOLvaoDpCN1g8FZkjn4ci/view)