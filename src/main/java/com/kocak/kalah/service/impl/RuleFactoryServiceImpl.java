package com.kocak.kalah.service.impl;

import com.kocak.kalah.exception.KalahRuntimeException;
import com.kocak.kalah.model.enums.KalahError;
import com.kocak.kalah.model.enums.RuleType;
import com.kocak.kalah.rule.Rulable;
import com.kocak.kalah.rule.impl.*;
import com.kocak.kalah.service.RuleFactoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RuleFactoryServiceImpl implements RuleFactoryService {

    private final Rule0GameActive rule0GameActive;
    private final Rule1ValidPit rule1ValidPit;
    private final Rule2UsersTurn rule2UsersTurn;
    private final Rule3TokenCountZero rule3TokenCountZero;
    private final Rule4PitKalah rule4PitKalah;
    private final Rule5Sow rule5Sow;
    private final Rule6Collect rule6Collect;
    private final Rule7SwitchSide rule7SwitchSide;
    private final Rule8GameOver rule8GameOver;

    @Override
    public Optional<Rulable> getFirstRule() {
        return Optional.of(rule0GameActive);
    }

    @Override
    public Optional<Rulable> getNextRule(Rulable currentRule, RuleType ruleType) {
        return Optional.ofNullable(switch (currentRule) {
            case Rule0GameActive i -> rule1ValidPit;
            case Rule1ValidPit i -> rule2UsersTurn;
            case Rule2UsersTurn i -> rule3TokenCountZero;
            case Rule3TokenCountZero i -> rule4PitKalah;
            case Rule4PitKalah i -> rule5Sow;
            case Rule5Sow i -> switch (ruleType) {
                case REGULAR -> rule7SwitchSide;
                case LAST_PIT_EMPTY -> rule6Collect;
                case LAST_PIT_KALAH -> rule8GameOver;
            };
            case Rule6Collect i -> rule7SwitchSide;
            case Rule7SwitchSide i -> rule8GameOver;
            case Rule8GameOver i -> null;
            default -> throw new KalahRuntimeException(KalahError.UNKNOWN_RULE);
        });
    }

}

