package frc.robot.drivetrain.drivetraincommands.drivetrainautomation;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.robotcore.userinput.ButtonDashboard;

public class DriveToPos1 extends CommandGroup {

    public DriveToPos1(boolean isRight){
        int angleInverse = 1;

        if (isRight) {
            angleInverse *= -1;
        }

    }
}