# UNIVERSIDAD POPULAR DEL CESAR
## Ingeniería de Sistemas e Informática

# GUÍA DEL TALLER
## Refactorización a Código Limpio
## Sistema de Facturación — Restaurante El Buen Sabor

**Programación de Computadores III · SS462 · Cuarto Semestre**
**Docente:** Ing. Alfredo David Bautista Romero

---

## 1. ¿Qué van a encontrar?

El proyecto RestauranteBuenSabor es un sistema de facturación para un restaurante que funciona correctamente, pero está escrito con el máximo de malas prácticas posibles. Su misión es refactorizarlo hasta convertirlo en código limpio, sin alterar el comportamiento del programa.

> ⚠️ El programa produce resultados correctos. El problema no es que falle — el problema es que es imposible de mantener, entender y modificar. Eso es exactamente lo que pasa en la industria real.

## 2. ¿Qué hace el sistema?

El programa simula la operación de caja de un restaurante con un menú interactivo de 6 opciones.

### Reglas de negocio
- **Subtotal**: suma de precio × cantidad para cada producto pedido
- **Descuento 5%**: se aplica si hay más de 3 productos diferentes en el pedido
- **IVA 19%**: se calcula sobre el subtotal después del descuento
- **Propina 10%**: se aplica si el subtotal (antes del IVA) supera $50.000
- **Total**: subtotal con descuento + IVA + propina

## 3. Catálogo de 34 Malas Prácticas

(Documentadas en el código original y corregidas en esta refactorización)

## 4. Niveles de Refactorización

- **N1 — Formato**: Indentación, llaves, espacios
- **N2 — Nombres**: Variables y métodos descriptivos
- **N3 — Estructura**: Constantes, eliminar duplicación, métodos pequeños
- **N4 — Diseño**: Clases con responsabilidad única (SRP)
- **N5 — Comentarios**: Solo el "por qué", no el "qué"

## 5. Criterio de Éxito

1. Produce los mismos resultados
2. Se lee de arriba a abajo
3. Cada clase tiene una sola responsabilidad
4. Los nombres hablan
5. No hay duplicación
6. Sin comentarios que expliquen el qué
