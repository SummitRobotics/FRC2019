package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain.Gear;

public class Shift extends Command implements CommandInterface{
    protected boolean gearNew;

    public Shift(){

    }

    @Override
    protected void initialize() {
        super.initialize();
    }
    @Override
    protected void execute() {
        subsystems.drivetrain.setGear(Gear.LOW);
    }
    @Override
    protected boolean isFinished() {
        return false;
    }
    
}