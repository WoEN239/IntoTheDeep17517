package com.example.meepmeep;

import com.acmerobotics.roadrunner.Pose2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.util.FieldUtil;
import com.noahbres.meepmeep.roadrunner.Constraints;
import com.noahbres.meepmeep.roadrunner.DriveTrainType;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.awt.Font;

public class MyMeepMeep {
    public static void main(String[] args) {
        FieldUtil.Companion.setFIELD_HEIGHT(3658);
        FieldUtil.Companion.setFIELD_WIDTH(3658);

        MeepMeep meepMeep = new MeepMeep(600);
        meepMeep.setAxesInterval(1000);

        RoadRunnerBotEntity myBot = new RoadRunnerBotEntity(meepMeep,
                new Constraints(600,600,3.14,3.14,300),
                300,300,
                new Pose2d(-1650,1710,0),
                meepMeep.getColorManager().getTheme(),1, DriveTrainType.MECANUM,false
        );


        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-1650, 1710, 0))
                .lineToX(30)
                .turn(Math.toRadians(90))
                .lineToY(30)
                .turn(Math.toRadians(90))
                .lineToX(0)
                .turn(Math.toRadians(90))
                .lineToY(0)
                .turn(Math.toRadians(90))
                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();

    }
    //
}