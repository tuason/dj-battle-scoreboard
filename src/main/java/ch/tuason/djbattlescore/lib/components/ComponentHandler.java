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

package ch.tuason.djbattlescore.lib.components;

import ch.tuason.djbattlescore.lib.MainController;
import ch.tuason.djbattlescore.lib.components.comps.BattleBarChart;
import ch.tuason.djbattlescore.lib.components.comps.Footer;
import ch.tuason.djbattlescore.lib.components.comps.Header;
import ch.tuason.djbattlescore.lib.components.comps.MainLayoutPane;
import ch.tuason.djbattlescore.lib.components.comps.NowPlayingImageRotator;
import ch.tuason.djbattlescore.lib.components.comps.ResultGridPane;
import ch.tuason.djbattlescore.lib.components.comps.ResultNowPlayingBoxRight;
import ch.tuason.djbattlescore.lib.data.entities.DjEntity;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;

/**
 *
 * @author maesi
 */
public class ComponentHandler {
   
    private final MainController mMainController;
    private MainLayoutPane mMainLayout;
    
    private ResultGridPane resultLayout;
    private NowPlayingImageRotator imageRotator;
    // private Button sayHiButton;
    private Header header;
    private BattleBarChart scoreBoardChart;
    private ResultNowPlayingBoxRight resultAndNowPlayingBox;
    private Footer footer;
    
    /**
     * constructor
     * 
     * @param controller 
     */
    public ComponentHandler(MainController controller) {
        this.mMainController = controller;
    }
    
    
    /**
     * returns the main layout...
     * @return 
     */
    public MainLayoutPane getMainLayout() {
        if (mMainLayout == null) {
            mMainLayout = new MainLayoutPane(this);
        }
        return mMainLayout;
    }
    
    
    /**
     * method to null the bar chart instance...
     */
    public void nullBarchartComponent() {
        this.scoreBoardChart = null;
    }
    
    
    /**
     * returns the bar chart component...
     * 
     * @return 
     */
    public BattleBarChart getScoreBoardChart() {
        if (scoreBoardChart == null) {
            scoreBoardChart = new BattleBarChart(getController());
        }
        return scoreBoardChart;
    }
    
    
    /**
     * returns the whole right side (result and play now panels)...
     * 
     * @return 
     */
    public ResultNowPlayingBoxRight getResultAndPlayNowLayout() {
        if (resultAndNowPlayingBox == null) {
            resultAndNowPlayingBox = new ResultNowPlayingBoxRight(this);
        }
        return resultAndNowPlayingBox;
    }
    
    
    /**
     * returns the layout with the current results / standings...
     * 
     * @return 
     */
    public ResultGridPane getResultLayout() {
        if (resultLayout == null) {
            resultLayout = new ResultGridPane(this);
        }
        return resultLayout;
    }
    
    
    /**
     * returns an image rotator with the currently playing dj...
     * 
     * @return a NowPlayingImageRotator instance...
     */
    public NowPlayingImageRotator getImageRotator() {
        if (imageRotator == null) {
            imageRotator = new NowPlayingImageRotator(this);
        }
        return imageRotator;
    }
    
    
    /**
     * returns the footer component...
     * 
     * @return a Footer instance...
     */
    public Footer getFooter() {
        if (footer == null) {
            footer = new Footer(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent t) {
                    Platform.exit();
                }
                
            }, new EventHandler<ActionEvent>() { 

                @Override
                public void handle(ActionEvent t) {
                    getController().getComponentHandler().getScoreBoardChart().resetChartAndAllData();       
                }
            });
        }
        return footer;
    }
    
    
    /**
     * returns the title box with logo and label...
     * @return 
     */
    public Header getHeader() {
        if (header == null) {
            header = new Header();
        }
        return header;
    }
   
    
    /**
     * opens a Import Dialog to import a CSV file...
     */
    public void showCSVImportDialog() {
        FileChooser fileChooser = new FileChooser();
            
        //Set extension filter
        FileChooser.ExtensionFilter extFilterCVS = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.CSV");
        fileChooser.getExtensionFilters().addAll(extFilterCVS);
             
        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);
                      
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file.getAbsolutePath()));
            if (getController().getDataHandler().readCSVFileFromBufferedReader(br, true)) {
                System.out.println("CSV data successfully imported... updating the app layout!");
                getMainLayout().reloadBarChartWithNewData();
                updateDjRanking();
            } else {
                System.out.println("Something with importing the CSV data went absolutely wrong!");
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Could not find the file to load...");
            Logger.getLogger(ComponentHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * method to update the dj ranking...
     */
    public void updateDjRanking() {
        List<DjEntity> results = getController().getDataHandler().getSortedAfterRankDjList();
        getResultLayout().removeCurrentDJRanking();
        if (!results.isEmpty()) {
            getResultLayout().addCurrentDJRanking(results);
            if (areAllVotesEqual(results)) {
                getImageRotator().addStandardImage();
            } else {
                getImageRotator().updateCurrentlyPlayingImage(results.get(0));
            }
        }
    }
    
    /**
     * checks whether all votes of the dj's are equal or not...
     * @param listOfDjs
     * @return boolean true or false whether the votes of the dj's are equal or
     * not...
     */
    private boolean areAllVotesEqual(List<DjEntity> listOfDjs) {
        int lastVote = -1;
        for (DjEntity dj : listOfDjs) {
            if (lastVote == -1) {
                lastVote = dj.getVotes();
            } else {
                if (lastVote != dj.getVotes()) {
                    return false;
                }
            }
        }
        return true;
    }
    
    
    
    public void checkIfNeededAndUpdateDjRanking() {
        if (getResultLayout().isDjRankingUpdateNeeded())
            updateDjRanking();
    }
    
    
    
    public MainController getController() {
        return this.mMainController;
    }
}
