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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
            setupDefaultData();
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
    
    
    public void increaseVotesForDj(DjEntity dj) {
        if (dj != null) {
            dj.setVotes(dj.getVotes() + 1);
        }
    }
    
    
    /**
     * this method helps to fill test data to the app...
     * 
     * // TODO we need to read the data out of a config file or better a csv text file!
     */
    private void setupDefaultData() {
        if (mDjs == null)
            mDjs = new ArrayList<>();
        
        DjEntity djOne = new DjEntity();
        djOne.setId(new Long(1));
        djOne.setName("DJ One");
        djOne.setSoundStyle("Nu-Beatz");
        djOne.setVotes(23);
        mDjs.add(djOne);
        
        DjEntity djTwo = new DjEntity();
        djTwo.setId(new Long(2));
        djTwo.setName("DJ Two");
        djTwo.setSoundStyle("Hip-Hop");
        djTwo.setVotes(34);
        mDjs.add(djTwo);
        
        DjEntity djThree = new DjEntity();
        djThree.setId(new Long(3));
        djThree.setName("DJ Three");
        djThree.setSoundStyle("Urban");
        djThree.setVotes(14);
        mDjs.add(djThree);
        
        DjEntity djFour = new DjEntity();
        djFour.setId(new Long(4));
        djFour.setName("DJ Four");
        djFour.setSoundStyle("House");
        djFour.setVotes(9);
        mDjs.add(djFour);
            
        DjEntity djFive = new DjEntity();
        djFive.setId(new Long(5));
        djFive.setName("DJ Five");
        djFive.setSoundStyle("Electro");
        djFive.setVotes(21);
        mDjs.add(djFive);
    }
}
