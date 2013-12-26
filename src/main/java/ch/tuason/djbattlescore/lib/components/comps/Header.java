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
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *
 * @author maesi
 */
public class Header extends HBox {

    /**
     * constructor
     * 
     */
    public Header() {
        super();
        initializeHeader();
    }
    
    /**
     * set up all components and the look and feel...
     */    
    private void initializeHeader() {
        setPadding(new Insets(15, 12, 15, 12));
        setSpacing(10);
        setStyle(DjBattleConstants.FOOTER_AND_HEADER_BACKGROUND_STYLE);
        
        Image imageKurhaus = new Image("/images/kurhaus_logo.png");
        Label title = new Label("  ...::: DJ Battle Scoreboard :::...", new ImageView(imageKurhaus));
        
        title.setFont(new Font("Arial", 40));
        title.setTextFill(Color.web("#FFFFFF"));
        
        getChildren().addAll(title);
    }
    
    
}
