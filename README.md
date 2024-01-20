# Advanced Task Manager v1.0


<div style="display: flex;	justify-content: center;	align-items: center;">
   <img src="https://github.com/SamanuelAdmin/AdvancedTaskManager/assets/68198268/207f87da-2aeb-46b9-937c-b65b63f485d5" style="height: 500px;">
   <img src="https://github.com/SamanuelAdmin/AdvancedTaskManager/assets/68198268/2b89cc56-458b-49d4-bef3-b1f5dba1e28d" style="height: 500px;">
</div>


#### Simple task manager with possibility of showing data on small 2.4'' tft lcd screen (via arduino). Now can show only CPU and RAM usage data 

____

### *Quickstart* (How to open Task Manager window?)
*For Linux and Windows.*

1. Clone the repository or just download.
2. Open project (*TaskManager* forder) in *IntelliJ IDEA* **win ROOT rights** or *other IDE* what you want.
   + If you are using IntelliJ IDEA, make sure the IDE opens project as **Maven**.
3. Run the project using `main` method in `Main.java` file.

If you get error like this
```java
java: cannot find symbol
  symbol:   class SystemInfo
  location: class com.taskmanager.SystemManager
```
just reload Oshi-core library inside the project. You can use this method:
1. Install all *dependencies*.
2. Add dependencies using ***File*** -> ***Project Structure*** -> ***Modules***. Press *"+"* button, then "*JARs or Directories...*" and choose all of three files.
3. Press ***Apply*** and ***Ok*** button.
4. Reopen task manager window.

____

### *How to assembly a screen?*

#### 1) At the first you need to get: 
   + [Arduino Uno](https://www.amazon.com/Electronics123-com-Inc-Arduino-Uno-R3/dp/B00HYRGJ2A/ref=sr_1_1?crid=1OJROUR17X1J0&keywords=arduino+uno&qid=1705775234&sprefix=arduino%2Caps%2C220&sr=8-1) (you can use [chinese copy of Arduino](https://www.aliexpress.com/item/1005004934125812.html?spm=a2g0o.productlist.main.13.4efec095eIChVp&algo_pvid=3082dddd-ba1b-4012-b0b9-c07088531008&aem_p4p_detail=202401201029249875984562747520002677278&algo_exp_id=3082dddd-ba1b-4012-b0b9-c07088531008-6&pdp_npi=4%40dis%21UAH%2144.34%2142.03%21%21%211.15%211.09%21%402116618817057753648407619e2116%2112000031070671178%21sea%21UA%210%21AB&curPageLogUid=SpST60563AHO&utparam-url=scene%3Asearch%7Cquery_from%3A&search_p4p_id=202401201029249875984562747520002677278_7))
   + [tft lcd 2.4'' screen shield for Arduino Uno](https://www.aliexpress.com/item/32919273566.html?spm=a2g0o.productlist.main.25.19445d51XApjIN&algo_pvid=8fa3dba4-44fe-4036-821b-c2c0af098502&algo_exp_id=8fa3dba4-44fe-4036-821b-c2c0af098502-12&pdp_npi=4%40dis%21UAH%21342.38%21191.63%21%21%218.88%214.97%21%402116617717057754539063194e13ad%2166113976794%21sea%21UA%210%21AB&curPageLogUid=Tc2WpXVJRC0e&utparam-url=scene%3Asearch%7Cquery_from%3A)
   + One USB wire (can be with arduino) to connect Arduino to your PC.

#### 2) Connect screen shield to arduino like a picture shows and arduino to USB port of your PC.
<img src="https://github.com/SamanuelAdmin/AdvancedTaskManager/assets/68198268/75b5a41e-2d61-463f-9d35-41781032bb66" style="height: 300px;">

#### 3) Download Arduino IDE (IDE for working with Arduino and other simmilar modules) from [official site]() and start it.

#### 4) Install all dependencies from *arduino dependencies*
  + Use ***Sketch*** -> ***Include Library*** -> ***Manage libraries*** and enter names of all libraries and download it one after another.
    [GUIDE HOW TO INSTALL LIBRARIES](https://www.digikey.com/en/maker/tutorials/2018/how-to-install-arduino-libraries)
  + It must looks like on this screenshot
    ![Screenshot_20240120_220208](https://github.com/SamanuelAdmin/AdvancedTaskManager/assets/68198268/7a012f28-81fc-4cfa-8543-3b853e3401a3)


#### 5) I can recommend you to test your Arduino and screen before uploading main firmware. You can do it with example skatches (***File*** -> ***Examples***)

#### 6) Next you need to upload code (from ``arduino code`` folder) to your Arduino using [this guide](https://support.arduino.cc/hc/en-us/articles/4733418441116-Upload-a-sketch-in-Arduino-IDE).

***Now you can connect arduino to your computer via USB and enjoy!***

___

## Dependencies

***For main task manager programm***
+ JDK >= 21.0.2
+ [Flatlaf](https://search.maven.org/artifact/com.formdev/flatlaf/3.3/jar?eh=) >= 3.3
+ [JSerialComm](https://oss.sonatype.org/service/local/repositories/releases/content/com/fazecast/jSerialComm/2.10.4/jSerialComm-2.10.4.jar) >= 1.3.11
+ [Oshi-core](https://search.maven.org/artifact/com.github.oshi/oshi-dist/6.4.11/pom) >= 6.4.11

***For arduino firmware***
+ Arduino_JSON >= 0.2.0
+ Adafruit BusIO >= 1.15.0
+ Adafruit GFX Library >= 1.11.9
+ SPFD5408 TFT Library >= 1.1.0
