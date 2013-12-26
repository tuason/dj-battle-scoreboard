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

package ch.tuason.djbattlescore.lib.data.entities;

import java.beans.Transient;

/**
 *
 * @author maesi
 */
public class DjEntity implements Comparable {
    
    private Long id;
    private String name;
    private String soundStyle;
    private int votes;
    //private int rank;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSoundStyle() {
        return soundStyle;
    }

    public void setSoundStyle(String soundStyle) {
        this.soundStyle = soundStyle;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    /*
    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
    */
    
    
    
    
    @Transient
    public String getDjNameWithSoundStyle() {
        return this.getName() + " (" + this.getSoundStyle() + ")";
    }

    @Override
    public int compareTo(Object o) {
        if (! (o instanceof DjEntity)) {
            return -1;
        }
        
        DjEntity compareObject = (DjEntity) o;
        
        if (getVotes() < compareObject.getVotes()) {
            return +1;
        } else if (getVotes() == compareObject.getVotes()) {
            return 0;
        } else if (getVotes() > compareObject.getVotes()) {
            return -1;
        }
        
        /*
        if (getRank() < compareObject.getRank()) {
            return +1;
        } else if (getRank() == compareObject.getRank()) {
            return 0;
        } else if (getRank() > compareObject.getRank()) {
            return -1;
        }
        */
        
        return -1;
    }
}
