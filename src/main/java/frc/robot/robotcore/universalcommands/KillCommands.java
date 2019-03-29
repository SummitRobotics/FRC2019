package frc.robot.robotcore.universalcommands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.command.Scheduler;

public class KillCommands extends InstantCommand {

    public KillCommands(){
        
    }

    @Override
    protected void initialize() {
        super.initialize();
    }
    @Override
    protected void execute() {
        Scheduler.getInstance().removeAll();
    }
    @Override
    protected void end() {
        super.end();
    }
}