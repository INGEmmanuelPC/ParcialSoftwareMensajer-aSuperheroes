# Sistema de Asignación de Misiones — Avengers

Sistema de mensajería y asignación automática de misiones para superhéroes, desarrollado en **Java 17+** con **Gradle** y una interfaz gráfica **Swing**.

---

## Descripción

Este proyecto permite:

- **Crear héroes** con habilidades específicas (Acuático, Volador, Trepamuros, Sigiloso, Control de Fuego).
- **Crear misiones** con requisitos de habilidades.
- **Asignar misiones automáticamente**: el sistema selecciona al héroe más adecuado según sus habilidades, sin intervención del usuario.
- **Notificar** los resultados vía Telegram o Email (configurable).

El sistema aplica los principios **SOLID**:

| Principio | Aplicación |
|-----------|-----------|
| **SRP** | Cada clase tiene una sola responsabilidad (Config crea, AsignadorMisiones orquesta, MainForm presenta). |
| **OCP** | Se agregan nuevos validadores o servicios de mensajería sin modificar código existente. |
| **LSP** | Todos los héroes concretos sustituyen a `SuperHeroe` sin romper el contrato. |
| **ISP** | Interfaces segregadas por habilidad (`IVolador`, `IAcuatico`, etc.). |
| **DIP** | Las dependencias se inyectan a través de `Config`, usando interfaces. |

---

## Requisitos previos

- **Java JDK 17** o superior ([descargar aquí](https://adoptium.net/))
- **IntelliJ IDEA** (recomendado) o cualquier IDE con soporte Gradle
- **Git** (para clonar el repositorio)

---

## Instalación

### 1. Clonar el repositorio

```bash
git clone https://github.com/tu-usuario/ParcialMensajeriaSuperheroes.git
cd ParcialMensajeriaSuperheroes
```

### 2. Configurar variables de entorno

Copiar la plantilla de variables de entorno y editarla con tus credenciales:

```bash
cp .env.example .env
```

Abrir el archivo `.env` con tu editor de texto favorito y reemplazar los valores:

```properties
# Telegram
TELEGRAM_BOT_TOKEN=123456:ABC-DEF1234ghIkl-zyx57W2v1u123ew11
TELEGRAM_CHAT_ID=987654321

# Email (SMTP)
SMTP_HOST=smtp.gmail.com
SMTP_PORT=587
SENDER_EMAIL=tu_correo@gmail.com
SENDER_PASSWORD=tu_contraseña_de_aplicacion
```

> **⚠️ IMPORTANTE:** El archivo `.env` contiene credenciales sensibles y **nunca** debe subirse al repositorio. Ya está incluido en el `.gitignore`.

### 3. Abrir en IntelliJ IDEA

1. Abrir IntelliJ IDEA.
2. Seleccionar **File → Open** y elegir la carpeta del proyecto.
3. IntelliJ detectará automáticamente que es un proyecto Gradle y descargará las dependencias.
4. Esperar a que termine la sincronización de Gradle (barra inferior).

---

## Ejecución

### Modo Interfaz Gráfica (por defecto)

Ejecutar la clase `ParcialMain` sin argumentos. Desde IntelliJ:

1. Navegar a `src/main/java/parcial1/solid/avengers/main/ParcialMain.java`
2. Click derecho → **Run 'ParcialMain.main()'**

O desde la terminal:

```bash
./gradlew run
```

### Modo Consola

Para ejecutar la demo por consola con asignación automática:

```bash
./gradlew run --args="--consola"
```

---

## Uso de la Interfaz Gráfica

1. **Crear un héroe**: ingresar nombre, seleccionar habilidades y presionar "Crear Héroe". Los héroes predefinidos (Aquaman, SpiderMan, Thor, IronMan, Hulk) ya están cargados.

2. **Crear una misión**: ingresar nombre, seleccionar las habilidades requeridas y presionar "Crear Misión".

3. **Asignar misiones**: presionar el botón verde "Asignar Misiones Automáticamente". El sistema seleccionará al héroe más adecuado para cada misión y mostrará los resultados.

---

## Estructura del proyecto

```
ParcialMensajeriaSuperheroes/
├── .env                          # Variables sensibles (NO se sube al repo)
├── .env.example                  # Plantilla de variables de entorno
├── .gitignore                    # Archivos ignorados por Git
├── build.gradle.kts              # Configuración de Gradle
├── settings.gradle.kts           # Nombre del proyecto
├── README.md                     # Este archivo
└── src/main/java/parcial1/solid/avengers/
    ├── config/
    │   └── Config.java           # Fábrica de dependencias (lee .env)
    ├── gui/
    │   └── MainForm.java         # Interfaz gráfica Swing
    ├── heroes/
    │   ├── SuperHeroe.java       # Clase abstracta base
    │   ├── CustomHero.java       # Héroe creado por el usuario
    │   ├── Aquaman.java
    │   ├── Hulk.java
    │   ├── IronMan.java
    │   ├── SpiderMan.java
    │   └── Thor.java
    ├── interfaces/
    │   ├── IAcuatico.java
    │   ├── IControlFuego.java
    │   ├── ISigiloso.java
    │   ├── ITrepamuros.java
    │   └── IVolador.java
    ├── main/
    │   └── ParcialMain.java      # Punto de entrada
    ├── mensajeria/
    │   ├── IServicioMensajeria.java  # Contrato de mensajería
    │   ├── TelegramService.java
    │   └── EmailNotifier.java
    ├── misiones/
    │   ├── TipoHabilidad.java
    │   ├── Mision.java
    │   ├── AsignadorMisiones.java
    │   └── ResultadoAsignacion.java
    └── validation/
        ├── CompatibilityValidator.java  # Contrato de validación
        └── SkillBasedValidator.java
```

---

## Generar Javadoc

Todas las clases, métodos, campos y paquetes están completamente documentados. Para generar la documentación:

### Desde IntelliJ IDEA

1. Ir a **Tools → Generate JavaDoc...**
2. Seleccionar el scope del proyecto.
3. Presionar **OK**.

### Desde la terminal

```bash
./gradlew javadoc
```

La documentación se generará en `build/docs/javadoc/`.

> El proyecto está configurado con `-Xdoclint:none` para prevenir errores de compilación en la generación de Javadoc.

---

## Flujo GitFlow

El proyecto sigue la convención **GitFlow**:

| Rama | Propósito |
|------|----------|
| `main` | Código en producción, estable. |
| `develop` | Rama de integración para desarrollo. |
| `feature/*` | Nuevas funcionalidades (ej: `feature/asignacion-automatica`). |
| `hotfix/*` | Correcciones urgentes sobre `main`. |
| `release/*` | Preparación de versiones. |

### Ejemplo de flujo de trabajo

```bash
# Crear rama de feature desde develop
git checkout develop
git checkout -b feature/nueva-funcionalidad

# Trabajar y hacer commits
git add .
git commit -m "feat: agregar nueva funcionalidad"

# Volver a develop y mergear
git checkout develop
git merge feature/nueva-funcionalidad

# Cuando develop esté listo para producción
git checkout main
git merge develop
git tag -a v1.0.0 -m "Versión 1.0.0"
```

---

## Tecnologías utilizadas

- **Java 17+**
- **Gradle (Kotlin DSL)**
- **Swing** (interfaz gráfica)
- **dotenv-java** (lectura de variables de entorno desde `.env`)
- **JUnit 5** (pruebas unitarias)
- **JavaMail / Angus Mail** (soporte para email)

---

## Licencia

Proyecto académico — Parcial de Ingeniería de Software.
