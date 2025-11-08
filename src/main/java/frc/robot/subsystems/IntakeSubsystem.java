package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

/**
 * Subsistema del Intake
 * Controla:
 * - Motor angulador (Neo) con control PID integrado
 * - Motor de ruedas (Kraken x60)
 */
public class IntakeSubsystem extends SubsystemBase {
    
    // Declaración de motores
    private final SparkMax angulatorMotor;
    private final TalonFX intakeWheelsMotor;
    
    /**
     * Constructor del subsistema
     * Aquí inicializamos y configuramos todos los motores
     */
    public IntakeSubsystem() {
        // CONFIGURACIÓN DEL MOTOR ANGULADOR (NEO con SparkMax) 
        angulatorMotor = new SparkMax(IntakeConstants.ANGULATOR_MOTOR_ID, MotorType.kBrushless);
        
        // Crear objeto de configuración
        SparkMaxConfig angulatorConfig = new SparkMaxConfig();
        
        // Configurar modo idle (brake = freno, coast = deslizamiento)
        angulatorConfig.idleMode(IdleMode.kBrake);
        
        // Configurar el PID Controller del SparkMax
   
        angulatorConfig.closedLoop.pid(
            IntakeConstants.kP,  // P: Proporcional - qué tan agresivo corrige el error
            IntakeConstants.kI,  // I: Integral - corrige errores acumulados
            IntakeConstants.kD   // D: Derivativo - amortigua oscilaciones
        );
        
        // Aplicar la configuración al motor
        angulatorMotor.configure(angulatorConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        
        // Resetear el encoder a cero (posición inicial)
        angulatorMotor.getEncoder().setPosition(0);
        
        // ===== CONFIGURACIÓN DEL MOTOR DE RUEDAS (KRAKEN x60) =====
        intakeWheelsMotor = new TalonFX(IntakeConstants.INTAKE_WHEELS_MOTOR_ID);
        
        // Configurar el Kraken en modo freno
        intakeWheelsMotor.setNeutralMode(NeutralModeValue.Brake);
    }
    
    /**
     * Controla el angulador manualmente (con joystick)
     * speed Velocidad entre -0.3 y 0.3
     */
    public void setAngulatorManual(double speed) {
        angulatorMotor.set(speed);
    }
    
 
    public void setAngulatorPID(double targetPosition) {
        // Usar el ClosedLoopController del SparkMax
        // ControlType.kPosition significa que queremos ir a una posición específica
        angulatorMotor.getClosedLoopController().setReference(
            targetPosition,
            ControlType.kPosition
        );
    }
    
    /**
     * Detiene el angulador completamente
     */
    public void stopAngulator() {
        angulatorMotor.set(0);
    }
    
    
    public void setIntakeWheels(double speed) {
        intakeWheelsMotor.set(speed);
    }
    
  
    public double getAngulatorPosition() {
        return angulatorMotor.getEncoder().getPosition();
    }
    
  
    public boolean atTargetPosition(double targetPosition) {
        double currentPosition = getAngulatorPosition();
        return Math.abs(currentPosition - targetPosition) < IntakeConstants.POSITION_TOLERANCE;
    }
    
    /**
     * Resetea el encoder del angulador a cero
     */
    public void resetAngulatorEncoder() {
        angulatorMotor.getEncoder().setPosition(0);
    }
 
    @Override
    public void periodic() {
        // Aquí se puede agregar teelemetría
       SmartDashboard.putNumber("Angulator Position", getAngulatorPosition());
    }
}