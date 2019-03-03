package frc.robot.drivetrain;

import com.revrobotics.CANPIDController;
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
        CLOSED_LOOP_ERROR =0;

    public DrivetrainConfig(){

    }

    public static void configMotorController(CANPIDController controller){
        setPIDF(controller);
        setOutputs(controller);
        configMotionProfile(controller);
    }

    public static void setPIDF(CANPIDController controller){
        controller.setP(P);
        controller.setI(I);
        controller.setD(D);
        controller.setFF(FEED_FWD);
        controller.setIZone(I_ZONE);
    }
    
    public static void setOutputs(CANPIDController controller){
        controller.setOutputRange(OUTPUT_MIN, OUTPUT_MAX);
    }

    public static void configMotionProfile(CANPIDController controller){
        controller.setSmartMotionMinOutputVelocity(MIN_VELOCITY, 0);
        controller.setSmartMotionMaxVelocity(MAX_VELOCITY, 0);
        controller.setSmartMotionAllowedClosedLoopError(CLOSED_LOOP_ERROR, 0);
        controller.setSmartMotionMaxAccel(MAX_ACCEL, 0);
        controller.setSmartMotionAccelStrategy(AccelStrategy.kSCurve, 0);
    }
}