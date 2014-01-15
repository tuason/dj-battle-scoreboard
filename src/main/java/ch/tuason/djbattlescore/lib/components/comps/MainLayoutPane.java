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

import ch.tuason.djbattlescore.lib.components.ComponentHandler;
import javafx.scene.layout.BorderPane;

/**
 * the mainlayout panel...
 * 
 * @author maesi
 */
public class MainLayoutPane extends BorderPane {
    
    private ComponentHandler mParent;

    /**
     * constructor
     * 
     * @param componentHandler the parent ComponentHandler instance...
     */
    public MainLayoutPane(ComponentHandler componentHandler) {
        super();
        this.mParent = componentHandler;
        
        initializeMainLayoutPane();
    }
    
    /**
     * initialized the main layout panel...
     */
    private void initializeMainLayoutPane() {
        setTop(getComponentHandler().getHeader());
        setCenter(getComponentHandler().getScoreBoardChart());
        setRight(getComponentHandler().getResultAndPlayNowLayout());
        setBottom(getComponentHandler().getFooter());
    }

    /**
     * a public method to reload the bar chart with complete new memory data... this
     * method is mainly used after a reload of a csv-file over the file load dialog
     * <ctrl> + <shift> + <enter>
     */
    public void reloadBarChartWithNewData() {
        getComponentHandler().nullBarchartComponent();
        setCenter(getComponentHandler().getScoreBoardChart());
    }
    
    /**
     * returns the ComponentHandler instance...
     * 
     * @return a ComponentHandler instance...
     */
    private ComponentHandler getComponentHandler() {
        return this.mParent;
    }
}
