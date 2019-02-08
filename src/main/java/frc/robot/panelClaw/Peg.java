package frc.robot.panelClaw;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotConstants;

public class Peg extends Subsystem{
    private Servo pegServo;
    private boolean isPegUp;

    private static Peg instance;

    private Peg(){
        pegServo = new Servo(RobotConstants.Ports.PEG_SERVO);
        //TODO - pegServo.setBounds(max, deadbandMax, center, deadbandMin, min);

        isPegUp = false;
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

    public void raisePeg(){
        if(!isPegUp){
            pegServo.set(1);
        }
        isPegUp = true;
    }
    public void lowerPeg(){
        if(isPegUp){
            pegServo.set(0);
        }
        isPegUp = false;
    }

    public boolean getPegState(){
        return isPegUp;
    }
}