
## Distributed Systems Assignments of SPPU Final Year IT Syllabus (2019 pattern)


|     Assignment No.      | Problem Statement  |
|:-----------------------:| :-------------- |
| [Assignment 1](Assignment_1) | Implement multi-threaded client/server Process communication using RMI.| 
| [Assignment 2](Assignment_2) | Develop distributed application using CORBA to demonstrate object brokering (Calculator or String operations).|
| [Assignment 3](Assignment_3) | Develop a distributed system, to find sum of N elements in an array by distributing N/n elements to n number of processors MPI or OpenMP. Demonstrate by displaying the intermediate sums calculated at different processors. |
| [Assignment 4](Assignment_4) | Implement Berkeley algorithm for clock synchronization.|
| [Assignment 5](Assignment_5) | Implement token ring based mutual exclusion algorithm.|
| [Assignment 6](Assignment_6) | Implement Bully and Ring algorithm for leader election.|
| [Assignment 7](Assignment_7) | Create a simple web service and write distributed application(calculator) to consume the Web Service.|
## Execution Steps

### Pre-requisites:

1. Install JDK-8

        sudo apt-get remove openjdk*
        sudo apt update
        sudo apt install openjdk-8-jdk openjdk-8-jre

2. Download [MPJ Express](https://sourceforge.net/projects/mpjexpress/files/releases/mpj-v0_44.tar.gz/download) and extract in the Downloads dir

3. Install Apache Netbeans

        sudo apt update && sudo apt upgrade
        sudo snap install netbeans --classic
   Glassfish server version must be 4.1.1


### Assignment 1:

Terminal 1:

    javac *.java
    rmic AdditionServerImplemnetation

Terminal 2:

    rmiregistry

Terminal 3:

    java AdditionServer

Terminal 4:

    java AdditionServiceClient 127.0.0.1 5 8

### Assignment 2:

For String Reverse Assignment :

Terminal 1:

    idlj -fall Reverse.idl
    javac *.java ReverseModule/*.java
    orbd -ORBInitialPort 1056&
    java Server -ORBInitialPort 1056& 

Terminal 2:

    java ReverseClient -ORBInitialPort 1056

For Calculator Assignment :

Terminal 1:

    idlj -fall Calculator.idl
    javac *.java CalculatorModule/*.java
    orbd -ORBInitialPort 1056&
    java Server -ORBInitialPort 1056& 

Terminal 2:

    java CalculatorClient -ORBInitialPort 1056


Situational:
    If you keep on getting, address already in use, then kill the process using the following command:
    
    killall -9 orbd

### Assignment 3:

Terminal:

    echo 'export MPJ_HOME=/home/ubuntu/Downloads/mpj-v0_44' >> ~/.bash_profile
    echo 'export PATH=$MPJ_HOME/bin:$PATH' >> ~/.bash_profile
    source ~/.bash_profile
    javac -cp $MPJ_HOME/lib/mpj.jar DistributedArraySum.java
    $MPJ_HOME/bin/mpjrun.sh -np 4 DistributedArraySum

### Assignment 4:

Terminal 1:

    javac *.java
    java MasterNode 3s

Terminal 2:

    java SlaveNode 25 &
    java SlaveNode 12 &
    java SlaveNode -10

### Assignment 5:

Terminal

    javac TokenRing.java
    java TokenRing

### Assignment 6:

Terminal

    javac BullyAlgorithm.java
    java BullyAlgorithm
    javac RingAlgorithm.java
    java RingAlgorithm

### Assignment 7:

[Youtube tutorial](https://www.youtube.com/watch?v=0z-HvSfr-M4)
    
    
