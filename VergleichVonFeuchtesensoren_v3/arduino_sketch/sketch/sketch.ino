#include "SD.h"
#include <Wire.h>
#include "Adafruit_HTU21DF.h"
#include "Adafruit_SHT31.h"
#include <Adafruit_BME280.h>
#include <Sensirion.h>
#include "DHT.h"
#include "FS.h"
#include "SPI.h"
#include "TimeLib.h"

#define SDA_SHT75 32
#define SCL_SHT75 33

#define SDA_HTU21 25
#define SCL_HTU21 26

#define SDA_SHT31 13
#define SCL_SHT31 14

#define SDA_BME280 21
#define SCL_BME280 22

#define SDA_DHT22 4
#define DHTTYPE DHT22

#define NUM_MEASUREMENTS 10
#define NUM_PARAMETERS 15 //Temperatur, Feuchtigkeit, Zeit wann gemessen wurde * fünf Sensoren 3*5 = 15
#define DELAY_TIME 500
#define STRING_LENGTH 300


Sensirion sht75 = Sensirion(SDA_SHT75, SCL_SHT75);
float temperature_sht75;
float humidity_sht75;
float dewpoint_sht75;

Adafruit_HTU21DF htu21 = Adafruit_HTU21DF();

Adafruit_SHT31 sht31 = Adafruit_SHT31();

Adafruit_BME280 bme280 = Adafruit_BME280();
TwoWire wire_bme280 = TwoWire(5);

DHT dht22(SDA_DHT22, DHTTYPE);

/*measurements[x][y]:
  x ist i-te Messung
  y=0 Temp.   SHT75
  y=1 Feucht. SHT75
  y=2 die seit dem Einschalten des Mikrokontrollers vergangene Zeit in Millisekunden     SHT75
  y=3 Temp.   HTU21
  y=4 Feucht. HTU21
  y=5 die seit dem Einschalten des Mikrokontrollers vergangene Zeit in Millisekunden     HTU21 
  y=6 Temp.   SHT31
  y=7 Feucht. SHT31
  y=8 die seit dem Einschalten des Mikrokontrollers vergangene Zeit in Millisekunden     SHT31
  y=9 Temp.   BME280
  y=10 Feucht.BME280
  y=11 die seit dem Einschalten des Mikrokontrollers vergangene Zeit in Millisekunden    BME280
  y=12 Temp.  DHT22
  y=13 Feucht.DHT22
  y=14 die seit dem Einschalten des Mikrokontrollers vergangene Zeit in Millisekunden    DHT22
*/
float measurements[NUM_MEASUREMENTS][NUM_PARAMETERS];

int counter = 0;
int totalMeasurementCounter = 0;
char fileName[32];


void doMeasurement() {
  
  sht75.measure(&measurements[counter][0], &measurements[counter][1], &dewpoint_sht75);
  measurements[counter][2] = (float)millis();
  
  htu21.begin(SDA_HTU21, SCL_HTU21);
  measurements[counter][3] = htu21.readTemperature();
  measurements[counter][4] = htu21.readHumidity();
  measurements[counter][5] =  (float)millis();
  
  sht31.begin(SDA_SHT31, SCL_SHT31);
  measurements[counter][6] = sht31.readTemperature();
  measurements[counter][7] = sht31.readHumidity();
  measurements[counter][8] =  (float)millis();

  measurements[counter][9] = bme280.readTemperature();
  measurements[counter][10] = bme280.readHumidity();
  measurements[counter][11] = (float)millis();

  measurements[counter][12] = dht22.readTemperature();
  measurements[counter][13] = dht22.readHumidity();
  measurements[counter][14] =  (float)millis();
  
}

void printLastMeasurement() {
  
  Serial.print("SHT75 Temp: "); Serial.print(measurements[counter][0]);
  Serial.print("; Hum: "); Serial.print(measurements[counter][1]);
  Serial.print("; Time: "); Serial.println(measurements[counter][2]);
  
  Serial.print("HTU21DF Temp: "); Serial.print(measurements[counter][3]);
  Serial.print("; Hum: "); Serial.print(measurements[counter][4]);
  Serial.print("; Time: "); Serial.println(measurements[counter][5]);
  
  Serial.print("SHT31 Temp: "); Serial.print(measurements[counter][6]);
  Serial.print("; Hum: "); Serial.print(measurements[counter][7]);
  Serial.print("; Time: "); Serial.println(measurements[counter][8]);
  
  Serial.print("BME280 Temp: "); Serial.print(measurements[counter][9]);
  Serial.print("; Hum: "); Serial.print(measurements[counter][10]);
  Serial.print("; Time: "); Serial.println(measurements[counter][11]);

  Serial.print("DHT22 Temp: "); Serial.print(measurements[counter][12]);
  Serial.print("; Hum: "); Serial.print(measurements[counter][13]);
  Serial.print("; Time: "); Serial.println(measurements[counter][14]);
  Serial.println("");

}

void writeMeasurementsOnSdCard() {
  char buffer[NUM_MEASUREMENTS][STRING_LENGTH];
  for(int i = 0; i < NUM_MEASUREMENTS; i++) {
    String measurement = String(totalMeasurementCounter) + ";" + String(measurements[i][0]) + ";" + String(measurements[i][1]) + ";" + String(measurements[i][2])
                   + ";" + String(measurements[i][3]) + ";" + String(measurements[i][4]) + ";" + String(measurements[i][5])
                   + ";" + String(measurements[i][6]) + ";" + String(measurements[i][7]) + ";" + String(measurements[i][8])
                   + ";" + String(measurements[i][9]) + ";" + String(measurements[i][10]) + ";" + String(measurements[i][11])
                   + ";" + String(measurements[i][12]) + ";" + String(measurements[i][13]) + ";" + String(measurements[i][14]); 
                 
    
    measurement.toCharArray(buffer[i],STRING_LENGTH);
    totalMeasurementCounter++;
  }
  appendFile(SD, fileName, buffer);
}



void createFileName(fs::FS &fs) {
  String fileNameStr = "/measurements_";
  char fileNameChar[32];
  int numOfFiles = 0;
  File dir = fs.open("/");
  while(dir.openNextFile()) {
    numOfFiles++;
  }
  fileNameStr = fileNameStr + String(numOfFiles) + ".txt";
  fileNameStr.toCharArray(fileName,32);
  Serial.print("Created name: ");
  Serial.println(fileName);
}

void writeFile(fs::FS &fs, const char * message){
    createFileName(fs);
    Serial.printf("Writing file: %s\n", fileName);
    File file = fs.open(fileName, FILE_WRITE);
    if(!file){
        Serial.println("Failed to open file for writing");
        return;
    }
    if(file.print(message)){
        Serial.println("File written");
    } else {
        Serial.println("Write failed");
    }
    file.close();
}

void appendFile(fs::FS &fs, const char * path, char messages[NUM_MEASUREMENTS][STRING_LENGTH]){
    Serial.printf("Appending to file: %s\n", path);

    File file = fs.open(path, FILE_APPEND);
    if(!file){
        Serial.println("Failed to open file for appending");
        return;
    }
    for(int i = 0; i < NUM_MEASUREMENTS; i++) {
      if(file.println(messages[i])){
          Serial.println("Message appended");
      } else {
          Serial.println("Append failed");
      }
    }

    file.close();
}


void setup() {
  Serial.begin(9600);
  Serial.println("Beginning");
  
  dht22.begin();
  
  wire_bme280.begin(SDA_BME280,SCL_BME280);
  bme280.begin(&wire_bme280); 

  if(!SD.begin()){
      Serial.println("Card Mount Failed");
      return;
  }
  uint8_t cardType = SD.cardType();

  if(cardType == CARD_NONE){
      Serial.println("No SD card attached");
      return;
  }

  Serial.print("SD Card Type: ");
  if(cardType == CARD_MMC){
      Serial.println("MMC");
  } else if(cardType == CARD_SD){
      Serial.println("SDSC");
  } else if(cardType == CARD_SDHC){
      Serial.println("SDHC");
  } else {
      Serial.println("UNKNOWN");
  }

  uint64_t cardSize = SD.cardSize() / (1024 * 1024);
  Serial.printf("SD Card Size: %lluMB\n", cardSize);

  Serial.printf("Total space: %lluMB\n", SD.totalBytes() / (1024 * 1024));
  Serial.printf("Used space: %lluMB\n", SD.usedBytes() / (1024 * 1024));
  writeFile(SD, "Meas_numb;SHT75_temp;SHT75_hum;SHT75_time;HTU21DF_temp;HTU21DF_hum;HTU21DF_time;SHT31_temp;SHT31_hum;SHT31_time;BME280_temp;BME280_hum;BME280_time;DHT22_temp;DHT22_hum;DHT22_time\n");

}


void loop() {
  if(counter == NUM_MEASUREMENTS) {
    writeMeasurementsOnSdCard();
    counter = 0;
  }
  doMeasurement();
  printLastMeasurement();
  counter++;
  delay(DELAY_TIME);
}
