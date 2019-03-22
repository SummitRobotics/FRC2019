package frc.robot.panelclaw.chairautomation;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.drivetrain.drivetraincommands.PowerDrive;
import frc.robot.panelclaw.pegcommands.ActuateChair;
import frc.robot.panelclaw.pegcommands.ActuatePeg;
import frc.robot.robotcore.universalcommands.Wait;

public class PinPanel extends CommandGroup{
    public PinPanel(){
        setInterruptible(true);
        addSequential(new ActuatePeg().new TogglePeg());
        addSequential(new PowerDrive(-0.3, false, 0.5));
        addSequential(new Wait(0.5));
        addSequential(new ActuateChair().new ToggleChair());
    }
}