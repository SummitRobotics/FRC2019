package frc.robot.cargoIntake;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotConstants;
import frc.robot.cargoIntake.Intake.IntakeState;

public class MoveIntake extends Command{
    private Intake intake = Intake.getInstance();
    private IntakeState position;
    private double error, target, direction;
    private final double THRESHOLD = 1;
    //TODO - Assign power via PID
    private final double POWER = 0.1;

    public MoveIntake(IntakeState position){
        requires(intake);
        position = this.position;
    }
    @Override
    protected void initialize() {
        double ticks = (position.value / 360) * RobotConstants.TALON_TICKS_PER_ROT;
        target = intake.getIntakeArmPosition() + ticks;
        error = target - intake.getIntakeArmPosition();
        direction = Math.copySign(1, error);
    }
    @Override
    protected void execute() {
        intake.setIntakeArm(position, POWER);
        error = target - intake.getIntakeArmPosition();
    }
    @Override
    protected boolean isFinished() {
        if(position == intake.getIntakeState()){
            return true;
        }
        if(direction == 1 && intake.getCargoLimit()){
            return true;
        }
        return (error < THRESHOLD) && (error > -THRESHOLD);
    }
    @Override
    protected void end() {
        super.end();
    }
}