package frc.robot.mast.mastcommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.robotcore.userinput.OI;
import frc.robot.mast.Mast;

public class MoveMastManual extends Command{
    private Mast mast = Mast.getInstance();
    private OI gamepad = OI.getInstance();

    public MoveMastManual(){
        setInterruptible(true);
        requires(mast);
    }
    @Override
    protected void initialize() {
        super.initialize();
    }
    @Override
    protected void execute() {
        mast.runMastManual(gamepad.mastDrive());
    }
    @Override
    protected boolean isFinished() {
        return false;
    }
    @Override
    protected void interrupted() {
        super.interrupted();
        mast.kill();
    }
    @Override
    protected void end() {
        super.end();
    }

}