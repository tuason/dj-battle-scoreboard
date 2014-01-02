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
 *
 * @author maesi
 */
public class MainLayoutPane extends BorderPane {
    
    private ComponentHandler mParent;

    
    public MainLayoutPane(ComponentHandler componentHandler) {
        super();
        this.mParent = componentHandler;
        
        initializeMainLayoutPane();
    }
    
    
    private void initializeMainLayoutPane() {
        setTop(getComponentHandler().getHeader());
        setCenter(getComponentHandler().getScoreBoardChart());
        //setRight(getComponentHandler().getResultLayout());
        setRight(getComponentHandler().getResultAndPlayNowLayout());
        setBottom(getComponentHandler().getFooter());
    }
    
    
    private ComponentHandler getComponentHandler() {
        return this.mParent;
    }
 
    
    public void reloadBarChartWithNewData() {
        getComponentHandler().clearBarchart();
        setCenter(getComponentHandler().getScoreBoardChart());
    }
}
