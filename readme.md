### Video Streaming 2 Demo App  under construction

for vlcj please see [vlcj](https://github.com/caprica/vlcj)  
based on [vlcj-javafx-demo](https://github.com/caprica/vlcj-javafx)  
see  [blog Java video streaming part 2](http://nsavagejvm.netlify.com/2017/01/java-video-streaming-part-2/) for details

### Set up

install vlc
install ffmpeg
this code works with a [golang terminal utility](https://github.com/nsavageJVM/v-stream-util)  that runs ffmpeg and manages transcode files
 
create video output directory and set up input video src and  paths for video output in config.properties 
see [config.properties](https://github.com/nsavageJVM/v-stream-util/blob/master/config.properties) for examples

set up input paths for transcoded  video files in runtime.properties 

#### Debian  
`./gradlew clean build -x test`  

`java -jar`  `-Dvs.properties=/home/USER_HOME_DIR/aJavaFXSpectrum/v-stream/runtime.properties` `build/libs/v-stream-0.0.1-SNAPSHOT.jar`

#### Debian set runtime.properties

install vlc  
app.video.native.dir=/usr/lib

#### Windows 7
`./gradlew clean build -x test`  
  
`java -jar`  `-Dvs.properties=C:\\Users\\USER_HOME_DIR\\v-stream-part-1\\runtime.properties`   `build/libs/v-stream-0.0.1-SNAPSHOT.jar`

#### Windows 7 set runtime.properties

install vlc then if 64 bit  
app.video.native.dir=C:\\Program Files\\VideoLAN\\VLC  
if 32 bit  
app.video.native.dir=C:\\Program Files(x86)\\VideoLAN\\VLC
