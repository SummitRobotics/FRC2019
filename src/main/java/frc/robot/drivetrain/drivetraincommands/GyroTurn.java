package frc.robot.drivetrain.drivetraincommands;

import edu.wpi.first.wpilibj.command.Command;

public class GyroTurn extends Command{

    private double angle;

    public GyroTurn(double angle){
        this.angle = angle;

    }
    /*public GyroTurn(double angle, double radius, double power){
        
    }*/
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
        return false;
    }
    @Override
    protected void end() {
        super.end();
    }
}