# tsx-java-14feb2023

# How run?

- Build the jar file first `` ./gradlew bootJar ``
- Now run the jar file `` java -jar build/libs/tsx-java-14feb2023-0.0.1-SNAPSHOT.jar ``
- Now run `` ngrok http 8080``

# How to test/use?

- Make a repo and go to it's settings.
- Now go to weebhook section.
- Click on add new webhook.
- Now put the ngrok url + /hook and select the content type to json.
- Now select " Let me select option" for events and select push,pr and merge group.
