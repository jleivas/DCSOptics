# Desarrollo de aplicacion Optics

## Introducción

Optics es una aplicación de escritorio basada en OptiData version 3 utilizado para registrar recetas 
oftalmológicas y almacenamiento de datos de clientes en las ópticas, tiene la capacidad tanto de imprimir 
el comprobante para el cliente como de enviarlo por correo, envía reporte de recetas efectuadas y 
obtiene reportes de ventas por periodo, actualmente se encuentra disponible la tercera versión del 
sistema en softdirex.cl, Optics es la cuarta versión y se encuentra en desarrollo.

## Mejoras

- Genera la receta oftalológica automáticamente, a diferencia de las versiones anteriores, no necesita un formulario pre-impreso
- Envía reportes de ventas por filtro al correo, ahora en formato HTML
- Tiene la capacidad de administrar mas de un inventario
- Almacenamiento de datos centralizado en una base de datos remota
- Puede registrar distintas oficinas o locales y trabajar en forma paralela conectado al mismo centro de datos
- Acceso de usuarios con permisos específicos para cada tipo de usuario
- Envío automático de reporte de errores
- Mejora en la interfaz gráfica
- Base de datos local embebida, no requiere software adicional a diferencia de las versiones anteriores

## Instalación
Puedes descargar el sistema y ver más información en el [sitio web de Softdirex](http://optidata.softdirex.cl)

Requisitos previos:
- Tener instalado [java 1.8 o superior](https://www.java.com/es/download/)
Para instalar:
- No requiere de otro software adicional, la instalación será más sencilla y no será necesario tener un mayor conocimiento 
técnico para la puesta en marcha del sistema.

 # System versions
 ## Registro de versiones del sistema

 #### [Version 4.1.1 (V411)](https://github.com/softdirex/DCSOptics/search?q=V411&type=Commits)
 - [[V411-006]Mensaje de instrucciones para renovar licencia](https://github.com/softdirex/DCSOptics/search?q=V411-006&type=Commits)
 - [[V411-005]Otorgar permisos de usuarios para acceder a pagar licencia](https://github.com/softdirex/DCSOptics/search?q=V411-005&type=Commits)
 - [[V411-004]Sección de redireccionamiento de pago a webpay](https://github.com/softdirex/DCSOptics/search?q=V411-004&type=Commits)
 - [[V411-003]Username de admin no se puede modificar](https://github.com/softdirex/DCSOptics/search?q=V411-003&type=Commits)
 - [[V411-002]Se anula la modificación automática de la clave de usuarios](https://github.com/softdirex/DCSOptics/search?q=V411-002&type=Commits)
 - [[V411-001]Evita pérdida de email al sincronizar por primera vez](https://github.com/softdirex/DCSOptics/search?q=V411-001&type=Commits)


 #### [Version 4.1.0 (V410)](https://github.com/softdirex/DCSOptics/search?q=V410&type=Commits)
 - [[V410-009]Validación de formato de folio al cargar una ficha](https://github.com/softdirex/DCSOptics/search?q=V410-009&type=Commits)
 - [[V410-008]Corrección de sintaxis sql con formato string](https://github.com/softdirex/DCSOptics/search?q=V410-008&type=Commits)
 - [[V410-007]Mensaje de error con envio de reporte al email](https://github.com/softdirex/DCSOptics/search?q=V410-007&type=Commits)
 - [[V410-006]Mensaje de error con detalles de la excepción lanzada al insertar registros en sincronización](https://github.com/softdirex/DCSOptics/search?q=V410-006&type=Commits)
 - [[V410-005]Validación de listas vacías en método de búsqueda](https://github.com/softdirex/DCSOptics/search?q=V410-005&type=Commits)
 - [[V410-004]Cierre de panel de herramientas después de escoger una opción](https://github.com/softdirex/DCSOptics/search?q=V410-004&type=Commits)
 - [[V410-003]Reordenamiento de entidades por prioridad para sincronizar](https://github.com/softdirex/DCSOptics/search?q=V410-003&type=Commits)
 - [[V410-002]Comparación de códigos sin discriminar letras mayusculas de minusculas](https://github.com/softdirex/DCSOptics/search?q=V410-002&type=Commits)
 - [[V410-001]Refactoring en sincronizar registros remotos con los locales](https://github.com/softdirex/DCSOptics/search?q=V410-001&type=Commits)


 #### [Version 4.0.9 (V409)](https://github.com/softdirex/DCSOptics/search?q=V409&type=Commits)
 - [[V409-005]Mejora en el proceso de sincronización](https://github.com/softdirex/DCSOptics/search?q=V409-005&type=Commits)
 - [Estandarización en fecha por defecto al sincronizar todo(V409-004)](https://github.com/softdirex/DCSOptics/search?q=V409-004&type=Commits)
 - [Se amplía a dos meses los registros remotos sincronizados(V409-003)](https://github.com/softdirex/DCSOptics/search?q=V409-003&type=Commits)
 - [Refactoring en obtención de fechas con formato(V409-002)](https://github.com/softdirex/DCSOptics/search?q=V409-002&type=Commits)
 - [Refactoring en generación de identificadores(V409-001)](https://github.com/softdirex/DCSOptics/search?q=V409-001&type=Commits)

 #### [Version 4.0.8 (V408)](https://github.com/softdirex/DCSOptics/search?q=V408&type=Commits)
 - [Validación de stock de lentes menores que cero(V408-004)](https://github.com/softdirex/DCSOptics/search?q=V408-004&type=Commits)
 - [Optimizacion de sincronizacion al subir los datos(V408-003)](https://github.com/softdirex/DCSOptics/search?q=V408-003&type=Commits)
 - [Enviar reportes en los mensajes de error(V408-002)](https://github.com/softdirex/DCSOptics/search?q=V408-002&type=Commits)
 - [crear metodos en las entidades para obtener valores a insertar en sql(V408-001)](https://github.com/softdirex/DCSOptics/search?q=V408-001&type=Commits)

 #### [Version 4.0.7 (V407)](https://github.com/softdirex/DCSOptics/search?q=V407&type=Commits)
 - [Refactoring de autocompletado de lentes constock sobre cero(V407-003)](https://github.com/softdirex/DCSOptics/search?q=V407-003&type=Commits)
 - [Refactoring de mensaje de error al no encontrar un lente para aumentar stock(V407-002)](https://github.com/softdirex/DCSOptics/search?q=V407-002&type=Commits)
 - [Se agrega signo mas como caracter valido para generar fichas en los campos de los armazones(V407-001)](https://github.com/softdirex/DCSOptics/search?q=V407-001&type=Commits)

 #### [Version 4.0.6 (V406)](https://github.com/softdirex/DCSOptics/search?q=V406&type=Commits)
 - [Validaciones de cristales y precios al generar ficha nueva(V406-001)](https://github.com/softdirex/DCSOptics/search?q=V406-001&type=Commits)

 #### [Version 4.0.5 (V405)](https://github.com/softdirex/DCSOptics/search?q=V405&type=Commits)
 - [Validaciones de fichas anuladas(V405-015)](https://github.com/softdirex/DCSOptics/search?q=V405-015&type=Commits)
 - [Nuevo formato del id si lanza una excepcion al intentar obtenerlos de la bd(V405-014)](https://github.com/softdirex/DCSOptics/search?q=V405-014&type=Commits)
 - [Validaciones al cargar folio por id(V405-013)](https://github.com/softdirex/DCSOptics/search?q=V405-013&type=Commits)
 - [Validaciones al modificar usuario en la sesión(V405-012)](https://github.com/softdirex/DCSOptics/search?q=V405-012&type=Commits)
 - [Aumentar stock oneClick(V405-011)](https://github.com/softdirex/DCSOptics/search?q=V405-011&type=Commits)
 - [Generar cotizaciones(V405-010)](https://github.com/softdirex/DCSOptics/search?q=V405-010&type=Commits)
 - [Optimizar la carga de registros(V405-009)](https://github.com/softdirex/DCSOptics/search?q=V405-009&type=Commits)
 - [Refactoring de generación de reporte para convenios (V405-008)](https://github.com/softdirex/DCSOptics/search?q=V405-008&type=Commits)
 - [Anular fichas del dia y recuperar stock (V405-007)](https://github.com/softdirex/DCSOptics/search?q=V405-007&type=Commits)
 - [Corrección de registro de abonos en CRUD Fichas UI (V405-006)](https://github.com/softdirex/DCSOptics/search?q=V405-006&type=Commits)
 - [Ver ventas propias del usuario en UI (V405-005)](https://github.com/softdirex/DCSOptics/search?q=V405-005&type=Commits)
 - [Generar excel con sistema antiguo (V405-004)](https://github.com/softdirex/DCSOptics/search?q=V405-004&type=Commits)
 - [Optimizar la generacion de fichas(V405-003)](https://github.com/softdirex/DCSOptics/search?q=V405-003&type=Commits)
 - [Enviar reportes por mail con resumen por vendedor y cantidad de lentes vendidos (V405-002)](https://github.com/softdirex/DCSOptics/search?q=V405-002&type=Commits)
 - [Generar reportes con cantidad de lentes vendidos (V405-001)](https://github.com/softdirex/DCSOptics/search?q=V405-001&type=Commits)
 - [Validaciones de errores en el proceso de sincronización (V-405)](https://github.com/softdirex/DCSOptics/search?q=V-405&type=Commits)

 #### [Version 4.0.4 (V-404)](https://github.com/softdirex/DCSOptics/search?q=V-404&type=Commits)
 
 - Validaciones de errores en el proceso de sincronización
 - Funcionalidad para sincronizar todo en caso de falta de registros

 #### [Version 4.0.3 (V-403)](https://github.com/softdirex/DCSOptics/search?q=V-403&type=Commits)
 
 - Validaciones de acceso mas amigable
 - Autocompletado con menor consumo de recursos
 - Previsualizar garantia despues de ser generada y corregir formato de hora
 - Ajuste de filtrado de registros de fichas

 #### [Version 4.0.2 (V-402)](https://github.com/softdirex/DCSOptics/search?q=V-402&type=Commits)
 
 - Refactoring del código para guardar fichas
 - Validación de sistema operativo para mostrar interfaz relacionada

 