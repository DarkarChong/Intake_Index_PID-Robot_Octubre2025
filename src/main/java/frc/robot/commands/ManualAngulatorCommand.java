package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

/**
 * Comando que controla el angulador manualmente con el joystick
 * Usa un DoubleSupplier para obtener el valor del joystick en tiempo real
 */
public class ManualAngulatorCommand extends Command {
    
    private final IntakeSubsystem intakeSubsystem;
    private final DoubleSupplier joystickSupplier;
    
   
    public ManualAngulatorCommand(
        IntakeSubsystem intakeSubsystem,
        DoubleSupplier joystickSupplier
    ) {
        this.intakeSubsystem = intakeSubsystem;
        this.joystickSupplier = joystickSupplier;
        
        addRequirements(intakeSubsystem);
    }
    

    @Override
    public void initialize() {
        System.out.println("Control manual del angulador activado");
    }
    
   
    @Override
    public void execute() {
        // Obtener el valor actual del joystick
        double joystickValue = joystickSupplier.getAsDouble();
        
        // Aplicar una zona muerta para evitar movimiento no querido
        if (Math.abs(joystickValue) < 0.05) {
            joystickValue = 0;
        }
        
        // Mover el angulador con el valor del joystick
        intakeSubsystem.setAngulatorManual(joystickValue);
    }
    
   
    @Override
    public void end(boolean interrupted) {
        intakeSubsystem.stopAngulator();
        System.out.println("Control manual del angulador desactivado");
    }
    
  
    @Override
    public boolean isFinished() {
        return false;
    }
}