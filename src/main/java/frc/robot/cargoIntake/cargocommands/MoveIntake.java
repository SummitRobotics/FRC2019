package frc.robot.cargointake.cargocommands;


import edu.wpi.first.wpilibj.command.Command;
import frc.robot.cargointake.Intake;
import frc.robot.robotcore.RobotConstants;

public class MoveIntake extends Command{
    private Intake intake = Intake.getInstance();
    private Intake.IntakeState intakePosition;
    //TODO - PID
    private final double THRESHOLD = 10;

    public MoveIntake(Intake.IntakeState intakePosition){
        requires(intake);
        this.intakePosition = intakePosition;
    }
    @Override
    protected void initialize() {
        super.initialize();
        double target = RobotConstants.TALON_TICKS_PER_ROT * (intakePosition.value / 360);
    }
    @Override
    protected void execute() {
        super.execute();
    }
    @Override
    protected boolean isFinished() {
        return (intake.getCargoLimit() || getPosition) || ((getPosition() > -THRESHOLD) && (getPosition() < THRESHOLD));
    }
    @Override
    protected void end() {
        super.end();
    }
}