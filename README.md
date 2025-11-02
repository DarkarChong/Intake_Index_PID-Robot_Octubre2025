# Intake & Index Robot - Control PID

Código de FRC para subsistemas de Intake e Index con control PID integrado del SparkMax.

**Actividad:** Veteranos - Octubre 2025  
**Proyecto:** Sistema de intake angulador con ruedas e index paralelo

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

⚠️ **Nota:** Estos valores deben calibrarse con el robot físico.

### Otros Parámetros Importantes
```java
MANUAL_SPEED_LIMIT = 0.3    // 30% velocidad máxima manual
INTAKE_SPEED = 0.7          // 70% velocidad de ruedas
INDEX_SPEED = 0.6           // 60% velocidad del index
TARGET_POSITION = 10.0      // Posición objetivo del PID (en rotaciones)
POSITION_TOLERANCE = 0.5    // Tolerancia de posición (±0.5 rotaciones)
```

---

## 📁 Estructura del Código

```
src/main/java/frc/robot/
├── commands/
│   ├── ManualAngulatorCommand.java      # Control manual con joystick
│   ├── PIDAngulatorCommand.java         # Control PID a posición fija
│   └── RunIntakeIndexCommand.java       # Control de todas las ruedas
├── subsystems/
│   ├── IntakeSubsystem.java             # Subsistema del intake
│   └── IndexSubsystem.java              # Subsistema del index
├── Constants.java                       # Todas las constantes centralizadas
├── Main.java                            # Punto de entrada
├── Robot.java                           # Clase principal del robot
└── RobotContainer.java                  # Configuración de controles y subsistemas
```

---

## 🚀 Cómo usar este código

### 1. Clonar el repositorio
```bash
git clone https://github.com/DarkarChong/Intake_Index_PID-Robot_Octubre2025.git
cd Intake_Index_PID-Robot_Octubre2025
```

### 2. Abrir en VS Code con WPILib
```bash
code .
```

### 3. Compilar
- **Ctrl+Shift+P** → `WPILib: Build Robot Code`

### 4. Deploy al robot
- **Ctrl+Shift+P** → `WPILib: Deploy Robot Code`

### 5. Probar en simulación
- **Ctrl+Shift+P** → `WPILib: Simulate Robot Code`

---

## 🔨 Calibración del PID

Para calibrar los valores de PID en tu robot:

1. **Prueba inicial:** Deja `kP = 0.05`, `kI = 0.0`, `kD = 0.0`
2. **Ajusta P:** Incrementa hasta que el angulador llegue al objetivo (puede oscilar)
3. **Ajusta D:** Agrega para amortiguar oscilaciones
4. **Ajusta I:** Solo si no llega exactamente al objetivo (rara vez necesario)

Ver más detalles en la [documentación de calibración PID](docs/PID_TUNING.md)

---

## 📊 Características Técnicas

### Control PID Integrado
Este proyecto utiliza el **PID Controller integrado del SparkMax** (no el de WPILib), que ofrece:
- ✅ Menor latencia (control en el hardware)
- ✅ Mayor precisión
- ✅ Configuración declarativa con `SparkMaxConfig`

### Command-Based Robot
Arquitectura moderna de WPILib que separa:
- **Subsistemas** → Hardware y lógica de control
- **Comandos** → Acciones específicas del robot
- **RobotContainer** → Mapeo de controles

---

## 🎓 Recursos Adicionales

- [Documentación Phoenix 6 (CTR)](https://v6.docs.ctr-electronics.com/)
- [Documentación REVLib 2025](https://docs.revrobotics.com/revlib/)
- [WPILib Command-Based Programming](https://docs.wpilib.org/en/stable/docs/software/commandbased/index.html)

---

## 👥 Equipo

**Autor:** DarkarChong  
**Proyecto:** Actividad Veteranos FRC  
**Fecha:** Noviembre 2025

---

## 📄 Licencia

Este proyecto utiliza la licencia estándar de WPILib. Ver [WPILib-License.md](WPILib-License.md) para más detalles.

---

## ⚠️ Notas Importantes

1. **IDs de motores:** Verifica que los CAN IDs coincidan con tu robot físico
2. **TARGET_POSITION:** Debe medirse con tu mecanismo específico
3. **Valores de PID:** Requieren calibración en el robot real
4. **Velocidades:** Ajusta según las características de tu intake

---

## 🐛 Troubleshooting

### El robot no responde
- Verifica que el Driver Station esté conectado
- Revisa que los CAN IDs sean correctos
- Confirma que las vendor libraries estén instaladas

### El PID no funciona bien
- Verifica que `TARGET_POSITION` sea correcto
- Calibra los valores kP, kI, kD
- Revisa que el encoder esté configurado correctamente

### Motores giran al revés
- Cambia `inverted(true/false)` en la configuración del motor

---

**¿Preguntas o problemas?** Abre un [Issue](https://github.com/DarkarChong/Intake_Index_PID-Robot_Octubre2025/issues) en este repositorio.
