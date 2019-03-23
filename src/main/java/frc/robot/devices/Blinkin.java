package frc.robot.devices;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.robotcore.RobotConstants;

public class Blinkin{

    public enum BlinkinState{

        //Solid colors
        LIME(0.73),
        RED(0.61),
        DARKRED(0.59),
        
        //Color 1 Options
        C1ENDTOENDBLENDTOBLACK(-0.03),

        //Color 2 Options
        C2ENDTOENDBLENDTOBLACK(0.17),
        HEARTBEATSLOW(0.23),
        HEARTBEATMEDIUM(0.25),
        HEARTBEATFAST(0.27),
        SHOT(0.33),
        STROBE(0.35);

        public final double value;

        BlinkinState(double value){
            this.value = value;
        }
    }

    private Spark blinkin;
    private BlinkinState currentState;

    private static Blinkin instance;

    private Blinkin(){
        blinkin = new Spark(RobotConstants.Ports.BLINKIN_LED);
        currentState = BlinkinState.values()[0];
    }

    public static Blinkin getInstance(){
        if(instance == null){
            instance = new Blinkin();
        }
        return instance;
    }

    public void setLEDColor(BlinkinState blinkenValue) {
        blinkin.set(blinkenValue.value);
        currentState = blinkenValue;
    }
}