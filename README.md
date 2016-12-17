# GroupTracker-Serv Documentation

## Requirements:
[Java sdk/jdk](http://www.oracle.com/technetwork/java/javase/downloads/index.html)

[Netbeans IDE](https://netbeans.org/downloads/) - I suggest the "Java EE" to be sure no errors occur

[pircbot jar file](http://www.jibble.org/pircbot.php)

## Setup:

-install java jdk then netbeans

-in netbeans, open the project

-go to tools->options->java->JavaScript and confirm it says JDK in the drop down

-under the project grouptracker, select Libraries then drag the pircbot jar into it

## Style Guide:

-DO NOT change the name of any of the files, variables, classes, or even the project name. this can break gt. You should ONLY change stuff in “quotation” marks unless you know what you are doing. 

-new lines must end with a ;

## Building/Deploying:

to deploy, right-click on grouptracker project itself and click build. there should now be a GroupTracker.jar in the projects dist folder. upload that to a web server that supports direct links. now go to the grouptrackerupdate’s updater.java and change the dropbox link on line 16 to the download link of grouptracker.jar.

NOTE: the link does not need to be changed if you drag GroupTracker.jar into dropbox while another GroupTracker.jar was in it.

## Running and exiting the bot:

-ubuntu/linux:

run:  java -jar “GroupTrackerUpdate.jar”

exit: pkill java

-windows: 

run: double click on grouptrackerupdate.jar 

exit: kill java through task manager

## GroupTrackerUpdate source

I did not feel this needed a repository, as all it does is run the bot. The source code can be found [here](https://www.dropbox.com/s/mzlyeroq0zj9a50/GroupTrackerUpdate.zip?dl=0). Please update the link (with a PR including changes) if changes are made.
