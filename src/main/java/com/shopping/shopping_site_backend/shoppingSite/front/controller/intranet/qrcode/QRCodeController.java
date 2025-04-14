package com.shopping.shopping_site_backend.shoppingSite.front.controller.intranet.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
步驟簡述：
1.設計 P-touch Template，這樣可以讓 Brother 印表機知道我們要的格式。
2.使用資料合成，把文字、圖片（例如 QRCode）等資料插入模板。
3.用 API 發送合成好的資料給印表機，讓它開始列印。
https://www.brother.tw/zh-tw/labellers/all-labellers/ql-820nwb
*/


/*準備印表機設定
Brother QL-820NWB 要切換到 "HTTP POST 模式" 或 "P-touch Template 模式"
（通常進印表機的設定 → 選擇 Template 模式，或用設定工具改）

設計模板

你可以用 P-touch Editor（Brother 官方軟體）設計一個標籤模板（含 QR Code、文字）。

把這個模板存在印表機上（存成 Template ID，比如 ID: 1）。

從程式上傳資料

Spring Boot 程式先產生資料（如 QR Code）。

把資料插入模板變數中。

最後透過 HTTP POST 傳送列印指令或資料檔（PRN / ESC/P）到 http://<printer-ip>/cgi-bin/print。

資料格式

不是傳 JSON！是傳 application/octet-stream，內容是 PRN / ESC/P 資料或指令。*/
@RequiredArgsConstructor
@RestController
public class QRCodeController {

  private final PrinterService printerService;

  @GetMapping(value = "/qrcode", produces = MediaType.IMAGE_PNG_VALUE)
  public ResponseEntity<byte[]> generateQRCode(@RequestParam String text) throws Exception {
    int width = 300;
    int height = 300;

    QRCodeWriter qrCodeWriter = new QRCodeWriter();
    BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
    BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

    ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
    ImageIO.write(bufferedImage, "PNG", pngOutputStream);
    byte[] pngData = pngOutputStream.toByteArray();
    return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(pngData);
  }

  @PostMapping("/print")
  public ResponseEntity<String> printQRCode(@RequestParam String text) throws Exception {
    byte[] qrCode = createQRCode(text);
    String qrCodeData = generateQRCodeBase64(text);
    printerService.printQRCode(text, qrCodeData);
    return ResponseEntity.ok("列印成功！");
  }

  private byte[] createQRCode(String text) throws Exception {
    QRCodeWriter qrCodeWriter = new QRCodeWriter();
    BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 300, 300);
    BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
    ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
    ImageIO.write(qrImage, "PNG", pngOutputStream);
    return pngOutputStream.toByteArray();
  }

  // 生成 QR Code 圖像並轉換為 Base64 編碼
  public static String generateQRCodeBase64(String qrCodeData) throws Exception {
    int width = 300; // 圖像寬度
    int height = 300; // 圖像高度

    // QR Code 生成的提示參數
    Map<EncodeHintType, Object> hints = new HashMap<>();
    hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L); // 錯誤修正等級
    hints.put(EncodeHintType.MARGIN, 1); // 邊框大小

    // 生成 QR Code
    BitMatrix bitMatrix =
        new MultiFormatWriter().encode(qrCodeData, BarcodeFormat.QR_CODE, width, height, hints);

    // 創建圖像並填充像素
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        image.setRGB(x, y, (bitMatrix.get(x, y) ? Color.BLACK.getRGB() : Color.WHITE.getRGB()));
      }
    }

    // 將圖像轉換為 Base64 編碼
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    ImageIO.write(image, "PNG", byteArrayOutputStream);
    byte[] imageBytes = byteArrayOutputStream.toByteArray();
    return Base64.getEncoder().encodeToString(imageBytes); // 返回 Base64 編碼的字符串
  }
}
