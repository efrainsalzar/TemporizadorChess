# TemporizadorChess

TemporizadorChess es una aplicación Android desarrollada para servir como temporizador de ajedrez, permitiendo gestionar partidas entre dos jugadores y almacenar los resultados en Firebase. El proyecto incluye funcionalidades de configuración de tiempo, seguimiento de movimientos y resultados, e informes de partidas jugadas.

## Características principales

- **Temporizador para dos jugadores**: Control individual del tiempo de cada jugador, con botones para iniciar, pausar y reiniciar los relojes.
- **Configuración personalizada**: Permite establecer minutos y segundos de juego para cada partida mediante una interfaz intuitiva.
- **Registro de jugadores**: Entrada de nombres para ambos jugadores antes de iniciar la partida.
- **Seguimiento de movimientos**: Contador de movimientos realizados por cada jugador durante la partida.
- **Detección de ganador**: Al finalizar el tiempo, la app determina y muestra automáticamente quién ganó o perdió.
- **Almacenamiento de resultados**: Los datos de cada partida (jugadores, tiempo, movimientos, resultado) se almacenan en Firebase Firestore, generando colecciones por fecha.
- **Informe de partidas**: Módulo de informes para visualizar partidas anteriores y sus estadísticas usando RecyclerView.
- **Interfaz sobre el juego**: Pantalla "Acerca del Juego" para mostrar información relevante y controles de navegación sencillos.

## Estructura del proyecto

- `MainActivity.kt`: Entrada principal. Recibe los nombres de los jugadores y permite configurar la partida.
- `Configuracion.kt`: Permite ajustar el tiempo de juego y realizar validaciones sobre los valores ingresados.
- `Temporizador.kt`: Lógica principal del temporizador, control de los relojes y botones de acción.
- `Resultados.kt`: Muestra los resultados de la partida e integra el guardado en Firebase.
- `InformeResult.kt`: Recupera y muestra estadísticas de partidas pasadas desde Firebase usando listas y adaptadores personalizados.
- `CustomAdapter.kt` y `ItemsViewModel.kt`: Adaptadores y modelos para mostrar datos de partidas en listas (RecyclerView).
- `AcercadeJuego.kt`: Pantalla informativa sobre el funcionamiento del temporizador.
- **Pruebas unitarias e instrumentadas**: Directorios `test` y `androidTest` con ejemplos básicos.

## Integración con Firebase

- El proyecto utiliza Firestore para almacenar los datos de cada partida.
- Los resultados se guardan con la fecha como nombre de colección, permitiendo organizar por día.
- Los informes recuperan y muestran estos datos en la app.

## Cómo usar

1. Ejecuta la app en un dispositivo Android.
2. Ingresa los nombres de los jugadores y configura el tiempo de la partida.
3. Inicia el temporizador y juega.
4. Al finalizar la partida, revisa los resultados y accede a los informes desde el menú.

## Capturas de pantalla

_Agrega aquí tus imágenes si lo deseas._

## Autor

- [efrainsalzar](https://github.com/efrainsalzar)

---

Este proyecto fue realizado como práctica de desarrollo móvil, integrando gestión de estados, almacenamiento en la nube y presentación de datos en Android.
