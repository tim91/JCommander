package org.jcommander.core.image;

import java.awt.Image;
import java.awt.Toolkit;

import org.apache.log4j.Logger;

public class ImageService {

	static Logger logger = Logger
			.getLogger("org.jcommander.core.image.ImageService");
	
	public Image PARENT_DIRECTORY_ICON;
	public Image APPLICATION_ICON;
	public Image DIRECTORY_ICON;
	
	private static ImageService imageService;
	
	private ImageService() {
		PARENT_DIRECTORY_ICON = Toolkit.getDefaultToolkit().getImage(ImageService.class.getResource("/icons/up_arrow.jpg"));
		APPLICATION_ICON = Toolkit.getDefaultToolkit().getImage(ImageService.class.getResource("/icons/totalCommander.png"));
		DIRECTORY_ICON = Toolkit.getDefaultToolkit().getImage(ImageService.class.getResource("/icons/folder-icon.png"));
	}
	
	public static ImageService getInstance(){
		if(imageService == null){
			return new ImageService();
		}
		else{
			return imageService;
		}
	}
}
