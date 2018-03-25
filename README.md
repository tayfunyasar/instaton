# instaton

Steps to run instaton

1-)
```
./gradlew cleanEclipse eclipse assemble build
cd docker
./run.sh
```

2-)
```
cd src/main/javascript
npm i
gulp
```

3-)
Run SpringBoot main class.

It will be starting serve application with in this url:
```
http://localhost:5000/
```

## TODOs

### Architecture
* Print instagram request logs

### Instagram
* Save InstagramFeedItem for each request
* Save credentials into db
* List private messages & send DMs using instagram4j
* Find a colourful instagram messaging template like facebook.com/messages
* Add custom inputs like notes, phone number, free text save for each user
* Configure auto reply templates if user sends spesific keywords

Happy coding.