package frc.robot.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.drivetrain.Drivetrain;
import frc.robot.drivetrain.drivetraincommands.EncoderDrive;
import frc.robot.drivetrain.drivetraincommands.GyroTurn;
import frc.robot.drivetrain.drivetraincommands.vision.TargetAlignment;
import frc.robot.mast.Mast;
import frc.robot.mast.mastcommands.MoveMast;
import frc.robot.panelclaw.Claw;
import frc.robot.panelclaw.Peg;
import frc.robot.panelclaw.chairautomation.EjectPanel;
import frc.robot.panelclaw.pegcommands.ActuatePeg;
import frc.robot.robotcore.universalcommands.Wait;

public class RocketRight extends CommandGroup{

    public RocketRight(){
        setInterruptible(true);
        requires(Drivetrain.getInstance());
        requires(Claw.getInstance());
        requires(Peg.getInstance());

        addSequential(new EncoderDrive(100));
        addSequential(new GyroTurn(30));
        addSequential(new EncoderDrive(100));
        addSequential(new GyroTurn(90));
        addSequential(new TargetAlignment());
        addSequential(new MoveMast(Mast.MastState.HIGH));
        addSequential(new EjectPanel());
        addSequential(new EncoderDrive(-30));
        addSequential(new GyroTurn(30));
        addSequential(new EncoderDrive(150));
        addSequential(new GyroTurn(-15));
        addSequential(new TargetAlignment());
        addSequential(new Wait(0.5));
        addSequential(new EncoderDrive(2));
        addSequential(new ActuatePeg().new SetPeg(Peg.PegState.UP));
        addSequential(new EncoderDrive(-150));
        addSequential(new GyroTurn(-30));
        addSequential(new EncoderDrive(-50));
        addSequential(new GyroTurn(30));
        addSequential(new EncoderDrive(-30));
        addSequential(new GyroTurn(-30));
        addSequential(new TargetAlignment());
        addSequential(new MoveMast(Mast.MastState.MID));
        addSequential(new EjectPanel());
    }

    @Override
    protected void interrupted() {
        super.interrupted();
        Drivetrain.getInstance().kill();
        Claw.getInstance().kill();
    }
}