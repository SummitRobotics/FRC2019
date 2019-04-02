package frc.robot.drivetrain.drivetraincommands.drivetrainautomation;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveToPos2 extends CommandGroup {

    public DriveToPos2(boolean isRight){

        int angleInverse = 1;

        if (isRight) {
            angleInverse *= -1;
        }


    }
}