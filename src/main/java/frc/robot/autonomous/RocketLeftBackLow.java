package frc.robot.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.chair.Chair;
import frc.robot.chair.chairautomation.EjectPanel;
import frc.robot.chair.chaircommands.ActuatePeg;
import frc.robot.climber.climbautomation.Level2Descend;
import frc.robot.drivetrain.Drivetrain;
import frc.robot.drivetrain.drivetraincommands.EncoderDrive;
import frc.robot.drivetrain.drivetraincommands.GyroTurn;
import frc.robot.drivetrain.drivetraincommands.vision.TargetAlignment;
import frc.robot.mast.Mast;
import frc.robot.mast.mastcommands.MoveMast;
import frc.robot.panelclaw.Claw;
import frc.robot.robotcore.universalcommands.Wait;

public class RocketLeftBackLow extends CommandGroup{


    public RocketLeftBackLow(){
        setInterruptible(true);
        requires(Drivetrain.getInstance());
        requires(Claw.getInstance());
        requires(Chair.getInstance());

        //addSequential(new Level2Descend());
        addSequential(new EncoderDrive(12));

        addSequential(new GyroTurn(-30));
        addSequential(new EncoderDrive(36));
        addSequential(new GyroTurn(-120));
        addSequential(new TargetAlignment());
        addSequential(new EjectPanel());

        addSequential(new EncoderDrive(-4));
        addSequential(new GyroTurn(-30));
        addSequential(new EncoderDrive(10));
        addSequential(new GyroTurn(30));
        addSequential(new EncoderDrive(8));
        addSequential(new GyroTurn(-30));
        addSequential(new TargetAlignment());
    }

    @Override
    protected void interrupted() {
        super.interrupted();
        Drivetrain.getInstance().kill();
        Claw.getInstance().kill();
    }
}