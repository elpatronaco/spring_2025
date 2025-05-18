Para ejecutar el proyecto sigue los pasos:
1. Levanta la infraestructura con `docker compose up --build -d`

2. Instala la versión del JDK de Java 11.
   - En mi caso he utilizado `openjdk 11.0.27 2025-04-15`.

3. Configuración de los servicios:
   - No es necesaria configuración adicional si se utiliza la infraestructura por defecto.
   - Los puertos de los servicios son:
     - Servicio de Productos: 18081
     - Servicio de Usuarios: 18082
     - Servicio de Notificaciones: 18083

4. Comandos para ejecutar cada servicio por separado:
   Estos dependen uno de otro, por lo que es recomendable iniciarlos en el siguiente orden:

   - Productos:
     ```
     cd spring-2025-productcatalog
     java -jar target/productcatalog-0.0.1.jar
     ```

   - Usuarios:
     ```
     cd spring-2025-user
     java -jar target/user-0.0.1.jar
     ```

   - Notificaciones:
     ```
     cd spring-2025-notification
     java -jar target/notification-0.0.1.jar
     ```

5. Notas adicionales:
   - Asegúrate de que la infraestructura esté en funcionamiento antes de iniciar los servicios.
