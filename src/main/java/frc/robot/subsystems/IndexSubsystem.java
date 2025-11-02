package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IndexConstants;

/**
 * Subsistema del Index
 * Controla dos motores Neo que giran en direcciones opuestas
 * para mover game pieces hacia adentro del robot
 */
public class IndexSubsystem extends SubsystemBase {
    
    private final SparkMax leftMotor;
    private final SparkMax rightMotor;
    
  
    public IndexSubsystem() {
        leftMotor = new SparkMax(IndexConstants.LEFT_MOTOR_ID, MotorType.kBrushless);
        
        SparkMaxConfig leftConfig = new SparkMaxConfig();
        leftConfig.idleMode(IdleMode.kBrake);
        // NO invertimos el motor izquierdo
        leftConfig.inverted(false);
        
        leftMotor.configure(leftConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        
        rightMotor = new SparkMax(IndexConstants.RIGHT_MOTOR_ID, MotorType.kBrushless);
        
        SparkMaxConfig rightConfig = new SparkMaxConfig();
        rightConfig.idleMode(IdleMode.kBrake);
        // Invertimos el motor derecho para que gire en dirección opuesta
        rightConfig.inverted(true);
        
        rightMotor.configure(rightConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }
    
  
    public void setIndexWheels(double speed) {
        // Ambos motores reciben la misma velocidad
        // Como uno está invertido, girarán en direcciones opuestas
        leftMotor.set(speed);
        rightMotor.set(speed);
    }
    
  
    public void stopIndexWheels() {
        leftMotor.set(0);
        rightMotor.set(0);
    }
    

    public double getLeftMotorSpeed() {
        return leftMotor.get();
    }
    
  
    public double getRightMotorSpeed() {
        return rightMotor.get();
    }
    
    
    @Override
    public void periodic() {
        // Aquí puedes agregar telemetría si necesitas
        // SmartDashboard.putNumber("Left Index Speed", getLeftMotorSpeed());
        // SmartDashboard.putNumber("Right Index Speed", getRightMotorSpeed());
    }
}