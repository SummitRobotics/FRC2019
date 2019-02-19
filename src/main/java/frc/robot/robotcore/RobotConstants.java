package frc.robot.robotcore;

public class RobotConstants {
  public static final double WHEEL_DIAMETER = 4.0;
  public static final double TALON_TICKS_PER_ROT = 4096;
  public static final double NEO_TICKS_PER_REV = 42;

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

    public static final int CONTROLLER_PORT = 0;
    public static final int DASHBOARD_PORT = 1;

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

    CARGO_LIMIT_SWITCH = 1,
    CLAW_LIMIT_SWITCH = 6,

    CARGO_BREAK_1 = 0,
    CARGO_BREAK_2 = 2,

    DRIVE_SOLENOID_OPEN = /*1*/ 6,
    DRIVE_SOLENOID_CLOSE = /*0*/ 7,
    PTO_SOLENOID_OPEN = 2,
    PTO_SOLENOID_CLOSE = 3, 
    CLAW_SOLENOID_OPEN = /*7*/ 1, 
    CLAW_SOLENOID_CLOSE = /*6*/ 0,
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
}
