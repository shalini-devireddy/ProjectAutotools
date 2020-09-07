package com.utils;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class FileHelper {

    private static final Logger LOG = Logger.getLogger(FileHelper.class);

   public List<String> readDataFromFile(String path){
//        LOG.debug(path);
//        LOG.debug(getClass().getResource(path));

        File aFile = new File(getClass().getResource(path).getFile());
        List<String> linesFromFile = null;

        try {
            linesFromFile = FileUtils.readLines(aFile, StandardCharsets.UTF_8);
        } catch (IOException e) {
            LOG.debug("path is: "+path);
            LOG.debug("Some error happened while reading the file");
        }
        return linesFromFile;
   }

   public void  writeDataIntoFile(String path, List<String> linesToWrite){
       LOG.debug("path is: "+path);
//       File aFile = new File(getClass().getResource(path).getFile());
       File aFile = new File(path);
       LOG.debug("path is: "+aFile.getAbsolutePath());
       try {
           FileUtils.writeLines(aFile, linesToWrite, false);
       } catch (IOException e) {
           LOG.debug("path is: "+path);
           LOG.debug("Some error happened while reading the file");
       }
   }

}
