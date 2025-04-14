package com.shopping.shopping_site_backend.shoppingSite.front.controller.intranet.qrcode;

import java.io.OutputStream;
import java.net.Socket;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PrinterService {
  private static final String PRINTER_IP = "http://<Printer_IP>/"; // 請用實際的印表機 IP 位址替換
  private static final String PRINT_COMMAND_ENDPOINT = "print"; // 根據印表機型號可能有所不同

  public void print(byte[] imageData, String printerIp, int port) throws Exception {
    try (Socket socket = new Socket(printerIp, port)) {
      OutputStream outputStream = socket.getOutputStream();
      outputStream.write(imageData);
      outputStream.flush();
    }
  }

  // 發送 QR Code 列印命令
  public void printQRCode(String text, String qrCodeData) {
    RestTemplate restTemplate = new RestTemplate();

    // 生成適當的列印命令（可以是原始數據或 JSON 格式）
    String printCommand = generatePrintCommand(text, qrCodeData);

    HttpHeaders headers = new HttpHeaders();
    headers.set("Content-Type", "application/json");

    HttpEntity<String> entity = new HttpEntity<>(printCommand, headers);

    // 發送 HTTP POST 請求到印表機
    ResponseEntity<String> response =
        restTemplate.exchange(
            PRINTER_IP + PRINT_COMMAND_ENDPOINT, HttpMethod.POST, entity, String.class);
    System.out.println("列印命令已發送: " + response.getBody());
  }

  // 生成列印命令，包含文字與 QR Code
  private String generatePrintCommand(String text, String qrCodeData) {
    // 假設印表機接受 JSON 格式的列印命令，可以包含文字與 QR Code 的設置
    return "{ "
        + "\"command\": \"PRINT_TEMPLATE\", "
        + "\"text\": \""
        + text
        + "\", "
        + "\"qrCode\": \""
        + qrCodeData
        + "\" "
        + "}";
  }
}
