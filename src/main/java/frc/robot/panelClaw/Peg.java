package frc.robot.panelclaw;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
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

    public enum PneumaticState{
        OUT(Value.kForward),
        IN(Value.kReverse);

        public final Value value;
        PneumaticState(Value value){
            this.value = value;
        }
    }

    private Servo pegServo;
    private PegState pegState;

    private DoubleSolenoid chairOuttake;
    private PneumaticState chairState;

    private DoubleSolenoid bopCargo;
    private PneumaticState bopState;

    private static Peg instance;

    private Peg(){
        pegServo = new Servo(RobotConstants.Ports.PEG_SERVO);
        pegServo.setBounds(2.2, 0, 1.5, 0, 0.8);

        chairOuttake = new DoubleSolenoid(RobotConstants.Ports.PANEL_SOLENOID_OPEN, RobotConstants.Ports.PANEL_SOLENOID_CLOSE);
        bopCargo = new DoubleSolenoid(RobotConstants.Ports.PTO_SOLENOID_CLOSE, RobotConstants.Ports.PTO_SOLENOID_OPEN);
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

    /* --- METHODS FOR PEG SERVO --- */
    public void setPeg(PegState pegPosition){
        pegServo.set(pegPosition.value);
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

    public boolean isPegUp(){
        if(pegState == PegState.DOWN){
            return false;
        }
        else if(pegState == PegState.UP){
            return true;
        }
        else{
            return false;
        }
    }

    public PegState togglePeg(){
        PegState pegPos = pegState;
            if(isPegUp()){
                pegPos = PegState.DOWN;
                return pegPos;
            }
            if(!isPegUp()){
                pegPos = PegState.UP;
                return pegPos;
            }
        return pegPos;
    }


    /* ----- METHODS FOR CHAIR (CARGO SHOOTER) ----- */
    public void setChair(PneumaticState chairPos){
        if(chairPos != chairState){
            chairOuttake.set(chairPos.value);
        }
        chairState = chairPos;
    }

    public boolean isChairOut(){
        if(chairState == PneumaticState.IN){
            return false;
        }
        else if(chairState == PneumaticState.OUT){
            return true;
        }
        else{
            return false;
        }
    }
    public PneumaticState toggleChair(){
        PneumaticState chairPos = chairState;
        if(isChairOut()){
            chairPos = PneumaticState.IN;
            return chairPos;
        }
        if(!isChairOut()){
            chairPos = PneumaticState.OUT;
            return chairPos;
        }
        return chairPos;
    }

    /* ----- BOP COMMANDS ----- */
    public void setBop(PneumaticState bopPos){
        bopCargo.set(bopPos.value);
        bopState = bopPos;
    }

    public boolean isBopOut(){
        if(bopState == PneumaticState.IN){
            return false;
        }
        else if(bopState == PneumaticState.OUT){
            return true;
        }
        else{
            return false;
        }
    }
    public PneumaticState toggleBop(){
        PneumaticState bopPos = bopState;
        if(isBopOut()){
            bopPos = PneumaticState.IN;
            return bopPos;
        }
        if(!isBopOut()){
            bopPos = PneumaticState.OUT;
            return bopPos;
        }
        return bopPos;
    }

}