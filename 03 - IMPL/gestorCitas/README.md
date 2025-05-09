# Proyecto de Gestión de Citas Médicas

Este proyecto es una aplicación de gestión de citas médicas desarrollada en **Spring Boot** utilizando **MongoDB** como base de datos. El sistema permite a los usuarios gestionar y consultar citas médicas para pacientes, doctores y consultorios, incluyendo reglas de validación como la disponibilidad de citas, límite de citas por doctor, etc.

## Características Principales

- **Gestión de Citas Médicas**: Permite a los usuarios agendar, editar y consultar citas médicas.
- **Gestión de Doctores**: Permite agregar, editar y consultar doctores.
- **Gestión de Consultorios**: Permite agregar, editar y consultar consultorios.
- **Reglas de Validación**: 
  - No se pueden agendar citas para el mismo doctor o consultorio a la misma hora.
  - Un paciente no puede tener dos citas en el mismo día con menos de 2 horas de diferencia.
  - Un doctor no puede tener más de 8 citas en un día.
- **Interfaz de Usuario**: Se utiliza **Thymeleaf** como motor de plantillas para las vistas web.

---

## Estructura del Proyecto

- **Spring Boot**: El backend de la aplicación está basado en **Spring Boot**.
- **MongoDB**: Se utiliza **MongoDB** como base de datos para almacenar la información de las citas, doctores y consultorios.
- **Docker**: Se utiliza **Docker** para crear un contenedor para la aplicación y MongoDB, lo que facilita el despliegue en diferentes entornos.

---

## Requisitos Previos

- **Docker**: Asegúrate de tener **Docker** y **Docker Compose** instalados en tu máquina. Puedes seguir los pasos en [Docker Docs](https://docs.docker.com/get-docker/) para instalar Docker y Docker Compose.

---

## Pasos para Desplegar la Aplicación en Docker

### 1. **Clonar el Repositorio**

Primero, clona este repositorio en tu máquina local:

```bash
git clone https://github.com/ernestoMartinezEspinosa/showcase-springboots.git
cd repo
