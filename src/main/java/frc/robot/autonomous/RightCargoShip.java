package frc.robot.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.chair.Chair;
import frc.robot.chair.chairautomation.EjectPanel;
import frc.robot.climber.climbautomation.Level2Descend;
import frc.robot.drivetrain.Drivetrain;
import frc.robot.drivetrain.drivetraincommands.EncoderDrive;
import frc.robot.drivetrain.drivetraincommands.GyroTurn;
import frc.robot.drivetrain.drivetraincommands.PowerDrive;
import frc.robot.panelclaw.Claw;
import frc.robot.robotcore.universalcommands.Wait;

public class RightCargoShip extends CommandGroup{

    public RightCargoShip(){
        setInterruptible(true);
        requires(Drivetrain.getInstance());

        addSequential(new Level2Descend());
        addSequential(new Wait(0.5));
        addSequential(new GyroTurn(10));
        addSequential(new PowerDrive(-0.7, 1.2));
        addSequential(new GyroTurn(45));

    }

    @Override
    protected void interrupted() {
        super.interrupted();
        Drivetrain.getInstance().kill();
        Claw.getInstance().kill();
    }
}