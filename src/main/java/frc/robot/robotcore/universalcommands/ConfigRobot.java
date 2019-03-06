package frc.robot.robotcore.universalcommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.robotcore.RobotBuilder;
import frc.robot.cargointake.CargoIntake;
import frc.robot.drivetrain.Drivetrain;
import frc.robot.panelclaw.Peg;

public class ConfigRobot extends Command{
    RobotBuilder robot = RobotBuilder.getInstance();

    public ConfigRobot(){

    }
    @Override
    protected void initialize() {
        robot.cargoIntake.setArmEncoder(0);
        robot.drivetrain.setDrivetrainEncoders(0);
        robot.drivetrain.resetGyro();
    }
    @Override
    protected void execute() {
        robot.cargoIntake.setRollers(CargoIntake.RollerState.OFF);
        robot.cargoIntake.setIntakeArm(CargoIntake.IntakeArmState.UP);
        robot.drivetrain.shiftGear(Drivetrain.GearState.HIGH);
        robot.peg.setPeg(Peg.PegState.UP);
        robot.peg.setChair(Peg.PneumaticState.IN);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
    @Override
    protected void end() {
        super.end();
    }
}