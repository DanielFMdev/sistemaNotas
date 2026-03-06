# Sistema de Notas por Usuario

Aplicación de consola en Java que permite registrar usuarios, iniciar sesión y gestionar notas personales. Los datos se almacenan en ficheros de texto en el sistema de archivos.

## Características

- **Registro de usuarios**: Alta con validación de email (debe contener `@` y `.`) y contraseña (mínimo 6 caracteres).
- **Inicio de sesión**: Acceso con email y contraseña.
- **Gestión de notas** (tras iniciar sesión):
  - Crear nota
  - Listar todas las notas
  - Ver una nota por su número (ID)
  - Eliminar una nota por su número (ID)
- **Persistencia**: Usuarios y notas se guardan en carpetas y ficheros locales.

## Estructura del proyecto

```
SistemasNotas/
├── src/
│   ├── app/
│   │   └── Main.java          # Punto de entrada y menú principal
│   ├── model/
│   │   ├── Login.java         # Lógica de inicio de sesión
│   │   └── Registro.java      # Lógica de registro de usuarios
│   └── service/
│       └── Notas.java         # CRUD de notas (crear, listar, ver, eliminar)
├── README.md
└── ...
```

## Estructura de datos en disco

- **Usuarios**: carpeta `usuarios/`, fichero `usuarios.txt`. Cada línea: `email : contraseña`.
- **Notas**: carpeta `notas/<email_usuario>/`, fichero de notas por usuario. Cada línea: `id contenido_nota`.

Las carpetas y ficheros se crean automáticamente al registrar el primer usuario o al crear la primera nota.

## Requisitos

- **Java** (JDK 8 o superior), con `javac` y `java` en el PATH.

## Cómo compilar y ejecutar

Desde la raíz del proyecto `SistemasNotas`:

```bash
# Compilar (los .java están en src con paquetes app, model, service)
javac -d out src/app/Main.java src/model/Login.java src/model/Registro.java src/service/Notas.java

# Ejecutar
java -cp out app.Main
```

Si usas un IDE (p. ej. IntelliJ IDEA), abre el proyecto y ejecuta la clase `app.Main`.

## Uso

1. **Menú principal**
   - `1` → Registrarse (email y contraseña).
   - `2` → Iniciar sesión (email y contraseña).
   - `0` → Salir.

2. **Tras iniciar sesión**
   - `1` → Crear nota (se pide el texto).
   - `2` → Listar todas tus notas.
   - `3` → Ver una nota por número (ID).
   - `4` → Eliminar una nota por número (ID).
   - `0` → Cerrar sesión (vuelves al menú principal).

## Notas técnicas

- La autenticación es por comparación directa de email y contraseña en texto plano; el proyecto es didáctico y no está pensado para entornos con requisitos de seguridad.
- Las notas se identifican por un número (ID) que se asigna al crear cada nota.
- Los paths de usuarios y notas son relativos al directorio desde el que se ejecuta la aplicación.

## Licencia

Proyecto de uso educativo/didáctico.
