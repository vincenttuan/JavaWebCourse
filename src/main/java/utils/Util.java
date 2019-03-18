package utils;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Base64;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.Part;

public class Util {

    public static String getFileName(Part part) {
        String partHeader = part.getHeader("content-disposition");
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    public static boolean saveFile(InputStream inputStream, String filePathAndName) {
        try {
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            File targetFile = new File(filePathAndName);
            FileOutputStream outStream = new FileOutputStream(targetFile);
            outStream.write(buffer);
            outStream.flush();
            outStream.close();
            return targetFile.exists();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public static boolean saveFile(String base64, String filePathAndName) {
        try {
            byte[] buffer = getDeBase64(base64);
            File targetFile = new File(filePathAndName);
            FileOutputStream outStream = new FileOutputStream(targetFile);
            outStream.write(buffer);
            outStream.flush();
            outStream.close();
            return targetFile.exists();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public static String getValue(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStreamReader isr = new InputStreamReader(is, "UTF-8");
        BufferedReader reader = new BufferedReader(isr);
        String data = "";
        while ((data = reader.readLine()) != null) {
            sb.append(data);
        }
        return sb.toString();
    }

    public static String getBase64(String filepath) throws IOException {
        InputStream is = new FileInputStream(filepath);
        return getBase64(is);
    }

    public static String getBase64(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] bytes = new byte[1];
        while (is.read(bytes) != -1) {
            baos.write(bytes);
        }
        String base64 = Base64.getEncoder().encodeToString(baos.toByteArray());
        return base64;
    }

    public static byte[] getDeBase64(String base64) throws IOException {
        return Base64.getDecoder().decode(base64);
    }

    public static String getBase64(InputStream is, int scaledWidth, int scaledHeight) throws IOException {
        Image image = ImageIO.read(is);
        BufferedImage bi = createResizedCopy(image, scaledWidth, scaledHeight, true);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bi, "jpg", baos);
        String base64 = Base64.getEncoder().encodeToString(baos.toByteArray());
        return base64;
    }

    private static BufferedImage createResizedCopy(Image originalImage, int scaledWidth, int scaledHeight, boolean preserveAlpha) {
        int imageType = preserveAlpha ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
        BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight, imageType);
        Graphics2D g = scaledBI.createGraphics();
        if (preserveAlpha) {
            g.setComposite(AlphaComposite.Src);
        }
        g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
        g.dispose();
        return scaledBI;
    }
    
    public static BufferedImage getAuthImg(String authCode) {
        // 建立圖像暫存區
        BufferedImage img = new BufferedImage(80, 30, BufferedImage.TYPE_INT_BGR);
        // 建立畫布
        Graphics g = img.getGraphics();
        // 設定顏色
        g.setColor(Color.YELLOW);
        // 塗滿背景
        g.fillRect(0, 0, 80, 30);
        // 設定顏色
        g.setColor(Color.BLACK);
        // 設定自型
        g.setFont(new Font("新細明體", Font.PLAIN, 30));
        // 繪字串
        g.drawString(authCode, 10, 23); // code, x, y
        
        // 干擾線條
        Random random = new Random();
        g.setColor(Color.BLUE);
        for (int i = 0; i < 10; i++) {
            int x = random.nextInt(80);
            int y = random.nextInt(30);
            int x2 = random.nextInt(80);
            int y2 = random.nextInt(30);
            g.drawLine(x, y, x2, y2);
        }
        return img;
    }

}