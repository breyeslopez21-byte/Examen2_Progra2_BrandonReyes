# Sistema de Administración de Parqueo 🚗🏍️

Este proyecto es una aplicación en **Java** que permite administrar el ingreso y salida de vehículos en un parqueo.
El sistema registra la información de los vehículos y calcula automáticamente el monto a pagar según el tiempo de permanencia.

## Características

* Registro de ingreso de vehículos
* Registro de salida de vehículos
* Cálculo automático del monto a pagar
* Visualización de los vehículos en una tabla
* Almacenamiento de la información en un archivo de texto
* Separación del proyecto por capas

## Tipos de vehículo y tarifas

| Tipo  | Tarifa por hora |
| ----- | --------------- |
| Moto  | ₡500            |
| Carro | ₡1000           |

El monto se calcula según el tiempo transcurrido entre la **hora de entrada** y la **hora de salida**.

## Estructura del proyecto

El proyecto está dividido en paquetes siguiendo una arquitectura por capas:

```
ProyectoParqueo
│
├── App
│   └── App.java (Clase main que inicia el sistema)
│
├── Presentacion
│   └── JFrame con la interfaz gráfica
│
├── Logica
│   └── Logica.java (Reglas del negocio)
│
├── Datos
│   └── Datos.java (Lectura y escritura del archivo)
│
└── Entidades
    └── Vehiculos.java (Modelo de datos)
```

## Archivo de almacenamiento

Los datos se guardan en el archivo:

```
parqueo.txt
```

Cada línea del archivo contiene:

```
placa,tipo,horaEntrada,horaSalida,monto
```

Ejemplo:

```
ABC123,Carro,2026-04-15T10:00,null,0
XYZ789,Moto,2026-04-15T09:30,2026-04-15T10:45,1000
```

## Tecnologías utilizadas

* Java
* Swing (JFrame)
* Programación orientada a objetos
* Manejo de archivos (BufferedReader / BufferedWriter)

## Cómo ejecutar el proyecto

1. Clonar el repositorio:

```
git clone https://github.com/tuusuario/tu-repositorio.git
```

2. Abrir el proyecto en **NetBeans o IntelliJ**.

3. Ejecutar la clase:

```
App.java
```

## Autor

Proyecto desarrollado como práctica académica para el curso de **Programación en Java**.
