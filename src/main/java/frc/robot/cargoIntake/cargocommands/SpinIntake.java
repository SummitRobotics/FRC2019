package frc.robot.cargointake.cargocommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.cargointake.Intake;
import frc.robot.cargointake.Intake.IntakeSpinState;

public class SpinIntake extends Command{
    private Intake intake = Intake.getInstance();
    private IntakeSpinState intakeSpin;
    private boolean timeoutFlag = false;

    public SpinIntake(IntakeSpinState intakeSpin, double timeout){
        requires(intake);
        setTimeout(timeout);
        this.timeoutFlag = true;
        this.intakeSpin = intakeSpin;
    }
    public SpinIntake(IntakeSpinState intakeSpin){
        requires(intake);
        this.intakeSpin = intakeSpin;
    }
    @Override
    protected void initialize() {
        super.initialize();
    }
    @Override
    protected void execute() {
        intake.setIntakeSpin(intakeSpin);
    }
    @Override
    protected boolean isFinished() {
        if(timeoutFlag){
            return isTimedOut();
        }
        else{
            return false;
        }
    }
    @Override
    protected void end() {
        super.end();
    }
}