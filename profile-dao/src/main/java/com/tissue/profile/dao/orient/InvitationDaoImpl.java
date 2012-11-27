package com.tissue.profile.dao.orient;

import com.tissue.core.util.OrientIdentityUtil;
import com.tissue.core.util.OrientDataSource;
import com.tissue.core.converter.InvitationConverter;
import com.tissue.domain.profile.Invitation;
import com.tissue.domain.profile.User;
import com.tissue.profile.dao.InvitationDao;
import com.tissue.profile.dao.UserDao;
import com.tissue.profile.dao.DuplicateEmailException;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import com.orientechnologies.orient.core.db.graph.OGraphDatabase;
import com.orientechnologies.orient.core.db.record.OIdentifiable;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.id.ORecordId;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;
import com.orientechnologies.orient.core.sql.OCommandSQL;

@Component
public class InvitationDaoImpl implements InvitationDao {

    @Autowired
    private OrientDataSource dataSource;

    public boolean canInvite(String fromId, String toId) {
        boolean result = true;

        String ridFrom = OrientIdentityUtil.decode(fromId);
        String ridTo = OrientIdentityUtil.decode(toId);

        OGraphDatabase db = dataSource.getDB();
        try {
            OIdentifiable from = new ORecordId(ridFrom);
            OIdentifiable to = new ORecordId(ridTo);

            String[] labels = {"invitation", "friend"};
            Set<OIdentifiable> edges = db.getEdgesBetweenVertexes(from, to, labels);
            if(edges.size() > 0) {
                result = false;
            }
        }
        catch(Exception exc) {
           //to do
            exc.printStackTrace();
        }
        finally {
            db.close();
        }
        return result;
    }

    public void inviteFriend(String fromId, String toId, String content) {
        
        String sql = "create edge Invitation from " + 
                     OrientIdentityUtil.decode(fromId) + 
                     " to " +
                     OrientIdentityUtil.decode(toId) +
                     " set label = 'invitation', status = 'unread', createTime = sysdate(), content = '" +
                     content + "'";

        OGraphDatabase db = dataSource.getDB();
        try {
            OCommandSQL cmd = new OCommandSQL(sql);
            db.command(cmd).execute();
        }
        catch(Exception exc) {
            exc.printStackTrace();
           //to do
        }
        finally {
            db.close();
        }
    }

    public int getInvitationsCount(String viewerId) {
         return getInvitationEdges(viewerId).size();
    }

    public List<Invitation> getInvitations(String viewerId) {
        List<Invitation> invitations = new ArrayList();

        Set<OIdentifiable> edges = getInvitationEdges(viewerId);
        for(OIdentifiable id : edges) {
            ODocument invitationDoc = new ODocument("Invitation", id.getIdentity());
            Invitation invitation = InvitationConverter.buildInvitation(invitationDoc);
            invitations.add(invitation);
        }
        return invitations;     
    }

    private Set<OIdentifiable> getInvitationEdges(String viewerId) {
        Set<OIdentifiable> inEdges = new HashSet();

        String rid = OrientIdentityUtil.decode(viewerId);
        OGraphDatabase db = dataSource.getDB();
        try {
            inEdges = db.getInEdges(new ORecordId(rid), "invitation");
        }
        catch(Exception exc) {
           //to do
            exc.printStackTrace();
        }
        finally {
            db.close();
        }

        return inEdges;
    }

    public boolean declineInvitation(String invitationId) {
        boolean flag = true;
        
        String sql = "update "+ 
                     OrientIdentityUtil.decode(invitationId) + 
                     " set status = 'declined', updateTime = sysdate()";

        OGraphDatabase db = dataSource.getDB();
        try {
            OCommandSQL cmd = new OCommandSQL(sql);
            db.command(cmd).execute();
        }
        catch(Exception exc) {
            flag = false;
            //to do
            exc.printStackTrace();
        }
        finally {
            db.close();
        }
        return flag;
    }


    public boolean acceptInvitation(String invitationId) {
        boolean flag = true;
        
        String rid = OrientIdentityUtil.decode(invitationId);

        String sqlGetRid = "select in.@rid as fromRid, out.@rid as toRid from invitation where @rid = ?";
        String sqlUpdateInvitation = "update invitation set status = 'accepted', updateTime = sysdate() where @rid = ?"; 

        OGraphDatabase db = dataSource.getDB();
        try {

            OCommandSQL cmdGetRid = new OCommandSQL(sqlGetRid);
            List<ODocument> rids = db.command(cmdGetRid).execute(rid);
            if(rids.size() > 0) {
                ODocument doc = rids.get(0);
                String fromRid = doc.field("fromRid", String.class);
                String toRid = doc.field("toRid", String.class);

                 //add friend
                String sqlAddFriend = "create Edge Friend from " + fromRid + " to " + toRid + " set createTime = sysdate()";
                OCommandSQL cmdAddFriend = new OCommandSQL(sqlAddFriend);
                db.command(cmdAddFriend).execute();

                 //update invitation
                OCommandSQL cmd = new OCommandSQL(sqlUpdateInvitation);
                db.command(cmd).execute(OrientIdentityUtil.decode(invitationId));
            }
        }
        catch(Exception exc) {
            flag = false;
            //to do
            exc.printStackTrace();
        }
        finally {
            db.close();
        }
        return flag;
    }

}
