package frc.robot.panelClaw;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.CommandInterface;

public class PanelIntake extends Command implements CommandInterface{

    private boolean flag = false;

    public PanelIntake(){
        requires(subsystems.claw);

    }
    @Override
    protected void initialize() {
        super.initialize();
    }
    @Override
    protected void execute() {
        if(!subsystems.claw.isPanelPresent()){
            subsystems.claw.lowerClaw();
            subsystems.claw.openClaw();
        }
        if(subsystems.claw.isPanelPresent()){
            subsystems.claw.closeClaw();
            subsystems.claw.raiseClaw();
            subsystems.peg.raisePeg();
        }
        flag = true;
    }
    @Override
    protected boolean isFinished() {
        return flag;
    }
    @Override
    protected void end() {

    }
}