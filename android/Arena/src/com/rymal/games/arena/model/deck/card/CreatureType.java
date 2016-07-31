package com.rymal.games.arena.model.deck.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum CreatureType {
	NO_TYPE,
	COLOSSUS,
	SERAPHIM,
	TITAN,
	TROLL,
	GORGON,
	ETTIN,
	MAGUS,
	UNICORN,
	DAIMON,
	AMAZON,
	CYCLOPS,
	WYRM;
	
	public static CreatureType toCreatureType(String name) {
		for(CreatureType type : CreatureType.values()){
			if (type.name().equals(name)){
				return type;
			}
		}
		return NO_TYPE;
	}

	public static List<CreatureType> createSet(int i) {
		
		List<CreatureType> types = new ArrayList<CreatureType>();
		
		for (CreatureType creatureType : CreatureType.values()) {
			if (creatureType != NO_TYPE) {
				types.add(creatureType);
			}
		}
		
		Collections.shuffle(types);
		
		return types.subList(0, i);
	}
}
