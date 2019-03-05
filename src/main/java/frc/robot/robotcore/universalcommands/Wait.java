package frc.robot.robotcore.universalcommands;

import edu.wpi.first.wpilibj.command.Command;

public class Wait extends Command{

    public Wait(double timeout){
        setInterruptible(false);
        setTimeout(timeout);
    }
    
    @Override
    protected void initialize() {
        super.initialize();
    }
    
    @Override
    protected void execute() {
        super.execute();
    }
    @Override
    protected boolean isFinished() {
        return isTimedOut();
    }
    @Override
    protected void end() {
        super.end();
    }
}