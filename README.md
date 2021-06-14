# Krishna-assistant

A digital assistant based on Java swing. It takes voice input, performs tasks and gives replays.

### Introduction

Krishna assistant is a digital assistant (Chatbot) developed in java language. It recognizes the user 's voice using google cloud speech recognition. Then as per user command it gives a reply using google cloud speech synthesizer and also performs a given task. It is somewhat similar to google assistant but it does not use any Artificial Intelligence. This program takes command from the user, search it in it’s database, Then performs  tasks and replies which are linked to that command. This project also has a GUI (Development mode) for interacting with database to manage data of assistant.

### How to use it

This software contains two modes 1. Assistant mode 2. Development mode.

 Assistant mode is a main part of this software which accepts user commands and replies. Development mode is using to manipulate database of assistants



#### 1. Assistant Mode:

To run this project without opening an IDE. Just open ** Krishna_Assistent.jar ** in dist folder. Or execute following line in the terminal.

java -jar "Krishna_Assistent.jar" 

It will look like this:

 ![Assistant Image](/Screenshots/a1.png)
![Assistant Image](/Screenshots/a2.png)

 

Note: You can also give commands by keyboard. Turn off mic and start typing by keyboard and hit enter.  

##### Common commands to try:



*   hi
*   hello
*   introduce yourself
*   open this pc (Open file explorer)
*   open chrome (Open chrome browser)
*   shutdown pc (Shutdown your computer)
*   open facebook (open facebook website in default browser)
*   open website youtube (open youtube website in default browser)
*   tell me a joke
#### 2.  Development mode() :

To open Development mode goto dist folder and open Development mode shortcut (Only for windows user). Or execute following line in terminal.java -jar "Krishna_Assistent.jar" "devmode"This will look like this:



### Credits :



1. For voice recognition and  speech synthesis this project uses google cloud api created by [GOXR3PLUS STUDIO](https://github.com/goxr3plus)

    Link for Github repo : [https://github.com/goxr3plus/java-google-speech-api](https://github.com/goxr3plus/java-google-speech-api)
