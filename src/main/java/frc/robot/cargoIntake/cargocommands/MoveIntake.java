package frc.robot.cargointake.cargocommands;


import edu.wpi.first.wpilibj.command.Command;
import frc.robot.cargointake.Intake;
import frc.robot.robotcore.RobotConstants;

public class MoveIntake extends Command{
    private Intake intake = Intake.getInstance();
    private Intake.IntakeState intakePosition;
    //TODO - PID
    boolean isDone;

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
        isDone = intake.setIntakeArm(intakePosition);
    }
    @Override
    protected boolean isFinished() {
        return isDone;
    }
    @Override
    protected void end() {
        super.end();
    }
}