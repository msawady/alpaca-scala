name := "alpaca-scala"

version := "3.0.0"

// POM settings for Sonatype
import xerial.sbt.Sonatype._
organization := "com.cynance"
homepage := Some(url("https://github.com/cynance/alpaca-scala"))
sonatypeProjectHosting := Some(GitHubHosting("cynance", "alpaca-scala", "devs@cynance.com"))
scmInfo := Some(ScmInfo(url("https://github.com/cynance/alpaca-scala"),"git@github.com:cynance/alpaca-scala.git"))
developers := List(Developer("cynance",
  "cynance",
  "devs@cynance.com",
  url("https://github.com/cynance")))
licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0"))
publishMavenStyle := true

enablePlugins(MicrositesPlugin)

// Add sonatype repository settings
publishTo := sonatypePublishTo.value

scalaVersion := "2.12.8"

val circeVersion = "0.13.0"
val hammockVersion = "0.11.3"
val catsVersion = "2.3.1"
val macwireVersion = "2.3.7"

val pureConfigVersion = "0.14.0"
val scalacticVersion = "3.2.3"
val akkaHttpVersion = "10.2.3"
val akkaStreamVersion = "2.6.11"
val jnatsVersion = "2.8.0"
val logBackVersion = "1.2.3"
val scalaLoggingVersion = "3.9.2"
val enumeratumVersion = "1.6.1"

val scalaTestVersion = "3.2.3"
val scalaMockVersion = "5.1.0"
val mockitoScalaVersion = "1.16.15"
val ammoniteVersion = "2.3.8"

libraryDependencies ++= Seq(
  "com.pepegar" %% "hammock-core" % hammockVersion,
  "com.pepegar" %% "hammock-circe" % hammockVersion,
  "com.github.pureconfig" %% "pureconfig" % pureConfigVersion,
  "io.circe" %% "circe-core" % circeVersion,
  "io.circe" %% "circe-generic" % circeVersion,
  "io.circe" %% "circe-parser" % circeVersion,
  "org.scalactic" %% "scalactic" % scalacticVersion,
  "org.scalatest" %% "scalatest" % scalaTestVersion % "test",
  "org.scalamock" %% "scalamock" % scalaMockVersion % "test",
  "com.typesafe.akka" %% "akka-http"   % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaStreamVersion, // or whatever the latest version is,
  "io.nats" % "jnats" % jnatsVersion,
  "org.typelevel" %% "cats-core" % catsVersion,
  "ch.qos.logback" % "logback-classic" % logBackVersion,
  "com.typesafe.scala-logging" %% "scala-logging" % scalaLoggingVersion,
  "com.softwaremill.macwire" %% "macros" % macwireVersion % "provided",
  "com.softwaremill.macwire" %% "macrosakka" % macwireVersion % "provided",
  "com.softwaremill.macwire" %% "util" % macwireVersion,
  "com.softwaremill.macwire" %% "proxy" % macwireVersion,
  "com.beachape" %% "enumeratum" % enumeratumVersion,
  "com.beachape" %% "enumeratum-circe" % enumeratumVersion,
  "org.mockito" %% "mockito-scala-scalatest" % mockitoScalaVersion,
  "com.lihaoyi" % "ammonite" % ammoniteVersion % "test" cross CrossVersion.full
)


sourceGenerators in Test += Def.task {
  val file = (sourceManaged in Test).value / "amm.scala"
  IO.write(file, """object amm extends App { ammonite.Main.main(args) }""")
  Seq(file)
}.taskValue

coverageExcludedPackages := ".*ConfigService.*;.*Config.*;alpaca\\.client\\..*"

coverageEnabled := true


//Microsite details
micrositeName := "Alpaca Scala"
micrositeDescription := "A Scala library for alpaca.markets"
micrositeAuthor := "Cynance"
micrositeBaseUrl := "/alpaca-scala"
micrositeDocumentationUrl := "/alpaca-scala/docs"

micrositePalette := Map(
  "brand-primary"     -> "#000",
  "brand-secondary"   -> "#000",
  "brand-tertiary"    -> "#fcd600",
  "gray-dark"         -> "#453E46",
  "gray"              -> "#837F84",
  "gray-light"        -> "#E3E2E3",
  "gray-lighter"      -> "#F4F3F4",
  "white-color"       -> "#FFFFFF")

micrositePushSiteWith := GitHub4s

micrositeGithubToken := sys.env.get("GITHUB_TOKEN")

micrositeGithubOwner := "cynance"
micrositeGithubRepo := "alpaca-scala"