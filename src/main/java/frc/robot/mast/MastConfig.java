package frc.robot.mast;

import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;

public class MastConfig{

    private static double
        P = 0.2,
        I = 0,
        D = 0.01,
        FEED_FWD = 0,
        I_ZONE = 0,
        OUTPUT_MIN = -0.55,
        OUTPUT_MAX = 0.18,
        MIN_VELOCITY = 0,
        MAX_VELOCITY = 1,
        MAX_ACCEL = 1,
        CLOSED_LOOP_ERROR =0,
        STALL_CURRENT_LIMIT = 40,
        RAMP_RATE_OPEN = 0.2,
        FREESPIN_CURRENT_LIMIT = 35;

    public MastConfig(){

    }

    public static void configMotorController(CANPIDController controller){
        setPIDF(controller);
        setOutputs(controller);
        //configMotionProfile(controller);
        //configCurrentLimits(controller);
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
    public static void configOpenLoopRampRates(CANSparkMax controller){
        controller.setOpenLoopRampRate(RAMP_RATE_OPEN);
    }
}