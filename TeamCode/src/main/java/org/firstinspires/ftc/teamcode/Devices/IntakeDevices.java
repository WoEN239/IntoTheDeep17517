package org.firstinspires.ftc.teamcode.Devices;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImplEx;

import org.firstinspires.ftc.teamcode.Modules.Intake.Diff.Diff;

/*
 Writing by @MrFrosty1234
*/

public class IntakeDevices {



    public static Diff scorer;
    public static Diff eater ;

    public static Servo flipRight    ;
    public static Servo flipLeft     ;

    public static Servo transferLeft ;
    public static Servo transferRight;

    public static ServoImplEx scorerLeft   ;
    public static ServoImplEx scorerRight  ;
    public static ServoImplEx scorerGrip   ;

    public static ServoImplEx eaterLeft    ;
    public static ServoImplEx eaterRight   ;
    public static ServoImplEx eaterGrip    ;

    public static void init(HardwareMap hardwareMap) {
        flipRight     = hardwareMap.get(Servo.class, "flipRight"     );
        flipLeft      = hardwareMap.get(Servo.class, "flipLeft"      );

        flipLeft.setDirection(Servo.Direction.FORWARD);
        flipRight.setDirection(Servo.Direction.REVERSE);

        transferLeft  = hardwareMap.get(Servo.class, "transferLeft"  );
        transferRight = hardwareMap.get(Servo.class, "transferRight" );

        transferLeft.setDirection(Servo.Direction.REVERSE);
        transferRight.setDirection(Servo.Direction.FORWARD);

        scorerLeft = hardwareMap.get   (ServoImplEx.class, "scorerLeft"    );
        scorerRight = hardwareMap.get  (ServoImplEx.class, "scorerRight"   );
        scorerGrip    = hardwareMap.get(ServoImplEx.class, "scorerGrip"    );

        scorerRight.setDirection(Servo.Direction.REVERSE);
        scorerLeft .setDirection(Servo.Direction.FORWARD);

        eaterLeft =    hardwareMap.get(ServoImplEx.class, "eaterLeft"      );
        eaterRight =   hardwareMap.get(ServoImplEx.class, "eaterRight"     );
        eaterGrip    = hardwareMap.get(ServoImplEx.class, "eaterGrip"      );

        eaterLeft.setDirection(Servo.Direction.REVERSE);
        eaterRight.setDirection(Servo.Direction.FORWARD);

        scorer = new Diff(scorerRight, scorerLeft);
        eater  = new Diff(eaterRight, eaterLeft);
    }
}
