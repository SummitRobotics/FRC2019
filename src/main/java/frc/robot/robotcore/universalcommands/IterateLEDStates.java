package frc.robot.robotcore.universalcommands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.devices.Blinkin;
import frc.robot.devices.Blinkin.BlinkinState;

public class IterateLEDStates extends InstantCommand {

    private static int currentState = 0, val;
    private Blinkin blinkin;

    public IterateLEDStates(int val) {
        this.val = val;
        blinkin = Blinkin.getInstance();
    }

    @Override
    protected void initialize() {
        super.initialize();
    }

    @Override
    protected void execute() {
        super.execute();

        currentState += val;
        int newVal = Math.abs(currentState) % BlinkinState.values().length;

        if (Math.copySign(1, currentState) == -1) {
            blinkin.setLEDState(BlinkinState.values()[(newVal - newVal - 1)]);
        } else {
            blinkin.setLEDState(BlinkinState.values()[newVal]);
        }
    }

    @Override
    protected void end() {
        super.end();
    }
}