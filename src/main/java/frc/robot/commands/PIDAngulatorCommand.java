package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.IntakeConstants;
import frc.robot.subsystems.IntakeSubsystem;

/**
 * Comando que mueve el angulador a una posición específica y la mantiene
 * en esa posición mientras se mantenga presionado el botón 3
 */
public class PIDAngulatorCommand extends Command {
    
    private final IntakeSubsystem intakeSubsystem;
    private final double targetPosition;
    
   
    public PIDAngulatorCommand(
        IntakeSubsystem intakeSubsystem,
        double targetPosition
    ) {
        this.intakeSubsystem = intakeSubsystem;
        this.targetPosition = targetPosition;
        
        // Este comando requiere el intake subsystem
        addRequirements(intakeSubsystem);
    }
    
    
    @Override
    public void initialize() {
        System.out.println("PID Control activado - Objetivo: " + targetPosition + " rotaciones");
        
        // Activar el control PID del SparkMax
        intakeSubsystem.setAngulatorPID(targetPosition);
    }
    
  
    @Override
    public void execute() {

        intakeSubsystem.setAngulatorPID(targetPosition);
        
        // System.out.println("Posición actual: " + intakeSubsystem.getAngulatorPosition());
    }
    

    @Override
    public void end(boolean interrupted) {
        // Detener el angulador cuando se suelta el botón
        intakeSubsystem.stopAngulator();
        
        if (interrupted) {
            System.out.println("PID Control interrumpido");
        } else {
            System.out.println("PID Control completado");
        }
    }
    
  
    @Override
    public boolean isFinished() {
        // return intakeSubsystem.atTargetPosition(targetPosition);
        
 
        return false;
    }
}