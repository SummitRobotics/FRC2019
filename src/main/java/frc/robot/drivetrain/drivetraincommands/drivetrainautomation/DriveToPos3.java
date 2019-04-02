package frc.robot.drivetrain.drivetraincommands.drivetrainautomation;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveToPos3 extends CommandGroup {

    public DriveToPos3(boolean isRight){

        int angleInverse = 1;

        if (isRight) {
            angleInverse *= -1;
        }


    }
}