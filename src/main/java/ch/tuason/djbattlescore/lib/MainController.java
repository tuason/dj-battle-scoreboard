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
    
    private MainApp mMainApplication;
    
    private DataHandler mDataHandler;
    private ComponentHandler mComponentHandler;
    
    /**
     * constructor
     * 
     * @param mainApp 
     */
    public MainController(MainApp mainApp) {
        this.mMainApplication = mainApp;
        
        initializeData();
    }
    
    
    private void initializeData() {
        System.out.println("--> trying to initialize the data for the app...");
        try {
            // TODO read data from a csv file in the root, otherwise just take the default data...
            //getDataHandler().setupDefaultData();
            System.out.println("--> data successfully initialized...");
        } catch (Exception e) {
            System.out.println("--> error while trying to initialize the data..." + 
                    " shutting down the app!");
        }
    }
    
    
    /**
     * returns a data handler instance...
     * @return 
     */
    public DataHandler getDataHandler() {
        if (mDataHandler == null) {
            mDataHandler = new DataHandler(this);
        }
        return mDataHandler;
    }
 
    
    public ComponentHandler getComponentHandler() {
        if (mComponentHandler == null) {
            mComponentHandler = new ComponentHandler(this);
        }
        return mComponentHandler;
    }
    
    
    public MainApp getMainApplicationInstance() {
        return this.mMainApplication;
    }
    
}
