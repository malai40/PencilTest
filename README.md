# PencilTest
Program to compile drawings into a movie to preview hand-drawn animations, i.e. a "pencil test".

Title: Pencil Test v.0.1.0
 
Author: malai40
 
License: GNU GENERAL PUBLIC LICENSE V3
 
Description:
Pencil Test is a program that compiles drawings into an animated movie to 
preview an animation, known as a pencil test. 
 	
The program is still in development, and as such should not yet be considered stable. Make backups of any input images before using this program.
 	
This program is intended for previewing animations before re-creating them in more robust video editing programs for final products. Results when using videos created with this program as final movies cannot be guaranteed.
 
This program does not currently support sound. The expectation is that the movie file created will be loaded in to a more robust video editing program if it needs to be paired with sound. 

Future versions of this program may include sound capability on operating systems for which the Java Media Framework (JMF) supports sound, such as Windows and Solaris SPARC.
 
This program does not, and will probably never, support sound on operating systems for which JMF does not support sound, such as Linux, macOS, and NetBeans. 
 
 Pencil Test Installation Instructions
 1) Clone repo to a local folder
 2) Import library to Java IDE of choice
 3) Install Java Media Framework (JMF)
 4) In IDE, link jmf.jar in JMF to library
 5) Open and run Main.java in library
 6) Follow the prompts that appear in the console.
 
 Notes: Installing Java Media Framework (JMF)
 To use this code, the host machine must have JMF installed. 
 The cross-platform version of JMF works on any client that can run Java. 
 To install this version, link jmf.jar from the JMFAll cross-platform library.
 Further optimized versions should be installed instead when possible, as follows:
 1) For Windows, link jmf.jar from the JMF with Windows Performance Pack (executable installation).
 2) For Solaris SPARC, link jmf.jar from the JMF with Solaris Performance Pack.
 3) For Linux, link jmf.jar from the JMF with Linux Performance Pack.
 To download JMF: https://www.oracle.com/java/technologies/javase/jmf-211e-download.html
 
Version History:
 v0.1.0 - Initial release on 2022-02-02. Pre-alpha.
 
 Credits:
 1) "JpegImagesToMovie" class, Sun Microsystems Inc., 1999-2001, v1.3 released on 01/03/13.
 2) "Java Media Framework" API, Oracle, v2.1.
