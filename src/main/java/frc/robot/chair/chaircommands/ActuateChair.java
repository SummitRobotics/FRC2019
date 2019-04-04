package frc.robot.chair.chaircommands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.chair.Chair;

public class ActuateChair{
    private Chair chair = Chair.getInstance();

    public ActuateChair(){
        
    }

public class SetChair extends InstantCommand{
    private Chair.PneumaticState chairPos;

        public SetChair(Chair.PneumaticState chairPos){
            setInterruptible(true);
            requires(chair);
            this.chairPos = chairPos;
        }
        @Override
        protected void initialize() {
            super.initialize();
        }
        @Override
        protected void execute() {
            chair.setChair(chairPos);
        }
        @Override
        protected void end() {
            super.end();
        }
    }
    public class ToggleChair extends InstantCommand{
        private Chair.PneumaticState panelPosition;
        
        public ToggleChair(){
            setInterruptible(true);
            requires(chair);
        }
        @Override
        protected void initialize() {
            super.initialize();
        }
        @Override
        protected void execute() {
            this.panelPosition = chair.toggleChair();
            chair.setChair(panelPosition);
        }
        @Override
        protected void end() {
            super.end();
        }
    }
}