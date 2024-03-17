package com.kocak.kalah.converter;

public interface DomainToViewConvertable<D, V> {

    V convertToView(D domain);

}
