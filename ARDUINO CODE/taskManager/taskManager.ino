#include <Adafruit_GFX.h>    // Core graphics library
#include <TftSpfd5408.h> // Hardware-specific library
#include <Arduino_JSON.h>  // For parse JSON strings
#include <assert.h>

#define LCD_CS A3 // Chip Select goes to Analog 3
#define LCD_CD A2 // Command/Data goes to Analog 2
#define LCD_WR A1 // LCD Write goes to Analog 1
#define LCD_RD A0 // LCD Read goes to Analog 0

#define LCD_RESET A4 // Can alternately just connect to Arduino's reset pin

TftSpfd5408 tft(LCD_CS, LCD_CD, LCD_WR, LCD_RD, LCD_RESET);

#define BLACK   0x0000
#define BLUE    0x001F
#define RED     0xF800
#define GREEN   0x07E0
#define CYAN    0x07FF
#define MAGENTA 0xF81F
#define YELLOW  0xFFE0
#define WHITE   0xFFFF
#define PURPLE  0x8888

int cpuInfo = 100;
int ramInfo = 100;


void updateInfo() {
  tft.fillScreen(BLACK);
  tft.setTextSize(7);

  // CPU info update
  tft.setCursor(13, 33); // make label in center of a row
  tft.setTextColor(GREEN);
  tft.print("CPU ");
  tft.setCursor((cpuInfo == 100) ? 150 : 160, 33);  // "dynamic" borders
  tft.print((String) cpuInfo + "%");

  // RAM info update
  tft.setCursor(13, 163); // make label in center of a row
  tft.setTextColor(BLUE);
  tft.print("RAM ");
  tft.setCursor((ramInfo == 100) ? 150 : 160, 163); // "dynamic" borders
  tft.print((String) ramInfo + "%");
}

void setup() {
  Serial.begin(9600);
  Serial.println("Start");

  // tft display init
  tft.reset();
  tft.begin(0x9341);
  tft.setRotation(3);

  // init and turn on lighter
  pinMode(13, OUTPUT);
  digitalWrite(13, HIGH);

  updateInfo();
}

void loop() { 
  // example of inputed string: {"cpuLoadData": 100, "ramLoadData": 90}
  String inputString = "";

  while (true) {
    if (Serial.available()) {
      char gottenChar = (char) Serial.read();

      if (gottenChar == '\n') break; // if it is the end of a json
      else inputString += gottenChar;
    }

  }

  if (inputString != "") {
    JSONVar gottenData = JSON.parse(inputString);

    if (gottenData.hasOwnProperty("cpuLoadData")) {
       cpuInfo = (int) gottenData["cpuLoadData"];
       Serial.println("Updated value of \"cpuInfo\": " + (String) cpuInfo);
    } 
    
    if (gottenData.hasOwnProperty("ramLoadData")) {
      ramInfo = (int) gottenData["ramLoadData"];
       Serial.println("Updated value of \"ramInfo\": " + (String) ramInfo);
    }

    updateInfo(); // redrawing labels on the screen
  }
}
