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

package ch.tuason.djbattlescore.lib;

import ch.tuason.djbattlescore.MainApp;
import ch.tuason.djbattlescore.lib.components.ComponentHandler;
import ch.tuason.djbattlescore.lib.data.DataHandler;

/**
 *
 * @author maesi
 */
public class MainController {
    
    private final MainApp mMainApplication;
    
    private DataHandler mDataHandler;
    private ComponentHandler mComponentHandler;
    
    /**
     * constructor
     * 
     * @param mainApp the instance of the main application...
     */
    public MainController(MainApp mainApp) {
        this.mMainApplication = mainApp;
    }
        
    /**
     * returns a data handler instance...
     * 
     * @return the DataHandler instance of the app...
     */
    public DataHandler getDataHandler() {
        if (mDataHandler == null) {
            mDataHandler = new DataHandler(this);
        }
        return mDataHandler;
    }
 
    
    /**
     * returns the component handler instance...
     * 
     * @return ComponentHandler instance...
     */
    public ComponentHandler getComponentHandler() {
        if (mComponentHandler == null) {
            mComponentHandler = new ComponentHandler(this);
        }
        return mComponentHandler;
    }
    
    
    /**
     * method which returns the current main application.
     * 
     * @return MainApp instance...
     */
    public MainApp getMainApplicationInstance() {
        return this.mMainApplication;
    }
    
}
