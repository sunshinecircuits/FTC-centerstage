 /*
 * Copyright (c) 2021 OpenFTC Team
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Blinker;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;


import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;


 /*x
  * This sample demonstrates how to run analysis during INIT
  * and then snapshot that value for later use when the START
  * command is issued. The pipeline is re-used from SkystoneDeterminationExample
  */
 @Autonomous(name="RedFrontCamTest")
 public class CamRedFront extends LinearOpMode
 {
     OpenCvWebcam webcam;
     SkystoneDeterminationExampleRedFront.SkystoneDeterminationPipeline pipeline;

     private DcMotor BLMotor;
     private DcMotor BRMotor;
     private Blinker control_Hub;
     private CRServo epicServo;
     private DcMotor FLMotor;
     private DcMotor FRMotor;
     public DcMotor Intake;
     public String PosString;
     private IMU imu;
     public int temp1;
     public int temp2;
     public int temp3;
     //    @Override
     public void driver(int FR, int FL, int BR, int BL, double pow, String telem){
         int FRtargetpos;
         int FLtargetpos;
         int BRtargetpos;
         int BLtargetpos;

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

             telemetry.addData("Avg1",temp1);
             telemetry.addData("Avg2",temp2);
             telemetry.addData("Avg3",temp3);

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


     @Override
     public void runOpMode()
     {
         /**
          * NOTE: Many comments have been omitted from this sample for the
          * sake of conciseness. If you're just starting out with EasyOpenCv,
          * you should take a look at {@link InternalCamera1Example} or its
          *'
          *
          * webcam counterpart, {@link WebcamExample} first.
          */

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

         int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
         webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam"), cameraMonitorViewId);
         pipeline = new SkystoneDeterminationExampleRedFront.SkystoneDeterminationPipeline();
         webcam.setPipeline(pipeline);

         webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
         {
             @Override
             public void onOpened()
             {
                 webcam.startStreaming(640,360, OpenCvCameraRotation.UPRIGHT);
             }

             @Override
             public void onError(int errorCode) {}
         });

         /*
          * The INIT-lofop:
          * This REPLACES waitForStart!
          */

         /*
          * The START command just came in: snapshot the current analysis now
          * for later
          * We must do this because the analysis will continue*
         * to change as the camera view changes once the robot starts moving!
         */

         /*
          * Show that snapshot on the telemetry
          */

         PosString = pipeline.getAnalysis();
         telemetry.addData("Pos", SkystoneDeterminationExampleRedFront.pos);
         telemetry.addData("PosString", PosString);
         telemetry.addData("Analysis", pipeline.getAnalysis());
         telemetry.addData("AVG1", pipeline.avg1);
         telemetry.addData("AVG2", pipeline.avg2);
         telemetry.addData("AVG3", pipeline.avg3);
         telemetry.update();
         waitForStart();
         PosString = pipeline.getAnalysis();
         telemetry.addData("Pos", SkystoneDeterminationExampleRedFront.pos);
         telemetry.addData("PosString", PosString);
         telemetry.addData("Analysis", pipeline.getAnalysis());
         telemetry.addData("AVG1", pipeline.avg1);
         temp1= pipeline.avg1;
         telemetry.addData("AVG2", pipeline.avg2);
         temp2= pipeline.avg2;
         telemetry.addData("AVG3", pipeline.avg3);
         temp3= pipeline.avg3;
         telemetry.update();
         if (opModeIsActive())
         {
            /* These values determine how far things go
            Forward: Approx 1ft =500
            Sideways: Approx 10.5-11=500
            Rotation: Almost exactly 90 degrees=925
            */

             telemetry.addData("FirstMove","Going");
             telemetry.update();
             String pos=SkystoneDeterminationExampleRedFront.pos;
             if (pos.equals("LEFT")){
                 driver(-150,150,150,-150,0.25,"Zero");
                 driver(1250,1250,1250,1250,0.5,"one");
                 driver(950,-950,950,-950,0.25,"two");//right
                 driver(-100,-100,-100,-100,0.5,"10/4");
                 Mot(Intake, 2000, true, 0.25, "three" );
                 driver(225,225,225,225,0.25,"four");//Forward
                 Mot(Intake, 100, true, 0.1, "Four-Point-Five" );
                 driver(-325,-325,-325,-325,0.25, "five");
                 driver(-150,150,150,-150,0.25, "six");
                 driver(-1500,-1500,-1500,-1500,0.2, "seven");
                 servy(1,1200, "eight");
                 servy(-1,1200, "nine");
                 driver(200,200,200,200,0.5, "nine");//front
                 driver(1500,-1500,-1500,1500,0.75, "ten");//left
                 driver(-400,-400,-400,-400,0.5, "eleven");//back
             } else if (pos.equals("CENTER")){
                 driver(25,25,25,25,0.5,"idc");
                 driver(-50,50,50,-50,0.5,"zero");
                 driver(1000,1000,1000,1000,0.5,"one");
                 driver(-200,200,200,-200,0.25,"two");
                 Mot(Intake, 2000, true, 0.25, "three" );
                 driver(205,205,205,205,0.25,"two");
                 driver(-350,-350,-350,-350,0.75,"four");
                 driver(920,-920, 920,-920,0.5,"five");
                 driver(-1600,-1600,-1600,-1600,0.50, "six");
                 driver(-250,250,250,-250,0.5, "six");
                 servy(1,1200, "seven");
                 servy(-1,1200, "eight");
                 driver(200,200,200,200,0.5, "nine");//front
                 driver(1100,-1100,-1100,1100,0.75, "ten");//left
                 driver(-550,-550,-550,-550,0.75, "eleven");//back
             } else if (pos.equals("RIGHT")){
                 driver(-550,550,550,-550,0.5, "one");
                 driver(800,800,800,800,0.5, "two");
                 Mot(Intake,2000,true,0.5,"three");
                 driver(-100,-100,-100,-100,0.5,"five");
                 driver(925,-925,925,-925, 0.5, "six");
                 driver(-1300,-1300,-1300,-1300, 0.25, "seven");
                 servy(1,1200, "nine");
                 servy(-1,1200, "ten");
                 driver(200,200,200,200, 0.5, "eleven");
                 driver(550,-550,-550,550, 0.5, "twelve");
                 driver(-400,-400,-400,-400, 0.5, "thirteen");
             }

             telemetry.addData("Moves","Done");
             telemetry.update();
             // RESET
         }
     }
 }
