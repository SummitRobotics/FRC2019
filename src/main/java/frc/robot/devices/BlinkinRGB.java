package frc.robot.devices;

import edu.wpi.first.wpilibj.Spark;
import frc.robot.drivetrain.Drivetrain;
import frc.robot.robotcore.RobotConstants;

public class BlinkinRGB{

    public enum BlinkinState{

        //Presets
        RAINBOW(-0.99),
        TWINKLES(-0.47),
        LAVA(-.31),
        FOREST(.53),

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

    public BlinkinState stateOne;
    public BlinkinState stateTwo;

    private static BlinkinRGB instance;

    private BlinkinRGB(){
        blinkin = new Spark(RobotConstants.Ports.BLINKIN_LED);

        stateOne = BlinkinState.FOREST;
        stateTwo = BlinkinState.LAVA;
    }

    public static BlinkinRGB getInstance(){
        if(instance == null){
            instance = new BlinkinRGB();
        }
        return instance;
    }

    public void setLEDState(BlinkinState blinkenValue) {
        blinkin.set(blinkenValue.value);
    }

    public void shiftSetLEDState(Drivetrain.GearState shiftState) {
        if (shiftState == Drivetrain.GearState.LOW) {
            blinkin.set(stateTwo.value);
        } else {
            blinkin.set(stateOne.value);
        }
    }
}