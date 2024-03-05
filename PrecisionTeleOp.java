package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ServoController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


@TeleOp
public class PrecisionTeleOp extends LinearOpMode
{
    // Hardware declarations
    public DcMotor FRwheel;
    public DcMotor FLwheel;
    public DcMotor BRwheel;
    public DcMotor BLwheel;
    public DcMotor InMotor;
    public DcMotor Hanger1;
    public DcMotor Hanger2;
    public CRServo Wheel1;
    public CRServo Wheel2;

    public CRServo Wheel3;
    public Servo DRONE;
    private DcMotor Depo;
    // public CRServo PIXback;
    public CRServo PIXleft;
    public CRServo PIXright;
    public Servo BoxDoor;
    public CRServo Tilt;
    public double handicap=1.5;

    @Override
    public void runOpMode()
    {
        FRwheel = hardwareMap.get(DcMotor.class,"FRMotor");
        FLwheel = hardwareMap.get(DcMotor.class,"FLMotor");
        BRwheel = hardwareMap.get(DcMotor.class,"BRMotor");
        BLwheel = hardwareMap.get(DcMotor.class,"BLMotor");
        InMotor = hardwareMap.get(DcMotor.class,"Intake");
        Hanger1 = hardwareMap.get(DcMotor.class,"HMotor1");
        Hanger2 = hardwareMap.get(DcMotor.class,"HMotor2");
        Depo = hardwareMap.get(DcMotor.class,"Depo");
        DRONE = hardwareMap.get(Servo.class, "Airplane");
        BoxDoor=hardwareMap.get(Servo.class,"BoxDoor");
        // PIXback = hardwareMap.get(CRServo.class, "PixelBack");
        PIXleft = hardwareMap.get(CRServo.class, "PixelLeft");
        Wheel1 = hardwareMap.get(CRServo.class, "Wheel1");
        Wheel2 = hardwareMap.get(CRServo.class, "Wheel2");
        Wheel3 = hardwareMap.get(CRServo.class, "Wheel3");
        PIXright = hardwareMap.get(CRServo.class, "PixelRight");
        Tilt=hardwareMap.get(CRServo.class,"Tilt");
        telemetry.addData("Status","Intialized");
        telemetry.addData("Handicap",handicap);
        telemetry.update();
        int Depostartpos= Depo.getCurrentPosition();
        telemetry.addData("Depo", Depostartpos);

        Hanger1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Hanger2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Hanger1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Hanger2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        Depo.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Depo.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();
        while(opModeIsActive())
        {
            telemetry.addData("Status","Running");
            telemetry.update();
            //Motors
            double FRtargetpow=0;
            double FLtargetpow=0;
            double BRtargetpow=0;
            double BLtargetpow=0;
            double INtargetpow=0;
            double Hang1pow=0;
            double Hang2pow=0;
            double Depopow=0;
            double W1pow=0;
            double W2pow=0;
            double W3pow=0;
            double BoxDoorPos=0;
            double TiltPow=0;

            /*
            FRtargetpow=gamepad1.right_stick_y;
            FRwheel.setPower(handicap*FRtargetpow);
            telemetry.addData("Front Right TP", FRtargetpow);
            telemetry.addData("Front Right CP", FRwheel.getPower());
            FLtargetpow=-gamepad1.left_stick_y;
            FLwheel.setPower(handicap*FLtargetpow);
            telemetry.addData("Front Left TP", FLtargetpow);
            telemetry.addData("Front Left CP", FLwheel.getPower());
            BRtargetpow=gamepad1.right_stick_y;
            BRwheel.setPower(handicap*BRtargetpow);
            telemetry.addData("Back Right TP", BRtargetpow);
            telemetry.addData("Back Right CP", BRwheel.getPower());
            BLtargetpow=-gamepad1.left_stick_y;
            BLwheel.setPower(handicap*BLtargetpow);
            telemetry.addData("Back Left TP", BLtargetpow);
            telemetry.addData("Back Left CP", BLwheel.getPower());
            */
            //movement for forward and back
            FRtargetpow=gamepad1.left_stick_y;
            FRwheel.setPower(handicap*FRtargetpow);
            //telemetry.addData("Front Right TP", FRtargetpow);
            //telemetry.addData("Front Right CP", FRwheel.getPower());
            FLtargetpow=-gamepad1.left_stick_y;
            FLwheel.setPower(handicap*FLtargetpow);
            //telemetry.addData("Front Left TP", FLtargetpow);
            //telemetry.addData("Front Left CP", FLwheel.getPower());
            BRtargetpow=gamepad1.left_stick_y;
            BRwheel.setPower(handicap*BRtargetpow);
            //telemetry.addData("Back Right TP", BRtargetpow);
            //telemetry.addData("Back Right CP", BRwheel.getPower());
            BLtargetpow=-gamepad1.left_stick_y;
            BLwheel.setPower(handicap*BLtargetpow);
            //telemetry.addData("Back Left TP", BLtargetpow);
            //telemetry.addData("Back Left CP", BLwheel.getPower());

            //movement for left and right
            FRtargetpow=gamepad1.left_stick_x;
            FRwheel.setPower(handicap*FRtargetpow);
            telemetry.addData("Front Right TP", FRtargetpow);
            telemetry.addData("Front Right CP", FRwheel.getPower());
            FLtargetpow=gamepad1.left_stick_x;
            FLwheel.setPower(handicap*FLtargetpow);
            telemetry.addData("Front Left TP", FLtargetpow);
            telemetry.addData("Front Left CP", FLwheel.getPower());
            BRtargetpow=-gamepad1.left_stick_x;
            BRwheel.setPower(handicap*BRtargetpow);
            telemetry.addData("Back Right TP", BRtargetpow);
            telemetry.addData("Back Right CP", BRwheel.getPower());
            BLtargetpow=-gamepad1.left_stick_x;
            BLwheel.setPower(handicap*BLtargetpow);
            telemetry.addData("Back Left TP", BLtargetpow);
            telemetry.addData("Back Left CP", BLwheel.getPower());


            //movement - rotation!!
            FRtargetpow=gamepad1.right_stick_x;
            FRwheel.setPower(handicap*FRtargetpow);
            telemetry.addData("Front Right TP", FRtargetpow);
            telemetry.addData("Front Right CP", FRwheel.getPower());
            FLtargetpow=gamepad1.right_stick_x;
            FLwheel.setPower(handicap*FLtargetpow);
            telemetry.addData("Front Left TP", FLtargetpow);
            telemetry.addData("Front Left CP", FLwheel.getPower());
            BRtargetpow=gamepad1.right_stick_x;
            BRwheel.setPower(handicap*BRtargetpow);
            telemetry.addData("Back Right TP", BRtargetpow);
            telemetry.addData("Back Right CP", BRwheel.getPower());
            BLtargetpow=gamepad1.right_stick_x;
            BLwheel.setPower(handicap*BLtargetpow);
            telemetry.addData("Back Left TP", BLtargetpow);
            telemetry.addData("Back Left CP", BLwheel.getPower());

            //servo testing

            /*
            if (gamepad1.b)
            {
                EStargetpos=-1;
                epicservo.setPower(EStargetpos);
                //controller.setServoPosition(0,EStargetpos);
            }
            else if (gamepad1.y)
            {
                EStargetpos=1;
                epicservo.setPower(EStargetpos);
                //controller.setServoPosition(0,EStargetpos);
            }
            //epicservo.setPosition(EStargetpos);
            telemetry.addData("servo TP", EStargetpos);
            //telemetry.addData("servo CP", epicservo.getPosition());
            */
            // intake movement & wheels
            if (gamepad2.a)
            {
                W1pow=-1;
                W2pow=-1;
                W3pow=-1;

            }
            else if (gamepad2.x)
            {
                W1pow=1;
                W2pow=1;
                W3pow=1;
            }
            else
            {
                W1pow=0;
                W2pow=0;
                W3pow=0;
            }
            if (gamepad2.b)
            {
                INtargetpow=1;
            }
            else if (gamepad2.y)
            {
                INtargetpow=-1;
            }
            else
            {
                INtargetpow=0;
            }
            InMotor.setPower(INtargetpow);
            Wheel1.setPower(W1pow);
            Wheel2.setPower(W2pow);
            Wheel3.setPower(W3pow);
            telemetry.addData("A stat", gamepad2.a);
            telemetry.addData("X stat", gamepad2.x);
            telemetry.addData("Intake power", InMotor.getPower());
            telemetry.addData("Wheel power 1", W1pow);
            telemetry.addData("Wheel power 2", W2pow);
            telemetry.addData("Wheel power 3", W3pow);

            // deposition movement
            if (gamepad2.dpad_up && Depo.getCurrentPosition()>(Depostartpos-10000))
            {
                Depopow=-1;

            }
            else if (gamepad2.dpad_down && Depo.getCurrentPosition()<Depostartpos-250)
            {
                Depopow=1;
            }
            else if (gamepad2.dpad_up && gamepad1.a)
            {
                Depopow=-1;

            }
            else if (gamepad2.dpad_down && gamepad1.a)
            {
                Depopow=1;
            }
            else
            {
                Depopow=0;
            }

            Depo.setPower(Depopow);
            Depo.setPower(Depopow);
            telemetry.addData("Up stat", gamepad2.dpad_up);
            telemetry.addData("Down stat", gamepad2.dpad_down);
            telemetry.addData("Depo power", Depo.getPower());
            telemetry.addData("Depo position", Depo.getCurrentPosition());
            telemetry.addData("Depo start position", Depostartpos);

            //SNTH.setPower(handicap*SNTHtp);
            //telemetry.addData("servo1 TP", SNTHtp);
            //telemetry.addData("servo1 CP", SNTH.getPower());
            //*/
            if (gamepad1.right_trigger>=0.5){
                if (handicap<1){
                    handicap=handicap+0.1;
                }
                while (gamepad1.right_trigger>=0.5){

                }
            }else if (gamepad1.left_trigger>=0.5){
                if (handicap>0.1){
                    handicap=handicap-0.1;
                }
                while (gamepad1.left_trigger>=0.5){

                }
            }
            telemetry.addData("Handicap",handicap);
            /*
            //Pixplane servo usage/testing
            if (gamepad2.left_bumper)
            {

            }
            else if (gamepad2.y)
            {
                DRONEtargetpow=1;
            } else{
                DRONEtargetpow=0;
            }
             //*/

            // Hanger code

            Hang1pow=0.75;
            if (false==((Math.abs(gamepad2.left_stick_y) < 0.25)&&(Math.abs(gamepad2.right_stick_y) < 0.25))) {
                Hanger1.setPower(-Hang1pow*gamepad2.left_stick_y);
            }
            telemetry.addData("HANG1TP",Hang1pow*gamepad2.left_stick_y);
            telemetry.addData("HANG1POS",Hanger1.getCurrentPosition());
            if (false==((Math.abs(gamepad2.left_stick_y) < 0.25)&&(Math.abs(gamepad2.right_stick_y) < 0.25))) {
                Hanger2.setPower(Hang2pow*gamepad2.right_stick_y);
            }
            telemetry.addData("HANG2TP",Hang2pow*gamepad2.right_stick_y);
            telemetry.addData("HANG2POS",Hanger2.getCurrentPosition());

            if ((Math.abs(gamepad2.left_stick_y) < 0.25)&&(Math.abs(gamepad2.right_stick_y) < 0.25)) {
                int BrakPos1 = Hanger1.getCurrentPosition();
                int BrakPos2 = Hanger2.getCurrentPosition();
                Hanger1.setTargetPosition(BrakPos1);
                Hanger2.setTargetPosition(BrakPos2);
                //Hanger1.setPower(1);
                //Hanger2.setPower(1);
                Hanger1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                Hanger2.setMode(DcMotor.RunMode.RUN_TO_POSITION);


            } else if ((Math.abs(gamepad2.left_stick_y)> 0.25)||(Math.abs(gamepad2.right_stick_y) > 0.25)){
                Hanger1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                Hanger2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            }
            //Depo Code
            if (gamepad1.left_trigger>0.75)
            {
                BoxDoorPos=0.5;
            }
            else if (gamepad1.right_trigger>0.75)
            {
                BoxDoorPos=0.1;
            }else{
                BoxDoorPos= 0.3;
            }

            if (gamepad1.left_bumper)
            {
                TiltPow=1;
            }
            else if (gamepad1.right_bumper)
            {
                TiltPow=-1;
            }
            else
            {
                TiltPow=0;
            }
            Tilt.setPower(TiltPow);
            BoxDoor.setPosition(BoxDoorPos);
            telemetry.addData("Tilt Target Power", TiltPow);
            telemetry.addData("BoxDoorPos", BoxDoorPos);
            // break
        }
    }
}