package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.IntakeConstants;
import frc.robot.Constants.IndexConstants;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.ManualAngulatorCommand;
import frc.robot.commands.PIDAngulatorCommand;
import frc.robot.commands.RunIntakeIndexCommand;
import frc.robot.subsystems.IndexSubsystem;
import frc.robot.subsystems.IntakeSubsystem;


public class RobotContainer {
    
    // DECLARACIÓN DE SUBSISTEMAS
    private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
    private final IndexSubsystem indexSubsystem = new IndexSubsystem();
    
    // CONTROLADOR
    private final CommandXboxController driverController = 
        new CommandXboxController(OIConstants.DRIVER_CONTROLLER_PORT);
    

    public RobotContainer() {
        configureDefaultCommands();
        configureBindings();
    }
    
    /**
     * Configura los comandos por defecto de cada subsistema
     * Estos comandos se ejecutan cuando ningún otro comando está usando el subsistema
     */
    private void configureDefaultCommands() {
        // El comando por defecto del intake es control manual con joystick
        // getLeftY() devuelve valores entre -1 y 1
        intakeSubsystem.setDefaultCommand(
            new ManualAngulatorCommand(
                intakeSubsystem,
                () -> -driverController.getLeftY()  // Negativo porque joystick está invertido
            )
        );
        
    }
    
    
    private void configureBindings() {
        
        // BOTÓN A: Girar todas las ruedas hacia adelante =====
        // whileTrue() = mientras el botón esté presionado
        driverController.button(OIConstants.BUTTON_WHEELS_FORWARD).whileTrue(
            new RunIntakeIndexCommand(
                intakeSubsystem,
                indexSubsystem,
                IntakeConstants.INTAKE_SPEED,   // Velocidad intake (positiva)
                IndexConstants.INDEX_SPEED      // Velocidad index (positiva)
            )
        );
        
        // BOTÓN B: Girar todas las ruedas hacia atrás (reversa) =====
        driverController.button(OIConstants.BUTTON_WHEELS_REVERSE).whileTrue(
            new RunIntakeIndexCommand(
                intakeSubsystem,
                indexSubsystem,
                -IntakeConstants.INTAKE_SPEED,  // Velocidad intake (negativa)
                -IndexConstants.INDEX_SPEED     // Velocidad index (negativa)
            )
        );
        
        // Mueve el angulador a la posición objetivo usando PID
        driverController.button(OIConstants.BUTTON_PID_CONTROL).whileTrue(
            new PIDAngulatorCommand(
                intakeSubsystem,
                IntakeConstants.TARGET_POSITION
            )
        );
        
        
    }
    

    public Command getAutonomousCommand() {
        return null;  // No hay autónomo en este proyecto
    }
}