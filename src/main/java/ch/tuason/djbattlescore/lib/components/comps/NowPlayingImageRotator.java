/*
 * Copyright (C) 2013 Tuason Software Inc.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package ch.tuason.djbattlescore.lib.components.comps;

import ch.tuason.djbattlescore.lib.DjBattleConstants;
import ch.tuason.djbattlescore.lib.components.ComponentHandler;
import ch.tuason.djbattlescore.lib.data.entities.DjEntity;
import java.util.HashMap;
import java.util.Map;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.apache.commons.lang3.StringUtils;

/**
 * 
 * Now Playing Image Rotator...
 * 
 * @author maesi
 */
public final class NowPlayingImageRotator extends GridPane {
    
    
    private final ComponentHandler mParent;
    
    private Label titleLabel;
    private Image standardImage;
    private Image currentImage;
    
    private HBox imageViewPanel;
    private ImageView imageView;
    
    private final Map<Long, Image> imageCache;
    

    public NowPlayingImageRotator(ComponentHandler componentHandler) {
        super();
        this.mParent = componentHandler;
        this.imageCache = new HashMap<>();
        
        this.setPadding(new Insets(20, 20, 20, 20)); // top, right, bottom, left...
        this.setHgap(5);
        this.setVgap(5);
        
        add(getNowPlayingTitleLabel(), 0, 0);
        add(getImageViewPanel(getImageView()), 0, 1);
        
        // updateCurrentlyPlayingImage(null);
    }
    
    
    public void updateCurrentlyPlayingImage(DjEntity leadingDj) {
        removeCurrentPlayImage();
        addRightImageToView(leadingDj);
    }
    
    
    private void addRightImageToView(DjEntity leadingDj) {
        if (leadingDj != null && !StringUtils.isEmpty(leadingDj.getAvatarPicPathMain())) {
            Image image = getImageFromCache(leadingDj);
            if (image == null) {
                try {
                    image = new Image(DjBattleConstants.IMAGE_RESOURCE_BASE_FOR_DJ_PICS + 
                        leadingDj.getAvatarPicPathMain());
                    
                    if (!image.isError()) {
                        imageCache.put(leadingDj.getId(), image);
                    }
                } catch(Exception e) {
                    System.out.println("the image for dj '" + 
                            leadingDj.getName() + 
                            "' could not be loaded for the image rotator... " + 
                            e.getMessage());
                    image = null;
                }
                
                try {
                    // it might be an absolute path?
                    if (image == null) {
                        image = new Image(DjBattleConstants.ABSOLUTE_IMAGE_FILEPATH_PREFIX + 
                                leadingDj.getAvatarPicPathMain());
                        if (!image.isError()) {
                            imageCache.put(leadingDj.getId(), image);
                        }
                    }
                } catch (Exception e) {
                    System.out.println("the image for dj '" + 
                            leadingDj.getName() + 
                            "' could not be loaded for the image rotator... " + 
                            e.getMessage());
                    image = null;
                }
                
            }
            
            if (image == null) {
                image = getStandardImage();
            }
            getImageView().setImage(image);
        } else {
            getImageView().setImage(getStandardImage());
        }
    }
    
    
    private void removeCurrentPlayImage() {
        if (this.currentImage != null)
            getChildren().remove(this.currentImage);
    }
    
    
    private Image getImageFromCache(DjEntity dj) {
        if (imageCache.containsKey(dj.getId())) {
            return imageCache.get(dj.getId());
        }
        return null;
    }
    
    private Image getStandardImage() {
        if (standardImage == null) {
            standardImage = new Image(DjBattleConstants.IMAGE_RESOURCE_PLAYING_NOW_STANDARD);
        }
        return standardImage;
    }

    
    private ImageView getImageView() {
        if (imageView == null) {
            imageView = new ImageView();
            imageView.setFitWidth(DjBattleConstants.DJ_AVATAR_SIZE_BIG_WIDTH);
            imageView.setPreserveRatio(true);
            imageView.setSmooth(true);
            imageView.setCache(true);
        }
        return imageView;
    }
    
    
    private HBox getImageViewPanel(ImageView view) {
        if (imageViewPanel == null) {
            imageViewPanel = new HBox();
            imageViewPanel.setPadding(new Insets(5, 0, 0, 30));
            imageViewPanel.getChildren().add(view);
        }
        return imageViewPanel;
    }
    
    
    private Label getNowPlayingTitleLabel() {
        
        if (titleLabel == null) {
            Image image = new Image(getClass().getResourceAsStream(
                    DjBattleConstants.IMAGE_RESOURCE_TURNTABLE_LOGO_48));
            titleLabel = new Label("Spinning the Decks", new ImageView(image));
            titleLabel.setFont(new Font("Arial", 30));
            titleLabel.setTextFill(Color.web(
                    DjBattleConstants.COLOR_RESULT_TITLE_TEXT));
        }    
        return titleLabel;
    }
    
    
    /**
     * clears all data and removes the image...
     */
    public void clearImageCacheAndData() {
        this.imageCache.clear();
        removeCurrentPlayImage();
    }
}
