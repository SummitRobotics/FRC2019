package frc.robot.devices;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.robotcore.RobotConstants;

public class BlinkinRGB{

    public enum Blinkin{
        RAINBOW_PALETTE(-0.99),
        RAINBOW_PARTY(-0.97),
        RAINBOW_OCEAN(-0.95),
        LARSON_SCANNER_RED(-0.35),
        COLOR_1_SLOW(0.03),
        COLOR_1_MED(0.05),
        COLOR_1_FAST(0.07),
        COLOR_1_2(0.53),
        BLUE(0.87),
        BLUE_VIOLET(0.89),
        VIOLET(0.91);

        public final double value;

        Blinkin(double value){
            this.value = value;
        }
    }

    private Spark blinkin;
    private Blinkin RGBState;

    private static BlinkinRGB instance;

    private BlinkinRGB(){
        blinkin = new Spark(RobotConstants.Ports.BLINKIN_LED);
    }

    public void setLEDColor(Blinkin blinkinValue){
        blinkin.set(blinkinValue.value);
        SmartDashboard.putNumber("value", blinkinValue.value);
        RGBState = blinkinValue;
    }
}