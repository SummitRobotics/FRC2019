package frc.robot.cargointake;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class CargoArmConfig{

    public static final double
    P = 1,
    I = 0,
    D = 0.01,
    FEEDFWD = 0,
    OUTPUT_NOMINAL_FORWARD = 0,
    OUTPUT_NOMINAL_REVERSE = 0,
    OUTPUT_PEAK_FORWARD = 0.80,
    OUTPUT_PEAK_REVERSE = -0.70,
    CONST_CURRENT = 30,
    PEAK_CURRENT = 30,
    //in ticks
    CLOSED_LOOP_ERROR = 25;
    public static final boolean isInverted = false;
    public static final boolean isPhaseInverted = true;


    public CargoArmConfig(){

    }

    public static void configTalon(TalonSRX talon){
        talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        configPIDF(talon);
        configOutputs(talon);
        //configCurrentLimits(talon);
        configInverts(talon);
        configClosedLoop(talon);

    }

    private static void configPIDF(TalonSRX talon){
        talon.config_kP(0, P);
        talon.config_kI(0, I);
        talon.config_kD(0, D);
        talon.config_kF(0, FEEDFWD);
    }

    private static void configOutputs(TalonSRX talon){
        talon.configNominalOutputForward(OUTPUT_NOMINAL_FORWARD);
        talon.configNominalOutputReverse(OUTPUT_NOMINAL_REVERSE);
        talon.configPeakOutputForward(OUTPUT_PEAK_FORWARD);
        talon.configPeakOutputReverse(OUTPUT_PEAK_REVERSE);
    }

    private static void configInverts(TalonSRX talon){
        talon.setSensorPhase(isPhaseInverted);
        talon.setInverted(isInverted);
    }

    /*private static void configCurrentLimits(TalonSRX talon){
        talon.configPeakCurrentLimit((int)PEAK_CURRENT);
        talon.configContinuousCurrentLimit((int)CONST_CURRENT);
    }*/

    private static void configClosedLoop(TalonSRX talon){
        talon.configAllowableClosedloopError(0, (int)CLOSED_LOOP_ERROR);
    }

    /*TODO - Motion Profiling! Use Motion Magic.
    private void configMotionProfile(){
        
    }*/
}