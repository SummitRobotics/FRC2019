package frc.robot.cargointake.cargocommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.cargointake.Intake;

public class SpinIntake extends Command{
    private Intake intake = Intake.getInstance();
    //TODO - PID
    private double timeout, direction;
    private boolean flag = true;

    public SpinIntake(double timeout, double direction){
        requires(intake);
        this.timeout = timeout;
        setTimeout(timeout);
        direction = Math.copySign(1, direction);
    }
    public SpinIntake(double direction){
        requires(intake);
        flag = false;
        direction = Math.copySign(1, direction);
    }
    @Override
    protected void initialize() {
        super.initialize();
    }
    @Override
    protected void execute() {
        intake.intake(1 * (int)direction);
    }
    @Override
    protected boolean isFinished() {
        if(flag){
            return timeSinceInitialized() > timeout;
        }
        else{
            return flag;
        }
    }
    @Override
    protected void end() {
        super.end();
        intake.intake(0);
    }
}