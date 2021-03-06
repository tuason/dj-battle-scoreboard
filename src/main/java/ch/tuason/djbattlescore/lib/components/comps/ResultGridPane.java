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
import ch.tuason.djbattlescore.lib.MainController;
import ch.tuason.djbattlescore.lib.components.ComponentHandler;
import ch.tuason.djbattlescore.lib.data.entities.DjEntity;
import java.util.ArrayList;
import java.util.Collection;
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
 * the result grid pane... a visual list of the current ranking!
 * 
 * @author maesi
 */
public class ResultGridPane extends GridPane {
    
    private final ComponentHandler mParent;
    private Label resultTitleLabel;
    
    private Collection<DjEntity> currentDjRanking;
    private final ArrayList<HBox> addedRankingComponents = new ArrayList<>();
    
    private Image standardImage;
    private final Map<Long, Image> imageCache;

    /**
     * constructor
     * 
     * @param componentHandler 
     */
    public ResultGridPane(ComponentHandler componentHandler) {
        super();
        
        this.mParent = componentHandler;
        this.imageCache = new HashMap<>();
        
        this.setPadding(new Insets(20, 20, 20, 20)); // top, right, bottom, left...
        this.setHgap(5);
        this.setVgap(5);
     
        add(getResultTitleLabel(), 0, 0);
    }
    
    
    /**
     * adds a certain DjEntity to the ranking grid panel...
     * 
     * @param results a Collection of DjEntity...
     */
    public void addCurrentDJRanking(Collection<DjEntity> results) {
        this.addedRankingComponents.clear();
        this.currentDjRanking = results;
        if (this.currentDjRanking != null && !this.currentDjRanking.isEmpty()) {
            int iPos = 1;
            for (DjEntity dj : this.currentDjRanking) {
                 
                Image djImage = null;
                
                if (!StringUtils.isEmpty(dj.getAvatarPicPath32())) {
                    djImage = getImageFromCache(dj);
                    
                    if (djImage == null) {
                        try {
                            djImage = new Image(getClass().getResourceAsStream(
                                DjBattleConstants.IMAGE_RESOURCE_BASE_FOR_DJ_PICS + 
                                dj.getAvatarPicPath32()));
                            if (!djImage.isError()) {
                                imageCache.put(dj.getId(), djImage);
                            }
                        } catch (Exception e) {
                            System.out.println("the image for dj '" + 
                                dj.getName() + 
                                "' could not be loaded for the avatar image... " + 
                                e.getMessage());
                            djImage = null;
                        }
                    }
                    
                    
                    // it might be an absolute path?
                    if (djImage == null) {
                        try {
                            djImage = new Image(DjBattleConstants.ABSOLUTE_IMAGE_FILEPATH_PREFIX + 
                                    dj.getAvatarPicPath32());
                            if (!djImage.isError()) {
                                imageCache.put(dj.getId(), djImage);
                            }
                        } catch (Exception e) {
                            System.out.println("the image for dj '" + 
                                dj.getName() + 
                                "' could not be loaded for the avatar image... " + 
                                e.getMessage());
                            djImage = null;
                        }
                    }
                    
                    if (djImage == null) {
                        djImage = getStandardImage();
                    }
                }       
                
                if (StringUtils.isEmpty(dj.getAvatarPicPath32()) || djImage == null) {
                    djImage = getStandardImage();
                }
                
                HBox djComponent = new HBox();
                djComponent.setPadding(new Insets(5, 0, 0, 20));
                
                Label djLabel = new Label(dj.getDjNameWithSoundStyle(), new ImageView(djImage));
                djLabel.setFont(new Font("Arial", 20));
                // component.setTextFill(Color.web(DjBattleConstants.COLOR_RESULT_TITLE_TEXT));
                djComponent.getChildren().add(djLabel);
                
                this.addedRankingComponents.add(djComponent);
                this.add(djComponent, 0, iPos);
                iPos++;
            }
        }
    }
    
    /**
     * determines whether a current ranking needs an update as the data shows
     * different graphs to what the datas represent...
     * 
     * @return boolean true - update needed / false - data are still the same, 
     * ignore updates!
     */
    public boolean isDjRankingUpdateNeeded() {
        if (currentDjRanking == null || currentDjRanking.isEmpty())
            return true;
        else {
            DjEntity[] ranking = currentDjRanking.toArray(new DjEntity[0]);
            int iPos = 0;
            for (DjEntity dj : getController().getDataHandler().getSortedAfterRankDjList()) {
                if (!dj.getId().equals(ranking[iPos].getId())) {                    
                    // as we want the leader only be changed when more votes, let us check here first!
                    return dj.getVotes() != ranking[iPos].getVotes();
                }
                iPos++;
            }
            return false;
        }
    }
    
    /**
     * removes alls ranking component
     */
    public void removeCurrentDJRanking() {
        if (this.addedRankingComponents != null && !this.addedRankingComponents.isEmpty())
            getChildren().removeAll(this.addedRankingComponents);
    }
    
    
    private Label getResultTitleLabel() {
        if (resultTitleLabel == null) {
            Image image = new Image(getClass().getResourceAsStream(
                    DjBattleConstants.IMAGE_RESOURCE_TURNTABLE_LOGO_48));
            resultTitleLabel = new Label("Current Ranking", new ImageView(image));
            resultTitleLabel.setFont(new Font("Arial", 30));
            resultTitleLabel.setTextFill(Color.web(
                    DjBattleConstants.COLOR_RESULT_TITLE_TEXT));
        }    
        return resultTitleLabel;
    }
    
    
    private Image getStandardImage() {
        if (standardImage == null) {
            standardImage = new Image(getClass().getResourceAsStream(
                DjBattleConstants.IMAGE_RESOURCE_TURNTABLE_LOGO_32));
        }
        return standardImage;
        
    }
    
    private Image getImageFromCache(DjEntity dj) {
        if (imageCache.containsKey(dj.getId())) {
            return imageCache.get(dj.getId());
        }
        return null;
    }
    
    /**
     * returns the MVC main controller...
     * 
     * @return the one and only MainController instance...
     */
    private MainController getController() {
        return getComponentHandler().getController();
    }
    
    /**
     * returns the ui component handler instance as ComponentHandler...
     *
     * @return a ComponentHandler instance...
     */
    private ComponentHandler getComponentHandler() {
        return this.mParent;
    }
    
    
    /**
     * clears all data and removes the current dj ranking...
     */
    public void clearImageCacheAndData() {
        this.imageCache.clear();
        removeCurrentDJRanking();
    }
}
