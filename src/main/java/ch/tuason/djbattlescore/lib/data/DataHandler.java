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

package ch.tuason.djbattlescore.lib.data;

import ch.tuason.djbattlescore.lib.DjBattleConstants;
import ch.tuason.djbattlescore.lib.MainController;
import ch.tuason.djbattlescore.lib.data.entities.DjEntity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author maesi
 */
public class DataHandler {
    
    private final MainController mMainController;
    
    private List<DjEntity> mDjs;

    /**
     * constructor
     * @param controller 
     */
    public DataHandler(MainController controller) {
        this.mMainController = controller;
    }
    
    
    public List<DjEntity> getDJList() {
        if (mDjs == null) {
            if (! importDataFromDjsCSVFile()) {
                // setupAppWithTestDefaultData();
                System.out.println("Error while trying to load default data...");
            }
        }
        return mDjs;
    }
    
    
    public List<DjEntity> getSortedAfterRankDjList() {
        ArrayList<DjEntity> result = new ArrayList<>(getDJList());
        Collections.sort(result);
        return result;
    }
    
    public List<String> getDJNamesWithSoundStyle() {
        List<String> result = new ArrayList<>();
        if (getDJList() != null && !getDJList().isEmpty()) {
            for (DjEntity dj : getDJList()) {
                // result.add(dj.getDjNameWithSoundStyle());
                result.add(dj.getName()); 
            }
        }
        return result;
    }
    
    
    public DjEntity getDjEntityWithName(String djName) {
        for (DjEntity dj : getDJList()) {
            if (dj.getName().equals(djName))
                return dj;
        }
        return null;
    }
    
    
    public DjEntity getDjEntityWithNameAndStyle(String nameAndSoundStyle) {
        for (DjEntity dj : getDJList()) {
            if (dj.getDjNameWithSoundStyle().equals(nameAndSoundStyle))
                return dj;
        }
        return null;
    }
    
    
    
    public void clearAllVotesBackToZero() {
        for (DjEntity dj : getDJList()) {
            if (dj != null) {
                dj.setVotes(DjBattleConstants.START_NUMBER_FOR_VOTES);
            }
        }
    }
    
    /**
     * clears all data and components...
     */
    private void clearAllDJAndCacheData() {
        mDjs.clear();
        getController().getComponentHandler().getImageRotator().clearImageCacheAndData();
        getController().getComponentHandler().getResultLayout().clearImageCacheAndData();
    }
    
    
    /**
     * increases a vote (+1) for one particular dj...
     * 
     * @param dj a DjEntity instance, which representste dj!
     *
     */
    public void increaseVotesForDj(DjEntity dj) {
        if (dj != null) {
            dj.setVotes(dj.getVotes() + 1);
        }
    }
    
    
    /**
     * method which reads a .CSV-file over an existing BufferedReader...
     * 
     * @param br a BufferedReader where we can read from... the lines should 
     * represent comma-seperated values in the following format:
     *  id,name,soundStyle,votes,avatarPicPath32,avatarPicPathMain
     *      
     * @param clearDataBeforeRead boolean value whether the cached buffer needs 
     * to be completey clearedä or not...
     * 
     * @return 
     */
    public boolean readCSVFileFromBufferedReader(BufferedReader br, boolean clearDataBeforeRead) {
        if (br == null) {
            return false;
        } else {
            if (clearDataBeforeRead) {
                clearAllDJAndCacheData();
            }
            try {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] dj = line.split(DjBattleConstants.FILE_CVS_SPLIT_BY, line.length()-1);
                    if (dj.length > 0) {
                        if (!dj[0].equalsIgnoreCase("id")) {
                            DjEntity djEntity = new DjEntity();
                            djEntity.setId(Long.valueOf(dj[0]));
                            djEntity.setName(StringUtils.isEmpty(dj[1]) ? "DJ no name" : dj[1]);
                            djEntity.setSoundStyle(StringUtils.isEmpty(dj[2]) ? "no style" : dj[2]);
                            djEntity.setVotes(StringUtils.isEmpty(dj[3]) ? DjBattleConstants.START_NUMBER_FOR_VOTES : Integer.valueOf(dj[3]).intValue());
                            djEntity.setAvatarPicPath32(StringUtils.isEmpty(dj[4]) ? null : dj[4]);
                            djEntity.setAvatarPicPathMain(StringUtils.isEmpty(dj[5]) ? null : dj[5]);
                            mDjs.add(djEntity);
                        }
                    }
                }

                return true;
            } 
            catch (IOException e) {
                System.out.println("Error while trying to import data from csv file... " + 
                    e.getMessage());
                return false;
            }
        }
    }
    
    
    
    /**
     * this method imports the data from a certain djs.csv file which is placed in the resource directory "data"...
     * 
     * @return true or false, whether the method was successfully or not...
     */
    private boolean importDataFromDjsCSVFile() {
        
        if (mDjs == null)
            mDjs = new ArrayList<>();
            
        BufferedReader br = new BufferedReader(new InputStreamReader(
            this.getClass().getResourceAsStream(
                          DjBattleConstants.FILE_RESOURCE_DJS_CSV)));
            
        return readCSVFileFromBufferedReader(br, false);
    }
    
    
    private MainController getController() {
        return this.mMainController;
    }
    
//    
//    /**
//     * this method helps to fill test data to the app...
//     * 
//     * // TODO we need to read the data out of a config file or better a csv text file!
//     */
//    private void setupAppWithTestDefaultData() {
//        if (mDjs == null)
//            mDjs = new ArrayList<>();
//        
//        DjEntity djOne = new DjEntity();
//        djOne.setId(new Long(1));
//        djOne.setName("DJ One");
//        djOne.setSoundStyle("Nu-Beatz");
//        djOne.setVotes(23);
//        djOne.setAvatarPicPath32("dj_avatar_1_32.jpg");
//        mDjs.add(djOne);
//        
//        DjEntity djTwo = new DjEntity();
//        djTwo.setId(new Long(2));
//        djTwo.setName("DJ Two");
//        djTwo.setSoundStyle("Hip-Hop");
//        djTwo.setVotes(34);
//        djTwo.setAvatarPicPath32("dj_avatar_2_32.jpg");
//        mDjs.add(djTwo);
//        
//        DjEntity djThree = new DjEntity();
//        djThree.setId(new Long(3));
//        djThree.setName("DJ Three");
//        djThree.setSoundStyle("Urban");
//        djThree.setVotes(14);
//        //djThree.setAvatarPicPath32("dj_avatar_3_32.jpg");
//        mDjs.add(djThree);
//        
//        DjEntity djFour = new DjEntity();
//        djFour.setId(new Long(4));
//        djFour.setName("DJ Four");
//        djFour.setSoundStyle("House");
//        djFour.setVotes(9);
//        //djFour.setAvatarPicPath32("dj_avatar_4_32.jpg");
//        mDjs.add(djFour);
//            
//        DjEntity djFive = new DjEntity();
//        djFive.setId(new Long(5));
//        djFive.setName("DJ Five");
//        djFive.setSoundStyle("Electro");
//        djFive.setVotes(21);
//        djFive.setAvatarPicPath32("dj_avatar_5_32.jpg");
//        mDjs.add(djFive);
//    }
}
