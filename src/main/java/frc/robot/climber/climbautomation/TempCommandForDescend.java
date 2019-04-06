package frc.robot.climber.climbautomation;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.climber.climbcommands.EngageFrontPistons;
import frc.robot.climber.climbcommands.EngageRearPiston;
import frc.robot.robotcore.universalcommands.Wait;

public class TempCommandForDescend extends CommandGroup{

    public TempCommandForDescend(){
        addSequential(new Wait(0.4));
        addSequential(new EngageFrontPistons(Value.kForward));
        addSequential(new Wait(0.3));
        addSequential(new EngageFrontPistons(Value.kReverse));
        addSequential(new EngageRearPiston(Value.kReverse));
    }
}