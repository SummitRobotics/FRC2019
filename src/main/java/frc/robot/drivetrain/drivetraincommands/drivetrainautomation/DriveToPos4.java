package frc.robot.drivetrain.drivetraincommands.drivetrainautomation;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveToPos4 extends CommandGroup {

    public DriveToPos4(boolean isRight){

        int angleInverse = 1;

        if (isRight) {
            angleInverse *= -1;
        }


    }
}