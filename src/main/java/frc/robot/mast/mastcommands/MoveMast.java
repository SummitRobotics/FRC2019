package frc.robot.mast.mastcommands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.mast.Mast;

public class MoveMast extends Command{
    private Mast mast = Mast.getInstance();
    private Mast.MastState position;

    public MoveMast (Mast.MastState position){
        requires(mast);
        this.position = position;
    }
    @Override
    protected void initialize() {
        super.initialize();
        mast.setMast(position);
    }
    @Override
    protected void execute() {
    
    }
    @Override
    protected boolean isFinished() {
        return mast.withinThreshold(position.value);
    }
    @Override
    protected void end() {
        super.end();
        SmartDashboard.putBoolean("Move mast done", true);
    }
}