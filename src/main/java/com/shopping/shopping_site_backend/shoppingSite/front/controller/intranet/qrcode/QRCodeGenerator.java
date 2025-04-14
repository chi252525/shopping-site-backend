package com.shopping.shopping_site_backend.shoppingSite.front.controller.intranet.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

public class QRCodeGenerator { // 生成 QR Code 圖像並轉換為 Base64 編碼
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

  public static void main(String[] args) {
    try {
      String qrCodeData = "https://example.com/qrcode"; // 要轉換為 QR Code 的資料
      String base64QRCode = generateQRCodeBase64(qrCodeData); // 生成並轉換為 Base64
      System.out.println("QR Code 的 Base64 編碼：\n" + base64QRCode);
    } catch (Exception e) {
      System.out.println("生成 QR Code 出錯：" + e.getMessage());
    }
  }
}
