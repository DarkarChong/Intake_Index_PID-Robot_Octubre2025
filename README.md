# Intake & Index Robot - Control PID

Código de FRC para subsistemas de Intake e Index con control PID integrado del SparkMax.


---

## 🤖 Descripción del Sistema

Este robot implementa dos subsistemas principales:

### **Intake Subsystem**
- **Angulador**: Motor Neo Brushless con control PID integrado del SparkMax
- **Ruedas**: Motor Kraken x60 para recolección de game pieces

### **Index Subsystem**
- **Dos motores Neo** girando en direcciones opuestas para mover piezas hacia el robot

---

## ⚙️ Hardware Configuration

| Componente | Motor | Controlador | CAN ID |
|------------|-------|-------------|--------|
| Angulador Intake | Neo Brushless | SparkMax | 1 |
| Ruedas Intake | Kraken x60 | TalonFX | 2 |
| Index Izquierdo | Neo Brushless | SparkMax | 3 |
| Index Derecho | Neo Brushless | SparkMax | 4 |

---

## 🎮 Controles

### Control Xbox - Puerto 0

| Botón | Función |
|-------|---------|
| **A (1)** | Girar todas las ruedas hacia adelante (intake + index) |
| **B (2)** | Girar todas las ruedas en reversa (intake + index) |
| **X (3)** | Control PID del angulador (va a posición objetivo) |
| **Joystick Izquierdo (Y)** | Control manual del angulador (límite: 30%) |

---

## 🔧 Configuración de Software

### Vendor Libraries
- **Phoenix 6** (CTR Electronics) - Para Kraken x60
- **REVLib 2025** - Para motores Neo

### Valores de PID (en `Constants.java`)
```java
kP = 0.1  // Proporcional
kI = 0.0  // Integral
kD = 0.0  // Derivativo
```

 **Nota:** Estos valores deben calibrarse con el robot físico.

### Otros Parámetros Importantes
```java
MANUAL_SPEED_LIMIT = 0.3    // 30% velocidad máxima manual
INTAKE_SPEED = 0.7          // 70% velocidad de ruedas
INDEX_SPEED = 0.6           // 60% velocidad del index
TARGET_POSITION = 10.0      // Posición objetivo del PID (en rotaciones)
POSITION_TOLERANCE = 0.5    // Tolerancia de posición (±0.5 rotaciones)
```

---


Ver más detalles en la [documentación de calibración PID](docs/PID_TUNING.md)

---

##  Recursos Adicionales

- [Documentación Phoenix 6 (CTR)](https://v6.docs.ctr-electronics.com/)
- [Documentación REVLib 2025](https://docs.revrobotics.com/revlib/)
- [WPILib Command-Based Programming](https://docs.wpilib.org/en/stable/docs/software/commandbased/index.html)

---

##  Licencia

Este proyecto utiliza la licencia estándar de WPILib. Ver [WPILib-License.md](WPILib-License.md) para más detalles.

---

##  Notas Importantes

1. **IDs de motores:** Verificar que los CAN IDs coincidan con el robot físico
2. **TARGET_POSITION:** Debe medirse con el mecanismo específico
3. **Valores de PID:** Requieren calibración en el robot real
4. **Velocidades:** Ajusta según las características del intake

---
