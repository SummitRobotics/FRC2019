package frc.robot.drivetrain;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANPIDController.AccelStrategy;

public class DrivetrainConfig{

    private static double
        P = 1,
        I = 0,
        D = 0,
        FEED_FWD = 0,
        I_ZONE = 0,
        OUTPUT_MIN = -1,
        OUTPUT_MAX = 1,
        MIN_VELOCITY = 0,
        MAX_VELOCITY = 1,
        MAX_ACCEL = 1,
        CLOSED_LOOP_ERROR =0,
        STALL_CURRENT_LIMIT = 40,
        FREESPIN_CURRENT_LIMIT = 35;

    public DrivetrainConfig(){

    }

    public static void configMotorController(CANSparkMax controller){
        setPIDF(controller);
        setOutputs(controller);
        //configMotionProfile(controller);
        configCurrentLimits(controller);
    }

    public static void setPIDF(CANSparkMax controller){
        controller.getPIDController().setP(P);
        controller.getPIDController().setI(I);
        controller.getPIDController().setD(D);
        controller.getPIDController().setFF(FEED_FWD);
        controller.getPIDController().setIZone(I_ZONE);
    }
    
    public static void setOutputs(CANSparkMax controller){
        controller.getPIDController().setOutputRange(OUTPUT_MIN, OUTPUT_MAX);
    }

    /*public static void configMotionProfile(CANSparkMax controller){
        controller.getPIDController().setSmartMotionMinOutputVelocity(MIN_VELOCITY, 0);
        controller.getPIDController().setSmartMotionMaxVelocity(MAX_VELOCITY, 0);
        controller.getPIDController().setSmartMotionAllowedClosedLoopError(CLOSED_LOOP_ERROR, 0);
        controller.getPIDController().setSmartMotionMaxAccel(MAX_ACCEL, 0);
        controller.getPIDController().setSmartMotionAccelStrategy(AccelStrategy.kSCurve, 0);
    }*/

    public static void configCurrentLimits(CANSparkMax controller){
        controller.setSmartCurrentLimit((int)STALL_CURRENT_LIMIT, (int)FREESPIN_CURRENT_LIMIT);
    }
}