# ♕ BYU CS 240 Chess

This project demonstrates mastery of proper software design, client/server architecture, networking using HTTP and WebSocket, database persistence, unit testing, serialization, and security.

## 10k Architecture Overview

The application implements a multiplayer chess server and a command line chess client.

[![Sequence Diagram](10k-architecture.png)](https://sequencediagram.org/index.html#initialData=C4S2BsFMAIGEAtIGckCh0AcCGAnUBjEbAO2DnBElIEZVs8RCSzYKrgAmO3AorU6AGVIOAG4jUAEyzAsAIyxIYAERnzFkdKgrFIuaKlaUa0ALQA+ISPE4AXNABWAexDFoAcywBbTcLEizS1VZBSVbbVc9HGgnADNYiN19QzZSDkCrfztHFzdPH1Q-Gwzg9TDEqJj4iuSjdmoMopF7LywAaxgvJ3FC6wCLaFLQyHCdSriEseSm6NMBurT7AFcMaWAYOSdcSRTjTka+7NaO6C6emZK1YdHI-Qma6N6ss3nU4Gpl1ZkNrZwdhfeByy9hwyBA7mIT2KAyGGhuSWi9wuc0sAI49nyMG6ElQQA)

## Modules

The application has three modules.

- **Client**: The command line program used to play a game of chess over the network.
- **Server**: The command line program that listens for network requests from the client and manages users and games.
- **Shared**: Code that is used by both the client and the server. This includes the rules of chess and tracking the state of a game.

## Starter Code

As you create your chess application you will move through specific phases of development. This starts with implementing the moves of chess and finishes with sending game moves over the network between your client and server. You will start each phase by copying course provided [starter-code](starter-code/) for that phase into the source code of the project. Do not copy a phases' starter code before you are ready to begin work on that phase.

## IntelliJ Support

Open the project directory in IntelliJ in order to develop, run, and debug your code using an IDE.

## Maven Support

You can use the following commands to build, test, package, and run your code.

| Command                    | Description                                     |
| -------------------------- | ----------------------------------------------- |
| `mvn compile`              | Builds the code                                 |
| `mvn package`              | Run the tests and build an Uber jar file        |
| `mvn package -DskipTests`  | Build an Uber jar file                          |
| `mvn install`              | Installs the packages into the local repository |
| `mvn test`                 | Run all the tests                               |
| `mvn -pl shared test`      | Run all the shared tests                        |
| `mvn -pl client exec:java` | Build and run the client `Main`                 |
| `mvn -pl server exec:java` | Build and run the server `Main`                 |

These commands are configured by the `pom.xml` (Project Object Model) files. There is a POM file in the root of the project, and one in each of the modules. The root POM defines any global dependencies and references the module POM files.

## Running the program using Java

Once you have compiled your project into an uber jar, you can execute it with the following command.

```sh
java -jar client/target/client-jar-with-dependencies.jar

♕ 240 Chess Client: chess.ChessPiece@7852e922
```

A link to the Sequence Diagram is here:

https://sequencediagram.org/index.html?presentationMode=readOnly#initialData=IYYwLg9gTgBAwgGwJYFMB2YBQAHYUxIhK4YwDKKUAbpTngUSWDABLBoAmCtu+hx7ZhWqEUdPo0EwAIsDDAAgiBAoAzqswc5wAEbBVKGBx2ZM6MFACeq3ETQBzGAAYAdAFZM9qBACu2GADEaMBUljAASij2SKoWckgQaIEA7gAWSGBiiKikALQAfOSUNFAAXDAA2gAKAPJkACoAujAA9D4GUAA6aADeAETtlMEAtih9pX0wfQA0U7jqydAc45MzUyjDwEgIK1MAvpjCJTAFrOxclOX9g1AjYxNTs33zqotQyw9rfRtbO58HbE43FgpyOonKUCiMUyUAAFJForFKJEAI4+NRgACUh2KohOhVk8iUKnU5XsKDAAFUOrCbndsYTFMo1Kp8UYdKUAGJITgwamURkwHRhOnAUaYRnElknUG4lTlNA+BAIHEiFRsyXM0kgSFyFD8uE3RkM7RS9Rs4ylBQcDh8jqM1VUPGnTUk1SlHUoPUKHxgVKw4C+1LGiWmrWs06W622n1+h1g9W5U6Ai5lCJQpFQSKqJVYFPAmWFI6XGDXDp3SblVZPQN++oQADW6ErU32jsohfgyHM5QATE4nN0y0MxWMYFXHlNa6l6020C3Vgd0BxTF5fP4AtB2OSYAAZCDRJIBNIZLLdvJF4ol6p1JqtAzqBJoIdTUWjFZfF5vD59SYHYsgoU+Ylj0r7lqOH5PF+SwfjAALnAWspqig5QIAePKwvuh6ouisTYgmhgumGbpkhSBq0uBowmkS4YWhyMDcrysbBtoQoipRYiutKSaXsh5TMfGcooDxZxAiWWE8tmuaYMBIJISUVwDBxC6Tn006zs246tn0-5XnJhTZD2MD9oOvRKSO75adWU5Bhp85WW2y6rt4fiBF4KDoHuB6+Mwx7pJkmCGReRTUNe0gAKK7uF9Thc0LQPqoT7dAAkNO0BIAAXvEiTlAAPOpjboPk7aAaJqblKlQbpVlBA5TA+W2YVaDFbJ8ngjAaH2D5mHeb6OEYvhQkasRLKkWAzEBo1c7UUybp0eUjExkGgrCjABVzo6og8bJpRYT5UkIHmCEdiJAGlD0umhVAIlBWAfYDkOS6cM566BJCtq7tCMAAOKjqyfmnoF57MPJ17fdFcX2KO3RpVAmXZWgeXrUVJVsjta1VXDNVPkjU0o7JbIEah0K-aMqiYSTo79Xhm0qAUXGkjA5LjUGk11k1M1mqoBSWotMACaxq3I2goY0W6PFEz9f2fbEtOEcmx1pjLYCkyg2bYIkBgyYrnZnaBfRQ2T4yVP0hsoAAktIxsAIy9gAzAALE8J6ZAaFYTF8OgIKADZuxBHtPGbAByo4e3sMCNJdxw3cD92mfrZuqMbFSm6Ols2-bTtTC7+rKQHUxez7fvvvnBujiHoxhxHT0rp4LkbtgPhQNg3DwLqmRS6MKT+WeOQg7xCmVLUDSQ9DaCVX61UI7j7NzvkL5l6MFf3L+cGowrYllLD8O1Yj9XC-PvRTMH-ur-Bm+E0J5SenqquwnA7coKrqIoO0WJy-TI2M8zE3C5z4Y83onzAW8g2IY1nugUWs0WSAP4jaTuKAHQM25gUSWZtLZy07OjB+XpMjPzUBrNAWtWqoL0lcY+acrblFto7NeAEeK3VKCZBe6CqEwBoQ7NeTk66vQCJYFAyoIDJBgAAKQgDyBBgRC4gAbEDPul8rrlGqJSO8LQzYw0xjvHG+88bNQXi3YA-CoBwAgGhKAKwADqLBzYxRaAAIV3AoOAABpL4rCM60KjpQAo6Nt7Yzqg1CBeij7PG9kYkxZjLHWNsQ4pxrjA6UI8Zw8+qZSF8RgAAK3EWgO+YieT4LRAND++RkFjV-ro-+c1YEMR5EtOMgswjCygVzeaNTeSqxWmEVhmCRLozyTkqmBDNZiBIfkPWXjrr5EYcZB6vQa4vVcgELwhiuxelgMAbALdCAI27oDW6CjB4VAilFGKcVjAlR8Yrco2sL5tXlDAEA3A8CMj0AYe+TyoAvP0GrFAhSaYEU-mLUazSAH5EtNIARFJDDAGVHaAU2hpggOANMDp2gQXizSU6eUH9Lmb1KIgNZXyDDq2GTc1JmLLgXQuQURhzC5lmGekAA
