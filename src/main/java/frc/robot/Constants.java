package frc.robot;

public final class Constants {
    
    // IDs de los motores en el CAN Bus
    public static final class IntakeConstants {
        public static final int ANGULATOR_MOTOR_ID = 1;  // ID del Neo del angulador
        public static final int INTAKE_WHEELS_MOTOR_ID = 2;  // ID del Kraken
        
        // Límites de velocidad
        public static final double MANUAL_SPEED_LIMIT = 0.3;
        
        // Velocidades de las ruedas
        public static final double INTAKE_SPEED = 0.7;
        
        // Configuración del PID ( calibrar estos valores)
        public static final double kP = 0.1;
        public static final double kI = 0.0;
        public static final double kD = 0.0;
        
        // Posición objetivo para el PID (en rotaciones del encoder) FALTA
        public static final double TARGET_POSITION = 10.0;
        
        // Tolerancia para considerar que llegaste a la posición
        public static final double POSITION_TOLERANCE = 0.5;
    }
    
    public static final class IndexConstants {
        public static final int LEFT_MOTOR_ID = 3;   // ID del Neo izquierdo
        public static final int RIGHT_MOTOR_ID = 4;  // ID del Neo derecho
        
        public static final double INDEX_SPEED = 0.6;
    }
    
    public static final class OIConstants {
        public static final int DRIVER_CONTROLLER_PORT = 0;
        public static final int BUTTON_WHEELS_FORWARD = 1; //Boton A
        public static final int BUTTON_WHEELS_REVERSE = 2; //Boton B
        public static final int BUTTON_PID_CONTROL = 3; //Boton X
        public static final int JOYSTICK_AXIS = 1;  // Eje Y del joystick izquierdo
    }
}