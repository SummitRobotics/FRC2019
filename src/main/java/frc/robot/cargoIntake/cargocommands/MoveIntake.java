package frc.robot.cargointake.cargocommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.cargointake.Intake;
import frc.robot.robotcore.RobotConstants;

public class MoveIntake extends Command{
    private Intake intake = Intake.getInstance();
    private Intake.IntakeState intakePosition;
    //TODO - PID
    private final double POWER = 0.5;
    private final double THRESHOLD = 10;
    private double target, error, direction;

    public MoveIntake(Intake.IntakeState intakePosition){
        requires(intake);
        this.intakePosition = intakePosition;
    }
    @Override
    protected void initialize() {
        double target = RobotConstants.TALON_TICKS_PER_ROT * (intakePosition.value / 360);
        error = target - intake.getIntakeArmEncoder();
        direction = Math.copySign(1, error);
    }
    @Override
    protected void execute() {
        intake.setIntakeArm(intakePosition, POWER * direction);
        error = target - intake.getIntakeArmEncoder();
        direction = Math.copySign(1, error);
    }
    @Override
    protected boolean isFinished() {
        return (intake.getCargoLimit() && direction == -1) || ((error > -THRESHOLD) && (error < THRESHOLD));
    }
    @Override
    protected void end() {
        super.end();
    }
}