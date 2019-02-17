package frc.robot.panelclaw;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.panelclaw.pegcommands.ActuatePeg;
import frc.robot.robotcore.RobotConstants;

public class Peg extends Subsystem{
    public enum PegState{
        UP(1),
        DOWN(0);

        public final double value;
        PegState(double value){
            this.value = value;
        }
    }

    public enum PanelOuttakeState{
        OUT(Value.kForward),
        IN(Value.kReverse);

        public final Value value;
        PanelOuttakeState(Value value){
            this.value = value;
        }
    }

    private Servo pegServo;
    private PegState pegState;

    private DoubleSolenoid panelOuttake;
    private PanelOuttakeState panelState;

    private static Peg instance;

    private Peg(){
        pegServo = new Servo(RobotConstants.Ports.PEG_SERVO);
        pegServo.setBounds(2.2, 0, 1.5, 0, 0.8);

        panelOuttake = new DoubleSolenoid(RobotConstants.Ports.PANEL_SOLENOID_OPEN, RobotConstants.Ports.PANEL_SOLENOID_CLOSE);
    }
    public static Peg getInstance(){
        if(instance == null){
            instance = new Peg();
        }
        return instance;
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new ActuatePeg(PegState.DOWN));
    }

    public void setPeg(PegState pegPosition){
        if(pegPosition != pegState){
            pegServo.set(pegPosition.value);
        }
        pegState = pegPosition;
    }

    public PegState getPegState(){
        if(pegState != null){
            return pegState;
        }
        else{
            SmartDashboard.putString("Error", "Peg State currently at null");
            setPeg(PegState.DOWN);
            return pegState;
        }
    }

    public PegState togglePeg(){
        PegState pegPos;
        if(pegState != null){
            if(pegState == PegState.UP){
                pegPos = PegState.DOWN;
                return pegPos;
            }
            else if(pegState == PegState.DOWN){
                pegPos = PegState.UP;
                return pegPos;
            }
        }
        pegPos = pegState.DOWN;
        return pegPos;
    }

    public void setPanel(PanelOuttakeState panelValue){
        if(panelValue != panelState){
            panelOuttake.set(panelValue.value);
        }
        panelState = panelValue;
    }

}