package com.ait.phonebook.fw;

import static org.monte.media.FormatKeys.EncodingKey;
import static org.monte.media.FormatKeys.FrameRateKey;
import static org.monte.media.FormatKeys.KeyFrameIntervalKey;
import static org.monte.media.FormatKeys.MIME_AVI;
import static org.monte.media.FormatKeys.MediaTypeKey;
import static org.monte.media.FormatKeys.MimeTypeKey;
import static org.monte.media.VideoFormatKeys.CompressorNameKey;
import static org.monte.media.VideoFormatKeys.DepthKey;
import static org.monte.media.VideoFormatKeys.ENCODING_AVI_MJPG;
import static org.monte.media.VideoFormatKeys.QualityKey;

import com.google.common.io.Files;
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import org.monte.media.Format;
import org.monte.media.FormatKeys;
import org.monte.media.FormatKeys.MediaType;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HelperBase {

  WebDriver driver;

  public HelperBase(WebDriver driver) {
    this.driver = driver;
  }

  public boolean isElementPresent(By locator) {
    try {
      driver.findElements(locator);
      return true;
    } catch (NoSuchElementException ex) {
      return false;

    }
  }

  public void click(By locator) {
    driver.findElement(locator).click();
  }

  public void type(By locator, String text) {
    if (text != null) {
      driver.findElement(locator).click();
      driver.findElement(locator).clear();
      driver.findElement(locator).sendKeys(text);
    }
  }

  public boolean isAlertPresent() {
    Alert alert = new WebDriverWait(driver, 20)
        .until(ExpectedConditions.alertIsPresent());
    if (alert == null) {
      return false;
    } else {
      driver.switchTo().alert();
      alert.accept();
      return true;
    }
  }

  public void pause(int millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  public String takeScreenShot() {
    File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    File screenshot = new File("screenshots/screen" +
        (System.currentTimeMillis() / 1000) % 3600 + ".png");

    try {
      Files.copy(tmp, screenshot);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    return screenshot.getAbsolutePath();

  }

  private ScreenRecorder screenRecorder;

  public void startRecording() throws IOException, AWTException {
    File file = new File("record");
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int width = screenSize.width;
    int height = screenSize.height;
    Rectangle captureSize = new Rectangle(0, 0, width, height);
    GraphicsConfiguration gc = GraphicsEnvironment
        .getLocalGraphicsEnvironment()
        .getDefaultScreenDevice()
        .getDefaultConfiguration();
    screenRecorder = new Recorder(gc, captureSize,
        new Format(MediaTypeKey, FormatKeys.MediaType.FILE, MimeTypeKey, MIME_AVI),
        new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_MJPG,
            CompressorNameKey, ENCODING_AVI_MJPG, DepthKey, 24, FrameRateKey,
            Rational.valueOf(15), QualityKey, 1.0f, KeyFrameIntervalKey, 15 * 60),
        new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey,
            Rational.valueOf(30)),
        null, file, "Video");
    screenRecorder.start();
  }

  public void stopRecording() throws IOException {
    screenRecorder.stop();
  }

  public void deleteScreenCast(String path) {
    File directory = new File(path);
    File[] files = directory.listFiles();
    for (File f : files) {
      f.delete();
    }
  }
}

