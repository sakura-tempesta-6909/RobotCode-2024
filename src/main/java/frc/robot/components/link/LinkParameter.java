
package frc.robot.components.link;

import frc.robot.components.link.LinkConst.LinkLeftSoftLimit;
import frc.robot.components.link.LinkConst.LinkRightSoftLimit;

public class LinkParameter {
    public static final class Angles {
        /**
         * 左の上と下の差: 221
         * 右の上と下の差: 245
         */

        /** 左の差 */
        public static final double LinkLeftGap = LinkRightSoftLimit.ForwardSoftLimit - LinkRightSoftLimit.ReverseSoftLimit;
        /** 右の差 */
        public static final double LinkRightGap = LinkLeftSoftLimit.ForwardSoftLimit - LinkLeftSoftLimit.ReverseSoftLimit;
        /** 左と右の差 */
        public static final double LinkGapPercent = LinkLeftGap / LinkRightGap;

        /** Ampの角度 */
        public static final double AmpLinkLeft = LinkLeftSoftLimit.ReverseSoftLimit + 221;
        /** SpeakerBelowの角度 */
        public static final double SpeakerBelowLinkLeft = LinkLeftSoftLimit.ReverseSoftLimit + 206;
        /** Podiumの角度 */
        public static final double SpeakerPodiumLinkLeft = LinkLeftSoftLimit.ReverseSoftLimit + 159;
        /** 第2Podiumの角度 */
        public static final double SpeakerSecondPodiumLinkLeft = LinkLeftSoftLimit.ReverseSoftLimit + 88;
        /** Intakeの角度 */
        public static final double IntakeLinkLeft = LinkLeftSoftLimit.ReverseSoftLimit + 150;
        /** Climbの準備の角度 */
        public static final double SetClimbLinkLeft = LinkLeftSoftLimit.ReverseSoftLimit + 221;
        /** Stageの角度 */
        public static final double StageLinkLeft = LinkLeftSoftLimit.ReverseSoftLimit;
        /** KeepCurrentの角度 */
        public static final double KeepCurrentAngleLinkLeft = LinkLeftSoftLimit.ReverseSoftLimit + 48;
        /** Climbするときの角度 */
        public static final double ClimbLinkLeft = LinkLeftSoftLimit.ReverseSoftLimit;

        /** Ampの角度 */
        public static final double AmpLinkRight = LinkRightSoftLimit.ReverseSoftLimit + (221 * LinkGapPercent);
        /** SpeakerBelowの角度 */
        public static final double SpeakerBelowLinkRight = LinkRightSoftLimit.ReverseSoftLimit + (206 * LinkGapPercent);
        /** Podiumの角度 */
        public static final double SpeakerPodiumLinkRight = LinkRightSoftLimit.ReverseSoftLimit + (159 * LinkGapPercent);
        /** 第2Podiumの角度 */
        public static final double SpeakerSecondPodiumLinkRight = LinkRightSoftLimit.ReverseSoftLimit + (88 * LinkGapPercent);
        /** Intakeの角度 */
        public static final double IntakeLinkRight = LinkRightSoftLimit.ReverseSoftLimit + (150 * LinkGapPercent);
        /** Climbの準備の角度 */
        public static final double SetClimbLinkRight = LinkRightSoftLimit.ReverseSoftLimit + (221 * LinkGapPercent);
        /** Stageの角度 */
        public static final double StageLinkRight = LinkRightSoftLimit.ReverseSoftLimit;
        /** KeepCurrentの角度 */
        public static final double KeepCurrentAngleLinkRight = LinkRightSoftLimit.ReverseSoftLimit + (48 * LinkGapPercent);
        /** Climbするときの角度 */
        public static final double ClimbLinkRight = LinkRightSoftLimit.ReverseSoftLimit;
    }

    // pidの値を書く
    public static class PID {
        /** gain(0) */
        public static final double UpLinkP = 3.5;
        public static final double UpLinkI = 1.5e-4;
        public static final double UpLinkD = 10;

        /** gain(1) */
        public static final double DownLinkP = 5;
        public static final double DownLinkI = 2.5e-4;

        /** gain(2) */
        public static final double ClimbLinkP = 8;
        public static final double ClimbLinkI = 4e-3;
    }

    public static class Current {
        /** LED関係 */
        public static final double ClimbCurrent = 40;
    }

    public static class Percent {
        /** KeepCurrentの時のOutPercentOutput */
        public static final double KeepCurrentAngleLink = 0.05;
        /** ClimbするときのPercentOutput */
        public static final double climb = -1;
    }

    public class FineAdjustment {
        /** Linkを上向きに微調整するときのPercentOutput */
        public static final double downAdjustment = -0.2;
        /** Linkを下向きに微調整するときのPercentOutput */
        public static final double upAdjustment = 0.2;
    }

    public static void ConstInit() {
    }

}