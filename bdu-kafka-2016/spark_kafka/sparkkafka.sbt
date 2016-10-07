import AssemblyKeys._

assemblySettings

name := "SparkKafka Project"

version := "1.0"

scalaVersion := "2.10.4"

val sparkVersion = "1.6.1"

libraryDependencies ++= Seq(
	"org.apache.spark" %% "spark-core" % sparkVersion % "provided",
	"org.apache.spark" %% "spark-streaming" % sparkVersion % "provided",
	"org.apache.spark" %% "spark-streaming-kafka" % sparkVersion)

mergeStrategy in assembly := {
	case m if m.toLowerCase.endsWith("manifest.mf") => MergeStrategy.discard
	case m if m.toLowerCase.startsWith("META-INF")  => MergeStrategy.discard
	case "reference.conf"                           => MergeStrategy.concat
	case m if m.endsWith("UnusedStubClass.class")   => MergeStrategy.discard
	case _                                          => MergeStrategy.first
}