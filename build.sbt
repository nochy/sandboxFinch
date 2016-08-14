lazy val commonSettings = Seq(
  version := "0.1.0",
  scalaVersion := "2.11.8",
  scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature"),
  resolvers ++= Seq(
    "twitter" at "https://maven.twttr.com/"
  ),
  updateOptions := updateOptions.value.withCachedResolution(true),
  javacOptions in (Compile, compile) := Seq("-source", "1.8", "-target", "1.8")
 )

val finagleVersion = "6.35.0"
val twitterUtilVersion = "6.34.0"
val scroogeVersion = "4.7.0"
val finchVersion = "0.11.0-M2"
val circeVersion="0.4.1"

val specs2Version = "2.3.11"

lazy val finch = (project in file("finch")).
  enablePlugins(JavaAppPackaging).
  dependsOn(thrift).
  settings(commonSettings: _*).
  settings(
    name := "sandbox-finch",
    mainClass in assembly := Some("sandbox.finch.SandboxFinchBoot"),
    libraryDependencies ++= Seq(
      "com.twitter" %% "finagle-http" % finagleVersion,
      "com.twitter" %% "finagle-thriftmux" % finagleVersion,
      "com.twitter" %% "finagle-stats" % finagleVersion,
      "com.twitter" %% "twitter-server" % "1.20.0",
      "com.github.finagle" %% "finch-circe" % finchVersion,
      "io.circe" %% "circe-generic" % circeVersion,
      "org.specs2" %% "specs2" % specs2Version % "test"
    )
  )

lazy val thrift = (project in file("thrift")).
  settings(commonSettings: _*).
  settings(
    name := "sandbox-thrift",
    libraryDependencies ++= Seq(
      "org.apache.thrift" % "libthrift" % "0.8.0",
      "com.twitter" %% "scrooge-core" % scroogeVersion,
      "com.twitter" %% "finagle-thriftmux" % finagleVersion
    )
  )

lazy val root = (project in file(".")).
  enablePlugins(JavaAppPackaging).
  aggregate(finch)
