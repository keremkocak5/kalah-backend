package com.kocak.kalah.service;

import com.kocak.kalah.model.enums.RuleType;
import com.kocak.kalah.rule.Rulable;

import java.util.Optional;

public interface RuleFactoryService {

    Optional<Rulable> getNextRule(Rulable rulable, RuleType ruleType);

    Optional<Rulable> getFirstRule();

}
