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
Puedes descargar el sistema y ver máss información en el [sitio web de Softdirex](http://optidata.softdirex.cl)

Requisitos previos:
- Tener instalado [java 1.8 o superior](https://www.java.com/es/download/)
Para instalar:
- No requiere de otro software adicional, la instalación será más sencilla y no será necesario tener un mayor conocimiento 
técnico para la puesta en marcha del sistema.

 # System versions
 ## Registro de versiones del sistema

 #### [Version 4.0.5 (V405)](https://github.com/softdirex/DCSOptics/search?q=V405&type=Commits)
 
 - Validaciones de errores en el proceso de sincronización [OK]
 - [Ver ventas propias del usuario en UI (V405-004)](https://github.com/softdirex/DCSOptics/search?q=V405-004&type=Commits)
 - [Generar excel con sistema antiguo (V405-003)](https://github.com/softdirex/DCSOptics/search?q=V405-003&type=Commits)
 - [Enviar reportes por mail con resumen por vendedor y cantidad de lentes vendidos (V405-002)](https://github.com/softdirex/DCSOptics/search?q=V405-002&type=Commits)
 - [Generar reportes con cantidad de lentes vendidos (V405-001)](https://github.com/softdirex/DCSOptics/search?q=V405-001&type=Commits)

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

 