/*
DEPRECATED
DO NOT USE

*/

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.*;
import com.qualcomm.robotcore.hardware.*;

public class SunTry extends LinearOpMode{
    /*
    * Part Declaration
    */
    public DcMotor FRfoot;
    public DcMotor FLfoot;
    public DcMotor BRfoot;
    public DcMotor BLfoot;
//    public Servo longy1;
//    public CRServo longy2;
//    public ColorSensor Colson;
    
    @Override
    public void runOpMode(){
        FRfoot=hardwareMap.get(DcMotor.class,"FRMotor");
        FLfoot=hardwareMap.get(DcMotor.class,"FLMotor");
        BRfoot=hardwareMap.get(DcMotor.class,"BRMotor");
        BLfoot=hardwareMap.get(DcMotor.class,"BLMotor");
//        longy1=hardwareMap.get(Servo.class,"Servo1");
//        longy2=hardwareMap.get(CRServo.class,"CRServo1");
//        Colson=hardwareMap.get(Servo.class,"CAfan");
        
        telemetry.addData("Status","Running");
        telemetry.addData("Heresy Levels","Low to Zero");
        telemetry.update();
        while (OpModeIsActive()){
            //Motors
            double FRtargetpow=0;
            double FLtargetpow=0;
            double BRtargetpow=0;
            double BLtargetpow=0;
//            boolean DEATH=false;
            FRtargetpow=gamepad1.right_stick_y;
            FRfoot.setPower(FRtargetpow);
            telemetry.addData("Front Right TP", FRtargetpow);
            telemetry.addData("Front Right CP", FRfoot.getPower());
            FLtargetpow=-gamepad1.left_stick_y;
            FLfoot.setPower(FLtargetpow);
            telemetry.addData("Front Left TP", FLtargetpow);
            telemetry.addData("Front Left CP", FLfoot.getPower());
            BRtargetpow=gamepad1.right_stick_y;
            BRfoot.setPower(BRtargetpow);
            telemetry.addData("Back Right TP", BRtargetpow);
            telemetry.addData("Back Right CP", BRfoot.getPower());
            BLtargetpow=-gamepad1.left_stick_y;
            BLfoot.setPower(BLtargetpow);
            telemetry.addData("Back Left TP", BLtargetpow);
            telemetry.addData("Back Left CP", BLfoot.getPower());
           DEATH=gamepad1.y;
            if (DEATH){
                System.exit(0);
            }
            
/*            // Servos
            double SersTP
            SersTP=gamepad1.right_trigger;
            longy1.setPower(SersTP);
            longy2.setPower(SersTP);
            telemetry.addData("Servos TP", SersTP);
            telemetry.addData("Servo 1 CP", longy1.getPower());
            telemetry.addData("Servo 2 CP", longy2.getPower());
            double NSersTP
            NSersTP=-gamepad1.left_trigger;
            longy1.setPower(NSersTP);
            longy2.setPower(NSersTP);
            telemetry.addData("Neg Servos TP", NSersTP);
            telemetry.addData("Servo 1 CP", longy1.getPower());
            telemetry.addData("Servo 2 CP", longy2.getPower());
            */
        }
    }
    public static void main(String[] args){
        
    }
}
