// (c) mddbook, wasowski, tberger

lazy val root = (project in file(".")).settings (

  organization := "mdsebook",
  name := "Scala, EMF, #noEclipse",
  version := "0.02",
  // stick to Scala version supported by Scala IDE 
  scalaVersion := "2.10.6",

  scalacOptions += "-deprecation",
  scalacOptions += "-feature",
  scalacOptions += "-language:implicitConversions",

  // include to the code generated by Eclipse in a separate EMF project
  unmanagedSourceDirectories in Compile += file(baseDirectory.value.getParent) / "mdsebook.fsm/src/",
  // include the EMFScala "framework" (there is probably a better way to do it, to avoid rebuilding)
  unmanagedSourceDirectories in Compile += file(baseDirectory.value.getParent) / "mdsebook.scala/src/main/scala",

  libraryDependencies += "org.eclipse.emf" % "org.eclipse.emf.ecore" % "2.11.+",
  libraryDependencies += "org.eclipse.emf" % "org.eclipse.emf.ecore.xmi" % "2.11.+",
  libraryDependencies += "org.eclipse.emf" % "org.eclipse.emf.common" % "2.11.+",
  libraryDependencies += "org.bitbucket.inkytonik.kiama" %% "kiama" % "2.0.0",
  libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.6" % "test"

)
