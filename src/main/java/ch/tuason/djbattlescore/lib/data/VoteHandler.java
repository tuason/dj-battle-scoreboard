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

import ch.tuason.djbattlescore.lib.data.entities.DjEntity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author maesi
 */
public class VoteHandler {
    
    private DataHandler mDataHandler;
    
    private Map<DjEntity, Long> mVotes;
    
    
    public VoteHandler(DataHandler parent) {
        this.mDataHandler = parent;
    }
    
    
    
    private Map<DjEntity, Long> getVotes() {
        if (mVotes == null) {
            mVotes = new HashMap<>();
        }
        return mVotes;
    }
    
    
    public void setUpVotesTable(List<DjEntity> djs) {
        getVotes().clear();
        for (DjEntity dj : djs) {
            getVotes().put(dj, 0L);
        }
    }
    
    
    public void clearAllVotesBackToZero() {
        if (!getVotes().isEmpty()) {
            for (Map.Entry<DjEntity, Long> entry : getVotes().entrySet()) {
                entry.setValue(0L);
            }
        }
    }
    
    
    
    
}
