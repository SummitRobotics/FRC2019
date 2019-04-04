package frc.robot.chair.chaircommands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.chair.Chair;

public class ActuatePeg{
    private Chair chair = Chair.getInstance();

    public ActuatePeg(){

    }

    public class SetPeg extends InstantCommand{
        private Chair.PegState pegPosition;

        public SetPeg(Chair.PegState pegPosition){
            setInterruptible(true);
            requires(chair);
            this.pegPosition = pegPosition;
        }
        @Override
        protected void initialize() {
            super.initialize();
        }
        @Override
        protected void execute() {
            chair.setPeg(pegPosition);
        }
        @Override
        protected void end() {
            super.end();
        }
    }

    public class TogglePeg extends InstantCommand{
        private Chair.PegState pegPosition;

        public TogglePeg(){
            setInterruptible(true);
            requires(chair);
        }
        @Override
        protected void initialize() {
            super.initialize();
        }
        @Override
        protected void execute() {
            this.pegPosition = chair.togglePeg();
            chair.setPeg(pegPosition);
        }
        @Override
        protected void end() {
            super.end();
        }
    }

}