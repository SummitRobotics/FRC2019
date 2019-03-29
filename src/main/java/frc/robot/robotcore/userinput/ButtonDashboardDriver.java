package frc.robot.robotcore.userinput;

import edu.wpi.first.wpilibj.GenericHID;
import frc.robot.robotcore.RobotConstants;

public class ButtonDashboardDriver extends GenericHID{
    public enum ButtonLayout{
        BOP(1),
        LOW(2),
        MID(3),
        HIGH(4),
        CARGO_GROUND(5),
        PANEL_GROUND(6),
        CLIMB(7),
        POS_3(8),
        POS_4(9),
        POS_1(10),
        POS_2(11),
        SIDE_AND_HEIGHT(12);

        public final int value;
        ButtonLayout(int value){
            this.value = value;
        }
    }
    
    public ButtonDashboardDriver(){
        super(RobotConstants.Ports.DASHBOARD_PORT);
    }


    @Override
    public double getY(Hand hand) {
        return 0;
    }
    @Override
    public double getX(Hand hand) {
        return 0;
    }

    public boolean getMastHigh(){
        return getRawButton(ButtonLayout.HIGH.value);
    }
    public boolean getMastMid(){
        return getRawButton(ButtonLayout.HIGH.value);
    }
    public boolean getMastLow(){
        return getRawButton(ButtonLayout.HIGH.value);
    }
    public boolean getSideAndHeight(){
        return getRawButton(ButtonLayout.SIDE_AND_HEIGHT.value);
    }
    public boolean getPos1(){
        return getRawButton(ButtonLayout.POS_1.value);
    }
    public boolean getPos2(){
        return getRawButton(ButtonLayout.POS_2.value);
    }
    public boolean getPos3(){
        return getRawButton(ButtonLayout.POS_3.value);
    }
    public boolean getPos4(){
        return getRawButton(ButtonLayout.POS_4.value);
    }
    public boolean getCargoGround(){
        return getRawButton(ButtonLayout.CARGO_GROUND.value);
    }
    public boolean getPanelGround(){
        return getRawButton(ButtonLayout.PANEL_GROUND.value);
    }
    public boolean getClimb(){
        return getRawButton(ButtonLayout.CLIMB.value);
    }
    public boolean getBop(){
        return getRawButton(ButtonLayout.BOP.value);
    }

}