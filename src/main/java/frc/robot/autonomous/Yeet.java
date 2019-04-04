package frc.robot.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.drivetrain.Drivetrain;
import frc.robot.drivetrain.drivetraincommands.EncoderDrive;
import frc.robot.drivetrain.drivetraincommands.GyroTurn;

public class Yeet extends CommandGroup{

    public Yeet(){
        setInterruptible(true);
        requires(Drivetrain.getInstance());

        //addSequential(new PowerDrive(1, 4.5));
        addSequential(new EncoderDrive(15));
        //addSequential(new GyroTurn(-90));
    }

    @Override
    protected void interrupted() {
        super.interrupted();
        Drivetrain.getInstance().kill();
    }
}