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
import java.util.Collection;
import java.util.List;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

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
     * @return 
     */
    public NowPlayingImageRotator getImageRotator() {
        if (imageRotator == null) {
            imageRotator = new NowPlayingImageRotator(this);
        }
        return imageRotator;
    }
    
    
    /*
    public BarChart getScoreBoardChartOriginalStyle() {
        if (scoreBoardChart == null) {
            
            final NumberAxis yAxis = new NumberAxis();
            final CategoryAxis xAxis = new CategoryAxis();
            
            scoreBoardChart = new BarChart(xAxis, yAxis);
            scoreBoardChart.setTitle("Kurhaus DJ Battle Scoreboard");
        }
        return scoreBoardChart;
    }
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
   
    /*
    public Button getSayHiButton() {
        if (sayHiButton == null) {
            sayHiButton = new Button();
            sayHiButton.setText("Say 'Hi Kurhaus'");
            sayHiButton.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    System.out.println("Hi, this is the Kurhaus scoreboard!");
                }
            });
        }
        return sayHiButton;
        
    }
    */
    
    
    public void updateDjRanking() {
        List<DjEntity> results = getController().getDataHandler().getSortedAfterRankDjList();
        getResultLayout().removeCurrentDJRanking();
        if (!results.isEmpty()) {
            getResultLayout().addCurrentDJRanking(results);
            getImageRotator().updateCurrentlyPlayingImage(results.get(0));
        }
    }
    
    
    
    public void checkIfNeededAndUpdateDjRanking() {
        if (getResultLayout().isDjRankingUpdateNeeded())
            updateDjRanking();
    }
    
    
    
    public MainController getController() {
        return this.mMainController;
    }
}
