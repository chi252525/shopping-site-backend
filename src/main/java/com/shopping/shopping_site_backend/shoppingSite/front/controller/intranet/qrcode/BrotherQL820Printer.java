package com.shopping.shopping_site_backend.shoppingSite.front.controller.intranet.qrcode;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/*Body: 直接送 PRN 檔 或 ESC/P 資料

*/
public class BrotherQL820Printer {

  // Brother QL-820NWB 印表機的 IP 位址
  private static final String PRINTER_IP = "http://<Printer_IP>/"; // 替換為實際的 IP 地址
  private static final String PRINT_COMMAND_ENDPOINT = "print"; // 根據印表機設定調整這個端點

  // 用「P-touch Template」模式 發送列印命令，包含文字與 Base64 編碼的 QR Code
  /*流程：
  用 Brother 的 P-touch Editor 設計一個漂亮模板：

  左邊放 QR Code（放變數）

  右邊放文字（放變數）

  存到印表機 → 記下 Template ID（例如 Template ID = 1）

  Spring Boot 程式傳資料：

  把文字、QR Code資料，按照 Brother Template 變數格式填好。

  用 HTTP POST 送過去 → 印出來就是你設計好的樣子！*/
  public void printTemplate(String text, String qrCodeBase64) {
    RestTemplate restTemplate = new RestTemplate();

    // 生成列印命令
    String printCommand = generatePrintCommand(text, qrCodeBase64);

    HttpHeaders headers = new HttpHeaders();
    headers.set("Content-Type", "application/json");

    HttpEntity<String> entity = new HttpEntity<>(printCommand, headers);

    // 發送 HTTP POST 請求到印表機
    ResponseEntity<String> response =
        restTemplate.exchange(
            PRINTER_IP + PRINT_COMMAND_ENDPOINT, HttpMethod.POST, entity, String.class);
    System.out.println("列印命令已發送: " + response.getBody());
  }

  // 生成列印命令，包含文字與 Base64 編碼的 QR Code
  private String generatePrintCommand(String text, String qrCodeBase64) {
    // 假設印表機接受 JSON 格式的列印命令，可以包含文字與 QR Code 的設置
    return "at=print\r\n"
        + // action type
        "tid="
        + 2
        + "\r\n"
        + // template id
        "text1="
        + text
        + "\r\n"
        + // 第一個變數
        "text2="
        + qrCodeBase64
        + "\r\n"; // 第二個變數 (假設你 QR code 用 Text 方式生成)
  }

  public static void main(String[] args) {
    BrotherQL820Printer printerService = new BrotherQL820Printer();
    String qrCodeData = "https://example.com/qrcode"; // 要轉換為 QR Code 的資料
    try {
      String base64QRCode =
          QRCodeGenerator.generateQRCodeBase64(qrCodeData); // 生成 QR Code Base64 編碼
      printerService.printTemplate("這是測試文字", base64QRCode); // 傳入文字與 Base64 編碼的 QR Code
    } catch (Exception e) {
      System.out.println("發送列印命令時出錯：" + e.getMessage());
    }
  }
}
