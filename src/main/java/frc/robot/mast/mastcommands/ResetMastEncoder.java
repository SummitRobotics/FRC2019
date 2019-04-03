package frc.robot.mast.mastcommands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.mast.Mast;

public class ResetMastEncoder extends InstantCommand {

    Mast mast = Mast.getInstance();

    public ResetMastEncoder() {
        requires(mast);
    }
    @Override
    protected void execute() {
        mast.setEncoderPos(0);
    }
}