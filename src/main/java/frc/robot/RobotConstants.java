package frc.robot;

public class RobotConstants {
  public static final double WHEEL_DIAMETER = 4.0;
  public static final double TALON_TICKS_PER_ROT = 4096;

  public static double TALON_INCHES_TO_TICKS(double inch){
    return (inch / (WHEEL_DIAMETER * Math.PI) * TALON_TICKS_PER_ROT);
  }
  public static double TALON_TICKS_TO_INCHES(double tick){
    return (tick / TALON_TICKS_PER_ROT * (WHEEL_DIAMETER * Math.PI));
  }
  public class Ports{

    public static final int CONTROLLER_PORT = 0;

    public static final int
    LEFT_DRIVE_MAIN = 32,
    LEFT_DRIVE_1 = 31,
    LEFT_DRIVE_2 = 30,
    RIGHT_DRIVE_MAIN = 22,
    RIGHT_DRIVE_1 = 21,
    RIGHT_DRIVE_2 = 20,

    DRIVE_SOLENOID_OPEN = 0,
    DRIVE_SOLENOID_CLOSE = 1,
    PTO_SOLENOID_OPEN = 2,
    PTO_SOLENOID_CLOSE = 3, 
    CLAW_SOLENOID_OPEN = 4, 
    CLAW_SOLENOID_CLOSE = 5,
    PANEL_SOLENOID_OPEN = 6,
    PANEL_SOLENOID_CLOSE = 7,

    CLAW_ARM = 0,
    INTAKE_ARM = 0,
    INTAKE_ROLLER = 0,

    PEG_SERVO = 0;
  }
}
