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
 * @author maesi
 */
public class ResultGridPane extends GridPane {
    
    private final ComponentHandler mParent;
    private Label resultLabel;
    
    private Collection<DjEntity> currentDjRanking;
    private final ArrayList<HBox> addedRankingComponents = new ArrayList<>();

    /**
     * constructor
     * 
     * @param componentHandler 
     */
    public ResultGridPane(ComponentHandler componentHandler) {
        super();
        
        this.mParent = componentHandler;
        
        this.setPadding(new Insets(20, 20, 20, 20)); // top, right, bottom, left...
        this.setHgap(5);
        this.setVgap(5);
     
        add(getResultLabel(), 0, 0);
    }
    
    
    public void addCurrentDJRanking() {
        this.addedRankingComponents.clear();
        this.currentDjRanking = getController().getDataHandler().getSortedAfterRankDjList();
        if (this.currentDjRanking != null && !this.currentDjRanking.isEmpty()) {
            int iPos = 1;
            for (DjEntity dj : this.currentDjRanking) {
                 
                Image djImage = null;
                
                if (!StringUtils.isEmpty(dj.getAvatarPicPath32())) {
                    djImage = new Image(getClass().getResourceAsStream(
                        DjBattleConstants.IMAGE_RESOURCE_BASE_FOR_DJ_PICS + 
                                dj.getAvatarPicPath32()));
                }       
                
                if (StringUtils.isEmpty(dj.getAvatarPicPath32()) || djImage == null) {
                    djImage = new Image(getClass().getResourceAsStream(
                        DjBattleConstants.IMAGE_RESOURCE_TURNTABLE_LOGO_32));
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
    
    public void removeCurrentDJRanking() {
        if (this.addedRankingComponents != null && !this.addedRankingComponents.isEmpty())
            getChildren().removeAll(this.addedRankingComponents);
    }
    
    
    private Label getResultLabel() {
        
        if (resultLabel == null) {
            Image image = new Image(getClass().getResourceAsStream(
                    DjBattleConstants.IMAGE_RESOURCE_TURNTABLE_LOGO_48));
            resultLabel = new Label("Current Ranking", new ImageView(image));
            resultLabel.setFont(new Font("Arial", 30));
            resultLabel.setTextFill(Color.web(
                    DjBattleConstants.COLOR_RESULT_TITLE_TEXT));
        }    
        return resultLabel;
    }
    
    
    private MainController getController() {
        return getComponentHandler().getController();
    }
    
    private ComponentHandler getComponentHandler() {
        return this.mParent;
    }
}
