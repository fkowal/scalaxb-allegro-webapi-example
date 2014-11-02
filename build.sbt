import ScalaxbKeys._
import AssemblyKeys._ // put this at the top of the file

assemblySettings

val scalaXml = "org.scala-lang.modules" %% "scala-xml" % "1.0.2"
val scalaParser = "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.1"
val configParser = "com.typesafe" % "config" % "1.2.1"
val dispatchV = "0.11.1" // change this to appropriate dispatch version
val dispatch = "net.databinder.dispatch" %% "dispatch-core" % dispatchV
 
organization := "com.example"
 
name := "scalaxb-allegro-sample"
 
scalaVersion := "2.11.1"
 
scalaxbSettings
 
packageName in (Compile, scalaxb) := "allegro"
 
dispatchVersion in (Compile, scalaxb) := dispatchV
 
async in (Compile, scalaxb) := true
 
sourceGenerators in Compile <+= scalaxb in Compile
 
resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

mainClass in (Compile, run) := Some("Main")

mainClass in (Compile, packageBin) := Some("Main")



libraryDependencies ++= Seq(scalaXml, scalaParser, dispatch, configParser)