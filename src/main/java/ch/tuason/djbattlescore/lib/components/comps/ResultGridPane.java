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
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *
 * @author maesi
 */
public class ResultGridPane extends GridPane {
    
    private ComponentHandler mParent;
    
    private Label resultLabel;

    /**
     * constructor
     * 
     * @param componentHandler 
     */
    public ResultGridPane(ComponentHandler componentHandler) {
        super();
        
        this.mParent = componentHandler;
        
        this.setPadding(new Insets(40, 0, 0, 50));
        this.setHgap(5);
        this.setVgap(5);
        
        

        add(getResultLabel(), 0, 0);
    }
    
    
    
    private Label getResultLabel() {
        
        if (resultLabel == null) {
            Image image = new Image(getClass().getResourceAsStream(
                    DjBattleConstants.IMAGE_RESOURCE_TURNTABLE_LOGO));
            resultLabel = new Label("Current Result", new ImageView(image));
            resultLabel.setFont(new Font("Arial", 20));
            resultLabel.setTextFill(Color.web(
                    DjBattleConstants.COLOR_RESULT_TITLE_TEXT));
        }    
        return resultLabel;
    }
    
}
