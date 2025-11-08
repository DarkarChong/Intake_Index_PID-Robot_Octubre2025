package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.IndexSubsystem;

/**
 * Comando que controla todas las ruedas (Intake + Index) simultáneamente
 * Se usa para los botones 1 y 2 del control
 */
public class RunIntakeIndexCommand extends Command {
    
    private final IntakeSubsystem intakeSubsystem;
    private final IndexSubsystem indexSubsystem;
    private final double intakeSpeed;
    private final double indexSpeed;
    
  
    public RunIntakeIndexCommand(
        IntakeSubsystem intakeSubsystem,
        IndexSubsystem indexSubsystem,
        double intakeSpeed,
        double indexSpeed
    ) {
        this.intakeSubsystem = intakeSubsystem;
        this.indexSubsystem = indexSubsystem;
        this.intakeSpeed = intakeSpeed;
        this.indexSpeed = indexSpeed;
        
        addRequirements(intakeSubsystem, indexSubsystem);
    }
    
    
    @Override
    public void initialize() {
        System.out.println("RunIntakeIndexCommand iniciado");
    }

    @Override
    public void execute() {
        // Activar ruedas del intake
        intakeSubsystem.setIntakeWheels(intakeSpeed);
        
        // Activar ruedas del index
        indexSubsystem.setIndexWheels(indexSpeed);
    }
    

    @Override
    public void end(boolean interrupted) {
        // Detener todas las ruedas cuando se suelta el botón
        intakeSubsystem.setIntakeWheels(0);
        indexSubsystem.stopIndexWheels();
        
        System.out.println("RunIntakeIndexCommand terminado");
    }
    

    @Override
    public boolean isFinished() {
     
        return false;
    }
}