package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;

@Autonomous(name="RBackC")

public class RBackC extends LinearOpMode{
    private DcMotor BLMotor;
    private DcMotor BRMotor;
    private CRServo epicServo;
    private DcMotor FLMotor;
    private DcMotor FRMotor;
    private DcMotor Intake;
    //    @Override
    public void driver(int FR, int FL, int BR, int BL, double pow, String telem){
        int FRtargetpos;
        int FLtargetpos;
        int BRtargetpos;
        int BLtargetpos;

        FRMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FLMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BRMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BLMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        telemetry.addData("Variables "+telem,"Declared");
        telemetry.update();

        FRtargetpos=FRMotor.getCurrentPosition()-(FR);
        FLtargetpos=FLMotor.getCurrentPosition()+(FL);
        BRtargetpos=BRMotor.getCurrentPosition()-(BR);
        BLtargetpos=BLMotor.getCurrentPosition()+(BL);

        telemetry.addData("MotorData "+telem,"Snatched");
        telemetry.update();

        FRMotor.setTargetPosition(FRtargetpos);
        FLMotor.setTargetPosition(FLtargetpos);
        BRMotor.setTargetPosition(BRtargetpos);
        BLMotor.setTargetPosition(BLtargetpos);

        telemetry.addData("TargetPos "+telem,"Declared");
        telemetry.addData("TargetPosFR ",FRtargetpos);
        telemetry.addData("TargetPosFL ",FLtargetpos);
        telemetry.addData("TargetPosBR ",BRtargetpos);
        telemetry.addData("TargetPosBL ",BLtargetpos);
        telemetry.update();

        FRMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FLMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BRMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BLMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        telemetry.addData("Running "+telem,"Happening");
        telemetry.update();

        FRMotor.setPower(pow);
        FLMotor.setPower(pow);
        BRMotor.setPower(pow);
        BLMotor.setPower(pow);

        telemetry.addData("Power "+telem,"Set");
        telemetry.update();

        telemetry.addData("While "+telem,"Started");
        telemetry.update();

        while (opModeIsActive()&&
                (
                        FRMotor.isBusy()||FLMotor.isBusy()||BRMotor.isBusy()||BLMotor.isBusy()
                )
        )
        {
            telemetry.addData("CurrentPosFR",FRMotor.getCurrentPosition());
            telemetry.addData("CurrentPosFL",FLMotor.getCurrentPosition());
            telemetry.addData("CurrentPosBR",BRMotor.getCurrentPosition());
            telemetry.addData("CurrentPosBL",BLMotor.getCurrentPosition());

            telemetry.addData("TargetPosFR ",FRtargetpos);
            telemetry.addData("TargetPosFL ",FLtargetpos);
            telemetry.addData("TargetPosBR ",BRtargetpos);
            telemetry.addData("TargetPosBL ",BLtargetpos);
            telemetry.update();
        }

        telemetry.addData("While "+telem,"Done");
        telemetry.update();

        FRMotor.setPower(0);
        FLMotor.setPower(0);
        BRMotor.setPower(0);
        BLMotor.setPower(0);

        telemetry.addData("Power","Zero");
        telemetry.update();
    }

    public void Mot(DcMotor motor, int dist, boolean rev, double pow, String telem){
        int Mottargetpos;
        telemetry.addData("Variables "+telem,"Declared");
        telemetry.update();
        if (rev) {
            Mottargetpos = motor.getCurrentPosition() - (dist);
        }else{
            Mottargetpos = motor.getCurrentPosition() + (dist);
        }
        motor.setTargetPosition(Mottargetpos);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor.setPower(pow);
        while (opModeIsActive()&& (motor.isBusy()))
        {
            telemetry.addData("CurrentPosM",motor.getCurrentPosition());
            telemetry.addData("TargetPosM ",Mottargetpos);
            telemetry.update();
        }
        motor.setPower(0);
    }

    public void servy(double pow, int sleeper,String Telem){
        epicServo.setPower(pow);
        telemetry.addData("EpicServo "+Telem,pow);
        sleep(sleeper);
        epicServo.setPower(0);
    }

    //*/
    public void runOpMode()

    {
        telemetry.addData("Motors","Grabbing");
        telemetry.update();
        FRMotor = hardwareMap.get(DcMotor.class,"FRMotor");
        FLMotor = hardwareMap.get(DcMotor.class,"FLMotor");
        BRMotor = hardwareMap.get(DcMotor.class,"BRMotor");
        BLMotor = hardwareMap.get(DcMotor.class,"BLMotor");
        Intake = hardwareMap.get(DcMotor.class,"Intake");
        telemetry.addData("Motors","Got");
        telemetry.update();
        epicServo = hardwareMap.get(CRServo.class, "Aero");

        waitForStart();
        if (opModeIsActive())
        {
            /* These values determine how far things go
            Forward: Approx 1ft =500
            Sideways: Approx 10.5-11=500
            Rotation: Almost exactly 90 degrees=925
            .
            */
            telemetry.addData("FirstMove","Going");
            telemetry.update();

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            driver(300,-300,-300,300,0.5,"one");
            driver(2200,2200,2200,2200,0.5,"two");
            driver(925,-925,925,-925, 0.5 ,"three");
            driver(-3800,-3800,-3800,-3800,0.5,"four");
            driver(1200,-1200,-1200,1200,0.5,"five");
            driver(-250,-250,-250,-250,0.25,"six");
            servy(1,1200,"seven");
            servy(-1,1200,"eight");
            driver(200,200,200,200,0.5,"nine");//front
            driver(-1200,1200,1200,-1200,0.75,"ten");//right
            driver(-1850,1850,-1850,1850,0.5,"eleven");
            driver(100,100,100,100,0.25,"squigs");
            Mot(Intake, 2000, true, 1, "twelve");
            driver(-100,-100,-100,-100,1,"fourteen");



            //dr
            //
            // iver(150,-150,-150,150,0.25,"one");

            //driver(1800,1800,1800,1800,0.25,"two");

            //servy(1,1000);
            //servy(0,500);

            //driver(-100,-100,-100,-100,0.25,"three");

            telemetry.addData("Moves","Done");
            telemetry.update();
            // RESET
        }
    }
}