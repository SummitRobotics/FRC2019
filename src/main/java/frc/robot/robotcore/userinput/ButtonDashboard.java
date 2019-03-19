package frc.robot.robotcore.userinput;

import edu.wpi.first.wpilibj.buttons.Button;

public class ButtonDashboard{

    private ButtonDashboardDriver dashboard;
    
    protected Button MastHigh = new Button(){
    
        @Override
        public boolean get() {
            return dashboard.getMastHigh();
        }
    };

    protected Button MastMid = new Button(){
    
        @Override
        public boolean get() {
            return dashboard.getMastMid();
        }
    };

    protected Button MastLow = new Button(){
    
        @Override
        public boolean get() {
            return dashboard.getMastLow();
        }
    };
    
    protected Button CargoLoadStation = new Button(){
    
        @Override
        public boolean get() {
            return dashboard.getCargoLoad();
        }
    };

    protected Button CargoGround = new Button(){
    
        @Override
        public boolean get() {
            return dashboard.getCargoGround();
        }
    };

    protected Button PanelLoad = new Button(){
    
        @Override
        public boolean get() {
            return dashboard.getPanelLoad();
        }
    };

    protected Button PanelGround = new Button(){
    
        @Override
        public boolean get() {
            return dashboard.getPanelGround();
        }
    };

    protected Button cancelAutomation = new Button(){
    
        @Override
        public boolean get() {
            return dashboard.getCancel();
        }
    };

    protected Button climb = new Button(){
    
        @Override
        public boolean get() {
            return dashboard.getClimb();
        }
    };

    public ButtonDashboard(){
        dashboard = new ButtonDashboardDriver();
    }
}