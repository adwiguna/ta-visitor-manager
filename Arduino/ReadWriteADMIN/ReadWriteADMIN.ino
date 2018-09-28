#include <SPI.h>
#include <MFRC522.h>

constexpr uint8_t RST_PIN = 9;     // Configurable, see typical pin layout above
constexpr uint8_t SS_PIN = 10;     // Configurable, see typical pin layout above

MFRC522 rfid(SS_PIN, RST_PIN); // Instance of the class
MFRC522 mfrc522(SS_PIN, RST_PIN);   // Create MFRC522 instance

MFRC522::MIFARE_Key key; 
// Init array that will store new NUID 
byte nuidPICC[4];
String dataString;
boolean done = false;

void setup() {
  // initialize serial:
  Serial.begin(9600);
  SPI.begin(); // Init SPI bus
  mfrc522.PCD_Init();
  rfid.PCD_Init(); // Init MFRC522
  
  for (byte i = 0; i < 6; i++) {
    key.keyByte[i] = 0xFF;
  }
}

void loop() {
  if(Serial.available()){
    char menu = (char)Serial.read();
    
    //menu untuk membaca NUID
    if(menu == '0'){
      while(!done){
        readNUID();
      }
      done = false;
    }

    //menu untuk menulis hak akses ke kartu
    else if(menu == '1'){
      while(!done){
        writeData();
      }
      done = false;
    }

    //menu untuk membaca data
    else if(menu == '2'){
      while(!done){
        readData();
      }
      done = false;
    }

    //menu untuk aktif/nonaktifkan kartu    
    else if(menu == '3'){
      
    }
    
    else
      return;
  }
}

void readData(){
  // Look for new cards
  if ( ! mfrc522.PICC_IsNewCardPresent()) {
    return;
  }
  // Select one of the cards
  if ( ! mfrc522.PICC_ReadCardSerial()) {
    return;
  }

//  mfrc522.PICC_DumpDetailsToSerial(&(mfrc522.uid)); //dump some details about the card
//  mfrc522.PICC_DumpToSerial(&(mfrc522.uid));      //uncomment this to see all blocks in hex

  byte buffer1[18];  
  byte len = 18;
  MFRC522::StatusCode status;


  status = mfrc522.PCD_Authenticate(MFRC522::PICC_CMD_MF_AUTH_KEY_A, 4, &key, &(mfrc522.uid)); //line 834 of MFRC522.cpp file
  if (status != MFRC522::STATUS_OK) {
    return;
  }
  byte block = 4;
  status = mfrc522.MIFARE_Read(block, buffer1, &len);
  if (status != MFRC522::STATUS_OK) {
    return;
  }
  //PRINT DATA
  for (uint8_t i = 0; i < 16; i++)
  {
    if (buffer1[i] != 32)
    {
      Serial.write(buffer1[i]);
    }
  }  

  block = 5;
  status = mfrc522.MIFARE_Read(block, buffer1, &len);
  if (status != MFRC522::STATUS_OK) {
    return;
  }
  //PRINT DATA
  for (uint8_t i = 0; i < 16; i++)
  {
    if (buffer1[i] != 32)
    {
      Serial.write(buffer1[i]);
    }
  }  

  block = 6;
  status = mfrc522.MIFARE_Read(block, buffer1, &len);
  if (status != MFRC522::STATUS_OK) {
    return;
  }
  //PRINT DATA
  for (uint8_t i = 0; i < 16; i++)
  {
    if (buffer1[i] != 32)
    {
      Serial.write(buffer1[i]);
    }
  }  
  
  mfrc522.PICC_HaltA();
  mfrc522.PCD_StopCrypto1();
  done = true;
}

void writeData(){
  // Look for new cards
  if ( ! mfrc522.PICC_IsNewCardPresent()) {
    return;
  }
  // Select one of the cards
  if ( ! mfrc522.PICC_ReadCardSerial()) {
    return;
  }

  MFRC522::PICC_Type piccType = mfrc522.PICC_GetType(mfrc522.uid.sak);
  byte buffer[48];
  byte block;
  byte len;
  MFRC522::StatusCode status;

  Serial.setTimeout(1000L);     // wait until 20 seconds for input from serial
  len = Serial.readBytesUntil('#', (char *) buffer, 48) ; // read data from serial
  for (byte i = len; i < 48; i++) buffer[i] = ' ';     // pad with spaces

  Serial.print(len);
  
  block = 4;
  //Serial.println(F("Authenticating using key A..."));
  status = mfrc522.PCD_Authenticate(MFRC522::PICC_CMD_MF_AUTH_KEY_A, block, &key, &(mfrc522.uid));
  if (status != MFRC522::STATUS_OK) {
    Serial.println(F("Gagal authentikasi"));
    return;
  }
  // Write block
  status = mfrc522.MIFARE_Write(block, buffer, 16);
  if (status != MFRC522::STATUS_OK) {
    return;
  }
  
  byte buffer2[16];
  // copy 16 bytes worth of memory from the item in array onwards
  memcpy(buffer2, &buffer[16], 16 * sizeof(byte)); 
  
  block = 5;
  //Serial.println(F("Authenticating using key A..."));
  status = mfrc522.PCD_Authenticate(MFRC522::PICC_CMD_MF_AUTH_KEY_A, block, &key, &(mfrc522.uid));
  if (status != MFRC522::STATUS_OK) {
    return;
  }
  Serial.println(F("Berhasil authentikasi"));
  
  // Write block
  status = mfrc522.MIFARE_Write(block, buffer2, 16);
  if (status != MFRC522::STATUS_OK) {
    return;
  }
  Serial.println(F("Berhasil menulis"));

  byte buffer3[16];
  // copy 16 bytes worth of memory from the item in array onwards
  memcpy(buffer3, &buffer[32], 16 * sizeof(byte)); 
  
  block = 6;
  //Serial.println(F("Authenticating using key A..."));
  status = mfrc522.PCD_Authenticate(MFRC522::PICC_CMD_MF_AUTH_KEY_A, block, &key, &(mfrc522.uid));
  if (status != MFRC522::STATUS_OK) {
    return;
  }
  // Write block
  status = mfrc522.MIFARE_Write(block, buffer3, 16);
  if (status != MFRC522::STATUS_OK) {
    return;
  }
  
  else Serial.println(F("MIFARE_Write() success"));    
  mfrc522.PICC_HaltA(); // Halt PICC
  mfrc522.PCD_StopCrypto1();  // Stop encryption on PCD
  done = true;
}

void readNUID() {
  // Look for new cards
  if ( ! rfid.PICC_IsNewCardPresent())
    return;
  // Verify if the NUID has been readed
  if ( ! rfid.PICC_ReadCardSerial())
    return;

//  Serial.print(F("PICC type: "));
  MFRC522::PICC_Type piccType = rfid.PICC_GetType(rfid.uid.sak);
//  Serial.println(rfid.PICC_GetTypeName(piccType));

  // Check is the PICC of Classic MIFARE type
  if (piccType != MFRC522::PICC_TYPE_MIFARE_MINI &&  
    piccType != MFRC522::PICC_TYPE_MIFARE_1K &&
    piccType != MFRC522::PICC_TYPE_MIFARE_4K) {
    return;
  }
  
  // Store NUID into nuidPICC array
  for (byte i = 0; i < 4; i++) {
    nuidPICC[i] = rfid.uid.uidByte[i];
  }

  //mencetak NUID
  printHex(rfid.uid.uidByte, rfid.uid.size);
  done = true;
  rfid.PICC_HaltA();
  rfid.PCD_StopCrypto1();
}

void printHex(byte *buffer, byte bufferSize) {
  for (byte i = 0; i < bufferSize; i++) {
    Serial.print(buffer[i] < 0x10 ? "0" : "");
    Serial.print(buffer[i], HEX);
  }
}
