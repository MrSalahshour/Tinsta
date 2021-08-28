package ir.sharif.math.ap99_2.tinsta_shared.explorer.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import ir.sharif.math.ap99_2.tinsta_shared.timeline.model.Timeline;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.Menu;
import ir.sharif.math.ap99_2.tinsta_shared.user_related.model.User;


import java.io.Serializable;
import java.util.LinkedList;


@JsonIdentityInfo(generator = JSOGGenerator.class,
        property = "id", scope = Explorer.class)
public class Explorer extends Timeline implements Serializable {


    public Explorer() {
    }



}
