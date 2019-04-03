package frc.robot.panelclaw.chairautomation;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.drivetrain.Drivetrain;
import frc.robot.drivetrain.drivetraincommands.PowerDrive;
import frc.robot.panelclaw.Claw;
import frc.robot.panelclaw.Peg;
import frc.robot.panelclaw.pegcommands.ActuateChair;
import frc.robot.panelclaw.pegcommands.ActuatePeg;
import frc.robot.robotcore.universalcommands.Wait;

public class PinPanel extends CommandGroup{

    private Drivetrain drivetrain = Drivetrain.getInstance();
    private Claw claw = Claw.getInstance();

    public PinPanel(){
        setInterruptible(true);
        requires(drivetrain);
        requires(claw);
        requires(Peg.getInstance());

        addSequential(new ActuatePeg().new TogglePeg());
        addSequential(new PowerDrive(-0.3, 0.5));
        addSequential(new Wait(0.5));
        addSequential(new ActuateChair().new ToggleChair());
    }

    @Override
    protected void interrupted() {
        super.interrupted();
        drivetrain.kill();
        claw.kill();
    }
}