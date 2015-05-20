import sbt._
import Keys._
import com.typesafe.sbt.SbtMultiJvm.MultiJvmKeys._
import twirl.sbt.TwirlPlugin.Twirl
import twirl.sbt.TwirlPlugin._

object Build extends Build {

  import BuildSettings._
  import Dependencies._

  // configure prompt to show current project
  override lazy val settings = super.settings :+ {
    shellPrompt := {
      s => Project.extract(s).currentProject.id + " > "
    }
  }

  // -------------------------------------------------------------------------------------------------------------------
  // Root Project
  // -------------------------------------------------------------------------------------------------------------------

  lazy val root = Project("zzb", file("."))
    .aggregate(zzbXmpp)
    .settings(basicSettings: _*)
    .settings(noPublishing: _*)


  // -------------------------------------------------------------------------------------------------------------------
  // Modules
  // -------------------------------------------------------------------------------------------------------------------

 
  lazy val zzbXmpp = Project("zzb-xmpp", file("zzb-xmpp"))
    .settings(zzbModuleSettings: _*)
    .settings(disableParallelTestSetting:_*)
    .settings(libraryDependencies ++=
    compile(scalaloggingSlf4j,nScalaTime,smackTcp,akkaActor,smackCore,smackExts,sprayUtil) ++
      test(scalatest,akkaTestKit) ++
      runtime(akkaSlf4j, logback)
    )
  // -------------------------------------------------------------------------------------------------------------------
  // Example Projects
  // -------------------------------------------------------------------------------------------------------------------


}
