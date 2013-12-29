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
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

/**
 *
 * @author maesi
 */
public class NowPlayingImageRotator extends GridPane {
    
    
    private final ComponentHandler mParent;

    public NowPlayingImageRotator(ComponentHandler componentHandler) {
        super();
        
        this.mParent = componentHandler;
        
        this.setPadding(new Insets(40, 20, 10, 20)); // top, right, bottom, left...
        this.setHgap(5);
        this.setVgap(5);
    }
    
    
    
    
    
}
