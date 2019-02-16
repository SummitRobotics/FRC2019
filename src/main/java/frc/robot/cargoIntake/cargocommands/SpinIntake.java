package frc.robot.cargointake.cargocommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.cargointake.Intake;

public class SpinIntake extends Command{
    private Intake intake = Intake.getInstance();
    //TODO - PID
    private final double POWER = 0.5;
    private double timeout;

    public SpinIntake(double timeout){
        requires(intake);
        this.timeout = timeout;
        setTimeout(timeout);
    }
    @Override
    protected void initialize() {
        super.initialize();
    }
    @Override
    protected void execute() {
        intake.intake(1);
    }
    @Override
    protected boolean isFinished() {
        return timeSinceInitialized() > timeout;
    }
    @Override
    protected void end() {
        super.end();
    }
}