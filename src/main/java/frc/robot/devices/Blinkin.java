package frc.robot.devices;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.Spark;
import frc.robot.robotcore.RobotConstants;

public class Blinkin{

    public enum BlinkinColors{

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

        BlinkinColors(double value){
            this.value = value;
        }
    }

    private Spark blinkin;
    private BlinkinColors CurrentColor;

    private static Blinkin instance;

    private Blinkin(){
        blinkin = new Spark(RobotConstants.Ports.BLINKIN_LED);
    }

    public Blinkin getInstance(){
        if(instance == null){
            instance = new Blinkin();
        }
        return instance;
    }
    

    public void setLEDColor(BlinkinColors blinkinValue){
        blinkin.set(blinkinValue.value);
        CurrentColor = blinkinValue;
    }

    //For testing only - remove after done

    
}