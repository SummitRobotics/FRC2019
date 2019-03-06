package frc.robot.robotcore;

public class RobotConstants {
  public static final double WHEEL_DIAMETER = 4.0;
  public static final double TALON_TICKS_PER_ROT = 4096;
  public static final double NEO_TICKS_PER_REV = 42;

  public static final double MAX_DRIVETRAIN_RPM = 5700;
  
  //TODO - new angles and heights
  public static final double CAMERA_HEIGHT = 21.5;
  public static final double CAMERA_ANGLE = 21.86;

  public static double TALON_INCHES_TO_TICKS(double inch){
    return (inch / (WHEEL_DIAMETER * Math.PI) * TALON_TICKS_PER_ROT);
  }
  public static double TALON_TICKS_TO_INCHES(double tick){
    return (tick / TALON_TICKS_PER_ROT * (WHEEL_DIAMETER * Math.PI));
  }

  public static double NEO_INCHES_TO_TICKS(double inch){
    return (inch / (WHEEL_DIAMETER * Math.PI) * NEO_TICKS_PER_REV);
  }
  public static double NEO_TICKS_TO_INCHES(double tick){
    return (tick / NEO_TICKS_PER_REV * (WHEEL_DIAMETER * Math.PI));
  }
  
  public class Ports{

    public static final int 
    DRIVER_1_PORT = 0,
    DRIVER_2_PORT = 0,
    DASHBOARD_PORT = 1;

    public static final int
    LEFT_DRIVE_0 = 20,
    LEFT_DRIVE_MAIN = 21,
    LEFT_DRIVE_1 = 22,
    RIGHT_DRIVE_0 = 30,
    RIGHT_DRIVE_MAIN = 31,
    RIGHT_DRIVE_1 = 32,

    MAST_DRIVER = 60,
    //MAST_FOLLOWER = 0,

    LOW_LIMIT_SWITCH = 5,
    //HIGH_LIMIT_SWITCH = 99,

    CARGO_LIMIT_SWITCH = 2,
    CLAW_LIMIT_SWITCH = 6,

    CARGO_BREAK_1 = 1,
    CARGO_BREAK_2 = 0,

    DRIVE_SOLENOID_OPEN = 1,
    DRIVE_SOLENOID_CLOSE = 0,
    PTO_SOLENOID_OPEN = 3,
    PTO_SOLENOID_CLOSE = 2, 
    CLAW_SOLENOID_OPEN = 7, 
    CLAW_SOLENOID_CLOSE = 6,
    PANEL_SOLENOID_OPEN = 4,
    PANEL_SOLENOID_CLOSE = 5,

    CLAW_MOVEMENT = 50,
    INTAKE_MOVEMENT = 40,
    INTAKE_ROLLER = 41,
    UNUSED = 61,

    PEG_SERVO = 1,

    BLINKIN_LED = 0,

    GYRO = 10,

    PRESSURE_READER = 0;
  }
  public class Cargo_PID{
    public static final double
    ARM_P = 1,
    ARM_I = 0,
    ARM_D = 0,
    ARM_F = 0,
    ARM_NOMINAL_FORWARD = 0,
    ARM_NOMINAL_REVERSE = 0,
    ARM_PEAK_FORWARD = 0.40,
    ARM_PEAK_REVERSE = 0.40,
    ARM_CONST_CURRENT = 30,
    ARM_PEAK_CURRENT = 30,
    //in ticks
    CLOSED_LOOP_ERROR = 25;
    public static final boolean isInverted = false;
    public static final boolean isPhaseInverted = false;
  }
  public class Claw_PID{
    public static final double
    ARM_P = 1,
    ARM_I = 0,
    ARM_D = 0,
    ARM_F = 0,
    ARM_NOMINAL_FORWARD = 0,
    ARM_NOMINAL_REVERSE = 0,
    ARM_PEAK_FORWARD = 0.35,
    ARM_PEAK_REVERSE = 0.35,
    ARM_CONST_CURRENT = 30,
    ARM_PEAK_CURRENT = 30,
    CLOSED_LOOP_ERROR = 25;
    
    public static final boolean 
    isInverted = false,
    isPhaseInverted = false;
  }
}
