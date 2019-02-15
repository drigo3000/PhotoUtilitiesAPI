/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swevolution.photoutilities;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.imgscalr.Scalr;

/**
 *
 * @author Rxkx
 */
public class PhotoFileManager implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(PhotoFileManager.class.getName());

    public static void uploadPhoto(InputStream is, String path, String id, String fileName) {
        try {
            createDirectoryStructure(path);

            BufferedImage originalImage = ImageIO.read(is);

            BufferedImage iconResImage = Scalr.resize(originalImage,
                    Scalr.Method.SPEED, Scalr.Mode.FIT_TO_WIDTH,
                    96, Scalr.OP_ANTIALIAS);
            BufferedImage lowResImage = Scalr.resize(originalImage,
                    Scalr.Method.SPEED, Scalr.Mode.FIT_TO_WIDTH,
                    260, Scalr.OP_ANTIALIAS);
            BufferedImage midResImage = Scalr.resize(originalImage,
                    Scalr.Method.BALANCED, Scalr.Mode.FIT_TO_WIDTH,
                    640, Scalr.OP_ANTIALIAS);
            BufferedImage highResImage = Scalr.resize(originalImage,
                    Scalr.Method.QUALITY, Scalr.Mode.FIT_TO_WIDTH,
                    960, Scalr.OP_ANTIALIAS);
            BufferedImage hdResImage = Scalr.resize(originalImage,
                    Scalr.Method.ULTRA_QUALITY, Scalr.Mode.FIT_TO_WIDTH,
                    1600, Scalr.OP_ANTIALIAS);

            //File originalFile = new File(configuracionOperadora.getPathPhotos()+ fileName);
            File iconResFile = new File(path + "icon_" + id + "_" + fileName);
            File lowResFile = new File(path + "low_" + id + "_" + fileName);
            File midResFile = new File(path + "mid_" + id + "_" + fileName);
            File highResFile = new File(path + "high_" + id + "_" + fileName);
            File hdResFile = new File(path + "hd_" + id + "_" + fileName);

            //ImageIO.write(originalImage, "jpg", originalFile);
            ImageIO.write(iconResImage, "jpg", iconResFile);
            ImageIO.write(lowResImage, "jpg", lowResFile);
            ImageIO.write(midResImage, "jpg", midResFile);
            ImageIO.write(highResImage, "jpg", highResFile);
            ImageIO.write(hdResImage, "jpg", hdResFile);
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage(), e);
        }
    }

    public static void uploadSearchPhoto(InputStream is, String fileName, String path) {
        try {
            createDirectoryStructure(path);
            BufferedImage originalImage = ImageIO.read(is);

            BufferedImage lowResImage = Scalr.resize(originalImage,
                    Scalr.Method.SPEED, Scalr.Mode.FIT_TO_WIDTH,
                    260, Scalr.OP_ANTIALIAS);
            BufferedImage midResImage = Scalr.resize(originalImage,
                    Scalr.Method.BALANCED, Scalr.Mode.FIT_TO_WIDTH,
                    360, Scalr.OP_ANTIALIAS);
            BufferedImage highResImage = Scalr.resize(originalImage,
                    Scalr.Method.QUALITY, Scalr.Mode.FIT_TO_WIDTH,
                    480, Scalr.OP_ANTIALIAS);
            BufferedImage hdResImage = Scalr.resize(originalImage,
                    Scalr.Method.ULTRA_QUALITY, Scalr.Mode.FIT_TO_WIDTH,
                    640, Scalr.OP_ANTIALIAS);

            //File originalFile = new File(configuracionOperadora.getPathPhotos()+ fileName);
            File lowResFile = new File(path
                    + "low_search_" + fileName);
            File midResFile = new File(path
                    + "mid_search_" + fileName);
            File highResFile = new File(path
                    + "high_search_" + fileName);
            File hdResFile = new File(path
                    + "hd_search_" + fileName);

            //ImageIO.write(originalImage, "jpg", originalFile);
            ImageIO.write(lowResImage, "jpg", lowResFile);
            ImageIO.write(midResImage, "jpg", midResFile);
            ImageIO.write(highResImage, "jpg", highResFile);
            ImageIO.write(hdResImage, "jpg", hdResFile);
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage(), e);
        }
    }

    public static void deletePhoto(String path, long id, String link) {
        File iconFile = new File(path + "icon_" + id + "_" + link);
        if (iconFile.exists()) {
            iconFile.delete();
        }

        File lowFile = new File(path + "low_" + id + "_" + link);
        if (lowFile.exists()) {
            lowFile.delete();
        }

        File midFile = new File(path + "mid_" + id + "_" + link);
        if (midFile.exists()) {
            midFile.delete();
        }

        File highFile = new File(path + "high_" + id + "_" + link);
        if (highFile.exists()) {
            highFile.delete();
        }

        File hdFile = new File(path + "hd_" + id + "_" + link);
        if (hdFile.exists()) {
            hdFile.delete();
        }
    }

    private static void createDirectoryStructure(String path) throws IOException {
        Path p = Paths.get(path);
        File f = new File(path);
        if (!f.exists()) {
            Files.createDirectories(p);
        }
    }
}
