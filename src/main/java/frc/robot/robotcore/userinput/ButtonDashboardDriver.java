package frc.robot.robotcore.userinput;

import edu.wpi.first.wpilibj.GenericHID;
import frc.robot.robotcore.RobotConstants;

public class ButtonDashboardDriver extends GenericHID{
    public enum ButtonLayout{
        HIGH_MAST(1),
        MID_MAST(2),
        LOW_MAST(3),
        CARGO_LOAD(4),
        CARGO_GROUND(5),
        PANEL_LOAD(6),
        PANEL_GROUND(7),
        CLIMB(8),
        CANCEL(9);

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
        return getRawButton(ButtonLayout.HIGH_MAST.value);
    }
    public boolean getMastMid(){
        return getRawButton(ButtonLayout.MID_MAST.value);
    }
    public boolean getMastLow(){
        return getRawButton(ButtonLayout.LOW_MAST.value);
    }
    
    public boolean getCargoLoad(){
        return getRawButton(ButtonLayout.CARGO_LOAD.value);
    }
    public boolean getCargoGround(){
        return getRawButton(ButtonLayout.CARGO_GROUND.value);
    }
    
    public boolean getPanelLoad(){
        return getRawButton(ButtonLayout.PANEL_LOAD.value);
    }
    public boolean getPanelGround(){
        return getRawButton(ButtonLayout.PANEL_GROUND.value);
    }

    public boolean getClimb(){
        return getRawButton(ButtonLayout.CLIMB.value);
    }
    public boolean getCancel(){
        return getRawButton(ButtonLayout.CANCEL.value);
    }

}