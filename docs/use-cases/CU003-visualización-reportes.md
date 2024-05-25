# CU003 - Visualización de reportes
---

**Descripción:**  El usuario puede ver todos los reportes que se han creado.

**Actor:** Usuario

```plantuml
@startuml "Visualizar reporte"
left to right direction
skinparam packageStyle rectangle

actor usuario
rectangle CyclistAlert {
  usuario -- (Visualizar reportes)
}

@enduml
```

## Flujo de Eventos (Guión)


| Actor  | Sistema |
|--------|---------|
| 1. Ingresa al feed||
| | 2. Se despliegan todos los reportes |
