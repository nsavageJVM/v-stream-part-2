### Video Streaming 2 Demo App  

for vlcj please see [vlcj](https://github.com/caprica/vlcj)  
based on [vlcj-javafx-demo](https://github.com/caprica/vlcj-javafx)  
see  [blog Java video streaming part 2](http://nsavagejvm.netlify.com/2017/04/java-video-streaming-part-2/) for details

### Set up

install vlc  
install ffmpeg  
this code works with a [golang terminal utility](https://github.com/nsavageJVM/v-stream-util)  that runs ffmpeg and manages transcode files  
set up [golang terminal utility](https://github.com/nsavageJVM/v-stream-util) from the readme  
set the input paths for rendering in runtime.properties see [setup section](http://nsavagejvm.netlify.com/2017/04/java-video-streaming-part-2/) for details

### Build and Run

#### Debian  
`./gradlew clean build -x test`  



#### Debian set runtime.properties

install vlc  
app.video.native.dir=/usr/lib

#### Windows 7
`./gradlew clean build -x test`  
  


#### Windows 7 set runtime.properties

install vlc then if 64 bit  
app.video.native.dir=C:\\Program Files\\VideoLAN\\VLC  
if 32 bit  
app.video.native.dir=C:\\Program Files(x86)\\VideoLAN\\VLC


#### Run
begin the transcode to file using the [golang terminal utility](https://github.com/nsavageJVM/v-stream-util)

##### Debian
`java -jar`  `-Dvs.properties=/home/USER_HOME_DIR/{path to}/runtime.properties` `build/libs/v-stream-0.0.1-SNAPSHOT.jar`
#### Windows
`java -jar`  `-Dvs.properties=C:\\Users\\USER_HOME_DIR\\{path to}\\runtime.properties`   `build/libs/v-stream-0.0.1-SNAPSHOT.jar`


![Screenshot demo](https://github.com/nsavageJVM/v-stream-part-2/blob/master/demo2.png)
