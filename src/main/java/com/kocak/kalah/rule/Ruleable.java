package com.kocak.kalah.rule;

import com.kocak.kalah.model.entity.Game;
import com.kocak.kalah.rule2.Ruleable2;
import org.apache.tomcat.util.digester.Rule;

public interface Ruleable {

    Ruleable applyRule(Game game, int pit);


}
