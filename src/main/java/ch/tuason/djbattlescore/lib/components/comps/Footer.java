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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

/**
 * the footer component...
 * 
 * @author maesi
 */
public class Footer extends BorderPane {

    private Button resetButton;
    private Button exitButton;
    
    private HBox copyright;
    private Label lblCopyright;
    private Label tuasonLink;
    
    private final EventHandler<ActionEvent> resetButtonEventHandler;
    private final EventHandler<ActionEvent> exitButtonEventHandler;
    
    /**
     * constructor
     * @param exitEvent
     * @param resetEvent
     */
    public Footer(final EventHandler<ActionEvent> exitEvent, final EventHandler<ActionEvent> resetEvent) {
        super();
        
        this.exitButtonEventHandler = exitEvent;
        this.resetButtonEventHandler = resetEvent;
        
        initializeFooter();
    }
    
    
    private void initializeFooter() {
        setPadding(new Insets(15, 12, 15, 12));
        setStyle(DjBattleConstants.FOOTER_AND_HEADER_BACKGROUND_STYLE);
        setLeft(getCopyRight());
        HBox buttonBox = new HBox();
        buttonBox.setSpacing(10);
        buttonBox.setAlignment(Pos.BOTTOM_LEFT);
        buttonBox.getChildren().addAll(getResetButton(), getExitButton());
        setRight(buttonBox);
    }
    
    
    private Button getResetButton() {
        if (resetButton == null) {
            resetButton = new Button("reset");
            resetButton.setPrefSize(100, 20);
            if (resetButtonEventHandler != null)
                resetButton.setOnAction(resetButtonEventHandler);
        }
        return resetButton;
    }
    
    private Button getExitButton() {
        if (exitButton == null) {
            exitButton = new Button("exit");
            exitButton.setPrefSize(100, 20);
            if (exitButtonEventHandler != null)
                exitButton.setOnAction(exitButtonEventHandler);
        }
        return exitButton;
    }
    
    
    private HBox getCopyRight() {
        if (copyright == null) {
            copyright = new HBox();
            copyright.getChildren().addAll(getCopyRightLabel(), getTuasonLink());
            copyright.setAlignment(Pos.BOTTOM_LEFT);
        }
        return copyright;
    }
    
    private Label getCopyRightLabel() {
        if (lblCopyright == null) {
            lblCopyright = new Label();
            lblCopyright.setFont(new Font("Arial", 12));
            lblCopyright.setText("(c)opyright 2014 by tuason software inc. - ");
        }
        return lblCopyright;
    }
    
    private Label getTuasonLink() {
        if (tuasonLink == null) {
            tuasonLink = new Label();
            tuasonLink.setFont(new Font("Arial", 10));
            tuasonLink.setText("http://www.tuason.com.au");
        }
        return tuasonLink;
    }
}
