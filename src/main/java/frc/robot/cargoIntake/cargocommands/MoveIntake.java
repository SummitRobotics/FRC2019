package frc.robot.cargointake.cargocommands;


import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.cargointake.Intake;
import frc.robot.robotcore.RobotConstants;

public class MoveIntake extends PIDCommand{
    private Intake intake = Intake.getInstance();
    private Intake.IntakeState intakePosition;
    //TODO - PID
    private final double THRESHOLD = 10;
    private static final double 
    P = 1,
    I = 0,
    D = 0;

    public MoveIntake(Intake.IntakeState intakePosition){
        super(P, I, D);
        requires(intake);
        this.intakePosition = intakePosition;
    }
    @Override
    protected void initialize() {
        super.initialize();
        double target = RobotConstants.TALON_TICKS_PER_ROT * (intakePosition.value / 360);
        setSetpoint(target);
    }
    @Override
    protected void usePIDOutput(double output) {
        intake.setIntakeArm(intakePosition, output);
    }
    @Override
    protected boolean isFinished() {
        return (intake.getCargoLimit() || getPosition) || ((getPosition() > -THRESHOLD) && (getPosition() < THRESHOLD));
    }
    @Override
    protected void end() {
        super.end();
    }
    @Override
    protected double returnPIDInput() {
        return intake.getIntakeArmPosition();
    }
}