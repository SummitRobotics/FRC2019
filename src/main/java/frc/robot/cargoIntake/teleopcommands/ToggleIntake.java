package frc.robot.cargointake.teleopcommands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.cargointake.Intake;

public class ToggleIntake extends InstantCommand{

    private Intake intake = Intake.getInstance();
    private Intake.IntakeSpinState intakeSpin;

    public ToggleIntake(){
        requires(intake);
    }

    @Override
    protected void initialize() {
    }
    @Override
    protected void execute() {
        this.intakeSpin = intake.toggleIntakeSpin();
        intake.setIntakeSpin(intakeSpin);
    }
    @Override
    protected void end() {
        super.end();
    }
}