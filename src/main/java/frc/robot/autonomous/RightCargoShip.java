package frc.robot.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.drivetrain.Drivetrain;
import frc.robot.drivetrain.drivetraincommands.EncoderDrive;
import frc.robot.drivetrain.drivetraincommands.GyroTurn;
import frc.robot.drivetrain.drivetraincommands.vision.TargetAlignment;
import frc.robot.panelclaw.Claw;
import frc.robot.panelclaw.Peg;
import frc.robot.panelclaw.chairautomation.EjectPanel;
import frc.robot.robotcore.universalcommands.Wait;

public class RightCargoShip extends CommandGroup{

    Drivetrain drivetrain = Drivetrain.getInstance();
    Claw claw = Claw.getInstance();

    public RightCargoShip(){
        setInterruptible(true);
        requires(drivetrain);
        requires(claw);
        requires(Peg.getInstance());

        addSequential(new EncoderDrive(100));
        addSequential(new GyroTurn(30));
        addSequential(new EncoderDrive(50));
        addSequential(new GyroTurn(-120));
        addSequential(new TargetAlignment());
        addSequential(new EjectPanel());
        addSequential(new EncoderDrive(24));
        addSequential(new GyroTurn(-90));
        addSequential(new EncoderDrive(40));
        addSequential(new GyroTurn(-30));
        addSequential(new EncoderDrive(36));
        addSequential(new GyroTurn(30));
        addSequential(new TargetAlignment());
        addSequential(new Wait(0.5));
        addSequential(new EncoderDrive(2));
        addSequential(new EncoderDrive(-150));
        addSequential(new GyroTurn(-30));
        addSequential(new EncoderDrive(-50));
        addSequential(new GyroTurn(30));
        addSequential(new EncoderDrive(-30));
        addSequential(new GyroTurn(90));
        addSequential(new TargetAlignment());
        addSequential(new EjectPanel());
    }

    @Override
    protected void interrupted() {
        super.interrupted();
        drivetrain.kill();
        claw.kill();
    }
}