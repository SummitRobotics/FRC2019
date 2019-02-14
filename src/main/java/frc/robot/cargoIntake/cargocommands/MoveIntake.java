package frc.robot.cargointake.cargocommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.cargointake.Intake;

public class MoveIntake extends Command{
    private Intake intake = Intake.getInstance();
    private Intake.IntakeState intakePosition;
    //TODO - PID
    private final double POWER = 0.5;
    private double target, error; 

    public MoveIntake(Intake.IntakeState intakePosition){
        requires(intake);
        this.intakePosition = intakePosition;
    }
    @Override
    protected void initialize() {
        super.initialize();
    }
    @Override
    protected void execute() {
        intake.setIntakeArm(intakePosition, POWER);
    }
    @Override
    protected boolean isFinished() {
        return false;
    }
    @Override
    protected void end() {
        super.end();
    }
}