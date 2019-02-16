package frc.robot.panelclaw;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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

    private Servo pegServo;
    private PegState pegState;

    private static Peg instance;

    private Peg(){
        pegServo = new Servo(RobotConstants.Ports.PEG_SERVO);
        pegServo.setBounds(2.2, 0, 1.5, 0, 0.8);
    }
    public static Peg getInstance(){
        if(instance == null){
            instance = new Peg();
        }
        return instance;
    }

    @Override
    protected void initDefaultCommand() {
        
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
}