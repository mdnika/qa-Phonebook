package com.ait.phonebook.fw;

import java.awt.AWTException;
import java.awt.GraphicsConfiguration;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.monte.media.Format;
import org.monte.media.Registry;
import org.monte.screenrecorder.ScreenRecorder;

public class Recorder extends ScreenRecorder {

  private String name;

  @Override
  public List<File> getCreatedMovieFiles() {
    return super.getCreatedMovieFiles();
  }

  @Override
  protected File createMovieFile(Format fileFormat) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd HH.mm");
    return new File(movieFolder, name + "-" + dateFormat.format(new Date()) + "."
    + Registry.getInstance().getExtension(fileFormat));
  }

  public Recorder(GraphicsConfiguration cfg, Rectangle captureArea,
      Format fileFormat, Format screenFormat,
      Format mouseFormat, Format audioFormat,
      File movieFolder, String name) throws IOException, AWTException {
    super(cfg, captureArea, fileFormat, screenFormat, mouseFormat, audioFormat, movieFolder);
  this.name = name;
  }
}
